/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimal2;
import Componentes.Software;
import Controladores.CabecerasTablas;
import Controladores.controlTransferencias;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

/**
 *
 * @author ADMIN
 */
public class dlgTransferencias extends javax.swing.JDialog {
    
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    public static MariaDbStatement sentenciaM;
    public static MariaDbConnection conM;
    
    public dlgTransferencias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        txtFechaFinal.setText(Fecha.formatoFecha(dcFecha.getText()));
        btnActualizarActionPerformed(null);
        txtFechaFinal.setVisible(false);
        prepararBD();
        // CabecerasTablas.todosReparto(tbDetalle);
        // txtFechaFinal.setText(Fecha.formatoFecha(dcFecha.getText()));
        // controlReparto.listRepartos(tbDetalle, "id_reparto",txtFechaFinal.getText().trim());

    }
    
    public static void prepararBD() {
        {
            try {
                con = (MariaDbConnection) new ConexionBD().getConexion();
                if (con == null) {
                    System.out.println("No hay Conexion con la Base de Datos");
                } else {
                    sentencia = (MariaDbStatement) con.createStatement();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try {
                conM = (MariaDbConnection) new ConexionBD().getConexionMovil();
                if (con == null) {
                    System.out.println("No hay Conexion con la Base de Datos Móvil");
                } else {
                    sentenciaM = (MariaDbStatement) conM.createStatement();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void Renders() {
        dlgTransferencias.tbDetalle.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimal2());
    }
    public static void Rendersd() {
        dlgTransferencias.tbDetalleTransf.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
        dlgTransferencias.tbDetalleTransf.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
    }
    
    private void LevantarReporte(String Dir, int id){
        VisorReportes vr = new VisorReportes(null, true);
        try {
            //prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat(Dir);
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("id", id);
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, conM);
            //se crea el visor con el reporte
            JRViewer jRViewer = new JRViewer(jasperPrint);
            //se elimina elementos del contenedor JPanel
            VisorReportes.jpContainer.removeAll();
            //para el tamaño del reporte se agrega un BorderLayout
            VisorReportes.jpContainer.setLayout(new BorderLayout());
            VisorReportes.jpContainer.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setZoomRatio((float) 1.25);
            jRViewer.setVisible(true);
            VisorReportes.jpContainer.repaint();
            VisorReportes.jpContainer.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
        vr.setLocationRelativeTo(this);
        vr.setVisible(true);
    }

   /* public static void CargarResumen() {
        DecimalFormat df = new DecimalFormat("#,###");
        String CT = String.valueOf(getCT());
        dlgTransferencias.txtCT.setText(df.format(Integer.parseInt(CT.replace(".", "").replace(",", ""))));
        String R = String.valueOf(getR());
        dlgTransferencias.txtR.setText(df.format(Integer.parseInt(R.replace(".", "").replace(",", ""))));
        String D = String.valueOf(getD());
        dlgTransferencias.txtD.setText(df.format(Integer.parseInt(D.replace(".", "").replace(",", ""))));
        String EER = String.valueOf(getEER());
        dlgTransferencias.txtEER.setText(df.format(Integer.parseInt(EER.replace(".", "").replace(",", ""))));
        String EED = String.valueOf(getEED());
        dlgTransferencias.txtEED.setText(df.format(Integer.parseInt(EED.replace(".", "").replace(",", ""))));
        String DIF = String.valueOf(getDIF());
        dlgTransferencias.txtDIF.setText(df.format(Integer.parseInt(DIF.replace(".", "").replace(",", ""))));
    }*/

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestionar Transferencias");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestionar Transferencias");
        }
    }

    private void nuevo() {
        dlgGestTransferencias GR = new dlgGestTransferencias(null, true);
        GR.setLocationRelativeTo(this);
        GR.Nuevo();
        GR.setVisible(true);

    }

    private void delTransferencia() {

        int x = tbDetalle.getSelectedRow();
        if (x < 0) {
            Mensajes.informacion("Seleccione una fila de la tabla");
        } else {
            try {
                int ID = Integer.parseInt(tbDetalle.getValueAt(x, 0).toString());
                int rpta = Mensajes.confirmar("Desea realmente ANULAR la Transferencia N°: " + ID);
                if (rpta == 0) {
                    if(tbDetalle.getValueAt(x, 4).toString().equals("L") && tbDetalle.getValueAt(x, 6).toString().equals("R")){
                        controlTransferencias.anularTransferenciaOrigenL(ID);
                        btnActualizarActionPerformed(null);   
                    }else if(tbDetalle.getValueAt(x, 4).toString().equals("R") && tbDetalle.getValueAt(x, 6).toString().equals("L")){
                        controlTransferencias.anularTransferenciaDestinoL(ID);
                        btnActualizarActionPerformed(null);  
                    }else{
                        controlTransferencias.anularTransferenciaRR(ID);
                        btnActualizarActionPerformed(null); 
                    }
                    //controlTransferencias.anularTransferencia(ID);
                    //btnActualizarActionPerformed(null);
                   // Renders();
                   // CargarResumen();
                }
            } catch (NumberFormatException e) {
            }

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

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtFechaFinal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        dcFecha = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleTransf = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemImprimir1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 6));

        btnNuevo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf - N.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setToolTipText("Registrar Nuevo Artículo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);

        btnEliminar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf -E.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setToolTipText("Eliminar Artículo");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        btnActualizar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf - AC.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR LISTADO");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);

        btnSalir.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back40.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(53, 47));
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbDetalle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalle.setOpaque(false);
        tbDetalle.getTableHeader().setResizingAllowed(false);
        tbDetalle.getTableHeader().setReorderingAllowed(false);
        tbDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDetalleMousePressed(evt);
            }
        });
        tbDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalle);

        dcFecha.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcFecha.setCalendarBackground(new java.awt.Color(255, 255, 255));
    dcFecha.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
    dcFecha.setFieldFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 16));
    dcFecha.setNavigateFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 14));
    dcFecha.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcFechaOnCommit(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setText("Transferencias de la fecha:");

    tbDetalleTransf.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
    tbDetalleTransf.setModel(new javax.swing.table.DefaultTableModel(
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
    tbDetalleTransf.setOpaque(false);
    tbDetalleTransf.getTableHeader().setResizingAllowed(false);
    tbDetalleTransf.getTableHeader().setReorderingAllowed(false);
    tbDetalleTransf.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tbDetalleTransfMouseClicked(evt);
        }
    });
    tbDetalleTransf.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            tbDetalleTransfKeyPressed(evt);
        }
    });
    jScrollPane2.setViewportView(tbDetalleTransf);

    javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
    Blanco.setLayout(BlancoLayout);
    BlancoLayout.setHorizontalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        .addGroup(BlancoLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    BlancoLayout.setVerticalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(13, 13, 13)
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jMenu1.setText("OPCIONES");
    jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
    jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

    itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
    itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
    itemSalir.setText("SALIR");
    itemSalir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemSalirActionPerformed(evt);
        }
    });
    jMenu1.add(itemSalir);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("IMPRIMIR");
    jMenu2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
    jMenu2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

    itemImprimir1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
    itemImprimir1.setText("IMPRIMIR DOCUMENTO DE TRANSFERENCIA                         ");
    itemImprimir1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemImprimir1ActionPerformed(evt);
        }
    });
    jMenu2.add(itemImprimir1);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        delTransferencia();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
        CabecerasTablas.todosTransferencias(tbDetalle);
        CabecerasTablas.limpiarTablasTransferenciasRealizadas(tbDetalle);
        CabecerasTablas.consDetalleTransferencias(tbDetalleTransf);
        CabecerasTablas.limpiarTablasDetalleTransferenciasRealizadas(tbDetalleTransf);
        controlTransferencias.listTransferencias(tbDetalle, "idtransferencia", txtFechaFinal.getText().trim());
        Renders();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void dcFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFechaOnCommit
        // TODO add your handling code here:
        txtFechaFinal.setText(Fecha.formatoFecha(dcFecha.getText()));
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_dcFechaOnCommit

    private void tbDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleKeyPressed

    private void tbDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleMouseClicked

    private void tbDetalleTransfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleTransfMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleTransfMouseClicked

    private void tbDetalleTransfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleTransfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleTransfKeyPressed

    private void tbDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.consDetalleTransferencias(tbDetalleTransf);
            controlTransferencias.listDetalleTransferencias(tbDetalleTransf);
            Rendersd();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbDetalleMousePressed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        //        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemImprimir1ActionPerformed
        // TODO add your handling code here:
        int x = tbDetalle.getSelectedRow();
        if (x < 0) {
            Mensajes.informacion("Seleccione una fila de la tabla para generar el Documento");
        } else {
            try{
                int ID = Integer.parseInt(tbDetalle.getValueAt(x, 0).toString());
                LevantarReporte("\\Reports\\transferencias\\DocumentoTransferencia.jasper", ID);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_itemImprimir1ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            dlgTransferencias dialog = new dlgTransferencias(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    public static javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private datechooser.beans.DateChooserCombo dcFecha;
    private javax.swing.JMenuItem itemImprimir1;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tbDetalle;
    public static javax.swing.JTable tbDetalleTransf;
    public static javax.swing.JTextField txtFechaFinal;
    // End of variables declaration//GEN-END:variables
}
