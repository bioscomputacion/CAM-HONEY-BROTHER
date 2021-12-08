/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
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
    public List<Locacion> listaLocaciones = new ArrayList<>();
    int fila = -1;
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmGestionStockMiel() throws SQLException {
        
        initComponents();
        mostrarDetalleStock();
        calcularTotalStockGlobal();
        ocultarColumnas();
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
    
    public void mostrarDetalleStock(){
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            StockRealMiel stock = new StockRealMiel();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = stock.mostrarDetalleStock();
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tDetalleStock1.setModel(modelo);
            
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

    public void ocultarColumnas() {

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
        tDetalleStock1.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tDetalleStock1.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.RIGHT);
        tDetalleStock1.getColumnModel().getColumn(4).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tDetalleStock1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void mostrarDetalleMovimientosStockLocacion(){
        
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
    
    public void calcularTotalStockLocacion() {

        double mielComprada, mielVendida, mielRecibida, mielEnviada, saldoMiel = 0.00;

        StockRealMiel stock = new StockRealMiel();
            
        mielComprada = stock.obtenerDetalleMielComprada(codigoLocacion);
        mielVendida = stock.obtenerDetalleMielVendida(codigoLocacion);
        mielRecibida = stock.obtenerDetalleMielRecibidaTraslado(codigoLocacion);
        mielEnviada = stock.obtenerDetalleMielEnviadaTraslado(codigoLocacion);
        
        saldoMiel = mielComprada + mielRecibida - mielVendida - mielEnviada;
        lStockTotalLocacion.setText(String.valueOf(saldoMiel));
        
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
        tDetalleStock1 = tProductores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel24 = new javax.swing.JLabel();
        lStockMielGlobal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lStockMielPago = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lStockMielEnConsignacion = new javax.swing.JLabel();
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
        rsbrDetalle = new rojeru_san.RSButtonRiple();
        jLabel28 = new javax.swing.JLabel();
        lStockMielGlobal2 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lStockMielGlobal3 = new javax.swing.JLabel();
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

        setTitle("GESTION DE STOCK DE MIEL - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        jTabbedPane1.setBackground(new java.awt.Color(51, 84, 111));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("KGS. DE MIEL ACOPIADOS EN CADA LOCACION:");

        tDetalleStock1.setBackground(new java.awt.Color(36, 33, 33));
        tDetalleStock1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDetalleStock1.setForeground(new java.awt.Color(207, 207, 207));
        tDetalleStock1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tDetalleStock1.setOpaque(true);
        tDetalleStock1.setRowHeight(20);
        tDetalleStock1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDetalleStock1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tDetalleStock1);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("STOCK DE MIEL GLOBAL DE LA EMPRESA:");

        lStockMielGlobal.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockMielGlobal.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal.setText("0.00");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("STOCK DE MIEL GLOBAL PAGO:");
        jLabel25.setToolTipText("");

        lStockMielPago.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielPago.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockMielPago.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielPago.setText("0.00");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("STOCK DE MIEL GLOBAL EN CONSIGNACION:");

        lStockMielEnConsignacion.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielEnConsignacion.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockMielEnConsignacion.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielEnConsignacion.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielEnConsignacion))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielPago))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielGlobal)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lStockMielGlobal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lStockMielPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lStockMielEnConsignacion))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Stock por locacion / Stock global", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("MOVIMIENTOS DE STOCK POR LOCACION:");

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
        jLabel27.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("STOCK DE MIEL TOTAL:");

        lStockTotalLocacion.setBackground(new java.awt.Color(255, 255, 255));
        lStockTotalLocacion.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockTotalLocacion.setForeground(new java.awt.Color(255, 255, 255));
        lStockTotalLocacion.setText("0.00");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionesDisponibles.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SELECCIONE UNA LOCACION PARA CONSULTAR SUS MOVIMIENTOS HISTORICOS:");

        rsbrDetalle.setBackground(new java.awt.Color(102, 204, 255));
        rsbrDetalle.setText("VER DETALLE");
        rsbrDetalle.setToolTipText("");
        rsbrDetalle.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrDetalleActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("STOCK DE MIEL PAGO:");

        lStockMielGlobal2.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal2.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockMielGlobal2.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal2.setText("0.00");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("STOCK DE MIEL EN CONSIGNACION:");

        lStockMielGlobal3.setBackground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal3.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        lStockMielGlobal3.setForeground(new java.awt.Color(255, 255, 255));
        lStockMielGlobal3.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane11)
                    .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rsbrDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockTotalLocacion))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lStockMielGlobal2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStockMielGlobal3)))
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
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rsbrDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(lStockTotalLocacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(lStockMielGlobal2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lStockMielGlobal3))
                .addGap(7, 7, 7))
        );

        jTabbedPane1.addTab("Registro de movimientos de stock", jPanel3);

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
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addComponent(rsbrAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void tDetalleStock1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleStock1MouseClicked

        fila = tDetalleStock.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tDetalleStock1MouseClicked

    private void rsbrAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrAceptarActionPerformed

        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_rsbrAceptarActionPerformed

    private void tDetalleMovimientosStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDetalleMovimientosStockMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tDetalleMovimientosStockMouseClicked

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacion = listaLocaciones.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();
            //ejecuto el metodo que me trae a la tabla todos los movimientos de stock realizados en la locacion seleccionada
            mostrarDetalleMovimientosStockLocacion();
            calcularTotalStockLocacion();

        }
    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed

    private void rsbrDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rsbrDetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lStockMielEnConsignacion;
    private javax.swing.JLabel lStockMielGlobal;
    private javax.swing.JLabel lStockMielGlobal2;
    private javax.swing.JLabel lStockMielGlobal3;
    private javax.swing.JLabel lStockMielPago;
    private javax.swing.JLabel lStockTotalLocacion;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rsbrAceptar;
    private rojeru_san.RSButtonRiple rsbrDetalle;
    public javax.swing.JTable tDetalleMovimientosStock;
    public javax.swing.JTable tDetalleStock;
    public javax.swing.JTable tDetalleStock1;
    public javax.swing.JTable tProductores;
    public javax.swing.JTable tProductores1;
    // End of variables declaration//GEN-END:variables
}
