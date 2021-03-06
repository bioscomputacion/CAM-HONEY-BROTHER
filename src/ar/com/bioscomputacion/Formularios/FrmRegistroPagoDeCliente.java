/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.CtaCteCliente;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.PagoCliente;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmRegistroPagoDeCliente extends javax.swing.JInternalFrame {

    static int codigoMovimientoCtaCte, codigoCliente, codigoComprobanteAfectadoPago, codigoMovimientoCtaCteComprobanteAfectado;
    static String tipoComprobanteAfectadoPago, numeroComprobanteAfectadoPago;
    static Double debeComprobante, haberComprobante;
    
    Double saldoImpago, pagoIngresado, saldoPendiente;
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroPagoDeCliente() {
        
        initComponents();
        inicializar();
        
    }
    
    public void inicializar(){
        
        //se almacena en la variable codigoMovimientoCtaCte
        //el numero de mov que tendra en el a cta. cte el pago a registrar
        CtaCteCliente ctacteCliente = new CtaCteCliente();
        codigoMovimientoCtaCte = ctacteCliente.mostrarIdMovimiento(codigoCliente)+1;
        
        tfMontoPago.setText("0.00");

        cbMetodosPago.setSelectedIndex(0);
        Calendar cal = new GregorianCalendar();
        dcFechaPago.setCalendar(cal);
        dcFechaPago.requestFocus();
        
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
        jLabel9 = new javax.swing.JLabel();
        dcFechaPago = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        tfCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbMetodosPago = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        tfObservaciones = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfImporteTotalComprobante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        tfMontoPago = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfSaldoImpagoComprobante = new javax.swing.JTextField();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        jLabel23 = new javax.swing.JLabel();
        tfSaldoPendiente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE PAGO DE CLIENTE - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL PAGO A REALIZAR:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("FECHA DE PAGO:");

        dcFechaPago.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaPago.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaPago.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SE ESTA registrando UN PAGO CORRESPONDIENTE A:");

        tfCliente.setEditable(false);
        tfCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfCliente.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("METODO DE PAGO:");

        cbMetodosPago.setBackground(new java.awt.Color(36, 33, 33));
        cbMetodosPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbMetodosPago.setForeground(new java.awt.Color(207, 207, 207));
        cbMetodosPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CHEQUE", "DEBITO", "EFECTIVO", "TRANSFERENCIA" }));
        cbMetodosPago.setPreferredSize(new java.awt.Dimension(136, 19));
        cbMetodosPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodosPagoActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("OBSERVACIONES:");

        tfObservaciones.setBackground(new java.awt.Color(51, 84, 111));
        tfObservaciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfObservaciones.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("IMPORTE DEL COMPROBANTE A PAGAR:                                                    $ ");

        tfImporteTotalComprobante.setEditable(false);
        tfImporteTotalComprobante.setBackground(new java.awt.Color(255, 255, 204));
        tfImporteTotalComprobante.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DATOS DEL PAGO:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("IMPORTES Y SALDOS:");

        tfMontoPago.setBackground(new java.awt.Color(51, 84, 111));
        tfMontoPago.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfMontoPago.setForeground(new java.awt.Color(255, 255, 255));
        tfMontoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfMontoPagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfMontoPagoKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("MONTO DEL PAGO A EFECTUAR:                                                                  $");

        jLabel22.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("SALDO IMPAGO DEL COMPROBANTE:                                                          $");

        tfSaldoImpagoComprobante.setEditable(false);
        tfSaldoImpagoComprobante.setBackground(new java.awt.Color(255, 0, 51));
        tfSaldoImpagoComprobante.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfSaldoImpagoComprobante.setForeground(new java.awt.Color(255, 255, 255));

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("SALIR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR PAGO");
        rdbrRegistrar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrarActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("SALDO PENDIENTE DE PAGO:                                                                        $");

        tfSaldoPendiente.setEditable(false);
        tfSaldoPendiente.setBackground(new java.awt.Color(255, 0, 51));
        tfSaldoPendiente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfSaldoPendiente.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCliente)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(dcFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMetodosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfObservaciones)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMontoPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(7, 7, 7)
                .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(dcFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMetodosPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMontoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE)
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

    private void cbMetodosPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodosPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMetodosPagoActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //chequeo de datos correctos y completos
        boolean informacionIncompleta = (cbMetodosPago.getSelectedItem().equals("SELECCIONAR") || tfObservaciones.getText().length() == 0 || tfMontoPago.getText().length() == 0);
        Double pagoRealizado = Double.valueOf(tfMontoPago.getText().toString());
        Double saldoPendiente = Double.valueOf(tfSaldoPendiente.getText().toString());
        
        if (informacionIncompleta){
            
            JOptionPane.showMessageDialog(null, "La informacion correspondiente al pago se halla incompleta. Ingrese la misma correctamente.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            cbMetodosPago.requestFocus();
            return;
            
        }
        
        if (pagoRealizado <= 0){
            
            JOptionPane.showMessageDialog(null, "No se puede registrar un pago con cantidad igual o menor a cero. Por favor ingrese el monto a pagar correctamente.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tfMontoPago.requestFocus();
            return;
            
        }
        
        //se esta intentando dar de alta un pago mayor a la deuda
        if (saldoPendiente < 0){
            
            JOptionPane.showMessageDialog(null, "No se puede registrar un pago con cantidad mayor al saldo adeudado del comprobante. Por favor ingrese el monto a pagar correctamente.", "REGISTRO DE PAGO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tfMontoPago.requestFocus();
            return;
            
        }
        
        //SE REGISTRA PRIMERO EL PAGO EN LA TABLA PAGO_PRODUCTOR
        //se necesita conocer el numero de movimiento con el que se registra el pago
        
        //obtenido en el metodo inicializar()
        
        //obtengo las fechas de factura y de vencimiento del pago de la misma
        Calendar cal;
        int d, m, a;
        cal = dcFechaPago.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        
        //obtengo metodo de pago y observacion ingresada
        String metodoPago = cbMetodosPago.getSelectedItem().toString();
        String observacion = tfObservaciones.getText().toString();
        
        //monto de pago obtenido al comienzo de este metodo y almacenado en la variable metodoPago
        //codigo del comprobante afectado en este pago almacenado en la variable 
        
        //1) se registra el pago
        PagoCliente pago = new PagoCliente(codigoMovimientoCtaCte, codigoCliente, new Date(a, m, d), metodoPago, observacion, pagoRealizado, codigoComprobanteAfectadoPago, tipoComprobanteAfectadoPago);
        pago.registrarPagoCliente(pago);
        

        //obtengo codigo de pago y saldo del comprobante afectado por el pago
        //para guardarlo en la tabla cta. cte.
        int codigoPago = pago.mostrarIdPagoACliente();
        double saldoComprobanteAfectado = Double.parseDouble(tfSaldoImpagoComprobante.getText().toString());
        
        //2) se registra el movimiento asociado al pago en la cta. cte. con el productor 
        
        String comprobanteAsociadoPago = "";
        if (tipoComprobanteAfectadoPago.equals("FACTURA")){
            
            comprobanteAsociadoPago = "FACT. N?? "+numeroComprobanteAfectadoPago;
        
        }
        else{
            
            comprobanteAsociadoPago = "PRES. N?? "+numeroComprobanteAfectadoPago;
        
        }
        
        CtaCteCliente ctacte = new CtaCteCliente(codigoCliente, codigoMovimientoCtaCte, new Date(a, m, d), "PAGO", codigoPago, comprobanteAsociadoPago, 0.00, 0.00, pagoRealizado, 0.00, "CANCELADO", "");
        ctacte.registrarMovimientoCtaCteCliente(ctacte);
        
        //3) se modifica el saldo del comprobante afectado por el pago
        ctacte.actualizarSaldoComprobanteCliente(codigoMovimientoCtaCteComprobanteAfectado, codigoCliente, debeComprobante, pagoRealizado, haberComprobante);
        
        FrmCtaCteCliente.mostrarCtaCteCliente(codigoCliente);
        FrmCtaCteCliente.ocultarColumnasCtaCte();

        this.dispose();
        
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void tfMontoPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMontoPagoKeyReleased

        /*if (txtPorcentajeGanancias.getText().length() != 0) {
            calcularPrecio(Double.valueOf(txtPrecioCosto.getText()));
        }*/
        
        //a medida que se ingresan numeros ir actualizando el saldo que quedaria impago una vez hecho el pago
        //Double saldoImpago, pagoIngresado, saldoPendiente;
        if (tfMontoPago.getText().length() != 0){

            saldoImpago = Double.parseDouble(tfSaldoImpagoComprobante.getText());
            pagoIngresado = Double.parseDouble(tfMontoPago.getText());
            saldoPendiente = saldoImpago - pagoIngresado;
            tfSaldoPendiente.setText(String.valueOf(saldoPendiente));

        }
        else{
            
            tfMontoPago.setText("0.00");
            
        }

    }//GEN-LAST:event_tfMontoPagoKeyReleased

    private void tfMontoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMontoPagoKeyTyped
        
        char c = evt.getKeyChar();

        if (tfMontoPago.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
        
    }//GEN-LAST:event_tfMontoPagoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbMetodosPago;
    public com.toedter.calendar.JDateChooser dcFechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTextField tfCliente;
    public javax.swing.JTextField tfImporteTotalComprobante;
    public javax.swing.JTextField tfMontoPago;
    public javax.swing.JTextField tfObservaciones;
    public javax.swing.JTextField tfSaldoImpagoComprobante;
    public javax.swing.JTextField tfSaldoPendiente;
    // End of variables declaration//GEN-END:variables
}
