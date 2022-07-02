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

public class FrmRegistroFacturaClienteExterior extends javax.swing.JInternalFrame {

    public int codigoCliente, codigoFactura, codigoItemFacturado, codigoMovimientoCtaCte;
    public Double totalMielFacturada;
    public List<ItemFacturadoFacturaCliente> itemsAFacturar = new ArrayList<>();
    public List<Locacion> listaLocacionesDisponibles = new ArrayList<>();
    
    //aca cargo todos los productores registrados en el sistema (tengan o no miel despoitada en su locacion)
    //de ahi voy a cargar en el combo el nombre de los mismos
    public List<Productor> listaProductores = new ArrayList<>();
    
    Double saldoMielOrigen, saldoMielDepositoProductorSeleccionado;

    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacionOrigen, codigoProductor;

    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacion;

    int fila = -1;
    int filaItemsFacturados = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroFacturaClienteExterior() throws SQLException {
        
        initComponents();
        mostrarClientes("");
        ocultarColumnasClientes();
        listarItemsFacturados();
        ocultarColumnasItemsFacturados();
        inicializar();
        
    }

    public void limpiarCampos(){

        tfNumeroComprobante.setText("");
        Calendar cal = new GregorianCalendar();
        dcFechaFactura.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidadItemFacturado.setText("");
        tfImporteItemFacturado.setText("");
        tfImporteTotalFactura.setText("0.00");
        cbLocacionesDisponibles.setSelectedIndex(0);
        mostrarClientes("");
        ocultarColumnasClientes();
        
        itemsAFacturar.clear();
        listarItemsFacturados();
        tpFactura.setSelectedIndex(0);
        tClientes.requestFocus();
        
        

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

        FacturaCliente facturaCliente = new FacturaCliente("FACTURA", "-", 0, 13, new Date(a, m, d), new Date(a, m, d), 0.00, 0.00);
        facturaCliente.registrarFacturaCliente(facturaCliente);
        //almaceno en la variable global codigoFactura el codigo de la nueva factura a registrar
        
        //aca almaceno el codigo de la factura recien cargada para utilizar el mismo para los items
        //y para ubicarla en caso de tener que eliminarla
        codigoFactura = facturaCliente.mostrarIdFacturaCliente();
        
        codigoItemFacturado = facturaCliente.mostrarIdItemAFacturar(codigoFactura)+1;
        
        //inicializo variable que almacena la cantidad de miel facturada en la factura que se va a registrar
        totalMielFacturada = 0.00;

        tfImporteTotalFactura.setText("0.00");
        tfImporteTotalFactura.setEditable(false);
        
        tfNombreCliente.setEditable(false);
        tfDocumentoCliente.setEditable(false);
        tfNacionalidadCliente.setEditable(false);
        
        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        
        listaLocacionesDisponibles = cargarListaLocaciones();
        
        for (int i = 0; i<listaLocacionesDisponibles.size(); i++){
            
            cbLocacionesDisponibles.addItem(listaLocacionesDisponibles.get(i).getNombre_locacion());
            
        }
        
        //se selecciona por defecto el tipo de factura E, ya que es un cliente en el exterior del pais
        //y se deshabilita el combo
        cbTipoFactura.setSelectedIndex(2);
        cbTipoFactura.setEnabled(false);
        
        tClientes.requestFocus();
        
    }

    public void mostrarClientes(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Cliente cliente = new Cliente();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = cliente.listarClientes(buscar, "STANDARDS");
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tClientes.setModel(modelo);
                    

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

        tClientes.getColumnModel().getColumn(2).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(2).setMinWidth(0);
        tClientes.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tClientes.getColumnModel().getColumn(3).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(3).setMinWidth(0);
        tClientes.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tClientes.getColumnModel().getColumn(4).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(4).setMinWidth(0);
        tClientes.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tClientes.getColumnModel().getColumn(5).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(5).setMinWidth(0);
        tClientes.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tClientes.getColumnModel().getColumn(6).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(6).setMinWidth(0);
        tClientes.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        tClientes.getColumnModel().getColumn(7).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(7).setMinWidth(0);
        tClientes.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        tClientes.getColumnModel().getColumn(8).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(8).setMinWidth(0);
        tClientes.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        tClientes.getColumnModel().getColumn(9).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(9).setMinWidth(0);
        tClientes.getColumnModel().getColumn(9).setPreferredWidth(0);

        tClientes.getColumnModel().getColumn(10).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(10).setMinWidth(0);
        tClientes.getColumnModel().getColumn(10).setPreferredWidth(0);

        tClientes.getColumnModel().getColumn(11).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(11).setMinWidth(0);
        tClientes.getColumnModel().getColumn(11).setPreferredWidth(0);

        tClientes.getColumnModel().getColumn(12).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(12).setMinWidth(0);
        tClientes.getColumnModel().getColumn(12).setPreferredWidth(0);

        tClientes.getColumnModel().getColumn(13).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(13).setMinWidth(0);
        tClientes.getColumnModel().getColumn(13).setPreferredWidth(0);

        tClientes.getColumnModel().getColumn(14).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(14).setMinWidth(0);
        tClientes.getColumnModel().getColumn(14).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tClientes.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tClientes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(6).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    

    public void ocultarColumnasItemsFacturados() {

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.LEFT);
        tItemsFacturados.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsFacturados.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsFacturados.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsFacturados.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tItemsFacturados.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where categoria = 'FISCALIZACION' order by codigo_locacion asc");
        
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
        jPanel1 = new javax.swing.JPanel();
        tpFactura = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tClientes = tClientes = new javax.swing.JTable(){
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
        tfNacionalidadCliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        lStockOrigen = new javax.swing.JLabel();
        lStockOrigen1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfCantidadItemFacturado = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfImporteItemFacturado = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tItemsFacturados = tItemsFacturados = tItemsFacturados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfImporteTotalFactura = new javax.swing.JTextField();
        rdbrRegistrar1 = new rojeru_san.RSButtonRiple();
        rdbrRegistrar2 = new rojeru_san.RSButtonRiple();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        cbDescripcionItem = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbTipoFactura = new javax.swing.JComboBox<>();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("REGISTRO DE FACTURA A CLIENTE EN EL EXTERIOR - CAM HONEY BROTHERS");
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
        jLabel2.setText("SELECCIONE EL CLIENTE CORRESPONDIENTE:");
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

        tClientes.setBackground(new java.awt.Color(36, 33, 33));
        tClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tClientes.setForeground(new java.awt.Color(207, 207, 207));
        tClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tClientes.setOpaque(true);
        tClientes.setRowHeight(20);
        tClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tClientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tClientes);

        tfIDCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfIDCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfIDCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID CLIENTE:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfNombreCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNombreCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfDocumentoCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfDocumentoCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("NACIONALIDAD:");

        tfNacionalidadCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfNacionalidadCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNacionalidadCliente.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(tfNacionalidadCliente)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfDocumentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13))
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
                .addComponent(tfNacionalidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del cliente en el exterior", jPanel2);

        jPanel4.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("SELECCIONE LA LOCACION DESDE LA QUE SE TRASLADARA LA MIEL:");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 255, 102));
        jLabel31.setText("LOCACION ORIGEN DE LA MIEL VENDIDA:");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbLocacionesDisponibles.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocacionesDisponiblesMouseClicked(evt);
            }
        });
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lStockOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStockOrigen1)))
                .addContainerGap())
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
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStockOrigen)
                    .addComponent(jLabel25)
                    .addComponent(lStockOrigen1))
                .addContainerGap(275, Short.MAX_VALUE))
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

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DESCRIPCION:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CANTIDAD:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        tfCantidadItemFacturado.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadItemFacturado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadItemFacturado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("IMPORTE:");

        tfImporteItemFacturado.setBackground(new java.awt.Color(51, 84, 111));
        tfImporteItemFacturado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteItemFacturado.setForeground(new java.awt.Color(255, 255, 255));

        tItemsFacturados.setBackground(new java.awt.Color(153, 255, 255));
        tItemsFacturados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCION", "CANTIDAD", "IMPORTE", "SUB TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tItemsFacturados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tItemsFacturadostItemsFacturadosFacturaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tItemsFacturados);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TOTAL:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("$");

        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 0, 51));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfImporteTotalFactura.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        tfImporteTotalFactura.setCaretColor(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rdbrRegistrar1.setBackground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar1.setForeground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar1.setText("FACTURAR");
        rdbrRegistrar1.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrar1ActionPerformed(evt);
            }
        });

        rdbrRegistrar2.setBackground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar2.setForeground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar2.setText("QUITAR");
        rdbrRegistrar2.setToolTipText("");
        rdbrRegistrar2.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrRegistrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrar2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        cbDescripcionItem.setBackground(new java.awt.Color(36, 33, 33));
        cbDescripcionItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbDescripcionItem.setForeground(new java.awt.Color(207, 207, 207));
        cbDescripcionItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "KG. DE MIEL", "TAMBOR DE MIEL X 300 KGS.", "LOTE DE MIEL X 70 TAMBORES", "LOTE DE MIEL X 71 TAMBORES", " " }));
        cbDescripcionItem.setPreferredSize(new java.awt.Dimension(136, 19));
        cbDescripcionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescripcionItemActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfCantidadItemFacturado))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(tfImporteItemFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdbrRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbrRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteItemFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCantidadItemFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDescripcionItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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

        //CHEQUEOS NECESARIOS:
        
        //1) DATOS COMPLETOS: 
        //a) seleccion de un cliente exportador interno
        //b) seleccion del origen de la miel vendida (y validacion del stock correspondiente)
        //c) datos de la factura: numero, fecha e importe total
        
        //2) Cantidades correctas
        //a) que no se este intentando vender 0 kgs. de miel
        //b) que la cantidad facturada no supere el stock de la locacion seleccionada, 

        Boolean informacionFactura = (cbTipoFactura.getSelectedItem() == "SELECCIONAR" || tfNumeroComprobante.getText().length() == 0 || tfImporteTotalFactura.getText().length() == 0 || cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR");

        if (tfIDCliente.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente al cual se le realizo la venta de miel.", "REGISTRO DE FACTURA A CLIENTE EN EL EXTERIOR", JOptionPane.ERROR_MESSAGE);
            totalMielFacturada = 0.00;
            itemsAFacturar.clear();
            tpFactura.setSelectedIndex(0);
            tClientes.requestFocus();
            return;
            
        }
        
        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente a la factura se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE FACTURA A CLIENTE EN EL EXTERIOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(1);
            tfNumeroComprobante.requestFocus();
            return;
            
        }
        
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

        //ver como puedo comparar las fechas de la factura y de vencimiento de la misma para que sean 
        //correcatmente almacenadas en la BD

        String tipoFactura = String.valueOf(cbTipoFactura.getSelectedItem());
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());
        Double importeFactura = Double.parseDouble(tfImporteTotalFactura.getText());
        

        //se procede al registro de la factura correspondiente a la VENTA de miel al cliente seleccionado
        //que en realidad es un update de la factura ya ingresada al inicializarse este formulario!
        FacturaCliente factura = new FacturaCliente(tipoFactura, numeroComprobante, codigoMovimientoCtaCte, codigoCliente, new Date(a1, m1, d1), new Date(a2, m2, d2), importeFactura, totalMielFacturada);
        
        if (factura.modificarFacturaCliente(factura, codigoFactura)){
            
            //ahora, se guardan todos los items facturados en dicha factura (crar el metodo)
            for (int i = 0; i<itemsAFacturar.size(); i++ ){

                ItemFacturadoFacturaCliente item = itemsAFacturar.get(i);
                item.facturarItem(item);

            }

            //ahora se guarda el movimiento correspondiente a la factura, en la cta. cte. del cliente con la empresa
            CtaCteCliente ctacteCliente = new CtaCteCliente(codigoCliente, codigoMovimientoCtaCte, new Date(a1, m1, d1), tipoFactura, codigoFactura, tfNumeroComprobante.getText(), totalMielFacturada, importeFactura, 0.00, importeFactura, "PENDIENTE", "");
            ctacteCliente.registrarMovimientoCtaCteCliente(ctacteCliente);

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, RESTANDO LA CANTIDAD DE KGS. VENDIDA EN ESTA FACTURA
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD: KGS. PAGOS, KGS. IMPAGOS, ETC.


            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("VENTA");
            stockMiel.setComprobante_asociado(tipoFactura);
            stockMiel.setId_comprobante_asociado(codigoFactura);
            stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
            
            //crear metodo para realizar esto:
            //en una variable deberia sumar todos los kilos de miel comprados, los cuales se pueden sacar
            //de las descripciones y cantidades de los items facturados (en la lista esta esa informacion!)
            //esa cantidad obtenida se almacenara en cantidad_miel
            stockMiel.setCantidad_miel(totalMielFacturada);
            //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
            //no se almacenara nada!
            //debo obtener el codigo de la locacion a partir del nombre de la misma
            //escogido en el combo de locaciones disponibles

            stockMiel.setLocacion_miel(codigoLocacion);

            //chequeo si la compra de miel quedara depositada en la locacion del productor
            //Locacion locacion = new Locacion();
/*            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

            if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

                //se trata de una compra en la cual la miel adquirida quedara acopiada en alguna locacion del productor
                //que vende la miel
                //cargo en el campo observacion el codigo del productor vendedor en esta compra
                stockMiel.setMiel_deposito_productor(codigoCliente);

                //teniendo este dato voy a poder llevar la cantidad de miel que hay en cada productor vendedor
                //viendola de manera global como "miel acopiada en locacion del productor"
                //pero pudiendo calcular y descontar o aumentar cuando sea necesario, la miel
                //comprada y depositada en cada uno de los productores correspondientes

                //cuando realice un traslado desde la locacion "locacion del productor"
                //voy a tener que descontar el stock global de dicha locacion
                //y discriminar y descontar consecuentemente la miel depositada
                //en la locacion del productor desde el cual se va a trasladar dicha miel


            }*/

            //se asigna a la compra el valor: FACTURADA, ya que es una compra con factura.
            stockMiel.setEstado_compra("FACTURADA");

            //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
            stockMiel.registrarMovimientoStock(stockMiel);

            JOptionPane.showMessageDialog(null, "La factura ha sido registrada exitosamente.","REGISTRO DE FACTURA DE CLIENTE EN EL EXTERIOR", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar la factura.","REGISTRO DE FACTURA DE CLIENTE EN EL EXTERIOR", JOptionPane.ERROR_MESSAGE);

        }

        this.dispose();
            
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        FacturaCliente factura = new FacturaCliente();
        factura.eliminarFacturaCliente(codigoFactura);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tpFacturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpFacturaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpFacturaComponentAdded

    private void cbTipoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoFacturaActionPerformed

    private void cbDescripcionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDescripcionItemActionPerformed

    private void rdbrRegistrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrar2ActionPerformed

        //tengo que quitar le item facturado de la lista de items a facturar
        //los cuales aun no se han dado de alta en la BD sino que aguardan
        //en dicha lista para luego ser recorridos y dados todos de alta
        //hay que eliminar el item de la lista de items a facturar y de la grilla que los muestra
        //mientras tanto

        if (filaItemsFacturados == -1){

            JOptionPane.showMessageDialog(null, "Por favor seleccione el item desvincular de la factura.", "DESVINCULACION DE ITEM FACTURADO", JOptionPane.INFORMATION_MESSAGE);

        }
        else{

            if (itemsAFacturar.size()>0){

                //primero obtengo la cantidad de miel del item a punto de desvincularse del credito
//                ItemFacturadoFacturaProductor item = itemsAFacturar.get(filaItemsFacturados);
  //              Double cantidadItemFacturado = item.getCantidadItemFacturado();

                //lo elimino de la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
                itemsAFacturar.remove(filaItemsFacturados);

                //se resta la cantidad de kgs. de miel del item removido a la variable totalMielFinanciada
     //           totalMielFacturada = totalMielFacturada-cantidadItemFacturado;

                //lo quito de la tabla

                listarItemsFacturados();
                ocultarColumnasItemsFacturados();
                calcularImporteTotalFactura();
                JOptionPane.showMessageDialog(null, "El item facturado ha sido desvinculado con exito de la factura.", "DESVINCULACION DE ITEM PRESUPUESTADO", JOptionPane.INFORMATION_MESSAGE);

            }
            else{

                JOptionPane.showMessageDialog(null, "No existen items facturados para poder desvincular.", "DESVINCULACION DE ITEM FACTURADO", JOptionPane.INFORMATION_MESSAGE);

            }

            cbDescripcionItem.requestFocus();

        }

    }//GEN-LAST:event_rdbrRegistrar2ActionPerformed

    private void rdbrRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrar1ActionPerformed

        //chequeo de datos completos
        if (String.valueOf(cbDescripcionItem.getSelectedItem()) == "SELECCIONAR"){

            JOptionPane.showMessageDialog(null, "Se debe seleccionar la descripcion del item a facturar.", "FACTURACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            cbDescripcionItem.requestFocus();
            return;

        }

        if (tfCantidadItemFacturado.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Se debe ingresar la cantidad correspondiente a facturar.", "FACTURACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemFacturado.requestFocus();
            return;

        }

        if (Integer.parseInt(tfCantidadItemFacturado.getText().toString()) == 0) {

            JOptionPane.showMessageDialog(null, "No se puede facturar un item con cantidad menor a una unidad.", "FACTURACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemFacturado.requestFocus();
            return;

        }

        if (tfImporteItemFacturado.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Se debe ingresar el importe correspondiente al item seleccionado.", "FACTURACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfImporteItemFacturado.requestFocus();
            return;

        }

        if (Double.parseDouble(tfImporteItemFacturado.getText().toString()) == 0.00) {

            JOptionPane.showMessageDialog(null, "No se puede facturar un item con importe igual a $ 0.00.", "FACTURACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfImporteItemFacturado.requestFocus();
            return;

        }

        String descripcionItemFacturado = String.valueOf(cbDescripcionItem.getSelectedItem());
        double importeItemFacturado = Double.parseDouble(tfImporteItemFacturado.getText());
        double totalItemFacturado = 0.00;

        Double cantidadItemFacturado = 0.00;

        switch (descripcionItemFacturado){

            case "KG. DE MIEL":
            //se suman los kilos sin convertirlos
            cantidadItemFacturado = Double.parseDouble(tfCantidadItemFacturado.getText().toString());
            totalItemFacturado = cantidadItemFacturado * importeItemFacturado;
            break;

            case "TAMBOR DE MIEL X 300 KGS.":
            //se suman los kilos sin convertirlos
            cantidadItemFacturado = Double.parseDouble(tfCantidadItemFacturado.getText().toString())*300.00;
            totalItemFacturado = cantidadItemFacturado * importeItemFacturado;
            break;

            case "LOTE DE MIEL X 70 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemFacturado = Double.parseDouble(tfCantidadItemFacturado.getText().toString())*21000.00;
            totalItemFacturado = cantidadItemFacturado * importeItemFacturado;
            break;

            case "LOTE DE MIEL X 71 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemFacturado = Double.parseDouble(tfCantidadItemFacturado.getText().toString())*21300.00;
            totalItemFacturado = cantidadItemFacturado * importeItemFacturado;
            break;

        }

        ItemFacturadoFacturaCliente itemFacturado = new ItemFacturadoFacturaCliente(codigoItemFacturado, codigoFactura, descripcionItemFacturado, cantidadItemFacturado, importeItemFacturado, totalItemFacturado);

        //lo agrego a la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
        itemsAFacturar.add(itemFacturado);

        //se suma la cantidad de kgs. de miel a la variable totalMielFinanciada
        totalMielFacturada = totalMielFacturada+cantidadItemFacturado;

        //lo agrego a la tabla

        listarItemsFacturados();
        ocultarColumnasItemsFacturados();
        calcularImporteTotalFactura();

        //limpio los campos
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidadItemFacturado.setText("");
        tfImporteItemFacturado.setText("");
        cbDescripcionItem.requestFocus();

        //incremento el codigo de item facturado para un potencial proximo item facturado
        codigoItemFacturado = codigoItemFacturado+1;

    }//GEN-LAST:event_rdbrRegistrar1ActionPerformed

    private void tItemsFacturadostItemsFacturadosFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tItemsFacturadostItemsFacturadosFacturaMouseClicked

        filaItemsFacturados = tItemsFacturados.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tItemsFacturadostItemsFacturadosFacturaMouseClicked

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked

        fila = tClientes.rowAtPoint(evt.getPoint());
        codigoCliente = Integer.parseInt(tClientes.getValueAt(fila, 0).toString());
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoCliente)+1;

        //cada vez que se hace click sobre la grilla se muestran en los campos debajo lso datos del productor
        //correspondiente a la fila de la grilla cliqueada
        tfIDCliente.setText(tClientes.getValueAt(fila, 0).toString());
        tfNombreCliente.setText(tClientes.getValueAt(fila, 1).toString());
        tfDocumentoCliente.setText(tClientes.getValueAt(fila, 2).toString());
        tfNacionalidadCliente.setText(tClientes.getValueAt(fila, 4).toString());

        //tamb, al hacer click en un productor, cancela los datos que se hayan insertado y aun no se hayan guardado
        //en la solapa de la factura
        limpiarCampos();

        itemsAFacturar.clear();
        listarItemsFacturados();

    }//GEN-LAST:event_tClientesMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarClientes(tfBusquedaPorNombre.getText());
        ocultarColumnasClientes();

    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void cbLocacionesDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesMouseClicked
    }//GEN-LAST:event_cbLocacionesDisponiblesMouseClicked

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        double saldoMiel = 0.00;

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocacionesDisponibles.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            saldoMiel = calcularTotalStockLocacion(codigoLocacionOrigen);
            saldoMielOrigen = saldoMiel;
            lStockOrigen.setText(String.valueOf(saldoMiel));

        }
        else{

            lStockOrigen.setText("0.00");
        }
    }//GEN-LAST:event_jLabel25MouseClicked

    private void listarItemsFacturados() {

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"DESCRIPCION","CANTIDAD","IMPORTE","SUB TOTAL"},itemsAFacturar.size());
        tItemsFacturados.setModel(modelo);
        TableModel modeloDatos = tItemsFacturados.getModel();
        
        for (int i = 0; i<itemsAFacturar.size(); i++ ){
            
            ItemFacturadoFacturaCliente item = itemsAFacturar.get(i);
            modeloDatos.setValueAt(item.getDescripcionItemFacturado(), i, 0);
            modeloDatos.setValueAt(item.getCantidadItemFacturado(), i, 1);
            modeloDatos.setValueAt(item.getImporteItemFacturado(), i, 2);
            modeloDatos.setValueAt(item.getTotalItemFacturado(), i, 3);
            
        }
        
    }

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
        for (int i = 0; i < tItemsFacturados.getRowCount(); i++) {
            saldo = saldo + Double.valueOf(tItemsFacturados.getValueAt(i, 3).toString());
        }
        
        tfImporteTotalFactura.setText(formateador.format(saldo));
    }
    
    public double calcularTotalStockLocacion(int codigoLocacion) {

        double mielComprada, mielVendida, mielRecibida, mielEnviada, saldoMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielComprada = stock.obtenerDetalleMielComprada(codigoLocacion);
        mielVendida = stock.obtenerDetalleMielVendida(codigoLocacion);
        mielRecibida = stock.obtenerDetalleMielRecibidaTraslado(codigoLocacion);
        mielEnviada = stock.obtenerDetalleMielEnviadaTraslado(codigoLocacion);
        
        saldoMiel = mielComprada + mielRecibida - mielVendida - mielEnviada;
        
        return saldoMiel;
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
    public javax.swing.JComboBox<String> cbLocacionesDisponibles;
    public javax.swing.JComboBox<String> cbTipoFactura;
    public com.toedter.calendar.JDateChooser dcFechaFactura;
    public com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lStockOrigen;
    private javax.swing.JLabel lStockOrigen1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rdbrRegistrar1;
    private rojeru_san.RSButtonRiple rdbrRegistrar2;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTable tClientes;
    public javax.swing.JTable tItemsFacturados;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadItemFacturado;
    public javax.swing.JTextField tfDocumentoCliente;
    public javax.swing.JTextField tfIDCliente;
    public javax.swing.JTextField tfImporteItemFacturado;
    public javax.swing.JTextField tfImporteTotalFactura;
    private javax.swing.JTextField tfNacionalidadCliente;
    public javax.swing.JTextField tfNombreCliente;
    public javax.swing.JTextField tfNumeroComprobante;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
