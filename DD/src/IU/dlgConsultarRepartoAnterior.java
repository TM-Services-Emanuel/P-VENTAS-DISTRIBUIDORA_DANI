package IU;

import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.Software;
import Controladores.CabecerasTablas;
import Controladores.controlReparto;
import static Controladores.controlReparto.getTotalReparto;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class dlgConsultarRepartoAnterior extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgConsultarRepartoAnterior(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.consRepartoAnterior(tbReparto);
        cabe.consDetalleRepartoAnterior(tbDetalleRepartoAnterior);
        //controlReparto.listarRepartoAnterior(tbReparto, Integer.parseInt(dlgGestRepartos.txtIdResponsable.getText()));
        controlReparto.listarRepartoAnterior(tbReparto, Integer.parseInt(dlgGestRepartos.txtIdMovil.getText()));
        Renders();

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Añadir productos sobrantes del reparto anterior");
        } else {
            this.setTitle(Software.getSoftware() + " - Añadir productos sobrantes del reparto anterior");
        }
    }

    public static void Renders() {
        dlgConsultarRepartoAnterior.tbReparto.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());
        dlgConsultarRepartoAnterior.tbReparto.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersDetalle() {
        dlgConsultarRepartoAnterior.tbDetalleRepartoAnterior.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReparto = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleRepartoAnterior = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        lbValorTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new newscomponents.RSButtonBigIcon_new();
        btnCargar = new newscomponents.RSButtonBigIcon_new();
        btnSalir = new newscomponents.RSButtonBigIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tbReparto.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbReparto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbReparto.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tbReparto.setShowVerticalLines(false);
        tbReparto.getTableHeader().setResizingAllowed(false);
        tbReparto.getTableHeader().setReorderingAllowed(false);
        tbReparto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRepartoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbRepartoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbReparto);

        jPanel1.setOpaque(false);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tbDetalleRepartoAnterior.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbDetalleRepartoAnterior.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDetalleRepartoAnterior.setEnabled(false);
        tbDetalleRepartoAnterior.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tbDetalleRepartoAnterior.setShowVerticalLines(false);
        tbDetalleRepartoAnterior.getTableHeader().setResizingAllowed(false);
        tbDetalleRepartoAnterior.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDetalleRepartoAnterior);

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DETALLE DEL REPARTO ANTERIOR");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
        );

        lbValorTotal.setBackground(new java.awt.Color(0, 102, 102));
        lbValorTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbValorTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValorTotal.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnActualizar.setBackground(new java.awt.Color(0, 102, 102));
        btnActualizar.setBorder(null);
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setBgHover(new java.awt.Color(1, 123, 123));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnActualizar.setIconTextGap(0);
        btnActualizar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        btnActualizar.setPixels(0);
        btnActualizar.setSizeIcon(60.0F);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnCargar.setBackground(new java.awt.Color(0, 102, 102));
        btnCargar.setBorder(null);
        btnCargar.setText("CARGAR R.A.");
        btnCargar.setBgHover(new java.awt.Color(1, 123, 123));
        btnCargar.setFocusPainted(false);
        btnCargar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCargar.setIconTextGap(0);
        btnCargar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPLY);
        btnCargar.setPixels(0);
        btnCargar.setSizeIcon(60.0F);
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCargar);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setText("SALIR");
        btnSalir.setBgHover(new java.awt.Color(1, 123, 123));
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(60.0F);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlancoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(lbValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbRepartoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRepartoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try{
                btnCargar.doClick();
            }catch(Exception e){}
            
        }
    }//GEN-LAST:event_tbRepartoMouseClicked

    private void tbRepartoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRepartoMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tbDetalleRepartoAnterior);
            controlReparto.listarDetalleRepartoAnterior(tbDetalleRepartoAnterior);
            RendersDetalle();
            DecimalFormat df = new DecimalFormat("#,###");
            lbValorTotal.setText(df.format(Integer.parseInt(String.valueOf(getTotalReparto()).replace(".", "").replace(",", ""))));
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_tbRepartoMousePressed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cabe.consRepartoAnterior(tbReparto);
        cabe.consDetalleRepartoAnterior(tbDetalleRepartoAnterior);
        controlReparto.listarRepartoAnterior(tbReparto, Integer.parseInt(dlgGestRepartos.txtIdResponsable.getText()));
        lbValorTotal.setText("");
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        if (tbReparto.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione el reparto anterior a añadir al nuevo registro");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea anexar estos registros?", "Anexar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                int fila0 = tbReparto.getSelectedRow();
                dlgGestRepartos.txtKmAnterior.setText(tbReparto.getValueAt(fila0, 5).toString().replace(".", "").replace(",", ""));
                dlgGestRepartos.txtCAnterior.setText(tbReparto.getValueAt(fila0, 7).toString());
                dlgGestRepartos.txtCdif.setText(tbReparto.getValueAt(fila0, 8).toString());
                dlgGestRepartos.txtCdifACA.setText(tbReparto.getValueAt(fila0, 9).toString());
                int fila = tbDetalleRepartoAnterior.getRowCount();
                for (int j = 0; j < fila; j++) {
                    String filas[] = {tbDetalleRepartoAnterior.getValueAt(j, 1).toString(), tbDetalleRepartoAnterior.getValueAt(j, 2).toString(), tbDetalleRepartoAnterior.getValueAt(j, 3).toString(), tbDetalleRepartoAnterior.getValueAt(j, 4).toString()};
                    //Mensajes.informacion("ID producto: "+filas[0]+"\nCODINT: "+filas[1]+"\nDESCRIPCION: "+filas[2]+"\nCANTIDAD: "+filas[3]);
                    controlReparto.addTabladesdeRepartoA(Integer.parseInt(filas[0]), filas[1], filas[2], Double.parseDouble(filas[3]), dlgGestRepartos.tbDetalleReparto);
                    dlgGestRepartos.txtValorRA.setText(lbValorTotal.getText().replace(".", "").replace(",", ""));
                    dlgGestRepartos.cant();
                }
                dlgGestRepartos.calcularDiferencia();
                dlgGestRepartos.calcularKilometraje();
                dlgGestRepartos.calcularCajas();
                dlgGestRepartos.Renders();
                this.dispose();
            }

        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(dlgConsultarRepartoAnterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarRepartoAnterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarRepartoAnterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarRepartoAnterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarRepartoAnterior dialog = new dlgConsultarRepartoAnterior(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    public static newscomponents.RSButtonBigIcon_new btnActualizar;
    public static newscomponents.RSButtonBigIcon_new btnCargar;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lbValorTotal;
    public static javax.swing.JTable tbDetalleRepartoAnterior;
    public static javax.swing.JTable tbReparto;
    // End of variables declaration//GEN-END:variables
}
