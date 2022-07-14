/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoFacturaProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.Locacion;
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
public class FrmRegistroFacturaProductor extends javax.swing.JInternalFrame {

    public int codigoProductor, codigoFactura, codigoMovimientoCtaCte;
    public List<Locacion> listaLocaciones = new ArrayList<>();
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacion;

    int fila = -1;
    int filaItemsFacturados = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroFacturaProductor() throws SQLException {
        
        initComponents();
        mostrarProductores("");
        ocultarColumnasProductores();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        //preparo las fechas de carga y de vencimiento de la factura
        Calendar cal = new GregorianCalendar();
        dcFechaFactura.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaFactura.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        listaLocaciones = cargarListaLocaciones();
        for (int i = 0; i<listaLocaciones.size(); i++){
            
            cbLocacionesDisponibles.addItem(listaLocaciones.get(i).getNombre_locacion());
            
        }
        
        //inicializo campos
        tfCantidadKilos.setText("0.00");
        tfPrecioUnitario.setText("0.00");
        tfImporteTotalFactura.setText("$ 0.00");
        
        tProductoresRegistrados.requestFocus();
        
    }

    public void mostrarProductores(String buscar) {
        
        try {
            
            //se crea un objeto Jtabla llamado "modelo"
            DefaultTableModel modelo;
            //se crea un objeto Fcliente llamado "funcion"
            Productor productor = new Productor();
            //en el onjeto jtabla "modelo" almaceno la tabla resultado de la funcion mostrarClientes del objeto "funcion" del tipo Fcliente
            modelo = productor.listarProductores(buscar);
            //en el objeto tProveedores del tipo jTtable almaceno el objeto del mismo tipo llamado "modelo"
            tProductoresRegistrados.setModel(modelo);

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }
    
    public void ocultarColumnasProductores() {

        /*tProductoresRegistrados.getColumnModel().getColumn(0).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(0).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(0).setPreferredWidth(0);*/
        
        /*tProductoresRegistrados.getColumnModel().getColumn(1).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(1).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(1).setPreferredWidth(0);*/

        tProductoresRegistrados.getColumnModel().getColumn(2).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(2).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(2).setPreferredWidth(0);

        
        tProductoresRegistrados.getColumnModel().getColumn(3).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(3).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        /*tProductoresRegistrados.getColumnModel().getColumn(4).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(4).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(4).setPreferredWidth(0);*/
        
        /*tProductoresRegistrados.getColumnModel().getColumn(5).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(5).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(5).setPreferredWidth(0);*/
        
        /*tProductoresRegistrados.getColumnModel().getColumn(6).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(6).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(6).setPreferredWidth(0);*/
        
        tProductoresRegistrados.getColumnModel().getColumn(7).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(7).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        tProductoresRegistrados.getColumnModel().getColumn(8).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(8).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(8).setPreferredWidth(0);
        
        tProductoresRegistrados.getColumnModel().getColumn(9).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(9).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(9).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(10).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(10).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(10).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(11).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(11).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(11).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(12).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(12).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(12).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(13).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(13).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(13).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(14).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(14).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(14).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(15).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(15).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(15).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(16).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(16).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(16).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(17).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(17).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(17).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(18).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(18).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(18).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(19).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(19).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(19).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(20).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(20).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(20).setPreferredWidth(0);

        tProductoresRegistrados.getColumnModel().getColumn(21).setMaxWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(21).setMinWidth(0);
        tProductoresRegistrados.getColumnModel().getColumn(21).setPreferredWidth(0);

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender5 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.CENTER);
        tProductoresRegistrados.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.LEFT);
        tProductoresRegistrados.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.LEFT);
        tProductoresRegistrados.getColumnModel().getColumn(4).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.CENTER);
        tProductoresRegistrados.getColumnModel().getColumn(5).setCellRenderer(cellRender4);   
        cellRender5.setHorizontalAlignment(SwingConstants.LEFT);
        tProductoresRegistrados.getColumnModel().getColumn(6).setCellRenderer(cellRender5);
        
        ((DefaultTableCellRenderer) tProductoresRegistrados.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    

    public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
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
    
    public ArrayList<String> cargarComboLocaciones() throws SQLException{
        
        ArrayList<String> locaciones = new ArrayList<String>();
        
        //el primer item debe ser "SELECCIONAR"
        locaciones.add("SELECCIONAR");
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select nombre_locacion from locacion");
        
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
        tpFactura = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfBusquedaPorNombre = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tProductoresRegistrados = tProductoresRegistrados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tfIDProductor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfNombreProductor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfDocumentoProductor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfProvinciaProductor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfLocalidadProductor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        dcFechaFactura = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfCantidadKilos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbTipoFactura = new javax.swing.JComboBox<>();
        tfDescripcion = new javax.swing.JTextField();
        tfTambores = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfImporteTotalFactura = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setTitle("REGISTRO DE FACTURA DE PRODUCTOR - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpFactura.setBackground(new java.awt.Color(51, 84, 111));
        tpFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("SELECCIONE EL PRODUCTOR CORRESPONDIENTE:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUSCAR POR NOMBRE:");

        tfBusquedaPorNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfBusquedaPorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfBusquedaPorNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfBusquedaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaPorNombreKeyReleased(evt);
            }
        });

        tProductoresRegistrados.setBackground(new java.awt.Color(36, 33, 33));
        tProductoresRegistrados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tProductoresRegistrados.setForeground(new java.awt.Color(207, 207, 207));
        tProductoresRegistrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tProductoresRegistrados.setOpaque(true);
        tProductoresRegistrados.setRowHeight(20);
        tProductoresRegistrados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProductoresRegistradosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tProductoresRegistrados);

        tfIDProductor.setEditable(false);
        tfIDProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfIDProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfIDProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID PRODUCTOR:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreProductor.setEditable(false);
        tfNombreProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfNombreProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNombreProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoProductor.setEditable(false);
        tfDocumentoProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfDocumentoProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfDocumentoProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PROVINCIA:");

        tfProvinciaProductor.setEditable(false);
        tfProvinciaProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfProvinciaProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfProvinciaProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("LOCALIDAD:");

        tfLocalidadProductor.setEditable(false);
        tfLocalidadProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfLocalidadProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfLocalidadProductor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(tfDocumentoProductor))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(tfProvinciaProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLocalidadProductor)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBusquedaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(tfIDProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDocumentoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfProvinciaProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLocalidadProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA FACTURA:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DE LA FACTURA:");

        dcFechaFactura.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaFactura.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("N°:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEMS FACTURADOS:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ITEM A FACTURAR:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CANTIDAD KGS.:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

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
        jLabel19.setText("PRECIO UNITARIO:");

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

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SELECCIONE EL DESTINO DE LA MIEL ADQUIRIDA:");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbLocacionesDisponibles.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("FACTURA:");

        cbTipoFactura.setBackground(new java.awt.Color(36, 33, 33));
        cbTipoFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoFactura.setForeground(new java.awt.Color(207, 207, 207));
        cbTipoFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "FACTURA A", "FACTURA C" }));
        cbTipoFactura.setPreferredSize(new java.awt.Dimension(136, 19));

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(204, 255, 255));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setText(" KGS. DE MIEL");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(204, 255, 255));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CONVERSION A TAMBORES:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IMPORTE TOTAL:");

        tfImporteTotalFactura.setEditable(false);
        tfImporteTotalFactura.setBackground(new java.awt.Color(255, 255, 255));
        tfImporteTotalFactura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(204, 255, 255));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(cbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel23)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfImporteTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos de la factura", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR FACTURA");
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
            .addComponent(tpFactura)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura
        
        Boolean informacionFactura = (cbTipoFactura.getSelectedItem() == "SELECCIONAR" || tfNumeroComprobante.getText().length() == 0 || tfImporteTotalFactura.getText().length() == 0 || tfImporteTotalFactura.getText().equals("$ 0.00") || tfImporteTotalFactura.getText().equals("$ 0.0") || cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR");
        
        if (tfIDProductor.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar el productor al cual se le realizo la compra de miel.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tProductoresRegistrados.requestFocus();
            return;
            
        }
        
        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente a la factura se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(1);
            tfNumeroComprobante.requestFocus();
            return;
            
        }
        
        //se procede al registro de la factura correspondiente a la compra de miel al productor seleccionado
        
        //obtengo las fechas de factura y de vencimiento del pago de la misma
        //ver como puedo comparar las fechas de la factura y de vencimiento de la misma para que sean 
        //correcatmente almacenadas en la BD
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

        Double importeFactura = Double.parseDouble(tfCantidadKilos.getText()) * Double.parseDouble(tfPrecioUnitario.getText());
        Double cantidadMielFacturada = Double.parseDouble(tfCantidadKilos.getText());
        String tipoFactura = String.valueOf(cbTipoFactura.getSelectedItem());
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());

        FacturaProductor factura = new FacturaProductor(tipoFactura,numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importeFactura, cantidadMielFacturada);
        
        if (factura.registrarFacturaProductor(factura)){

            //Se guarda el movimiento correspondiente a la factura, en la cta. cte. de la empresa con el productor
            codigoFactura = factura.mostrarIdFacturaProductor();
            CtaCteProductor ctacteProductor = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a1, m1, d1), tipoFactura, codigoFactura, numeroComprobante, cantidadMielFacturada, importeFactura, 0.00, importeFactura, "PENDIENTE", "");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, SUMANDO LA CANTIDAD DE KGS. COMPRADA EN ESTA FACTURA
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD: miel propia o miel no propia (esta es miel propia)
            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("COMPRA");
            stockMiel.setComprobante_asociado(tipoFactura);
            stockMiel.setId_comprobante_asociado(codigoFactura);
            stockMiel.setNumero_comprobante_asociado(numeroComprobante);
            stockMiel.setCantidad_miel(cantidadMielFacturada);
            
            //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
            //no se almacenara nada!
            //debo obtener el codigo de la locacion a partir del nombre de la misma
            //escogido en el combo de locaciones disponibles
            stockMiel.setLocacion_miel(codigoLocacion);

            //chequeo si la compra de miel quedara depositada en la locacion del productor
            Locacion locacion = new Locacion();
            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

            if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

                //se trata de una compra en la cual la miel adquirida quedara acopiada en alguna locacion del productor
                //que vende la miel
                //cargo en el campo observacion el codigo del productor vendedor en esta compra
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
            stockMiel.setEstado_compra("FACTURADA");

            //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
            stockMiel.registrarMovimientoStock(stockMiel);

            JOptionPane.showMessageDialog(null, "La factura ha sido registrada exitosamente.","REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar la factura.","REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);

        }

        this.dispose();
            
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    public void calcularImporteTotalFactura() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
        /*for (int i = 0; i < tItemsFacturados.getRowCount(); i++) {
            saldo = saldo + Double.valueOf(tItemsFacturados.getValueAt(i, 3).toString());
        }*/
        
        tfPrecioUnitario.setText(formateador.format(saldo));
    }
    
    
    private void tProductoresRegistradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductoresRegistradosMouseClicked

        fila = tProductoresRegistrados.rowAtPoint(evt.getPoint());
        codigoProductor = Integer.parseInt(tProductoresRegistrados.getValueAt(fila, 0).toString());
        CtaCteProductor ctacteProductor = new CtaCteProductor();
        codigoMovimientoCtaCte = ctacteProductor.mostrarIdMovimiento(codigoProductor)+1;
        
        //cada vez que se hace click sobre la grilla se muestran en los campos debajo lso datos del productor
        //correspondiente a la fila de la grilla cliqueada
        tfIDProductor.setText(tProductoresRegistrados.getValueAt(fila, 0).toString());
        tfNombreProductor.setText(tProductoresRegistrados.getValueAt(fila, 1).toString());
        tfDocumentoProductor.setText(tProductoresRegistrados.getValueAt(fila, 2).toString());
        tfProvinciaProductor.setText(tProductoresRegistrados.getValueAt(fila, 4).toString());
        tfLocalidadProductor.setText(tProductoresRegistrados.getValueAt(fila, 5).toString());
        
    }//GEN-LAST:event_tProductoresRegistradosMouseClicked

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarProductores(tfBusquedaPorNombre.getText());
        ocultarColumnasProductores();
        
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

    private void tfCantidadKilosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCantidadKilosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCantidadKilosActionPerformed

    private void tfCantidadKilosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCantidadKilosKeyReleased

        //VER COMO PUEDO REDONDEAR!
        if (tfCantidadKilos.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double tambores = kilos / 300;
            tfTambores.setText(String.valueOf(Math.round(tambores*100.0)/100.0)+" TAMBORES");
            Double lotes = kilos / 21000;
            tfLotes.setText(String.valueOf(Math.round(lotes*100.0)/100.0)+" LOTES");
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfImporteTotalFactura.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfTambores.setText("0 TAMBORES");
            tfLotes.setText("0 LOTES");
            tfCantidadKilos.setText("0.00");
            tfImporteTotalFactura.setText("$ 0.00");

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

    private void tfPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioUnitarioKeyReleased

        if (tfPrecioUnitario.getText().length() != 0){

            Double kilos = Double.parseDouble(tfCantidadKilos.getText());
            Double precioUnitario = Double.parseDouble(tfPrecioUnitario.getText());
            Double importeFactura = kilos*precioUnitario;
            tfImporteTotalFactura.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfPrecioUnitario.setText("0.00");
            tfImporteTotalFactura.setText("$ 0.00");

        }

    }//GEN-LAST:event_tfPrecioUnitarioKeyReleased

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacion = listaLocaciones.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();

        }
        
    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLocacionesDisponibles;
    public javax.swing.JComboBox<String> cbTipoFactura;
    public com.toedter.calendar.JDateChooser dcFechaFactura;
    public com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tProductoresRegistrados;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfImporteTotalFactura;
    public javax.swing.JTextField tfLocalidadProductor;
    private javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNombreProductor;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfProvinciaProductor;
    private javax.swing.JTextField tfTambores;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
