/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmDevolucionCompraConsignacion.totalMielDevuelta;
import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
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
public class FrmRegistroNotaCreditoProductor extends javax.swing.JInternalFrame {
        
    static int codigoProductor;
    static String nombreProductor;
    int codigoNotaCredito, codigoMovimientoCtaCte, codigoFactura, codigoLocacion, codigoComprobanteAfectadoNotaCredito;
    String tipoComprobanteAfectadoNotaCredito, nombreLocacion;
    Double totalMielFacturada, totalMielIngresadaDevolucion, importeFactura;
    Double importeNotaCredito, precioUnitario, saldoPendiente, saldoImpago, debeComprobanteAfectado, haberComprobanteAfectado, totalkilosFacturados, totalKilosImpagos, totalKilosIngresadosDevolucion;
    int fila = -1;
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroNotaCreditoProductor() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        mostrarFacturasProductor(codigoProductor);
        ocultarColumnas();
        
        Calendar cal = new GregorianCalendar();
        dcFechaNotaCredito.setCalendar(cal);
        
        //se almacena en la variable codigoMovimientoCtaCte
        //el numero de mov que tendra en el a cta. cte el pago a registrar
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;
        
        //para ir controlando y comparando lo ingresado por el usuario con lo que esta registrado en la factura
        totalMielFacturada = 0.00;
        totalMielIngresadaDevolucion = 0.00;
        importeFactura = 0.00;
        importeNotaCredito = 0.00;
        
        lLocacionMielFacturaI.setText("");
        
        tFacturasProductor.requestFocus();
        
    }

    public static void mostrarFacturasProductor(int codigoProductor) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Productor productor = new Productor();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = productor.listarFacturasProductor(codigoProductor);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tFacturasProductor.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnas() {

        tFacturasProductor.getColumnModel().getColumn(0).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(0).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(0).setPreferredWidth(0);

        tFacturasProductor.getColumnModel().getColumn(3).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(3).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(3).setPreferredWidth(0);

        /*tFacturasProductor.getColumnModel().getColumn(1).setMaxWidth(0);
        tDetalleStFacturasProductortock1.getColumnModel().getColumn(1).setMinWidth(0);
        tDetalleSttFacturasProductorock1.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tFacturasProductor().}getColumn(2).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(2).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        
        /*tFacturasProductor.getColumnModel().getColumn(3).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(3).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        /*tFacturasProductor.getColumnModel().getColumn(4).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tFacturasProductor.getColumnModel().getColumn(4).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tFacturasProductor.getColumnModel().getColumn(4).setMaxWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setMinWidth(0);
        tFacturasProductor.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender7 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender8 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tFacturasProductor.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tFacturasProductor.getColumnModel().getColumn(2).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.CENTER);
        tFacturasProductor.getColumnModel().getColumn(3).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tFacturasProductor.getColumnModel().getColumn(4).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tFacturasProductor.getColumnModel().getColumn(5).setCellRenderer(cellRender5);
        cellRender6.setHorizontalAlignment(SwingConstants.RIGHT);
        tFacturasProductor.getColumnModel().getColumn(6).setCellRenderer(cellRender6);
        cellRender7.setHorizontalAlignment(SwingConstants.RIGHT);
        tFacturasProductor.getColumnModel().getColumn(7).setCellRenderer(cellRender7);
        cellRender8.setHorizontalAlignment(SwingConstants.CENTER);
        tFacturasProductor.getColumnModel().getColumn(8).setCellRenderer(cellRender8);
        
        ((DefaultTableCellRenderer) tFacturasProductor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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
        jLabel37 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        rsbrAsociar = new rojeru_san.RSButtonRiple();
        tpFactura = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tFacturasProductor = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfTipoFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfNumeroFactura = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfImporteFactura = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        tfInformacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        tfKilosFacturados = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tfImporteTotalComprobante = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tfSaldoImpagoComprobante = new javax.swing.JTextField();
        tfImporteNotaCredito = new javax.swing.JTextField();
        tfKilosADevolver = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfSaldoPendiente = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfKilosImpagos = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lLocacionMielFacturaI = new javax.swing.JLabel();
        lLocacionMielFacturaII = new javax.swing.JLabel();
        lLocacionMielFacturaIII = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        dcFechaNotaCredito = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfKilosFinalesNC = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfPrecioUnitarioFinalNC = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbTipoNotaCredito = new javax.swing.JComboBox<>();
        tfDescripcion1 = new javax.swing.JTextField();
        tfTambores = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfImporteTotalNC = new javax.swing.JTextField();
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

        jLabel37.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("LIMITE KGS.:");

        setBackground(new java.awt.Color(51, 84, 111));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE NOTA DE CREDITO DE PRODUCTOR - CAM HONEY BROTHERS");
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
        rsbrAsociar.setText("REGISTRAR NOTA DE CREDITO");
        rsbrAsociar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrAsociar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrAsociarActionPerformed(evt);
            }
        });

        tpFactura.setBackground(new java.awt.Color(51, 84, 111));
        tpFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tpFactura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tpFacturaFocusGained(evt);
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
        jLabel6.setText("SELECCIONE LA FACTURA QUE SE DESEA ASOCIAR:");

        jLabel7.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MOSTRAR FACTURAS EN EL PERIODO:");

        tfBusquedaPorNombre.setEditable(false);
        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

        tFacturasProductor.setBackground(new java.awt.Color(36, 33, 33));
        tFacturasProductor.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        tFacturasProductor.setForeground(new java.awt.Color(207, 207, 207));
        tFacturasProductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tFacturasProductor.setOpaque(true);
        tFacturasProductor.setRowHeight(20);
        tFacturasProductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tFacturasProductorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tFacturasProductorMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(tFacturasProductor);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("HA SELECCIONADO EL COMPROBANTE:");
        jLabel14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLabel14FocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TIPO:");

        tfTipoFactura.setEditable(false);
        tfTipoFactura.setBackground(new java.awt.Color(51, 84, 111));
        tfTipoFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTipoFactura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N°:");

        tfNumeroFactura.setEditable(false);
        tfNumeroFactura.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroFactura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("IMPORTE:");

        tfImporteFactura.setEditable(false);
        tfImporteFactura.setBackground(new java.awt.Color(51, 84, 111));
        tfImporteFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteFactura.setForeground(new java.awt.Color(255, 255, 255));

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
                                    .addComponent(tfTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfImporteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14))
                        .addGap(0, 187, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfImporteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tpFactura.addTab("Facturas del productor", jPanel6);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("INGRESE LA INFORMACION CORRESPONDIENTE A LA N. DE CREDITO:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SE ESTA REGISTRANDO UNA NOTA DE CREDITO CORRESPONDIENTE A:");

        tfInformacion.setEditable(false);
        tfInformacion.setBackground(new java.awt.Color(0, 0, 0));
        tfInformacion.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfInformacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DATOS DE LA FACTURA A ASOCIAR A LA NOTA DE CREDITO:");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("KGS. FACTURADOS:");

        tfKilosFacturados.setEditable(false);
        tfKilosFacturados.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosFacturados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosFacturados.setPreferredSize(new java.awt.Dimension(137, 23));
        tfKilosFacturados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosFacturadosActionPerformed(evt);
            }
        });
        tfKilosFacturados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosFacturadosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosFacturadosKeyTyped(evt);
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
        jLabel35.setText("IMPORTE FACTURA:");

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

        tfImporteNotaCredito.setEditable(false);
        tfImporteNotaCredito.setBackground(new java.awt.Color(51, 84, 111));
        tfImporteNotaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteNotaCredito.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImporteNotaCreditoActionPerformed(evt);
            }
        });
        tfImporteNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfImporteNotaCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfImporteNotaCreditoKeyTyped(evt);
            }
        });

        tfKilosADevolver.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosADevolver.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosADevolver.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosADevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosADevolverActionPerformed(evt);
            }
        });
        tfKilosADevolver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfKilosADevolverKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosADevolverKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosADevolverKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("SALDO PENDIENTE:");

        tfSaldoPendiente.setEditable(false);
        tfSaldoPendiente.setBackground(new java.awt.Color(255, 0, 0));
        tfSaldoPendiente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("* INGRESE KGS. A DEVOLVER:");

        tfKilosImpagos.setEditable(false);
        tfKilosImpagos.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosImpagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("KGS. DISPONIBLES:");

        jLabel39.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("IMPORTE TOTAL DE LA NC:");

        lLocacionMielFacturaI.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lLocacionMielFacturaI.setForeground(new java.awt.Color(255, 255, 0));
        lLocacionMielFacturaI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lLocacionMielFacturaI.setText("Miel acopiada en la locacion: ...");

        lLocacionMielFacturaII.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lLocacionMielFacturaII.setForeground(new java.awt.Color(255, 255, 255));
        lLocacionMielFacturaII.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lLocacionMielFacturaII.setText("La miel ingresada en la nota de credito sera descontada del stock en dicha locacion.");

        lLocacionMielFacturaIII.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lLocacionMielFacturaIII.setForeground(new java.awt.Color(255, 255, 255));
        lLocacionMielFacturaIII.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lLocacionMielFacturaIII.setText("(Y tambien del stock global de la empresa.)");

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
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(5, 5, 5)
                                        .addComponent(tfKilosFacturados, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(jLabel36)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfImporteNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfKilosADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfKilosImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lLocacionMielFacturaI)
                            .addComponent(lLocacionMielFacturaII)
                            .addComponent(lLocacionMielFacturaIII))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKilosImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKilosFacturados, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKilosADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfImporteNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lLocacionMielFacturaI, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLocacionMielFacturaII, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLocacionMielFacturaIII, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpFactura.addTab("Datos de la factura y de la devolucion de miel", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 84, 111));
        jPanel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel4FocusGained(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("INGRESE LA INFORMACION DE LA NOTA DE CREDITO:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("* FECHA:");

        dcFechaNotaCredito.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaNotaCredito.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaNotaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dcFechaNotaCredito.setPreferredSize(new java.awt.Dimension(0, 23));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("* N° COMPROBANTE:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ITEM:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("KGS.:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));
        tfNumeroComprobante.setPreferredSize(new java.awt.Dimension(0, 23));

        tfKilosFinalesNC.setEditable(false);
        tfKilosFinalesNC.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosFinalesNC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosFinalesNC.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosFinalesNC.setPreferredSize(new java.awt.Dimension(0, 23));
        tfKilosFinalesNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosFinalesNCActionPerformed(evt);
            }
        });
        tfKilosFinalesNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosFinalesNCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosFinalesNCKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("PRECIO UNITARIO:");

        tfPrecioUnitarioFinalNC.setEditable(false);
        tfPrecioUnitarioFinalNC.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioUnitarioFinalNC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitarioFinalNC.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioUnitarioFinalNC.setPreferredSize(new java.awt.Dimension(0, 23));
        tfPrecioUnitarioFinalNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioFinalNCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioFinalNCKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("* TIPO DE NOTA DE CREDITO:");

        cbTipoNotaCredito.setBackground(new java.awt.Color(255, 255, 0));
        cbTipoNotaCredito.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoNotaCredito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOTA DE CREDITO A", "NOTA DE CREDITO C" }));
        cbTipoNotaCredito.setEnabled(false);
        cbTipoNotaCredito.setPreferredSize(new java.awt.Dimension(0, 23));

        tfDescripcion1.setEditable(false);
        tfDescripcion1.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion1.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescripcion1.setText(" KGS. DE MIEL");
        tfDescripcion1.setPreferredSize(new java.awt.Dimension(0, 23));

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores.setForeground(new java.awt.Color(255, 255, 255));
        tfTambores.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTambores.setPreferredSize(new java.awt.Dimension(0, 23));
        tfTambores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTamboresActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("CONVERSION A TAMBORES:");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("IMPORTE TOTAL:");

        tfImporteTotalNC.setEditable(false);
        tfImporteTotalNC.setBackground(new java.awt.Color(255, 0, 0));
        tfImporteTotalNC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteTotalNC.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteTotalNC.setPreferredSize(new java.awt.Dimension(0, 23));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(0, 0, 0));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfLotes.setForeground(new java.awt.Color(255, 255, 255));
        tfLotes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLotes.setPreferredSize(new java.awt.Dimension(0, 23));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                        .addComponent(tfKilosFinalesNC, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfPrecioUnitarioFinalNC, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfImporteTotalNC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbTipoNotaCredito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcFechaNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(tfLotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(29, 29, 29))
                    .addComponent(cbTipoNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcFechaNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(tfKilosFinalesNC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPrecioUnitarioFinalNC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteTotalNC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(203, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos para la nota de credito", jPanel4);

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
            .addComponent(tpFactura, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        fila = tDetalleStock.rowAtPoint(evt.getPoint());

    }//GEN-LAST:event_tDetalleStockMouseClicked

    private void rsbrAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrAsociarActionPerformed
        
        //Se llevan a cabo los siguientes registros:
        //1) "nota de credito"
        //2) movimiento correspondiente en la cta. cte. de la empresa con el productor
        //3) si se trata de una devolucion de miel, "devolucion"
        //4) movimiento en el stock de miel, correspondiente a la devolucion de la miel en la nota de credito
        
        //chequeo de datos correctos y completos
        boolean informacionIncompleta = (cbTipoNotaCredito.getSelectedItem().equals("SELECCIONAR") || tfNumeroComprobante.getText().length() == 0 || tfImporteTotalNC.getText().length() == 0 || tfImporteTotalNC.getText().equals("0.00") || tfImporteTotalNC.getText().equals("0.0"));
        
        if (informacionIncompleta){
            
            JOptionPane.showMessageDialog(null, "La informacion correspondiente a la nota de credito se halla incompleta. Ingrese la misma correctamente.", "REGISTRO DE NOTA DE CREIDTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            cbTipoNotaCredito.requestFocus();
            return;
            
        }
        
        //SE REGISTRA PRIMERO LA NOTA DE CREDITO EN LA TABLA NOTA_CREDITO_PRODUCTOR
        //se necesita conocer el numero de movimiento con el que se registra la nota de credito
        //obtenido en el metodo inicializar()
        
        //obtengo las fechas de nota de credito y de vencimiento del pago de la misma
        Calendar cal;
        int d, m, a;
        cal = dcFechaNotaCredito.getCalendar();
        //fecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        
        //obtengo tipo de nota de credito
        String tipoNotaCredito = cbTipoNotaCredito.getSelectedItem().toString();
        String numeroComprobante = tfNumeroComprobante.getText();
        //en las vartiables importeNotaCredito y totalMielIngresadaDevolucion tenemos los totales para todos los registros
        
        //1) se registra la nota de credito
        NotaCreditoProductor nCredito = new NotaCreditoProductor(numeroComprobante, tipoNotaCredito, codigoMovimientoCtaCte, codigoProductor, new Date(a, m, d), importeNotaCredito, totalKilosIngresadosDevolucion, codigoFactura);
        nCredito.registrarNotaCreditoProductor(nCredito);
        
        //obtengo codigo de nota de credito y saldo del comprobante afectado por la nota de credito
        //para guardarlo en la tabla cta. cte.
        codigoNotaCredito = nCredito.mostrarIdNotaCreditoProductor();
        //calcular el valor de la factura menos le valor de la nota de credito
        double saldoComprobanteAfectado = Double.parseDouble(tfSaldoImpagoComprobante.getText().toString())- Double.parseDouble(tfImporteTotalNC.getText().toString());
        
        //2) se registra el movimiento asociado a la nota de credito en la cta. cte. con el productor 
        String comprobanteAsociadoNotaCredito = "";
        if (tipoNotaCredito.equals("NOTA DE CREDITO A")){
            
            comprobanteAsociadoNotaCredito = "FACT. A N° "+tfNumeroFactura.getText();
        
        }
        else{

            comprobanteAsociadoNotaCredito = "FACT. C N° "+tfNumeroFactura.getText();

        }
        
        CtaCteProductor ctacte = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a, m, d), tipoNotaCredito, codigoNotaCredito, numeroComprobante, comprobanteAsociadoNotaCredito,0.00, 0.00, importeNotaCredito, 0.00, "CANCELADO", "");
        ctacte.registrarMovimientoCtaCteProductor(ctacte);
        
        //3) se modifica el saldo del comprobante afectado por el pago
        
        //el valor de importeNotaCredito siempre esta actualizado
        //el valor de codigoComprobanteAfectadoNotaCredito
        //el valor de debeComprobanteAfectado
        //el valor de haberComprobanteAfectado
        
        ctacte.actualizarSaldoComprobanteProductor(codigoComprobanteAfectadoNotaCredito, codigoProductor, debeComprobanteAfectado, importeNotaCredito, haberComprobanteAfectado);
        
        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        
        //4) se registra la devolucion de miel ingresada en la nc

        //Se obtiene el numero de movimiento que tendra el comprobante de devolucion en la cuenta corriente con el productor
        //ademas en la variable codigoMovimientoCtaCteCompra ya tenemos almacenado el numero de movimiento correspndiente
        //a la compra en consignacion, ya que a la misma se le debe editar el estado en algunos casos (pasandolo a CANCELADO)   
        /*CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;

        DevolucionProductor devolucion = new DevolucionProductor(numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a, m, d), totalMielIngresadaDevolucion);
        if (devolucion.registrarDevolucionProductor(devolucion)){

            //esto es para almacenar la relacion entre el comprobante de devolucion que se esta almacenando
            //y la compra en consignacion a la que esta afectando dicho comprobante
            ComprobantesRelacionadosCompraConsignacion comprobanteRelacionado = new ComprobantesRelacionadosCompraConsignacion();

            //SE REGISTRA LA RELACION ENTRE EL COMPROBANTE Y LA COMPRA EN CONSIGNACION (para saber que cantidad de kgs.
            //se abonaron con este comprobante: factura a, b o presupuesto)
            comprobanteRelacionado.setCodigoProductor(codigoProductor);
            comprobanteRelacionado.setCodigoCompra(codigoCompra);
            comprobanteRelacionado.setCodigo_comprobante_relacionado(codigoDevolucion);
            comprobanteRelacionado.setTipo_comprobante_relacionado(tipoComprobante);
            comprobanteRelacionado.setCantidadMielAfectada(totalMielDevuelta);
            comprobanteRelacionado.relacionarComprobanteACompraConsignacion(comprobanteRelacionado);

            //Ahora se guarda el movimiento correspondiente a la factura o presupuesto, en la cta. cte. de la empresa con el productor
            ctacteProductor.setCodigoProductor(codigoProductor);
            ctacteProductor.setCodigoMovimiento(codigoMovimientoCtaCte);
            ctacteProductor.setFechaMovimiento(new Date(a1, m1, d1));
            ctacteProductor.setDescripcionMovimiento(tipoComprobante);
            ctacteProductor.setComprobanteAsociado(codigoDevolucion);
            ctacteProductor.setNumeroComprobante(numeroComprobante);
            ctacteProductor.setCantidadMiel(totalMielDevuelta);
            ctacteProductor.setDebe(0.00);
            ctacteProductor.setHaber(0.00);
            ctacteProductor.setSaldo(0.00);
            //se guarda con estado de comprobante como "PENDIENTE", ya que obviamente se acaba de facturar y esta impago
            ctacteProductor.setEstadoMovimiento("CANCELADO");
            ctacteProductor.setObservacion("");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //ADEMAS:
            //Una vez cargado el comprobante de devolucion por la cantidad de miel que se haya decidido devolver
            //es necesario realizar el siguiente analisis:
            //1) Se devuelve la compra en consignacion completa
            //2) Se devuelve una parte de la compra en consignacion y el restante se mantiene en consignacion
            
            if (totalMielMantenidaEnConsignacion != 0.00){

                //significa que no se devolvio toda la miel comprada en consignacion
                JOptionPane.showMessageDialog(null, "Se devolvieron: "+totalMielDevuelta+" kgs. de miel. Se mantendran en consignacion: "+totalMielMantenidaEnConsignacion+" kgs. de miel.", "DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

            }
            else{

                //Significa que se devolvio toda la miel comprada en consignacion, se debe CANCELAR la compra en consignacion
                JOptionPane.showMessageDialog(null, "Se devolvieron: "+totalMielDevuelta+" kgs. de miel. La compra en consignacion ha sido cancelada.", "DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

                //El estado de la compra en consignacion pasa a ser "CANCELADO", se debe editar tal movimiento en cta. cte.
                //tengo que obtener el codigoMovimientoCtaCteCompra pero de la compra en consignacion, para pder cancelarla!!!
                ctacteProductor.cancelarCompraConsignacion(codigoMovimientoCtaCteCompra, codigoProductor);

            }

        }*/
        
        //ULTIMO PASO A REALIZAR:
        //El stock global de la empresa debe alterarse y reflejar el cambio realizado
        //ya que ahora se cuenta con menos miel "impaga" debido a la devolucion de la misma
        //(las devoluciones restan el stock globlal de miel impaga de la empresa, y el stock de miel impaga
        //de la locacion en la que se encuentre la miel devuelta)
        //SE DEBE RESTAR LA MISMA CANTIDAD DEL STOCK DE MIEL "IMPAGO", YA QUE LA MIEL
        //EN CONSIGNACION DEVUELTA YA NO ES PARTE DEL STOCK DE MIEL "IMPAGO" DE LA EMPRESA

        StockRealMiel stockMiel = new StockRealMiel();
        stockMiel.setFecha_movimiento(new Date(a, m, d));
        stockMiel.setTipo_movimiento("DEVOLUCION");
        stockMiel.setComprobante_asociado(tipoNotaCredito);
        stockMiel.setId_comprobante_asociado(codigoNotaCredito);
        
        stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
        stockMiel.setCantidad_miel(totalKilosIngresadosDevolucion);
        
        //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
        //no se almacenara nada!
        //debo obtener el codigo de la locacion a partir del nombre de la misma
        //escogido en el combo de locaciones disponibles

        stockMiel.setLocacion_miel(codigoLocacion);

        //chequeo si la miel a devolver se encuentra en la locacion de un productor
        Locacion locacion = new Locacion();
        String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

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

        //se asigna a la devolucion el valor: FACTURADA, ya que es una compra con factura.
        stockMiel.setEstado_compra("FACTURADA");

        //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
        stockMiel.registrarMovimientoStock(stockMiel);
        
        //NO SE ESTA REGISTRANDO LA DEVOLUCION DE MIEL CORRESPONDIENTE A LA NOTA DE CREDITO A NIVEL STOCK DE MIEL

        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        this.dispose();

    }//GEN-LAST:event_rsbrAsociarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        //esto deberia cancelar la insercion de la nota de credito y la posterior asociacion de la misma a una factura
        this.dispose();
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tFacturasProductorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFacturasProductorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tFacturasProductorMouseEntered

    private void tFacturasProductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFacturasProductorMouseClicked
        
        //colocar en este evento el filtro de facturas pendientes y canceladas,
        //o sea, si se selecciona una factura cancelada largar aviso y return, no dejar seleccionarla!
        
        fila = tFacturasProductor.rowAtPoint(evt.getPoint());
        
        if (fila > -1){
            
            if (tFacturasProductor.getValueAt(fila, 9).toString().equals("CANCELADO")){
                
                JOptionPane.showMessageDialog(null, "La factura seleccionada se encuentra cancelada. Seleccione un comprobante pendiente de cancelar por favor.", "REGISTRO DE NOTA DE CREDITO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
                //debo vaciar todos los campos de la segunda pestaña!
                tfTipoFactura.setText("");
                tfNumeroFactura.setText("");
                tfImporteFactura.setText("");
                cbTipoNotaCredito.setEnabled(true);
                cbTipoNotaCredito.setSelectedIndex(0);
                cbTipoNotaCredito.setEnabled(false);

                lLocacionMielFacturaI.setText("");

                //kilos facturados
                tfKilosFacturados.setText("");
                //importe del comprobante
                tfImporteTotalComprobante.setText("");
                //precio unitario del kilo facturado en el comprobante
                tfPrecioUnitario.setText("");
                //saldo impago del comprobante
                tfSaldoImpagoComprobante.setText("");
                
                tfKilosImpagos.setText("");
                tfKilosADevolver.setText("");
                tfImporteNotaCredito.setText("");
                //saldo pendiente del comprobante, una vez efectuado el pago!
                tfSaldoPendiente.setText("");

                tfKilosFinalesNC.setText("");
                tfPrecioUnitarioFinalNC.setText("");
                tfImporteTotalNC.setText("");
                tfTambores.setText("");
                tfLotes.setText("");
                
            }
            else{

                //cada vez que se hace click sobre la grilla se muestran en los campos debajo los datos
                //de la factura seleccionada correspondiente a la fila de la grilla cliqueada
                tfTipoFactura.setText(tFacturasProductor.getValueAt(fila, 1).toString());
                tfNumeroFactura.setText(tFacturasProductor.getValueAt(fila, 2).toString());
                //IMPORTE FACTURADO EN EL COMPROBANTE, QUE NO ES LO MISMO QUE SALDO IMPAGO DE LA FACTURA
                tfImporteFactura.setText(tFacturasProductor.getValueAt(fila, 5).toString());

                //asigno valores que debera mostrar el formulario de pago al productor
                tfInformacion.setText(tFacturasProductor.getValueAt(fila, 1).toString()+" N° "+tFacturasProductor.getValueAt(fila, 2).toString()+" / Productor N° "+codigoProductor+": "+nombreProductor);

                //en esta variable siempre va a estar almacenado el codigo de la factura seleccionada en la grilla
                //el cual voy a necesitar a la hora de alterar el saldo de la misma restando el valor acreditado
                codigoFactura = Integer.parseInt(tFacturasProductor.getValueAt(fila, 0).toString());
                StockRealMiel stock = new StockRealMiel();
                Locacion locacion = new Locacion();
                //locacion donde se almaceno la miel facturada en el comprobante
                codigoLocacion = stock.obtenerLocacionMielADevolverEnNotaCredito(codigoFactura);
                nombreLocacion = locacion.mostrarNombreLocacion(codigoLocacion);
                lLocacionMielFacturaI.setText("Miel acopiada en la locacion: "+nombreLocacion);
                //en esta variable siempre va a estar almacenado el codigo de movimiento que tiene la factura en la cta. cte.
                codigoComprobanteAfectadoNotaCredito = Integer.parseInt(tFacturasProductor.getValueAt(fila, 3).toString());
                //en esta variable se almacena el tipo de factura, que sirve entre otras cuestiones para ver que tipo
                //de nota de credito se habilita en el combo de notas de credito
                tipoComprobanteAfectadoNotaCredito = tFacturasProductor.getValueAt(fila, 1).toString();
                //habilito notas de credito A o notas de credito C
                if (tipoComprobanteAfectadoNotaCredito.equals("FACTURA A")){

                    //la nota de credito debe ser A
                    cbTipoNotaCredito.setEnabled(true);
                    cbTipoNotaCredito.setSelectedIndex(0);
                    cbTipoNotaCredito.setEnabled(false);

                }
                else{

                    //la nota de credito debe ser C
                    cbTipoNotaCredito.setEnabled(true);
                    cbTipoNotaCredito.setSelectedIndex(1);
                    cbTipoNotaCredito.setEnabled(false);

                }
                
                //estas variables las uso para alterar el saldo de la factura asociada a la nota de credito nueva
                debeComprobanteAfectado = Double.parseDouble(tFacturasProductor.getValueAt(fila, 5).toString());
                saldoImpago = Double.valueOf(tFacturasProductor.getValueAt(fila, 6).toString());
                haberComprobanteAfectado = debeComprobanteAfectado - saldoImpago;

                //Por defecto vamos a empezar asumiendo que se desean abonar todos los kilos impagos
                //en el comprobante a pagarse, o sea, se desea abonar todo el saldo impago del comprobante
                importeNotaCredito = saldoImpago;

                //kilos facturados
                tfKilosFacturados.setText(String.valueOf(tFacturasProductor.getValueAt(fila, 7)));
                //importe del comprobante
                tfImporteTotalComprobante.setText(String.valueOf(tFacturasProductor.getValueAt(fila, 5)));
                //precio unitario del kilo facturado en el comprobante
                precioUnitario = Double.valueOf(tFacturasProductor.getValueAt(fila, 8).toString());
                tfPrecioUnitario.setText(String.valueOf(precioUnitario));
                //saldo impago del comprobante
                tfSaldoImpagoComprobante.setText(String.valueOf(tFacturasProductor.getValueAt(fila, 6)));
                
                //en SALDO PENDIENTE deberia guardarse: SALDO IMPAGO - IMPORET NOTA CREDITO (que se vaya ingresando)
                //saldo pendiente del comprobante, una vez efectuado el pago!
                Double saldoPendienteDePago = saldoImpago - importeNotaCredito;
                tfSaldoPendiente.setText(String.valueOf(saldoPendienteDePago));
                Double kilosImpagos = saldoImpago / precioUnitario;
                totalKilosImpagos = kilosImpagos;
                totalKilosIngresadosDevolucion = totalKilosImpagos;
                tfKilosImpagos.setText(String.valueOf(kilosImpagos));
                //por defecto asumimos que se devolveran todos los kilos que corresponden al saldo del comprobante
                //mas de eso no se podria devolver
                tfKilosADevolver.setText(String.valueOf(kilosImpagos));
                tfImporteNotaCredito.setText(String.valueOf(kilosImpagos * precioUnitario));

                //esto es para inicializar los campos en la ultima pestaña!
                tfKilosFinalesNC.setText(String.valueOf(totalKilosImpagos));
                Double cantidadKilos = totalKilosImpagos;
                tfPrecioUnitarioFinalNC.setText(tfPrecioUnitario.getText());
                Double precioUnitario = Double.valueOf(tfPrecioUnitario.getText());
                tfImporteTotalNC.setText(String.valueOf(cantidadKilos * precioUnitario));

                //para mostrar conversion de kilos a tambores y a lotes
                //VER COMO PUEDO REDONDEAR!
                if (tfKilosADevolver.getText().length() != 0){

                    Double kilos = Double.parseDouble(tfKilosADevolver.getText());
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
        
    }//GEN-LAST:event_tFacturasProductorMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tfKilosFinalesNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosFinalesNCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesNCActionPerformed

    private void tfKilosFinalesNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFinalesNCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesNCKeyReleased

    private void tfKilosFinalesNCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFinalesNCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFinalesNCKeyTyped

    private void tfPrecioUnitarioFinalNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioFinalNCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioFinalNCKeyReleased

    private void tfPrecioUnitarioFinalNCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioFinalNCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioFinalNCKeyTyped

    private void tfKilosFacturadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosFacturadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFacturadosActionPerformed

    private void tfKilosFacturadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFacturadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFacturadosKeyReleased

    private void tfKilosFacturadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosFacturadosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKilosFacturadosKeyTyped

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioKeyReleased

    private void tfPrecioUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioKeyTyped

    private void tfImporteNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImporteNotaCreditoActionPerformed

    }//GEN-LAST:event_tfImporteNotaCreditoActionPerformed

    private void tfImporteNotaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImporteNotaCreditoKeyReleased
    }//GEN-LAST:event_tfImporteNotaCreditoKeyReleased

    private void tfImporteNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImporteNotaCreditoKeyTyped

        char c = evt.getKeyChar();

        if (tfImporteNotaCredito.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfImporteNotaCreditoKeyTyped

    private void tfKilosADevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosADevolverActionPerformed
    }//GEN-LAST:event_tfKilosADevolverActionPerformed

    private void tfKilosADevolverKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosADevolverKeyReleased

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
        
        if (tfKilosADevolver.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfKilosADevolver.setText(String.valueOf(totalKilosImpagos));
            //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
            totalKilosIngresadosDevolucion = totalKilosImpagos;
            importeNotaCredito = totalKilosIngresadosDevolucion * precioUnitario;
            tfImporteNotaCredito.setText(String.valueOf(importeNotaCredito));
            //esto es para ir actualizando los datos en la ultima pestaña!
            tfKilosFinalesNC.setText(String.valueOf(totalKilosImpagos));
            //el precio unitario no lo toco ya que siempre es el mismo
            tfImporteTotalNC.setText(String.valueOf(importeNotaCredito));
            tfKilosADevolver.requestFocus();
            
        }
        else{
            
            kilosIngresadosDevolucion = Double.valueOf(tfKilosADevolver.getText());
            
            if (kilosIngresadosDevolucion <= 0.00){

                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfKilosADevolver.setText(String.valueOf(totalKilosImpagos));
                //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                totalKilosIngresadosDevolucion = totalKilosImpagos;
                importeNotaCredito = totalKilosIngresadosDevolucion * precioUnitario;
                tfImporteNotaCredito.setText(String.valueOf(importeNotaCredito));
                //esto es para ir actualizando los datos en la ultima pestaña!
                tfKilosFinalesNC.setText(String.valueOf(totalKilosImpagos));
                //el precio unitario no lo toco ya que siempre es el mismo
                tfImporteTotalNC.setText(String.valueOf(importeNotaCredito));
                tfKilosADevolver.requestFocus();

            }
            else{

                if (kilosIngresadosDevolucion > totalKilosImpagos){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                    tfKilosADevolver.setText(String.valueOf(totalKilosImpagos));
                    //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                    totalKilosIngresadosDevolucion = totalKilosImpagos;
                    importeNotaCredito = totalKilosIngresadosDevolucion * precioUnitario;
                    tfImporteNotaCredito.setText(String.valueOf(importeNotaCredito));
                    //esto es para ir actualizando los datos en la ultima pestaña!
                    tfKilosFinalesNC.setText(String.valueOf(totalKilosImpagos));
                    //el precio unitario no lo toco ya que siempre es el mismo
                    tfImporteTotalNC.setText(String.valueOf(importeNotaCredito));
                    tfKilosADevolver.requestFocus();

                }
                else{

                    totalKilosIngresadosDevolucion = kilosIngresadosDevolucion;
                    importeNotaCredito = totalKilosIngresadosDevolucion * precioUnitario;
                    tfImporteNotaCredito.setText(String.valueOf(importeNotaCredito));
                    //y tamb modifico el campo que muestra el saldo que va a quedar desp del pago
                    saldoPendiente = saldoImpago - importeNotaCredito;
                    tfSaldoPendiente.setText(String.valueOf(saldoPendiente));
                    //esto es para ir actualizando los datos en la ultima pestaña!
                    tfKilosFinalesNC.setText(String.valueOf(totalKilosIngresadosDevolucion));
                    //el precio unitario no lo toco ya que siempre es el mismo
                    tfImporteTotalNC.setText(String.valueOf(importeNotaCredito));
                    tfKilosADevolver.requestFocus();

                }
                
            }
            
        }

        //para mostrar conversion de kilos a tambores y a lotes
        //VER COMO PUEDO REDONDEAR!
        if (tfKilosADevolver.getText().length() != 0){

            Double kilos = Double.parseDouble(tfKilosADevolver.getText());
            Double tambores = kilos / 300;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
            Double lotes = kilos / 21000;
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");

        }
        else{

            tfTambores.setText("0 TAMBORES");
            tfLotes.setText("0 LOTES");

        }
        
    }//GEN-LAST:event_tfKilosADevolverKeyReleased

    private void tfKilosADevolverKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosADevolverKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosADevolver.getText().contains(".") && c == '.') {
            
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
        
    }//GEN-LAST:event_tfKilosADevolverKeyTyped

    private void tpFacturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tpFacturaFocusGained
    }//GEN-LAST:event_tpFacturaFocusGained

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
       
        // TODO add your handling code here:
        tfKilosADevolver.requestFocus();
        
    }//GEN-LAST:event_jPanel3FocusGained

    private void jPanel4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel4FocusGained
       
        // TODO add your handling code here:
        cbTipoNotaCredito.requestFocus();
        
    }//GEN-LAST:event_jPanel4FocusGained

    private void jLabel14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel14FocusGained
       
        // TODO add your handling code here:
        tfBusquedaPorNombre.requestFocus();
        
    }//GEN-LAST:event_jLabel14FocusGained

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
       
        // TODO add your handling code here:
        tfBusquedaPorNombre.requestFocus();
        
    }//GEN-LAST:event_jPanel6FocusGained

    private void tfKilosADevolverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosADevolverKeyPressed

    }//GEN-LAST:event_tfKilosADevolverKeyPressed

    private void tfTamboresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTamboresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTamboresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcionesRegistro;
    public javax.swing.JComboBox<String> cbTipoNotaCredito;
    public com.toedter.calendar.JDateChooser dcFechaNotaCredito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
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
    private javax.swing.JLabel lLocacionMielFacturaI;
    private javax.swing.JLabel lLocacionMielFacturaII;
    private javax.swing.JLabel lLocacionMielFacturaIII;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rsbrAsociar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tDetalleStock;
    public static javax.swing.JTable tFacturasProductor;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfDescripcion1;
    public javax.swing.JTextField tfImporteFactura;
    public static javax.swing.JTextField tfImporteNotaCredito;
    public javax.swing.JTextField tfImporteTotalComprobante;
    public javax.swing.JTextField tfImporteTotalNC;
    public javax.swing.JTextField tfInformacion;
    public static javax.swing.JTextField tfKilosADevolver;
    public javax.swing.JTextField tfKilosFacturados;
    public javax.swing.JTextField tfKilosFinalesNC;
    public javax.swing.JTextField tfKilosImpagos;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfNumeroFactura;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfPrecioUnitarioFinalNC;
    public javax.swing.JTextField tfSaldoImpagoComprobante;
    public javax.swing.JTextField tfSaldoPendiente;
    public javax.swing.JTextField tfTambores;
    public javax.swing.JTextField tfTipoFactura;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
