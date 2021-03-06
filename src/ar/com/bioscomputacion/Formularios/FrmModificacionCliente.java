/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.Cliente;
import ar.com.bioscomputacion.Funciones.Productor;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmModificacionCliente extends javax.swing.JInternalFrame {

    int codigoCliente;
    /**
     * Creates new form FrmGenerico
     */
    public FrmModificacionCliente() {
        
        initComponents();
        inicializar();
        
    }
    
    public void inicializar(){
        
        tfNombre.requestFocus();
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        tpCliente = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        cbCategoriaCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfDocumento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbNacionalidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tfDomicilio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        tfNombreFantasia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfRazonSocial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbCondicionIVA = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        tfCuit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfDomicilioFiscal = new javax.swing.JTextField();
        rdbrRegistrar = new rojeru_san.RSButtonRiple();
        rsbrCancelar = new rojeru_san.RSButtonRiple();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MODIFICACION DE CLIENTE - CAM HONEY BROTHERS");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpCliente.setBackground(new java.awt.Color(51, 84, 111));
        tpCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INFORMACION PERSONAL DEL CLIENTE:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("* CATEGORIA:");
        jLabel17.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("* NOMBRE/S Y APELLIDO:");

        tfNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombre.setForeground(new java.awt.Color(255, 255, 255));
        tfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNombreKeyReleased(evt);
            }
        });

        cbCategoriaCliente.setBackground(new java.awt.Color(255, 255, 0));
        cbCategoriaCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbCategoriaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CLIENTE STANDARD", "EXPORTADOR INTERNO" }));
        cbCategoriaCliente.setPreferredSize(new java.awt.Dimension(136, 19));
        cbCategoriaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaClienteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("N?? DOCUMENTO:");

        tfDocumento.setBackground(new java.awt.Color(51, 84, 111));
        tfDocumento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDocumento.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("NACIONALIDAD:");

        cbNacionalidad.setBackground(new java.awt.Color(255, 255, 0));
        cbNacionalidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "AFGANISTAN", "ALBANIA", "ALEMANIA", "ANDORRA", "ANGOLA", "ANTIGUA Y BARBUDA", "ARABIA SAUDITAARGELIA", "ARGENTINA", "ARMENIA", "AUSTRALIA", "AUSTRIA", "AZERBAIY??N", "BAHAMAS", "BANGLAD??S", "BARBADOS", "BAR??IN", "B??LGICA", "BELICE", "BIELORRUSIA", "BEN??N", "BIRMANIABOLIVIA", "BOSNIA Y HERZEGOVINA", "BOTSUANA", "BRASIL", "BRUNEI", "BULGARIA", "BURKINA FASO", "BURUNDI", "BUT??N", "CABO VERDE", "CAMBOYA", "CAMER??N", "CANAD??", "CATAR", "REP??BLICA CENTROAFRICANA", "CHAD", "REP??BLICA CHECACHILE", "CHINA", "CHIPRE", "COLOMBIA", "COMORAS", "REP??BLICA DEL CONGO", "REP??BLICA DEMOCR??TICA DEL CONGO", "COREA DEL NORTE", "COREA DEL SUR", "COSTA DE MARFIL", "COSTA RICA", "CROACIA", "CUBA", "DINAMARCA", "DOMINICA", "REP??BLICA DOMINICANA", "ECUADOR", "EGIPTO", "EL SALVADOR", "EMIRATOS ??RABES UNIDOS", "ERITREA", "ESLOVAQUIA", "ESLOVENIA", "ESPA??A", "ESTADOS UNIDOS", "ESTONIA", "ETIOP??A", "FILIPINAS", "FINLANDIA", "FIYI", "FRANCIA", "GAB??N", "GAMBIA", "GEORGIA", "GHANA", "GRANADA", "GRECIA", "GUATEMALA", "GUINEA", "GUINEA-BIS??U", "GUINEA ECUATORIAL", "GUYANA", "HAIT??", "HONDURAS", "HUNGR??A", "INDIA", "INDONESIA", "IRAK", "IR??N", "IRLANDA", "ISLANDIA", "ISRAEL", "ITALIA", "JAMAICA", "JAP??N", "JORDANIA", "KAZAJIST??N", "KENIA", "KIRGUIST??N", "KIRIBATI", "KUWAIT", "LAOS", "LESOTO", "LETONIA", "L??BANO", "LIBERIA", "LIBIA", "LIECHTENSTEIN", "LITUANIA", "LUXEMBURGO", "MACEDONIA DEL NORTE", "MADAGASCAR", "MALASIA", "MALAUI", "MALDIVAS", "MALI / MAL??", "MALTA", "MARRUECOS", "ISLAS MARSHALL", "MAURICIO", "MAURITANIA", "M??XICO", "MICRONESIA", "MOLDAVIA", "M??NACO", "MONGOLIA", "MONTENEGRO", "MOZAMBIQUE", "NAMIBIA", "NAURU", "NEPAL", "NICARAGUA", "N??GER", "NIGERIA", "NORUEGA", "NUEVA ZELANDA", "OM??N", "PA??SES BAJOS", "PAKIST??N", "PALAOS", "PALESTINA", "PANAM??", "PAP??A NUEVA GUINEA", "PARAGUAY", "PER??", "POLONIA", "PORTUGAL", "REINO UNIDO", "RUANDA", "RUMANIA", "RUSIA", "ISLAS SALOM??N", "SAMOA", "SAN CRIST??BAL Y NIEVES", "SAN MARINO", "SAN VICENTE Y LAS GRANADINAS", "SANTA LUC??A", "SANTO TOM?? Y PR??NCIPE", "SENEGAL", "SERBIA", "SEYCHELLES", "SIERRA LEONA", "SINGAPUR", "SIRIA", "SOMALIA", "SRI LANKA", "SUAZILANDIA", "SUD??FRICA", "SUD??N", "SUD??N DEL SUR", "SUECIA", "SUIZA", "SURINAM", "TAILANDIA", "TANZANIA", "TAYIKIST??N", "TIMOR ORIENTAL", "TOGO", "TONGA", "TRINIDAD Y TOBAGO", "T??NEZ", "TURKMENIST??N", "TURQU??A", "TUVALU", "UCRANIA", "UGANDA", "URUGUAY", "UZBEKIST??N", "VANUATU", "CIUDAD DEL VATICANO", "VENEZUELA", "VIETNAM", "YEMEN", "YIBUTI", "ZAMBIA", "ZIMBABUE" }));
        cbNacionalidad.setPreferredSize(new java.awt.Dimension(136, 19));
        cbNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNacionalidadActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DOMICILIO:");

        tfDomicilio.setBackground(new java.awt.Color(51, 84, 111));
        tfDomicilio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDomicilio.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TELEFONO/S:");

        tfTelefono.setBackground(new java.awt.Color(51, 84, 111));
        tfTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTelefono.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CORREO ELECTRONICO:");

        tfCorreo.setBackground(new java.awt.Color(51, 84, 111));
        tfCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCorreo.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 116, Short.MAX_VALUE))
                            .addComponent(cbCategoriaCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfTelefono))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 94, Short.MAX_VALUE))
                            .addComponent(tfDocumento))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDomicilio)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 580, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCategoriaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tpCliente.addTab("Informacion personal", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 84, 111));

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("DATOS DE FACTURACION DEL CLIENTE:");
        jLabel2.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("NOMBRE DE FANTASIA:");

        tfNombreFantasia.setBackground(new java.awt.Color(51, 84, 111));
        tfNombreFantasia.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombreFantasia.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("RAZON SOCIAL:");

        tfRazonSocial.setBackground(new java.awt.Color(51, 84, 111));
        tfRazonSocial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfRazonSocial.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CONDICION FRENTE AL IVA:");
        jLabel15.setToolTipText("");

        cbCondicionIVA.setBackground(new java.awt.Color(255, 255, 0));
        cbCondicionIVA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbCondicionIVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CONSUMIDOR FINAL", "IVA RESP. MONOTRIBUTO", "IVA RESP. INSCRIPTO", "IVA SUJETO EXENTO" }));
        cbCondicionIVA.setPreferredSize(new java.awt.Dimension(136, 19));
        cbCondicionIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCondicionIVAActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("N?? CUIT:");

        tfCuit.setBackground(new java.awt.Color(51, 84, 111));
        tfCuit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCuit.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("DOMICILIO FISCAL:");

        tfDomicilioFiscal.setBackground(new java.awt.Color(51, 84, 111));
        tfDomicilioFiscal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDomicilioFiscal.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(122, 122, 122))
                    .addComponent(tfNombreFantasia)
                    .addComponent(tfRazonSocial)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(cbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCuit)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(tfDomicilioFiscal)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNombreFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(tfCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDomicilioFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tpCliente.addTab("Datos para la facturacion", jPanel3);

        rdbrRegistrar.setBackground(new java.awt.Color(47, 110, 164));
        rdbrRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/editar.png"))); // NOI18N
        rdbrRegistrar.setText("MODIFICAR CLIENTE");
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
            .addComponent(tpCliente)
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
                .addComponent(tpCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void rdbrRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbrRegistrarActionPerformed

        //Solo es obligatorio el ingreso del nombre del cliente y la categoria del mismo
        Boolean informacionOpcionalFaltante = false;

        //para evitar errores al cargar pais, provincia y localidad seleccionados en los respectivos combos
        String categoriaCliente = "";
        String nacionalidad = "";
        String estadoProvincia = "";
        String localidad = "";

        //chequea toda la informacion que no es obligatoria para advertir al usuario del soft de tal situacion
        //de todas formas debe dejar realizar la modificacion del cliente sin estos datos!
        if (tfDocumento.getText().length() == 0 || cbNacionalidad.getSelectedItem().equals("SELECCIONAR") || tfDomicilio.getText().length() == 0 || tfTelefono.getText().length() == 0 || tfCorreo.getText().length() == 0 || tfNombreFantasia.getText().length() == 0 || tfRazonSocial.getText().length() == 0 || cbCondicionIVA.getSelectedItem().equals("SELECCIONAR") || tfCuit.getText().length() == 0 || tfDomicilioFiscal.getText().length() == 0){
            
            informacionOpcionalFaltante = true;
            
        }
        
        if (String.valueOf(cbCategoriaCliente.getSelectedItem()) != "SELECCIONAR"){

            categoriaCliente = String.valueOf(cbCategoriaCliente.getSelectedItem()).toUpperCase();

        }

        if (String.valueOf(cbNacionalidad.getSelectedItem()) != "SELECCIONAR"){

            nacionalidad = String.valueOf(cbNacionalidad.getSelectedItem()).toUpperCase();

        }

        String condicionIVA = "";
        if (String.valueOf(cbCondicionIVA.getSelectedItem()) != "SELECCIONAR"){

            condicionIVA = String.valueOf(cbCondicionIVA.getSelectedItem()).toUpperCase();

        }

        //chequeo de categoria de cliente
        if (String.valueOf(cbCategoriaCliente.getSelectedItem()) == "SELECCIONAR") {

            JOptionPane.showMessageDialog(null, "Debe seleccionar la categoria del cliente.", "MODIFICACION DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tpCliente.setSelectedIndex(0);
            tfNombre.requestFocus();
            return;

        }

        //el ingreso del nombre del cliente es obligatorio para el registro del mismo
        if (tfNombre.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del cliente.", "MODIFICACION DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tpCliente.setSelectedIndex(0);
            tfNombre.requestFocus();
            return;

        }

        //Si se llego aca es porque se completaron todos los datos o se completaron los datos obligatorios
        //a) si se completaron todos los datos se procede a la modificacion del cliente
        //b) si se completaron solo los datos obligatorios el sistema advierte tal situacion y da la opcion al usuario
        // de modificar el cliente solo con los datos obligatorios o bien volver a la modificacion para ingresar
        //mas o todos los datos faltantes
        int respuesta = 0;
        
        if (informacionOpcionalFaltante) {

            respuesta = JOptionPane.showConfirmDialog(null, "No se ha ingresado toda la informacion requerida. ??Desea modificar solos los datos ingresados?", "MODIFICACION DE CLIENTE", JOptionPane.YES_NO_OPTION);
            
        }

        //el usuario confirma el registro sin los datos (o bien no habia info faltante y respuesta quedo inicializada en 0)
        if(respuesta == 0){

            //se procede a la modificacion de los datos del cliente
            Cliente cliente = new Cliente(tfNombreFantasia.getText().toUpperCase(), tfRazonSocial.getText().toUpperCase(), condicionIVA, tfCuit.getText(),tfDomicilioFiscal.getText().toUpperCase(),"ACTIVO", categoriaCliente,
                tfNombre.getText().toUpperCase(), tfDocumento.getText(),
                //Pais, estado y localidad se cargan como valores vacios ya que la tabla
                //en la base de datos lo permite
                nacionalidad, estadoProvincia, localidad,
                tfDomicilio.getText().toUpperCase(), tfTelefono.getText().toUpperCase(),
                tfCorreo.getText().toUpperCase());

            if (cliente.modificar(cliente, codigoCliente)) {

                JOptionPane.showMessageDialog(null, "La informacion del cliente ha sido modificada exitosamente.","MODIFICACION DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

            } else {

                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar modificar la informacion del cliente.","MODIFICACION DE CLIENTE", JOptionPane.ERROR_MESSAGE);

            }

        }
        //el usuario cancela la insercion del cliente sin los datos
        else{

            tpCliente.setSelectedIndex(0);
            tfNombre.requestFocus();
            return;

        }
      
    }//GEN-LAST:event_rdbrRegistrarActionPerformed

    private void rsbrCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbrCancelarActionPerformed

        // se cancela el registro del productor y se cierra el formulario
        JOptionPane.showMessageDialog(null, "Se ha cancelado la modificacion del cliente.", "MODIFICACION DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void tfNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNombreKeyReleased

    }//GEN-LAST:event_tfNombreKeyReleased

    private void cbCategoriaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaClienteActionPerformed
    }//GEN-LAST:event_cbCategoriaClienteActionPerformed

    private void cbNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNacionalidadActionPerformed
    }//GEN-LAST:event_cbNacionalidadActionPerformed

    private void cbCondicionIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCondicionIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCondicionIVAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbCategoriaCliente;
    public javax.swing.JComboBox<String> cbCondicionIVA;
    public javax.swing.JComboBox<String> cbNacionalidad;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSButtonRiple rdbrRegistrar;
    private rojeru_san.RSButtonRiple rsbrCancelar;
    public javax.swing.JTextField tfCorreo;
    public javax.swing.JTextField tfCuit;
    public javax.swing.JTextField tfDocumento;
    public javax.swing.JTextField tfDomicilio;
    public javax.swing.JTextField tfDomicilioFiscal;
    public javax.swing.JTextField tfNombre;
    public javax.swing.JTextField tfNombreFantasia;
    public javax.swing.JTextField tfRazonSocial;
    public javax.swing.JTextField tfTelefono;
    private javax.swing.JTabbedPane tpCliente;
    // End of variables declaration//GEN-END:variables
}
