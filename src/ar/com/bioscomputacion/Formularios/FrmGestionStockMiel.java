/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import static ar.com.bioscomputacion.Formularios.FrmPrincipal.deskPrincipal;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class FrmGestionStockMiel extends javax.swing.JInternalFrame {

    int codigoLocacion;

    //para cargar la lista de locaciones registradas en el sistema
    public List<Locacion> listaLocaciones = new ArrayList<>();

    //para cargar la lista de productores registrados en el sistema
    public List<Productor> listaProductores = new ArrayList<>();
    
    int fila, fila2 = -1;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionStockMiel() throws SQLException {
        
        initComponents();
        mostrarDetalleStockLocaciones();
        calcularTotalStockGlobal();
        calcularTotalStockGlobalPago();
        calcularTotalStockGlobalCredito();
        ocultarColumnasDetalleLocaciones();
        //calcularTotalStockEmbarque();
        //calcularTotalStockExportacion!!!
        //actualiza informacion en la pestaña 1
        mostrarDetalleStockProductores();
        calcularTotalStockEnProductores();
        calcularTotalStockPagoEnProductores();
        calcularTotalStockCreditoEnProductores();
        ocultarColumnasDetalleProductores();
        inicializar();
        
        
    }

    public void inicializar() throws SQLException{
        
        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        
        listaLocaciones = cargarListaLocaciones();
        
        for (int i = 0; i<listaLocaciones.size(); i++){
            
            cbLocacionesDisponibles.addItem(listaLocaciones.get(i).getNombre_locacion());
            
        }
        
        /*listaProductores = cargarListaProductores();
        
        for (int i = 0; i<listaProductores.size(); i++){
            
            cbLocacionesDisponibles.addItem(listaProductores.get(i).getNombre_locacion());
            
        }*/
        
        tDetalleStock.requestFocus();
        
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
    
    public void mostrarDetalleStockLocaciones(){
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = stock.mostrarDetalleStockLocacion();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tDetalleStockLocacion.setModel(modelo);
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void calcularTotalStockGlobal(){
        
        try {
            
            Double totalStockGlobal = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockGlobal = stock.calcularTotalStockGlobal();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielGlobal.setText(String.valueOf(totalStockGlobal));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }

    public void calcularTotalStockGlobalPago(){
        
        try {
            
            Double totalStockGlobalPago = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockGlobalPago = stock.calcularTotalStockGlobalPago();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielGlobalPago.setText(String.valueOf(totalStockGlobalPago));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void calcularTotalStockGlobalCredito(){
        
        try {
            
            Double totalStockGlobalCredito = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockGlobalCredito = stock.calcularTotalStockGlobalCredito();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielGlobalCredito.setText(String.valueOf(totalStockGlobalCredito));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void mostrarDetalleStockProductores(){
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = stock.mostrarDetalleStockProductor();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tDetalleStockProductor.setModel(modelo);
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void calcularTotalStockEnProductores(){
        
        try {
            
            Double totalStockProductores = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockProductores = stock.calcularTotalStockEnProductores();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielEnProductores.setText(String.valueOf(totalStockProductores));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }

    public void calcularTotalStockPagoEnProductores(){
        
        try {
            
            Double totalStockPagoProductores = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockPagoProductores = stock.calcularTotalStockPagoEnProductores();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielPagoEnProductores.setText(String.valueOf(totalStockPagoProductores));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void calcularTotalStockCreditoEnProductores(){
        
        try {
            
            Double totalStockCreditoProductores = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockCreditoProductores = stock.calcularTotalStockCreditoEnProductores();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielCreditoEnProductores.setText(String.valueOf(totalStockCreditoProductores));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    public void calcularTotalStockEmbarque(){
        
        try {
            
            Double totalStockEmbarque = 0.00;
            
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            totalStockEmbarque = stock.calcularTotalStockEmbarque();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            lStockMielEmbarcado.setText(String.valueOf(totalStockEmbarque));
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }

    public void ocultarColumnasDetalleLocaciones() {

        /*tDetalleStock1.getColumnModel().getColumn(0).setMaxWidth(0);
        tDetalleStock1().getColumn(0).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tDetalleStock1.getColumnModel().getColumn(1).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(1).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*v.getColumnModel().getColumn(2).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(2).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        
        /*tDetalleStock1.getColumnModel().getColumn(3).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(3).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        /*tDetalleStock1.getColumnModel().getColumn(4).setMaxWidth(0);
        tDetalleStock1.getColumnModel().getColumn(4).setMinWidth(0);
        tDetalleStock1.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleStockLocacion.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleStockLocacion.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockLocacion.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockLocacion.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockLocacion.getColumnModel().getColumn(4).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tDetalleStockLocacion.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void ocultarColumnasDetalleProductores() {

        /*tDetalleStockProductor.getColumnModel().getColumn(0).setMaxWidth(0);
        tDetalleStockProductor().getColumn(0).setMinWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(0).setPreferredWidth(0);*/

        /*tDetalleStockProductor.getColumnModel().getColumn(1).setMaxWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(1).setMinWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        /*tDetalleStockProductor().getColumn(2).setMaxWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(2).setMinWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(2).setPreferredWidth(0);*/

        
        /*tDetalleStockProductor.getColumnModel().getColumn(3).setMaxWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(3).setMinWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(3).setPreferredWidth(0);*/
        
        /*tDetalleStockProductor.getColumnModel().getColumn(4).setMaxWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(4).setMinWidth(0);
        tDetalleStockProductor.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleStockProductor.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleStockProductor.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleStockProductor.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockProductor.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockProductor.getColumnModel().getColumn(4).setCellRenderer(cellRender5);
        cellRender6.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStockProductor.getColumnModel().getColumn(5).setCellRenderer(cellRender6);
        
        ((DefaultTableCellRenderer) tDetalleStockProductor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    

    public void ocultarColumnasDetallePorLocacion() {

        tDetalleMovimientosStock.getColumnModel().getColumn(3).setMaxWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(3).setMinWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(3).setPreferredWidth(0);

        tDetalleMovimientosStock.getColumnModel().getColumn(6).setMaxWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(6).setMinWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(6).setPreferredWidth(0);

        tDetalleMovimientosStock.getColumnModel().getColumn(7).setMaxWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(7).setMinWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(7).setPreferredWidth(0);

        tDetalleMovimientosStock.getColumnModel().getColumn(8).setMaxWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(8).setMinWidth(0);
        tDetalleMovimientosStock.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender6 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleMovimientosStock.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleMovimientosStock.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleMovimientosStock.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tDetalleMovimientosStock.getColumnModel().getColumn(4).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleMovimientosStock.getColumnModel().getColumn(5).setCellRenderer(cellRender5);
        cellRender6.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleMovimientosStock.getColumnModel().getColumn(9).setCellRenderer(cellRender6);
        
        ((DefaultTableCellRenderer) tDetalleMovimientosStock.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void mostrarDetalleMovimientosStockLocacion(int codigoLocacion){
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = stock.mostrarDetalleMovimientosStockLocacion(codigoLocacion);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tDetalleMovimientosStock.setModel(modelo);
            
        }

        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
    
    }
    
    
    ///sirveee
    public void calcularTotalStockLocacion(int codigoLocacion) {

        StockRealMiel stock = new StockRealMiel();
            
        Double ingresoMiel = stock.obtenerDetalleIngresoMiel(codigoLocacion);
        Double egresoMiel = stock.obtenerDetalleEgresoMiel(codigoLocacion);
        Double ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(codigoLocacion);
        Double egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(codigoLocacion);
        Double ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(codigoLocacion);
        Double egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(codigoLocacion);
        Double totalStockLocacion = ingresoMiel - egresoMiel;
        Double totalStockPagoLocacion = ingresoMielPaga - egresoMielPaga;
        Double totalStockImpagoLocacion = ingresoMielImpaga - egresoMielImpaga;
        
        lStockTotalLocacion.setText(String.valueOf(totalStockLocacion));
        lStockPagoLocacion.setText(String.valueOf(totalStockPagoLocacion));
        lStockImpagoLocacion.setText(String.valueOf(totalStockImpagoLocacion));
        
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tProductores = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jScrollPane5 = new javax.swing.JScrollPane();
        tProductores1 = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        tDetalleStock = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tDetalleStockLocacion = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel24 = new javax.swing.JLabel();
        lStockMielGlobal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lStockMielGlobalPago = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lStockMielGlobalCredito = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lStockMielEmbarcado = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lStockMielEmbarcadoPago = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lStockMielEmbarcadoImpago = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lStockMielEmbarcado1 = new javax.swing.JLabel();
        lStockMielEmbarcadoPago1 = new javax.swing.JLabel();
        lStockMielEmbarcadoImpago1 = new javax.swing.JLabel();
        rsbrActualizar = new rojeru_san.RSButtonRiple();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        tDetalleStockProductor = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        rsbrActualizar1 = new rojeru_san.RSButtonRiple();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        lStockMielEnProductores = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lStockMielPagoEnProductores = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lStockMielCreditoEnProductores = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tDetalleMovimientosStock = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel27 = new javax.swing.JLabel();
        lStockTotalLocacion = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lStockPagoLocacion = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lStockImpagoLocacion = new javax.swing.JLabel();
        tbOpciones = new javax.swing.JToolBar();
        bConsultaMovimiento = new javax.swing.JButton();
        rsbrAceptar = new rojeru_san.RSButtonRiple();

        tProductores.setBackground(new java.awt.Color(36, 33, 33));
        tProductores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tProductores.setForeground(new java.awt.Color(207, 207, 207));
        tProductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tProductores.setOpaque(true);
        tProductores.setRowHeight(20);
        tProductores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProductoresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tProductores);

        tProductores1.setBackground(new java.awt.Color(36, 33, 33));
        tProductores1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tProductores1.setForeground(new java.awt.Color(207, 207, 207));
        tProductores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tProductores1.setOpaque(true);
        tProductores1.setRowHeight(20);
        tProductores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProductores1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tProductores1);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("STOCK REAL DE MIEL DE LA EMPRESA");

        tDetalleStock.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStock.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStock.setOpaque(true);
        tDetalleStock.setRowHeight(20);
        tDetalleStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStockMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tDetalleStock);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GESTION DE STOCK DE MIEL - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(800, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        jTabbedPane1.setBackground(new java.awt.Color(51, 84, 111));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("KGS. DE MIEL ACOPIADOS EN CADA LOCACION:");

        tDetalleStockLocacion.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStockLocacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStockLocacion.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStockLocacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStockLocacion.setOpaque(true);
        tDetalleStockLocacion.setRowHeight(20);
        tDetalleStockLocacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStockLocacionMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tDetalleStockLocacion);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("STOCK DE LA EMPRESA:");

        lStockMielGlobal.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockMielGlobal.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal.setText("0.00");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("PAGO:");
        jLabel25.setToolTipText("");

        lStockMielGlobalPago.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobalPago.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielGlobalPago.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobalPago.setText("0.00");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("EN CONSIGNACION:");

        lStockMielGlobalCredito.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobalCredito.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielGlobalCredito.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobalCredito.setText("0.00");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 0));
        jLabel30.setText("STOCK EN EMBARQUE:");

        lStockMielEmbarcado.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockMielEmbarcado.setForeground(new java.awt.Color(255, 255, 0));
        lStockMielEmbarcado.setText("0.00");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 0));
        jLabel31.setText("PAGO:");
        jLabel31.setToolTipText("");

        lStockMielEmbarcadoPago.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcadoPago.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielEmbarcadoPago.setForeground(new java.awt.Color(255, 255, 0));
        lStockMielEmbarcadoPago.setText("0.00");

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 0));
        jLabel32.setText("EN CONSIGNACION:");

        lStockMielEmbarcadoImpago.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcadoImpago.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielEmbarcadoImpago.setForeground(new java.awt.Color(255, 255, 0));
        lStockMielEmbarcadoImpago.setText("0.00");

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 255, 0));
        jLabel33.setText("EN CONSIGNACION:");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 255, 0));
        jLabel34.setText("PAGO:");
        jLabel34.setToolTipText("");

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 255, 0));
        jLabel35.setText("STOCK EN EXPORTACION:");

        lStockMielEmbarcado1.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcado1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockMielEmbarcado1.setForeground(new java.awt.Color(0, 255, 0));
        lStockMielEmbarcado1.setText("0.00");

        lStockMielEmbarcadoPago1.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcadoPago1.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielEmbarcadoPago1.setForeground(new java.awt.Color(0, 255, 0));
        lStockMielEmbarcadoPago1.setText("0.00");

        lStockMielEmbarcadoImpago1.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEmbarcadoImpago1.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielEmbarcadoImpago1.setForeground(new java.awt.Color(0, 255, 0));
        lStockMielEmbarcadoImpago1.setText("0.00");

        rsbrActualizar.setBackground(new java.awt.Color(0, 0, 0));
        rsbrActualizar.setText("ACTUALIZAR");
        rsbrActualizar.setToolTipText("");
        rsbrActualizar.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rsbrActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielGlobal))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielGlobalCredito))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielGlobalPago)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcado))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcadoImpago))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcadoPago)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcadoImpago1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcadoPago1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEmbarcado1)))
                        .addGap(0, 219, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rsbrActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsbrActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(lStockMielEmbarcado)
                        .addComponent(jLabel24)
                        .addComponent(lStockMielGlobal))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(lStockMielEmbarcado1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(lStockMielGlobalPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(lStockMielGlobalCredito)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lStockMielEmbarcadoPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(lStockMielEmbarcadoImpago)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(lStockMielEmbarcadoPago1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(lStockMielEmbarcadoImpago1))))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Stock de miel global y por locacion", jPanel2);

        jPanel4.setBackground(new java.awt.Color(51, 84, 111));

        jLabel4.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("PRODUCTORES Y KGS. DE MIEL ACOPIADOS EN SUS LOCACIONES:");

        tDetalleStockProductor.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStockProductor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStockProductor.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStockProductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStockProductor.setOpaque(true);
        tDetalleStockProductor.setRowHeight(20);
        tDetalleStockProductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStockProductorMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tDetalleStockProductor);

        rsbrActualizar1.setBackground(new java.awt.Color(0, 0, 0));
        rsbrActualizar1.setText("ACTUALIZAR");
        rsbrActualizar1.setToolTipText("");
        rsbrActualizar1.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rsbrActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrActualizar1ActionPerformed(evt);
            }
        });

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("STOCK DE LA EMPRESA ACOPIADO EN DEPOSITOS DE PRODUCTORES:");

        lStockMielEnProductores.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEnProductores.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockMielEnProductores.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielEnProductores.setText("0.00");

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("PAGO:");
        jLabel37.setToolTipText("");

        lStockMielPagoEnProductores.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielPagoEnProductores.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielPagoEnProductores.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielPagoEnProductores.setText("0.00");

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("EN CONSIGNACION:");

        lStockMielCreditoEnProductores.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielCreditoEnProductores.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        lStockMielCreditoEnProductores.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielCreditoEnProductores.setText("0.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rsbrActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEnProductores))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielCreditoEnProductores))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielPagoEnProductores)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsbrActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lStockMielEnProductores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lStockMielPagoEnProductores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lStockMielCreditoEnProductores))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Miel en productores", jPanel4);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("MOVIMIENTOS EN EL STOCK DE MIEL DE CADA LOCACION:");

        tDetalleMovimientosStock.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleMovimientosStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleMovimientosStock.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleMovimientosStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleMovimientosStock.setOpaque(true);
        tDetalleMovimientosStock.setRowHeight(20);
        tDetalleMovimientosStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleMovimientosStockMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tDetalleMovimientosStock);

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("STOCK DE MIEL TOTAL:");

        lStockTotalLocacion.setBackground(new java.awt.Color(255, 255, 255));
        lStockTotalLocacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockTotalLocacion.setForeground(new java.awt.Color(255, 255, 255));
        lStockTotalLocacion.setText("0.00");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(255, 255, 0));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SELECCIONE UNA LOCACION PARA CONSULTAR SUS MOVIMIENTOS HISTORICOS:");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("PAGO:");

        lStockPagoLocacion.setBackground(new java.awt.Color(255, 255, 255));
        lStockPagoLocacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockPagoLocacion.setForeground(new java.awt.Color(255, 255, 255));
        lStockPagoLocacion.setText("0.00");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("EN CONSIGNACION:");

        lStockImpagoLocacion.setBackground(new java.awt.Color(255, 255, 255));
        lStockImpagoLocacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lStockImpagoLocacion.setForeground(new java.awt.Color(255, 255, 255));
        lStockImpagoLocacion.setText("0.00");

        tbOpciones.setBackground(new java.awt.Color(0, 0, 0));
        tbOpciones.setBorder(null);
        tbOpciones.setFloatable(false);
        tbOpciones.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        bConsultaMovimiento.setBackground(new java.awt.Color(0, 0, 0));
        bConsultaMovimiento.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        bConsultaMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        bConsultaMovimiento.setText("  CONSULTAR MOVIMIENTO  ");
        bConsultaMovimiento.setBorderPainted(false);
        bConsultaMovimiento.setFocusable(false);
        bConsultaMovimiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bConsultaMovimiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bConsultaMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultaMovimientoActionPerformed(evt);
            }
        });
        tbOpciones.add(bConsultaMovimiento);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lStockTotalLocacion))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lStockPagoLocacion)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockImpagoLocacion))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tbOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lStockTotalLocacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lStockPagoLocacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lStockImpagoLocacion))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Historial de movimientos de stock", jPanel3);

        rsbrAceptar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrAceptar.setText("ACEPTAR");
        rsbrAceptar.setToolTipText("");
        rsbrAceptar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Stock de miel global y por locacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tProductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductoresMouseClicked

        //fila = tMovimientos.rowAtPoint(evt.getPoint());
        //codigoProductor = tMovimientos.getValueAt(fila, 0).toString();
    }//GEN-LAST:event_tProductoresMouseClicked

    private void tProductores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductores1MouseClicked
    }//GEN-LAST:event_tProductores1MouseClicked

    private void tDetalleStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStockMouseClicked

        fila = tDetalleStock.rowAtPoint(evt.getPoint());

    }//GEN-LAST:event_tDetalleStockMouseClicked

    private void rsbrAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrAceptarActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrAceptarActionPerformed

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacion = listaLocaciones.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();
            //ejecuto el metodo que me trae a la tabla todos los movimientos de stock realizados en la locacion seleccionada
            mostrarDetalleMovimientosStockLocacion(codigoLocacion);
            ocultarColumnasDetallePorLocacion();
            calcularTotalStockLocacion(codigoLocacion);

        }
        else{

            mostrarDetalleMovimientosStockLocacion(0);
            ocultarColumnasDetallePorLocacion();
            calcularTotalStockLocacion(0);

        }

    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed

    private void tDetalleMovimientosStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleMovimientosStockMouseClicked

        fila2 = tDetalleStockProductor.rowAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tDetalleMovimientosStockMouseClicked

    private void tDetalleStockLocacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStockLocacionMouseClicked

        fila = tDetalleStock.rowAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tDetalleStockLocacionMouseClicked

    private void rsbrActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrActualizarActionPerformed

        //actualiza informacion en la pestaña 0
        mostrarDetalleStockLocaciones();
        calcularTotalStockGlobal();
        calcularTotalStockGlobalPago();
        calcularTotalStockGlobalCredito();
        calcularTotalStockEmbarque();
        ocultarColumnasDetalleLocaciones();
        //actualiza informacion en la pestaña 1
        mostrarDetalleStockProductores();
        calcularTotalStockEnProductores();
        calcularTotalStockGlobalPago();
        calcularTotalStockGlobalCredito();
        ocultarColumnasDetalleProductores();
        
    }//GEN-LAST:event_rsbrActualizarActionPerformed

    private void tDetalleStockProductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStockProductorMouseClicked
    }//GEN-LAST:event_tDetalleStockProductorMouseClicked

    private void rsbrActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrActualizar1ActionPerformed

        //actualiza informacion en la pestaña 0
        mostrarDetalleStockLocaciones();
        calcularTotalStockGlobal();
        calcularTotalStockGlobalPago();
        calcularTotalStockGlobalCredito();
        calcularTotalStockEmbarque();
        ocultarColumnasDetalleLocaciones();
        //actualiza informacion en la pestaña 1
        mostrarDetalleStockProductores();
        calcularTotalStockEnProductores();
        calcularTotalStockGlobalPago();
        calcularTotalStockGlobalCredito();
        ocultarColumnasDetalleProductores();
        
    }//GEN-LAST:event_rsbrActualizar1ActionPerformed

    private void bConsultaMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultaMovimientoActionPerformed
        
        // CONSULTA DETALLADA DEL MOVIMIENTO DE STOCK SELECCIONADO
        
        if (fila2 == -1) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el movimiento cuyos datos desea consultar.", "CONSULTA DETALLADA DE MOVIMIENTO DE STOCK", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{

            FrmDetalleMovimientoStock form = new FrmDetalleMovimientoStock();
            

            form.lFechaMovimiento.setText(tDetalleMovimientosStock.getValueAt(fila2, 0).toString());
            form.lTipoMovimiento.setText(tDetalleMovimientosStock.getValueAt(fila2, 1).toString());
            form.lComprobanteAsociado.setText(tDetalleMovimientosStock.getValueAt(fila2, 2).toString());
            form.lNumeroComprobante.setText(tDetalleMovimientosStock.getValueAt(fila2, 4).toString());
            form.codigoComprobanteConsultado = Integer.valueOf(tDetalleMovimientosStock.getValueAt(fila2, 3).toString());
            form.cantidadMielAfectada = Double.valueOf(tDetalleMovimientosStock.getValueAt(fila2, 5).toString());
            
            //form.codigolocacionDeposito = Integer.valueOf(tDetalleMovimientosStock.getValueAt(fila2, 6).toString());
            
            form.inicializar();

            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();

            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);

            form.setClosable(true);
            form.setIconifiable(false);

        }
            
    }//GEN-LAST:event_bConsultaMovimientoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConsultaMovimiento;
    public javax.swing.JComboBox<String> cbLocacionesDisponibles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lStockImpagoLocacion;
    private javax.swing.JLabel lStockMielCreditoEnProductores;
    private javax.swing.JLabel lStockMielEmbarcado;
    private javax.swing.JLabel lStockMielEmbarcado1;
    private javax.swing.JLabel lStockMielEmbarcadoImpago;
    private javax.swing.JLabel lStockMielEmbarcadoImpago1;
    private javax.swing.JLabel lStockMielEmbarcadoPago;
    private javax.swing.JLabel lStockMielEmbarcadoPago1;
    private javax.swing.JLabel lStockMielEnProductores;
    private javax.swing.JLabel lStockMielGlobal;
    private javax.swing.JLabel lStockMielGlobalCredito;
    private javax.swing.JLabel lStockMielGlobalPago;
    private javax.swing.JLabel lStockMielPagoEnProductores;
    private javax.swing.JLabel lStockPagoLocacion;
    private javax.swing.JLabel lStockTotalLocacion;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rsbrAceptar;
    private rojeru_san.RSButtonRiple rsbrActualizar;
    private rojeru_san.RSButtonRiple rsbrActualizar1;
    public javax.swing.JTable tDetalleMovimientosStock;
    public javax.swing.JTable tDetalleStock;
    public javax.swing.JTable tDetalleStockLocacion;
    public javax.swing.JTable tDetalleStockProductor;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    private javax.swing.JToolBar tbOpciones;
    // End of variables declaration//GEN-END:variables
}
