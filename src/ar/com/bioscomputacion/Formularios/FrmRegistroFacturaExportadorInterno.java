/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.AjusteCompensacionStock;
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
import ar.com.bioscomputacion.Funciones.PresupuestoCliente;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
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

    public int codigoExportadorInterno, codigoFactura, codigoPresupuesto, codigoMovimientoCtaCte;
    public Double totalMielAFacturar, importeTotalComprobante;
    public String numeroPresupuesto, tipoComprobante;
    public List<Locacion> listaLocacionesDisponibles = new ArrayList<>();
    
    //para recordar los registros de la tabla stock de miel que se deben eliminar en caso de que
    //no se confirme la venta de la miel
    public int codigoTrasladoOrigenMielPaga, codigoTrasladoOrigenMielImpaga, codigoTrasladoDestinoMielPaga, codigoTrasladoDestinoMielImpaga;
    //para recordar la cantidad de miel paga y la cantidad de miel impaga que se debe re ajustar en la tabla
    //de ajuste y compensacion de stock, en caso de no confirmarse la venta de miel
    public double mielPagaVendida, mielImpagaVendida;
    //para recordar las locaciones origen y destino involucradas en el traslado y la venta
    public int codigoLocacionOrigen, codigoLocacionDestino;

    //aca cargo todos los productores registrados en el sistema
    //de ahi voy a cargar en el combo el nombre de los mismos
    public List<Productor> listaProductores = new ArrayList<>();
    
    Double saldoMielOrigen, saldoMielDepositoProductorSeleccionado, saldoMielPaga, saldoMielImpaga, totalMielVenta, saldoMielPagaIngresado, saldoMielImpagaIngresado;
 
    int fila = -1;
    
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

        tfCantidadKilos.setText(String.valueOf(totalMielAFacturar));
        tfPrecioUnitario.setText("0.00");
        tfImporteTotalFactura.setText("$ 0.00");
        
        //obtenemos el numero de comprobante en caso de que se seleccione presupuesto
        PresupuestoCliente presupuesto = new PresupuestoCliente();
        numeroPresupuesto = String.valueOf(presupuesto.mostrarIdPresupuestoCliente()+1);

        //la venta a un exportador interno puede ser presupuestada o facturada con comprobantes a o c
        cbTipoComprobante.setSelectedIndex(0);
        tfNumeroComprobante.setText("");
        
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

    /*public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
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
        
    }*/

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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        cbTipoComprobante = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfCantidadKilos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfImporteTotalFactura = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        tfTambores = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("REGISTRO DE VENTA A EXPORTADOR INTERNO - CAM HONEY BROTHERS");
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

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL COMPROBANTE:");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("* COMPROBANTE:");

        cbTipoComprobante.setBackground(new java.awt.Color(255, 255, 0));
        cbTipoComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoComprobante.setForeground(new java.awt.Color(207, 207, 207));
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURA A", "FACTURA C", "PRESUPUESTO" }));
        cbTipoComprobante.setPreferredSize(new java.awt.Dimension(136, 19));
        cbTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoComprobanteActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("* N°:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("* FECHA:");

        dcFechaFactura.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaFactura.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("* VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ITEM A FACTURAR:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion.setText(" KGS. DE MIEL");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("* KGS.:");

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

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("* PRECIO UNITARIO:");

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

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IMPORTE TOTAL:");

        tfImporteTotalFactura.setEditable(false);
        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(0, 0, 0));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfLotes.setForeground(new java.awt.Color(255, 255, 255));

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CONVERSION A TAMBORES:");

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
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(dcFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel18)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfImporteTotalFactura))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel18))
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos de la factura", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR COMPROBANTE");
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
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
        Boolean informacionFactura = (cbTipoComprobante.getSelectedItem().equals("SELECCIONAR") || tfNumeroComprobante.getText().length() == 0 || tfImporteTotalFactura.getText().length() == 0 || tfImporteTotalFactura.getText().equals("$ 0.00") || tfImporteTotalFactura.getText().equals("$ 0.0"));
        
        if (tfIDCliente.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar el exportador interno al cual se le realizo la venta de miel.", "REGISTRO DE VENTA A EXPORTADOR INTERNO", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tExportadoresInternos.requestFocus();
            return;
            
        }
        
        //chequea informacion del comprobante, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al comprobante se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE VENTA A EXPORTADOR INTERNO", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(1);
            tfNumeroComprobante.requestFocus();
            return;
            
        }
        
        //se procede al registro del comprobante correspondiente a la venta de miel al exportador seleccionado
        
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

        //OBTENGO: que tipo de comprobante se escogio para la facturacion de la venta al exportador interno
        //el numero de comprobante del mismo
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());
        //el codigo de comprobante, que dependera de la eleccion entre presupuesto y factura (a o c)
        int codigoComprobante = 0;
        //y el comprobante asociado para cargar en la tabla de stock de miel
        String comprobanteAsociadoMielPaga = "";
        String comprobanteAsociadoMielImpaga = "";
        
        //1) 
        //a) Se procede al registro del comprobante correspondiente a la venta
        //que puede ser: factura a, factura c o presupuesto
        //b) Se obtiene el numero de movimiento que tendra el comprobante de facturacion en la cuenta corriente con el productor
        //ademas en la variable codigoMovimientoCtaCteCompra ya tenemos almacenado el numero de movimiento correspndiente
        //a la compra en consignacion, ya que a la misma se le debe editar el estado en algunos casos (pasandolo a CANCELADO)   
        CtaCteCliente ctacteCliente = new CtaCteCliente();
        codigoMovimientoCtaCte = ctacteCliente.mostrarIdMovimiento(codigoExportadorInterno)+1;
        
        switch (tipoComprobante){
            
            case "FACTURA A":

                //se escogio como tipo de comprobante la "FACTURA A"
                //se registra la factura
                FacturaCliente facturaA = new FacturaCliente(tipoComprobante, numeroComprobante, codigoMovimientoCtaCte, codigoExportadorInterno, new Date(a1, m1, d1), new Date(a2, m2, d2), importeTotalComprobante, totalMielAFacturar);
                if (facturaA.registrarFacturaCliente(facturaA)){

                    //obtengo codigo de factura para utilizarlo en el almacenamiento de las relaciones
                    codigoComprobante = facturaA.mostrarIdFacturaCliente();
                    
                }
                
                comprobanteAsociadoMielPaga = "FACT. A / TRASLADO MIEL PAGA";
                comprobanteAsociadoMielImpaga = "FACT. A / TRASLADO MIEL IMPAGA";
                
                break;

            case "FACTURA C":

                //se escogio como tipo de comprobante la "FACTURA C"
                //se registra la factura
                FacturaCliente facturaC = new FacturaCliente(tipoComprobante, numeroComprobante, codigoMovimientoCtaCte, codigoExportadorInterno, new Date(a1, m1, d1), new Date(a2, m2, d2), importeTotalComprobante, totalMielAFacturar);
                if (facturaC.registrarFacturaCliente(facturaC)){
                    
                    //obtengo codigo de factura para utilizarlo en el almacenamiento de las relaciones
                    codigoComprobante = facturaC.mostrarIdFacturaCliente();

                }
                
                comprobanteAsociadoMielPaga = "FACT. C / TRASLADO MIEL PAGA";
                comprobanteAsociadoMielImpaga = "FACT. C / TRASLADO MIEL IMPAGA";
                
                break;

            case "PRESUPUESTO":
                
                //se escogio como tipo de comprobante el "PRESUPUESTO"
                //se registra el presupuesto
                PresupuestoCliente presupuesto = new PresupuestoCliente(numeroComprobante, codigoMovimientoCtaCte, codigoExportadorInterno, new Date(a1, m1, d1), new Date(a2, m2, d2), importeTotalComprobante, totalMielAFacturar);
                if (presupuesto.registrarPresupuestoCliente(presupuesto)){
                    
                    //obtengo codigo de presupuesto para utilizarlo en el almacenamiento de las relaciones
                    codigoComprobante = presupuesto.mostrarIdPresupuestoCliente();
                    
                }
                
                comprobanteAsociadoMielPaga = "PRESUPUESTO / TRASLADO MIEL PAGA";
                comprobanteAsociadoMielImpaga = "PRESUPUESTO / TRASLADO MIEL IMPAGA";
                
                break;
                
            default:
                
                break;

        }

        //3)
        //Ahora se guarda el movimiento correspondiente a la factura o presupuesto, en la cta. cte. del cliente con la empresa
        ctacteCliente.setCodigoCliente(codigoExportadorInterno);
        ctacteCliente.setCodigoMovimiento(codigoMovimientoCtaCte);
        ctacteCliente.setFechaMovimiento(new Date(a1, m1, d1));
        ctacteCliente.setDescripcionMovimiento(tipoComprobante);
        ctacteCliente.setComprobanteAsociado(codigoComprobante);
        ctacteCliente.setNumeroComprobante(numeroComprobante);
        ctacteCliente.setCantidadMiel(totalMielAFacturar);
        ctacteCliente.setDebe(importeTotalComprobante);
        ctacteCliente.setHaber(0.00);
        ctacteCliente.setSaldo(importeTotalComprobante);
        //se ctacteCliente con estado de comprobante como "PENDIENTE", ya que obviamente se acaba de facturar y esta impago
        ctacteCliente.setEstadoMovimiento("PENDIENTE");
        ctacteCliente.setObservacion("");
        ctacteCliente.registrarMovimientoCtaCteCliente(ctacteCliente);
        
        //se deben modificar (actualizar) los registros en la tabla stock de miel
        //para dejar asentado el tipo y el numero de comprobante en los traslados ventas correspondientes
        StockRealMiel stock = new StockRealMiel();
        stock.modificarTipoYNumeroComprobanteMovimientoStock(codigoTrasladoOrigenMielPaga, comprobanteAsociadoMielPaga, codigoComprobante, numeroComprobante);
        stock.modificarTipoYNumeroComprobanteMovimientoStock(codigoTrasladoDestinoMielPaga, comprobanteAsociadoMielPaga, codigoComprobante, numeroComprobante);
        stock.modificarTipoYNumeroComprobanteMovimientoStock(codigoTrasladoOrigenMielImpaga, comprobanteAsociadoMielImpaga, codigoComprobante, numeroComprobante);
        stock.modificarTipoYNumeroComprobanteMovimientoStock(codigoTrasladoDestinoMielImpaga, comprobanteAsociadoMielImpaga, codigoComprobante, numeroComprobante);

        //falta el ajuste y compensacion?????

        JOptionPane.showMessageDialog(null, "La factura ha sido registrada exitosamente.","REGISTRO DE VENTA A EXPORTADOR INTERNO", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
            
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE VENTA A EXPORTADOR INTERNO", JOptionPane.INFORMATION_MESSAGE);
        
        //se deben eliminar los movimientos de stock generados en el formulario anterior
        //y se deben re ajustar los valores de miel paga y miel impaga en las ajustados en el formulario anterior
        StockRealMiel stock = new StockRealMiel();
        stock.eliminarMovimientoStock(codigoTrasladoOrigenMielPaga);
        stock.eliminarMovimientoStock(codigoTrasladoDestinoMielPaga);
        stock.eliminarMovimientoStock(codigoTrasladoOrigenMielImpaga);
        stock.eliminarMovimientoStock(codigoTrasladoDestinoMielImpaga);
        
        //ademas se deben re ajustar los valores ajustados en el formulario de traslados
        //volviendo los mismos a sus valores originales
        AjusteCompensacionStock ajuste = new AjusteCompensacionStock();
        //Locacion origen
        Double cantidadMielPagaLocacion = ajuste.consultarCantidadMielPagaLocacion(codigoLocacionOrigen) + mielPagaVendida;
        Double cantidadMielImpagaLocacion = ajuste.consultarCantidadMielImpagaLocacion(codigoLocacionOrigen) + mielImpagaVendida;
        Double cantidadMielImpagaVendidadLocacion = ajuste.consultarCantidadMielImpagaVendidaLocacion(codigoLocacionOrigen) - mielImpagaVendida;
        ajuste.setStock_miel_pago(cantidadMielPagaLocacion);
        ajuste.setStock_miel_impago(cantidadMielImpagaLocacion);
        ajuste.setStock_miel_impago_vendido(cantidadMielImpagaVendidadLocacion);
        ajuste.modificarValoresMielLocacion(ajuste, codigoLocacionOrigen);
        //Locacion destino
        cantidadMielPagaLocacion = ajuste.consultarCantidadMielPagaLocacion(codigoLocacionDestino) - mielPagaVendida;
        cantidadMielImpagaLocacion = ajuste.consultarCantidadMielImpagaLocacion(codigoLocacionDestino) - mielImpagaVendida;
        cantidadMielImpagaVendidadLocacion = ajuste.consultarCantidadMielImpagaVendidaLocacion(codigoLocacionDestino);
        ajuste.setStock_miel_pago(cantidadMielPagaLocacion);
        ajuste.setStock_miel_impago(cantidadMielImpagaLocacion);
        ajuste.setStock_miel_impago_vendido(cantidadMielImpagaVendidadLocacion);
        ajuste.modificarValoresMielLocacion(ajuste, codigoLocacionDestino);

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
        codigoExportadorInterno = Integer.parseInt(tExportadoresInternos.getValueAt(fila, 0).toString());
        CtaCteCliente ctacteCliente = new CtaCteCliente();
        codigoMovimientoCtaCte = ctacteCliente.mostrarIdMovimiento(codigoExportadorInterno)+1;
        
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

    private void tpFacturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpFacturaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpFacturaComponentAdded

    private void cbTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoComprobanteActionPerformed
        
        //en caso de seleccionarse presupeusto se utiliza el numero ya obtenido
        //caso contrario se deja en blanco para completarse con el numer de factura confeccionada
        tipoComprobante = cbTipoComprobante.getSelectedItem().toString();
        
        if (tipoComprobante.equals("FACTURA A") || tipoComprobante.equals("FACTURA C")){
            
            tfNumeroComprobante.setText("");
            tfNumeroComprobante.setEditable(true);
            
        }
        else{
            
            tfNumeroComprobante.setText(numeroPresupuesto);
            tfNumeroComprobante.setEditable(false);
            
        }
        
        tfNumeroComprobante.requestFocus();
        
    }//GEN-LAST:event_cbTipoComprobanteActionPerformed

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
            importeTotalComprobante = Math.round(importeFactura*100.0)/100.0;
            tfImporteTotalFactura.setText(String.valueOf(importeTotalComprobante));

        }
        else{

            if (tfPrecioUnitario.getText().equals("0.0") || tfPrecioUnitario.getText().equals("0.00") ){

            }
            else{

                tfPrecioUnitario.setText("0.00");
                tfImporteTotalFactura.setText("$ 0.00");

            }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcionesMiel;
    public javax.swing.JComboBox<String> cbTipoComprobante;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTable tExportadoresInternos;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDocumentoCliente;
    public javax.swing.JTextField tfIDCliente;
    public javax.swing.JTextField tfImporteTotalFactura;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNacionalidadProductor;
    public javax.swing.JTextField tfNombreCliente;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfTambores;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
