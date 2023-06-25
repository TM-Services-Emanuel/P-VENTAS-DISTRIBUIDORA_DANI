package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Numero_a_Letra;
import Componentes.RenderDecimal;
import Componentes.RenderDecimal2;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Componentes.PrinterService;
import Componentes.RenderCantidadconPuntos;
import Componentes.RenderDecimal1;
import Componentes.traerIP;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import Datos.GestionarArticulosMovil;
import Datos.GestionarCliente;
import Datos.GestionarFactura;
import Datos.GestionarVendedor;
import java.awt.event.KeyEvent;
import Modelo.ArticuloMovil;
import Modelo.ClienteMovil;
import Modelo.Vendedor;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public final class dlgVentas extends javax.swing.JDialog {

    private static MariaDbConnection con;
    private static MariaDbStatement stTransaccion;
    private static MariaDbStatement st;
    private static MariaDbConnection conMovil;
    private static MariaDbStatement stTransaccionMovil;
    private static MariaDbStatement stMovil;
    public static int PrecioVenta;
    public static double costoiva;
    public static double descuento;
    public static String UsuarioL = "";
    public Reporte jasper;
    static String emp;
    static String dir;
    static String cel;

    private static String Timbrado;
    private static String Desde;
    private static String Hasta;

    static public Numero_a_Letra d;

    private Dimension dim;

    public dlgVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        //prepararBD();
        jasper = new Reporte();
        CabecerasTablas.ventas(tbDetalle);
        Cancelar();
        pintarCondicion();
        Invisible();

        obtenerTIMBRA();
        titulo();
        d = new Numero_a_Letra();

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("VENTA DE PRODUCTOS");
        } else {
            this.setTitle(Software.getSoftware() + " - VENTA DE PRODUCTOS");
        }
        if (Timbrado == null) {
            lbTimbrado.setText("");
        } else {
            lbTimbrado.setText("TIMBRADO N°: " + Timbrado);
        }
        if (Desde == null && Hasta == null) {
            lbValidaz.setText("");
        } else {
            lbValidaz.setText("PERIODO DE VALIDEZ: " + Desde + " AL " + Hasta);
        }
    }

    public static void Cancelar() {
        limpiarCampos();
        //dcFecha.setEnabled(false);
        btnBuscarClientes.setEnabled(false);
        rContado.setEnabled(false);
        rContado.setSelected(true);
        rCredito.setEnabled(false);
        btnBuscarArticulo.setEnabled(false);
        txtCant.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnNuevo.requestFocus();
        itemNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        itemGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        itemCancelar.setEnabled(false);
        itemBuscarA.setEnabled(false);
        itemBuscarC.setEnabled(false);
        itemQuitar.setEnabled(false);
        btnSalir.setEnabled(true);
        itemSalir.setEnabled(true);
        cant();
    }

    public void Rendes() {
        dlgVentas.tbDetalle.getColumnModel().getColumn(5).setCellRenderer(new RenderCantidadconPuntos());
        dlgVentas.tbDetalle.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
        dlgVentas.tbDetalle.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal1());
        dlgVentas.tbDetalle.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimal1());
        dlgVentas.tbDetalle.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal1());
        dlgVentas.tbDetalle.getColumnModel().getColumn(10).setCellRenderer(new RenderDecimal());
    }

    public void Invisible() {
        lbComprobante.setVisible(false);
        txtEstablecimiento.setVisible(false);
        txtEmision.setVisible(false);
        txtFacturaN.setVisible(false);
        btnAdd.setVisible(false);
        btnRestar.setVisible(false);
        lbCond.setVisible(false);
        txtTotalL.setVisible(false);
        lbCred.setVisible(false);
        txtExentaL.setVisible(false);
        txt5L.setVisible(false);
        txt10L.setVisible(false);
        txtNetoL.setVisible(false);
        txtDescuentoL.setVisible(false);
        txtCodArticulo.setVisible(false);
        txtCodCliente.setVisible(false);
        txtdisponibleL.setVisible(false);
        txtFecha.setVisible(false);
        txtidEmision.setVisible(false);
        txt5libre.setVisible(false);
        txt10Libre.setVisible(false);
        btnCant.setVisible(false);
        txtTotalCosto.setVisible(false);
    }

    private static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            if (con == null) {
                Mensajes.error("No hay Conexion con la Base de Datos");
            } else {
                st = (MariaDbStatement) con.createStatement();
                stTransaccion = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            Mensajes.error("ERR_CON: " + e.getMessage());
        }
        try {
            conMovil = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (conMovil == null) {
                Mensajes.error("No hay Conexion con la Base de Datos Móvil");
            } else {
                stMovil = (MariaDbStatement) conMovil.createStatement();
                stTransaccionMovil = (MariaDbStatement) conMovil.createStatement();

            }
        } catch (SQLException e) {
            Mensajes.error("ERR_CON_M: " + e.getMessage());
        }
    }

    public static void cant() {
        int total = tbDetalle.getRowCount();
        if (total == 0) {
            etiCant.setText(" LISTA VACÍA".toUpperCase());
        } else if (total == 1) {
            etiCant.setText(" SE CONTABILIZA 1 ITEM EN LA TABLA DE VENTA".toUpperCase());
        } else if (total > 1) {
            etiCant.setText(" SE CONTABILIZAN " + String.valueOf(total) + " ITEMS EN LA TABLA DE VENTA".toUpperCase());
        }
    }

    private void pintarCondicion() {
        if (rContado.isSelected()) {
            rContado.setFont(new java.awt.Font("Roboto", 1, 11));
            rContado.setForeground(new java.awt.Color(0, 102, 102));
            rCredito.setFont(new java.awt.Font("Roboto", 0, 11));
            rCredito.setForeground(new java.awt.Color(0, 0, 0));
            lbCond.setText("CONTADO");
        } else {
            rContado.setFont(new java.awt.Font("Roboto", 0, 11));
            rContado.setForeground(new java.awt.Color(0, 0, 0));
            rCredito.setFont(new java.awt.Font("Roboto", 1, 11));
            rCredito.setForeground(new java.awt.Color(0, 102, 102));
            lbCond.setText("CREDITO");
        }
    }

    private static void limpiarCampos() {
        txtCod.setText("");
        txtidEmision.setText("");
        txtFacturaN.setText("");
        txtFecha.setText("");
        txtfechaF.setText("");
        txtHora.setText("");
        txtFacturaN.setText("");
        txtCodCliente.setText("");
        txtRuc.setText("");
        txtRazonS.setText("");
        rContado.isSelected();
        txtTotalL.setText("0");
        txtTotal.setText("0");
        txtArt.setText("");
        txtCant.setText("");
        txtExenta.setText("0");
        txtExentaL.setText("0");
        txt5L.setText("0");
        txt5.setText("0");
        txt10L.setText("0");
        txt10.setText("0");
        txtNetoL.setText("0");
        txt5libre.setText("");
        txt10Libre.setText("");
        txtDescuentoL.setText("0");
        lbCond.setText("");
        txtCaja.setText("");
        txtEstablecimiento.setText("");
        txtEmision.setText("");
        rContado.setSelected(true);
        txtTotalCosto.setText("");
        CabecerasTablas.limpiarTablasVentas(tbDetalle);
        CabecerasTablas.ventas(tbDetalle);
        controlFactura.canCelar();
        txtAbonoF.setText("0");
        txtAbonoT.setText("0");
        txtVueltoF.setText("0");
        txtVueltoT.setText("0");
        lbEmpleadoT.setText("");
        lbEmpleadoF.setText("");
    }

    public static void habilitarCANTCOSTO() {
        if (txtArt.getText().isEmpty()) {
            txtCant.setEnabled(false);
        } else {
            txtCant.setEnabled(true);
        }
    }

    /* public static void imprimirTicketO() {

        try {

            PrinterMatrix printer = new PrinterMatrix();
            Extenso e = new Extenso();

            e.setNumber(20.30);
            //Definir el tamanho del papel para la impresion de dinamico y 32 columnas
            int filas = tbDetalle.getRowCount();
            int tamaño = filas+40;
            printer.setOutSize(tamaño, 80);

            //Imprimir = 1ra linea de la columa de 1 a 32
            printer.printTextWrap(0, 1, 3, 40, "========================================");
            printer.printTextWrap(1, 1, 32, 80, "FARMAMILI"); //NOMBRE EMPRESA
            printer.printTextWrap(3, 1, 28, 80, "de Alfonso Avalos Gimenez."); //PROPIETARIO
            printer.printTextWrap(4, 1, 16, 80, "Jose M. A. Godoy c/ Las Residentas - (0971) 799 004"); //DIRECCION
            printer.printTextWrap(5, 1, 3, 80, "_____________________________________________________________________________");
            printer.printTextWrap(6, 1, 3, 80, "FECHA: " + Fecha.fechaCorrecta() + " " + Fecha.darHora()); //FECHA Y HORA
            printer.printTextWrap(6, 1, 65, 80, lbCond.getText()); //CONDICION DE VENTA
            printer.printTextWrap(7, 1, 3, 80, "TICKET N°: " + txtCodFactura.getText()); //FACTURA O PEDIDO
            printer.printTextWrap(7, 1, 65, 80, "CAJA N°: " + txtCaja.getText()); //CAJA
            printer.printTextWrap(8, 1, 3, 80, "VENDEDOR: " + lbEmpleado.getText()); //VENDEDOR
            printer.printTextWrap(9, 1, 3, 80, "CLIENTE: " + txtRuc.getText() + " / " + txtRazonS.getText());//RUC Y RS
            printer.printTextWrap(10, 1, 3, 80, "_____________________________________________________________________________");
            printer.printTextWrap(11, 1, 3, 80, "DESCRIPCION                            CANT  P.PUBL  %DESC  P.UNIT   TOTAL");
            printer.printTextWrap(12, 1, 0, 80, " ");

            for (int i = 0; i < filas; i++) {
                int p = 13 + i; //Fila
                DecimalFormat formateador = new DecimalFormat("#,###");
                String DES = printer.alinharADireita(10, tbDetalle.getValueAt(i, 2).toString());
                String Cant = tbDetalle.getValueAt(i, 3).toString();
                String Ppublic = tbDetalle.getValueAt(i, 17).toString();
                String Desc = tbDetalle.getValueAt(i, 16).toString();
                String Punit = tbDetalle.getValueAt(i, 5).toString();
                String Mont = tbDetalle.getValueAt(i, 13).toString();
                printer.printTextWrap(p, 1, 3, 40, DES);
                printer.printTextWrap(p, 1, 43, 46, formateador.format(Integer.parseInt(Cant.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 48, 57, formateador.format(Integer.parseInt(Ppublic.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 58, 62, formateador.format(Integer.parseInt(Desc.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 64, 71, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 72, 80, formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));

            }

            printer.printTextWrap(filas + 17, 1, 3, 80, "-----------------------------------------------------------------------------");

            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = printer.alinharADireita(10, formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", ""))));
            printer.printTextWrap(filas + 18, 1, 3, 20, "TOTAL A PAGAR:");
            printer.printTextWrap(filas + 18, 1, 67, 80, (tot));
            printer.printTextWrap(filas + 19, 1, 3, 80, "-----------------------------------------------------------------------------");
            String efe = printer.alinharADireita(10, txtAbono.getText());
            printer.printTextWrap(filas + 20, 1, 3, 80, "EFECTIVO : ");
            printer.printTextWrap(filas + 20, 1, 20, 80, efe);

            String cam = printer.alinharADireita(10, txtVuelto.getText());
            printer.printTextWrap(filas + 21, 1, 3, 80, "VUELTO   : ");
            printer.printTextWrap(filas + 21, 1, 20, 80, cam);

            printer.printTextWrap(filas + 22, 1, 3, 80, "=============================================================================");
            printer.printTextWrap(filas + 23, 1, 27, 80, "!GRACIAS POR SU PREFERENCIA!");
            printer.printTextWrap(filas + 26, 1, 12, 80, "E-FARM - v1.2.1 - Software Integral de Gestión Farmacéutica");
            printer.printTextWrap(filas + 27, 1, 3, 80, "=============================================================================");

            ///CREAR ARCHIVO EN CARPETA DEL PROYECTO PARA PEDIDOS
            //printer.toFile("C:\\tmp\\impresion.txt");
            printer.toFile("impresion.txt");
            FileInputStream inputStream = null;

            try {
                inputStream = new FileInputStream("impresion.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
            if (inputStream == null) {
                return;
            }

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, attributeSet);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("No existen impresoras instaladas");
            }

            inputStream.close();
            //imprimirFin(subTotal, total, dineroR, devolucion); //ESTE METODO NO SE UTILIZARA

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir " + e);
        }
    }
     */
    // ticketera mtu matricial
    /*public static void imprimirTicket() {

        try {
            try {
                String sql = "select em_razonsocial, em_direccion, em_celular from empresa where em_indicador='S'";
                st = (MariaDbStatement) con.createStatement();
                rss = st.executeQuery(sql);
                try{
                    rss.first();
                    emp=rss.getString(1);
                    dir=rss.getString(2);
                    cel=rss.getString(3);
                } catch (SQLException e) {
                }
            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
            }
            PrinterMatrix printer = new PrinterMatrix();
            Extenso e = new Extenso();

            e.setNumber(1);
            //Definir el tamanho del papel para la impresion de dinamico y 32 columnas
            int filas = tbDetalle.getRowCount();
            int tamaño = filas+15;
            printer.setOutSize(tamaño, 80);

            //Imprimir = 1ra linea de la columa de 1 a 32
            //printer.printTextWrap(0, 1, 15, 24, "FARMAMILI"); //NOMBRE EMPRESA
            switch (emp.length()) {
                case 1 -> printer.printTextWrap(0, 1, 20, 40, emp); //NOMBRE EMPRESA
                case 2 -> printer.printTextWrap(0, 1, 19, 40, emp); //NOMBRE EMPRESA
                case 3 -> printer.printTextWrap(0, 1, 19, 40, emp); //NOMBRE EMPRESA
                case 4 -> printer.printTextWrap(0, 1, 18, 40, emp); //NOMBRE EMPRESA
                case 5 -> printer.printTextWrap(0, 1, 18, 40, emp); //NOMBRE EMPRESA
                case 6 -> printer.printTextWrap(0, 1, 17, 40, emp); //NOMBRE EMPRESA
                case 7 -> printer.printTextWrap(0, 1, 17, 40, emp); //NOMBRE EMPRESA
                case 8 -> printer.printTextWrap(0, 1, 16, 40, emp); //NOMBRE EMPRESA
                case 9 -> printer.printTextWrap(0, 1, 16, 40, emp); //NOMBRE EMPRESA
                case 10 -> printer.printTextWrap(0, 1, 15, 40, emp); //NOMBRE EMPRESA
                case 11 -> printer.printTextWrap(0, 1, 15, 40, emp); //NOMBRE EMPRESA
                case 12 -> printer.printTextWrap(0, 1, 14, 40, emp); //NOMBRE EMPRESA
                case 13 -> printer.printTextWrap(0, 1, 14, 40, emp); //NOMBRE EMPRESA
                case 14 -> printer.printTextWrap(0, 1, 13, 40, emp); //NOMBRE EMPRESA
                case 15 -> printer.printTextWrap(0, 1, 13, 40, emp); //NOMBRE EMPRESA
                case 16 -> printer.printTextWrap(0, 1, 12, 40, emp); //NOMBRE EMPRESA
                case 17 -> printer.printTextWrap(0, 1, 12, 40, emp); //NOMBRE EMPRESA
                case 18 -> printer.printTextWrap(0, 1, 11, 40, emp); //NOMBRE EMPRESA
                case 19 -> printer.printTextWrap(0, 1, 11, 40, emp); //NOMBRE EMPRESA
                case 20 -> printer.printTextWrap(0, 1, 10, 40, emp); //NOMBRE EMPRESA
                case 21 -> printer.printTextWrap(0, 1, 10, 40, emp); //NOMBRE EMPRESA
                case 22 -> printer.printTextWrap(0, 1, 9, 40, emp); //NOMBRE EMPRESA
                case 23 -> printer.printTextWrap(0, 1, 9, 40, emp); //NOMBRE EMPRESA
                case 24 -> printer.printTextWrap(0, 1, 8, 40, emp); //NOMBRE EMPRESA
                case 25 -> printer.printTextWrap(0, 1, 8, 40, emp); //NOMBRE EMPRESA
                case 26 -> printer.printTextWrap(0, 1, 7, 40, emp); //NOMBRE EMPRESA
                case 27 -> printer.printTextWrap(0, 1, 7, 40, emp); //NOMBRE EMPRESA
                case 28 -> printer.printTextWrap(0, 1, 6, 40, emp); //NOMBRE EMPRESA
                case 29 -> printer.printTextWrap(0, 1, 6, 40, emp); //NOMBRE EMPRESA
                case 30 -> printer.printTextWrap(0, 1, 5, 40, emp); //NOMBRE EMPRESA
                case 31 -> printer.printTextWrap(0, 1, 5, 40, emp); //NOMBRE EMPRESA
                case 32 -> printer.printTextWrap(0, 1, 4, 40, emp); //NOMBRE EMPRESA
                case 33 -> printer.printTextWrap(0, 1, 4, 40, emp); //NOMBRE EMPRESA
                case 34 -> printer.printTextWrap(0, 1, 3, 40, emp); //NOMBRE EMPRESA
                case 35 -> printer.printTextWrap(0, 1, 3, 40, emp); //NOMBRE EMPRESA
                case 36 -> printer.printTextWrap(0, 1, 2, 40, emp); //NOMBRE EMPRESA
                case 37 -> printer.printTextWrap(0, 1, 2, 40, emp); //NOMBRE EMPRESA
                case 38 -> printer.printTextWrap(0, 1, 1, 40, emp); //NOMBRE EMPRESA
                case 39 -> printer.printTextWrap(0, 1, 1, 40, emp); //NOMBRE EMPRESA
            }
            //printer.printTextWrap(0, 1, 15, 40, emp); //NOMBRE EMPRESA
            printer.printTextWrap(0, 1, 41, 80, "_______________________________________"); //PROPIETARIO
            //printer.printTextWrap(1, 1, 3, 37, "Jose M. A. Godoy c/ Las Residentas"); //DIRECCION
            switch (dir.length()) {
                case 1 -> printer.printTextWrap(1, 1, 20, 40, dir); //NOMBRE EMPRESA
                case 2 -> printer.printTextWrap(1, 1, 19, 40, dir); //NOMBRE EMPRESA
                case 3 -> printer.printTextWrap(1, 1, 19, 40, dir); //NOMBRE EMPRESA
                case 4 -> printer.printTextWrap(1, 1, 18, 40, dir); //NOMBRE EMPRESA
                case 5 -> printer.printTextWrap(1, 1, 18, 40, dir); //NOMBRE EMPRESA
                case 6 -> printer.printTextWrap(1, 1, 17, 40, dir); //NOMBRE EMPRESA
                case 7 -> printer.printTextWrap(1, 1, 17, 40, dir); //NOMBRE EMPRESA
                case 8 -> printer.printTextWrap(1, 1, 16, 40, dir); //NOMBRE EMPRESA
                case 9 -> printer.printTextWrap(1, 1, 16, 40, dir); //NOMBRE EMPRESA
                case 10 -> printer.printTextWrap(1, 1, 15, 40, dir); //NOMBRE EMPRESA
                case 11 -> printer.printTextWrap(1, 1, 15, 40, dir); //NOMBRE EMPRESA
                case 12 -> printer.printTextWrap(1, 1, 14, 40, dir); //NOMBRE EMPRESA
                case 13 -> printer.printTextWrap(1, 1, 14, 40, dir); //NOMBRE EMPRESA
                case 14 -> printer.printTextWrap(1, 1, 13, 40, dir); //NOMBRE EMPRESA
                case 15 -> printer.printTextWrap(1, 1, 13, 40, dir); //NOMBRE EMPRESA
                case 16 -> printer.printTextWrap(1, 1, 12, 40, dir); //NOMBRE EMPRESA
                case 17 -> printer.printTextWrap(1, 1, 12, 40, dir); //NOMBRE EMPRESA
                case 18 -> printer.printTextWrap(1, 1, 11, 40, dir); //NOMBRE EMPRESA
                case 19 -> printer.printTextWrap(1, 1, 11, 40, dir); //NOMBRE EMPRESA
                case 20 -> printer.printTextWrap(1, 1, 10, 40, dir); //NOMBRE EMPRESA
                case 21 -> printer.printTextWrap(1, 1, 10, 40, dir); //NOMBRE EMPRESA
                case 22 -> printer.printTextWrap(1, 1, 9, 40, dir); //NOMBRE EMPRESA
                case 23 -> printer.printTextWrap(1, 1, 9, 40, dir); //NOMBRE EMPRESA
                case 24 -> printer.printTextWrap(1, 1, 8, 40, dir); //NOMBRE EMPRESA
                case 25 -> printer.printTextWrap(1, 1, 8, 40, dir); //NOMBRE EMPRESA
                case 26 -> printer.printTextWrap(1, 1, 7, 40, dir); //NOMBRE EMPRESA
                case 27 -> printer.printTextWrap(1, 1, 7, 40, dir); //NOMBRE EMPRESA
                case 28 -> printer.printTextWrap(1, 1, 6, 40, dir); //NOMBRE EMPRESA
                case 29 -> printer.printTextWrap(1, 1, 6, 40, dir); //NOMBRE EMPRESA
                case 30 -> printer.printTextWrap(1, 1, 5, 40, dir); //NOMBRE EMPRESA
                case 31 -> printer.printTextWrap(1, 1, 5, 40, dir); //NOMBRE EMPRESA
                case 32 -> printer.printTextWrap(1, 1, 4, 40, dir); //NOMBRE EMPRESA
                case 33 -> printer.printTextWrap(1, 1, 4, 40, dir); //NOMBRE EMPRESA
                case 34 -> printer.printTextWrap(1, 1, 3, 40, dir); //NOMBRE EMPRESA
                case 35 -> printer.printTextWrap(1, 1, 3, 40, dir); //NOMBRE EMPRESA
                case 36 -> printer.printTextWrap(1, 1, 2, 40, dir); //NOMBRE EMPRESA
                case 37 -> printer.printTextWrap(1, 1, 2, 40, dir); //NOMBRE EMPRESA
                case 38 -> printer.printTextWrap(1, 1, 1, 40, dir); //NOMBRE EMPRESA
                case 39 -> printer.printTextWrap(1, 1, 1, 40, dir); //NOMBRE EMPRESA
            }
            //printer.printTextWrap(1, 1, 3, 37, dir); //DIRECCION
            //printer.printTextWrap(1, 1, 43, 73, "Cel:(0981) 700 414"); //DIRECCION
            printer.printTextWrap(1, 1, 43, 73, "Cel:"+cel); //DIRECCION
            printer.printTextWrap(2, 1, 1, 40, "________________________________________");
            printer.printTextWrap(2, 1, 41, 67, "FECHA: " + txtFecha.getText() + " " + Fecha.darHora()); //FECHA Y HORA
            printer.printTextWrap(2, 1, 71, 78, lbCond.getText()); //CONDICION DE VENTA
            printer.printTextWrap(3, 1, 1, 24, "TICKET N: " +txtTC.getText()+""+txtCodFactura.getText()); //FACTURA O PEDIDO
            printer.printTextWrap(3, 1, 26, 37, "CAJA N: " + txtCaja.getText()); //CAJA
            printer.printTextWrap(3, 1, 41, 80, "VENDEDOR: " + lbEmpleado.getText()); //VENDEDOR
            printer.printTextWrap(4, 1, 1, 40, "CLIENTE: " + txtRazonS.getText());//RUC Y RS
            printer.printTextWrap(4, 1, 41, 80, "R.U.C.: " + txtRuc.getText());//RUC Y RS
            printer.printTextWrap(5, 1, 1, 40, "________________________________________");
            printer.printTextWrap(5, 1, 41, 79, "CANT   P.PUBL  %DESC  P.UNIT    TOTAL");
            for (int i = 0; i < filas; i++) {
                int p = 6 + i; //Fila
                DecimalFormat formateador = new DecimalFormat("#,###");
                String DES = printer.alinharADireita(10, tbDetalle.getValueAt(i, 2).toString());
                //String DES = tbDetalle.getValueAt(i, 2).toString();
                String Cant = tbDetalle.getValueAt(i, 3).toString();
                int pp = (int) (Integer.parseInt(tbDetalle.getValueAt(i, 17).toString())/Float.parseFloat(tbDetalle.getValueAt(i, 3).toString()));
                String Ppublic = String.valueOf(pp);
                String Desc = tbDetalle.getValueAt(i, 16).toString();
                String Punit = tbDetalle.getValueAt(i, 5).toString();
                String Mont = tbDetalle.getValueAt(i, 13).toString();
                printer.printTextWrap(p, 1, 1, 40, DES);
                printer.printTextWrap(p, 1, 41, 45, formateador.format(Integer.parseInt(Cant.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 48, 54, formateador.format(Integer.parseInt(Ppublic.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 56, 61, formateador.format(Integer.parseInt(Desc.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 63, 69, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))));
                printer.printTextWrap(p, 1, 72, 79, formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            printer.printTextWrap(filas+6, 1, 1, 40, "---------------------------------------");

            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = printer.alinharADireita(10, formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", ""))));
            printer.printTextWrap(filas + 6, 1, 41, 55, "TOTAL A PAGAR:");
            printer.printTextWrap(filas + 6, 1, 66, 79, (tot));
            printer.printTextWrap(filas + 7, 1, 1, 40, "---------------------------------------");
            String efe = printer.alinharADireita(10, txtAbono.getText());
            printer.printTextWrap(filas + 7, 1, 41, 52, "EFECTIVO : ");
            printer.printTextWrap(filas + 7, 1, 55, 80, efe);

            String cam = printer.alinharADireita(10, txtVuelto.getText());
            printer.printTextWrap(filas + 8, 1, 1, 12, "VUELTO   : ");
            printer.printTextWrap(filas + 8, 1, 15, 40, cam);

            printer.printTextWrap(filas + 8, 1, 41, 80, "=======================================");
            printer.printTextWrap(filas + 9, 1, 6, 34, "GRACIAS POR SU PREFERENCIA!!");
            printer.printTextWrap(filas + 9, 1, 52, 66, "SISTEMA E-FARM");
            printer.printTextWrap(filas + 10, 1, 1, 40, "=======================================");

            ///CREAR ARCHIVO EN CARPETA DEL PROYECTO PARA PEDIDOS
            //printer.toFile("C:\\tmp\\impresion.txt");
            printer.toFile("impresion.txt");
            FileInputStream inputStream = null;

            try {
                inputStream = new FileInputStream("impresion.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar ticket: "+ex.getMessage());
            }
            if (inputStream == null) {
                return;
            }

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, attributeSet);
                    byte[] cutP = new byte[] { 0x1d, 'V', 1 };
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("No existen impresoras instaladas");
            }

            inputStream.close();
            //imprimirFin(subTotal, total, dineroR, devolucion); //ESTE METODO NO SE UTILIZARA

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir " + e);
        }
    }*/
    private static void obtenerTIMBRA() {
        String ip = traerIP.getIP();
        try {
            prepararBD();
            ResultSet rsi = stMovil.executeQuery("SELECT * FROM v_puntoemision3 WHERE ip='" + ip.trim() + "' AND tipo='L' AND estado='Activo'");
            rsi.last();
            do {
                Timbrado = rsi.getString("timbra");
                Desde = rsi.getString("fechadesde");
                Hasta = rsi.getString("fechahasta");
            } while (rsi.next());
            SimpleDateFormat fe = new SimpleDateFormat("dd/MM/yyyy");
            rsi.close();
            try {
                Date FechaA = fe.parse(Fecha.fechaFormulario());
                Date FechaT = fe.parse(Hasta);
                if (FechaA.compareTo(FechaT) > 0) {
                    Mensajes.Alerta("FACTURA LEGAL NO HABILITADO:\nEl Timbrado actual ha expirado.\nPara retomar las facturaciones legales sera necesario configurar un nuevo Timbrado.");
                    btnFacturaLegal.setEnabled(false);
                    itemFactura_Legal.setEnabled(false);
                }
            } catch (ParseException es) {
            }
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
        } catch (SQLException ex) {
            Mensajes.error("FACTURA LEGAL NO HABILITADO:\nNo se encuentra un timbrado ni punto de emisión para registras facturas legales.");
            btnFacturaLegal.setEnabled(false);
            itemFactura_Legal.setEnabled(false);
            btnNuevo.setEnabled(false);
            itemNuevo.setEnabled(false);
        }
    }

    private static void obtenerNFactura() {

        int idEmision1;
        String Establecimiento1;
        String Emision1;
        int facturaactual1;
        int facturafin1;
        try {
            String ip = traerIP.getIP();
            prepararBD();
            ResultSet ress = stMovil.executeQuery("SELECT * FROM v_puntoemision4 WHERE ip='" + ip + "' AND tipo='L' AND tipo2='F' AND estado='Activo'");
            ress.first();
            do {
                idEmision1 = ress.getInt("idemision");
                Establecimiento1 = ress.getString("establecimiento");
                Emision1 = ress.getString("puntoemision");
                facturaactual1 = ress.getInt("facturaactual");
                facturafin1 = ress.getInt("facturafin");

                if (facturaactual1 < facturafin1) {
                    String cod = GestionarFactura.getCodigo();
                    txtCodF.setText(cod);
                    txtidEmision.setText(String.valueOf(idEmision1));
                    txtEstablecimiento1.setText(Establecimiento1);
                    txtEmision1.setText(Emision1);
                    int facf = facturaactual1 + 1;
                    switch (String.valueOf(facf).length()) {
                        case 1 ->
                            txtFacturaN1.setText("000000" + String.valueOf(facf));
                        case 2 ->
                            txtFacturaN1.setText("00000" + String.valueOf(facf));
                        case 3 ->
                            txtFacturaN1.setText("0000" + String.valueOf(facf));
                        case 4 ->
                            txtFacturaN1.setText("000" + String.valueOf(facf));
                        case 5 ->
                            txtFacturaN1.setText("00" + String.valueOf(facf));
                        case 6 ->
                            txtFacturaN1.setText("0" + String.valueOf(facf));
                        case 7 ->
                            txtFacturaN1.setText(String.valueOf(facf));
                        default -> {
                        }
                    }

                    OpcionesEmision.dispose();
                    dlgFinFacturaL.setSize(360, 330);
                    dlgFinFacturaL.setLocationRelativeTo(null);
                    dlgFinFacturaL.setModal(true);
                    dlgFinFacturaL.setTitle("CONFIRMAR VENTA");
                    txtCodVendedorF.setText("");
                    dlgFinFacturaL.setVisible(true);
                    txtCodVendedorF.requestFocus();
                } else {
                    Mensajes.Alerta("OBSERVACIÓN:\nNo es posible emitir una nueva factura legal.\nSe ha alcanzado la cantidad máxima de facturación para el punto de expedición o emisión actual.\n");
                }
            } while (ress.next());
            ress.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
        } catch (SQLException ex) {
            Mensajes.Alerta("FACTURA LEGAL NO HABILITADO:\nNo se encuentra un timbrado ni punto de emisión para registras facturas legales.");
            btnFacturaLegal.setEnabled(false);
            itemFactura_Legal.setEnabled(false);
        }
    }

    public static void comprobarNFactura() {
        //
        try {
            String Establecimiento1;
            String Emision1;
            int facturaactual1;
            int facturafin1;
            prepararBD();
            ResultSet resss = stMovil.executeQuery("SELECT * FROM v_puntoemision4 WHERE idemision=" + txtidEmision.getText().trim());
            resss.first();
            do {
                Establecimiento1 = resss.getString("establecimiento");
                Emision1 = resss.getString("puntoemision");
                facturaactual1 = resss.getInt("facturaactual");
                facturafin1 = resss.getInt("facturafin");

                if (facturaactual1 < facturafin1) {

                    txtEstablecimiento1.setText(Establecimiento1);
                    txtEmision1.setText(Emision1);
                    int fa = facturaactual1 + 1;
                    switch (String.valueOf(fa).length()) {
                        case 1 ->
                            txtFacturaN1.setText("000000" + String.valueOf(fa));
                        case 2 ->
                            txtFacturaN1.setText("00000" + String.valueOf(fa));
                        case 3 ->
                            txtFacturaN1.setText("0000" + String.valueOf(fa));
                        case 4 ->
                            txtFacturaN1.setText("000" + String.valueOf(fa));
                        case 5 ->
                            txtFacturaN1.setText("00" + String.valueOf(fa));
                        case 6 ->
                            txtFacturaN1.setText("0" + String.valueOf(fa));
                        case 7 ->
                            txtFacturaN1.setText(String.valueOf(fa));
                        default -> {
                        }
                    }
                }
            } while (resss.next());

            resss.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();

            String cod = GestionarFactura.getCodigo();
            txtCodF.setText(cod);

            String NFactura = txtEstablecimiento1.getText().trim() + "-" + txtEmision1.getText().trim() + "-" + txtFacturaN1.getText().trim();
            String cond = lbCond.getText();
            String est;
            if (cond.equals("CONTADO")) {
                est = "ABONADO";
            } else {
                est = "PENDIENTE";
            }
            UsuarioL = Login.getUsuarioLogueado();
            int resp = JOptionPane.showConfirmDialog(dlgFinFacturaL, "¿Seguro que deseas registrar esta Venta al sistema?", "CONFIRMACIÓN DE VENTA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    prepararBD();
                    con.setAutoCommit(false);
                    conMovil.setAutoCommit(false);
                    String sql = "insert into factura values(" + txtCodF.getText().trim() + "," + txtCodVendedorF.getText().trim() + "," + txtCodCliente.getText().trim() + "," + txtCaja.getText().trim() + "," + txtidEmision.getText().trim() + ", 'F','" + NFactura + "','" + lbCond.getText() + "','"
                            + txtFecha.getText() + "','" + txtHora.getText() + "'," + txtTotalCosto.getText() + "," + txtTotalL.getText() + "," + txtExentaL.getText() + "," + txt5L.getText() + "," + txt10L.getText() + ",'S','" + UsuarioL + "','" + est + "')";
                    String sql4 = "UPDATE puntoemision set facturaactual=" + Integer.parseInt(txtFacturaN1.getText().trim()) + " WHERE idemision=" + txtidEmision.getText().trim();
                    String sql5 = "UPDATE ref set nventa=" + Integer.parseInt(txtFacturaN1.getText().trim()) + " WHERE idemision=" + txtidEmision.getText().trim();
                    stTransaccion.executeUpdate(sql);
                    stTransaccionMovil.executeUpdate(sql4);
                    stTransaccionMovil.executeUpdate(sql5);
                    int fila = tbDetalle.getRowCount();
                    for (int j = 0; j < fila; j++) {
                        String filas[] = {
                            tbDetalle.getValueAt(j, 0).toString(), //idproducto 0
                            tbDetalle.getValueAt(j, 1).toString(),//dependencia  1
                            tbDetalle.getValueAt(j, 2).toString(), //iddependencia  2
                            tbDetalle.getValueAt(j, 11).toString(), //iva  3
                            tbDetalle.getValueAt(j, 5).toString(), //cantidad  4
                            tbDetalle.getValueAt(j, 6).toString(), //precio  5
                            tbDetalle.getValueAt(j, 10).toString(),//total  6
                            tbDetalle.getValueAt(j, 13).toString() // promo 7
                        };
                        sql = "insert into detalle_factura values(" + txtCodF.getText() + ", " + filas[0] + ", '" + filas[1] + "', " + filas[2] + ", " + filas[3] + ", " + filas[4] + ", " + filas[5] + ", " + filas[6] + ",'" + filas[7] + "')";
                        String sql2 = null;
                        if (filas[1].equals("S")) {
                            sql2 = "UPDATE productos SET stock=(stock-" + filas[4] + ") WHERE  idproducto=" + filas[2];
                        } else if (filas[1].equals("N")) {
                            sql2 = "UPDATE productos SET stock=(stock-" + filas[4] + ") WHERE  idproducto=" + filas[0];
                        }
                        stTransaccion.executeUpdate(sql);
                        stTransaccionMovil.executeUpdate(sql2);
                    }

                    con.commit();
                    conMovil.commit();

                    imprimirTicketOriginal();
                    imprimirTicketDuplicado();
                    Mensajes.informacion("VENTA REGISTRADA EXITOSAMENTE!");
                    dlgFinFacturaL.dispose();
                    CabecerasTablas.limpiarTablasVentas(tbDetalle);
                    CabecerasTablas.ventas(tbDetalle);
                    controlFactura.canCelar();
                    Cancelar();
                    st.close();
                    stMovil.close();
                    stTransaccion.close();
                    stTransaccionMovil.close();
                    con.close();
                    conMovil.close();
                    txtAbonoF.setText("0");
                    txtVueltoF.setText("0");
                    cant();
                } catch (SQLException e) {
                    con.rollback();
                    conMovil.rollback();
                    Mensajes.error("TRANSACCIÓN FALLIDA: La venta no fue registrada en el sistema.\nError:ADD_V: " + e.getMessage().toUpperCase());
                    controlFactura.canCelar();
                    st.close();
                    stMovil.close();
                    stTransaccion.close();
                    stTransaccionMovil.close();
                    con.close();
                    conMovil.close();
                    dlgFinFacturaL.dispose();
                }
            }
        } catch (SQLException ex) {
            Mensajes.Alerta("ERROR FATAL: NO SE ENCUENTRA O NO EXISTE PUNTO DE EMISIÓN PARA ESTA TERMINAL");
            btnNuevo.setEnabled(false);
            itemNuevo.setEnabled(false);
        }
    }

    public static void obtenerNTicket() {
        String cod = GestionarFactura.getCodigo();
        txtCodT.setText(cod);

        int idEmision1;
        String Establecimiento1;
        String Emision1;
        int facturaactual1;
        try {
            prepararBD();
            String ip = traerIP.getIP();
            ResultSet rrs = stMovil.executeQuery("SELECT * FROM v_puntoemision4 WHERE ip='" + ip + "' AND tipo='L' AND tipo2='T' AND estado='Activo'");
            if (rrs != null) {
                rrs.first();
                do {
                    idEmision1 = rrs.getInt("idemision");
                    Establecimiento1 = rrs.getString("establecimiento");
                    Emision1 = rrs.getString("puntoemision");
                    facturaactual1 = rrs.getInt("facturaactual");

                    txtidEmision.setText(String.valueOf(idEmision1));
                    txtEPE.setText(Establecimiento1 + "-" + Emision1);
                    int numero = (facturaactual1 + 1);
                    txtTicketN.setText(String.valueOf(numero));
                } while (rrs.next());
                rrs.close();
            }
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
            OpcionesEmision.dispose();
            dlgFinTicket.setSize(370, 340);
            dlgFinTicket.setLocationRelativeTo(null);
            dlgFinTicket.setModal(true);
            dlgFinTicket.setTitle("CONFIRMAR VENTA");
            txtCodVendedorT.setText("");
            dlgFinTicket.setVisible(true);
            txtCodVendedorT.requestFocus();

        } catch (SQLException ex) {
            Mensajes.Alerta("OBSERVACIÓN:\nEn estos momentos es imposible emitir Ticket de venta.\nEl Sistema no logra identificar un PUNTO DE EMISIÓN habilitado para esta terminal de venta.Para mayor información comuniquese con el proveedor del Sistema.\n\nOrigen: " + ex.getMessage());
        }
    }

    public static void ComprobarNTicket() {
        String cod = GestionarFactura.getCodigo();
        txtCodT.setText(cod);

        try {
            prepararBD();
            String Establecimiento1;
            String Emision1;
            int facturaactual1;
            ResultSet res = stMovil.executeQuery("SELECT * FROM v_puntoemision4 WHERE idemision=" + txtidEmision.getText().trim());
            res.last();
            do {
                Establecimiento1 = res.getString("establecimiento");
                Emision1 = res.getString("puntoemision");
                facturaactual1 = res.getInt("facturaactual");
                int numero = facturaactual1 + 1;
                txtEPE.setText(Establecimiento1 + "-" + Emision1);
                txtTicketN.setText(String.valueOf(numero));
            } while (res.next());
            res.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
            String NFactura = txtEPE.getText().trim() + "-" + txtTicketN.getText().trim();
            String cond = lbCond.getText();
            String est;
            if (cond.equals("CONTADO")) {
                est = "ABONADO";
            } else {
                est = "PENDIENTE";
            }
            UsuarioL = Login.getUsuarioLogueado();
            int resp = JOptionPane.showConfirmDialog(dlgFinTicket, "¿Seguro que deseas registrar esta Venta al sistema?", "CONFIRMACIÓN DE VENTA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    prepararBD();
                    con.setAutoCommit(false);
                    conMovil.setAutoCommit(false);
                    String sql = "insert into factura values(" + txtCodT.getText().trim() + "," + txtCodVendedorT.getText().trim() + "," + txtCodCliente.getText().trim() + "," + txtCaja.getText().trim() + "," + txtidEmision.getText().trim() + ", 'T','" + NFactura + "','" + lbCond.getText() + "','"
                            + txtFecha.getText() + "','" + txtHora.getText() + "'," + txtTotalCosto.getText() + "," + txtTotalL.getText() + "," + txtExentaL.getText() + "," + txt5L.getText() + "," + txt10L.getText() + ",'S','" + UsuarioL + "','" + est + "')";
                    String sql4 = "UPDATE puntoemision set facturaactual=" + Integer.parseInt(txtTicketN.getText().trim()) + " WHERE idemision=" + txtidEmision.getText().trim();
                    String sql5 = "UPDATE ref set nventa=" + Integer.parseInt(txtTicketN.getText().trim()) + " WHERE idemision=" + txtidEmision.getText().trim();
                    stTransaccion.executeUpdate(sql);
                    stTransaccionMovil.executeUpdate(sql4);
                    stTransaccionMovil.executeUpdate(sql5);
                    int fila = tbDetalle.getRowCount();
                    for (int j = 0; j < fila; j++) {
                        String filas[] = {
                            tbDetalle.getValueAt(j, 0).toString(), //idproducto 0
                            tbDetalle.getValueAt(j, 1).toString(),//dependencia  1
                            tbDetalle.getValueAt(j, 2).toString(), //iddependencia  2
                            tbDetalle.getValueAt(j, 11).toString(), //iva  3
                            tbDetalle.getValueAt(j, 5).toString(), //cantidad  4
                            tbDetalle.getValueAt(j, 6).toString(), //precio  5
                            tbDetalle.getValueAt(j, 10).toString(),//total  6
                            tbDetalle.getValueAt(j, 13).toString()//promo  7

                        };
                        sql = "insert into detalle_factura values(" + txtCodT.getText() + ", " + filas[0] + ", '" + filas[1] + "', " + filas[2] + ", " + filas[3] + ", " + filas[4] + ", " + filas[5] + ", " + filas[6] + ",'" + filas[7] + "')";
                        String sql2 = null;
                        if (filas[1].equals("S")) {
                            sql2 = "UPDATE productos SET stock=(stock-" + filas[4] + ") WHERE  idproducto=" + filas[2];
                        } else if (filas[1].equals("N")) {
                            sql2 = "UPDATE productos SET stock=(stock-" + filas[4] + ") WHERE  idproducto=" + filas[0];
                        }
                        stTransaccion.executeUpdate(sql);
                        stTransaccionMovil.executeUpdate(sql2);
                    }
                    con.commit();
                    conMovil.commit();
                    Mensajes.informacion("VENTA REGISTRADA EXITOSAMENTE!");
                    dlgFinTicket.dispose();
                    imprimirTicket();
                    CabecerasTablas.limpiarTablasVentas(tbDetalle);
                    CabecerasTablas.ventas(tbDetalle);
                    controlFactura.canCelar();
                    Cancelar();
                    st.close();
                    stMovil.close();
                    stTransaccion.close();
                    stTransaccionMovil.close();
                    con.close();
                    conMovil.close();
                    txtAbonoT.setText("0");
                    txtVueltoT.setText("0");
                    cant();
                } catch (SQLException e) {
                    con.rollback();
                    conMovil.rollback();
                    Mensajes.error("TRANSACCIÓN FALLIDA: La venta no fue registrada en el sistema.\nError:ADD_V: " + e.getMessage().toUpperCase());
                    controlFactura.canCelar();
                    st.close();
                    stMovil.close();
                    stTransaccion.close();
                    stTransaccionMovil.close();
                    con.close();
                    conMovil.close();
                    dlgFinTicket.dispose();
                }
            }
        } catch (SQLException ex) {
            Mensajes.informacion("OBSERVACIÓN:\nEn estos momentos es imposible emitir Ticket de venta.\nEl Sistema no logra identificar un PUNTO DE EMISIÓN habilitado para esta terminal de venta.\nPara mayor información comuniquese con el proveedor del Sistema.");
        }
    }

    /*public static void SendData() {
        String empresa=null;
        String ruc=null;
        String celular=null;
        String direccion=null;
        try {
            int filas = tbDetalle.getRowCount();
            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", "")));
            try {
                rs = st.executeQuery("SELECT * FROM empresa WHERE em_indicador='S'");
                rs.last();
                empresa = rs.getString("em_razonsocial");
                ruc = rs.getString("em_ruc");
                celular = rs.getString("em_telefono") + "-" + rs.getString("em_celular");
                direccion = rs.getString("em_direccion");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
            }
            DecimalFormat nformat = new DecimalFormat("##,###,###");
            String msg = empresa + "\n";
            getByteString(msg, 0, 0, 0, 1);
            String msg1 = "VENTAS DE LACTEOS LACTOLANDA\n";
            msg1 += "RUC: " + ruc + "\n";
            msg1 += "CEL: " + celular + "\n";
            msg1 += direccion + "\n";
            msg1 += "CNEL. OVIEDO - PARAGUAY\n";
            msg1 += "I.V.A. INCLUIDO\n";
            msg1 += "------------------------------------------------\n";//48
            mmOutputStream.write(ESC_ALIGN_CENTER);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg1, 0, 0, 0, 0));
            String msg2 = "TIMBRADO: " + Timbrado + "\n";
            msg2 += "VALIDO DESDE: " + Desde + " HASTA: " + Hasta + "\n";
            msg2 += "FACTURA " + lbCond.getText().trim() + " NRO: " + txtEstablecimiento.getText().trim()
                + "-" + txtEmision.getText().trim() + "-" + txtFacturaN.getText().trim() + "\n";
            msg2 += "FECHA/HORA: " + txtfechaF.getText().trim() + " " + txtHora.getText().trim() + "\n";
            msg2 += "VENDEDOR: " + lbEmpleado.getText().trim() + "\n";
            msg2 += "\n";
            msg2 += "CLIENTE: " + txtRazonS.getText().trim() + "\n";
            msg2 += "RUC/CI: " + txtRuc.getText().trim() + "\n";
            msg2 += "------------------------------------------------\n";
            msg2 += String.format("%1$1s %2$10s %3$1s %4$12s %5$17s", "IVA", "CANT", "", "PRECIO", "   SUB-TOTAL");
            msg2 += "\n";
            msg2 += "------------------------------------------------\n";
            for (int i = 0; i < filas; i++) {

                String codB = tbDetalle.getValueAt(i, 1).toString().trim();
                String Descripcion = tbDetalle.getValueAt(i, 2).toString().trim();
                String cant = tbDetalle.getValueAt(i, 3).toString().trim();
                String Punit = tbDetalle.getValueAt(i, 4).toString().trim();
                String Mont = tbDetalle.getValueAt(i, 8).toString().trim();
                int idiva = Integer.parseInt(tbDetalle.getValueAt(i, 9).toString().trim());
                String iva = null;
                switch (idiva) {
                    case 1 ->
                        iva = "EXENTA";
                    case 2 ->
                        iva = "5%";
                    case 3 ->
                        iva = "10%";
                    default -> {
                    }
                }
                
                
                msg2 += String.format("%1$-1s", codB + "/" + Descripcion + "\n");
                msg2 += String.format("%1$-9s %2$-12s %3$-15s %4$-9s", iva + "%", cant , Punit, Mont);
            }
            msg2 += "\n";
            mmOutputStream.write(ESC_ALIGN_LEFT);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg2, 0, 0, 0, 0));
            String msg3 = "------------------------\n";

            msg3 += "TOTAL Gs." + tot + "\n";
            msg3 += "------------------------\n";
            mmOutputStream.write(ESC_ALIGN_CENTER);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg3, 0, 0, 1, 1));
            String msg4 = d.Convertir(tot.replace(".", "").replace(",", ""), true) + "\n";
            msg4 += "------------------------------------------------\n";
            msg4 += "\n";
            msg4 += "TOTALES GRAVADA" + "\n";
            msg4 += "EXENTAS   -------->              " + txtExenta.getText().trim() + "\n";
            msg4 += "GRAV.  5% -------->              " + txt5libre.getText().trim() + "\n";
            msg4 += "GRAV. 10% -------->              " + txt10Libre.getText().trim() + "\n\n";
            msg4 += "LIQUIDACIÓN DEL I.V.A." + "\n";
            msg4 += "IVA 5%    -------->              " + txt5.getText().trim() + "\n";
            msg4 += "IVA 10%   -------->              " + txt10.getText().trim() + "\n";
            mmOutputStream.write(ESC_ALIGN_LEFT);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg4, 0, 0, 0, 0));
            String msg5 = "------------------------------------------------\n";
            int totaliva = Integer.parseInt(txt5L.getText()) + Integer.parseInt(txt10L.getText());
            msg5 += "TOTAL I.V.A.: " + nformat.format(Integer.parseInt(String.valueOf(totaliva))) + "\n";
            msg5 += "------------------------------------------------\n";
            mmOutputStream.write(ESC_ALIGN_CENTER);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg5, 0, 0, 0, 0));
            String msg6 = "EFECTIVO: 0 \n";
            msg6 += "VUELTO:   0" + "\n";
            msg6 += "\n";
            msg6 += "ORIGINAL: CLIENTE\n";
            msg6 += "\n";
            msg6 += "\n";
            mmOutputStream.write(ESC_ALIGN_LEFT);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg6, 0, 0, 0, 0));
            String msg7 = empresa + "\n";
            msg7 += "AGRADECE SU PREFERENCIA";
            msg7 += "\n\n";
            mmOutputStream.write(ESC_ALIGN_CENTER);
            mmOutputStream.write(0x1C);
            mmOutputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            mmOutputStream.write(0x1B);
            mmOutputStream.write(0x74);
            mmOutputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
            mmOutputStream.write(getByteString(msg7, 0, 0, 0, 0));
            mmOutputStream.write("\n\n".getBytes());

        } catch (Exception e) {
            Mensajes.error("ERROR_IFL: Ocurrio un error inesperado enviando la orden de impresión.\nMOTIVO: "+e.getMessage().toString());
        }
    }*/
    public static void imprimirTicket() {
        //Impresora matricial tmu-220

        String empresa = null;
        String ruc = null;
        String celular = null;
        String direccion = null;

        PrinterService printerService = new PrinterService();

        final byte[] openCD = {27, 112, 0, 60, 120};
        printerService.printBytes(openCD);

        System.out.println(printerService.getPrinters());
        int filas = tbDetalle.getRowCount();
        DecimalFormat formateador = new DecimalFormat("#,###");
        String tot = formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", "")));
        try {
            prepararBD();
            ResultSet rs = st.executeQuery("SELECT * FROM empresa WHERE em_indicador='S'");
            rs.last();
            empresa = rs.getString("em_razonsocial");
            ruc = rs.getString("em_ruc");
            celular = rs.getString("em_telefono") + "-" + rs.getString("em_celular");
            direccion = rs.getString("em_direccion");
            rs.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
        }
        String Ticket = "              " + empresa + "\n";
        Ticket += "         VENTAS DE LACTEOS LACTOLANDA\n";
        Ticket += "                RUC: " + ruc + "\n";
        Ticket += "         CEL: " + celular + "\n";
        Ticket += "     " + direccion + "\n";
        Ticket += "            CNEL. OVIEDO - PARAGUAY\n";
        Ticket += "----------------------------------------------\n";
        Ticket += "TICKET " + lbCond.getText().trim() + " NRO: " + txtEPE.getText().trim() + "-" + txtTicketN.getText().trim() + "\n";
        Ticket += "FECHA/HORA: " + txtfechaF.getText().trim() + " " + txtHora.getText().trim() + "\n";
        Ticket += "VENDEDOR: " + lbEmpleadoT.getText().trim() + "\n";
        Ticket += "\n";
        Ticket += "CLIENTE: " + txtRazonS.getText().trim() + "\n";
        Ticket += "RUC/CI: " + txtRuc.getText().trim() + "\n";
        Ticket += "----------------------------------------------\n";
        Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
        Ticket += "\n";
        Ticket += "----------------------------------------------\n";
        for (int i = 0; i < filas; i++) {
            String codB = tbDetalle.getValueAt(i, 3).toString().trim() + " " + tbDetalle.getValueAt(i, 13).toString().trim();
            String Descripcion = tbDetalle.getValueAt(i, 4).toString().trim();
            String cant = tbDetalle.getValueAt(i, 5).toString().trim();
            String Punit = tbDetalle.getValueAt(i, 6).toString().trim();
            String Mont = tbDetalle.getValueAt(i, 10).toString().trim();
            int idiva = Integer.parseInt(tbDetalle.getValueAt(i, 11).toString().trim());
            String iva = null;
            switch (idiva) {
                case 1 ->
                    iva = "EXENTA";
                case 2 ->
                    iva = "5%";
                case 3 ->
                    iva = "10%";
                default -> {
                }
            }

            //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
            Ticket += String.format("%1$1s", codB + "\n");
            Ticket += String.format("%1$1s", Descripcion + "\n");
            Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
        }
        Ticket += "\n";
        Ticket += "==============================================\n";
        Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\n";
        Ticket += "==============================================\n";
        Ticket += "\n";
        Ticket += "EFECTIVO:  " + txtAbonoT.getText().trim() + "\n";
        Ticket += "VUELTO:    " + txtVueltoT.getText().trim() + "\n";
        Ticket += "\n";
        Ticket += "              " + empresa + "\n";
        Ticket += "            AGRADECE SU PREFERENCIA\n";
        Ticket += "\n";

        printerService.printString(Ticket);
        //print some stuff

        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};
        //final byte[] openCD={27,112,0,60,120};
        // printerService.printBytes(openCD);
        printerService.printBytes(cutP);

    }

    public static void imprimirTicketOriginal() {
        //Impresora matricial tmu-220
        String empresa = null;
        String ruc = null;
        String celular = null;
        String direccion = null;

        PrinterService printerService = new PrinterService();

        final byte[] openCD = {27, 112, 0, 60, 120};
        printerService.printBytes(openCD);

        System.out.println(printerService.getPrinters());
        int filas = tbDetalle.getRowCount();
        DecimalFormat formateador = new DecimalFormat("#,###");
        String tot = formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", "")));
        try {
            prepararBD();
            ResultSet rs = st.executeQuery("SELECT * FROM empresa WHERE em_indicador='S'");
            rs.last();
            empresa = rs.getString("em_razonsocial");
            ruc = rs.getString("em_ruc");
            celular = rs.getString("em_telefono") + "-" + rs.getString("em_celular");
            direccion = rs.getString("em_direccion");
            rs.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
        }
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
        Ticket += "FACTURA " + lbCond.getText().trim() + " NRO: " + txtEstablecimiento1.getText().trim()
                + "-" + txtEmision1.getText().trim() + "-" + txtFacturaN1.getText().trim() + "\n";
        Ticket += "FECHA/HORA: " + txtfechaF.getText().trim() + " " + txtHora.getText().trim() + "\n";
        Ticket += "VENDEDOR: " + lbEmpleadoF.getText().trim() + "\n";
        Ticket += "\n";
        Ticket += "CLIENTE: " + txtRazonS.getText().trim() + "\n";
        Ticket += "RUC/CI: " + txtRuc.getText().trim() + "\n";
        Ticket += "----------------------------------------------\n";
        Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
        Ticket += "\n";
        Ticket += "----------------------------------------------\n";
        for (int i = 0; i < filas; i++) {
            String codB = tbDetalle.getValueAt(i, 3).toString().trim() + " " + tbDetalle.getValueAt(i, 13).toString().trim();
            String Descripcion = tbDetalle.getValueAt(i, 4).toString().trim();
            String cant = tbDetalle.getValueAt(i, 5).toString().trim();
            String Punit = tbDetalle.getValueAt(i, 6).toString().trim();
            String Mont = tbDetalle.getValueAt(i, 10).toString().trim();
            int idiva = Integer.parseInt(tbDetalle.getValueAt(i, 11).toString().trim());
            String iva = null;
            switch (idiva) {
                case 1 ->
                    iva = "EXENTA";
                case 2 ->
                    iva = "5%";
                case 3 ->
                    iva = "10%";
                default -> {
                }
            }

            //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
            Ticket += String.format("%1$1s", codB + "\n");
            Ticket += String.format("%1$1s", Descripcion + "\n");
            Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
        }
        Ticket += "\n";
        Ticket += "==============================================\n";
        Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\n";
        //Ticket += "           TOTAL Gs.:"+tot+"\n";
        Ticket += "==============================================\n";
        String letras = d.Convertir(tot.replace(".", "").replace(",", ""), true);
        Ticket += String.format("%1$1s", letras + "\n");
        //Ticket += "\n";
        Ticket += "==============================================\n";
        Ticket += "\n";
        Ticket += "-------------- TOTALES GRAVADA ---------------\n";
        Ticket += "EXENTAS     ------>              " + txtExenta.getText().trim() + "\n";
        Ticket += "GRAVADA 5%  ------>              " + txt5libre.getText().trim() + "\n";
        Ticket += "GRAVADA 10% ------>              " + txt10Libre.getText().trim() + "\n";
        Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\n";
        Ticket += "I.V.A. 5%   ------>              " + txt5.getText() + "\n";
        Ticket += "I.V.A. 10%  ------>              " + txt10.getText() + "\n";
        Ticket += "----------------------------------------------\n";
        String totaliva = String.valueOf(Integer.parseInt(txt5L.getText()) + Integer.parseInt(txt10L.getText()));
        Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
        Ticket += "----------------------------------------------\n";
        Ticket += "\n";
        Ticket += "EFECTIVO:  " + txtAbonoF.getText().trim() + "\n";
        Ticket += "VUELTO:    " + txtVueltoF.getText().trim() + "\n";
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
        //final byte[] openCD={27,112,0,60,120};
        //printerService.printBytes(openCD);
        printerService.printBytes(cutP);

    }

    public static void imprimirTicketDuplicado() {
        //Impresora matricial tmu-220
        String empresa = null;
        String ruc = null;
        String celular = null;
        String direccion = null;

        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());
        int filas = tbDetalle.getRowCount();
        DecimalFormat formateador = new DecimalFormat("#,###");
        String tot = formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", "")));
        try {
            prepararBD();
            ResultSet rs = st.executeQuery("SELECT * FROM empresa WHERE em_indicador='S'");
            rs.last();
            empresa = rs.getString("em_razonsocial");
            ruc = rs.getString("em_ruc");
            celular = rs.getString("em_telefono") + "-" + rs.getString("em_celular");
            direccion = rs.getString("em_direccion");
            rs.close();
            st.close();
            stMovil.close();
            stTransaccion.close();
            stTransaccionMovil.close();
            con.close();
            conMovil.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
        }
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
        Ticket += "FACTURA " + lbCond.getText().trim() + " NRO: " + txtEstablecimiento1.getText().trim()
                + "-" + txtEmision1.getText().trim() + "-" + txtFacturaN1.getText().trim() + "\n";
        Ticket += "FECHA/HORA: " + txtfechaF.getText().trim() + " " + txtHora.getText().trim() + "\n";
        Ticket += "VENDEDOR: " + lbEmpleadoF.getText().trim() + "\n";
        Ticket += "\n";
        Ticket += "CLIENTE: " + txtRazonS.getText().trim() + "\n";
        Ticket += "RUC/CI: " + txtRuc.getText().trim() + "\n";
        Ticket += "----------------------------------------------\n";
        Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
        Ticket += "\n";
        Ticket += "----------------------------------------------\n";
        for (int i = 0; i < filas; i++) {
            String codB = tbDetalle.getValueAt(i, 3).toString().trim() + " " + tbDetalle.getValueAt(i, 13).toString().trim();
            String Descripcion = tbDetalle.getValueAt(i, 4).toString().trim();
            String cant = tbDetalle.getValueAt(i, 5).toString().trim();
            String Punit = tbDetalle.getValueAt(i, 6).toString().trim();
            String Mont = tbDetalle.getValueAt(i, 10).toString().trim();
            int idiva = Integer.parseInt(tbDetalle.getValueAt(i, 11).toString().trim());
            String iva = null;
            switch (idiva) {
                case 1 ->
                    iva = "EXENTA";
                case 2 ->
                    iva = "5%";
                case 3 ->
                    iva = "10%";
                default -> {
                }
            }

            //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
            Ticket += String.format("%1$1s", codB + "\n");
            Ticket += String.format("%1$1s", Descripcion + "\n");
            Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
        }
        Ticket += "\n";
        Ticket += "==============================================\n";
        Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\n";
        //Ticket += "           TOTAL Gs.:"+tot+"\n";
        Ticket += "==============================================\n";
        String letras = d.Convertir(tot.replace(".", "").replace(",", ""), true);
        Ticket += String.format("%1$1s", letras + "\n");
        //Ticket += "\n";
        Ticket += "==============================================\n";
        Ticket += "\n";
        Ticket += "-------------- TOTALES GRAVADA ---------------\n";
        Ticket += "EXENTAS     ------>              " + txtExenta.getText().trim() + "\n";
        Ticket += "GRAVADA 5%  ------>              " + txt5libre.getText().trim() + "\n";
        Ticket += "GRAVADA 10% ------>              " + txt10Libre.getText().trim() + "\n";
        Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\n";
        Ticket += "I.V.A. 5%   ------>              " + txt5.getText() + "\n";
        Ticket += "I.V.A. 10%  ------>              " + txt10.getText() + "\n";
        Ticket += "----------------------------------------------\n";
        String totaliva = String.valueOf(Integer.parseInt(txt5L.getText()) + Integer.parseInt(txt10L.getText()));
        Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
        Ticket += "----------------------------------------------\n";
        Ticket += "\n";
        Ticket += "EFECTIVO:  " + txtAbonoF.getText().trim() + "\n";
        Ticket += "VUELTO:    " + txtVueltoF.getText().trim() + "\n";
        Ticket += "\n";
        Ticket += "DUPLICADO:  ARCHIVO TRIBUTARIO\n";
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

    }

    /*public static void imprimirTicket() {
        //Impresora termica
        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());
        int filas = tbDetalle.getRowCount();
        DecimalFormat formateador = new DecimalFormat("#,###");
        String tot = formateador.format(Integer.parseInt(txtTotalL.getText().replace(".", "").replace(",", "")));

        String Ticket = "                TICKET DE VENTA\n";
        Ticket += "-----------------------------------------------\n";
        Ticket += "FECHA: " + txtFecha.getText().trim() + " " + Fecha.darHora() + "\n";
        Ticket += "VENDEDOR/A: " + lbEmpleado.getText().trim() + "\n";
        Ticket += "-----------------------------------------------\n";
        for (int i = 0; i < filas; i++) {
            String Punit = tbDetalle.getValueAt(i, 5).toString().trim();
            String Mont = tbDetalle.getValueAt(i, 13).toString().trim();
            String Descripcion = tbDetalle.getValueAt(i, 2).toString().trim();
            Ticket += String.format("%1$1s", (i + 1) + "- " + Descripcion + "\n");
            //Ticket += String.format("%1$11s %2$15s %3$19s" ,"CANT: "+tbDetalle.getValueAt(i, 3).toString(), "PRECIO: "+formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), "SUBTOTAL: "+formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))))+ "\n";
            Ticket += String.format("%1$-11s %2$-15s %3$-19s", "CANT: " + tbDetalle.getValueAt(i, 3).toString(), "PRECIO: " + formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), "SUBTOTAL: " + formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", "")))) + "\n";
        }
        Ticket += "-----------------------------------------------\n";
        Ticket += String.format("%1$6s %2$32s", "TOTAL A PAGAR:", tot) + "\n";
        Ticket += "-----------------------------------------------\n";
        Ticket += "          GRACIAS POR SU PREFERENCIA!\n";
        Ticket += "\n\n\n\n\n\n";

        printerService.printString("POS-80C", Ticket);
        //print some stuff

        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};

        printerService.printBytes("POS-80C", cutP);

    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        menuEmergente = new javax.swing.JPopupMenu();
        itemCantidad = new javax.swing.JMenuItem();
        dlgFinFacturaL = new javax.swing.JDialog();
        btnConfirmarFactura = new javax.swing.JButton();
        txtAbonoL = new javax.swing.JTextField();
        Blanco1 = new org.edisoncor.gui.panel.PanelImage();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtAbonoF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtVueltoF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodVendedorF = new javax.swing.JTextField();
        lbEmpleadoF = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtEstablecimiento1 = new javax.swing.JTextField();
        txtEmision1 = new javax.swing.JTextField();
        txtFacturaN1 = new javax.swing.JTextField();
        txtCodF = new javax.swing.JTextField();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        itemVolverdeFactura = new javax.swing.JMenuItem();
        dlgFinTicket = new javax.swing.JDialog();
        btnConfirmarTicket = new javax.swing.JButton();
        txtAbonoTL = new javax.swing.JTextField();
        Blanco2 = new org.edisoncor.gui.panel.PanelImage();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtAbonoT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtVueltoT = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtCodVendedorT = new javax.swing.JTextField();
        lbEmpleadoT = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        txtEPE = new javax.swing.JTextField();
        txtTicketN = new javax.swing.JTextField();
        txtCodT = new javax.swing.JTextField();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        item_ConfirmarTicket = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        item_VolverdeTicket = new javax.swing.JMenuItem();
        OpcionesEmision = new javax.swing.JDialog();
        Blanco3 = new org.edisoncor.gui.panel.PanelImage();
        btnTicket = new javax.swing.JButton();
        btnFacturaLegal = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        itemTicket_de_Venta = new javax.swing.JMenuItem();
        itemFactura_Legal = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        itemCancelarEmitirComprobante = new javax.swing.JMenuItem();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        lbComprobante = new javax.swing.JLabel();
        txtFacturaN = new javax.swing.JTextField();
        txtEstablecimiento = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtRazonS = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        btnBuscarClientes = new javax.swing.JButton();
        txtfechaF = new javax.swing.JTextField();
        lbTimbrado = new javax.swing.JLabel();
        lbValidaz = new javax.swing.JLabel();
        txtEmision = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel12 = new javax.swing.JPanel();
        rContado = new javax.swing.JRadioButton();
        rCredito = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtArt = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        etiCant = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        txtNetoL = new javax.swing.JTextField();
        txtDescuentoL = new javax.swing.JTextField();
        txtCodArticulo = new javax.swing.JTextField();
        txtExentaL = new javax.swing.JTextField();
        txt5L = new javax.swing.JTextField();
        txt10L = new javax.swing.JTextField();
        txt5libre = new javax.swing.JTextField();
        txt10Libre = new javax.swing.JTextField();
        btnBuscarArticulo = new newscomponents.RSButtonGradientIcon_new();
        jPanel3 = new javax.swing.JPanel();
        lbCred = new javax.swing.JLabel();
        txtdisponibleL = new javax.swing.JTextField();
        txtTotalL = new javax.swing.JTextField();
        lbCond = new javax.swing.JLabel();
        btnRestar = new javax.swing.JButton();
        txtCodCliente = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnCant = new javax.swing.JButton();
        txtidEmision = new javax.swing.JTextField();
        txtTotalCosto = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        btnNuevo = new newscomponents.RSButtonBigIcon_new();
        btnGuardar = new newscomponents.RSButtonBigIcon_new();
        btnCancelar = new newscomponents.RSButtonBigIcon_new();
        btnSalir = new newscomponents.RSButtonBigIcon_new();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtExenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemBuscarC = new javax.swing.JMenuItem();
        itemBuscarA = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        itemCantidad.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        itemCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Modify.png"))); // NOI18N
        itemCantidad.setText("Modificar Cantidad");
        itemCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCantidadActionPerformed(evt);
            }
        });
        menuEmergente.add(itemCantidad);

        dlgFinFacturaL.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dlgFinFacturaL.setResizable(false);
        dlgFinFacturaL.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dlgFinFacturaLWindowOpened(evt);
            }
        });
        dlgFinFacturaL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dlgFinFacturaLKeyPressed(evt);
            }
        });

        btnConfirmarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/accept.png"))); // NOI18N
        btnConfirmarFactura.setText("CONFIRMAR");
        btnConfirmarFactura.setEnabled(false);
        btnConfirmarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarFacturaActionPerformed(evt);
            }
        });

        txtAbonoL.setEditable(false);
        txtAbonoL.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Blanco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco1.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel14.setOpaque(false);

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel15.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel15.setText("EFECTIVO");

        txtAbonoF.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        txtAbonoF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbonoF.setText("0");
        txtAbonoF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAbonoFMouseClicked(evt);
            }
        });
        txtAbonoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoFActionPerformed(evt);
            }
        });
        txtAbonoF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoFKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel16.setText("VUELTO");

        txtVueltoF.setEditable(false);
        txtVueltoF.setBackground(new java.awt.Color(255, 255, 255));
        txtVueltoF.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        txtVueltoF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVueltoF.setText("0");
        txtVueltoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAbonoF, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txtVueltoF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbonoF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVueltoF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("DETALLAR COBRANZA", new javax.swing.ImageIcon(getClass().getResource("/Iconos/billete.png")), jPanel14); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ID VENDEDOR");

        txtCodVendedorF.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtCodVendedorF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodVendedorF.setBorder(null);
        txtCodVendedorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodVendedorFActionPerformed(evt);
            }
        });
        txtCodVendedorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodVendedorFKeyPressed(evt);
            }
        });

        lbEmpleadoF.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbEmpleadoF.setForeground(new java.awt.Color(255, 255, 255));
        lbEmpleadoF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("FACTURA N°");

        txtEstablecimiento1.setEditable(false);
        txtEstablecimiento1.setBackground(new java.awt.Color(255, 255, 255));
        txtEstablecimiento1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtEstablecimiento1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstablecimiento1ActionPerformed(evt);
            }
        });

        txtEmision1.setEditable(false);
        txtEmision1.setBackground(new java.awt.Color(255, 255, 255));
        txtEmision1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtEmision1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmision1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmision1ActionPerformed(evt);
            }
        });

        txtFacturaN1.setEditable(false);
        txtFacturaN1.setBackground(new java.awt.Color(255, 255, 255));
        txtFacturaN1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFacturaN1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFacturaN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFacturaN1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFacturaN1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodVendedorF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbEmpleadoF, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel20)
                        .addGap(10, 10, 10)
                        .addComponent(txtEstablecimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtEmision1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtFacturaN1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodVendedorF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lbEmpleadoF, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstablecimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmision1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFacturaN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Blanco1Layout = new javax.swing.GroupLayout(Blanco1);
        Blanco1.setLayout(Blanco1Layout);
        Blanco1Layout.setHorizontalGroup(
            Blanco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Blanco1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Blanco1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Blanco1Layout.setVerticalGroup(
            Blanco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Blanco1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtCodF.setEditable(false);
        txtCodF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jMenuBar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jMenu3.setText("OPCIONES");
        jMenu3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_thumb_up_black_24.png"))); // NOI18N
        jMenuItem3.setText("Confirmar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        itemVolverdeFactura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemVolverdeFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
        itemVolverdeFactura.setText("Volver a vender               ");
        itemVolverdeFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVolverdeFacturaActionPerformed(evt);
            }
        });
        jMenu3.add(itemVolverdeFactura);

        jMenuBar3.add(jMenu3);

        dlgFinFacturaL.setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout dlgFinFacturaLLayout = new javax.swing.GroupLayout(dlgFinFacturaL.getContentPane());
        dlgFinFacturaL.getContentPane().setLayout(dlgFinFacturaLLayout);
        dlgFinFacturaLLayout.setHorizontalGroup(
            dlgFinFacturaLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgFinFacturaLLayout.createSequentialGroup()
                .addComponent(Blanco1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgFinFacturaLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConfirmarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbonoL)
                    .addComponent(txtCodF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgFinFacturaLLayout.setVerticalGroup(
            dlgFinFacturaLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgFinFacturaLLayout.createSequentialGroup()
                .addComponent(Blanco1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(dlgFinFacturaLLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnConfirmarFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAbonoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dlgFinTicket.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dlgFinTicket.setResizable(false);
        dlgFinTicket.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dlgFinTicketWindowOpened(evt);
            }
        });
        dlgFinTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dlgFinTicketKeyPressed(evt);
            }
        });

        btnConfirmarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/accept.png"))); // NOI18N
        btnConfirmarTicket.setText("CONFIRMAR");
        btnConfirmarTicket.setEnabled(false);
        btnConfirmarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarTicketActionPerformed(evt);
            }
        });

        txtAbonoTL.setEditable(false);
        txtAbonoTL.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Blanco2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco2.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel17.setOpaque(false);

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel18.setOpaque(false);

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel26.setText("EFECTIVO");

        txtAbonoT.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        txtAbonoT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbonoT.setText("0");
        txtAbonoT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAbonoTMouseClicked(evt);
            }
        });
        txtAbonoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoTActionPerformed(evt);
            }
        });
        txtAbonoT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoTKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel27.setText("VUELTO");

        txtVueltoT.setEditable(false);
        txtVueltoT.setBackground(new java.awt.Color(255, 255, 255));
        txtVueltoT.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        txtVueltoT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVueltoT.setText("0");
        txtVueltoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAbonoT)
                    .addComponent(txtVueltoT, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(txtAbonoT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVueltoT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("DETALLAR COBRANZA", new javax.swing.ImageIcon(getClass().getResource("/Iconos/billete.png")), jPanel17); // NOI18N

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID VENDEDOR");

        txtCodVendedorT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodVendedorT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodVendedorT.setBorder(null);
        txtCodVendedorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodVendedorTActionPerformed(evt);
            }
        });
        txtCodVendedorT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodVendedorTKeyPressed(evt);
            }
        });

        lbEmpleadoT.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbEmpleadoT.setForeground(new java.awt.Color(255, 255, 255));
        lbEmpleadoT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("TICKET N°");

        txtEPE.setEditable(false);
        txtEPE.setBackground(new java.awt.Color(255, 255, 255));
        txtEPE.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtEPE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEPE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEPEKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEPEKeyTyped(evt);
            }
        });

        txtTicketN.setEditable(false);
        txtTicketN.setBackground(new java.awt.Color(255, 255, 255));
        txtTicketN.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtTicketN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTicketN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTicketNKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTicketNKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel24)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodVendedorT, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbEmpleadoT, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(10, 10, 10)
                                .addComponent(txtEPE, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTicketN)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCodVendedorT, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(lbEmpleadoT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel25))
                    .addComponent(txtEPE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTicketN, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Blanco2Layout = new javax.swing.GroupLayout(Blanco2);
        Blanco2.setLayout(Blanco2Layout);
        Blanco2Layout.setHorizontalGroup(
            Blanco2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Blanco2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Blanco2Layout.setVerticalGroup(
            Blanco2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Blanco2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCodT.setEditable(false);
        txtCodT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jMenuBar4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jMenu4.setText("OPCIONES");
        jMenu4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        item_ConfirmarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_thumb_up_black_24.png"))); // NOI18N
        item_ConfirmarTicket.setText("Confirmar");
        item_ConfirmarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ConfirmarTicketActionPerformed(evt);
            }
        });
        jMenu4.add(item_ConfirmarTicket);
        jMenu4.add(jSeparator8);

        item_VolverdeTicket.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        item_VolverdeTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
        item_VolverdeTicket.setText("Volver a vender               ");
        item_VolverdeTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_VolverdeTicketActionPerformed(evt);
            }
        });
        jMenu4.add(item_VolverdeTicket);

        jMenuBar4.add(jMenu4);

        dlgFinTicket.setJMenuBar(jMenuBar4);

        javax.swing.GroupLayout dlgFinTicketLayout = new javax.swing.GroupLayout(dlgFinTicket.getContentPane());
        dlgFinTicket.getContentPane().setLayout(dlgFinTicketLayout);
        dlgFinTicketLayout.setHorizontalGroup(
            dlgFinTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgFinTicketLayout.createSequentialGroup()
                .addComponent(Blanco2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgFinTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAbonoTL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmarTicket, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgFinTicketLayout.setVerticalGroup(
            dlgFinTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgFinTicketLayout.createSequentialGroup()
                .addComponent(btnConfirmarTicket)
                .addGap(101, 101, 101)
                .addComponent(txtAbonoTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Blanco2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        OpcionesEmision.setResizable(false);

        Blanco3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco3.setPreferredSize(new java.awt.Dimension(690, 418));

        btnTicket.setBackground(new java.awt.Color(0, 204, 0));
        btnTicket.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        btnTicket.setForeground(new java.awt.Color(255, 255, 255));
        btnTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/TICK_30.png"))); // NOI18N
        btnTicket.setText("TICKET DE VENTA");
        btnTicket.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnTicket.setBorderPainted(false);
        btnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketActionPerformed(evt);
            }
        });

        btnFacturaLegal.setBackground(new java.awt.Color(0, 0, 255));
        btnFacturaLegal.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        btnFacturaLegal.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturaLegal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/FACT_30.png"))); // NOI18N
        btnFacturaLegal.setText("FACTURA LEGAL");
        btnFacturaLegal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFacturaLegal.setBorderPainted(false);
        btnFacturaLegal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaLegalActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("EMITIR COMPROBANTE");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout Blanco3Layout = new javax.swing.GroupLayout(Blanco3);
        Blanco3.setLayout(Blanco3Layout);
        Blanco3Layout.setHorizontalGroup(
            Blanco3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
            .addComponent(btnFacturaLegal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Blanco3Layout.setVerticalGroup(
            Blanco3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Blanco3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnFacturaLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jMenuBar5.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_view_stream_black_20.png"))); // NOI18N
        jMenu5.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemTicket_de_Venta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemTicket_de_Venta.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        itemTicket_de_Venta.setText("TICKET DE VENTA               ");
        itemTicket_de_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTicket_de_VentaActionPerformed(evt);
            }
        });
        jMenu5.add(itemTicket_de_Venta);

        itemFactura_Legal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFactura_Legal.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        itemFactura_Legal.setText("FACTURA LEGAL");
        itemFactura_Legal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFactura_LegalActionPerformed(evt);
            }
        });
        jMenu5.add(itemFactura_Legal);
        jMenu5.add(jSeparator10);

        itemCancelarEmitirComprobante.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelarEmitirComprobante.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        itemCancelarEmitirComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
        itemCancelarEmitirComprobante.setText("Volver a vender");
        itemCancelarEmitirComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarEmitirComprobanteActionPerformed(evt);
            }
        });
        jMenu5.add(itemCancelarEmitirComprobante);

        jMenuBar5.add(jMenu5);

        OpcionesEmision.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout OpcionesEmisionLayout = new javax.swing.GroupLayout(OpcionesEmision.getContentPane());
        OpcionesEmision.getContentPane().setLayout(OpcionesEmisionLayout);
        OpcionesEmisionLayout.setHorizontalGroup(
            OpcionesEmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco3, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        OpcionesEmisionLayout.setVerticalGroup(
            OpcionesEmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        jLabel1.setText("OPERACIÓN N°");

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("FECHA Y HORA:");

        jLabel19.setText("MOV. CAJA N°");

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbComprobante.setText("COMPROBANTE N°: ");

        txtFacturaN.setEditable(false);
        txtFacturaN.setBackground(new java.awt.Color(255, 255, 255));
        txtFacturaN.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFacturaN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFacturaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFacturaNActionPerformed(evt);
            }
        });
        txtFacturaN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFacturaNKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFacturaNKeyTyped(evt);
            }
        });

        txtEstablecimiento.setEditable(false);
        txtEstablecimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtEstablecimiento.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEstablecimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstablecimientoActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtRazonS.setEditable(false);
        txtRazonS.setBackground(new java.awt.Color(255, 255, 255));
        txtRazonS.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtRazonS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRazonS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSActionPerformed(evt);
            }
        });

        txtRuc.setEditable(false);
        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtRuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscarClientes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnBuscarClientes.setForeground(new java.awt.Color(0, 102, 102));
        btnBuscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        btnBuscarClientes.setText("F3-Clientes");
        btnBuscarClientes.setBorderPainted(false);
        btnBuscarClientes.setContentAreaFilled(false);
        btnBuscarClientes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBuscarClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(btnBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRazonS)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRazonS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarClientes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtfechaF.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfechaF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbTimbrado.setBackground(new java.awt.Color(0, 102, 102));
        lbTimbrado.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbTimbrado.setForeground(new java.awt.Color(255, 255, 255));
        lbTimbrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTimbrado.setText("TIMBRADO N°: ");
        lbTimbrado.setOpaque(true);

        lbValidaz.setBackground(new java.awt.Color(0, 102, 102));
        lbValidaz.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbValidaz.setForeground(new java.awt.Color(255, 255, 255));
        lbValidaz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValidaz.setText("VALIDEZ:");
        lbValidaz.setOpaque(true);

        txtEmision.setEditable(false);
        txtEmision.setBackground(new java.awt.Color(255, 255, 255));
        txtEmision.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmisionActionPerformed(evt);
            }
        });

        txtHora.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel12.setBackground(java.awt.Color.white);
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rContado);
        rContado.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rContado.setForeground(new java.awt.Color(0, 102, 102));
        rContado.setSelected(true);
        rContado.setText("CONTADO");
        rContado.setOpaque(false);
        rContado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rContadoActionPerformed(evt);
            }
        });
        jPanel12.add(rContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 8, -1, 13));

        buttonGroup1.add(rCredito);
        rCredito.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rCredito.setForeground(new java.awt.Color(0, 102, 102));
        rCredito.setText("CREDITO");
        rCredito.setOpaque(false);
        rCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rCreditoActionPerformed(evt);
            }
        });
        jPanel12.add(rCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 26, -1, 13));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTimbrado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbValidaz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 102, Short.MAX_VALUE)
                                .addComponent(lbComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(txtFacturaN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(lbValidaz, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtfechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFacturaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbComprobante)
                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 89, 665, -1));

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL A COBRAR");
        jLabel7.setOpaque(true);
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 450, 75));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 50)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(204, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0");
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel7.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 80, 450, 94));

        Blanco.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 88, 460, 180));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArt.setEditable(false);
        txtArt.setBackground(new java.awt.Color(255, 255, 255));
        txtArt.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
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
        jPanel2.add(txtArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 2, 480, 28));

        txtCant.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
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
        jPanel2.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 2, 50, 28));

        etiCant.setBackground(new java.awt.Color(0, 102, 102));
        etiCant.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        etiCant.setForeground(new java.awt.Color(255, 255, 255));
        etiCant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiCant.setText("Artículos registrados:");
        etiCant.setOpaque(true);
        jPanel2.add(etiCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 660, 20));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tbDetalle.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDetalle.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetalle.setRowHeight(25);
        tbDetalle.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 30, 1140, 337));

        txtNetoL.setEditable(false);
        txtNetoL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNetoL.setText("0");
        jPanel2.add(txtNetoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 370, 49, -1));

        txtDescuentoL.setEditable(false);
        txtDescuentoL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoL.setText("0");
        jPanel2.add(txtDescuentoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, 47, -1));

        txtCodArticulo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtCodArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodArticuloActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 370, 37, -1));

        txtExentaL.setEditable(false);
        txtExentaL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtExentaL.setText("0");
        jPanel2.add(txtExentaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 370, 37, -1));

        txt5L.setEditable(false);
        txt5L.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt5L.setText("0");
        jPanel2.add(txt5L, new org.netbeans.lib.awtextra.AbsoluteConstraints(1037, 370, 47, -1));

        txt10L.setEditable(false);
        txt10L.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt10L.setText("0");
        jPanel2.add(txt10L, new org.netbeans.lib.awtextra.AbsoluteConstraints(1085, 370, 51, -1));
        jPanel2.add(txt5libre, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 100, -1));
        jPanel2.add(txt10Libre, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 100, -1));

        btnBuscarArticulo.setText("F9-CARGAR");
        btnBuscarArticulo.setColorPrimario(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorPrimarioHover(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorSecundario(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setColorSecundarioHover(new java.awt.Color(0, 102, 102));
        btnBuscarArticulo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnBuscarArticulo.setForegroundHover(new java.awt.Color(205, 0, 0));
        btnBuscarArticulo.setForegroundIconHover(new java.awt.Color(205, 0, 0));
        btnBuscarArticulo.setIconTextGap(10);
        btnBuscarArticulo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnBuscarArticulo.setSizeIcon(21.0F);
        btnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArticuloActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 2, 131, 28));

        Blanco.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 238, 1140, 390));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbCred.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        lbCred.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lbCred, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 86, 22));
        jPanel3.add(txtdisponibleL, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 86, -1));

        txtTotalL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotalL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalL.setText("0");
        jPanel3.add(txtTotalL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 79, -1));

        lbCond.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbCond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lbCond, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 79, 20));

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, 25));

        txtCodCliente.setEditable(false);
        txtCodCliente.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        txtCodCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });
        jPanel3.add(txtCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 40, -1));

        txtFecha.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 61, 20));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        btnCant.setText("Act. Precio");
        btnCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCantActionPerformed(evt);
            }
        });
        jPanel3.add(btnCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 40, -1));

        txtidEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidEmisionActionPerformed(evt);
            }
        });
        jPanel3.add(txtidEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 66, -1));

        txtTotalCosto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalCostoActionPerformed(evt);
            }
        });
        jPanel3.add(txtTotalCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 100, -1));

        jPanel16.setOpaque(false);
        jPanel16.setLayout(new java.awt.GridLayout(1, 6));

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
        jPanel16.add(btnNuevo);

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
        jPanel16.add(btnGuardar);

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
        jPanel16.add(btnCancelar);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setText("SALIR");
        btnSalir.setBgHover(new java.awt.Color(1, 123, 123));
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(50.0F);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel16.add(btnSalir);

        jPanel3.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 80));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EXCENTAS");

        txtExenta.setEditable(false);
        txtExenta.setBackground(new java.awt.Color(255, 255, 255));
        txtExenta.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtExenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtExenta.setText("0");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("IVA 5%");

        txt5.setEditable(false);
        txt5.setBackground(new java.awt.Color(255, 255, 255));
        txt5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txt5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt5.setText("0");

        txt10.setEditable(false);
        txt10.setBackground(new java.awt.Color(255, 255, 255));
        txt10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txt10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt10.setText("0");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("IVA 10%");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtExenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt5)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, -1, -1));

        Blanco.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 80));

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_view_stream_black_20.png"))); // NOI18N
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_note_add_black_24.png"))); // NOI18N
        itemNuevo.setText("NUEVO");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevo);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_save_black_24.png"))); // NOI18N
        itemGuardar.setText("GUARDAR UN NUEVO REGISTRO                    ");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardar);

        itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_cancel_black_24.png"))); // NOI18N
        itemCancelar.setText("CANCELAR");
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        jMenu1.add(itemCancelar);
        jMenu1.add(jSeparator1);

        itemBuscarC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscarC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search20.png"))); // NOI18N
        itemBuscarC.setText("CAMBIAR CLIENTE");
        itemBuscarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarCActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarC);

        itemBuscarA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        itemBuscarA.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscarA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_add_black_24.png"))); // NOI18N
        itemBuscarA.setText("AGREGAR PRODUCTOS");
        itemBuscarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarAActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscarA);
        jMenu1.add(jSeparator4);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_remove_black_24.png"))); // NOI18N
        itemQuitar.setText("QUITAR PRODUCTO/S");
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        jMenu1.add(itemQuitar);
        jMenu1.add(jSeparator5);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/outline_exit_to_app_black_24.png"))); // NOI18N
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(Blanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        controlFactura.addTabla(tbDetalle);
        Rendes();
        cant();
        txtCodArticulo.setText("");
        txtArt.setText("");
        txtCant.setText("");
        btnBuscarArticulo.requestFocus();
        habilitarCANTCOSTO();
        btnBuscarArticulo.doClick();
        //btnBuscarArticuloActionPerformed(null);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        try {
            controlFactura.delRenglon(tbDetalle);
            Rendes();
            cant();
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_btnRestarActionPerformed

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtCant);
        ArticuloMovil Ar = GestionarArticulosMovil.busArticulo((txtCodArticulo.getText()));
        String dependencia = Ar.getDependencia();
        int idDep = Ar.getIddependencia();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (dependencia.equals("S")) {
                ArticuloMovil A = GestionarArticulosMovil.busArticulo(String.valueOf(idDep));
                if (txtCant.getText().isEmpty()) {
                    txtCant.selectAll();
                } else if (txtCant.getText().equals("0")) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) == 0.0) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) > A.getStock()) {
                    Mensajes.error("La cantidad que estas intentando vender supera el stock actual del producto");
                    txtCant.requestFocus();
                    txtCant.setText(String.valueOf(Ar.getStock()).trim().replace(".0", "").replace(",", ""));
                    txtCant.selectAll();
                } else {
                    btnAdd.doClick();
                }
            } else if (dependencia.equals("N")) {
                if (txtCant.getText().isEmpty()) {
                    txtCant.selectAll();
                } else if (txtCant.getText().equals("0")) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) == 0.0) {
                    txtCant.selectAll();
                } else if (Double.parseDouble(txtCant.getText()) > Ar.getStock()) {
                    Mensajes.error("La cantidad que estas intentando vender supera el stock actual del producto");
                    txtCant.requestFocus();
                    txtCant.setText(String.valueOf(Ar.getStock()).trim().replace(".0", "").replace(",", ""));
                    txtCant.selectAll();
                } else {
                    btnAdd.doClick();
                }

            }
        }
    }//GEN-LAST:event_txtCantKeyPressed

    private void btnBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientesActionPerformed
        // TODO add your handling code here:
        try {
            dlgBuscarCliente bcliente = new dlgBuscarCliente(null, true);
            bcliente.setLocationRelativeTo(null);
            bcliente.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }

    }//GEN-LAST:event_btnBuscarClientesActionPerformed

    private void rContadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rContadoActionPerformed
        // TODO add your handling code here:
        pintarCondicion();
    }//GEN-LAST:event_rContadoActionPerformed

    private void rCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rCreditoActionPerformed
        // TODO add your handling code here:
        pintarCondicion();
    }//GEN-LAST:event_rCreditoActionPerformed

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

    private void itemBuscarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarAActionPerformed
        // TODO add your handling code here:
        btnBuscarArticulo.doClick();
    }//GEN-LAST:event_itemBuscarAActionPerformed

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:S
        btnRestar.doClick();
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCantActionPerformed

    private void txtArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArtActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtArtActionPerformed

    private void txtArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArtKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtArtKeyReleased

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtFacturaNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaNKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaNKeyPressed

    private void txtFacturaNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaNKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

            System.out.println("Ingresa Solo Numeros");
        }
        int limite = 15;
        if (txtFacturaN.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFacturaNKeyTyped

    private void txtRazonSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSActionPerformed

    private void itemBuscarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarCActionPerformed
        // TODO add your handling code here:
        btnBuscarClientes.doClick();
    }//GEN-LAST:event_itemBuscarCActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void txtEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstablecimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimientoActionPerformed

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MousePressed

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

    private void itemCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCantidadActionPerformed
        // TODO add your handling code here:
        btnCantActionPerformed(null);
    }//GEN-LAST:event_itemCantidadActionPerformed

    private void txtidEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidEmisionActionPerformed

    private void txtEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmisionActionPerformed

    private void txtFacturaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFacturaNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaNActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void btnCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCantActionPerformed
        // TODO add your handling code here:
        if (tbDetalle.getSelectedRowCount() != 0) {
            try {
                controlFactura.actCantidad(tbDetalle);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnCantActionPerformed

    private void txtCodArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodArticuloActionPerformed

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void btnConfirmarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarFacturaActionPerformed
        // TODO add your handling code here:
        try {
            comprobarNFactura();
        } catch (Exception e) {
            Mensajes.Alerta("Error ComprobarNFactura: " + e.getMessage());
        }

    }//GEN-LAST:event_btnConfirmarFacturaActionPerformed

    private void txtCodVendedorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodVendedorFActionPerformed
        // TODO add your handling code here:
        try {
            if (Integer.parseInt(txtCodVendedorF.getText()) <= 0) {
                Mensajes.error("CODIGO EQUIVOCADO O NO POSEE PERFIL PARA VENTA");
                btnConfirmarFactura.setEnabled(false);
                txtCodVendedorF.requestFocus();
                txtCodVendedorF.selectAll();
            } else {
                try {
                    Vendedor vn = GestionarVendedor.busVendedorFactura(txtCodVendedorF.getText());
                    lbEmpleadoF.setText(vn.getNombreV());
                    btnConfirmarFactura.setEnabled(true);
                    txtAbonoF.requestFocus();
                    txtAbonoF.selectAll();
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodVendedorFActionPerformed

    private void txtCodVendedorFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVendedorFKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtCodVendedorF);
    }//GEN-LAST:event_txtCodVendedorFKeyPressed

    private void txtEstablecimiento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstablecimiento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento1ActionPerformed

    private void txtEmision1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmision1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmision1ActionPerformed

    private void txtFacturaN1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaN1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaN1KeyPressed

    private void txtFacturaN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaN1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

            System.out.println("Ingresa Solo Numeros");
        }
        int limite = 15;
        if (txtFacturaN.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFacturaN1KeyTyped

    private void txtAbonoFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAbonoFMouseClicked
        // TODO add your handling code here:
        //txtAbono.selectAll();
    }//GEN-LAST:event_txtAbonoFMouseClicked

    private void txtAbonoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoFActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtAbonoL.getText()) == 0) {
            btnConfirmarFactura.doClick();
        } else {
            try {
                int calculos = controlFactura.calCulosF();
                //txtVueltoF.setText(String.valueOf(calculos));
                DecimalFormat df = new DecimalFormat("#,###");
                txtVueltoF.setText(df.format(Integer.parseInt(String.valueOf(calculos).trim().replace(".", "").replace(",", ""))));
                txtVueltoF.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtAbonoFActionPerformed

    private void txtAbonoFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoFKeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbonoF.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbonoF.setText(df.format(Integer.valueOf(txtAbonoF.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbonoF.setText("0");
                txtAbonoF.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            if (txtAbonoF.getText().trim().length() >= 1) {
                DecimalFormat dff = new DecimalFormat("#0");
                txtAbonoL.setText(dff.format(Integer.valueOf(txtAbonoF.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbonoL.setText("0");

            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtAbonoFKeyReleased

    private void txtVueltoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoFActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtVueltoF.getText().trim().replace(".", "").replace(",", "")) < 0) {
            txtAbonoF.requestFocus();
        } else {
            btnConfirmarFactura.doClick();
        }

    }//GEN-LAST:event_txtVueltoFActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        btnConfirmarFactura.doClick();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void itemVolverdeFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVolverdeFacturaActionPerformed
        // TODO add your handling code here:
        dlgFinFacturaL.dispose();
        btnConfirmarFactura.setEnabled(false);
        txtCodVendedorF.setText("");
        lbEmpleadoF.setText("");
        txtAbonoF.setText("0");
        txtVueltoF.setText("0");
    }//GEN-LAST:event_itemVolverdeFacturaActionPerformed

    private void dlgFinFacturaLWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dlgFinFacturaLWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_dlgFinFacturaLWindowOpened

    private void dlgFinFacturaLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dlgFinFacturaLKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dlgFinFacturaLKeyPressed

    private void btnConfirmarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarTicketActionPerformed
        // TODO add your handling code here:
        try {
            ComprobarNTicket();
        } catch (Exception e) {
            Mensajes.Alerta("Error ComprobarNTicket: " + e.getMessage());
        }

    }//GEN-LAST:event_btnConfirmarTicketActionPerformed

    private void txtCodVendedorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodVendedorTActionPerformed
        // TODO add your handling code here:
        try {
            if (Integer.parseInt(txtCodVendedorT.getText()) <= 0) {
                Mensajes.error("CODIGO EQUIVOCADO O NO POSEE PERFIL PARA VENTA");
                btnConfirmarTicket.setEnabled(false);
                txtCodVendedorT.requestFocus();
                txtCodVendedorT.selectAll();
            } else {
                try {
                    Vendedor vn = GestionarVendedor.busVendedorTicket(txtCodVendedorT.getText());
                    lbEmpleadoT.setText(vn.getNombreV());
                    btnConfirmarTicket.setEnabled(true);
                    txtAbonoT.requestFocus();
                    txtAbonoT.selectAll();
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodVendedorTActionPerformed

    private void txtCodVendedorTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVendedorTKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtCodVendedorT);
    }//GEN-LAST:event_txtCodVendedorTKeyPressed

    private void txtTicketNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTicketNKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTicketNKeyPressed

    private void txtTicketNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTicketNKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTicketNKeyTyped

    private void txtEPEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEPEKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEPEKeyPressed

    private void txtEPEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEPEKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEPEKeyTyped

    private void txtAbonoTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAbonoTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonoTMouseClicked

    private void txtAbonoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoTActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtAbonoTL.getText()) == 0) {
            btnConfirmarTicket.doClick();
        } else {
            try {
                int calculos = controlFactura.calCulosT();
                //txtVuelto.setText(String.valueOf(calculos));
                DecimalFormat df = new DecimalFormat("#,###");
                txtVueltoT.setText(df.format(Integer.parseInt(String.valueOf(calculos).trim().replace(".", "").replace(",", ""))));
                txtVueltoT.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtAbonoTActionPerformed

    private void txtAbonoTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoTKeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbonoT.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbonoT.setText(df.format(Integer.valueOf(txtAbonoT.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbonoT.setText("0");
                txtAbonoT.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            if (txtAbonoT.getText().trim().length() >= 1) {
                DecimalFormat dff = new DecimalFormat("#0");
                txtAbonoTL.setText(dff.format(Integer.valueOf(txtAbonoT.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbonoTL.setText("0");

            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtAbonoTKeyReleased

    private void txtVueltoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoTActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtVueltoT.getText().trim().replace(".", "").replace(",", "")) < 0) {
            txtAbonoT.requestFocus();
        } else {
            btnConfirmarTicket.doClick();
        }

    }//GEN-LAST:event_txtVueltoTActionPerformed

    private void item_ConfirmarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ConfirmarTicketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_ConfirmarTicketActionPerformed

    private void item_VolverdeTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_VolverdeTicketActionPerformed
        // TODO add your handling code here:
        dlgFinTicket.dispose();
        btnConfirmarTicket.setEnabled(false);
        txtCodVendedorT.setText("");
        txtCodT.setText("");
        lbEmpleadoT.setText("");
        txtAbonoT.setText("0");
        txtAbonoTL.setText("0");
        txtVueltoT.setText("0");
    }//GEN-LAST:event_item_VolverdeTicketActionPerformed

    private void dlgFinTicketWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dlgFinTicketWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_dlgFinTicketWindowOpened

    private void dlgFinTicketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dlgFinTicketKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dlgFinTicketKeyPressed

    private void btnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketActionPerformed
        // TODO add your handling code here:
        try {
            obtenerNTicket();
        } catch (Exception e) {
            Mensajes.Alerta("Error obtenerNTicket: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTicketActionPerformed

    private void btnFacturaLegalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaLegalActionPerformed
        // TODO add your handling code here:
        //txtCodVendedorF.setText("");
        try {
            obtenerNFactura();
        } catch (Exception e) {
            Mensajes.Alerta("Error obtenerNFactura: " + e.getMessage());
        }


    }//GEN-LAST:event_btnFacturaLegalActionPerformed

    private void itemTicket_de_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTicket_de_VentaActionPerformed
        // TODO add your handling code here:
        btnTicket.doClick();
    }//GEN-LAST:event_itemTicket_de_VentaActionPerformed

    private void itemFactura_LegalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFactura_LegalActionPerformed
        // TODO add your handling code here:
        btnFacturaLegal.doClick();
    }//GEN-LAST:event_itemFactura_LegalActionPerformed

    private void itemCancelarEmitirComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarEmitirComprobanteActionPerformed
        // TODO add your handling code here:
        OpcionesEmision.dispose();
    }//GEN-LAST:event_itemCancelarEmitirComprobanteActionPerformed

    private void btnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArticuloActionPerformed
        // TODO add your handling code here:
        try {
            dlgBuscarArticuloVenta baf = new dlgBuscarArticuloVenta(null, true);
            baf.setLocationRelativeTo(null);
            //baf.setLocation(125, 365);
            baf.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_btnBuscarArticuloActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:7
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //prepararBD();
        ClienteMovil Cl = GestionarCliente.busCliente("1");
        controlFactura.selectClienteInicio("1");
        rContado.setSelected(true);
        pintarCondicion();
        txtFecha.setText(Fecha.fechaCorrecta());
        txtfechaF.setText(Fecha.fechaCorrectaFachada());
        txtHora.setText(Fecha.darHoraSinSS());
        btnBuscarClientes.setEnabled(true);
        rContado.setEnabled(true);
        rCredito.setEnabled(true);
        btnBuscarArticulo.setEnabled(true);
        txtCant.setEnabled(true);
        btnNuevo.setEnabled(false);
        itemNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        itemGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        itemCancelar.setEnabled(true);
        itemBuscarA.setEnabled(true);
        itemBuscarC.setEnabled(true);
        itemQuitar.setEnabled(true);
        btnSalir.setEnabled(false);
        itemSalir.setEnabled(false);
        btnBuscarArticulo.doClick();
        habilitarCANTCOSTO();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (tbDetalle.getRowCount() <= 0) {
            Mensajes.informacion("La lista de productos a facturar esta vacía.\nRECOMENDACIÓN: Inserte en la tabla los detalles de la venta e intente procesarlo nuevamente.");
            btnBuscarArticuloActionPerformed(null);
        } else if (Integer.parseInt(txtTotalL.getText().trim()) > 0) {
            OpcionesEmision.setSize(370, 295);
            OpcionesEmision.setLocationRelativeTo(this);
            OpcionesEmision.setModal(true);
            OpcionesEmision.setTitle("OPCIONES");
            OpcionesEmision.setVisible(true);
        } else {
            Mensajes.informacion("Total a cobrar es igual a 0.\nRECOMENDACIÓN: Verifique el precio de los productos.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar esta VENTA?");
        if (rpta == 0) {
            limpiarCampos();
            btnBuscarClientes.setEnabled(false);
            rContado.setEnabled(false);
            rContado.setSelected(true);
            rCredito.setEnabled(false);
            btnBuscarArticulo.setEnabled(false);
            txtCant.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnNuevo.requestFocus();
            itemNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            itemGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            itemCancelar.setEnabled(false);
            itemBuscarA.setEnabled(false);
            itemBuscarC.setEnabled(false);
            itemQuitar.setEnabled(false);
            btnSalir.setEnabled(true);
            itemSalir.setEnabled(true);
            cant();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            controlFactura.canCelar();
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtAbonoFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoFKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbonoF);
    }//GEN-LAST:event_txtAbonoFKeyPressed

    private void txtAbonoTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoTKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbonoT);
    }//GEN-LAST:event_txtAbonoTKeyPressed

    private void txtTotalCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCostoActionPerformed

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
            java.util.logging.Logger.getLogger(dlgVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            dlgVentas dialog = new dlgVentas(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelImage Blanco1;
    private org.edisoncor.gui.panel.PanelImage Blanco2;
    private org.edisoncor.gui.panel.PanelImage Blanco3;
    public static javax.swing.JDialog OpcionesEmision;
    private javax.swing.JButton btnAdd;
    private static newscomponents.RSButtonGradientIcon_new btnBuscarArticulo;
    public static javax.swing.JButton btnBuscarClientes;
    public static newscomponents.RSButtonBigIcon_new btnCancelar;
    private javax.swing.JButton btnCant;
    public static javax.swing.JButton btnConfirmarFactura;
    public static javax.swing.JButton btnConfirmarTicket;
    public static javax.swing.JButton btnFacturaLegal;
    public static newscomponents.RSButtonBigIcon_new btnGuardar;
    public static newscomponents.RSButtonBigIcon_new btnNuevo;
    private javax.swing.JButton btnRestar;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    public static javax.swing.JButton btnTicket;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JDialog dlgFinFacturaL;
    public static javax.swing.JDialog dlgFinTicket;
    public static javax.swing.JLabel etiCant;
    private static javax.swing.JMenuItem itemBuscarA;
    private static javax.swing.JMenuItem itemBuscarC;
    private static javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemCancelarEmitirComprobante;
    private javax.swing.JMenuItem itemCantidad;
    private static javax.swing.JMenuItem itemFactura_Legal;
    private static javax.swing.JMenuItem itemGuardar;
    public static javax.swing.JMenuItem itemNuevo;
    private static javax.swing.JMenuItem itemQuitar;
    private static javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemTicket_de_Venta;
    private javax.swing.JMenuItem itemVolverdeFactura;
    private javax.swing.JMenuItem item_ConfirmarTicket;
    private javax.swing.JMenuItem item_VolverdeTicket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbComprobante;
    public static javax.swing.JLabel lbCond;
    public static javax.swing.JLabel lbCred;
    public static javax.swing.JLabel lbEmpleadoF;
    public static javax.swing.JLabel lbEmpleadoT;
    private javax.swing.JLabel lbTimbrado;
    private javax.swing.JLabel lbValidaz;
    private javax.swing.JPopupMenu menuEmergente;
    public static javax.swing.JRadioButton rContado;
    public static javax.swing.JRadioButton rCredito;
    public static javax.swing.JTable tbDetalle;
    public static javax.swing.JTextField txt10;
    public static javax.swing.JTextField txt10L;
    public static javax.swing.JTextField txt10Libre;
    public static javax.swing.JTextField txt5;
    public static javax.swing.JTextField txt5L;
    public static javax.swing.JTextField txt5libre;
    public static javax.swing.JTextField txtAbonoF;
    public static javax.swing.JTextField txtAbonoL;
    public static javax.swing.JTextField txtAbonoT;
    public static javax.swing.JTextField txtAbonoTL;
    public static javax.swing.JTextField txtArt;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCant;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodArticulo;
    public static javax.swing.JTextField txtCodCliente;
    public static javax.swing.JTextField txtCodF;
    public static javax.swing.JTextField txtCodT;
    public static javax.swing.JTextField txtCodVendedorF;
    public static javax.swing.JTextField txtCodVendedorT;
    public static javax.swing.JTextField txtDescuentoL;
    public static javax.swing.JTextField txtEPE;
    public static javax.swing.JTextField txtEmision;
    public static javax.swing.JTextField txtEmision1;
    public static javax.swing.JTextField txtEstablecimiento;
    public static javax.swing.JTextField txtEstablecimiento1;
    public static javax.swing.JTextField txtExenta;
    public static javax.swing.JTextField txtExentaL;
    public static javax.swing.JTextField txtFacturaN;
    public static javax.swing.JTextField txtFacturaN1;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtHora;
    public static javax.swing.JTextField txtNetoL;
    public static javax.swing.JTextField txtRazonS;
    public static javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtTicketN;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtTotalCosto;
    public static javax.swing.JTextField txtTotalL;
    public static javax.swing.JTextField txtVueltoF;
    public static javax.swing.JTextField txtVueltoT;
    public static javax.swing.JTextField txtdisponibleL;
    public static javax.swing.JTextField txtfechaF;
    public static javax.swing.JTextField txtidEmision;
    // End of variables declaration//GEN-END:variables

}
