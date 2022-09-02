/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionComprobantes extends javax.swing.JInternalFrame {

    int fila = -1;
    int codigoComprobante;
    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionComprobantes() {
        
        initComponents();
        mostrarComprobantes("");
        //ocultarColumnasComprobantes();
        inicializar();
        
    }

    public void inicializar() {
        
        cbTipoComprobante.setSelectedIndex(0);
        cbTipoComprobante.requestFocus();
        
}

        public static void mostrarComprobantes(String tipoComprobante) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            //Productor productor = new Productor();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            //modelo = productor.listarProductores(tipoComprobante);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            //tComprobantes.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public static void ocultarColumnasComprobantes() {

        /*tProductores.getColumnModel().getColumn(0).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(0).setMinWidth(0);
        tProductores.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tProductores.getColumnModel().getColumn(1).setMaxWidth(0);
        tProductores.getColumnModel().getColumn(1).setMinWidth(0);
        tProductores.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tComprobantes.getColumnModel().getColumn(6).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(6).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        tComprobantes.getColumnModel().getColumn(7).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(7).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        tComprobantes.getColumnModel().getColumn(8).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(8).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        tComprobantes.getColumnModel().getColumn(9).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(9).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(9).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(10).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(10).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(10).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(11).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(11).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(11).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(12).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(12).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(12).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(13).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(13).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(13).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(14).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(14).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(14).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(15).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(15).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(15).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(16).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(16).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(16).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(17).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(17).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(17).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(18).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(18).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(18).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(19).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(19).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(19).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(20).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(20).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(20).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(21).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(21).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(21).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(6).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tComprobantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
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
        jPanel1 = new javax.swing.JPanel();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tComprobantes = tComprobantes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        cbTipoComprobante = new javax.swing.JComboBox<>();
        tbOpciones = new javax.swing.JToolBar();
        bConsultaMovimiento = new javax.swing.JButton();
        rsbrSalir = new rojeru_san.RSButtonRiple();

        setTitle("GESTION DE COMPROBANTES - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("COMPROBANTES REGISTRADOS EN EL SISTEMA");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VER COMPROBANTES:");

        tComprobantes.setBackground(new java.awt.Color(36, 33, 33));
        tComprobantes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tComprobantes.setForeground(new java.awt.Color(207, 207, 207));
        tComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tComprobantes.setOpaque(true);
        tComprobantes.setRowHeight(20);
        tComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tComprobantesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tComprobantes);

        cbTipoComprobante.setBackground(new java.awt.Color(255, 255, 0));
        cbTipoComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURAS A", "FACTURAS C", "FACTURAS E", "PRESUPUESTOS", "PAGOS", "NOTAS DE CREDITO A", "NOTAS DE CREDITO C", "NOTAS DE CREDITO E", "ANULACIONES", "DEVOLUCIONES" }));
        cbTipoComprobante.setPreferredSize(new java.awt.Dimension(136, 19));
        cbTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoComprobanteActionPerformed(evt);
            }
        });

        tbOpciones.setBackground(new java.awt.Color(0, 0, 0));
        tbOpciones.setBorder(null);
        tbOpciones.setFloatable(false);
        tbOpciones.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        bConsultaMovimiento.setBackground(new java.awt.Color(0, 0, 0));
        bConsultaMovimiento.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bConsultaMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        bConsultaMovimiento.setText("  CONSULTAR MOVIMIENTO  ");
        bConsultaMovimiento.setBorderPainted(false);
        bConsultaMovimiento.setFocusable(false);
        bConsultaMovimiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bConsultaMovimiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bConsultaMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultaMovimientoActionPerformed(evt);
            }
        });
        tbOpciones.add(bConsultaMovimiento);

        rsbrSalir.setBackground(new java.awt.Color(0, 0, 0));
        rsbrSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Imagenes/btn-cerrar.png"))); // NOI18N
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 16)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tbOpciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tComprobantesMouseClicked

        fila = tComprobantes.rowAtPoint(evt.getPoint());
        codigoComprobante = Integer.valueOf(tComprobantes.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tComprobantesMouseClicked

    private void cbTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoComprobanteActionPerformed
    }//GEN-LAST:event_cbTipoComprobanteActionPerformed

    private void bConsultaMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultaMovimientoActionPerformed
    }//GEN-LAST:event_bConsultaMovimientoActionPerformed

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsultaMovimiento;
    public javax.swing.JComboBox<String> cbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tComprobantes;
    private javax.swing.JToolBar tbOpciones;
    // End of variables declaration//GEN-END:variables
}
