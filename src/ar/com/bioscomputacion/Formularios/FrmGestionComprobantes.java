/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.AnulacionPresupuestoProductor;
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
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionComprobantes extends javax.swing.JInternalFrame {

    int fila = -1;
    //para saber todo el tiempo que tipo de comprobantes se selecciono visualizar
    String tipoComprobante;
    //pasa conocer todo el tiempo el codigo de comprobante del comprobante seleccionado en la grilaa
    int codigoComprobante;

    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionComprobantes() {
        
        initComponents();
        mostrarComprobantes("");
        //ocultarColumnasComprobantes();
        inicializar();
        
    }

    public void inicializar() {
        
        tipoComprobante = "SELECCIONAR";
        cbTipoComprobante.setSelectedIndex(0);
        cbTipoComprobante.requestFocus();
        
}

        public void mostrarComprobantes(String tipoComprobante) {
        
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
            ANULACIONES falta implmentar!
            DEVOLUCIONES falta terminar de implementar!
            TRASLADOS
            */
            try {
            
            DefaultTableModel modelo;
            
            switch (tipoComprobante){
                
                case "SELECCIONAR":
                    
                    tComprobantes.removeAll();
                    break;
                
                case "FACTURAS A":
                    
                    FacturaProductor facturaA = new FacturaProductor();
                    modelo = facturaA.listarFacturasA("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "FACTURAS C":
                    
                    FacturaProductor facturaC = new FacturaProductor();
                    modelo = facturaC.listarFacturasC("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "FACTURAS E":
                    
                    FacturaCliente facturaE = new FacturaCliente();
                    modelo = facturaE.listarFacturasE("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "PRESUPUESTOS DE PRODUCTORES":
                    
                    PresupuestoProductor presupuestoProductor = new PresupuestoProductor();
                    modelo = presupuestoProductor.listarPresupuestosDeProductores("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "PRESUPUESTOS A CLIENTES":
                    
                    PresupuestoCliente presupuestoCliente = new PresupuestoCliente();
                    modelo = presupuestoCliente.listarPresupuestosAClientes("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "CONSIGNACIONES":
                    
                    CreditoProductor credito = new CreditoProductor();
                    modelo = credito.listarConsignacionesDeProductores("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesConsignaciones();
                    break;
        
                case "INGRESOS":
                    
                    IngresoMielPropia ingreso = new IngresoMielPropia();
                    modelo = ingreso.listarIngresosMiel("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesIngresos();
                    break;
        
                case "FACTURACIONES DE CONSIGNACIONES":
                    
                    //FALTA CREAR E IMPLEMENTAR TODO LO QUE RESPECTA A ESTE MOVIMIENTO
                    
                    //crear metodo que traiga todos los comprobantes que esten relacionados a compras en consignacion
                    //ya sean facturas a, facturas c o presupuestos
                    break;
        
                case "PAGOS A PRODUCTORES":
                    
                    PagoProductor pagoProductor = new PagoProductor();
                    modelo = pagoProductor.listarPagosAProductores("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesPagos();
                    break;
        
                case "PAGOS DE CLIENTES":
                    
                    PagoCliente pagoCliente = new PagoCliente();
                    modelo = pagoCliente.listarPagosAClientes("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesPagos();
                    break;
        
                case "NOTAS DE CREDITO A":
                    
                    NotaCreditoProductor notaCreditoA = new NotaCreditoProductor();
                    modelo = notaCreditoA.listarNotasCreditoA("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "NOTAS DE CREDITO C":
                    
                    NotaCreditoProductor notaCreditoC = new NotaCreditoProductor();
                    modelo = notaCreditoC.listarNotasCreditoC("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "NOTAS DE CREDITO E":
                    
                    NotaCreditoCliente notaCreditoE = new NotaCreditoCliente();
                    modelo = notaCreditoE.listarNotasCreditoE("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "ANULACIONES":
                    
                    AnulacionPresupuestoProductor anulacion = new AnulacionPresupuestoProductor();
                    modelo = anulacion.listarAnulacionesPresupuestosProductores("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "DEVOLUCIONES":
                    
                    //FALTA TERMINAR LA PARTE DE DEVOLUCION DE MIEL EN CONSIGNACIONES
                    DevolucionProductor devolucion = new DevolucionProductor();
                    modelo = devolucion.listarDevolucionesAProductores("");
                    tComprobantes.setModel(modelo);
                    //ocultarColumnasComprobantesComprasYVentas();
                    break;
        
                case "TRASLADOS":
                    
                    Traslado traslado = new Traslado();
                    modelo = traslado.listarTraslados("");
                    tComprobantes.setModel(modelo);
                    ocultarColumnasComprobantesTraslados();
                    break;
        
                default:
                    
                    break;
        
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
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
        bConsultarComprobante = new javax.swing.JButton();
        bEliminarComprobante = new javax.swing.JButton();
        bReImprimirComprobante = new javax.swing.JButton();
        rsbrSalir = new rojeru_san.RSButtonRiple();

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
        cbTipoComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURAS A", "FACTURAS C", "FACTURAS E", "PRESUPUESTOS DE PRODUCTORES", "PRESUPUESTO A CLIENTES", "CONSIGNACIONES", "INGRESOS", "FACTURACIONES DE CONSIGNACIONES", "PAGOS A PRODUCTORES", "PAGOS DE CLIENTES", "NOTAS DE CREDITO A", "NOTAS DE CREDITO C", "NOTAS DE CREDITO E", "ANULACIONES", "DEVOLUCIONES", "TRASLADOS" }));
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

        bConsultarComprobante.setBackground(new java.awt.Color(0, 0, 0));
        bConsultarComprobante.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bConsultarComprobante.setForeground(new java.awt.Color(255, 255, 255));
        bConsultarComprobante.setText("  CONSULTAR COMPROBANTE  ");
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

        bEliminarComprobante.setBackground(new java.awt.Color(0, 0, 0));
        bEliminarComprobante.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bEliminarComprobante.setForeground(new java.awt.Color(255, 255, 255));
        bEliminarComprobante.setText("  ELIMINAR COMPROBANTE  ");
        bEliminarComprobante.setBorderPainted(false);
        bEliminarComprobante.setFocusable(false);
        bEliminarComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEliminarComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bEliminarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarComprobanteActionPerformed(evt);
            }
        });
        tbOpciones.add(bEliminarComprobante);

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

        rsbrSalir.setBackground(new java.awt.Color(0, 0, 0));
        rsbrSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Imagenes/btn-cerrar.png"))); // NOI18N
        rsbrSalir.setText("SALIR");
        rsbrSalir.setFont(new java.awt.Font("Roboto Bold", 3, 16)); // NOI18N
        rsbrSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrSalirActionPerformed(evt);
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
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(rsbrSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tbOpciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
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

        //almaceno el tipo de comprobante seleccionado para ver
        tipoComprobante = cbTipoComprobante.getSelectedItem().toString();
        mostrarComprobantes(tipoComprobante);
        tComprobantes.requestFocus();
        
    }//GEN-LAST:event_cbTipoComprobanteActionPerformed

    private void bConsultarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarComprobanteActionPerformed
        
        // CONSULTA DETALLADA DEL MOVIMIENTO DE STOCK SELECCIONADO
        
        if (fila == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el comprobante cuyos datos desea consultar.", "CONSULTA DETALLADA DE COMPROBANTE", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{

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
            
            form.lFechaMovimiento.setText(tComprobantes.getValueAt(fila, 1).toString());
            switch (tipoComprobante){
                
                case "FACTURAS A":
                
                    form.lTipoComprobante.setText("FACTURA A");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "FACTURAS C":
                
                    form.lTipoComprobante.setText("FACTURA C");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "FACTURAS E":
                
                    form.lTipoComprobante.setText("FACTURA E");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "PRESUPUESTOS DE PRODUCTORES":
                
                    form.lTipoComprobante.setText("PRESUPUESTO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "PRESUPUESTOS A CLIENTES":
                
                    form.lTipoComprobante.setText("PRESUPUESTO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "CONSIGNACIONES":
                
                    form.lTipoComprobante.setText("CONSIGNACION");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 5).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 5).toString());
                    break;
            
                case "INGRESOS":
                
                    form.lTipoComprobante.setText("INGRESO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                    break;
            
                case "FACTURACIONES DE CONSIGNACIONES":
                
                    form.lTipoComprobante.setText("FACTURACIONES");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                    break;
            
                case "PAGOS A PRODUCTORES":
                
                    form.lTipoComprobante.setText("PAGO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "PAGOS DE CLIENTES":
                
                    form.lTipoComprobante.setText("PAGO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "NOTAS DE CREDITO A":
                
                    form.lTipoComprobante.setText("NOTA DE CREDITO A");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "NOTAS DE CREDITO C":
                
                    form.lTipoComprobante.setText("NOTA DE CREDITO C");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "NOTAS DE CREDITO E":
                
                    form.lTipoComprobante.setText("NOTA DE CREDITO E");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "ANULACIONES":
                
                    form.lTipoComprobante.setText("ANULACION");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 6).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 6).toString());
                    break;
            
                case "DEVOLUCIONES":
                    
                    form.lTipoComprobante.setText("DEVOLUCION");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 3).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 3).toString());
                    break;
                
                case "TRASLADOS":
                    
                    form.lTipoComprobante.setText("TRASLADO");
                    form.lKgsMiel.setText(tComprobantes.getValueAt(fila, 4).toString());
                    form.cantidadMielAfectada = Double.valueOf(tComprobantes.getValueAt(fila, 4).toString());
                    break;
                
                default:
                        
                    break;
            
            }
            //llevo tipo de comprobante consultado al formulario de detalle de comprobante
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

        }
            
    }//GEN-LAST:event_bConsultarComprobanteActionPerformed

    private void rsbrSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrSalirActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrSalirActionPerformed

    private void bEliminarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEliminarComprobanteActionPerformed

    private void bReImprimirComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReImprimirComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bReImprimirComprobanteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsultarComprobante;
    private javax.swing.JButton bEliminarComprobante;
    private javax.swing.JButton bReImprimirComprobante;
    public javax.swing.JComboBox<String> cbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rsbrSalir;
    public static javax.swing.JTable tComprobantes;
    private javax.swing.JToolBar tbOpciones;
    // End of variables declaration//GEN-END:variables
}
