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
import Componentes.Numero_a_Letra;
import Componentes.RenderCargaTotalconPunto;
import Componentes.RenderDecimalconPuntos;
import Componentes.RenderDevueltoconPunto;
import Componentes.RenderRecambioconPunto;
import Componentes.RenderVendidoconPunto;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlReparto;
import Datos.GestionarReparto;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
public class dlgGestRepartos extends javax.swing.JDialog {

    private static final CabecerasTablas cabe = new CabecerasTablas();

    //public static ResultSet rs;
    public static MariaDbConnection con;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection conMovil;
    public static MariaDbStatement sentenciaMovil;
    public static MariaDbStatement stTransaccionMovil;
    static String UsuarioL = "";
    static String opcion = "";
    public Reporte jasper;
    public Numero_a_Letra d;
    static int anterior, dif, inicio, cierre, vacio, diferencia, totalc;

    /**
     * Creates new form dlgGestRepartos
     *
     * @param parent
     * @param modal
     */
    public dlgGestRepartos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        noVisible();
        CabecerasTablas.reparto(tbDetalleReparto);
        Renders();
        UsuarioL = Login.getUsuarioLogueado();
        d = new Numero_a_Letra();

    }

    public static void Renders() {
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(4).setCellRenderer(new RenderDecimalconPuntos());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimalconPuntos());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimalconPuntos());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(7).setCellRenderer(new RenderCargaTotalconPunto());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(9).setCellRenderer(new RenderRecambioconPunto());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(11).setCellRenderer(new RenderVendidoconPunto());
        dlgGestRepartos.tbDetalleReparto.getColumnModel().getColumn(14).setCellRenderer(new RenderDevueltoconPunto());
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Crear o modificar Repartos");
        } else {
            this.setTitle(Software.getSoftware() + " - Crear o modificar Repartos");
        }
    }

    private void noVisible() {
        txtCaja.setVisible(false);
        btnAdd.setVisible(false);
        txtOpcion.setVisible(false);
        txtResponsable.setVisible(false);
        txtChofer.setVisible(false);
        txtMovil.setVisible(false);
        txtFechaIF.setVisible(false);
        txtIdMovil.setVisible(false);
        txtTotalL.setVisible(false);
        txtCodArticulo.setVisible(false);
        txtRecambioL.setVisible(false);
        txtDevueltosL.setVisible(false);
        txtTotalVentasL.setVisible(false);
        btnBuscarArticulo.setVisible(false);
        btnRestar.setVisible(false);
        txtPresentadoL.setVisible(false);
        txtDiferenciaL.setVisible(false);
        lbComR.setVisible(false);
        lbComC.setVisible(false);
        txtIdResponsable.setVisible(false);
        txtIdChofer.setVisible(false);
        txtValorRA.setVisible(false);
        txtValorCompra.setVisible(false);
        txtValorTransferencia.setVisible(false);
    }

    public static void habilitarCANT() {
        if (txtProducto.getText().isEmpty()) {
            txtCantidad.setEnabled(false);
        } else {
            txtCantidad.setEnabled(true);
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

    public static void limpiarCampos() {
        txtIdReparto.setText("");
        lbInfoMovil.setText("");
        lbInfoChofer.setText("");
        txtIdResponsable.setText("");
        txtResponsable.setText("");
        lbComR.setText("");
        txtIdChofer.setText("");
        txtChofer.setText("");
        lbComC.setText("");
        txtIdMovil.setText("");
        txtMovil.setText("");
        txtFechaI.setText("");
        txtFechaIF.setText("");
        txtHoraI.setText("");
        txtEstado.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtOpcion.setText("");
        txtCodArticulo.setText("");
        txtTotalL.setText("0");
        txtRecambioL.setText("0");
        txtDevueltosL.setText("0");
        txtTotalVentasL.setText("0");
        txtPresentadoL.setText("0");
        txtDiferenciaL.setText("0");
        txtKmAnterior.setText("0");
        txtKmActual.setText("0");
        txtKmRecorrido.setText("0");

        txtValorRA.setText("0");
        txtValorCompra.setText("0");
        txtValorTransferencia.setText("0");

        txtTotal.setText("0");
        txtRecambio.setText("0");
        txtDevueltos.setText("0");
        txtTotalVentas.setText("0");
        txtPresentado.setText("0");
        txtDiferencia.setText("0");
        txtGastosTotal.setText("0");
        txtComisionR.setText("0");
        txtComisionC.setText("0");
        txtEstado.setText("");
        txtCaja.setText("");
        txtContenedorI.setText("0");
        txtContenedorF.setText("0");
        txtContenedorD.setText("");
        cbCerrar.setSelected(false);
        chAgregar.setSelected(false);
        lbInfoCantidad.setText("");
        CabecerasTablas.reparto(tbDetalleReparto);
        CabecerasTablas.limpiarTablasRepartos(tbDetalleReparto);
    }

    public static void cant() {
        int total = tbDetalleReparto.getRowCount();
        switch (total) {
            case 0 ->
                lbInfoCantidad.setText("lista vacia.");
            case 1 ->
                lbInfoCantidad.setText("Se lista " + total + " producto en el reparto.");
            default ->
                lbInfoCantidad.setText("Se listan " + total + " productos en el reparto.");
        }

    }

    public static void calcularDiferencia() {
        int venta, entrega, diferencia;
        venta = Integer.parseInt(txtTotalVentasL.getText());
        entrega = Integer.parseInt(txtPresentadoL.getText());
        diferencia = (entrega - venta);

        DecimalFormat df = new DecimalFormat("#,###");
        txtDiferenciaL.setText(String.valueOf(diferencia));
        txtDiferencia.setText(df.format(Integer.parseInt(String.valueOf(diferencia).replace(".", "").replace(",", ""))));
    }

    public static void calcularCajas() {
        try {
            //int anterior,dif, inicio,cierre, vacio, diferencia, totalc;

            anterior = Integer.parseInt(txtCAnterior.getText());
            if (txtContenedorI.getText().trim().length() == 0) {
                inicio = 0;
            } else if (txtContenedorI.getText().trim().length() > 0) {
                inicio = Integer.parseInt(txtContenedorI.getText());
            }
            totalc = anterior + inicio;
            txtContenedorT.setText(String.valueOf(totalc));

            if (txtContenedorF.getText().trim().length() == 0) {
                cierre = 0;
            } else if (txtContenedorF.getText().trim().length() > 0) {
                cierre = Integer.parseInt(txtContenedorF.getText());
            }
            if (txtContenedorV.getText().length() == 0) {
                vacio = 0;
            } else if (txtContenedorV.getText().length() > 0) {
                vacio = Integer.parseInt(txtContenedorV.getText());
            }

            diferencia = (cierre + vacio) - Integer.parseInt(txtContenedorT.getText());

            dif = Integer.parseInt(txtCdifACA.getText());
            int difFinal = (diferencia);
            int difAC = (diferencia) + (dif);
            txtContenedorD.setText(String.valueOf(difFinal));
            txtContenedorDA.setText(String.valueOf(difAC));

        } catch (Exception e) {
        }

    }

    public static void calcularKilometraje() {
        try {
            int inicio, cierre, diferencia;
            inicio = Integer.parseInt(txtKmAnterior.getText());
            cierre = Integer.parseInt(txtKmActual.getText());
            diferencia = cierre - inicio;
            txtKmRecorrido.setText(String.valueOf(diferencia));
        } catch (Exception e) {
        }

    }

    public static void calcularComisiones() {
        DecimalFormat df = new DecimalFormat("#,###");
        try {
            if (txtPresentadoL.getText().isEmpty()) {
                txtComisionR.setText("0");
            } else {
                int monto;
                double com;
                int Mcomision;
                monto = Integer.parseInt(txtPresentadoL.getText());
                com = Double.parseDouble(lbComR.getText());
                Mcomision = (int) (monto * (com / 100));
                //txtComisionR.setText(String.valueOf(Mcomision));
                txtComisionR.setText(df.format(Integer.parseInt(String.valueOf(Mcomision).replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
        }
        try {
            if (txtPresentadoL.getText().isEmpty()) {
                txtComisionC.setText("0");
            } else {
                int monto;
                double com;
                int Mcomision;
                monto = Integer.parseInt(txtPresentadoL.getText());
                com = Double.parseDouble(lbComC.getText());
                Mcomision = (int) (monto * (com / 100));
                //txtComisionC.setText(String.valueOf(Mcomision));
                txtComisionC.setText(df.format(Integer.parseInt(String.valueOf(Mcomision).replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
        }
    }

    public static void modificarcboResponsable() {
        DefaultComboBoxModel ml = new DefaultComboBoxModel();
        String codResponsable = txtIdResponsable.getText().trim();
        try {
            prepararBD();
            ResultSet res = sentencia.executeQuery("SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
            ml.addElement("SELECCIONE UN RESPONSABLE");
            while (res.next()) {
                ml.addElement(res.getString("ven_nombre"));

            }
            ResultSet rss = sentencia.executeQuery("SELECT * FROM v_vendedores WHERE ven_codigo=" + codResponsable);
            rss.first();
            Object descripcion = (Object) rss.getString("ven_nombre");
            cboResponsable.setModel(ml);
            cboResponsable.setSelectedItem(descripcion);
        } catch (SQLException ew) {
        }
    }

    public static void ObtenerGastosA() {

        try {
            String idm;
            idm = txtIdMovil.getText();
            try {
                prepararBD();
                ResultSet rs = sentencia.executeQuery("SELECT SUM(ga_importe) AS total FROM gastos WHERE ga_idmovil=" + idm + " AND caja_ca_id=" + txtCaja.getText().trim() + " AND ga_indicador='S' AND tipo='A'");
                rs.last();
                if (rs.getString("total") == null) {
                    txtGastosTotal.setText("0");
                } else {
                    DecimalFormat df = new DecimalFormat("#,###");
                    txtGastosTotal.setText(df.format(Integer.parseInt(String.valueOf(rs.getString("total")).replace(".", "").replace(",", ""))));
                }
                rs.close();
                sentencia.close();
                sentenciaMovil.close();
                stTransaccionMovil.close();
                con.close();
                conMovil.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del gasto: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }

    }

    public void Nuevo() {
        btnNuevoActionPerformed(null);
    }

    public void Cancelar() {
        controlReparto.array.vaciar();
        limpiarCampos();
        btnNuevo.setEnabled(true);
        itemNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        itemQuitar.setEnabled(false);
        btnSalir.setEnabled(true);
        itemSalir.setEnabled(true);
        btnRC.setEnabled(false);
        btnRR.setEnabled(false);
        itemHojaR.setEnabled(false);
        itemHojaR1.setEnabled(false);
        btnCargarRA.setEnabled(false);
        btnCompraA.setEnabled(false);
        txtContenedorI.setEnabled(false);
        txtContenedorF.setEnabled(false);
        txtKmAnterior.setEnabled(false);
        txtKmActual.setEnabled(false);
        txtPresentado.setEnabled(false);
        btnCargarTransferencias.setEnabled(false);
        cboResponsable.setEnabled(false);
        chAgregar.setEnabled(false);
        //txtIdReparto.setEnabled(true);
        cbCerrar.setEnabled(false);
        rdCargaT.setEnabled(false);
        rdRecambios.setEnabled(false);
        rdDevueltos.setEnabled(false);
        tbDetalleReparto.setEnabled(false);
        this.dispose();
        Volver();

    }

    private void Volver() {
        //dlgRepartos ac = new dlgRepartos(null,true);
        //ac.setLocationRelativeTo(null);
        //ac.setVisible(true);
        CabecerasTablas.todosReparto(dlgRepartos.tbDetalle);
        CabecerasTablas.limpiarTablasTodosRepartos(dlgRepartos.tbDetalle);
        controlReparto.listRepartos(dlgRepartos.tbDetalle, "id_reparto", dlgRepartos.txtFechaFinal.getText().trim());
        dlgRepartos.Renders();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoCargado = new javax.swing.ButtonGroup();
        grupoOpciones = new javax.swing.ButtonGroup();
        DialogCajas = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnGuardarC = new newscomponents.RSButtonBigIcon_new();
        btnCancelarC = new newscomponents.RSButtonBigIcon_new();
        btnSalirC = new newscomponents.RSButtonBigIcon_new();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        lbInfoCantidad = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdReparto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbInfoMovil = new javax.swing.JLabel();
        lbInfoChofer = new javax.swing.JLabel();
        cboResponsable = new rojerusan.RSComboBox();
        txtFechaI = new javax.swing.JTextField();
        txtHoraI = new javax.swing.JTextField();
        lbComR = new javax.swing.JLabel();
        lbComC = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCargarRA = new newscomponents.RSButtonGradientIcon_new();
        btnCompraA = new newscomponents.RSButtonGradientIcon_new();
        btnCargarTransferencias = new newscomponents.RSButtonGradientIcon_new();
        jPanel7 = new javax.swing.JPanel();
        chAgregar = new rojerusan.RSCheckBox();
        jSeparator8 = new javax.swing.JSeparator();
        rdCargaT = new rojerusan.RSRadioButton();
        rdRecambios = new rojerusan.RSRadioButton();
        rdDevueltos = new rojerusan.RSRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRecambio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDevueltos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotalVentas = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPresentado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDiferencia = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtProducto = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtComisionR = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtComisionC = new javax.swing.JTextField();
        btnRR = new javax.swing.JButton();
        btnRC = new javax.swing.JButton();
        lbComR1 = new javax.swing.JLabel();
        lbComC1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtContenedorI = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtContenedorF = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtContenedorT = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtContenedorV = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtContenedorD = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtContenedorDA = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtKmAnterior = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtKmActual = new javax.swing.JTextField();
        txtKmRecorrido = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        cbCerrar = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtGastosTotal = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new newscomponents.RSButtonBigIcon_new();
        btnModificar = new newscomponents.RSButtonBigIcon_new();
        btnGuardar = new newscomponents.RSButtonBigIcon_new();
        btnCancelar = new newscomponents.RSButtonBigIcon_new();
        btnSalir = new newscomponents.RSButtonBigIcon_new();
        btnAdd = new javax.swing.JButton();
        txtOpcion = new javax.swing.JTextField();
        txtResponsable = new javax.swing.JTextField();
        txtChofer = new javax.swing.JTextField();
        txtMovil = new javax.swing.JTextField();
        txtFechaIF = new javax.swing.JTextField();
        txtTotalL = new javax.swing.JTextField();
        txtIdMovil = new javax.swing.JTextField();
        txtCodArticulo = new javax.swing.JTextField();
        txtRecambioL = new javax.swing.JTextField();
        txtDevueltosL = new javax.swing.JTextField();
        txtTotalVentasL = new javax.swing.JTextField();
        btnRestar = new javax.swing.JButton();
        btnBuscarArticulo = new javax.swing.JButton();
        txtPresentadoL = new javax.swing.JTextField();
        txtDiferenciaL = new javax.swing.JTextField();
        txtIdResponsable = new javax.swing.JTextField();
        txtValorRA = new javax.swing.JTextField();
        txtIdChofer = new javax.swing.JTextField();
        txtValorCompra = new javax.swing.JTextField();
        txtValorTransferencia = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtCAnterior = new javax.swing.JTextField();
        txtCdif = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtCdifACA = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        MnOpcionesReparto = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemModificar = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarArticulo = new javax.swing.JMenuItem();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        MnImprimirReparto = new javax.swing.JMenu();
        itemHojaApunte1 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        itemHojaApunte = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemHojaR = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        itemHojaR1 = new javax.swing.JMenuItem();

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.GridLayout(1, 6));

        btnGuardarC.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarC.setText("GUARDAR");
        btnGuardarC.setBgHover(new java.awt.Color(75, 75, 75));
        btnGuardarC.setFocusPainted(false);
        btnGuardarC.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardarC.setIconTextGap(-2);
        btnGuardarC.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardarC.setPixels(0);
        btnGuardarC.setSizeIcon(42.0F);
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });
        jPanel12.add(btnGuardarC);

        btnCancelarC.setBackground(new java.awt.Color(102, 102, 102));
        btnCancelarC.setText("CANCELAR");
        btnCancelarC.setBgHover(new java.awt.Color(75, 75, 75));
        btnCancelarC.setFocusPainted(false);
        btnCancelarC.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancelarC.setIconTextGap(-2);
        btnCancelarC.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelarC.setPixels(0);
        btnCancelarC.setSizeIcon(42.0F);
        btnCancelarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCActionPerformed(evt);
            }
        });
        jPanel12.add(btnCancelarC);

        btnSalirC.setBackground(new java.awt.Color(102, 102, 102));
        btnSalirC.setText("SALIR");
        btnSalirC.setBgHover(new java.awt.Color(75, 75, 75));
        btnSalirC.setFocusPainted(false);
        btnSalirC.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSalirC.setIconTextGap(-2);
        btnSalirC.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalirC.setPixels(0);
        btnSalirC.setSizeIcon(42.0F);
        btnSalirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirCActionPerformed(evt);
            }
        });
        jPanel12.add(btnSalirC);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("CONTROL DE CAJAS");

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel14.setOpaque(false);

        jLabel3.setText("CAJAS STOCK");

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("CAJAS VACIAS");

        jTextField2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setText("VENCIDOS");

        jTextField3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setText("TOTAL");

        jTextField4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel21.setBackground(new java.awt.Color(102, 102, 102));
        jLabel21.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("ANTERIOR");
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(102, 102, 102));
        jLabel22.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("ACTUAL");
        jLabel22.setOpaque(true);

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel16.setOpaque(false);

        jLabel26.setText("CAJAS STOCK");

        jTextField7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel27.setText("CAJAS VACIAS");

        jTextField8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel28.setText("TOTAL");

        jTextField9.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DIFERENCIA ACUMULADA:   ");
        jLabel29.setOpaque(true);

        jLabel30.setBackground(new java.awt.Color(102, 102, 102));
        jLabel30.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("0");
        jLabel30.setOpaque(true);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel5))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogCajasLayout = new javax.swing.GroupLayout(DialogCajas.getContentPane());
        DialogCajas.getContentPane().setLayout(DialogCajasLayout);
        DialogCajasLayout.setHorizontalGroup(
            DialogCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogCajasLayout.setVerticalGroup(
            DialogCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogCajasLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbInfoCantidad.setBackground(new java.awt.Color(0, 102, 102));
        lbInfoCantidad.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbInfoCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lbInfoCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoCantidad.setOpaque(true);
        Blanco.add(lbInfoCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 527, 910, 20));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel1.setText("ID REPARTO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 17, 65, -1));

        txtIdReparto.setEditable(false);
        txtIdReparto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtIdReparto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdReparto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdRepartoActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdReparto, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 13, 156, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel2.setText("RESPONSABLE:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 46, 80, -1));

        lbInfoMovil.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbInfoMovil.setForeground(new java.awt.Color(17, 35, 46));
        lbInfoMovil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lbInfoMovil, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 71, 294, 20));

        lbInfoChofer.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbInfoChofer.setForeground(new java.awt.Color(17, 35, 46));
        lbInfoChofer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lbInfoChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 97, 294, 20));

        cboResponsable.setForeground(new java.awt.Color(17, 35, 46));
        cboResponsable.setColorArrow(new java.awt.Color(17, 35, 46));
        cboResponsable.setColorBorde(new java.awt.Color(204, 204, 204));
        cboResponsable.setColorBoton(new java.awt.Color(255, 255, 255));
        cboResponsable.setColorDisabledIndex(new java.awt.Color(255, 255, 255));
        cboResponsable.setColorDisabledIndexText(new java.awt.Color(255, 255, 255));
        cboResponsable.setColorFondo(new java.awt.Color(255, 255, 255));
        cboResponsable.setColorListaItemsTXT(new java.awt.Color(17, 35, 46));
        cboResponsable.setColorSeleccion(new java.awt.Color(204, 204, 204));
        cboResponsable.setColorSeleccionTXT(new java.awt.Color(17, 35, 46));
        cboResponsable.setDisabledIdex("-1");
        cboResponsable.setEnabled(false);
        cboResponsable.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
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
        jPanel1.add(cboResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 40, 295, 25));

        txtFechaI.setEditable(false);
        txtFechaI.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaI.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtFechaI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtFechaI, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 13, 76, -1));

        txtHoraI.setEditable(false);
        txtHoraI.setBackground(new java.awt.Color(255, 255, 255));
        txtHoraI.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtHoraI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtHoraI, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 13, 46, -1));

        lbComR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbComR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbComR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 38, 23));

        lbComC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbComC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbComC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, 38, 24));

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 2, 394, 124));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setOpaque(false);

        btnCargarRA.setBackground(new java.awt.Color(102, 102, 102));
        btnCargarRA.setText("REPARTO ANTERIOR");
        btnCargarRA.setColorPrimario(new java.awt.Color(130, 128, 128));
        btnCargarRA.setColorPrimarioHover(new java.awt.Color(130, 128, 128));
        btnCargarRA.setColorSecundario(new java.awt.Color(130, 128, 128));
        btnCargarRA.setColorSecundarioHover(new java.awt.Color(130, 128, 128));
        btnCargarRA.setEnabled(false);
        btnCargarRA.setFocusPainted(false);
        btnCargarRA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarRA.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCargarRA.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCargarRA.setIconTextGap(10);
        btnCargarRA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPLY);
        btnCargarRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRAActionPerformed(evt);
            }
        });

        btnCompraA.setBackground(new java.awt.Color(102, 102, 102));
        btnCompraA.setText("COMPRA DEL DÍA");
        btnCompraA.setColorPrimario(new java.awt.Color(130, 128, 128));
        btnCompraA.setColorPrimarioHover(new java.awt.Color(130, 128, 128));
        btnCompraA.setColorSecundario(new java.awt.Color(130, 128, 128));
        btnCompraA.setColorSecundarioHover(new java.awt.Color(130, 128, 128));
        btnCompraA.setEnabled(false);
        btnCompraA.setFocusPainted(false);
        btnCompraA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCompraA.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCompraA.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCompraA.setIconTextGap(10);
        btnCompraA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        btnCompraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraAActionPerformed(evt);
            }
        });

        btnCargarTransferencias.setBackground(new java.awt.Color(102, 102, 102));
        btnCargarTransferencias.setText("TRANSFERENCIAS");
        btnCargarTransferencias.setColorPrimario(new java.awt.Color(130, 128, 128));
        btnCargarTransferencias.setColorPrimarioHover(new java.awt.Color(130, 128, 128));
        btnCargarTransferencias.setColorSecundario(new java.awt.Color(130, 128, 128));
        btnCargarTransferencias.setColorSecundarioHover(new java.awt.Color(130, 128, 128));
        btnCargarTransferencias.setEnabled(false);
        btnCargarTransferencias.setFocusPainted(false);
        btnCargarTransferencias.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarTransferencias.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btnCargarTransferencias.setForegroundText(new java.awt.Color(0, 0, 0));
        btnCargarTransferencias.setIconTextGap(10);
        btnCargarTransferencias.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SWAP_HORIZ);
        btnCargarTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTransferenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCargarTransferencias, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(btnCompraA, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCargarRA, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnCargarRA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCompraA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargarTransferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Blanco.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 2, -1, 154));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(160, 160));

        chAgregar.setBackground(new java.awt.Color(102, 102, 102));
        chAgregar.setForeground(new java.awt.Color(0, 0, 0));
        chAgregar.setText("DETALLAR REPARTO");
        chAgregar.setColorCheck(new java.awt.Color(0, 153, 0));
        chAgregar.setColorUnCheck(new java.awt.Color(0, 153, 0));
        chAgregar.setEnabled(false);
        chAgregar.setFocusPainted(false);
        chAgregar.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        chAgregar.setIconTextGap(10);
        chAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chAgregarActionPerformed(evt);
            }
        });

        jSeparator8.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator8.setOpaque(true);

        grupoCargado.add(rdCargaT);
        rdCargaT.setForeground(new java.awt.Color(0, 0, 0));
        rdCargaT.setSelected(true);
        rdCargaT.setText("CARGA TOTAL");
        rdCargaT.setColorCheck(new java.awt.Color(0, 153, 0));
        rdCargaT.setColorUnCheck(new java.awt.Color(255, 51, 0));
        rdCargaT.setEnabled(false);
        rdCargaT.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rdCargaT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCargaTActionPerformed(evt);
            }
        });

        grupoCargado.add(rdRecambios);
        rdRecambios.setForeground(new java.awt.Color(0, 0, 0));
        rdRecambios.setText("RECAMBIOS/VENCIDOS");
        rdRecambios.setColorCheck(new java.awt.Color(0, 153, 0));
        rdRecambios.setColorUnCheck(new java.awt.Color(255, 51, 0));
        rdRecambios.setEnabled(false);
        rdRecambios.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rdRecambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdRecambiosActionPerformed(evt);
            }
        });

        grupoCargado.add(rdDevueltos);
        rdDevueltos.setForeground(new java.awt.Color(0, 0, 0));
        rdDevueltos.setText("DEVUELTOS");
        rdDevueltos.setColorCheck(new java.awt.Color(0, 153, 0));
        rdDevueltos.setColorUnCheck(new java.awt.Color(255, 51, 0));
        rdDevueltos.setEnabled(false);
        rdDevueltos.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rdDevueltos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDevueltosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdCargaT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rdRecambios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rdDevueltos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(chAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdCargaT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdRecambios, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdDevueltos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        Blanco.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 2, 159, 154));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        tbDetalleReparto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tbDetalleReparto.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalleReparto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbDetalleReparto.setFillsViewportHeight(true);
        tbDetalleReparto.setGridColor(new java.awt.Color(223, 223, 223));
        tbDetalleReparto.setRowHeight(24);
        tbDetalleReparto.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tbDetalleReparto.setShowVerticalLines(false);
        tbDetalleReparto.getTableHeader().setResizingAllowed(false);
        tbDetalleReparto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbDetalleReparto);

        Blanco.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 159, 910, 365));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMEN DE VALORES & ARREGLO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12), new java.awt.Color(27, 57, 84))); // NOI18N
        jPanel8.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel4.setText("Total de recambios/vencidos");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setBorder(null);
        txtTotal.setOpaque(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel9.setText("Total de la carga en reparto");

        txtRecambio.setEditable(false);
        txtRecambio.setBackground(new java.awt.Color(255, 255, 255));
        txtRecambio.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtRecambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRecambio.setText("0");
        txtRecambio.setBorder(null);
        txtRecambio.setOpaque(false);
        txtRecambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRecambioActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel10.setText("Total de devueltos/sobrantes");

        txtDevueltos.setEditable(false);
        txtDevueltos.setBackground(new java.awt.Color(255, 255, 255));
        txtDevueltos.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtDevueltos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDevueltos.setText("0");
        txtDevueltos.setBorder(null);
        txtDevueltos.setOpaque(false);
        txtDevueltos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDevueltosActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Total de ventas a Presentar");

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentas.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtTotalVentas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalVentas.setText("0");
        txtTotalVentas.setBorder(null);
        txtTotalVentas.setOpaque(false);
        txtTotalVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalVentasActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 0));
        jLabel12.setText("Efectivo Presentado");

        txtPresentado.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtPresentado.setForeground(new java.awt.Color(0, 102, 0));
        txtPresentado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPresentado.setText("0");
        txtPresentado.setEnabled(false);
        txtPresentado.setOpaque(false);
        txtPresentado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPresentadoActionPerformed(evt);
            }
        });
        txtPresentado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPresentadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPresentadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresentadoKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 0));
        jLabel13.setText("Diferencia");

        txtDiferencia.setEditable(false);
        txtDiferencia.setBackground(new java.awt.Color(255, 255, 255));
        txtDiferencia.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtDiferencia.setForeground(new java.awt.Color(255, 51, 0));
        txtDiferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiferencia.setText("0");
        txtDiferencia.setBorder(null);
        txtDiferencia.setOpaque(false);
        txtDiferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiferenciaActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDevueltos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiferencia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPresentado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(txtTotalVentas, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotal)
                            .addComponent(txtRecambio, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(8, 8, 8))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRecambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDevueltos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalVentas)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPresentado)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiferencia)
                    .addComponent(jLabel13))
                .addGap(9, 9, 9))
        );

        Blanco.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 71, 330, -1));

        txtProducto.setEditable(false);
        txtProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtProducto.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProducto.setPreferredSize(new java.awt.Dimension(7, 20));
        Blanco.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 128, 340, 27));

        txtCantidad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setEnabled(false);
        txtCantidad.setPreferredSize(new java.awt.Dimension(7, 20));
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });
        Blanco.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 128, 48, 27));

        txtEstado.setEditable(false);
        txtEstado.setBackground(new java.awt.Color(0, 102, 102));
        txtEstado.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtEstado.setForeground(new java.awt.Color(255, 255, 255));
        txtEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstado.setBorder(null);
        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });
        Blanco.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 2, 325, 36));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COMISIONES GENERADAS AUTOMÁTICAMENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12), new java.awt.Color(27, 57, 84))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("RESPONSABLE");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 18, -1, 30));

        txtComisionR.setEditable(false);
        txtComisionR.setBackground(new java.awt.Color(255, 255, 255));
        txtComisionR.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtComisionR.setForeground(new java.awt.Color(255, 51, 0));
        txtComisionR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtComisionR.setText("0");
        txtComisionR.setBorder(null);
        txtComisionR.setOpaque(false);
        txtComisionR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComisionRActionPerformed(evt);
            }
        });
        jPanel3.add(txtComisionR, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 18, 80, 30));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("CHOFER");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 56, 50, 30));

        txtComisionC.setEditable(false);
        txtComisionC.setBackground(new java.awt.Color(255, 255, 255));
        txtComisionC.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtComisionC.setForeground(new java.awt.Color(255, 51, 0));
        txtComisionC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtComisionC.setText("0");
        txtComisionC.setBorder(null);
        txtComisionC.setOpaque(false);
        txtComisionC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComisionCActionPerformed(evt);
            }
        });
        jPanel3.add(txtComisionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 56, 80, 30));

        btnRR.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnRR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_attach_money_black_20.png"))); // NOI18N
        btnRR.setText("RECIBO");
        btnRR.setEnabled(false);
        btnRR.setIconTextGap(0);
        btnRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRRActionPerformed(evt);
            }
        });
        jPanel3.add(btnRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 16, -1, 35));

        btnRC.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnRC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_attach_money_black_20.png"))); // NOI18N
        btnRC.setText("RECIBO");
        btnRC.setEnabled(false);
        btnRC.setIconTextGap(0);
        btnRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRCActionPerformed(evt);
            }
        });
        jPanel3.add(btnRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 55, -1, 35));

        lbComR1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbComR1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbComR1, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 18, 30, 30));

        lbComC1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbComC1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbComC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 56, 30, 30));

        Blanco.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 450, 330, 100));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setOpaque(false);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel7.setText("CAJAS - Levantados");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 3, 100, 23));

        txtContenedorI.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorI.setText("0");
        txtContenedorI.setEnabled(false);
        txtContenedorI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorIActionPerformed(evt);
            }
        });
        txtContenedorI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContenedorIKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContenedorIKeyReleased(evt);
            }
        });
        jPanel9.add(txtContenedorI, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 3, 40, 23));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel16.setText("CAJAS - Vacios + Venc.");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 78, 110, 23));

        txtContenedorF.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorF.setText("0");
        txtContenedorF.setEnabled(false);
        txtContenedorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorFActionPerformed(evt);
            }
        });
        txtContenedorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContenedorFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContenedorFKeyReleased(evt);
            }
        });
        jPanel9.add(txtContenedorF, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 53, 40, 23));

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel24.setText("TOTAL CAJAS");
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 28, 90, 23));

        txtContenedorT.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorT.setText("0");
        txtContenedorT.setBorder(null);
        txtContenedorT.setEnabled(false);
        txtContenedorT.setOpaque(false);
        txtContenedorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorTActionPerformed(evt);
            }
        });
        txtContenedorT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContenedorTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContenedorTKeyReleased(evt);
            }
        });
        jPanel9.add(txtContenedorT, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 28, 40, 23));

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel25.setText("CAJAS - c/ Stock.");
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 53, 90, 23));

        txtContenedorV.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorV.setText("0");
        txtContenedorV.setEnabled(false);
        txtContenedorV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorVActionPerformed(evt);
            }
        });
        txtContenedorV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContenedorVKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContenedorVKeyReleased(evt);
            }
        });
        jPanel9.add(txtContenedorV, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 78, 40, 23));

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 0));
        jLabel33.setText("DIFERENCIA DEL DÍA");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 103, 110, 23));

        txtContenedorD.setEditable(false);
        txtContenedorD.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorD.setForeground(new java.awt.Color(0, 153, 0));
        txtContenedorD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorD.setBorder(null);
        txtContenedorD.setOpaque(false);
        txtContenedorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorDActionPerformed(evt);
            }
        });
        jPanel9.add(txtContenedorD, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 103, 40, 23));

        jLabel34.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("VARIACIÓN ACTUAL");
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 128, 95, 23));

        txtContenedorDA.setEditable(false);
        txtContenedorDA.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtContenedorDA.setForeground(new java.awt.Color(255, 0, 0));
        txtContenedorDA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContenedorDA.setBorder(null);
        txtContenedorDA.setOpaque(false);
        txtContenedorDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorDAActionPerformed(evt);
            }
        });
        jPanel9.add(txtContenedorDA, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 128, 40, 23));

        Blanco.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(754, 2, 159, 154));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO DE RECORRIDO DEL DÍA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12), new java.awt.Color(27, 57, 84))); // NOI18N
        jPanel10.setOpaque(false);

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel17.setText("Kilometraje del reparto anterior");

        txtKmAnterior.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtKmAnterior.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKmAnterior.setText("0");
        txtKmAnterior.setEnabled(false);
        txtKmAnterior.setOpaque(false);
        txtKmAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKmAnteriorKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel19.setText("Kilometraje del reparto actual");

        txtKmActual.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtKmActual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKmActual.setText("0");
        txtKmActual.setEnabled(false);
        txtKmActual.setOpaque(false);
        txtKmActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKmActualKeyReleased(evt);
            }
        });

        txtKmRecorrido.setEditable(false);
        txtKmRecorrido.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtKmRecorrido.setForeground(new java.awt.Color(255, 51, 0));
        txtKmRecorrido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKmRecorrido.setText("0");
        txtKmRecorrido.setBorder(null);
        txtKmRecorrido.setOpaque(false);

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel20.setText("Kilometros recorrido");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKmRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKmActual)
                            .addComponent(txtKmAnterior))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKmAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKmActual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKmRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        Blanco.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 325, 330, 120));

        cbCerrar.setBackground(new java.awt.Color(0, 102, 102));
        cbCerrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        cbCerrar.setForeground(new java.awt.Color(255, 255, 255));
        cbCerrar.setText("FINALIZAR TRABAJO");
        cbCerrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbCerrar.setEnabled(false);
        cbCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cbCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cbCerrar.setIconTextGap(10);
        cbCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCerrarActionPerformed(evt);
            }
        });
        Blanco.add(cbCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 38, 325, 31));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASTOS ADMINISTRATIVOS REGISTRADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12), new java.awt.Color(27, 57, 84))); // NOI18N
        jPanel11.setOpaque(false);

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel18.setText("Total del móvil en el día");

        txtGastosTotal.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtGastosTotal.setForeground(new java.awt.Color(255, 51, 0));
        txtGastosTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGastosTotal.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(txtGastosTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGastosTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        Blanco.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 264, 330, -1));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 9));

        btnNuevo.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevo.setBorder(null);
        btnNuevo.setText("NUEVO");
        btnNuevo.setBgHover(new java.awt.Color(1, 123, 123));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnNuevo.setIconTextGap(0);
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.NOTE_ADD);
        btnNuevo.setPixels(0);
        btnNuevo.setSizeIcon(60.0F);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnModificar.setBackground(new java.awt.Color(0, 102, 102));
        btnModificar.setBorder(null);
        btnModificar.setText("MODIFICAR");
        btnModificar.setBgHover(new java.awt.Color(1, 123, 123));
        btnModificar.setEnabled(false);
        btnModificar.setFocusPainted(false);
        btnModificar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModificar.setIconTextGap(0);
        btnModificar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnModificar.setPixels(0);
        btnModificar.setSizeIcon(60.0F);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel5.add(btnModificar);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setBorder(null);
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBgHover(new java.awt.Color(1, 123, 123));
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardar.setIconTextGap(0);
        btnGuardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar.setPixels(0);
        btnGuardar.setSizeIcon(60.0F);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar);

        btnCancelar.setBackground(new java.awt.Color(0, 102, 102));
        btnCancelar.setBorder(null);
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBgHover(new java.awt.Color(1, 123, 123));
        btnCancelar.setEnabled(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancelar.setIconTextGap(0);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setPixels(0);
        btnCancelar.setSizeIcon(60.0F);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancelar);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setText("SALIR");
        btnSalir.setBgHover(new java.awt.Color(1, 123, 123));
        btnSalir.setEffectFocus(true);
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(60.0F);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel5.add(btnSalir);

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 511, 81));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 18, 24, 20));
        jPanel6.add(txtOpcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 50, 44, -1));
        jPanel6.add(txtResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 20, 50, -1));
        jPanel6.add(txtChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 50, 50, -1));
        jPanel6.add(txtMovil, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 60, -1));

        txtFechaIF.setEditable(false);
        txtFechaIF.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaIF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaIFActionPerformed(evt);
            }
        });
        jPanel6.add(txtFechaIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 60, -1));

        txtTotalL.setText("0");
        jPanel6.add(txtTotalL, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, 60, -1));
        jPanel6.add(txtIdMovil, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 60, -1));
        jPanel6.add(txtCodArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 60, -1));

        txtRecambioL.setText("0");
        jPanel6.add(txtRecambioL, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 60, -1));

        txtDevueltosL.setText("0");
        jPanel6.add(txtDevueltosL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 60, -1));

        txtTotalVentasL.setText("0");
        jPanel6.add(txtTotalVentasL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 30, 60, -1));

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });
        jPanel6.add(btnRestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        btnBuscarArticulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscarArticulo.setForeground(new java.awt.Color(0, 0, 51));
        btnBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnBuscarArticulo.setText("F9-Agregar");
        btnBuscarArticulo.setBorderPainted(false);
        btnBuscarArticulo.setContentAreaFilled(false);
        btnBuscarArticulo.setEnabled(false);
        btnBuscarArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArticuloActionPerformed(evt);
            }
        });
        jPanel6.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 39, -1));

        txtPresentadoL.setText("0");
        jPanel6.add(txtPresentadoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 60, -1));

        txtDiferenciaL.setText("0");
        txtDiferenciaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiferenciaLActionPerformed(evt);
            }
        });
        jPanel6.add(txtDiferenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 50, 60, -1));

        txtIdResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdResponsableActionPerformed(evt);
            }
        });
        jPanel6.add(txtIdResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 60, -1));

        txtValorRA.setText("0");
        txtValorRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorRAActionPerformed(evt);
            }
        });
        jPanel6.add(txtValorRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 50, 60, -1));
        jPanel6.add(txtIdChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, 60, -1));

        txtValorCompra.setText("0");
        jPanel6.add(txtValorCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 30, 60, 20));

        txtValorTransferencia.setText("0");
        jPanel6.add(txtValorTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 50, 60, 20));

        jLabel32.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("VARIANCIÓN");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 50, 110, 22));

        txtCAnterior.setEditable(false);
        txtCAnterior.setBackground(new java.awt.Color(255, 255, 255));
        txtCAnterior.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCAnterior.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCAnterior.setText("0");
        txtCAnterior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCAnteriorActionPerformed(evt);
            }
        });
        jPanel6.add(txtCAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 26, 43, 22));

        txtCdif.setEditable(false);
        txtCdif.setBackground(new java.awt.Color(255, 255, 255));
        txtCdif.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCdif.setForeground(new java.awt.Color(0, 153, 0));
        txtCdif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCdif.setText("0");
        txtCdif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        txtCdif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCdifActionPerformed(evt);
            }
        });
        jPanel6.add(txtCdif, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 26, 42, 22));

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("DIF.");
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 26, 20, 22));

        txtCdifACA.setEditable(false);
        txtCdifACA.setBackground(new java.awt.Color(255, 255, 255));
        txtCdifACA.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCdifACA.setForeground(new java.awt.Color(205, 0, 0));
        txtCdifACA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCdifACA.setText("0");
        txtCdifACA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 0, 0)));
        jPanel6.add(txtCdifACA, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 50, 42, 22));

        jLabel36.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("STOCK");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 26, -1, 22));

        jLabel31.setFont(new java.awt.Font("Roboto Black", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("INFO. ANTERIOR DE CAJAS");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(752, 6, 160, 70));

        txtCaja.setEditable(false);
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 60, -1));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        MnOpcionesReparto.setText("OPCIONES");
        MnOpcionesReparto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setBackground(new java.awt.Color(255, 255, 255));
        itemNuevo.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_note_add_black_24.png"))); // NOI18N
        itemNuevo.setText("NUEVO");
        itemNuevo.setBorder(null);
        itemNuevo.setOpaque(true);
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemNuevo);

        itemModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemModificar.setBackground(new java.awt.Color(255, 255, 255));
        itemModificar.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_edit_black_24.png"))); // NOI18N
        itemModificar.setText("GUARDAR MODIFICACIÓN                                                 ");
        itemModificar.setBorder(null);
        itemModificar.setEnabled(false);
        itemModificar.setOpaque(true);
        itemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemModificar);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setBackground(new java.awt.Color(255, 255, 255));
        itemGuardar.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_save_black_24.png"))); // NOI18N
        itemGuardar.setText("GUARDAR NUEVO");
        itemGuardar.setBorder(null);
        itemGuardar.setEnabled(false);
        itemGuardar.setOpaque(true);
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemGuardar);

        itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelar.setBackground(new java.awt.Color(255, 255, 255));
        itemCancelar.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_cancel_black_24.png"))); // NOI18N
        itemCancelar.setText("CANCELAR");
        itemCancelar.setBorder(null);
        itemCancelar.setEnabled(false);
        itemCancelar.setOpaque(true);
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemCancelar);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOpaque(true);
        MnOpcionesReparto.add(jSeparator1);

        itemBuscarArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        itemBuscarArticulo.setBackground(new java.awt.Color(255, 255, 255));
        itemBuscarArticulo.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemBuscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_add_black_24.png"))); // NOI18N
        itemBuscarArticulo.setText("AGREGAR PRODUCTO");
        itemBuscarArticulo.setBorder(null);
        itemBuscarArticulo.setEnabled(false);
        itemBuscarArticulo.setOpaque(true);
        itemBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarArticuloActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemBuscarArticulo);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setBackground(new java.awt.Color(255, 255, 255));
        itemQuitar.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_remove_black_24.png"))); // NOI18N
        itemQuitar.setText("QUITAR PRODUCTO");
        itemQuitar.setBorder(null);
        itemQuitar.setEnabled(false);
        itemQuitar.setOpaque(true);
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemQuitar);

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator3.setOpaque(true);
        MnOpcionesReparto.add(jSeparator3);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setBackground(new java.awt.Color(255, 255, 255));
        itemSalir.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
        itemSalir.setText("SALIR");
        itemSalir.setBorder(null);
        itemSalir.setOpaque(true);
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        MnOpcionesReparto.add(itemSalir);

        jMenuBar1.add(MnOpcionesReparto);

        MnImprimirReparto.setBackground(new java.awt.Color(255, 255, 255));
        MnImprimirReparto.setText("IMPRIMIR");
        MnImprimirReparto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        MnImprimirReparto.setOpaque(true);

        itemHojaApunte1.setBackground(new java.awt.Color(255, 255, 255));
        itemHojaApunte1.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemHojaApunte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_text_snippet_black_24.png"))); // NOI18N
        itemHojaApunte1.setText("HOJA DE CONTROL DE STOCK");
        itemHojaApunte1.setBorder(null);
        itemHojaApunte1.setEnabled(false);
        itemHojaApunte1.setOpaque(true);
        itemHojaApunte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHojaApunte1ActionPerformed(evt);
            }
        });
        MnImprimirReparto.add(itemHojaApunte1);

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator7.setOpaque(true);
        MnImprimirReparto.add(jSeparator7);

        itemHojaApunte.setBackground(new java.awt.Color(255, 255, 255));
        itemHojaApunte.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemHojaApunte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_text_snippet_black_24.png"))); // NOI18N
        itemHojaApunte.setText("BOTETA DE RECEPCIÓN EN DEPÓSITO/ALMACÉN                   ");
        itemHojaApunte.setBorder(null);
        itemHojaApunte.setEnabled(false);
        itemHojaApunte.setOpaque(true);
        itemHojaApunte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHojaApunteActionPerformed(evt);
            }
        });
        MnImprimirReparto.add(itemHojaApunte);

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator5.setOpaque(true);
        MnImprimirReparto.add(jSeparator5);

        itemHojaR.setBackground(new java.awt.Color(255, 255, 255));
        itemHojaR.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemHojaR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_text_snippet_black_24.png"))); // NOI18N
        itemHojaR.setText("INFORME COMPLETO < CONSOLIDADO DEL REPARTO >");
        itemHojaR.setBorder(null);
        itemHojaR.setEnabled(false);
        itemHojaR.setOpaque(true);
        itemHojaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHojaRActionPerformed(evt);
            }
        });
        MnImprimirReparto.add(itemHojaR);

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator6.setOpaque(true);
        MnImprimirReparto.add(jSeparator6);

        itemHojaR1.setBackground(new java.awt.Color(255, 255, 255));
        itemHojaR1.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        itemHojaR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_text_snippet_black_24.png"))); // NOI18N
        itemHojaR1.setText("INFORE SIMPLE < CONSOLIDADO DEL REPARTO >");
        itemHojaR1.setBorder(null);
        itemHojaR1.setEnabled(false);
        itemHojaR1.setOpaque(true);
        itemHojaR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHojaR1ActionPerformed(evt);
            }
        });
        MnImprimirReparto.add(itemHojaR1);

        jMenuBar1.add(MnImprimirReparto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
        if (txtCantidad.getText().isEmpty()) {
            txtCantidad.requestFocus();
        } else {
            btnAdd.doClick();
        }
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        // TODO add your handling code here:
        try {
            dlgBuscarArticuloReparto bac = new dlgBuscarArticuloReparto(null, true);
            //bac.setLocationRelativeTo(null);
            bac.setLocation(330, 22);
            switch (txtOpcion.getText().trim()) {
                case "C" ->
                    bac.tituloOperacion("REGISTRAR CARGA TOTAL DEL REPARTO");
                case "R" ->
                    bac.tituloOperacion("REGISTRAR RECAMBIOS/VENCIDOS");
                case "D" ->
                    bac.tituloOperacion("REGISTRAR DEVUELTOS/CARGAS SOBRANTES");
                default -> {
                }
            }
            bac.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

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
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        switch (txtOpcion.getText()) {
            case "C" -> {
                controlReparto.addTabla(tbDetalleReparto);
                txtProducto.setText("");
                txtCantidad.setText("");
                txtCodArticulo.setText("");
                habilitarCANT();
                cant();
                calcularDiferencia();
                Renders();
                btnBuscarArticuloActionPerformed(null);
            }
            case "R" -> {
                controlReparto.addTablaRecabios(tbDetalleReparto);
                txtProducto.setText("");
                txtCantidad.setText("");
                txtCodArticulo.setText("");
                habilitarCANT();
                cant();
                calcularDiferencia();
                Renders();
                btnBuscarArticuloActionPerformed(null);
            }
            case "D" -> {
                controlReparto.addTablaDevueltos(tbDetalleReparto);
                txtProducto.setText("");
                txtCantidad.setText("");
                txtCodArticulo.setText("");
                habilitarCANT();
                cant();
                calcularDiferencia();
                Renders();
                btnBuscarArticuloActionPerformed(null);

            }
            default -> {
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtCantidad);
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtRecambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRecambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRecambioActionPerformed

    private void txtDevueltosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDevueltosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDevueltosActionPerformed

    private void txtTotalVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentasActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        try {
            controlReparto.delRenglon(tbDetalleReparto);
            cant();
            calcularDiferencia();
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_btnRestarActionPerformed

    private void txtComisionRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComisionRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComisionRActionPerformed

    private void txtComisionCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComisionCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComisionCActionPerformed

    private void txtPresentadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresentadoKeyPressed
        // TODO add your handling code here:
        if (!txtPresentado.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtPresentado);
        }
    }//GEN-LAST:event_txtPresentadoKeyPressed

    private void txtPresentadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresentadoKeyTyped
        // TODO add your handling code here:
        int limite = 10;
        if (txtPresentado.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPresentadoKeyTyped

    private void txtPresentadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresentadoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtPresentado.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtPresentado.setText(df.format(Integer.valueOf(txtPresentado.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtPresentado.setText("0");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (txtPresentado.getText().equals("")) {
            txtPresentado.setText("0");
        } else {
            DecimalFormat dff = new DecimalFormat("#0");
            txtPresentadoL.setText(dff.format(Integer.valueOf(txtPresentado.getText().trim().replace(".", "").replace(",", ""))));
        }
        calcularComisiones();
        calcularDiferencia();
    }//GEN-LAST:event_txtPresentadoKeyReleased

    private void txtDiferenciaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiferenciaLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiferenciaLActionPerformed

    private void txtContenedorIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorIKeyReleased
        // TODO add your handling code here:
        calcularCajas();
    }//GEN-LAST:event_txtContenedorIKeyReleased

    private void txtContenedorFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorFKeyReleased
        // TODO add your handling code here:
        calcularCajas();
    }//GEN-LAST:event_txtContenedorFKeyReleased

    private void txtDiferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiferenciaActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void txtIdRepartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdRepartoActionPerformed
        // TODO add your handling code here:
        /*   if (!txtIdReparto.getText().isEmpty()) {
            opcion = "M";
            controlReparto.array.vaciar();
            CabecerasTablas.limpiarTablasRepartos(tbDetalleReparto);
            controlReparto.aModifcar();
            calcularComisiones();
            controlReparto.listarDetalleReparto_A_Modificar(tbDetalleReparto);
            Renders();
        } else {
            Cancelar();
        }*/

    }//GEN-LAST:event_txtIdRepartoActionPerformed

    private void txtIdResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdResponsableActionPerformed

    private void txtFechaIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaIFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaIFActionPerformed

    private void txtContenedorIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorIActionPerformed
        // TODO add your handling code here:
        txtContenedorF.requestFocus();
    }//GEN-LAST:event_txtContenedorIActionPerformed

    private void txtPresentadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPresentadoActionPerformed
        // TODO add your handling code here:
        txtKmActual.requestFocus();
    }//GEN-LAST:event_txtPresentadoActionPerformed

    private void cbCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCerrarActionPerformed
        // TODO add your handling code here:
        if (cbCerrar.isSelected()) {
            Mensajes.informacion("Con esta acción se dara por finalizada toda operación del reparto."
                    + "\nSe consolidaran los datos para los respectivos reportes.");
        }
    }//GEN-LAST:event_cbCerrarActionPerformed

    private void itemHojaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHojaRActionPerformed
        // TODO add your handling code here:
        
        VisorReportes vr = new VisorReportes(null, true);
        try {
            prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\movimiento_reparto.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("id_reparto", Integer.parseInt(txtIdReparto.getText().trim()));
            parametros.put("estado", txtEstado.getText());
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
        
    }//GEN-LAST:event_itemHojaRActionPerformed

    private void itemHojaApunteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHojaApunteActionPerformed
        // TODO add your handling code here:
        VisorReportes vr = new VisorReportes(null, true);
        try {
            prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\Hoja_reparto.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("id_reparto", Integer.parseInt(txtIdReparto.getText().trim()));
            parametros.put("estado", txtEstado.getText());
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
    }//GEN-LAST:event_itemHojaApunteActionPerformed

    private void btnRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRRActionPerformed
        // TODO add your handling code here:
        String letras = d.Convertir(txtComisionR.getText().replace(".", "").replace(",", ""), true);
        VisorReportes vr = new VisorReportes(null, true);
        try {
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\reciboDinero.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("idreparto", Integer.parseInt(txtIdReparto.getText().trim()));
            parametros.put("idvendedor", Integer.parseInt(txtIdResponsable.getText().trim()));
            parametros.put("Letras", letras);
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
    }//GEN-LAST:event_btnRRActionPerformed

    private void btnRCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRCActionPerformed

        String letras = d.Convertir(txtComisionC.getText().replace(".", "").replace(",", ""), true);
        VisorReportes vr = new VisorReportes(null, true);
        try {
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\reciboDinero.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("idreparto", Integer.parseInt(txtIdReparto.getText().trim()));
            parametros.put("idvendedor", Integer.parseInt(txtIdChofer.getText().trim()));
            parametros.put("Letras", letras);
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
    }//GEN-LAST:event_btnRCActionPerformed

    private void txtKmAnteriorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKmAnteriorKeyReleased
        // TODO add your handling code here:
        calcularKilometraje();
    }//GEN-LAST:event_txtKmAnteriorKeyReleased

    private void txtKmActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKmActualKeyReleased
        // TODO add your handling code here:
        calcularKilometraje();
    }//GEN-LAST:event_txtKmActualKeyReleased

    private void txtValorRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorRAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorRAActionPerformed

    private void itemHojaR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHojaR1ActionPerformed
        VisorReportes vr = new VisorReportes(null, true);
        try {
            prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("id_reparto", Integer.parseInt(txtIdReparto.getText().trim()));
            parametros.put("estado", txtEstado.getText());
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
    }//GEN-LAST:event_itemHojaR1ActionPerformed

    private void itemHojaApunte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHojaApunte1ActionPerformed
        // TODO add your handling code here:
        VisorReportes vr = new VisorReportes(null, true);
        try {
            prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\ControlSTx.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put("vendedor", cboResponsable.getSelectedItem());
            parametros.put("fecha", txtFechaI.getText());
            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
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
    }//GEN-LAST:event_itemHojaApunte1ActionPerformed

    private void btnCargarRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRAActionPerformed
        // TODO add your handling code here:
        if (cboResponsable.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un responsable para filtrar el reparto anterior");
        } else {
            try {
                dlgConsultarRepartoAnterior consReparto = new dlgConsultarRepartoAnterior(null, true);
                consReparto.setLocationRelativeTo(null);
                consReparto.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("Servidor no esta activo");
            }
        }
    }//GEN-LAST:event_btnCargarRAActionPerformed

    private void btnCompraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraAActionPerformed
        // TODO add your handling code here:
        if (cboResponsable.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un responsable para filtrar las compras del día");
        } else {
            try {
                dlgConsultarCompras1 consCompras = new dlgConsultarCompras1(null, true);
                consCompras.setLocationRelativeTo(null);
                consCompras.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("Servidor no esta activo");
            }
        }
    }//GEN-LAST:event_btnCompraAActionPerformed

    private void btnCargarTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTransferenciasActionPerformed
        // TODO add your handling code here:
        if (cboResponsable.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un responsable para filtrar las trasferencia realizada");
        } else {
            try {
                dlgConsultarTransferencia consT = new dlgConsultarTransferencia(null, true);
                consT.setLocationRelativeTo(null);
                consT.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("Servidor no esta activo");
            }
        }
    }//GEN-LAST:event_btnCargarTransferenciasActionPerformed

    private void cboResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboResponsableActionPerformed
        // TODO add your handling code here:
        if (opcion.equals("N")) {
            if (cboResponsable.getSelectedIndex() == 0) {
                txtIdMovil.setText("");
                lbInfoChofer.setText("");
                lbInfoMovil.setText("");
                lbComC.setText("");
                lbComR.setText("");
                lbComC1.setText("");
                lbComR1.setText("");

                txtResponsable.setText("");
                txtChofer.setText("");
                txtMovil.setText("");
                txtIdChofer.setText("");
            } else {
                txtResponsable.setText(cboResponsable.getSelectedItem().toString());
                //prepararBD();
                try {
                    String resp;
                    resp = cargarComboBox.getCodidgo(cboResponsable);
                    try {
                        prepararBD();
                        ResultSet rs = sentencia.executeQuery("SELECT ven_codigo,idmovil, movil, ven_comision FROM v_vendedores WHERE ven_codigo=" + resp);
                        rs.last();
                        txtIdResponsable.setText(String.valueOf(rs.getInt("ven_codigo")));
                        txtIdMovil.setText(String.valueOf(rs.getInt("idmovil")));
                        lbInfoMovil.setText(" Referencia: " + rs.getString("movil"));
                        txtMovil.setText(rs.getString("movil"));
                        lbComR.setText(String.valueOf(rs.getDouble("ven_comision")));
                        lbComR1.setText(String.valueOf(rs.getDouble("ven_comision")) + "%");

                        rs.close();
                        sentencia.close();
                        sentenciaMovil.close();
                        stTransaccionMovil.close();
                        con.close();
                        conMovil.close();
                    } catch (SQLException ex) {
                        Mensajes.error("Error al querer obtener ID del móvil: " + ex.getMessage());
                    }
                } catch (Exception pr) {
                }
                try {
                    String idm;
                    idm = txtIdMovil.getText();
                    try {
                        prepararBD();
                        ResultSet rs = sentencia.executeQuery("SELECT ven_codigo,ven_nombre, ven_comision FROM v_vendedores WHERE idmovil=" + idm + " AND idfuncion=1");
                        rs.last();
                        lbInfoChofer.setText(" Chofer: " + rs.getString("ven_nombre"));
                        txtIdChofer.setText(rs.getString("ven_codigo"));
                        txtChofer.setText(rs.getString("ven_nombre"));
                        lbComC.setText(String.valueOf(rs.getDouble("ven_comision")));
                        lbComC1.setText(String.valueOf(rs.getDouble("ven_comision")) + "%");
                        rs.close();
                        sentencia.close();
                        sentenciaMovil.close();
                        stTransaccionMovil.close();
                        con.close();
                        conMovil.close();
                    } catch (SQLException ex) {
                        Mensajes.error("Error al querer obtener valor del chofer: " + ex.getMessage());
                    }
                } catch (Exception pr) {
                }

                try {
                    String idm;
                    idm = txtIdMovil.getText();
                    try {
                        prepararBD();
                        ResultSet rs = sentencia.executeQuery("SELECT SUM(ga_importe) AS total FROM gastos WHERE ga_idmovil=" + idm + " AND caja_ca_id=" + txtCaja.getText().trim() + " AND ga_indicador='S' AND tipo='A'");
                        rs.last();
                        if (rs.getString("total") == null) {
                            txtGastosTotal.setText("0");
                        } else {
                            DecimalFormat df = new DecimalFormat("#,###");
                            txtGastosTotal.setText(df.format(Integer.parseInt(String.valueOf(rs.getString("total")).replace(".", "").replace(",", ""))));
                        }
                        rs.close();
                        sentencia.close();
                        sentenciaMovil.close();
                        stTransaccionMovil.close();
                        con.close();
                        conMovil.close();
                    } catch (SQLException ex) {
                        Mensajes.error("Error al querer obtener valor del gasto: " + ex.getMessage());
                    }
                } catch (Exception pr) {
                }
            }
        }
    }//GEN-LAST:event_cboResponsableActionPerformed

    private void cboResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboResponsableKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCargarRA.doClick();
        }
    }//GEN-LAST:event_cboResponsableKeyPressed

    private void chAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chAgregarActionPerformed
        // TODO add your handling code here:
        if (chAgregar.isSelected()) {
            if (opcion.equals("N")) {
                rdCargaT.setEnabled(true);
                rdCargaT.setSelected(true);
                txtOpcion.setText("C");
                rdDevueltos.setEnabled(false);
                rdRecambios.setEnabled(false);
                btnBuscarArticulo.setEnabled(true);
                btnBuscarArticulo.doClick();

            } else if (opcion.equals("M")) {
                rdCargaT.setEnabled(false);
                rdDevueltos.setEnabled(true);
                rdDevueltos.setSelected(true);
                txtOpcion.setText("D");
                rdRecambios.setEnabled(true);
                btnBuscarArticulo.setEnabled(true);
                btnBuscarArticulo.doClick();
            }
            itemBuscarArticulo.setEnabled(true);

        } else {
            rdCargaT.setEnabled(false);
            rdDevueltos.setSelected(true);
            txtOpcion.setText("");
            rdDevueltos.setEnabled(false);
            rdRecambios.setEnabled(false);
            btnBuscarArticulo.setEnabled(false);
            itemBuscarArticulo.setEnabled(false);
        }
    }//GEN-LAST:event_chAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        //limpiarCampos();
        //Cancelar();
        String cod = GestionarReparto.getCodigoReparto();
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargarComboBox.cargarResponsable(cboResponsable, "SELECT * FROM v_vendedores WHERE idfuncion=2 AND ven_indicador='S'");
        txtIdReparto.setText(cod);
        txtFechaI.setText(Fecha.fechaCorrectaFachada());
        txtFechaIF.setText(Fecha.fechaCorrecta());
        txtHoraI.setText(Fecha.darHoraSinSS());
        txtEstado.setText("INICIALIZANDO REPARTO");
        //
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        itemModificar.setEnabled(false);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        itemSalir.setEnabled(false);
        itemBuscarArticulo.setEnabled(false);
        itemQuitar.setEnabled(true);
        //
        cboResponsable.setEnabled(true);
        btnCargarRA.setEnabled(true);
        btnCompraA.setEnabled(true);
        btnCargarTransferencias.setEnabled(true);
        chAgregar.setEnabled(true);
        //txtIdReparto.setEnabled(false);
        txtContenedorI.setEnabled(true);
        txtKmAnterior.setEnabled(true);
        //
        tbDetalleReparto.setEnabled(true);
        cboResponsable.requestFocus();
        //cboResponsable.setPopupVisible(true);
        opcion = "N";
        System.out.println(UsuarioL = Login.getUsuarioLogueado());
        cant();
        calcularCajas();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cod = GestionarReparto.getCodigoReparto();
        txtIdReparto.setText(cod);
        if (cboResponsable.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione el responsable de este reparto");
            cboResponsable.requestFocus();
            cboResponsable.setPopupVisible(true);
        } else if (tbDetalleReparto.getRowCount() == 0) {
            Mensajes.informacion("El reparto no puede guardarse vacío.\nFavor cargue los productos que estaran disponibles para la venta");
            btnCargarRA.requestFocus();
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = UsuarioL = Login.getUsuarioLogueado();
                        prepararBD();
                        con.setAutoCommit(false);
                        conMovil.setAutoCommit(false);
                        int totalCo = Integer.parseInt(txtComisionR.getText().replace(".", "").replace(",", "")) + Integer.parseInt(txtComisionC.getText().replace(".", "").replace(",", ""));
                        String sql = "insert into reparto values(" + txtIdReparto.getText() + "," + txtCaja.getText() + "," + txtIdResponsable.getText() + "," + lbComR.getText().trim()
                                + "," + txtIdMovil.getText().trim() + ",'" + txtMovil.getText() + "'," + txtIdChofer.getText().trim() + ", '" + txtChofer.getText() + "'," + lbComC.getText().trim()
                                + ",'" + txtFechaIF.getText() + "','" + txtHoraI.getText() + "'," + txtValorRA.getText() + "," + txtValorCompra.getText() + "," + txtValorTransferencia.getText() + "," + txtTotalL.getText()
                                + "," + txtRecambioL.getText() + "," + txtDevueltosL.getText() + "," + txtTotalVentasL.getText() + "," + txtPresentadoL.getText()
                                + "," + txtDiferenciaL.getText() + "," + txtCAnterior.getText() + "," + txtCdif.getText() + "," + txtCdifACA.getText() + "," + txtContenedorI.getText() + "," + txtContenedorT.getText() + "," + txtContenedorF.getText() + "," + txtContenedorV.getText() + "," + txtContenedorD.getText() + "," + txtContenedorDA.getText() + "," + txtGastosTotal.getText().trim().replace(".", "").replace(",", "") + "," + totalCo + "," + txtKmAnterior.getText() + "," + txtKmActual.getText() + "," + txtKmRecorrido.getText()
                                + ",'S','N','" + usuario + "')";
                        String sql2 = "INSERT INTO comisiones (idreparto,idcaja,idvendedor,fecha,monto,comision,totalComision,usuario,estado)"
                                + "VALUES(" + txtIdReparto.getText() + "," + txtCaja.getText() + "," + txtIdResponsable.getText() + ",'" + txtFechaIF.getText() + "'," + txtPresentadoL.getText()
                                + "," + lbComR.getText() + "," + txtComisionR.getText().replace(".", "").replace(",", "") + ",'" + usuario + "','S'" + ")";
                        String sql3 = "INSERT INTO comisiones (idreparto,idcaja,idvendedor,fecha,monto,comision,totalComision,usuario,estado)"
                                + "VALUES(" + txtIdReparto.getText() + "," + txtCaja.getText() + "," + txtIdChofer.getText() + ",'" + txtFechaIF.getText() + "'," + txtPresentadoL.getText()
                                + "," + lbComC.getText() + "," + txtComisionC.getText().replace(".", "").replace(",", "") + ",'" + usuario + "','S'" + ")";
                        stTransaccionMovil.executeUpdate(sql);
                        stTransaccionMovil.executeUpdate(sql2);
                        stTransaccionMovil.executeUpdate(sql3);
                        int fila = tbDetalleReparto.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {
                                tbDetalleReparto.getValueAt(j, 0).toString(),//0

                                tbDetalleReparto.getValueAt(j, 4).toString(),//1
                                tbDetalleReparto.getValueAt(j, 5).toString(),//2
                                tbDetalleReparto.getValueAt(j, 6).toString(),//3

                                tbDetalleReparto.getValueAt(j, 7).toString(),//4
                                tbDetalleReparto.getValueAt(j, 8).toString(),//5
                                tbDetalleReparto.getValueAt(j, 9).toString(),//6
                                tbDetalleReparto.getValueAt(j, 10).toString(),//7
                                tbDetalleReparto.getValueAt(j, 11).toString(),//8
                                tbDetalleReparto.getValueAt(j, 12).toString(),//9
                                tbDetalleReparto.getValueAt(j, 13).toString(),//10
                                tbDetalleReparto.getValueAt(j, 14).toString(),//11
                                tbDetalleReparto.getValueAt(j, 15).toString()};//12
                            sql = "insert into detalle_reparto values(" + txtIdReparto.getText() + "," + filas[0] + "," + filas[1]
                                    + "," + filas[2] + "," + filas[3] + "," + filas[4] + "," + filas[5] + "," + filas[6] + "," + filas[7]
                                    + "," + filas[8] + "," + filas[9] + "," + filas[10] + "," + filas[11] + "," + filas[12] + ")";
                            stTransaccionMovil.executeUpdate(sql);

                        }
                        con.commit();
                        conMovil.commit();
                        Mensajes.informacion("El reparto ha sido regitrada exitosamente");
                        sentencia.close();
                        sentenciaMovil.close();
                        stTransaccionMovil.close();
                        con.close();
                        conMovil.close();
                    } catch (SQLException e) {
                        try {
                            con.rollback();
                            conMovil.rollback();
                            Mensajes.error("TRANSACCION FALLIDA. LOS DATOS NO FUERON GUARDADOS EN LA BD." + e.getMessage().toUpperCase());
                            sentencia.close();
                            sentenciaMovil.close();
                            stTransaccionMovil.close();
                            con.close();
                            conMovil.close();
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int cod = Integer.parseInt(txtIdReparto.getText().trim());
        String cerrado;
        if (cbCerrar.isSelected()) {
            cerrado = "S";
        } else {
            cerrado = "N";
        }
        try {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea aplicar cambios al contenido de este reparto?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    String usuario = UsuarioL = Login.getUsuarioLogueado();
                    prepararBD();
                    con.setAutoCommit(false);
                    conMovil.setAutoCommit(false);
                    int totalCo = Integer.parseInt(txtComisionR.getText().replace(".", "").replace(",", "")) + Integer.parseInt(txtComisionC.getText().replace(".", "").replace(",", ""));
                    String sql = "UPDATE reparto SET valor_ra=" + txtValorRA.getText() + ", valor_c=" + txtValorCompra.getText() + ", valor_t=" + txtValorTransferencia.getText() + ", valor_total=" + txtTotalL.getText()
                            + ", valor_recambio=" + txtRecambioL.getText() + ", valor_devuelto=" + txtDevueltosL.getText() + ", efectivo_entregar=" + txtTotalVentasL.getText() + ", efectivo_entregado=" + txtPresentadoL.getText()
                            + ", diferencia=" + txtDiferenciaL.getText() + ", recolector_inicio=" + txtContenedorI.getText() + ", recolector_total=" + txtContenedorT.getText() + ", recolector_fin=" + txtContenedorF.getText()
                            + ", recolector_vacios=" + txtContenedorV.getText() + ", recolector_dif=" + txtContenedorD.getText() + ", recolector_dacum=" + txtContenedorDA.getText() + ", gasto_reparto=" + txtGastosTotal.getText().replace(".", "").replace(",", "") + ", comision_reparto=" + totalCo
                            + ", km_anterior=" + txtKmAnterior.getText() + ", km_actual=" + txtKmActual.getText() + ", km_recorrido=" + txtKmRecorrido.getText() + ", cerrado='" + cerrado + "', usuario='" + usuario + "' "
                            + "WHERE id_reparto=" + cod;

                    String sql2 = "UPDATE comisiones SET monto=" + txtPresentadoL.getText() + ",comision=" + lbComR.getText() + ",totalcomision=" + txtComisionR.getText().replace(".", "").replace(",", "") + ",usuario='" + usuario + "'"
                            + " WHERE idreparto=" + txtIdReparto.getText() + " AND idvendedor=" + txtIdResponsable.getText();

                    String sql3 = "UPDATE comisiones SET monto=" + txtPresentadoL.getText() + ",comision=" + lbComC.getText() + ",totalcomision=" + txtComisionC.getText().replace(".", "").replace(",", "") + ",usuario='" + usuario + "'"
                            + " WHERE idreparto=" + txtIdReparto.getText() + " AND idvendedor=" + txtIdChofer.getText();
                    stTransaccionMovil.executeUpdate(sql);
                    stTransaccionMovil.executeUpdate(sql2);
                    stTransaccionMovil.executeUpdate(sql3);

                    int fila = tbDetalleReparto.getRowCount();
                    for (int j = 0; j < fila; j++) {
                        String filas[] = {
                            tbDetalleReparto.getValueAt(j, 0).toString(),//0 id
                            tbDetalleReparto.getValueAt(j, 4).toString(),//1 RA
                            tbDetalleReparto.getValueAt(j, 5).toString(),//2 C
                            tbDetalleReparto.getValueAt(j, 6).toString(),//3 T
                            tbDetalleReparto.getValueAt(j, 7).toString(),//4 carga total
                            tbDetalleReparto.getValueAt(j, 8).toString(),//5 monto carga total
                            tbDetalleReparto.getValueAt(j, 9).toString(),//6 recambio
                            tbDetalleReparto.getValueAt(j, 10).toString(),//7 monto recambio
                            tbDetalleReparto.getValueAt(j, 11).toString(),//8 vendido
                            tbDetalleReparto.getValueAt(j, 12).toString(),//9 monto venta
                            tbDetalleReparto.getValueAt(j, 13).toString(),//10 monto costo
                            tbDetalleReparto.getValueAt(j, 14).toString(),//11 devolucion
                            tbDetalleReparto.getValueAt(j, 15).toString()};//12 monto devolucion

                        if (tbDetalleReparto.getValueAt(j, 16).toString().equals("N")) {

                            sql = "insert into detalle_reparto values(" + txtIdReparto.getText() + "," + filas[0] + "," + filas[1]
                                    + "," + filas[2] + "," + filas[3] + "," + filas[4] + "," + filas[5] + "," + filas[6] + "," + filas[7]
                                    + "," + filas[8] + "," + filas[9] + "," + filas[10] + "," + filas[11] + "," + filas[12] + ")";
                            stTransaccionMovil.executeUpdate(sql);
                        } else if (tbDetalleReparto.getValueAt(j, 16).toString().equals("V")) {
                            sql = "UPDATE detalle_reparto SET c=" + filas[2] + ", t=" + filas[3] + ", carga_total=" + filas[4] + ", monto_carga_total=" + filas[5]
                                    + ", recambio=" + filas[6] + ", monto_recambio=" + filas[7] + ", venta=" + filas[8] + ", monto_venta=" + filas[9]
                                    + ", monto_costo=" + filas[10] + ", devuelve=" + filas[11] + ", monto_devuelve=" + filas[12] + " WHERE idreparto=" + cod + " AND idproducto=" + filas[0];
                            stTransaccionMovil.executeUpdate(sql);
                        }

                    }
                    con.commit();
                    conMovil.commit();

                    Mensajes.informacion("Las modificaciones fueron aplicadas exitosamente");
                    sentencia.close();
                    sentenciaMovil.close();
                    stTransaccionMovil.close();
                    con.close();
                    conMovil.close();
                    if (cerrado.equals("S")) {
                        txtEstado.setText("REPARTO CERRADO");
                    }
                } catch (SQLException e) {
                    try {
                        con.rollback();
                        conMovil.rollback();
                        Mensajes.error("TRANSACCION FALLIDA. LOS DATOS NO FUERON GUARDADOS EN LA BD." + e.getMessage().toUpperCase());
                        sentencia.close();
                        sentenciaMovil.close();
                        stTransaccionMovil.close();
                        con.close();
                        conMovil.close();
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
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea cancelar esta operación?");
        if (rpta == 0) {
            controlReparto.array.vaciar();
            limpiarCampos();
            btnNuevo.setEnabled(true);
            itemNuevo.setEnabled(true);
            btnModificar.setEnabled(false);
            itemModificar.setEnabled(false);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            itemCancelar.setEnabled(false);
            itemQuitar.setEnabled(false);
            btnSalir.setEnabled(true);
            itemSalir.setEnabled(true);
            itemHojaR.setEnabled(false);
            itemHojaR1.setEnabled(false);
            btnCargarRA.setEnabled(false);
            btnCompraA.setEnabled(false);
            btnRC.setEnabled(false);
            btnRR.setEnabled(false);
            txtContenedorI.setEnabled(false);
            txtContenedorF.setEnabled(false);
            txtPresentado.setEnabled(false);
            txtKmAnterior.setEnabled(false);
            txtKmActual.setEnabled(false);
            btnCargarTransferencias.setEnabled(false);
            cboResponsable.setEnabled(false);
            chAgregar.setEnabled(false);
            cbCerrar.setEnabled(false);
            rdCargaT.setEnabled(false);
            rdRecambios.setEnabled(false);
            rdDevueltos.setEnabled(false);
            tbDetalleReparto.setEnabled(false);
            this.dispose();
            Volver();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            controlReparto.array.vaciar();

            this.dispose();
            Volver();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rdCargaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCargaTActionPerformed
        // TODO add your handling code here:
        txtOpcion.setText("C");
        btnBuscarArticuloActionPerformed(null);
    }//GEN-LAST:event_rdCargaTActionPerformed

    private void rdRecambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdRecambiosActionPerformed
        // TODO add your handling code here:
        txtOpcion.setText("R");
        btnBuscarArticuloActionPerformed(null);
    }//GEN-LAST:event_rdRecambiosActionPerformed

    private void rdDevueltosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDevueltosActionPerformed
        // TODO add your handling code here:
        txtOpcion.setText("D");
        btnBuscarArticuloActionPerformed(null);
    }//GEN-LAST:event_rdDevueltosActionPerformed

    private void txtContenedorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorFActionPerformed
        // TODO add your handling code here:
        txtContenedorV.requestFocus();
    }//GEN-LAST:event_txtContenedorFActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCancelarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCancelarCActionPerformed

    private void btnSalirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirCActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            DialogCajas.dispose();
        }
    }//GEN-LAST:event_btnSalirCActionPerformed

    private void txtContenedorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorTActionPerformed

    private void txtContenedorTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorTKeyReleased

    private void txtContenedorVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorVActionPerformed
        // TODO add your handling code here:
        txtPresentado.requestFocus();
    }//GEN-LAST:event_txtContenedorVActionPerformed

    private void txtContenedorVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorVKeyReleased
        // TODO add your handling code here:
        calcularCajas();
    }//GEN-LAST:event_txtContenedorVKeyReleased

    private void txtContenedorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorDActionPerformed

    private void txtContenedorFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorFKeyPressed
        // TODO add your handling code here:
        if (!txtContenedorF.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtContenedorF);
        }
    }//GEN-LAST:event_txtContenedorFKeyPressed

    private void txtContenedorVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorVKeyPressed
        // TODO add your handling code here:
        if (!txtContenedorV.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtContenedorV);
        }
    }//GEN-LAST:event_txtContenedorVKeyPressed

    private void txtContenedorIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorIKeyPressed
        // TODO add your handling code here:
        if (!txtContenedorI.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtContenedorI);
        }
    }//GEN-LAST:event_txtContenedorIKeyPressed

    private void txtContenedorTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorTKeyPressed
        // TODO add your handling code here:
        if (!txtContenedorT.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtContenedorT);
        }
    }//GEN-LAST:event_txtContenedorTKeyPressed

    private void txtContenedorDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorDAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorDAActionPerformed

    private void txtCAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCAnteriorActionPerformed

    private void txtCdifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCdifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCdifActionPerformed

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
            java.util.logging.Logger.getLogger(dlgGestRepartos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgGestRepartos dialog = new dlgGestRepartos(new javax.swing.JFrame(), true);
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
    private javax.swing.JDialog DialogCajas;
    private javax.swing.JMenu MnImprimirReparto;
    private javax.swing.JMenu MnOpcionesReparto;
    private javax.swing.JButton btnAdd;
    public static javax.swing.JButton btnBuscarArticulo;
    public static newscomponents.RSButtonBigIcon_new btnCancelar;
    public static newscomponents.RSButtonBigIcon_new btnCancelarC;
    public static newscomponents.RSButtonGradientIcon_new btnCargarRA;
    public static newscomponents.RSButtonGradientIcon_new btnCargarTransferencias;
    public static newscomponents.RSButtonGradientIcon_new btnCompraA;
    public static newscomponents.RSButtonBigIcon_new btnGuardar;
    public static newscomponents.RSButtonBigIcon_new btnGuardarC;
    public static newscomponents.RSButtonBigIcon_new btnModificar;
    public static newscomponents.RSButtonBigIcon_new btnNuevo;
    public static javax.swing.JButton btnRC;
    public static javax.swing.JButton btnRR;
    private javax.swing.JButton btnRestar;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    public static newscomponents.RSButtonBigIcon_new btnSalirC;
    public static javax.swing.JCheckBox cbCerrar;
    public static rojerusan.RSComboBox cboResponsable;
    public static rojerusan.RSCheckBox chAgregar;
    private javax.swing.ButtonGroup grupoCargado;
    private javax.swing.ButtonGroup grupoOpciones;
    public static javax.swing.JMenuItem itemBuscarArticulo;
    public static javax.swing.JMenuItem itemCancelar;
    public static javax.swing.JMenuItem itemGuardar;
    public static javax.swing.JMenuItem itemHojaApunte;
    public static javax.swing.JMenuItem itemHojaApunte1;
    public static javax.swing.JMenuItem itemHojaR;
    public static javax.swing.JMenuItem itemHojaR1;
    public static javax.swing.JMenuItem itemModificar;
    public static javax.swing.JMenuItem itemNuevo;
    public static javax.swing.JMenuItem itemQuitar;
    public static javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    public static javax.swing.JLabel lbComC;
    public static javax.swing.JLabel lbComC1;
    public static javax.swing.JLabel lbComR;
    public static javax.swing.JLabel lbComR1;
    public static javax.swing.JLabel lbInfoCantidad;
    public static javax.swing.JLabel lbInfoChofer;
    public static javax.swing.JLabel lbInfoMovil;
    public static rojerusan.RSRadioButton rdCargaT;
    public static rojerusan.RSRadioButton rdDevueltos;
    public static rojerusan.RSRadioButton rdRecambios;
    public static final javax.swing.JTable tbDetalleReparto = new javax.swing.JTable();
    public static javax.swing.JTextField txtCAnterior;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCdif;
    public static javax.swing.JTextField txtCdifACA;
    public static javax.swing.JTextField txtChofer;
    public static javax.swing.JTextField txtCodArticulo;
    public static javax.swing.JTextField txtComisionC;
    public static javax.swing.JTextField txtComisionR;
    public static javax.swing.JTextField txtContenedorD;
    public static javax.swing.JTextField txtContenedorDA;
    public static javax.swing.JTextField txtContenedorF;
    public static javax.swing.JTextField txtContenedorI;
    public static javax.swing.JTextField txtContenedorT;
    public static javax.swing.JTextField txtContenedorV;
    public static javax.swing.JTextField txtDevueltos;
    public static javax.swing.JTextField txtDevueltosL;
    public static javax.swing.JTextField txtDiferencia;
    public static javax.swing.JTextField txtDiferenciaL;
    public static javax.swing.JTextField txtEstado;
    public static javax.swing.JTextField txtFechaI;
    public static javax.swing.JTextField txtFechaIF;
    public static javax.swing.JLabel txtGastosTotal;
    public static javax.swing.JTextField txtHoraI;
    public static javax.swing.JTextField txtIdChofer;
    public static javax.swing.JTextField txtIdMovil;
    public static javax.swing.JTextField txtIdReparto;
    public static javax.swing.JTextField txtIdResponsable;
    public static javax.swing.JTextField txtKmActual;
    public static javax.swing.JTextField txtKmAnterior;
    public static javax.swing.JTextField txtKmRecorrido;
    public static javax.swing.JTextField txtMovil;
    public static javax.swing.JTextField txtOpcion;
    public static javax.swing.JTextField txtPresentado;
    public static javax.swing.JTextField txtPresentadoL;
    public static javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtRecambio;
    public static javax.swing.JTextField txtRecambioL;
    public static javax.swing.JTextField txtResponsable;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtTotalL;
    public static javax.swing.JTextField txtTotalVentas;
    public static javax.swing.JTextField txtTotalVentasL;
    public static javax.swing.JTextField txtValorCompra;
    public static javax.swing.JTextField txtValorRA;
    public static javax.swing.JTextField txtValorTransferencia;
    // End of variables declaration//GEN-END:variables

}
