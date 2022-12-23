/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.AnulacionComprobanteProductor;
import ar.com.bioscomputacion.Funciones.CreditoPresupuestoProductor;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.FacturaCliente;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.IngresoMielPropia;
import ar.com.bioscomputacion.Funciones.NotaCreditoCliente;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.PagoCliente;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import ar.com.bioscomputacion.Funciones.PresupuestoCliente;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Traslado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmDetalleComprobanteConsultado extends javax.swing.JInternalFrame {

    static int codigoComprobanteConsultado, codigolocacionDeposito, codigolocacionDepositoOrigen, codigolocacionDepositoDestino;
    static Double cantidadMielAfectada;
    static String tipoComprobante, comprobante, productorDeposito;
    /**
     * Creates new form FrmGenerico
     */
    public FrmDetalleComprobanteConsultado() {
        
        initComponents();
        inicializar();
        
    }

    
    
    public void inicializar(){
        
        /*
        SELECCIONAR
        FACTURAS A
        FACTURAS C
        FACTURAS E
        PRESUPUESTOS DE PRODUCTORES
        PRESUPUESTOS A CLIENTES
        CONSIGNACIONES
        INGRESOS
        FACTURACIONES DE CONSIGNACIONES
        PAGOS A PRODUCTORES
        PAGOS DE CLIENTES
        NOTAS DE CREDITO A
        NOTAS DE CREDITO C
        NOTAS DE CREDITO E
        ANULACIONES
        DEVOLUCIONES
        TRASLADOS
        */

        
        FacturaProductor factura = new FacturaProductor();
        FacturaCliente facturaCliente = new FacturaCliente();
        PresupuestoProductor presupuesto = new PresupuestoProductor();
        PresupuestoCliente presupuestoCliente = new PresupuestoCliente();
        CreditoProductor credito = new CreditoProductor();
        IngresoMielPropia ingreso = new IngresoMielPropia();
        PagoProductor pagoProductor = new PagoProductor();
        PagoCliente pagoCliente = new PagoCliente();
        NotaCreditoProductor notaCredito = new NotaCreditoProductor();
        NotaCreditoCliente notaCreditoCliente = new NotaCreditoCliente();
        CreditoPresupuestoProductor creditoPresupuestoProductor = new CreditoPresupuestoProductor();
        AnulacionComprobanteProductor anulacion = new AnulacionComprobanteProductor();
        DevolucionProductor devolucion = new DevolucionProductor();
        Traslado traslado = new Traslado();
        
        Double importeComprobante = 0.00;
        Double precioUnitarioFacturado = 0.00;
        Double importeAbonado = 0.00;
        Double importeAbonadoEnKgs = 0.00; 
        Double saldoComprobante = 0.00;
        Double saldoComprobanteEnKgs = 0.00;
        
        int comprobanteAfectado = 0;
        String tipoComprobanteAfectado = "";
        String numeroComprobanteAfectadoPago = "";
        
        String productor = "";
        String cliente = "";
        String observacion = "";
        String tipoComprobanteAnulado = "";
        String locacionAcopio = "";
        String locacionOrigenTraslado = "";
        String locacionDestinoTraslado = "";
        
                                
        if (!(tipoComprobante == null)){

            switch (tipoComprobante){

                case "FACTURA A":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    saldoComprobante = importeComprobante - importeAbonado;
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    locacionAcopio = mostrarNombreLocacionAcopioMiel("FACTURA A", codigoComprobanteConsultado);
                    productor = factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado);

                    lMielAfectada.setText("MIEL ADQUIRIDA EN LA COMPRA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("MIEL FACTURADA POR:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(productor);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("LOCACION DE ACOPIO DE LA MIEL:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(locacionAcopio);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);

                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. FACTURADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DE LA FACTURA:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE PAGO DE LA FACTURA:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(true);
                    lSaldo.setText("SALDO IMPAGO DE LA FACTURA:");
                    lSaldoComprobante.setVisible(true);
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    lSaldoKgs.setVisible(true);
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                    break;

                case "FACTURA C":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA C",codigoComprobanteConsultado);
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    saldoComprobante = importeComprobante - importeAbonado;
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    locacionAcopio = mostrarNombreLocacionAcopioMiel("FACTURA C", codigoComprobanteConsultado);
                    productor = factura.mostrarNombreProductorFacturaC(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL ADQUIRIDA EN LA COMPRA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("MIEL FACTURADA POR:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(productor);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("LOCACION DE ACOPIO DE LA MIEL:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(locacionAcopio);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);

                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. FACTURADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DE LA FACTURA:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE PAGO DE LA FACTURA:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(true);
                    lSaldo.setText("SALDO IMPAGO DE LA FACTURA:");
                    lSaldoComprobante.setVisible(true);
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    lSaldoKgs.setVisible(true);
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                    break;

                case "FACTURA E":

                    JOptionPane.showMessageDialog(rootPane, "EN DESARROLLO");
                    break;

                case "PRESUPUESTO":

                    importeComprobante = presupuesto.mostrarImportePresupuesto(codigoComprobanteConsultado);
                    precioUnitarioFacturado = presupuesto.mostrarPrecioUnitarioPresupuesto(codigoComprobanteConsultado);
                    importeAbonado = presupuesto.mostrarImportePagoPresupuesto(codigoComprobanteConsultado);
                    saldoComprobante = importeComprobante - importeAbonado;
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    locacionAcopio = mostrarNombreLocacionAcopioMiel("PRESUPUESTO", codigoComprobanteConsultado);
                    productor = presupuesto.mostrarNombreProductorPresupuesto(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL ADQUIRIDA EN LA COMPRA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("MIEL PRESUPUESTADA POR:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(productor);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("LOCACION DE ACOPIO DE LA MIEL:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(locacionAcopio);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);

                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. PRESUPUESTADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DEL PRESUPUESTO:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE PAGO DEL PRESUPUESTO:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(true);
                    lSaldo.setText("SALDO IMPAGO DEL PRESUPUESTO:");
                    lSaldoComprobante.setVisible(true);
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    lSaldoKgs.setVisible(true);
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");

                    break;

                case "CONSIGNACION":

                    locacionAcopio = mostrarNombreLocacionAcopioMiel("CONSIGNACION", codigoComprobanteConsultado);
                    productor = credito.mostrarNombreProductorCredito(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL ADQUIRIDA EN LA COMPRA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lObjeto1.setVisible(true);
                    lObjeto1.setText("MIEL CONSIGNADA POR:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(productor);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("LOCACION DE ACOPIO DE LA MIEL:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(locacionAcopio);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);

                    lPrecioKG.setVisible(false);
                    lPrecioUnitario.setVisible(false);
                    lImporte.setVisible(false);
                    lImporteComprobante.setVisible(false);
                    lImporteDescontado.setVisible(false);
                    lImporteAbonado.setVisible(false);
                    lImporteAbonadoKgs.setVisible(false);
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);

                    //FALTARIA MOSTRAR LOS KILOS DESCONTADOS DE LA CONSIGNACION, YA SEA POR FACTURACION DE MIEL PARA ABONARLA
                    //O POR DEVOLUCION DE MIEL DE LA CONSIGNACION

                    break;

                case "INGRESO":

                    locacionAcopio = mostrarNombreLocacionAcopioMiel("INGRESO", codigoComprobanteConsultado);
                    observacion = ingreso.mostrarObservacionIngreso(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL INGRESADA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION DE INGRESO Y ACOPIO DE LA MIEL:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(locacionAcopio);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("OBSERVACION:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(observacion);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);
                    
                    lPrecioKG.setVisible(false);
                    lPrecioUnitario.setVisible(false);
                    lImporte.setVisible(false);
                    lImporteComprobante.setVisible(false);
                    lImporteDescontado.setVisible(false);
                    lImporteAbonado.setVisible(false);
                    lImporteAbonadoKgs.setVisible(false);
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);

                    break;

                case "FACTURACION DE CONSIGNACION":

                    JOptionPane.showMessageDialog(rootPane, "EN DESARROLLO");
                    break;

                case "PAGO":
                    
                    //ADEMAS DE LOS DATOS DEL PAGO TENEMOS QUE MOSTRAR LOS DEL COMPROBANTE AFECTADO POR EL PAGO
                    productor = pagoProductor.mostrarNombreProductorPago(codigoComprobanteConsultado);
                    comprobanteAfectado = pagoProductor.mostrarCodigoComprobanteAfectadoPago(codigoComprobanteConsultado);
                    tipoComprobanteAfectado = pagoProductor.mostrarTipoComprobanteAfectadoPago(codigoComprobanteConsultado);
                    numeroComprobanteAfectadoPago = "";
                    //es solo el numero del pago a nivel interno en la bd
                    lNumeroComprobante.setText(String.valueOf(codigoComprobanteConsultado));
                    if (tipoComprobanteAfectado.equals("PRESUPUESTO")){
                        
                        //es un pago de un presupuesto
                        numeroComprobanteAfectadoPago = pagoProductor.mostrarNumeroPresupuestoAfectadoPago(codigoComprobanteConsultado);
                        precioUnitarioFacturado = presupuesto.mostrarPrecioUnitarioPresupuesto(comprobanteAfectado);
                        importeComprobante = presupuesto.mostrarImportePresupuesto(comprobanteAfectado);
                        
                        
                    }
                    else{
                        
                        //es un pago de una factura
                        numeroComprobanteAfectadoPago = pagoProductor.mostrarNumeroFacturaAfectadoPago(codigoComprobanteConsultado);
                        precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(comprobanteAfectado);
                        importeComprobante = factura.mostrarImporteFactura(comprobanteAfectado);
                        
                    }
                    
                    //obtengo importe del comprobante, precio unitario facturado, importe del pago y saldo del comprobante afectado
                    importeAbonado = pagoProductor.mostrarImportePago(codigoComprobanteConsultado);
                    saldoComprobante = importeComprobante - importeAbonado;
                    
                    lMielAfectada.setText("MONTO ABONADO EQUIVALENTE A:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lObjeto1.setVisible(true);
                    lObjeto1.setText("PAGO REALIZADO A:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(productor);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("COMPROBANTE AFECTADO POR EL PAGO:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(tipoComprobanteAfectado+" N° "+numeroComprobanteAfectadoPago);
                    //en estos dos objetos que siguen colocar datos de la factura! (o no)
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);

                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. FACTURADO / PRESUPUESTADO EN EL COMPROBANTE ABONADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DEL COMPROBANTE ABONADO:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE ABONADO EN EL PAGO");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);
                    //HAY QUE HACER PARA pagos de clienteS!
                    
                    break;

                case "NOTA DE CREDITO A":

                    //ADEMAS DE LOS DATOS DELA NC TENEMOS QUE MOSTRAR LOS DEL COMPROBANTE AFECTADO POR LA MISMA
                    //obtengo codigo, tipo y numero del comprobante afectado por la nc
                    productor = notaCredito.mostrarNombreProductorNotaCredito(codigoComprobanteConsultado);
                    comprobanteAfectado = notaCredito.mostrarCodigoComprobanteAfectadoNC(codigoComprobanteConsultado);
                    numeroComprobanteAfectadoPago = "FACTURA A N° "+notaCredito.mostrarNumeroFacturaAfectadaNC(codigoComprobanteConsultado);

                    importeAbonado = notaCredito.mostrarImporteNotaCredito(codigoComprobanteConsultado);
                    //es un pago de un presupuesto
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(comprobanteAfectado);
                    importeComprobante = factura.mostrarImporteFactura(comprobanteAfectado);
                    saldoComprobante = importeComprobante - importeAbonado;
                    
                    lMielAfectada.setText("CANTIDAD DE MIEL DEVUELTA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lMielAfectada.setVisible(true);
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION DE ACOPIO DE LA MIEL DEVUELTA:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(mostrarNombreLocacionAcopioMiel("NOTA DE CREDITO A", codigoComprobanteConsultado));
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("MIEL DEVUELTA A:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(productor);
                    lObjeto3.setVisible(true);
                    lObjeto3.setText("COMPROBANTE ASOCIADO A LA NOTA DE CREDITO A:");
                    lObjeto6.setVisible(true);
                    lObjeto6.setText(numeroComprobanteAfectadoPago);
                    
                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. ACREDITADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DEL COMPROBANTE AFECTADO POR LA NOTA DE CREDITO:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE ACREDITADO:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);
                    //HAY QUE HACER PARA NCA de clienteS!
                    
                    break;

                case "NOTA DE CREDITO C":

                    //ADEMAS DE LOS DATOS DELA NC TENEMOS QUE MOSTRAR LOS DEL COMPROBANTE AFECTADO POR LA MISMA
                    //obtengo codigo, tipo y numero del comprobante afectado por la nc
                    productor = notaCredito.mostrarNombreProductorNotaCredito(codigoComprobanteConsultado);
                    comprobanteAfectado = notaCredito.mostrarCodigoComprobanteAfectadoNC(codigoComprobanteConsultado);
                    numeroComprobanteAfectadoPago = "FACTURA C N° "+notaCredito.mostrarNumeroFacturaAfectadaNC(codigoComprobanteConsultado);

                    importeAbonado = notaCredito.mostrarImporteNotaCredito(codigoComprobanteConsultado);
                    //es un pago de un presupuesto
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(comprobanteAfectado);
                    importeComprobante = factura.mostrarImporteFactura(comprobanteAfectado);
                    saldoComprobante = importeComprobante - importeAbonado;
                    
                    lMielAfectada.setText("CANTIDAD DE MIEL DEVUELTA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lMielAfectada.setVisible(true);
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION DE ACOPIO DE LA MIEL DEVUELTA:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(mostrarNombreLocacionAcopioMiel("NOTA DE CREDITO C", codigoComprobanteConsultado));
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("MIEL DEVUELTA A:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(productor);
                    lObjeto3.setVisible(true);
                    lObjeto3.setText("COMPROBANTE ASOCIADO A LA NOTA DE CREDITO C:");
                    lObjeto6.setVisible(true);
                    lObjeto6.setText(numeroComprobanteAfectadoPago);
                    
                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. ACREDITADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DEL COMPROBANTE AFECTADO POR LA NOTA DE CREDITO:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE ACREDITADO:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);
                    //HAY QUE HACER PARA NCA de clienteS!
                    
                    break;

                case "NOTA DE CREDITO E":

                    /*importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("NOTA DE CREDITO E",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION DE ACOPIO DE LA MIEL DEVUELTA:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(mostrarNombreLocacionAcopioMiel("NOTA DE CREDITO A", codigoComprobanteConsultado));
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("MIEL DEVUELTA A:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(notaCredito.mostrarNombreProductorNotaCredito(codigoComprobanteConsultado));
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);*/

                    break;

                case "CREDITO DE PRESUPUESTO":

                    //ADEMAS DE LOS DATOS DEL CREDITO TENEMOS QUE MOSTRAR LOS DEL COMPROBANTE AFECTADO POR EL MISMO
                    //obtengo codigo, tipo y numero del comprobante afectado por el credito
                    productor = creditoPresupuestoProductor.mostrarNombreProductorCreditoPresupuestoProductor(codigoComprobanteConsultado);
                    comprobanteAfectado = creditoPresupuestoProductor.mostrarCodigoComprobanteAfectadoCreditoPresupuesto(codigoComprobanteConsultado);
                    numeroComprobanteAfectadoPago = "PRESUPUESTO N° "+creditoPresupuestoProductor.mostrarNumeroPrespuestoAfectadoCreditoPresupuesto(codigoComprobanteConsultado);

                    importeAbonado = creditoPresupuestoProductor.mostrarImporteCreditoPresupuestoProductor(codigoComprobanteConsultado);
                    //es un pago de un presupuesto
                    precioUnitarioFacturado = presupuesto.mostrarPrecioUnitarioPresupuesto(comprobanteAfectado);
                    importeComprobante = presupuesto.mostrarImportePresupuesto(comprobanteAfectado);
                    saldoComprobante = importeComprobante - importeAbonado;
                    
                    lMielAfectada.setText("CANTIDAD DE MIEL DEVUELTA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lMielAfectada.setVisible(true);
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    
                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION DE ACOPIO DE LA MIEL DEVUELTA:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(mostrarNombreLocacionAcopioMiel("CREDITO DE PRESUPUESTO", codigoComprobanteConsultado));
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("MIEL DEVUELTA A:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(productor);
                    lObjeto3.setVisible(true);
                    lObjeto3.setText("COMPROBANTE ASOCIADO AL CREDITO DE PRESUPUESTO:");
                    lObjeto6.setVisible(true);
                    lObjeto6.setText(numeroComprobanteAfectadoPago);
                    
                    lPrecioKG.setVisible(true);
                    lPrecioKG.setText("PRECIO KG. ACREDITADO:");
                    lPrecioUnitario.setVisible(true);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lImporte.setVisible(true);
                    lImporte.setText("IMPORTE TOTAL DEL COMPROBANTE AFECTADO POR EL CREDITO:");
                    lImporteComprobante.setVisible(true);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    lImporteDescontado.setVisible(true);
                    lImporteDescontado.setText("IMPORTE ACREDITADO:");
                    lImporteAbonado.setVisible(true);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    lImporteAbonadoKgs.setVisible(true);
                    lImporteAbonadoKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);
                    //HAY QUE HACER PARA NCA de clienteS!
                    
                    break;

                case "DEVOLUCION DE CONSIGNACION":

                    JOptionPane.showMessageDialog(rootPane, "EN DESARROLLO");
                    break;
                    
                case "TRASLADO":

                    locacionOrigenTraslado = traslado.mostrarNombreLocacionOrigenTraslado(codigoComprobanteConsultado);
                    locacionDestinoTraslado = traslado.mostrarNombreLocacionDestinoTraslado(codigoComprobanteConsultado);
                    observacion = traslado.mostrarObservacionTraslado(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL TRASLADADA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(true);
                    lLotes.setVisible(true);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");

                    lObjeto1.setVisible(true);
                    lObjeto1.setText("LOCACION ORIGEN DE LA MIEL TRASLADADA:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(locacionOrigenTraslado);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("LOCACION DESTINO PARA LA MIEL TRASLADADA::");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(locacionDestinoTraslado);
                    lObjeto3.setVisible(true);
                    lObjeto3.setText("OBSERVACION:");
                    lObjeto6.setVisible(true);
                    lObjeto6.setText(observacion);
                    
                    lPrecioKG.setVisible(false);
                    lPrecioUnitario.setVisible(false);
                    lImporte.setVisible(false);
                    lImporteComprobante.setVisible(false);
                    lImporteDescontado.setVisible(false);
                    lImporteAbonado.setVisible(false);
                    lImporteAbonadoKgs.setVisible(false);
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);

                    break;

                case "ANULACION DE COMPROBANTE":

                    tipoComprobanteAnulado = anulacion.mostrarTipoComprobanteAnulacionComprobante(codigoComprobanteConsultado);
                    numeroComprobanteAfectadoPago = anulacion.mostrarNumeroComprobanteAnulacionComprobante(codigoComprobanteConsultado);
                    locacionAcopio = mostrarNombreLocacionAcopioMiel("INGRESO", codigoComprobanteConsultado);
                    observacion = anulacion.mostrarObservacionAnulacionComprobante(codigoComprobanteConsultado);
                    
                    lMielAfectada.setText("MIEL AFECTADA:");
                    lKgsMiel.setVisible(true);
                    lTambores.setVisible(false);
                    lLotes.setVisible(false);
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");

                    lObjeto1.setVisible(true);
                    lObjeto1.setText("COMPROBANTE ANULADO:");
                    lObjeto4.setVisible(true);
                    lObjeto4.setText(tipoComprobanteAnulado);
                    lObjeto2.setVisible(true);
                    lObjeto2.setText("N° COMPROBANTE:");
                    lObjeto5.setVisible(true);
                    lObjeto5.setText(numeroComprobanteAfectadoPago);
                    lObjeto3.setVisible(false);
                    lObjeto6.setVisible(false);
                    
                    /*lPrecioKG.setVisible(false);
                    lPrecioUnitario.setVisible(false);
                    lImporte.setVisible(false);
                    lImporteComprobante.setVisible(false);
                    lImporteDescontado.setVisible(false);
                    lImporteAbonado.setVisible(false);
                    lImporteAbonadoKgs.setVisible(false);
                    lSaldo.setVisible(false);
                    lSaldoComprobante.setVisible(false);
                    lSaldoKgs.setVisible(false);*/

                    break;

                default:

                    break;

            }

        }
        
    }

    public String mostrarNombreLocacionAcopioMiel(String referencia, int id_comprobante) {
        
        String nombreLocacion = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select l.nombre_locacion from stock_real_miel s join locacion l on s.locacion_miel = l.codigo_locacion where s.id_comprobante_asociado = '"+id_comprobante+"' and s.comprobante_asociado = '"+referencia+"'");

            while (rs.next()){
            
                nombreLocacion = rs.getString("nombre_locacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return nombreLocacion;
            
        }
        
        return nombreLocacion;
    
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lMielAfectada = new javax.swing.JLabel();
        rsbrAceptar = new rojeru_san.RSButtonRiple();
        lTipoComprobante = new javax.swing.JLabel();
        lNumeroComprobante = new javax.swing.JLabel();
        lKgsMiel = new javax.swing.JLabel();
        ll = new javax.swing.JLabel();
        lFechaMovimiento = new javax.swing.JLabel();
        lImporte = new javax.swing.JLabel();
        lImporteComprobante = new javax.swing.JLabel();
        lImporteDescontado = new javax.swing.JLabel();
        lImporteAbonado = new javax.swing.JLabel();
        lSaldo = new javax.swing.JLabel();
        lSaldoComprobante = new javax.swing.JLabel();
        lImporteAbonadoKgs = new javax.swing.JLabel();
        lPrecioKG = new javax.swing.JLabel();
        lPrecioUnitario = new javax.swing.JLabel();
        lSaldoKgs = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lObjeto3 = new javax.swing.JLabel();
        lTambores = new javax.swing.JLabel();
        lLotes = new javax.swing.JLabel();
        lObjeto1 = new javax.swing.JLabel();
        lObjeto2 = new javax.swing.JLabel();
        lObjeto4 = new javax.swing.JLabel();
        lObjeto5 = new javax.swing.JLabel();
        lObjeto6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("DETALLE DE COMPROBANTE - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("INFORMACION CORRESPONDIENTE AL COMPROBANTE:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("COMPROBANTE:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("N°:");

        lMielAfectada.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lMielAfectada.setForeground(new java.awt.Color(255, 255, 204));
        lMielAfectada.setText("MIEL AFECTADA:");

        rsbrAceptar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrAceptar.setText("ACEPTAR");
        rsbrAceptar.setToolTipText("");
        rsbrAceptar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrAceptarActionPerformed(evt);
            }
        });

        lTipoComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lTipoComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lTipoComprobante.setText("-");

        lNumeroComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lNumeroComprobante.setText("-");

        lKgsMiel.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lKgsMiel.setForeground(new java.awt.Color(255, 255, 204));
        lKgsMiel.setText("-");
        lKgsMiel.setToolTipText("");

        ll.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ll.setForeground(new java.awt.Color(255, 255, 255));
        ll.setText("FECHA:");

        lFechaMovimiento.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lFechaMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        lFechaMovimiento.setText("-");

        lImporte.setBackground(new java.awt.Color(255, 255, 204));
        lImporte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lImporte.setForeground(new java.awt.Color(204, 255, 255));
        lImporte.setText("IMPORTE TOTAL DEL COMPROBANTE:");

        lImporteComprobante.setBackground(new java.awt.Color(255, 255, 204));
        lImporteComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteComprobante.setForeground(new java.awt.Color(204, 255, 255));
        lImporteComprobante.setText("-");

        lImporteDescontado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lImporteDescontado.setForeground(new java.awt.Color(204, 255, 255));
        lImporteDescontado.setText("IMPORTE ABONADO:");

        lImporteAbonado.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteAbonado.setForeground(new java.awt.Color(204, 255, 255));
        lImporteAbonado.setText("-");

        lSaldo.setBackground(new java.awt.Color(255, 255, 255));
        lSaldo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lSaldo.setForeground(new java.awt.Color(204, 255, 255));
        lSaldo.setText("SALDO IMPAGO DEL COMPROBANTE:");

        lSaldoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoComprobante.setForeground(new java.awt.Color(204, 255, 255));
        lSaldoComprobante.setText("-");

        lImporteAbonadoKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteAbonadoKgs.setForeground(new java.awt.Color(204, 255, 255));
        lImporteAbonadoKgs.setText("-");

        lPrecioKG.setBackground(new java.awt.Color(255, 255, 204));
        lPrecioKG.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lPrecioKG.setForeground(new java.awt.Color(204, 255, 255));
        lPrecioKG.setText("PRECIO KG.:");

        lPrecioUnitario.setBackground(new java.awt.Color(255, 255, 204));
        lPrecioUnitario.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lPrecioUnitario.setForeground(new java.awt.Color(204, 255, 255));
        lPrecioUnitario.setText("-");

        lSaldoKgs.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoKgs.setForeground(new java.awt.Color(204, 255, 255));
        lSaldoKgs.setText("-");

        lObjeto3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lObjeto3.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto3.setText("objeto3");

        lTambores.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lTambores.setForeground(new java.awt.Color(255, 255, 204));
        lTambores.setText("-");
        lTambores.setToolTipText("");

        lLotes.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lLotes.setForeground(new java.awt.Color(255, 255, 204));
        lLotes.setText("-");
        lLotes.setToolTipText("");

        lObjeto1.setBackground(new java.awt.Color(255, 255, 204));
        lObjeto1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lObjeto1.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto1.setText("objeto1");

        lObjeto2.setBackground(new java.awt.Color(255, 255, 204));
        lObjeto2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lObjeto2.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto2.setText("objeto2");

        lObjeto4.setBackground(new java.awt.Color(255, 255, 204));
        lObjeto4.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lObjeto4.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto4.setText("objeto4");

        lObjeto5.setBackground(new java.awt.Color(255, 255, 204));
        lObjeto5.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lObjeto5.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto5.setText("objeto5");

        lObjeto6.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lObjeto6.setForeground(new java.awt.Color(255, 255, 204));
        lObjeto6.setText("objeto6");

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
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(ll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lFechaMovimiento)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTipoComprobante))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNumeroComprobante)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lMielAfectada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lKgsMiel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTambores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lLotes))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lObjeto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lObjeto4))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lObjeto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lObjeto5))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lObjeto3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lObjeto6))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lImporteDescontado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lImporteAbonado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lImporteAbonadoKgs))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lSaldoComprobante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lSaldoKgs))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lPrecioKG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lPrecioUnitario))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lImporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lImporteComprobante)))
                .addContainerGap(385, Short.MAX_VALUE))
            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ll)
                    .addComponent(lFechaMovimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lTipoComprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lNumeroComprobante))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lKgsMiel)
                    .addComponent(lMielAfectada)
                    .addComponent(lTambores)
                    .addComponent(lLotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lObjeto1)
                    .addComponent(lObjeto4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lObjeto2)
                    .addComponent(lObjeto5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lObjeto3)
                    .addComponent(lObjeto6))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPrecioKG)
                    .addComponent(lPrecioUnitario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lImporte)
                    .addComponent(lImporteComprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lImporteDescontado)
                    .addComponent(lImporteAbonado)
                    .addComponent(lImporteAbonadoKgs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSaldoComprobante)
                    .addComponent(lSaldoKgs)
                    .addComponent(lSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(451, Short.MAX_VALUE)))
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JLabel lFechaMovimiento;
    private javax.swing.JLabel lImporte;
    public javax.swing.JLabel lImporteAbonado;
    public javax.swing.JLabel lImporteAbonadoKgs;
    public javax.swing.JLabel lImporteComprobante;
    private javax.swing.JLabel lImporteDescontado;
    public static javax.swing.JLabel lKgsMiel;
    public static javax.swing.JLabel lLotes;
    private javax.swing.JLabel lMielAfectada;
    public static javax.swing.JLabel lNumeroComprobante;
    private javax.swing.JLabel lObjeto1;
    private javax.swing.JLabel lObjeto2;
    private javax.swing.JLabel lObjeto3;
    private javax.swing.JLabel lObjeto4;
    private javax.swing.JLabel lObjeto5;
    private javax.swing.JLabel lObjeto6;
    private javax.swing.JLabel lPrecioKG;
    public javax.swing.JLabel lPrecioUnitario;
    private javax.swing.JLabel lSaldo;
    public static javax.swing.JLabel lSaldoComprobante;
    public javax.swing.JLabel lSaldoKgs;
    public static javax.swing.JLabel lTambores;
    public static javax.swing.JLabel lTipoComprobante;
    private javax.swing.JLabel ll;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrAceptar;
    // End of variables declaration//GEN-END:variables
}
