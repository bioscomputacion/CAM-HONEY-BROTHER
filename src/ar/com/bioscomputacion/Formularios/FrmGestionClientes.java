/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.Cliente;
import ar.com.bioscomputacion.Funciones.Productor;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionClientes extends javax.swing.JInternalFrame {

    int fila = -1;
    //private conexion mysql = new conexion(); 
    //private Connection cn = mysql.conectar();
    private String sSQL = ""; //Sentencia SQL
    static String llamadaDesde;
    String codigoProductor;
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionClientes() {
        
        initComponents();
        mostrarClientes("", "TODOS");
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
        rdbrRegistrar3 = new rojeru_san.RSButtonRiple();
        bgClientes = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tClientes = tClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel4 = new javax.swing.JLabel();
        tfBuscarCliente = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        rdbrEliminar = new rojeru_san.RSButtonRiple();
        rsbrSalir = new rojeru_san.RSButtonRiple();
        rdbrModificar = new rojeru_san.RSButtonRiple();
        rdbrDetalle = new rojeru_san.RSButtonRiple();
        lClientes = new javax.swing.JLabel();
        rbTodosLosClientes = new javax.swing.JRadioButton();
        rbExportadoresInternos = new javax.swing.JRadioButton();
        rbClientesStandards = new javax.swing.JRadioButton();
        rsbrActualizar = new rojeru_san.RSButtonRiple();

        rdbrRegistrar3.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar3.setText("VER DETALLE");
        rdbrRegistrar3.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrar3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("GESTION DE CLIENTES - CAM HONEY BROTHERS");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("CLIENTES REGISTRADOS EN EL SISTEMA");

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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUSCAR POR NOMBRE:");

        tfBuscarCliente.setBackground(new java.awt.Color(51, 84, 111));
        tfBuscarCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        tfBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarClienteKeyReleased(evt);
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

        rsbrSalir.setBackground(new java.awt.Color(0, 0, 0));
        rsbrSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Imagenes/btn-cerrar.png"))); // NOI18N
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 16)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
            }
        });

        rdbrModificar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrModificar.setText("EDITAR DATOS");
        rdbrModificar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrModificarActionPerformed(evt);
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

        lClientes.setBackground(new java.awt.Color(255, 255, 255));
        lClientes.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lClientes.setForeground(new java.awt.Color(255, 255, 255));
        lClientes.setText("VER CLIENTES:");

        rbTodosLosClientes.setBackground(new java.awt.Color(255, 255, 255));
        bgClientes.add(rbTodosLosClientes);
        rbTodosLosClientes.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbTodosLosClientes.setForeground(new java.awt.Color(255, 255, 0));
        rbTodosLosClientes.setText("TODOS");
        rbTodosLosClientes.setOpaque(false);
        rbTodosLosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosLosClientesActionPerformed(evt);
            }
        });

        rbExportadoresInternos.setBackground(new java.awt.Color(255, 255, 255));
        bgClientes.add(rbExportadoresInternos);
        rbExportadoresInternos.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbExportadoresInternos.setForeground(new java.awt.Color(255, 255, 0));
        rbExportadoresInternos.setText("EXPORTADORES INTERNOS");
        rbExportadoresInternos.setOpaque(false);
        rbExportadoresInternos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbExportadoresInternosActionPerformed(evt);
            }
        });

        rbClientesStandards.setBackground(new java.awt.Color(255, 255, 255));
        bgClientes.add(rbClientesStandards);
        rbClientesStandards.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbClientesStandards.setForeground(new java.awt.Color(255, 255, 0));
        rbClientesStandards.setText("STANDARDS");
        rbClientesStandards.setOpaque(false);
        rbClientesStandards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbClientesStandardsActionPerformed(evt);
            }
        });

        rsbrActualizar.setBackground(new java.awt.Color(0, 0, 0));
        rsbrActualizar.setText("ACTUALIZAR");
        rsbrActualizar.setToolTipText("");
        rsbrActualizar.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rsbrActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rsbrSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfBuscarCliente))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lClientes)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(rbTodosLosClientes)
                                .addGap(18, 18, 18)
                                .addComponent(rbClientesStandards)
                                .addGap(18, 18, 18)
                                .addComponent(rbExportadoresInternos))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rsbrActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(rdbrModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbrEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbrDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(tfBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbTodosLosClientes)
                        .addComponent(rbExportadoresInternos)
                        .addComponent(rbClientesStandards)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsbrActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

    public void inicializar() {
        
        rdbrModificar.setVisible(true);
        rdbrEliminar.setVisible(true);
        rdbrDetalle.setVisible(true);
        
        rbTodosLosClientes.setSelected(true);
        rbClientesStandards.setSelected(false);
        rbExportadoresInternos.setSelected(false);
        
        tfBuscarCliente.setText("");
        tfBuscarCliente.requestFocus();
        
}

    public static void mostrarClientes(String buscar, String filtro) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo = null;
            //se crea un objeto Fcliente llamado "funcion"
            Cliente cliente = new Cliente();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            
            modelo = cliente.listarClientes(buscar, filtro);
                    
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tClientes.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public static void ocultarColumnas() {

        /*tClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(0).setMinWidth(0);
        tClientes.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tClientes.getColumnModel().getColumn(1).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(1).setMinWidth(0);
        tClientes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tClientes.getColumnModel().getColumn(2).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(2).setMinWidth(0);
        tClientes.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        /*tClientes.getColumnModel().getColumn(3).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(3).setMinWidth(0);
        tClientes.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        tClientes.getColumnModel().getColumn(4).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(4).setMinWidth(0);
        tClientes.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        tClientes.getColumnModel().getColumn(5).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(5).setMinWidth(0);
        tClientes.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        tClientes.getColumnModel().getColumn(6).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(6).setMinWidth(0);
        tClientes.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        /*tClientes.getColumnModel().getColumn(7).setMaxWidth(0);
        tClientes.getColumnModel().getColumn(7).setMinWidth(0);
        tClientes.getColumnModel().getColumn(7).setPreferredWidth(0);*/
        
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
        tClientes.getColumnModel().getColumn(3).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tClientes.getColumnModel().getColumn(7).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked

        fila = tClientes.rowAtPoint(evt.getPoint());
        codigoProductor = tClientes.getValueAt(fila, 0).toString();

    }//GEN-LAST:event_tClientesMouseClicked

    private void rdbrEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrEliminarActionPerformed

        // ELIMINACION DE PRODUCTOR
 
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el cliente que desea dar de baja.", "BAJA DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            if(JOptionPane.showConfirmDialog(null, "Seguro desea dar de baja el cliente?", "BAJA DE CLIENTE", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE) == 0){
                
                Cliente cliente = new Cliente();
                
                if(cliente.eliminar(Integer.parseInt(tClientes.getValueAt(fila, 0).toString()))){
                    
                    JOptionPane.showMessageDialog(null, "El cliente ha sido dado de baja exitosamente.", "BAJA DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                    //para refrescar la grilla y visualizar los cambios
                    
                }
                else{
                    
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al intentardar de baja el cliente.", "BAJA DE CLIENTE", JOptionPane.ERROR_MESSAGE);
                    
                }

                if (rbTodosLosClientes.isSelected()){
                
                    mostrarClientes("", "TODOS");
                    
                }
                else{
                    
                    if (rbClientesStandards.isSelected()){
                        
                        mostrarClientes("", "STANDARDS");
                        
                    }
                    else{
                        
                        mostrarClientes("", "EXPORTADORES");
                        
                    }
                }
                
                ocultarColumnas();
                fila = -1;
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Se ha cancelado la eliminacion del cliente.", "BAJA DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
        
    }//GEN-LAST:event_rdbrEliminarActionPerformed

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed

    private void rdbrModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrModificarActionPerformed

        // MODIFICACION DE LOS DATOS DEL PRODUCTOR
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el cliente cuyos datos desea modificar.", "MODIFICACION DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            FrmModificacionCliente form = new FrmModificacionCliente();

            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();

            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            form.setClosable(true);
            form.setIconifiable(false);
            
            //llevo el codigo del productor seleccionado
            form.codigoCliente = Integer.parseInt(tClientes.getValueAt(fila, 0).toString());
            form.tfNombre.setText(tClientes.getValueAt(fila, 1).toString());
            form.tfDocumento.setText(tClientes.getValueAt(fila, 2).toString());
            
            //combo categoria de cliente
            form.cbCategoriaCliente.setSelectedItem(tClientes.getValueAt(fila, 15));
            
            //los clientes se cargan solo con su nacionalidad, no llevan localidad ni estado o provincia
            if (tClientes.getValueAt(fila, 3).toString().length() == 0)
            {
                
                form.cbNacionalidad.setSelectedIndex(0);
                
            }
            else
            {
                
                form.cbNacionalidad.setSelectedItem(tClientes.getValueAt(fila, 3));
                
            }
            
            form.tfDomicilio.setText(tClientes.getValueAt(fila, 6).toString());
            form.tfTelefono.setText(tClientes.getValueAt(fila, 7).toString());
            form.tfCorreo.setText(tClientes.getValueAt(fila, 8).toString());
            form.tfNombreFantasia.setText(tClientes.getValueAt(fila, 9).toString());
            form.tfRazonSocial.setText(tClientes.getValueAt(fila, 10).toString());
            
            //combos condicion frente al iva
            if (tClientes.getValueAt(fila, 11).toString().length() == 0)
            {
                
                //el productor fue cargado sin una condicion frente al iva
                form.cbCondicionIVA.setSelectedIndex(0);
                
            }
            else{
            
                //el productor fue cargado con una condicion frente al iva
                form.cbCondicionIVA.setSelectedItem(tClientes.getValueAt(fila, 11));
                
            }
            
            form.tfCuit.setText(tClientes.getValueAt(fila, 12).toString());
            form.tfDomicilioFiscal.setText(tClientes.getValueAt(fila, 13).toString());
            
        }

    }//GEN-LAST:event_rdbrModificarActionPerformed

    private void rdbrRegistrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbrRegistrar3ActionPerformed

    private void rdbrDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrDetalleActionPerformed

        // CONSULTA DETALLADA DE CLIENTE
        JOptionPane.showMessageDialog(null, "CONSULTA DETALLADA DE CLIENTE - EN CONSTRUCCION");

    }//GEN-LAST:event_rdbrDetalleActionPerformed

    private void tfBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarClienteKeyReleased

        if (rbTodosLosClientes.isSelected()){

            mostrarClientes(tfBuscarCliente.getText(), "TODOS");

        }
        else{

            if (rbClientesStandards.isSelected()){

                mostrarClientes(tfBuscarCliente.getText(), "STANDARDS");

            }
            else{

                mostrarClientes(tfBuscarCliente.getText(), "EXPORTADORES");

            }
        }
        
        ocultarColumnas();
        
    }//GEN-LAST:event_tfBuscarClienteKeyReleased

    private void rbTodosLosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosLosClientesActionPerformed

        if (rbTodosLosClientes.isSelected()){

            mostrarClientes("", "TODOS");

        }
        else{

            if (rbClientesStandards.isSelected()){
            
                mostrarClientes("", "STANDARDS");
    
            }
            else{
            
                mostrarClientes("", "EXPORTADORES");
                
            }

        }

        ocultarColumnas();

    }//GEN-LAST:event_rbTodosLosClientesActionPerformed

    private void rbExportadoresInternosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbExportadoresInternosActionPerformed

        if (rbTodosLosClientes.isSelected()){

            mostrarClientes("", "TODOS");

        }
        else{

            if (rbClientesStandards.isSelected()){
            
                mostrarClientes("", "STANDARDS");
    
            }
            else{
            
                mostrarClientes("", "EXPORTADORES");
                
            }

        }

        ocultarColumnas();

    }//GEN-LAST:event_rbExportadoresInternosActionPerformed

    private void rbClientesStandardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbClientesStandardsActionPerformed

        if (rbTodosLosClientes.isSelected()){

            mostrarClientes("", "TODOS");

        }
        else{

            if (rbClientesStandards.isSelected()){
            
                mostrarClientes("", "STANDARDS");
    
            }
            else{
            
                mostrarClientes("", "EXPORTADORES");
                
            }

        }

        ocultarColumnas();

    }//GEN-LAST:event_rbClientesStandardsActionPerformed

    private void rsbrActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrActualizarActionPerformed


        if (rbTodosLosClientes.isSelected()){

            mostrarClientes("", "TODOS");

        }
        else{

            if (rbClientesStandards.isSelected()){
            
                mostrarClientes("", "STANDARDS");
    
            }
            else{
            
                mostrarClientes("", "EXPORTADORES");
                
            }

        }

        tfBuscarCliente.setText("");
        ocultarColumnas();

    }//GEN-LAST:event_rsbrActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lClientes;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    public static javax.swing.JRadioButton rbClientesStandards;
    public static javax.swing.JRadioButton rbExportadoresInternos;
    public static javax.swing.JRadioButton rbTodosLosClientes;
    private rojeru_san.RSButtonRiple rdbrDetalle;
    private rojeru_san.RSButtonRiple rdbrEliminar;
    private rojeru_san.RSButtonRiple rdbrModificar;
    private rojeru_san.RSButtonRiple rdbrRegistrar3;
    private rojeru_san.RSButtonRiple rsbrActualizar;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tClientes;
    public javax.swing.JTextField tfBuscarCliente;
    // End of variables declaration//GEN-END:variables
}
