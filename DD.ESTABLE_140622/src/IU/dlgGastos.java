package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.ControlGasto;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class dlgGastos extends javax.swing.JDialog {

    public static ResultSet rs;
    public static MariaDbConnection con;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection conMovil;
    public static MariaDbStatement sentenciaMovil;

    public dlgGastos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png"));
        setIconImage(icon);
        titulo();   
        noVisible();
        //cargarComboBox.cargar(cbDetalleGasto, "SELECT * FROM detallegasto WHERE dg_indicador='S'"); 
        //cargarComboBox.cargarResponsable(cboResponsable, "SELECT * FROM v_vendedores WHERE idfuncion>=2 AND idfuncion<=4 AND ven_indicador='S'");
        btnCancelarActionPerformed(null);
    }
    
    private void noVisible(){
        txtIdMovil.setVisible(false);
        lblCodDetalle.setVisible(false);
        txtImporteL.setVisible(false);
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Registrar egreso del día");
        } else {
            this.setTitle(Software.getSoftware() + " - Registrar egreso del día");
        }
    }

    void limpiarCampos() {
        txtCaja.setText("");
        txtImporte.setText("0");
        txtImporteL.setText("0");
        txtObservacion.setText("");
        //cbDetalleGasto.setSelectedIndex(0);
        lbInfoMovil.setText("");
        txtIdMovil.setText("");
    }

    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            conMovil = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                sentencia = (MariaDbStatement) con.createStatement();
            }
            if (conMovil == null) {
                System.out.println("No hay Conexion con la Base de Datos Movil");
            } else {
                sentenciaMovil = (MariaDbStatement) conMovil.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void nuevo(){
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargarComboBox.cargar(cbDetalleGasto, "SELECT * FROM detallegasto WHERE dg_indicador='S'");
        cargarComboBox.cargarResponsable(cboResponsable, "SELECT * FROM v_vendedores WHERE idfuncion>=2 AND idfuncion<=4 AND ven_indicador='S'");
        
        cbDetalleGasto.setEnabled(true);
        txtImporte.setEnabled(true);
        txtObservacion.setEnabled(true);
        cboResponsable.setEnabled(true);
        dcFecha.setEnabled(true);
        cbGenerado.setEnabled(true);

        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        btnDetalle.setEnabled(true);

        cbDetalleGasto.requestFocus();
        cbDetalleGasto.setPopupVisible(true);
    }
    
    public void Volver(){
        dlgGestGastos.txtGL.setText("0");
        dlgGestGastos.txtGR.setText("0");
        dlgGestGastos.txtGA.setText("0");
        CabecerasTablas.gestGastos(dlgGestGastos.tbGastos);
        CabecerasTablas.limpiarTablasGastos(dlgGestGastos.tbGastos);
        ControlGasto.listarGastos(dlgGestGastos.tbGastos, dlgGestGastos.txtFechaFinal.getText().trim());
        dlgGestGastos.Renders();
        dlgGestGastos.calcularValores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        dcFecha = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbDetalleGasto = new javax.swing.JComboBox();
        btnDetalle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        txtImporteL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        lblCodDetalle = new javax.swing.JLabel();
        cboResponsable = new javax.swing.JComboBox<>();
        lbInfoMovil = new javax.swing.JLabel();
        txtIdMovil = new javax.swing.JTextField();
        cbGenerado = new javax.swing.JComboBox<>();
        barMenu = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemModificar = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnNuevo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document30.png"))); // NOI18N
        btnNuevo.setText("Nuevo-F1");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setMaximumSize(new java.awt.Dimension(85, 57));
        btnNuevo.setMinimumSize(new java.awt.Dimension(85, 57));
        btnNuevo.setPreferredSize(new java.awt.Dimension(90, 60));
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo);

        btnModificar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/edit30.png"))); // NOI18N
        btnModificar.setText("Modif-F5");
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setMaximumSize(new java.awt.Dimension(63, 57));
        btnModificar.setMinimumSize(new java.awt.Dimension(63, 57));
        btnModificar.setPreferredSize(new java.awt.Dimension(90, 60));
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificar);

        btnGuardar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.setText("Guardar-F6");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setMaximumSize(new java.awt.Dimension(71, 57));
        btnGuardar.setMinimumSize(new java.awt.Dimension(71, 57));
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel30.png"))); // NOI18N
        btnCancelar.setText("Cancel-Esc");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMaximumSize(new java.awt.Dimension(75, 57));
        btnCancelar.setMinimumSize(new java.awt.Dimension(75, 57));
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        btnSalir.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        btnSalir.setText("Salir-Alt+F4");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel6.setText("Movimiento N°");

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N

        dcFecha.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
    dcFecha.setCalendarBackground(new java.awt.Color(255, 255, 204));
    dcFecha.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dcFecha.setEnabled(false);
    dcFecha.setFieldFont(new java.awt.Font("Microsoft Sans Serif", java.awt.Font.PLAIN, 11));

    jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    jLabel1.setText("Fecha");

    jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    jLabel2.setText("Motivo");

    cbDetalleGasto.setBackground(new java.awt.Color(255, 255, 204));
    cbDetalleGasto.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    cbDetalleGasto.setEnabled(false);
    cbDetalleGasto.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbDetalleGastoActionPerformed(evt);
        }
    });
    cbDetalleGasto.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cbDetalleGastoKeyPressed(evt);
        }
    });

    btnDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
    btnDetalle.setToolTipText("Gestionar Laboratorio");
    btnDetalle.setBorderPainted(false);
    btnDetalle.setContentAreaFilled(false);
    btnDetalle.setEnabled(false);
    btnDetalle.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDetalleActionPerformed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    jLabel3.setText("Responsable");

    jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    jLabel4.setText("Importe");

    txtImporte.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 11)); // NOI18N
    txtImporte.setForeground(new java.awt.Color(0, 153, 0));
    txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtImporte.setText("0");
    txtImporte.setEnabled(false);
    txtImporte.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtImporteActionPerformed(evt);
        }
    });
    txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            txtImporteKeyPressed(evt);
        }
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtImporteKeyReleased(evt);
        }
    });

    txtImporteL.setEditable(false);
    txtImporteL.setBackground(new java.awt.Color(255, 255, 255));
    txtImporteL.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    txtImporteL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

    jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    jLabel5.setText("Observación");

    txtObservacion.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    txtObservacion.setEnabled(false);
    txtObservacion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtObservacionActionPerformed(evt);
        }
    });
    txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtObservacionKeyTyped(evt);
        }
    });

    lblCodDetalle.setText(".");

    cboResponsable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    cboResponsable.setEnabled(false);
    cboResponsable.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboResponsableActionPerformed(evt);
        }
    });
    cboResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cboResponsableKeyPressed(evt);
        }
    });

    lbInfoMovil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    lbInfoMovil.setForeground(new java.awt.Color(0, 102, 153));
    lbInfoMovil.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));

    cbGenerado.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
    cbGenerado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERADO EN:", "SALÓN", "REPARTO", "ADMINISTRACIÓN" }));
    cbGenerado.setEnabled(false);
    cbGenerado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbGeneradoActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4)
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtIdMovil, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtImporteL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblCodDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cboResponsable, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbInfoMovil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbDetalleGasto, 0, 204, Short.MAX_VALUE)
                                .addComponent(cbGenerado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, 0)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtObservacion, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGap(12, 12, 12))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel1))
                    .addGap(8, 8, 8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDetalleGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbGenerado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbInfoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtIdMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtImporteL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCodDetalle))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(25, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
    Blanco.setLayout(BlancoLayout);
    BlancoLayout.setHorizontalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BlancoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(31, 31, 31))
    );
    BlancoLayout.setVerticalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    barMenu.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

    menuOpciones.setText("Opciones");
    menuOpciones.setFocusable(false);
    menuOpciones.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
    menuOpciones.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

    itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
    itemNuevo.setText("Nuevo");
    itemNuevo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemNuevoActionPerformed(evt);
        }
    });
    menuOpciones.add(itemNuevo);

    itemModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
    itemModificar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/edit15.png"))); // NOI18N
    itemModificar.setText("Guardar Modificación");
    itemModificar.setEnabled(false);
    itemModificar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemModificarActionPerformed(evt);
        }
    });
    menuOpciones.add(itemModificar);

    itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
    itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save15.png"))); // NOI18N
    itemGuardar.setText("Guardar Nuevo");
    itemGuardar.setEnabled(false);
    itemGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemGuardarActionPerformed(evt);
        }
    });
    menuOpciones.add(itemGuardar);

    itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel15.png"))); // NOI18N
    itemCancelar.setText("Cancelar");
    itemCancelar.setEnabled(false);
    itemCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemCancelarActionPerformed(evt);
        }
    });
    menuOpciones.add(itemCancelar);

    itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
    itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
    itemSalir.setText("Salir");
    itemSalir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemSalirActionPerformed(evt);
        }
    });
    menuOpciones.add(itemSalir);

    barMenu.add(menuOpciones);

    setJMenuBar(barMenu);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 516, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbDetalleGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDetalleGastoActionPerformed
        // TODO add your handling code here:
        if (cbDetalleGasto.getSelectedIndex() == 0) {
            lblCodDetalle.setText("");
        } else {
            String cod = cargarComboBox.getCodidgo(cbDetalleGasto);
            lblCodDetalle.setText(cod);
        }
    }//GEN-LAST:event_cbDetalleGastoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargarComboBox.cargar(cbDetalleGasto, "SELECT * FROM detallegasto WHERE dg_indicador='S'");
        cargarComboBox.cargarResponsable(cboResponsable, "SELECT * FROM v_vendedores WHERE idfuncion>=2 AND idfuncion<=4 AND ven_indicador='S'");
        
        cbDetalleGasto.setEnabled(true);
        txtImporte.setEnabled(true);
        txtObservacion.setEnabled(true);
        cboResponsable.setEnabled(true);
        //dcFecha.setEnabled(true);
        cbGenerado.setEnabled(true);

        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        btnDetalle.setEnabled(true);
        btnSalir.setEnabled(false);
        itemSalir.setEnabled(false);

        cbDetalleGasto.requestFocus();
        cbDetalleGasto.setPopupVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        /*   if (validarCampos.estaVacio(txtDescripcion)&& validarCampos.estaVacio(txtCostoL) && validarCampos.estaVacio(txtPrecioPublicoL)&& validarCampos.estaVacio(txtPrecioVentaL)
            && validarCampos.estaVacio(txtCodLab)&& validarCampos.estaVacio(txtCodPro)&& validarCampos.estaVacio(txtCodFam)
            && validarCampos.estaVacio(txtStock)&& validarCampos.estaVacio(txtStockMin)){
            try{
                int resp = JOptionPane.showConfirmDialog(this,"¿Seguro que desea modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION){
                    controlArticulo.actArticulo();
                    limpiarCampos();
                    CabecerasTablas.limpiarTablas(tbProductos);
                    controlArticulo.listArticulo(tbProductos, "cod");
                    cant();
                    this.dispose();
                }
            }catch(Exception ee){System.out.println(ee.getMessage());}
        } else {
            Mensajes.informacion("Debe llenar obligatoriamente los campos señalados");
        }*/
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtImporte.getText().isEmpty()) {
            Mensajes.error("Ingrese en importe a registrar");
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else if (txtImporte.getText().equals("0")) {
            Mensajes.error("Ingrese un importe mayor a 0");
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else if (txtObservacion.getText().isEmpty()) {
            Mensajes.error("Ingrese una Observacion con relacion al registro");
            txtObservacion.requestFocus();
        } else if (lblCodDetalle.getText().isEmpty()) {
            Mensajes.error("Seleccione un Motivo");
            cbDetalleGasto.requestFocus();
            cbDetalleGasto.setPopupVisible(true);
        }else if (cboResponsable.getSelectedIndex()==0) {
            Mensajes.error("Seleccione el Responsable");
            cboResponsable.requestFocus();
            cboResponsable.setPopupVisible(true);
        }else if (cbGenerado.getSelectedIndex()==0) {
            Mensajes.error("Seleccione el origen del gasto");
            cbGenerado.requestFocus();
            cbGenerado.setPopupVisible(true);
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    ControlGasto.addGasto();
                    btnCancelarActionPerformed(null);
                }
            } catch (HeadlessException ee) {
                System.out.println(ee.getMessage());
            }
            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        cbDetalleGasto.setEnabled(false);
        txtImporte.setEnabled(false);
        txtObservacion.setEnabled(false);
        btnDetalle.setEnabled(false);
        cboResponsable.setEnabled(false);
        dcFecha.setEnabled(false);
        cbGenerado.setEnabled(false);

        btnNuevo.setEnabled(true);
        itemNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        itemSalir.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemModificarActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        // TODO add your handling code here:
        dlgDetalleGasto dg = new dlgDetalleGasto(null, true);
        dg.setLocationRelativeTo(null);
        dg.setVisible(true);
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
             Volver();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
        if (txtImporte.getText().isEmpty()) {
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else {
            txtObservacion.requestFocus();
        }
    }//GEN-LAST:event_txtImporteActionPerformed

    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtImporte);
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtImporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyReleased
        // TODO add your handling code here:
        try {
            if (txtImporte.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtImporte.setText(df.format(Integer.valueOf(txtImporte.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtImporte.setText("0");
                txtImporte.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        if (txtImporte.getText().equals("")) {
            txtImporteL.setText("0");
        } else {
            DecimalFormat dff = new DecimalFormat("#0");
            txtImporteL.setText(dff.format(Integer.valueOf(txtImporte.getText().trim().replace(".", "").replace(",", ""))));
        }
    }//GEN-LAST:event_txtImporteKeyReleased

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void cbDetalleGastoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbDetalleGastoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboResponsable.requestFocus();
            cboResponsable.setPopupVisible(true);
        }
    }//GEN-LAST:event_cbDetalleGastoKeyPressed

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
        // TODO add your handling code here:
        if (txtObservacion.getText().isEmpty()) {
            txtObservacion.requestFocus();
            txtObservacion.selectAll();
        } else {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void cboResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboResponsableActionPerformed
        // TODO add your handling code here:
        if (cboResponsable.getSelectedIndex() == 0) {
            txtIdMovil.setText("");
            lbInfoMovil.setText("");
        } else {
            prepararBD();
            try {
                String resp;
                resp = cargarComboBox.getCodidgo(cboResponsable);
                try {
                    rs = sentencia.executeQuery("SELECT idmovil, movil FROM v_vendedores WHERE ven_codigo=" + resp);
                    rs.last();
                    txtIdMovil.setText(String.valueOf(rs.getInt("idmovil")));
                    lbInfoMovil.setText("Referencia: " + rs.getString("movil"));
                    rs.close();
                } catch (SQLException ex) {
                    Mensajes.error("Error al querer obtener ID del móvil: " + ex.getMessage());
                }
            } catch (Exception pr) {
            }
        }
    }//GEN-LAST:event_cboResponsableActionPerformed

    private void cboResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboResponsableKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtImporte.requestFocus();
        }
    }//GEN-LAST:event_cboResponsableKeyPressed

    private void cbGeneradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGeneradoActionPerformed

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
            java.util.logging.Logger.getLogger(dlgGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            dlgGastos dialog = new dlgGastos(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuBar barMenu;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnDetalle;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox cbDetalleGasto;
    public static javax.swing.JComboBox<String> cbGenerado;
    public static javax.swing.JComboBox<String> cboResponsable;
    public static datechooser.beans.DateChooserCombo dcFecha;
    public static javax.swing.JMenuItem itemCancelar;
    public static javax.swing.JMenuItem itemGuardar;
    public static javax.swing.JMenuItem itemModificar;
    public static javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbInfoMovil;
    public static javax.swing.JLabel lblCodDetalle;
    private javax.swing.JMenu menuOpciones;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtIdMovil;
    public static javax.swing.JTextField txtImporte;
    public static javax.swing.JTextField txtImporteL;
    public static javax.swing.JTextField txtObservacion;
    // End of variables declaration//GEN-END:variables
}
