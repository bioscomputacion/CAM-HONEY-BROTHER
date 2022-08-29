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
import ar.com.bioscomputacion.Reportes.VistaBoleta;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Caco
 */

public class FrmDevolucionCompraConsignacion extends javax.swing.JInternalFrame {
    
    //VARIABLES PARA RECORDAR EL MOVIMIENTO CORRESPONDIENTE A LA COMPRA EN CONSIGNACION
    public static int codigoMovimientoCtaCteCompra, codigoProductor, codigoCompra, tipoComprobante;
    public static Date fechaMovimientoCompra;
    public static String numeroComprobanteCompra, observacionCompra, nombreProductor;

    //para poder controlar y comparar cuanta miel se habia financiado en la compra a consignacion
    //y cuanta miel se facturara en realidad (quedando un saldo de miel sin facturar el cual debe ser devuelto al productor
    //o mantenido en stock siendo siguiendo con su estado de miel impaga)
    //public static Double totalMielFinanciadaCompra, totalMielDescontadaCompra, totalMielFinanciada, totalMielFacturada;
    public static Double totalMielFinanciadaCompra, totalMielMantenidaEnConsignacion, totalMielYaDescontadaCompra, totalMielDevuelta, totalMielComprobante;

    public static int codigoDevolucion, codigoMovimientoCtaCte, codigoLocacion; 
    
    //comprobantes relacionados a la compra en consignacion (facturas, presupuestos y devoluciones)
    public static List<ComprobantesRelacionadosCompraConsignacion> comprobantesRelacionados = new ArrayList<>();
    
    //apuntadores de las grillas
    int filaItemsFinanciados = -1;
    int filaItemsFacturados = -1;
    
    //conexion a la base de datos
    public static ConexionBD mysql = new ConexionBD();
    public static Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmDevolucionCompraConsignacion() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public static void inicializar() throws SQLException{
        
        //limpio los arreglos que se utilizaran
        comprobantesRelacionados.clear();
        
        Calendar cal = new GregorianCalendar();
        dcFechaDevolucion.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaDevolucion.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        //inicializo el campo cantidad de kilos a facturar con lo que queda sin facturar aun de la compra
        
        if (totalMielMantenidaEnConsignacion != null){
            
            tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
            tfTambores.setText(String.valueOf(Math.round(0)));
            tfLotes.setText("0 LOTES");
            Double kilos = totalMielMantenidaEnConsignacion;
            Double tambores = kilos / 300;
            Double lotes = kilos / 21000;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" tambores");
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" lotes");

        }
        
        //almaceno en la variable global codigoPresupuesto el codigo del nuevo presupuesto (en caso de facturarse
        //la consignacion usando presupuestos)
        DevolucionProductor devolucionProductor = new DevolucionProductor();
        codigoDevolucion = devolucionProductor.mostrarIdDevolucionProductor()+1;
        tfNumeroComprobante.setText(String.valueOf(codigoDevolucion));
        tfNumeroComprobante.setEditable(false);
        
        //inicializo variable que almacena la cantidad de miel devuelta en el comprobante que se va a registrar
        totalMielDevuelta = totalMielMantenidaEnConsignacion;
        totalMielComprobante = 0.00;        
        tfCantidadKilos.requestFocus();
        
        //tengo que obtener la locacion donde se encuentra la miel que se va a devolver
        //ademas: es necesario realizar una distincion entre la miel depositada en una locacion
        //y la miel depositada en una locacion de productor
        //necesito un metodo que dandole el numero de comprobante me devuelva la locacion de la miel
        //detallada en el mismo
        StockRealMiel stock = new StockRealMiel();
        codigoLocacion = stock.obtenerLocacionMielADevolverOFacturar(codigoCompra);
        System.out.println(codigoCompra);
        System.out.println(codigoLocacion);
        
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
        jLabel27 = new javax.swing.JLabel();
        tfTambores = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcFechaDevolucion = new com.toedter.calendar.JDateChooser();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR - CAM HONEY BROTHERS");
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
        jLabel2.setText("INFORMACION DE LA DEVOLUCION:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SE ESTA DEVOLVIENDO MIEL FINANCIADA POR:");

        tfDatosCompraConsignacion.setEditable(false);
        tfDatosCompraConsignacion.setBackground(new java.awt.Color(0, 0, 0));
        tfDatosCompraConsignacion.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfDatosCompraConsignacion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ITEM FINANCIADO - INGRESE IMPORTE Y CANTIDAD A DEVOLVER:");

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

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("N° DEVOLUCION:");

        tfNumeroComprobante.setEditable(false);
        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA:");

        dcFechaDevolucion.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaDevolucion.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaDevolucion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

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
                            .addComponent(jLabel18))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNumeroComprobante)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(dcFechaDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                                .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel26))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
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
                .addContainerGap())
        );

        tpCompraConsignacion.addTab("Datos del credito afectado por la devolucion", jPanel2);

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

        tpCompraConsignacion.getAccessibleContext().setAccessibleName("Datos del credito del que se devolvera miel:");

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
        //c) fechas de la devolucion
        //d) importe total del comprobante
        
        totalMielMantenidaEnConsignacion = totalMielMantenidaEnConsignacion - totalMielDevuelta;
        
        Boolean informacionFactura = (tfNumeroComprobante.getText().length() == 0);

        //chequea informacion de la devolucion, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al comprobante se halla incompleta. Por favor ingresela correctamente.", "DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpCompraConsignacion.requestFocus();
            return;
            
        }
        
        //obtengo las fechas de factura y de vencimiento del pago de la misma
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaDevolucion.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;
        
        //tengo que obtener la cantidad que se DEVUELVE tambien

        //OBTENGO: tipo de comprobante, el cual es DEVOLUCION
        //numero de comprobante del mismo
        //y la cantidad total de devolucion del mismo
        String tipoComprobante = "DEVOLUCION";
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());

        //Se procede al registro del comprobante correspondiente a la devolucion de miel de la compra a consignacion
        
        //Se obtiene el numero de movimiento que tendra el comprobante de devolucion en la cuenta corriente con el productor
        //ademas en la variable codigoMovimientoCtaCteCompra ya tenemos almacenado el numero de movimiento correspndiente
        //a la compra en consignacion, ya que a la misma se le debe editar el estado en algunos casos (pasandolo a CANCELADO)   
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;

        
        //el unico comprobante que se puede escoger es DEVOLUCION
        DevolucionProductor devolucion = new DevolucionProductor(numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), totalMielDevuelta);
        if (devolucion.registrarDevolucionProductor(devolucion)){

            //esto es para almacenar la relacion entre el comprobante de devolucion que se esta almacenando
            //y la compra en consignacion a la que esta afectando dicho comprobante
            ComprobantesRelacionadosCompraConsignacion comprobanteRelacionado = new ComprobantesRelacionadosCompraConsignacion();

            //SE REGISTRA LA RELACION ENTRE EL COMPROBANTE Y LA COMPRA EN CONSIGNACION (para saber que cantidad de kgs.
            //se abonaron con este comprobante: factura a, b o presupuesto)
            comprobanteRelacionado.setCodigoProductor(codigoProductor);
            comprobanteRelacionado.setCodigoCompra(codigoCompra);
            comprobanteRelacionado.setCodigo_comprobante_relacionado(codigoDevolucion);
            comprobanteRelacionado.setTipo_comprobante_relacionado(tipoComprobante);
            comprobanteRelacionado.setCantidadMielAfectada(totalMielDevuelta);
            comprobanteRelacionado.relacionarComprobanteACompraConsignacion(comprobanteRelacionado);

            //Ahora se guarda el movimiento correspondiente a la factura o presupuesto, en la cta. cte. de la empresa con el productor
            ctacteProductor.setCodigoProductor(codigoProductor);
            ctacteProductor.setCodigoMovimiento(codigoMovimientoCtaCte);
            ctacteProductor.setFechaMovimiento(new Date(a1, m1, d1));
            ctacteProductor.setDescripcionMovimiento(tipoComprobante);
            ctacteProductor.setComprobanteAsociado(codigoDevolucion);
            ctacteProductor.setNumeroComprobante(numeroComprobante);
            ctacteProductor.setCantidadMiel(totalMielDevuelta);
            ctacteProductor.setDebe(0.00);
            ctacteProductor.setHaber(0.00);
            ctacteProductor.setSaldo(0.00);
            //se guarda con estado de comprobante como "PENDIENTE", ya que obviamente se acaba de facturar y esta impago
            ctacteProductor.setEstadoMovimiento("CANCELADO");
            ctacteProductor.setObservacion("");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //ADEMAS:
            //Una vez cargado el comprobante de devolucion por la cantidad de miel que se haya decidido devolver
            //es necesario realizar el siguiente analisis:
            //1) Se devuelve la compra en consignacion completa
            //2) Se devuelve una parte de la compra en consignacion y el restante se mantiene en consignacion
            
            if (totalMielMantenidaEnConsignacion != 0.00){

                //significa que no se devolvio toda la miel comprada en consignacion
                JOptionPane.showMessageDialog(null, "Se devolvieron: "+totalMielDevuelta+" kgs. de miel. Se mantendran en consignacion: "+totalMielMantenidaEnConsignacion+" kgs. de miel.", "DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

            }
            else{

                //Significa que se devolvio toda la miel comprada en consignacion, se debe CANCELAR la compra en consignacion
                JOptionPane.showMessageDialog(null, "Se devolvieron: "+totalMielDevuelta+" kgs. de miel. La compra en consignacion ha sido cancelada.", "DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);

                //El estado de la compra en consignacion pasa a ser "CANCELADO", se debe editar tal movimiento en cta. cte.
                //tengo que obtener el codigoMovimientoCtaCteCompra pero de la compra en consignacion, para pder cancelarla!!!
                //ctacteProductor.cancelarCompraConsignacion(codigoMovimientoCtaCteCompra, codigoProductor);

            }

        }
        
        //ULTIMO PASO A REALIZAR:
        //El stock global de la empresa debe alterarse y reflejar el cambio realizado
        //ya que ahora se cuenta con menos miel "impaga" debido a la devolucion de la misma
        //(las devoluciones restan el stock globlal de miel impaga de la empresa, y el stock de miel impaga
        //de la locacion en la que se encuentre la miel devuelta)
        //SE DEBE RESTAR LA MISMA CANTIDAD DEL STOCK DE MIEL "IMPAGO", YA QUE LA MIEL
        //EN CONSIGNACION DEVUELTA YA NO ES PARTE DEL STOCK DE MIEL "IMPAGO" DE LA EMPRESA

        StockRealMiel stockMiel = new StockRealMiel();
        stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
        stockMiel.setTipo_movimiento("DEVOLUCION");
        stockMiel.setComprobante_asociado(tipoComprobante);
        stockMiel.setId_comprobante_asociado(codigoDevolucion);
        
        stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
        stockMiel.setCantidad_miel(totalMielDevuelta);
        //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
        //no se almacenara nada!
        //debo obtener el codigo de la locacion a partir del nombre de la misma
        //escogido en el combo de locaciones disponibles

        stockMiel.setLocacion_miel(codigoLocacion);

        //chequeo si la miel a devolver se encuentra en la locacion de un productor
        Locacion locacion = new Locacion();
        String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

        if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

            //se trata de una devolucion en la cual la miel devuelta se encuentra en la locacion
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

        JOptionPane.showMessageDialog(null, "El comprobante ha sido registrado exitosamente.","DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        
        java.util.Locale locale = new Locale("es", "CL");
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("reporteDevolucion.jasper"));

            Map parametro = new HashMap<String, Integer>();

            parametro.put("codigo_devolucion", codigoDevolucion);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
            parametro.put(JRParameter.REPORT_LOCALE, locale);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.show();

            // JasperPrintManager.printReport( jp, true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }

        FrmCtaCteConProductor.mostrarCtaCteProductor(codigoProductor);
        FrmCtaCteConProductor.ocultarColumnasCtaCte();
        this.dispose();

    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        //elimino presupuestos y facturas que no se hayan confirmado
        PresupuestoProductor presupuesto = new PresupuestoProductor();
        presupuesto.eliminarPresupuestoProductor(codigoDevolucion);
        //FacturaProductor factura = new FacturaProductor();
        //factura.eliminarFacturaProductor(codigoFactura);

        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tpCompraConsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpCompraConsignacionMouseClicked

        tfCantidadKilos.requestFocus();
    }//GEN-LAST:event_tpCompraConsignacionMouseClicked

    private void tpCompraConsignacionComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tpCompraConsignacionComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tpCompraConsignacionComponentAdded

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

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

    private void tfCantidadKilosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyReleased

        Double kilosADevolver = 0.00;
        Double kilosDisponibles = totalMielMantenidaEnConsignacion;

        if (tfCantidadKilos.getText().length() != 0){

            kilosADevolver = Double.parseDouble(tfCantidadKilos.getText().toString());

        }

        //no se debe permitir devolver mas kilos de los financiados en la compra en consignacion
        if (kilosADevolver > kilosDisponibles){

            JOptionPane.showMessageDialog(null, "LA CANTIDAD INGRESADA ES MAYOR A LA CANTIDAD DISPONIBLE","DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
            tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
            totalMielDevuelta = 0.00;

        }
        else{

            //no se debe permitir devolver cero kilos
            if (kilosADevolver == 0){

                JOptionPane.showMessageDialog(null, "CANTIDAD INGRESADA INCORRECTA","DEVOLUCION DE COMPRA EN CONSIGNACION A PRODUCTOR",JOptionPane.ERROR_MESSAGE);
                tfCantidadKilos.setText(String.valueOf(totalMielMantenidaEnConsignacion));
                Double kilos = Double.parseDouble(tfCantidadKilos.getText());
                Double tambores = kilos / 300;
                tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
                Double lotes = kilos / 21000;
                tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
                totalMielDevuelta = 0.00;

            }
            //se ha ingresado una cantidad correcta, mayor a 0, igual o menor a la cantidad disponible para devolver
            else{

                Double kilos = Double.parseDouble(tfCantidadKilos.getText());
                Double tambores = kilos / 300;
                tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
                Double lotes = kilos / 21000;
                tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
                //voy guardando aca el importe final de la factura
                //voy guardando aca lo que se ingresa como cantidad a facturar
                totalMielDevuelta = kilos;
                //esto no habria que tocarlo???
                //totalMielMantenidaEnConsignacion = totalMielFinanciadaCompra - totalMielFacturada;

            }

        }
    }//GEN-LAST:event_tfCantidadKilosKeyReleased

    private void tfCantidadKilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosActionPerformed

    }//GEN-LAST:event_tfCantidadKilosActionPerformed

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
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
    public static com.toedter.calendar.JDateChooser dcFechaDevolucion;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTextField tfCantidadKilos;
    public static javax.swing.JTextField tfDatosCompraConsignacion;
    public static javax.swing.JTextField tfDescripcion;
    public static javax.swing.JTextField tfLotes;
    public static javax.swing.JTextField tfNumeroComprobante;
    public static javax.swing.JTextField tfTambores;
    public static javax.swing.JTextField tfTotalesMielCompra;
    public static javax.swing.JTabbedPane tpCompraConsignacion;
    // End of variables declaration//GEN-END:variables
}
