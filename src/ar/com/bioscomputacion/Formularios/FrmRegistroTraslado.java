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
    
    Double saldoMielOrigen, saldoMielPaga, saldoMielImpaga, saldoMielDepositoProductorSeleccionado, totalMielTraslado, saldoMielPagaIngresado, saldoMielImpagaIngresado;
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacionOrigen, codigoLocacionDestino, codigoProductor, codigoTraslado;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroTraslado() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        saldoMielOrigen = 0.00;
        saldoMielDepositoProductorSeleccionado = 0.00;
        
        Calendar cal = new GregorianCalendar();
        dcFechaTraslado.setCalendar(cal);
        
        //para calcular el id de traslado
        Traslado traslado = new Traslado();
        tfNumeroComprobante.setText(String.valueOf(traslado.mostrarIdTraslado()+1));
        codigoTraslado = traslado.mostrarIdTraslado()+1;
        
        //hasta que no seleccione una locacion origen no puedo ver el saldo de miel dsponible para trasladar
        tfKilosDisponiblesPagos.setText("0.00");
        tfKilosDisponiblesImpagos.setText("0.00");
        tfTotalKilosTraslado.setText("0.00");
        
        lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL TRASLADO: 0.00 KGS.");
        rbMielPagaDisponible.setSelected(true);
        rbMielImpagaDisponible.setSelected(false);
        rbMielPagaDisponible.setEnabled(false);
        rbMielImpagaDisponible.setEnabled(false);
        tfKilosDisponiblesPagos.setEnabled(false);
        tfKilosDisponiblesImpagos.setEnabled(false);
        
        cbMotivoTraslado.setSelectedIndex(0);
        dcFechaTraslado.requestFocus();

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
        
        
        int codigoGenerico = 22 ;
        ArrayList<Productor> productores = new ArrayList<Productor>();
        Productor productor = new Productor();
        productor.setCod_productor(-1);
        productor.setNombre("SELECCIONAR");
        productores.add(productor);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select cod_productor, nombre from productor p join persona r on p.cod_persona = r.cod_persona where p.cod_productor <> '" + codigoGenerico + "'order by p.cod_productor asc");
        
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
    
    //LOS 3 METODOS A CONTINUACION DEVUELVEN:
    //1) STOCK TOTAL DE MIEL EN LA LOCACION
    //2) STOCK TOTAL DE MIEL PAGA EN LA LOCACION
    //3) STOCK TOTAL DE MIEL IMPAGA EN LA LOCACION
    
    public double calcularStockTotalMielLocacion(int codigoLocacion) {

        double ingresoMiel, egresoMiel, saldoTotalMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMiel = stock.obtenerDetalleIngresoMiel(codigoLocacion);
        egresoMiel = stock.obtenerDetalleEgresoMiel(codigoLocacion);
        
        saldoTotalMiel = ingresoMiel - egresoMiel;
        
        return saldoTotalMiel;
        
    }
    
    public double calcularStockMielPagaLocacion(int codigoLocacion) {

        double ingresoMielPaga, egresoMielPaga, saldoTotalMielPaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(codigoLocacion);
        egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(codigoLocacion);
        
        saldoTotalMielPaga = ingresoMielPaga - egresoMielPaga;
        
        return saldoTotalMielPaga;
        
    }
    
    public double calcularStockMielImpagaLocacion(int codigoLocacion) {

        double ingresoMielImpaga, egresoMielImpaga, saldoTotalMielImpaga = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(codigoLocacion);
        egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(codigoLocacion);
        
        saldoTotalMielImpaga = ingresoMielImpaga - egresoMielImpaga;
        
        return saldoTotalMielImpaga;
        
    }
    
    //LOS 3 METODOS A CONTINUACION DEVUELVEN:
    //1) STOCK TOTAL DE MIEL EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    //2) STOCK TOTAL DE MIEL PAGA EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    //3) STOCK TOTAL DE MIEL IMPAGA EN LA LOCACION EN LA LOCACION DEL PRODUCTOR
    
    public double calcularStocktTotalMielLocacionDepositoProductor(int codigoProductor) {

        double mielCompradaEnDepositoProductor, mielTrasladadaDesdeLocacionProductor, saldoMielLocacionProductor = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielCompradaEnDepositoProductor = stock.obtenerDetalleMielCompradaDepositadaLocacionProductor(codigoProductor);
        mielTrasladadaDesdeLocacionProductor = stock.obtenerDetalleMielTrasladadaDesdeLocacionProductor(codigoProductor);
        
        saldoMielLocacionProductor = mielCompradaEnDepositoProductor - mielTrasladadaDesdeLocacionProductor;
        
        return saldoMielLocacionProductor;
        
    }
    
    public double calcularStocktTotalMielPagaLocacionDepositoProductor(int codigoProductor) {

        double mielCompradaEnDepositoProductor, mielTrasladadaDesdeLocacionProductor, saldoMielLocacionProductor = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielCompradaEnDepositoProductor = stock.obtenerDetalleMielPagaCompradaDepositadaLocacionProductor(codigoProductor);
        mielTrasladadaDesdeLocacionProductor = stock.obtenerDetalleMielPagaTrasladadaDesdeLocacionProductor(codigoProductor);
        
        saldoMielLocacionProductor = mielCompradaEnDepositoProductor - mielTrasladadaDesdeLocacionProductor;
        
        return saldoMielLocacionProductor;
        
    }
    
    public double calcularStocktTotalMielImpagaLocacionDepositoProductor(int codigoProductor) {

        double mielCompradaEnDepositoProductor, mielTrasladadaDesdeLocacionProductor, saldoMielLocacionProductor = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielCompradaEnDepositoProductor = stock.obtenerDetalleMielImpagaCompradaDepositadaLocacionProductor(codigoProductor);
        mielTrasladadaDesdeLocacionProductor = stock.obtenerDetalleMielImpagaTrasladadaDesdeLocacionProductor(codigoProductor);
        
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
        bgOpcionesMiel = new javax.swing.ButtonGroup();
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
        jLabel28 = new javax.swing.JLabel();
        tfKilosDisponiblesPagos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfKilosDisponiblesImpagos = new javax.swing.JTextField();
        tfTotalKilosTraslado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        rbMielPagaDisponible = new javax.swing.JRadioButton();
        rbMielImpagaDisponible = new javax.swing.JRadioButton();
        lMielDisponibleTraslado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE TRASLADO DE MIEL - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL TRASLADO:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA:");

        dcFechaTraslado.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaTraslado.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaTraslado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEM Y CANTIDADES A TRASLADAR:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        cbMotivoTraslado.setBackground(new java.awt.Color(255, 51, 51));
        cbMotivoTraslado.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cbMotivoTraslado.setForeground(new java.awt.Color(207, 207, 207));
        cbMotivoTraslado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "TRASLADO DE MIEL ENTRE LOCACIONES PROPIAS", "TRASLADO DE MIEL DESDE LOCACION DEL PRODUCTOR A LOCACION PROPIA", "TRASLADO DESDE LOCACION PROPIA A HOMOGENEIZACION", "TRASLADO DESDE LOCACION DEL PRODUCTOR A HOMOGENEIZACION", "TRASLADO DESDE HOMOGENEIZACION A LOCACION PROPIA", "TRASLADO DESDE HOMOGENEIZACION A FISCALIZACION", "TRASLADO DESDE FISCALIZACION A EMBARQUE (VENTA)", "TRASLADO DESDE EMBARQUE A FISCALIZACION", "TRASLADO DESDE EMBARQUE AL EXTERIOR (EXPORTACION)" }));
        cbMotivoTraslado.setPreferredSize(new java.awt.Dimension(136, 19));
        cbMotivoTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMotivoTrasladoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("MOTIVO DEL TRASLADO:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 255, 102));
        jLabel15.setText("ORIGEN DEL TRASLADO:");

        cbLocacionOrigen.setBackground(new java.awt.Color(102, 255, 102));
        cbLocacionOrigen.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
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
        jLabel16.setForeground(new java.awt.Color(255, 255, 102));
        jLabel16.setText("DESTINO DEL TRASLADO:");

        cbLocacionDestino.setBackground(new java.awt.Color(255, 255, 102));
        cbLocacionDestino.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
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
        lStockOrigen.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockOrigen.setForeground(new java.awt.Color(102, 255, 102));
        lStockOrigen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockOrigen.setText("0.00");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 255, 102));
        jLabel25.setText("VALIDAR STOCK:");
        jLabel25.setToolTipText("");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 102));
        jLabel26.setText("VALIDAR STOCK:");
        jLabel26.setToolTipText("");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        lStockDestino.setBackground(new java.awt.Color(255, 255, 255));
        lStockDestino.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockDestino.setForeground(new java.awt.Color(255, 255, 102));
        lStockDestino.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockDestino.setText("0.00");

        cbProductores.setBackground(new java.awt.Color(153, 255, 255));
        cbProductores.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cbProductores.setForeground(new java.awt.Color(207, 207, 207));
        cbProductores.setPreferredSize(new java.awt.Dimension(136, 19));
        cbProductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoresActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 255, 255));
        jLabel27.setText("VALIDAR STOCK:");
        jLabel27.setToolTipText("");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        lStockDepositoProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockDepositoProductor.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockDepositoProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockDepositoProductor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStockDepositoProductor.setText("0.00");

        lStockOrigen1.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen1.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockOrigen1.setForeground(new java.awt.Color(102, 255, 102));
        lStockOrigen1.setText("KGS.");

        lStockOrigen2.setBackground(new java.awt.Color(255, 255, 255));
        lStockOrigen2.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockOrigen2.setForeground(new java.awt.Color(255, 255, 102));
        lStockOrigen2.setText("KGS.");

        lStockProductor.setBackground(new java.awt.Color(255, 255, 255));
        lStockProductor.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockProductor.setForeground(new java.awt.Color(153, 255, 255));
        lStockProductor.setText("KGS.");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 255, 255));
        jLabel28.setText("MIEL EN DEPOSITO DE:");
        jLabel28.setToolTipText("");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        tfKilosDisponiblesPagos.setBackground(new java.awt.Color(0, 0, 0));
        tfKilosDisponiblesPagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosDisponiblesPagos.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosDisponiblesPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosDisponiblesPagosActionPerformed(evt);
            }
        });
        tfKilosDisponiblesPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesPagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesPagosKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("TRASLADO N°:");

        tfNumeroComprobante.setEditable(false);
        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        tfKilosDisponiblesImpagos.setBackground(new java.awt.Color(0, 0, 0));
        tfKilosDisponiblesImpagos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosDisponiblesImpagos.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosDisponiblesImpagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosDisponiblesImpagosActionPerformed(evt);
            }
        });
        tfKilosDisponiblesImpagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesImpagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosDisponiblesImpagosKeyTyped(evt);
            }
        });

        tfTotalKilosTraslado.setEditable(false);
        tfTotalKilosTraslado.setBackground(new java.awt.Color(0, 0, 0));
        tfTotalKilosTraslado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTotalKilosTraslado.setForeground(new java.awt.Color(255, 255, 255));
        tfTotalKilosTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalKilosTrasladoActionPerformed(evt);
            }
        });
        tfTotalKilosTraslado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTotalKilosTrasladoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTotalKilosTrasladoKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TRASLADO TOTAL:");

        bgOpcionesMiel.add(rbMielPagaDisponible);
        rbMielPagaDisponible.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbMielPagaDisponible.setForeground(new java.awt.Color(255, 255, 255));
        rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
        rbMielPagaDisponible.setOpaque(false);
        rbMielPagaDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMielPagaDisponibleActionPerformed(evt);
            }
        });
        rbMielPagaDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbMielPagaDisponibleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbMielPagaDisponibleKeyReleased(evt);
            }
        });

        bgOpcionesMiel.add(rbMielImpagaDisponible);
        rbMielImpagaDisponible.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        rbMielImpagaDisponible.setForeground(new java.awt.Color(255, 255, 255));
        rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
        rbMielImpagaDisponible.setOpaque(false);
        rbMielImpagaDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMielImpagaDisponibleActionPerformed(evt);
            }
        });
        rbMielImpagaDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbMielImpagaDisponibleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rbMielImpagaDisponibleKeyReleased(evt);
            }
        });

        lMielDisponibleTraslado.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lMielDisponibleTraslado.setForeground(new java.awt.Color(255, 255, 255));
        lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL TRASLADO:");

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(lMielDisponibleTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(250, 250, 250))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfNumeroComprobante))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(dcFechaTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbMotivoTraslado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockOrigen1))
                                    .addComponent(cbLocacionOrigen, 0, 206, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockDepositoProductor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockProductor))
                                    .addComponent(cbProductores, 0, 206, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lStockDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lStockOrigen2))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addComponent(cbLocacionDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rbMielPagaDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbMielImpagaDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfKilosDisponiblesImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfKilosDisponiblesPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfTotalKilosTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel21))
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dcFechaTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(cbMotivoTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(6, 6, 6)
                                .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel8)
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLocacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(lStockDestino)
                                .addComponent(lStockOrigen2)
                                .addComponent(lStockProductor)
                                .addComponent(lStockDepositoProductor))))
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbLocacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(37, 37, 37)
                            .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(lStockOrigen)
                                .addComponent(lStockOrigen1)))))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lMielDisponibleTraslado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(tfKilosDisponiblesPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(3, 3, 3)))
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKilosDisponiblesImpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMielImpagaDisponible)
                            .addComponent(tfTotalKilosTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rbMielPagaDisponible))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
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

        /*
        SELECCIONAR
        TRASLADO DE MIEL ENTRE LOCACIONES PROPIAS
        TRASLADO DE MIEL DESDE LOCACION DEL PRODUCTOR A LOCACION PROPIA
        TRASLADO DESDE LOCACION PROPIA A HOMOGENEIZACION
        TRASLADO DESDE LOCACION DEL PRODUCTOR A HOMOGENEIZACION
        TRASLADO DESDE HOMOGENEIZACION A LOCACION PROPIA
        TRASLADO DESDE HOMOGENEIZACION A FISCALIZACION
        TRASLADO DESDE FISCALIZACION A EMBARQUE (VENTA)
        TRASLADO DESDE EMBARQUE A FISCALIZACION
        TRASLADO DESDE EMBARQUE AL EXTERIOR (EXPORTACION)
        */
        
        try {
            
            listaLocacionesOrigen.clear();
            listaLocacionesDestino.clear();
            cbLocacionOrigen.removeAllItems();
            cbLocacionDestino.removeAllItems();
            cbProductores.removeAllItems();
            lStockOrigen.setText("0.00");
            lStockDestino.setText("0.00");
            lStockDepositoProductor.setText("0.00");
            
            tfKilosDisponiblesPagos.setText("0.00");
            tfKilosDisponiblesImpagos.setText("0.00");
            tfTotalKilosTraslado.setText("0.00");
            
            rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
            rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
            rbMielPagaDisponible.setEnabled(false);
            rbMielImpagaDisponible.setEnabled(false);
            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(false);

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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE FISCALIZACION A EMBARQUE (VENTA)":
                    
                    //combo origen solo locaciones de fiscalizacion y combo destino solo locaciones de embarque

                    listaLocacionesOrigen = cargarListaLocaciones("FISCALIZACION");
                    listaLocacionesDestino = cargarListaLocaciones("EMBARQUE");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
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
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.setSelectedIndex(0);
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;
                    
                case "TRASLADO DESDE EMBARQUE AL EXTERIOR (EXPORTACION)":
                    
                    //combo origen solo locaciones de embarque y combo destino solo locaciones de fiscalizacion

                    listaLocacionesOrigen = cargarListaLocaciones("EMBARQUE");
                    
                    //DEBERIA CARGARSE EN EL COMBO DESTINO LA LOCACION "EXTERIOR"???
                    //LA CUAL NO DEBERIA MOSTRAR VALIDACION DE STOCK YA QUE ES MIEL VENDIDA
                    //QUENO PERTENECE MAS A LA EMPRESA
                    //¿DEBERIA MOSTRAR LOS CLIENTES DISPONIBLES PARA?!
                    //listaLocacionesDestino = cargarListaLocaciones("FISCALIZACION");
                    
                    for (int i = 0; i<listaLocacionesOrigen.size(); i++){
                        
                        cbLocacionOrigen.addItem(listaLocacionesOrigen.get(i).getNombre_locacion());
                        
                    }
                    
                    /*for (int i = 0; i<listaLocacionesDestino.size(); i++){
                        
                        cbLocacionDestino.addItem(listaLocacionesDestino.get(i).getNombre_locacion());
                        
                    }*/
                    
                    cbLocacionOrigen.setSelectedIndex(0);
                    cbLocacionDestino.addItem("SELECCIONAR");
                    cbLocacionDestino.setSelectedIndex(0);
                    cbLocacionDestino.enable(false);
                    cbProductores.addItem("SELECCIONAR");
                    cbProductores.setSelectedIndex(0);
                    cbProductores.enable(false);
                    break;

                default :
                    
                    //ninguno de los casos
                    break;
                    
            }
            
            cbLocacionOrigen.requestFocus();
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmRegistroTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbMotivoTrasladoActionPerformed

    private void cbLocacionOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionOrigenActionPerformed
    }//GEN-LAST:event_cbLocacionOrigenActionPerformed

    private void cbLocacionDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionDestinoActionPerformed
    }//GEN-LAST:event_cbLocacionDestinoActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //REGISTRO DE TRASLADO
        
        int respuesta = 0;
        
        //obtengo la fecha del traslado
        Calendar cal1;
        int d1, m1, a1;
        cal1 = dcFechaTraslado.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;

        int origenTraslado = codigoLocacionOrigen;
        int destinoTraslado = codigoLocacionDestino;
        Date fechaTraslado = new Date(a1, m1, d1);


        Boolean informacionTraslado = (cbMotivoTraslado.getSelectedItem() == "SELECCIONAR" || cbLocacionOrigen.getSelectedItem() == "SELECCIONAR" || cbLocacionDestino.getSelectedItem() == "SELECCIONAR");
        String numeroComprobante = tfNumeroComprobante.getText();
        String descripcionItemtraslado= "KGS. DE MIEL";
        Double cantidadTraslado = Double.valueOf(tfTotalKilosTraslado.getText().toString());
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
        
        //CHEQUEOS:
        //1) Se chequea que la informacion este completa
        //2) Se chequea que el origen y el destino del traslado no sean el mismo!
        //3) Se intenta trasladar una cantidad existente en la locacion origen, pero se trata de un movimiento
        //   desde una locacion de productor, el cual no reune la cantidad necesaria a trasladar
        

        //1er chequeo: informacion completa o incompleta
        if (informacionTraslado) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al traslado se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            tfTotalKilosTraslado.requestFocus();
            return;
            
        }
        
        //2do chequeo: origen y destino del traslado duplicados
        if (codigoLocacionOrigen == codigoLocacionDestino){

            JOptionPane.showMessageDialog(null, "La locacion origen debe ser distinta a la locacion destino. Por favor seleccione correctamente las mismas.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            cbLocacionOrigen.requestFocus();
            return;

        }
        
        //3er chequeo: cantidad de miel ingresada para el traslado igual a 0 kgs
        if (Double.valueOf(tfTotalKilosTraslado.getText()) == 0.00){

            JOptionPane.showMessageDialog(null, "La cantidad de miel ingresada para realizar el traslado es incorrecta. Por favor ingresela correctamente.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);
            cbLocacionOrigen.requestFocus();
            return;

        }
        
        //CASO A) TRASLADO NORMAL
        //CASO B) TRASLADO DESDE FISCALIZACION A EMABARQUE, LO CUAL SIGNIFICA UNA VENTA QUE DEBE DESCONTAR EL STOCK GLOBAL DE LA
        //EMPRESA, INCREMENTANDO EL STOCK QUE EXISTE EN LA LOCACION "EXTERIOR"
        //CASO C) TRASLADO DESDE EMBARQUE A FISCALIZACION, LO CUAL SIGNIFICA UNA DEVOLUCION ((CANCELACION DE UNA VENTA)
        //Y DEBE VOLVER A INCREMENTAR EL STOCK GLOBAL DE LA EMPRESA DECREMENTANDO EL STOCK ALMACENADO EN "EXTERIOR"
        
        //se almacena el motivo del traslado para ver como sigue la insercion del mismo
        String motivoTraslado = cbMotivoTraslado.getSelectedItem().toString();
        
        //CASO B) TRASLADO DESDE FISCALIZACION A EMABARQUE, LO CUAL SIGNIFICA UNA VENTA QUE DEBE DESCONTAR EL STOCK GLOBAL DE LA
        //EMPRESA, INCREMENTANDO EL STOCK QUE EXISTE EN LA LOCACION "EXTERIOR"
        if (motivoTraslado.equals("TRASLADO DESDE FISCALIZACION A EMBARQUE (VENTA)")){
            
            //caso B) TRASLADO NORMAL
            respuesta = JOptionPane.showConfirmDialog(null, "Se debe registrar la factura asociada a la venta de la miel a trasladar. ¿Desea realizar dicho registro?");
            
            if (respuesta == 0){
                
                //se acepta el registro de la factura d venta al cliente en el exterior
                
                //Se deben registrar la factura de venta al cliente en el exterior
                //y el traslado de la miel vendida desde fiscalacion a embarque
                
                
                //se procede al registro del traslado
                Traslado traslado = new Traslado(numeroComprobante, descripcionItemtraslado, cantidadTraslado, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);

                if (traslado.registrarTrasladoMiel(traslado)){

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
                    stockMiel.setId_comprobante_asociado(codigoTraslado);
                    stockMiel.setNumero_comprobante_asociado(String.valueOf(codigoTraslado));
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

                    JOptionPane.showMessageDialog(null, "El traslado ha sido registrado exitosamente.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();

                }
                else{

                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el traslado.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);

                }
                
            }
            else{
                
                if (respuesta == 1){
                    
                    //no se acepta el registro de la factura de venta al cliente, se cancelan todos los cambios
                    JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    
                }
                else{
                    
                    //se cancela la eleccion entre la facturacion y la no facturacion, el formulario debe permanecer abierto
                    //mostrando todos los datos ingresados hasta el momento
                    rdbrRegistrar.requestFocus();
                    return;
                
                }
                
            }
            
        }
        else{
        //CASO C) TRASLADO DESDE EMBARQUE A FISCALIZACION, LO CUAL SIGNIFICA UNA DEVOLUCION ((CANCELACION DE UNA VENTA)
        //Y DEBE VOLVER A INCREMENTAR EL STOCK GLOBAL DE LA EMPRESA DECREMENTANDO EL STOCK ALMACENADO EN "EXTERIOR"
            if (motivoTraslado.equals("TRASLADO DESDE EMBARQUE A FISCALIZACION")){
            
                //caso C) TRASLADO DESDE EMBARQUE A FISCALIZACION
                respuesta = JOptionPane.showConfirmDialog(null, "Se debe registrar la nota de credito que se asociara a la factura correspondiente a la miel devuelta. ¿Desea realizar dicho registro?");

                if (respuesta == 0){

                    //se acepta el registro de la nota de credito para el  cliente en el exterior

                    //Se deben registrar la nota de credito, asociarla a la factura
                    //y el traslado de la miel devuelta desde embarque a fiscalizacion


                    //se procede al registro del traslado
                    Traslado traslado = new Traslado(numeroComprobante,descripcionItemtraslado, cantidadTraslado, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);

                    if (traslado.registrarTrasladoMiel(traslado)){

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
                        stockMiel.setId_comprobante_asociado(codigoTraslado);
                        stockMiel.setNumero_comprobante_asociado(String.valueOf(codigoTraslado));
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

                        JOptionPane.showMessageDialog(null, "El traslado ha sido registrado exitosamente.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();

                    }
                    else{

                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el traslado.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);

                    }

                }
                else{

                    if (respuesta == 1){

                        //no se acepta el registro de la nota de credito al  cliente, se cancelan todos los cambios
                        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();

                    }
                    else{

                        //se cancela la eleccion entre la realizacion o no de la nota de credito, el formulario debe permanecer 
                        //abierto mostrando todos los datos ingresados hasta el momento
                        rdbrRegistrar.requestFocus();
                        return;

                    }

                }

            }
            else{
                System.out.println(numeroComprobante);
                //CASO A) TRASLADO NORMAL
                
                //informacion completa y correcta, se procede al registro del traslado
                //debo ver si es necesario realizar dos traslados: uno para la miel paga y otro para la miel impaga
                //o se trata de un traslado de o bien solo miel paga o solo miel impaga
                
                if (saldoMielPagaIngresado != 0){

                    //debe hacerse al menos el traslado de miel paga
                    Traslado trasladoMielPaga = new Traslado(numeroComprobante, descripcionItemtraslado, saldoMielPagaIngresado, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);
                    if (trasladoMielPaga.registrarTrasladoMiel(trasladoMielPaga)){

                        //obtengo el codigo del traslado recien dado de alta para almacenarlo como comprobante asociado
                        //en la tabla stock real de miel
                        int codigoTraslado = trasladoMielPaga.mostrarIdTraslado();

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
                        stockMiel.setId_comprobante_asociado(codigoTraslado);
                        stockMiel.setNumero_comprobante_asociado(String.valueOf(codigoTraslado));
                        stockMiel.setCantidad_miel(saldoMielPagaIngresado);
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

                        stockMiel.setEstado_compra("FACTURADA");
                        stockMiel.registrarMovimientoStock(stockMiel);
                        //se registra el traslado para locacion destino
                        stockMiel.setTipo_movimiento("TRASLADO - DESTINO");
                        stockMiel.setLocacion_miel(destinoTraslado);
                        stockMiel.setEstado_compra("FACTURADA");
                        stockMiel.registrarMovimientoStock(stockMiel);

                        JOptionPane.showMessageDialog(null, "El traslado ha sido registrado exitosamente.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();

                    }
                    else{

                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el traslado.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);

                    }

                
                }

                if (saldoMielImpagaIngresado != 0){

                    //debe hacerse tambien el traslado de miel impaga
                    Traslado trasladoMielImpaga = new Traslado(numeroComprobante, descripcionItemtraslado, saldoMielImpagaIngresado, motivoTraslado, origenTraslado, destinoTraslado, fechaTraslado);
                    if (trasladoMielImpaga.registrarTrasladoMiel(trasladoMielImpaga)){

                        //obtengo el codigo del traslado recien dado de alta para almacenarlo como comprobante asociado
                        //en la tabla stock real de miel
                        int codigoTraslado = trasladoMielImpaga.mostrarIdTraslado();

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
                        stockMiel.setId_comprobante_asociado(codigoTraslado);
                        stockMiel.setNumero_comprobante_asociado(String.valueOf(codigoTraslado));
                        stockMiel.setCantidad_miel(saldoMielImpagaIngresado);
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

                        stockMiel.setEstado_compra("SIN FACTURAR");
                        stockMiel.registrarMovimientoStock(stockMiel);
                        //se registra el traslado para locacion destino
                        stockMiel.setTipo_movimiento("TRASLADO - DESTINO");
                        stockMiel.setLocacion_miel(destinoTraslado);
                        stockMiel.setEstado_compra("SIN FACTURAR");
                        stockMiel.registrarMovimientoStock(stockMiel);

                        JOptionPane.showMessageDialog(null, "El traslado ha sido registrado exitosamente.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();

                    }
                    else{

                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el traslado.","REGISTRO DE TRASLADO DE MIEL", JOptionPane.ERROR_MESSAGE);

                    }

                }
            }
            
        }

        this.dispose();
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void cbProductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductoresActionPerformed
    }//GEN-LAST:event_cbProductoresActionPerformed

    private void cbLocacionOrigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionOrigenMouseClicked

        //si en el combo origen no esta seleccionado el item MIEL EN DEPOSITO
        //se debe inhabilitar el combo de los productores debajo
        if (cbLocacionOrigen.getSelectedItem() != "MIEL EN DEPOSITOS DE PRODUCTORES"){
            
            cbProductores.removeAllItems();
            cbProductores.setEnabled(false);
            lStockDepositoProductor.setText("0.00");
                    
        }
        
    }//GEN-LAST:event_cbLocacionOrigenMouseClicked

    private void cbLocacionDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocacionDestinoMouseClicked
    }//GEN-LAST:event_cbLocacionDestinoMouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
 
        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        //ademas, se deben llenar los campos de kilos disponibles pagos e impagos reflejando la cant de miel existente
        //en la locacion origen seleccionada
        
        if (cbLocacionOrigen.getSelectedIndex() != 0){
            
            //La locacon origen sera un productor seleccionado en el cmobo de los productores
            if (cbLocacionOrigen.getSelectedItem().toString().equals("MIEL EN DEPOSITOS DE PRODUCTORES")){

                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PRODUCTOR EN EL COMBO DE PRODUCTORES Y LUEGO Y VALIDAR LA MIEL ACOPIADA EN SUS DEPOSITOS.", "REGISTRO DE TRASLADO DE MIEL", JOptionPane.INFORMATION_MESSAGE);
                //si es miel depositada en una locacion de productores se debe permitir
                //ver al menos cuanta miel hay en depositos de productores pero si se debe seleccionar
                //un productor y validar la miel en sus depositos apra poder cargar el traslado con cantidades
                codigoLocacionOrigen = listaLocacionesOrigen.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
                saldoMielOrigen = calcularStockTotalMielLocacion(codigoLocacionOrigen);
                lStockOrigen.setText(String.valueOf(saldoMielOrigen));
                cbProductores.requestFocus();
                
            }
            //La locacion origen no es una locacion de productor
            else{

                //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
                //caso contrario busco el codigo asociado al nombre seleccionado
                codigoLocacionOrigen = listaLocacionesOrigen.get(cbLocacionOrigen.getSelectedIndex()).getCodigo_locacion();
                //ademas muestro el stock fisico discponible en cada una de las locaciones (pago e impago)
                //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen

                //valores reales y originales
                saldoMielOrigen = calcularStockTotalMielLocacion(codigoLocacionOrigen);
                saldoMielPaga = calcularStockMielPagaLocacion(codigoLocacionOrigen);
                saldoMielImpaga = calcularStockMielImpagaLocacion(codigoLocacionOrigen);

                //valores que iran cambiando a medida que se tocan los numeros a trasladar
                //sirven ademas para realizar controles y filtros
                totalMielTraslado = saldoMielOrigen;
                saldoMielPagaIngresado = saldoMielPaga;
                saldoMielImpagaIngresado = saldoMielImpaga;

                lStockOrigen.setText(String.valueOf(saldoMielOrigen));
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPagaIngresado));
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpagaIngresado));
                tfTotalKilosTraslado.setText(String.valueOf(totalMielTraslado));

                lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL TRASLADO: "+String.valueOf(saldoMielOrigen)+" KGS.");
                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: "+saldoMielPaga+" KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: "+saldoMielImpaga+" KGS.");
                rbMielPagaDisponible.setEnabled(true);
                rbMielImpagaDisponible.setEnabled(true);
                rbMielPagaDisponible.setSelected(true);
                tfKilosDisponiblesPagos.setEnabled(true);
                tfKilosDisponiblesImpagos.setEnabled(false);
                tfKilosDisponiblesPagos.requestFocus();

            }
            
        }
        else{
            
            lStockOrigen.setText("0.00");
            tfKilosDisponiblesPagos.setText("0.00");
            tfKilosDisponiblesImpagos.setText("0.00");
            tfTotalKilosTraslado.setText("0.00");
            
            lMielDisponibleTraslado.setText("KGS. DE MIEL DISPONIBLES PARA REALIZAR EL TRASLADO: 0.00 KGS.");
            rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
            rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
            rbMielPagaDisponible.setEnabled(false);
            rbMielImpagaDisponible.setEnabled(false);
            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(false);
            cbLocacionOrigen.requestFocus();

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
            saldoMiel = calcularStockTotalMielLocacion(codigoLocacionDestino);
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

                //cbProductores.removeAllItems();
                //lStockDepositoProductor.setText("0.00");
                //cbProductores.setEnabled(false);

            }
            
        }

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked

        // cada vez que selecciona un productor, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente
        
        //tambien se muestra el stock fisico disponible en la locacion seleccionada
        
        //si este como esta habilitado significa que se esta intentando trasladar miel desde la locacion
        //de un productor
        if (cbProductores.isEnabled()){
            
            double saldoMiel = 0.00;

            //no se encuentra seleccionado ningun productor en el combo
            if (cbProductores.getSelectedIndex() != 0){

                //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
                //caso contrario busco el codigo asociado al nombre seleccionado
                codigoProductor = listaProductores.get(cbProductores.getSelectedIndex()).getCod_productor();
                //ademas muestro el stock fisico discponible en cada una de las locaciones (pago e impago)
                //sirviendo tambien dicho dato para no permitir mover mas de lo que hay desde la locacion origen

                //valores reales y originales
                saldoMielOrigen = calcularStocktTotalMielLocacionDepositoProductor(codigoProductor);
                saldoMielPaga = calcularStocktTotalMielPagaLocacionDepositoProductor(codigoProductor);
                saldoMielImpaga = calcularStocktTotalMielImpagaLocacionDepositoProductor(codigoProductor);

                //valores que iran cambiando a medida que se tocan los numeros a trasladar
                //sirven ademas para realizar controles y filtros
                totalMielTraslado = saldoMielOrigen;
                saldoMielPagaIngresado = saldoMielPaga;
                saldoMielImpagaIngresado = saldoMielImpaga;

                lStockDepositoProductor.setText(String.valueOf(saldoMielOrigen));
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPagaIngresado));
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpagaIngresado));
                tfTotalKilosTraslado.setText(String.valueOf(totalMielTraslado));

                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: "+saldoMielPaga+" KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: "+saldoMielImpaga+" KGS.");
                rbMielPagaDisponible.setEnabled(true);
                rbMielImpagaDisponible.setEnabled(true);
                rbMielPagaDisponible.setSelected(true);
                tfKilosDisponiblesPagos.setEnabled(true);
                tfKilosDisponiblesImpagos.setEnabled(false);
                tfKilosDisponiblesPagos.requestFocus();

            }
            else{

                lStockDepositoProductor.setText("0.00");
                tfKilosDisponiblesPagos.setText("0.00");
                tfKilosDisponiblesImpagos.setText("0.00");
                tfTotalKilosTraslado.setText("0.00");

                rbMielPagaDisponible.setText("MIEL PAGA DISPONIBLE: 0.00 KGS.");
                rbMielImpagaDisponible.setText("MIEL IMPAGA DISPONIBLE: 0.00 KGS.");
                rbMielPagaDisponible.setEnabled(false);
                rbMielImpagaDisponible.setEnabled(false);
                tfKilosDisponiblesPagos.setEnabled(false);
                tfKilosDisponiblesImpagos.setEnabled(false);
                cbLocacionOrigen.requestFocus();

            }
            
        }
        
    }//GEN-LAST:event_jLabel27MouseClicked

    private void tfKilosDisponiblesPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosActionPerformed

    }//GEN-LAST:event_tfKilosDisponiblesPagosActionPerformed

    private void tfKilosDisponiblesPagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vaco (si 0 pero no vacio)
        //2) que no se ingrese un valor superior a la miel paga disponible en la locacion origen
        //3) que no se ingrese un valor que sumado al segundo valor supera al total de miel disponible en la locacion origen
        
        if (tfKilosDisponiblesPagos.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE TRASLADO DE MIEL",JOptionPane.ERROR_MESSAGE);
            tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
            Double kilosTotalesTraslado = saldoMielPaga + saldoMielImpagaIngresado;
            tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
            tfKilosDisponiblesPagos.requestFocus();
            
        }
        else{
            
            Double kilosPagosIngresados = Double.valueOf(tfKilosDisponiblesPagos.getText());
            
            if (kilosPagosIngresados > saldoMielPaga){
                
                JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE TRASLADO DE MIEL",JOptionPane.ERROR_MESSAGE);
                tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
                Double kilosTotalesTraslado = saldoMielPaga + saldoMielImpagaIngresado;
                tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
                tfKilosDisponiblesPagos.requestFocus();
                
            }
            else{
                
                if (kilosPagosIngresados + saldoMielImpagaIngresado > saldoMielOrigen){
                
                    JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE TRASLADO DE MIEL",JOptionPane.ERROR_MESSAGE);
                    tfKilosDisponiblesPagos.setText(String.valueOf(saldoMielPaga));
                    Double kilosTotalesTraslado = saldoMielPaga + saldoMielImpagaIngresado;
                    tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
                    tfKilosDisponiblesPagos.requestFocus();
                
                }
                //el valor ingresado supero todos los filtros es correcto
                else{
                
                    saldoMielPagaIngresado = kilosPagosIngresados;
                    totalMielTraslado = saldoMielPagaIngresado + saldoMielImpagaIngresado;
                    tfTotalKilosTraslado.setText(String.valueOf(totalMielTraslado));
                    tfKilosDisponiblesPagos.requestFocus();
                
                }
                
            }
            
            
        }

    }//GEN-LAST:event_tfKilosDisponiblesPagosKeyReleased

    private void tfKilosDisponiblesPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesPagosKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosDisponiblesPagos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
        
    }//GEN-LAST:event_tfKilosDisponiblesPagosKeyTyped

    private void tfKilosDisponiblesImpagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosActionPerformed
    }//GEN-LAST:event_tfKilosDisponiblesImpagosActionPerformed

    private void tfKilosDisponiblesImpagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosKeyReleased

        //chequeos a realizar con el valor ingresado
        //1) que no se ingrese vaco (si 0 pero no vacio)
        //2) que no se ingrese un valor superior a la miel paga disponible en la locacion origen
        //3) que no se ingrese un valor que sumado al segundo valor supera al total de miel disponible en la locacion origen
        
        //ANDA BIEN PERO FALTA
        //FALAT QUE CADA VEZ QUE PISO EL TEXTO CON EL VALRO DE SALDOMIELPAGA TAMB HAGA LA SUMA ENEL CAMPO TRASLADO TOTAL
        
        if (tfKilosDisponiblesImpagos.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "CANTIDAD INCORRECTA");
            tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
            Double kilosTotalesTraslado = saldoMielImpaga + saldoMielPagaIngresado;
            tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
            tfKilosDisponiblesImpagos.requestFocus();
            
        }
        //seguir cambiando pago por impago
        else{
            
            Double kilosImpagosIngresados = Double.valueOf(tfKilosDisponiblesImpagos.getText());
            
            if (kilosImpagosIngresados > saldoMielImpaga){
                
                JOptionPane.showMessageDialog(null, "CANTIDAD INCORRECTA");
                tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
                Double kilosTotalesTraslado = saldoMielImpaga + saldoMielPagaIngresado;
                tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
                tfKilosDisponiblesImpagos.requestFocus();
                
            }
            else{
                
                if (kilosImpagosIngresados + saldoMielPagaIngresado > saldoMielOrigen){
                
                    JOptionPane.showMessageDialog(null, "CANTIDAD INCORRECTA");
                    tfKilosDisponiblesImpagos.setText(String.valueOf(saldoMielImpaga));
                    Double kilosTotalesTraslado = saldoMielImpaga + saldoMielPagaIngresado;
                    tfTotalKilosTraslado.setText(String.valueOf(kilosTotalesTraslado));
                    tfKilosDisponiblesImpagos.requestFocus();
                
                }
                //el valor ingresado supero todos los filtros es correcto
                else{
                
                    //JOptionPane.showMessageDialog(null, "CANTIDAD CORRECTA");
                    saldoMielImpagaIngresado = kilosImpagosIngresados;
                    totalMielTraslado = saldoMielImpagaIngresado + saldoMielPagaIngresado;
                    tfTotalKilosTraslado.setText(String.valueOf(totalMielTraslado));
                    tfKilosDisponiblesImpagos.requestFocus();
                
                }
                
            }
            
        }

    }//GEN-LAST:event_tfKilosDisponiblesImpagosKeyReleased

    private void tfKilosDisponiblesImpagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosDisponiblesImpagosKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosDisponiblesImpagos.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
        
    }//GEN-LAST:event_tfKilosDisponiblesImpagosKeyTyped

    private void tfTotalKilosTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalKilosTrasladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalKilosTrasladoActionPerformed

    private void tfTotalKilosTrasladoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTotalKilosTrasladoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalKilosTrasladoKeyReleased

    private void tfTotalKilosTrasladoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTotalKilosTrasladoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalKilosTrasladoKeyTyped

    private void rbMielImpagaDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleActionPerformed
        
        if (rbMielImpagaDisponible.isSelected()){
            
            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(true);
            tfKilosDisponiblesImpagos.requestFocus();
            
        }
        else{
            
            tfKilosDisponiblesPagos.setEnabled(true);
            tfKilosDisponiblesImpagos.setEnabled(false);
            tfKilosDisponiblesPagos.requestFocus();
            
        }

    }//GEN-LAST:event_rbMielImpagaDisponibleActionPerformed

    private void rbMielPagaDisponibleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleKeyPressed
    }//GEN-LAST:event_rbMielPagaDisponibleKeyPressed

    private void rbMielImpagaDisponibleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleKeyPressed
    }//GEN-LAST:event_rbMielImpagaDisponibleKeyPressed

    private void rbMielPagaDisponibleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleKeyReleased
    }//GEN-LAST:event_rbMielPagaDisponibleKeyReleased

    private void rbMielImpagaDisponibleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbMielImpagaDisponibleKeyReleased
    }//GEN-LAST:event_rbMielImpagaDisponibleKeyReleased

    private void rbMielPagaDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMielPagaDisponibleActionPerformed
        
        if (rbMielPagaDisponible.isSelected()){
            
            tfKilosDisponiblesPagos.setEnabled(true);
            tfKilosDisponiblesImpagos.setEnabled(false);
            tfKilosDisponiblesPagos.requestFocus();
            
        }
        else{
            
            tfKilosDisponiblesPagos.setEnabled(false);
            tfKilosDisponiblesImpagos.setEnabled(true);
            tfKilosDisponiblesImpagos.requestFocus();
            
        }

    }//GEN-LAST:event_rbMielPagaDisponibleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcionesMiel;
    public javax.swing.JComboBox<String> cbLocacionDestino;
    public javax.swing.JComboBox<String> cbLocacionOrigen;
    public javax.swing.JComboBox<String> cbMotivoTraslado;
    public javax.swing.JComboBox<String> cbProductores;
    public com.toedter.calendar.JDateChooser dcFechaTraslado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel lMielDisponibleTraslado;
    private javax.swing.JLabel lStockDepositoProductor;
    private javax.swing.JLabel lStockDestino;
    private javax.swing.JLabel lStockOrigen;
    private javax.swing.JLabel lStockOrigen1;
    private javax.swing.JLabel lStockOrigen2;
    private javax.swing.JLabel lStockProductor;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private javax.swing.JRadioButton rbMielImpagaDisponible;
    private javax.swing.JRadioButton rbMielPagaDisponible;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTextField tfKilosDisponiblesImpagos;
    public static javax.swing.JTextField tfKilosDisponiblesPagos;
    public static javax.swing.JTextField tfNumeroComprobante;
    public static javax.swing.JTextField tfTotalKilosTraslado;
    // End of variables declaration//GEN-END:variables
}
