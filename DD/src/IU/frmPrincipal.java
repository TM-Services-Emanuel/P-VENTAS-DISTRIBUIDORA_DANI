package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.ReporteMovil;
import Componentes.Mensajes;
import Componentes.Reloj;
import Componentes.Software;
import Componentes.generarCodigos;
import Componentes.traerIP;
import Controladores.ControlLogeo;
import Datos.GestionarImagen;
import Modelo.Imagen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.ResultSet;
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

public final class frmPrincipal extends javax.swing.JFrame {
    
    public ReporteMovil jasper;

    public MariaDbStatement sentencia;
    public MariaDbConnection con;
    private ResultSet rs;
    
    public MariaDbStatement sentenciaMovil;
    public MariaDbConnection conMovil;
    private ResultSet rsMovil;
    private Dimension dim;
    
    public frmPrincipal() {
        
        initComponents();
        //dim=super.getToolkit().getScreenSize();
        //super.setSize(dim);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        
        /*Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int ancho = (int)d.getWidth();
        int alto = (int)d.getHeight();
        this.setSize(ancho, alto);
        panelImage1.setSize(ancho, alto);*/
        try { 
            lbIp.setText("DIRECCIÓN IP : "+traerIP.getIP());
        } catch (Exception e) {
        }
        titulo();
        prepararBD();
        Iniciar();
        cargarIcono();
        cargarTapiz();
        informacionGral();
        mnNCProveedor.setVisible(false);
        //mnPagoProveedor.setVisible(false);
        lbversion.setText("Versión del Software: "+Software.getVersion());
        jasper = new ReporteMovil();
    }
    
    void titulo(){
        if(Software.getSoftware().equals("null")){
            this.setTitle("Ventana principal");
        }else{
            this.setTitle("Ventana principal del sistema "+Software.getSoftware()+" - "+Software.getDescripcion());
        }
        if(Software.getVersion().equals("null")){
            lbversion.setText("Versión del Software: No disponible");
        }else{
            lbversion.setText("Versión del Software: "+Software.getVersion());
        }
    }

    private void Iniciar() {
        Reloj hilo = new Reloj(lblFecha);
        hilo.start();
    }

    void ubicacion() {
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho-100, alto-100);
        setLocationRelativeTo(null);
    }

    void cargarTapiz() {
        try {
            Imagen imagen = GestionarImagen.fondoPrincipal();
            String nombre = "/Recursos/" + imagen.getImgFondo();
            ((JPanelConFondo) panelFondo).setImagen(nombre);
        } catch (Exception e) {
            Mensajes.informacion("Error al cargar Fondo del Sistema.");
        }
    }

    void prepararBD() {
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
            conMovil = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (conMovil == null) {
                System.out.println("No hay Conexion con la Base de Datos Movil");
            } else {
                sentenciaMovil = (MariaDbStatement) conMovil.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void informacionGral(){
        try {
            rs = sentencia.executeQuery("select * from v_sucursal where suc_indicador='S'");
            rs.first();
            try{
                if(rs.getRow()!=0){
                            //txtCod.setText(rs.getString(1));
                            lbSucursal.setText(rs.getString(5));
                            lbEmpresa.setText(rs.getString(3));
                            lbRUC.setText(rs.getString(4));
                }else{
                    System.out.println("No se puede cargar Información Gral.");
                }
            }catch(SQLException ee){
            System.out.println(ee.getMessage());
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelFondo = new JPanelConFondo();
        btnClientes = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnIMD = new javax.swing.JButton();
        btnRepartos = new javax.swing.JButton();
        btnGestionarCompras = new javax.swing.JButton();
        btnGestionarVentas = new javax.swing.JButton();
        btnCMD = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnSalidaP = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnGestionarVentasMovil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbEmpresa = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbSucursal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbRUC = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        lbPerfil = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        btnSalir = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JToolBar.Separator();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel13 = new javax.swing.JLabel();
        lbversion = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabel15 = new javax.swing.JLabel();
        lbIp = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        mbBarraMenu = new javax.swing.JMenuBar();
        mnSistema = new javax.swing.JMenu();
        itemFondo = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem52 = new javax.swing.JMenuItem();
        mnCalc = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        mnCerrarSistema = new javax.swing.JMenuItem();
        mnConfiguracion = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        mnMantenimiento = new javax.swing.JMenu();
        mnInformacion = new javax.swing.JMenu();
        itemEmpresa = new javax.swing.JMenuItem();
        itemSucursal = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        mnGTimbradoM = new javax.swing.JMenuItem();
        mnGPuntoEmisionM = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        mnGCiudadM = new javax.swing.JMenuItem();
        mnGClasificacionM = new javax.swing.JMenuItem();
        mnGUMM = new javax.swing.JMenuItem();
        mnGImpuestoM = new javax.swing.JMenuItem();
        itemFamilia = new javax.swing.JMenuItem();
        itemLaboratorio = new javax.swing.JMenuItem();
        itemCiudades = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem51 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        S1 = new javax.swing.JPopupMenu.Separator();
        mnLogistica = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        mnEmpleados = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        mnComision = new javax.swing.JMenuItem();
        mnProveedores = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        mnClientes = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        S3 = new javax.swing.JPopupMenu.Separator();
        mnCheques = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        mnDeuda = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        S5 = new javax.swing.JPopupMenu.Separator();
        mnSeguridad = new javax.swing.JMenu();
        smModUsuarios = new javax.swing.JMenuItem();
        smModUsuariosD = new javax.swing.JMenuItem();
        S4 = new javax.swing.JPopupMenu.Separator();
        itemExportar = new javax.swing.JMenuItem();
        itemImportar = new javax.swing.JMenuItem();
        divisor3 = new javax.swing.JMenu();
        mnCaja = new javax.swing.JMenu();
        mnIniciarCaja = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        mnCierredeCaja = new javax.swing.JMenuItem();
        divisor4 = new javax.swing.JMenu();
        mnGsIn = new javax.swing.JMenu();
        mnIngresosVarios1 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        mnGestGastos = new javax.swing.JMenuItem();
        divisor5 = new javax.swing.JMenu();
        mnProductos = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        mnCompras = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        mnNCProveedor = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnGC = new javax.swing.JMenuItem();
        mnVentas = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnGV = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        mnGVM1 = new javax.swing.JMenuItem();
        mnGVM = new javax.swing.JMenuItem();
        mnAyuda2 = new javax.swing.JMenu();
        mnPagoProveedor = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnDetallePagoProveedor = new javax.swing.JMenuItem();
        mnTransferencias = new javax.swing.JMenu();
        itemGestionarTR = new javax.swing.JMenuItem();
        mnReparto = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        divisor1 = new javax.swing.JMenu();
        mnAyuda1 = new javax.swing.JMenu();
        rpClientes = new javax.swing.JMenu();
        mnGProductosM2 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        rpVendedores1 = new javax.swing.JMenu();
        itemTVentasv1 = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        rpGastosIngresos = new javax.swing.JMenu();
        itemRGL = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        itemRGR = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        rpArticulos = new javax.swing.JMenu();
        mnGProductosM1 = new javax.swing.JMenuItem();
        mnGProductosM4 = new javax.swing.JMenuItem();
        mnGProductosM3 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        mnInventarioCompleto = new javax.swing.JMenuItem();
        mnInventarioStock_mayor = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem38 = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        rpCompras = new javax.swing.JMenu();
        itemTVentasv2 = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        rpTransferencias = new javax.swing.JMenu();
        rpTransferenciasGeneral = new javax.swing.JMenuItem();
        S8 = new javax.swing.JPopupMenu.Separator();
        rpVentas = new javax.swing.JMenu();
        itemTVentasv = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jMenuItem44 = new javax.swing.JMenuItem();
        S7 = new javax.swing.JPopupMenu.Separator();
        rpVendedores = new javax.swing.JMenu();
        jMenuItem50 = new javax.swing.JMenuItem();
        S10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem54 = new javax.swing.JMenuItem();
        S11 = new javax.swing.JPopupMenu.Separator();
        MitemRDC = new javax.swing.JMenuItem();
        S12 = new javax.swing.JPopupMenu.Separator();
        MitemRDC1 = new javax.swing.JMenuItem();
        S9 = new javax.swing.JPopupMenu.Separator();
        rpGanancias = new javax.swing.JMenu();
        jMenuItem57 = new javax.swing.JMenuItem();
        divisor2 = new javax.swing.JMenu();
        mnAyuda = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        setResizable(false);

        panelImage1.setBackground(new java.awt.Color(0, 102, 102));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(690, 418));

        panelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(0, 102, 102));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_clientes.png"))); // NOI18N
        btnClientes.setText("CLIENTES - F3");
        btnClientes.setBorderPainted(false);
        btnClientes.setFocusPainted(false);
        btnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnCompras.setBackground(new java.awt.Color(255, 255, 255));
        btnCompras.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnCompras.setForeground(new java.awt.Color(0, 102, 102));
        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Realizar Compras.png"))); // NOI18N
        btnCompras.setText("COMPRAR - F4");
        btnCompras.setBorderPainted(false);
        btnCompras.setFocusPainted(false);
        btnCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(255, 255, 255));
        btnVentas.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(0, 102, 102));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Realizar Ventas.png"))); // NOI18N
        btnVentas.setText("VENDER - F5");
        btnVentas.setBorderPainted(false);
        btnVentas.setFocusPainted(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnIMD.setBackground(new java.awt.Color(255, 255, 255));
        btnIMD.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnIMD.setForeground(new java.awt.Color(0, 102, 102));
        btnIMD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/abrir.png"))); // NOI18N
        btnIMD.setText("INICIAR MOV.");
        btnIMD.setBorderPainted(false);
        btnIMD.setFocusPainted(false);
        btnIMD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMDActionPerformed(evt);
            }
        });

        btnRepartos.setBackground(new java.awt.Color(255, 255, 255));
        btnRepartos.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnRepartos.setForeground(new java.awt.Color(0, 102, 102));
        btnRepartos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reparto_30.png"))); // NOI18N
        btnRepartos.setText("GEST. REPARTOS");
        btnRepartos.setBorderPainted(false);
        btnRepartos.setFocusPainted(false);
        btnRepartos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRepartos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRepartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepartosActionPerformed(evt);
            }
        });

        btnGestionarCompras.setBackground(new java.awt.Color(255, 255, 255));
        btnGestionarCompras.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnGestionarCompras.setForeground(new java.awt.Color(0, 102, 102));
        btnGestionarCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_compras50.png"))); // NOI18N
        btnGestionarCompras.setText("VER COMPRAS");
        btnGestionarCompras.setBorderPainted(false);
        btnGestionarCompras.setFocusPainted(false);
        btnGestionarCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGestionarCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGestionarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarComprasActionPerformed(evt);
            }
        });

        btnGestionarVentas.setBackground(new java.awt.Color(255, 255, 255));
        btnGestionarVentas.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnGestionarVentas.setForeground(new java.awt.Color(0, 102, 102));
        btnGestionarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Ventas.png"))); // NOI18N
        btnGestionarVentas.setText("VER VENTAS");
        btnGestionarVentas.setBorderPainted(false);
        btnGestionarVentas.setFocusPainted(false);
        btnGestionarVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGestionarVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGestionarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarVentasActionPerformed(evt);
            }
        });

        btnCMD.setBackground(new java.awt.Color(255, 255, 255));
        btnCMD.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnCMD.setForeground(new java.awt.Color(0, 102, 102));
        btnCMD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar.png"))); // NOI18N
        btnCMD.setText("CERRAR MOV.");
        btnCMD.setBorderPainted(false);
        btnCMD.setFocusPainted(false);
        btnCMD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCMD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCMD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCMDActionPerformed(evt);
            }
        });

        btnProductos.setBackground(new java.awt.Color(255, 255, 255));
        btnProductos.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(0, 102, 102));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Productos.png"))); // NOI18N
        btnProductos.setText("PRODUCTOS - F1");
        btnProductos.setBorderPainted(false);
        btnProductos.setFocusPainted(false);
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnSalidaP.setBackground(new java.awt.Color(255, 255, 255));
        btnSalidaP.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnSalidaP.setForeground(new java.awt.Color(0, 102, 102));
        btnSalidaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salidaproductos.png"))); // NOI18N
        btnSalidaP.setText("SALIDA DE PROD.");
        btnSalidaP.setBorderPainted(false);
        btnSalidaP.setFocusPainted(false);
        btnSalidaP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalidaP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalidaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaPActionPerformed(evt);
            }
        });

        btnTransferencia.setBackground(new java.awt.Color(255, 255, 255));
        btnTransferencia.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnTransferencia.setForeground(new java.awt.Color(0, 102, 102));
        btnTransferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf.png"))); // NOI18N
        btnTransferencia.setText("TRANSFERENCIAS");
        btnTransferencia.setBorderPainted(false);
        btnTransferencia.setFocusPainted(false);
        btnTransferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransferencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });

        btnGestionarVentasMovil.setBackground(new java.awt.Color(255, 255, 255));
        btnGestionarVentasMovil.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnGestionarVentasMovil.setForeground(new java.awt.Color(0, 102, 102));
        btnGestionarVentasMovil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Ventas.png"))); // NOI18N
        btnGestionarVentasMovil.setText("VENTAS MÓVIL");
        btnGestionarVentasMovil.setBorderPainted(false);
        btnGestionarVentasMovil.setFocusPainted(false);
        btnGestionarVentasMovil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGestionarVentasMovil.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGestionarVentasMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarVentasMovilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGestionarVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGestionarVentasMovil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGestionarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnIMD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCMD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 537, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnSalidaP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnRepartos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTransferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIMD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCMD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalidaP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGestionarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGestionarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGestionarVentasMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRepartos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icon.png"))); // NOI18N

        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel1.setBackground(new java.awt.Color(210, 229, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("RAZÓN SOCIAL:");

        lbEmpresa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lbEmpresa.setText("SIN ESPECIFICAR");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SUCURSAL:");

        lbSucursal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbSucursal.setForeground(new java.awt.Color(255, 255, 255));
        lbSucursal.setText("SIN ESPECIFICAR");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("R.U.C.:");

        lbRUC.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbRUC.setForeground(new java.awt.Color(255, 255, 255));
        lbRUC.setText("SIN ESPECIFICAR");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("INFORMACIÓN DE LA EMPRESA");
        jLabel17.setOpaque(true);

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Logo_DD_normal_1.png"))); // NOI18N
        panelImage2.setPreferredSize(new java.awt.Dimension(520, 315));

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(lbRUC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpresa)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRUC)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbSucursal))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(210, 229, 235));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE:");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("SIN ESPECIFICAR");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("USUARIO:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PERFIL:");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lbUsuario.setText("SIN ESPECIFICAR");

        lbPerfil.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lbPerfil.setText("SIN ESPECIFICAR");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("ACCESO AL SISTEMA");
        jLabel18.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(lbPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator17.setBackground(new java.awt.Color(179, 215, 236));
        jSeparator17.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        btnSalir.setToolTipText("Cerrar Sistema");
        btnSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel5.setText("    ");
        jToolBar1.add(jLabel5);

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(17, 35, 46));
        lblFecha.setText("Fecha: ");
        jToolBar1.add(lblFecha);

        jLabel9.setText("   ");
        jToolBar1.add(jLabel9);
        jToolBar1.add(jSeparator18);

        jLabel10.setText("   ");
        jToolBar1.add(jLabel10);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(17, 35, 46));
        jLabel8.setText("Data base name: P-VENTA - port: 3306");
        jToolBar1.add(jLabel8);

        jLabel12.setText("   ");
        jToolBar1.add(jLabel12);
        jToolBar1.add(jSeparator3);

        jLabel13.setText("   ");
        jToolBar1.add(jLabel13);

        lbversion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbversion.setForeground(new java.awt.Color(17, 35, 46));
        lbversion.setText("Versión del Software:");
        jToolBar1.add(lbversion);

        jLabel14.setText("   ");
        jToolBar1.add(jLabel14);
        jToolBar1.add(jSeparator7);

        jLabel15.setText("   ");
        jToolBar1.add(jLabel15);

        lbIp.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbIp.setForeground(new java.awt.Color(17, 35, 46));
        lbIp.setText("ip");
        jToolBar1.add(lbIp);

        jLabel19.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Software de gestión de Articulos, Compras & Ventas");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/LOGO-FACT-EXPRESS - 216.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("+");

        jLabel22.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Aplicación de facturación móvil");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(767, 767, 767)
                        .addComponent(jLabel1)
                        .addGap(0, 403, Short.MAX_VALUE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelImage1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelImage1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jSeparator17)))
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel19)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel20)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel22)
                        .addGap(53, 53, 53)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mbBarraMenu.setBackground(new java.awt.Color(255, 255, 255));
        mbBarraMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        mnSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menubuttonofthreelines_79781.png"))); // NOI18N
        mnSistema.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnSistema.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemFondo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/setting15.png"))); // NOI18N
        itemFondo.setText("Gestionar Fondo del Sistema");
        itemFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFondoActionPerformed(evt);
            }
        });
        mnSistema.add(itemFondo);
        mnSistema.add(jSeparator4);

        jMenuItem52.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/datalook15.png"))); // NOI18N
        jMenuItem52.setText("Cerrar sesión");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        mnSistema.add(jMenuItem52);

        mnCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/calculator.png"))); // NOI18N
        mnCalc.setText("Calculadora de windows");
        mnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCalcActionPerformed(evt);
            }
        });
        mnSistema.add(mnCalc);
        mnSistema.add(jSeparator12);

        mnCerrarSistema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mnCerrarSistema.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnCerrarSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        mnCerrarSistema.setText("Cerrar el sistema              ");
        mnCerrarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCerrarSistemaActionPerformed(evt);
            }
        });
        mnSistema.add(mnCerrarSistema);

        mbBarraMenu.add(mnSistema);

        mnConfiguracion.setText("Configuración");
        mnConfiguracion.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnConfiguracion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/setting15 (2).png"))); // NOI18N
        jMenuItem1.setText("Establecer impresora predeterminada");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnConfiguracion.add(jMenuItem1);

        jMenuItem9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/datalook15.png"))); // NOI18N
        jMenuItem9.setText("Información del Software");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mnConfiguracion.add(jMenuItem9);

        mbBarraMenu.add(mnConfiguracion);

        mnMantenimiento.setText("Mantenimiento");
        mnMantenimiento.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnMantenimiento.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        mnInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        mnInformacion.setText("Informaciones generales y auxiliares");
        mnInformacion.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        itemEmpresa.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        itemEmpresa.setText("Gestionar empresa");
        itemEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEmpresaActionPerformed(evt);
            }
        });
        mnInformacion.add(itemEmpresa);

        itemSucursal.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        itemSucursal.setText("Gestionar sucursales");
        itemSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSucursalActionPerformed(evt);
            }
        });
        mnInformacion.add(itemSucursal);
        mnInformacion.add(jSeparator14);

        mnGTimbradoM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGTimbradoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGTimbradoM.setText("Gestionar Timbrado");
        mnGTimbradoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGTimbradoMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGTimbradoM);

        mnGPuntoEmisionM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGPuntoEmisionM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGPuntoEmisionM.setText("Gestionar Punto de Emisión");
        mnGPuntoEmisionM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGPuntoEmisionMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGPuntoEmisionM);
        mnInformacion.add(jSeparator22);

        mnGCiudadM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGCiudadM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGCiudadM.setText("Gestionar Ciudades");
        mnGCiudadM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGCiudadMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGCiudadM);

        mnGClasificacionM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGClasificacionM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGClasificacionM.setText("Gestionar Clasificación");
        mnGClasificacionM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGClasificacionMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGClasificacionM);

        mnGUMM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGUMM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGUMM.setText("Gestionar Unidad de medida");
        mnGUMM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGUMMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGUMM);

        mnGImpuestoM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGImpuestoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_CEL.png"))); // NOI18N
        mnGImpuestoM.setText("Gestionar Impuesto");
        mnGImpuestoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGImpuestoMActionPerformed(evt);
            }
        });
        mnInformacion.add(mnGImpuestoM);

        itemFamilia.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        itemFamilia.setText("Gestionar familias");
        itemFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFamiliaActionPerformed(evt);
            }
        });
        mnInformacion.add(itemFamilia);

        itemLaboratorio.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        itemLaboratorio.setText("Gestionar Marcas");
        itemLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLaboratorioActionPerformed(evt);
            }
        });
        mnInformacion.add(itemLaboratorio);

        itemCiudades.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemCiudades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        itemCiudades.setText("Gestionar mas Ciudades");
        itemCiudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCiudadesActionPerformed(evt);
            }
        });
        mnInformacion.add(itemCiudades);

        jMenuItem35.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        jMenuItem35.setText("Gestionar motivos");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        mnInformacion.add(jMenuItem35);
        mnInformacion.add(jSeparator9);

        jMenuItem51.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        jMenuItem51.setText("Gestionar motivos de ingresos");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        mnInformacion.add(jMenuItem51);

        jMenuItem53.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MANT_PC.png"))); // NOI18N
        jMenuItem53.setText("Gestionar motivos de gastos");
        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        mnInformacion.add(jMenuItem53);

        mnMantenimiento.add(mnInformacion);
        mnMantenimiento.add(S1);

        mnLogistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logistic_25.png"))); // NOI18N
        mnLogistica.setText("Puntos de Control & Logística");
        mnLogistica.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gestionarPC.png"))); // NOI18N
        jMenuItem10.setText("Gestionar  Puntos de Control & Logistica                           ");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        mnLogistica.add(jMenuItem10);

        mnMantenimiento.add(mnLogistica);

        mnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleados_25.png"))); // NOI18N
        mnEmpleados.setText("Recurso Humano");
        mnEmpleados.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadoss_25.png"))); // NOI18N
        jMenuItem8.setText("Gestionar de Funcionarios");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        mnEmpleados.add(jMenuItem8);

        mnComision.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnComision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/comision_25.png"))); // NOI18N
        mnComision.setText("Comisiones");
        mnComision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnComisionActionPerformed(evt);
            }
        });
        mnEmpleados.add(mnComision);

        mnMantenimiento.add(mnEmpleados);

        mnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedores_25.png"))); // NOI18N
        mnProveedores.setText("Proveedores");
        mnProveedores.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadoss_25.png"))); // NOI18N
        jMenuItem7.setText("Gestionar Proveedores                                    ");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnProveedores.add(jMenuItem7);

        mnMantenimiento.add(mnProveedores);

        mnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clientes_25.png"))); // NOI18N
        mnClientes.setText("Clientes");
        mnClientes.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadoss_25.png"))); // NOI18N
        jMenuItem6.setText("Gestionar Clientes                                  ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnClientes.add(jMenuItem6);

        mnMantenimiento.add(mnClientes);
        mnMantenimiento.add(S3);

        mnCheques.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/documentos.png"))); // NOI18N
        mnCheques.setText("Cheques");
        mnCheques.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Bank_Check.png"))); // NOI18N
        jMenuItem12.setText("Gestionar Cheques");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        mnCheques.add(jMenuItem12);

        mnMantenimiento.add(mnCheques);

        mnDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/documentos.png"))); // NOI18N
        mnDeuda.setText("Deudas");
        mnDeuda.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/AgregarIngresos - copia.png"))); // NOI18N
        jMenuItem11.setText("Gestionar Deudas                                  ");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        mnDeuda.add(jMenuItem11);

        mnMantenimiento.add(mnDeuda);
        mnMantenimiento.add(S5);

        mnSeguridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/seguridad_25.png"))); // NOI18N
        mnSeguridad.setText("Seguridad");
        mnSeguridad.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        smModUsuarios.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        smModUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuarios_25.png"))); // NOI18N
        smModUsuarios.setText("Gestionar usuarios");
        smModUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smModUsuariosActionPerformed(evt);
            }
        });
        mnSeguridad.add(smModUsuarios);

        smModUsuariosD.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        smModUsuariosD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuarios_25.png"))); // NOI18N
        smModUsuariosD.setText("Gestionar usuarios - Desarrollador");
        smModUsuariosD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smModUsuariosDActionPerformed(evt);
            }
        });
        mnSeguridad.add(smModUsuariosD);
        mnSeguridad.add(S4);

        itemExportar.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/backup_25 (1).png"))); // NOI18N
        itemExportar.setText("Generar respaldo (Backup)");
        itemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarActionPerformed(evt);
            }
        });
        mnSeguridad.add(itemExportar);

        itemImportar.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/restore_25.png"))); // NOI18N
        itemImportar.setText("Restaurar backup (Importar BD)");
        itemImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemImportarActionPerformed(evt);
            }
        });
        mnSeguridad.add(itemImportar);

        mnMantenimiento.add(mnSeguridad);

        mbBarraMenu.add(mnMantenimiento);

        divisor3.setText("|");
        divisor3.setEnabled(false);
        divisor3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        divisor3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        divisor3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mbBarraMenu.add(divisor3);

        mnCaja.setText("Movimiento Diario");
        mnCaja.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnCaja.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        mnIniciarCaja.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnIniciarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/abrir - copia.png"))); // NOI18N
        mnIniciarCaja.setText("Inicializar Mov. Diario (Caja)");
        mnIniciarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnIniciarCajaActionPerformed(evt);
            }
        });
        mnCaja.add(mnIniciarCaja);
        mnCaja.add(jSeparator15);

        mnCierredeCaja.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnCierredeCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar - copia.png"))); // NOI18N
        mnCierredeCaja.setText("Finalizar Mov. Diario (Cierre de caja)");
        mnCierredeCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCierredeCajaActionPerformed(evt);
            }
        });
        mnCaja.add(mnCierredeCaja);

        mbBarraMenu.add(mnCaja);

        divisor4.setText("|");
        divisor4.setEnabled(false);
        divisor4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        divisor4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        divisor4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mbBarraMenu.add(divisor4);

        mnGsIn.setText("Gastos e Ingresos");
        mnGsIn.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnGsIn.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        mnIngresosVarios1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnIngresosVarios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/GestionarIngresos - copia.png"))); // NOI18N
        mnIngresosVarios1.setText("Gestionar todos los Ingresos Diarios");
        mnIngresosVarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnIngresosVarios1ActionPerformed(evt);
            }
        });
        mnGsIn.add(mnIngresosVarios1);
        mnGsIn.add(jSeparator19);

        mnGestGastos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGestGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/GestionarGastos - copia.png"))); // NOI18N
        mnGestGastos.setText("Gestionar todos los Gastos Diarios");
        mnGestGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGestGastosActionPerformed(evt);
            }
        });
        mnGsIn.add(mnGestGastos);

        mbBarraMenu.add(mnGsIn);

        divisor5.setText("|");
        divisor5.setEnabled(false);
        divisor5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        divisor5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        divisor5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mbBarraMenu.add(divisor5);

        mnProductos.setText("Productos");
        mnProductos.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnProductos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Productos_25.png"))); // NOI18N
        jMenuItem2.setText("Gestionar Productos  ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnProductos.add(jMenuItem2);
        mnProductos.add(jSeparator8);

        jMenuItem3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf 30.png"))); // NOI18N
        jMenuItem3.setText("Ajustar stock de Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnProductos.add(jMenuItem3);
        mnProductos.add(jSeparator1);

        jMenuItem4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salidaproductos_35.png"))); // NOI18N
        jMenuItem4.setText("Aplicar salida a Productos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnProductos.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/SalidaControl_35.png"))); // NOI18N
        jMenuItem5.setText("Controlar salidas aplicadas a Productos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mnProductos.add(jMenuItem5);

        mbBarraMenu.add(mnProductos);

        mnCompras.setText("Compras");
        mnCompras.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnCompras.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem30.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/add_compra_25.png"))); // NOI18N
        jMenuItem30.setText("Registrar compras de proveedores");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        mnCompras.add(jMenuItem30);

        mnNCProveedor.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnNCProveedor.setText("Notas de Crédito del Proveedor");
        mnNCProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNCProveedorActionPerformed(evt);
            }
        });
        mnCompras.add(mnNCProveedor);
        mnCompras.add(jSeparator6);

        mnGC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mnGC.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_compras25.png"))); // NOI18N
        mnGC.setText("Gestionar todas las compras realizadas                                  ");
        mnGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGCActionPerformed(evt);
            }
        });
        mnCompras.add(mnGC);

        mbBarraMenu.add(mnCompras);

        mnVentas.setText("Ventas");
        mnVentas.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnVentas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem23.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Realizar Ventas - 35.png"))); // NOI18N
        jMenuItem23.setText("Realizar ventas");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        mnVentas.add(jMenuItem23);
        mnVentas.add(jSeparator5);

        mnGV.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        mnGV.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Ventas 35.png"))); // NOI18N
        mnGV.setText("Gestionar todas las ventas realizadas                                     ");
        mnGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGVActionPerformed(evt);
            }
        });
        mnVentas.add(mnGV);
        mnVentas.add(jSeparator25);

        mnGVM1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGVM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Ventas 35.png"))); // NOI18N
        mnGVM1.setText("Gestionar todas las ventas móviles                                     ");
        mnGVM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGVM1ActionPerformed(evt);
            }
        });
        mnVentas.add(mnGVM1);

        mnGVM.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGVM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gestionar_Ventas 35.png"))); // NOI18N
        mnGVM.setText("Gestionar todas las ventas móviles - Nueva Versión");
        mnGVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGVMActionPerformed(evt);
            }
        });
        mnVentas.add(mnGVM);

        mbBarraMenu.add(mnVentas);

        mnAyuda2.setText("Pagos");
        mnAyuda2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnAyuda2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        mnPagoProveedor.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnPagoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/billete.png"))); // NOI18N
        mnPagoProveedor.setText("Registrar pagos a Proveedores");
        mnPagoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPagoProveedorActionPerformed(evt);
            }
        });
        mnAyuda2.add(mnPagoProveedor);
        mnAyuda2.add(jSeparator2);

        mnDetallePagoProveedor.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnDetallePagoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/billetera.png"))); // NOI18N
        mnDetallePagoProveedor.setText("Gestionar todos los pagos realizados");
        mnDetallePagoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDetallePagoProveedorActionPerformed(evt);
            }
        });
        mnAyuda2.add(mnDetallePagoProveedor);

        mbBarraMenu.add(mnAyuda2);

        mnTransferencias.setText("Transferencias");
        mnTransferencias.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnTransferencias.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemGestionarTR.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemGestionarTR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Transf 30.png"))); // NOI18N
        itemGestionarTR.setText("Gestionar todas las Transferencias realizadas");
        itemGestionarTR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionarTRActionPerformed(evt);
            }
        });
        mnTransferencias.add(itemGestionarTR);

        mbBarraMenu.add(mnTransferencias);

        mnReparto.setText("Repartos");
        mnReparto.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnReparto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem18.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/GestRepartos_25.png"))); // NOI18N
        jMenuItem18.setText("Gestionar Repartos");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        mnReparto.add(jMenuItem18);

        mbBarraMenu.add(mnReparto);

        divisor1.setText("|");
        divisor1.setEnabled(false);
        divisor1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        divisor1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        divisor1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mbBarraMenu.add(divisor1);

        mnAyuda1.setText("Generar Reportes");
        mnAyuda1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnAyuda1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        rpClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpClientes.setText("Clientes");
        rpClientes.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        mnGProductosM2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGProductosM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnGProductosM2.setText("Listado de Clientes");
        mnGProductosM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGProductosM2ActionPerformed(evt);
            }
        });
        rpClientes.add(mnGProductosM2);

        mnAyuda1.add(rpClientes);
        mnAyuda1.add(jSeparator26);

        rpVendedores1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpVendedores1.setText("Movimiento Diario");
        rpVendedores1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        itemTVentasv1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemTVentasv1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        itemTVentasv1.setText("Mov. Diario (Cierre de caja)");
        itemTVentasv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTVentasv1ActionPerformed(evt);
            }
        });
        rpVendedores1.add(itemTVentasv1);

        mnAyuda1.add(rpVendedores1);
        mnAyuda1.add(jSeparator21);

        rpGastosIngresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpGastosIngresos.setText("Gastos e Ingresos");
        rpGastosIngresos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        itemRGL.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemRGL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        itemRGL.setText("Reporte Detallado de Gastos Generados");
        itemRGL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRGLActionPerformed(evt);
            }
        });
        rpGastosIngresos.add(itemRGL);
        rpGastosIngresos.add(jSeparator28);

        itemRGR.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemRGR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        itemRGR.setText("Reporte Resumido de Gastos Generados");
        itemRGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRGRActionPerformed(evt);
            }
        });
        rpGastosIngresos.add(itemRGR);

        mnAyuda1.add(rpGastosIngresos);
        mnAyuda1.add(jSeparator16);

        rpArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpArticulos.setText("Productos");
        rpArticulos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        mnGProductosM1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGProductosM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnGProductosM1.setText("Listado de Productos Lácteos - Solo Precio de Venta");
        mnGProductosM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGProductosM1ActionPerformed(evt);
            }
        });
        rpArticulos.add(mnGProductosM1);

        mnGProductosM4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGProductosM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnGProductosM4.setText("Listado de Productos Lácteos Alternativo - Solo Precio de Venta");
        mnGProductosM4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGProductosM4ActionPerformed(evt);
            }
        });
        rpArticulos.add(mnGProductosM4);

        mnGProductosM3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnGProductosM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnGProductosM3.setText("Listado de Productos Lácteos - Completo");
        mnGProductosM3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGProductosM3ActionPerformed(evt);
            }
        });
        rpArticulos.add(mnGProductosM3);
        rpArticulos.add(jSeparator11);

        mnInventarioCompleto.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnInventarioCompleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnInventarioCompleto.setText("Inventario de Productos en Salón de Venta - Completo");
        mnInventarioCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInventarioCompletoActionPerformed(evt);
            }
        });
        rpArticulos.add(mnInventarioCompleto);

        mnInventarioStock_mayor.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        mnInventarioStock_mayor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        mnInventarioStock_mayor.setText("Inventario de Productos en Salón de Venta - Stock > 0");
        mnInventarioStock_mayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInventarioStock_mayorActionPerformed(evt);
            }
        });
        rpArticulos.add(mnInventarioStock_mayor);
        rpArticulos.add(jSeparator13);

        jMenuItem38.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jMenuItem38.setText("Stock Valorizado de Salón de Venta");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        rpArticulos.add(jMenuItem38);

        mnAyuda1.add(rpArticulos);
        mnAyuda1.add(jSeparator23);

        rpCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpCompras.setText("Compras");
        rpCompras.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        itemTVentasv2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemTVentasv2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        itemTVentasv2.setText("Reporte de Compras Realizadas");
        itemTVentasv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTVentasv2ActionPerformed(evt);
            }
        });
        rpCompras.add(itemTVentasv2);

        mnAyuda1.add(rpCompras);
        mnAyuda1.add(jSeparator24);

        rpTransferencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpTransferencias.setText("Transferencias");
        rpTransferencias.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        rpTransferenciasGeneral.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        rpTransferenciasGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        rpTransferenciasGeneral.setText("Reporte de Transferencias");
        rpTransferenciasGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rpTransferenciasGeneralActionPerformed(evt);
            }
        });
        rpTransferencias.add(rpTransferenciasGeneral);

        mnAyuda1.add(rpTransferencias);
        mnAyuda1.add(S8);

        rpVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpVentas.setText("Ventas");
        rpVentas.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        itemTVentasv.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemTVentasv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        itemTVentasv.setText("Reporte de Ventas Realizadas en General");
        itemTVentasv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTVentasvActionPerformed(evt);
            }
        });
        rpVentas.add(itemTVentasv);
        rpVentas.add(jSeparator27);

        jMenuItem44.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jMenuItem44.setText("Reporte de Ventas por cliente");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        rpVentas.add(jMenuItem44);

        mnAyuda1.add(rpVentas);
        mnAyuda1.add(S7);

        rpVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpVendedores.setText("Repartos");
        rpVendedores.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem50.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jMenuItem50.setText("Reporte detallado de Valores en Reparto");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        rpVendedores.add(jMenuItem50);
        rpVendedores.add(S10);

        jMenuItem54.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jMenuItem54.setText("Reporte de Stock y Valor por Reparto");
        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        rpVendedores.add(jMenuItem54);
        rpVendedores.add(S11);

        MitemRDC.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        MitemRDC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/billetera.png"))); // NOI18N
        MitemRDC.setText("Recibo de Dinero - Pago de Comisiones");
        MitemRDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitemRDCActionPerformed(evt);
            }
        });
        rpVendedores.add(MitemRDC);
        rpVendedores.add(S12);

        MitemRDC1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        MitemRDC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        MitemRDC1.setText("Hoja de Control de Stock");
        MitemRDC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitemRDC1ActionPerformed(evt);
            }
        });
        rpVendedores.add(MitemRDC1);

        mnAyuda1.add(rpVendedores);
        mnAyuda1.add(S9);

        rpGanancias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Impri_report.png"))); // NOI18N
        rpGanancias.setText("Ganancia");
        rpGanancias.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem57.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jMenuItem57.setText("Reporte de Ganancias en General");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        rpGanancias.add(jMenuItem57);

        mnAyuda1.add(rpGanancias);

        mbBarraMenu.add(mnAyuda1);

        divisor2.setText("|");
        divisor2.setEnabled(false);
        divisor2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        divisor2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        divisor2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mbBarraMenu.add(divisor2);

        mnAyuda.setText("Sistema");
        mnAyuda.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mnAyuda.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem17.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Copyright 25.png"))); // NOI18N
        jMenuItem17.setText("Acerca de...");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        mnAyuda.add(jMenuItem17);

        mbBarraMenu.add(mnAyuda);

        setJMenuBar(mbBarraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            dlgAjusteStock ajuste = new dlgAjusteStock(this, true);
            ajuste.setLocationRelativeTo(null);
            ajuste.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        try {
            dlgSalidaMercaderia salida = new dlgSalidaMercaderia(this, true);
            salida.setLocationRelativeTo(null);
            salida.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
       //Mensajes.Sistema("La función: Aplicar salidas a artículos se encuentra bloqueada \nen estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        try {
            dlgConSalidas conSalidas = new dlgConSalidas(this, true);
            conSalidas.setLocationRelativeTo(null);
            conSalidas.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
        //Mensajes.Sistema("La función: Controlar salidas aplicadas a artículos se encuentra bloqueada \nen estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        abrirClientes();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        abrirProductosMoviles();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnCerrarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCerrarSistemaActionPerformed
        // TODO add your handling code here:
        salir();
    }//GEN-LAST:event_mnCerrarSistemaActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        abrirProveedor();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void itemLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLaboratorioActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            dlgLaboratorio laboratorio = new dlgLaboratorio(this, true);
            laboratorio.setLocationRelativeTo(null);
            laboratorio.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_itemLaboratorioActionPerformed

    private void itemCiudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCiudadesActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            dlgCiudad ciu = new dlgCiudad(this, true);
            ciu.setLocationRelativeTo(null);
            ciu.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_itemCiudadesActionPerformed

    private void itemFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFamiliaActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            dlgFamilia familia = new dlgFamilia(this, true);
            familia.setLocationRelativeTo(null);
            familia.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_itemFamiliaActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        try {
            dlgVendedor vend = new dlgVendedor(this, true);
            vend.setLocationRelativeTo(null);
            vend.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        btnComprasActionPerformed(null);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            dlgMotivo motivo = new dlgMotivo(this, true);
            motivo.setLocationRelativeTo(null);
            motivo.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        btnVentasActionPerformed(null);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void mnGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGVActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
            try {
            dlgConsultarFacturas cf = new dlgConsultarFacturas(this, true);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
        //Mensajes.informacion("ESTA FUNCION ESTARA DISPONIBLE EN LA SIGUIENTE ACTUALIZACION");
    }//GEN-LAST:event_mnGVActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        // TODO add your handling code here:
        CerrarCesion();


    }//GEN-LAST:event_jMenuItem52ActionPerformed

    public void salir() {
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del sistema?");
        if (rpta == 0) {
            String msg = ControlLogeo.desLogeo();
            System.exit(0);
            //this.dispose();

        }
    }

    public void CerrarCesion() {
        int rpta = Mensajes.confirmar("¿Seguro que desea Cerrar Sesión?");
        if (rpta == 0) {
            String msg = ControlLogeo.desLogeo();
            //System.exit(0);
            this.dispose();
            frmAcceso ac = new frmAcceso();
            ac.setLocationRelativeTo(null);
            ac.setVisible(true);

        }
    }
    
    void abrirImpresoras() {
        try {
            dlgImpresoras impre = new dlgImpresoras(this, true);
            impre.setLocationRelativeTo(null);
            impre.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }

    }

    void abrirFactura() {
        try {
            dlgVentas factura = new dlgVentas(this, true);
            factura.setLocationRelativeTo(this);
            factura.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }

    }

    void abrirCompras() {
        try {
            dlgCompras1 compras = new dlgCompras1(this, true);
            compras.setLocationRelativeTo(null);
            compras.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }

    }
    
    void abrirPagosProveedor() {
        try {
            dlgRegistrarPagosProveedor rpp = new dlgRegistrarPagosProveedor(this, true);
            rpp.setLocationRelativeTo(null);
            rpp.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }

    }

    void abrirClientes() {
        try {
            dlgClientes clientes = new dlgClientes(this, true);
            //clientes.setSize(1000, 540);
            clientes.setLocationRelativeTo(null);
            clientes.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirDeuda() {
        try {
            dlgDeudas deudas = new dlgDeudas(this, true);
            //clientes.setSize(1000, 540);
            deudas.setLocationRelativeTo(null);
            deudas.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirCheques() {
        try {
            dlgCheques deudas = new dlgCheques(this, true);
            //clientes.setSize(1000, 540);
            deudas.setLocationRelativeTo(null);
            deudas.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirCiudadMovil() {
        try {
            dlgCiudadMovil ciudadm = new dlgCiudadMovil(this, true);
            ciudadm.setLocationRelativeTo(null);
            ciudadm.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirClasificacionMovil() {
        try {
            dlgClasificacionMovil clasifM = new dlgClasificacionMovil(this, true);
            clasifM.setLocationRelativeTo(null);
            clasifM.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirTimbradoMovil() {
        try {
            dlgTimbradoMovil TimbradoM = new dlgTimbradoMovil(this, true);
            TimbradoM.setLocationRelativeTo(null);
            TimbradoM.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirPuntoEmisionMovil() {
        try {
            dlgPuntoEmisionMovil PPM = new dlgPuntoEmisionMovil(this, true);
            PPM.setLocationRelativeTo(null);
            PPM.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirUMMovil() {
        try {
            dlgUMMovil umM = new dlgUMMovil(this, true);
            umM.setLocationRelativeTo(null);
            umM.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    
    void abrirIVAMovil() {
        try {
            dlgIVAMovil ivaM = new dlgIVAMovil(this, true);
            ivaM.setLocationRelativeTo(null);
            ivaM.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }

    void abrirArticulos() {
        try {
            dlgArticulos articulo = new dlgArticulos(this, true);
            articulo.setLocationRelativeTo(null);
            articulo.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
     void abrirProductosMoviles() {
        try {
            dlgArticulosMovil articulo = new dlgArticulosMovil(this, true);
            articulo.setLocationRelativeTo(null);
            articulo.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
     
     void abrirTransferencias() {
        try {
            //dlgGestTransferencias transf = new dlgGestTransferencias(this, true);
            dlgTransferencias transf = new dlgTransferencias(this, true);
            transf.setLocationRelativeTo(null);
            transf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }

    private void smModUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smModUsuariosActionPerformed
        // TODO add your handling code here:
        try {
            dlgGestUsuario gu = new dlgGestUsuario(this, true);
            gu.setLocationRelativeTo(null);
            gu.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_smModUsuariosActionPerformed

    private void mnGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGCActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
            try {
            dlgConsultarCompras consCompras = new dlgConsultarCompras(this, true);
            consCompras.setLocationRelativeTo(null);
            consCompras.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_mnGCActionPerformed

    private void mnCierredeCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCierredeCajaActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                Mensajes.informacion("La caja ya fue cerrada.\n\nPodra acceder a este formulario para visualizar los movimientos en la siguiente apertura de caja.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
            } else {
                dlgCajaDia cajaDia = new dlgCajaDia(this, false);
                cajaDia.setLocationRelativeTo(null);
                cajaDia.setVisible(true);
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
        //Mensajes.informacion("ESTA FUNCION ESTA SIENDO DESARROLLADO ACTUALMENTE");
    }//GEN-LAST:event_mnCierredeCajaActionPerformed

    private void itemFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFondoActionPerformed
        // TODO add your handling code here:
        try {
            dlgFondo fondo = new dlgFondo(this, true);
            fondo.setLocationRelativeTo(null);
            fondo.setVisible(true);
            //((JPanelConFondo) panelFondo).setImagen("/Recursos/imagen8.jpg");
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_itemFondoActionPerformed

    private void mnInventarioCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInventarioCompletoActionPerformed
        // TODO add your handling code here:
       jasper.ListaProductosMovilesH("\\Reports\\articulos\\Inventario_Productos.jasper");
    }//GEN-LAST:event_mnInventarioCompletoActionPerformed

    private void itemEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEmpresaActionPerformed
        // TODO add your handling code here:
        try {
            dlgEmpresa empresa = new dlgEmpresa(this, true);
            empresa.setLocationRelativeTo(null);
            empresa.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_itemEmpresaActionPerformed

    private void itemSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSucursalActionPerformed
        // TODO add your handling code here:
        try {
            dlgSucursal sucursal = new dlgSucursal(this, true);
            sucursal.setLocationRelativeTo(null);
            sucursal.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_itemSucursalActionPerformed

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("La función: Generar respaldo se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_itemExportarActionPerformed

    private void itemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemImportarActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("La función: Restaurar backup se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_itemImportarActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        dlgAyuda a = new dlgAyuda(this, true);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void mnPagoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPagoProveedorActionPerformed
        // TODO add your handling code here:
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar las compras a proveedores sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirPagosProveedor();
        }
    }//GEN-LAST:event_mnPagoProveedorActionPerformed

    private void mnNCProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNCProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnNCProveedorActionPerformed

    private void mnIniciarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnIniciarCajaActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja WHERE ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                dlgCaja caja = new dlgCaja(this, true);
                caja.setLocationRelativeTo(null);
                caja.setVisible(true);
            } else {
                Mensajes.informacion("La caja ya fue inicializada.\n\nPuede comenzar a registrar compras o realizar ventas\nsin ningún inconveniente.");
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_mnIniciarCajaActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            dlgDetalleIngreso detalleI = new dlgDetalleIngreso(this, true);
            detalleI.setLocationRelativeTo(null);
            detalleI.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            dlgDetalleGasto detalleG = new dlgDetalleGasto(this, true);
            detalleG.setLocationRelativeTo(null);
            detalleG.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        salir();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try{
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a vender sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirFactura();
        }
        }catch(Exception e){Mensajes.error("Error obteniendo fecha de caja: "+e.getMessage());}
        
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        abrirClientes();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar las compras a proveedores sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirCompras();
        }
    }//GEN-LAST:event_btnComprasActionPerformed

    private void mnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCalcActionPerformed
        // TODO add your handling code here:
        try        
    {
        Runtime rt = Runtime.getRuntime();           
        Process p = rt.exec("calc");            
        p.waitFor();        
    }        
    catch ( IOException | InterruptedException ioe )       
    {            
        ioe.getMessage();
    }
    }//GEN-LAST:event_mnCalcActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        abrirImpresoras();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        dlgSoftware a = new dlgSoftware(this, true);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void smModUsuariosDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smModUsuariosDActionPerformed
        // TODO add your handling code here:
        try {
            dlgGestUsuarioD gud = new dlgGestUsuarioD(this, true);
            gud.setLocationRelativeTo(null);
            gud.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_smModUsuariosDActionPerformed

    private void btnIMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMDActionPerformed

        try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja WHERE ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                dlgCaja caja = new dlgCaja(this, true);
                caja.setLocationRelativeTo(null);
                caja.setVisible(true);
            } else {
                Mensajes.informacion("La caja ya fue inicializada.\n\nPuede comenzar a registrar compras o realizar ventas\nsin ningún inconveniente.");
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnIMDActionPerformed

    private void mnGCiudadMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGCiudadMActionPerformed
        // TODO add your handling code here:
        abrirCiudadMovil();
    }//GEN-LAST:event_mnGCiudadMActionPerformed

    private void mnGClasificacionMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGClasificacionMActionPerformed
        // TODO add your handling code here:
        abrirClasificacionMovil();
    }//GEN-LAST:event_mnGClasificacionMActionPerformed

    private void mnGUMMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGUMMActionPerformed
        // TODO add your handling code here:
        abrirUMMovil();
    }//GEN-LAST:event_mnGUMMActionPerformed

    private void mnGImpuestoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGImpuestoMActionPerformed
        // TODO add your handling code here:
        abrirIVAMovil();
        
    }//GEN-LAST:event_mnGImpuestoMActionPerformed

    private void mnGTimbradoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGTimbradoMActionPerformed
        // TODO add your handling code here:
        abrirTimbradoMovil();
    }//GEN-LAST:event_mnGTimbradoMActionPerformed

    private void mnGProductosM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGProductosM1ActionPerformed
        // TODO add your handling code here:
        jasper.ListaProductosMovilesV("\\Reports\\articulos\\ListaProductosx.jasper");
    }//GEN-LAST:event_mnGProductosM1ActionPerformed

    private void mnGProductosM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGProductosM2ActionPerformed
        // TODO add your handling code here:
        jasper.ListaClientesMoviles("\\Reports\\clientes\\ListaClientes.jasper");
    }//GEN-LAST:event_mnGProductosM2ActionPerformed

    private void mnGPuntoEmisionMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGPuntoEmisionMActionPerformed
        // TODO add your handling code here:
        abrirPuntoEmisionMovil();
    }//GEN-LAST:event_mnGPuntoEmisionMActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            dlgMovilesReparto mreparto = new dlgMovilesReparto(this, true);
            mreparto.setLocationRelativeTo(null);
            mreparto.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar los repartos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirRepartos();
        }
        
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void mnGProductosM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGProductosM3ActionPerformed
        // TODO add your handling code here:
        jasper.ListaProductosMovilesH("\\Reports\\articulos\\ListaProductos_Completo.jasper");
    }//GEN-LAST:event_mnGProductosM3ActionPerformed

    private void itemTVentasvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTVentasvActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteTotalVentas rsc = new dlgReporteTotalVentas(this, true);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_itemTVentasvActionPerformed

    private void itemTVentasv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTVentasv1ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteResumenCaja rsc = new dlgReporteResumenCaja(this, false);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_itemTVentasv1ActionPerformed

    private void btnRepartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepartosActionPerformed
        // TODO add your handling code here:7
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar los repartos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirRepartos();
        }
    }//GEN-LAST:event_btnRepartosActionPerformed

    private void btnGestionarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarComprasActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarCompras consCompras = new dlgConsultarCompras(this, true);
            consCompras.setLocationRelativeTo(null);
            consCompras.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnGestionarComprasActionPerformed

    private void btnGestionarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarVentasActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarFacturas cf = new dlgConsultarFacturas(this, true);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_btnGestionarVentasActionPerformed

    private void btnCMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCMDActionPerformed
        // TODO add your handling code here:
        try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                Mensajes.informacion("La caja ya fue cerrada.\n\nPodra acceder a este formulario para visualizar los movimientos en la siguiente apertura de caja.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
            } else {
                dlgCajaDia cajaDia = new dlgCajaDia(this, false);
                cajaDia.setLocationRelativeTo(null);
                cajaDia.setVisible(true);
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnCMDActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        abrirProductosMoviles();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnSalidaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaPActionPerformed
        // TODO add your handling code here:
         try {
            dlgSalidaMercaderia salida = new dlgSalidaMercaderia(this, true);
            salida.setLocationRelativeTo(null);
            salida.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnSalidaPActionPerformed

    private void mnGestGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGestGastosActionPerformed
        // TODO add your handling code here:
        try {
                dlgGestGastos Ggastos = new dlgGestGastos(this, true);
                Ggastos.setLocationRelativeTo(null);
                Ggastos.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_mnGestGastosActionPerformed

    private void mnIngresosVarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnIngresosVarios1ActionPerformed
        // TODO add your handling code here:
        Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        /*try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara registrar cobranzas u otros ingresos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
            } else {
                dlgIngreso ingreso = new dlgIngreso(this, true);
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }*/
    }//GEN-LAST:event_mnIngresosVarios1ActionPerformed

    private void itemRGLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRGLActionPerformed
        // TODO add your handling code here:
        try {
                dlgReporteGastoLocal RG = new dlgReporteGastoLocal(this, true);
                RG.setLocationRelativeTo(null);
                RG.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_itemRGLActionPerformed

    private void itemRGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRGRActionPerformed
        // TODO add your handling code here:
        try {
                dlgReporteGastoReparto RG = new dlgReporteGastoReparto(this, true);
                RG.setLocationRelativeTo(null);
                RG.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_itemRGRActionPerformed

    private void itemGestionarTRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarTRActionPerformed
        // TODO add your handling code here:
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar los repartos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirTransferencias();
        }
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos por motivos de desarrollo.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_itemGestionarTRActionPerformed

    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        // TODO add your handling code here:
        String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
        if (!fe.equals(Fecha.fechaCorrecta())) {
            Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara poder comenzar a registrar los repartos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
        } else {
            abrirTransferencias();
        }
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    private void btnGestionarVentasMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarVentasMovilActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarFacturasMovil1 cf = new dlgConsultarFacturasMovil1(this, true);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_btnGestionarVentasMovilActionPerformed

    private void mnGVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGVMActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarFacturasMovil cf = new dlgConsultarFacturasMovil(this, true);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_mnGVMActionPerformed

    private void mnInventarioStock_mayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInventarioStock_mayorActionPerformed
        // TODO add your handling code here:
        jasper.ListaProductosMovilesH("\\Reports\\articulos\\Inventario_Productos_con_stock.jasper");
    }//GEN-LAST:event_mnInventarioStock_mayorActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        // TODO add your handling code here:
        jasper.ListaProductosMovilesH("\\Reports\\articulos\\StockValorizadoCompleto.jasper");
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void mnGVM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGVM1ActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarFacturasMovil1 cf = new dlgConsultarFacturasMovil1(this, true);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_mnGVM1ActionPerformed

    private void mnComisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnComisionActionPerformed
        // TODO add your handling code here:
       /* dlgComisionEmpleado ce = new dlgComisionEmpleado(this, true);
        ce.setLocationRelativeTo(null);
        ce.setVisible(true);*/  
        Mensajes.Sistema("La función: Comisiones se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
    }//GEN-LAST:event_mnComisionActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteGananciaReparto ReCom = new dlgReporteGananciaReparto(this, true);
            ReCom.setLocationRelativeTo(this);
            ReCom.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteValorizadoRepartos StckREp = new dlgReporteValorizadoRepartos(this, true);
            StckREp.setLocationRelativeTo(null);
            StckREp.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void MitemRDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitemRDCActionPerformed
        // TODO add your handling code here:
        //Mensajes.Sistema("Esta función se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        try {
            dlgReporteComisiones ReCom = new dlgReporteComisiones(this, true);
            ReCom.setLocationRelativeTo(this);
            ReCom.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_MitemRDCActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteGananciaRepartoG ReCom = new dlgReporteGananciaRepartoG(this, true);
            ReCom.setLocationRelativeTo(this);
            ReCom.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteVentapoCliente rsc = new dlgReporteVentapoCliente(this, true);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void itemTVentasv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTVentasv2ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteCompraporCliente rsc = new dlgReporteCompraporCliente(this, true);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_itemTVentasv2ActionPerformed

    private void rpTransferenciasGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rpTransferenciasGeneralActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteTransferencias ReCom = new dlgReporteTransferencias(this, true);
            ReCom.setLocationRelativeTo(this);
            ReCom.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_rpTransferenciasGeneralActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        abrirDeuda();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        abrirCheques();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void MitemRDC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitemRDC1ActionPerformed
        // TODO add your handling code here:
        VisorReportes vr = new VisorReportes(null, true);
        try {
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat("\\Reports\\repartos\\ControlSTlimpio.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            //parametros.put("vendedor", cboResponsable.getSelectedItem());
            //parametros.put("fecha", txtFechaI.getText());
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
    }//GEN-LAST:event_MitemRDC1ActionPerformed

    private void mnGProductosM4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGProductosM4ActionPerformed
        // TODO add your hand-ling code here:
       // jasper.ListaProductosMovilesV("\\Reports\\repartos\\ControlST_CP.jasper");
       jasper.ListaProductosMovilesV("\\Reports\\articulos\\ListaProductosxx.jasper");
    }//GEN-LAST:event_mnGProductosM4ActionPerformed

    private void mnDetallePagoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDetallePagoProveedorActionPerformed
        // TODO add your handling code here:
        try {
            dlgConsultarPagosProveedor consPagos = new dlgConsultarPagosProveedor(this, true);
            consPagos.setLocationRelativeTo(null);
            consPagos.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_mnDetallePagoProveedorActionPerformed
    void abrirProveedor() {
        try {
            dlgProveedores proveedor = new dlgProveedores(this, true);
            proveedor.setLocationRelativeTo(null);
            proveedor.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }
    void abrirRepartos() {
        try {
            dlgRepartos repartos = new dlgRepartos(null, true);
            repartos.setLocationRelativeTo(null);
            repartos.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }

    void cargarIcono() {
        try {
            java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png"));
            setIconImage(icon);
            setVisible(true);
            this.setLocationRelativeTo(null);
        } catch (Exception e) {
            Mensajes.error("No se pudo cargo el icono del sistema.");
        }
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MitemRDC;
    private javax.swing.JMenuItem MitemRDC1;
    public static javax.swing.JPopupMenu.Separator S1;
    public static javax.swing.JPopupMenu.Separator S10;
    public static javax.swing.JPopupMenu.Separator S11;
    public static javax.swing.JPopupMenu.Separator S12;
    public static javax.swing.JPopupMenu.Separator S3;
    public static javax.swing.JPopupMenu.Separator S4;
    public static javax.swing.JPopupMenu.Separator S5;
    public static javax.swing.JPopupMenu.Separator S7;
    public static javax.swing.JPopupMenu.Separator S8;
    public static javax.swing.JPopupMenu.Separator S9;
    public static javax.swing.JButton btnCMD;
    public static javax.swing.JButton btnClientes;
    public static javax.swing.JButton btnCompras;
    public static javax.swing.JButton btnGestionarCompras;
    public static javax.swing.JButton btnGestionarVentas;
    public static javax.swing.JButton btnGestionarVentasMovil;
    public static javax.swing.JButton btnIMD;
    public static javax.swing.JButton btnProductos;
    public static javax.swing.JButton btnRepartos;
    public static javax.swing.JButton btnSalidaP;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnTransferencia;
    public static javax.swing.JButton btnVentas;
    private javax.swing.JMenu divisor1;
    private javax.swing.JMenu divisor2;
    public static javax.swing.JMenu divisor3;
    public static javax.swing.JMenu divisor4;
    public static javax.swing.JMenu divisor5;
    private javax.swing.JMenuItem itemCiudades;
    private javax.swing.JMenuItem itemEmpresa;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemFamilia;
    public static javax.swing.JMenuItem itemFondo;
    private javax.swing.JMenuItem itemGestionarTR;
    private javax.swing.JMenuItem itemImportar;
    private javax.swing.JMenuItem itemLaboratorio;
    private javax.swing.JMenuItem itemRGL;
    private javax.swing.JMenuItem itemRGR;
    private javax.swing.JMenuItem itemSucursal;
    private javax.swing.JMenuItem itemTVentasv;
    private javax.swing.JMenuItem itemTVentasv1;
    private javax.swing.JMenuItem itemTVentasv2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem38;
    public static javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    public static javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JToolBar.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public static javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    public static javax.swing.JPopupMenu.Separator jSeparator23;
    public static javax.swing.JPopupMenu.Separator jSeparator24;
    public static javax.swing.JPopupMenu.Separator jSeparator25;
    public static javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JLabel lbEmpresa;
    private javax.swing.JLabel lbIp;
    public static javax.swing.JLabel lbPerfil;
    public static javax.swing.JLabel lbRUC;
    public static javax.swing.JLabel lbSucursal;
    public static javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbversion;
    private javax.swing.JMenuBar mbBarraMenu;
    public static javax.swing.JMenu mnAyuda;
    public static javax.swing.JMenu mnAyuda1;
    public static javax.swing.JMenu mnAyuda2;
    public static javax.swing.JMenu mnCaja;
    private javax.swing.JMenuItem mnCalc;
    private javax.swing.JMenuItem mnCerrarSistema;
    public static javax.swing.JMenu mnCheques;
    private javax.swing.JMenuItem mnCierredeCaja;
    public static javax.swing.JMenu mnClientes;
    public static javax.swing.JMenuItem mnComision;
    public static javax.swing.JMenu mnCompras;
    public static javax.swing.JMenu mnConfiguracion;
    public static javax.swing.JMenuItem mnDetallePagoProveedor;
    public static javax.swing.JMenu mnDeuda;
    public static javax.swing.JMenu mnEmpleados;
    private javax.swing.JMenuItem mnGC;
    private javax.swing.JMenuItem mnGCiudadM;
    private javax.swing.JMenuItem mnGClasificacionM;
    private javax.swing.JMenuItem mnGImpuestoM;
    private javax.swing.JMenuItem mnGProductosM1;
    private javax.swing.JMenuItem mnGProductosM2;
    private javax.swing.JMenuItem mnGProductosM3;
    private javax.swing.JMenuItem mnGProductosM4;
    private javax.swing.JMenuItem mnGPuntoEmisionM;
    private javax.swing.JMenuItem mnGTimbradoM;
    private javax.swing.JMenuItem mnGUMM;
    private javax.swing.JMenuItem mnGV;
    public static javax.swing.JMenuItem mnGVM;
    public static javax.swing.JMenuItem mnGVM1;
    private javax.swing.JMenuItem mnGestGastos;
    public static javax.swing.JMenu mnGsIn;
    public static javax.swing.JMenu mnInformacion;
    private javax.swing.JMenuItem mnIngresosVarios1;
    private javax.swing.JMenuItem mnIniciarCaja;
    private javax.swing.JMenuItem mnInventarioCompleto;
    private javax.swing.JMenuItem mnInventarioStock_mayor;
    public static javax.swing.JMenu mnLogistica;
    public static javax.swing.JMenu mnMantenimiento;
    public static javax.swing.JMenuItem mnNCProveedor;
    public static javax.swing.JMenuItem mnPagoProveedor;
    public static javax.swing.JMenu mnProductos;
    public static javax.swing.JMenu mnProveedores;
    public static javax.swing.JMenu mnReparto;
    public static javax.swing.JMenu mnSeguridad;
    public static javax.swing.JMenu mnSistema;
    public static javax.swing.JMenu mnTransferencias;
    public static javax.swing.JMenu mnVentas;
    public static javax.swing.JPanel panelFondo;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    public static javax.swing.JMenu rpArticulos;
    public static javax.swing.JMenu rpClientes;
    public static javax.swing.JMenu rpCompras;
    public static javax.swing.JMenu rpGanancias;
    public static javax.swing.JMenu rpGastosIngresos;
    public static javax.swing.JMenu rpTransferencias;
    private javax.swing.JMenuItem rpTransferenciasGeneral;
    public static javax.swing.JMenu rpVendedores;
    public static javax.swing.JMenu rpVendedores1;
    public static javax.swing.JMenu rpVentas;
    private javax.swing.JMenuItem smModUsuarios;
    public static javax.swing.JMenuItem smModUsuariosD;
    // End of variables declaration//GEN-END:variables
}
