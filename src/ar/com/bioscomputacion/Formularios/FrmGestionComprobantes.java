/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.AjusteCompensacionStock;
import ar.com.bioscomputacion.Funciones.AnulacionComprobanteProductor;
import ar.com.bioscomputacion.Funciones.CreditoPresupuestoProductor;
import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.CtaCteCliente;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.FacturaCliente;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.IngresoMielPropia;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.NotaCreditoCliente;
import ar.com.bioscomputacion.Funciones.NotaCreditoProductor;
import ar.com.bioscomputacion.Funciones.PagoCliente;
import ar.com.bioscomputacion.Funciones.PagoProductor;
import ar.com.bioscomputacion.Funciones.PresupuestoCliente;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import ar.com.bioscomputacion.Funciones.Traslado;
import java.awt.Dimension;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionComprobantes extends javax.swing.JInternalFrame {

    int fila = -1, codigoComprobante, fechaInicial, fechaFinal;
    //para saber todo el tiempo que tipo de comprobantes se selecciono visualizar
    String tipoComprobante, tipoConsulta, mesConsulta, añoConsulta;

    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionComprobantes() {
        
        initComponents();
        inicializar();
        
    }

    public void inicializar() {
        
        cargarCombosFechas();
        cbTipoComprobante.setSelectedIndex(0);
        cbTipoConsulta.setSelectedIndex(0);
        cbTipoComprobante.requestFocus();
        
    }

    public void cargarCombosFechas() {
        
        LocalDate fecha = LocalDate.now();
        int año = fecha.getYear();
        int mes = fecha.getMonthValue();
        
        cbAñoConsulta.addItem("SELECCIONAR");
        for (int i = 2022; i<=año; i++){

            cbAñoConsulta.addItem(Integer.toString(i));

        }   
        
        cbAñoConsulta.setSelectedItem(String.valueOf(año));
        cbMesConsulta.setSelectedIndex(mes);

    }
    
    public void mostrarComprobantes(String tipoComprobante, String tipoConsulta, String añoConsulta, String mesConsulta) {
        
            /*
            SELECCIONAR
            FACTURAS A listo 
            FACTURAS C listo
            FACTURAS E listo
            PRESUPUESTOS DE PRODUCTORES listo
            PRESUPUESTOS A CLIENTES listo
            CONSIGNACIONES listo
            INGRESOS listo
            FACTURACIONES DE CONSIGNACIONES falta terminar de implementar!
            PAGOS A PRODUCTORES listo
            PAGOS DE CLIENTES listo
            NOTAS DE CREDITO A listo
            NOTAS DE CREDITO C listo
            NOTAS DE CREDITO E listo
            ANULACIONES DE COMPROBANTES listo
            DEVOLUCIONES DE CONSIGNACIONES falta terminar de implementar!
            TRASLADOS listo
            */
            
            //PRIMERO SE ARMA LA FECHA DE CONSULTA, UTILIZANDO EL AÑO Y EL MES, OBTENIENDO EL PRIMER
            //Y EL ULTIMO DIA DEL MES PASADO COMO ARGUMENTO
            LocalDate fechaInicialConsulta = LocalDate.parse(añoConsulta+"-"+mesConsulta+"-01");
            String ultimoDiaMes = String.valueOf(fechaInicialConsulta.lengthOfMonth());
            LocalDate fechaFinalConsulta = LocalDate.parse(añoConsulta+"-"+mesConsulta+"-"+ultimoDiaMes);
            
            //ahora hay que ver si se trata de una consulta de comprobantes de productores
            //o una consulta de comprobantes de clientes
            
            if (tipoConsulta.equals("cliente")){
                
                //consulta de comprobantes de clientes
                switch (tipoComprobante){
                    
                    case "SELECCIONAR":

                        tComprobantes.removeAll();
                        break;

                    default:
                        
                        DefaultTableModel modelo;

                        switch (tipoComprobante){

                            case "FACTURAS A":

                                FacturaCliente facturaA = new FacturaCliente();
                                //modelo = facturaA.listarFacturasA("");
                                //tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "FACTURAS C":

                                FacturaCliente facturaC = new FacturaCliente();
                                //modelo = facturaC.listarFacturasC("");
                                //tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "FACTURAS E":

                                FacturaCliente facturaE = new FacturaCliente();
                                modelo = facturaE.listarFacturasE("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "PRESUPUESTOS A CLIENTES":

                                PresupuestoCliente presupuestoCliente = new PresupuestoCliente();
                                modelo = presupuestoCliente.listarPresupuestosAClientes("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "PAGOS DE CLIENTES":

                                PagoCliente pagoCliente = new PagoCliente();
                                modelo = pagoCliente.listarPagosAClientes("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesPagos();
                                break;

                            case "NOTAS DE CREDITO A":

                                NotaCreditoCliente notaCreditoA = new NotaCreditoCliente();
                                modelo = notaCreditoA.listarNotasCreditoE("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "NOTAS DE CREDITO C":

                                NotaCreditoCliente notaCreditoC = new NotaCreditoCliente();
                                modelo = notaCreditoC.listarNotasCreditoE("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "NOTAS DE CREDITO E":

                                NotaCreditoCliente notaCreditoE = new NotaCreditoCliente();
                                modelo = notaCreditoE.listarNotasCreditoE("");
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            default:

                                break;

                        }
                        
                }
                
            }
            else{
                
                //consulta de comprobantes de productores
                switch (tipoComprobante){
                    
                    case "SELECCIONAR":

                        tComprobantes.removeAll();
                        break;

                    default:
                        
                        DefaultTableModel modelo;

                        switch (tipoComprobante){

                            case "FACTURA A":

                                FacturaProductor facturaA = new FacturaProductor();
                                modelo = facturaA.listarFacturasA(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "FACTURA C":

                                FacturaProductor facturaC = new FacturaProductor();
                                modelo = facturaC.listarFacturasC(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "PRESUPUESTO":

                                PresupuestoProductor presupuestoProductor = new PresupuestoProductor();
                                modelo = presupuestoProductor.listarPresupuestosDeProductores(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "CONSIGNACION":

                                CreditoProductor consignacion = new CreditoProductor();
                                modelo = consignacion.listarConsignacionesDeProductores(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesConsignaciones();
                                break;

                            case "INGRESO":

                                IngresoMielPropia ingreso = new IngresoMielPropia();
                                modelo = ingreso.listarIngresosMiel(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesIngresos();
                                break;

                            case "FACTURACION DE CONSIGNACION":

                                JOptionPane.showMessageDialog(rootPane, "EN DESARROLLO");
                                break;

                            case "PAGO":

                                PagoProductor pagoProductor = new PagoProductor();
                                modelo = pagoProductor.listarPagosAProductores(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesPagos();
                                break;

                            case "NOTA DE CREDITO A":

                                NotaCreditoProductor notaCreditoA = new NotaCreditoProductor();
                                modelo = notaCreditoA.listarNotasCreditoA(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "NOTA DE CREDITO C":

                                NotaCreditoProductor notaCreditoC = new NotaCreditoProductor();
                                modelo = notaCreditoC.listarNotasCreditoC(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "CREDITO DE PRESUPUESTO":

                                CreditoPresupuestoProductor credito = new CreditoPresupuestoProductor();
                                modelo = credito.listarCreditosPresupuestosProductores(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesComprasYVentas();
                                break;

                            case "DEVOLUCION DE CONSIGNACION":

                                JOptionPane.showMessageDialog(rootPane, "EN DESARROLLO");
                                break;

                            case "TRASLADO":

                                Traslado traslado = new Traslado();
                                modelo = traslado.listarTraslados(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesTraslados();
                                break;

                            case "ANULACION DE COMPROBANTE":

                                AnulacionComprobanteProductor anulacion = new AnulacionComprobanteProductor();
                                modelo = anulacion.listarAnulacionesDeComprobantes(fechaInicialConsulta, fechaFinalConsulta);
                                tComprobantes.setModel(modelo);
                                ocultarColumnasComprobantesAnulaciones();
                                break;

                            default:

                                break;

                        }
                
                    }
                
                }
            
    }
    
    public void ocultarColumnasComprobantesComprasYVentas() {

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

    public void ocultarColumnasComprobantesConsignaciones() {

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

    public void ocultarColumnasComprobantesPagos() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);

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
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(3).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(6).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tComprobantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public void ocultarColumnasComprobantesIngresos() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);*/

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(2).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(3).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tComprobantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public void ocultarColumnasComprobantesTraslados() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(6).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(6).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(6).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(7).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(7).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(7).setPreferredWidth(0);

        tComprobantes.getColumnModel().getColumn(8).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(8).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(8).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(1).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(2).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tComprobantes.getColumnModel().getColumn(3).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(4).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tComprobantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    public void ocultarColumnasComprobantesAnulaciones() {

        tComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);

        /*tComprobantes.getColumnModel().getColumn(1).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(2).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        /*tComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);*/

        tComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
        tComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);

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
        tComprobantes.getColumnModel().getColumn(3).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tComprobantes.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tComprobantes.getColumnModel().getColumn(6).setCellRenderer(cellRender4);   
        
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
        jButton8 = new javax.swing.JButton();
        bConsultarComprobante = new javax.swing.JButton();
        bReImprimirComprobante = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        rsbrSalir = new rojeru_san.RSButtonRiple();
        tbOpciones1 = new javax.swing.JToolBar();
        bEliminarComprobante = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbAñoConsulta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbMesConsulta = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbTipoConsulta = new javax.swing.JComboBox<>();

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
        cbTipoComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURAS A", "FACTURAS C", "FACTURAS E", "PRESUPUESTOS", "CONSIGNACIONES", "INGRESOS", "FACTURACIONES DE CONSIGNACIONES", "PAGOS", "NOTAS DE CREDITO A", "NOTAS DE CREDITO C", "NOTAS DE CREDITO E", "CREDITOS DE PRESUPUESTOS", "DEVOLUCIONES", "TRASLADOS", "ANULACIONES DE COMPROBANTES" }));
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

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("  VER COMPROBANTES ");
        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton8);

        bConsultarComprobante.setBackground(new java.awt.Color(0, 0, 0));
        bConsultarComprobante.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bConsultarComprobante.setForeground(new java.awt.Color(255, 255, 255));
        bConsultarComprobante.setText("  CONSULTA DETALLADA  ");
        bConsultarComprobante.setBorderPainted(false);
        bConsultarComprobante.setFocusable(false);
        bConsultarComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bConsultarComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bConsultarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultarComprobanteActionPerformed(evt);
            }
        });
        tbOpciones.add(bConsultarComprobante);

        bReImprimirComprobante.setBackground(new java.awt.Color(0, 0, 0));
        bReImprimirComprobante.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bReImprimirComprobante.setForeground(new java.awt.Color(255, 255, 255));
        bReImprimirComprobante.setText("  RE IMPRIMIR COMPROBANTE  ");
        bReImprimirComprobante.setBorderPainted(false);
        bReImprimirComprobante.setFocusable(false);
        bReImprimirComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bReImprimirComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bReImprimirComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReImprimirComprobanteActionPerformed(evt);
            }
        });
        tbOpciones.add(bReImprimirComprobante);

        jButton9.setBackground(new java.awt.Color(0, 0, 0));
        jButton9.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("  MODIFICAR COMPROBANTE  ");
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        tbOpciones.add(jButton9);

        rsbrSalir.setBackground(new java.awt.Color(0, 0, 0));
        rsbrSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Imagenes/btn-cerrar.png"))); // NOI18N
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 16)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
            }
        });

        tbOpciones1.setBackground(new java.awt.Color(0, 0, 0));
        tbOpciones1.setBorder(null);
        tbOpciones1.setFloatable(false);
        tbOpciones1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        bEliminarComprobante.setBackground(new java.awt.Color(0, 0, 0));
        bEliminarComprobante.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bEliminarComprobante.setForeground(new java.awt.Color(255, 255, 255));
        bEliminarComprobante.setText("  ANULAR COMPROBANTE  ");
        bEliminarComprobante.setBorderPainted(false);
        bEliminarComprobante.setFocusable(false);
        bEliminarComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEliminarComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bEliminarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarComprobanteActionPerformed(evt);
            }
        });
        tbOpciones1.add(bEliminarComprobante);

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("  ACTUALIZAR DATOS ");
        jButton7.setBorderPainted(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        tbOpciones1.add(jButton7);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("AÑO:");

        cbAñoConsulta.setBackground(new java.awt.Color(255, 255, 0));
        cbAñoConsulta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbAñoConsulta.setPreferredSize(new java.awt.Dimension(136, 19));
        cbAñoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAñoConsultaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MES:");

        cbMesConsulta.setBackground(new java.awt.Color(255, 255, 0));
        cbMesConsulta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbMesConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        cbMesConsulta.setPreferredSize(new java.awt.Dimension(136, 19));
        cbMesConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMesConsultaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DE/A:");

        cbTipoConsulta.setBackground(new java.awt.Color(255, 255, 0));
        cbTipoConsulta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CLIENTES", "PRODUCTORES" }));
        cbTipoConsulta.setPreferredSize(new java.awt.Dimension(136, 19));
        cbTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rsbrSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tbOpciones1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(150, 150, 150))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbTipoConsulta, 0, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbAñoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMesConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(29, 29, 29)))
                                    .addComponent(cbAñoConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(29, 29, 29)))
                            .addComponent(cbTipoConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMesConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
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

        //almaceno comprobante seleccionado y codigo de comprobante seleccionado en la grilla
        fila = tComprobantes.rowAtPoint(evt.getPoint());
        codigoComprobante = Integer.valueOf(tComprobantes.getValueAt(fila, 0).toString());
        
    }//GEN-LAST:event_tComprobantesMouseClicked

    private void cbTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoComprobanteActionPerformed
        
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
        */

        String comprobante = cbTipoComprobante.getSelectedItem().toString();
        
        switch (comprobante){

            case "SELECCIONAR":

                tipoComprobante = "SELECCIONAR";
                break;

            case "FACTURAS A":

                tipoComprobante = "FACTURA A";
                break;

            case "FACTURAS C":

                tipoComprobante = "FACTURA C";
                break;

            case "FACTURAS E":

                tipoComprobante = "FACTURA E";
                break;

            case "PRESUPUESTOS":

                tipoComprobante = "PRESUPUESTO";
                break;

            case "CONSIGNACIONES":

                tipoComprobante = "CONSIGNACION";
                break;

            case "INGRESOS":

                tipoComprobante = "INGRESO";
                break;

            case "FACTURACIONES DE CONSIGNACIONES":

                tipoComprobante = "FACTURACION DE CONSIGNACION";
                break;

            case "PAGOS":

                tipoComprobante = "PAGO";
                break;

            case "NOTAS DE CREDITO A":

                tipoComprobante = "NOTA DE CREDITO A";
                break;

            case "NOTAS DE CREDITO C":

                tipoComprobante = "NOTA DE CREDITO C";
                break;

            case "NOTAS DE CREDITO E":

                tipoComprobante = "NOTA DE CREDITO E";
                break;

            case "CREDITOS DE PRESUPUESTOS":

                tipoComprobante = "CREDITO DE PRESUPUESTO";
                break;

            case "DEVOLUCIONES":

                tipoComprobante = "DEVOLUCION DE CONSIGNACION";
                break;

            case "TRASLADOS":

                tipoComprobante = "TRASLADO";
                break;

            case "ANULACIONES DE COMPROBANTES":

                tipoComprobante = "ANULACION DE COMPROBANTE";
                break;

            default:

                break;

        }
        
    }//GEN-LAST:event_cbTipoComprobanteActionPerformed

    private void bConsultarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarComprobanteActionPerformed
        
        // CONSULTA DETALLADA  DE COMPROBANTE   
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el comprobante cuyos datos desea consultar.", "CONSULTA DETALLADA DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            return;
            
        }
        
        FrmDetalleComprobanteConsultado form = new FrmDetalleComprobanteConsultado();

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
        */

        switch (tipoComprobante){

            case "FACTURA A":

                form.lTipoComprobante.setText("FACTURA A");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "FACTURA C":

                form.lTipoComprobante.setText("FACTURA C");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "FACTURA E":

                form.lTipoComprobante.setText("FACTURA E");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "PRESUPUESTO":

                form.lTipoComprobante.setText("PRESUPUESTO");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "CONSIGNACION":

                form.lTipoComprobante.setText("CONSIGNACION");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 5).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 5).toString());
                break;

            case "INGRESO":

                form.lTipoComprobante.setText("INGRESO");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                break;

            case "FACTURACION DE CONSIGNACION":

                form.lTipoComprobante.setText("FACTURACION DE CONSIGNACION");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                break;

            case "PAGO":

                form.lTipoComprobante.setText("PAGO");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "NOTA DE CREDITO A":

                form.lTipoComprobante.setText("NOTA DE CREDITO A");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "NOTA DE CREDITO C":

                form.lTipoComprobante.setText("NOTA DE CREDITO C");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "NOTA DE CREDITO E":

                form.lTipoComprobante.setText("NOTA DE CREDITO E");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "CREDITO DE PRESUPUESTO":

                form.lTipoComprobante.setText("CREDITO DE PRESUPUESTO");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                break;

            case "DEVOLUCION DE CONSIGNACION":

                form.lTipoComprobante.setText("DEVOLUCION DE CONSIGNACION");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                break;

            case "TRASLADO":

                form.lTipoComprobante.setText("TRASLADO");
                form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 4).toString());
                form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 4).toString());
                break;

            case "ANULACION DE COMPROBANTE":

                form.lTipoComprobante.setText("ANULACION DE COMPROBANTE");
                form.lKgsMiel.setText("0.00");
                form.cantidadMielAfectada = 0.00;
                break;

            default:

                break;

        }
        
        //llevo tipo de comprobante consultado al formulario de detalle de comprobante
        form.lFechaMovimiento.setText(tComprobantes.getValueAt(fila, 1).toString());
        form.tipoComprobante = tipoComprobante;
        form.lNumeroComprobante.setText(tComprobantes.getValueAt(fila, 2).toString());
        form.codigoComprobanteConsultado = Integer.valueOf(tComprobantes.getValueAt(fila, 0).toString());

        form.inicializar();

        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_bConsultarComprobanteActionPerformed

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed

    private void bEliminarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarComprobanteActionPerformed

        // anulacion del comprobante seleccionado
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el comprobante que desea anular.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            //la ANULACION de un comprobante conlleva el analisis y posterior accion en cuanto
            //a que dependiendo del comprobante anulado quizas haya que modificar y ajustar saldos y demas
            //tanto saldos de dinero como saldos de miel!
            
            /*
            FACTURAS A (DE PRODUCTORES / A CLIENTES)
            FACTURAS C (DE PRODUCTORES / A CLIENTES)
            FACTURAS E (A CLIENTES)
            PRESUPUESTOS (DE PRODUCTORES / A CLIENTES)
            (***TODOS LOS COMPROBANTES ANTERIORES PUEDEN ESTAR AFECTADOS POR PAGOS Y NOTAS DE CREDITO, ENTONCES: 
            a la hora de intentar anular un comprobante de este tipo se chequea que no este afectado por pagos,
            notas de credito y/o creditos de presupuestos; y que la cantidad de miel anulada no provoque una descompensacion
            en el stock real de miel***)
            
            INGRESOS
            (***solo se deberia dejar anular un ingreso de miel si al restablecer las cantidades de miel
            en la locacion relacionada, los valores no quedan en negativo! o sea, no se provoca una descompensacion
            del stock real de miel***)
            
            FACTURACIONES DE CONSIGNACIONES 
            (***AFECTAN A CONSIGNACIONES Y A SU VEZ, SE VEN AFECTADAS POR PAGOS, VER BIEN***)
            
            CONSIGNACIONES
            (***LAS CONSIGNACIONES PUEDEN ESTAR AFECTADAS POR FACTURACIONES O DEVOLUCIONES DE CONSIGNACIONES:
            se chequea que la consignacion no este afectada por facturaciones de consignaciones y/o devoluciones
            de consignaciones. Ademas tambien se chequea que al contra restar la miel, no se provoque una descompensacion
            del stock real de miel***)
            
            DEVOLUCIONES DE CONSIGNACIONES
            (***AFECTAN A CONSIGNACIONES PERO NO SE VEN AFECTADAS POR PAGOS U OTROS COMPROBANTES, VER BIEN***)

            PAGOS A PRODUCTORES
            PAGOS DE CLIENTES
            NOTAS DE CREDITO A
            NOTAS DE CREDITO C
            NOTAS DE CREDITO E
            CREDITOS DE PRESUPUESTOS DE PRODUCTORES
            CREDITOS DE PRESUPUESTOS A CLIENTES
            (***TODOS LOS PAGOS, NOTAS DE CREDITO Y CREDITOS AFECTAN A OTROS COMPROBANTES (LOS DE COMPRA), PERO NO ESTAN
            AFECTADOS POR OTROS COMPROBANTES, POR LO CUAL SU ANULACION ES SENCILLA, Y LO UNICO QUE TIENEN QUE HACER
            ES CONTRA RESTAR EL IMPORTE DE DINERO DEL MOVIMIENTO QUE ESTAN ANULANDO, LA CANTIDAD DE MIEL EN EL CASO
            DE LAS NOTAS DE CREDITO Y LOS CREDITOS, Y ADEMAS TIENEN QUE RESTABLECER LOS IMPORTES
            PAGADOS Y ACREDITADOS EN LOS COMPROBANTES QUE SE VIERON AFECTADOS POR TALES "COMPROBANTES DE ACREDITACION")
            
            TRASLADOS
            (***ANULAR UN TRASLADO SIGNIFICA TOCAR CANTIDADES DE MIEL PAGA Y MIEL IMPAGA QUE QUIZAS YA NO SE
            ENCUENTRAN EN LAS LOCACIONES AFECTADAS POR EL TRASLADO, ENTONCES:
            SE DEBE PEDIR UNA DOBLE CONFIRMACION EN EL CASO DEL REGISTRO DE UN TRASLADO, ANTICIPANDO AL USUARIO
            QUE DICHO TRASLADO LUEGO NO PODRA SER ANULADO***)
        
            ANULACIONES: 
            (***SE APLICA LA MISMA REGLA QUE PARA LOS TRASLADOS***)*/
            
            boolean comprobanteAfectadoPagos = false;
            boolean consignacionAfectadaComprobantes = false;
            boolean descompensacionStock = false;
            Double cantidadMielAfectada = 0.00;
            Double stockLocacion = 0.00;
            Double diferenciaStock = 0.00;
            int locacionMiel = 0;
            int productor = 0;
            int movimiento = 0;
            String estadoComprobante = "";
            
            /*formas de ingreso de la miel al sistema*/
            FacturaProductor facturaProductor = new FacturaProductor();
            FacturaCliente facturaCliente = new FacturaCliente();
            PresupuestoProductor presupuestoProductor = new PresupuestoProductor();
            PresupuestoCliente presupuestoCliente = new PresupuestoCliente();
            CreditoProductor consignacion = new CreditoProductor();
            IngresoMielPropia ingresoMielPropia = new IngresoMielPropia();
            //***listas las anulaciones de todas las formas de ingreso al sistema!***
            
            /**pagos y comprobantes de acreditacion de dinero*/
            PagoProductor pagoProductor = new PagoProductor();
            PagoCliente pagoCliente = new PagoCliente();
            NotaCreditoProductor notaCreditoProductor = new NotaCreditoProductor();
            NotaCreditoCliente notaCreditoCliente = new NotaCreditoCliente();
            CreditoPresupuestoProductor creditoPresupuestoProductor = new CreditoPresupuestoProductor();
            
            /*comprobantes que afectan a las consignaciones*/
            DevolucionProductor devolucionProductor = new DevolucionProductor();
            //facturaciones de consignaciones: falta hacer!
            //las facturaciones de consignaciones son comprobantes de compra: factura a, factura c y presupuesto
            //de los cuales, antes de ser anulados, debe chequearse sino poseen pagos asociados tambien
            
            /*traslados de miel*/
            //Traslado traslado = new Traslado();
            AjusteCompensacionStock ajuste = new AjusteCompensacionStock();
            AnulacionComprobanteProductor anulacion = new AnulacionComprobanteProductor();
            Locacion locacion = new Locacion();
            StockRealMiel stock = new StockRealMiel();
            CtaCteProductor ctacteProductor = new CtaCteProductor();
            CtaCteCliente ctacteCliente = new CtaCteCliente();
            //LocalDate fecha = LocalDate.now();
            FrmRegistroAnulacionComprobanteProductor form = new FrmRegistroAnulacionComprobanteProductor();
            form.bandera = tipoConsulta;
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            /**/
            
            switch (tipoComprobante){

                case "FACTURA A":
                    
                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    productor = facturaProductor.mostrarCodigoProductorFactura(codigoComprobante);
                    movimiento = facturaProductor.mostrarCodigoMovimientoEnCtaCteFacturaProductor("FACTURA A", codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "La factura ha sido anulada anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{
                        
                        //2do chequeo: controlar que la factura a anularse no se encuentre afectada por acreditaciones
                        comprobanteAfectadoPagos = facturaProductor.chequearAcreditacionesSobreFactura(codigoComprobante);
                        
                        if (comprobanteAfectadoPagos){

                            JOptionPane.showMessageDialog(null, "La factura que desea anular se encuentra afectada por pagos y/o notas de credito. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else{

                            //3er chequeo: se controla que el stock no se descompense
                            //tambien se debe chequear que al contra restarse la cantidad de miel que ingreso al sistema
                            //con el registro de la ANULACION en cuestion, el stock de la locacion involucrada no quede en negativo
                            //(esto podria darse si se carga la factura "erronea", y se realiza un traslado sacando la miel ingresada
                            //en la factura de la locacion involucrada
                            //entonces: debo obtener el codigo de la locacion donde se deposito la miel comprada en la factura
                            //consultar el stock DE MIEL PAGA de dicha locacion y realizar el chequeo pertinente
                            locacionMiel = stock.obtenerLocacionMielADevolverEnNotaCredito(codigoComprobante);
                            stockLocacion = ajuste.consultarCantidadMielPagaLocacion(locacionMiel);
                            cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                            diferenciaStock = stockLocacion - cantidadMielAfectada;

                            if (diferenciaStock < 0.00){

                                descompensacionStock = true;

                            }

                            if (descompensacionStock){

                                JOptionPane.showMessageDialog(null, "La cantidad de miel facturada en el comprobante que desea anular ya no posee relacion con el stock de miel actual. La anulacion del comprobante descompensara el stock real de miel. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                            }
                            else{

                                //se llevan al formulacion de anulacion todos los datos necesarios
                                form.tipoComprobanteAnulado = tipoComprobante;
                                form.codigoComprobanteAnulado = codigoComprobante;
                                form.numeroComprobanteAnulado = facturaProductor.mostrarNumeroComprobanteFactura(codigoComprobante);
                                form.importeDineroAnulado = facturaProductor.mostrarImporteFactura(codigoComprobante);
                                form.cantidadMielAnulada = cantidadMielAfectada;
                                form.fechaComprobanteAnulado = facturaProductor.mostrarFechaFactura(codigoComprobante);
                                form.codigoLocacion = stock.obtenerLocacionMielADevolverEnNotaCredito(codigoComprobante);
                                form.codigoProductor = facturaProductor.mostrarCodigoProductorFactura(codigoComprobante);

                                form.inicializar();

                                deskPrincipal.add(form);

                                form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                form.setVisible(true);

                                form.setClosable(true);
                                form.setIconifiable(false);

                            }

                        }

                    }
                    
                    break;

                case "FACTURA C":
                    
                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    productor = facturaProductor.mostrarCodigoProductorFactura(codigoComprobante);
                    movimiento = facturaProductor.mostrarCodigoMovimientoEnCtaCteFacturaProductor("FACTURA C", codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "La factura ha sido anulada anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{
                        
                        //2do chequeo: controlar que la factura a anularse no se encuentre afectada por acreditaciones
                        comprobanteAfectadoPagos = facturaProductor.chequearAcreditacionesSobreFactura(codigoComprobante);
                        
                        if (comprobanteAfectadoPagos){

                            JOptionPane.showMessageDialog(null, "La factura que desea anular se encuentra afectada por pagos y/o notas de credito. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else{

                            //3er chequeo: se controla que el stock no se descompense
                            //tambien se debe chequear que al contra restarse la cantidad de miel que ingreso al sistema
                            //con el registro de la ANULACION en cuestion, el stock de la locacion involucrada no quede en negativo
                            //(esto podria darse si se carga la factura "erronea", y se realiza un traslado sacando la miel ingresada
                            //en la factura de la locacion involucrada
                            //entonces: debo obtener el codigo de la locacion donde se deposito la miel comprada en la factura
                            //consultar el stock DE MIEL PAGA de dicha locacion y realizar el chequeo pertinente
                            locacionMiel = stock.obtenerLocacionMielADevolverEnNotaCredito(codigoComprobante);
                            stockLocacion = ajuste.consultarCantidadMielPagaLocacion(locacionMiel);
                            cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                            diferenciaStock = stockLocacion - cantidadMielAfectada;

                            if (diferenciaStock < 0.00){

                                descompensacionStock = true;

                            }

                            if (descompensacionStock){

                                JOptionPane.showMessageDialog(null, "La cantidad de miel facturada en el comprobante que desea anular ya no posee relacion con el stock de miel actual. La anulacion del comprobante descompensara el stock real de miel. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                            }
                            else{

                                //se llevan al formulacion de anulacion todos los datos necesarios
                                form.tipoComprobanteAnulado = tipoComprobante;
                                form.codigoComprobanteAnulado = codigoComprobante;
                                form.numeroComprobanteAnulado = facturaProductor.mostrarNumeroComprobanteFactura(codigoComprobante);
                                form.importeDineroAnulado = facturaProductor.mostrarImporteFactura(codigoComprobante);
                                form.cantidadMielAnulada = cantidadMielAfectada;
                                form.fechaComprobanteAnulado = facturaProductor.mostrarFechaFactura(codigoComprobante);
                                form.codigoLocacion = stock.obtenerLocacionMielADevolverEnNotaCredito(codigoComprobante);
                                form.codigoProductor = facturaProductor.mostrarCodigoProductorFactura(codigoComprobante);

                                form.inicializar();

                                deskPrincipal.add(form);

                                form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                form.setVisible(true);

                                form.setClosable(true);
                                form.setIconifiable(false);

                            }

                        }

                    }
                    
                    break;

                case "FACTURA E":

                    JOptionPane.showMessageDialog(null, "EN DESARROLLO");
                    break;

                case "PRESUPUESTO":
                    
                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    productor = presupuestoProductor.mostrarCodigoProductorPresupuesto(codigoComprobante);
                    movimiento = presupuestoProductor.mostrarCodigoMovimientoEnCtaCtePresupuestoProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "El presupuesto ha sido anulado anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{
                        
                        //2do chequeo: controlar que la factura a anularse no se encuentre afectada por acreditaciones
                        comprobanteAfectadoPagos = presupuestoProductor.chequearAcreditacionesSobrePresupuesto(codigoComprobante);
                        
                        if (comprobanteAfectadoPagos){

                            JOptionPane.showMessageDialog(null, "El presupuesto que desea anular se encuentra afectado por pagos y/o creditos de presupuestos. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else{

                            //3er chequeo: se controla que el stock no se descompense
                            //tambien se debe chequear que al contra restarse la cantidad de miel que ingreso al sistema
                            //con el registro de la ANULACION en cuestion, el stock de la locacion involucrada no quede en negativo
                            //(esto podria darse si se carga la factura "erronea", y se realiza un traslado sacando la miel ingresada
                            //en la factura de la locacion involucrada
                            //entonces: debo obtener el codigo de la locacion donde se deposito la miel comprada en la factura
                            //consultar el stock DE MIEL PAGA de dicha locacion y realizar el chequeo pertinente
                            //ESTO AHORA SE PODRIA CHEQUEAR DIRECTAMENTE DESDE LA TABLA DE AJUSTE Y COMPENSACION DE STOCK!
                            locacionMiel = stock.obtenerLocacionMielADevolverEnCreditoPresupuesto(codigoComprobante);
                            stockLocacion = ajuste.consultarCantidadMielPagaLocacion(locacionMiel);
                            cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                            diferenciaStock = stockLocacion - cantidadMielAfectada;

                            if (diferenciaStock < 0.00){

                                descompensacionStock = true;

                            }

                            if (descompensacionStock){

                                JOptionPane.showMessageDialog(null, "La cantidad de miel presupuestada en el comprobante que desea anular ya no posee relacion con el stock de miel actual. La anulacion del comprobante descompensara el stock real de miel. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                            }
                            else{

                                //se llevan al formulacion de anulacion todos los datos necesarios
                                form.tipoComprobanteAnulado = tipoComprobante;
                                form.codigoComprobanteAnulado = codigoComprobante;
                                form.numeroComprobanteAnulado = presupuestoProductor.mostrarNumeroComprobantePresupuesto(codigoComprobante);
                                form.importeDineroAnulado = presupuestoProductor.mostrarImportePresupuesto(codigoComprobante);
                                form.cantidadMielAnulada = cantidadMielAfectada;
                                form.fechaComprobanteAnulado = presupuestoProductor.mostrarFechaPresupuesto(codigoComprobante);
                                form.codigoLocacion = stock.obtenerLocacionMielADevolverEnCreditoPresupuesto(codigoComprobante);
                                form.codigoProductor = presupuestoProductor.mostrarCodigoProductorPresupuesto(codigoComprobante);

                                form.inicializar();

                                deskPrincipal.add(form);

                                form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                form.setVisible(true);

                                form.setClosable(true);
                                form.setIconifiable(false);

                            }

                        }

                    }
                    
                    break;

                case "CONSIGNACION":

                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    productor = consignacion.mostrarCodigoProductorConsignacion(codigoComprobante);
                    movimiento = consignacion.mostrarCodigoMovimientoEnCtaCteConsignacionProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "La compra en consignacion ha sido anulada anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{
                        
                        //2do chequeo: controlar que la consignacion a anularse no se encuentre afectada por facturaciones!
                        consignacionAfectadaComprobantes = consignacion.chequearComprobantesSobreConsignacion(codigoComprobante);
                        
                        if (consignacionAfectadaComprobantes){

                            JOptionPane.showMessageDialog(null, "La compra en consignacion que desea anular se encuentra afectada por facturaciones y/o devoluciones de consignaciones. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else{

                            //3er chequeo: se controla que el stock no se descompense
                            //tambien se debe chequear que al contra restarse la cantidad de miel que ingreso al sistema
                            //con el registro de la consignacion, con el registro de la anulacion en cuestion, el stock de la locacion 
                            //involucrada no quede en negativo
                            //(esto podria darse si se carga la consignacion "erronea", y se realiza un traslado sacando la miel ingresada
                            //en la consignacion de la locacion involucrada
                            //entonces: debo obtener el codigo de la locacion donde se deposito la miel comprada en la consignacion
                            //consultar el stock DE MIEL IMPAGA de dicha locacion y realizar el chequeo pertinente
                            locacionMiel = stock.obtenerLocacionMielADevolverOFacturar(codigoComprobante);
                            stockLocacion = ajuste.consultarCantidadMielImpagaLocacion(locacionMiel);
                            cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 5).toString());
                            diferenciaStock = stockLocacion - cantidadMielAfectada;

                            if (diferenciaStock < 0.00){

                                descompensacionStock = true;

                            }

                            if (descompensacionStock){

                                JOptionPane.showMessageDialog(null, "La cantidad de miel consignada en el comprobante que desea anular ya no posee relacion con el stock de miel actual. La anulacion del comprobante descompensara el stock real de miel. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                            }
                            else{

                                //se llevan al formulacion de anulacion todos los datos necesarios
                                form.tipoComprobanteAnulado = tipoComprobante;
                                form.codigoComprobanteAnulado = codigoComprobante;
                                form.numeroComprobanteAnulado = consignacion.mostrarNumeroComprobanteConsignacion(codigoComprobante);
                                form.importeDineroAnulado = 0.00;
                                form.cantidadMielAnulada = cantidadMielAfectada;
                                form.fechaComprobanteAnulado = consignacion.mostrarFechaConsignacion(codigoComprobante);
                                form.codigoLocacion = stock.obtenerLocacionMielADevolverOFacturar(codigoComprobante);
                                form.codigoProductor = consignacion.mostrarCodigoProductorConsignacion(codigoComprobante);

                                form.inicializar();

                                deskPrincipal.add(form);

                                form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                form.setVisible(true);

                                form.setClosable(true);
                                form.setIconifiable(false);

                            }

                        }

                    }
                    
                    break;

                case "INGRESO":

                    //Se debe chequear que al contra restarse la cantidad de miel que ingreso al sistema
                    //con el registro de la ANULACION en cuestion, el stock de la locacion involucrada no quede en negativo
                    descompensacionStock = false;
                    locacionMiel = ingresoMielPropia.obtenerLocacionMielIngreso(codigoComprobante);
                    stockLocacion = ajuste.consultarCantidadMielPagaLocacion(locacionMiel);
                    cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                    diferenciaStock = stockLocacion - cantidadMielAfectada;
                    
                    if (diferenciaStock < 0.00){
                    
                        descompensacionStock = true;
                    
                    }
                    
                    //SE REALIZAN LOS CHEQUEOS NECESARIOS PARA VER SI SE PUEDE ANULAR O NO LA FACTURA
                    if (descompensacionStock){

                        JOptionPane.showMessageDialog(null, "La cantidad de miel ingresada en el comprobante que desea anular ya no posee relacion con el stock de miel actual. La anulacion del comprobante descompensara el stock real de miel. No es posible llevar a cabo su anulacion. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{

                        //se llevan al formulacion de anulacion todos los datos necesarios
                        form.tipoComprobanteAnulado = tipoComprobante;
                        form.codigoComprobanteAnulado = codigoComprobante;
                        form.numeroComprobanteAnulado = ingresoMielPropia.mostrarNumeroComprobanteIngreso(codigoComprobante);
                        form.importeDineroAnulado = 0.00;
                        form.cantidadMielAnulada = cantidadMielAfectada;
                        form.fechaComprobanteAnulado = ingresoMielPropia.mostrarFechaIngreso(codigoComprobante);
                        form.codigoLocacion = stock.obtenerLocacionMielADevolverEnIngreso(codigoComprobante);

                        form.inicializar();

                        deskPrincipal.add(form);

                        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                        form.setVisible(true);

                        form.setClosable(true);
                        form.setIconifiable(false);

                    }
                    
                    break;

                case "FACTURACION DE CONSIGNACION":

                    JOptionPane.showMessageDialog(null, "EN DESARROLLO");
                    break;

                case "PAGO":

                    //solo se realiza el primer chequeo, que el comprobante no haya sido anulado previamente
                    productor = pagoProductor.mostrarCodigoProductorPago(codigoComprobante);
                    movimiento = pagoProductor.mostrarCodigoMovimientoEnCtaCtePagoProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "El pago ha sido anulado anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{

                        //no se realiza ningun chequeo mas , ya que el pago no debe contra restar cantidades de miel
                        //sino importes de dinero acreditados en las ctas. ctes.
                        //se llevan al formulacion de anulacion todos los datos necesarios
                        form.tipoComprobanteAnulado = tipoComprobante;
                        form.codigoComprobanteAnulado = codigoComprobante;
                        form.numeroComprobanteAnulado = String.valueOf(codigoComprobante);
                        form.importeDineroAnulado = pagoProductor.mostrarImportePago(codigoComprobante);
                        form.cantidadMielAnulada = 0.00;
                        form.fechaComprobanteAnulado = pagoProductor.mostrarFechaPago(codigoComprobante);
                        form.codigoProductor = pagoProductor.mostrarCodigoProductorPago(codigoComprobante);

                        form.inicializar();

                        deskPrincipal.add(form);

                        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                        form.setVisible(true);

                        form.setClosable(true);
                        form.setIconifiable(false);

                    }
                    
                    break;

                case "NOTA DE CREDITO A":

                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    
                    productor = notaCreditoProductor.mostrarCodigoProductorNotaCredito(codigoComprobante);
                    movimiento = notaCreditoProductor.mostrarCodigoMovimientoEnCtaCteNotaCreditoProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "La nota de credito A ha sido anulada anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{

                        //se llevan al formulacion de anulacion todos los datos necesarios
                        form.tipoComprobanteAnulado = tipoComprobante;
                        form.codigoComprobanteAnulado = codigoComprobante;
                        form.numeroComprobanteAnulado = notaCreditoProductor.mostrarNumeroComprobanteNotaCredito(codigoComprobante);
                        form.importeDineroAnulado = notaCreditoProductor.mostrarImporteNotaCredito(codigoComprobante);
                        form.cantidadMielAnulada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                        form.fechaComprobanteAnulado = notaCreditoProductor.mostrarFechaNotaCredito(codigoComprobante);
                        form.codigoProductor = notaCreditoProductor.mostrarCodigoProductorNotaCredito(codigoComprobante);
                        form.codigoLocacion = stock.obtenerLocacionMielADevolverPorNotaCreditoAnulada(codigoComprobante);

                        form.inicializar();

                        deskPrincipal.add(form);

                        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                        form.setVisible(true);

                        form.setClosable(true);
                        form.setIconifiable(false);

                    }
                    
                    break;

                case "NOTA DE CREDITO C":

                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    
                    productor = notaCreditoProductor.mostrarCodigoProductorNotaCredito(codigoComprobante);
                    movimiento = notaCreditoProductor.mostrarCodigoMovimientoEnCtaCteNotaCreditoProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "La nota de credito C ha sido anulada anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{

                        //se llevan al formulacion de anulacion todos los datos necesarios
                        form.tipoComprobanteAnulado = tipoComprobante;
                        form.codigoComprobanteAnulado = codigoComprobante;
                        form.numeroComprobanteAnulado = notaCreditoProductor.mostrarNumeroComprobanteNotaCredito(codigoComprobante);
                        form.importeDineroAnulado = notaCreditoProductor.mostrarImporteNotaCredito(codigoComprobante);
                        form.cantidadMielAnulada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                        form.fechaComprobanteAnulado = notaCreditoProductor.mostrarFechaNotaCredito(codigoComprobante);
                        form.codigoProductor = notaCreditoProductor.mostrarCodigoProductorNotaCredito(codigoComprobante);
                        form.codigoLocacion = stock.obtenerLocacionMielADevolverPorNotaCreditoAnulada(codigoComprobante);

                        form.inicializar();

                        deskPrincipal.add(form);

                        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                        form.setVisible(true);

                        form.setClosable(true);
                        form.setIconifiable(false);

                    }
                    
                    break;

                case "NOTA DE CREDITO E":

                    JOptionPane.showMessageDialog(null, "EN DESARROLLO");
                    break;

                case "CREDITO DE PRESUPUESTO":

                    //1er chequeo: controlar si ya no esta anulado el comprobante
                    
                    productor = creditoPresupuestoProductor.mostrarCodigoProductorCreditoPresupuesto(codigoComprobante);
                    movimiento = creditoPresupuestoProductor.mostrarCodigoMovimientoEnCtaCteCreditoPresupuestoProductor(codigoComprobante);
                    estadoComprobante = ctacteProductor.obtenerEstadoComprobanteCtaCteProductor(productor, movimiento);
                    
                    if (estadoComprobante.equals("ANULADO")){
                        
                        //si el comprobante ya se encuentra anulado se informa y se cancela su nueva anulacion, obvio
                        JOptionPane.showMessageDialog(null, "El credito de presupuesto ha sido anulado anteriormente. Seleccione otro comprobante para realizar dicha tarea por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    else{

                        //se llevan al formulacion de anulacion todos los datos necesarios
                        form.tipoComprobanteAnulado = tipoComprobante;
                        form.codigoComprobanteAnulado = codigoComprobante;
                        form.numeroComprobanteAnulado = creditoPresupuestoProductor.mostrarNumeroComprobanteCreditoPresupuesto(codigoComprobante);
                        form.importeDineroAnulado = creditoPresupuestoProductor.mostrarImporteCreditoPresupuestoProductor(codigoComprobante);
                        form.cantidadMielAnulada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                        form.fechaComprobanteAnulado = creditoPresupuestoProductor.mostrarFechaCreditoPresupuesto(codigoComprobante);
                        form.codigoProductor = creditoPresupuestoProductor.mostrarCodigoProductorCreditoPresupuesto(codigoComprobante);
                        form.codigoLocacion = stock.obtenerLocacionMielADevolverPorCreditoPresupuestoAnulado(codigoComprobante);
                        System.out.println(form.codigoProductor);
                        System.out.println(form.codigoLocacion);

                        form.inicializar();

                        deskPrincipal.add(form);

                        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                        form.setVisible(true);

                        form.setClosable(true);
                        form.setIconifiable(false);

                    }
                    
                    break;

                case "DEVOLUCION DE CONSIGNACION":

                    JOptionPane.showMessageDialog(null, "EN DESARROLLO");
                    break;

                case "TRASLADO":

                    JOptionPane.showMessageDialog(null, "No es posible anular un tralado de miel. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case "ANULACION DE COMPROBANTE":

                    JOptionPane.showMessageDialog(null, "No es posible anular una anulacion de comprobante. Seleccione otro comprobante para anular por favor.", "ANULACION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:

                    break;

            }

        }

    }//GEN-LAST:event_bEliminarComprobanteActionPerformed

    private void bReImprimirComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReImprimirComprobanteActionPerformed

        // ELIMINACION DEL MOVIMIENTO SELECCIONADO
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el comprobante que desea re imprimir.", "RE IMPRESION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            JOptionPane.showMessageDialog(null, "EN DESARROLLO.", "RE IMPRESION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            
        }

    }//GEN-LAST:event_bReImprimirComprobanteActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        //para actualizar datos ingresados mientras este formulario esta abierto
        mostrarComprobantes(tipoComprobante, tipoConsulta, añoConsulta, mesConsulta);
        tComprobantes.requestFocus();

    }//GEN-LAST:event_jButton7ActionPerformed

    private void cbAñoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAñoConsultaActionPerformed
        
        añoConsulta = cbAñoConsulta.getSelectedItem().toString();
        
    }//GEN-LAST:event_cbAñoConsultaActionPerformed

    private void cbMesConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesConsultaActionPerformed
        
        String mes = cbMesConsulta.getSelectedItem().toString();
        
        switch (mes){
            
            case "SELECCIONAR":
                
                mesConsulta = "SELECCIONAR";
                break;
                
            case "ENERO":
                
                mesConsulta = "01";
                break;
                
            case "FEBRERO":
                
                mesConsulta = "02";
                break;
                
            case "MARZO":
                
                mesConsulta = "03";
                break;
                
            case "ABRIL":
                
                mesConsulta = "04";
                break;
                
            case "MAYO":
                
                mesConsulta = "05";
                break;
                
            case "JUNIO":
                
                mesConsulta = "06";
                break;
                
            case "JULIO":
                
                mesConsulta = "07";
                break;
                
            case "AGOSTO":
                
                mesConsulta = "08";
                break;
                
            case "SEPTIEMBRE":
                
                mesConsulta = "09";
                break;
                
            case "OCTUBRE":
                
                mesConsulta = "10";
                break;
                
            case "NOVIEMBRE":
                
                mesConsulta = "11";
                break;
                
            case "DICIEMBRE":
                
                mesConsulta = "12";
                break;
                
            default:
            
                break;
                
        }
        
    }//GEN-LAST:event_cbMesConsultaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        if (tipoComprobante.equals("SELECCIONAR") || tipoConsulta.equals("SELECCIONAR") || añoConsulta.equals("SELECCIONAR") || mesConsulta.equals("SELECCIONAR")){
        
            JOptionPane.showMessageDialog(null, "Por favor ingrese los datos correctamente para poder realizar la consulta deseada.", "GESTION DE COMPROBANTES", JOptionPane.INFORMATION_MESSAGE);
        
        }
        else{
        
            mostrarComprobantes(tipoComprobante, tipoConsulta, añoConsulta, mesConsulta);
        
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoConsultaActionPerformed
        
        String consulta = cbTipoConsulta.getSelectedItem().toString();
        
        switch (consulta){

            case "SELECCIONAR":

                tipoConsulta = "SELECCIONAR";
                break;

            case "CLIENTES":

                tipoConsulta = "CLIENTE";
                break;

            case "PRODUCTORES":

                tipoConsulta = "PRODUCTOR";
                break;
                
            default:
                
                break;
                
        }

        
    }//GEN-LAST:event_cbTipoConsultaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        // MODIFICACION DEL COMPROBANTE SELECCIONADO
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el comprobante que desea modificar.", "EDICION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            JOptionPane.showMessageDialog(null, "EN DESARROLLO.", "EDICION DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            //se deberian dejar editar aquellos comprobantes que no aun no han sido afectados por otros comprobantes
            //en el caso de comprobantes que han sido ya afectados por otros comprobantes, se deberian dejar
            //eliminar solamente
            
            //la eliminacion de un comprobante conlleva el analisis y posterior accion en cuanto
            //a que dependiendo del comprobante eliminado quizas haya que modificar y ajustar saldos y demas
            
            /*
            FACTURAS A
            FACTURAS C
            FACTURAS E
            PRESUPUESTOS DE PRODUCTORES
            PRESUPUESTOS A CLIENTES
            (TODOS LOS COMPROBANTES ANTERIORES PUEDEN ESTAR AFECTADOS POR PAGOS, ENTONCES: 
            DEBERIAN DEJARSE ELIMINAR??? O SE DEBERIAN DIRECTAMENTE ANULAR EN EL CASO DE LOS PRESUPUESTOS
            Y ANULAR CON NOTAS DE CREDITO EN EL CASO DE LAS FACTURAS?)
            
            CONSIGNACIONES
            (LAS CONSIGNACIONES PUEDEN ESTAR AFECTADAS POR FACTURACIONES O DEVOLUCIONES
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
        
        }

    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsultarComprobante;
    private javax.swing.JButton bEliminarComprobante;
    private javax.swing.JButton bReImprimirComprobante;
    public javax.swing.JComboBox<String> cbAñoConsulta;
    public javax.swing.JComboBox<String> cbMesConsulta;
    public javax.swing.JComboBox<String> cbTipoComprobante;
    public javax.swing.JComboBox<String> cbTipoConsulta;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tComprobantes;
    private javax.swing.JToolBar tbOpciones;
    private javax.swing.JToolBar tbOpciones1;
    // End of variables declaration//GEN-END:variables
}
