/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.FacturaCliente;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.NotaCreditoCliente;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.PagoCliente;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import ar.com.bioscomputacion.Funciones.PresupuestoCliente;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
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

        public void mostrarComprobantes(String tipoComprobante) {
        
            /*
            SELECCIONAR
            FACTURAS A
            FACTURAS C
            FACTURAS E
            PRESUPUESTOS DE PRODUCTORES
            PRESUPUESTOS A CLIENTES
            CONSIGNACIONES
            PAGOS A PRODUCTORES
            PAGOS DE CLIENTES
            NOTAS DE CREDITO A
            NOTAS DE CREDITO C
            NOTAS DE CREDITO E
            ANULACIONES
            DEVOLUCIONES            
            */
            try {
            
            DefaultTableModel modelo;
            
            switch (tipoComprobante){
                
                case "SELECCIONAR":
                    
                    tComprobantes.removeAll();
                    break;
                
                case "FACTURAS A":
                    
                    FacturaProductor facturaA = new FacturaProductor();
                    modelo = facturaA.listarFacturasA("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "FACTURAS C":
                    
                    FacturaProductor facturaC = new FacturaProductor();
                    modelo = facturaC.listarFacturasC("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "FACTURAS E":
                    
                    FacturaCliente facturaE = new FacturaCliente();
                    modelo = facturaE.listarFacturasE("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "PRESUPUESTOS DE PRODUCTORES":
                    
                    PresupuestoProductor presupuestoProductor = new PresupuestoProductor();
                    modelo = presupuestoProductor.listarPresupuestos("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "PRESUPUESTOS A CLIENTES":
                    
                    PresupuestoCliente presupuestoCliente = new PresupuestoCliente();
                    modelo = presupuestoCliente.listarPresupuestos("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "CONSIGNACIONES":
                    
                    CreditoProductor credito = new CreditoProductor();
                    modelo = credito.listarConsignaciones("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesII();
                    break;
        
                case "PAGOS A PRODUCTORES":
                    
                    PagoProductor pagoProductor = new PagoProductor();
                    modelo = pagoProductor.listarPagos("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "PAGOS DE CLIENTES":
                    
                    PagoCliente pagoCliente = new PagoCliente();
                    modelo = pagoCliente.listarPagos("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "NOTAS DE CREDITO A":
                    
                    NotaCreditoProductor notaCreditoA = new NotaCreditoProductor();
                    modelo = notaCreditoA.listarNotasCreditoA("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "NOTAS DE CREDITO C":
                    
                    NotaCreditoProductor notaCreditoC = new NotaCreditoProductor();
                    modelo = notaCreditoC.listarNotasCreditoC("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "NOTAS DE CREDITO E":
                    
                    NotaCreditoCliente notaCreditoE = new NotaCreditoCliente();
                    modelo = notaCreditoE.listarNotasCreditoE("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                case "ANULACIONES":
                    
                    ///NotaCreditoProductor notaCreditoC = new NotaCreditoProductor();
                    //modelo = notaCreditoC.listarNotasCreditoC("");
                    //tComprobantes.setModel(modelo);
                    //ocultarColumnasComprobantes();
                    //break;
        
                case "DEVOLUCIONES":
                    
                    DevolucionProductor devolucion = new DevolucionProductor();
                    modelo = devolucion.listarDevoluciones("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantes();
                    break;
        
                default:
                    
                    break;
        
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnasComprobantes() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);*/

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

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(2).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(6).setCellRenderer(cellRender5);   
        
        ((DefaultTableCellRenderer) tComprobantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public void ocultarColumnasComprobantesII() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);*/

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(2).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        
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
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURAS A", "FACTURAS C", "FACTURAS E", "PRESUPUESTOS DE PRODUCTORES", "PRESUPUESTOS A CLIENTES", "CONSIGNACIONES", "INGRESOS", "FACTURACIONES DE CONSIGNACIONES", "PAGOS A PRODUCTORES", "PAGOS DE CLIENTES", "NOTAS DE CREDITO A", "NOTAS DE CREDITO C", "NOTAS DE CREDITO E", "ANULACIONES", "DEVOLUCIONES" }));
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tbOpciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        /*
        SELECCIONAR
        FACTURAS A
        FACTURAS C
        FACTURAS E
        PRESUPUESTOS
        PAGOS
        NOTAS DE CREDITO A
        NOTAS DE CREDITO C
        NOTAS DE CREDITO E
        ANULACIONES
        DEVOLUCIONES
        */
        
        String tipoComprobante = cbTipoComprobante.getSelectedItem().toString();
        mostrarComprobantes(tipoComprobante);
        tComprobantes.requestFocus();
        
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
