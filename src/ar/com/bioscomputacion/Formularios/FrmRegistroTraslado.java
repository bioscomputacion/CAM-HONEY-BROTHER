/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.Persona;
import ar.com.bioscomputacion.Funciones.Productor;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmRegistroTraslado extends javax.swing.JInternalFrame {

    //cargo todas las locaciones registradas en el sistema
    //de ahi voy a cargar en el combo el nombre de las mismas
    public List<Locacion> listaLocacionesOrigen = new ArrayList<>();
    public List<Locacion> listaLocacionesDestino = new ArrayList<>();
    
    //aca cargo todos los productores registrados en el sistema
    //de ahi voy a cargar en el combo el nombre de los mismos
    public List<Productor> listaProductores = new ArrayList<>();
    
    Double saldoMielOrigen, saldoMielDepositoProductorSeleccionado;
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacionOrigen, codigoLocacionDestino, codigoProductor;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroTraslado() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public void limpiarCampos(){

        cbMotivoTraslado.setSelectedIndex(0);
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidad.setText("");
        dcFechaTraslado.requestFocus();

    }

    public void inicializar() throws SQLException{
        
        saldoMielOrigen = 0.00;
        saldoMielDepositoProductorSeleccionado = 0.00;
        
        Calendar cal = new GregorianCalendar();
        dcFechaTraslado.setCalendar(cal);
        
        limpiarCampos();
        
    }

    public ArrayList<Locacion> cargarListaLocaciones(String categoria) throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where categoria = '"+ categoria +"' order by codigo_locacion asc");
        
        try{
            
            while(rs.next()){
                
                int codigoLocacion = rs.getInt("codigo_locacion");
                String nombreLocacion = rs.getString("nombre_locacion");
                Locacion loc = new Locacion();
                loc.setCodigo_locacion(codigoLocacion);
                loc.setNombre_locacion(nombreLocacion);
                locaciones.add(loc);
                
            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return locaciones;
        
    }
    
    public ArrayList<Productor> cargarListaLocacionesProductores() throws SQLException{
        
        
        int codigoGenerico = 38;
        ArrayList<Productor> productores = new ArrayList<Productor>();
        Productor productor = new Productor();
        productor.setCod_productor(-1);
        productor.setNombre("SELECCIONAR");
        productores.add(productor);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select cod_productor, nombre from productor p join persona r on p.cod_persona = r.cod_persona where p.cod_productor > '" + codigoGenerico + "'order by p.cod_productor asc");
        
        try{
            
            int i=0;
            while(rs.next()){
                
                int codigoProductor = rs.getInt("cod_productor");
                String nombreProductor = rs.getString("nombre");
                Productor prod = new Productor();
                prod.setCod_productor(codigoProductor);
                prod.setNombre(nombreProductor);
                productores.add(prod);  
                
                i++;
                
            }
            
        }
        catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
        return productores;
        
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
    
    public double calcularTotalStockLocacionDepositoProductor(int codigoProductor) {

        double mielCompradaEnDepositoProductor, mielTrasladadaDesdeLocacionProductor, saldoMielLocacionProductor = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielCompradaEnDepositoProductor = stock.obtenerDetalleMielCompradaDepositadaLocacionProductor(codigoProductor);
        mielTrasladadaDesdeLocacionProductor = stock.obtenerDetalleMielTrasladadaDesdeLocacionProductor(codigoProductor);
        
        saldoMielLocacionProductor = mielCompradaEnDepositoProductor - mielTrasladadaDesdeLocacionProductor;
        
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
        cbProductores = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        lStockDepositoProductor = new javax.swing.JLabel();
        lStockOrigen1 = new javax.swing.JLabel();
        lStockOrigen2 = new javax.swing.JLabel();
        lStockProductor = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
        cbMotivoTraslado.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbMotivoTraslado.setForeground(new java.awt.Color(207, 207, 207));
        cbMotivoTraslado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "TRASLADO DE MIEL ENTRE LOCACIONES PROPIAS", "TRASLADO DE MIEL DESDE LOCACION DEL PRODUCTOR A LOCACION PROPIA", "TRASLADO DESDE LOCACION PROPIA A HOMOGENEIZACION", "TRASLADO DESDE LOCACION DEL PRODUCTOR A HOMOGENEIZACION", "TRASLADO DESDE HOMOGENEIZACION A LOCACION PROPIA", "TRASLADO DESDE HOMOGENEIZACION A FISCALIZACION", "TRASLADO DESDE FISCALIZACION A EMBARQUE", "TRASLADO DESDE EMBARQUE A FISCALIZACION" }));
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
        cbLocacionOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocacionOrigenMouseClicked(evt);
            }
        });
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
        cbLocacionDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocacionDestinoMouseClicked(evt);
            }
        });
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
        rsbrCancelar.setText("SALIR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        lStockOrigen.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockOrigen.setForeground(new java.awt.Color(255, 204, 0));
        lStockOrigen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockOrigen.setText("0.00");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("VALIDAR STOCK:");
        jLabel25.setToolTipText("");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("VALIDAR STOCK:");
        jLabel26.setToolTipText("");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        lStockDestino.setBackground(new java.awt.Color(255, 255, 255));
        lStockDestino.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockDestino.setForeground(new java.awt.Color(255, 255, 102));
        lStockDestino.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockDestino.setText("0.00");

        cbProductores.setBackground(new java.awt.Color(36, 33, 33));
        cbProductores.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbProductores.setForeground(new java.awt.Color(207, 207, 207));
        cbProductores.setPreferredSize(new java.awt.Dimension(136, 19));
        cbProductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoresActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("VALIDAR STOCK:");
        jLabel27.setToolTipText("");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        lStockDepositoProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockDepositoProductor.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockDepositoProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockDepositoProductor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockDepositoProductor.setText("0.00");

        lStockOrigen1.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen1.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockOrigen1.setForeground(new java.awt.Color(255, 204, 0));
        lStockOrigen1.setText("KGS.");

        lStockOrigen2.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen2.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockOrigen2.setForeground(new java.awt.Color(255, 255, 102));
        lStockOrigen2.setText("KGS.");

        lStockProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockProductor.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        lStockProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockProductor.setText("KGS.");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("PRODUCTORES:");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("VER:");
        jLabel28.setToolTipText("");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
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
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15)
                                    .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lStockOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockOrigen1)))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbLocacionDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lStockDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockOrigen2))))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbProductores, 0, 265, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(lStockDepositoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockProductor)))
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
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lStockDestino)
                    .addComponent(lStockOrigen)
                    .addComponent(jLabel25)
                    .addComponent(lStockOrigen1)
                    .addComponent(lStockOrigen2))
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(7, 7, 7)
                        .addComponent(cbProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lStockProductor)
                                    .addComponent(lStockDepositoProductor)
                                    .addComponent(jLabel27)))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel28)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        try {
            
            listaLocacionesOrigen.clear();
            listaLocacionesDestino.clear();
            cbLocacionOrigen.removeAllItems();
            cbLocacionDestino.removeAllItems();
            lStockOrigen.setText("0.00");
            lStockDestino.setText("0.00");
            lStockDepositoProductor.setText("0.00");
            
            //de acuerdo a lo que se seleccione en el combo del motivo del traslado
            //es lo que se va a poder visualizar en los combos origen y destino del traslado
            String motivoTraslado = cbMotivoTraslado.getSelectedItem().toString();

            switch (motivoTraslado){
                
                case "SELECCIONAR":

                    //combos origen y destino deben mostrar solo la palabra SELECCIONAR
                    
                    cbLocacionOrigen.addItem("SELECCIONAR");
                    cbLocacionDestino.addItem("SELECCIONAR");
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;

                case "TRASLADO DE MIEL ENTRE LOCACIONES PROPIAS":

                    //combos origen y destino deben mostrar solo locaciones de la empresa
                    
                    listaLocacionesOrigen = cargarListaLocaciones("DEPOSITO DE ACOPIO PROPIO");
                    listaLocacionesDestino = cargarListaLocaciones("DEPOSITO DE ACOPIO PROPIO");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                       cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DE MIEL DESDE LOCACION DEL PRODUCTOR A LOCACION PROPIA":
                    
                    //combo origen solo locaciones de productores y combo destino solo locaciones de la empresa

                    listaLocacionesOrigen = cargarListaLocaciones("DEPOSITO DE PRODUCTOR");
                    listaLocacionesDestino = cargarListaLocaciones("DEPOSITO DE ACOPIO PROPIO");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    //TENGO QUE HABILITAR TAMBIEN EL TERCER COMBO, QUE MUESTRA LOS PRODUCTORES Y EL SALDO
                    //DE MIEL ACOPIADO EN LAS INSTALACIONES DE CADA UNO DE ELLOS
                    //PERO DICHA HABILITACION SE PRODUCE AL SELECCIONAR "MIEL EN DEPOSITO" EN EL COMBO ORIGEN
                    break;
                    
                case "TRASLADO DESDE LOCACION PROPIA A HOMOGENEIZACION":
                    
                    //combo origen solo locaciones de la empresa y combo destino solo locaciones de homogeneizacion

                    listaLocacionesOrigen = cargarListaLocaciones("DEPOSITO DE ACOPIO PROPIO");
                    listaLocacionesDestino = cargarListaLocaciones("HOMOGENEIZACION");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE LOCACION DEL PRODUCTOR A HOMOGENEIZACION":
                    
                    //combo origen solo locaciones de productores y combo destino solo locaciones de homogeneizacion

                    listaLocacionesOrigen = cargarListaLocaciones("DEPOSITO DE PRODUCTOR");
                    listaLocacionesDestino = cargarListaLocaciones("HOMOGENEIZACION");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    //TENGO QUE HABILITAR TAMBIEN EL TERCER COMBO, QUE MUESTRA LOS PRODUCTORES Y EL SALDO
                    //DE MIEL ACOPIADO EN LAS INSTALACIONES DE CADA UNO DE ELLOS
                    //PERO DICHA HABILITACION SE PRODUCE AL SELECCIONAR "MIEL EN DEPOSITO" EN EL COMBO ORIGEN
                    break;
                    
                case "TRASLADO DESDE HOMOGENEIZACION A LOCACION PROPIA":
                    
                    //combo origen solo locaciones de homogeneizacion y combo destino solo locaciones de la empresa

                    listaLocacionesOrigen = cargarListaLocaciones("HOMOGENEIZACION");
                    listaLocacionesDestino = cargarListaLocaciones("DEPOSITO DE ACOPIO PROPIO");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE HOMOGENEIZACION A FISCALIZACION":
                    
                    //combo origen solo locaciones de homogeneizacion y combo destino solo locaciones de fiscalizacion

                    listaLocacionesOrigen = cargarListaLocaciones("HOMOGENEIZACION");
                    listaLocacionesDestino = cargarListaLocaciones("FISCALIZACION");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE FISCALIZACION A EMBARQUE":
                    
                    //combo origen solo locaciones de fiscalizacion y combo destino solo locaciones de embarque

                    listaLocacionesOrigen = cargarListaLocaciones("FISCALIZACION");
                    listaLocacionesDestino = cargarListaLocaciones("EMBARQUE");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE EMBARQUE A FISCALIZACION":
                    
                    //combo origen solo locaciones de embarque y combo destino solo locaciones de fiscalizacion

                    listaLocacionesOrigen = cargarListaLocaciones("EMBARQUE");
                    listaLocacionesDestino = cargarListaLocaciones("FISCALIZACION");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                default :
                    
                    //ninguno de los casos
                    break;
                    
            }
            
            cbLocacionOrigen.setSelectedIndex(0);
            cbLocacionDestino.setSelectedIndex(0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmRegistroTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbMotivoTrasladoActionPerformed

    private void cbDescripcionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDescripcionItemActionPerformed

    private void cbLocacionOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionOrigenActionPerformed
    }//GEN-LAST:event_cbLocacionOrigenActionPerformed

    private void cbLocacionDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionDestinoActionPerformed
    }//GEN-LAST:event_cbLocacionDestinoActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //REGISTRO DE TRASLADO
        
        Boolean informacionTraslado = (cbMotivoTraslado.getSelectedItem() == "SELECCIONAR" || cbDescripcionItem.getSelectedItem() == "SELECCIONAR" || tfCantidad.getText().length() == 0 || cbLocacionOrigen.getSelectedItem() == "SELECCIONAR" || cbLocacionDestino.getSelectedItem() == "SELECCIONAR");
        String descripcionItemtraslado= cbDescripcionItem.getSelectedItem().toString();
        String origenSeleccionado= "";
        Locacion locacion = new Locacion();
        String categoriaLocacionOrigen = "";
        categoriaLocacionOrigen = locacion.mostrarCategoriaLocacion(codigoLocacionOrigen);
        
        if (categoriaLocacionOrigen.equals("DEPOSITO DE PRODUCTOR")){
            
            origenSeleccionado = "MIEL DEPOSITADA";
                    
        }
        else{
            
            origenSeleccionado = "MIEL NO DEPOSITADA";
            
        }
        
        Double cantidadTraslado = 0.00;

        //almaceno y convierto a kgs.
        switch (descripcionItemtraslado){
            
            case "KG. DE MIEL":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString());
                break;

            case "TAMBOR DE MIEL X 300 KGS.":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*300.00;
                break;

            case "LOTE DE MIEL X 70 TAMBORES":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*21000.00;
                break;

            case "LOTE DE MIEL X 71 TAMBORES":
                //se suman los kilos sin convertirlos
                cantidadTraslado = Double.parseDouble(tfCantidad.getText().toString())*21300.00;
                break;

        }
        
        //CHEQUEOS:
        //1) Se chequea que la informacion este completa
        //2) Se chequea que la cantidad de miel a trasladar sea mayor que 0 (o sea, que no se intente trasladar 0 kgs. de miel)
        //3) Se chequea que el origen y el destino del traslado no sean el mismo!
        //4) A) Se chequea que no se intente trasladar mas miel de la que existe estoqueada en la locacion origen
        //   B) Se intenta trasladar una cantidad existente en la locacion origen, pero se trata de un movimiento
        //      desde una locacion de productor, el cual no reune la cantidad necesaria a trasladar    

        //1er chequeo: informacion completa o incompleta
        if (informacionTraslado) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al traslado se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            dcFechaTraslado.requestFocus();
            return;
            
        }
        
        //2do chequeo: traslado mayor a 0 kgs.
        if (Double.parseDouble(tfCantidad.getText().toString()) <= 0.00){

            JOptionPane.showMessageDialog(null, "No se puede trasladar un item con cantidad igual o menor a cero unidadades. Por favor ingrese la cantidad de miel a trasladar correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            tfCantidad.requestFocus();
            return;

        }
        
        //3er chequeo: 
        if (codigoLocacionOrigen == codigoLocacionDestino){

            JOptionPane.showMessageDialog(null, "La locacion origen debe ser distinta a la locacion destino. Por favor seleccione correctamente las mismas.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            cbLocacionOrigen.requestFocus();
            return;

        }
        
        //4to chequeo / caso A)
        if (origenSeleccionado.equals("MIEL DEPOSITADA") == false & saldoMielOrigen < Double.parseDouble(tfCantidad.getText().toString())){

            JOptionPane.showMessageDialog(null, "No se puede trasladar mas cantidad de miel de la que existe fisicamente en la locacion origen. Por favor ingrese la cantidad de miel a trasladar correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            tfCantidad.requestFocus();
            return;

        }
        //4to chequeo / caso B)
        if (origenSeleccionado.equals("MIEL DEPOSITADA") & saldoMielDepositoProductorSeleccionado < Double.parseDouble(tfCantidad.getText().toString())){

            JOptionPane.showMessageDialog(null, "La cantidad de miel ingresada para realizar el traslado supera a la depositada en la locacion del productor seleccionado. Por favor seleccione otro productor o ingrese la cantidad de miel a trasladar correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            tfCantidad.requestFocus();
            return;

        }
        
        //informacion completa y correcta, se procede al registro de toda la informacion
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

        if (origenSeleccionado.equals("MIEL DEPOSITADA")){

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
            stockMiel.setMiel_deposito_productor(codigoProductor);


        }

        stockMiel.registrarMovimientoStock(stockMiel);
        //se registra el traslado para locacion destino
        stockMiel.setTipo_movimiento("TRASLADO - DESTINO");
        stockMiel.setLocacion_miel(destinoTraslado);
        stockMiel.registrarMovimientoStock(stockMiel);

        this.dispose();
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void cbProductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductoresActionPerformed
    }//GEN-LAST:event_cbProductoresActionPerformed

    private void cbLocacionOrigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionOrigenMouseClicked
    }//GEN-LAST:event_cbLocacionOrigenMouseClicked

    private void cbLocacionDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionDestinoMouseClicked
    }//GEN-LAST:event_cbLocacionDestinoMouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        double saldoMiel = 0.00;
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocacionesOrigen.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            saldoMiel = calcularTotalStockLocacion(codigoLocacionOrigen);
            saldoMielOrigen = saldoMiel;
            lStockOrigen.setText(String.valueOf(saldoMiel));
            
        }
        else{
            
            lStockOrigen.setText("0.00");
        }
        
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        double saldoMiel = 0.00;
        
        if (cbLocacionDestino.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionDestino = listaLocacionesDestino.get(cbLocacionDestino.getSelectedIndex()).getCodigo_locacion();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            saldoMiel = calcularTotalStockLocacion(codigoLocacionDestino);
            lStockDestino.setText(String.valueOf(saldoMiel));
            
        }
        else{
            
            lStockDestino.setText("0.00");
        }
            
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        
        //obtengo la categoria de la locacion origen
        
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacionOrigen = listaLocacionesOrigen.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
            
            //obtengo la categoria de la locacion origen
            Locacion locacion = new Locacion();
            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacionOrigen);

            //si la categoria de la locacion origen es "DEPOSITO DE PRODUCTOR"
            //se habilita el combo productores y se muestran todos los productores cargados en el sistema
            //para poder validar el stock de miel en cada uno de ellos
            if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

                //se habilita el combo y se cargan los productores en el mismo
                try {
                    listaProductores = cargarListaLocacionesProductores();
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistroTraslado.class.getName()).log(Level.SEVERE, null, ex);
                }

                //primero limpio cargas anteriores del combo
                cbProductores.removeAllItems();
                
                for (int i = 0; i<listaProductores.size(); i++){

                   cbProductores.addItem(listaProductores.get(i).getNombre());

                }
                
                lStockDepositoProductor.setText("0.00");
                cbProductores.setEnabled(true);
                cbProductores.setSelectedIndex(0);

            }
            else{

                cbProductores.removeAllItems();
                lStockDepositoProductor.setText("0.00");
                cbProductores.setEnabled(false);

            }
            
        }

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked

        // cada vez que selecciona un productor, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        double saldoMiel = 0.00;
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoProductor = listaProductores.get(cbProductores.getSelectedIndex()).getCod_productor();
            //ademas muestro el stock fisico discponible en cada una de las locaciones
            //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen
            saldoMiel = calcularTotalStockLocacionDepositoProductor(codigoProductor);
            saldoMielDepositoProductorSeleccionado = saldoMiel;
            lStockDepositoProductor.setText(String.valueOf(saldoMiel));
            
        }
        else{
            
            lStockDepositoProductor.setText("0.00");
        }
        
    }//GEN-LAST:event_jLabel27MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
    public javax.swing.JComboBox<String> cbLocacionDestino;
    public javax.swing.JComboBox<String> cbLocacionOrigen;
    public javax.swing.JComboBox<String> cbMotivoTraslado;
    public javax.swing.JComboBox<String> cbProductores;
    public com.toedter.calendar.JDateChooser dcFechaTraslado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lStockDepositoProductor;
    private javax.swing.JLabel lStockDestino;
    private javax.swing.JLabel lStockOrigen;
    private javax.swing.JLabel lStockOrigen1;
    private javax.swing.JLabel lStockOrigen2;
    private javax.swing.JLabel lStockProductor;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTextField tfCantidad;
    // End of variables declaration//GEN-END:variables
}
