package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Redondeo;
import Datos.ArregloCompras;
import Datos.GestionarArticulos;
import Datos.GestionarArticulosMovil;
import Datos.GestionarCompra;
import Datos.GestionarProveedor;
import IU.dlgBuscarArticuloCompra;
import IU.dlgBuscarProveedor;
import IU.dlgCompras;
import IU.dlgConsultarCompras;
import IU.dlgConsultarCompras1;
import Modelo.Articulo;
import Modelo.ArticuloMovil;
import Modelo.DetalleCompra;
import Modelo.Proveedor;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlCompra {

    static Proveedor prov;
    static Articulo art;
    static DetalleCompra dc;
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
        String PC= String.valueOf(art.getCosto());
        DecimalFormat df = new DecimalFormat("#,###");
        dlgCompras.txtCosto.setText((df.format(Integer.parseInt(PC.trim().replace(".", "").replace(",", "")))));
        dlgCompras.txtCostoL.setText(PC);
    }
    public static void insertar(String cod, String codB, String desc, String cant, String prec, String total, int iva,String PCosto, String Gan, String Des, JTable tabla) {

        String fila[] = new String[19];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codB;
        fila[2] = desc;
        fila[3] = cant;
        fila[4] = cant;
        fila[5] = prec;
        fila[6]=prec;
        fila[7]=String.valueOf(iva);
        switch (iva) {
            case 10 -> {
                fila[8] = "0";
                fila[9] = "0";
                fila[10] = "0";
                fila[11]= "0";
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
        return Redondeo.redondearI(total/21);
    }
    
    public static int get10() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras.tbDetalle.getModel().getValueAt(i, 12)));
        }
        return Redondeo.redondearI(total/11);
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
    public static double CalcCostoIVA() {
        try {
            double iva = art.getIVA();
            double div = 0;
            switch ((int) iva) {
                case 5 -> div = 21;
                case 10 -> div = 11;
                default -> {
                }
            }
            if(iva !=0){
                costoiva = Redondeo.redondearD((costo) / div);
            }else{
                costoiva=0.0;
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
                case 5 -> div = 21;
                case 10 -> div = 11;
                default -> {
                }
            }
            if(iva !=0){
                costoiva = Redondeo.redondearD((costo) / div);
            }else{
                costoiva=0.0;
            }
            
        } catch (Exception e) {
            Mensajes.error("Error al calcular costo iva: " + e.getMessage());
        }
        return costoiva;
    }
    public static int CalcGanancia() {
        try {
            int pv,pc,G;
            pv = (art.getPrecioVenta());
            if (pv == 0) {
                ganancia =0;
            } else {
                pc=(costo);
                G=(pv - pc)/(pc / 100);
                ganancia = Redondeo.redondearI(G);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Ganancia: " + e.getMessage());
        }
        return ganancia;
    }
    public static int CalcGananciaC(int pv) {
        try {
            int pc,G;
            if (pv == 0) {
                ganancia =0;
            } else {
                pc=(costo);
                G=(pv - pc)/(pc / 100);
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
            if (pp==0) {
                descuento = 0;
            } else {
                int dif=pp-pv;
                int desc=(dif*100)/pp;
                descuento = Redondeo.redondearI(desc);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Descuento: " + e.getMessage());
        }
        return descuento;
    }
    public static int CalcDescuentoC(int pv, int pp) {
        try {
            if (pp==0) {
                descuento = 0;
            } else {
                int dif=pp-pv;
                int desc=(dif*100)/pp;
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
            if(fila[11].toString().equals("S")){
                fila[11]="ACTIVO";
            }else{
                fila[11]="ANULADO";
            }
            fila[12].toString();
            tb.addRow(fila);
        }
    }

    public static void listarDetalleCompras(JTable tabla) {
        int x = dlgConsultarCompras.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        if(dlgConsultarCompras.tbCompra.getValueAt(x, 2).toString().equals("L")){
            dlgConsultarCompras.lbInformacion.setText("COMPRA PARA VENTA LOCAL");
        }else{
            dlgConsultarCompras.lbInformacion.setText("COMPRA PARA VENTA EN REPARTO");
        }
        String idcompra = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        String mov = dlgConsultarCompras.tbCompra.getValueAt(x, 1).toString();
        String fecha = dlgConsultarCompras.tbCompra.getValueAt(x, 3).toString()+" "+dlgConsultarCompras.tbCompra.getValueAt(x, 4).toString();
        String pro = dlgConsultarCompras.tbCompra.getValueAt(x, 5).toString()+" - "+dlgConsultarCompras.tbCompra.getValueAt(x, 6).toString();
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

    public static String anularCompra() {
        String msg;
        int x = dlgConsultarCompras.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras.tbCompra.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarCompra.delCompra(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("Compra Anulada");
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
        msg = GestionarCompra.delCompra(cod,usuario);
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
    
    public static void actCantidad(JTable tabla) {
        try {
            int fila = dlgCompras.tbDetalle.getSelectedRow();
            int ca = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 4).toString());
            int pre = Integer.parseInt(dlgCompras.tbDetalle.getValueAt(fila, 6).toString());
            String cant = String.valueOf(Mensajes.ingresarNumerosC(ca));
            String monto = String.valueOf(pre*Integer.parseInt(cant));
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
            int pv=art.getPrecioVenta();
            int pp=art.getPrecioPublico();
            String Gan = String.valueOf(CalcGananciaC(pv));
            String Des = String.valueOf(CalcDescuentoC(pv,pp));
            String monto = String.valueOf(cant*Integer.parseInt(precio));
            
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
