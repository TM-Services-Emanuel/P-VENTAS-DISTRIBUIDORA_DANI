package IU;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.cargarComboBoxMovil;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlPuntoEmisionMovil;
import Controladores.controlTimbradoMovil;
import Datos.GestionarPuntoEmisionMovil;
import Datos.GestionarTimbradoMovil;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class dlgPuntoEmisionMovil extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    public MariaDbStatement sentencia;
    public MariaDbConnection con;
    private ResultSet rs;

    public dlgPuntoEmisionMovil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepararBD();
        cargarComboBoxMovil.cargar(cboTimbrado, "SELECT * FROM timbrado WHERE estado='Activo'");
        titulo();
        cabe.PuntoEmision(tbPuntoEmision);
        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
        Estados();
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestionar Punto de Emisión");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestionar Punto de Emisión");
        }
    }

    private void prepararBD() {
        {
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
    }

    private void Estados() {
        if (rbActivo.isSelected()) {
            etiEstado.setText("ACTIVO");
        } else {
            etiEstado.setText("INACTIVO");
        }
    }

    public void Cancelar() {
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
        btnEliminar.setEnabled(false);
        itemEliminar.setEnabled(false);
        tbPuntoEmision.setVisible(true);
        //
        cboTimbrado.setEnabled(false);
        txtEstablecimiento.setEnabled(false);
        txtEmision.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtFInicio.setEnabled(false);
        txtFFin.setEnabled(false);
        txtFActual.setEnabled(false);
        txtNVenta.setEnabled(false);
        cbAMovil.setEnabled(false);
        rbActivo.setEnabled(false);
        rbInactivo.setEnabled(false);
        cboTipo2.setEnabled(false);
        limpiarCampos();
        btnNuevo.requestFocus();
    }
    
    public void modcboTimbrado(String idTimbrado) {
        DefaultComboBoxModel ml = new DefaultComboBoxModel();
        try {
            rs = sentencia.executeQuery("SELECT * FROM timbrado WHERE estado='Activo'");
            ml.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                ml.addElement(rs.getString("descripcion"));

            }
            ResultSet rss = sentencia.executeQuery("SELECT * FROM timbrado WHERE idtimbrado="+idTimbrado);
            rss.first();
            Object descripcion = (Object) rss.getString("descripcion");
            cboTimbrado.setModel(ml);
            cboTimbrado.setSelectedItem(descripcion);
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LOS LABORATORIOS: "+ew.getMessage().toUpperCase());
        }
        
        try {
                try {
                    rs = sentencia.executeQuery("select * from timbrado where idtimbrado=" + idTimbrado);
                    rs.first();
                    try {
                        if (rs.getRow() != 0) {
                            lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                            txtEstablecimiento.requestFocus();
                        } else {
                            System.out.println("No se puede cargar Fecha timbrado.");
                        }
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } catch (Exception e) {
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupActivo = new javax.swing.ButtonGroup();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboTimbrado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtEstablecimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmision = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFInicio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFFin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFActual = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNVenta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cbAMovil = new javax.swing.JCheckBox();
        txtIP = new javax.swing.JTextField();
        rbActivo = new javax.swing.JRadioButton();
        rbInactivo = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        etiEstado = new javax.swing.JLabel();
        cboTipo2 = new javax.swing.JComboBox<>();
        lbFechaTimbrado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPuntoEmision = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        barMenu = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemModificar = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        itemEliminar = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 9));

        btnNuevo.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document30.png"))); // NOI18N
        btnNuevo.setText("Nuevo-F1");
        btnNuevo.setToolTipText("Nuevo - F3");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);

        btnModificar.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/edit30.png"))); // NOI18N
        btnModificar.setText("Modif-F5");
        btnModificar.setToolTipText("Modificar - F5");
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnGuardar.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.setText("Guardar-F6");
        btnGuardar.setToolTipText("Guardar - F6");
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel30.png"))); // NOI18N
        btnCancelar.setText("Cancel-Esc");
        btnCancelar.setToolTipText("Cancelar - Esc");
        btnCancelar.setEnabled(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        btnEliminar.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/garbage30.png"))); // NOI18N
        btnEliminar.setText("Elim-Supr");
        btnEliminar.setToolTipText("Eliminar - Supr");
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

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
        jPanel1.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);

        jLabel3.setText("ID");

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Timbrado");

        cboTimbrado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboTimbrado.setEnabled(false);
        cboTimbrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimbradoActionPerformed(evt);
            }
        });
        cboTimbrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTimbradoKeyPressed(evt);
            }
        });

        jLabel6.setText("Establecimiento");

        txtEstablecimiento.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtEstablecimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento.setEnabled(false);
        txtEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstablecimientoActionPerformed(evt);
            }
        });
        txtEstablecimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstablecimientoKeyPressed(evt);
            }
        });

        jLabel7.setText("P. Emisión");

        txtEmision.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmision.setEnabled(false);
        txtEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmisionActionPerformed(evt);
            }
        });
        txtEmision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmisionKeyPressed(evt);
            }
        });

        jLabel8.setText("Dirección");

        txtDireccion.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtDireccion.setEnabled(false);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel9.setText("Factura Inicio");

        txtFInicio.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFInicio.setEnabled(false);
        txtFInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFInicioActionPerformed(evt);
            }
        });
        txtFInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFInicioKeyPressed(evt);
            }
        });

        jLabel10.setText("Factura Fin");

        txtFFin.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFFin.setEnabled(false);
        txtFFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFFinActionPerformed(evt);
            }
        });
        txtFFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFFinKeyPressed(evt);
            }
        });

        jLabel11.setText("Factura Actual");

        txtFActual.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFActual.setEnabled(false);
        txtFActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFActualActionPerformed(evt);
            }
        });
        txtFActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFActualKeyPressed(evt);
            }
        });

        jLabel12.setText("N° Venta");

        txtNVenta.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtNVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNVenta.setEnabled(false);
        txtNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNVentaActionPerformed(evt);
            }
        });
        txtNVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNVentaKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 35, 46)));
        jPanel3.setOpaque(false);

        cbAMovil.setSelected(true);
        cbAMovil.setText("Aplicación Móvil");
        cbAMovil.setEnabled(false);
        cbAMovil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cbAMovil.setIconTextGap(6);
        cbAMovil.setOpaque(false);
        cbAMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAMovilActionPerformed(evt);
            }
        });

        txtIP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setEnabled(false);
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIPKeyPressed(evt);
            }
        });

        grupActivo.add(rbActivo);
        rbActivo.setText("P. Emisión Activo");
        rbActivo.setEnabled(false);
        rbActivo.setIconTextGap(6);
        rbActivo.setOpaque(false);
        rbActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbActivoActionPerformed(evt);
            }
        });

        grupActivo.add(rbInactivo);
        rbInactivo.setSelected(true);
        rbInactivo.setText("P. Emisión Inactivo");
        rbInactivo.setEnabled(false);
        rbInactivo.setIconTextGap(6);
        rbInactivo.setOpaque(false);
        rbInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInactivoActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("IP");

        etiEstado.setBackground(new java.awt.Color(17, 35, 46));
        etiEstado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        etiEstado.setForeground(new java.awt.Color(255, 255, 255));
        etiEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiEstado.setOpaque(true);

        cboTipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TICKET", "FACTURA LEGAL" }));
        cboTipo2.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(rbActivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbInactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addComponent(etiEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbAMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAMovil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIP)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbInactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbFechaTimbrado.setBackground(new java.awt.Color(17, 35, 46));
        lbFechaTimbrado.setForeground(new java.awt.Color(255, 255, 255));
        lbFechaTimbrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaTimbrado.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtFFin))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtFActual, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFechaTimbrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cboTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFechaTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtFActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbPuntoEmision.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tbPuntoEmision.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPuntoEmision.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPuntoEmision.getTableHeader().setResizingAllowed(false);
        tbPuntoEmision.getTableHeader().setReorderingAllowed(false);
        tbPuntoEmision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPuntoEmisionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbPuntoEmisionMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbPuntoEmision);

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        barMenu.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        menuOpciones.setText("Opciones");
        menuOpciones.setFocusable(false);
        menuOpciones.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        menuOpciones.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        itemNuevo.setText("Nuevo");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        menuOpciones.add(itemNuevo);

        itemModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemModificar.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
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
        itemGuardar.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
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
        itemCancelar.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel15.png"))); // NOI18N
        itemCancelar.setText("Cancelar");
        itemCancelar.setEnabled(false);
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemCancelar);

        itemEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemEliminar.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        itemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/garbage15.png"))); // NOI18N
        itemEliminar.setText("Eliminar");
        itemEliminar.setEnabled(false);
        itemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemEliminar);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
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
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        String cod = GestionarPuntoEmisionMovil.getCodigo();
        cargarComboBoxMovil.cargar(cboTimbrado, "SELECT * FROM timbrado WHERE estado='Activo'");
        txtCod.setText(cod);
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        itemSalir.setEnabled(false);
        btnEliminar.setEnabled(false);
        itemEliminar.setEnabled(false);
        tbPuntoEmision.setVisible(false);
        //
        cboTimbrado.setEnabled(true);
        txtEstablecimiento.setEnabled(true);
        txtEmision.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtFInicio.setEnabled(true);
        txtFFin.setEnabled(true);
        txtFActual.setEnabled(true);
        txtNVenta.setEnabled(true);
        cbAMovil.setEnabled(true);
        rbActivo.setEnabled(true);
        rbInactivo.setEnabled(true);
        cboTipo2.setEnabled(true);
        //
        CabecerasTablas.limpiarTablas(tbPuntoEmision);
        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
        cboTimbrado.requestFocus();
        cboTimbrado.setPopupVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                controlPuntoEmisionMovil.delPuntoEmision();
                CabecerasTablas.limpiarTablas(tbPuntoEmision);
                controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                Cancelar();
            }
        } catch (HeadlessException ee) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        try {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                controlPuntoEmisionMovil.actPuntoEmision();
                controlPuntoEmisionMovil.actRef();
                CabecerasTablas.limpiarTablas(tbPuntoEmision);
                controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                Cancelar();
            }
        } catch (HeadlessException ee) {
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (cboTimbrado.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un Timbrado");
            cboTimbrado.requestFocus();
            cboTimbrado.setPopupVisible(true);
        } else if (txtEstablecimiento.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Establecimiento");
            txtEstablecimiento.requestFocus();
        } else if (txtEmision.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Punto de Emisión");
            txtEmision.requestFocus();
        } else if (txtDireccion.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese la Dirección");
            txtDireccion.requestFocus();
        } else if (txtFInicio.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para el inicio de las facturaciones");
            txtFInicio.requestFocus();
        } else if (txtFFin.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para la finalización de las facturaciones");
            txtFFin.requestFocus();
        } else if (txtFActual.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número actual de la facturación");
            txtFActual.requestFocus();
        } else if (txtNVenta.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número de venta actual - Infomación requerido para la Aplicación Fact-Express");
            txtNVenta.requestFocus();
        } else if (!cbAMovil.isSelected()) {
            if (txtIP.getText().trim().isEmpty()) {
                Mensajes.informacion("Ingrese la dirección IP de la terminar que utilizara este Punto de Emisión");
                txtIP.requestFocus();
            }
            else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    String cod = GestionarPuntoEmisionMovil.getCodigo();
                    txtCod.setText(cod);
                    controlPuntoEmisionMovil.addPuntoEmision();
                    controlPuntoEmisionMovil.addRef();
                    CabecerasTablas.limpiarTablas(tbPuntoEmision);
                    controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                    Cancelar();
                }
            } catch (HeadlessException ee) {
            }
        }
        }else{
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    String cod = GestionarPuntoEmisionMovil.getCodigo();
                    txtCod.setText(cod);
                    controlPuntoEmisionMovil.addPuntoEmision();
                    controlPuntoEmisionMovil.addRef();
                    CabecerasTablas.limpiarTablas(tbPuntoEmision);
                    controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                    Cancelar();
                }
            } catch (HeadlessException ee) {
            }
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbPuntoEmisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPuntoEmisionMouseClicked
        // TODO add your handling code here:
        try {
            btnNuevo.setEnabled(false);
            itemNuevo.setEnabled(false);
            btnModificar.setEnabled(true);
            itemModificar.setEnabled(true);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(true);
            itemCancelar.setEnabled(true);
            btnSalir.setEnabled(false);
            itemSalir.setEnabled(false);
            btnEliminar.setEnabled(true);
            itemEliminar.setEnabled(true);
            tbPuntoEmision.setVisible(true);
            //
            cboTimbrado.setEnabled(false);
            txtEstablecimiento.setEnabled(true);
            txtEmision.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtFInicio.setEnabled(true);
            txtFFin.setEnabled(true);
            txtFActual.setEnabled(true);
            txtNVenta.setEnabled(true);
            cbAMovil.setEnabled(true);
            rbActivo.setEnabled(true);
            rbInactivo.setEnabled(true);
            cboTipo2.setEnabled(true);

            int fila = tbPuntoEmision.getSelectedRow();
            String cod = tbPuntoEmision.getValueAt(fila, 0).toString();
            String itTimbrado = tbPuntoEmision.getValueAt(fila, 1).toString();
            String establecimiento = tbPuntoEmision.getValueAt(fila, 3).toString();
            String puntoEmision = tbPuntoEmision.getValueAt(fila, 4).toString();
            String direccion = tbPuntoEmision.getValueAt(fila, 5).toString();
            String fi = tbPuntoEmision.getValueAt(fila, 6).toString();
            String ff = tbPuntoEmision.getValueAt(fila, 7).toString();
            String fa = tbPuntoEmision.getValueAt(fila, 8).toString();
            String nv = tbPuntoEmision.getValueAt(fila, 9).toString();
            String tipo = tbPuntoEmision.getValueAt(fila, 10).toString();
            String tipo2 = tbPuntoEmision.getValueAt(fila, 11).toString();
            String ip = tbPuntoEmision.getValueAt(fila, 12).toString();
            String estado = tbPuntoEmision.getValueAt(fila, 13).toString();

            txtCod.setText(cod);
            modcboTimbrado(itTimbrado);
            txtEstablecimiento.setText(establecimiento);
            txtEmision.setText(puntoEmision);
            txtDireccion.setText(direccion);
            txtFInicio.setText(fi);
            txtFFin.setText(ff);
            txtFActual.setText(fa);
            txtNVenta.setText(nv);
            if (tipo.equals("M")) {
                cbAMovil.setSelected(true);
                cbAMovilActionPerformed(null);
            } else {
                cbAMovil.setSelected(false);
                txtIP.setEnabled(true);
            }
            txtIP.setText(ip);
            if (tipo2.equals("T")) {
                cboTipo2.setSelectedIndex(0);
            } else if (tipo2.equals("F")) {
                cboTipo2.setSelectedIndex(1);
            }
            if (estado.equals("Inactivo")) {
                rbInactivo.setSelected(true);
            } else {
                rbActivo.setSelected(true);
            }
            txtEstablecimiento.requestFocus();
        } catch (Exception e) {}
        
    }//GEN-LAST:event_tbPuntoEmisionMouseClicked

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarActionPerformed
        // TODO add your handling code here:
        btnModificarActionPerformed(null);
    }//GEN-LAST:event_itemModificarActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void itemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarActionPerformed
        // TODO add your handling code here:
        btnEliminarActionPerformed(null);
    }//GEN-LAST:event_itemEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar?");
        if (rpta == 0) {
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
            btnEliminar.setEnabled(false);
            itemEliminar.setEnabled(false);
            tbPuntoEmision.setVisible(true);
            //
            cboTimbrado.setEnabled(false);
            txtEstablecimiento.setEnabled(false);
            txtEmision.setEnabled(false);
            txtDireccion.setEnabled(false);
            txtFInicio.setEnabled(false);
            txtFFin.setEnabled(false);
            txtFActual.setEnabled(false);
            txtNVenta.setEnabled(false);
            cbAMovil.setEnabled(false);
            rbActivo.setEnabled(false);
            rbInactivo.setEnabled(false);
            cboTipo2.setEnabled(false);
            limpiarCampos();
            btnNuevo.requestFocus();
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalir.doClick();
    }//GEN-LAST:event_itemSalirActionPerformed

    private void txtFInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFInicioActionPerformed
        // TODO add your handling code here:
        txtFFin.requestFocus();
    }//GEN-LAST:event_txtFInicioActionPerformed

    private void txtEmisionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmisionKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEmision);
        validarCampos.cantCaracteres(txtEmision, 3);
    }//GEN-LAST:event_txtEmisionKeyPressed

    private void txtEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmisionActionPerformed
        // TODO add your handling code here:
        txtDireccion.requestFocus();
    }//GEN-LAST:event_txtEmisionActionPerformed

    private void txtEstablecimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstablecimientoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEstablecimiento);
        validarCampos.cantCaracteres(txtEstablecimiento, 3);
    }//GEN-LAST:event_txtEstablecimientoKeyPressed

    private void txtEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstablecimientoActionPerformed
        // TODO add your handling code here:
        txtEmision.requestFocus();
    }//GEN-LAST:event_txtEstablecimientoActionPerformed

    private void cboTimbradoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTimbradoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cboTimbrado.getSelectedIndex() == 0) {
            lbFechaTimbrado.setText("");
        } else {
            try {
                int idT;
                idT = Integer.parseInt(cargarComboBoxMovil.getCodidgo(cboTimbrado));
                try {
                    rs = sentencia.executeQuery("select * from timbrado where idtimbrado=" + idT);
                    rs.first();
                    try {
                        if (rs.getRow() != 0) {
                            lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                            txtEstablecimiento.requestFocus();
                        } else {
                            System.out.println("No se puede cargar Fecha timbrado.");
                        }
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } catch (Exception e) {
            }
        txtEstablecimiento.requestFocus();
        }
            
        }
    }//GEN-LAST:event_cboTimbradoKeyPressed

    private void cboTimbradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimbradoActionPerformed
        // TODO add your handling code here:
        /*if (cboTimbrado.getSelectedIndex() == 0) {
            lbFechaTimbrado.setText("");
        } else {
            try {
                int idT;
                idT = Integer.parseInt(cargarComboBoxMovil.getCodidgo(cboTimbrado));
                try {
                    rs = sentencia.executeQuery("select * from timbrado where idtimbrado=" + idT);
                    rs.first();
                    try {
                        if (rs.getRow() != 0) {
                            lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                            txtEstablecimiento.requestFocus();
                        } else {
                            System.out.println("No se puede cargar Fecha timbrado.");
                        }
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } catch (Exception e) {
            }
        }*/
    }//GEN-LAST:event_cboTimbradoActionPerformed

    private void rbInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInactivoActionPerformed
        // TODO add your handling code here:
        Estados();
    }//GEN-LAST:event_rbInactivoActionPerformed

    private void rbActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbActivoActionPerformed
        // TODO add your handling code here:
        Estados();
    }//GEN-LAST:event_rbActivoActionPerformed

    private void cbAMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAMovilActionPerformed
        // TODO add your handling code here:
        if (cbAMovil.isSelected()) {
            txtIP.setEnabled(false);
        } else {
            txtIP.setEnabled(true);
            txtIP.requestFocus();
            rbActivo.doClick();
        }
    }//GEN-LAST:event_cbAMovilActionPerformed

    private void txtIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtIP);
        validarCampos.cantCaracteres(txtIP, 16);
    }//GEN-LAST:event_txtIPKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        // TODO add your handling code here:
        validarCampos.cantCaracteres(txtDireccion, 20);
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
        txtFInicio.requestFocus();
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtFInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFInicioKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFInicio);
        validarCampos.cantCaracteres(txtFInicio, 3);
    }//GEN-LAST:event_txtFInicioKeyPressed

    private void txtFFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFFinActionPerformed
        // TODO add your handling code here:
        txtFActual.requestFocus();
    }//GEN-LAST:event_txtFFinActionPerformed

    private void txtFFinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFFinKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFFin);
        validarCampos.cantCaracteres(txtFFin, 6);
    }//GEN-LAST:event_txtFFinKeyPressed

    private void txtFActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFActualActionPerformed
        // TODO add your handling code here:
        txtNVenta.requestFocus();
    }//GEN-LAST:event_txtFActualActionPerformed

    private void txtFActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFActualKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFActual);
        validarCampos.cantCaracteres(txtFActual, 6);
    }//GEN-LAST:event_txtFActualKeyPressed

    private void txtNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNVentaActionPerformed
        // TODO add your handling code here:
        cbAMovil.requestFocus();
    }//GEN-LAST:event_txtNVentaActionPerformed

    private void txtNVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNVentaKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtNVenta);
        validarCampos.cantCaracteres(txtNVenta, 6);
    }//GEN-LAST:event_txtNVentaKeyPressed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void tbPuntoEmisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPuntoEmisionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPuntoEmisionMouseEntered

    void limpiarCampos() {
        txtCod.setText("");
        cboTimbrado.setSelectedIndex(0);
        lbFechaTimbrado.setText("");
        txtEstablecimiento.setText("");
        txtEmision.setText("");
        txtDireccion.setText("");
        txtFInicio.setText("");
        txtFFin.setText("");
        txtFActual.setText("");
        txtNVenta.setText("");
        txtIP.setText("");
        cbAMovil.setSelected(true);
        rbInactivo.setSelected(true);
        tbPuntoEmision.clearSelection();
        cboTipo2.setSelectedIndex(0);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(dlgPuntoEmisionMovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(() -> {
            dlgPuntoEmisionMovil dialog = new dlgPuntoEmisionMovil(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JCheckBox cbAMovil;
    public static javax.swing.JComboBox<String> cboTimbrado;
    public static javax.swing.JComboBox<String> cboTipo2;
    private javax.swing.JLabel etiEstado;
    private javax.swing.ButtonGroup grupActivo;
    private javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemModificar;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbFechaTimbrado;
    private javax.swing.JMenu menuOpciones;
    public static javax.swing.JRadioButton rbActivo;
    public static javax.swing.JRadioButton rbInactivo;
    private javax.swing.JTable tbPuntoEmision;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtEmision;
    public static javax.swing.JTextField txtEstablecimiento;
    public static javax.swing.JTextField txtFActual;
    public static javax.swing.JTextField txtFFin;
    public static javax.swing.JTextField txtFInicio;
    public static javax.swing.JTextField txtIP;
    public static javax.swing.JTextField txtNVenta;
    // End of variables declaration//GEN-END:variables
}
