/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmDevolucionCompraConsignacion.totalMielDevuelta;
import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import static ar.com.bioscomputacion.Formularios.FrmRegistroNotaCreditoProductor.codigoProductor;
import ar.com.bioscomputacion.Funciones.AjusteCompensacionStock;
import ar.com.bioscomputacion.Funciones.ComprobantesAcreditacionComprobantesAfectadosProductor;
import ar.com.bioscomputacion.Funciones.CreditoPresupuestoProductor;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmRegistroCreditoPresupuestoProductor extends javax.swing.JInternalFrame {
        
    static int codigoProductor;
    int codigoCredito, codigoMovimientoCtaCte, codigoPresupuesto, codigoLocacion, codigoComprobanteAfectadoAnulacion;
    String tipoComprobanteAfectadoAnulacion;
    Double totalMielPresupuestada, totalMielIngresadaAnulacion, importePresupuesto;
    Double importeCredito, precioUnitario, saldoPendiente, saldoImpago, debeComprobanteAfectado, haberComprobanteAfectado, totalkilosPresupuetados, totalKilosImpagos, totalKilosIngresadosAnulacion;
    int fila = -1;
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroCreditoPresupuestoProductor() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        mostrarFPresupuestosProductor(codigoProductor);
        ocultarColumnas();
        
        Calendar cal = new GregorianCalendar();
        dcFechaAnulacion.setCalendar(cal);
        
        //se almacena en la variable codigoMovimientoCtaCte
        //el numero de mov que tendra en el a cta. cte el pago a registrar
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;
        
        //para calcular el id de CREDITO DE PRESUPUESTO
        CreditoPresupuestoProductor creditoPresupuesto = new CreditoPresupuestoProductor();
        codigoCredito = creditoPresupuesto.mostrarIdCreditoPresupuestoProductor()+1;
        tfNumeroComprobante.setText(String.valueOf(codigoCredito));
        
        //para ir controlando y comparando lo ingresado por el usuario con lo que esta registrado en el presupuesto
        totalMielPresupuestada = 0.00;
        totalMielIngresadaAnulacion = 0.00;
        importePresupuesto = 0.00;
        importeCredito = 0.00;
        
        tPresupuestosProductor.requestFocus();
        
    }

    public static void mostrarFPresupuestosProductor(int codigoProductor) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Productor productor = new Productor();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = productor.listarPresupuestosProductor(codigoProductor);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tPresupuestosProductor.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnas() {

        tPresupuestosProductor.getColumnModel().getColumn(0).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(0).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tPresupuestosProductor.getColumnModel().getColumn(1).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(1).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tPresupuestosProductor.getColumnModel().getColumn(2).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(2).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(2).setPreferredWidth(0);

        /*tPresupuestosProductor.getColumnModel(2).getColumn(3).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(3).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(3).setPreferredWidth(0);*/

        /*tPresupuestosProductor.getColumnModel().getColumn(4).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(4).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tPresupuestosProductor.getColumnModel().getColumn(5).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(5).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tPresupuestosProductor.getColumnModel().getColumn(6).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(6).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        /*tPresupuestosProductor.getColumnModel().getColumn(7).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(7).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(7).setPreferredWidth(0);*/
        
        /*tPresupuestosProductor.getColumnModel().getColumn(8).setMaxWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(8).setMinWidth(0);
        tPresupuestosProductor.getColumnModel().getColumn(8).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender7 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tPresupuestosProductor.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tPresupuestosProductor.getColumnModel().getColumn(3).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tPresupuestosProductor.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tPresupuestosProductor.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tPresupuestosProductor.getColumnModel().getColumn(6).setCellRenderer(cellRender5);
        cellRender6.setHorizontalAlignment(SwingConstants.RIGHT);
        tPresupuestosProductor.getColumnModel().getColumn(7).setCellRenderer(cellRender6);
        cellRender7.setHorizontalAlignment(SwingConstants.LEFT);
        tPresupuestosProductor.getColumnModel().getColumn(8).setCellRenderer(cellRender7);
        
        ((DefaultTableCellRenderer) tPresupuestosProductor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tProductores = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jScrollPane5 = new javax.swing.JScrollPane();
        tProductores1 = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        tDetalleStock = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        bgOpcionesRegistro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        rsbrAsociar = new rojeru_san.RSButtonRiple();
        tpPresupuestos = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tPresupuestosProductor = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfNumeroPresupuesto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfImportePresupuesto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        tfInformacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        tfKilosPresupuestados = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tfImporteTotalComprobante = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tfSaldoImpagoComprobante = new javax.swing.JTextField();
        tfImporteAnulacion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfKilosAAnular = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfSaldoPendiente = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfKilosImpagos = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        dcFechaAnulacion = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfKilosFinalesAnulacion = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfPrecioUnitarioFinalAnulacion = new javax.swing.JTextField();
        tfDescripcion1 = new javax.swing.JTextField();
        tfTambores = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfImporteTotalAnulacion = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();

        tProductores.setBackground(new java.awt.Color(36, 33, 33));
        tProductores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tProductores.setForeground(new java.awt.Color(207, 207, 207));
        tProductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tProductores.setOpaque(true);
        tProductores.setRowHeight(20);
        tProductores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProductoresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tProductores);

        tProductores1.setBackground(new java.awt.Color(36, 33, 33));
        tProductores1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tProductores1.setForeground(new java.awt.Color(207, 207, 207));
        tProductores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tProductores1.setOpaque(true);
        tProductores1.setRowHeight(20);
        tProductores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProductores1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tProductores1);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("STOCK REAL DE MIEL DE LA EMPRESA");

        tDetalleStock.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStock.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStock.setOpaque(true);
        tDetalleStock.setRowHeight(20);
        tDetalleStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStockMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tDetalleStock);

        setBackground(new java.awt.Color(51, 84, 111));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("CANCELAR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        rsbrAsociar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrAsociar.setText("REGISTRAR CREDITO");
        rsbrAsociar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrAsociar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrAsociarActionPerformed(evt);
            }
        });

        tpPresupuestos.setBackground(new java.awt.Color(51, 84, 111));
        tpPresupuestos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tpPresupuestos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tpPresupuestosFocusGained(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 84, 111));
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("SELECCIONE EL PRESUPUESTO QUE SE DESEA ANULAR:");

        jLabel7.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MOSTRAR PRESUPUESTOS EN EL PERIODO:");

        tfBusquedaPorNombre.setEditable(false);
        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

        tPresupuestosProductor.setBackground(new java.awt.Color(36, 33, 33));
        tPresupuestosProductor.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        tPresupuestosProductor.setForeground(new java.awt.Color(207, 207, 207));
        tPresupuestosProductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tPresupuestosProductor.setOpaque(true);
        tPresupuestosProductor.setRowHeight(20);
        tPresupuestosProductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tPresupuestosProductorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tPresupuestosProductorMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(tPresupuestosProductor);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("HA SELECCIONADO EL COMPROBANTE:");
        jLabel14.setToolTipText("");
        jLabel14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLabel14FocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PRESUPUESTO N°:");

        tfNumeroPresupuesto.setEditable(false);
        tfNumeroPresupuesto.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroPresupuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroPresupuesto.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("IMPORTE:");

        tfImportePresupuesto.setEditable(false);
        tfImportePresupuesto.setBackground(new java.awt.Color(51, 84, 111));
        tfImportePresupuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImportePresupuesto.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(tfNumeroPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfImportePresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14))
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(29, 29, 29))
                    .addComponent(tfImportePresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpPresupuestos.addTab("Presupuestos del productor", jPanel6);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("INGRESE LA INFORMACION CORRESPONDIENTE A LA ANULACION:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SE ESTA REGISTRANDO UN CREDITO CORRESPONDIENTE A:");

        tfInformacion.setEditable(false);
        tfInformacion.setBackground(new java.awt.Color(0, 0, 0));
        tfInformacion.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfInformacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DATOS DEL PRESUPUESTO A ASOCIAR AL CREDITO:");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("KGS. PRESUPUESTADOS:");

        tfKilosPresupuestados.setEditable(false);
        tfKilosPresupuestados.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosPresupuestados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosPresupuestados.setPreferredSize(new java.awt.Dimension(137, 23));
        tfKilosPresupuestados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosPresupuestadosActionPerformed(evt);
            }
        });
        tfKilosPresupuestados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosPresupuestadosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosPresupuestadosKeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("PRECIO UNITARIO:");

        tfPrecioUnitario.setEditable(false);
        tfPrecioUnitario.setBackground(new java.awt.Color(255, 255, 204));
        tfPrecioUnitario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitario.setPreferredSize(new java.awt.Dimension(137, 23));
        tfPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyTyped(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("IMPORTE PRESUPUESTO:");

        tfImporteTotalComprobante.setEditable(false);
        tfImporteTotalComprobante.setBackground(new java.awt.Color(255, 255, 204));
        tfImporteTotalComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("SALDO IMPAGO:");

        tfSaldoImpagoComprobante.setEditable(false);
        tfSaldoImpagoComprobante.setBackground(new java.awt.Color(255, 0, 0));
        tfSaldoImpagoComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfSaldoImpagoComprobante.setForeground(new java.awt.Color(255, 255, 255));
        tfSaldoImpagoComprobante.setPreferredSize(new java.awt.Dimension(137, 23));

        tfImporteAnulacion.setEditable(false);
        tfImporteAnulacion.setBackground(new java.awt.Color(51, 84, 111));
        tfImporteAnulacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteAnulacion.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteAnulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImporteAnulacionActionPerformed(evt);
            }
        });
        tfImporteAnulacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfImporteAnulacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfImporteAnulacionKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("* INGRESE KGS. A DEVOLVER:");

        tfKilosAAnular.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosAAnular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosAAnular.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosAAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosAAnularActionPerformed(evt);
            }
        });
        tfKilosAAnular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfKilosAAnularKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosAAnularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosAAnularKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("IMPORTE DEL CREDITO:");

        jLabel23.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("SALDO PENDIENTE:");

        tfSaldoPendiente.setEditable(false);
        tfSaldoPendiente.setBackground(new java.awt.Color(255, 0, 0));
        tfSaldoPendiente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfSaldoPendiente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("KGS. DISPONIBLES:");

        tfKilosImpagos.setEditable(false);
        tfKilosImpagos.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosImpagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5)
                            .addComponent(tfInformacion)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel33)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfKilosPresupuestados, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel34))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(tfKilosImpagos, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfKilosAAnular, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfImporteAnulacion, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(7, 7, 7)
                        .addComponent(tfInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfKilosImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(tfKilosPresupuestados, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKilosAAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel22)
                    .addComponent(tfImporteAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        tpPresupuestos.addTab("Datos del presupuesto", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 84, 111));
        jPanel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel4FocusGained(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("INGRESE LA INFORMACION DEL CREDITO:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("* FECHA:");

        dcFechaAnulacion.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaAnulacion.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaAnulacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("* CREDITO N°:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ITEM:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("KGS.:");

        tfNumeroComprobante.setEditable(false);
        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        tfKilosFinalesAnulacion.setEditable(false);
        tfKilosFinalesAnulacion.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosFinalesAnulacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosFinalesAnulacion.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosFinalesAnulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosFinalesAnulacionActionPerformed(evt);
            }
        });
        tfKilosFinalesAnulacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosFinalesAnulacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosFinalesAnulacionKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("PRECIO UNITARIO:");

        tfPrecioUnitarioFinalAnulacion.setEditable(false);
        tfPrecioUnitarioFinalAnulacion.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioUnitarioFinalAnulacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitarioFinalAnulacion.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioUnitarioFinalAnulacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioFinalAnulacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioFinalAnulacionKeyTyped(evt);
            }
        });

        tfDescripcion1.setEditable(false);
        tfDescripcion1.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion1.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescripcion1.setText(" KGS. DE MIEL");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores.setForeground(new java.awt.Color(255, 255, 255));
        tfTambores.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("CONVERSION A TAMBORES:");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("IMPORTE TOTAL:");

        tfImporteTotalAnulacion.setEditable(false);
        tfImporteTotalAnulacion.setBackground(new java.awt.Color(255, 0, 0));
        tfImporteTotalAnulacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteTotalAnulacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(0, 0, 0));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfLotes.setForeground(new java.awt.Color(255, 255, 255));
        tfLotes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(tfLotes)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tfNumeroComprobante)
                                .addGap(18, 18, 18)
                                .addComponent(dcFechaAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(349, 349, 349))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(tfKilosFinalesAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfPrecioUnitarioFinalAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfImporteTotalAnulacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel11)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFechaAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(jLabel25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKilosFinalesAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPrecioUnitarioFinalAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteTotalAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        tpPresupuestos.addTab("Datos para el credito", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsbrAsociar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tpPresupuestos, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tpPresupuestos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsbrAsociar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tProductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductoresMouseClicked

        //fila = tMovimientos.rowAtPoint(evt.getPoint());
        //codigoProductor = tMovimientos.getValueAt(fila, 0).toString();
    }//GEN-LAST:event_tProductoresMouseClicked

    private void tProductores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductores1MouseClicked
    }//GEN-LAST:event_tProductores1MouseClicked

    private void tDetalleStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStockMouseClicked
    }//GEN-LAST:event_tDetalleStockMouseClicked

    private void rsbrAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrAsociarActionPerformed
        
        //Se llevan a cabo los siguientes registros:
        //1) "anulacion"
        //2) movimiento correspondiente en la cta. cte. de la empresa con el productor
        //3) si se trata de una devolucion de miel, "devolucion"
        //4) movimiento en el stock de miel, correspondiente a la devolucion de la miel en la nota de credito
        
        //chequeo de datos correctos y completos
        boolean informacionIncompleta = (tfNumeroComprobante.getText().length() == 0 || tfImporteTotalAnulacion.getText().length() == 0 || tfImporteTotalAnulacion.getText().equals("0.00") || tfImporteTotalAnulacion.getText().equals("0.0"));
        
        if (informacionIncompleta){
            
            JOptionPane.showMessageDialog(null, "La informacion correspondiente al credito se halla incompleta. Ingrese la misma correctamente por favor.", "REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tfNumeroComprobante.requestFocus();
            return;
            
        }
        
        //SE REGISTRA PRIMERO LA ANULACION EN LA TABLA ANULACION
        //se necesita conocer el numero de movimiento con el que se registra la anulacion
        //obtenido en el metodo inicializar()
        
        //obtengo la fechas de anulacion y de vencimiento del pago de la misma
        Calendar cal;
        int d, m, a;
        cal = dcFechaAnulacion.getCalendar();
        //fecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        
        String numeroComprobante = tfNumeroComprobante.getText();
        //en las vartiables importeAnulacion y totalMielIngresadaAnulacion tenemos los totales para todos los registros
        
        //1) se registra la anulacion
        CreditoPresupuestoProductor creditoPresupuesto = new CreditoPresupuestoProductor(numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a, m, d), importeCredito, totalKilosIngresadosAnulacion, codigoPresupuesto);
        creditoPresupuesto.registrarCreditoPresupuestoProductor(creditoPresupuesto);
        
        //el codigo de anulacion es auto incrementable y ya lo asine tamb como n° de comprobante
        //calcular el valor del presupuesto menos el valor de la anulacion
        double saldoComprobanteAfectado = Double.parseDouble(tfSaldoImpagoComprobante.getText().toString())- Double.parseDouble(tfImporteTotalAnulacion.getText().toString());
        
        //2) se registra el movimiento asociado a la nota de credito en la cta. cte. con el productor 
        String comprobanteAsociadoNotaCredito = "PRES. N° "+tfNumeroPresupuesto.getText();
        
        CtaCteProductor ctacte = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a, m, d), "CREDITO DE PRESUPUESTO", codigoCredito, numeroComprobante, comprobanteAsociadoNotaCredito,0.00, importeCredito, 0.00, 0.00, "CANCELADO", "");
        ctacte.registrarMovimientoCtaCteProductor(ctacte);
        
        //3) se modifica el saldo del comprobante afectado por el pago
        
        //el valor de importeAnulacion siempre esta actualizado
        //el valor de codigoComprobanteAfectadoAnulacion
        //el valor de debeComprobanteAfectado
        //el valor de haberComprobanteAfectado
        
        ctacte.actualizarSaldoComprobanteProductor(codigoComprobanteAfectadoAnulacion, codigoProductor, debeComprobanteAfectado, importeCredito, haberComprobanteAfectado);
        
        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        
        //4) se guarda la relacion entre la nc y el comprobante que se ha afectado con la misma
        ComprobantesAcreditacionComprobantesAfectadosProductor relacion = new  ComprobantesAcreditacionComprobantesAfectadosProductor();
        relacion.setCodigo_productor(codigoProductor);
        relacion.setTipo_comprobante_acreditacion("CREDITO DE PRESUPUESTO");
        relacion.setCodigo_comprobante_acreditacion(codigoCredito);
        relacion.setTipo_comprobante_afectado_credito("PRESUPUESTO");
        relacion.setCodigo_comprobante_afectado_credito(codigoPresupuesto);
        relacion.setImporte_acreditado(importeCredito);
        relacion.setEstado_acreditacion("VALIDO");
        relacion.registrarRelacionCreditoComprobanteAfectado(relacion);
        
        StockRealMiel stockMiel = new StockRealMiel();
        stockMiel.setFecha_movimiento(new Date(a, m, d));
        stockMiel.setTipo_movimiento("DEVOLUCION");
        stockMiel.setComprobante_asociado("CREDITO DE PRESUPUESTO");
        stockMiel.setId_comprobante_asociado(codigoCredito);
        
        stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
        stockMiel.setCantidad_miel(totalKilosIngresadosAnulacion);
        
        //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
        //no se almacenara nada!
        //debo obtener el codigo de la locacion a partir del nombre de la misma
        //escogido en el combo de locaciones disponibles

        stockMiel.setLocacion_miel(codigoLocacion);

        //chequeo si la miel a devolver se encuentra en la locacion de un productor
        Locacion locacion = new Locacion();
        String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);
        System.out.println(codigoLocacion);

        if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

            //se trata de una devolucion en la cual la miel devuelta se encuentra en la locacion
            //de un productor
            //cargo en el campo correspondiente el codigo del productor vendedor en esta compra
            stockMiel.setMiel_deposito_productor(codigoProductor);

            //teniendo este dato voy a poder llevar la cantidad de miel que hay en cada productor vendedor
            //viendola de manera global como "miel acopiada en locacion del productor"
            //pero pudiendo calcular y descontar o aumentar cuando sea necesario, la miel
            //comprada y depositada en cada uno de los productores correspondientes

            //cuando realice un traslado desde la locacion "locacion del productor"
            //voy a tener que descontar el stock global de dicha locacion
            //y discriminar y descontar consecuentemente la miel depositada
            //en la locacion del productor desde el cual se va a trasladar dicha miel

        }

        //se asigna a la devolucion el valor: FACTURADA, ya que es una compra con presupuesto.
        stockMiel.setEstado_compra("FACTURADA");
        stockMiel.setEstado_movimiento("VALIDO");

        //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
        stockMiel.registrarMovimientoStock(stockMiel);
        
        //ANTES DE CERRAR EL FORMULARIO ACTUALIZO LOS VALORES DE MIEL EN LA LOCACION CORRESPONDIENTE
        //ESTA TABLE SERVIRA SIEMPRE QUE HAYA QUE AJUSTAR Y COMPENSAR EL STOCK DE MIEL PAGO E IMPAGO!
        AjusteCompensacionStock ajuste = new AjusteCompensacionStock();
        Double cantidadMielPagaLocacion = ajuste.consultarCantidadMielPagaLocacion(codigoLocacion) - totalKilosIngresadosAnulacion;
        Double cantidadMielImpagaLocacion = ajuste.consultarCantidadMielImpagaLocacion(codigoLocacion);
        Double cantidadMielImpagaVendidadLocacion = ajuste.consultarCantidadMielImpagaVendidaLocacion(codigoLocacion);
        ajuste.setStock_miel_pago(cantidadMielPagaLocacion);
        ajuste.setStock_miel_impago(cantidadMielImpagaLocacion);
        ajuste.setStock_miel_impago_vendido(cantidadMielImpagaVendidadLocacion);
        ajuste.modificarValoresMielLocacion(ajuste, codigoLocacion);

        //JOptionPane.showMessageDialog(null, "La factura ha sido registrada exitosamente.","REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        this.dispose();

    }//GEN-LAST:event_rsbrAsociarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        //esto deberia cancelar la insercion de la nota de credito y la posterior asociacion de la misma a una factura
        this.dispose();
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tPresupuestosProductorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPresupuestosProductorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tPresupuestosProductorMouseEntered

    private void tPresupuestosProductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPresupuestosProductorMouseClicked
        
        //colocar en este evento el filtro de facturas pendientes y canceladas,
        //o sea, si se selecciona una factura cancelada largar aviso y return, no dejar seleccionarla!
        
        fila = tPresupuestosProductor.rowAtPoint(evt.getPoint());
        
        if (fila > -1){
            
            if (tPresupuestosProductor.getValueAt(fila, 8).toString().equals("CANCELADO")){
                
                JOptionPane.showMessageDialog(null, "El presupuesto seleccionado se encuentra cancelado. Seleccione un comprobante pendiente de cancelar por favor.", "REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
                //debo vaciar todos los campos de la segunda pestaña!
                tfNumeroPresupuesto.setText("");
                tfImportePresupuesto.setText("");

                //kilos presupuestados
                tfKilosPresupuestados.setText("");
                //importe del comprobante
                tfImporteTotalComprobante.setText("");
                //precio unitario del kilo facturado en el comprobante
                tfPrecioUnitario.setText("");
                //saldo impago del comprobante
                tfSaldoImpagoComprobante.setText("");
                //saldo pendiente del comprobante, una vez efectuado el pago!
                tfSaldoPendiente.setText("");
                tfKilosImpagos.setText("");
                tfKilosAAnular.setText("");
                tfImporteAnulacion.setText("");
                tfKilosFinalesAnulacion.setText("");
                tfPrecioUnitarioFinalAnulacion.setText("");
                tfImporteTotalAnulacion.setText("");
                tfTambores.setText("");
                tfLotes.setText("");
                
            }
            else{
                
                if (tPresupuestosProductor.getValueAt(fila, 8).toString().equals("ANULADO")){

                    JOptionPane.showMessageDialog(null, "El presupuesto seleccionado se encuentra anulado. Seleccione otro comprobante por favor.", "REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
                    //debo vaciar todos los campos de la segunda pestaña!
                    tfNumeroPresupuesto.setText("");
                    tfImportePresupuesto.setText("");

                    //kilos presupuestados
                    tfKilosPresupuestados.setText("");
                    //importe del comprobante
                    tfImporteTotalComprobante.setText("");
                    //precio unitario del kilo facturado en el comprobante
                    tfPrecioUnitario.setText("");
                    //saldo impago del comprobante
                    tfSaldoImpagoComprobante.setText("");
                    //saldo pendiente del comprobante, una vez efectuado el pago!
                    tfSaldoPendiente.setText("");
                    tfKilosImpagos.setText("");
                    tfKilosAAnular.setText("");
                    tfImporteAnulacion.setText("");
                    tfKilosFinalesAnulacion.setText("");
                    tfPrecioUnitarioFinalAnulacion.setText("");
                    tfImporteTotalAnulacion.setText("");
                    tfTambores.setText("");
                    tfLotes.setText("");

                }
                else{

                    //cada vez que se hace click sobre la grilla se muestran en los campos debajo los datos de la factura seleccionada
                    //correspondiente a la fila de la grilla cliqueada
                    tfNumeroPresupuesto.setText(tPresupuestosProductor.getValueAt(fila, 1).toString());
                    //IMPORTE FACTURADO EN EL COMPROBANTE, QUE NO ES LO MISMO QUE SALDO IMPAGO DE LA FACTURA
                    tfImportePresupuesto.setText(tPresupuestosProductor.getValueAt(fila, 4).toString());

                    //en esta variable siempre va a estar almacenado el codigo de la factura seleccionada en la grilla
                    //el cual voy a necesitar a la hora de alterar el saldo de la misma restando el valor acreditado
                    codigoPresupuesto = Integer.parseInt(tPresupuestosProductor.getValueAt(fila, 0).toString());
                    StockRealMiel stock = new StockRealMiel();
                    //para ver si estoy pasando bien el codigo del presupuesto que se selecciona
                    codigoLocacion = stock.obtenerLocacionMielADevolverEnCreditoPresupuesto(codigoPresupuesto);
                    //para ver si el valor de codigoLocacion esta bien devuelto por la funcion
                    //en esta variable siempre va a estar almacenado el codigo de movimiento que tiene el presupuesto en la cta. cte.
                    //VER COMO SE OBTIENEEEEE, ese dato esta en la tabla presupuesto!!!   
                    codigoComprobanteAfectadoAnulacion = Integer.parseInt(tPresupuestosProductor.getValueAt(fila, 2).toString());
                    //en esta variable se almacena el tipo de factura, que sirve entre otras cuestiones para ver que tipo
                    //de nota de credito se habilita en el combo de notas de credito
                    tipoComprobanteAfectadoAnulacion = "PRESUPUESTO";
                    //estas variables las uso para alterar el saldo de la factura asociada a la nota de credito nueva
                    debeComprobanteAfectado = Double.parseDouble(tPresupuestosProductor.getValueAt(fila, 4).toString());
                    Double saldo = Double.parseDouble(tPresupuestosProductor.getValueAt(fila, 5).toString());
                    haberComprobanteAfectado = debeComprobanteAfectado - saldo;

                    //Por defecto vamos a empezar asumiendo que se desean ANULAR todos los kilos impagos
                    //en el comprobante a pagarse, o sea, se desea ANULST todo el saldo impago del comprobante
                    importeCredito = saldoImpago;

                    //kilos presupuestados
                    tfKilosPresupuestados.setText(String.valueOf(tPresupuestosProductor.getValueAt(fila, 6)));
                    //importe del comprobante
                    tfImporteTotalComprobante.setText(String.valueOf(tPresupuestosProductor.getValueAt(fila, 4)));
                    //precio unitario del kilo facturado en el comprobante
                    precioUnitario = Double.valueOf(tPresupuestosProductor.getValueAt(fila, 7).toString());
                    tfPrecioUnitario.setText(String.valueOf(precioUnitario));
                    //saldo impago del comprobante
                    tfSaldoImpagoComprobante.setText(String.valueOf(tPresupuestosProductor.getValueAt(fila, 5)));
                    saldoImpago = Double.valueOf(tPresupuestosProductor.getValueAt(fila, 5).toString());
                    //saldo pendiente del comprobante, una vez efectuado el pago!
                    tfSaldoPendiente.setText(String.valueOf(tPresupuestosProductor.getValueAt(fila, 5)));
                    Double saldoPendienteDePago = Double.valueOf(tPresupuestosProductor.getValueAt(fila, 5).toString());
                    Double kilosImpagos = saldoPendienteDePago / precioUnitario;
                    totalKilosImpagos = kilosImpagos;
                    totalKilosIngresadosAnulacion = totalKilosImpagos;
                    tfKilosImpagos.setText(String.valueOf(kilosImpagos));
                    //por defecto asumimos que se devolveran todos los kilos que corresponden al saldo del comprobante
                    //mas de eso no se podria devolver
                    tfKilosAAnular.setText(String.valueOf(kilosImpagos));
                    tfImporteAnulacion.setText(String.valueOf(kilosImpagos * precioUnitario));

                    //esto es para inicializar los campos en la ultima pestaña!
                    tfKilosFinalesAnulacion.setText(String.valueOf(totalKilosImpagos));
                    Double cantidadKilos = totalKilosImpagos;
                    tfPrecioUnitarioFinalAnulacion.setText(tfPrecioUnitario.getText());
                    Double precioUnitario = Double.valueOf(tfPrecioUnitario.getText());
                    tfImporteTotalAnulacion.setText(String.valueOf(cantidadKilos * precioUnitario));

                    //para mostrar conversion de kilos a tambores y a lotes
                    //VER COMO PUEDO REDONDEAR!
                    if (tfKilosAAnular.getText().length() != 0){

                        Double kilos = Double.parseDouble(tfKilosAAnular.getText());
                        Double tambores = kilos / 300;
                        tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
                        Double lotes = kilos / 21000;
                        tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");

                    }
                    else{

                        tfTambores.setText("0 TAMBORES");
                        tfLotes.setText("0 LOTES");

                    }

                }
            }
        
        }
        
    }//GEN-LAST:event_tPresupuestosProductorMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tfKilosFinalesAnulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosFinalesAnulacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesAnulacionActionPerformed

    private void tfKilosFinalesAnulacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFinalesAnulacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesAnulacionKeyReleased

    private void tfKilosFinalesAnulacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFinalesAnulacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesAnulacionKeyTyped

    private void tfPrecioUnitarioFinalAnulacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioFinalAnulacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioFinalAnulacionKeyReleased

    private void tfPrecioUnitarioFinalAnulacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioFinalAnulacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioFinalAnulacionKeyTyped

    private void tfKilosPresupuestadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosPresupuestadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosPresupuestadosActionPerformed

    private void tfKilosPresupuestadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosPresupuestadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosPresupuestadosKeyReleased

    private void tfKilosPresupuestadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosPresupuestadosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosPresupuestadosKeyTyped

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioKeyReleased

    private void tfPrecioUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioKeyTyped

    private void tfImporteAnulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImporteAnulacionActionPerformed

    }//GEN-LAST:event_tfImporteAnulacionActionPerformed

    private void tfImporteAnulacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImporteAnulacionKeyReleased
    }//GEN-LAST:event_tfImporteAnulacionKeyReleased

    private void tfImporteAnulacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImporteAnulacionKeyTyped

        char c = evt.getKeyChar();

        if (tfImporteAnulacion.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfImporteAnulacionKeyTyped

    private void tfKilosAAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosAAnularActionPerformed
    }//GEN-LAST:event_tfKilosAAnularActionPerformed

    private void tfKilosAAnularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAAnularKeyReleased

        //CHEQUEOS A REALIZAR:
        
        //1) que no se ingrese vacio
        //2) que no se ingrese una cantidad igual a 0 kgs. a pagar
        //3) que no se ingrese una cantidad de kgs. a pagar superior a la cantidad de kgs. facturados
        //(en realidad este ultimo chequeo debe hacerse de acuerdo al saldo edl comprobante y no al total del comprobante, 
        //ya que podria darse el caso de que el comprobante se encontrara parcialmente cancelado, lo cual significa que no se
        //adeudan todos los kilos en el facturados. Dicho dato lo tengo en la variable totalKilosImpagos).
        
        //cuando los datos sean bien ingresados quedaran almacenados en la variable "totalKilosIngresadosPago"
        //y el control aca lo hago con la variable totalKilosImpagos
        
        Double kilosIngresadosDevolucion = 0.00;
        
        if (tfKilosAAnular.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfKilosAAnular.setText(String.valueOf(totalKilosImpagos));
            //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
            totalKilosIngresadosAnulacion = totalKilosImpagos;
            importeCredito = totalKilosIngresadosAnulacion * precioUnitario;
            tfImporteAnulacion.setText(String.valueOf(importeCredito));
            //esto es para ir actualizando los datos en la ultima pestaña!
            tfKilosFinalesAnulacion.setText(String.valueOf(totalKilosImpagos));
            //el precio unitario no lo toco ya que siempre es el mismo
            tfImporteTotalAnulacion.setText(String.valueOf(importeCredito));
            tfKilosAAnular.requestFocus();
            
        }
        else{
            
            kilosIngresadosDevolucion = Double.valueOf(tfKilosAAnular.getText());
            
            if (kilosIngresadosDevolucion <= 0.00){

                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfKilosAAnular.setText(String.valueOf(totalKilosImpagos));
                //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                totalKilosIngresadosAnulacion = totalKilosImpagos;
                importeCredito = totalKilosIngresadosAnulacion * precioUnitario;
                tfImporteAnulacion.setText(String.valueOf(importeCredito));
                //esto es para ir actualizando los datos en la ultima pestaña!
                tfKilosFinalesAnulacion.setText(String.valueOf(totalKilosImpagos));
                //el precio unitario no lo toco ya que siempre es el mismo
                tfImporteTotalAnulacion.setText(String.valueOf(importeCredito));
                tfKilosAAnular.requestFocus();

            }
            else{

                if (kilosIngresadosDevolucion > totalKilosImpagos){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE CREDITO DE PRESUPUESTO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                    tfKilosAAnular.setText(String.valueOf(totalKilosImpagos));
                    //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                    totalKilosIngresadosAnulacion = totalKilosImpagos;
                    importeCredito = totalKilosIngresadosAnulacion * precioUnitario;
                    tfImporteAnulacion.setText(String.valueOf(importeCredito));
                    //esto es para ir actualizando los datos en la ultima pestaña!
                    tfKilosFinalesAnulacion.setText(String.valueOf(totalKilosImpagos));
                    //el precio unitario no lo toco ya que siempre es el mismo
                    tfImporteTotalAnulacion.setText(String.valueOf(importeCredito));
                    tfKilosAAnular.requestFocus();

                }
                else{

                    totalKilosIngresadosAnulacion = kilosIngresadosDevolucion;
                    importeCredito = totalKilosIngresadosAnulacion * precioUnitario;
                    tfImporteAnulacion.setText(String.valueOf(importeCredito));
                    //y tamb modifico el campo que muestra el saldo que va a quedar desp del pago
                    saldoPendiente = saldoImpago - importeCredito;
                    tfSaldoPendiente.setText(String.valueOf(saldoPendiente));
                    //esto es para ir actualizando los datos en la ultima pestaña!
                    tfKilosFinalesAnulacion.setText(String.valueOf(totalKilosIngresadosAnulacion));
                    //el precio unitario no lo toco ya que siempre es el mismo
                    tfImporteTotalAnulacion.setText(String.valueOf(importeCredito));
                    tfKilosAAnular.requestFocus();

                }
                
            }
            
        }

        //para mostrar conversion de kilos a tambores y a lotes
        //VER COMO PUEDO REDONDEAR!
        if (tfKilosAAnular.getText().length() != 0){

            Double kilos = Double.parseDouble(tfKilosAAnular.getText());
            Double tambores = kilos / 300;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
            Double lotes = kilos / 21000;
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");

        }
        else{

            tfTambores.setText("0 TAMBORES");
            tfLotes.setText("0 LOTES");

        }
        
    }//GEN-LAST:event_tfKilosAAnularKeyReleased

    private void tfKilosAAnularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAAnularKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosAAnular.getText().contains(".") && c == '.') {
            
            getToolkit().beep();
            evt.consume();
        } 
        else
            
            if (!Character.isDigit(c)) {
            
                if (c != '.') {
                    getToolkit().beep();
                    evt.consume();
                }
            
        }
        
    }//GEN-LAST:event_tfKilosAAnularKeyTyped

    private void tpPresupuestosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tpPresupuestosFocusGained
    }//GEN-LAST:event_tpPresupuestosFocusGained

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
       
        // TODO add your handling code here:
        tfKilosAAnular.requestFocus();
        
    }//GEN-LAST:event_jPanel3FocusGained

    private void jPanel4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel4FocusGained
    }//GEN-LAST:event_jPanel4FocusGained

    private void jLabel14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel14FocusGained
       
        // TODO add your handling code here:
        tfBusquedaPorNombre.requestFocus();
        
    }//GEN-LAST:event_jLabel14FocusGained

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
       
        // TODO add your handling code here:
        tfBusquedaPorNombre.requestFocus();
        
    }//GEN-LAST:event_jPanel6FocusGained

    private void tfKilosAAnularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAAnularKeyPressed

    }//GEN-LAST:event_tfKilosAAnularKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcionesRegistro;
    public com.toedter.calendar.JDateChooser dcFechaAnulacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rsbrAsociar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tDetalleStock;
    public static javax.swing.JTable tPresupuestosProductor;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfDescripcion1;
    public static javax.swing.JTextField tfImporteAnulacion;
    public javax.swing.JTextField tfImportePresupuesto;
    public javax.swing.JTextField tfImporteTotalAnulacion;
    public javax.swing.JTextField tfImporteTotalComprobante;
    public javax.swing.JTextField tfInformacion;
    public static javax.swing.JTextField tfKilosAAnular;
    public javax.swing.JTextField tfKilosFinalesAnulacion;
    public javax.swing.JTextField tfKilosImpagos;
    public javax.swing.JTextField tfKilosPresupuestados;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfNumeroPresupuesto;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfPrecioUnitarioFinalAnulacion;
    public javax.swing.JTextField tfSaldoImpagoComprobante;
    public javax.swing.JTextField tfSaldoPendiente;
    public javax.swing.JTextField tfTambores;
    private javax.swing.JTabbedPane tpPresupuestos;
    // End of variables declaration//GEN-END:variables
}
