/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CreditoProductor;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.ItemFacturadoCreditoProductor;
import ar.com.bioscomputacion.Funciones.Locacion;
import ar.com.bioscomputacion.Funciones.Productor;
import ar.com.bioscomputacion.Funciones.StockRealMiel;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Caco
 */
public class FrmRegistroCreditoProductor extends javax.swing.JInternalFrame {

    public int codigoProductor, codigoCredito, codigoItemFinanciado, codigoMovimientoCtaCte;
    public Double totalMielFinanciada;
    public List<ItemFacturadoCreditoProductor> itemsAFinanciar = new ArrayList<>();
    public List<Locacion> listaLocaciones = new ArrayList<>();
    
    //a medida que se seleccionan locaciones en los combos en estas variables se almacenan sus codigos
    //para luego usarlos a la hora de registrar el traslado
    int codigoLocacion;

    int fila = -1;
    int filaItemsFinanciados = -1;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    
    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroCreditoProductor() throws SQLException {
        
        initComponents();
        mostrarProductores("");
        ocultarColumnasProductores();
        listarItemsFinanciados();
        //ocultarColumnasItemsFinanciados();
        inicializar();
        
    }

    public void limpiarCampos(){

        //tfNumeroComprobante.setText("");
        Calendar cal = new GregorianCalendar();
        dcFechaCredito.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidadItemFinanciado.setText("");
        cbLocacionesDisponibles.setSelectedIndex(0);
        mostrarProductores("");
        ocultarColumnasProductores();
        
        itemsAFinanciar.clear();
        listarItemsFinanciados();
        tpCredito.setSelectedIndex(0);
        tProductoresRegistrados.requestFocus();

    }
    
    public void inicializar() throws SQLException{
        
        Calendar cal = new GregorianCalendar();
        dcFechaCredito.setCalendar(cal);
        dcFechaVencimiento.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaCredito.getCalendar();
        //FECHA DEL CREDITO
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        CreditoProductor creditoProductor = new CreditoProductor("-", 0, 22, new Date(a, m, d), new Date(a, m, d), 0.00);
        creditoProductor.registrarCreditoProductor(creditoProductor);
        //almaceno en la variable global codigoCredito el codigo del nuevo credito a registrar
        
        //aca almaceno el codigo del credito recien cargado para utilizar el mismo para los items
        //y para ubicarlo en caso de tener que eliminarlo
        codigoCredito = creditoProductor.mostrarIdCreditoProductor();
        
        //almaceno el codigo del primer item que se vinculara al credito
        codigoItemFinanciado = creditoProductor.mostrarIdItemAFacturarACredito(codigoCredito)+1;
        
        //inicializo variable que almacena la cantidad de miel financiada en el credito que se va a registrar
        totalMielFinanciada = 0.00;
        
        tfNombreProductor.setEditable(false);
        tfDocumentoProductor.setEditable(false);
        tfProvinciaProductor.setEditable(false);
        tfLocalidadProductor.setEditable(false);
        
        //campo con el numero del presupuesto, no se debe poder editar
        tfNumeroComprobante.setText(String.valueOf(codigoCredito));
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
    

    public void ocultarColumnasItemsFinanciados() {

        DefaultTableCellRenderer cellRender1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRender2 = new DefaultTableCellRenderer();
        
        cellRender1.setHorizontalAlignment(SwingConstants.LEFT);
        tItemsFinanciados.getColumnModel().getColumn(0).setCellRenderer(cellRender1);   
        cellRender2.setHorizontalAlignment(SwingConstants.RIGHT);
        tItemsFinanciados.getColumnModel().getColumn(1).setCellRenderer(cellRender2);   
        
        ((DefaultTableCellRenderer) tItemsFinanciados.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
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

    private void listarItemsFinanciados() {

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"DESCRIPCION","CANTIDAD"},itemsAFinanciar.size());
        tItemsFinanciados.setModel(modelo);
        TableModel modeloDatos = tItemsFinanciados.getModel();
        
        for (int i = 0; i<itemsAFinanciar.size(); i++ ){
            
            ItemFacturadoCreditoProductor item = itemsAFinanciar.get(i);
            modeloDatos.setValueAt(item.getDescripcionItemFinanciado(), i, 0);
            modeloDatos.setValueAt(item.getCantidadItemFinanciado(), i, 1);
            
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
        jPanel1 = new javax.swing.JPanel();
        tpCredito = new javax.swing.JTabbedPane();
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
        dcFechaCredito = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        tfCantidadItemFinanciado = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tItemsFinanciados = tItemsFinanciados = tItemsFinanciados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        rdbrRegistrar1 = new rojeru_san.RSButtonRiple();
        rdbrRegistrar2 = new rojeru_san.RSButtonRiple();
        jLabel9 = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        cbDescripcionItem = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE CREDITO CON PRODUCTOR - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpCredito.setBackground(new java.awt.Color(51, 84, 111));
        tpCredito.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpCredito.addTab("Informacion del productor", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION DE LA FACTURA:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DEL CREDITO:");
        jLabel6.setToolTipText("");

        dcFechaCredito.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaCredito.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaCredito.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("CREDITO N°:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEMS FINANCIADOS:");

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

        tfCantidadItemFinanciado.setBackground(new java.awt.Color(51, 84, 111));
        tfCantidadItemFinanciado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCantidadItemFinanciado.setForeground(new java.awt.Color(255, 255, 255));

        tItemsFinanciados.setBackground(new java.awt.Color(153, 255, 255));
        tItemsFinanciados.setModel(new javax.swing.table.DefaultTableModel(
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
        tItemsFinanciados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tItemsFinanciadostItemsFacturadosFacturaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tItemsFinanciados);

        rdbrRegistrar1.setBackground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar1.setForeground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar1.setText("FINANCIAR");
        rdbrRegistrar1.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrar1ActionPerformed(evt);
            }
        });

        rdbrRegistrar2.setBackground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar2.setForeground(new java.awt.Color(0, 0, 0));
        rdbrRegistrar2.setText("QUITAR");
        rdbrRegistrar2.setToolTipText("");
        rdbrRegistrar2.setFont(new java.awt.Font("Roboto Bold", 3, 12)); // NOI18N
        rdbrRegistrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrar2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VENCIMIENTO:");

        dcFechaVencimiento.setBackground(new java.awt.Color(255, 51, 102));
        dcFechaVencimiento.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaVencimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        cbDescripcionItem.setBackground(new java.awt.Color(36, 33, 33));
        cbDescripcionItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
                                    .addComponent(dcFechaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(cbDescripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfCantidadItemFinanciado))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbLocacionesDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbrRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdbrRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                        .addComponent(dcFechaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCantidadItemFinanciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDescripcionItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbrRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbrRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpCredito.addTab("Datos del credito", jPanel3);

        rsbrCancelar.setBackground(new java.awt.Color(47, 110, 164));
        rsbrCancelar.setText("SALIR");
        rsbrCancelar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rsbrCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbrCancelarActionPerformed(evt);
            }
        });

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR CREDITO");
        rdbrRegistrar.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N
        rdbrRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbrRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpCredito)
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
                .addComponent(tpCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
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

        //tamb, al hacer click en un productor, cancela los datos que se hayan insertado y aun no se hayan guardado
        //en la solapa de la factura
        limpiarCampos();
        
        itemsAFinanciar.clear();
        listarItemsFinanciados();

    }//GEN-LAST:event_tProductoresRegistradosMouseClicked

    private void tItemsFinanciadostItemsFacturadosFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tItemsFinanciadostItemsFacturadosFacturaMouseClicked

        filaItemsFinanciados = tItemsFinanciados.rowAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tItemsFinanciadostItemsFacturadosFacturaMouseClicked

    private void rdbrRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrar1ActionPerformed

        //chequeo de datos completos
        if (String.valueOf(cbDescripcionItem.getSelectedItem()) == "SELECCIONAR"){

            JOptionPane.showMessageDialog(null, "Se debe seleccionar la descripcion del item a financiar.", "FINANCIACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            cbDescripcionItem.requestFocus();
            return;

        }

        if (tfCantidadItemFinanciado.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Se debe ingresar la cantidad correspondiente a financiar.", "FINANCIACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemFinanciado.requestFocus();
            return;

        }

        if (Integer.parseInt(tfCantidadItemFinanciado.getText().toString()) == 0) {

            JOptionPane.showMessageDialog(null, "No se puede financiar un item con cantidad menor a una unidad.", "FINANCIACION DE ITEMS", JOptionPane.ERROR_MESSAGE);
            tfCantidadItemFinanciado.requestFocus();
            return;

        }

        String descripcionItemFinanciado = String.valueOf(cbDescripcionItem.getSelectedItem());

        Double cantidadItemFinanciado = 0.00;
        
        

        switch (descripcionItemFinanciado){

            case "KG. DE MIEL":
            //se suman los kilos sin convertirlos
            cantidadItemFinanciado = Double.parseDouble(tfCantidadItemFinanciado.getText().toString());
            break;

            case "TAMBOR DE MIEL X 300 KGS.":
            //se suman los kilos sin convertirlos
            cantidadItemFinanciado = Double.parseDouble(tfCantidadItemFinanciado.getText().toString())*300.00;
            break;

            case "LOTE DE MIEL X 70 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemFinanciado = Double.parseDouble(tfCantidadItemFinanciado.getText().toString())*21000.00;
            break;

            case "LOTE DE MIEL X 71 TAMBORES":
            //se suman los kilos sin convertirlos
            cantidadItemFinanciado = Double.parseDouble(tfCantidadItemFinanciado.getText().toString())*21300.00;
            break;

        }

        ItemFacturadoCreditoProductor itemFacturado = new ItemFacturadoCreditoProductor(codigoItemFinanciado, codigoCredito, descripcionItemFinanciado, cantidadItemFinanciado);

        //System.out.println(codigoItemFinanciado);
        //System.out.println(codigoCredito);
        //System.out.println(descripcionItemFinanciado);
        //System.out.println(cantidadItemFinanciado);

        //lo agrego a la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
        itemsAFinanciar.add(itemFacturado);
        System.out.println(itemsAFinanciar.size());

        //se suma la cantidad de kgs. de miel a la variable totalMielFinanciada
        totalMielFinanciada = totalMielFinanciada+cantidadItemFinanciado;

        //lo agrego a la tabla
        listarItemsFinanciados();
        ocultarColumnasItemsFinanciados();

        //limpio los campos
        cbDescripcionItem.setSelectedIndex(0);
        tfCantidadItemFinanciado.setText("");
        cbDescripcionItem.requestFocus();

        //incremento el codigo de item facturado para un potencial proximo item facturado
        codigoItemFinanciado = codigoItemFinanciado+1;

    }//GEN-LAST:event_rdbrRegistrar1ActionPerformed

    private void rdbrRegistrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrar2ActionPerformed

        //tengo que quitar le item facturado de la lista de items a facturar
        //los cuales aun no se han dado de alta en la BD sino que aguardan
        //en dicha lista para luego ser recorridos y dados todos de alta
        //hay que eliminar el item de la lista de items a facturar y de la grilla que los muestra
        //mientras tanto

        if (filaItemsFinanciados == -1){

            JOptionPane.showMessageDialog(null, "Por favor seleccione el item a desvincular del credito.", "DESVINCULACION DE ITEM FINANCIADO", JOptionPane.INFORMATION_MESSAGE);

        }
        else{

            if (itemsAFinanciar.size()>0){
                
                //primero obtengo la cantidad de miel del item a punto de desvincularse del credito
                ItemFacturadoCreditoProductor item = itemsAFinanciar.get(filaItemsFinanciados);
                Double cantidadItemFinanciado = item.getCantidadItemFinanciado();
                
                //lo elimino de la lista que luego sera recorrida para almacenar uno por uno los items facturados en la bd
                itemsAFinanciar.remove(filaItemsFinanciados);

                //se resta la cantidad de kgs. de miel del item removido a la variable totalMielFinanciada
                totalMielFinanciada = totalMielFinanciada-cantidadItemFinanciado;
                
                //lo quito de la tabla
                listarItemsFinanciados();
                ocultarColumnasItemsFinanciados();
                JOptionPane.showMessageDialog(null, "El item financiado ha sido desvinculado con exito del credito.", "DESVINCULACION DE ITEM FINANCIADO", JOptionPane.INFORMATION_MESSAGE);

            }
            else{

                JOptionPane.showMessageDialog(null, "No existen items financiados para poder desvincular.", "DESVINCULACION DE ITEM FINANCIADO", JOptionPane.INFORMATION_MESSAGE);

            }

            cbDescripcionItem.requestFocus();

        }

    }//GEN-LAST:event_rdbrRegistrar2ActionPerformed

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

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE FACTURA DE PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
        CreditoProductor credito = new CreditoProductor();
        //credito.eliminarFacturaProductor(codigoCredito);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Es obligatoria la seleccion de un productor de los listados en la grilla
        //Tambien son obligatorios todos los campos referidos a la factura: numero de factura, fecha
        //items facturados y monto total de la factura

        //VER COMO SE CONTROLA QUE SE HAYA INGRESADO AL MENOS UN ITEM AL CREDITOOO!!!
        Boolean informacionFactura = (cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR" || itemsAFinanciar.size()==0);

        if (tfIDProductor.getText().length() == 0){

            JOptionPane.showMessageDialog(null, "Debe seleccionar el productor al cual se le realizo la compra de miel.", "REGISTRO DE CREDITO CON PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            totalMielFinanciada = 0.00;
            itemsAFinanciar.clear();
            tpCredito.setSelectedIndex(0);
            tProductoresRegistrados.requestFocus();
            return;

        }

        //chequea informacion del credito, la cual es obligatoria para poder registrar el mismo
        if (informacionFactura) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al credito se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE CREDITO CON PRODUCTOR", JOptionPane.ERROR_MESSAGE);
            tpCredito.setSelectedIndex(1);
            dcFechaCredito.requestFocus();
            return;
            
        }

        //obtengo las fechas de factura y de vencimiento del pago de la misma
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaCredito.getCalendar();
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

        //se procede al registro del credito correspondiente a la compra de miel al productor seleccionado
        //que en realidad es un update del credito ya ingresado al inicializarse este formulario!
        CreditoProductor credito = new CreditoProductor(tfNumeroComprobante.getText(), codigoMovimientoCtaCte, codigoProductor, new Date(a1, m1, d1), new Date(a2, m2, d2), totalMielFinanciada);
        
        if (credito.modificarCreditoProductor(credito, codigoCredito)){

            //ahora, se guardan todos los items facturados en dicha factura (crar el metodo)

            for (int i = 0; i<itemsAFinanciar.size(); i++ ){

                System.out.println(i);
                ItemFacturadoCreditoProductor item = itemsAFinanciar.get(i);
                item.financiarItem(item);

            }

            //ahora se guarda el movimiento correspondiente al credito, en la cta. cte. de la empresa con el productor
            CtaCteProductor ctacteProductor = new CtaCteProductor(codigoProductor, codigoMovimientoCtaCte, new Date(a1, m1, d1), "CREDITO", codigoCredito, tfNumeroComprobante.getText(), totalMielFinanciada, 0.00, 0.00, 0.00, "PENDIENTE", "");
            ctacteProductor.registrarMovimientoCtaCteProductor(ctacteProductor);

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, SUMANDO LA CANTIDAD DE KGS. COMPRADA EN ESTE CREDITO
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD: PAGOS, IMPAGOS, ETC.

            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("COMPRA");
            stockMiel.setComprobante_asociado("CREDITO");
            stockMiel.setId_comprobante_asociado(codigoCredito);
            stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
            //crear metodo para realizar esto:
            //en una variable deberia sumar todos los kilos de miel comprados, los cuales se pueden sacar
            //de las descripciones y cantidades de los items financiados (en la lista esta esa informacion!)
            //esa cantidad obtenida se almacenara en cantidad_miel
            stockMiel.setCantidad_miel(totalMielFinanciada);
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
            
            //se asigna a la compra el valor: SIN FACTURAR, ya que es un credito. Dicho movimiento cambiara su estado
            //a: FACTURADA, cuando la compra a credito sea facturada o presupuestada.
            stockMiel.setEstado_compra("SIN FACTURAR");

            //caso contrario no cargo ningun codigo de productor ya que la miel no se dejo en su locacion
            stockMiel.registrarMovimientoStock(stockMiel);

            JOptionPane.showMessageDialog(null, "El credito ha sido registrado exitosamente.","REGISTRO DE CREDITO CON PRODUCTOR", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el credito.","REGISTRO DE CREDITO CON PRODUCTOR", JOptionPane.ERROR_MESSAGE);

        }

        this.dispose();

    }//GEN-LAST:event_rdbrRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbDescripcionItem;
    public javax.swing.JComboBox<String> cbLocacionesDisponibles;
    public com.toedter.calendar.JDateChooser dcFechaCredito;
    public com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rdbrRegistrar1;
    private rojeru_san.RSButtonRiple rdbrRegistrar2;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTable tItemsFinanciados;
    public javax.swing.JTable tProductoresRegistrados;
    public javax.swing.JTextField tfBusquedaPorNombre;
    public javax.swing.JTextField tfCantidadItemFinanciado;
    public javax.swing.JTextField tfDocumentoProductor;
    public javax.swing.JTextField tfIDProductor;
    public javax.swing.JTextField tfLocalidadProductor;
    public javax.swing.JTextField tfNombreProductor;
    public javax.swing.JTextField tfNumeroComprobante;
    public javax.swing.JTextField tfProvinciaProductor;
    private javax.swing.JTabbedPane tpCredito;
    // End of variables declaration//GEN-END:variables
}
