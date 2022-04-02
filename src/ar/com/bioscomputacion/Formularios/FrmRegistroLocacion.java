/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.Locacion;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmRegistroLocacion extends javax.swing.JInternalFrame {

    int fila = -1;
    int codigoLocacion;
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroLocacion() {
        
        initComponents();
        mostrarLocaciones("");
        ocultarColumnas();
        inicializar();
        
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tfNombreLocacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfUbicacionLocacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfObservacion = new javax.swing.JTextField();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        jLabel14 = new javax.swing.JLabel();
        cbCategoriaLocacion = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tLocaciones = tLocaciones = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        rdbrModificar = new rojeru_san.RSButtonRiple();
        rdbrEliminar = new rojeru_san.RSButtonRiple();
        rdbrDetalle = new rojeru_san.RSButtonRiple();
        rsbrSalir = new rojeru_san.RSButtonRiple();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE LOCACION - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        jTabbedPane1.setBackground(new java.awt.Color(51, 84, 111));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA LOCACION:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NOMBRE DE LA LOCACION:");

        tfNombreLocacion.setBackground(new java.awt.Color(51, 84, 111));
        tfNombreLocacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombreLocacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("UBICACION DE LA LOCACION:");

        tfUbicacionLocacion.setBackground(new java.awt.Color(51, 84, 111));
        tfUbicacionLocacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfUbicacionLocacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("OBSERVACIONES:");

        tfObservacion.setBackground(new java.awt.Color(51, 84, 111));
        tfObservacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfObservacion.setForeground(new java.awt.Color(255, 255, 255));

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR LOCACION");
        rdbrRegistrar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrarActionPerformed(evt);
            }
        });

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("CANCELAR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CATEGORIA DE LA LOCACION:");

        cbCategoriaLocacion.setBackground(new java.awt.Color(36, 33, 33));
        cbCategoriaLocacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbCategoriaLocacion.setForeground(new java.awt.Color(207, 207, 207));
        cbCategoriaLocacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "DEPOSITO DE PRODUCTOR", "DEPOSITO DE ACOPIO PROPIO", "HOMOGENEIZACION", "FISCALIZACION", "EMBARQUE" }));
        cbCategoriaLocacion.setPreferredSize(new java.awt.Dimension(136, 19));
        cbCategoriaLocacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaLocacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUbicacionLocacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNombreLocacion))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCategoriaLocacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfObservacion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(346, 346, 346)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNombreLocacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUbicacionLocacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategoriaLocacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Informacion de la locacion", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("SELECCIONE LA LOCACION CORRESPONDIENTE:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BUSCAR POR NOMBRE:");

        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

        tLocaciones.setBackground(new java.awt.Color(36, 33, 33));
        tLocaciones.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tLocaciones.setForeground(new java.awt.Color(207, 207, 207));
        tLocaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tLocaciones.setOpaque(true);
        tLocaciones.setRowHeight(20);
        tLocaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tLocacionesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tLocaciones);

        rdbrModificar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrModificar.setText("EDITAR DATOS");
        rdbrModificar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrModificarActionPerformed(evt);
            }
        });

        rdbrEliminar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/quitar.png"))); // NOI18N
        rdbrEliminar.setText("ELIMINAR");
        rdbrEliminar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrEliminarActionPerformed(evt);
            }
        });

        rdbrDetalle.setBackground(new java.awt.Color(47, 110, 164));
        rdbrDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrDetalle.setText("CONSULTA");
        rdbrDetalle.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrDetalleActionPerformed(evt);
            }
        });

        rsbrSalir.setBackground(new java.awt.Color(0, 0, 0));
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 18)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane4))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbrModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbrEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbrDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado de locaciones registradas", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    public void inicializar(){
        
        limpiarCampos();
        
    }
    
    public void limpiarCampos(){
        
        tfNombreLocacion.setText("");
        tfUbicacionLocacion.setText("");
        cbCategoriaLocacion.setSelectedIndex(0);
        tfObservacion.setText("");
        tfNombreLocacion.requestFocus();
        
    }

    public static void mostrarLocaciones(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Locacion locacion = new Locacion();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = locacion.listarLocaciones(buscar);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tLocaciones.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }

    public static void ocultarColumnas() {

        /*tLocaciones.getColumnModel().getColumn(0).setMaxWidth(0);
        tLocaciones.getColumnModel().getColumn(0).setMinWidth(0);
        tLocaciones.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tLocaciones.getColumnModel().getColumn(1).setMaxWidth(0);
        tLocaciones.getColumnModel().getColumn(1).setMinWidth(0);
        tLocaciones.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tLocaciones.getColumnModel().getColumn(2).setMaxWidth(0);
        tLocaciones.getColumnModel().getColumn(2).setMinWidth(0);
        tLocaciones.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tLocaciones.getColumnModel().getColumn(3).setMaxWidth(0);
        tLocaciones.getColumnModel().getColumn(3).setMinWidth(0);
        tLocaciones.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tLocaciones.getColumnModel().getColumn(4).setMaxWidth(0);
        tLocaciones.getColumnModel().getColumn(4).setMinWidth(0);
        tLocaciones.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tLocaciones.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tLocaciones.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tLocaciones.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        
        ((DefaultTableCellRenderer) tLocaciones.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarLocaciones(tfBusquedaPorNombre.getText());
        ocultarColumnas();

    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tLocacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLocacionesMouseClicked

        fila = tLocaciones.rowAtPoint(evt.getPoint());
        codigoLocacion = Integer.parseInt(tLocaciones.getValueAt(fila, 0).toString());

    }//GEN-LAST:event_tLocacionesMouseClicked

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        // se cancela el registro de la locacion y se cierra el formulario
        JOptionPane.showMessageDialog(null, "Se ha cancelado el registro de la locacion.", "REGISTRO DE LOCACION", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed
        
        Boolean informacionPersonal = (tfNombreLocacion.getText().length() == 0 || tfUbicacionLocacion.getText().length() == 0 || cbCategoriaLocacion.getSelectedItem()=="SELECCIONAR");
        
        //el ingreso del nombre del productor es obligatorio para el registro del mismo
        if (informacionPersonal) {

            JOptionPane.showMessageDialog(null, "La informacion de la locacion se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE LOCACION", JOptionPane.ERROR_MESSAGE);
            tfNombreLocacion.requestFocus();
            return;

        }

        Locacion locacion = new Locacion(tfNombreLocacion.getText().toUpperCase(), tfUbicacionLocacion.getText().toUpperCase(), tfObservacion.getText().toUpperCase(), cbCategoriaLocacion.getSelectedItem().toString());

        if (locacion.registrarLocacion(locacion)) {

            JOptionPane.showMessageDialog(null, "La locacion ha sido registrada exitosamente.");
            limpiarCampos();
            //esto es para que en la otra pestaña ya se vea la nueva locacion cargada
            mostrarLocaciones("");
            ocultarColumnas();

        } else {

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar la locacion.");

        }
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void cbCategoriaLocacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaLocacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaLocacionActionPerformed

    private void rdbrModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrModificarActionPerformed

        // MODIFICACION DE LOS DATOS DEL PRODUCTOR
        if (fila == -1) {

            JOptionPane.showMessageDialog(null, "Por favor seleccione la locacion cuyos datos desea modificar.", "MODIFICACION DE LOCACION", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            
            FrmModificacionLocacion form = new FrmModificacionLocacion();

            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();

            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            form.setClosable(true);
            form.setIconifiable(false);

            //llevo el codigo de la locacion seleccionada
            form.codigoLocacion = Integer.parseInt(tLocaciones.getValueAt(fila, 0).toString());
            form.tfNombreLocacion.setText(tLocaciones.getValueAt(fila, 1).toString());
            form.tfUbicacionLocacion.setText(tLocaciones.getValueAt(fila, 2).toString());
            form.cbCategoriaLocacion.setSelectedItem(tLocaciones.getValueAt(fila, 4));
            form.tfObservacion.setText(tLocaciones.getValueAt(fila, 3).toString());
            
        }
        
    }//GEN-LAST:event_rdbrModificarActionPerformed

    private void rdbrEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrEliminarActionPerformed

        // ELIMINACION DE LOCACION
        
        /*DEBERIA CHEQUEAR QUE LA LOCACION NO FIGURE CON STOCK DE MIEL POSITIVO*/

        if (fila == -1) {

            JOptionPane.showMessageDialog(null, "Por favor seleccione la locacion que desea dar de baja.", "BAJA DE LOCACION", JOptionPane.INFORMATION_MESSAGE);

        }
        else{

            if(JOptionPane.showConfirmDialog(null, "Seguro desea dar de baja la locacion?", "BAJA DE LOCACION", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE) == 0){

                Locacion locacion = new Locacion();

                if(locacion.eliminarLocacion(Integer.parseInt(tLocaciones.getValueAt(fila, 0).toString()))){

                    mostrarLocaciones("");
                    ocultarColumnas();
                    JOptionPane.showMessageDialog(null, "La locacion ha sido dada de baja exitosamente.", "BAJA DE LOCACION", JOptionPane.INFORMATION_MESSAGE);
                    tLocaciones.requestFocus();

                }
                else{

                    JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar dar de baja la locacion.", "BAJA DE LOCACION", JOptionPane.ERROR_MESSAGE);

                }

            }
            else{

                JOptionPane.showMessageDialog(null, "Se ha cancelado la eliminacion de la locacion.", "BAJA DE LOCACION", JOptionPane.INFORMATION_MESSAGE);

            }

            //btnActualizar.doClick();

        }

    }//GEN-LAST:event_rdbrEliminarActionPerformed

    private void rdbrDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrDetalleActionPerformed

        // CONSULTA DETALLADA DE PRODUCTOR
        JOptionPane.showMessageDialog(null, "CONSULTA DETALLADA DE LOCACION - EN CONSTRUCCION");

    }//GEN-LAST:event_rdbrDetalleActionPerformed

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_rsbrSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbCategoriaLocacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rdbrDetalle;
    private rojeru_san.RSButtonRiple rdbrEliminar;
    private rojeru_san.RSButtonRiple rdbrModificar;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tLocaciones;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfNombreLocacion;
    public javax.swing.JTextField tfObservacion;
    public javax.swing.JTextField tfUbicacionLocacion;
    // End of variables declaration//GEN-END:variables
}
