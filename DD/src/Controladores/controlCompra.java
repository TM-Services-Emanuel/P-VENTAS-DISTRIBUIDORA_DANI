package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Redondeo;
import Datos.ArregloCompras;
import Datos.ArregloPago;
import Datos.GestionarArticulos;
import Datos.GestionarArticulosMovil;
import Datos.GestionarCompra;
import Datos.GestionarProveedor;
import IU.dlgBuscarArticuloCompra;
import IU.dlgBuscarFacturaCredito;
import IU.dlgBuscarProveedor;
import IU.dlgCompras;
import IU.dlgConsultarCompras;
import IU.dlgConsultarCompras1;
import IU.dlgConsultarPagosProveedor;
import IU.dlgRegistrarPagosProveedor;
import Modelo.Articulo;
import Modelo.ArticuloMovil;
import Modelo.DetalleCompra;
import Modelo.DetallePago;
import Modelo.Proveedor;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlCompra {

    static Proveedor prov;
    static Articulo art;
    static DetallePago dcPago;
    static DetalleCompra dc;
    public static ArregloPago arrayPago = new ArregloPago();
    public static ArregloCompras array = new ArregloCompras();
    public static int costo;
    public static double costoiva;
    public static int ganancia;
    public static int descuento;
    static String UsuarioL = "";

    public static void selectProveedor() {
        int x = dlgBuscarProveedor.tbDetalle.getSelectedRow();
        String cod = dlgBuscarProveedor.tbDetalle.getValueAt(x, 0).toString();
        prov = GestionarProveedor.busProveedor(cod);
        dlgCompras.txtCodProv.setText(String.valueOf(prov.getCodP()));
        dlgCompras.txtRazonS.setText(prov.getRazoS());
        dlgCompras.txtRuc.setText(prov.getRuc());
    }

    public static void selectArticulo() {
        int x = dlgBuscarArticuloCompra.tbDetalle.getSelectedRow();
        String cod = dlgBuscarArticuloCompra.tbDetalle.getValueAt(x, 0).toString();
        art = GestionarArticulos.busArticulo(cod);
        dlgCompras.txtCodA.setText(String.valueOf(art.getCodArticulo()));
        dlgCompras.txtArt.setText(art.getDescripcion());
        dlgCompras.txtCant.setText("1");
        dlgCompras.txtCant.selectAll();
        String PC = String.valueOf(art.getCosto());
        DecimalFormat df = new DecimalFormat("#,###");
        dlgCompras.txtCosto.setText((df.format(Integer.parseInt(PC.trim().replace(".", "").replace(",", "")))));
        dlgCompras.txtCostoL.setText(PC);
    }

    public static void insertar(String cod, String codB, String desc, String cant, String prec, String total, int iva, String PCosto, String Gan, String Des, JTable tabla) {

        String fila[] = new String[19];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codB;
        fila[2] = desc;
        fila[3] = cant;
        fila[4] = cant;
        fila[5] = prec;
        fila[6] = prec;
        fila[7] = String.valueOf(iva);
        switch (iva) {
            case 10 -> {
                fila[8] = "0";
                fila[9] = "0";
                fila[10] = "0";
                fila[11] = "0";
                fila[12] = total;
                fila[13] = total;
            }
            case 5 -> {
                fila[8] = "0";
                fila[9] = "0";
                fila[10] = total;
                fila[11] = total;
                fila[12] = "0";
                fila[13] = "0";
            }
            default -> {
                fila[8] = total;
                fila[9] = total;
                fila[10] = "0";
                fila[11] = "0";
                fila[12] = "0";
                fila[13] = "0";
            }
        }
        fila[14] = total;
        fila[15] = total;
        fila[16] = PCosto;
        fila[17] = Gan;
        fila[18] = Des;
        tb.addRow(fila);
    }

    public static void insertarPagos(int CodCompra, String Factura, int Monto_Compra, int Monto_Saldo, JTable tabla) {

        String fila[] = new String[10];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = String.valueOf(CodCompra);
        fila[1] = Factura;
        fila[2] = String.valueOf(Monto_Compra);
        fila[3] = String.valueOf(Monto_Saldo);
        fila[4] = String.valueOf(Monto_Saldo);
        int Saldo = Monto_Saldo - Monto_Saldo;
        fila[5] = String.valueOf(Saldo);
        if (Saldo == 0) {
            fila[6] = "PAGO ABONADO";
        } else if (Saldo > 0) {
            fila[6] = "PAGO PARCIAL";
        } else if (Saldo == Monto_Saldo) {
            fila[6] = "PAGO PENDIENTE";
        }
        fila[7] = String.valueOf(Monto_Saldo);
        fila[8] = "-";
        fila[9] = "0";

        tb.addRow(fila);
    }

    public static int getExcetas() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras.tbDetalle.getModel().getValueAt(i, 8)));
        }
        return Redondeo.redondearI(total);
    }

    public static int get5() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras.tbDetalle.getModel().getValueAt(i, 10)));
        }
        return Redondeo.redondearI(total / 21);
    }

    public static int get10() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras.tbDetalle.getModel().getValueAt(i, 12)));
        }
        return Redondeo.redondearI(total / 11);
    }

    public static int getTotal() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras.tbDetalle.getModel().getValueAt(i, 15)));
        }
        return Redondeo.redondearI(total);
    }

    public static int getSaldoCompras() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgConsultarCompras.tbCompra.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgConsultarCompras.tbCompra.getValueAt(i, 14)));
        }
        //return Redondeo.redondearI(total);
        return total;
    }

    public static int getTotalPagos() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistrarPagosProveedor.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRegistrarPagosProveedor.tbDetalle.getModel().getValueAt(i, 4)));
        }
        //return Redondeo.redondearI(total);
        return total;
    }

    public static double CalcCostoIVA() {
        try {
            double iva = art.getIVA();
            double div = 0;
            switch ((int) iva) {
                case 5 ->
                    div = 21;
                case 10 ->
                    div = 11;
                default -> {
                }
            }
            if (iva != 0) {
                costoiva = Redondeo.redondearD((costo) / div);
            } else {
                costoiva = 0.0;
            }

        } catch (Exception e) {
            Mensajes.error("Error al calcular costo iva: " + e.getMessage());
        }
        return costoiva;
    }

    public static double CalcCostoIVAC(double iva) {
        try {
            double div = 0;
            switch ((int) iva) {
                case 5 ->
                    div = 21;
                case 10 ->
                    div = 11;
                default -> {
                }
            }
            if (iva != 0) {
                costoiva = Redondeo.redondearD((costo) / div);
            } else {
                costoiva = 0.0;
            }

        } catch (Exception e) {
            Mensajes.error("Error al calcular costo iva: " + e.getMessage());
        }
        return costoiva;
    }

    public static int CalcGanancia() {
        try {
            int pv, pc, G;
            pv = (art.getPrecioVenta());
            if (pv == 0) {
                ganancia = 0;
            } else {
                pc = (costo);
                G = (pv - pc) / (pc / 100);
                ganancia = Redondeo.redondearI(G);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Ganancia: " + e.getMessage());
        }
        return ganancia;
    }

    public static int CalcGananciaC(int pv) {
        try {
            int pc, G;
            if (pv == 0) {
                ganancia = 0;
            } else {
                pc = (costo);
                G = (pv - pc) / (pc / 100);
                ganancia = Redondeo.redondearI(G);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Ganancia: " + e.getMessage());
        }
        return ganancia;
    }

    public static int CalcDescuento() {
        try {
            int pv = art.getPrecioVenta();
            int pp = art.getPrecioPublico();
            if (pp == 0) {
                descuento = 0;
            } else {
                int dif = pp - pv;
                int desc = (dif * 100) / pp;
                descuento = Redondeo.redondearI(desc);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Descuento: " + e.getMessage());
        }
        return descuento;
    }

    public static int CalcDescuentoC(int pv, int pp) {
        try {
            if (pp == 0) {
                descuento = 0;
            } else {
                int dif = pp - pv;
                int desc = (dif * 100) / pp;
                descuento = Redondeo.redondearI(desc);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Descuento: " + e.getMessage());
        }
        return descuento;
    }

    public static void addTabla(JTable tabla) {
        try {
            int codA = art.getCodArticulo();
            String codB = art.getCodBarra();
            String desc = art.getDescripcion();
            double cant = Double.parseDouble(dlgCompras.txtCant.getText());
            costo = Integer.valueOf(dlgCompras.txtCostoL.getText());
            int mont = (int) (cant * costo);
            int iva = art.getIVA();
            double PCIVA = CalcCostoIVA();
            int Gan = CalcGanancia();
            int Des = CalcDescuento();
            DetalleCompra dco = new DetalleCompra(codA);
            if (array.busca(dco.getCodArticulo()) != -1) {
                Mensajes.informacion("EL ARTICULO YA FUE AGREGADO");
            } else {
                array.agregar(dco);
                insertar(String.valueOf(codA), codB, desc, String.valueOf(cant), String.valueOf(costo), String.valueOf(mont), iva, String.valueOf(PCIVA), String.valueOf(Gan), String.valueOf(Des), tabla);
                String total = String.valueOf(getTotal());
                String exentas = String.valueOf(getExcetas());
                String iva5 = String.valueOf(get5());
                String iva10 = String.valueOf(get10());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras.txtExentaL.setText(exentas);
                dlgCompras.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
                dlgCompras.txt5L.setText(iva5);
                dlgCompras.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
                dlgCompras.txt10L.setText(iva10);
                dlgCompras.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
                dlgCompras.txtTotalL.setText(total);
                dlgCompras.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void addTablaPagos(JTable tabla, int CodCompra, String Factura, int Monto_Compra, int Monto_Pago) {
        try {
            DetallePago dco = new DetallePago(CodCompra, Factura);
            if (arrayPago.busca(dco.getCodCompra()) != -1) {
                //Mensajes.Sistema("ANEXIÓN RESTRINGIDA:\nLa compra con la Factura Nro: "+dco.getFactura()+" ya fue agregada a la tabla de pagos.");
            } else {
                arrayPago.agregar(dco);
                insertarPagos(CodCompra, Factura, Monto_Compra, Monto_Pago, tabla);
                String total = String.valueOf(getTotalPagos());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgRegistrarPagosProveedor.txtTotal.setText("Gs." + df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void actMontoPago(JTable tabla) {
        try {
            int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
            int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 3).toString().replace(".", "").replace(",", ""));
            //int nuevo_monto = Mensajes.ingresar_Monto_a_abonar(monto_saldo);
            int nuevo_monto = Integer.parseInt(dlgRegistrarPagosProveedor.txtAbono.getText().trim().replace(".", "").replace(",", ""));
            int saldo = monto_saldo - nuevo_monto;
            String estado = null;
            if (saldo == 0) {
                estado = "PAGO ABONADO";
            } else if (saldo == monto_saldo) {
                estado = "PAGO PENDIENTE";
            } else if (saldo > 0 || saldo < monto_saldo) {
                estado = "PAGO PARCIAL";
            }
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(nuevo_monto, fila, 4);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(saldo, fila, 5);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(estado, fila, 6);

            String total = String.valueOf(getTotalPagos());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgRegistrarPagosProveedor.txtTotal.setText("Gs." + df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }
    
    public static void addNCPagoProveedor(JTable tabla) {
        try {
            int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
            int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 7).toString().replace(".", "").replace(",", ""));
            int monto_nc = Integer.parseInt(dlgRegistrarPagosProveedor.txtAbono1.getText().trim().replace(".", "").replace(",", ""));
            String NroNC= dlgRegistrarPagosProveedor.txtNC.getText().trim();
            //int monto_abonado = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 4).toString().replace(".", "").replace(",", ""));
            
            
            int nuevo_monto_saldo= monto_saldo - monto_nc;
            int saldo = nuevo_monto_saldo - nuevo_monto_saldo;
            String estado = null;
            if (saldo == 0) {
                estado = "PAGO ABONADO";
            } else if (saldo == monto_saldo) {
                estado = "PAGO PENDIENTE";
            } else if (saldo > 0 || saldo < monto_saldo) {
                estado = "PAGO PARCIAL";
            }
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(nuevo_monto_saldo, fila, 3);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(nuevo_monto_saldo, fila, 4);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(saldo, fila, 5);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(estado, fila, 6);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(NroNC, fila, 8);
            dlgRegistrarPagosProveedor.tbDetalle.setValueAt(monto_nc, fila, 9);
            

            String total = String.valueOf(getTotalPagos());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgRegistrarPagosProveedor.txtTotal.setText("Gs." + df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

    public static void consLinea() {
        int fila = dlgCompras.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 0).toString());
        int p = array.busca(cod);
        if (p == -1) {
            Mensajes.informacion("Artículo no existe");
        } else {
            dc = array.getFila(p);
            int codAr = dc.getCodArticulo();
            //int cant = dc.getCant();
            //int prec = dc.getPrecio();
            //int monto = dc.getMonto();
        }
    }

    public static void consLineaPagos() {
        int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 0).toString());
        int p = arrayPago.busca(cod);
        if (p == -1) {
            Mensajes.informacion("Factura no existe");
        } else {
            dcPago = arrayPago.getFila(p);
            int codPago = dcPago.getCodCompra();
            //int cant = dc.getCant();
            //int prec = dc.getPrecio();
            //int monto = dc.getMonto();
        }
    }

    public static void delRenglon(JTable tabla) {
        consLinea();
        int fila = dlgCompras.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 0).toString());
        int p = array.busca(cod);
        if (p != -1) {
            int res = Mensajes.confirmar("Desea quitar esta linea");
            if (res == 0) {
                array.eliminar(p);
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                tb.removeRow(fila);
                String total = String.valueOf(Redondeo.redondearI(getTotal()));
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras.txtTotalL.setText(total);
                dlgCompras.txtTotal.setText(df.format(Integer.valueOf(total.trim().replace(".", "").replace(",", ""))));
            }
        }
    }

    public static void delRenglonPagos(JTable tabla) {
        consLineaPagos();
        int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 0).toString());
        int p = arrayPago.busca(cod);
        if (p != -1) {
            int res = Mensajes.confirmar("Desea quitar esta linea");
            if (res == 0) {
                arrayPago.eliminar(p);
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                tb.removeRow(fila);
                String total = String.valueOf(getTotalPagos());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgRegistrarPagosProveedor.txtTotal.setText("Gs." + df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            }
        }
    }

    public static void canCelar() {
        array.vaciar();
    }

    /*    public static String addCompra() {
        String msg;
        int codc = Integer.parseInt(dlgCompras.txtCod.getText());
        int codProv = Integer.parseInt(dlgCompras.txtCodProv.getText());
        String condPago = (dlgCompras.lbCond.getText());
        String Fact= dlgCompras.txtFactura.getText();
        String fecha = dlgCompras.txtFecha.getText();
        int total = Integer.parseInt(dlgCompras.txtTotalL.getText());
        int exenta= Integer.parseInt(dlgCompras.txtExentaL.getText());
        int iva5= Integer.parseInt(dlgCompras.txt5L.getText());
        int iva10=Integer.parseInt(dlgCompras.txt10L.getText());

        Compra c = new Compra(codc, codProv,condPago,Fact, fecha, total, exenta, iva5, iva10);

        array.vaciar();
        msg = GestionarCompra.addCompra(c);

        if (msg == null) {
            Mensajes.informacion("Compra Realizada");
            controlCompra.addDetalleCompra();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }*/

 /*   public static String addDetalleCompra() {
        String msg = null;
        int fila = dlgCompras.tbDetalle.getRowCount();
        for (int i = 0; i < fila; i++) {
            int codCompra = Integer.valueOf(dlgCompras.txtCod.getText());
            int codArt = Integer.valueOf(dlgCompras.tbDetalle.getValueAt(i, 0).toString());
            int cantidad = Integer.valueOf(dlgCompras.tbDetalle.getValueAt(i, 4).toString());
            int precio = Integer.valueOf(dlgCompras.tbDetalle.getValueAt(i, 6).toString());
            int mont = Integer.valueOf(dlgCompras.tbDetalle.getValueAt(i, 14).toString());

            dc = new DetalleCompra(codCompra, codArt, cantidad, precio, mont);

            msg = GestionarCompra.addDetalleCompra(dc);
        }
        if (msg == null) {
            Mensajes.informacion("Detalle Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }*/

 /*  public static void finalizar(JTable tabla) {
        int fila = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int renglones = tb.getRowCount();
        for (int i = 0; i < renglones; i++) {
            fila++;
        }
        if (fila <= 0) {
            Mensajes.error("No ha ingresado artículos");
        } else {
            dlgFinCompra fc = new dlgFinCompra(null, true);
            fc.setLocationRelativeTo(null);
            int total = Redondeo.redondearI(getTotal());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgFinCompra.lblTotal.setText("₲ "+df.format(Integer.valueOf(String.valueOf(total).trim().replace(".", "").replace(",", ""))));
            //dlgFinCompra.lblTotal.setText(String.valueOf(total));
            fc.setVisible(true);
        }
    }*/

 /*------CONSULTADO LAS COMPRAS REALIZADAS*/
    public static void listarCompras(JTable tabla) {
        List lista;
        lista = GestionarCompra.listarCompras();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            //fila[10].toString();
            if (fila[11].toString().equals("S")) {
                fila[11] = "";
            } else {
                fila[11] = "ANULADO";
            }
            fila[12].toString();
            //fila[13].toString();
            switch (fila[13].toString()) {
                case "PE":
                    fila[13] = "PAGO PENDIENTE";
                    break;
                case "PA":
                    fila[13] = "PAGO PARCIAL";
                    break;
                case "AB":
                    fila[13] = "PAGO ABONADO";
                    break;
                case "AN":
                    fila[13] = "";
                    break;
                default:
                    break;
            }
            fila[14].toString();
            tb.addRow(fila);
        }
    }

    public static void listarPagosCompras(JTable tabla) {
        List lista;
        lista = GestionarCompra.listarPagosCompras();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2] = fila[3].toString() + " - " + fila[4].toString();
            fila[3] = fila[5].toString();
            fila[4] = Fecha.formatoFechaDinver(fila[6].toString());
            fila[5] = Fecha.FormatoHoraSinSSString(fila[7].toString());
            fila[6] = fila[8].toString();
            if (fila[9].toString().equals("S")) {
                fila[7] = "";
            } else if (fila[9].toString().equals("N")) {
                fila[7] = "ANULADO";
            }

            tb.addRow(fila);
        }
    }

    public static void listarPaposComprasFiltro(JTable tabla, int CodProveedor) {
        List lista;
        lista = GestionarCompra.listarPagosComprasFiltro(CodProveedor);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2] = fila[3].toString() + " - " + fila[4].toString();
            fila[3] = fila[5].toString();
            fila[4] = Fecha.formatoFechaDinver(fila[6].toString());
            fila[5] = Fecha.FormatoHoraSinSSString(fila[7].toString());
            fila[6] = fila[8].toString();
            if (fila[9].toString().equals("S")) {
                fila[7] = "";
            } else if (fila[9].toString().equals("N")) {
                fila[7] = "ANULADO";
            }
            tb.addRow(fila);
        }
    }

    public static void listarComprasFiltro(JTable tabla, int CodProveedor) {
        List lista;
        lista = GestionarCompra.listarComprasFiltro(CodProveedor);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            //fila[10].toString();
            if (fila[11].toString().equals("S")) {
                fila[11] = "";
            } else {
                fila[11] = "ANULADO";
            }
            fila[12].toString();
            //fila[13].toString();
            switch (fila[13].toString()) {
                case "PE":
                    fila[13] = "PAGO PENDIENTE";
                    break;
                case "PA":
                    fila[13] = "PAGO PARCIAL";
                    break;
                case "AB":
                    fila[13] = "PAGO ABONADO";
                    break;
                case "AN":
                    fila[13] = "";
                    break;
                default:
                    break;
            }
            fila[14].toString();
            tb.addRow(fila);
        }
    }

    public static void listarComprasPendientes(JTable tabla, int CodProveedor, String Desde, String Hasta) {
        List lista;
        lista = GestionarCompra.listarComprasPendientes(CodProveedor, Desde, Hasta);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2] = Fecha.formatoFechaDinver(fila[2].toString());
            fila[3] = Fecha.FormatoHoraSinSSString(fila[3].toString());
            fila[4].toString();
            fila[5].toString();
            if (fila[6].toString().equals("PE")) {
                fila[6] = "PAGO PENDIENTE";
            } else if (fila[6].toString().equals("PA")) {
                fila[6] = "PAGO PARCIAL";
            } else if (fila[6].toString().equals("AB")) {
                fila[6] = "PAGO ABONADO";
            }
            fila[6].toString();
            fila[7].toString();
            //fila[8].toString();
            tb.addRow(fila);
        }
    }

    public static int getTotalTF() {
        int total = 0;
        if (dlgBuscarFacturaCredito.Seleccionados(8)) {
            for (int i = 0; i < dlgBuscarFacturaCredito.tbCompraCredito.getRowCount(); i++) {
                boolean sel = (boolean) dlgBuscarFacturaCredito.tbCompraCredito.getValueAt(i, 8);
                if (sel) {
                    total += Integer.valueOf(String.valueOf(dlgBuscarFacturaCredito.tbCompraCredito.getModel().getValueAt(i, 7)));
                }
            }
        }
        return (total);
    }

    public static void listarDetalleCompras(JTable tabla) {
        int x = dlgConsultarCompras.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        if (dlgConsultarCompras.tbCompra.getValueAt(x, 2).toString().equals("L")) {
            dlgConsultarCompras.lbInformacion.setText("COMPRA PARA VENTA LOCAL");
        } else {
            dlgConsultarCompras.lbInformacion.setText("COMPRA PARA VENTA EN REPARTO");
        }
        String idcompra = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        String mov = dlgConsultarCompras.tbCompra.getValueAt(x, 1).toString();
        String fecha = Fecha.formatoFechaDinver(dlgConsultarCompras.tbCompra.getValueAt(x, 3).toString()) + " " + dlgConsultarCompras.tbCompra.getValueAt(x, 4).toString();
        String pro = dlgConsultarCompras.tbCompra.getValueAt(x, 5).toString() + " - " + dlgConsultarCompras.tbCompra.getValueAt(x, 6).toString();
        String codPro = dlgConsultarCompras.tbCompra.getValueAt(x, 9).toString();
        DecimalFormat df = new DecimalFormat("#,###");
        String total = dlgConsultarCompras.tbCompra.getValueAt(x, 10).toString();
        dlgConsultarCompras.txtCodCompra.setText(idcompra);
        dlgConsultarCompras.txtmov.setText(mov);
        dlgConsultarCompras.txtFechaCompra.setText(fecha);
        dlgConsultarCompras.txtProveedor.setText(pro);
        dlgConsultarCompras.txtTotal.setText(df.format(Integer.parseInt(total.trim().replace(".", "").replace(",", ""))));
        List lista;
        lista = GestionarCompra.listarDetalleCompras(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            tb.addRow(fila);
        }
    }

    public static void listarDetallePagosCompras(JTable tabla) {
        int x = dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow();
        String cod = dlgConsultarPagosProveedor.tbPagosProveedor.getValueAt(x, 0).toString();
        List lista;
        lista = GestionarCompra.listarDetallePagosCompras(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4] = Fecha.formatoFechaDinver(fila[4].toString());
            fila[5] = Fecha.FormatoHoraSinSSString(fila[5].toString());
            fila[6].toString();
            tb.addRow(fila);
        }
    }

    public static String anularCompra() {
        String msg;
        int x = dlgConsultarCompras.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarCompra.delCompra(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Compra Anulada");
            controlCompra.actStockEliminarCompra();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String anularPagosProveedor() {
        String msg;
        int x = dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow();
        String cod = dlgConsultarPagosProveedor.tbPagosProveedor.getValueAt(x, 0).toString();
        String usuario = Login.getUsuarioLogueado();
        msg = GestionarCompra.delPagosProveedor(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Pago a proveedor Anulado");
            controlCompra.actStockEliminarCompra();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String anularCompraReparto() {
        String msg;
        int x = dlgConsultarCompras.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarCompra.delCompra(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Compra Anulada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actStockEliminarCompra() {
        String msg = null;
        int f = dlgConsultarCompras.tbDetalleCompra.getRowCount();
        for (int i = 0; i < f; i++) {
            int coda = Integer.parseInt(dlgConsultarCompras.tbDetalleCompra.getValueAt(i, 1).toString());
            double st = Double.parseDouble(dlgConsultarCompras.tbDetalleCompra.getValueAt(i, 4).toString());
            ArticuloMovil a = new ArticuloMovil(coda, st);
            msg = GestionarArticulosMovil.actStockMENOS(a);
        }
        if (msg == null) {
            Mensajes.informacion("Stock Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    /*public static String actSaldoEstadoCompra() {
        String msg = null;
        int f = dlgConsultarPagosProveedor.tbDetallePagosProveedor.getRowCount();
        for (int i = 0; i < f; i++) {
            int codCompra = Integer.parseInt(dlgConsultarPagosProveedor.tbDetallePagosProveedor.getValueAt(i, 1).toString());
            int monto_abonado = Integer.parseInt(dlgConsultarPagosProveedor.tbDetallePagosProveedor.getValueAt(i, 7).toString());
            DetallePago a = new DetallePago(codCompra, monto_abonado,);
            msg = GestionarCompra.actCompra(a);
        }
        if (msg == null) {
            Mensajes.informacion("Stock Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
*/
    public static void actCantidad(JTable tabla) {
        try {
            int fila = dlgCompras.tbDetalle.getSelectedRow();
            int ca = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 4).toString());
            int pre = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 6).toString());
            String cant = String.valueOf(Mensajes.ingresarNumerosC(ca));
            String monto = String.valueOf(pre * Integer.parseInt(cant));
            int iva = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 7).toString());
            dlgCompras.tbDetalle.setValueAt(cant, fila, 3);
            dlgCompras.tbDetalle.setValueAt(cant, fila, 4);

            switch (iva) {
                case 10 -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 12);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 13);
                }
                case 5 -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 10);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 11);
                }
                default -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 8);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 9);
                }
            }
            dlgCompras.tbDetalle.setValueAt(monto, fila, 14);
            dlgCompras.tbDetalle.setValueAt(monto, fila, 15);
            String total = String.valueOf(getTotal());
            String exentas = String.valueOf(getExcetas());
            String iva5 = String.valueOf(get5());
            String iva10 = String.valueOf(get10());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgCompras.txtExentaL.setText(exentas);
            dlgCompras.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
            dlgCompras.txt5L.setText(iva5);
            dlgCompras.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
            dlgCompras.txt10L.setText(iva10);
            dlgCompras.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
            dlgCompras.txtTotalL.setText(total);
            dlgCompras.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

    public static void actPrecio(JTable tabla) {
        try {
            int fila = dlgCompras.tbDetalle.getSelectedRow();
            String cod = (dlgCompras.tbDetalle.getValueAt(fila, 0).toString());
            art = GestionarArticulos.busArticulo(cod);
            int cant = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 4).toString());
            int pre = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 6).toString());
            String precio = String.valueOf(Mensajes.ingresarPrecioC(pre));
            costo = Integer.valueOf(precio);
            int iva = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 7).toString());
            String PCIVA = String.valueOf(CalcCostoIVAC(iva));
            int pv = art.getPrecioVenta();
            int pp = art.getPrecioPublico();
            String Gan = String.valueOf(CalcGananciaC(pv));
            String Des = String.valueOf(CalcDescuentoC(pv, pp));
            String monto = String.valueOf(cant * Integer.parseInt(precio));

            dlgCompras.tbDetalle.setValueAt(precio, fila, 5);
            dlgCompras.tbDetalle.setValueAt(precio, fila, 6);

            switch (iva) {
                case 10 -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 12);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 13);
                }
                case 5 -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 10);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 11);
                }
                default -> {
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 8);
                    dlgCompras.tbDetalle.setValueAt(monto, fila, 9);
                }
            }
            dlgCompras.tbDetalle.setValueAt(monto, fila, 14);
            dlgCompras.tbDetalle.setValueAt(monto, fila, 15);
            dlgCompras.tbDetalle.setValueAt(PCIVA, fila, 16);
            dlgCompras.tbDetalle.setValueAt(Gan, fila, 17);
            dlgCompras.tbDetalle.setValueAt(Des, fila, 18);
            String total = String.valueOf(getTotal());
            String exentas = String.valueOf(getExcetas());
            String iva5 = String.valueOf(get5());
            String iva10 = String.valueOf(get10());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgCompras.txtExentaL.setText(exentas);
            dlgCompras.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
            dlgCompras.txt5L.setText(iva5);
            dlgCompras.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
            dlgCompras.txt10L.setText(iva10);
            dlgCompras.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
            dlgCompras.txtTotalL.setText(total);
            dlgCompras.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

}
