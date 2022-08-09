/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmRegistroPagoAProductor extends javax.swing.JInternalFrame {

    static int codigoMovimientoCtaCte, codigoProductor, codigoComprobanteAfectadoPago, codigoMovimientoCtaCteComprobanteAfectado;
    static String tipoComprobanteAfectadoPago, numeroComprobanteAfectadoPago;
    static Double debeComprobante, haberComprobante, totalkilosFacturados, totalKilosImpagos, totalKilosIngresadosPago, precioUnitario;
    
    Double importePago, saldoPendiente, saldoImpago;
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroPagoAProductor() {
        
        initComponents();
        inicializar();
        
    }
    
    public void inicializar(){
        
        //Por defecto vamos a empezar asumiendo que se desean abonar todos los kilos impagos
        //en el comprobante a pagarse, o sea, se desea abonar todo el saldo impago del comprobante
        importePago = saldoImpago;
        
        //aca se guardan los kilos que falta facturar
        //en las dos otras variables ya se guardaron kilos facturados y kilos
        //totalKilosIngresadosPago = totalkilosFacturados - totalKilosImpagos;
        
        //se almacena en la variable codigoMovimientoCtaCte
        //el numero de mov que tendra en el a cta. cte el pago a registrar
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;
        
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
        jLabel24 = new javax.swing.JLabel();
        tfKilosFacturados = new javax.swing.JTextField();
        tfKilosAPagar = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tfKilosImpagos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE PAGO A PRODUCTOR - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL PAGO A REALIZAR:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("* FECHA DE PAGO:");

        dcFechaPago.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaPago.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaPago.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SE ESTA REALIZANDO UN PAGO CORRESPONDIENTE A:");

        tfCliente.setEditable(false);
        tfCliente.setBackground(new java.awt.Color(0, 0, 0));
        tfCliente.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("* METODO DE PAGO:");

        cbMetodosPago.setBackground(new java.awt.Color(255, 255, 0));
        cbMetodosPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbMetodosPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CHEQUE", "DEBITO", "EFECTIVO", "TRANSFERENCIA" }));
        cbMetodosPago.setPreferredSize(new java.awt.Dimension(136, 19));
        cbMetodosPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodosPagoActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("* OBSERVACION:");

        tfObservaciones.setBackground(new java.awt.Color(51, 84, 111));
        tfObservaciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfObservaciones.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("IMPORTE DEL COMPROBANTE:");

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

        tfMontoPago.setEditable(false);
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
        jLabel21.setText("* MONTO DEL PAGO:");

        jLabel22.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("SALDO IMPAGO:");

        tfSaldoImpagoComprobante.setEditable(false);
        tfSaldoImpagoComprobante.setBackground(new java.awt.Color(0, 0, 0));
        tfSaldoImpagoComprobante.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfSaldoImpagoComprobante.setForeground(new java.awt.Color(255, 255, 255));

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("CANCELAR");
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
        jLabel23.setText("SALDO PENDIENTE:");

        tfSaldoPendiente.setEditable(false);
        tfSaldoPendiente.setBackground(new java.awt.Color(255, 0, 51));
        tfSaldoPendiente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfSaldoPendiente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("KGS. FACTURADOS:");

        tfKilosFacturados.setEditable(false);
        tfKilosFacturados.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosFacturados.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tfKilosAPagar.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosAPagar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfKilosAPagar.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosAPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosAPagarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosAPagarKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("PRECIO UNITARIO:");

        tfPrecioUnitario.setEditable(false);
        tfPrecioUnitario.setBackground(new java.awt.Color(255, 255, 204));
        tfPrecioUnitario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("* KGS. A PAGAR:");

        jLabel28.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("LIMITE KGS.:");

        tfKilosImpagos.setEditable(false);
        tfKilosImpagos.setBackground(new java.awt.Color(255, 255, 204));
        tfKilosImpagos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

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
                                .addGap(0, 41, Short.MAX_VALUE))))
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfKilosFacturados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfMontoPago, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(tfKilosAPagar)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfKilosImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMetodosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfKilosImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfImporteTotalComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfKilosFacturados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfKilosAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMontoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSaldoImpagoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
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
        boolean informacionIncompleta = (cbMetodosPago.getSelectedItem().equals("SELECCIONAR") || tfObservaciones.getText().length() == 0);
        
        if (informacionIncompleta){
            
            JOptionPane.showMessageDialog(null, "La informacion correspondiente al pago se halla incompleta. Ingrese la misma correctamente.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            cbMetodosPago.requestFocus();
            return;
            
        }
        
        //en importePago quedo almacenado el monto del pago a realizarse
        if (importePago <= 0){
            
            JOptionPane.showMessageDialog(null, "No se puede registrar un pago con cantidad igual o menor a cero. Por favor ingrese el monto a pagar correctamente.", "REGISTRO DE PAGO A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
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
        PagoProductor pago = new PagoProductor(codigoMovimientoCtaCte, codigoProductor, new Date(a, m, d), metodoPago, observacion, importePago, codigoComprobanteAfectadoPago, tipoComprobanteAfectadoPago);
        pago.registrarPagoProductor(pago);
        

        //obtengo codigo de pago y saldo del comprobante afectado por el pago
        //para guardarlo en la tabla cta. cte.
        int codigoPago = pago.mostrarIdPagoAProductor();
        double saldoComprobanteAfectado = Double.parseDouble(tfSaldoImpagoComprobante.getText().toString());
        
        //2) se registra el movimiento asociado al pago en la cta. cte. con el productor 
        
        String comprobanteAsociadoPago = "";
        if (tipoComprobanteAfectadoPago.equals("FACTURA A")){
            
            comprobanteAsociadoPago = "FACT. A N° "+numeroComprobanteAfectadoPago;
        
        }
        else{
            
            if (tipoComprobanteAfectadoPago.equals("FACTURA B")){
                
                comprobanteAsociadoPago = "FACT. B N° "+numeroComprobanteAfectadoPago;
                
            }
            else{
                
                comprobanteAsociadoPago = "PRES. N° "+numeroComprobanteAfectadoPago;
                
            }
        
        }
        
        CtaCteProductor ctacte = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a, m, d), "PAGO", codigoPago, comprobanteAsociadoPago, 0.00, 0.00, importePago, 0.00, "CANCELADO", "");
        ctacte.registrarMovimientoCtaCteProductor(ctacte);
        
        //3) se modifica el saldo del comprobante afectado por el pago
        ctacte.actualizarSaldoComprobanteProductor(codigoMovimientoCtaCteComprobanteAfectado, codigoProductor, debeComprobante, importePago, haberComprobante);
        
        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();

        this.dispose();
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void tfMontoPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMontoPagoKeyReleased
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

    private void tfKilosAPagarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAPagarKeyReleased

        //CHEQUEOS A REALIZAR:
        
        //1) que no se ingrese vacio
        //2) que no se ingrese una cantidad igual a 0 kgs. a pagar
        //3) que no se ingrese una cantidad de kgs. a pagar superior a la cantidad de kgs. facturados
        //(en realidad este ultimo chequeo debe hacerse de acuerdo al saldo edl comprobante y no al total del comprobante, 
        //ya que podria darse el caso de que el comprobante se encontrara parcialmente cancelado, lo cual significa que no se
        //adeudan todos los kilos en el facturados. Dicho dato lo tengo en la variable totalKilosImpagos).
        
        //cuando los datos sean bien ingresados quedaran almacenados en la variable "totalKilosIngresadosPago"
        //y el control aca lo hago con la variable totalKilosImpagos
        
        Double kilosIngresadosPago = 0.00;
        
        if (tfKilosAPagar.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE PAGO A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfKilosAPagar.setText(String.valueOf(totalKilosImpagos));
            //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
            totalKilosIngresadosPago = totalKilosImpagos;
            importePago = totalKilosIngresadosPago * precioUnitario;
            tfMontoPago.setText(String.valueOf(importePago));
            tfKilosAPagar.requestFocus();
            
        }
        else{
            
            kilosIngresadosPago = Double.valueOf(tfKilosAPagar.getText());
            
            if (kilosIngresadosPago <= 0.00){

                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE PAGO A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfKilosAPagar.setText(String.valueOf(totalKilosImpagos));
                //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                totalKilosIngresadosPago = totalKilosImpagos;
                importePago = totalKilosIngresadosPago * precioUnitario;
                tfMontoPago.setText(String.valueOf(importePago));
                tfKilosAPagar.requestFocus();

            }
            else{

                if (kilosIngresadosPago > totalKilosImpagos){

                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE PAGO A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                    tfKilosAPagar.setText(String.valueOf(totalKilosImpagos));
                    //tengo que traer de alguna manera o calcular el precio unitario del kilo, deberia mostrar el tf???
                    totalKilosIngresadosPago = totalKilosImpagos;
                    importePago = totalKilosIngresadosPago * precioUnitario;
                    tfMontoPago.setText(String.valueOf(importePago));
                    tfKilosAPagar.requestFocus();

                }
                else{

                    totalKilosIngresadosPago = kilosIngresadosPago;
                    importePago = totalKilosIngresadosPago * precioUnitario;
                    tfMontoPago.setText(String.valueOf(importePago));
                    //y tamb modifico el campo que muestra el saldo que va a quedar desp del pago
                    saldoPendiente = saldoImpago - importePago;
                    tfSaldoPendiente.setText(String.valueOf(saldoPendiente));
                    tfKilosAPagar.requestFocus();

                }
                
            }
            
        }
        
    }//GEN-LAST:event_tfKilosAPagarKeyReleased

    private void tfKilosAPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAPagarKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosAPagar.getText().contains(".") && c == '.') {
            
            getToolkit().beep();
            evt.consume();
        } 
        else
            
            if (!Character.isDigit(c)) {
            
                if (c != '.') {
                    getToolkit().beep();
                    evt.consume();
                }
            
        }
        
    }//GEN-LAST:event_tfKilosAPagarKeyTyped


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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    public javax.swing.JTextField tfKilosAPagar;
    public javax.swing.JTextField tfKilosFacturados;
    public javax.swing.JTextField tfKilosImpagos;
    public javax.swing.JTextField tfMontoPago;
    public javax.swing.JTextField tfObservaciones;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfSaldoImpagoComprobante;
    public javax.swing.JTextField tfSaldoPendiente;
    // End of variables declaration//GEN-END:variables
}
