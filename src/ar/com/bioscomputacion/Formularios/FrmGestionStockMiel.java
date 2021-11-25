/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionStockMiel extends javax.swing.JInternalFrame {

    int codigoLocacion;
    int fila = -1;
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionStockMiel() throws SQLException {
        
        initComponents();
        mostrarDetalleStock();
        ocultarColumnas();
        inicializar();
        
        
    }

    public void inicializar() throws SQLException{
        
        //
        
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
    
    public void mostrarDetalleStock(){
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = stock.mostrarDetalleStock();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tDetalleStock1.setModel(modelo);
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
        public void ocultarColumnas() {

        /*tDetalleStock1.getColumnModel().getColumn(0).setMaxWidth(0);
        tDetalleStock1().getColumn(0).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tDetalleStock1.getColumnModel().getColumn(1).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(1).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*v.getColumnModel().getColumn(2).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(2).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        
        /*tDetalleStock1.getColumnModel().getColumn(3).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(3).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        /*tDetalleStock1.getColumnModel().getColumn(4).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(4).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleStock1.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleStock1.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(4).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tDetalleStock1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tDetalleStock1 = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

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

        setTitle("GESTION DE STOCK DE MIEL - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        jTabbedPane1.setBackground(new java.awt.Color(51, 84, 111));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("KGS. DE MIEL ACOPIADOS EN CADA LOCACION:");

        tDetalleStock1.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStock1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStock1.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStock1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStock1.setOpaque(true);
        tDetalleStock1.setRowHeight(20);
        tDetalleStock1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStock1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tDetalleStock1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane10))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Detalle de stock por locacion", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 173, Short.MAX_VALUE))
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

    private void tDetalleStock1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStock1MouseClicked

        fila = tDetalleStock.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tDetalleStock1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JTable tDetalleStock;
    private javax.swing.JTable tDetalleStock1;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    // End of variables declaration//GEN-END:variables
}
