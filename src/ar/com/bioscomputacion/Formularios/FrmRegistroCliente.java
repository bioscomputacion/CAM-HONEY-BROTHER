/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author Caco
 */
public class FrmRegistroCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmGenerico
     */
    public FrmRegistroCliente() {
        initComponents();
        inicializar();
    }

    public void inicializar(){
        
        tpCliente.setSelectedIndex(0);
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
        jPanel1 = new javax.swing.JPanel();
        tpCliente = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cbCategoriaCliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfDocumento = new javax.swing.JTextField();
        cbNacionalidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tfDomicilio = new javax.swing.JTextField();
        tfTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTRO DE CLIENTE - CAM HONEY BROTHERS");

        jPanel1.setBackground(new java.awt.Color(51, 84, 111));

        tpCliente.setBackground(new java.awt.Color(51, 84, 111));
        tpCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 84, 111));

        cbCategoriaCliente.setBackground(new java.awt.Color(255, 255, 0));
        cbCategoriaCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbCategoriaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CLIENTE STANDARD", "EXPORTADOR INTERNO" }));
        cbCategoriaCliente.setPreferredSize(new java.awt.Dimension(136, 25));
        cbCategoriaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("INGRESE LA INFORMACION PERSONAL DEL CLIENTE:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TELEFONO/S:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("N° DOCUMENTO:");

        tfDocumento.setBackground(new java.awt.Color(51, 84, 111));
        tfDocumento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfDocumento.setForeground(new java.awt.Color(255, 255, 255));
        tfDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDocumentoKeyTyped(evt);
            }
        });

        cbNacionalidad.setBackground(new java.awt.Color(255, 255, 0));
        cbNacionalidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "AFGANISTAN", "ALBANIA", "ALEMANIA", "ANDORRA", "ANGOLA", "ANTIGUA Y BARBUDA", "ARABIA SAUDITA", "ARGELIA", "ARGENTINA", "ARMENIA", "AUSTRALIA", "AUSTRIA", "AZERBAIYÁN", "BAHAMAS", "BANGLADÉS", "BARBADOS", "BARÉIN", "BÉLGICA", "BELICE", "BIELORRUSIA", "BENÍN", "BIRMANIA", "BOLIVIA", "BOSNIA Y HERZEGOVINA", "BOTSUANA", "BRASIL", "BRUNEI", "BULGARIA", "BURKINA FASO", "BURUNDI", "BUTÁN", "CABO VERDE", "CAMBOYA", "CAMERÚN", "CANADÁ", "CATAR", "REPÚBLICA CENTROAFRICANA", "CHAD", "REPÚBLICA CHECA", "CHILE", "CHINA", "CHIPRE", "COLOMBIA", "COMORAS", "REPÚBLICA DEL CONGO", "REPÚBLICA DEMOCRÁTICA DEL CONGO", "COREA DEL NORTE", "COREA DEL SUR", "COSTA DE MARFIL", "COSTA RICA", "CROACIA", "CUBA", "DINAMARCA", "DOMINICA", "REPÚBLICA DOMINICANA", "ECUADOR", "EGIPTO", "EL SALVADOR", "EMIRATOS ÁRABES UNIDOS", "ERITREA", "ESLOVAQUIA", "ESLOVENIA", "ESPAÑA", "ESTADOS UNIDOS", "ESTONIA", "ETIOPÍA", "FILIPINAS", "FINLANDIA", "FIYI", "FRANCIA", "GABÓN", "GAMBIA", "GEORGIA", "GHANA", "GRANADA", "GRECIA", "GUATEMALA", "GUINEA", "GUINEA-BISÁU", "GUINEA ECUATORIAL", "GUYANA", "HAITÍ", "HONDURAS", "HUNGRÍA", "INDIA", "INDONESIA", "IRAK", "IRÁN", "IRLANDA", "ISLANDIA", "ISRAEL", "ITALIA", "JAMAICA", "JAPÓN", "JORDANIA", "KAZAJISTÁN", "KENIA", "KIRGUISTÁN", "KIRIBATI", "KUWAIT", "LAOS", "LESOTO", "LETONIA", "LÍBANO", "LIBERIA", "LIBIA", "LIECHTENSTEIN", "LITUANIA", "LUXEMBURGO", "MACEDONIA DEL NORTE", "MADAGASCAR", "MALASIA", "MALAUI", "MALDIVAS", "MALI / MALÍ", "MALTA", "MARRUECOS", "ISLAS MARSHALL", "MAURICIO", "MAURITANIA", "MÉXICO", "MICRONESIA", "MOLDAVIA", "MÓNACO", "MONGOLIA", "MONTENEGRO", "MOZAMBIQUE", "NAMIBIA", "NAURU", "NEPAL", "NICARAGUA", "NÍGER", "NIGERIA", "NORUEGA", "NUEVA ZELANDA", "OMÁN", "PAÍSES BAJOS", "PAKISTÁN", "PALAOS", "PALESTINA", "PANAMÁ", "PAPÚA NUEVA GUINEA", "PARAGUAY", "PERÚ", "POLONIA", "PORTUGAL", "REINO UNIDO", "RUANDA", "RUMANIA", "RUSIA", "ISLAS SALOMÓN", "SAMOA", "SAN CRISTÓBAL Y NIEVES", "SAN MARINO", "SAN VICENTE Y LAS GRANADINAS", "SANTA LUCÍA", "SANTO TOMÉ Y PRÍNCIPE", "SENEGAL", "SERBIA", "SEYCHELLES", "SIERRA LEONA", "SINGAPUR", "SIRIA", "SOMALIA", "SRI LANKA", "SUAZILANDIA", "SUDÁFRICA", "SUDÁN", "SUDÁN DEL SUR", "SUECIA", "SUIZA", "SURINAM", "TAILANDIA", "TANZANIA", "TAYIKISTÁN", "TIMOR ORIENTAL", "TOGO", "TONGA", "TRINIDAD Y TOBAGO", "TÚNEZ", "TURKMENISTÁN", "TURQUÍA", "TUVALU", "UCRANIA", "UGANDA", "URUGUAY", "UZBEKISTÁN", "VANUATU", "CIUDAD DEL VATICANO", "VENEZUELA", "VIETNAM", "YEMEN", "YIBUTI", "ZAMBIA", "ZIMBABUE" }));
        cbNacionalidad.setPreferredSize(new java.awt.Dimension(136, 25));
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

        tfTelefono.setBackground(new java.awt.Color(51, 84, 111));
        tfTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfTelefono.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CORREO ELECTRONICO:");

        tfCorreo.setBackground(new java.awt.Color(51, 84, 111));
        tfCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCorreo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NACIONALIDAD:");

        tfNombre.setBackground(new java.awt.Color(51, 84, 111));
        tfNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfNombre.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("* NOMBRE/S Y APELLIDO:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("* CATEGORIA:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfDomicilio)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfCorreo))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCategoriaCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfDocumento)
                        .addGap(18, 18, 18)
                        .addComponent(cbNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(333, 333, 333))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategoriaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
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
        jLabel2.setText("INGRESE LOS DATOS DE FACTURACION DEL CLIENTE:");
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
        cbCondicionIVA.setMinimumSize(new java.awt.Dimension(205, 25));
        cbCondicionIVA.setPreferredSize(new java.awt.Dimension(136, 19));
        cbCondicionIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCondicionIVAActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("N° CUIT:");

        tfCuit.setBackground(new java.awt.Color(51, 84, 111));
        tfCuit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tfCuit.setForeground(new java.awt.Color(255, 255, 255));
        tfCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCuitKeyTyped(evt);
            }
        });

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
        rdbrRegistrar.setText("REGISTRAR CLIENTE");
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
                .addComponent(rdbrRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rsbrCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tpCliente)
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

        //para evitar errores al cargar pais del cliente
        String categoriaCliente = "";
        String estadoProvincia = "";
        String localidad = "";
        String nacionalidad = "";
        
        //chequea toda la informacion que no es obligatoria para advertir al usuario del soft de tal situacion
        //de todas formas debe dejar realizar el registro del cliente sin estos datos
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

            JOptionPane.showMessageDialog(null, "Debe seleccionar la categoria del cliente.", "REGISTRO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tpCliente.setSelectedIndex(0);
            cbCategoriaCliente.requestFocus();
            return;

        }

        //el ingreso del nombre del cliente es obligatorio para el registro del mismo
        if (tfNombre.getText().length() == 0) {

            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del cliente.", "REGISTRO DE CLIENTE", JOptionPane.ERROR_MESSAGE);
            tpCliente.setSelectedIndex(0);
            tfNombre.requestFocus();
            return;

        }

        //Si se llego aca es porque se completaron todos los datos o se completaron los datos obligatorios
        //a) si se completaron todos los datos se procede al registro del cliente
        //b) si se completaron solo los datos obligatorios el sistema advierte tal situacion y da la opcion al usuario
        // de registrar el cliente solo con los datos obligatorios o bien volver al registro para ingresar
        //mas o todos los datos faltantes
        int respuesta = 0;
        
        if (informacionOpcionalFaltante) {

            respuesta = JOptionPane.showConfirmDialog(null, "No se ha ingresado toda la informacion requerida. ¿Desea registrar el cliente sin los datos faltantes?", "REGISTRO DE CLIENTE", JOptionPane.YES_NO_OPTION);
            
        }

        //el usuario confirma el registro sin los datos (o bien no habia info faltante y respuesta quedo inicializada en 0)
        if(respuesta == 0){

            //se procede al registro del cliente
            Cliente cliente = new Cliente(tfNombreFantasia.getText().toUpperCase(), tfRazonSocial.getText().toUpperCase(), condicionIVA, tfCuit.getText(), tfDomicilioFiscal.getText().toUpperCase(), "ACTIVO", categoriaCliente,
                    tfNombre.getText().toUpperCase(), tfDocumento.getText(),
                    //Pais, estado y localidad se cargan como valores vacios ya que la tabla
                    //en la base de datos lo permite
                    nacionalidad, estadoProvincia, localidad,
                    tfDomicilio.getText().toUpperCase(), tfTelefono.getText().toUpperCase(),
                    tfCorreo.getText().toUpperCase());

            if (cliente.registrar(cliente)) {

                JOptionPane.showMessageDialog(null, "El cliente ha sido registrado exitosamente.","REGISTRO DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

            } else {

                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar registrar el cliente.","REGISTRO DE CLIENTE", JOptionPane.ERROR_MESSAGE);

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

        // se cancela el registro del cliente y se cierra el formulario
        JOptionPane.showMessageDialog(null, "Se ha cancelado el registro del cliente.", "REGISTRO DE CLIENTE", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }//GEN-LAST:event_rsbrCancelarActionPerformed

    private void cbNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNacionalidadActionPerformed

    private void cbCondicionIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCondicionIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCondicionIVAActionPerformed

    private void cbCategoriaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaClienteActionPerformed

    private void tfDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDocumentoKeyTyped
        
        char c = evt.getKeyChar();

        if (tfDocumento.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
        
    }//GEN-LAST:event_tfDocumentoKeyTyped

    private void tfCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCuitKeyTyped
        
        char c = evt.getKeyChar();

        if (tfCuit.getText().contains(".") && c == '.') {
            getToolkit().beep();
            evt.consume();
        } else if (!Character.isDigit(c)) {
            if (c != '.') {
                getToolkit().beep();
                evt.consume();
            }

        }
        
    }//GEN-LAST:event_tfCuitKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbCategoriaCliente;
    public javax.swing.JComboBox<String> cbCondicionIVA;
    public javax.swing.JComboBox<String> cbNacionalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
