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

    public int codigoCliente, codigoFactura, codigoMovimientoCtaCte, codigoTrasladoMielPaga, codigoTrasladoMielImpaga;
    public Double totalMielFacturada, importeTotalFactura;

    int fila = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroFacturaClienteExterior() throws SQLException {
        
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

        //no hace falta cargar combo con locaciones, ya se selecciono todo en el form de traslados
        //la cantidad de miel tambien ya ha sido ingresada
        //esta inicializacion debe hacerse con datos que vendrian del formulario de traslados!
        tfCantidadKilos.setText(String.valueOf(totalMielFacturada));
        tfPrecioUnitario.setText("0.00");
        tfImporteTotalFactura.setText("$ 0.00");

        //se selecciona por defecto el tipo de factura E, ya que es un cliente en el interior del pais
        //y se deshabilita el combo de facturas, ya que es una venta con factura a si o si
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cbTipoFactura = new javax.swing.JComboBox<>();
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
        jLabel24 = new javax.swing.JLabel();
        tfTambores = new javax.swing.JTextField();
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

        tfNacionalidadCliente.setEditable(false);
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
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del cliente en el exterior", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA FACTURA:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("* FECHA:");

        dcFechaFactura.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaFactura.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("* N°:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("* VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("* FACTURA:");

        cbTipoFactura.setBackground(new java.awt.Color(255, 255, 0));
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

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CONVERSION A TAMBORES:");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores.setForeground(new java.awt.Color(255, 255, 255));

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
                                .addGap(33, 33, 33))
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
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(7, 7, 7)
                        .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel18))
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(190, Short.MAX_VALUE))
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
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        //CHEQUEOS NECESARIOS:
        
        //1) DATOS COMPLETOS: 
        //a) seleccion de un cliente en el exterior
        //b) datos de la factura: numero, fecha e importe total
        //todos los demas chequeos ya han sido llevados a cabo en el formulario traslados

        if (tfIDCliente.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente al cual se le realizo la venta de miel.", "REGISTRO DE FACTURA A CLIENTE EN EL EXTERIOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tClientes.requestFocus();
            return;
            
        }
        
        //chequeo de info obligatoria
        if (tfNumeroComprobante.getText().length() == 0 || tfPrecioUnitario.getText().length() == 0) {

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
        
        //se procede al registro de la factura correspondiente a la VENTA de miel al cliente seleccionado
        //que en realidad es un update de la factura ya ingresada al inicializarse este formulario!
        FacturaCliente factura = new FacturaCliente(tipoFactura, numeroComprobante, codigoMovimientoCtaCte, codigoCliente, new Date(a1, m1, d1), new Date(a2, m2, d2), importeTotalFactura, totalMielFacturada);
        
        if (factura.registrarFacturaCliente(factura)){
            
            //ahora se guarda el movimiento correspondiente a la factura, en la cta. cte. del cliente con la empresa
            CtaCteCliente ctacteCliente = new CtaCteCliente(codigoCliente, codigoMovimientoCtaCte, new Date(a1, m1, d1), tipoFactura, codigoFactura, tfNumeroComprobante.getText(), totalMielFacturada, importeTotalFactura, 0.00, importeTotalFactura, "PENDIENTE", "");
            ctacteCliente.registrarMovimientoCtaCteCliente(ctacteCliente);
            //EN ESTE PUNTO: DEBERIA UBICAR LOS MOVIMIENTOS CORRESPONDIENTES A LA FACTURA EN LA TABLA STOCK REAL DE MIEL
            //Y EDITARLOS, ASOCIANDO A ELLOS EL NUMERO DE FACTURA CONFECCIONADA PARA LA VENTA AL EXTERIOR
            //(dato que va en el campo comprobante_asociado)
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
        //SE DEBEN ELIMINAR LOS TRASLADOS REALIZADOS EN EL FORMULARIO ANTERIOR
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tpFacturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpFacturaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpFacturaComponentAdded

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

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased

        if (tfPrecioUnitario.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            importeTotalFactura = Math.round(importeFactura*100.0)/100.0;
            tfImporteTotalFactura.setText(String.valueOf(importeTotalFactura));

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

    private void tfCantidadKilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosActionPerformed

    private void cbTipoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoFacturaActionPerformed

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked

        fila = tClientes.rowAtPoint(evt.getPoint());
        codigoCliente = Integer.parseInt(tClientes.getValueAt(fila, 0).toString());
        CtaCteCliente ctacteCliente = new CtaCteCliente();
        codigoMovimientoCtaCte = ctacteCliente.mostrarIdMovimiento(codigoCliente)+1;

        //cada vez que se hace click sobre la grilla se muestran en los campos debajo lso datos del cliente
        //correspondiente a la fila de la grilla cliqueada
        tfIDCliente.setText(tClientes.getValueAt(fila, 0).toString());
        tfNombreCliente.setText(tClientes.getValueAt(fila, 1).toString());
        tfDocumentoCliente.setText(tClientes.getValueAt(fila, 2).toString());
        tfNacionalidadCliente.setText(tClientes.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tClientesMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarClientes(tfBusquedaPorNombre.getText());
        ocultarColumnasClientes();
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
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
    public static javax.swing.JTable tClientes;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDocumentoCliente;
    public javax.swing.JTextField tfIDCliente;
    public javax.swing.JTextField tfImporteTotalFactura;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNacionalidadCliente;
    public javax.swing.JTextField tfNombreCliente;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfTambores;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
