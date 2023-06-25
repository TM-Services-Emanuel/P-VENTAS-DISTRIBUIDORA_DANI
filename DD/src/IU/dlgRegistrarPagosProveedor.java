package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimal2;
import Componentes.RenderPagos;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlArticuloMovil;
import Controladores.controlCompra;
//import Controladores.controlCompra;
import Datos.GestionarCompra;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public final class dlgRegistrarPagosProveedor extends javax.swing.JDialog {

    public static MariaDbConnection con;
    public static MariaDbStatement stTransaccion;
    public Reporte jasper;

    public dlgRegistrarPagosProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        jasper = new Reporte();
        CabecerasTablas.Pagos(tbDetalle);
        Cancelar();
        Invisible();

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Registrar pagos a proveedores");
        } else {
            this.setTitle(Software.getSoftware() + " - Registrar pagos a proveedores");
        }
    }

    public void Cancelar() {
        limpiarCampos();
        btnBuscarFacturas.setEnabled(false);
        dcFDesde.setEnabled(false);
        dcFHasta.setEnabled(false);
        btnNuevo.setEnabled(true);
        itemNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        itemQuitar.setEnabled(false);
        cbProveedores.setEnabled(false);
        btnSalir.setEnabled(true);
        cant();
    }

    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                stTransaccion = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Renders() {
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(2).setCellRenderer(new RenderDecimal2());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(3).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(4).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(6).setCellRenderer(new RenderPagos());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal1());
    }

    public void Invisible() {
        btnAdd.setVisible(false);
        btnRestar.setVisible(false);
        txtCodProveedor.setVisible(false);
        txtDesde.setVisible(false);
        txtHasta.setVisible(false);
        lbmonto.setVisible(false);
        lbmonto1.setVisible(false);
    }

    public static void cant() {
        int total = tbDetalle.getRowCount();
        etiCant.setText("Cantidad de Facturas anexadas para registro de pagos: " + String.valueOf(total));
    }

    private void limpiarCampos() {
        txtCod.setText("");
        txtHasta.setText("");
        txtCaja.setText("");
        txtCodProveedor.setText("");
        txtFechaFachada.setText("");
        txtTotal.setText("");
        controlCompra.arrayPago.vaciar();
        CabecerasTablas.Pagos(tbDetalle);
        CabecerasTablas.limpiarTablas(tbDetalle);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");

    }

    private void CambiarMontoAbonar() {
        if (tbDetalle.getSelectedRowCount() != 0) {
            try {
                int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
                String factura = (dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 1).toString());
                int monto_factura = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 2).toString().replace(".", "").replace(",", ""));
                int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 3).toString().replace(".", "").replace(",", ""));
                int monto_abonar = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 4).toString().replace(".", "").replace(",", ""));
                int saldo = monto_saldo - monto_abonar;
                DecimalFormat df = new DecimalFormat("#,###");
                lbFactura.setText("Factura Nro: " + factura);
                lbMonto_Factura.setText("Monto de la compra: " + df.format(monto_factura));
                lbMonto_abonar.setText("Monto del saldo a abonar: " + df.format(monto_saldo));
                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
                lbmonto.setText(String.valueOf(monto_saldo));
                txtAbono.setText("0");
                DialogCambioPago.setSize(295, 185);
                DialogCambioPago.setLocationRelativeTo(this);
                DialogCambioPago.setModal(true);
                //DialogCambioPago.setTStitle("OPCIONES");
                DialogCambioPago.setVisible(true);
                txtAbono.requestFocus();
            } catch (Exception e) {
            }
        }
    }

    private void AnexarNC() {
        if (tbDetalle.getSelectedRowCount() != 0) {
            try {
                //controlCompra.actMontoPago(tbDetalle);
                int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
                String factura = (dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 1).toString());
                int monto_factura = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 2).toString().replace(".", "").replace(",", ""));
                int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 7).toString().replace(".", "").replace(",", ""));
                int monto_abonar = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 4).toString().replace(".", "").replace(",", ""));
                int saldo = monto_saldo - monto_abonar;
                DecimalFormat df = new DecimalFormat("#,###");
                lbFactura1.setText("Factura Nro: " + factura);
                lbMonto_Factura1.setText("Monto de la compra: " + df.format(monto_factura));
                lbmonto1.setText(String.valueOf(monto_saldo));
                txtNC.setText("-");
                txtAbono1.setText("0");
                DialogAgregarNC.setSize(295, 201);
                DialogAgregarNC.setLocationRelativeTo(this);
                DialogAgregarNC.setModal(true);
                //DialogCambioPago.setTStitle("OPCIONES");
                DialogAgregarNC.setVisible(true);
                txtNC.requestFocus();

            } catch (Exception e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuEmergente = new javax.swing.JPopupMenu();
        itemAnexarNC = new javax.swing.JMenuItem();
        itemMonto_a_abonar = new javax.swing.JMenuItem();
        DialogCambioPago = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelarModificacionPago = new javax.swing.JButton();
        lbFactura = new javax.swing.JLabel();
        lbMonto_abonar = new javax.swing.JLabel();
        lbMonto_Factura = new javax.swing.JLabel();
        lbSaldo_final = new javax.swing.JLabel();
        lbmonto = new javax.swing.JLabel();
        DialogAgregarNC = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAbono1 = new javax.swing.JTextField();
        btnAceptar1 = new javax.swing.JButton();
        btnCancelarModificacionPago1 = new javax.swing.JButton();
        lbFactura1 = new javax.swing.JLabel();
        lbMonto_Factura1 = new javax.swing.JLabel();
        lbmonto1 = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        txtFechaFachada = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtReciboD = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        etiCant = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txtHasta = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnNuevo = new newscomponents.RSButtonBigIcon_new();
        btnGuardar = new newscomponents.RSButtonBigIcon_new();
        btnCancelar = new newscomponents.RSButtonBigIcon_new();
        btnSalir = new newscomponents.RSButtonBigIcon_new();
        txtCodProveedor = new javax.swing.JTextField();
        btnRestar = new javax.swing.JButton();
        txtDesde = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbProveedores = new RSMaterialComponent.RSComboBoxMaterial();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        btnBuscarFacturas = new newscomponents.RSButtonGradientIcon_new();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        menuEmergente.setBackground(new java.awt.Color(255, 255, 255));
        menuEmergente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        itemAnexarNC.setBackground(new java.awt.Color(255, 255, 255));
        itemAnexarNC.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        itemAnexarNC.setForeground(new java.awt.Color(255, 0, 0));
        itemAnexarNC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_edit_black_36.png"))); // NOI18N
        itemAnexarNC.setText("1- ANEXAR NOTA DE CRÉDITO A ESTA FACTURA");
        itemAnexarNC.setOpaque(true);
        itemAnexarNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAnexarNCActionPerformed(evt);
            }
        });
        menuEmergente.add(itemAnexarNC);

        itemMonto_a_abonar.setBackground(new java.awt.Color(255, 255, 255));
        itemMonto_a_abonar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        itemMonto_a_abonar.setForeground(new java.awt.Color(0, 102, 51));
        itemMonto_a_abonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_edit_black_36.png"))); // NOI18N
        itemMonto_a_abonar.setText("2- MODIFICAR EL MONTO A ABONAR DE ESTA FACTURA");
        itemMonto_a_abonar.setOpaque(true);
        itemMonto_a_abonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMonto_a_abonarActionPerformed(evt);
            }
        });
        menuEmergente.add(itemMonto_a_abonar);

        DialogCambioPago.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CAMBIO DEL MONTO A ABONAR");

        txtAbono.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbono.setText("0");
        txtAbono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoActionPerformed(evt);
            }
        });
        txtAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoKeyReleased(evt);
            }
        });

        btnAceptar.setText("ACEPTAR CAMBIO");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelarModificacionPago.setText("CANCELAR");
        btnCancelarModificacionPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModificacionPagoActionPerformed(evt);
            }
        });

        lbFactura.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbFactura.setText("Factura Nro:");

        lbMonto_abonar.setText("Monto actual a abonar:");

        lbMonto_Factura.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbMonto_Factura.setText("Monto de la Factura:");

        lbSaldo_final.setText("Saldo final:");

        lbmonto.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMonto_abonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lbmonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelarModificacionPago))
                    .addComponent(txtAbono)
                    .addComponent(lbMonto_Factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSaldo_final, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMonto_Factura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMonto_abonar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSaldo_final)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(lbmonto))
                    .addComponent(btnCancelarModificacionPago))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogCambioPagoLayout = new javax.swing.GroupLayout(DialogCambioPago.getContentPane());
        DialogCambioPago.getContentPane().setLayout(DialogCambioPagoLayout);
        DialogCambioPagoLayout.setHorizontalGroup(
            DialogCambioPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogCambioPagoLayout.setVerticalGroup(
            DialogCambioPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogCambioPagoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        DialogAgregarNC.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ANEXAR NOTA DE CRÉDITO A LA FACTURA");

        txtAbono1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtAbono1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbono1.setText("0");
        txtAbono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAbono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbono1ActionPerformed(evt);
            }
        });
        txtAbono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbono1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbono1KeyReleased(evt);
            }
        });

        btnAceptar1.setText("ACEPTAR ANEXIÓN");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        btnCancelarModificacionPago1.setText("CANCELAR");
        btnCancelarModificacionPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModificacionPago1ActionPerformed(evt);
            }
        });

        lbFactura1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbFactura1.setText("Factura Nro:");

        lbMonto_Factura1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbMonto_Factura1.setText("Monto de la Factura:");

        lbmonto1.setText("0");

        txtNC.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtNC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNC.setText("-");
        txtNC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCActionPerformed(evt);
            }
        });
        txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNCKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lbmonto1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar1)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelarModificacionPago1))
                    .addComponent(txtAbono1)
                    .addComponent(lbMonto_Factura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFactura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNC, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFactura1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMonto_Factura1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAbono1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar1)
                        .addComponent(lbmonto1))
                    .addComponent(btnCancelarModificacionPago1))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogAgregarNCLayout = new javax.swing.GroupLayout(DialogAgregarNC.getContentPane());
        DialogAgregarNC.getContentPane().setLayout(DialogAgregarNCLayout);
        DialogAgregarNCLayout.setHorizontalGroup(
            DialogAgregarNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogAgregarNCLayout.setVerticalGroup(
            DialogAgregarNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel3.setLayout(new java.awt.GridLayout(1, 4));

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        jLabel1.setText("Operación N°");

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha");

        jLabel12.setText("Mov.N°");

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaActionPerformed(evt);
            }
        });
        txtCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCajaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaKeyTyped(evt);
            }
        });

        txtFechaFachada.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtFechaFachada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaFachada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFachadaActionPerformed(evt);
            }
        });

        jLabel14.setText("Recibo de Dinero Nro");

        txtReciboD.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtReciboD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReciboD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReciboDKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFachada, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReciboD, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtReciboD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtFechaFachada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setOpaque(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setRowHeaderView(null);

        tbDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
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
        tbDetalle.setRowHeight(25);
        tbDetalle.setRowMargin(0);
        tbDetalle.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbDetalle.setShowGrid(true);
        tbDetalle.setShowVerticalLines(false);
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
        jScrollPane1.setViewportView(tbDetalle);

        etiCant.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        etiCant.setText("Cantidad de facturas anexadas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(etiCant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(463, 463, 463))
                    .addComponent(jScrollPane1))
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiCant)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 102, 102));
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel9.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 370, 30));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel9.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, 23));

        txtHasta.setEditable(false);
        txtHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtHasta.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHastaActionPerformed(evt);
            }
        });
        jPanel9.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 70, -1));

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(1, 6));

        btnNuevo.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevo.setBorder(null);
        btnNuevo.setText("NUEVO");
        btnNuevo.setBgHover(new java.awt.Color(1, 123, 123));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnNuevo.setIconTextGap(0);
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        btnNuevo.setPixels(0);
        btnNuevo.setSizeIcon(40.0F);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel10.add(btnNuevo);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setBorder(null);
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBgHover(new java.awt.Color(1, 123, 123));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnGuardar.setIconTextGap(0);
        btnGuardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar.setPixels(0);
        btnGuardar.setSizeIcon(40.0F);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel10.add(btnGuardar);

        btnCancelar.setBackground(new java.awt.Color(0, 102, 102));
        btnCancelar.setBorder(null);
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBgHover(new java.awt.Color(1, 123, 123));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnCancelar.setIconTextGap(0);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setPixels(0);
        btnCancelar.setSizeIcon(40.0F);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel10.add(btnCancelar);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setText("SALIR");
        btnSalir.setBgHover(new java.awt.Color(1, 123, 123));
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(40.0F);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel10.add(btnSalir);

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 60));

        txtCodProveedor.setEditable(false);
        txtCodProveedor.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        txtCodProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(txtCodProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 50, -1));

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });
        jPanel9.add(btnRestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 23));

        txtDesde.setEditable(false);
        txtDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtDesde.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesdeActionPerformed(evt);
            }
        });
        jPanel9.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 70, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setOpaque(false);

        jLabel2.setText("Proveedor");

        jLabel5.setText("Hasta");

        jLabel13.setText("Desde");

        cbProveedores.setColorMaterial(new java.awt.Color(0, 102, 102));
        cbProveedores.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresActionPerformed(evt);
            }
        });

        dcFDesde.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
    dcFDesde.setCalendarBackground(new java.awt.Color(255, 255, 255));
    dcFDesde.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
    dcFDesde.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 12));
    dcFDesde.setNavigateFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
    dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcFDesdeOnCommit(evt);
        }
    });

    dcFHasta.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
dcFHasta.setCalendarBackground(new java.awt.Color(255, 255, 255));
dcFHasta.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
dcFHasta.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 12));
dcFHasta.setNavigateFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
    public void onCommit(datechooser.events.CommitEvent evt) {
        dcFHastaOnCommit(evt);
    }
    });

    btnBuscarFacturas.setText("CARGAR LISTA DE FACTURAS");
    btnBuscarFacturas.setColorPrimario(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorPrimarioHover(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorSecundario(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorSecundarioHover(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    btnBuscarFacturas.setForegroundHover(new java.awt.Color(205, 0, 0));
    btnBuscarFacturas.setForegroundIconHover(new java.awt.Color(205, 0, 0));
    btnBuscarFacturas.setIconTextGap(10);
    btnBuscarFacturas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
    btnBuscarFacturas.setSizeIcon(21.0F);
    btnBuscarFacturas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBuscarFacturasActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnBuscarFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btnBuscarFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
    Blanco.setLayout(BlancoLayout);
    BlancoLayout.setHorizontalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addGap(5, 5, 5)
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BlancoLayout.createSequentialGroup()
                    .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGap(5, 5, 5))
    );
    BlancoLayout.setVerticalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(5, 5, 5)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    jMenu1.setText("Opciones");
    jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
    jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

    itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_note_add_black_24.png"))); // NOI18N
    itemNuevo.setText("Nuevo");
    itemNuevo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemNuevoActionPerformed(evt);
        }
    });
    jMenu1.add(itemNuevo);

    itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
    itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_save_black_24.png"))); // NOI18N
    itemGuardar.setText("Guardar Nuevo");
    itemGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemGuardarActionPerformed(evt);
        }
    });
    jMenu1.add(itemGuardar);

    itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_cancel_black_24.png"))); // NOI18N
    itemCancelar.setText("Cancelar");
    itemCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemCancelarActionPerformed(evt);
        }
    });
    jMenu1.add(itemCancelar);
    jMenu1.add(jSeparator1);

    itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
    itemQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_remove_black_24.png"))); // NOI18N
    itemQuitar.setText("Quitar Factura");
    itemQuitar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemQuitarActionPerformed(evt);
        }
    });
    jMenu1.add(itemQuitar);
    jMenu1.add(jSeparator3);

    itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
    itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
    itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
    itemSalir.setText("Salir");
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
        .addGroup(layout.createSequentialGroup()
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        /*controlCompra.addTablaPagos(tbDetalle);
        Renders();
        cant();
        tbDetalle.changeSelection(tbDetalle.getRowCount() - 1, 0, false, false);
        btnBuscarArticuloActionPerformed(null);*/


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        try {
            controlCompra.delRenglonPagos(tbDetalle);
            Renders();
            cant();
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }

    }//GEN-LAST:event_btnRestarActionPerformed

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

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:S
        btnRestar.doClick();
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void txtCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaActionPerformed

    private void txtCajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaKeyPressed

    private void txtCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaKeyTyped

    private void tbDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleMousePressed

    private void tbDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == 1) {
            if (evt.getClickCount() == 2) {
                menuEmergente.show(tbDetalle, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbDetalleMouseClicked

    private void txtHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHastaActionPerformed

    private void btnBuscarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturasActionPerformed
        // TODO add your handling code here:
        if (txtCodProveedor.getText().isEmpty()) {
            Mensajes.Alerta("Seleccione un Proveedor para filtrar las facturas.");
        } else if (txtDesde.getText().isEmpty()) {
            Mensajes.Alerta("Es necesario proporcionar una fecha de inicio para iniciar el filtrado.");
        } else if (txtHasta.getText().isEmpty()) {
            Mensajes.Alerta("Es necesario proporcionar una fecha final para iniciar el filtrado.");
        } else {
            try {
                dlgBuscarFacturaCredito bac = new dlgBuscarFacturaCredito(null, true);
                bac.setLocationRelativeTo(null);
                // bac.setLocation(200, 250);
                bac.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No hay conexión con el servidor");
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarFacturasActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:7
        String cod = GestionarCompra.getCodigoPago();
        txtCod.setText(cod);
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtFechaFachada.setText(Fecha.fechaCorrectaFachada());
        dcFDesde.setEnabled(true);
        dcFHasta.setEnabled(true);
        dcFDesdeOnCommit(null);
        dcFHastaOnCommit(null);
        btnBuscarFacturas.setEnabled(true);
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        itemQuitar.setEnabled(true);
        btnSalir.setEnabled(false);
        cbProveedores.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cod = GestionarCompra.getCodigoPago();
        txtCod.setText(cod);
        if (txtReciboD.getText().isEmpty()) {
            Mensajes.informacion("Ingrese el número de Recido de Dinero emitido por el proveedor");
            txtReciboD.requestFocus();
        } else if (tbDetalle.getRowCount() <= 0) {
            Mensajes.informacion("Tabla vacía.\nPara registrar un pago es necesario identificar las Facturas de Compra.");
            btnBuscarFacturas.doClick();
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas registrar el pago al sistema?", "CONFIRMACIÓN DE PAGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = Login.getUsuarioLogueado();
                        prepararBD();
                        con.setAutoCommit(false);
                        String sql = "insert into pagos_proveedor values(" + txtCod.getText() + "," + txtCaja.getText() + "," + txtCodProveedor.getText() + ",'" + txtReciboD.getText()
                                + "','" + Fecha.formatoFecha(txtFechaFachada.getText().trim()) + "','" + Fecha.darHora() + "'," + txtTotal.getText().replace("Gs.", "").replace(".", "").replace(",", "") + ",'S','" + usuario + "')";
                        stTransaccion.executeUpdate(sql);
                        int fila = tbDetalle.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {
                                tbDetalle.getValueAt(j, 0).toString(),//idcompra
                                tbDetalle.getValueAt(j, 4).toString(),//monto_pago
                                tbDetalle.getValueAt(j, 5).toString(),//saldo pendiente
                                tbDetalle.getValueAt(j, 6).toString(),//estado actual
                                tbDetalle.getValueAt(j, 8).toString(),//nc_nro
                                tbDetalle.getValueAt(j, 9).toString(),//nc_monto
                            };
                            sql = "insert into detalle_pagos_proveedor values(" + txtCod.getText() + "," + filas[0] + "," + filas[1] + "," + (j + 1) + ",'" + filas[4] + "'," + filas[5] + ")";
                            String estado = null;
                            switch (filas[3]) {
                                case "PAGO PENDIENTE" ->
                                    estado = "PE";
                                case "PAGO PARCIAL" ->
                                    estado = "PA";
                                case "PAGO ABONADO" ->
                                    estado = "AB";
                                default -> {
                                }
                            }
                            String sql2 = "UPDATE compra SET estado='" + estado + "', saldo=" + filas[2] + " WHERE  com_codigo=" + filas[0];
                            stTransaccion.executeUpdate(sql);
                            stTransaccion.executeUpdate(sql2);
                        }
                        con.commit();
                        Mensajes.informacion("El Pago con Recibo de Dinero N°:" + txtReciboD.getText() + " ha sido regitrado exitosamente");
                        stTransaccion.close();
                        con.close();

                    } catch (SQLException e) {
                        try {
                            con.rollback();
                            Mensajes.error("TRANSACCION FALLIDA. LOS DATOS NO FUERON GUARDADOS EN LA BD." + e.getMessage().toUpperCase());
                            stTransaccion.close();
                            con.close();
                        } catch (SQLException se) {
                            Mensajes.error(se.getMessage());
                        }
                    }
                    Cancelar();
                    cant();
                }
            } catch (Exception ee) {
                System.out.println(ee.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar la Compra?");
        if (rpta == 0) {
            limpiarCampos();
            btnBuscarFacturas.setEnabled(false);
            dcFDesde.setEnabled(false);
            dcFHasta.setEnabled(false);
            itemQuitar.setEnabled(false);
            btnNuevo.setEnabled(true);
            itemNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            itemCancelar.setEnabled(false);
            cbProveedores.setEnabled(false);
            btnSalir.setEnabled(true);
            cant();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            controlCompra.arrayPago.vaciar();
            try {
                CabecerasTablas.ArticulosMovil(dlgArticulosMovil.tbProductos);
                CabecerasTablas.limpiarTablasArticuloM(tbDetalle);
                controlArticuloMovil.listArticulo(dlgArticulosMovil.tbProductos, "cod");
            } catch (Exception e) {
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void dcFDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFDesdeOnCommit
        // TODO add your handling code here:
        txtDesde.setText(Fecha.formatoFecha(dcFDesde.getText()));
    }//GEN-LAST:event_dcFDesdeOnCommit

    private void dcFHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFHastaOnCommit
        // TODO add your handling code here:
        txtHasta.setText(Fecha.formatoFecha(dcFHasta.getText()));
    }//GEN-LAST:event_dcFHastaOnCommit

    private void txtDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesdeActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed
        // TODO add your handling code here:
        try {
            String id = cargarComboBox.getCodidgo(cbProveedores);
            txtCodProveedor.setText(id);
        } catch (Exception e) {
            txtCodProveedor.setText("");
        }
    }//GEN-LAST:event_cbProveedoresActionPerformed

    private void itemMonto_a_abonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMonto_a_abonarActionPerformed
        // TODO add your handling code here:
        CambiarMontoAbonar();
    }//GEN-LAST:event_itemMonto_a_abonarActionPerformed

    private void btnCancelarModificacionPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModificacionPagoActionPerformed
        // TODO add your handling code here:
        DialogCambioPago.dispose();
        tbDetalle.clearSelection();
    }//GEN-LAST:event_btnCancelarModificacionPagoActionPerformed

    private void txtAbonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbono.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbono.setText(df.format(Integer.valueOf(txtAbono.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbono.setText("0");
                txtAbono.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int abono = Integer.parseInt(txtAbono.getText().trim().replace(".", "").replace(",", ""));
            int monto = Integer.parseInt(lbmonto.getText().trim());
            if (abono > monto) {
                Mensajes.Sistema("El monto ingresado: " + df.format(abono) + " supera el valor completo o parcial de esta factura, la cual es de: " + df.format(monto));
                txtAbono.setText(df.format(monto));
                txtAbono.requestFocus();
                abono = Integer.parseInt(txtAbono.getText().trim().replace(".", "").replace(",", ""));
                int saldo = monto - abono;
                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
            } else {
                int saldo = monto - abono;
                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtAbonoKeyReleased

    private void txtAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoActionPerformed
        // TODO add your handling code here:
        btnAceptarActionPerformed(null);
    }//GEN-LAST:event_txtAbonoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        controlCompra.actMontoPago(tbDetalle);
        DialogCambioPago.dispose();
        tbDetalle.clearSelection();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtAbonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbono);
    }//GEN-LAST:event_txtAbonoKeyPressed

    private void txtFechaFachadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFachadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFachadaActionPerformed

    private void txtReciboDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReciboDKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtReciboD);
        validarCampos.cantCaracteres(txtReciboD, 15);
    }//GEN-LAST:event_txtReciboDKeyPressed

    private void txtAbono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbono1ActionPerformed
        // TODO add your handling code here:
        btnAceptar1.doClick();
    }//GEN-LAST:event_txtAbono1ActionPerformed

    private void txtAbono1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbono1KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbono1);
    }//GEN-LAST:event_txtAbono1KeyPressed

    private void txtAbono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbono1KeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbono1.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbono1.setText(df.format(Integer.valueOf(txtAbono1.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbono1.setText("0");
                txtAbono1.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int abono = Integer.parseInt(txtAbono1.getText().trim().replace(".", "").replace(",", ""));
            int monto = Integer.parseInt(lbmonto1.getText().trim());
            if (abono > monto) {
                Mensajes.Sistema("El monto de la Nota de Crédito ingresado: " + df.format(abono) + " supera el valor del Saldo actual esta factura, la cual es de: " + df.format(monto));
                txtAbono1.setText(df.format(monto));
                txtAbono1.requestFocus();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtAbono1KeyReleased

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        // TODO add your handling code here:
        if (txtNC.getText().trim().equals("-") && txtAbono1.getText().trim().equals("0")) {
            controlCompra.addNCPagoProveedor(tbDetalle);
            DialogAgregarNC.dispose();
            tbDetalle.clearSelection();
        } else {
            if (txtNC.getText().trim().equals("-")) {
                Mensajes.Sistema("Ingrese el Número de Nota de Crédito correspondiente a esta factura.");
                txtNC.requestFocus();
                txtNC.selectAll();
            } else if (txtAbono1.getText().trim().equals("0")) {
                Mensajes.Sistema("No se puede registrar una Nota de Crédito con monto 0.");
                txtAbono1.requestFocus();
                txtAbono1.selectAll();
            } else {
                controlCompra.addNCPagoProveedor(tbDetalle);
                DialogAgregarNC.dispose();
                tbDetalle.clearSelection();
            }
        }


    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnCancelarModificacionPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModificacionPago1ActionPerformed
        // TODO add your handling code here:
        DialogAgregarNC.dispose();
    }//GEN-LAST:event_btnCancelarModificacionPago1ActionPerformed

    private void itemAnexarNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAnexarNCActionPerformed
        // TODO add your handling code here:
        AnexarNC();
    }//GEN-LAST:event_itemAnexarNCActionPerformed

    private void txtNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCActionPerformed
        // TODO add your handling code here:}
        txtAbono1.requestFocus();
    }//GEN-LAST:event_txtNCActionPerformed

    private void txtNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtNC);
        validarCampos.cantCaracteres(txtNC, 15);
    }//GEN-LAST:event_txtNCKeyPressed

    private void txtNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgRegistrarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            dlgRegistrarPagosProveedor dialog = new dlgRegistrarPagosProveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JDialog DialogAgregarNC;
    private javax.swing.JDialog DialogCambioPago;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar1;
    private javax.swing.JButton btnAdd;
    private newscomponents.RSButtonGradientIcon_new btnBuscarFacturas;
    public static newscomponents.RSButtonBigIcon_new btnCancelar;
    private javax.swing.JButton btnCancelarModificacionPago;
    private javax.swing.JButton btnCancelarModificacionPago1;
    public static newscomponents.RSButtonBigIcon_new btnGuardar;
    public static newscomponents.RSButtonBigIcon_new btnNuevo;
    private javax.swing.JButton btnRestar;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    private RSMaterialComponent.RSComboBoxMaterial cbProveedores;
    private datechooser.beans.DateChooserCombo dcFDesde;
    private datechooser.beans.DateChooserCombo dcFHasta;
    public static javax.swing.JLabel etiCant;
    private javax.swing.JMenuItem itemAnexarNC;
    private javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemMonto_a_abonar;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemQuitar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lbFactura;
    private javax.swing.JLabel lbFactura1;
    private javax.swing.JLabel lbMonto_Factura;
    private javax.swing.JLabel lbMonto_Factura1;
    private javax.swing.JLabel lbMonto_abonar;
    private javax.swing.JLabel lbSaldo_final;
    private javax.swing.JLabel lbmonto;
    private javax.swing.JLabel lbmonto1;
    private javax.swing.JPopupMenu menuEmergente;
    public static javax.swing.JTable tbDetalle;
    public static javax.swing.JTextField txtAbono;
    public static javax.swing.JTextField txtAbono1;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodProveedor;
    public static javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtFechaFachada;
    public static javax.swing.JTextField txtHasta;
    public static javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtReciboD;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
