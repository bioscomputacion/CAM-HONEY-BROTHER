/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.FacturaProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoPresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.PresupuestoProductor;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
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
public class FrmRegistroPresupuestoProductor extends javax.swing.JInternalFrame {

    public int codigoProductor, codigoPresupuesto, codigoMovimientoCtaCte;
    public List<ItemFacturadoPresupuestoProductor> itemsAPresupuestar = new ArrayList<>();
    public List<Locacion> listaLocaciones = new ArrayList<>();
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacion;

    int fila = -1;
    int filaItemsPresupuestados = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroPresupuestoProductor() throws SQLException {
        
        initComponents();
        mostrarProductores("");
        ocultarColumnasProductores();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        //ahora se guarda el movimiento correspondiente al presupuesto, en la cta. cte. de la empresa con el productor
        PresupuestoProductor presupuesto = new PresupuestoProductor();
        //Ver la forma de averiguar cual es el realmente el numero de presupuesto que se cargara
        //sin tener que insertar un presupuesto de antemano!
        codigoPresupuesto = presupuesto.mostrarIdPresupuestoProductor()+1;

        //preparo las fechas de carga y de vencimiento del presupuesto
        Calendar cal = new GregorianCalendar();
        dcFechaPresupuesto.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaPresupuesto.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        tfNombreProductor.setEditable(false);
        tfDocumentoProductor.setEditable(false);
        tfProvinciaProductor.setEditable(false);
        tfLocalidadProductor.setEditable(false);
        
        //campo con el numero del presupuesto, no se debe poder editar
        tfNumeroComprobante.setText(String.valueOf(codigoPresupuesto));
        tfNumeroComprobante.setEditable(false);
        
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
        tfImporteTotalPresupuesto.setText("$ 0.00");
        
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
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcFechaPresupuesto = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfCantidadKilos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfPrecioUnitario = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tfImporteTotalPresupuesto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfTambores = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfLotes = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE PRESUPUESTO DE PRODUCTOR - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpFactura.setBackground(new java.awt.Color(51, 84, 111));
        tpFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("SELECCIONE EL PRODUCTOR CORRESPONDIENTE:");

        jLabel4.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tProductoresRegistradosMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(tProductoresRegistrados);

        tfIDProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfIDProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfIDProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID PRODUCTOR:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOMBRE:");

        tfNombreProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfNombreProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfNombreProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("N° DOCUMENTO:");

        tfDocumentoProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfDocumentoProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfDocumentoProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PROVINCIA:");

        tfProvinciaProductor.setBackground(new java.awt.Color(0, 0, 0));
        tfProvinciaProductor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfProvinciaProductor.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("LOCALIDAD:");

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
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(tfNombreProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDocumentoProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL PRESUPUESTO:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("* PRESUPUESTO N°:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("* FECHA:");

        dcFechaPresupuesto.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaPresupuesto.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaPresupuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("* VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ITEM A PRESUPUESTAR:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        tfDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDescripcion.setText(" KGS. DE MIEL");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("* KGS.:");

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
        jLabel19.setText("* PRECIO UNITARIO:");

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
        jLabel23.setText("IMPORTE TOTAL:");

        tfImporteTotalPresupuesto.setEditable(false);
        tfImporteTotalPresupuesto.setBackground(new java.awt.Color(255, 0, 0));
        tfImporteTotalPresupuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteTotalPresupuesto.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CONVERSION A TAMBORES:");

        tfTambores.setEditable(false);
        tfTambores.setBackground(new java.awt.Color(0, 0, 0));
        tfTambores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTambores.setForeground(new java.awt.Color(255, 255, 255));
        tfTambores.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CONVERSION A LOTES:");

        tfLotes.setEditable(false);
        tfLotes.setBackground(new java.awt.Color(0, 0, 0));
        tfLotes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfLotes.setForeground(new java.awt.Color(255, 255, 255));
        tfLotes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("* SELECCIONE EL DESTINO DE LA MIEL ADQUIRIDA:");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(255, 255, 0));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcFechaPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel16)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tfPrecioUnitario)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tfImporteTotalPresupuesto)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel21)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFechaPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel23)
                                .addComponent(jLabel18))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfImporteTotalPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCantidadKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTambores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        tpFactura.addTab("Datos para el presupuesto", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR PRESUPUESTO");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpFactura)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tfBusquedaPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaPorNombreKeyReleased

        mostrarProductores(tfBusquedaPorNombre.getText());
        ocultarColumnasProductores();
        
    }//GEN-LAST:event_tfBusquedaPorNombreKeyReleased

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

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura

        Boolean informacionFactura = (tfImporteTotalPresupuesto.getText().length() == 0 || tfImporteTotalPresupuesto.getText().equals("$ 0.0") || tfImporteTotalPresupuesto.getText().equals("$ 0.00") || cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR");

        if (tfIDProductor.getText().length() == 0){

            JOptionPane.showMessageDialog(null, "Debe seleccionar el productor al cual se le realizo la compra de miel.", "REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tProductoresRegistrados.requestFocus();
            return;

        }

        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al presupuesto se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(1);
            tfCantidadKilos.requestFocus();
            return;
            
        }
        
        //se procede al registro del presupuesto correspondiente a la compra de miel al productor seleccionado
        
        //obtengo las fechas de presupuesto y de vencimiento del pago del mismo
        //ver como puedo comparar las fechas del presupuesto y de vencimiento de la misma para que sean 
        //correcatmente almacenadas en la BD
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaPresupuesto.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;
        cal2 = dcFechaVencimiento.getCalendar();
        //ffecha de vencimiento de la factura
        d2 = cal2.get(Calendar.DAY_OF_MONTH);
        m2 = cal2.get(Calendar.MONTH);
        a2 = cal2.get(Calendar.YEAR) - 1900;

        Double importePresupuesto = Double.parseDouble(tfCantidadKilos.getText()) * Double.parseDouble(tfPrecioUnitario.getText());
        Double cantidadMielPresupuestada = Double.parseDouble(tfCantidadKilos.getText());
        String numeroComprobante = String.valueOf(tfNumeroComprobante.getText());

        PresupuestoProductor presupuesto = new PresupuestoProductor(numeroComprobante, codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importePresupuesto, cantidadMielPresupuestada);
        
        if (presupuesto.registrarPresupuestoProductor(presupuesto)){
            
            //Se guarda el movimiento correspondiente al presupuesto, en la cta. cte. de la empresa con el productor
            codigoPresupuesto = presupuesto.mostrarIdPresupuestoProductor();
            CtaCteProductor ctacteProductor = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a1, m1, d1), "PRESUPUESTO", codigoPresupuesto, numeroComprobante, cantidadMielPresupuestada, importePresupuesto, 0.00, importePresupuesto, "PENDIENTE", "");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, SUMANDO LA CANTIDAD DE KGS. COMPRADA EN ESTA FACTURA
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD: PAGOS, IMPAGOS, ETC.
            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("COMPRA");
            stockMiel.setComprobante_asociado("PRESUPUESTO");
            stockMiel.setId_comprobante_asociado(codigoPresupuesto);
            stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
            //crear metodo para realizar esto:
            //en una variable deberia sumar todos los kilos de miel comprados, los cuales se pueden sacar
            //de las descripciones y cantidades de los items facturados (en la lista esta esa informacion!)
            //esa cantidad obtenida se almacenara en cantidad_miel
            stockMiel.setCantidad_miel(cantidadMielPresupuestada);
            //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
            //no se almacenara nada!
            //debo obtener el codigo de la locacion a partir del nombre de la misma
            //escogido en el combo de locaciones disponibles

            stockMiel.setLocacion_miel(codigoLocacion);

            //chequeo si la compra de miel quedara depositada en la locacion del productor
            Locacion locacion = new Locacion();
            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);

            if (categoriaLocacion.equals("DEPOSITO DE PRODUCTOR")){

                //se trata de una compra cen la cual la miel adquirida quedara acopiada en alguna locacion del productor
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

            //se asigna a la compra el valor: FACTURADA, ya que es una compra con presupuesto.
            stockMiel.setEstado_compra("FACTURADA");

            //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
            stockMiel.registrarMovimientoStock(stockMiel);

            JOptionPane.showMessageDialog(null, "El presupuesto ha sido registrado exitosamente.","REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el presupuesto.","REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);

        }

        this.dispose();

    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed

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
            tfImporteTotalPresupuesto.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfTambores.setText("0 TAMBORES");
            tfLotes.setText("0 LOTES");
            tfCantidadKilos.setText("0.00");
            tfImporteTotalPresupuesto.setText("$ 0.00");

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
            tfImporteTotalPresupuesto.setText("$ "+String.valueOf(Math.round(importeFactura*100.0)/100.0));

        }
        else{

            tfPrecioUnitario.setText("0.00");
            tfImporteTotalPresupuesto.setText("$ 0.00");

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

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacion = listaLocaciones.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();

        }
    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed

    private void tProductoresRegistradosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProductoresRegistradosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tProductoresRegistradosMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbLocacionesDisponibles;
    public com.toedter.calendar.JDateChooser dcFechaPresupuesto;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tProductoresRegistrados;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadKilos;
    public javax.swing.JTextField tfDescripcion;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfImporteTotalPresupuesto;
    public javax.swing.JTextField tfLocalidadProductor;
    public javax.swing.JTextField tfLotes;
    public javax.swing.JTextField tfNombreProductor;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfPrecioUnitario;
    public javax.swing.JTextField tfProvinciaProductor;
    public javax.swing.JTextField tfTambores;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
