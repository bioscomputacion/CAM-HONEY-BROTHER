/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
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
        
    static int codigoProductor, codigoMovimientoCtaCte, codigoFactura, codigoComprobanteAfectadoNotaCredito;
    static String tipoComprobanteAfectadoNotaCredito;
    static Double totalMielFacturada, totalMielIngresadaDevolucion, importeFactura, importeNotaCredito, debeComprobanteAfectado, haberComprobanteAfectado;
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
        
        rbRegistroNotaCreditoDevolucionMiel.setSelected(true);
        //aca por defecto mostramos todos los kilos facturados en la factura y disponibles para la devolucion y nota de credito
        tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
        tfKilosDevolucion.setEnabled(true);
        //aca aun no debo mostrar nada
        tfImporteNotaCredito.setText("");
        tfImporteNotaCredito.setEnabled(false);
        
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
        tfInformacionFacturaSeleccionada = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        rbRegistroNotaCreditoDevolucionMiel = new javax.swing.JRadioButton();
        rbRegistroNotaCreditoUnicamente = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        tfCantidadFacturada = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tfPrecioFacturado = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tfImporteTotalFactura = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tfSaldoImpagoFactura = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        tfImporteNotaCredito = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfKilosDevolucion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        dcFechaNotaCredito = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfCantidadKilosNotaCredito = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfPrecioUnitarioNotaCredito = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbTipoNotaCredito = new javax.swing.JComboBox<>();
        tfDescripcion1 = new javax.swing.JTextField();
        tfTambores1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfImporteTotalNotaCredito = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfLotes1 = new javax.swing.JTextField();

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
        rsbrAsociar.setText("ASOCIAR A NOTA DE CREDITO");
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
        jLabel14.setText("A SELECCIONADO EL COMPROBANTE:");
        jLabel14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLabel14FocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("FACTURA:");

        tfTipoFactura.setEditable(false);
        tfTipoFactura.setBackground(new java.awt.Color(51, 84, 111));
        tfTipoFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTipoFactura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° COMPROBANTE:");

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
                                    .addComponent(tfTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfNumeroFactura))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfImporteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14))
                        .addGap(0, 187, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(tfTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(29, 29, 29))
                                .addComponent(tfImporteFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(29, 29, 29))
                                .addComponent(tfNumeroFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
        jLabel3.setText("INGRESE LA INFORMACION CORRESPONDIENTE A LA DEVOLUCION:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SE ESTA REALIZANDO UN PAGO CORRESPONDIENTE A:");

        tfInformacionFacturaSeleccionada.setEditable(false);
        tfInformacionFacturaSeleccionada.setBackground(new java.awt.Color(0, 0, 0));
        tfInformacionFacturaSeleccionada.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfInformacionFacturaSeleccionada.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("INFORMACION FACTURADA:");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        bgOpcionesRegistro.add(rbRegistroNotaCreditoDevolucionMiel);
        rbRegistroNotaCreditoDevolucionMiel.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbRegistroNotaCreditoDevolucionMiel.setForeground(new java.awt.Color(255, 255, 255));
        rbRegistroNotaCreditoDevolucionMiel.setText("REGISTRAR DEVOLUCION DE MIEL Y CALCULAR IMPORTE DE LA NOTA DE CREDITO:");
        rbRegistroNotaCreditoDevolucionMiel.setOpaque(false);
        rbRegistroNotaCreditoDevolucionMiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRegistroNotaCreditoDevolucionMielActionPerformed(evt);
            }
        });
        rbRegistroNotaCreditoDevolucionMiel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbRegistroNotaCreditoDevolucionMielKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbRegistroNotaCreditoDevolucionMielKeyReleased(evt);
            }
        });

        bgOpcionesRegistro.add(rbRegistroNotaCreditoUnicamente);
        rbRegistroNotaCreditoUnicamente.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbRegistroNotaCreditoUnicamente.setForeground(new java.awt.Color(255, 255, 255));
        rbRegistroNotaCreditoUnicamente.setText("REGISTRAR IMPORTE DE LA NOTA DE CREDITO SIN REALIZAR DEVOLUCION DE MIEL:");
        rbRegistroNotaCreditoUnicamente.setOpaque(false);
        rbRegistroNotaCreditoUnicamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRegistroNotaCreditoUnicamenteActionPerformed(evt);
            }
        });
        rbRegistroNotaCreditoUnicamente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbRegistroNotaCreditoUnicamenteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbRegistroNotaCreditoUnicamenteKeyReleased(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("KGS.:");

        tfCantidadFacturada.setEditable(false);
        tfCantidadFacturada.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadFacturada.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadFacturada.setForeground(new java.awt.Color(255, 255, 255));
        tfCantidadFacturada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCantidadFacturadaActionPerformed(evt);
            }
        });
        tfCantidadFacturada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCantidadFacturadaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCantidadFacturadaKeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("PRECIO:");

        tfPrecioFacturado.setEditable(false);
        tfPrecioFacturado.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioFacturado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioFacturado.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioFacturado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioFacturadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioFacturadoKeyTyped(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("IMPORTE TOTAL:");

        tfImporteTotalFactura.setEditable(false);
        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 255, 0));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("SALDO IMPAGO:");

        tfSaldoImpagoFactura.setEditable(false);
        tfSaldoImpagoFactura.setBackground(new java.awt.Color(255, 0, 0));
        tfSaldoImpagoFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfSaldoImpagoFactura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SELECCIONE EL TIPO DE REGISTRO DE LA NOTA DE CREDITO:");

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));

        tfImporteNotaCredito.setBackground(new java.awt.Color(0, 0, 0));
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

        jLabel21.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("KGS. A DEVOLVER:");

        tfKilosDevolucion.setBackground(new java.awt.Color(0, 0, 0));
        tfKilosDevolucion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosDevolucion.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosDevolucionActionPerformed(evt);
            }
        });
        tfKilosDevolucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfKilosDevolucionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosDevolucionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosDevolucionKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("IMPORTE A ACREDITAR:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescripcion.setText(" KGS. DE MIEL");

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("ITEM:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfInformacionFacturaSeleccionada, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 7, Short.MAX_VALUE))
                                    .addComponent(tfDescripcion))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCantidadFacturada, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPrecioFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfImporteTotalFactura)
                                    .addComponent(jLabel35))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfSaldoImpagoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(rbRegistroNotaCreditoUnicamente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                                .addComponent(rbRegistroNotaCreditoDevolucionMiel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfKilosDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfImporteNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(tfInformacionFacturaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCantidadFacturada, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(tfPrecioFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSaldoImpagoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(rbRegistroNotaCreditoDevolucionMiel)
                .addGap(18, 18, 18)
                .addComponent(rbRegistroNotaCreditoUnicamente)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKilosDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(tfImporteNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(29, 29, 29))
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

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("* N°:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ITEM:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("* KGS.:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        tfCantidadKilosNotaCredito.setEditable(false);
        tfCantidadKilosNotaCredito.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadKilosNotaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadKilosNotaCredito.setForeground(new java.awt.Color(255, 255, 255));
        tfCantidadKilosNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCantidadKilosNotaCreditoActionPerformed(evt);
            }
        });
        tfCantidadKilosNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCantidadKilosNotaCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCantidadKilosNotaCreditoKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("* PRECIO UNITARIO:");

        tfPrecioUnitarioNotaCredito.setEditable(false);
        tfPrecioUnitarioNotaCredito.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioUnitarioNotaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitarioNotaCredito.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioUnitarioNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioNotaCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioNotaCreditoKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("* N. DE CREDITO:");

        cbTipoNotaCredito.setBackground(new java.awt.Color(255, 255, 0));
        cbTipoNotaCredito.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoNotaCredito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOTA DE CREDITO A", "NOTA DE CREDITO C" }));
        cbTipoNotaCredito.setEnabled(false);
        cbTipoNotaCredito.setPreferredSize(new java.awt.Dimension(136, 19));

        tfDescripcion1.setEditable(false);
        tfDescripcion1.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion1.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescripcion1.setText(" KGS. DE MIEL");

        tfTambores1.setEditable(false);
        tfTambores1.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores1.setForeground(new java.awt.Color(255, 255, 255));
        tfTambores1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("CONVERSION A TAMBORES:");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("IMPORTE TOTAL:");

        tfImporteTotalNotaCredito.setEditable(false);
        tfImporteTotalNotaCredito.setBackground(new java.awt.Color(255, 0, 0));
        tfImporteTotalNotaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteTotalNotaCredito.setForeground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("CONVERSION A LOTES:");

        tfLotes1.setEditable(false);
        tfLotes1.setBackground(new java.awt.Color(0, 0, 0));
        tfLotes1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfLotes1.setForeground(new java.awt.Color(255, 255, 255));
        tfLotes1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel29)
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
                                                .addComponent(tfCantidadKilosNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfPrecioUnitarioNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfImporteTotalNotaCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tfLotes1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addComponent(tfTambores1, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addContainerGap())
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbTipoNotaCredito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(dcFechaNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101))))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(tfCantidadKilosNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPrecioUnitarioNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteTotalNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTambores1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLotes1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos para la nota de credito", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rsbrAsociar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tpFactura, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
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
        boolean informacionIncompleta = (cbTipoNotaCredito.getSelectedItem().equals("SELECCIONAR") || tfNumeroComprobante.getText().length() == 0 || tfImporteTotalNotaCredito.getText().length() == 0 || tfImporteTotalNotaCredito.getText().equals("0.00") || tfImporteTotalNotaCredito.getText().equals("0.0"));
        
        if (informacionIncompleta){
            
            JOptionPane.showMessageDialog(null, "La informacion correspondiente a la nota de credito se halla incompleta. Ingrese la misma correctamente.", "REGISTRO DE NOTA DE CREIDTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            cbTipoNotaCredito.requestFocus();
            return;
            
        }
        
        //SE REGISTRA PRIMERO EL PAGO EN LA TABLA PAGO_PRODUCTOR
        //se necesita conocer el numero de movimiento con el que se registra el pago
        
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
        NotaCreditoProductor nCredito = new NotaCreditoProductor(numeroComprobante, tipoNotaCredito, codigoMovimientoCtaCte, codigoProductor, new Date(a, m, d), importeNotaCredito, totalMielIngresadaDevolucion);
        nCredito.registrarNotaCreditoProductor(nCredito);
        
        //obtengo codigo de nota de credito y saldo del comprobante afectado por la nota de credito
        //para guardarlo en la tabla cta. cte.
        int codigoNotaCredito = nCredito.mostrarIdNotaCreditoProductor();
        //calcular el valor de la factura menos le valor de la nota de credito
        double saldoComprobanteAfectado = Double.parseDouble(tfSaldoImpagoFactura.getText().toString())- Double.parseDouble(tfImporteTotalNotaCredito.getText().toString());
        
        //2) se registra el movimiento asociado a la nota de credito en la cta. cte. con el productor 
        String comprobanteAsociadoNotaCredito = "";
        if (tipoNotaCredito.equals("NOTA DE CREDITO A")){
            
            comprobanteAsociadoNotaCredito = "FACT. A N° "+tfNumeroFactura.getText();
        
        }
        else{

            comprobanteAsociadoNotaCredito = "FACT. B N° "+tfNumeroFactura.getText();

        }
        
        CtaCteProductor ctacte = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a, m, d), tipoNotaCredito, codigoNotaCredito, comprobanteAsociadoNotaCredito, 0.00, 0.00, importeNotaCredito, importeNotaCredito, "CANCELADO", "");
        ctacte.registrarMovimientoCtaCteProductor(ctacte);
        
        //3) se modifica el saldo del comprobante afectado por el pago
        //de donde salen los valores de: codigoComprobanteAfectadoNotaCredito, debeComprobanteAfectado, haberComprobanteAfectado????
        
        //el valor de importeNotaCredito siempre esta actualizado
        //el valor de codigoComprobanteAfectadoNotaCredito
        //el valor de debeComprobanteAfectado
        //el valor de haberComprobanteAfectado
        
        ctacte.actualizarSaldoComprobanteProductor(codigoComprobanteAfectadoNotaCredito, codigoProductor, debeComprobanteAfectado, importeNotaCredito, haberComprobanteAfectado);
        
        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        
        //HASTA ACA VIENE TODO BIEN: FALTA VER BIEN EL TEMA DE LA ACTUALIZACION DEL SALDO Y TOMAR COMO REFERENCIA
        
        //EL SALDO IMPAGO DE LA FACTURA Y NO EL IMPORTE FACTURADO! EN REALIDAD QUIZAS NO, YA QUE LA NOTA DE CREDITO ES PARA
        //ANULAR LA FACTURA ENTERA O PARTE DE ELLA, LO CUAL VA A GENERAR UN SALDO A FAVOR EN EL CASO QUE SE HAYA HECHO ANTERIOREMENTE
        //UN PAGO QUE AFECTE AL SALDO DE ESTA FACTURA. DE ESTE ANALISIS SALE QUE QUIZAS SE DEBERIA DEJAR REALIZAR UNA NOTA DE CREDITO
        //Y ASOCIARLA A UNA FACTURA CANCELADA, PREVIO AVISO Y NOTIFICACION AL USUARIO, LO CUAL HARA QUE SI ESTABA PAGA
        //ESE PAGO PASE A SER UN SALDO A FAVOR O UN PAGO QUE AFECTE AL RESUMEN ENTERO EN SI
        
        //DESP DE ESTO HAY QUE REGISTRAR LA DEVOLUCION EN CASO DE QUE EXISTA, Y TAMB MODIFICAR EL STOCK DE MIEL DE LA EMPRESA
        //CUANDO SE REGISTRA LA DEVOLUCION SE REGISTRAN LOS KILOS DEVUELTOS! O SEA QUE EL SALDO DE MIEL VA A SER ALTERADO POR
        //DICHA DEVOLUCION (EL STOCK DE MIEL GLOBAL Y DE LA LOCACION DESDE DONDE SE DEVUELVA)

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
        
        //cada vez que se hace click sobre la grilla se muestran en los campos debajo los datos de la factura seleccionada
        //correspondiente a la fila de la grilla cliqueada
        tfTipoFactura.setText(tFacturasProductor.getValueAt(fila, 1).toString());
        tfNumeroFactura.setText(tFacturasProductor.getValueAt(fila, 2).toString());
        //IMPORTE FACTURADO EN EL COMPROBANTE, QUE NO ES LO MISMO QUE SALDO IMPAGO DE LA FACTURA
        tfImporteFactura.setText(tFacturasProductor.getValueAt(fila, 4).toString());
        
        //en esta variable siempre va a estar almacenado el codigo de la factura seleccionada en la grilla
        //el cual voy a necesitar a la hora de alterar el saldo de la misma restando el valor acreditado
        codigoFactura = Integer.parseInt(tFacturasProductor.getValueAt(fila, 0).toString());
        
        //en esta variable siempre va a estar almacenado el codigo de movimiento que tiene la factura en la cta. cte.
        //VER COMO SE OBTIENEEEEE   
        codigoComprobanteAfectadoNotaCredito = Integer.parseInt(tFacturasProductor.getValueAt(fila, 0).toString());
        
        //en esta variable se almacena el tipo de factura, que sirve entre otras cuestiones para ver que tipo
        //de nota de credito se habilita en el combo de notas de credito
        tipoComprobanteAfectadoNotaCredito = tFacturasProductor.getValueAt(fila, 1).toString();
        //habilito notas de credito A o notas de credito C
        if (tipoComprobanteAfectadoNotaCredito.equals("FACTURA A")){
            
            cbTipoNotaCredito.setEnabled(true);
            cbTipoNotaCredito.setSelectedIndex(0);
            cbTipoNotaCredito.setEnabled(false);
            
        }
        else{
            
            cbTipoNotaCredito.setEnabled(true);
            cbTipoNotaCredito.setSelectedIndex(1);
            cbTipoNotaCredito.setEnabled(false);
            
        }
        

        //esto es para realizar los controles
        totalMielFacturada = Double.valueOf(tFacturasProductor.getValueAt(fila, 6).toString());
        //Por defecto vamos a empezar asumiendo que se devuelve toda la miel facturada en el comprobante a asociar a la nota de credito
        totalMielIngresadaDevolucion = totalMielFacturada;
        
        importeFactura = Double.valueOf(tFacturasProductor.getValueAt(fila, 4).toString());
        //lo mismo: como por defecto se asume que se devuelve toda la miel, el importe de la nota de credito sera el mismo que el facturado
        importeNotaCredito = importeFactura;
        
        tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
        tfImporteNotaCredito.setText("");
        
        //datos esenciales de la factura seleccionada para asociar a la nota de credito
        tfCantidadFacturada.setText(tFacturasProductor.getValueAt(fila, 6).toString());
        tfPrecioFacturado.setText(tFacturasProductor.getValueAt(fila, 7).toString());
        tfImporteTotalFactura.setText(tFacturasProductor.getValueAt(fila, 4).toString());
        tfSaldoImpagoFactura.setText(tFacturasProductor.getValueAt(fila, 5).toString());

        //con esto voy llenando los datos de la nota de credito en si
        tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielFacturada));
        Double cantidadKilos = totalMielFacturada;
        tfPrecioUnitarioNotaCredito.setText(tfPrecioFacturado.getText());
        Double precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
        tfImporteTotalNotaCredito.setText(String.valueOf(cantidadKilos * precioUnitario));

    }//GEN-LAST:event_tFacturasProductorMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tfCantidadKilosNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosNotaCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosNotaCreditoActionPerformed

    private void tfCantidadKilosNotaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosNotaCreditoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosNotaCreditoKeyReleased

    private void tfCantidadKilosNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosNotaCreditoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosNotaCreditoKeyTyped

    private void tfPrecioUnitarioNotaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioNotaCreditoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioNotaCreditoKeyReleased

    private void tfPrecioUnitarioNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioNotaCreditoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioUnitarioNotaCreditoKeyTyped

    private void rbRegistroNotaCreditoDevolucionMielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoDevolucionMielActionPerformed

        if (tfNumeroFactura.getText().length() != 0){

            if (rbRegistroNotaCreditoDevolucionMiel.isSelected()){

                JOptionPane.showMessageDialog(null, "Ingrese solo la cantidad de kgs. de miel a devolver. El sistema calculara el importe de la nota de credito.", "REGISTRO DE NOTA DE CREDITO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
                tfKilosDevolucion.setEnabled(true);
                tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));

                //con esto voy llenando los datos de la nota de credito en la ultima pestaña
                tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielFacturada));
                Double cantidadKilos = totalMielFacturada;
                tfPrecioUnitarioNotaCredito.setText(tfPrecioFacturado.getText());
                Double precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
                importeNotaCredito = cantidadKilos * precioUnitario;
                tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));

                //aca no debo mostrar nada 
                tfImporteNotaCredito.setText("");
                tfImporteNotaCredito.setEnabled(false);
                tfKilosDevolucion.requestFocus();

            }
            else{

                //aca no debo mostrar nada 
                tfKilosDevolucion.setText("");
                tfKilosDevolucion.setEnabled(false);
                
                //pongo esta variable en 0, ya que no se devuelven kgs. de miel
                totalMielIngresadaDevolucion = 0.00;

                //con esto voy llenando los datos de la nota de credito en la ultima pestaña
                tfCantidadKilosNotaCredito.setText("0.00");
                tfPrecioUnitarioNotaCredito.setText("0.00");
                tfImporteTotalNotaCredito.setText(String.valueOf(importeFactura));

                tfImporteNotaCredito.setEnabled(true);
                tfImporteNotaCredito.setText(String.valueOf(importeFactura));
                tfImporteNotaCredito.requestFocus();

            }

        }
        else{
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione una factura para asociar la misma a la nota de credito.", "REGISTRO DE NOTA DE CREDITO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            rbRegistroNotaCreditoDevolucionMiel.setSelected(true);
            rbRegistroNotaCreditoUnicamente.setSelected(false);
            tpFactura.setSelectedIndex(0);
            tFacturasProductor.requestFocus();
            
        }
        
    }//GEN-LAST:event_rbRegistroNotaCreditoDevolucionMielActionPerformed

    private void rbRegistroNotaCreditoDevolucionMielKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoDevolucionMielKeyPressed

    }//GEN-LAST:event_rbRegistroNotaCreditoDevolucionMielKeyPressed

    private void rbRegistroNotaCreditoDevolucionMielKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoDevolucionMielKeyReleased

    }//GEN-LAST:event_rbRegistroNotaCreditoDevolucionMielKeyReleased

    private void rbRegistroNotaCreditoUnicamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoUnicamenteActionPerformed

        if (tfNumeroFactura.getText().length() != 0){

            if (rbRegistroNotaCreditoUnicamente.isSelected()){

                JOptionPane.showMessageDialog(null, "Ingrese solo el importe a acreditarse, ya que ha indicado que no se realizara devolucion de miel.", "REGISTRO DE NOTA DE CREDITO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
                //aca no debo mostrar nada 
                tfKilosDevolucion.setText("");
                tfKilosDevolucion.setEnabled(false);
                
                //pongo esta variable en 0, ya que no se devuelven kgs. de miel
                totalMielIngresadaDevolucion = 0.00;

                //con esto voy llenando los datos de la nota de credito en la ultima pestaña
                tfCantidadKilosNotaCredito.setText("0.00");
                tfPrecioUnitarioNotaCredito.setText("0.00");
                tfImporteTotalNotaCredito.setText(String.valueOf(importeFactura));

                tfImporteNotaCredito.setEnabled(true);
                tfImporteNotaCredito.setText(String.valueOf(importeFactura));
                tfImporteNotaCredito.requestFocus();

            }
            else{

                tfKilosDevolucion.setEnabled(true);
                tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
                                
                //con esto voy llenando los datos de la nota de credito en la ultima pestaña
                tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielFacturada));
                Double cantidadKilos = totalMielFacturada;
                tfPrecioUnitarioNotaCredito.setText(tfPrecioFacturado.getText());
                Double precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
                //aca va el total facturado, ya que se ingrersan por defecto todos los kilos para la devolucion
                importeNotaCredito = cantidadKilos * precioUnitario;
                tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));

                //aca no debo mostrar nada 
                tfImporteNotaCredito.setText("");
                tfImporteNotaCredito.setEnabled(false);
                tfKilosDevolucion.requestFocus();

            }

        }
        else{
                    
            JOptionPane.showMessageDialog(null, "Por favor seleccione una factura para asociar la misma a la nota de credito.", "REGISTRO DE NOTA DE CREDITO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            rbRegistroNotaCreditoDevolucionMiel.setSelected(true);
            rbRegistroNotaCreditoUnicamente.setSelected(false);
            tpFactura.setSelectedIndex(0);
            tFacturasProductor.requestFocus();
            
        }
        
    }//GEN-LAST:event_rbRegistroNotaCreditoUnicamenteActionPerformed

    private void rbRegistroNotaCreditoUnicamenteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoUnicamenteKeyPressed

    }//GEN-LAST:event_rbRegistroNotaCreditoUnicamenteKeyPressed

    private void rbRegistroNotaCreditoUnicamenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbRegistroNotaCreditoUnicamenteKeyReleased

    }//GEN-LAST:event_rbRegistroNotaCreditoUnicamenteKeyReleased

    private void tfCantidadFacturadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadFacturadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadFacturadaActionPerformed

    private void tfCantidadFacturadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadFacturadaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadFacturadaKeyReleased

    private void tfCantidadFacturadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadFacturadaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadFacturadaKeyTyped

    private void tfPrecioFacturadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioFacturadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioFacturadoKeyReleased

    private void tfPrecioFacturadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioFacturadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioFacturadoKeyTyped

    private void tfImporteNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImporteNotaCreditoActionPerformed

    }//GEN-LAST:event_tfImporteNotaCreditoActionPerformed

    private void tfImporteNotaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImporteNotaCreditoKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vacio
        //2) que no se ingrese un importe igual a 0 para la nota de credito
        //3) que no se ingrese un importe superior al importe total de la factura
        
        //cuando los datos sean bien ingresados quedaran almacenados en la variable "importeNotaCredito"
        
        Double importeIngresado = 0.00;
        
        
        if (tfImporteNotaCredito.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Importe ingresado incorrecto.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfImporteNotaCredito.setText(String.valueOf(importeFactura));
            importeNotaCredito = importeFactura;
            //se van actualizando los datos de la ultima pestaña
            tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));
            
            tfImporteNotaCredito.requestFocus();
            
        }
        else{
            
            importeIngresado = Double.valueOf(tfImporteNotaCredito.getText());
            
            if (importeIngresado <= 0.00){
                
                JOptionPane.showMessageDialog(null, "Importe ingresado incorrecto.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfImporteNotaCredito.setText(String.valueOf(importeFactura));
                importeNotaCredito = importeFactura;
                //se van actualizando los datos de la ultima pestaña
                tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));

                tfImporteNotaCredito.requestFocus();
                
            }
            else{
                
                if (importeIngresado > importeFactura){

                    JOptionPane.showMessageDialog(null, "Importe ingresado incorrecto.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                    tfImporteNotaCredito.setText(String.valueOf(importeFactura));
                    importeNotaCredito = importeFactura;
                    //se van actualizando los datos de la ultima pestaña
                    tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));

                    tfImporteNotaCredito.requestFocus();

                }
                else{

                    importeNotaCredito = importeIngresado;
                    tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));
                    
                    tfImporteNotaCredito.requestFocus();

                }

            }
            
        }

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

    private void tfKilosDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosDevolucionActionPerformed
    }//GEN-LAST:event_tfKilosDevolucionActionPerformed

    private void tfKilosDevolucionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDevolucionKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vacio
        //2) que no se ingrese una cantidad igual a 0 kgs. devueltos
        //3) que no se ingrese una cantidad de kgs. superior a la cantidad de kg.s facturada
        
        //cuando los datos sean bien ingresados quedaran almacenados en la variable "totalMielIngrersadaDevolucion"
        
        Double precioUnitario = 0.00;
        Double kilosDevolucionIngresados = 0.00;
        
        if (tfKilosDevolucion.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
            precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
            totalMielIngresadaDevolucion = totalMielFacturada;
            tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielIngresadaDevolucion));
            tfPrecioUnitarioNotaCredito.setText(String.valueOf(precioUnitario));
            importeNotaCredito = totalMielIngresadaDevolucion * precioUnitario;
            //se van actualizando los datos de la ultima pestaña
            tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));
            
            tfKilosDevolucion.requestFocus();
            
        }
        else{
            
            kilosDevolucionIngresados = Double.valueOf(tfKilosDevolucion.getText());
            
            if (kilosDevolucionIngresados <= 0.00){
                
                
                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
                precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
                totalMielIngresadaDevolucion = totalMielFacturada;
                tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielIngresadaDevolucion));
                tfPrecioUnitarioNotaCredito.setText(String.valueOf(precioUnitario));
                importeNotaCredito = totalMielIngresadaDevolucion * precioUnitario;
                //se van actualizando los datos de la ultima pestaña
                tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));
    
                tfKilosDevolucion.requestFocus();
                
            }
            else{

                if (kilosDevolucionIngresados > totalMielFacturada){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE NOTA DE CREDITO DE PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                    tfKilosDevolucion.setText(String.valueOf(totalMielFacturada));
                    precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
                    totalMielIngresadaDevolucion = totalMielFacturada;
                    tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielIngresadaDevolucion));
                    tfPrecioUnitarioNotaCredito.setText(String.valueOf(precioUnitario));
                    importeNotaCredito = totalMielIngresadaDevolucion * precioUnitario;
                    //se van actualizando los datos de la ultima pestaña
                    tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));

                    tfKilosDevolucion.requestFocus();
                
                }
                else{

                    precioUnitario = Double.valueOf(tfPrecioFacturado.getText());
                    totalMielIngresadaDevolucion = kilosDevolucionIngresados;
                    tfCantidadKilosNotaCredito.setText(String.valueOf(totalMielIngresadaDevolucion));
                    tfPrecioUnitarioNotaCredito.setText(String.valueOf(precioUnitario));
                    importeNotaCredito = kilosDevolucionIngresados * precioUnitario;
                    //se van actualizando los datos de la ultima pestaña
                    tfImporteTotalNotaCredito.setText(String.valueOf(importeNotaCredito));
                    tfKilosDevolucion.requestFocus();

                }
                
            }
            
        }
        
    }//GEN-LAST:event_tfKilosDevolucionKeyReleased

    private void tfKilosDevolucionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDevolucionKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosDevolucion.getText().contains(".") && c == '.') {
            
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
        
    }//GEN-LAST:event_tfKilosDevolucionKeyTyped

    private void tpFacturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tpFacturaFocusGained
    }//GEN-LAST:event_tpFacturaFocusGained

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
       
        // TODO add your handling code here:
        tfKilosDevolucion.requestFocus();
        
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

    private void tfKilosDevolucionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDevolucionKeyPressed

    }//GEN-LAST:event_tfKilosDevolucionKeyPressed


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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JSeparator jSeparator6;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JRadioButton rbRegistroNotaCreditoDevolucionMiel;
    private javax.swing.JRadioButton rbRegistroNotaCreditoUnicamente;
    private rojeru_san.RSButtonRiple rsbrAsociar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tDetalleStock;
    public static javax.swing.JTable tFacturasProductor;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadFacturada;
    public javax.swing.JTextField tfCantidadKilosNotaCredito;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDescripcion1;
    public javax.swing.JTextField tfImporteFactura;
    public static javax.swing.JTextField tfImporteNotaCredito;
    public javax.swing.JTextField tfImporteTotalFactura;
    public javax.swing.JTextField tfImporteTotalNotaCredito;
    public javax.swing.JTextField tfInformacionFacturaSeleccionada;
    public static javax.swing.JTextField tfKilosDevolucion;
    public javax.swing.JTextField tfLotes1;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfNumeroFactura;
    public javax.swing.JTextField tfPrecioFacturado;
    public javax.swing.JTextField tfPrecioUnitarioNotaCredito;
    public javax.swing.JTextField tfSaldoImpagoFactura;
    public javax.swing.JTextField tfTambores1;
    public javax.swing.JTextField tfTipoFactura;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
