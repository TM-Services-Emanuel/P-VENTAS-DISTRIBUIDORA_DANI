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
        btnSalir.setEnabled(true);
        itemSalir.setEnabled(true);
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
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnRestar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtIdMovilO = new javax.swing.JTextField();
        txtIdMovilD = new javax.swing.JTextField();
        lbOpcionOrigen = new javax.swing.JLabel();
        lbOpcionDestino = new javax.swing.JLabel();
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
        Oscuro1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel4 = new javax.swing.JLabel();
        lbInfoMovilO = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ckLD = new javax.swing.JCheckBox();
        ckRD = new javax.swing.JCheckBox();
        cboDestino = new javax.swing.JComboBox<>();
        Oscuro2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        lbInfoMovilD = new javax.swing.JLabel();
        btnBuscarArticulo = new javax.swing.JButton();
        txtArt = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        txtCodArticulo = new javax.swing.JTextField();
        txtCaja = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
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
        btnNuevo.setText("NUEVO");
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

        btnGuardar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
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
        btnCancelar.setText("CANCELAR");
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
        btnSalir.setText("SALIR");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalir);

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        jPanel5.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL TRANSFERENCIA");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(51, 51, 51));
        txtTotal.setFont(new java.awt.Font("Digital-7 Mono", 1, 40)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        txtIdMovilD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMovilDActionPerformed(evt);
            }
        });

        lbOpcionOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbOpcionDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtIdMovilO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(lbOpcionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbOpcionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(184, 184, 184)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdMovilO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAdd)
                            .addComponent(lbOpcionOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbOpcionDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel1.setText("ID Transf.");

        txtIdTransf.setEditable(false);

        jLabel2.setText("Fecha");

        txtHora.setEditable(false);

        jLabel3.setText("Hora");

        txtFechaF.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);

        grupoOrigen.add(ckLO);
        ckLO.setText("SALÓN DE VENTA");
        ckLO.setEnabled(false);
        ckLO.setOpaque(false);
        ckLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLOActionPerformed(evt);
            }
        });

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

        cboOrigen.setEnabled(false);
        cboOrigen.setOpaque(false);
        cboOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOrigenActionPerformed(evt);
            }
        });

        Oscuro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro1.setPreferredSize(new java.awt.Dimension(690, 418));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TRANSFERENCIA DE ORIGEN");

        javax.swing.GroupLayout Oscuro1Layout = new javax.swing.GroupLayout(Oscuro1);
        Oscuro1.setLayout(Oscuro1Layout);
        Oscuro1Layout.setHorizontalGroup(
            Oscuro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Oscuro1Layout.setVerticalGroup(
            Oscuro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        lbInfoMovilO.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbInfoMovilO.setForeground(new java.awt.Color(17, 35, 46));
        lbInfoMovilO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 35, 46)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckLO)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckRO)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbInfoMovilO, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Oscuro1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(ckLO, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(ckRO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInfoMovilO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setOpaque(false);

        grupoDestino.add(ckLD);
        ckLD.setText("SALÓN DE VENTA");
        ckLD.setEnabled(false);
        ckLD.setOpaque(false);
        ckLD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLDActionPerformed(evt);
            }
        });

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

        cboDestino.setEnabled(false);
        cboDestino.setOpaque(false);
        cboDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDestinoActionPerformed(evt);
            }
        });

        Oscuro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro2.setPreferredSize(new java.awt.Dimension(690, 418));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TRANSFERENCIA DESTINO");

        javax.swing.GroupLayout Oscuro2Layout = new javax.swing.GroupLayout(Oscuro2);
        Oscuro2.setLayout(Oscuro2Layout);
        Oscuro2Layout.setHorizontalGroup(
            Oscuro2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Oscuro2Layout.setVerticalGroup(
            Oscuro2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        lbInfoMovilD.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbInfoMovilD.setForeground(new java.awt.Color(17, 35, 46));
        lbInfoMovilD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 35, 46)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro2, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckRD)
                    .addComponent(ckLD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbInfoMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Oscuro2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckLD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckRD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInfoMovilD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnBuscarArticulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscarArticulo.setForeground(new java.awt.Color(0, 0, 51));
        btnBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnBuscarArticulo.setText("F9-Agregar");
        btnBuscarArticulo.setActionCommand("F9 - Buscar Artículo");
        btnBuscarArticulo.setBorderPainted(false);
        btnBuscarArticulo.setContentAreaFilled(false);
        btnBuscarArticulo.setEnabled(false);
        btnBuscarArticulo.setFocusable(false);
        btnBuscarArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArticuloActionPerformed(evt);
            }
        });

        txtArt.setEditable(false);
        txtArt.setBackground(new java.awt.Color(255, 255, 255));
        txtArt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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

        txtCant.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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
        tbDetalle.getTableHeader().setResizingAllowed(false);
        tbDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbDetalle);

        txtCodArticulo.setEditable(false);
        txtCodArticulo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setText("Mov.N°");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArt, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdTransf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlancoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                .addContainerGap())
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("OPCIONES");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        itemNuevo.setText("NUEVO");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevo);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save15.png"))); // NOI18N
        itemGuardar.setText("GUARDAR NUEVO");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardar);

        itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel15.png"))); // NOI18N
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
        itemBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        itemBuscarArticulo.setText("AGREGAR PRODUCTOS A TRANSFERIR                    ");
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarArticulo);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/No-entry.png"))); // NOI18N
        itemQuitar.setText("QUITAR PRODUCTO");
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        jMenu1.add(itemQuitar);
        jMenu1.add(jSeparator3);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 573, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
//        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
////        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
//        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void itemBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarArticuloActionPerformed
        // TODO add your handling code here:
        btnBuscarArticulo.doClick();
    }//GEN-LAST:event_itemBuscarArticuloActionPerformed

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:S
        btnRestar.doClick();
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
//        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
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
        btnSalir.setEnabled(false);
        itemSalir.setEnabled(false);
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
            btnSalir.setEnabled(true);
            itemSalir.setEnabled(true);
            itemQuitar.setEnabled(false);
            cboOrigen.setEnabled(false);
            cboDestino.setEnabled(false);
            this.dispose();
            Volver();
            //cant();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            //controlCompra1.array.vaciar();
            try {
                //cabe.ArticulosMovil(dlgArticulosMovil.tbProductos);
                //controlArticuloMovil.listArticulo(dlgArticulosMovil.tbProductos, "cod");
            } catch (Exception e) {
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

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
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private org.edisoncor.gui.panel.PanelImage Oscuro1;
    private org.edisoncor.gui.panel.PanelImage Oscuro2;
    public static javax.swing.JButton btnAdd;
    public static javax.swing.JButton btnBuscarArticulo;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnRestar;
    public static javax.swing.JButton btnSalir;
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
    private javax.swing.JMenuItem itemSalir;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
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
