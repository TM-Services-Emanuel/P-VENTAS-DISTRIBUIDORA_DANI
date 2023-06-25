package IU;

import Componentes.Mensajes;
import Componentes.MiRender;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimal1;
import Componentes.Software;
import Controladores.CabecerasTablas;
import Controladores.controlCompra1;
import Controladores.controlReparto;
import Controladores.controlTransferencias;
import java.util.Objects;
import javax.swing.JOptionPane;

public class dlgConsultarTransferencia extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgConsultarTransferencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        CabecerasTablas.todosTransferencias2(tbTransferencia);
        CabecerasTablas.consDetalleTransferencias(tbDetalleTransferencia);
        controlTransferencias.listarTransferenciaReparto(tbTransferencia, dlgGestRepartos.txtCaja.getText().trim(),dlgGestRepartos.txtIdMovil.getText().trim());
        Renders();

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Añadir Transferencia a reparto");
        } else {
            this.setTitle(Software.getSoftware() + " - Añadir Transferencia a reparto");
        }
    }

    public static void Renders() {
        dlgConsultarTransferencia.tbTransferencia.setDefaultRenderer(Object.class, new MiRender());
        dlgConsultarTransferencia.tbTransferencia.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimal2());
    }
    

    public static void RendersD() {
        dlgConsultarTransferencia.tbDetalleTransferencia.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
        dlgConsultarTransferencia.tbDetalleTransferencia.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
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
        tbTransferencia = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleTransferencia = new javax.swing.JTable()
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
        btnActualizar.setText("Actualizar Compras");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnCargar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf - copia.png"))); // NOI18N
        btnCargar.setText("Cargar Transferencia");
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

        tbTransferencia.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tbTransferencia.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTransferencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbTransferencia.getTableHeader().setResizingAllowed(false);
        tbTransferencia.getTableHeader().setReorderingAllowed(false);
        tbTransferencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransferenciaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbTransferenciaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbTransferencia);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbDetalleTransferencia.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tbDetalleTransferencia.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalleTransferencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbDetalleTransferencia.setEnabled(false);
        tbDetalleTransferencia.getTableHeader().setResizingAllowed(false);
        tbDetalleTransferencia.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDetalleTransferencia);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbTransferenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransferenciaMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbTransferenciaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:Z
        CabecerasTablas.todosTransferencias2(tbTransferencia);
        CabecerasTablas.limpiarTablasTransferenciasRealizadas2(tbTransferencia);
        CabecerasTablas.consDetalleTransferencias(tbDetalleTransferencia);
        CabecerasTablas.limpiarTablasDetalleTransferenciasRealizadas(tbDetalleTransferencia);
        controlCompra1.listarComprasReparto(tbTransferencia, dlgGestRepartos.txtCaja.getText());
        controlTransferencias.listarTransferenciaReparto(tbTransferencia, dlgGestRepartos.txtCaja.getText().trim(),dlgGestRepartos.txtIdMovil.getText().trim());
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tbTransferenciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransferenciaMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.consDetalleTransferencias(tbDetalleTransferencia);
            controlTransferencias.listDetalleTransferencias2(tbDetalleTransferencia);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbTransferenciaMousePressed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea anexar estos registros?", "Anexar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            if (tbTransferencia.getSelectedRow() < 0) {
                Mensajes.informacion("Seleccione la transferencia a añadir al reparto");
            } else {
                int T = tbTransferencia.getSelectedRow();
                if (Objects.equals(Integer.valueOf(tbTransferencia.getValueAt(T, 9).toString()), Integer.valueOf(dlgGestRepartos.txtIdMovil.getText().trim()))) {
                    //salida
                    int fila = tbDetalleTransferencia.getRowCount();
                    for (int j = 0; j < fila; j++) {
                        String filas[] = {tbDetalleTransferencia.getValueAt(j, 1).toString(),
                            tbDetalleTransferencia.getValueAt(j, 2).toString(),
                            tbDetalleTransferencia.getValueAt(j, 3).toString(),
                            tbDetalleTransferencia.getValueAt(j, 4).toString()};

                        controlReparto.addTabladesdeTransferencia(Integer.parseInt(filas[0]), filas[1], filas[2], Double.parseDouble(filas[3]) * (-1), dlgGestRepartos.tbDetalleReparto);
                        dlgGestRepartos.txtValorTransferencia.setText(lbValorTotal.getText().replace(".", "").replace(",", ""));
                        dlgGestRepartos.cant();
                    }
                } else if (Objects.equals(Integer.valueOf(tbTransferencia.getValueAt(T, 10).toString()), Integer.valueOf(dlgGestRepartos.txtIdMovil.getText().trim()))) {
                    //entrada
                    int fila = tbDetalleTransferencia.getRowCount();
                    for (int j = 0; j < fila; j++) {
                        String filas[] = {tbDetalleTransferencia.getValueAt(j, 1).toString(),
                            tbDetalleTransferencia.getValueAt(j, 2).toString(),
                            tbDetalleTransferencia.getValueAt(j, 3).toString(),
                            tbDetalleTransferencia.getValueAt(j, 4).toString()};

                        controlReparto.addTabladesdeTransferencia(Integer.parseInt(filas[0]), filas[1], filas[2], Double.parseDouble(filas[3]), dlgGestRepartos.tbDetalleReparto);
                        dlgGestRepartos.txtValorTransferencia.setText(lbValorTotal.getText().replace(".", "").replace(",", ""));
                        dlgGestRepartos.cant();
                    }
                }

            }
            dlgGestRepartos.calcularDiferencia();
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
            java.util.logging.Logger.getLogger(dlgConsultarTransferencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarTransferencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarTransferencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarTransferencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarTransferencia dialog = new dlgConsultarTransferencia(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable tbDetalleTransferencia;
    public static javax.swing.JTable tbTransferencia;
    // End of variables declaration//GEN-END:variables
}
