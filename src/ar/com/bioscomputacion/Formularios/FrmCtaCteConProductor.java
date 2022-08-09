/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoCreditoProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Caco
 */
public class FrmCtaCteConProductor extends javax.swing.JInternalFrame {

    public int codigoProductor, codigoFactura, codigoItemFacturado;
    //la variable ctaCteMostrada es para no ejecutar el mismo codigo cada vez que se hace click en la pestaña cta cte
    //una vez que se pone en true el codigo ya no debe ejecutarse, caso contrario se ejcuta dicho codigo.
    
    public static int codigoComprobante;
    public String nombreProductor;
    public static int fila, fila2 = -1;
    /**
     * Creates new form FrmGenerico
     */
    public FrmCtaCteConProductor() {
        
        initComponents();
        mostrarProductores("");
        ocultarColumnasProductores();
        //ocultarColumnasItemsFacturados();
        inicializar();
        
    }

    public void inicializar(){
        
        tfNombreProductor.setEditable(false);
        tfDocumentoProductor.setEditable(false);
        
        tProductores.requestFocus();
        
    }

    public void mostrarProductores(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Productor productor = new Productor();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = productor.listarProductores(buscar);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tProductores.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnasProductores() {

        /*tProductores.getColumnModel().getColumn(0).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(0).setMinWidth(0);
        tProductores.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tProductores.getColumnModel().getColumn(1).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(1).setMinWidth(0);
        tProductores.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tProductores.getColumnModel().getColumn(2).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(2).setMinWidth(0);
        tProductores.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tProductores.getColumnModel().getColumn(3).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(3).setMinWidth(0);
        tProductores.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tProductores.getColumnModel().getColumn(4).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(4).setMinWidth(0);
        tProductores.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tProductores.getColumnModel().getColumn(5).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(5).setMinWidth(0);
        tProductores.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tProductores.getColumnModel().getColumn(6).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(6).setMinWidth(0);
        tProductores.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        tProductores.getColumnModel().getColumn(7).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(7).setMinWidth(0);
        tProductores.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        tProductores.getColumnModel().getColumn(8).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(8).setMinWidth(0);
        tProductores.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        tProductores.getColumnModel().getColumn(9).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(9).setMinWidth(0);
        tProductores.getColumnModel().getColumn(9).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(10).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(10).setMinWidth(0);
        tProductores.getColumnModel().getColumn(10).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(11).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(11).setMinWidth(0);
        tProductores.getColumnModel().getColumn(11).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(12).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(12).setMinWidth(0);
        tProductores.getColumnModel().getColumn(12).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(13).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(13).setMinWidth(0);
        tProductores.getColumnModel().getColumn(13).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(14).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(14).setMinWidth(0);
        tProductores.getColumnModel().getColumn(14).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(15).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(15).setMinWidth(0);
        tProductores.getColumnModel().getColumn(15).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(16).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(16).setMinWidth(0);
        tProductores.getColumnModel().getColumn(16).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(17).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(17).setMinWidth(0);
        tProductores.getColumnModel().getColumn(17).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(18).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(18).setMinWidth(0);
        tProductores.getColumnModel().getColumn(18).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(19).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(19).setMinWidth(0);
        tProductores.getColumnModel().getColumn(19).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(20).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(20).setMinWidth(0);
        tProductores.getColumnModel().getColumn(20).setPreferredWidth(0);

        tProductores.getColumnModel().getColumn(21).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(21).setMinWidth(0);
        tProductores.getColumnModel().getColumn(21).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tProductores.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tProductores.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tProductores.getColumnModel().getColumn(5).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.LEFT);
        tProductores.getColumnModel().getColumn(6).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tProductores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public static void ocultarColumnasCtaCte() {

        tMovimientos.getColumnModel().getColumn(0).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(0).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(0).setPreferredWidth(0);

        tMovimientos.getColumnModel().getColumn(1).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(1).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(1).setPreferredWidth(0);

        /*tMovimientos.getColumnModel().getColumn(2).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(2).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        
        /*tMovimientos.getColumnModel().getColumn(3).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(3).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        tMovimientos.getColumnModel().getColumn(4).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(4).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        /*tMovimientos.getColumnModel().getColumn(5).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(5).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tMovimientos.getColumnModel().getColumn(6).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(6).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        /*tMovimientos.getColumnModel().getColumn(7).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(7).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(7).setPreferredWidth(0);*/
        
        /*tMovimientos.getColumnModel().getColumn(8).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(8).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(8).setPreferredWidth(0);*/
        
        /*tMovimientos.getColumnModel().getColumn(9).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(9).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(9).setPreferredWidth(0);*/

        /*tMovimientos.getColumnModel().getColumn(10).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(10).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(10).setPreferredWidth(0);*/

        tMovimientos.getColumnModel().getColumn(11).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(11).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(11).setPreferredWidth(0);
        
        tMovimientos.getColumnModel().getColumn(12).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(12).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(12).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender7 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender8 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tMovimientos.getColumnModel().getColumn(2).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tMovimientos.getColumnModel().getColumn(3).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.CENTER);
        tMovimientos.getColumnModel().getColumn(5).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tMovimientos.getColumnModel().getColumn(6).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tMovimientos.getColumnModel().getColumn(7).setCellRenderer(cellRender5);   
        cellRender6.setHorizontalAlignment(SwingConstants.RIGHT);
        tMovimientos.getColumnModel().getColumn(8).setCellRenderer(cellRender6);   
        cellRender7.setHorizontalAlignment(SwingConstants.RIGHT);
        tMovimientos.getColumnModel().getColumn(9).setCellRenderer(cellRender7);   
        cellRender8.setHorizontalAlignment(SwingConstants.RIGHT);
        tMovimientos.getColumnModel().getColumn(10).setCellRenderer(cellRender8);   
        
        ((DefaultTableCellRenderer) tMovimientos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public static void mostrarCtaCteProductor(int codigoProductor) {
        
        try {
            
            //muestra todos los movimientos en la cta. cte. que tiene la empresa con el productor seleccionado
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
            DefaultTableModel modelo = null;
            //para mostrar en la grilla todos los movimientos del cliente
            CtaCteProductor ctacteProductor = new CtaCteProductor();
            modelo = ctacteProductor.listarMovimientosCtaCteProductor(codigoProductor);
            tMovimientos.setModel(modelo);
            //muestra el saldo en el label debajo de la grilla
            lSaldoDineroImpago.setText("SALDO IMPAGO: $ " + formateador.format(ctacteProductor.mostrarSaldoCtaCteProductor(codigoProductor)));
            //lSaldoMielImpago.setText(formateador.format(ctacteProductor.mostrarSaldoMielImpagaAProductor(codigoProductor)-ctacteProductor.obtenerDetalleMielDescontadaCompraConsignacion(Integer.valueOf(tMovimientos.getValueAt(fila2, 4).toString()))+ " KGS. DE MIEL IMPAGOS"));
            Double mielFinanciadaImpaga = ctacteProductor.mostrarSaldoMielImpagaAProductor(codigoProductor);
            //Double mielFinanciadaYaDescontada = ctacteProductor.obtenerDetalleMielDescontadaCompraConsignacion(codigoProductor);
            //String mielImpaga= "";
                    
            if (mielFinanciadaImpaga.equals(0.00)){
                
                //mielImpaga = "0.00";
                
            }
            else{
                
                //mielImpaga = formateador.format(ctacteProductor.mostrarSaldoMielImpagaAProductor(codigoProductor)-ctacteProductor.obtenerDetalleMielDescontadaCompraConsignacion(codigoComprobante));
                //mielImpaga = String.valueOf(mielFinanciadaImpaga - mielFinanciadaYaDescontada);
                
                
            }
            
            lSaldoMielImpago.setText(String.valueOf(mielFinanciadaImpaga)+ " KGS. DE MIEL IMPAGOS");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        tpFactura = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tProductores = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tfIDProductor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfNombreProductor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfDocumentoProductor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lSaldoDineroImpago = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMovimientos = new javax.swing.JTable();
        lSaldoMielImpago = new javax.swing.JLabel();
        tbOpciones = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        rsbrSalir = new rojeru_san.RSButtonRiple();

        jCheckBox1.setText("jCheckBox1");

        setBackground(new java.awt.Color(51, 84, 111));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CUENTA CORRIENTE CON PRODUCTORES - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(800, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpFactura.setBackground(new java.awt.Color(51, 84, 111));
        tpFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tpFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpFacturaMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("SELECCIONE EL PRODUCTOR CORRESPONDIENTE:");

        jLabel4.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUSCAR PRODUCTOR POR NOMBRE:");

        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

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

        tfIDProductor.setEditable(false);
        tfIDProductor.setBackground(new java.awt.Color(51, 84, 111));
        tfIDProductor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfIDProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID PRODUCTOR:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreProductor.setEditable(false);
        tfNombreProductor.setBackground(new java.awt.Color(51, 84, 111));
        tfNombreProductor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombreProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoProductor.setEditable(false);
        tfDocumentoProductor.setBackground(new java.awt.Color(51, 84, 111));
        tfDocumentoProductor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDocumentoProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("A SELECCIONADO EL PRODUCTOR:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfNombreProductor))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfDocumentoProductor)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDocumentoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tpFactura.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("MOVIMIENTOS EN LA CTA. CTE. DEL PRODUCTOR SELECCIONADO:");

        lSaldoDineroImpago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lSaldoDineroImpago.setForeground(new java.awt.Color(0, 255, 0));
        lSaldoDineroImpago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lSaldoDineroImpago.setText("TOTAL");

        tMovimientos.setBackground(new java.awt.Color(36, 33, 33));
        tMovimientos.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        tMovimientos.setForeground(new java.awt.Color(207, 207, 207));
        tMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tMovimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMovimientosMouseClicked(evt);
            }
        });
        tMovimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tMovimientosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tMovimientosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tMovimientos);

        lSaldoMielImpago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lSaldoMielImpago.setForeground(new java.awt.Color(255, 255, 0));
        lSaldoMielImpago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lSaldoMielImpago.setText("TOTAL");

        tbOpciones.setBackground(new java.awt.Color(0, 0, 0));
        tbOpciones.setBorder(null);
        tbOpciones.setFloatable(false);
        tbOpciones.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("  ABONAR COMPROBANTE  ");
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("  FACTURAR COMPRA  ");
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton2);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("  DEVOLVER MIEL  ");
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton3);

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("  REGISTRAR NOTA DE CREDITO  ");
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("  ANULAR PRESUPUESTO  ");
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lSaldoMielImpago, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lSaldoDineroImpago, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(tbOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lSaldoDineroImpago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lSaldoMielImpago))
        );

        tpFactura.addTab("Cuenta corriente con el productor seleccionado", jPanel3);

        rsbrSalir.setBackground(new java.awt.Color(47, 110, 164));
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpFactura)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura)
                .addGap(18, 18, 18)
                .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarProductores(tfBusquedaPorNombre.getText());
        ocultarColumnasProductores();
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tProductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductoresMouseClicked

        fila = tProductores.rowAtPoint(evt.getPoint());
        //en esta variable siempre va a estar almacenado el codigo del productor seleccionado en la grilla
        //en esta variable siempre va a estar almacenado el nombre del productor seleccionado en la grilla
        codigoProductor = Integer.parseInt(tProductores.getValueAt(fila, 0).toString());
        nombreProductor = tProductores.getValueAt(fila, 1).toString();

        //cada vez que se hace click sobre la grilla se muestran en los campos debajo lso datos del productor
        //correspondiente a la fila de la grilla cliqueada
        tfIDProductor.setText(tProductores.getValueAt(fila, 0).toString());
        tfNombreProductor.setText(tProductores.getValueAt(fila, 1).toString());
        tfDocumentoProductor.setText(tProductores.getValueAt(fila, 2).toString());

        //cada vez que se selecciona un productor en la otra pestaña se muestra la corriente con el, tenga movimientos o no
        mostrarCtaCteProductor(codigoProductor);
        ocultarColumnasCtaCte();
        
    }//GEN-LAST:event_tProductoresMouseClicked

    private void tMovimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMovimientosMouseClicked

        fila2 = tMovimientos.rowAtPoint(evt.getPoint());
        //almacena el codigo interno del movimiento seleccionado, todo el tiempo
        codigoComprobante = Integer.valueOf(tMovimientos.getValueAt(fila2, 1).toString());
        
    }//GEN-LAST:event_tMovimientosMouseClicked

    private void tMovimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMovimientosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMovimientosKeyPressed

    private void tMovimientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMovimientosKeyReleased
    }//GEN-LAST:event_tMovimientosKeyReleased

    private void tpFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpFacturaMouseClicked
    }//GEN-LAST:event_tpFacturaMouseClicked

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            
            //1er chequeo: se debe seleccionar una fila de la grilla
            if (fila2 == -1) {
                
                JOptionPane.showMessageDialog(null, "Seleccione la fila correspondiente a la compra a consignacion que desea facturar.", "FACTURACION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            //2do chequeo: se debe corroborar que se esta intentando facturar un credito y no otro tipo
            //de movimiento, como por ejemplo: un pago, una factura, un presupuesto, etc.
            if (!(tMovimientos.getValueAt(fila2, 3).toString().equals("CONSIGNACION"))) {
                
                JOptionPane.showMessageDialog(null, "No se puede facturar el movimiento seleccionado. Seleccione una compra en consignacion para facturar la misma por favor.", "FACTURACION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            //3er chequeo: se debe corroborar que no se esta intentando facturar una compra a consignacion ya facturada
            if (tMovimientos.getValueAt(fila2, 11).toString().equals("CANCELADO")) {
                
                JOptionPane.showMessageDialog(null, "Esta intentando facturar una compra a consignacion ya facturada. Seleccione otro comprobante para realizar el pago correspondiente por favor.", "FACTURACION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            // "ID PRODUCTOR", "ID MOVIMIENTO", "FECHA", "REFERENCIA", "COMPROBANTE ASOCIADO", "N° COMPROB.", "KGS. MIEL COMPRADOS", "KGS. MIEL IMPAGOS", "IMPORTE", "PAGADO", "SALDO", "ESTADO MOVIMIENTO", "OBSERVACION"};
            
            FrmFacturacionCompraConsignacion form = new FrmFacturacionCompraConsignacion();
            
            //asigno valores que debera mostrar el formulario de pago al productor
            //y recuerdo todos los datos del MOVIMIENTO CORRESPONDIENTE A LA COMPRA EN CONSIGNACION
            //para editar luego la misma sin perder la informacion original
            FrmFacturacionCompraConsignacion.codigoProductor = Integer.valueOf(tMovimientos.getValueAt(fila2, 0).toString());
            FrmFacturacionCompraConsignacion.codigoMovimientoCtaCteCompra = Integer.valueOf(tMovimientos.getValueAt(fila2, 1).toString());
            FrmFacturacionCompraConsignacion.fechaMovimientoCompra = Date.valueOf(tMovimientos.getValueAt(fila2, 2).toString());
            //referencia no hace falta pasar porque ya sabemos que es una compra en consignacion
            //codigoCompra es el codigo de la compra en consignacion
            FrmFacturacionCompraConsignacion.codigoCompra = Integer.valueOf(tMovimientos.getValueAt(fila2, 4).toString());
            FrmFacturacionCompraConsignacion.numeroComprobanteCompra = tMovimientos.getValueAt(fila2, 5).toString();
            //recuerdo la cantidad de miel adquirida originalmente en la compra
            //y la cantidad de miel ya descontada de la misma, ya sea por facturacion o por devolucion de miel en ella
            FrmFacturacionCompraConsignacion.totalMielFinanciadaCompra = Double.valueOf(tMovimientos.getValueAt(fila2, 6).toString());
            FrmFacturacionCompraConsignacion.totalMielYaDescontadaCompra = Double.valueOf(tMovimientos.getValueAt(fila2, 6).toString()) - Double.valueOf(tMovimientos.getValueAt(fila2, 7).toString());
            FrmFacturacionCompraConsignacion.totalMielMantenidaEnConsignacion = Double.valueOf(tMovimientos.getValueAt(fila2, 7).toString());
            //ademas muestro estos datos debajo de la grilla de items financiados disponibles para la facturacion
            String totales = String.valueOf("  TOTALES: Miel financiada: "+FrmFacturacionCompraConsignacion.totalMielFinanciadaCompra+" kgs. / Miel descontada: "+FrmFacturacionCompraConsignacion.totalMielYaDescontadaCompra+"kgs. / Miel en consignacion: "+FrmFacturacionCompraConsignacion.totalMielMantenidaEnConsignacion+" kgs.");
            FrmFacturacionCompraConsignacion.tfTotalesMielCompra.setText(totales);
            //importe, pagado y saldo no hace falta pasar ya que no se manejan valores en este tipo de movimientos, va 0 en todos
            //estado de movimiento no hace falta pasar, se asentara CANCELADO o  PENDIENTE segun corresponda
            FrmFacturacionCompraConsignacion.observacionCompra = tMovimientos.getValueAt(fila2, 12).toString();
            
            FrmFacturacionCompraConsignacion.nombreProductor = tfNombreProductor.getText();
            form.tfDatosCompraConsignacion.setText("COMPRA EN CONSIGNACION N° "+FrmFacturacionCompraConsignacion.numeroComprobanteCompra+" / Productor N° "+FrmFacturacionCompraConsignacion.codigoProductor+" - "+nombreProductor);
            
            //con esto obtengo todos los comprobantes que afectan a la compra en consignacion
            FrmFacturacionCompraConsignacion.obtenerComprobantesRelacionadosCompraConsignacion(FrmFacturacionCompraConsignacion.codigoCompra);

            //FrmFacturacionCompraConsignacion.tfCantidadKilos.setText(String.valueOf(form));
            FrmFacturacionCompraConsignacion.inicializar();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmCtaCteConProductor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            
            //1er chequeo: se debe seleccionar una fila de la grilla
            if (fila2 == -1) {
                
                JOptionPane.showMessageDialog(null, "Seleccione la fila correspondiente a la compra en consignacion cuya cantidad de miel desea afectar con una devolucion.", "DEVOLUCION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            //2do chequeo: se debe corroborar que se esta intentando facturar un credito y no otro tipo
            //de movimiento, como por ejemplo: un pago, una factura, un presupuesto, etc.
            if (!(tMovimientos.getValueAt(fila2, 3).toString().equals("CONSIGNACION"))) {
                
                JOptionPane.showMessageDialog(null, "No se puede afectar con una devolucion de miel el movimiento seleccionado. Seleccione una compra en consignacion para poder llevar a cabo dicha tarea por favor.", "DEVOLUCION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            //3er chequeo: se debe corroborar que no se esta intentando facturar una compra a consignacion ya facturada
            if (tMovimientos.getValueAt(fila2, 11).toString().equals("CANCELADO")) {
                
                JOptionPane.showMessageDialog(null, "Esta intentando afectar con una devolucion de miel una compra en consignacion ya cancelada. Seleccione otro comprobante para poder llevar a cabo dicha tarea por favor.", "DEVOLUCION DE COMPRA EN CONSIGNACION", JOptionPane.ERROR_MESSAGE);
                tMovimientos.requestFocus();
                return;
                
            }
            
            // "ID PRODUCTOR", "ID MOVIMIENTO", "FECHA", "REFERENCIA", "COMPROBANTE ASOCIADO", "N° COMPROB.", "KGS. MIEL COMPRADOS", "KGS. MIEL IMPAGOS", "IMPORTE", "PAGADO", "SALDO", "ESTADO MOVIMIENTO", "OBSERVACION"};
            
            FrmDevolucionCompraConsignacion form = new FrmDevolucionCompraConsignacion();
            
            //asigno valores que debera mostrar el formulario de pago al productor
            //y recuerdo todos los datos del MOVIMIENTO CORRESPONDIENTE A LA COMPRA EN CONSIGNACION
            //para editar luego la misma sin perder la informacion original
            FrmDevolucionCompraConsignacion.codigoProductor = Integer.valueOf(tMovimientos.getValueAt(fila2, 0).toString());
            FrmDevolucionCompraConsignacion.codigoMovimientoCtaCteCompra = Integer.valueOf(tMovimientos.getValueAt(fila2, 1).toString());
            FrmDevolucionCompraConsignacion.fechaMovimientoCompra = Date.valueOf(tMovimientos.getValueAt(fila2, 2).toString());
            //referencia no hace falta pasar porque ya sabemos que es una compra en consignacion
            //codigoCompra es el codigo de la compra en consignacion
            FrmDevolucionCompraConsignacion.codigoCompra = Integer.valueOf(tMovimientos.getValueAt(fila2, 4).toString());
            FrmDevolucionCompraConsignacion.numeroComprobanteCompra = tMovimientos.getValueAt(fila2, 5).toString();
            //recuerdo la cantidad de miel adquirida originalmente en la compra
            //y la cantidad de miel ya descontada de la misma, ya sea por facturacion o por devolucion de miel en ella
            FrmDevolucionCompraConsignacion.totalMielFinanciadaCompra = Double.valueOf(tMovimientos.getValueAt(fila2, 6).toString());
            FrmDevolucionCompraConsignacion.totalMielYaDescontadaCompra = Double.valueOf(tMovimientos.getValueAt(fila2, 6).toString()) - Double.valueOf(tMovimientos.getValueAt(fila2, 7).toString());
            FrmDevolucionCompraConsignacion.totalMielMantenidaEnConsignacion = Double.valueOf(tMovimientos.getValueAt(fila2, 7).toString());
            //ademas muestro estos datos debajo de la grilla de items financiados disponibles para la facturacion
            String totales = String.valueOf("  TOTALES: Miel financiada: "+FrmDevolucionCompraConsignacion.totalMielFinanciadaCompra+" kgs. / Miel descontada: "+FrmDevolucionCompraConsignacion.totalMielYaDescontadaCompra+"kgs. / Miel en consignacion: "+FrmDevolucionCompraConsignacion.totalMielMantenidaEnConsignacion+" kgs.");
            FrmDevolucionCompraConsignacion.tfTotalesMielCompra.setText(totales);
            //importe, pagado y saldo no hace falta pasar ya que no se manejan valores en este tipo de movimientos, va 0 en todos
            //estado de movimiento no hace falta pasar, se asentara CANCELADO o  PENDIENTE segun corresponda
            FrmDevolucionCompraConsignacion.observacionCompra = tMovimientos.getValueAt(fila2, 12).toString();
            
            FrmDevolucionCompraConsignacion.nombreProductor = tfNombreProductor.getText();
            form.tfDatosCompraConsignacion.setText("COMPRA EN CONSIGNACION N° "+FrmDevolucionCompraConsignacion.numeroComprobanteCompra+" / Productor N° "+FrmDevolucionCompraConsignacion.codigoProductor+" - "+nombreProductor);
            
            //con esto obtengo todos los comprobantes que afectan a la compra en consignacion
            FrmDevolucionCompraConsignacion.obtenerComprobantesRelacionadosCompraConsignacion(FrmDevolucionCompraConsignacion.codigoCompra);
            
            FrmDevolucionCompraConsignacion.inicializar();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmCtaCteConProductor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //1er chequeo: se debe seleccionar una fila de la grilla
        if (fila2 == -1) {
            
            JOptionPane.showMessageDialog(null, "Seleccione la fila correspondiente al comprobante que desea abonar.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //2do chequeo: se debe corroborar que se esta intentando pagar una factura o un presupuesto y no otro tipo
        //de movimiento, como por ejemplo: un pago anterior, un saldo a favor, etc.
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("PAGO") || tMovimientos.getValueAt(fila2, 3).toString().equals("SALDO A FAVOR")) {
            
            JOptionPane.showMessageDialog(null, "No se puede vincular un pago al movimiento seleccionado. Seleccione una factura o un presupuesto para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //3er chequeo: se debe corroborar que se esta intentando pagar una factura o un presupuesto y no un credito (el cual debe facturarse)
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("CONSIGNACION")) {
            
            JOptionPane.showMessageDialog(null, "No se puede abonar una compra a consignacion. La misma debe ser antes facturada o presupuestada.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //4to chequeo: se debe corroborar que no se esta intentando abonar una factura o un presupuesto ya cancelado
        if (tMovimientos.getValueAt(fila2, 11).toString().equals("CANCELADO")) {
            
            System.out.println("Entra bien!");
            JOptionPane.showMessageDialog(null, "Esta intentando abonar un comprobante ya cancelado. Seleccione otro comprobante para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        FrmRegistroPagoAProductor form = new FrmRegistroPagoAProductor();
        //asigno valores que debera mostrar el formulario de pago al productor
        form.tfCliente.setText(tMovimientos.getValueAt(fila2, 3).toString()+" N° "+tMovimientos.getValueAt(fila2, 5).toString()+" / Productor N° "+tfIDProductor.getText()+": "+tfNombreProductor.getText());

        //kilos facturados e importe del comprobante
        form.tfKilosFacturados.setText(String.valueOf(tMovimientos.getValueAt(fila2, 6)));
        //importe del comprobante
        form.tfImporteTotalComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 8)));
        //precio unitario del kilo facturado en el comprobante
        Double kilosFacturados = Double.valueOf(tMovimientos.getValueAt(fila2, 6).toString());
        Double importeComprobante = Double.valueOf(tMovimientos.getValueAt(fila2, 8).toString());
        Double precioUnitario = importeComprobante / kilosFacturados;
        //precio unitario del kilo facturado en el comprobante
        form.precioUnitario = importeComprobante / kilosFacturados;
        form.tfPrecioUnitario.setText(String.valueOf(precioUnitario));
        //saldo impago del comprobante
        form.tfSaldoImpagoComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
        form.saldoImpago = Double.valueOf(tMovimientos.getValueAt(fila2, 10).toString());
        //saldo pendiente del comprobante, una vez efectuado el pago!
        form.tfSaldoPendiente.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
        Double saldoPendienteDePago = Double.valueOf(tMovimientos.getValueAt(fila2, 10).toString());
        Double kilosImpagos = saldoPendienteDePago / precioUnitario;
        FrmRegistroPagoAProductor.totalKilosImpagos = kilosImpagos;
        form.tfKilosImpagos.setText(String.valueOf(kilosImpagos));
        //por defecto asumimos que se pagaran todos los kilos que corresponden al saldo del comprobante
        //mas de eso no se podria pagar
        form.tfKilosAPagar.setText(String.valueOf(kilosImpagos));
        form.tfMontoPago.setText(String.valueOf(kilosImpagos * precioUnitario));

        FrmRegistroPagoAProductor.codigoProductor = Integer.parseInt(tfIDProductor.getText());
        FrmRegistroPagoAProductor.codigoComprobanteAfectadoPago = Integer.parseInt(tMovimientos.getValueAt(fila2, 4).toString());
        FrmRegistroPagoAProductor.tipoComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 3).toString();
        FrmRegistroPagoAProductor.numeroComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 5).toString();
        FrmRegistroPagoAProductor.codigoMovimientoCtaCteComprobanteAfectado = Integer.parseInt(tMovimientos.getValueAt(fila2, 1).toString());
        FrmRegistroPagoAProductor.debeComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 8).toString());
        FrmRegistroPagoAProductor.haberComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 9).toString());
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);
        
        form.inicializar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        //1er chequeo: se debe seleccionar una fila de la grilla
        if (fila2 == -1) {
            
            JOptionPane.showMessageDialog(null, "Seleccione la fila correspondiente al comprobante que desea anular.", "ANULACION DE COMPROBANTE", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //2do chequeo: se debe corroborar que se esta intentando pagar una factura o un presupuesto y no otro tipo
        //de movimiento, como por ejemplo: un pago anterior, un saldo a favor, etc.
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("PAGO") || tMovimientos.getValueAt(fila2, 3).toString().equals("SALDO A FAVOR")) {
            
            JOptionPane.showMessageDialog(null, "No se puede vincular un pago al movimiento seleccionado. Seleccione una factura o un presupuesto para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //3er chequeo: se debe corroborar que se esta intentando pagar una factura o un presupuesto y no un credito (el cual debe facturarse)
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("CONSIGNACION")) {
            
            JOptionPane.showMessageDialog(null, "No se puede abonar una compra a consignacion. la misma debe ser antes facturada o presupuestada.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //4to chequeo: se debe corroborar que no se esta intentando abonar una factura o un presupuesto ya cancelado
        if (tMovimientos.getValueAt(fila2, 11).toString().equals("CANCELADO")) {
            
            System.out.println("Entra bien!");
            JOptionPane.showMessageDialog(null, "Esta intentando abonar un comprobante ya cancelado. Seleccione otro comprobante para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        FrmRegistroPagoAProductor form = new FrmRegistroPagoAProductor();
        //asigno valores que debera mostrar el formulario de pago al productor
        form.tfCliente.setText(tMovimientos.getValueAt(fila2, 3).toString()+" N° "+tMovimientos.getValueAt(fila2, 5).toString()+" / Productor N° "+tfIDProductor.getText()+": "+tfNombreProductor.getText());
        form.tfImporteTotalComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 8)));
        form.tfSaldoImpagoComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
        form.tfSaldoPendiente.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
        FrmRegistroPagoAProductor.codigoProductor = Integer.parseInt(tfIDProductor.getText());
        FrmRegistroPagoAProductor.codigoComprobanteAfectadoPago = Integer.parseInt(tMovimientos.getValueAt(fila2, 4).toString());
        FrmRegistroPagoAProductor.tipoComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 3).toString();
        FrmRegistroPagoAProductor.numeroComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 5).toString();
        FrmRegistroPagoAProductor.codigoMovimientoCtaCteComprobanteAfectado = Integer.parseInt(tMovimientos.getValueAt(fila2, 1).toString());
        FrmRegistroPagoAProductor.debeComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 8).toString());
        FrmRegistroPagoAProductor.haberComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 9).toString());
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);
        
        form.inicializar();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        //Abre un formulario que muestra todas las facturas del productor seleccionado
        //para poder asociar alguna factura NO CANCELADA a la nota de credito que se esta por registrar
        //en otra pestaña de la misma ventana se ingresaran los datos puntuales de la nota de credito

        //el unico chequeo a realizar es que se este trabajando en la cta. cte. de un productor (que se haya seleccionado el mismo)
        //implementar el chequeo!
        if (tfIDProductor.getText().length() == 0){
            
            //no se esta visualizando la cta. cte de ningun productor!
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar un productor para visualizar su cta. cte..", "REGISTRO NOTA DE CREDITO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //se abre el formulario de asociacion de nota de credito (el mismo muestra todas las facturas del productor)
        try {
            
            FrmRegistroNotaCreditoProductor form = new FrmRegistroNotaCreditoProductor();
            //recordar chequear si este campo no esta vacio, ya que si lo esta, es porque no se esta visualizando
            //ninguna cta. cte.
            form.codigoProductor = Integer.parseInt(tfIDProductor.getText());
            //asigno valores que debera mostrar el formulario de pago al productor
            /*form.tfCliente.setText(tMovimientos.getValueAt(fila2, 3).toString()+" N° "+tMovimientos.getValueAt(fila2, 5).toString()+" / Productor N° "+tfIDProductor.getText()+": "+tfNombreProductor.getText());
            form.tfImporteTotalComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 8)));
            form.tfSaldoImpagoComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
            form.tfSaldoPendiente.setText(String.valueOf(tMovimientos.getValueAt(fila2, 10)));
            FrmRegistroPagoAProductor.codigoProductor = Integer.parseInt(tfIDProductor.getText());
            FrmRegistroPagoAProductor.codigoComprobanteAfectadoPago = Integer.parseInt(tMovimientos.getValueAt(fila2, 4).toString());
            FrmRegistroPagoAProductor.tipoComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 3).toString();
            FrmRegistroPagoAProductor.numeroComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 5).toString();
            FrmRegistroPagoAProductor.codigoMovimientoCtaCteComprobanteAfectado = Integer.parseInt(tMovimientos.getValueAt(fila2, 1).toString());
            FrmRegistroPagoAProductor.debeComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 8).toString());
            FrmRegistroPagoAProductor.haberComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 9).toString());*/
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();

            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);

            form.setClosable(true);
            form.setIconifiable(false);

            form.inicializar();

        } catch (SQLException ex) {
            Logger.getLogger(FrmCtaCteConProductor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    public void actualizarImporteTotalPago() {

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double importeTotalPago = 0.00;
        
        for (int i = 0; i < tMovimientos.getRowCount(); i++) {
            
            importeTotalPago = importeTotalPago + Double.valueOf(tMovimientos.getValueAt(i, 11).toString());
            
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JLabel lSaldoDineroImpago;
    public static javax.swing.JLabel lSaldoMielImpago;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tMovimientos;
    public javax.swing.JTable tProductores;
    private javax.swing.JToolBar tbOpciones;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfNombreProductor;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
