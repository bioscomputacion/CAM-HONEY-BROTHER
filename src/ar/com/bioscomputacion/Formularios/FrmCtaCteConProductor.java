/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmCtaCteConProductor extends javax.swing.JInternalFrame {

    public int codigoProductor, codigoFactura, codigoItemFacturado;
    //la variable ctaCteMostrada es para no ejecutar el mismo codigo cada vez que se hace click en la pestaña cta cte
    //una vez que se pone en true el codigo ya no debe ejecutarse, caso contrario se ejcuta dicho codigo.
    public String nombreProductor;
    int fila, fila2 = -1;
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
        tfProvinciaProductor.setEditable(false);
        tfLocalidadProductor.setEditable(false);
        
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

        tMovimientos.getColumnModel().getColumn(10).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(10).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(10).setPreferredWidth(0);

        tMovimientos.getColumnModel().getColumn(11).setMaxWidth(0);
        tMovimientos.getColumnModel().getColumn(11).setMinWidth(0);
        tMovimientos.getColumnModel().getColumn(11).setPreferredWidth(0);
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender7 = new DefaultTableCellRenderer();
        
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
            lSaldoMielImpago.setText(formateador.format(ctacteProductor.mostrarSaldoMielImpagaAProductor(codigoProductor))+ " KGS. DE MIEL IMPAGOS");

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
        jLabel13 = new javax.swing.JLabel();
        tfProvinciaProductor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfLocalidadProductor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lSaldoDineroImpago = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMovimientos = new javax.swing.JTable();
        bPago = new javax.swing.JButton();
        lbPagoTotal = new javax.swing.JLabel();
        lImporteTotalPago = new javax.swing.JLabel();
        lSaldoMielImpago = new javax.swing.JLabel();
        rsbrSalir = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(51, 84, 111));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CUENTA CORRIENTE CON PRODUCTORES - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

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

        tfIDProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfIDProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfIDProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID PRODUCTOR:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfNombreProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNombreProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfDocumentoProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfDocumentoProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PROVINCIA:");

        tfProvinciaProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfProvinciaProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfProvinciaProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("LOCALIDAD:");

        tfLocalidadProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfLocalidadProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfLocalidadProductor.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDocumentoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(tfProvinciaProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLocalidadProductor)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDocumentoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfProvinciaProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLocalidadProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("MOVIMIENTOS EN LA CTA. CTE. DEL PRODUCTOR SELECCIONADO:");

        lSaldoDineroImpago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lSaldoDineroImpago.setForeground(new java.awt.Color(153, 255, 255));
        lSaldoDineroImpago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lSaldoDineroImpago.setText("TOTAL");

        tMovimientos.setBackground(new java.awt.Color(36, 33, 33));
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

        bPago.setBackground(new java.awt.Color(36, 33, 33));
        bPago.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        bPago.setForeground(new java.awt.Color(224, 224, 224));
        bPago.setText("PAGAR");
        bPago.setPreferredSize(new java.awt.Dimension(159, 33));
        bPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPagoActionPerformed(evt);
            }
        });

        lbPagoTotal.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lbPagoTotal.setForeground(new java.awt.Color(153, 255, 255));
        lbPagoTotal.setText("IMPORTE TOTAL A PAGAR: $");

        lImporteTotalPago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lImporteTotalPago.setForeground(new java.awt.Color(153, 255, 255));
        lImporteTotalPago.setText("0.00");

        lSaldoMielImpago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lSaldoMielImpago.setForeground(new java.awt.Color(153, 255, 255));
        lSaldoMielImpago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lSaldoMielImpago.setText("TOTAL");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbPagoTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lImporteTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(bPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lSaldoMielImpago, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lSaldoDineroImpago, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPagoTotal)
                        .addComponent(lImporteTotalPago)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(lSaldoDineroImpago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lSaldoMielImpago))
        );

        tpFactura.addTab("Cuenta corriente con el productor", jPanel3);

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
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
        tfProvinciaProductor.setText(tProductores.getValueAt(fila, 4).toString());
        tfLocalidadProductor.setText(tProductores.getValueAt(fila, 5).toString());

        //cada vez que se selecciona un productor en la otra pestaña se muestra la corriente con el, tenga movimientos o no
        mostrarCtaCteProductor(codigoProductor);
        ocultarColumnasCtaCte();
    }//GEN-LAST:event_tProductoresMouseClicked

    private void tMovimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMovimientosMouseClicked

        fila2 = tMovimientos.rowAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tMovimientosMouseClicked

    private void tMovimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMovimientosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMovimientosKeyPressed

    private void tMovimientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMovimientosKeyReleased
    }//GEN-LAST:event_tMovimientosKeyReleased

    private void bPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagoActionPerformed

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
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("CREDITO")) {
            
            JOptionPane.showMessageDialog(null, "No se puede abonar un credito. El mismo debe ser antes facturado o presupuestado para luego realizar el pago correspondiente.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        //4to chequeo: se debe corroborar que no se esta intentando abonar una factura o un presupuesto ya cancelado
        if (tMovimientos.getValueAt(fila2, 10).toString().equals("CANCELADO")) {
            
            JOptionPane.showMessageDialog(null, "Esta intentando abonar un comprobante ya cancelado. Seleccione otro comprobante para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;
            
        }
        
        FrmRegistroPagoAProductor form = new FrmRegistroPagoAProductor();
        //asigno valores que debera mostrar el formulario de pago al productor
        form.tfCliente.setText(tMovimientos.getValueAt(fila2, 3).toString()+" N° "+tMovimientos.getValueAt(fila2, 5).toString()+" / Productor N° "+tfIDProductor.getText()+": "+tfNombreProductor.getText());
        form.tfImporteTotalComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 7)));
        form.tfSaldoImpagoComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 9)));
        form.tfSaldoPendiente.setText(String.valueOf(tMovimientos.getValueAt(fila2, 9)));
        FrmRegistroPagoAProductor.codigoProductor = Integer.parseInt(tfIDProductor.getText());
        FrmRegistroPagoAProductor.codigoComprobanteAfectadoPago = Integer.parseInt(tMovimientos.getValueAt(fila2, 4).toString());
        FrmRegistroPagoAProductor.tipoComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 3).toString();
        FrmRegistroPagoAProductor.numeroComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 5).toString();
        FrmRegistroPagoAProductor.codigoMovimientoCtaCteComprobanteAfectado = Integer.parseInt(tMovimientos.getValueAt(fila2, 1).toString());
        FrmRegistroPagoAProductor.debeComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 7).toString());
        FrmRegistroPagoAProductor.haberComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 8).toString());
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);
        
        form.inicializar();
                            

    }//GEN-LAST:event_bPagoActionPerformed

    private void tpFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpFacturaMouseClicked
    }//GEN-LAST:event_tpFacturaMouseClicked

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed

    public void actualizarImporteTotalPago() {

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double importeTotalPago = 0.00;
        
        for (int i = 0; i < tMovimientos.getRowCount(); i++) {
            
            importeTotalPago = importeTotalPago + Double.valueOf(tMovimientos.getValueAt(i, 11).toString());
            
        }

        lImporteTotalPago.setText(String.valueOf(formateador.format(importeTotalPago)));
        System.out.println(importeTotalPago);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPago;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel lImporteTotalPago;
    public static javax.swing.JLabel lSaldoDineroImpago;
    public static javax.swing.JLabel lSaldoMielImpago;
    private javax.swing.JLabel lbPagoTotal;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tMovimientos;
    public javax.swing.JTable tProductores;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfLocalidadProductor;
    public javax.swing.JTextField tfNombreProductor;
    public javax.swing.JTextField tfProvinciaProductor;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
