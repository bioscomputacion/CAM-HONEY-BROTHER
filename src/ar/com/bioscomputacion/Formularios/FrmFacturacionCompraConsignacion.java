/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.Cliente;
import ar.com.bioscomputacion.Funciones.ComprobantesRelacionadosCompraConsignacion;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoFacturaProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.CtaCteCliente;
import ar.com.bioscomputacion.Funciones.DevolucionProductor;
import ar.com.bioscomputacion.Funciones.FacturaCliente;
import ar.com.bioscomputacion.Funciones.ItemFacturadoCreditoProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoFacturaCliente;
import ar.com.bioscomputacion.Funciones.ItemFacturadoPresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Caco
 */

public class FrmFacturacionCompraConsignacion extends javax.swing.JInternalFrame {
    
    //VARIABLES PARA RECORDAR EL MOVIMIENTO CORRESPONDIENTE A LA COMPRA EN CONSIGNACION
    public static int codigoMovimientoCtaCteCompra, codigoProductor, codigoCompra, tipoComprobante;
    public static Date fechaMovimientoCompra;
    public static String numeroComprobanteCompra, observacionCompra, nombreProductor;

    //para poder controlar y comparar cuanta miel se habia financiado en la compra a consignacion
    //y cuanta miel se facturara en realidad (quedando un saldo de miel sin facturar el cual debe ser devuelto al productor
    //o mantenido en stock siendo siguiendo con su estado de miel impaga)
    //public static Double totalMielFinanciadaCompra, totalMielDescontadaCompra, totalMielFinanciada, totalMielFacturada;
    public static Double totalMielFinanciadaCompra, totalMielMantenidaEnConsignacion, totalMielYaDescontadaCompra, totalMielFacturada, importeComprobante;

    public static int codigoFactura, codigoPresupuesto, codigoItemFacturado, codigoItemPresupuestado, codigoMovimientoCtaCte, codigoLocacion; 
    
    //comprobantes relacionados a la compra en consignacion (facturas, presupuestos y devoluciones)
    public static List<ComprobantesRelacionadosCompraConsignacion> comprobantesRelacionados = new ArrayList<>();
    
    //items originales en la compra a consignacion
    public static List<ItemFacturadoCreditoProductor> compraOriginal = new ArrayList<>();

    //items financiados de la compra a consignacion
    public static List<ItemFacturadoCreditoProductor> itemsFinanciados = new ArrayList<>();
    
    //items facturados de la compra a consignacion
    public static List<ItemFacturadoFacturaProductor> itemsFacturados = new ArrayList<>();
    public static List<ItemFacturadoPresupuestoProductor> itemsPresupuestados = new ArrayList<>();
    
    //apuntadores de las grillas
    int filaItemsFinanciados = -1;
    int filaItemsFacturados = -1;
    
    //conexion a la base de datos
    public static ConexionBD mysql = new ConexionBD();
    public static Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmFacturacionCompraConsignacion() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public static void inicializar() throws SQLException{
        
        //limpio los arreglos que se utilizaran
        comprobantesRelacionados.clear();
        
        tfNumeroComprobante.setText("");
        
        Calendar cal = new GregorianCalendar();
        dcFechaFactura.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaFactura.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        //inicializo el campo cantidad de kilos a facturar con lo que queda sin facturar aun de la compra
        
        if (totalMielMantenidaEnConsignacion != null){
            
            tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
            tfPrecioUnitario.setText("0.00");
            tfSubTotal.setText("0.00");
            tfTambores.setText(String.valueOf(Math.round(0)));
            tfLotes.setText("0 LOTES");
            Double kilos = totalMielMantenidaEnConsignacion;
            Double tambores = kilos / 300;
            Double lotes = kilos / 21000;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" tambores");
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" lotes");
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfSubTotal.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        
        tfImporteTotalFactura.setText(" $ 0.00");
        
        cbTipoComprobante.setSelectedIndex(1);
        tfNumeroComprobante.setEditable(true);
        tfNumeroComprobante.setText("");

        tpCompraConsignacion.setSelectedIndex(0);
        
        //esto deberia no hacerlooO!!!! ver como puedo dejar de hacer esto
        PresupuestoProductor presupuestoProductor = new PresupuestoProductor();
        //almaceno en la variable global codigoPresupuesto el codigo del nuevo presupuesto (en caso de facturarse
        //la consignacion usando presupuestos)
        codigoPresupuesto = presupuestoProductor.mostrarIdPresupuestoProductor()+1;
        
        //inicializo variable que almacena la cantidad de miel facturada en el comprobante que se va a registrar
        totalMielFacturada = totalMielMantenidaEnConsignacion;
        importeComprobante = 0.00;
        
        //inicializo el contador del total de miel que no se facturara de la compra a consignacion que se esta facturando
        //el mismo empieza con la cantidad de miel sin facturar en la compra en consignacion
        //???????????????????????
        //totalMielMantenidaEnConsignacion = totalMielYaDescontadaCompra;
        
        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        
        tfCantidadKilos.requestFocus();
        
        //tengo que obtener la locacion donde se encuentra la miel que se va a devolver
        //ademas: es necesario realizar una distincion entre la miel depositada en una locacion
        //y la miel depositada en una locacion de productor
        //necesito un metodo que dandole el numero de comprobante me devuelva la locacion de la miel
        //detallada en el mismo
        StockRealMiel stock = new StockRealMiel();
        codigoLocacion = stock.obtenerLocacionMielADevolverOFacturar(codigoCompra);
        
    }

    public static void obtenerComprobantesRelacionadosCompraConsignacion(int compraConsignacion) {

        //Este metodo solo debe cargar todos los comprobantes relacionados a la compra que se esta por facturar
        //en un arreglo que tendra el codigo y el tipo de cada uno de ellos, para luego recorrerlo e ir obteniendo 
        //los items facturados, presupuestados y/o devueltos y a partir de esta informacion poder calcular
        //para cada uno de esos items encontrados la cantidad que se le facturo, presupuesto y devolvio en TOTAL.
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM comprobantes_relacionados_compra_credito WHERE codigo_compra_consignacion = '"+ compraConsignacion+"'");

            while (rs.next()) {
                
                ComprobantesRelacionadosCompraConsignacion item = new ComprobantesRelacionadosCompraConsignacion();
                
                item.setCodigoCompra(rs.getInt("codigo_compra_consignacion"));
                item.setCodigo_comprobante_relacionado(rs.getInt("codigo_comprobante_relacionado"));
                item.setTipo_comprobante_relacionado(rs.getString("tipo_comprobante_relacionado"));
                item.setCantidadMielAfectada(rs.getDouble("cantidad_miel_afectada"));
                
                comprobantesRelacionados.add(item);
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
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
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel1 = new javax.swing.JPanel();
        tpCompraConsignacion = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        tfDatosCompraConsignacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        tfTotalesMielCompra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfCantidadKilos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfSubTotal = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tfTambores = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfImporteTotalFactura = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cbTipoComprobante = new javax.swing.JComboBox<>();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR - CAM HONEY BROTHERS");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpCompraConsignacion.setBackground(new java.awt.Color(51, 84, 111));
        tpCompraConsignacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tpCompraConsignacion.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tpCompraConsignacionComponentAdded(evt);
            }
        });
        tpCompraConsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpCompraConsignacionMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("INFORMACION DE LA COMPRA EN CONSIGNACION:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SE ESTA FACTURANDO UNA COMPRA A CONSIGNACION CORRESPONDIENTE A:");

        tfDatosCompraConsignacion.setEditable(false);
        tfDatosCompraConsignacion.setBackground(new java.awt.Color(0, 0, 0));
        tfDatosCompraConsignacion.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfDatosCompraConsignacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ITEM FINANCIADO - INGRESE IMPORTE Y CANTIDAD A FACTURAR:");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        tfTotalesMielCompra.setEditable(false);
        tfTotalesMielCompra.setBackground(new java.awt.Color(255, 255, 255));
        tfTotalesMielCompra.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        tfTotalesMielCompra.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        tfTotalesMielCompra.setCaretColor(new java.awt.Color(255, 255, 255));
        tfTotalesMielCompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DESCRIPCION:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(204, 255, 255));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setText(" KGS. DE MIEL");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("KGS. DISPONIBLES:");

        tfCantidadKilos.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadKilos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadKilos.setForeground(new java.awt.Color(255, 255, 255));
        tfCantidadKilos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCantidadKilosActionPerformed(evt);
            }
        });
        tfCantidadKilos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCantidadKilosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCantidadKilosKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("PRECIO UNITARIO KG.:");

        tfPrecioUnitario.setBackground(new java.awt.Color(51, 84, 111));
        tfPrecioUnitario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfPrecioUnitario.setForeground(new java.awt.Color(255, 255, 255));
        tfPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioUnitarioKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("SUB TOTAL:");

        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        tfSubTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("CONVERSION DE KGS. A TAMBORES:");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(204, 255, 255));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("CONVERSION DE KGS. A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(204, 255, 255));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfTotalesMielCompra, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDatosCompraConsignacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCantidadKilos)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel27)
                            .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfPrecioUnitario)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSubTotal)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(7, 7, 7)
                .addComponent(tfDatosCompraConsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTotalesMielCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel26))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel23))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel28))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        tpCompraConsignacion.addTab("Datos del credito a facturar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA FACTURA:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA:");

        dcFechaFactura.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaFactura.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("N°:");

        tfNumeroComprobante.setEditable(false);
        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TOTAL DEL COMPROBANTE:");

        tfImporteTotalFactura.setEditable(false);
        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 0, 51));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfImporteTotalFactura.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        tfImporteTotalFactura.setCaretColor(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("COMPROBANTE:");
        jLabel22.setToolTipText("");

        cbTipoComprobante.setBackground(new java.awt.Color(36, 33, 33));
        cbTipoComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoComprobante.setForeground(new java.awt.Color(207, 207, 207));
        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURA A", "FACTURA C", "PRESUPUESTO" }));
        cbTipoComprobante.setPreferredSize(new java.awt.Dimension(136, 19));
        cbTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoComprobanteActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(7, 7, 7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        tpCompraConsignacion.addTab("Datos del comprobante de facturacion", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR COMPROBANTE");
        rdbrRegistrar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrarActionPerformed(evt);
            }
        });

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("SALIR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpCompraConsignacion)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpCompraConsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //CHEQUEOS NECESARIOS:
        
        //1) DATOS COMPLETOS: 
        //a) seleccion del tipo de comprobante
        //b) ingreso de numero de comprobante en caso de seleccionarse factura a o b
        //c) fechas de la factura y vencimiento de pago de la misma
        //d) importe total del comprobante
        
        totalMielMantenidaEnConsignacion = totalMielMantenidaEnConsignacion - totalMielFacturada;
        System.out.println(totalMielFacturada);
        System.out.println(totalMielMantenidaEnConsignacion);
        
        Boolean informacionFactura = (cbTipoComprobante.getSelectedItem() == "SELECCIONAR" || tfNumeroComprobante.getText().length() == 0 || importeComprobante == 0.00);

        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al comprobante se halla incompleta. Por favor ingresela correctamente.", "FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpCompraConsignacion.requestFocus();
            return;
            
        }
        
        //obtengo las fechas de factura y de vencimiento del pago de la misma
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaFactura.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;
        cal2 = dcFechaVencimiento.getCalendar();
        //ffecha de vencimiento de la factura
        d2 = cal2.get(Calendar.DAY_OF_MONTH);
        m2 = cal2.get(Calendar.MONTH);
        a2 = cal2.get(Calendar.YEAR) - 1900;
        
        //tengo que obtener la cantidad que se factura tambien

        //OBTENGO: que tipo de comprobante se escogio para la facturacion de la compra en consignacion
        //el numero de comprobante del mismo
        //y el importe de facturacion total del mismo
        String tipoComprobante = String.valueOf(cbTipoComprobante.getSelectedItem());
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());

        //Se procede al registro del comprobante correspondiente a la facturacion de la compra a consignacion
        //que puede ser: factura a, factura c o presupuesto
        
        //Se obtiene el numero de movimiento que tendra el comprobante de facturacion en la cuenta corriente con el productor
        //ademas en la variable codigoMovimientoCtaCteCompra ya tenemos almacenado el numero de movimiento correspndiente
        //a la compra en consignacion, ya que a la misma se le debe editar el estado en algunos casos (pasandolo a CANCELADO)   
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;

        //esto es para almacenar la relacion entre el comprobante de facturacion que se esta almacenando
        //y la compra en consignacion a la que esta afectando dicho comprobante
        ComprobantesRelacionadosCompraConsignacion comprobanteRelacionado = new ComprobantesRelacionadosCompraConsignacion();
        
        switch (tipoComprobante){
            
            case "FACTURA A":

                System.out.println("factura a");
                //se escogio como tipo de comprobante la "FACTURA A"
                //se registra la factura
                FacturaProductor facturaA = new FacturaProductor(numeroComprobante, tipoComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importeComprobante, totalMielFacturada);
                if (facturaA.registrarFacturaProductor(facturaA)){

                    //obtengo codigo de factura para utilizarlo en el almacenamiento de las relaciones
                    codigoFactura = facturaA.mostrarIdFacturaProductor();

                }
                
                break;

            case "FACTURA C":

                System.out.println("factura c");
                //se escogio como tipo de comprobante la "FACTURA B"
                //se registra la factura
                FacturaProductor facturaB = new FacturaProductor(numeroComprobante, tipoComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importeComprobante, totalMielFacturada);
                if (facturaB.registrarFacturaProductor(facturaB)){
                    
                    //obtengo codigo de factura para utilizarlo en el almacenamiento de las relaciones
                    codigoFactura = facturaB.mostrarIdFacturaProductor();

                }
                
                break;

            case "PRESUPUESTO":
                
                System.out.println("presupuesto");
                //se escogio como tipo de comprobante el "PRESUPUESTO"
                //se registra el presupuesto
                PresupuestoProductor presupuesto = new PresupuestoProductor(numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importeComprobante, totalMielFacturada);
                if (presupuesto.registrarPresupuestoProductor(presupuesto)){
                    
                    //obtengo codigo de presupuesto para utilizarlo en el almacenamiento de las relaciones
                    codigoFactura = presupuesto.mostrarIdPresupuestoProductor();

                }
                
                break;
                
            default:
                
                break;

        }

        //SE REGISTRA LA RELACION ENTRE EL COMPROBANTE Y LA COMPRA EN CONSIGNACION (para saber que cantidad de kgs.
        //se abonaron con este comprobante: factura a, b o presupuesto)
        comprobanteRelacionado.setCodigoCompra(codigoProductor);
        comprobanteRelacionado.setCodigoCompra(codigoCompra);
        comprobanteRelacionado.setCodigo_comprobante_relacionado(codigoFactura);
        comprobanteRelacionado.setTipo_comprobante_relacionado(tipoComprobante);
        comprobanteRelacionado.setCantidadMielAfectada(totalMielFacturada);
        comprobanteRelacionado.relacionarComprobanteACompraConsignacion(comprobanteRelacionado);

        //Ahora se guarda el movimiento correspondiente a la factura o presupuesto, en la cta. cte. de la empresa con el productor
        ctacteProductor.setCodigoProductor(codigoProductor);
        ctacteProductor.setCodigoMovimiento(codigoMovimientoCtaCte);
        ctacteProductor.setFechaMovimiento(new Date(a1, m1, d1));
        ctacteProductor.setDescripcionMovimiento(tipoComprobante);
        ctacteProductor.setComprobanteAsociado(codigoFactura);
        ctacteProductor.setNumeroComprobante(numeroComprobante);
        ctacteProductor.setCantidadMiel(totalMielFacturada);
        ctacteProductor.setDebe(importeComprobante);
        ctacteProductor.setHaber(0.00);
        ctacteProductor.setSaldo(importeComprobante);
        //se guarda con estado de comprobante como "PENDIENTE", ya que obviamente se acaba de facturar y esta impago
        ctacteProductor.setEstadoMovimiento("PENDIENTE");
        ctacteProductor.setObservacion("");
        ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);
        
        //ADEMAS:
        //Una vez cargado el comprobante de facturacion por la cantidad de miel que se haya decidido facturar
        //es necesario realizar el siguiente analisis:
        //1) Se factura la compra en consignacion completa
        //2) Se factura una parte de la compra en consignacion y el restante se mantiene en consignacion
        if (totalMielMantenidaEnConsignacion != 0.00){
            
            //significa que no se facturo toda la miel comprada en consignacion
            JOptionPane.showMessageDialog(null, "Se facturaron: "+totalMielFacturada+" kgs. de miel. Se mantendran en consignacion: "+totalMielMantenidaEnConsignacion+" kgs. de miel.", "FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{

            //Significa que se facturo toda la miel comprada en consignacion, se debe CANCELAR la compra en consignacion
            JOptionPane.showMessageDialog(null, "Se facturaron: "+totalMielFacturada+" kgs. de miel. La compra en consignacion ha sido cancelada.", "FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            
            //El estado de la compra en consignacion pasa a ser "CANCELADO", se debe editar tal movimiento en cta. cte.
            //tengo que obtener el codigoMovimientoCtaCteCompra pero de la compra en consignacion, para pder cancelarla!!!
            ctacteProductor.cancelarCompraConsignacion(codigoMovimientoCtaCteCompra, codigoProductor);

        }
        
        //ULTIMO PASO A REALIZAR:
        //El stock global de la empresa debe alterarse y reflejar el cambio realizado
        //ya que ahora se cuenta con menos miel "impaga" y mas miel "paga" debido a la facturacion de la misma
        //(las facturaciones de mil impaga restan el stock globlal de miel impaga de la empresa, y el stock de miel impaga
        //de la locacion en la que se encuentre la miel devuelta), y suman la miel paga de la misma locacion
        //SE DEBE RESTAR LA MISMA CANTIDAD DEL STOCK DE MIEL "IMPAGO", YA QUE LA MIEL
        //EN CONSIGNACION FACTURADA YA NO ES PARTE DEL STOCK DE MIEL "IMPAGO" DE LA EMPRESA

        StockRealMiel stockMiel = new StockRealMiel();
        stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
        stockMiel.setTipo_movimiento("COMPRA");
        stockMiel.setComprobante_asociado(tipoComprobante);
        stockMiel.setId_comprobante_asociado(codigoFactura);
        
        stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
        stockMiel.setCantidad_miel(totalMielFacturada);
        //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
        //no se almacenara nada!
        //debo obtener el codigo de la locacion a partir del nombre de la misma
        //escogido en el combo de locaciones disponibles

        stockMiel.setLocacion_miel(codigoLocacion);

        //chequeo si la miel a devolver se encuentra en la locacion de un productor
        Locacion locacion = new Locacion();
        String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

        if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

            //se trata de una facturacion en la cual la miel facturada se encuentra en la locacion
            //de un productor
            //cargo en el campo correspondiente el codigo del productor vendedor en esta compra
            stockMiel.setMiel_deposito_productor(codigoProductor);

            //teniendo este dato voy a poder llevar la cantidad de miel que hay en cada productor vendedor
            //viendola de manera global como "miel acopiada en locacion del productor"
            //pero pudiendo calcular y descontar o aumentar cuando sea necesario, la miel
            //comprada y depositada en cada uno de los productores correspondientes

            //cuando realice un traslado desde la locacion "locacion del productor"
            //voy a tener que descontar el stock global de dicha locacion
            //y discriminar y descontar consecuentemente la miel depositada
            //en la locacion del productor desde el cual se va a trasladar dicha miel

        }

        //se asigna a la compra el valor: FACTURADA, ya que es una compra con factura.
        stockMiel.setEstado_compra("SIN FACTURAR");

        //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
        stockMiel.registrarMovimientoStock(stockMiel);

        JOptionPane.showMessageDialog(null, "El comprobante ha sido registrado exitosamente.","FACTURACION DE COMPRA A CONSIGNACION EN PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        this.dispose();

    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

        //se limpian todos los arreglos
        compraOriginal.clear();
        itemsFinanciados.clear();
        itemsFacturados.clear();
        
        //elimino presupuestos y facturas que no se hayan confirmado
        PresupuestoProductor presupuesto = new PresupuestoProductor();
        presupuesto.eliminarPresupuestoProductor(codigoPresupuesto);
        //FacturaProductor factura = new FacturaProductor();
        //factura.eliminarFacturaProductor(codigoFactura);

        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tpCompraConsignacionComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpCompraConsignacionComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpCompraConsignacionComponentAdded

    private void cbTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoComprobanteActionPerformed

        // TODO add your handling code here:
        if (cbTipoComprobante.getSelectedItem().toString() == "PRESUPUESTO"){
            
            //se selecciona como metodo de facturacion presupuesto
            tfNumeroComprobante.setText(String.valueOf(codigoPresupuesto));
            tfNumeroComprobante.setEditable(false);
        

        }
        else{
        
            //se selecciona como metodo de facturacion fact a o fact b
            tfNumeroComprobante.setText("");
            tfNumeroComprobante.setEditable(true);
            tfNumeroComprobante.requestFocus();

        }
        
    }//GEN-LAST:event_cbTipoComprobanteActionPerformed

    private void tpCompraConsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpCompraConsignacionMouseClicked

        tfCantidadKilos.requestFocus();

    }//GEN-LAST:event_tpCompraConsignacionMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
    }//GEN-LAST:event_jPanel3MouseClicked

    private void tfCantidadKilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosActionPerformed
    }//GEN-LAST:event_tfCantidadKilosActionPerformed

    private void tfCantidadKilosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyReleased

        Double kilosAFacturar = 0.00;
        Double kilosDisponibles = totalMielMantenidaEnConsignacion;
        
        if (tfCantidadKilos.getText().length() != 0){

            kilosAFacturar = Double.parseDouble(tfCantidadKilos.getText().toString());

        }
        
        //no se debe permitir facturar mas kilos de los financiados en la compra en consignacion
        if (kilosAFacturar > kilosDisponibles){
            
            JOptionPane.showMessageDialog(null, "LA CANTIDAD INGRESADA ES MAYOR A LA CANTIDAD DISPONIBLE","FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
            totalMielFacturada = 0.00;
            
        }
        else{
            
            //no se debe permitir facturar cero kilos
            if (kilosAFacturar == 0){

                JOptionPane.showMessageDialog(null, "CANTIDAD INGRESADA INCORRECTA","FACTURACION DE COMPRA EN CONSIGNACION A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
                Double kilos = Double.parseDouble(tfCantidadKilos.getText());
                Double tambores = kilos / 300;
                tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
                Double lotes = kilos / 21000;
                tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
                Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
                Double importeFactura = kilos*precioUnitario;
                tfSubTotal.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
                tfImporteTotalFactura.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
                importeComprobante = importeFactura;
                totalMielFacturada = 0.00;

            }
            //se ha ingresado una cantidad correcta, mayor a 0, igual o menor a la cantidad disponible para facturar
            else{
                
                Double kilos = Double.parseDouble(tfCantidadKilos.getText());
                Double tambores = kilos / 300;
                tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
                Double lotes = kilos / 21000;
                tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
                Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
                Double importeFactura = kilos*precioUnitario;
                tfSubTotal.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
                tfImporteTotalFactura.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
                //voy guardando aca el importe final de la factura
                importeComprobante = importeFactura;
                //voy guardando aca lo que se ingresa como cantidad a facturar
                totalMielFacturada = kilos;
                //esto no habria que tocarlo???
                //totalMielMantenidaEnConsignacion = totalMielFinanciadaCompra - totalMielFacturada;

            }
            
        }
        
    }//GEN-LAST:event_tfCantidadKilosKeyReleased

    private void tfCantidadKilosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyTyped

        char c = evt.getKeyChar();

        if (tfCantidadKilos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfCantidadKilosKeyTyped

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased

        if (tfPrecioUnitario.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfSubTotal.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
            tfImporteTotalFactura.setText(" $ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));
            //voy guardando aca el importe final
            importeComprobante = importeFactura;

        }
        else{

            tfPrecioUnitario.setText("0.00");
            tfSubTotal.setText(" $ 0.00");
            tfImporteTotalFactura.setText(" $ 0.00");
            //voy guardando aca el importe final
            importeComprobante = 0.00;

        }
    }//GEN-LAST:event_tfPrecioUnitarioKeyReleased

    private void tfPrecioUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyTyped

        char c = evt.getKeyChar();

        if (tfPrecioUnitario.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }

    }//GEN-LAST:event_tfPrecioUnitarioKeyTyped

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
        tfImporteTotalFactura.setText(formateador.format(saldo));
        
    }
    
    public double calcularTotalStockLocacion(int codigoLocacion) {

        double mielComprada, mielVendida, mielRecibida, mielEnviada, saldoMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielComprada = stock.obtenerDetalleMielComprada(codigoLocacion);
        mielVendida = stock.obtenerDetalleMielVendida(codigoLocacion);
        mielRecibida = stock.obtenerDetalleMielRecibidaTraslado(codigoLocacion);
        mielEnviada = stock.obtenerDetalleMielEnviadaTraslado(codigoLocacion);
        
        saldoMiel = mielComprada + mielRecibida - mielVendida - mielEnviada;
        
        return saldoMiel;
        
    }

    public static ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where categoria = 'DEPOSITO DE ACOPIO PROPIO' or categoria = 'HOMOGENEIZACION' or categoria = 'DEPOSITO DE PRODUCTOR' order by codigo_locacion asc");
        
        try{
            
            int i = 0;
            while(rs.next()){
                
                int codigoLocacion = rs.getInt("codigo_locacion");
                String nombreLocacion = rs.getString("nombre_locacion");
                Locacion loc = new Locacion();
                loc.setCodigo_locacion(codigoLocacion);
                loc.setNombre_locacion(nombreLocacion);
                locaciones.add(loc);
                i++;

            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return locaciones;
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> cbTipoComprobante;
    public static com.toedter.calendar.JDateChooser dcFechaFactura;
    public static com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDatosCompraConsignacion;
    public static javax.swing.JTextField tfDescripcion;
    public static javax.swing.JTextField tfImporteTotalFactura;
    public static javax.swing.JTextField tfLotes;
    public static javax.swing.JTextField tfNumeroComprobante;
    public static javax.swing.JTextField tfPrecioUnitario;
    public static javax.swing.JTextField tfSubTotal;
    public static javax.swing.JTextField tfTambores;
    public static javax.swing.JTextField tfTotalesMielCompra;
    public static javax.swing.JTabbedPane tpCompraConsignacion;
    // End of variables declaration//GEN-END:variables
}
