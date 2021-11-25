/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import ar.com.bioscomputacion.Funciones.Traslado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmRegistroTraslado extends javax.swing.JInternalFrame {

    public List<Locacion> locaciones = new ArrayList<>();
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroTraslado() throws SQLException {
        
        initComponents();
        //mostrarProductores("");
        //ocultarColumnasProductores();
        //ocultarColumnasItemsFacturados();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        Calendar cal = new GregorianCalendar();
        dcFechaTraslado.setCalendar(cal);
        
        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        ArrayList<String> locaciones= new ArrayList<String>();
        locaciones = cargarComboLocaciones();
        
        for (int i = 0; i<locaciones.size(); i++){
            
            cbLocacionOrigen.addItem(locaciones.get(i));
            cbLocacionDestino.addItem(locaciones.get(i));
            
        }
        
        cbLocacionOrigen.setSelectedIndex(0);
        cbLocacionDestino.setSelectedIndex(0);
        dcFechaTraslado.requestFocus();
        
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
        jLabel6 = new javax.swing.JLabel();
        dcFechaTraslado = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        cbMotivoTraslado = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbDescripcionItem = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        tfCantidad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbLocacionOrigen = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbLocacionDestino = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setTitle("REGISTRO DE TRASLADO DE MIEL - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL TRASLADO:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DEL TRASLADO:");

        dcFechaTraslado.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaTraslado.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaTraslado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEM TRASLADADO:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        cbMotivoTraslado.setBackground(new java.awt.Color(36, 33, 33));
        cbMotivoTraslado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbMotivoTraslado.setForeground(new java.awt.Color(207, 207, 207));
        cbMotivoTraslado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "TRASLADO DE MIEL ENTRE LOCACIONES DE LA EMPRESA", "TRASLADO DE MIEL A HOMOGENEIZACION", "TRASLADO DE MIEL A FISCALIZACION", "TRASLADO DE MIEL A EMBARQUE" }));
        cbMotivoTraslado.setPreferredSize(new java.awt.Dimension(136, 19));
        cbMotivoTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMotivoTrasladoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("MOTIVO DEL TRASLADO:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DESCRIPCION:");

        cbDescripcionItem.setBackground(new java.awt.Color(36, 33, 33));
        cbDescripcionItem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbDescripcionItem.setForeground(new java.awt.Color(207, 207, 207));
        cbDescripcionItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "KG. DE MIEL", "TAMBOR DE MIEL X 300 KGS.", "LOTE DE MIEL X 70 TAMBORES ", "LOTE DE MIEL X 71 TAMBORES ", " " }));
        cbDescripcionItem.setPreferredSize(new java.awt.Dimension(136, 19));
        cbDescripcionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescripcionItemActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CANTIDAD:");

        tfCantidad.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidad.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ORIGEN DEL TRASLADO:");

        cbLocacionOrigen.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionOrigen.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionOrigen.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionOrigen.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionOrigenActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("DESTINO DEL TRASLADO:");

        cbLocacionDestino.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionDestino.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionDestino.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionDestino.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionDestinoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ORIGEN Y DESTINO DE LA CARGA TRASLADADA:");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR TRASLADO");
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

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFechaTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(cbMotivoTraslado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(tfCantidad)
                                .addContainerGap())))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel16)
                                    .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel21))
                .addGap(7, 7, 7)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFechaTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMotivoTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cbMotivoTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMotivoTrasladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMotivoTrasladoActionPerformed

    private void cbDescripcionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDescripcionItemActionPerformed

    private void cbLocacionOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLocacionOrigenActionPerformed

    private void cbLocacionDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLocacionDestinoActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura

        Boolean informacionTraslado = (cbMotivoTraslado.getSelectedItem() == "SELECCIONAR" || cbDescripcionItem.getSelectedItem() == "SELECCIONAR" || tfCantidad.getText().length() == 0 || cbLocacionOrigen.getSelectedItem() == "SELECCIONAR" || cbLocacionDestino.getSelectedItem() == "SELECCIONAR");
        

        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionTraslado) {

            if (JOptionPane.showConfirmDialog(null, "La informacion correspondiente al traslado se halla incompleta. ¿Desea ingresar la misma?",
                "REGISTRO DE TRASLADO DE MIEL", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            //no tengo claro que hacer aca!
            dcFechaTraslado.requestFocus();

            }
            else{

                // deberia cancelarse el registro de la factura!

            }

        }
        else{

            //obtengo las fechas de factura y de vencimiento del pago de la misma
            Calendar cal1;
            int d1, m1, a1;
            cal1 = dcFechaTraslado.getCalendar();
            //ffecha de la factura
            d1 = cal1.get(Calendar.DAY_OF_MONTH);
            m1 = cal1.get(Calendar.MONTH);
            a1 = cal1.get(Calendar.YEAR) - 1900;
            
            String motivoTraslado = cbMotivoTraslado.getSelectedItem().toString();
            String descripcionItem = cbDescripcionItem.getSelectedItem().toString();
            //int origenTraslado = cbLocacionOrigen.getSelectedIndex()-1;
            //int destinoTraslado = cbLocacionDestino.getSelectedIndex()-1;
            //quitar esto luego de las pruebas
            //solucionando el tema del codigo de la locacion seleccionada en el combo lo demas anda perfecto
            int origenTraslado = 11;
            int destinoTraslado = 12;
            Double cantidadItem = Double.parseDouble(tfCantidad.getText().toString());
            Date fechaTraslado = new Date(a1, m1, d1);
            
            System.out.println(origenTraslado);
            System.out.println(destinoTraslado);
                    
            //se procede al registro del traslado
            Traslado traslado = new Traslado(descripcionItem, cantidadItem, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);
            traslado.registrarTrasladoMiel(traslado);
            //obtengo el codigo del traslado recien dado de alta para almacenarlo como comprobante asociado
            //en la tabla stock real de miel
            int codigoTraslado = traslado.mostrarIdTraslado();

            System.out.println(codigoTraslado);
            
            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, PUDIENDO VARIAR O NO EL STOCK GLOBAL
            //LO QUE SI DEBE VARIAR ES EL STOCK EN CADA UNA DE LAS LOCACIONES INVOLUCRADAS EN EL TRASLADO:
            //DEBE DESCONTARSE EL STOCK TRASLADADO DE LA LOCACION ORIGEN Y DEBE INCREMENTARSE EL STOCK TRASLADADO
            //EN LA LOCACION DESTINO
            //EL STOCK GLOBAL VA A VARIAR CUANDO LA LOCACION ORIGEN SEA FISCALIZACION Y LA LOCACION DESTINO SEA EMBARQUE
            //(EN ESTE CASO SE DEBE DISMINUIR EL STOCK GLOBAL YA QUE ES MIEL VENDIDA)
            //CASO CONTRARIO, SE MUEVEN LOS STOCKS EN LAS LOCACIONES PERO SIN TOCARSE EL STOCK GLOBAL
            
            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(fechaTraslado);
            //Cuando se trata de un traslado puede ser traslado origen o traslado destino
            stockMiel.setTipo_movimiento("TRASLADO - ORIGEN");
            stockMiel.setComprobante_asociado(codigoTraslado);
            stockMiel.setCantidad_miel(cantidadItem);
            stockMiel.setLocacion_miel(origenTraslado);
            stockMiel.setObservacion("");

            stockMiel.registrarMovimientoStock(stockMiel);

            this.dispose();

        }
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "CANCELAR EL TRASLADO???!!!!");
        this.dispose();
    }//GEN-LAST:event_rsbrCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
    public javax.swing.JComboBox<String> cbLocacionDestino;
    public javax.swing.JComboBox<String> cbLocacionOrigen;
    public javax.swing.JComboBox<String> cbMotivoTraslado;
    public com.toedter.calendar.JDateChooser dcFechaTraslado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTextField tfCantidad;
    // End of variables declaration//GEN-END:variables
}
