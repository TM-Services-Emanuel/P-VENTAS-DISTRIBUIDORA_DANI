package IU;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.cargarComboBoxMovil;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlArticuloMovil;
import Datos.GestionarArticulosMovil;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class dlgGestArticulosMovil11 extends javax.swing.JDialog {

    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    public static int id;

    public dlgGestArticulosMovil11(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        prepararBD();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
        Invisible();
        dependencia();
        //calcularGanancia();
    }

    private void dependencia() {
        if (ckDependencia.isSelected()) {
            btnBuscarDependencia.setEnabled(true);
        } else if (!ckDependencia.isSelected()) {
            btnBuscarDependencia.setEnabled(false);
        }
    }

    private void promocion() {
        if (ckPromocion.isSelected()) {
            txtCantPromocion.setEnabled(true);
            txtCantPromocion.requestFocus();
            txtPrecioPromocion.setEnabled(true);
            txtPorcPromocion.setEnabled(true);
        } else if (!ckPromocion.isSelected()) {
            txtCantPromocion.setEnabled(false);
            txtCantPromocion.setText("0");
            txtPrecioPromocion.setEnabled(true);
            txtPrecioPromocion.setText("0");
            txtPorcPromocion.setEnabled(false);
            txtPorcPromocion.setText("0");
        }
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Crear o modificar Productos");
        } else {
            this.setTitle(Software.getSoftware() + " - Crear o modificar Productos");
        }
    }

    private void CargarCombos() {
        cargarComboBoxMovil.cargar(cboClasificacion, "SELECT * FROM division WHERE estado='S'");
        cargarComboBoxMovil.cargar(cboUM, "SELECT * FROM unidad_medida WHERE estado='S'");
        cargarComboBoxMovil.cargar(cboImpuesto, "SELECT idiva, descripcion FROM iva WHERE estado='S'");
    }

    private void Invisible() {
        txtCodUM.setVisible(false);
        txtCodImpuesto.setVisible(false);
        txtCodClasificacion.setVisible(false);
        txtPrecioVentaL.setVisible(false);
        txtPrecioCostoL.setVisible(false);
    }

    private void Habilitacion() {
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        btnLaboratorio.setEnabled(true);
        btnProveedor.setEnabled(true);
        btnFamilia.setEnabled(true);
        txtCodInterno.setEnabled(true);
        txtDescripcion.setEnabled(true);
        cboClasificacion.setEnabled(true);
        cboImpuesto.setEnabled(true);
        cboUM.setEnabled(true);
        txtPrecioVenta.setEnabled(true);
        txtStock.setEnabled(true);
        txtPrecioVentaMinorista.setEnabled(true);
        ckHabilitar.setEnabled(true);
        ckHabilitar.setSelected(false);
    }

    private void Cancelar() {
        limpiarCampos();
        btnNuevo.setEnabled(true);
        itemNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        btnLaboratorio.setEnabled(false);
        btnProveedor.setEnabled(false);
        btnFamilia.setEnabled(false);
        txtCodInterno.setEnabled(false);
        txtDescripcion.setEnabled(false);
        cboClasificacion.setEnabled(false);
        cboImpuesto.setEnabled(false);
        cboUM.setEnabled(false);
        txtPrecioVenta.setEnabled(false);
        txtStock.setEnabled(false);
        Volver();
        this.dispose();
    }

    private void calcularGanancia() {
        DecimalFormat df = new DecimalFormat("#,###");
        int costo, ganancia, venta;
        venta = Integer.parseInt(txtPrecioVentaL.getText());
        costo = Integer.parseInt(txtPrecioCostoL.getText());
        ganancia = venta - costo;
        txtGanancia.setText(String.valueOf(df.format(ganancia)));
    }

    private void calcularGananciaMinorista() {
        DecimalFormat df = new DecimalFormat("#,###");
        int costo, ganancia, venta;
        venta = Integer.parseInt(txtPrecioVentaMinorista.getText().trim().replace(",", "").replace(".", ""));
        costo = Integer.parseInt(txtCosto.getText().trim().replace(",", "").replace(".", ""));
        ganancia = venta - costo;
        txtGananciaMinorista.setText(String.valueOf(df.format(ganancia)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCodClasificacion = new javax.swing.JTextField();
        txtCodUM = new javax.swing.JTextField();
        txtCodImpuesto = new javax.swing.JTextField();
        txtPrecioVentaL = new javax.swing.JTextField();
        txtPrecioCostoL = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtCodProducto = new javax.swing.JTextField();
        txtCodInterno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboClasificacion = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboUM = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cboImpuesto = new javax.swing.JComboBox();
        btnFamilia = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnLaboratorio = new javax.swing.JButton();
        c7 = new javax.swing.JLabel();
        c9 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCodBarra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        c11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtPrecioVentaMinorista = new javax.swing.JTextField();
        c12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtGanancia = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtGananciaMinorista = new javax.swing.JTextField();
        c8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        c10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtCantM = new javax.swing.JTextField();
        ckHabilitar = new rojerusan.RSCheckBox();
        txtidDependencia = new javax.swing.JTextField();
        btnBuscarDependencia = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        ckPromocion = new rojerusan.RSCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtCantPromocion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPorcPromocion = new javax.swing.JTextField();
        txtPrecioPromocion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ckDependencia = new rojerusan.RSCheckBox();
        barMenu = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document30.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setFocusPainted(false);
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

        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
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

        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
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

        txtPrecioVentaL.setEditable(false);
        txtPrecioVentaL.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        txtPrecioVentaL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaL.setText("0");

        txtPrecioCostoL.setEditable(false);
        txtPrecioCostoL.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        txtPrecioCostoL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioCostoL.setText("0");

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodUM, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPrecioCostoL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVentaL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioVentaL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioCostoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Blanco.add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 83));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMACIÓN DEL PRODUCTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodProducto.setEditable(false);
        txtCodProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtCodProducto.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(txtCodProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 27, 136, 23));

        txtCodInterno.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodInterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCodInterno.setNextFocusableComponent(txtDescripcion);
        txtCodInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodInternoActionPerformed(evt);
            }
        });
        txtCodInterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodInternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodInternoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 56, 311, 23));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel2.setText("ID Producto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 27, -1, 20));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel3.setText("Código Interno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, 20));

        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 114, 503, 23));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel7.setText("Descripción del Producto");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, 20));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel5.setText("Clasificación");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 16, -1, 20));

        cboClasificacion.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cboClasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboClasificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboClasificacion.setNextFocusableComponent(cboUM);
        cboClasificacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboClasificacionItemStateChanged(evt);
            }
        });
        cboClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClasificacionActionPerformed(evt);
            }
        });
        cboClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboClasificacionKeyPressed(evt);
            }
        });
        jPanel7.add(cboClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 12, 213, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel4.setText("U. Medida");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 48, -1, 20));

        cboUM.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cboUM.setAutoscrolls(true);
        cboUM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboUM.setNextFocusableComponent(cboImpuesto);
        cboUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUMActionPerformed(evt);
            }
        });
        cboUM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboUMKeyPressed(evt);
            }
        });
        jPanel7.add(cboUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 45, 213, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel6.setText("Impuesto");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 81, -1, 20));

        cboImpuesto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cboImpuesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboImpuesto.setNextFocusableComponent(txtCosto);
        cboImpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboImpuestoActionPerformed(evt);
            }
        });
        cboImpuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboImpuestoKeyPressed(evt);
            }
        });
        jPanel7.add(cboImpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 77, 213, -1));

        btnFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        btnFamilia.setToolTipText("Gestionar Familia");
        btnFamilia.setBorderPainted(false);
        btnFamilia.setContentAreaFilled(false);
        btnFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFamiliaActionPerformed(evt);
            }
        });
        jPanel7.add(btnFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 79, 21, 20));

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        btnProveedor.setToolTipText("Gestionar Proveedor");
        btnProveedor.setBorderPainted(false);
        btnProveedor.setContentAreaFilled(false);
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });
        jPanel7.add(btnProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 47, 21, 20));

        btnLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        btnLaboratorio.setToolTipText("Gestionar Laboratorio");
        btnLaboratorio.setBorderPainted(false);
        btnLaboratorio.setContentAreaFilled(false);
        btnLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratorioActionPerformed(evt);
            }
        });
        jPanel7.add(btnLaboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 14, 21, 20));

        c7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel7.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 14, -1, -1));

        c9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel7.add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 47, -1, -1));

        c5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel7.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 79, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 148, 350, 110));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel9.setText("Código Barra");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, -1, 20));

        txtCodBarra.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodBarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCodBarra.setNextFocusableComponent(txtDescripcion);
        txtCodBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodBarraActionPerformed(evt);
            }
        });
        txtCodBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodBarraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodBarraKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 85, 311, 23));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(17, 35, 46));
        jLabel19.setText("Precio Mayorista");
        jLabel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(jLabel19);
        jLabel19.setBounds(26, 159, 110, 19);

        txtPrecioVenta.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(0, 102, 0));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtPrecioVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPrecioVentaMouseClicked(evt);
            }
        });
        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });
        jPanel3.add(txtPrecioVenta);
        txtPrecioVenta.setBounds(149, 155, 120, 28);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(17, 35, 46));
        jLabel10.setText("Precio de Costo");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(jLabel10);
        jLabel10.setBounds(28, 17, 108, 19);

        txtCosto.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtCosto.setForeground(new java.awt.Color(204, 0, 0));
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCostoMouseClicked(evt);
            }
        });
        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });
        jPanel3.add(txtCosto);
        txtCosto.setBounds(150, 13, 120, 28);

        c11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel3.add(c11);
        c11.setBounds(274, 13, 20, 28);

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(17, 35, 46));
        jLabel22.setText("Precio Minorista");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(27, 88, 114, 17);

        txtPrecioVentaMinorista.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtPrecioVentaMinorista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVentaMinorista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtPrecioVentaMinorista.setMinimumSize(new java.awt.Dimension(7, 21));
        txtPrecioVentaMinorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPrecioVentaMinoristaMouseClicked(evt);
            }
        });
        txtPrecioVentaMinorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaMinoristaActionPerformed(evt);
            }
        });
        txtPrecioVentaMinorista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaMinoristaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaMinoristaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaMinoristaKeyTyped(evt);
            }
        });
        jPanel3.add(txtPrecioVentaMinorista);
        txtPrecioVentaMinorista.setBounds(150, 83, 120, 28);

        c12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel3.add(c12);
        c12.setBounds(273, 155, 20, 30);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(265, 30));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ganancia May.");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtGanancia.setEditable(false);
        txtGanancia.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtGanancia.setForeground(new java.awt.Color(255, 255, 255));
        txtGanancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGanancia.setBorder(null);
        txtGanancia.setOpaque(false);
        txtGanancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGananciaFocusLost(evt);
            }
        });
        txtGanancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGananciaMouseClicked(evt);
            }
        });
        txtGanancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGananciaActionPerformed(evt);
            }
        });
        txtGanancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGananciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel2);
        jPanel2.setBounds(4, 119, 290, 30);

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setPreferredSize(new java.awt.Dimension(265, 30));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Ganancia Min.");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtGananciaMinorista.setEditable(false);
        txtGananciaMinorista.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtGananciaMinorista.setForeground(new java.awt.Color(255, 255, 255));
        txtGananciaMinorista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGananciaMinorista.setBorder(null);
        txtGananciaMinorista.setOpaque(false);
        txtGananciaMinorista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGananciaMinoristaFocusLost(evt);
            }
        });
        txtGananciaMinorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGananciaMinoristaMouseClicked(evt);
            }
        });
        txtGananciaMinorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGananciaMinoristaActionPerformed(evt);
            }
        });
        txtGananciaMinorista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGananciaMinoristaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaMinoristaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaMinoristaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtGananciaMinorista, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtGananciaMinorista, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel5);
        jPanel5.setBounds(4, 47, 290, 30);

        c8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel3.add(c8);
        c8.setBounds(274, 83, 20, 30);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 205, 300, 195));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(17, 35, 46));
        jLabel13.setText("Stock actual");

        txtStock.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setText("0");
        txtStock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtStock.setEnabled(false);
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(10, 10, 10)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 265, 219, -1));

        c10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alert20.png"))); // NOI18N
        jPanel1.add(c10, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 114, -1, 23));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        txtCantM.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        txtCantM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCantM.setEnabled(false);
        txtCantM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantMMouseClicked(evt);
            }
        });
        txtCantM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantMActionPerformed(evt);
            }
        });
        txtCantM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantMKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantMKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantMKeyTyped(evt);
            }
        });

        ckHabilitar.setForeground(new java.awt.Color(0, 102, 102));
        ckHabilitar.setText("APLICAR PRECIO MAYORISTA");
        ckHabilitar.setColorCheck(new java.awt.Color(0, 102, 102));
        ckHabilitar.setColorUnCheck(new java.awt.Color(102, 102, 102));
        ckHabilitar.setFocusPainted(false);
        ckHabilitar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        ckHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckHabilitarActionPerformed(evt);
            }
        });
        ckHabilitar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ckHabilitarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ckHabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCantM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 148, -1, -1));

        txtidDependencia.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtidDependencia.setForeground(new java.awt.Color(17, 35, 46));
        txtidDependencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidDependencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtidDependencia.setEnabled(false);
        jPanel1.add(txtidDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 40, 84, 23));

        btnBuscarDependencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        btnBuscarDependencia.setToolTipText("Gestionar Familia");
        btnBuscarDependencia.setBorderPainted(false);
        btnBuscarDependencia.setContentAreaFilled(false);
        btnBuscarDependencia.setFocusPainted(false);
        btnBuscarDependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDependenciaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 40, 21, 23));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ckPromocion.setForeground(new java.awt.Color(0, 102, 102));
        ckPromocion.setText("Habilitar Promoción");
        ckPromocion.setColorCheck(new java.awt.Color(0, 102, 102));
        ckPromocion.setColorUnCheck(new java.awt.Color(102, 102, 102));
        ckPromocion.setFocusPainted(false);
        ckPromocion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        ckPromocion.setIconTextGap(2);
        ckPromocion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckPromocionActionPerformed(evt);
            }
        });
        jPanel8.add(ckPromocion, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 149, 24));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel1.setText("%");
        jPanel8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 50, 10, 23));

        txtCantPromocion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCantPromocion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantPromocion.setText("0");
        txtCantPromocion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCantPromocion.setEnabled(false);
        txtCantPromocion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantPromocionActionPerformed(evt);
            }
        });
        txtCantPromocion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantPromocionKeyPressed(evt);
            }
        });
        jPanel8.add(txtCantPromocion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 25, 90, 23));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel8.setText("A PARTIR DE");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 25, -1, 23));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel11.setText("PRECIO PROMOCIÓN");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 50, -1, 23));

        txtPorcPromocion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtPorcPromocion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcPromocion.setText("0");
        txtPorcPromocion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtPorcPromocion.setEnabled(false);
        txtPorcPromocion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPorcPromocionMouseClicked(evt);
            }
        });
        txtPorcPromocion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPorcPromocionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcPromocionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcPromocionKeyTyped(evt);
            }
        });
        jPanel8.add(txtPorcPromocion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 40, 23));

        txtPrecioPromocion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtPrecioPromocion.setForeground(new java.awt.Color(0, 102, 102));
        txtPrecioPromocion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioPromocion.setText("0");
        txtPrecioPromocion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtPrecioPromocion.setEnabled(false);
        txtPrecioPromocion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPrecioPromocionMouseClicked(evt);
            }
        });
        txtPrecioPromocion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioPromocionActionPerformed(evt);
            }
        });
        txtPrecioPromocion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioPromocionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioPromocionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioPromocionKeyTyped(evt);
            }
        });
        jPanel8.add(txtPrecioPromocion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 90, 23));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel12.setText("UNIDADES");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 25, 60, 23));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, 354, 85));

        ckDependencia.setForeground(new java.awt.Color(0, 102, 102));
        ckDependencia.setText("Habilitar Dependencia");
        ckDependencia.setColorCheck(new java.awt.Color(0, 102, 102));
        ckDependencia.setColorUnCheck(new java.awt.Color(102, 102, 102));
        ckDependencia.setFocusPainted(false);
        ckDependencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ckDependencia.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        ckDependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckDependenciaActionPerformed(evt);
            }
        });
        jPanel1.add(ckDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 15, 170, 23));

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 89, -1, 411));

        barMenu.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        menuOpciones.setText("OPCIONES");
        menuOpciones.setFocusable(false);
        menuOpciones.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        menuOpciones.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        itemNuevo.setText("NUEVO");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        menuOpciones.add(itemNuevo);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save15.png"))); // NOI18N
        itemGuardar.setText("GUARDAR NUEVO REGISTRO");
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
        itemCancelar.setText("CANCELAR");
        itemCancelar.setEnabled(false);
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemCancelar);

        barMenu.add(menuOpciones);

        setJMenuBar(barMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboImpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboImpuestoActionPerformed
        // TODO add your handling code here:
        /* if (cboImpuesto.getSelectedIndex() == 0) {
            txtGanancia.setText("0");
        } else {
            String item = String.valueOf(cboImpuesto.getSelectedItem());
            try {
                rs = sentencia.executeQuery("Select idiva from iva where descripcion='" + item + "' and estado='S'");
                rs.first();
                id = rs.getInt("idiva");
            } catch (SQLException ex) {
                Mensajes.error("ID Impuesto:" + ex.getMessage());
            }
        }*/
    }//GEN-LAST:event_cboImpuestoActionPerformed

    private void cboClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClasificacionActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboClasificacionActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here
        CargarCombos();
        limpiarCampos();
        Habilitacion();
        String cod = GestionarArticulosMovil.getCodigo();
        txtCodProducto.setText(cod);
        txtCodInterno.requestFocus();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (cboClasificacion.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione una Clasificación");
            cboClasificacion.requestFocus();
        } else if (cboUM.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione una Unidad de medida");
            cboUM.requestFocus();
        } else if (cboImpuesto.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un Impuesto");
            cboImpuesto.requestFocus();
        } else if (ckDependencia.isSelected() && Integer.parseInt(txtidDependencia.getText()) <= 0) {
            Mensajes.Alerta("Dependencia de Producto habilitado!\nBusque y Seleccione el Producto al cual vincular.");
        } else if (validarCampos.estaVacio(txtDescripcion) && validarCampos.estaVacio(txtPrecioVentaL)
                && validarCampos.estaVacio(txtStock)) {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    String cod = GestionarArticulosMovil.getCodigo();
                    txtCodProducto.setText(cod);
                    controlArticuloMovil.addArticulo1();
                    Cancelar();
                } else {
                    txtCodInterno.requestFocus();
                }
            } catch (HeadlessException ee) {
                System.out.println("FATAL ERROR: " + ee.getMessage());
            }
        } else {
            Mensajes.informacion("Verifique que todos los campos exigidos esten completados");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void btnFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFamiliaActionPerformed
        // TODO add your handling code here:
        dlgIVAMovil fa = new dlgIVAMovil(null, true);
        fa.setLocationRelativeTo(null);
        fa.setVisible(true);
    }//GEN-LAST:event_btnFamiliaActionPerformed

    private void btnLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorioActionPerformed
        // TODO add your handling code here:
        dlgClasificacionMovil la = new dlgClasificacionMovil(null, true);
        la.setLocationRelativeTo(null);
        la.setVisible(true);
    }//GEN-LAST:event_btnLaboratorioActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        // TODO add your handling code here:
        dlgUMMovil umM = new dlgUMMovil(null, true);
        umM.setLocationRelativeTo(null);
        umM.setVisible(true);
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
        /*if (!btnGuardar.isEnabled()) {
                btnModificar.doClick();
            } else {
                btnGuardar.doClick();
            }*/
        ckHabilitar.requestFocus();
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void txtGananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtGananciaActionPerformed

    private void cboUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUMActionPerformed

    private void txtGananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaKeyReleased

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtCosto.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtCosto.setText(df.format(Integer.valueOf(txtCosto.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtCosto.setText("0");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (txtCosto.getText().equals("")) {
            txtCosto.setText("0");
        } else {
            DecimalFormat dff = new DecimalFormat("#0");
            txtPrecioCostoL.setText(dff.format(Integer.valueOf(txtCosto.getText().trim().replace(".", "").replace(",", ""))));
        }
        calcularGanancia();
        calcularGananciaMinorista();
    }//GEN-LAST:event_txtCostoKeyReleased

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        // TODO add your handling code here:
        try {
            if (txtPrecioVenta.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtPrecioVenta.setText(df.format(Integer.valueOf(txtPrecioVenta.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtPrecioVenta.setText("0");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (txtPrecioVenta.getText().equals("")) {
            txtPrecioVenta.setText("0");
        } else {
            DecimalFormat dff = new DecimalFormat("#0");
            txtPrecioVentaL.setText(dff.format(Integer.valueOf(txtPrecioVenta.getText().trim().replace(".", "").replace(",", ""))));
        }
        calcularGanancia();
    }//GEN-LAST:event_txtPrecioVentaKeyReleased

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        // TODO add your handling code here:
        int limite = 10;
        if (txtCosto.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCostoKeyTyped

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:  
        txtPrecioVentaMinorista.requestFocus();
    }//GEN-LAST:event_txtCostoActionPerformed

    private void txtGananciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGananciaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaFocusLost

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar?");
        if (rpta == 0) {
            limpiarCampos();
            btnNuevo.setEnabled(true);
            itemNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            itemCancelar.setEnabled(false);
            btnLaboratorio.setEnabled(false);
            btnProveedor.setEnabled(false);
            btnFamilia.setEnabled(false);
            txtCodInterno.setEnabled(false);
            txtDescripcion.setEnabled(false);
            cboClasificacion.setEnabled(false);
            cboImpuesto.setEnabled(false);
            cboUM.setEnabled(false);
            txtCosto.setEnabled(false);
            txtGanancia.setEnabled(false);
            txtPrecioVenta.setEnabled(false);
            txtStock.setEnabled(false);
            Volver();
            this.dispose();
        } else {
            txtCodInterno.requestFocus();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtCodInternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodInternoKeyPressed
        // TODO add your handling code here:
        /* if (!txtCodBarra.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtCodBarra);
        }*/

    }//GEN-LAST:event_txtCodInternoKeyPressed

    private void txtGananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        int limite = 5;
        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

            System.out.println("Ingresa Solo Numeros");
        }
        if (txtGanancia.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGananciaKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        // TODO add your handling code here:

        int limite = 10;
        if (txtPrecioVenta.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioVentaKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        // TODO add your handling code here:
        //char c = evt.getKeyChar();
        int limite = 7;
        if (txtStock.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPrecioVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecioVentaMouseClicked
        // TODO add your handling code here:
        //txtPrecioVenta.selectAll();
    }//GEN-LAST:event_txtPrecioVentaMouseClicked

    private void txtCostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCostoMouseClicked
        // TODO add your handling code here:
        //txtCosto.selectAll();
    }//GEN-LAST:event_txtCostoMouseClicked

    private void txtCodInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodInternoActionPerformed
        // TODO add your handling code here:
        txtCodBarra.requestFocus();
    }//GEN-LAST:event_txtCodInternoActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
        cboClasificacion.requestFocus();
        cboClasificacion.setPopupVisible(true);
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void cboClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboClasificacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboUM.requestFocus();
            cboUM.setPopupVisible(true);
        }
    }//GEN-LAST:event_cboClasificacionKeyPressed

    private void cboUMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboUMKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboImpuesto.requestFocus();
            cboImpuesto.setPopupVisible(true);
        }
    }//GEN-LAST:event_cboUMKeyPressed

    private void cboImpuestoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboImpuestoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtStock.isEnabled()) {
                ckHabilitar.requestFocus();
            } else {
                txtStock.requestFocus();
            }
        }
    }//GEN-LAST:event_cboImpuestoKeyPressed

    private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyPressed
        // TODO add your handling code here:
        if (!txtPrecioVenta.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPrecioVenta);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtPrecioVentaKeyPressed

    private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyPressed
        // TODO add your handling code here:
        if (!txtCosto.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtCosto);
        }
    }//GEN-LAST:event_txtCostoKeyPressed

    private void txtStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyPressed
        // TODO add your handling code here:
        //validarCampos.soloNumeros(txtStock);
        validarCampos.soloDecimales(txtStock);
    }//GEN-LAST:event_txtStockKeyPressed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
        ckHabilitar.requestFocus();
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtGananciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGananciaMouseClicked
        // TODO add your handling code here:
        txtGanancia.selectAll();
    }//GEN-LAST:event_txtGananciaMouseClicked

    private void txtGananciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyPressed
        // TODO add your handling code here:
        if (!txtGanancia.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtGanancia);
        }
    }//GEN-LAST:event_txtGananciaKeyPressed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowLostFocus

    private void cboClasificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboClasificacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboClasificacionItemStateChanged

    private void txtCodInternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodInternoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtCodInternoKeyTyped

    private void txtCodBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodBarraActionPerformed
        // TODO add your handling code here:
        txtDescripcion.requestFocus();
    }//GEN-LAST:event_txtCodBarraActionPerformed

    private void txtCodBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodBarraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodBarraKeyPressed

    private void txtCodBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodBarraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodBarraKeyTyped

    private void txtPrecioVentaMinoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecioVentaMinoristaMouseClicked
        // TODO add your handling code here:
        txtPrecioVenta.selectAll();
    }//GEN-LAST:event_txtPrecioVentaMinoristaMouseClicked

    private void txtPrecioVentaMinoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaMinoristaActionPerformed
        // TODO add your handling code here:
        txtPrecioVenta.requestFocus();
    }//GEN-LAST:event_txtPrecioVentaMinoristaActionPerformed

    private void txtPrecioVentaMinoristaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaMinoristaKeyPressed
        // TODO add your handling code here:
        if (!txtPrecioVentaMinorista.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPrecioVentaMinorista);
        }

    }//GEN-LAST:event_txtPrecioVentaMinoristaKeyPressed

    private void txtPrecioVentaMinoristaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaMinoristaKeyReleased
        // TODO add your handling code here:
        try {
            if (txtPrecioVentaMinorista.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtPrecioVentaMinorista.setText(df.format(Integer.valueOf(txtPrecioVentaMinorista.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtPrecioVentaMinorista.setText("0");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        calcularGananciaMinorista();
    }//GEN-LAST:event_txtPrecioVentaMinoristaKeyReleased

    private void txtPrecioVentaMinoristaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaMinoristaKeyTyped
        // TODO add your handling code here:
        int limite = 10;
        if (txtPrecioVentaMinorista.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioVentaMinoristaKeyTyped

    private void txtCantMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantMMouseClicked

    private void txtCantMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantMActionPerformed
        // TODO add your handling code here:
        if (txtCantM.getText().isEmpty()) {
            txtCantM.setText("2");
            txtPrecioVentaMinorista.requestFocus();
        } else if (Integer.parseInt(txtCantM.getText()) < 2) {
            Mensajes.informacion("Para aplicar Precio Mayorista se requiere una Cantidad mayor que 1");
            txtCantM.setText("2");
            txtPrecioVentaMinorista.requestFocus();
        } else {
            txtPrecioVentaMinorista.requestFocus();
        }
    }//GEN-LAST:event_txtCantMActionPerformed

    private void txtCantMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantMKeyPressed
        // TODO add your handling code here:
        if (!txtCantM.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPrecioVenta);
        }
    }//GEN-LAST:event_txtCantMKeyPressed

    private void txtCantMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantMKeyReleased

    private void txtCantMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantMKeyTyped

    private void txtGananciaMinoristaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaFocusLost

    private void txtGananciaMinoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaMouseClicked

    private void txtGananciaMinoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaActionPerformed

    private void txtGananciaMinoristaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaKeyPressed

    private void txtGananciaMinoristaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaKeyReleased

    private void txtGananciaMinoristaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMinoristaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaMinoristaKeyTyped

    private void btnBuscarDependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDependenciaActionPerformed
        // TODO add your handling code here:
        dlgBuscarArticuloDependencia dep = new dlgBuscarArticuloDependencia(null, true);
        dep.setLocationRelativeTo(null);
        dep.setVisible(true);
    }//GEN-LAST:event_btnBuscarDependenciaActionPerformed

    private void ckHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckHabilitarActionPerformed
        // TODO add your handling code here:
        if (ckHabilitar.isSelected()) {
            txtCantM.setEnabled(true);
            //txtPrecioVentaMinorista.setEnabled(true);
            txtCantM.requestFocus();
        } else {
            txtCantM.setEnabled(false);
            txtCosto.requestFocus();
            //txtPrecioVentaMinorista.setEnabled(false);
            //txtCantM.setText("2");
            //txtPrecioVentaM.setText("0");
            //txtPrecioVentaML.setText("0");
        }
    }//GEN-LAST:event_ckHabilitarActionPerformed

    private void ckHabilitarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ckHabilitarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCantM.isEnabled()) {
                txtCosto.requestFocus();
            } else {
                txtCantM.requestFocus();
            }
        }
    }//GEN-LAST:event_ckHabilitarKeyPressed

    private void ckDependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckDependenciaActionPerformed
        // TODO add your handling code here:
        dependencia();
    }//GEN-LAST:event_ckDependenciaActionPerformed

    private void txtPorcPromocionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPromocionKeyPressed
        // TODO add your handling code here:
        if (!txtPorcPromocion.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPorcPromocion);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtPorcPromocionKeyPressed

    private void txtPorcPromocionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPromocionKeyReleased
        // TODO add your handling code here:
        try {
            if (txtPorcPromocion.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtPorcPromocion.setText(df.format(Integer.valueOf(txtPorcPromocion.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtPorcPromocion.setText("0");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtPorcPromocionKeyReleased

    private void txtPorcPromocionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPromocionKeyTyped
        // TODO add your handling code here:
        int limite = 10;
        if (txtPorcPromocion.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPorcPromocionKeyTyped

    private void txtPorcPromocionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPorcPromocionMouseClicked
        // TODO add your handling code here:
        txtPorcPromocion.selectAll();
    }//GEN-LAST:event_txtPorcPromocionMouseClicked

    private void ckPromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckPromocionActionPerformed
        // TODO add your handling code here:
        promocion();
    }//GEN-LAST:event_ckPromocionActionPerformed

    private void txtCantPromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantPromocionActionPerformed
        // TODO add your handling code here:
        txtPrecioPromocion.requestFocus();
    }//GEN-LAST:event_txtCantPromocionActionPerformed

    private void txtPrecioPromocionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecioPromocionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioPromocionMouseClicked

    private void txtPrecioPromocionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPromocionKeyPressed
        // TODO add your handling code here:
        if (!txtPrecioPromocion.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPrecioPromocion);
        }
    }//GEN-LAST:event_txtPrecioPromocionKeyPressed

    private void txtPrecioPromocionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPromocionKeyReleased
        // TODO add your handling code here:
        try {
            if (txtPrecioPromocion.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtPrecioPromocion.setText(df.format(Integer.valueOf(txtPrecioPromocion.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtPrecioPromocion.setText("0");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtPrecioPromocionKeyReleased

    private void txtPrecioPromocionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPromocionKeyTyped
        // TODO add your handling code here:
        int limite = 10;
        if (txtPrecioPromocion.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioPromocionKeyTyped

    private void txtPrecioPromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioPromocionActionPerformed
        // TODO add your handling code here:
        txtPorcPromocion.requestFocus();
    }//GEN-LAST:event_txtPrecioPromocionActionPerformed

    private void txtCantPromocionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantPromocionKeyPressed
        // TODO add your handling code here:
        if (!txtCantPromocion.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtCantPromocion);
        }
    }//GEN-LAST:event_txtCantPromocionKeyPressed

    public void Nuevo() {
        btnNuevoActionPerformed(null);
    }

    public static void limpiarCampos() {
        txtCodProducto.setText("");
        txtDescripcion.setText("");
        txtCodInterno.setText("");
        txtCodBarra.setText("");
        txtCosto.setText("0");
        txtGanancia.setText("0");
        txtPrecioVenta.setText("0");
        txtPrecioVentaL.setText("0");
        txtStock.setText("0");
        txtPrecioVentaMinorista.setText("0");
        txtCantM.setText("3");
        txtGananciaMinorista.setText("0");
        //txtStockMin.setText("0");
        txtCodClasificacion.setText("");
        txtCodImpuesto.setText("");
        txtCodUM.setText("");
        cboClasificacion.setSelectedIndex(0);
        cboUM.setSelectedIndex(0);
        cboImpuesto.setSelectedIndex(0);
    }

    void Volver() {
        CabecerasTablas.tablaArticuloAuxiliarCompra(dlgBuscarArticuloCompra1.tbDetalle);
        controlArticuloMovil.filtrarCodBarraActivo(dlgBuscarArticuloCompra1.tbDetalle, "");
        dlgBuscarArticuloCompra1.txtBuscar.setText("");
        dlgBuscarArticuloCompra1.txtBuscar.requestFocus();
        dlgBuscarArticuloCompra1.tbDetalle.clearSelection();
    }

    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                sentencia = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modcboClasificacion() {
        DefaultComboBoxModel ml = new DefaultComboBoxModel();
        String codClasificacion = txtCodClasificacion.getText().trim();
        try {
            rs = sentencia.executeQuery("SELECT * FROM division WHERE estado='S'");
            ml.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                ml.addElement(rs.getString("descripcion"));

            }
            ResultSet rss = sentencia.executeQuery("SELECT * FROM division WHERE iddivision=" + codClasificacion);
            rss.first();
            Object descripcion = (Object) rss.getString("descripcion");
            dlgGestArticulosMovil11.cboClasificacion.setModel(ml);
            dlgGestArticulosMovil11.cboClasificacion.setSelectedItem(descripcion);
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LOS LABORATORIOS: "+ew.getMessage().toUpperCase());
        }
    }

    public void modcboUM() {
        DefaultComboBoxModel pr = new DefaultComboBoxModel();
        String codUM = txtCodUM.getText().trim();
        try {
            rs = sentencia.executeQuery("SELECT * FROM unidad_medida WHERE estado='S'");
            pr.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                pr.addElement(rs.getString("unidadmedida"));
            }
            ResultSet rss = sentencia.executeQuery("SELECT * FROM unidad_medida WHERE idunidad=" + codUM);
            rss.first();
            Object descripcion = (Object) rss.getString("unidadmedida");
            dlgGestArticulosMovil11.cboUM.setModel(pr);
            dlgGestArticulosMovil11.cboUM.setSelectedItem(descripcion);
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LOS PROVEEDORES: "+ew.getMessage().toUpperCase());
        }
    }

    public void modcboImpuesto() {
        DefaultComboBoxModel fm = new DefaultComboBoxModel();
        String codImpuesto = txtCodImpuesto.getText().trim();
        try {
            rs = sentencia.executeQuery("SELECT * FROM iva WHERE estado='S'");
            fm.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                fm.addElement(rs.getString("descripcion"));
            }
            ResultSet rss = sentencia.executeQuery("SELECT * FROM iva WHERE idiva=" + codImpuesto);
            rss.first();
            Object descripcion = (Object) rss.getString("descripcion");
            dlgGestArticulosMovil11.cboImpuesto.setModel(fm);
            dlgGestArticulosMovil11.cboImpuesto.setSelectedItem(descripcion);
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LAS FAMILIAS: "+ew.getMessage().toUpperCase());
        }
    }

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
            java.util.logging.Logger.getLogger(dlgGestArticulosMovil11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            dlgGestArticulosMovil11 dialog = new dlgGestArticulosMovil11(new javax.swing.JFrame(), true);
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
    public static javax.swing.JButton btnBuscarDependencia;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnFamilia;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnLaboratorio;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnProveedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel c10;
    private javax.swing.JLabel c11;
    private javax.swing.JLabel c12;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c7;
    private javax.swing.JLabel c8;
    private javax.swing.JLabel c9;
    public static javax.swing.JComboBox<String> cboClasificacion;
    public static javax.swing.JComboBox<String> cboImpuesto;
    public static javax.swing.JComboBox<String> cboUM;
    public static rojerusan.RSCheckBox ckDependencia;
    public static rojerusan.RSCheckBox ckHabilitar;
    public static rojerusan.RSCheckBox ckPromocion;
    public static javax.swing.JMenuItem itemCancelar;
    public static javax.swing.JMenuItem itemGuardar;
    public static javax.swing.JMenuItem itemNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JMenu menuOpciones;
    public static javax.swing.JTextField txtCantM;
    public static javax.swing.JTextField txtCantPromocion;
    public static javax.swing.JTextField txtCodBarra;
    public static javax.swing.JTextField txtCodClasificacion;
    public static javax.swing.JTextField txtCodImpuesto;
    public static javax.swing.JTextField txtCodInterno;
    public static javax.swing.JTextField txtCodProducto;
    public static javax.swing.JTextField txtCodUM;
    public static javax.swing.JTextField txtCosto;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtGanancia;
    public static javax.swing.JTextField txtGananciaMinorista;
    public static javax.swing.JTextField txtPorcPromocion;
    public static javax.swing.JTextField txtPrecioCostoL;
    public static javax.swing.JTextField txtPrecioPromocion;
    public static javax.swing.JTextField txtPrecioVenta;
    public static javax.swing.JTextField txtPrecioVentaL;
    public static javax.swing.JTextField txtPrecioVentaMinorista;
    public static javax.swing.JTextField txtStock;
    public static javax.swing.JTextField txtidDependencia;
    // End of variables declaration//GEN-END:variables
}
