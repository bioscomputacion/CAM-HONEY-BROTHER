/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Formularios;

import ar.com.bioscomputacion.Funciones.StockRealMiel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author braya
 */
public class FrmPrincipal extends javax.swing.JFrame {
    
    //en esta variable se llevara siempre el total de la miel consignada que se ha vendido
    //para poder compensar el stock cuando se desea facturar una consignacion desde una cta. cte. con productor
    //y esa miel consignada ya ha sido vendida, la cual al facturarse sumaria erroneamente el stock pago de miel
    //CADA VEZ QUE SE REALIZA UNA VENTA QUE INVOLUCRA MIEL EN CONSIGNACION
    //LA CANTIDAD CORRESPONDIENTE A LA MIEL EN CONSIGNACION EN DICHA VENTA, SE ALMACENARA EN LA VARIABLE
    //mielConsignacionVendida para que dicho numero sirva luego para la compensacion del stock global de miel
    Double mielConsignacionVendida = 0.00;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Iconos/forms.png")).getImage());
        menuPrincipal.setOpaque(true);
        menuPrincipal.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new java.awt.Color(51, 84, 111));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        
        txtCod_usuario.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deskPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel2 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTipo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCod_usuario = new javax.swing.JTextField();
        menuPrincipal = new javax.swing.JMenuBar();
        menuPersona = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        menuGestion = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        menuCompraMiel = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuTraslados = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        menuCtasCtes = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CAMHONEYBROTHER - SOFTWARE");
        setResizable(false);

        deskPrincipal.setPreferredSize(new java.awt.Dimension(980, 550));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/Imagenes/Logo_principal.png"))); // NOI18N

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelHora1.setFont(new java.awt.Font("Roboto Bold", 3, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Usuario:");

        lbNombre.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(255, 255, 255));
        lbNombre.setText("NOMBRE DEL USUARIO");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Tipo:");

        lbTipo.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbTipo.setForeground(new java.awt.Color(255, 255, 255));
        lbTipo.setText("TIPO DEL USUARIO");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Hora:");

        deskPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(rSLabelHora1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(lbNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(lbTipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPrincipal.setLayer(txtCod_usuario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout deskPrincipalLayout = new javax.swing.GroupLayout(deskPrincipal);
        deskPrincipal.setLayout(deskPrincipalLayout);
        deskPrincipalLayout.setHorizontalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deskPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(deskPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(deskPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCod_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        deskPrincipalLayout.setVerticalGroup(
            deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deskPrincipalLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addGroup(deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(deskPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lbNombre)
                        .addComponent(jLabel4)
                        .addComponent(lbTipo)
                        .addComponent(jLabel5)
                        .addComponent(txtCod_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menuPrincipal.setBackground(new java.awt.Color(47, 110, 164));
        menuPrincipal.setForeground(new java.awt.Color(47, 110, 164));

        menuPersona.setForeground(new java.awt.Color(255, 255, 255));
        menuPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/persona.png"))); // NOI18N
        menuPersona.setText("ALTAS");
        menuPersona.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem8.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem8.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_usuarios.png"))); // NOI18N
        jMenuItem8.setLabel("REGISTRO DE CLIENTE");
        jMenuItem8.setOpaque(true);
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuPersona.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem9.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem9.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem9.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_usuarios.png"))); // NOI18N
        jMenuItem9.setLabel("REGISTRO DE PRODUCTOR");
        jMenuItem9.setOpaque(true);
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuPersona.add(jMenuItem9);

        menuPrincipal.add(menuPersona);

        menuGestion.setForeground(new java.awt.Color(255, 255, 255));
        menuGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/herramientas.png"))); // NOI18N
        menuGestion.setText("GESTION");
        menuGestion.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem10.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem10.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem10.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_cta_cte.png"))); // NOI18N
        jMenuItem10.setText("GESTION DE PRODUCTORES");
        jMenuItem10.setOpaque(true);
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuGestion.add(jMenuItem10);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem4.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem4.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_cta_cte.png"))); // NOI18N
        jMenuItem4.setText("GESTION DE CLIENTES");
        jMenuItem4.setOpaque(true);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuGestion.add(jMenuItem4);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem11.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem11.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem11.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_cta_cte.png"))); // NOI18N
        jMenuItem11.setText("GESTION DEL STOCK DE MIEL");
        jMenuItem11.setOpaque(true);
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        menuGestion.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem12.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem12.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem12.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/gestion_cta_cte.png"))); // NOI18N
        jMenuItem12.setText("GESTION Y REGISTRO DE LOCACIONES");
        jMenuItem12.setOpaque(true);
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        menuGestion.add(jMenuItem12);

        menuPrincipal.add(menuGestion);

        menuCompraMiel.setForeground(new java.awt.Color(255, 255, 255));
        menuCompraMiel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/producto.png"))); // NOI18N
        menuCompraMiel.setText("COMPRAS E INGRESOS");
        menuCompraMiel.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem1.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem1.setText("REGISTRO DE COMPRA CON FACTURA A PRODUCTOR");
        jMenuItem1.setOpaque(true);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuCompraMiel.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem3.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem3.setText("REGISTRO DE COMPRA CON PRESUPUESTO A PRODUCTOR");
        jMenuItem3.setOpaque(true);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuCompraMiel.add(jMenuItem3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItem5.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem5.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem5.setText("REGISTRO DE COMPRA A CONSIGNACION A PRODUCTOR");
        jMenuItem5.setOpaque(true);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuCompraMiel.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem6.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem6.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem6.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem6.setText("REGISTRO DE INGRESO DE MIEL PROPIA");
        jMenuItem6.setOpaque(true);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuCompraMiel.add(jMenuItem6);

        menuPrincipal.add(menuCompraMiel);

        menuTraslados.setForeground(new java.awt.Color(255, 255, 255));
        menuTraslados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/camión3.png"))); // NOI18N
        menuTraslados.setText("TRASLADOS, VENTAS Y EXPORTACION");
        menuTraslados.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N

        jMenuItem16.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem16.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem16.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem16.setText("REGISTRO DE TRASLADO DE MIEL");
        jMenuItem16.setOpaque(true);
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        menuTraslados.add(jMenuItem16);

        menuPrincipal.add(menuTraslados);

        menuCtasCtes.setForeground(new java.awt.Color(255, 255, 255));
        menuCtasCtes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/cuenta_corriente.png"))); // NOI18N
        menuCtasCtes.setText("CTAS. CTES.");
        menuCtasCtes.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N

        jMenuItem18.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem18.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem18.setText("CUENTAS CORRIENTES DE CLIENTES");
        jMenuItem18.setOpaque(true);
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        menuCtasCtes.add(jMenuItem18);

        jMenuItem19.setBackground(new java.awt.Color(51, 84, 111));
        jMenuItem19.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jMenuItem19.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuItemIcon/historial_venta.png"))); // NOI18N
        jMenuItem19.setText("CUENTAS CORRIENTES CON PRODUCTORES");
        jMenuItem19.setOpaque(true);
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        menuCtasCtes.add(jMenuItem19);

        menuPrincipal.add(menuCtasCtes);

        menuSalir.setForeground(new java.awt.Color(255, 255, 255));
        menuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/bioscomputacion/MenuIcons/cerrar_sesion.png"))); // NOI18N
        menuSalir.setText("CERRAR SESIÓN");
        menuSalir.setFont(new java.awt.Font("Arial", 3, 10)); // NOI18N
        menuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSalirMouseClicked(evt);
            }
        });
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuSalir);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        deskPrincipal.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed

    }//GEN-LAST:event_menuSalirActionPerformed

    private void menuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSalirMouseClicked
        this.dispose();

        FrmLogin form = new FrmLogin();
        form.setVisible(true);
    }//GEN-LAST:event_menuSalirMouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        FrmRegistroCliente form = new FrmRegistroCliente();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);
                            
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        FrmRegistroProductor form = new FrmRegistroProductor();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);
                            
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed

        FrmGestionProductores form = new FrmGestionProductores();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            
            FrmRegistroFacturaProductor form = new FrmRegistroFacturaProductor();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

        try {
            FrmGestionStockMiel form = new FrmGestionStockMiel();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
        } catch (SQLException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

        FrmRegistroLocacion form = new FrmRegistroLocacion();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        try {
            
            FrmRegistroPresupuestoProductor form = new FrmRegistroPresupuestoProductor();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        FrmGestionClientes form = new FrmGestionClientes();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

        try {
            
            FrmRegistroCreditoProductor form = new FrmRegistroCreditoProductor();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        try {
            
            FrmRegistroIngresoMielPropia form = new FrmRegistroIngresoMielPropia();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

        try {
            
            FrmRegistroTraslado form = new FrmRegistroTraslado();
            
            deskPrincipal.add(form);
            Dimension desktopSize = deskPrincipal.getSize();
            Dimension FrameSize = form.getSize();
            
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            
            form.setClosable(true);
            form.setIconifiable(false);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                            
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed

        FrmCtaCteCliente form = new FrmCtaCteCliente();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

        FrmCtaCteConProductor form = new FrmCtaCteConProductor();
        
        deskPrincipal.add(form);
        Dimension desktopSize = deskPrincipal.getSize();
        Dimension FrameSize = form.getSize();

        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        form.setClosable(true);
        form.setIconifiable(false);

    }//GEN-LAST:event_jMenuItem19ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane deskPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JLabel lbNombre;
    public javax.swing.JLabel lbTipo;
    private javax.swing.JMenu menuCompraMiel;
    private javax.swing.JMenu menuCtasCtes;
    public static javax.swing.JMenu menuGestion;
    public static javax.swing.JMenu menuPersona;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenu menuSalir;
    private javax.swing.JMenu menuTraslados;
    private rojeru_san.RSLabelHora rSLabelHora1;
    public javax.swing.JTextField txtCod_usuario;
    // End of variables declaration//GEN-END:variables
}
