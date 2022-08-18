/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.IngresoMielPropia;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Traslado;
import java.sql.SQLException;

/**
 *
 * @author Caco
 */
public class FrmDetalleMovimientoStock extends javax.swing.JInternalFrame {

    static int codigoComprobanteConsultado;
    static Double cantidadMielAfectada;
    static String referencia, comprobante;
    /**
     * Creates new form FrmGenerico
     */
    public FrmDetalleMovimientoStock() {
        
        initComponents();
        inicializar();
    }

    
    
    public void inicializar(){
        
        //Tipos de movimientos: COMPRA, INGRESO, VENTA, TRASLADO-DESTINO, TRASLADO-ORIGEN
        //es necesario ver que tipo de movimiento es y de que referencia se trata tamb
        
        referencia = lTipoMovimiento.getText();
        comprobante = lComprobanteAsociado.getText();
        
        FacturaProductor factura = new FacturaProductor();
        PresupuestoProductor presupuesto = new PresupuestoProductor();
        CreditoProductor credito = new CreditoProductor();
        IngresoMielPropia ingreso = new IngresoMielPropia();
        Traslado traslado = new Traslado();
        NotaCreditoProductor notaCredito = new NotaCreditoProductor();
        DevolucionProductor devolucion = new DevolucionProductor();
        
        Double importeComprobante = 0.00;
        Double precioUnitarioFacturado = 0.00;
        Double importeAbonado = 0.00;
        Double saldoComprobante = 0.00;
        Double importeAbonadoEnKgs = 0.00; 
        Double saldoComprobanteEnKgs = 0.00;
                                
        switch (referencia){
            
            case "COMPRA":
                
                switch (comprobante){
                    
                    //facturas a, facturas c, presupuestos y consignaciones
                    
                    case "FACTURA A":
                       
                        importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                        lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                        precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                        lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                        lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                        importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                        lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                        saldoComprobante = importeComprobante - importeAbonado;
                        lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                        importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                        lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                        saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                        lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                        break;
                
                    case "FACTURA C":
                       
                        importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                        lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                        precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                        lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                        lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                        importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                        lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                        saldoComprobante = importeComprobante - importeAbonado;
                        lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                        importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                        lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                        saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                        lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                        break;
                
                    case "PRESUPUESTO":
                       
                        //copiar los procedimientos mostrar de la clase factura en las demas clases!!!!
                        
                        importeComprobante = presupuesto.mostrarImportePresupuesto(codigoComprobanteConsultado);
                        lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                        precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                        lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                        lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                        importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                        lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                        saldoComprobante = importeComprobante - importeAbonado;
                        lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                        importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                        lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                        saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                        lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                        break;
                
                    case "CONSIGNACION":
                        
                        break;
                        
                    default:
                
                }
                
                break;
                
            case "INGRESO":
                
                //solo ingresos
                
                break;
                
            case "TRASLADO-DESTINO":
                
                //solo traslados destino
                
                break;
                
            case "VENTA":
                
                //solo facturas e
                
                break;
                
            case "TRASLADO-ORIGEN":
                
                //solo traslados origen
                
                break;
                
            case "DEVOLUCION":
                
                switch (comprobante){
                    
                    //notas de credito y devoluciones
                    
                    case "NOTA DE CREDITO A":
                        
                        break;
                
                    case "NOTA DE CREDITO C":
                        
                        break;
                
                    case "DEVOLUCION":
                        
                        break;
                
                    default:
                
                }
                
                break;
                
            default:
                
            
        }
        
        
        
        /*Double importeComprobante = 0.00;
        Double precioUnitarioFacturado = 0.00;
        Double importeAbonado = 0.00;
        Double importeAbonadoEnKgs = 0.00;
        Double saldoComprobante = 0.00;
        Double saldoComprobanteEnKgs = 0.00;
        FacturaProductor factura = new FacturaProductor();

        //desde aca tendria que ir en el inicializar del form detalle de movimiento de stock
        importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
        lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
        precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
        lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
        lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
        importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
        lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
        saldoComprobante = importeComprobante - importeAbonado;
        lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
        importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
        lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
        saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
        lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");*/

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
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rsbrAceptar = new rojeru_san.RSButtonRiple();
        lTipoMovimiento = new javax.swing.JLabel();
        lComprobanteAsociado = new javax.swing.JLabel();
        lNumeroComprobante = new javax.swing.JLabel();
        lKgsMiel = new javax.swing.JLabel();
        ll = new javax.swing.JLabel();
        lFechaMovimiento = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lImporteComprobante = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lImporteAbonado = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lSaldoComprobante = new javax.swing.JLabel();
        lImporteKgs = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lPrecioUnitario = new javax.swing.JLabel();
        lSaldoKgs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DETALLE DE MOVIMIENTO DE STOCK - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("INFORMACION CORRESPONDIENTE AL MOVIMIENTO DE STOCK:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TIPO DE MOVIMIENTO:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("COMPROBANTE ASOCIADO:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("N°:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 204));
        jLabel8.setText("MIEL AFECTADA:");

        rsbrAceptar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrAceptar.setText("ACEPTAR");
        rsbrAceptar.setToolTipText("");
        rsbrAceptar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrAceptarActionPerformed(evt);
            }
        });

        lTipoMovimiento.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lTipoMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        lTipoMovimiento.setText("-");

        lComprobanteAsociado.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lComprobanteAsociado.setForeground(new java.awt.Color(255, 255, 255));
        lComprobanteAsociado.setText("-");

        lNumeroComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lNumeroComprobante.setText("-");

        lKgsMiel.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lKgsMiel.setForeground(new java.awt.Color(255, 255, 204));
        lKgsMiel.setText("-");
        lKgsMiel.setToolTipText("");

        ll.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ll.setForeground(new java.awt.Color(255, 255, 255));
        ll.setText("FECHA DEL MOVIMIENTO:");

        lFechaMovimiento.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lFechaMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        lFechaMovimiento.setText("-");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("IMPORTE DEL COMPROBANTE:");

        lImporteComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lImporteComprobante.setText("-");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("IMPORTE ABONADO:");

        lImporteAbonado.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteAbonado.setForeground(new java.awt.Color(255, 255, 255));
        lImporteAbonado.setText("-");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 153, 153));
        jLabel10.setText("SALDO DEL COMPROBANTE:");

        lSaldoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoComprobante.setForeground(new java.awt.Color(255, 153, 153));
        lSaldoComprobante.setText("-");

        lImporteKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteKgs.setForeground(new java.awt.Color(255, 255, 255));
        lImporteKgs.setText("-");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PRECIO UNITARIO:");

        lPrecioUnitario.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lPrecioUnitario.setForeground(new java.awt.Color(255, 255, 255));
        lPrecioUnitario.setText("-");

        lSaldoKgs.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoKgs.setForeground(new java.awt.Color(255, 153, 153));
        lSaldoKgs.setText("-");

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lImporteAbonado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lImporteKgs))
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lSaldoComprobante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSaldoKgs))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lNumeroComprobante))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPrecioUnitario))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lKgsMiel)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTipoMovimiento))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lComprobanteAsociado))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(ll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaMovimiento))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lImporteComprobante)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ll)
                    .addComponent(lFechaMovimiento))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lTipoMovimiento))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lComprobanteAsociado))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lNumeroComprobante))
                .addGap(17, 17, 17)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lKgsMiel)
                    .addComponent(jLabel8))
                .addGap(19, 19, 19)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lPrecioUnitario))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lImporteComprobante))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lImporteAbonado)
                        .addComponent(lImporteKgs)))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lSaldoComprobante)
                        .addComponent(lSaldoKgs)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void rsbrAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrAceptarActionPerformed

        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_rsbrAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lComprobanteAsociado;
    public javax.swing.JLabel lFechaMovimiento;
    public javax.swing.JLabel lImporteAbonado;
    public javax.swing.JLabel lImporteComprobante;
    public javax.swing.JLabel lImporteKgs;
    public javax.swing.JLabel lKgsMiel;
    public javax.swing.JLabel lNumeroComprobante;
    public javax.swing.JLabel lPrecioUnitario;
    public static javax.swing.JLabel lSaldoComprobante;
    public javax.swing.JLabel lSaldoKgs;
    public javax.swing.JLabel lTipoMovimiento;
    private javax.swing.JLabel ll;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrAceptar;
    // End of variables declaration//GEN-END:variables
}
