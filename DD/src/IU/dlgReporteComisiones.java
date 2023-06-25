/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Numero_a_Letra;
import Componentes.RenderDecimal2;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlReparto;
import static Controladores.controlReparto.getTotalComision;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class dlgReporteComisiones extends javax.swing.JDialog {

    private static final CabecerasTablas cabe = new CabecerasTablas();
    public Reporte jasper;
    public Numero_a_Letra d;
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    public static MariaDbStatement sentenciaM;
    public static MariaDbConnection conM;
    
    /**
     * Creates new form dlgReporteComisiones
     * @param parent
     * @param modal
     */
    public dlgReporteComisiones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        CabecerasTablas.ReporteComision(tbDetalleComisiones);
        cargarComboBox.cargarFuncionarios(cboFuncionarios, "SELECT * FROM v_vendedores WHERE idfuncion>=1 AND idfuncion <=2 AND ven_indicador='S'");
        jasper = new Reporte();
        d= new Numero_a_Letra();
        invisible();
        prepararBD();
    }
    
    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Generar Recibo de Dinero - Pago de Comisiones");
        } else {
            this.setTitle(Software.getSoftware() + " - Generar Recibo de Dinero - Pago de Comisiones");
        }
    }
    public static void invisible(){
        txtIdVendedor.setVisible(false);
        txtFD.setVisible(false);
        txtFH.setVisible(false);
    }
    
    public static void Renders() {
        tbDetalleComisiones.getColumnModel().getColumn(3).setCellRenderer(new RenderDecimal2());
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
    
    private void LevantarReporte(String Dir,String Nombre1,Integer Valor1, String Nombre2, String Valor2, String Nombre3, String Valor3, String Nombre4, String Valor4, String Nombre5, int Valor5){
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
            parametros.put(Nombre1, Valor1);
            parametros.put(Nombre2, Valor2);
            parametros.put(Nombre3, Valor3);
            parametros.put(Nombre4, Valor4);
            parametros.put(Nombre5, Valor5);
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
        //vr.setSize(250, 50);
        vr.setResizable(false);
        vr.setLocationRelativeTo(this);
        vr.setVisible(true);
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
        jPanel5 = new javax.swing.JPanel();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtFD = new javax.swing.JTextField();
        txtFH = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboFuncionarios = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtFDesde = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFHasta = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        txtIdVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTotalC = new javax.swing.JTextField();
        lbTotal = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MnOpcionesReparto = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout(1, 9));

        btnGenerar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reporte x 40.png"))); // NOI18N
        btnGenerar.setText("Generar Reporte - F6");
        btnGenerar.setToolTipText("Nuevo - F3");
        btnGenerar.setEnabled(false);
        btnGenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGenerar);

        btnSalir.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back40.png"))); // NOI18N
        btnSalir.setText("Salir - Alt+F4");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel5.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFH, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFD, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OscuroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel2.setText("Funcionario");

        cboFuncionarios.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cboFuncionarios.setMaximumRowCount(15);
        cboFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFuncionariosActionPerformed(evt);
            }
        });
        cboFuncionarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboFuncionariosKeyPressed(evt);
            }
        });

        jLabel3.setText("Fecha desde");

        txtFDesde.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFDesdeActionPerformed(evt);
            }
        });
        txtFDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFDesdeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFDesdeKeyReleased(evt);
            }
        });

        jLabel4.setText("Fecha hasta");

        txtFHasta.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFHastaActionPerformed(evt);
            }
        });
        txtFHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFHastaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFHastaKeyReleased(evt);
            }
        });

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Find.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.setIconTextGap(10);
        btnFiltrar.setOpaque(false);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboFuncionarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(txtIdVendedor))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbDetalleComisiones.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbDetalleComisiones.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalleComisiones.setEnabled(false);
        tbDetalleComisiones.getTableHeader().setResizingAllowed(false);
        tbDetalleComisiones.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbDetalleComisiones);

        txtTotalC.setEditable(false);
        txtTotalC.setBackground(new java.awt.Color(17, 35, 46));
        txtTotalC.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        txtTotalC.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTotalC.setBorder(null);

        lbTotal.setBackground(new java.awt.Color(17, 35, 46));
        lbTotal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotal.setOpaque(true);

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlancoLayout.createSequentialGroup()
                        .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(txtTotalC, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalC, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        MnOpcionesReparto.setText("Opciones");
        MnOpcionesReparto.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        MnOpcionesReparto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reports.png"))); // NOI18N
        itemNuevo.setText("Generar Reporte");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemNuevo);
        MnOpcionesReparto.add(jSeparator3);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemSalir);

        jMenuBar1.add(MnOpcionesReparto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        if(tbDetalleComisiones.getRowCount()==0){
            Mensajes.error("No existe infomación para generar reporte");
        }else{
            String letras= d.Convertir(txtTotalC.getText().replace(".", "").replace(",", ""), true);
            /*jasper.reporteCincoParametroVertical("\\Reports\\repartos\\reciboDineroF.jasper", 
                    "idvendedor", Integer.parseInt(txtIdVendedor.getText().trim()), 
                    "fechaD", (txtFD.getText().trim()), 
                    "fechaH", (txtFH.getText().trim()),
                    "Letras",letras,
                    "Total",Integer.parseInt(txtTotalC.getText().replace(".", "").replace(",", "")));*/
            LevantarReporte("\\Reports\\repartos\\reciboDineroF.jasper", "idvendedor", Integer.parseInt(txtIdVendedor.getText().trim()), 
                    "fechaD", (txtFD.getText().trim()), 
                    "fechaH", (txtFH.getText().trim()),
                    "Letras",letras,
                    "Total",Integer.parseInt(txtTotalC.getText().replace(".", "").replace(",", "")));
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFuncionariosActionPerformed
        // TODO add your handling code here:
        if(cboFuncionarios.getSelectedIndex()==0){
            txtIdVendedor.setText("");
        }else{
            txtIdVendedor.setText(cargarComboBox.getCodidgo(cboFuncionarios));
            txtFDesde.requestFocus();
        }
    }//GEN-LAST:event_cboFuncionariosActionPerformed

    private void cboFuncionariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboFuncionariosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFDesde.requestFocus();
        }
    }//GEN-LAST:event_cboFuncionariosKeyPressed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        txtFD.setText(Fecha.formatoFecha(txtFDesde.getText()));
        txtFH.setText(Fecha.formatoFecha(txtFHasta.getText()));
        if (cboFuncionarios.getSelectedIndex() == 0) {
            lbTotal.setText("");
            txtTotalC.setText("");
            CabecerasTablas.limpiarTablas(tbDetalleComisiones);
            btnGenerar.setEnabled(false);
            Mensajes.informacion("SELECCIONE UN FUNCIONARIO");
            cboFuncionarios.requestFocus();
            cboFuncionarios.setPopupVisible(true);
        } else if (txtFDesde.getText().isEmpty()) {
            lbTotal.setText("");
            txtTotalC.setText("");
            CabecerasTablas.limpiarTablas(tbDetalleComisiones);
            btnGenerar.setEnabled(false);
            Mensajes.informacion("Ingrese la fecha desde");
            txtFDesde.requestFocus();
        } else if (txtFHasta.getText().isEmpty()) {
            lbTotal.setText("");
            txtTotalC.setText("");
            CabecerasTablas.limpiarTablas(tbDetalleComisiones);
            btnGenerar.setEnabled(false);
            Mensajes.informacion("Ingrese la fecha hasta");
            txtFHasta.requestFocus();
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaD = formato.parse(txtFD.getText());
                Date fechaH = formato.parse(txtFH.getText());
                if (fechaH.before(fechaD)) {
                    Mensajes.error("Verifique que las fechas desde y hasta esten correctos");
                    txtFDesde.requestFocus();
                } else {
                    CabecerasTablas.limpiarTablas(tbDetalleComisiones);
                    CabecerasTablas.ReporteComision(tbDetalleComisiones);
                    controlReparto.listarComisiones(tbDetalleComisiones, Integer.parseInt(txtIdVendedor.getText()), txtFD.getText().trim(), txtFH.getText().trim());
                    Renders();
                    DecimalFormat df = new DecimalFormat("#,###");
                    lbTotal.setText("TOTAL COMISIÓN Gs.");
                    txtTotalC.setText(df.format(Integer.parseInt(String.valueOf(getTotalComision()).replace(".", "").replace(",", ""))));
                    if (tbDetalleComisiones.getRowCount() > 0) {
                        btnGenerar.setEnabled(true);
                    } else {
                        btnGenerar.setEnabled(false);
                    }
                }
            } catch (ParseException ex) {
                Mensajes.error("Error comparando fechas");
            }
        }                    
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtFDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFDesdeActionPerformed
        // TODO add your handling code here:
        txtFD.setText(Fecha.formatoFecha(txtFDesde.getText()));
        txtFHasta.requestFocus();
    }//GEN-LAST:event_txtFDesdeActionPerformed

    private void txtFHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFHastaActionPerformed
        // TODO add your handling code here:
        txtFH.setText(Fecha.formatoFecha(txtFHasta.getText()));
        btnFiltrar.doClick();
    }//GEN-LAST:event_txtFHastaActionPerformed

    private void txtFDesdeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFDesdeKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            if (txtFDesde.getText().length() == 2) {
                txtFDesde.setText(txtFDesde.getText().concat("/"));
            }else if (txtFDesde.getText().length() == 5) {
                txtFDesde.setText(txtFDesde.getText().concat("/"));
            }
        }
    }//GEN-LAST:event_txtFDesdeKeyReleased

    private void txtFDesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFDesdeKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFDesde);
        validarCampos.cantCaracteres(txtFDesde, 10);
    }//GEN-LAST:event_txtFDesdeKeyPressed

    private void txtFHastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFHastaKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFHasta);
        validarCampos.cantCaracteres(txtFHasta, 10);
    }//GEN-LAST:event_txtFHastaKeyPressed

    private void txtFHastaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFHastaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            if (txtFHasta.getText().length() == 2) {
                txtFHasta.setText(txtFHasta.getText().concat("/"));
            }else if (txtFHasta.getText().length() == 5) {
                txtFHasta.setText(txtFHasta.getText().concat("/"));
            }
        }
    }//GEN-LAST:event_txtFHastaKeyReleased

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
            java.util.logging.Logger.getLogger(dlgReporteComisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgReporteComisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgReporteComisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgReporteComisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgReporteComisiones dialog = new dlgReporteComisiones(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenu MnOpcionesReparto;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JButton btnFiltrar;
    public static javax.swing.JButton btnGenerar;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox<String> cboFuncionarios;
    public static javax.swing.JMenuItem itemNuevo;
    public static javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lbTotal;
    public static final javax.swing.JTable tbDetalleComisiones = new javax.swing.JTable();
    public static javax.swing.JTextField txtFD;
    private javax.swing.JTextField txtFDesde;
    public static javax.swing.JTextField txtFH;
    private javax.swing.JTextField txtFHasta;
    public static javax.swing.JTextField txtIdVendedor;
    private javax.swing.JTextField txtTotalC;
    // End of variables declaration//GEN-END:variables

}
