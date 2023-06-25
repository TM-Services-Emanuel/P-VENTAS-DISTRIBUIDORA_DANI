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
        controlReparto.listarRepartoAnterior(tbReparto, Integer.parseInt(dlgGestRepartos.txtIdResponsable.getText()));
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
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        lbValorTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnActualizar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refress_30.png"))); // NOI18N
        btnActualizar.setText("Actualizar Repartos");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnCargar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/anterior_25.png"))); // NOI18N
        btnCargar.setText("Cargar reparto");
        btnCargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCargar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCargar);

        jButton3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbReparto.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tbReparto.setModel(new javax.swing.table.DefaultTableModel(
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
        tbReparto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbDetalleRepartoAnterior.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
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
        tbDetalleRepartoAnterior.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDetalleRepartoAnterior.setEnabled(false);
        tbDetalleRepartoAnterior.getTableHeader().setResizingAllowed(false);
        tbDetalleRepartoAnterior.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDetalleRepartoAnterior);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbValorTotal.setBackground(new java.awt.Color(17, 35, 46));
        lbValorTotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbValorTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValorTotal.setOpaque(true);

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lbValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
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
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbRepartoMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cabe.consRepartoAnterior(tbReparto);
        cabe.consDetalleRepartoAnterior(tbDetalleRepartoAnterior);
        controlReparto.listarRepartoAnterior(tbReparto, Integer.parseInt(dlgGestRepartos.txtIdResponsable.getText()));
        lbValorTotal.setText("");
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tbRepartoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRepartoMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tbDetalleRepartoAnterior);
            controlReparto.listarDetalleRepartoAnterior(tbDetalleRepartoAnterior);
            RendersDetalle();
            DecimalFormat df = new DecimalFormat("#,###");
            lbValorTotal.setText(df.format(Integer.parseInt(String.valueOf(getTotalReparto()).replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbRepartoMousePressed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
            if (tbReparto.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione el reparto anterior a añadir al nuevo registro");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea anexar estos registros?", "Anexar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                int fila0 = tbReparto.getSelectedRow();
                dlgGestRepartos.txtKmAnterior.setText(tbReparto.getValueAt(fila0, 5).toString().replace(".", "").replace(",", ""));
                int fila = tbDetalleRepartoAnterior.getRowCount();
                for (int j = 0; j < fila; j++) {
                    String filas[] = {tbDetalleRepartoAnterior.getValueAt(j, 1).toString(), tbDetalleRepartoAnterior.getValueAt(j, 2).toString(), tbDetalleRepartoAnterior.getValueAt(j, 3).toString(), tbDetalleRepartoAnterior.getValueAt(j, 4).toString()};
                    //Mensajes.informacion("ID producto: "+filas[0]+"\nCODINT: "+filas[1]+"\nDESCRIPCION: "+filas[2]+"\nCANTIDAD: "+filas[3]);
                    controlReparto.addTabladesdeRepartoA(Integer.parseInt(filas[0]), filas[1], filas[2], Double.parseDouble(filas[3]), dlgGestRepartos.tbDetalleReparto);
                    dlgGestRepartos.txtValorRA.setText(lbValorTotal.getText().replace(".", "").replace(",", ""));
                    dlgGestRepartos.cant();
                }
            }
            dlgGestRepartos.calcularDiferencia();
            dlgGestRepartos.calcularKilometraje();
            dlgGestRepartos.Renders();
            this.dispose();
        }
            
        
    }//GEN-LAST:event_btnCargarActionPerformed

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
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lbValorTotal;
    public static javax.swing.JTable tbDetalleRepartoAnterior;
    public static javax.swing.JTable tbReparto;
    // End of variables declaration//GEN-END:variables
}
