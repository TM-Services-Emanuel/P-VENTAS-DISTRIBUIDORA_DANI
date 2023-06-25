package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Numero_a_Letra;
import Componentes.PrinterService;
import Componentes.Redondeo;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimal1;
import Componentes.cargarComboBoxMovil;
import Componentes.Software;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class dlgConsultarFacturasMovil1 extends javax.swing.JDialog {

    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;

    static public Numero_a_Letra d;

    public dlgConsultarFacturasMovil1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        CabecerasTablas.consVentasMoviles(tbVentasMoviles);
        CabecerasTablas.consDetalleVentasMoviles(tbDetalleVentasMoviles);
        controlFactura.listFacturasMovil1(tbVentasMoviles);
        Renders();
        txtIdPE.setVisible(false);
        txtIdT.setVisible(false);
        prepararBD();
        d = new Numero_a_Letra();

    }

    public static void prepararBD() {
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

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de Ventas Móviles realizadas");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de Ventas Móviles realizadas");
        }
    }

    public static void Renders() {
        dlgConsultarFacturasMovil1.tbVentasMoviles.getColumnModel().getColumn(11).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarFacturasMovil1.tbDetalleVentasMoviles.getColumnModel().getColumn(4).setCellRenderer(new RenderDecimal1());
        dlgConsultarFacturasMovil1.tbDetalleVentasMoviles.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());
    }

    public void filtrar() {
        if (ckHabilitar.isSelected()) {
            cboTimbrado.setEnabled(true);
            btnFiltrar.setEnabled(true);
            cargarComboBoxMovil.cargar(cboTimbrado, "SELECT idtimbrado,descripcion FROM timbrado WHERE estado='Activo'");
        } else {
            cboTimbrado.setSelectedIndex(0);
            cboTimbrado.setEnabled(false);
            cbPE.setEnabled(false);
            btnFiltrar.setEnabled(false);
            btnActualizarActionPerformed(null);

        }
    }

    public static void imprimirTicketOriginal(String Timbrado, String Desde, String Hasta, String Condicion, String Factura,
            String Fechas, String Hora, String Cliente, String Ruc, String Vendedor, String Total, String Exenta, String Cinco, String Diez) {
        //Impresora matricial tmu-220
        String empresa = null;
        String ruc = null;
        String celular = null;
        String direccion = null;

        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());
        DecimalFormat formateador = new DecimalFormat("#,###");
        try {
            rs = sentencia.executeQuery("SELECT * FROM empresa WHERE estado='S'");
            rs.last();
            empresa = rs.getString("razon_social");
            ruc = rs.getString("ruc");
            celular = rs.getString("telefono");
            direccion = rs.getString("direccion");
            rs.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
        }
        try {
            String Ticket = "              " + empresa + "\n";
            Ticket += "         VENTAS DE LACTEOS LACTOLANDA\n";
            Ticket += "                RUC: " + ruc + "\n";
            Ticket += "         CEL: " + celular + "\n";
            Ticket += "     " + direccion + "\n";
            Ticket += "            CNEL. OVIEDO - PARAGUAY\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "              TIMBRADO: " + Timbrado + "\n";
            Ticket += "  VALIDO DESDE: " + Desde + " HASTA: " + Hasta + "\n";
            Ticket += "               I.V.A. INCLUIDO\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "FACTURA " + Condicion + " NRO: " + Factura + "\n";
            Ticket += "FECHA/HORA: " + Fecha.formatoFechaFF(Fechas) + " " + Hora + "\n";
            Ticket += "VENDEDOR: " + Vendedor + "\n";
            Ticket += "\n";
            Ticket += "CLIENTE: " + Cliente + "\n";
            Ticket += "RUC/CI: " + Ruc + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\n";
            Ticket += "----------------------------------------------\n";
            int filas = tbDetalleVentasMoviles.getRowCount();
            for (int i = 0; i < filas; i++) {
                String codB = tbDetalleVentasMoviles.getValueAt(i, 1).toString().trim();
                String Descripcion = tbDetalleVentasMoviles.getValueAt(i, 2).toString().trim();
                String cant = tbDetalleVentasMoviles.getValueAt(i, 3).toString().trim();
                String Punit = tbDetalleVentasMoviles.getValueAt(i, 4).toString().trim().replace(".", "").replace(",", "");
                String Mont = tbDetalleVentasMoviles.getValueAt(i, 5).toString().trim().replace(".", "").replace(",", "");
                String iva = tbDetalleVentasMoviles.getValueAt(i, 6).toString().trim() + "%";
                String um = tbDetalleVentasMoviles.getValueAt(i, 7).toString().trim();

                Ticket += String.format("%1$1s", codB /*+ "-" + Descripcion */+ "\n");
                Ticket += String.format("%1$1s", /*codB + "-" + */Descripcion + "\n");
                Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant + " " + um, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            Ticket += "\n";
            Ticket += "==============================================\n";
            //Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", formateador.format(Integer.parseInt(Total))) + "\n";
            Ticket += "TOTAL A PAGAR Gs: " + formateador.format(Integer.parseInt(Total)) + "\n";
            //Ticket += "           TOTAL Gs.:"+tot+"\n";
            Ticket += "==============================================\n";
            String letras = d.Convertir(Total, true);
            Ticket += String.format("%1$1s", letras + "\n");
            //Ticket += "\n";
            Ticket += "==============================================\n";
            Ticket += "\n";
            Ticket += "-------------- TOTALES GRAVADA ---------------\n";
            Ticket += "EXENTAS     ------>              " + formateador.format(Integer.parseInt(Exenta.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 5%  ------>              " + formateador.format(Integer.parseInt(Cinco.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 10% ------>              " + formateador.format(Integer.parseInt(Diez.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\n";
            int iv5 = Redondeo.redondearI(Integer.parseInt(Cinco.replace(".", "").replace(",", "")) / 21);
            Ticket += "I.V.A. 5%   ------>              " + formateador.format(iv5) + "\n";
            int iv10 = Redondeo.redondearI(Integer.parseInt(Diez.replace(".", "").replace(",", "")) / 11);
            Ticket += "I.V.A. 10%  ------>              " + formateador.format(Redondeo.redondearI(iv10)) + "\n";
            Ticket += "----------------------------------------------\n";
            String totaliva = String.valueOf(iv5 + iv10);
            //Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
            Ticket += "TOTAL I.V.A.: " + formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "\n";
            Ticket += "EFECTIVO:  0\n";
            Ticket += "VUELTO:    0\n";
            Ticket += "\n";
            Ticket += "ORIGINAL:  CLIENTE\n";
            // Ticket += "DUPLICADO: Archivo Tributario\n";
            Ticket += "\n";
            Ticket += "              " + empresa + "\n";
            Ticket += "            AGRADECE SU PREFERENCIA\n";
            Ticket += "\n";

            printerService.printString(Ticket);
            //print some stuff

            // cut that paper!
            byte[] cutP = new byte[]{0x1d, 'V', 1};

            printerService.printBytes(cutP);
        } catch (Exception e) {
        }
    }

    public static void imprimirTicketDuplicado(String Timbrado, String Desde, String Hasta, String Condicion, String Factura,
            String Fechas, String Hora, String Cliente, String Ruc, String Vendedor, String Total, String Exenta, String Cinco, String Diez) {
        //Impresora matricial tmu-220
        String empresa = null;
        String ruc = null;
        String celular = null;
        String direccion = null;

        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());
        DecimalFormat formateador = new DecimalFormat("#,###");
        try {
            rs = sentencia.executeQuery("SELECT * FROM empresa WHERE estado='S'");
            rs.last();
            empresa = rs.getString("razon_social");
            ruc = rs.getString("ruc");
            celular = rs.getString("telefono");
            direccion = rs.getString("direccion");
            rs.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
        }
        try {
            String Ticket = "              " + empresa + "\n";
            Ticket += "         VENTAS DE LACTEOS LACTOLANDA\n";
            Ticket += "                RUC: " + ruc + "\n";
            Ticket += "         CEL: " + celular + "\n";
            Ticket += "     " + direccion + "\n";
            Ticket += "            CNEL. OVIEDO - PARAGUAY\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "              TIMBRADO: " + Timbrado + "\n";
            Ticket += "  VALIDO DESDE: " + Desde + " HASTA: " + Hasta + "\n";
            Ticket += "               I.V.A. INCLUIDO\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "FACTURA " + Condicion + " NRO: " + Factura + "\n";
            Ticket += "FECHA/HORA: " + Fecha.formatoFechaFF(Fechas) + " " + Hora + "\n";
            Ticket += "VENDEDOR: " + Vendedor + "\n";
            Ticket += "\n";
            Ticket += "CLIENTE: " + Cliente + "\n";
            Ticket += "RUC/CI: " + Ruc + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\n";
            Ticket += "----------------------------------------------\n";
            int filas = tbDetalleVentasMoviles.getRowCount();
            for (int i = 0; i < filas; i++) {
                String codB = tbDetalleVentasMoviles.getValueAt(i, 1).toString().trim();
                String Descripcion = tbDetalleVentasMoviles.getValueAt(i, 2).toString().trim();
                String cant = tbDetalleVentasMoviles.getValueAt(i, 3).toString().trim();
                String Punit = tbDetalleVentasMoviles.getValueAt(i, 4).toString().trim().replace(".", "").replace(",", "");
                String Mont = tbDetalleVentasMoviles.getValueAt(i, 5).toString().trim().replace(".", "").replace(",", "");
                String iva = tbDetalleVentasMoviles.getValueAt(i, 6).toString().trim() + "%";
                String um = tbDetalleVentasMoviles.getValueAt(i, 7).toString().trim();

                //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
                Ticket += String.format("%1$1s", codB+ "\n");
                Ticket += String.format("%1$1s", Descripcion + "\n");
                Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant + " " + um, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            Ticket += "\n";
            Ticket += "==============================================\n";
            //Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", formateador.format(Integer.parseInt(Total))) + "\n";
            Ticket += "TOTAL A PAGAR Gs: " + formateador.format(Integer.parseInt(Total)) + "\n";
            //Ticket += "           TOTAL Gs.:"+tot+"\n";
            Ticket += "==============================================\n";
            String letras = d.Convertir(Total, true);
            Ticket += String.format("%1$1s", letras + "\n");
            //Ticket += "\n";
            Ticket += "==============================================\n";
            Ticket += "\n";
            Ticket += "-------------- TOTALES GRAVADA ---------------\n";
            Ticket += "EXENTAS     ------>              " + formateador.format(Integer.parseInt(Exenta.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 5%  ------>              " + formateador.format(Integer.parseInt(Cinco.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 10% ------>              " + formateador.format(Integer.parseInt(Diez.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\n";
            int iv5 = Redondeo.redondearI(Integer.parseInt(Cinco.replace(".", "").replace(",", "")) / 21);
            Ticket += "I.V.A. 5%   ------>              " + formateador.format(iv5) + "\n";
            int iv10 = Redondeo.redondearI(Integer.parseInt(Diez.replace(".", "").replace(",", "")) / 11);
            Ticket += "I.V.A. 10%  ------>              " + formateador.format(Redondeo.redondearI(iv10)) + "\n";
            Ticket += "----------------------------------------------\n";
            String totaliva = String.valueOf(iv5 + iv10);
            //Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
            Ticket += "TOTAL I.V.A.: " + formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "\n";
            Ticket += "EFECTIVO:  0\n";
            Ticket += "VUELTO:    0\n";
            Ticket += "\n";
            //Ticket += "ORIGINAL:  CLIENTE\n";
            Ticket += "DUPLICADO: ARCHIVO TRIBUTARIO\n";
            Ticket += "\n";
            Ticket += "              " + empresa + "\n";
            Ticket += "            AGRADECE SU PREFERENCIA\n";
            Ticket += "\n";
            
            printerService.printString(Ticket);
            //print some stuff

            // cut that paper!
            byte[] cutP = new byte[]{0x1d, 'V', 1};

            printerService.printBytes(cutP);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentasMoviles = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleVentasMoviles = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboTimbrado = new javax.swing.JComboBox<>();
        ckHabilitar = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        cbPE = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        txtIdPE = new javax.swing.JTextField();
        txtIdT = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        itemBuscarA = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemBuscar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnActualizar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refress_30.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR LISTADO");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnReimprimir.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/printer30.png"))); // NOI18N
        btnReimprimir.setText("RE-IMPRIMIR FACTURA");
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReimprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });
        jPanel2.add(btnReimprimir);

        btnAnular.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/compras_anular.png"))); // NOI18N
        btnAnular.setText("ANULAR FACTURA");
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnular);

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
        jPanel2.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setOpaque(false);

        tbVentasMoviles.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbVentasMoviles.setModel(new javax.swing.table.DefaultTableModel(
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
        tbVentasMoviles.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbVentasMoviles.setOpaque(false);
        tbVentasMoviles.getTableHeader().setResizingAllowed(false);
        tbVentasMoviles.getTableHeader().setReorderingAllowed(false);
        tbVentasMoviles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVentasMovilesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbVentasMovilesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbVentasMoviles);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jScrollPane2.setOpaque(false);

        tbDetalleVentasMoviles.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tbDetalleVentasMoviles.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalleVentasMoviles.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDetalleVentasMoviles.setOpaque(false);
        tbDetalleVentasMoviles.getTableHeader().setResizingAllowed(false);
        tbDetalleVentasMoviles.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDetalleVentasMoviles);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(17, 35, 46));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPCIONES DE FILTRADO");
        jLabel1.setOpaque(true);
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 290, 37));

        jLabel2.setText("TIMBRADO NRO:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 71, -1, -1));

        cboTimbrado.setEnabled(false);
        cboTimbrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimbradoActionPerformed(evt);
            }
        });
        jPanel3.add(cboTimbrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 67, 160, -1));

        ckHabilitar.setText("Habilitar filtrado");
        ckHabilitar.setOpaque(false);
        ckHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckHabilitarActionPerformed(evt);
            }
        });
        jPanel3.add(ckHabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 43, 105, -1));

        jLabel3.setText("PUNTO DE EMISIÓN:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 99, -1, -1));

        cbPE.setEnabled(false);
        cbPE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPEActionPerformed(evt);
            }
        });
        jPanel3.add(cbPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 95, 160, -1));

        btnFiltrar.setText("FILTRAR");
        btnFiltrar.setEnabled(false);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 128, -1, -1));
        jPanel3.add(txtIdPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 44, 50, -1));
        jPanel3.add(txtIdT, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 44, 50, -1));

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu2.setText("OPCIONES");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemBuscarA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarA.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscarA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refress_30.png"))); // NOI18N
        itemBuscarA.setText("ACTUALIZAR LISTADO                                                              ");
        itemBuscarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarAActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscarA);
        jMenu2.add(jSeparator4);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/compras_anular.png"))); // NOI18N
        itemQuitar.setText("ANULAR FACTURA");
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        jMenu2.add(itemQuitar);
        jMenu2.add(jSeparator5);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        itemSalir.setText("SALIR");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu2.add(itemSalir);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("BÚSQUEDA");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        itemBuscar.setText("POR NUMERO DE FACTURA                                                  ");
        itemBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbVentasMovilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMovilesMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbVentasMovilesMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarFacturasMovil1.tbVentasMoviles.getSelectedRow() < 0) {
            Mensajes.error("Seleccione una fila de la tabla");
        } else {
            int x = dlgConsultarFacturasMovil1.tbVentasMoviles.getSelectedRow();
            String estado = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 11).toString();
            if (estado.equals("ANULADO")) {
                Mensajes.informacion("Esta Venta Móvil ya fue anulada");
            } else {
                try {
                    String msg;
                    int rpta = Mensajes.confirmar("Seguro que desea Anular esta Venta Móvil?");
                    if (rpta == 0) {
                        msg = controlFactura.anularVentaMovil1();
                        if (msg == null) {
                            CabecerasTablas.limpiarTablasVentasMoviles(tbVentasMoviles);
                            CabecerasTablas.limpiarTablasDetalleVentasMoviles(tbDetalleVentasMoviles);
                            CabecerasTablas.consVentasMoviles(tbVentasMoviles);
                            CabecerasTablas.consDetalleVentasMoviles(tbDetalleVentasMoviles);
                            controlFactura.listFacturasMovil1(tbVentasMoviles);
                            Renders();
                        }
                    }
                } catch (Exception e) {
                    Mensajes.informacion("Seleccione la fila a eliminar:" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablasVentasMoviles(tbVentasMoviles);
        CabecerasTablas.limpiarTablasDetalleVentasMoviles(tbDetalleVentasMoviles);
        CabecerasTablas.consVentasMoviles(tbVentasMoviles);
        CabecerasTablas.consDetalleVentasMoviles(tbDetalleVentasMoviles);
        controlFactura.listFacturasMovil1(tbVentasMoviles);
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tbVentasMovilesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMovilesMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablasDetalleVentasMoviles(tbDetalleVentasMoviles);
            controlFactura.listDetalleVentasMoviles1(tbDetalleVentasMoviles);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbVentasMovilesMousePressed

    private void itemBuscarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarAActionPerformed
        // TODO add your handling code here:
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_itemBuscarAActionPerformed

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:
        btnAnularActionPerformed(null);
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarActionPerformed
        // TODO add your handling code here:
        try {
            String cod = (JOptionPane.showInputDialog("Ingrese N° de Factura Compra"));
            for (int i = 0; i < tbVentasMoviles.getRowCount(); i++) {
                if (tbVentasMoviles.getValueAt(i, 5).equals(cod)) {
                    tbVentasMoviles.changeSelection(i, 1, false, false);
                    tbVentasMovilesMousePressed(null);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Consulta cancelada" + e.getMessage());
        }
    }//GEN-LAST:event_itemBuscarActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarFacturasMovil1.tbVentasMoviles.getSelectedRow() < 0) {
            Mensajes.error("Seleccione una fila de la tabla");
        } else {
            int x = dlgConsultarFacturasMovil1.tbVentasMoviles.getSelectedRow();
            String idventa = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 0).toString();
            String idemision = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 1).toString();
            String estado = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 13).toString();
            if (estado.equals("ANULADO")) {
                Mensajes.informacion("Esta Venta Móvil ya fue anulada");
            } else {
                try {
                    String msg;
                    int rpta = Mensajes.confirmar("Seguro que desea RE-IMPRIMIR esta Factura Móvil?");
                    if (rpta == 0) {
                        int fil = dlgConsultarFacturasMovil1.tbVentasMoviles.getSelectedRow();
                        String Timbrado = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 2).toString();
                        String Desde = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 3).toString();
                        String Hasta = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 4).toString();
                        String Condicion = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 6).toString();
                        String Factura = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 5).toString();
                        String Fecha = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 7).toString();
                        String Hora = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 8).toString();
                        String Cliente = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 10).toString();
                        String Ruc = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 9).toString();
                        String Vendedor = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 12).toString();
                        String Total = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 11).toString().replace(".", "").replace(",", "");
                        String Exenta = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 14).toString();
                        String Cinco = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 15).toString();
                        String Diez = dlgConsultarFacturasMovil1.tbVentasMoviles.getValueAt(x, 16).toString();

                        imprimirTicketOriginal(Timbrado, Desde, Hasta, Condicion, Factura, Fecha, Hora, Cliente, Ruc, Vendedor, Total, Exenta, Cinco, Diez);
                        imprimirTicketDuplicado(Timbrado, Desde, Hasta, Condicion, Factura, Fecha, Hora, Cliente, Ruc, Vendedor, Total, Exenta, Cinco, Diez);
                    
                    }

                } catch (Exception e) {

                }
            }
        }
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        if (cboTimbrado.getSelectedIndex() == 0) {
            Mensajes.error("SELECCIONE UN TIMBRADO PARA HACER EFECTIVO EL FILTRADO");
        } else {
            if (cbPE.getSelectedIndex() == 0) {
                CabecerasTablas.limpiarTablasVentasMoviles(tbVentasMoviles);
                CabecerasTablas.limpiarTablasDetalleVentasMoviles(tbDetalleVentasMoviles);
                CabecerasTablas.consVentasMoviles(tbVentasMoviles);
                CabecerasTablas.consDetalleVentasMoviles(tbDetalleVentasMoviles);
                controlFactura.listFacturasMovilesT1(tbVentasMoviles, txtIdT.getText());
                Renders();

            } else {
                CabecerasTablas.limpiarTablasVentasMoviles(tbVentasMoviles);
                CabecerasTablas.limpiarTablasDetalleVentasMoviles(tbDetalleVentasMoviles);
                CabecerasTablas.consVentasMoviles(tbVentasMoviles);
                CabecerasTablas.consDetalleVentasMoviles(tbDetalleVentasMoviles);
                controlFactura.listFacturasMovilesTPE1(tbVentasMoviles, txtIdT.getText(), txtIdPE.getText());
                Renders();

            }
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void ckHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckHabilitarActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_ckHabilitarActionPerformed

    private void cboTimbradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimbradoActionPerformed
        // TODO add your handling code here:
        try {
            int codT = 0;
            if (cboTimbrado.getSelectedIndex() == 0) {
                txtIdT.setText("");
                cbPE.setSelectedIndex(0);
                cbPE.setEnabled(false);
            } else {
                cbPE.setEnabled(true);
                try {
                    String id = cargarComboBoxMovil.getCodidgo(cboTimbrado);
                    txtIdT.setText(id);
                } catch (Exception e) {
                    txtIdT.setText("");
                }
                prepararBD();
                String timb;
                timb = cboTimbrado.getSelectedItem().toString();
                try {
                    rs = sentencia.executeQuery("SELECT * FROM timbrado WHERE descripcion='" + timb + "'");
                    rs.last();
                    codT = rs.getInt("idtimbrado");
                    rs.close();
                } catch (SQLException ex) {
                    Mensajes.error("Error al querer obtener valor del Timbrado: " + ex.getMessage());
                }
                cargarComboBoxMovil.cargarPE(cbPE, "SELECT idemision,establecimiento,puntoemision,direccion FROM v_puntoemision WHERE idtimbrado=" + codT);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cboTimbradoActionPerformed

    private void cbPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPEActionPerformed
        // TODO add your handling code here:
        try {
            String id = cargarComboBoxMovil.getCodidgo(cbPE);
            txtIdPE.setText(id);
        } catch (Exception e) {
            txtIdPE.setText("");
        }
    }//GEN-LAST:event_cbPEActionPerformed

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
            java.util.logging.Logger.getLogger(dlgConsultarFacturasMovil1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturasMovil1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturasMovil1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturasMovil1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarFacturasMovil1 dialog = new dlgConsultarFacturasMovil1(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbPE;
    private javax.swing.JComboBox<String> cboTimbrado;
    private javax.swing.JCheckBox ckHabilitar;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemBuscarA;
    private javax.swing.JMenuItem itemQuitar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    public static javax.swing.JTable tbDetalleVentasMoviles;
    public static javax.swing.JTable tbVentasMoviles;
    private javax.swing.JTextField txtIdPE;
    private javax.swing.JTextField txtIdT;
    // End of variables declaration//GEN-END:variables
}
