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
import ar.com.bioscomputacion.Funciones.IngresoMielPropia;
import ar.com.bioscomputacion.Funciones.NotaCreditoCliente;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.PagoCliente;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import ar.com.bioscomputacion.Funciones.PresupuestoCliente;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Traslado;
import java.sql.SQLException;

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
        //falta crear las clases correspondientes a ka anulacion (de un presupuesto)
        DevolucionProductor devolucion = new DevolucionProductor();
        Traslado traslado = new Traslado();
        
        Double importeComprobante = 0.00;
        Double precioUnitarioFacturado = 0.00;
        Double importeAbonado = 0.00;
        Double saldoComprobante = 0.00;
        Double importeAbonadoEnKgs = 0.00; 
        Double saldoComprobanteEnKgs = 0.00;
                                
        if (!(tipoComprobante == null)){

            switch (tipoComprobante){

                //facturas a, facturas c, presupuestos y consignaciones

                case "FACTURA A":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "FACTURA C":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "FACTURA E":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "PRESUPUESTO":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "CONSIGNACION":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "INGRESO":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "FACTURACION DE CONSIGNACION":

                    /*importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));*/

                    break;

                case "PAGO":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                case "NOTA DE CREDITO A":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "NOTA DE CREDITO C":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "NOTA DE CREDITO E":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "ANULACION":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                case "DEVOLUCION":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                case "TRASLADO":

                    importeComprobante = factura.mostrarImporteFactura(codigoComprobanteConsultado);
                    lImporteComprobante.setText("$ "+String.valueOf(importeComprobante));
                    precioUnitarioFacturado = factura.mostrarPrecioUnitarioFactura(codigoComprobanteConsultado);
                    lPrecioUnitario.setText("$ "+String.valueOf(precioUnitarioFacturado));
                    lKgsMiel.setText(String.valueOf(cantidadMielAfectada)+" KGS.");
                    lTambores.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 300)*100.00) / 100.00)+" TAMBORES)");
                    lLotes.setText(String.valueOf("("+Math.round((cantidadMielAfectada / 21000)*100.00) / 100.00)+" LOTES)");
                    importeAbonado = factura.mostrarImportePagoFactura("FACTURA A",codigoComprobanteConsultado);
                    lImporteAbonado.setText("$ "+String.valueOf(importeAbonado));
                    saldoComprobante = importeComprobante - importeAbonado;
                    lSaldoComprobante.setText("$ "+String.valueOf(saldoComprobante));
                    importeAbonadoEnKgs = importeAbonado / precioUnitarioFacturado;
                    lImporteKgs.setText("(EQUIVALENTE A "+String.valueOf(importeAbonadoEnKgs)+" KGS.)");
                    saldoComprobanteEnKgs = saldoComprobante / precioUnitarioFacturado;
                    lSaldoKgs.setText("(EQUIVALENTE A "+String.valueOf(saldoComprobanteEnKgs)+" KGS.)");
                    //falta traer el nombre del productor implicado y la locacion donde se deposita o de donde se 
                    //extrae la miel
                    lProductorImplicado.setVisible(true);
                    lProductorImplicado.setText("MIEL FACTURADA POR:");
                    lProductor.setText(factura.mostrarNombreProductorFacturaA(codigoComprobanteConsultado));

                    break;

                default:

                    break;


            }

        }
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
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rsbrAceptar = new rojeru_san.RSButtonRiple();
        lTipoComprobante = new javax.swing.JLabel();
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
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        lProductorImplicado = new javax.swing.JLabel();
        lProductor = new javax.swing.JLabel();
        lTambores = new javax.swing.JLabel();
        lLotes = new javax.swing.JLabel();
        lProductorImplicado1 = new javax.swing.JLabel();
        lLocacion = new javax.swing.JLabel();

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
        jLabel4.setText("TIPO DE COMPROBANTE:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("N° DE COMPROBANTE:");

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
        ll.setText("FECHA DEL MOVIMIENTO:");

        lFechaMovimiento.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lFechaMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        lFechaMovimiento.setText("-");

        jLabel7.setBackground(new java.awt.Color(255, 255, 204));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 255));
        jLabel7.setText("IMPORTE TOTAL:");

        lImporteComprobante.setBackground(new java.awt.Color(255, 255, 204));
        lImporteComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteComprobante.setForeground(new java.awt.Color(204, 255, 255));
        lImporteComprobante.setText("-");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setText("IMPORTE ABONADO:");

        lImporteAbonado.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteAbonado.setForeground(new java.awt.Color(204, 255, 255));
        lImporteAbonado.setText("-");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setText("SALDO DEL COMPROBANTE:");

        lSaldoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoComprobante.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoComprobante.setForeground(new java.awt.Color(204, 255, 255));
        lSaldoComprobante.setText("-");

        lImporteKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lImporteKgs.setForeground(new java.awt.Color(204, 255, 255));
        lImporteKgs.setText("-");

        jLabel11.setBackground(new java.awt.Color(255, 255, 204));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 255));
        jLabel11.setText("PRECIO KG.:");

        lPrecioUnitario.setBackground(new java.awt.Color(255, 255, 204));
        lPrecioUnitario.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lPrecioUnitario.setForeground(new java.awt.Color(204, 255, 255));
        lPrecioUnitario.setText("-");

        lSaldoKgs.setBackground(new java.awt.Color(255, 255, 255));
        lSaldoKgs.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lSaldoKgs.setForeground(new java.awt.Color(204, 255, 255));
        lSaldoKgs.setText("-");

        lProductorImplicado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lProductorImplicado.setForeground(new java.awt.Color(255, 255, 255));
        lProductorImplicado.setText("productorImplicado");

        lProductor.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lProductor.setForeground(new java.awt.Color(255, 255, 255));
        lProductor.setText("-");

        lTambores.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lTambores.setForeground(new java.awt.Color(255, 255, 204));
        lTambores.setText("-");
        lTambores.setToolTipText("");

        lLotes.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lLotes.setForeground(new java.awt.Color(255, 255, 204));
        lLotes.setText("-");
        lLotes.setToolTipText("");

        lProductorImplicado1.setBackground(new java.awt.Color(255, 255, 204));
        lProductorImplicado1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lProductorImplicado1.setForeground(new java.awt.Color(255, 255, 204));
        lProductorImplicado1.setText("locacionImplicada");

        lLocacion.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lLocacion.setForeground(new java.awt.Color(255, 255, 204));
        lLocacion.setText("-");

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addContainerGap())
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lKgsMiel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTambores)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLotes)
                                .addGap(18, 18, 18)
                                .addComponent(lProductorImplicado1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLocacion))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lImporteAbonado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lImporteKgs))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPrecioUnitario)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lImporteComprobante))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSaldoComprobante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSaldoKgs)))
                        .addGap(362, 362, Short.MAX_VALUE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTipoComprobante))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(ll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaMovimiento))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(lProductorImplicado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lProductor)))
                        .addGap(0, 475, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())))
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNumeroComprobante)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lTipoComprobante))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lNumeroComprobante))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lProductorImplicado1)
                        .addComponent(lLocacion))
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lKgsMiel)
                        .addComponent(jLabel8)
                        .addComponent(lTambores)
                        .addComponent(lLotes)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lPrecioUnitario)
                    .addComponent(jLabel7)
                    .addComponent(lImporteComprobante))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lImporteAbonado)
                    .addComponent(lImporteKgs))
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSaldoComprobante)
                    .addComponent(lSaldoKgs)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProductorImplicado)
                    .addComponent(lProductor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    public javax.swing.JLabel lFechaMovimiento;
    public javax.swing.JLabel lImporteAbonado;
    public javax.swing.JLabel lImporteComprobante;
    public javax.swing.JLabel lImporteKgs;
    public javax.swing.JLabel lKgsMiel;
    public javax.swing.JLabel lLocacion;
    public javax.swing.JLabel lLotes;
    public javax.swing.JLabel lNumeroComprobante;
    public javax.swing.JLabel lPrecioUnitario;
    public javax.swing.JLabel lProductor;
    private javax.swing.JLabel lProductorImplicado;
    private javax.swing.JLabel lProductorImplicado1;
    public static javax.swing.JLabel lSaldoComprobante;
    public javax.swing.JLabel lSaldoKgs;
    public javax.swing.JLabel lTambores;
    public javax.swing.JLabel lTipoComprobante;
    private javax.swing.JLabel ll;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrAceptar;
    // End of variables declaration//GEN-END:variables
}
