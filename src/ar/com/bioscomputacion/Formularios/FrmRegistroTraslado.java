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

    //cargo todas las locaciones registradas en el sistema
    public List<Locacion> listaLocaciones = new ArrayList<>();
    
    //cargo todas las locaciones de productores que tienen o no stock de miel en deposito
    //las obtengo al cargar el arreglo anterior, cada vez que encuentro la locacion 0, chequeo el codigo
    //registrado en el campo miel_deposito_productor
    public List<Locacion> listaLocacionesDeProductores = new ArrayList<>();
    
    Double saldoMielOrigen;
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacionOrigen, codigoLocacionDestino, codigoLocacionDepositoPoductor;
    
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
        
        listaLocaciones = cargarListaLocaciones();
        listaLocacionesDeProductores = cargarListaLocacionesProductores();
        
        for (int i = 0; i<listaLocaciones.size(); i++){
            
            cbLocacionOrigen.addItem(listaLocaciones.get(i).getNombre_locacion());
            cbLocacionDestino.addItem(listaLocaciones.get(i).getNombre_locacion());
            
        }
        
        cbLocacionOrigen.setSelectedIndex(0);
        cbLocacionDestino.setSelectedIndex(0);
        dcFechaTraslado.requestFocus();
        
        
    }

    public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion order by codigo_locacion asc");
        
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
    
    public ArrayList<Locacion> cargarListaLocacionesProductores() throws SQLException{
        
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select nombre_locacion from locacion where codigo_locacion =1'" + codigoLocacionMielDeposita+ "'order by codigo_locacion asc");
        
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

    public ArrayList<String> cargarComboLocaciones() throws SQLException{
        
        ArrayList<String> locaciones = new ArrayList<String>();
        
        //el primer item debe ser "SELECCIONAR"
        locaciones.add("SELECCIONAR");
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select nombre_locacion from locacion order by codigo_locacion desc");
        
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
    
    public double calcularTotalStockLocacionDepositoProductor(int codigoLocacion) {

        double mielCompradaEnDeposito, mielVendidaDesdeDeposito, mielTrasladadaDesdeLocacionProductor, mielTrasladadaALocacionProductor, saldoMielLocacionProductor = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielCompradaEnDeposito = stock.obtenerDetalleMielComprada(codigoLocacion);
        mielVendidaDesdeDeposito = stock.obtenerDetalleMielVendida(codigoLocacion);
        mielTrasladadaDesdeLocacionProductor = stock.obtenerDetalleMielRecibidaTraslado(codigoLocacion);
        mielTrasladadaALocacionProductor = stock.obtenerDetalleMielEnviadaTraslado(codigoLocacion);
        
        saldoMielLocacionProductor = mielCompradaEnDeposito + mielVendidaDesdeDeposito - mielTrasladadaALocacionProductor - mielTrasladadaDesdeLocacionProductor;
        
        return saldoMielLocacionProductor;
        
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
        lStockOrigen = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lStockDestino = new javax.swing.JLabel();
        cbLocacionesDeProductores = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        lStockOrigen1 = new javax.swing.JLabel();

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
        cbDescripcionItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "KG. DE MIEL", "TAMBOR DE MIEL X 300 KGS.", "LOTE DE MIEL X 70 TAMBORES", "LOTE DE MIEL X 71 TAMBORES" }));
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

        lStockOrigen.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockOrigen.setForeground(new java.awt.Color(255, 204, 0));
        lStockOrigen.setText("0.00");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 51));
        jLabel25.setText("STOCK FISICO DISPONIBLE:");
        jLabel25.setToolTipText("");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 51));
        jLabel26.setText("STOCK FISICO DISPONIBLE:");
        jLabel26.setToolTipText("");

        lStockDestino.setBackground(new java.awt.Color(255, 255, 255));
        lStockDestino.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockDestino.setForeground(new java.awt.Color(255, 204, 0));
        lStockDestino.setText("0.00");

        cbLocacionesDeProductores.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDeProductores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionesDeProductores.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionesDeProductores.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDeProductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDeProductoresActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 51));
        jLabel27.setText("STOCK FISICO DISPONIBLE:");
        jLabel27.setToolTipText("");

        lStockOrigen1.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen1.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockOrigen1.setForeground(new java.awt.Color(255, 204, 0));
        lStockOrigen1.setText("0.00");

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
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockOrigen))
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockDestino)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(cbLocacionesDeProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStockOrigen1)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(lStockOrigen))
                    .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(lStockOrigen1))
                    .addComponent(cbLocacionesDeProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(lStockDestino))
                    .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocaciones.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            double saldoMiel = 0.00;
            saldoMiel = calcularTotalStockLocacion(codigoLocacionOrigen);
            saldoMielOrigen = saldoMiel;
            lStockOrigen.setText(String.valueOf(saldoMiel));
            
        }

    }//GEN-LAST:event_cbLocacionOrigenActionPerformed

    private void cbLocacionDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionDestinoActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        if (cbLocacionDestino.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionDestino = listaLocaciones.get(cbLocacionDestino.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            double saldoMiel = 0.00;
            saldoMiel = calcularTotalStockLocacion(codigoLocacionDestino);
            lStockDestino.setText(String.valueOf(saldoMiel));
            
        }
            
    }//GEN-LAST:event_cbLocacionDestinoActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura

        Boolean informacionTraslado = (cbMotivoTraslado.getSelectedItem() == "SELECCIONAR" || cbDescripcionItem.getSelectedItem() == "SELECCIONAR" || tfCantidad.getText().length() == 0 || cbLocacionOrigen.getSelectedItem() == "SELECCIONAR" || cbLocacionDestino.getSelectedItem() == "SELECCIONAR");
        String descripcionItemtraslado= cbDescripcionItem.getSelectedItem().toString();
        Double cantidadTraslado = 0.00;

        /*KG. DE MIEL
        TAMBOR DE MIEL X 300 KGS.
        LOTE DE MIEL X 70 TAMBORES
        LOTE DE MIEL X 71 TAMBORES*/

        //almaceno y convierto a kgs.
        
        switch (descripcionItemtraslado){
            
            case "KG. DE MIEL":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString());
                System.out.println(cantidadTraslado);
                break;

            case "TAMBOR DE MIEL X 300 KGS.":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*300.00;
                System.out.println(cantidadTraslado);
                break;

            case "LOTE DE MIEL X 70 TAMBORES":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*21000.00;
                System.out.println(cantidadTraslado);
                break;

            case "LOTE DE MIEL X 71 TAMBORES":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*21300.00;
                System.out.println(cantidadTraslado);
                break;

        }

        //informacion incompleta
        if (informacionTraslado) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al traslado se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            dcFechaTraslado.requestFocus();
            return;
            
        }
        else{
            
            //se intenta trasladar 0 kgs. de miel
            if (Double.parseDouble(tfCantidad.getText().toString()) == 0.00){
                
                JOptionPane.showMessageDialog(null, "No se puede trasladar un item con cantidad igual o menor a cero unidadades. Por favor ingrese la cantidad de miel a trasladar correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
                tfCantidad.requestFocus();
                return;
                
            }
            else{
                
                //se intenta trasladar mayor cantidad que la existente
                if (saldoMielOrigen < Double.parseDouble(tfCantidad.getText().toString())){
                    
                    JOptionPane.showMessageDialog(null, "No se puede trasladar mas cantidad de miel de la que existe fisicamente en la locacion origen. Por favor ingrese la cantidad de miel a trasladar correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
                    tfCantidad.requestFocus();
                    return;
                    
                }
                else{
                    
                    //se intenta trasladar miel de una locacion a si misma!
                    if (codigoLocacionOrigen == codigoLocacionDestino){
                        
                        JOptionPane.showMessageDialog(null, "La locacion origen debe ser distinta a la locacion destino. Por favor seleccione correctamente las mismas.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
                        cbLocacionOrigen.requestFocus();
                        return;
                        
                    
                    }
                    //informacion completa y correcta, se procede al registro de toda la informacion
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
                        int origenTraslado = codigoLocacionOrigen;
                        int destinoTraslado = codigoLocacionDestino;
                        Date fechaTraslado = new Date(a1, m1, d1);

                        //se procede al registro del traslado
                        Traslado traslado = new Traslado(descripcionItemtraslado, cantidadTraslado, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);
                        traslado.registrarTrasladoMiel(traslado);
                        //obtengo el codigo del traslado recien dado de alta para almacenarlo como comprobante asociado
                        //en la tabla stock real de miel
                        int codigoTraslado = traslado.mostrarIdTraslado();

                        //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, PUDIENDO VARIAR O NO EL STOCK GLOBAL
                        //LO QUE SI DEBE VARIAR ES EL STOCK EN CADA UNA DE LAS LOCACIONES INVOLUCRADAS EN EL TRASLADO:
                        //DEBE DESCONTARSE EL STOCK TRASLADADO DE LA LOCACION ORIGEN Y DEBE INCREMENTARSE EL STOCK TRASLADADO
                        //EN LA LOCACION DESTINO
                        //EL STOCK GLOBAL VA A VARIAR CUANDO LA LOCACION ORIGEN SEA FISCALIZACION Y LA LOCACION DESTINO SEA EMBARQUE
                        //(EN ESTE CASO SE DEBE DISMINUIR EL STOCK GLOBAL YA QUE ES MIEL VENDIDA)
                        //CASO CONTRARIO, SE MUEVEN LOS STOCKS EN LAS LOCACIONES PERO SIN TOCARSE EL STOCK GLOBAL

                        //se registra el traslado para locacion origen
                        StockRealMiel stockMiel = new StockRealMiel();
                        stockMiel.setFecha_movimiento(fechaTraslado);
                        //Cuando se trata de un traslado puede ser traslado origen o traslado destino
                        stockMiel.setTipo_movimiento("TRASLADO - ORIGEN");
                        stockMiel.setComprobante_asociado("TRASLADO");
                        stockMiel.setNumero_comprobante_asociado(codigoTraslado);
                        stockMiel.setCantidad_miel(cantidadTraslado);
                        stockMiel.setLocacion_miel(origenTraslado);
                        
                        if (origenTraslado == 0){
                            
                            //se trata de un traslado de miel stockeada en la locacion de algun productor
                            //se debe descontar el stock global de la locacion "LOCACION DEL PRODUCTOR"
                            //y se debe descontar el stock de la locacion desde la que se traslada
                            //cuyo codigo lo obtenemos a partir del nombre seleccionado en el combo de las locaciones
                            //que o bien: figuran con stock mayor a 0 o figura entre todas las locaciones
                            //cuya categoria deberia llamarse "LOCACION DE PRODUCTORES" (ya que son las unicas locaciones
                            //que pueden tener miel comprada aun depositada en ellas
                            
                            //cargo en el campo miel_deposito_productor el codigo de la locacion origen seleccionada
                            //en el segundo combo
                            //y como el tipo de movimiento sera TRASLADO - ORIGEN
                            //la cantidad de miel sera descontada
                            stockMiel.setMiel_deposito_productor(codigoLocacionDepositoPoductor);
                            
                            
                        }
                        else{
                            
                            //ver bien desde cuales y a cuales locaciones se puede trasladar stock
                        
                        }
                        
                        //VER COMO HACER PARA REGISTRAR EL INCREMENTO DE LA COMPRA
                        //O EL DECREMENTO DE LA VENTA SI LA MIEL SE SACA DE LA LOCACION 0,
                        //O SEA QUE ES MIEL QUE SE DEBE DESCONTAR O AUMENTAR EN LA LOCACION 0
                        //PERO TAMBIEN SE DEBE ALTERAR DICHO STOCK EN LA LOCACION DESDE LA QUE SE SACA O LLEVA LA MIEL

                        stockMiel.registrarMovimientoStock(stockMiel);
                        //se registra el traslado para locacion destino
                        stockMiel.setTipo_movimiento("TRASLADO - DESTINO");
                        stockMiel.setLocacion_miel(destinoTraslado);
                        stockMiel.registrarMovimientoStock(stockMiel);

                        this.dispose();

                    }
                
                }
                
            }
            
        }
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "CANCELAR EL TRASLADO???!!!!");
        this.dispose();
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void cbLocacionesDeProductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDeProductoresActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        /*
        if (cbLocacionesDeProductores.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado y muestro el stock disponible para trasladar
            codigoLocacionDepositoPoductor = listaLocaciones.get(cbLocacionesDeProductores.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            double saldoMiel = 0.00;
            saldoMiel = calcularTotalStockLocacionDepositoProductor(codigoLocacionOrigen);
            saldoMielOrigen = saldoMiel;
            lStockOrigen.setText(String.valueOf(saldoMiel));
            
        }
        */
        codigoLocacionDepositoPoductor = 11;

    }//GEN-LAST:event_cbLocacionesDeProductoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
    public javax.swing.JComboBox<String> cbLocacionDestino;
    public javax.swing.JComboBox<String> cbLocacionOrigen;
    public javax.swing.JComboBox<String> cbLocacionesDeProductores;
    public javax.swing.JComboBox<String> cbMotivoTraslado;
    public com.toedter.calendar.JDateChooser dcFechaTraslado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lStockDestino;
    private javax.swing.JLabel lStockOrigen;
    private javax.swing.JLabel lStockOrigen1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTextField tfCantidad;
    // End of variables declaration//GEN-END:variables
}
