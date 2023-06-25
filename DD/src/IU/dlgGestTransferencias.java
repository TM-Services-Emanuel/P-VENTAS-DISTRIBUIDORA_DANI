/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.RenderDecimal1;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import Controladores.controlTransferencias;
import Datos.GestionarArticulosMovil;
import Datos.GestionarTransferencia;
import Modelo.ArticuloMovil;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

/**
 *
 * @author ADMIN
 */
public class dlgGestTransferencias extends javax.swing.JDialog {

    /**
     * Creates new form dlgGestTransferencias
     */
    public static ResultSet rs;
    public static MariaDbConnection con;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection conMovil;
    public static MariaDbStatement sentenciaMovil;
    public static MariaDbStatement stTransaccionMovil;
    static String UsuarioL = "";

    public dlgGestTransferencias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        NoVisible();
        titulo();
        CabecerasTablas.Transferencia(tbDetalle);
        Cancelar();
        
        //LevantarDatos();
        cargarComboBox.cargarResponsable(cboOrigen, "SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
        cargarComboBox.cargarResponsable(cboDestino, "SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
    }

    public final void LevantarDatos() {
        cargarComboBox.cargarResponsable(cboOrigen, "SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
        cargarComboBox.cargarResponsable(cboDestino, "SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
        ckROActionPerformed(null);
        ckRDActionPerformed(null);
    }

    public static void habilitarCANT() {
        if (txtArt.getText().isEmpty()) {
            txtCant.setEnabled(false);
        } else {
            txtCant.setEnabled(true);
        }
    }

    public static void BloquearOrigen() {
        if (dlgGestTransferencias.tbDetalle.getRowCount() > 0) {
            ckLO.setEnabled(false);
            ckRO.setEnabled(false);
            cboOrigen.setEnabled(false);
        } else {
            ckLO.setEnabled(true);
            ckRO.setEnabled(true);
            cboOrigen.setEnabled(true);
        }
    }
    
    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Realizar Transferencia");
        } else {
            this.setTitle(Software.getSoftware() + " - Realizar Transferencia");
        }
    }
    
    public static void NoVisible(){
        txtIdMovilO.setVisible(false);
        txtIdMovilD.setVisible(false);
        txtFecha.setVisible(false);
        lbOpcionOrigen.setVisible(false);
        lbOpcionDestino.setVisible(false);
        txtCodArticulo.setVisible(false);
        btnRestar.setVisible(false);
        btnAdd.setVisible(false);
    }

    private static void Comparar() {
        try {
            if (Integer.parseInt(txtIdMovilO.getText().trim()) == Integer.parseInt(txtIdMovilD.getText().trim())) {
                Mensajes.informacion("OBSERVACIÓN:\nNo sera posible realizar transferencias.\nLos depositos de Origen y Destinos no pueden ser iguales.");
            }
        } catch (NumberFormatException e) {
        }
    }

    private static void CompararLocal() {
        try {
            if (lbOpcionOrigen.getText().trim().equals("L") && lbOpcionDestino.getText().trim().equals("L")) {
                Mensajes.informacion("OBSERVACIÓN:\nNo sera posible realizar esta transferencias.\nLos depositos de Origen y Destinos no pueden ser iguales.");
                //ckRD.setSelected(true);
                ckRD.doClick();
                lbInfoMovilD.setText("");
            }
        } catch (NumberFormatException e) {
        }
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
                stTransaccionMovil = (MariaDbStatement) conMovil.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtIdTransf.setText("");
        txtIdMovilD.setText("");
        txtIdMovilO.setText("");
        txtFecha.setText("");
        txtCaja.setText("");
        txtFechaF.setText("");
        txtHora.setText("");
        lbInfoMovilD.setText("");
        lbInfoMovilO.setText("");
        lbOpcionDestino.setText("");
        lbOpcionOrigen.setText("");
        txtArt.setText("");
        txtCant.setText("");
        txtCodArticulo.setText("");
        txtTotal.setText("0");
        controlFactura.canCelarT();
        CabecerasTablas.Transferencia(tbDetalle);
        CabecerasTablas.limpiarTablasTransferencias(tbDetalle);
    }

    public static void Renders() {
        dlgGestTransferencias.tbDetalle.getColumnModel().getColumn(3).setCellRenderer(new RenderDecimal1());
        dlgGestTransferencias.tbDetalle.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
    }

    public final void Cancelar() {
        limpiarCampos();
        ckLO.setEnabled(false);
        ckRO.setEnabled(false);
        ckLD.setEnabled(false);
        ckRD.setEnabled(false);

        btnBuscarArticulo.setEnabled(false);
        txtCant.setEnabled(false);
        btnNuevo.setEnabled(true);
        itemNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        itemBuscarArticulo.setEnabled(false);
        itemQuitar.setEnabled(false);
        cboOrigen.setEnabled(false);
        cboDestino.setEnabled(false);
        this.dispose();
        Volver();
    }

    public void Nuevo() {
        btnNuevoActionPerformed(null);
    }

    private void Volver() {
        CabecerasTablas.todosTransferencias(dlgTransferencias.tbDetalle);
        CabecerasTablas.limpiarTablasTransferenciasRealizadas(dlgTransferencias.tbDetalle);
        CabecerasTablas.consDetalleTransferencias(dlgTransferencias.tbDetalleTransf);
        CabecerasTablas.limpiarTablasDetalleTransferenciasRealizadas(dlgTransferencias.tbDetalleTransf);
        controlTransferencias.listTransferencias(dlgTransferencias.tbDetalle, "idtransferencia", dlgTransferencias.txtFechaFinal.getText().trim());
        dlgTransferencias.Renders();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoOrigen = new javax.swing.ButtonGroup();
        grupoDestino = new javax.swing.ButtonGroup();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdTransf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFechaF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        ckLO = new javax.swing.JCheckBox();
        ckRO = new javax.swing.JCheckBox();
        cboOrigen = new javax.swing.JComboBox<>();
        lbInfoMovilO = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ckLD = new javax.swing.JCheckBox();
        ckRD = new javax.swing.JCheckBox();
        cboDestino = new javax.swing.JComboBox<>();
        lbInfoMovilD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtArt = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        txtCodArticulo = new javax.swing.JTextField();
        txtCaja = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnBuscarArticulo = new newscomponents.RSButtonGradientIcon_new();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnNuevo = new newscomponents.RSButtonBigIcon_new();
        btnGuardar = new newscomponents.RSButtonBigIcon_new();
        btnCancelar = new newscomponents.RSButtonBigIcon_new();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtIdMovilO = new javax.swing.JTextField();
        txtIdMovilD = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        btnRestar = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lbOpcionOrigen = new javax.swing.JLabel();
        lbOpcionDestino = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        itemQuitar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID Transf.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        txtIdTransf.setEditable(false);
        jPanel1.add(txtIdTransf, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 13, 145, -1));

        jLabel2.setText("Fecha");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 16, -1, -1));

        txtHora.setEditable(false);
        jPanel1.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 13, 48, -1));

        jLabel3.setText("Hora");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 16, -1, -1));

        txtFechaF.setEditable(false);
        jPanel1.add(txtFechaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 13, 96, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grupoOrigen.add(ckLO);
        ckLO.setText("SALÓN DE VENTA");
        ckLO.setEnabled(false);
        ckLO.setOpaque(false);
        ckLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLOActionPerformed(evt);
            }
        });
        jPanel2.add(ckLO, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 36, -1, 18));

        grupoOrigen.add(ckRO);
        ckRO.setSelected(true);
        ckRO.setText("MÓVIL DE REPARTO.");
        ckRO.setEnabled(false);
        ckRO.setOpaque(false);
        ckRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckROActionPerformed(evt);
            }
        });
        jPanel2.add(ckRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 58, -1, 20));

        cboOrigen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cboOrigen.setEnabled(false);
        cboOrigen.setOpaque(false);
        cboOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOrigenActionPerformed(evt);
            }
        });
        jPanel2.add(cboOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 56, 275, -1));

        lbInfoMovilO.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbInfoMovilO.setForeground(new java.awt.Color(0, 102, 102));
        lbInfoMovilO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.add(lbInfoMovilO, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 85, 275, 28));

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TRANSFERENCIA DE ORIGEN");
        jLabel4.setOpaque(true);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 428, 29));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 39, 428, 125));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grupoDestino.add(ckLD);
        ckLD.setText("SALÓN DE VENTA");
        ckLD.setEnabled(false);
        ckLD.setOpaque(false);
        ckLD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLDActionPerformed(evt);
            }
        });
        jPanel3.add(ckLD, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 32, -1, 18));

        grupoDestino.add(ckRD);
        ckRD.setSelected(true);
        ckRD.setText("MÓVIL DE REPARTO.");
        ckRD.setEnabled(false);
        ckRD.setOpaque(false);
        ckRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckRDActionPerformed(evt);
            }
        });
        jPanel3.add(ckRD, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 58, -1, 22));

        cboDestino.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cboDestino.setEnabled(false);
        cboDestino.setOpaque(false);
        cboDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDestinoActionPerformed(evt);
            }
        });
        jPanel3.add(cboDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 57, 275, -1));

        lbInfoMovilD.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbInfoMovilD.setForeground(new java.awt.Color(0, 102, 102));
        lbInfoMovilD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel3.add(lbInfoMovilD, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 86, 275, 28));

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TRANSFERENCIA DESTINO");
        jLabel6.setOpaque(true);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 428, 29));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 39, 428, 125));

        txtArt.setEditable(false);
        txtArt.setBackground(new java.awt.Color(255, 255, 255));
        txtArt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArtActionPerformed(evt);
            }
        });
        txtArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArtKeyReleased(evt);
            }
        });
        jPanel1.add(txtArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 450, 30));

        txtCant.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
        });
        jPanel1.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 170, 50, 30));

        tbDetalle.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
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
        tbDetalle.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetalle.setRowHeight(20);
        tbDetalle.setShowGrid(true);
        tbDetalle.setShowVerticalLines(false);
        tbDetalle.getTableHeader().setResizingAllowed(false);
        tbDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbDetalle);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 205, 862, 260));

        txtCodArticulo.setEditable(false);
        txtCodArticulo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jPanel1.add(txtCodArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 37, -1));

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 13, 109, -1));

        jLabel12.setText("Mov.N°");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 16, -1, -1));

        btnBuscarArticulo.setBackground(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setText("F9 - AGREGAR");
        btnBuscarArticulo.setColorPrimario(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorPrimarioHover(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorSecundario(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorSecundarioHover(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setEnabled(false);
        btnBuscarArticulo.setFocusPainted(false);
        btnBuscarArticulo.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnBuscarArticulo.setIconTextGap(10);
        btnBuscarArticulo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnBuscarArticulo.setSizeIcon(25.0F);
        btnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 170, 140, 30));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.GridLayout(1, 6));

        btnNuevo.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevo.setBorder(null);
        btnNuevo.setText("NUEVO");
        btnNuevo.setBgHover(new java.awt.Color(1, 123, 123));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnNuevo.setIconTextGap(0);
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        btnNuevo.setPixels(0);
        btnNuevo.setSizeIcon(50.0F);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel7.add(btnNuevo);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setBorder(null);
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBgHover(new java.awt.Color(1, 123, 123));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardar.setIconTextGap(0);
        btnGuardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar.setPixels(0);
        btnGuardar.setSizeIcon(50.0F);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel7.add(btnGuardar);

        btnCancelar.setBackground(new java.awt.Color(0, 102, 102));
        btnCancelar.setBorder(null);
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBgHover(new java.awt.Color(1, 123, 123));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancelar.setIconTextGap(0);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setPixels(0);
        btnCancelar.setSizeIcon(50.0F);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel7.add(btnCancelar);

        jPanel5.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(51, 51, 51));
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setBorder(null);
        txtTotal.setOpaque(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtIdMovilD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMovilDActionPerformed(evt);
            }
        });

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lbOpcionOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbOpcionDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtIdMovilO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbOpcionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbOpcionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdMovilO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbOpcionDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbOpcionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAdd))))
                .addContainerGap())
        );

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("OPCIONES");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_note_add_black_20.png"))); // NOI18N
        itemNuevo.setText("NUEVO");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevo);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_save_black_20.png"))); // NOI18N
        itemGuardar.setText("GUARDAR NUEVO");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardar);

        itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_cancel_black_20.png"))); // NOI18N
        itemCancelar.setText("CANCELAR");
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        jMenu1.add(itemCancelar);
        jMenu1.add(jSeparator1);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        itemBuscarArticulo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_add_black_20.png"))); // NOI18N
        itemBuscarArticulo.setText("AGREGAR PRODUCTOS A TRANSFERIR                    ");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarArticulo);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_remove_black_20.png"))); // NOI18N
        itemQuitar.setText("QUITAR PRODUCTO");
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        jMenu1.add(itemQuitar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
//        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        // TODO add your handling code here:
        btnBuscarArticulo.doClick();
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:S
        btnRestar.doClick();
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void txtArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArtActionPerformed

    private void txtArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArtKeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
        if (lbOpcionOrigen.getText().trim().equals("L")) {
            validarCampos.soloDecimales(txtCant);
            ArticuloMovil Ar = GestionarArticulosMovil.busArticulo((txtCodArticulo.getText()));
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (txtCant.getText().isEmpty()) {
                    txtCant.selectAll();
                } else if (txtCant.getText().equals("0")) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) == 0.0) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) > Ar.getStock()) {
                    Mensajes.error("La cantidad que estas intentando Transferir supera el stock actual del producto");
                    txtCant.requestFocus();
                    txtCant.setText(String.valueOf(Ar.getStock()).trim().replace(".0", "").replace(",", ""));
                    txtCant.selectAll();
                } else {
                    btnAdd.doClick();
                }
            }
        } else if (lbOpcionOrigen.getText().trim().equals("R")) {
            validarCampos.soloDecimales(txtCant);
            //ArticuloMovil Ar = GestionarArticulosMovil.busArticulo((txtCodArticulo.getText()));
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (txtCant.getText().isEmpty()) {
                    txtCant.selectAll();
                } else if (txtCant.getText().equals("0")) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) == 0.0) {
                    txtCant.selectAll();
                } else {
                    btnAdd.doClick();
                }
            }
        }
    }//GEN-LAST:event_txtCantKeyPressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (lbOpcionOrigen.getText().trim().equals("L")) {
            controlFactura.addTablaT(dlgGestTransferencias.tbDetalle);
            Renders();
            txtCodArticulo.setText("");
            txtArt.setText("");
            txtCant.setText("");
            btnBuscarArticulo.requestFocus();
            habilitarCANT();
            btnBuscarArticuloActionPerformed(null);
        } else if (lbOpcionOrigen.getText().trim().equals("R")) {
            controlFactura.addTablaTR(dlgGestTransferencias.tbDetalle);
            Renders();
            txtCodArticulo.setText("");
            txtArt.setText("");
            txtCant.setText("");
            btnBuscarArticulo.requestFocus();
            habilitarCANT();
            btnBuscarArticuloActionPerformed(null);
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        try {
            controlFactura.delRenglonT(tbDetalle);
            Renders();
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_btnRestarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void cboOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOrigenActionPerformed
        // TODO add your handling code here:
        if (cboOrigen.getSelectedIndex() == 0) {
            txtIdMovilO.setText("");
            lbOpcionOrigen.setText("R");
            btnBuscarArticulo.setEnabled(false);
            itemBuscarArticulo.setEnabled(false);
        } else {
            prepararBD();
            try {
                String resp;
                resp = cargarComboBox.getCodidgo(cboOrigen);
                try {
                    rs = sentencia.executeQuery("SELECT ven_codigo,idmovil, movil, ven_comision FROM v_vendedores WHERE ven_codigo=" + resp);
                    rs.last();
                    txtIdMovilO.setText(String.valueOf(rs.getInt("idmovil")));
                    lbInfoMovilO.setText(" Referencia: " + rs.getString("movil"));
                    rs.close();
                    btnBuscarArticulo.setEnabled(true);
                    itemBuscarArticulo.setEnabled(true);
                } catch (SQLException ex) {
                    Mensajes.error("Error al querer obtener ID del móvil: " + ex.getMessage());
                }

            } catch (Exception pr) {
            }
        }
        //Comparar();
    }//GEN-LAST:event_cboOrigenActionPerformed

    private void cboDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDestinoActionPerformed
        // TODO add your handling code here:
        if (cboDestino.getSelectedIndex() == 0) {
            txtIdMovilD.setText("");
            lbOpcionDestino.setText("");
        } else {
            prepararBD();
            try {
                String resp;
                resp = cargarComboBox.getCodidgo(cboDestino);
                try {
                    rs = sentencia.executeQuery("SELECT ven_codigo,idmovil, movil, ven_comision FROM v_vendedores WHERE ven_codigo=" + resp);
                    rs.last();
                    txtIdMovilD.setText(String.valueOf(rs.getInt("idmovil")));
                    lbInfoMovilD.setText(" Referencia: " + rs.getString("movil"));
                    rs.close();
                } catch (SQLException ex) {
                    Mensajes.error("Error al querer obtener ID del móvil: " + ex.getMessage());
                }
            } catch (Exception pr) {
            }
        }
        //Comparar();
    }//GEN-LAST:event_cboDestinoActionPerformed

    private void ckLOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLOActionPerformed
        // TODO add your handling code here:
        lbOpcionOrigen.setText("L");
        cboOrigen.setEnabled(false);
        lbInfoMovilO.setText(" Referencia: SALÓN DE VENTA");
        txtIdMovilO.setText("");
        btnBuscarArticulo.setEnabled(true);
        itemBuscarArticulo.setEnabled(true);
        //CompararLocal();
    }//GEN-LAST:event_ckLOActionPerformed

    private void ckLDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLDActionPerformed
        // TODO add your handling code here:
        lbOpcionDestino.setText("L");
        cboDestino.setEnabled(false);
        txtIdMovilD.setText("");
        lbInfoMovilD.setText(" Referencia: " + ckLD.getText().trim());
        //CompararLocal();
    }//GEN-LAST:event_ckLDActionPerformed

    private void ckROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckROActionPerformed
        // TODO add your handling code here:
        lbOpcionOrigen.setText("R");
        cboOrigen.setEnabled(true);
        lbInfoMovilO.setText("");
        cboOrigenActionPerformed(null);
    }//GEN-LAST:event_ckROActionPerformed

    private void ckRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckRDActionPerformed
        // TODO add your handling code here:
        lbOpcionDestino.setText("R");
        cboDestino.setEnabled(true);
        lbInfoMovilD.setText("");
        CompararLocal();
    }//GEN-LAST:event_ckRDActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtIdMovilDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMovilDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMovilDActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        // TODO add your handling code here:
        if (lbOpcionOrigen.getText().trim().equals("L")) {
            try {
                dlgBuscarArticuloVenta1 baf = new dlgBuscarArticuloVenta1(null, true);
                baf.setLocationRelativeTo(null);
                baf.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No hay conexión con el servidor");
            }
        } else if (lbOpcionOrigen.getText().trim().equals("R")) {
            try {
                dlgBuscarArticuloTransferencia baf = new dlgBuscarArticuloTransferencia(null, true);
                baf.setLocationRelativeTo(null);
                baf.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No hay conexión con el servidor");
            }
        }
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:7
        String cod = GestionarTransferencia.getCodigo();
        txtIdTransf.setText(cod);
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtFecha.setText(Fecha.fechaCorrecta());
        txtFechaF.setText(Fecha.fechaCorrectaFachada());
        txtHora.setText(Fecha.darHoraSinSS());
        LevantarDatos();
        //
        ckLO.setEnabled(true);
        ckRO.setEnabled(true);
        ckLD.setEnabled(true);
        ckRD.setEnabled(true);

        //btnBuscarArticulo.setEnabled(true);
        txtCant.setEnabled(true);
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        //itemBuscarArticulo.setEnabled(true);
        itemQuitar.setEnabled(true);
        habilitarCANT();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cod = GestionarTransferencia.getCodigo();
        txtIdTransf.setText(cod);
        if (ckRO.isSelected() && cboOrigen.getSelectedIndex() == 0) {
            Mensajes.error("TRANSFERENCIA ORIGEN:\nSeleccione el reparto de Origen.");
            cboOrigen.requestFocus();
        } else if (ckRD.isSelected() && cboDestino.getSelectedIndex() == 0) {
            cboDestino.requestFocus();
            Mensajes.error("TRANSFERENCIA DESTINO:\nSeleccione el reparto de Destino.");
        } else if (lbOpcionOrigen.getText().trim().equals("R") && lbOpcionDestino.getText().trim().equals("R") && Integer.parseInt(txtIdMovilO.getText().trim()) == Integer.parseInt(txtIdMovilD.getText().trim())) {
            Mensajes.informacion("OBSERVACIÓN:\nNo sera posible realizar transferencias.\nLos depositos de Origen y Destinos no pueden ser iguales.");
        } else if (lbOpcionOrigen.getText().trim().equals("L") && lbOpcionDestino.getText().trim().equals("L")) {
            Mensajes.informacion("OBSERVACIÓN:\nNo sera posible realizar esta transferencias.\nLos depositos de Origen y Destinos no pueden ser iguales.");
        } else if (tbDetalle.getRowCount() == 0) {
            Mensajes.error("OBSERVACIÓN:\nAún no haz añadido ningún producto al detalle.");
        } else {
            if (lbOpcionOrigen.getText().equals("L") && lbOpcionDestino.getText().equals("R")) {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = Login.getUsuarioLogueado();
                        prepararBD();
                        con.setAutoCommit(false);
                        String sql = "INSERT INTO transferencia VALUES(" + txtIdTransf.getText() + "," + txtCaja.getText() + ",'" + txtFecha.getText() + "','" + txtHora.getText() + "','" + lbOpcionOrigen.getText()
                                + "',0,'" + lbInfoMovilO.getText() + "','" + lbOpcionDestino.getText() + "'," + txtIdMovilD.getText() + ",'" + lbInfoMovilD.getText() + "'," + txtTotal.getText().replace(".", "").replace(",", "") + " ,'" + usuario + "','S')";
                        stTransaccionMovil.executeUpdate(sql);
                        int fila = tbDetalle.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {tbDetalle.getValueAt(j, 0).toString(),
                                tbDetalle.getValueAt(j, 4).toString(),
                                tbDetalle.getValueAt(j, 3).toString(),
                                tbDetalle.getValueAt(j, 5).toString()};
                            String sql1 = "INSERT INTO detalle_transferencia VALUES(" + txtIdTransf.getText() + "," + filas[0] + "," + filas[1] + "," + filas[2] + "," + filas[3] + ")";
                            String sql2 = "UPDATE productos SET stock=stock-" + filas[1] + " WHERE  idproducto=" + filas[0];
                            stTransaccionMovil.executeUpdate(sql1);
                            stTransaccionMovil.executeUpdate(sql2);
                        }
                        con.commit();
                        stTransaccionMovil.close();
                        Mensajes.informacion("TRANSACCIÓN EXITOSA!\nLa Transferencia: Salón de venta a Reparto fue registrado satisfactoriamente");
                    } catch (SQLException e) {
                        try {
                            con.rollback();
                            Mensajes.error("TRANSACCION FALLIDA.\nLos datos no fueron guardados.\nMotivo: "+e.getMessage().toUpperCase());
                        } catch (SQLException se) {
                            Mensajes.error(se.getMessage());
                        }
                    }
                    Cancelar();
                }
            } else if (lbOpcionOrigen.getText().equals("R") && lbOpcionDestino.getText().equals("L")) {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = Login.getUsuarioLogueado();
                        prepararBD();
                        con.setAutoCommit(false);
                        String sql = "INSERT INTO transferencia VALUES(" + txtIdTransf.getText() + "," + txtCaja.getText() + ",'" + txtFecha.getText() + "','" + txtHora.getText() + "','" + lbOpcionOrigen.getText()
                                + "',"+txtIdMovilO.getText()+",'" + lbInfoMovilO.getText() + "','" + lbOpcionDestino.getText() + "',0,'" + lbInfoMovilD.getText() + "'," + txtTotal.getText().replace(".", "").replace(",", "") + " ,'" + usuario + "','S')";
                        stTransaccionMovil.executeUpdate(sql);
                        int fila = tbDetalle.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {tbDetalle.getValueAt(j, 0).toString(),
                                tbDetalle.getValueAt(j, 4).toString(),
                                tbDetalle.getValueAt(j, 3).toString(),
                                tbDetalle.getValueAt(j, 5).toString()};
                            String sql1 = "INSERT INTO detalle_transferencia VALUES(" + txtIdTransf.getText() + "," + filas[0] + "," + filas[1] + "," + filas[2] + "," + filas[3] + ")";
                            String sql2 = "UPDATE productos SET stock=stock+" + filas[1] + " WHERE  idproducto=" + filas[0];
                            stTransaccionMovil.executeUpdate(sql1);
                            stTransaccionMovil.executeUpdate(sql2);
                        }
                        con.commit();
                        stTransaccionMovil.close();
                        Mensajes.informacion("TRANSACCIÓN EXITOSA!\nLa Transferencia: Reparto a Salón de venta fue registrado satisfactoriamente");
                    } catch (SQLException e) {
                        try {
                            con.rollback();
                            Mensajes.error("TRANSACCION FALLIDA.\nLos datos no fueron guardados.\nMotivo: "+e.getMessage().toUpperCase());
                        } catch (SQLException se) {
                            Mensajes.error(se.getMessage());
                        }
                    }
                    Cancelar();
                }
            }else if(lbOpcionOrigen.getText().equals("R") && lbOpcionDestino.getText().equals("R")){
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = Login.getUsuarioLogueado();
                        prepararBD();
                        con.setAutoCommit(false);
                        String sql = "INSERT INTO transferencia VALUES(" + txtIdTransf.getText() + "," + txtCaja.getText() + ",'" + txtFecha.getText() + "','" + txtHora.getText() + "','" + lbOpcionOrigen.getText()
                                + "',"+txtIdMovilO.getText()+",'" + lbInfoMovilO.getText() + "','" + lbOpcionDestino.getText() + "'," + txtIdMovilD.getText() + ",'" + lbInfoMovilD.getText() + "'," + txtTotal.getText().replace(".", "").replace(",", "") + " ,'" + usuario + "','S')";
                        stTransaccionMovil.executeUpdate(sql);
                        int fila = tbDetalle.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {tbDetalle.getValueAt(j, 0).toString(),
                                tbDetalle.getValueAt(j, 4).toString(),
                                tbDetalle.getValueAt(j, 3).toString(),
                                tbDetalle.getValueAt(j, 5).toString()};
                            String sql1 = "INSERT INTO detalle_transferencia VALUES(" + txtIdTransf.getText() + "," + filas[0] + "," + filas[1] + "," + filas[2] + "," + filas[3] + ")";
                            //String sql2 = "UPDATE productos SET stock=stock+" + filas[1] + " WHERE  idproducto=" + filas[0];
                            stTransaccionMovil.executeUpdate(sql1);
                            //stTransaccionMovil.executeUpdate(sql2);
                        }
                        con.commit();
                        stTransaccionMovil.close();
                        Mensajes.informacion("TRANSACCIÓN EXITOSA!\nLa Transferencia: Reparto a Reparto fue registrado satisfactoriamente");
                    } catch (SQLException e) {
                        try {
                            con.rollback();
                            Mensajes.error("TRANSACCION FALLIDA.\nLos datos no fueron guardados.\nMotivo: "+e.getMessage().toUpperCase());
                        } catch (SQLException se) {
                            Mensajes.error(se.getMessage());
                        }
                    }
                    Cancelar();
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar la Transferencia?");
        if (rpta == 0) {
            limpiarCampos();
            ckLO.setEnabled(false);
            ckRO.setEnabled(false);
            ckLD.setEnabled(false);
            ckRD.setEnabled(false);

            btnBuscarArticulo.setEnabled(false);
            txtCant.setEnabled(false);
            btnNuevo.setEnabled(true);
            itemNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            itemCancelar.setEnabled(false);
            itemBuscarArticulo.setEnabled(false);
            itemQuitar.setEnabled(false);
            cboOrigen.setEnabled(false);
            cboDestino.setEnabled(false);
            this.dispose();
            Volver();
            //cant();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(dlgGestTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgGestTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgGestTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgGestTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgGestTransferencias dialog = new dlgGestTransferencias(new javax.swing.JFrame(), true);
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
    public static javax.swing.JButton btnAdd;
    public static newscomponents.RSButtonGradientIcon_new btnBuscarArticulo;
    public static newscomponents.RSButtonBigIcon_new btnCancelar;
    public static newscomponents.RSButtonBigIcon_new btnGuardar;
    public static newscomponents.RSButtonBigIcon_new btnNuevo;
    public static javax.swing.JButton btnRestar;
    public static javax.swing.JComboBox<String> cboDestino;
    public static javax.swing.JComboBox<String> cboOrigen;
    public static javax.swing.JCheckBox ckLD;
    public static javax.swing.JCheckBox ckLO;
    public static javax.swing.JCheckBox ckRD;
    public static javax.swing.JCheckBox ckRO;
    private javax.swing.ButtonGroup grupoDestino;
    private javax.swing.ButtonGroup grupoOrigen;
    private javax.swing.JMenuItem itemBuscarArticulo;
    private javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemQuitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JLabel lbInfoMovilD;
    public static javax.swing.JLabel lbInfoMovilO;
    public static javax.swing.JLabel lbOpcionDestino;
    public static javax.swing.JLabel lbOpcionOrigen;
    public static javax.swing.JTable tbDetalle;
    public static javax.swing.JTextField txtArt;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCant;
    public static javax.swing.JTextField txtCodArticulo;
    public static javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaF;
    private javax.swing.JTextField txtHora;
    public static javax.swing.JTextField txtIdMovilD;
    public static javax.swing.JTextField txtIdMovilO;
    private javax.swing.JTextField txtIdTransf;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
