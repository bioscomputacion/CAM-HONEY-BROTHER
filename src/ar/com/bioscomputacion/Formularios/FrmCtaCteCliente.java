/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.Cliente;
import ar.com.bioscomputacion.Funciones.CtaCteCliente;
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
public class FrmCtaCteCliente extends javax.swing.JInternalFrame {

    public int codigoCliente, codigoFactura, codigoItemFacturado;
    //la variable ctaCteMostrada es para no ejecutar el mismo codigo cada vez que se hace click en la pestaña cta cte
    //una vez que se pone en true el codigo ya no debe ejecutarse, caso contrario se ejcuta dicho codigo.
    public String nombreCliente;
    int fila, fila2 = -1;
    /**
     * Creates new form FrmGenerico
     */
    public FrmCtaCteCliente() {
        
        initComponents();
        mostrarClientes("");
        ocultarColumnasClientes();
        //ocultarColumnasItemsFacturados();
        inicializar();
        
    }

    public void inicializar(){
        
        tfNombreCliente.setEditable(false);
        tfDocumentoCliente.setEditable(false);
        
        tClientes.requestFocus();
        
    }

    public void mostrarClientes(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Cliente cliente = new Cliente();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = cliente.listarClientes(buscar, "TODOS");
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tClientes.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnasClientes() {

        /*tProductores.getColumnModel().getColumn(0).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(0).setMinWidth(0);
        tProductores.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tProductores.getColumnModel().getColumn(1).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(1).setMinWidth(0);
        tProductores.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tClientes.getColumnModel().getColumn(2).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(2).setMinWidth(0);
        tClientes.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tClientes.getColumnModel().getColumn(3).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(3).setMinWidth(0);
        tClientes.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tProductores.getColumnModel().getColumn(4).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(4).setMinWidth(0);
        tProductores.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tProductores.getColumnModel().getColumn(5).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(5).setMinWidth(0);
        tProductores.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tProductores.getColumnModel().getColumn(6).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(6).setMinWidth(0);
        tProductores.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
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

        tClientes.getColumnModel().getColumn(15).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(15).setMinWidth(0);
        tClientes.getColumnModel().getColumn(15).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tClientes.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(5).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.LEFT);
        tClientes.getColumnModel().getColumn(6).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
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

    public static void mostrarCtaCteCliente(int codigoCliente) {
        
        
        try {
            
            //muestra todos los movimientos en la cta. cte. que tiene la empresa con el productor seleccionado
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
            DefaultTableModel modelo = null;
            //para mostrar en la grilla todos los movimientos del cliente
            CtaCteCliente ctacteCliente = new CtaCteCliente();
            modelo = ctacteCliente.listarMovimientosCtaCteCliente(codigoCliente);
            tMovimientos.setModel(modelo);
            //muestra el saldo en el label debajo de la grilla
            lSaldoDineroImpago.setText("SALDO IMPAGO: $ " + formateador.format(ctacteCliente.mostrarSaldoCtaCteCliente(codigoCliente)));
            //lSaldoMielImpago.setText(formateador.format(ctacteCliente.mostrarSaldoMielImpagaAProductor(codigoCliente))+ " KGS. DE MIEL IMPAGOS");

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
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lSaldoDineroImpago = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMovimientos = new javax.swing.JTable();
        tbOpciones = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        rsbrSalir = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(51, 84, 111));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CUENTA CORRIENTE DE CLIENTES - CAM HONEY BROTHERS");
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
        jLabel2.setText("SELECCIONE EL CLIENTE CORRESPONDIENTE:");

        jLabel4.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUSCAR CLIENTE POR NOMBRE:");

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
        tfIDCliente.setBackground(new java.awt.Color(51, 84, 111));
        tfIDCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfIDCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID CLIENTE:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreCliente.setEditable(false);
        tfNombreCliente.setBackground(new java.awt.Color(51, 84, 111));
        tfNombreCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombreCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoCliente.setEditable(false);
        tfDocumentoCliente.setBackground(new java.awt.Color(51, 84, 111));
        tfDocumentoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDocumentoCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("A SELECCIONADO EL CLIENTE:");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNombreCliente)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfDocumentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
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
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(tfIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDocumentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del cliente", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("MOVIMIENTOS EN LA CTA. CTE. DEL CLIENTE SELECCIONADO:");

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

        tbOpciones.setBackground(new java.awt.Color(0, 0, 0));
        tbOpciones.setFloatable(false);
        tbOpciones.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("  ABONAR COMPROBANTE  ");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton1);

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("  REGISTRAR NOTA DE CREDITO  ");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbOpciones.add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("  ANULAR COMPROBANTE  ");
        jButton5.setToolTipText("");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
                        .addComponent(lSaldoDineroImpago))
                    .addComponent(tbOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lSaldoDineroImpago)
                .addGap(11, 11, 11))
        );

        tpFactura.addTab("Cuenta corriente del cliente seleccionado", jPanel3);

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

        mostrarClientes(tfBusquedaPorNombre.getText());
        ocultarColumnasClientes();
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked

        fila = tClientes.rowAtPoint(evt.getPoint());
        //en esta variable siempre va a estar almacenado el codigo del productor seleccionado en la grilla
        //en esta variable siempre va a estar almacenado el nombre del productor seleccionado en la grilla
        codigoCliente = Integer.parseInt(tClientes.getValueAt(fila, 0).toString());
        nombreCliente = tClientes.getValueAt(fila, 1).toString();
        //System.out.println(codigoCliente +" "+ nombreCliente);

        //cada vez que se hace click sobre la grilla se muestran en los campos debajo lso datos del productor
        //correspondiente a la fila de la grilla cliqueada
        tfIDCliente.setText(tClientes.getValueAt(fila, 0).toString());
        tfNombreCliente.setText(tClientes.getValueAt(fila, 1).toString());
        tfDocumentoCliente.setText(tClientes.getValueAt(fila, 2).toString());

        //cada vez que se selecciona un productor en la otra pestaña se muestra la corriente con el, tenga movimientos o no
        mostrarCtaCteCliente(codigoCliente);
        ocultarColumnasCtaCte();
    }//GEN-LAST:event_tClientesMouseClicked

    private void tMovimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMovimientosMouseClicked

        fila2 = tMovimientos.rowAtPoint(evt.getPoint());
        
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //1er chequeo: se debe seleccionar una fila de la grilla
        if (fila2 == -1) {

            JOptionPane.showMessageDialog(null, "Seleccione la fila correspondiente al comprobante que desea abonar.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;

        }

        //2do chequeo: se debe corroborar que se esta intentando pagar una factura o un presupuesto y no otro tipo
        //de movimiento, como por ejemplo: un pago anterior, un saldo a favor, etc.
        if (tMovimientos.getValueAt(fila2, 3).toString().equals("PAGO") || tMovimientos.getValueAt(fila2, 3).toString().equals("SALDO A FAVOR")) {

            JOptionPane.showMessageDialog(null, "No se puede vincular un pago al movimiento seleccionado. Seleccione una factura para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;

        }

        //3ER chequeo: se debe corroborar que no se esta intentando abonar una factura o un presupuesto ya cancelado
        if (tMovimientos.getValueAt(fila2, 10).toString().equals("CANCELADO")) {

            JOptionPane.showMessageDialog(null, "Esta intentando abonar un comprobante ya cancelado. Seleccione otro comprobante para realizar el pago correspondiente por favor.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tMovimientos.requestFocus();
            return;

        }

        FrmRegistroPagoDeCliente form = new FrmRegistroPagoDeCliente();
        //asigno valores que debera mostrar el formulario de pago al productor
        form.tfCliente.setText(tMovimientos.getValueAt(fila2, 3).toString()+" N° "+tMovimientos.getValueAt(fila2, 5).toString()+" / CLIENTE N° "+tfIDCliente.getText()+": "+tfNombreCliente.getText());
        form.tfImporteTotalComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 7)));
        form.tfSaldoImpagoComprobante.setText(String.valueOf(tMovimientos.getValueAt(fila2, 9)));
        form.tfSaldoPendiente.setText(String.valueOf(tMovimientos.getValueAt(fila2, 9)));
        FrmRegistroPagoDeCliente.codigoCliente = Integer.parseInt(tfIDCliente.getText());
        FrmRegistroPagoDeCliente.codigoComprobanteAfectadoPago = Integer.parseInt(tMovimientos.getValueAt(fila2, 4).toString());
        FrmRegistroPagoDeCliente.tipoComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 3).toString();
        FrmRegistroPagoDeCliente.numeroComprobanteAfectadoPago = tMovimientos.getValueAt(fila2, 5).toString();
        FrmRegistroPagoDeCliente.codigoMovimientoCtaCteComprobanteAfectado = Integer.parseInt(tMovimientos.getValueAt(fila2, 1).toString());
        FrmRegistroPagoDeCliente.debeComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 7).toString());
        FrmRegistroPagoDeCliente.haberComprobante = Double.parseDouble(tMovimientos.getValueAt(fila2, 8).toString());

        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

        form.inicializar();

    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tClientes;
    public static javax.swing.JTable tMovimientos;
    private javax.swing.JToolBar tbOpciones;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfDocumentoCliente;
    public javax.swing.JTextField tfIDCliente;
    public javax.swing.JTextField tfNombreCliente;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
