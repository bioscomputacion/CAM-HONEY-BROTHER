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

    public int codigoProductor, codigoPresupuesto, codigoItemPresupuestado, codigoMovimientoCtaCte;
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
        ocultarColumnasItemsPresupuestados();
        inicializar();
        
    }

    public void inicializar() throws SQLException{
        
        //ERROOORRR!!!!!!!, si no hay facturas cargadas aun, el mostrarIDFactura me devuelve 1, pero
        //luego esa factura no se almacena con el codigo 1, sino con el autoincrementable que corresponda
        //lo cual hace que los items facturados en esa factura se asocien a la factura 1
        //que luego no existe!
        //lo mismo con el movimiento correspondiente en la cta. cte.
        //se guarda asociado a la factura 1 pero esta luego no existe, entonces no se pueden vincular
        
        //PARA CORREGIR EL ERROR DE ARRIBA HAGO LO DE ABAJO
        
        //creo el objeto facturaProveedor
        //DEBERIA GUARDARLO EN LA BASE DE DATOS Y SI LA CARGA DE LA FACT SE CANCELA ELIMINAR LA MISMA
        //ENTONCES EL CODIGO DE FACTURA QUE MANEJA AL OBTENER EL MISMO DESDE LA BASE DE DATOS ES REAL
        //Y NO CAUSA ERRORES DE CARGA EN ITEMS FACTURADOS Y ERRORES DE ENLACES ENTRE FAC Y CTA. CTE
        //esta factura en principio queda asociada al productor generico n° 38 y luego, si se confirma
        //la insercion de la misma, se modifican sus datos por los datos reales ingresados por el usuario
        //del soft
        
        //esto lo hago aca y a la variable codigoItemFacturado la tengo que ir incrementando
        //a medida que se cargan items facturados en la grilla
        
        Calendar cal = new GregorianCalendar();
        dcFechaPresupuesto.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaPresupuesto.getCalendar();
        //ffecha de la factura
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        PresupuestoProductor presupuestoProductor = new PresupuestoProductor("-", 0, 38, new Date(a, m, d), new Date(a, m, d), 0.00);
        presupuestoProductor.registrarPresupuestoProductor(presupuestoProductor);
        //almaceno en la variable global codigoFactura el codigo de la nueva factura a registrar
        
        //aca almaceno el codigo de la factura recien cargada para utilizar el mismo para los items
        //y para ubicarla en caso de tener que eliminarla
        codigoPresupuesto = presupuestoProductor.mostrarIdPresupuestoProductor();
        
        codigoItemPresupuestado = presupuestoProductor.mostrarIdItemAPresupuestar(codigoPresupuesto)+1;
        
        tfImporteTotalPresupuesto.setText("0.00");
        tfImporteTotalPresupuesto.setEditable(false);
        
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
    

    public void ocultarColumnasItemsPresupuestados() {

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender3 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender4 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.LEFT);
        tItemsPresupuestados.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsPresupuestados.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        cellRender3.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsPresupuestados.getColumnModel().getColumn(2).setCellRenderer(cellRender3);   
        cellRender4.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsPresupuestados.getColumnModel().getColumn(3).setCellRenderer(cellRender4);   
        
        ((DefaultTableCellRenderer) tItemsPresupuestados.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
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
        jLabel6 = new javax.swing.JLabel();
        dcFechaPresupuesto = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfCantidadItemPresupuestado = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfImporteItemPresupuestado = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tItemsPresupuestados = tItemsPresupuestados = tItemsPresupuestados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfImporteTotalPresupuesto = new javax.swing.JTextField();
        rdbrPresupuestar = new rojeru_san.RSButtonRiple();
        rdbrQuitar = new rojeru_san.RSButtonRiple();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        cbDescripcionItem = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        setTitle("REGISTRO DE PRESUPUESTO DE PRODUCTOR - CAM HONEY BROTHERS");

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
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tpFactura.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DEL PRESUPUESTO:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DEL PRESUPUESTO:");

        dcFechaPresupuesto.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaPresupuesto.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaPresupuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("PRESUPUESTO N°:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEMS PRESUPUESTADOS:");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DESCRIPCION:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CANTIDAD:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        tfCantidadItemPresupuestado.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadItemPresupuestado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadItemPresupuestado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("IMPORTE:");

        tfImporteItemPresupuestado.setBackground(new java.awt.Color(51, 84, 111));
        tfImporteItemPresupuestado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfImporteItemPresupuestado.setForeground(new java.awt.Color(255, 255, 255));

        tItemsPresupuestados.setBackground(new java.awt.Color(153, 255, 255));
        tItemsPresupuestados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCION", "CANTIDAD", "IMPORTE", "SUB TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tItemsPresupuestados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tItemsPresupuestadostItemsFacturadosFacturaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tItemsPresupuestados);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TOTAL:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("$");

        tfImporteTotalPresupuesto.setBackground(new java.awt.Color(0, 102, 153));
        tfImporteTotalPresupuesto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfImporteTotalPresupuesto.setForeground(new java.awt.Color(255, 255, 255));
        tfImporteTotalPresupuesto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        tfImporteTotalPresupuesto.setCaretColor(new java.awt.Color(255, 255, 255));
        tfImporteTotalPresupuesto.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rdbrPresupuestar.setBackground(new java.awt.Color(0, 0, 0));
        rdbrPresupuestar.setForeground(new java.awt.Color(0, 0, 0));
        rdbrPresupuestar.setText("PRESUPUESTAR");
        rdbrPresupuestar.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrPresupuestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrPresupuestarActionPerformed(evt);
            }
        });

        rdbrQuitar.setBackground(new java.awt.Color(0, 0, 0));
        rdbrQuitar.setForeground(new java.awt.Color(0, 0, 0));
        rdbrQuitar.setText("QUITAR");
        rdbrQuitar.setToolTipText("");
        rdbrQuitar.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrQuitarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        cbDescripcionItem.setBackground(new java.awt.Color(36, 33, 33));
        cbDescripcionItem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbDescripcionItem.setForeground(new java.awt.Color(207, 207, 207));
        cbDescripcionItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "KG. DE MIEL", "TAMBOR DE MIEL X 300 KGS.", "LOTE DE MIEL X 70 TAMBORES", "LOTE DE MIEL X 71 TAMBORES", " " }));
        cbDescripcionItem.setPreferredSize(new java.awt.Dimension(136, 19));
        cbDescripcionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescripcionItemActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SELECCIONE LA LOCACION DONDE SERA ACOPIADA LA MIEL ADQUIRIDA:");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbLocacionesDisponibles.setForeground(new java.awt.Color(207, 207, 207));
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfNumeroComprobante))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcFechaPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfCantidadItemPresupuestado))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(tfImporteItemPresupuestado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdbrPresupuestar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbrQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteTotalPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(7, 7, 7)
                        .addComponent(dcFechaPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(7, 7, 7)
                                .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfImporteItemPresupuestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCantidadItemPresupuestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrPresupuestar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfImporteTotalPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpFactura.addTab("Datos del presupuesto", jPanel3);

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
                .addComponent(tpFactura)
                .addGap(18, 18, 18)
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

        //mostrarProductores(tfBusquedaPorNombre.getText());
        //ocultarColumnasProductores();

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

    private void tItemsPresupuestadostItemsFacturadosFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tItemsPresupuestadostItemsFacturadosFacturaMouseClicked

        filaItemsPresupuestados = tItemsPresupuestados.rowAtPoint(evt.getPoint());

    }//GEN-LAST:event_tItemsPresupuestadostItemsFacturadosFacturaMouseClicked

    private void rdbrPresupuestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrPresupuestarActionPerformed

        //chequeo de datos completos
        if (String.valueOf(cbDescripcionItem.getSelectedItem()) == "SELECCIONAR"){

            JOptionPane.showMessageDialog(null, "Se debe seleccionar la descripcion del item a presupuestar.", "PRESUPUESTO DE ITEMS", JOptionPane.ERROR_MESSAGE);
            cbDescripcionItem.requestFocus();
            return;

        }

        if (tfCantidadItemPresupuestado.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Se debe ingresar la cantidad correspondiente a presupuestar.", "PRESUPUESTO DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemPresupuestado.requestFocus();
            return;

        }

        if (Integer.parseInt(tfCantidadItemPresupuestado.getText().toString()) == 0) {

            JOptionPane.showMessageDialog(null, "No se puede presupuestar un item con cantidad menor a una unidad.", "PRESUPUESTO DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemPresupuestado.requestFocus();
            return;

        }

        if (tfImporteItemPresupuestado.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Se debe ingresar el importe correspondiente al item seleccionado.", "PRESUPUESTO DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfImporteItemPresupuestado.requestFocus();
            return;

        }

        if (Double.parseDouble(tfImporteItemPresupuestado.getText().toString()) == 0.00) {

            JOptionPane.showMessageDialog(null, "No se puede presupuestar un item con importe igual a $ 0.00.", "PRESUPUESTO DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfImporteItemPresupuestado.requestFocus();
            return;

        }

        String descripcionItemPresupuestado = String.valueOf(cbDescripcionItem.getSelectedItem());
        double importeItemPresupuestado = Double.parseDouble(tfImporteItemPresupuestado.getText());
        double totalItemPresupuestado = 0.00;

        Double cantidadItemPresupuestado = 0.00;

        switch (descripcionItemPresupuestado){

            case "KG. DE MIEL":
            //se suman los kilos sin convertirlos
            cantidadItemPresupuestado = Double.parseDouble(tfCantidadItemPresupuestado.getText().toString());
            //System.out.println(cantidadItemFacturado);
            totalItemPresupuestado = cantidadItemPresupuestado * importeItemPresupuestado;
            break;

            case "TAMBOR DE MIEL X 300 KGS.":
            //se suman los kilos sin convertirlos
            cantidadItemPresupuestado = Double.parseDouble(tfCantidadItemPresupuestado.getText().toString())*300.00;
            //System.out.println(cantidadItemFacturado);
            totalItemPresupuestado = cantidadItemPresupuestado * importeItemPresupuestado;
            break;

            case "LOTE DE MIEL X 70 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemPresupuestado = Double.parseDouble(tfCantidadItemPresupuestado.getText().toString())*21000.00;
            //System.out.println(cantidadItemFacturado);
            totalItemPresupuestado = cantidadItemPresupuestado * importeItemPresupuestado;
            break;

            case "LOTE DE MIEL X 71 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemPresupuestado = Double.parseDouble(tfCantidadItemPresupuestado.getText().toString())*21300.00;
            //System.out.println(cantidadItemFacturado);
            totalItemPresupuestado = cantidadItemPresupuestado * importeItemPresupuestado;
            break;

        }

        ItemFacturadoPresupuestoProductor itemPresupuestado = new ItemFacturadoPresupuestoProductor(codigoItemPresupuestado, codigoPresupuesto, descripcionItemPresupuestado, cantidadItemPresupuestado, importeItemPresupuestado, totalItemPresupuestado);

        //lo agrego a la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
        itemsAPresupuestar.add(itemPresupuestado);

        //lo agrego a la tabla

        listarItemsPresupuestados();
        ocultarColumnasItemsPresupuestados();
        calcularImporteTotalPresupuesto();

        //limpio los campos
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidadItemPresupuestado.setText("");
        tfImporteItemPresupuestado.setText("");
        cbDescripcionItem.requestFocus();

        //incremento el codigo de item facturado para un potencial proximo item facturado
        codigoItemPresupuestado = codigoItemPresupuestado+1;

    }//GEN-LAST:event_rdbrPresupuestarActionPerformed

    private void listarItemsPresupuestados() {

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"DESCRIPCION","CANTIDAD","IMPORTE","SUB TOTAL"},itemsAPresupuestar.size());
        tItemsPresupuestados.setModel(modelo);
        TableModel modeloDatos = tItemsPresupuestados.getModel();
        
        for (int i = 0; i<itemsAPresupuestar.size(); i++ ){
            
            ItemFacturadoPresupuestoProductor item = itemsAPresupuestar.get(i);
            modeloDatos.setValueAt(item.getDescripcionItemPresupuestado(), i, 0);
            modeloDatos.setValueAt(item.getCantidadItemPresupuestado(), i, 1);
            modeloDatos.setValueAt(item.getImporteItemPresupuestado(), i, 2);
            modeloDatos.setValueAt(item.getTotalItemPresupuestado(), i, 3);
            
        }
        
    }

    public void calcularImporteTotalPresupuesto() {
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        Double saldo = 0.00;
        
        for (int i = 0; i < tItemsPresupuestados.getRowCount(); i++) {
            saldo = saldo + Double.valueOf(tItemsPresupuestados.getValueAt(i, 3).toString());
        }
        
        tfImporteTotalPresupuesto.setText(formateador.format(saldo));
        
    }
    
    private void rdbrQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrQuitarActionPerformed

        //tengo que quitar le item presupuestado de la lista de items a presupuestar
        //los cuales aun no se han dado de alta en la BD sino que aguardan
        //en dicha lista para luego ser recorridos y dados todos de alta
        //hay que eliminar el item de la lista de items a presupuestar y de la grilla que los muestra
        //mientras tanto
        

        if (filaItemsPresupuestados == -1){
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione el item desvincular del presupuesto.", "DESVINCULACION DE ITEM PRESUPUESTADO", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            
            if (itemsAPresupuestar.size()>0){
                
                //lo elimino de la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
                itemsAPresupuestar.remove(filaItemsPresupuestados);

                //lo quito de la tabla

                listarItemsPresupuestados();
                ocultarColumnasItemsPresupuestados();
                calcularImporteTotalPresupuesto();
                JOptionPane.showMessageDialog(null, "El item presupuestado ha sido desvinculado con exito del presupuesto.", "DESVINCULACION DE ITEM PRESUPUESTADO", JOptionPane.INFORMATION_MESSAGE);
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "No existen items presupuestados para poder desvincular.", "DESVINCULACION DE ITEM PRESUPUESTADO", JOptionPane.INFORMATION_MESSAGE);
                
            }
            
            cbDescripcionItem.requestFocus();
            
        }
        
    }//GEN-LAST:event_rdbrQuitarActionPerformed

    private void cbDescripcionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDescripcionItemActionPerformed

    private void cbLocacionesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocacionesDisponiblesActionPerformed

        // cada vez que selecciona un nombre de locacion, se busca su codigo de locacion en la lista de locaciones
        // y se almacena dicho codigo en la variable correspondiente

        if (cbLocacionesDisponibles.getSelectedIndex() != 0){

            //si es cero no se debe hacer nada, ya que es el item "SELECCIONAR"
            //caso contrario busco el codigo asociado al nombre seleccionado
            codigoLocacion = listaLocaciones.get(cbLocacionesDisponibles.getSelectedIndex()).getCodigo_locacion();

        }
    }//GEN-LAST:event_cbLocacionesDisponiblesActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura

        Boolean informacionFactura = (tfImporteTotalPresupuesto.getText().length() == 0 || cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR");

        if (tfIDProductor.getText().length() == 0){

            JOptionPane.showMessageDialog(null, "Debe seleccionar el productor al cual se le realizo la compra de miel.", "REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpFactura.setSelectedIndex(0);
            tProductoresRegistrados.requestFocus();
            return;

        }

        //chequea informacion de la factura, la cual es obligatoria para poder registrar la misma
        if (informacionFactura) {

            if (JOptionPane.showConfirmDialog(null, "La informacion correspondiente al presupuesto se halla incompleta. ¿Desea ingresar la misma?",
                "REGISTRO DE PRESUPUESTO DE PRODUCTOR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            //no tengo claro que hacer aca!
            tpFactura.setSelectedIndex(1);
            tfNumeroComprobante.requestFocus();

        }
        else{

            // deberia cancelarse el registro de la factura!

        }

        }
        else{

            //obtengo las fechas de factura y de vencimiento del pago de la misma
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

            //ver como puedo comparar las fechas de la factura y de vencimiento de la misma para que sean
            //correcatmente almacenadas en la BD

            Double importeFactura = Double.parseDouble(tfImporteTotalPresupuesto.getText());

            //se procede al registro de la factura correspondiente a la compra de miel al productor seleccionado
            //que en realidad es un update de la factura ya ingresada al inicializarse este formulario!
            PresupuestoProductor presupuesto = new PresupuestoProductor(tfNumeroComprobante.getText(), codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), importeFactura);
            presupuesto.modificarPresupuestoProductor(presupuesto, codigoPresupuesto);

            //ahora, se guardan todos los items facturados en dicha factura (crar el metodo)
            //ademas se calcula la cantidad de kgs de miel adquirida para guardarla correctamente
            //en la tabla stock real de miel
            Double totalMielFacturada = 0.00;

            for (int i = 0; i<itemsAPresupuestar.size(); i++ ){

                ItemFacturadoPresupuestoProductor item = itemsAPresupuestar.get(i);

                /*SELECCIONAR
                KG. DE MIEL
                TAMBOR DE MIEL X 300 KGS.
                LOTE DE MIEL X 70 TAMBORES
                LOTE DE MIEL X 71 TAMBORES*/
                
                System.out.println(item.getDescripcionItemPresupuestado());
                System.out.println("");

                switch (item.getDescripcionItemPresupuestado()){

                    case "KG. DE MIEL":
                    //se suman los kilos sin convertirlos
                    totalMielFacturada = totalMielFacturada + item.getCantidadItemPresupuestado();
                    break;

                    case "TAMBOR DE MIEL X 300 KGS.":
                    //se suman los kilos sin convertirlos
                    totalMielFacturada = totalMielFacturada + 300.00;
                    break;

                    case "LOTE DE MIEL X 70 TAMBORES":
                    //se suman los kilos sin convertirlos
                    totalMielFacturada = totalMielFacturada + 21000.00;
                    break;

                    case "LOTE DE MIEL X 71 TAMBORES":
                    //se suman los kilos sin convertirlos
                    totalMielFacturada = totalMielFacturada + 21300.00;
                    break;

                    default:
                    //nada
                    break;

                }

                item.presupuestarItem(item);

            }

            //ahora se guarda el movimiento correspondiente a la factura, en la cta. cte. de la empresa con el productor
            CtaCteProductor ctacteProductor = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a1, m1, d1), "PRESUPUESTO", codigoPresupuesto, tfNumeroComprobante.getText(), importeFactura, 0.00, importeFactura, "PENDIENTE", "");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, SUMANDO LA CANTIDAD DE KGS. COMPRADA EN ESTA FACTURA
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD: PAGOS, IMPAGOS, ETC.

            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("COMPRA");
            stockMiel.setComprobante_asociado("PRESUPUESTO");
            stockMiel.setNumero_comprobante_asociado(codigoPresupuesto);
            //crear metodo para realizar esto:
            //en una variable deberia sumar todos los kilos de miel comprados, los cuales se pueden sacar
            //de las descripciones y cantidades de los items facturados (en la lista esta esa informacion!)
            //esa cantidad obtenida se almacenara en cantidad_miel
            stockMiel.setCantidad_miel(totalMielFacturada);
            //el codigo de la locacion donde se almacenara la miel comprada es un foreign key, si no existe
            //no se almacenara nada!
            //debo obtener el codigo de la locacion a partir del nombre de la misma
            //escogido en el combo de locaciones disponibles

            stockMiel.setLocacion_miel(codigoLocacion);
            
            //chequeo si la compra de miel quedara depositada en la locacion del productor
            Locacion locacion = new Locacion();
            String categoriaLocacion = locacion.mostrarCategoriaLocacion(codigoLocacion);
            
            System.out.println(codigoLocacion);
            System.out.println(categoriaLocacion);
            
            if (categoriaLocacion == "DEPOSITO DE PRODUCTOR"){
                
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
            //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion

            stockMiel.registrarMovimientoStock(stockMiel);

            this.dispose();
            //tambien: en lugar del dispose, deberia limpiar campos y dar la opcion
            //de registrar una nueva factura de compra de miel

        }

    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        PresupuestoProductor factura = new PresupuestoProductor();
        factura.eliminarPresupuestoProductor(codigoPresupuesto);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rdbrPresupuestar;
    private rojeru_san.RSButtonRiple rdbrQuitar;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tItemsPresupuestados;
    public javax.swing.JTable tProductoresRegistrados;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadItemPresupuestado;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfImporteItemPresupuestado;
    public javax.swing.JTextField tfImporteTotalPresupuesto;
    public javax.swing.JTextField tfLocalidadProductor;
    public javax.swing.JTextField tfNombreProductor;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfProvinciaProductor;
    private javax.swing.JTabbedPane tpFactura;
    // End of variables declaration//GEN-END:variables
}
