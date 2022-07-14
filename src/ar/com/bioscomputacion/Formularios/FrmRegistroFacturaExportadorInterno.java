/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.Cliente;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoFacturaProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CtaCteCliente;
import ar.com.bioscomputacion.Funciones.FacturaCliente;
import ar.com.bioscomputacion.Funciones.ItemFacturadoFacturaCliente;
import ar.com.bioscomputacion.Funciones.Locacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Caco
 */
public class FrmRegistroFacturaExportadorInterno extends javax.swing.JInternalFrame {

    public int codigoCliente, codigoFactura, codigoItemFacturado, codigoMovimientoCtaCte;
    public Double totalMielFacturada;
    public List<Locacion> listaLocacionesDisponibles = new ArrayList<>();
    
    //aca cargo todos los productores registrados en el sistema
    //de ahi voy a cargar en el combo el nombre de los mismos
    public List<Productor> listaProductores = new ArrayList<>();
    
    Double saldoMielOrigen, saldoMielDepositoProductorSeleccionado, saldoMielPaga, saldoMielImpaga, totalMielVenta, saldoMielPagaIngresado, saldoMielImpagaIngresado;
 
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado de la miel vendida
    int codigoLocacionOrigen, codigoProductor;

    int fila = -1;
    int filaItemsFacturados = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroFacturaExportadorInterno() throws SQLException {
        
        initComponents();
        mostrarClientes("");
        ocultarColumnasClientes();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        Calendar cal = new GregorianCalendar();
        dcFechaFactura.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaFactura.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        listaLocacionesDisponibles = cargarListaLocaciones();
        
        for (int i = 0; i<listaLocacionesDisponibles.size(); i++){
            
            cbLocacionOrigen.addItem(listaLocacionesDisponibles.get(i).getNombre_locacion());
            
        }
        
        //hasta que no seleccione una locacion origen no puedo ver el saldo de miel dsponible para trasladar
        tfKilosDisponiblesPagos.setText("0.00");
        tfKilosDisponiblesImpagos.setText("0.00");
        tfTotalKilosVenta.setText("0.00");
        //inicializo campos
        //el campo cantidad de kilos va de la mano del campo totalKilosVenta
        tfCantidadKilos.setText("0.00");
        tfPrecioUnitario.setText("0.00");
        tfImporteTotalFactura.setText("$ 0.00");

        lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR LA VENTA: 0.00 KGS.");

        rbMielPagaDisponible.setSelected(true);
        rbMielImpagaDisponible.setSelected(false);
        rbMielPagaDisponible.setEnabled(false);
        rbMielImpagaDisponible.setEnabled(false);
        tfKilosDisponiblesPagos.setEnabled(false);
        tfKilosDisponiblesImpagos.setEnabled(false);
        
        cbProductores.setEnabled(false);
        
        //se selecciona por defecto el tipo de factura A, ya que es un cliente en el interior del pais
        //y se deshabilita el combo de facturas, ya que es una venta con factura a si o si
        cbTipoFactura.setSelectedIndex(1);
        cbTipoFactura.setEnabled(false);
        
        tExportadoresInternos.requestFocus();
        
    }

    public void mostrarExportadoresInternos(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Cliente cliente = new Cliente();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = cliente.listarExportadoresInternos(buscar);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tExportadoresInternos.setModel(modelo);
                    

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }

    public void mostrarClientes(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Cliente cliente = new Cliente();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = cliente.listarClientes(buscar, "EXPORTADORES");
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tExportadoresInternos.setModel(modelo);
                    

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnasClientes() {

        /*tClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(0).setMinWidth(0);
        tClientes.getColumnModel().getColumn(0).setPreferredWidth(0);*/
        
        /*tClientes.getColumnModel().getColumn(1).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(1).setMinWidth(0);
        tClientes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tExportadoresInternos.getColumnModel().getColumn(2).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(2).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tExportadoresInternos.getColumnModel().getColumn(3).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(3).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tClientes.getColumnModel().getColumn(4).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(4).setMinWidth(0);
        tClientes.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tClientes.getColumnModel().getColumn(5).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(5).setMinWidth(0);
        tClientes.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tClientes.getColumnModel().getColumn(6).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(6).setMinWidth(0);
        tClientes.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        tExportadoresInternos.getColumnModel().getColumn(7).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(7).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        tExportadoresInternos.getColumnModel().getColumn(8).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(8).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        tExportadoresInternos.getColumnModel().getColumn(9).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(9).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(9).setPreferredWidth(0);

        tExportadoresInternos.getColumnModel().getColumn(10).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(10).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(10).setPreferredWidth(0);

        tExportadoresInternos.getColumnModel().getColumn(11).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(11).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(11).setPreferredWidth(0);

        tExportadoresInternos.getColumnModel().getColumn(12).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(12).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(12).setPreferredWidth(0);

        tExportadoresInternos.getColumnModel().getColumn(13).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(13).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(13).setPreferredWidth(0);

        tExportadoresInternos.getColumnModel().getColumn(14).setMaxWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(14).setMinWidth(0);
        tExportadoresInternos.getColumnModel().getColumn(14).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tExportadoresInternos.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tExportadoresInternos.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tExportadoresInternos.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tExportadoresInternos.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.LEFT);
        tExportadoresInternos.getColumnModel().getColumn(6).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tExportadoresInternos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where (categoria = 'DEPOSITO DE ACOPIO PROPIO' or categoria = 'DEPOSITO DE PRODUCTOR') order by codigo_locacion asc");
        
        try{
            
            int i = 0;
            while(rs.next()){
                
                int codigoLocacion = rs.getInt("codigo_locacion");
                String nombreLocacion = rs.getString("nombre_locacion");
                Locacion loc = new Locacion();
                loc.setCodigo_locacion(codigoLocacion);
                loc.setNombre_locacion(nombreLocacion);
                locaciones.add(loc);
                i++;

                
            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return locaciones;
        
    }

    public ArrayList<Productor> cargarListaLocacionesProductores() throws SQLException{
        
        
        //int codigoGenerico = 22;
        ArrayList<Productor> productores = new ArrayList<Productor>();
        Productor productor = new Productor();
        productor.setCod_productor(-1);
        productor.setNombre("SELECCIONAR");
        productores.add(productor);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select cod_productor, nombre from productor p join persona r on p.cod_persona = r.cod_persona where p.cod_productor <> '22' order by p.cod_productor asc");
        
        try{
            
            int i=0;
            while(rs.next()){
                
                int codigoProductor = rs.getInt("cod_productor");
                String nombreProductor = rs.getString("nombre");
                Productor prod = new Productor();
                prod.setCod_productor(codigoProductor);
                prod.setNombre(nombreProductor);
                productores.add(prod);  
                
                i++;
                
            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return productores;
        
    }
    
    public ArrayList<String> cargarComboLocaciones() throws SQLException{
        
        ArrayList<String> locaciones = new ArrayList<String>();
        
        //el primer item debe ser "SELECCIONAR"
        locaciones.add("SELECCIONAR");
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select nombre_locacion from locacion");
        
        try{
            
            while(rs.next()){
                
                locaciones.add(rs.getString("nombre_locacion"));
                
            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return locaciones;
        
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        bgOpcionesMiel = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tpFactura = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tExportadoresInternos = tExportadoresInternos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tfIDCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfNombreCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfDocumentoCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfNacionalidadProductor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        cbLocacionOrigen = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        lStockOrigen = new javax.swing.JLabel();
        lStockOrigen1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbProductores = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        lStockDepositoProductor = new javax.swing.JLabel();
        lStockProductor = new javax.swing.JLabel();
        lMielDisponibleTraslado = new javax.swing.JLabel();
        rbMielPagaDisponible = new javax.swing.JRadioButton();
        rbMielImpagaDisponible = new javax.swing.JRadioButton();
        tfKilosDisponiblesPagos = new javax.swing.JTextField();
        tfKilosDisponiblesImpagos = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfTotalKilosVenta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cbTipoFactura = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        tfCantidadKilos = new javax.swing.JTextField();
        tfPrecioUnitario = new javax.swing.JTextField();
        tfImporteTotalFactura = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tfTambores = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("REGISTRO DE FACTURA A EXPORTADOR INTERNO - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpFactura.setBackground(new java.awt.Color(51, 84, 111));
        tpFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tpFactura.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tpFacturaComponentAdded(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("SELECCIONE EL EXPORTADOR CORRESPONDIENTE:");
        jLabel2.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUSCAR POR NOMBRE:");

        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

        tExportadoresInternos.setBackground(new java.awt.Color(36, 33, 33));
        tExportadoresInternos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tExportadoresInternos.setForeground(new java.awt.Color(207, 207, 207));
        tExportadoresInternos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tExportadoresInternos.setOpaque(true);
        tExportadoresInternos.setRowHeight(20);
        tExportadoresInternos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tExportadoresInternosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tExportadoresInternos);

        tfIDCliente.setEditable(false);
        tfIDCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfIDCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfIDCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID CLIENTE:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreCliente.setEditable(false);
        tfNombreCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfNombreCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNombreCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoCliente.setEditable(false);
        tfDocumentoCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfDocumentoCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfDocumentoCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("NACIONALIDAD:");

        tfNacionalidadProductor.setEditable(false);
        tfNacionalidadProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfNacionalidadProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNacionalidadProductor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfNacionalidadProductor)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(tfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(tfDocumentoCliente))))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(tfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDocumentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNacionalidadProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del exportador interno", jPanel2);

        jPanel4.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("SELECCIONE LA LOCACION DESDE LA QUE SE TRASLADARA LA MIEL:");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 255, 102));
        jLabel31.setText("LOCACION ORIGEN DE LA MIEL VENDIDA:");

        cbLocacionOrigen.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionOrigen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbLocacionOrigen.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionOrigen.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocacionOrigenMouseClicked(evt);
            }
        });
        cbLocacionOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionOrigenActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 255, 102));
        jLabel25.setText("VALIDAR STOCK:");
        jLabel25.setToolTipText("");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        lStockOrigen.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockOrigen.setForeground(new java.awt.Color(102, 255, 102));
        lStockOrigen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockOrigen.setText("0.00");

        lStockOrigen1.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen1.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockOrigen1.setForeground(new java.awt.Color(102, 255, 102));
        lStockOrigen1.setText("KGS.");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 255, 255));
        jLabel28.setText("VER PRODUCTORES CON MIEL ACOPIADA:");
        jLabel28.setToolTipText("");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        cbProductores.setBackground(new java.awt.Color(153, 255, 255));
        cbProductores.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cbProductores.setForeground(new java.awt.Color(207, 207, 207));
        cbProductores.setPreferredSize(new java.awt.Dimension(136, 19));
        cbProductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoresActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 255, 255));
        jLabel27.setText("VALIDAR STOCK:");
        jLabel27.setToolTipText("");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        lStockDepositoProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockDepositoProductor.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockDepositoProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockDepositoProductor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockDepositoProductor.setText("0.00");

        lStockProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockProductor.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockProductor.setText("KGS.");

        lMielDisponibleTraslado.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lMielDisponibleTraslado.setForeground(new java.awt.Color(255, 255, 255));
        lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL VENTA AL EXPORTADOR:");

        bgOpcionesMiel.add(rbMielPagaDisponible);
        rbMielPagaDisponible.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbMielPagaDisponible.setForeground(new java.awt.Color(255, 255, 255));
        rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
        rbMielPagaDisponible.setOpaque(false);
        rbMielPagaDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMielPagaDisponibleActionPerformed(evt);
            }
        });
        rbMielPagaDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbMielPagaDisponibleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbMielPagaDisponibleKeyReleased(evt);
            }
        });

        bgOpcionesMiel.add(rbMielImpagaDisponible);
        rbMielImpagaDisponible.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbMielImpagaDisponible.setForeground(new java.awt.Color(255, 255, 255));
        rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
        rbMielImpagaDisponible.setOpaque(false);
        rbMielImpagaDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMielImpagaDisponibleActionPerformed(evt);
            }
        });
        rbMielImpagaDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbMielImpagaDisponibleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbMielImpagaDisponibleKeyReleased(evt);
            }
        });

        tfKilosDisponiblesPagos.setBackground(new java.awt.Color(0, 0, 0));
        tfKilosDisponiblesPagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosDisponiblesPagos.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosDisponiblesPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosDisponiblesPagosActionPerformed(evt);
            }
        });
        tfKilosDisponiblesPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesPagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesPagosKeyTyped(evt);
            }
        });

        tfKilosDisponiblesImpagos.setBackground(new java.awt.Color(0, 0, 0));
        tfKilosDisponiblesImpagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosDisponiblesImpagos.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosDisponiblesImpagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosDisponiblesImpagosActionPerformed(evt);
            }
        });
        tfKilosDisponiblesImpagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesImpagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesImpagosKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TOTAL KGS. A VENDER:");

        tfTotalKilosVenta.setEditable(false);
        tfTotalKilosVenta.setBackground(new java.awt.Color(0, 0, 0));
        tfTotalKilosVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTotalKilosVenta.setForeground(new java.awt.Color(255, 255, 255));
        tfTotalKilosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalKilosVentaActionPerformed(evt);
            }
        });
        tfTotalKilosVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTotalKilosVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTotalKilosVentaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbProductores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbLocacionOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lStockOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockOrigen1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lStockDepositoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockProductor))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lMielDisponibleTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(119, 119, 119))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rbMielPagaDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbMielImpagaDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfKilosDisponiblesImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfKilosDisponiblesPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfTotalKilosVenta))))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(3, 3, 3)
                .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStockOrigen)
                    .addComponent(jLabel25)
                    .addComponent(lStockOrigen1))
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lStockProductor)
                    .addComponent(lStockDepositoProductor))
                .addGap(18, 18, 18)
                .addComponent(lMielDisponibleTraslado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tfKilosDisponiblesPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKilosDisponiblesImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMielImpagaDisponible)
                            .addComponent(tfTotalKilosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rbMielPagaDisponible))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        tpFactura.addTab("Validacion del origen de la miel", jPanel4);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA FACTURA:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DE LA FACTURA:");

        dcFechaFactura.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaFactura.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("N°:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEMS FACTURADOS:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("FACTURA:");

        cbTipoFactura.setBackground(new java.awt.Color(36, 33, 33));
        cbTipoFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoFactura.setForeground(new java.awt.Color(207, 207, 207));
        cbTipoFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURA A", "FACTURA E" }));
        cbTipoFactura.setPreferredSize(new java.awt.Dimension(136, 19));
        cbTipoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoFacturaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ITEM A FACTURAR:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CANTIDAD KGS.:");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("PRECIO UNITARIO:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IMPORTE TOTAL:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(204, 255, 255));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setText(" KGS. DE MIEL");

        tfCantidadKilos.setEditable(false);
        tfCantidadKilos.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadKilos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadKilos.setForeground(new java.awt.Color(255, 255, 255));
        tfCantidadKilos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCantidadKilosActionPerformed(evt);
            }
        });
        tfCantidadKilos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCantidadKilosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCantidadKilosKeyTyped(evt);
            }
        });

        tfPrecioUnitario.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioUnitario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitario.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyTyped(evt);
            }
        });

        tfImporteTotalFactura.setEditable(false);
        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CONVERSION A TAMBORES:");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(204, 255, 255));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(204, 255, 255));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbTipoFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel18)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel19)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel23)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(7, 7, 7)
                        .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel18))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos de la factura", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR FACTURA");
        rdbrRegistrar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrarActionPerformed(evt);
            }
        });

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("SALIR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpFactura)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura
        
        Boolean informacionFactura = (tfNumeroComprobante.getText().length() == 0 || tfImporteTotalFactura.getText().length() == 0 || tfImporteTotalFactura.getText().equals("$ 0.00") || tfImporteTotalFactura.getText().equals("$ 0.0"));
        
        if (tfIDCliente.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar el exportador interno al cual se le realizo la venta de miel.", "REGISTRO DE FACTURA A EXPORTADOR INTERNO", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tExportadoresInternos.requestFocus();
            return;
            
        }
        
        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente a la factura se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE FACTURA A EXPORTADOR INTERNO", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(1);
            tfNumeroComprobante.requestFocus();
            return;
            
        }
        
        //se procede al registro de la factura correspondiente a la venta de miel al exportador seleccionado
        
        //obtengo las fechas de factura y de vencimiento del pago de la misma
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaFactura.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;
        cal2 = dcFechaVencimiento.getCalendar();
        //ffecha de vencimiento de la factura
        d2 = cal2.get(Calendar.DAY_OF_MONTH);
        m2 = cal2.get(Calendar.MONTH);
        a2 = cal2.get(Calendar.YEAR) - 1900;

        Double importeFactura = Double.parseDouble(tfCantidadKilos.getText()) * Double.parseDouble(tfPrecioUnitario.getText());
        Double cantidadMielFacturada = Double.parseDouble(tfCantidadKilos.getText());
        String tipoFactura = String.valueOf(cbTipoFactura.getSelectedItem());
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());
        String origenSeleccionado= "";
        Locacion locacion = new Locacion();
        String categoriaLocacionOrigen = "";
        categoriaLocacionOrigen = locacion.mostrarCategoriaLocacion(codigoLocacionOrigen);
        
        if (categoriaLocacionOrigen.equals("DEPOSITO DE PRODUCTOR")){
            
            origenSeleccionado = "MIEL DEPOSITADA";
                    
        }
        else{
            
            origenSeleccionado = "MIEL NO DEPOSITADA";
            
        }
        
        FacturaCliente factura = new FacturaCliente(tipoFactura, numeroComprobante, codigoMovimientoCtaCte, codigoCliente, new Date(a1, m1, d1), new Date(a2, m2, d2), importeFactura, cantidadMielFacturada);
        
        if (factura.registrarFacturaCliente(factura)){
            
            //ahora se guarda el movimiento correspondiente a la factura, en la cta. cte. de la empresa con el productor
            codigoFactura = factura.mostrarIdFacturaCliente();
            CtaCteCliente ctacteCliente = new CtaCteCliente(codigoCliente, codigoMovimientoCtaCte, new Date(a1, m1, d1), tipoFactura, codigoFactura, numeroComprobante, cantidadMielFacturada, importeFactura, 0.00, importeFactura, "PENDIENTE", "");
            ctacteCliente.registrarMovimientoCtaCteCliente(ctacteCliente);
            
            //A NIVEL STOCK DE MIEL, SE DEBEN DIFERENCIAR LAS VENTAS DE MIEL PAGA Y MIEL IMPAGA
            //YA QUE ES NECESARIO REGISTRAR DOS MOVIMIENTOS DE STOCK, UNO POR CADA UNA DE LAS VENTAS NOMBRADAS
            if (saldoMielPagaIngresado != 0){

                //se registra el movimiento de stock correspondiente a la venta de miel paga
                StockRealMiel stockMiel = new StockRealMiel();
                stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
                //se trata d euna venta dentro del pais
                stockMiel.setTipo_movimiento("VENTA");
                stockMiel.setComprobante_asociado("FACTURA E");
                stockMiel.setId_comprobante_asociado(codigoFactura);
                stockMiel.setNumero_comprobante_asociado(String.valueOf(numeroComprobante));
                stockMiel.setCantidad_miel(saldoMielPagaIngresado);
                stockMiel.setLocacion_miel(codigoLocacionOrigen);

                if (origenSeleccionado.equals("MIEL DEPOSITADA")){

                    //se trata de un traslado de miel stockeada en la locacion de algun productor
                    //se debe descontar el stock global de la locacion "LOCACION DEL PRODUCTOR"
                    stockMiel.setMiel_deposito_productor(codigoProductor);


                }

                stockMiel.setEstado_compra("FACTURADA");
                stockMiel.registrarMovimientoStock(stockMiel);
                
            }

            if (saldoMielImpagaIngresado != 0){

                //se registra el movimiento de stock correspondiente a la venta de miel impaga
                StockRealMiel stockMiel = new StockRealMiel();
                stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
                //se trata d euna venta dentro del pais
                stockMiel.setTipo_movimiento("VENTA");
                stockMiel.setComprobante_asociado("FACTURA E");
                stockMiel.setId_comprobante_asociado(codigoFactura);
                stockMiel.setNumero_comprobante_asociado(String.valueOf(numeroComprobante));
                stockMiel.setCantidad_miel(saldoMielImpagaIngresado);
                stockMiel.setLocacion_miel(codigoLocacionOrigen);

                if (origenSeleccionado.equals("MIEL DEPOSITADA")){

                    //se trata de un traslado de miel stockeada en la locacion de algun productor
                    //se debe descontar el stock global de la locacion "LOCACION DEL PRODUCTOR"
                    stockMiel.setMiel_deposito_productor(codigoProductor);


                }
                
                stockMiel.setEstado_compra("SIN FACTURAR");
                stockMiel.registrarMovimientoStock(stockMiel);

                JOptionPane.showMessageDialog(null, "La factura ha sido registrada exitosamente.","REGISTRO DE FACTURA A EXPORTADOR INTERNO", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

            }
            
        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar la factura.","REGISTRO DE FACTURA A EXPORTADOR INTERNO", JOptionPane.ERROR_MESSAGE);

        }
            
        this.dispose();
            
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        FacturaProductor factura = new FacturaProductor();
        factura.eliminarFacturaProductor(codigoFactura);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
        /*for (int i = 0; i < tItemsFacturados.getRowCount(); i++) {
            saldo = saldo + Double.valueOf(tItemsFacturados.getValueAt(i, 3).toString());
        }*/
        
        tfImporteTotalFactura.setText(formateador.format(saldo));
    }
    
    //LOS 3 METODOS A CONTINUACION DEVUELVEN:
    //1) STOCK TOTAL DE MIEL EN LA LOCACION
    //2) STOCK TOTAL DE MIEL PAGA EN LA LOCACION
    //3) STOCK TOTAL DE MIEL IMPAGA EN LA LOCACION
    
    public double calcularStockTotalMielLocacion(int codigoLocacion) {

        double ingresoMiel, egresoMiel, saldoTotalMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMiel = stock.obtenerDetalleIngresoMiel(codigoLocacion);
        egresoMiel = stock.obtenerDetalleEgresoMiel(codigoLocacion);
        
        saldoTotalMiel = ingresoMiel - egresoMiel;
        
        return saldoTotalMiel;
        
    }
    
    public double calcularStockMielPagaLocacion(int codigoLocacion) {

        double ingresoMielPaga, egresoMielPaga, saldoTotalMielPaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(codigoLocacion);
        egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(codigoLocacion);
        
        saldoTotalMielPaga = ingresoMielPaga - egresoMielPaga;
        
        return saldoTotalMielPaga;
        
    }
    
    public double calcularStockMielImpagaLocacion(int codigoLocacion) {

        double ingresoMielImpaga, egresoMielImpaga, saldoTotalMielImpaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(codigoLocacion);
        egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(codigoLocacion);
        
        saldoTotalMielImpaga = ingresoMielImpaga - egresoMielImpaga;
        
        return saldoTotalMielImpaga;
        
    }
    
    //LOS 3 METODOS A CONTINUACION DEVUELVEN:
    //1) STOCK TOTAL DE MIEL EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    //2) STOCK TOTAL DE MIEL PAGA EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    //3) STOCK TOTAL DE MIEL IMPAGA EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    
    public double calcularStocktTotalMielLocacionDepositoProductor(int codigoProductor) {

        double ingresoMiel, egresoMiel, saldoTotalMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMiel = stock.obtenerDetalleIngresoMielLocacionProductor(codigoProductor);
        egresoMiel = stock.obtenerDetalleEgresoMielLocacionProductor(codigoProductor);
        
        saldoTotalMiel = ingresoMiel - egresoMiel;
        
        return saldoTotalMiel;
        
    }
    
    public double calcularStocktTotalMielPagaLocacionDepositoProductor(int codigoProductor) {

        double ingresoMielPaga, egresoMielPaga, saldoTotalMielPaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielPaga = stock.obtenerDetalleIngresoMielPagaLocacionProductor(codigoProductor);
        egresoMielPaga = stock.obtenerDetalleEgresoMielPagaLocacionProductor(codigoProductor);
        
        saldoTotalMielPaga = ingresoMielPaga - egresoMielPaga;
        
        return saldoTotalMielPaga;
        
    }
    
    public double calcularStocktTotalMielImpagaLocacionDepositoProductor(int codigoProductor) {

        double ingresoMielImpaga, egresoMielImpaga, saldoTotalMielImpaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpagaLocacionProductor(codigoProductor);
        egresoMielImpaga = stock.obtenerDetalleEgresoMielImpagaLocacionProductor(codigoProductor);
        
        saldoTotalMielImpaga = ingresoMielImpaga - egresoMielImpaga;
        
        return saldoTotalMielImpaga;
        
    }
    
    private void tExportadoresInternosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tExportadoresInternosMouseClicked

        fila = tExportadoresInternos.rowAtPoint(evt.getPoint());
        codigoCliente = Integer.parseInt(tExportadoresInternos.getValueAt(fila, 0).toString());
        CtaCteCliente ctacteCliente = new CtaCteCliente();
        codigoMovimientoCtaCte = ctacteCliente.mostrarIdMovimiento(codigoCliente)+1;
        
        //cada vez que se hace click sobre la grilla se muestran en los campos debajo
        //los datos del cliente exportador interno
        //correspondiente a la fila de la grilla cliqueada
        tfIDCliente.setText(tExportadoresInternos.getValueAt(fila, 0).toString());
        tfNombreCliente.setText(tExportadoresInternos.getValueAt(fila, 1).toString());
        tfDocumentoCliente.setText(tExportadoresInternos.getValueAt(fila, 2).toString());
        tfNacionalidadProductor.setText(tExportadoresInternos.getValueAt(fila, 4).toString());
        
    }//GEN-LAST:event_tExportadoresInternosMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarExportadoresInternos(tfBusquedaPorNombre.getText());
        ocultarColumnasClientes();
        
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void cbTipoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoFacturaActionPerformed

    private void tpFacturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpFacturaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpFacturaComponentAdded

    private void cbLocacionOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionOrigenActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionOrigen.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocacionesDisponibles.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();

        }
        
    }//GEN-LAST:event_cbLocacionOrigenActionPerformed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        //ademas, se deben llenar los campos de kilos disponibles pagos e impagos reflejando
        //la cant de miel existente
        //en la locacion origen seleccionada
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //La locacon origen sera un productor seleccionado en el cmobo de los productores
            if (cbLocacionOrigen.getSelectedItem().toString().equals("MIEL EN DEPOSITOS DE PRODUCTORES")){

                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PRODUCTOR EN EL COMBO DE PRODUCTORES Y LUEGO Y VALIDAR LA MIEL ACOPIADA EN SUS DEPOSITOS.", "REGISTRO DE FACTURA A EXPORTADOR INTERNO", JOptionPane.INFORMATION_MESSAGE);
                //si es miel depositada en una locacion de productores se debe permitir
                //ver al menos cuanta miel hay en depositos de productores pero si se debe seleccionar
                //un productor y validar la miel en sus depositos apra poder cargar el traslado con cantidades
                codigoLocacionOrigen = listaLocacionesDisponibles.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
                saldoMielOrigen = calcularStockTotalMielLocacion(codigoLocacionOrigen);
                lStockOrigen.setText(String.valueOf(saldoMielOrigen));
                cbProductores.requestFocus();
                
            }
            //La locacion origen no es una locacion de productor
            else{

                //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
                //caso contrario busco el codigo asociado al nombre seleccionado
                codigoLocacionOrigen = listaLocacionesDisponibles.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
                //ademas muestro el stock fisico discponible en cada una de las locaciones (pago e impago)
                //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen

                //valores reales y originales
                saldoMielOrigen = calcularStockTotalMielLocacion(codigoLocacionOrigen);
                saldoMielPaga = calcularStockMielPagaLocacion(codigoLocacionOrigen);
                saldoMielImpaga = calcularStockMielImpagaLocacion(codigoLocacionOrigen);

                //valores que iran cambiando a medida que se tocan los numeros a trasladar
                //sirven ademas para realizar controles y filtros
                totalMielVenta = saldoMielOrigen;
                saldoMielPagaIngresado = saldoMielPaga;
                saldoMielImpagaIngresado = saldoMielImpaga;

                lStockOrigen.setText(String.valueOf(saldoMielOrigen));
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPagaIngresado));
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpagaIngresado));
                tfTotalKilosVenta.setText(String.valueOf(totalMielVenta));
                tfCantidadKilos.setText(String.valueOf(totalMielVenta));

                lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL TRASLADO: "+String.valueOf(saldoMielOrigen)+" KGS.");
                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: "+saldoMielPaga+" KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: "+saldoMielImpaga+" KGS.");
                rbMielPagaDisponible.setEnabled(true);
                rbMielImpagaDisponible.setEnabled(true);
                rbMielPagaDisponible.setSelected(true);
                tfKilosDisponiblesPagos.setEnabled(true);
                tfKilosDisponiblesImpagos.setEnabled(false);
                tfKilosDisponiblesPagos.requestFocus();

            }
            
        }
        else{
            
            lStockOrigen.setText("0.00");
            tfKilosDisponiblesPagos.setText("0.00");
            tfKilosDisponiblesImpagos.setText("0.00");
            tfTotalKilosVenta.setText("0.00");
            tfCantidadKilos.setText("0.00");
            
            lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR LA VENTA: 0.00 KGS.");
            rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
            rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
            rbMielPagaDisponible.setEnabled(false);
            rbMielImpagaDisponible.setEnabled(false);
            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(false);
            cbLocacionOrigen.requestFocus();

        }
        
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked

        //obtengo la categoria de la locacion origen

        if (cbLocacionOrigen.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocacionesDisponibles.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();

            //obtengo la categoria de la locacion origen
            Locacion locacion = new Locacion();
            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacionOrigen);

            //si la categoria de la locacion origen es "DEPOSITO DE PRODUCTOR"
            //se habilita el combo productores y se muestran todos los productores cargados en el sistema
            //para poder validar el stock de miel en cada uno de ellos
            if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

                //se habilita el combo y se cargan los productores en el mismo
                try {
                    
                    listaProductores = cargarListaLocacionesProductores();
                    
                } catch (SQLException ex) {
                    
                    Logger.getLogger(FrmRegistroTraslado.class.getName()).log(Level.SEVERE, null, ex);
                    
                }

                //primero limpio cargas anteriores del combo
                cbProductores.removeAllItems();

                for (int i = 0; i<listaProductores.size(); i++){

                    cbProductores.addItem(listaProductores.get(i).getNombre());

                }

                lStockDepositoProductor.setText("0.00");
                cbProductores.setEnabled(true);
                cbProductores.setSelectedIndex(0);

            }
            else{

                //cbProductores.removeAllItems();
                //lStockDepositoProductor.setText("0.00");
                //cbProductores.setEnabled(false);

            }

        }

    }//GEN-LAST:event_jLabel28MouseClicked

    private void cbProductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductoresActionPerformed

        // cada vez que selecciona un nombre de productor, se busca su codigo de productor en la lista de productores
        // y se almacena dicho codigo en la variable correspondiente

        if (cbProductores.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoProductor = listaProductores.get(cbProductores.getSelectedIndex()).getCod_productor();

        }
        
    }//GEN-LAST:event_cbProductoresActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked

        // cada vez que selecciona un productor, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        
        //si este como esta habilitado significa que se esta intentando trasladar miel desde la locacion
        //de un productor
        if (cbProductores.isEnabled()){
            
            double saldoMiel = 0.00;

            //no se encuentra seleccionado ningun productor en el combo
            if (cbProductores.getSelectedIndex() != 0){

                //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
                //caso contrario busco el codigo asociado al nombre seleccionado
                codigoProductor = listaProductores.get(cbProductores.getSelectedIndex()).getCod_productor();
                //ademas muestro el stock fisico discponible en cada una de las locaciones (pago e impago)
                //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen

                //valores reales y originales
                saldoMielOrigen = calcularStocktTotalMielLocacionDepositoProductor(codigoProductor);
                saldoMielPaga = calcularStocktTotalMielPagaLocacionDepositoProductor(codigoProductor);
                saldoMielImpaga = calcularStocktTotalMielImpagaLocacionDepositoProductor(codigoProductor);

                //valores que iran cambiando a medida que se tocan los numeros a trasladar
                //sirven ademas para realizar controles y filtros
                totalMielVenta = saldoMielOrigen;
                saldoMielPagaIngresado = saldoMielPaga;
                saldoMielImpagaIngresado = saldoMielImpaga;

                lStockDepositoProductor.setText(String.valueOf(saldoMielOrigen));
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPagaIngresado));
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpagaIngresado));
                tfTotalKilosVenta.setText(String.valueOf(totalMielVenta));
                tfCantidadKilos.setText(String.valueOf(totalMielVenta));

                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: "+saldoMielPaga+" KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: "+saldoMielImpaga+" KGS.");
                rbMielPagaDisponible.setEnabled(true);
                rbMielImpagaDisponible.setEnabled(true);
                rbMielPagaDisponible.setSelected(true);
                tfKilosDisponiblesPagos.setEnabled(true);
                tfKilosDisponiblesImpagos.setEnabled(false);
                tfKilosDisponiblesPagos.requestFocus();

            }
            else{

                lStockDepositoProductor.setText("0.00");
                tfKilosDisponiblesPagos.setText("0.00");
                tfKilosDisponiblesImpagos.setText("0.00");
                tfTotalKilosVenta.setText("0.00");
                tfCantidadKilos.setText("0.00");

                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
                rbMielPagaDisponible.setEnabled(false);
                rbMielImpagaDisponible.setEnabled(false);
                tfKilosDisponiblesPagos.setEnabled(false);
                tfKilosDisponiblesImpagos.setEnabled(false);
                cbLocacionOrigen.requestFocus();

            }
            
        }
        
    }//GEN-LAST:event_jLabel27MouseClicked

    private void cbLocacionOrigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionOrigenMouseClicked

        //si en el combo origen no esta seleccionado el item MIEL EN DEPOSITO
        //se debe inhabilitar el combo de los productores debajo
        if (cbLocacionOrigen.getSelectedItem() != "MIEL EN DEPOSITOS DE PRODUCTORES"){
            
            cbProductores.removeAllItems();
            cbProductores.setEnabled(false);
            lStockDepositoProductor.setText("0.00");
                    
        }
        
    }//GEN-LAST:event_cbLocacionOrigenMouseClicked

    private void tfCantidadKilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosActionPerformed

    private void tfCantidadKilosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyReleased

        //VER COMO PUEDO REDONDEAR!
        if (tfCantidadKilos.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double tambores = kilos / 300;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
            Double lotes = kilos / 21000;
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfImporteTotalFactura.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfTambores.setText("0 TAMBORES");
            tfLotes.setText("0 LOTES");
            tfCantidadKilos.setText("0.00");
            tfImporteTotalFactura.setText("$ 0.00");

        }
    }//GEN-LAST:event_tfCantidadKilosKeyReleased

    private void tfCantidadKilosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyTyped

        char c = evt.getKeyChar();

        if (tfCantidadKilos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfCantidadKilosKeyTyped

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased

        if (tfPrecioUnitario.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfImporteTotalFactura.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfPrecioUnitario.setText("0.00");
            tfImporteTotalFactura.setText("$ 0.00");

        }
    }//GEN-LAST:event_tfPrecioUnitarioKeyReleased

    private void tfPrecioUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyTyped

        char c = evt.getKeyChar();

        if (tfPrecioUnitario.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfPrecioUnitarioKeyTyped

    private void rbMielPagaDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleActionPerformed

        if (rbMielPagaDisponible.isSelected()){

            tfKilosDisponiblesPagos.setEnabled(true);
            tfKilosDisponiblesImpagos.setEnabled(false);
            tfKilosDisponiblesPagos.requestFocus();

        }
        else{

            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(true);
            tfKilosDisponiblesImpagos.requestFocus();

        }
    }//GEN-LAST:event_rbMielPagaDisponibleActionPerformed

    private void rbMielPagaDisponibleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleKeyPressed

    }//GEN-LAST:event_rbMielPagaDisponibleKeyPressed

    private void rbMielPagaDisponibleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleKeyReleased

    }//GEN-LAST:event_rbMielPagaDisponibleKeyReleased

    private void rbMielImpagaDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleActionPerformed

        if (rbMielImpagaDisponible.isSelected()){

            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(true);
            tfKilosDisponiblesImpagos.requestFocus();

        }
        else{

            tfKilosDisponiblesPagos.setEnabled(true);
            tfKilosDisponiblesImpagos.setEnabled(false);
            tfKilosDisponiblesPagos.requestFocus();

        }
    }//GEN-LAST:event_rbMielImpagaDisponibleActionPerformed

    private void rbMielImpagaDisponibleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleKeyPressed

    }//GEN-LAST:event_rbMielImpagaDisponibleKeyPressed

    private void rbMielImpagaDisponibleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleKeyReleased

    }//GEN-LAST:event_rbMielImpagaDisponibleKeyReleased

    private void tfKilosDisponiblesPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosActionPerformed

    }//GEN-LAST:event_tfKilosDisponiblesPagosActionPerformed

    private void tfKilosDisponiblesPagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vaco (si 0 pero no vacio)
        //2) que no se ingrese un valor superior a la miel paga disponible en la locacion origen
        //3) que no se ingrese un valor que sumado al segundo valor supera al total de miel disponible en la locacion origen

        if (tfKilosDisponiblesPagos.getText().length() == 0){

            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
            tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
            Double kilosTotalesVenta = saldoMielPaga + saldoMielImpagaIngresado;
            tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
            tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
            tfKilosDisponiblesPagos.requestFocus();

        }
        else{

            Double kilosPagosIngresados = Double.valueOf(tfKilosDisponiblesPagos.getText());

            if (kilosPagosIngresados > saldoMielPaga){

            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
                Double kilosTotalesVenta = saldoMielPaga + saldoMielImpagaIngresado;
                tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
                tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
                tfKilosDisponiblesPagos.requestFocus();

            }
            else{

                if (kilosPagosIngresados + saldoMielImpagaIngresado > saldoMielOrigen){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
                    tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
                    Double kilosTotalesVenta = saldoMielPaga + saldoMielImpagaIngresado;
                    tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
                    tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
                    tfKilosDisponiblesPagos.requestFocus();

                }
                //el valor ingresado supero todos los filtros es correcto
                else{

                    saldoMielPagaIngresado = kilosPagosIngresados;
                    totalMielVenta = saldoMielPagaIngresado + saldoMielImpagaIngresado;
                    tfTotalKilosVenta.setText(String.valueOf(totalMielVenta));
                    tfCantidadKilos.setText(String.valueOf(totalMielVenta));
                    tfKilosDisponiblesPagos.requestFocus();

                }

            }

        }
    }//GEN-LAST:event_tfKilosDisponiblesPagosKeyReleased

    private void tfKilosDisponiblesPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosDisponiblesPagos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfKilosDisponiblesPagosKeyTyped

    private void tfKilosDisponiblesImpagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosActionPerformed

    }//GEN-LAST:event_tfKilosDisponiblesImpagosActionPerformed

    private void tfKilosDisponiblesImpagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vaco (si 0 pero no vacio)
        //2) que no se ingrese un valor superior a la miel paga disponible en la locacion origen
        //3) que no se ingrese un valor que sumado al segundo valor supera al total de miel disponible en la locacion origen

        //ANDA BIEN PERO FALTA
        //FALAT QUE CADA VEZ QUE PISO EL TEXTO CON EL VALRO DE SALDOMIELPAGA TAMB HAGA LA SUMA ENEL CAMPO TRASLADO TOTAL

        if (tfKilosDisponiblesImpagos.getText().length() == 0){

            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
            tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
            Double kilosTotalesVenta = saldoMielImpaga + saldoMielPagaIngresado;
            tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
            tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
            tfKilosDisponiblesImpagos.requestFocus();

        }
        //seguir cambiando pago por impago
        else{

            Double kilosImpagosIngresados = Double.valueOf(tfKilosDisponiblesImpagos.getText());

            if (kilosImpagosIngresados > saldoMielImpaga){

                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
                Double kilosTotalesVenta = saldoMielImpaga + saldoMielPagaIngresado;
                tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
                tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
                tfKilosDisponiblesImpagos.requestFocus();

            }
            else{

                if (kilosImpagosIngresados + saldoMielPagaIngresado > saldoMielOrigen){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE FACTURA A EXPORTADOR INTERNO",JOptionPane.ERROR_MESSAGE);
                    tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
                    Double kilosTotalesVenta = saldoMielImpaga + saldoMielPagaIngresado;
                    tfTotalKilosVenta.setText(String.valueOf(kilosTotalesVenta));
                    tfCantidadKilos.setText(String.valueOf(kilosTotalesVenta));
                    tfKilosDisponiblesImpagos.requestFocus();

                }
                //el valor ingresado supero todos los filtros es correcto
                else{

                    //JOptionPane.showMessageDialog(null, "CANTIDAD CORRECTA");
                    saldoMielImpagaIngresado = kilosImpagosIngresados;
                    totalMielVenta = saldoMielImpagaIngresado + saldoMielPagaIngresado;
                    tfTotalKilosVenta.setText(String.valueOf(totalMielVenta));
                    tfCantidadKilos.setText(String.valueOf(totalMielVenta));
                    tfKilosDisponiblesImpagos.requestFocus();

                }

            }

        }
    }//GEN-LAST:event_tfKilosDisponiblesImpagosKeyReleased

    private void tfKilosDisponiblesImpagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosDisponiblesImpagos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfKilosDisponiblesImpagosKeyTyped

    private void tfTotalKilosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalKilosVentaActionPerformed
    }//GEN-LAST:event_tfTotalKilosVentaActionPerformed

    private void tfTotalKilosVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTotalKilosVentaKeyReleased
    }//GEN-LAST:event_tfTotalKilosVentaKeyReleased

    private void tfTotalKilosVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTotalKilosVentaKeyTyped
    }//GEN-LAST:event_tfTotalKilosVentaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcionesMiel;
    public javax.swing.JComboBox<String> cbLocacionOrigen;
    public javax.swing.JComboBox<String> cbProductores;
    public javax.swing.JComboBox<String> cbTipoFactura;
    public com.toedter.calendar.JDateChooser dcFechaFactura;
    public com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lMielDisponibleTraslado;
    private javax.swing.JLabel lStockDepositoProductor;
    private javax.swing.JLabel lStockOrigen;
    private javax.swing.JLabel lStockOrigen1;
    private javax.swing.JLabel lStockProductor;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private javax.swing.JRadioButton rbMielImpagaDisponible;
    private javax.swing.JRadioButton rbMielPagaDisponible;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTable tExportadoresInternos;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDocumentoCliente;
    public javax.swing.JTextField tfIDCliente;
    public javax.swing.JTextField tfImporteTotalFactura;
    public static javax.swing.JTextField tfKilosDisponiblesImpagos;
    public static javax.swing.JTextField tfKilosDisponiblesPagos;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNacionalidadProductor;
    public javax.swing.JTextField tfNombreCliente;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfTambores;
    public static javax.swing.JTextField tfTotalKilosVenta;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
