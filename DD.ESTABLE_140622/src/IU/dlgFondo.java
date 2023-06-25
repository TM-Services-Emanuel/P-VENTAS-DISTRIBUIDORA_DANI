package IU;

import Componentes.CargarJList;
import Componentes.SeleccionarImagen;
import Componentes.Software;
import Componentes.metodos;
import Controladores.ControlImagen;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

public class dlgFondo extends javax.swing.JDialog {

    private final File file = null;
    private JFileChooser seleccionar;
    private final Image imagen = null;

    ControlImagen ci = new ControlImagen();

    SeleccionarImagen s = new SeleccionarImagen();

    public dlgFondo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        CargarJList.cargar(listaImagenes, "SELECT * FROM imagen");
        jLabel2.setVisible(false);
        btnGuardar.setVisible(false);
        
    }
    
    final void titulo(){
        if(Software.getSoftware().equals("null")){
            this.setTitle("Cambiar fondo");
        }else{
            this.setTitle(Software.getSoftware()+" - Cambiar fondo");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaImagenes = new javax.swing.JList();
        panelFondo = new JPanelConFondo();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listaImagenes.setBackground(new java.awt.Color(255, 255, 204));
        listaImagenes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listaImagenes.setFont(new java.awt.Font("Calibri", 1, 10)); // NOI18N
        listaImagenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaImagenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaImagenes);

        panelFondo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/folder30.png"))); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 469, 21));

        jLabel2.setBackground(new java.awt.Color(255, 255, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 469, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaImagenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaImagenesMouseClicked
        // TODO add your handling code here:
        System.out.println("VAlue: "+listaImagenes.getSelectedValue());
        try{
            String codI=CargarJList.getCodidgo(listaImagenes);
            System.out.println("Codigo img: "+codI);
            //ci.buscar(CargarJList.getCodidgo(listaImagenes));
            ci.buscar(codI);
            //ci.establecerFondo(CargarJList.getCodidgo(listaImagenes));
            ci.establecerFondo(codI);
        }catch(Exception ee){
            System.out.println(ee.getMessage());
        }
        
        
    }//GEN-LAST:event_listaImagenesMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
//        m.Abrir_Dialogos(panelFondo, jButton1);

        /*String texto = btnNuevo.getText();
        if (texto.equals("Nuevo Fondo")) {
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ver_foto.png")));
            m.Abrir_DialogoSeleccionImagen(panelFondo, btnNuevo, jLabel1, jLabel2);
        } else {
            ControlImagen.addImagen();
            try {
                Path origen = Paths.get(jLabel2.getText());
                StringBuilder dest2 = new StringBuilder(System.getProperty("user.dir"));
                System.out.println(System.getProperty("user.dir"));
                dest2.append("/src/Recursos/").append(jLabel1.getText());
                Path dest = Paths.get(dest2.toString());
                System.out.println(dest);
                CopyOption[] option = new CopyOption[]{
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(origen, dest, option);
                btnNuevo.setText("Nuevo Fondo");
                jLabel1.setText("");
                panelFondo.removeAll();
                panelFondo.repaint();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        vaciarJList();
        CargarJList.cargar(jList1, "SELECT * FROM imagen");*/
        m.Abrir_DialogoSeleccionImagen(panelFondo, btnNuevo,btnGuardar, jLabel1, jLabel2);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
            ControlImagen.addImagen();
            try {
                Path origen = Paths.get(jLabel2.getText());
                StringBuilder dest2 = new StringBuilder(System.getProperty("user.dir"));
                System.out.println(System.getProperty("user.dir"));
                dest2.append("/src/Recursos/").append(jLabel1.getText());
                Path dest = Paths.get(dest2.toString());
                System.out.println(dest);
                CopyOption[] option = new CopyOption[]{
                StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(origen, dest, option);
            } catch (IOException e) {
                System.out.println(e.toString());
            } 
            
            panelFondo.removeAll();
            panelFondo.repaint();
            jLabel1.setText("");
            jLabel2.setText("");
            btnNuevo.setVisible(true);
            btnGuardar.setVisible(false);
            vaciarJList();
            CargarJList.cargar(listaImagenes, "SELECT * FROM imagen");
    }//GEN-LAST:event_btnGuardarActionPerformed

    void vaciarJList() {
        DefaultListModel model = new DefaultListModel();
        listaImagenes.setModel(model);
    }
    
    metodos m = new metodos();

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
            java.util.logging.Logger.getLogger(dlgFondo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            dlgFondo dialog = new dlgFondo(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaImagenes;
    public static javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
