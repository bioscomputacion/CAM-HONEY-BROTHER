/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.ConexionBD;
import ar.com.bioscomputacion.Funciones.CtaCteProductor;
import ar.com.bioscomputacion.Funciones.IngresoMielPropia;
import ar.com.bioscomputacion.Funciones.ItemIngresadoIngresoMiel;
import ar.com.bioscomputacion.Funciones.Locacion;
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
public class FrmRegistroIngresoMielPropia extends javax.swing.JInternalFrame {

    public int codigoIngreso;
    public Double totalMielIngresada;
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
    public FrmRegistroIngresoMielPropia() throws SQLException {
        
        initComponents();
        inicializar();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void inicializar() throws SQLException{
        
        Calendar cal = new GregorianCalendar();
        dcFechaIngreso.setCalendar(cal);
        
        int d, m, a;
        cal = dcFechaIngreso.getCalendar();
        //FECHA DEL CREDITO
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;

        IngresoMielPropia ingresoMiel = new IngresoMielPropia();
        codigoIngreso = ingresoMiel.mostrarIdIngresoMielPropia()+1;
        
        //inicializo variable que almacena la cantidad de miel ingresada
        totalMielIngresada = 0.00;
        
        //campo con el numero del ingreso, no se debe poder editar
        tfNumeroComprobante.setText(String.valueOf(codigoIngreso));
        tfNumeroComprobante.setEditable(false);
        
        //carga del combo de las locaciones disponibles y almacena en la lista las mismas, con codigo y nombre
        //para tener acceso facilmente al codigo de la locacion, segun el nombre seleccionado en el combo
        //para eso, vamos a usar la lista "locaciones", que es un arreglo de objetos del tipo locacion
        
        listaLocaciones = cargarListaLocaciones();
        
        for (int i = 0; i<listaLocaciones.size(); i++){
            
            cbLocacionesDisponibles.addItem(listaLocaciones.get(i).getNombre_locacion());
            
        }
        
        //inicializo campos
        tfKilosAIngresar.setText("0.00");

        dcFechaIngreso.requestFocus();
        
    }

    public ArrayList<Locacion> cargarListaLocaciones() throws SQLException{
        
        ArrayList<Locacion> locaciones = new ArrayList<Locacion>();
        Locacion loc0 = new Locacion();
        loc0.setCodigo_locacion(-1);
        loc0.setNombre_locacion("SELECCIONAR");
        locaciones.add(loc0);
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where categoria = 'DEPOSITO DE ACOPIO PROPIO' or categoria = 'HOMOGENEIZACION' order by codigo_locacion asc");
        
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel1 = new javax.swing.JPanel();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        tfNumeroComprobante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        cbLocacionesDisponibles = new javax.swing.JComboBox<>();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();
        jLabel14 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfKilosAIngresar = new javax.swing.JTextField();
        tfObservacion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        setTitle("REGISTRO DE INGRESO DE MIEL PROPIA - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        rSPanelShadow2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION RELACIONADA AL INGRESO:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("INGRESO N°:");

        tfNumeroComprobante.setBackground(new java.awt.Color(51, 84, 111));
        tfNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNumeroComprobante.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA DEL INGRESO:");
        jLabel6.setToolTipText("");

        dcFechaIngreso.setBackground(new java.awt.Color(36, 33, 33));
        dcFechaIngreso.setForeground(new java.awt.Color(207, 207, 207));
        dcFechaIngreso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ITEM INGRESADO:");
        jLabel7.setToolTipText("");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SELECCIONE EL DESTINO DE LA MIEL INGRESADA:");

        cbLocacionesDisponibles.setBackground(new java.awt.Color(36, 33, 33));
        cbLocacionesDisponibles.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbLocacionesDisponibles.setForeground(new java.awt.Color(207, 207, 207));
        cbLocacionesDisponibles.setPreferredSize(new java.awt.Dimension(136, 19));
        cbLocacionesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocacionesDisponiblesActionPerformed(evt);
            }
        });

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("REGISTRAR INGRESO");
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

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DESCRIPCION:");

        tfDescripcion.setEditable(false);
        tfDescripcion.setBackground(new java.awt.Color(204, 255, 255));
        tfDescripcion.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        tfDescripcion.setText(" KGS. DE MIEL");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("KGS. A INGRESAR:");

        tfKilosAIngresar.setBackground(new java.awt.Color(51, 84, 111));
        tfKilosAIngresar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfKilosAIngresar.setForeground(new java.awt.Color(255, 255, 255));
        tfKilosAIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKilosAIngresarActionPerformed(evt);
            }
        });
        tfKilosAIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfKilosAIngresarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKilosAIngresarKeyTyped(evt);
            }
        });

        tfObservacion.setBackground(new java.awt.Color(51, 84, 111));
        tfObservacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfObservacion.setForeground(new java.awt.Color(255, 255, 255));
        tfObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfObservacionActionPerformed(evt);
            }
        });
        tfObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfObservacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfObservacionKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("INGRESE AQUI UNA OBSERVACION RELACIONADA AL INGRESO:");

        javax.swing.GroupLayout rSPanelShadow2Layout = new javax.swing.GroupLayout(rSPanelShadow2);
        rSPanelShadow2.setLayout(rSPanelShadow2Layout);
        rSPanelShadow2Layout.setHorizontalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow2Layout.createSequentialGroup()
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rSPanelShadow2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                                .addGap(122, 122, 122))
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator4)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfKilosAIngresar))
                                .addGap(18, 18, 18)
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(tfObservacion)
                            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dcFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        rSPanelShadow2Layout.setVerticalGroup(
            rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(7, 7, 7)
                        .addComponent(tfNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addComponent(dcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rSPanelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfKilosAIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rSPanelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLocacionesDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
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

        //Tambien son obligatorios todos los campos referidos al ingresoa: numero de ingreso, fecha
        //items ingresados y locacion de deposito de la miel

        Boolean informacionIngreso = (cbLocacionesDisponibles.getSelectedItem() == "SELECCIONAR" || tfKilosAIngresar.getText().equals("0.00") || tfKilosAIngresar.getText().equals("0.0"));

        //chequea informacion del ingreso, la cual es obligatoria para poder registrar el mismo
        if (informacionIngreso) {

            JOptionPane.showMessageDialog(null, "La informacion correspondiente al ingreso se halla incompleta. Por favor ingresela correctamente.", "REGISTRO DE INGRESO DE MIEL PROPIA", JOptionPane.ERROR_MESSAGE);
            dcFechaIngreso.requestFocus();
            return;

        }
        
        //obtengo las fechas de ingreso
        Calendar cal1, cal2;
        int d1, d2, m1, m2, a1, a2;
        cal1 = dcFechaIngreso.getCalendar();
        //ffecha de la factura
        d1 = cal1.get(Calendar.DAY_OF_MONTH);
        m1 = cal1.get(Calendar.MONTH);
        a1 = cal1.get(Calendar.YEAR) - 1900;

        //se procede al registro del ingresode miel
        //que en realidad es un update del ingreso ya ingresado al inicializarse este formulario!
        IngresoMielPropia ingreso = new IngresoMielPropia(tfNumeroComprobante.getText(), new Date(a1, m1, d1), totalMielIngresada,tfObservacion.getText());

        if (ingreso.registrarIngresoMielPropia(ingreso)){

            //SE DEBE ADEMAS ALTERAR EL STOCK DE MIEL, SUMANDO LA CANTIDAD DE KGS. COMPRADA EN ESTE INGRESO
            // A DICHO STOCK, APUNTANDO ADEMAS EL ESTADO DE ESTA CANTIDAD, son kilos de miel propios (facturados)

            StockRealMiel stockMiel = new StockRealMiel();
            stockMiel.setFecha_movimiento(new Date(a1, m1, d1));
            stockMiel.setTipo_movimiento("INGRESO");
            stockMiel.setComprobante_asociado("INGRESO");
            stockMiel.setId_comprobante_asociado(codigoIngreso);
            stockMiel.setNumero_comprobante_asociado(tfNumeroComprobante.getText());
            //crear metodo para realizar esto:
            //en una variable deberia sumar todos los kilos de miel ingresados, los cuales se pueden sacar
            //de las descripciones y cantidades de los items ingresados (en la lista esta esa informacion!)
            //esa cantidad obtenida se almacenara en cantidad_miel
            stockMiel.setCantidad_miel(totalMielIngresada);
            //el codigo de la locacion donde se almacenara la miel ingresada es un foreign key, si no existe
            //no se almacenara nada!
            //debo obtener el codigo de la locacion a partir del nombre de la misma
            //escogido en el combo de locaciones disponibles

            stockMiel.setLocacion_miel(codigoLocacion);

            //se asigna a la compra el valor: FACTURADA, ya que es un ingreso de miel propia.
            stockMiel.setEstado_compra("FACTURADA");

            stockMiel.registrarMovimientoStock(stockMiel);

            JOptionPane.showMessageDialog(null, "El ingreso ha sido registrado exitosamente.","REGISTRO DE INGRESO DE MIEL PROPIA", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        }
        else{

            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el ingreso.","REGISTRO DE INGRESO DE MIEL PROPIA", JOptionPane.ERROR_MESSAGE);

        }

        this.dispose();
        
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        JOptionPane.showMessageDialog(null, "Esta a punto de cerrar el formulario. Se perderan los cambios no guardados.", "REGISTRO DE INGRESO DE MIEL PROPIA", JOptionPane.INFORMATION_MESSAGE);
        IngresoMielPropia ingreso = new IngresoMielPropia();
        ingreso.eliminarIngresoMielPropia(codigoIngreso);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tfKilosAIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKilosAIngresarActionPerformed

    }//GEN-LAST:event_tfKilosAIngresarActionPerformed

    private void tfKilosAIngresarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAIngresarKeyReleased
        
        //se ha ingresado una cantidad incorrecta o eliminado la misma
        if (tfKilosAIngresar.getText().length() == 0){
            
            JOptionPane.showMessageDialog(null, "Cantidad ingresada incorrecta.","REGISTRO DE INGRESO DE MIEL PROPIA",JOptionPane.ERROR_MESSAGE);
            tfKilosAIngresar.setText(String.valueOf("0.00"));
            tfKilosAIngresar.requestFocus();
            
        }
        else{
            
            Double kilosAIngresar = Double.valueOf(tfKilosAIngresar.getText());
            //se ha ingresado una cantidad incorrecta, igual a 0
            if (kilosAIngresar == 0.00 || kilosAIngresar == 0.0){

                JOptionPane.showMessageDialog(null, "ICantidad ingresada incorrecta.","REGISTRO DE INGRESO DE MIEL PROPIA",JOptionPane.ERROR_MESSAGE);
                tfKilosAIngresar.setText(String.valueOf("0.00"));
                tfKilosAIngresar.requestFocus();

            }
            //se ha ingresado una cantidad correcta, o sea,  mayor a 0
            else{

                //voy guardando aca lo que se ingresa como cantidad a trasladar
                totalMielIngresada = kilosAIngresar;

            }
            
        }

    }//GEN-LAST:event_tfKilosAIngresarKeyReleased

    private void tfKilosAIngresarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKilosAIngresarKeyTyped

        char c = evt.getKeyChar();

        if (tfKilosAIngresar.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
    }//GEN-LAST:event_tfKilosAIngresarKeyTyped

    private void tfObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfObservacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfObservacionActionPerformed

    private void tfObservacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfObservacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfObservacionKeyReleased

    private void tfObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfObservacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfObservacionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbLocacionesDisponibles;
    public com.toedter.calendar.JDateChooser dcFechaIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public static javax.swing.JTextField tfDescripcion;
    public static javax.swing.JTextField tfKilosAIngresar;
    public javax.swing.JTextField tfNumeroComprobante;
    public static javax.swing.JTextField tfObservacion;
    // End of variables declaration//GEN-END:variables
}
