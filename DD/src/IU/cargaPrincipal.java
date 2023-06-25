package IU;

public class cargaPrincipal extends javax.swing.JFrame {

    public cargaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        rSTextFieldMaterial1 = new RSMaterialComponent.RSTextFieldMaterial();
        rSPasswordMaterial1 = new RSMaterialComponent.RSPasswordMaterial();
        rSButtonIconShadow1 = new RSMaterialComponent.RSButtonIconShadow();
        btnCargarTransferencias = new newscomponents.RSButtonGradientIcon_new();
        btnCargarRA = new newscomponents.RSButtonGradientIcon_new();
        btnCompraA = new newscomponents.RSButtonGradientIcon_new();
        rSComboBox1 = new rojerusan.RSComboBox();
        rSCheckBox1 = new rojerusan.RSCheckBox();
        rSSwitch1 = new rojerusan.RSSwitch();
        rSRadioButton1 = new rojerusan.RSRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));
        Oscuro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 133, 27, 29));

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 133, 27, 29));

        rSTextFieldMaterial1.setForeground(new java.awt.Color(0, 0, 0));
        rSTextFieldMaterial1.setColorMaterial(new java.awt.Color(0, 102, 0));
        rSTextFieldMaterial1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        rSTextFieldMaterial1.setPlaceholder("");
        getContentPane().add(rSTextFieldMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 214, 73, 20));
        getContentPane().add(rSPasswordMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 296, -1, -1));

        rSButtonIconShadow1.setBackground(new java.awt.Color(0, 153, 0));
        rSButtonIconShadow1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        rSButtonIconShadow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconShadow1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSButtonIconShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 105, -1, -1));

        btnCargarTransferencias.setText("3- TRANSFERENCIAS");
        btnCargarTransferencias.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCargarTransferencias.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCargarTransferencias.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCargarTransferencias.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCargarTransferencias.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarTransferencias.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ZOOM_OUT_MAP);
        btnCargarTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTransferenciasActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarTransferencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 180, 39));

        btnCargarRA.setText("1- REPARTO ANTERIOR");
        btnCargarRA.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCargarRA.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCargarRA.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCargarRA.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCargarRA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarRA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPLY);
        btnCargarRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRAActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 154, 180, 39));

        btnCompraA.setText("2- COMPRA DEL DÍA");
        btnCompraA.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCompraA.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCompraA.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCompraA.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCompraA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCompraA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        btnCompraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraAActionPerformed(evt);
            }
        });
        getContentPane().add(btnCompraA, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 180, 39));

        rSComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        rSComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" }));
        rSComboBox1.setSelectedIndex(-1);
        rSComboBox1.setColorArrow(new java.awt.Color(17, 35, 46));
        rSComboBox1.setColorBorde(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorBoton(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorDisabledIndex(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorFondo(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorSeleccion(new java.awt.Color(102, 102, 102));
        rSComboBox1.setColorSeleccionTXT(new java.awt.Color(0, 0, 0));
        rSComboBox1.setDisabledIdex("-1");
        rSComboBox1.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        getContentPane().add(rSComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 132, 90, 20));

        rSCheckBox1.setText("rSCheckBox1");
        getContentPane().add(rSCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));
        getContentPane().add(rSSwitch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, 40, 30));

        rSRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        rSRadioButton1.setText("CARGA TOTAL");
        rSRadioButton1.setColorUnCheck(new java.awt.Color(0, 153, 51));
        rSRadioButton1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        getContentPane().add(rSRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconShadow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconShadow1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIconShadow1ActionPerformed

    private void btnCargarTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTransferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarTransferenciasActionPerformed

    private void btnCargarRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarRAActionPerformed

    private void btnCompraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompraAActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cargaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new cargaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    public static newscomponents.RSButtonGradientIcon_new btnCargarRA;
    public static newscomponents.RSButtonGradientIcon_new btnCargarTransferencias;
    public static newscomponents.RSButtonGradientIcon_new btnCompraA;
    private RSMaterialComponent.RSButtonIconShadow rSButtonIconShadow1;
    private rojerusan.RSCheckBox rSCheckBox1;
    private rojerusan.RSComboBox rSComboBox1;
    private RSMaterialComponent.RSPasswordMaterial rSPasswordMaterial1;
    private rojerusan.RSRadioButton rSRadioButton1;
    private rojerusan.RSSwitch rSSwitch1;
    private RSMaterialComponent.RSTextFieldMaterial rSTextFieldMaterial1;
    // End of variables declaration//GEN-END:variables
}
