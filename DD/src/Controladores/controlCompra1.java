package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Redondeo;
import Datos.ArregloCompras;
import Datos.GestionarArticulos;
import Datos.GestionarArticulosMovil;
import Datos.GestionarCompra;
import Datos.GestionarProveedor;
import IU.dlgBuscarArticuloCompra1;
import IU.dlgBuscarProveedor;
import IU.dlgBuscarProveedor1;
import IU.dlgCompras1;
import IU.dlgConsultarCompras;
import IU.dlgConsultarCompras1;
import IU.dlgReporteCompraporCliente;
import IU.dlgSalidaMercaderia;
import Modelo.Articulo;
import Modelo.ArticuloMovil;
import Modelo.DetalleCompra;
import Modelo.Proveedor;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlCompra1 {

    static Proveedor prov;
    static ArticuloMovil art;
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
        dlgCompras1.txtCodProv.setText(String.valueOf(prov.getCodP()));
        dlgCompras1.txtRazonS.setText(prov.getRazoS());
        dlgCompras1.txtRuc.setText(prov.getRuc());
    }
    
    public static void selectProveedor1() {
        int x = dlgBuscarProveedor1.tbDetalle.getSelectedRow();
        String cod = dlgBuscarProveedor1.tbDetalle.getValueAt(x, 0).toString();
        prov = GestionarProveedor.busProveedor(cod);
        dlgReporteCompraporCliente.txtCod.setText(String.valueOf(prov.getCodP()));
        dlgReporteCompraporCliente.txtDescripcion.setText(prov.getRazoS());
        dlgReporteCompraporCliente.txtRuc.setText(prov.getRuc());
    }
    public static void selectArticulo() {
        int x = dlgBuscarArticuloCompra1.tbDetalle.getSelectedRow();
        String cod = dlgBuscarArticuloCompra1.tbDetalle.getValueAt(x, 0).toString();
        art = GestionarArticulosMovil.busArticulo(cod);
        dlgCompras1.txtCodA.setText(String.valueOf(art.getIdproducto()));
        dlgCompras1.txtArt.setText(art.getDescripcion());
        //dlgCompras1.txtCant.setText("1");
        String PC= String.valueOf(art.getPrecio_costo());
        DecimalFormat df = new DecimalFormat("#,###");
        dlgCompras1.txtCosto.setText((df.format(Integer.parseInt(PC.trim().replace(".", "").replace(",", "")))));
        dlgCompras1.txtCostoL.setText(PC);
        
    }
      
        
    public static int getExcetas() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras1.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras1.tbDetalle.getModel().getValueAt(i, 9)));
        }
        return (total);
    }
    
    public static int get5() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras1.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras1.tbDetalle.getModel().getValueAt(i, 11)));
        }
        return Redondeo.redondearI(total/21);
    }
    
    public static int get10() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras1.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras1.tbDetalle.getModel().getValueAt(i, 13)));
        }
        return Redondeo.redondearI(total/11);
    }
    
    public static int getTotal() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgCompras1.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgCompras1.tbDetalle.getModel().getValueAt(i, 15)));
        }
        return (total);
    }

    public static int CalcGanancia() {
        try {
            int pv,pc,G;
            pv = (art.getPrecio_venta());
            if (pv == 0) {
                ganancia =0;
            } else {
                pc=(costo);
                G=(pv - pc);
                ganancia = (G);
            }
        } catch (Exception e) {
            Mensajes.error("Error al calcular Ganancia: " + e.getMessage());
        }
        return ganancia;
    }
    public static void insertar(String cod, String codI, String desc, String cant, String prec, String total, int iva, String Gan, JTable tabla) {

        String fila[] = new String[19];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
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
        fila[16] = Gan;
        tb.addRow(fila);
    }

    public static void addTabla(JTable tabla) {
        try {
            int codA = art.getIdproducto();
            String codI = art.getCodinterno();
            String desc = art.getDescripcion();
            double cant = Double.parseDouble(dlgCompras1.txtCant.getText());
            costo = Integer.valueOf(dlgCompras1.txtCostoL.getText());
            int mont = (int) (cant * costo);
            int iva = 0;
            switch (art.getIva()) {
                case 1 -> iva=0;
                case 2 -> iva=5;
                case 3 -> iva=10;
                default -> {
                }
            }
            int Gan = CalcGanancia();
            DetalleCompra dco = new DetalleCompra(codA);
            if (array.busca(dco.getCodArticulo()) != -1) {
                // Mensajes.informacion("EL ARTICULO YA FUE AGREGADO");
                // System.out.println("BUSCAR ARTICULO: "+array.busca(dco.getCodArticulo()));
                int Nfila=array.busca(dco.getCodArticulo());
                double cantTabla = Double.parseDouble(dlgCompras1.tbDetalle.getValueAt(Nfila, 4).toString());
                System.out.println("fila: "+Nfila+" ** cantidad en tabla: "+ cantTabla+" ** cantidad a agregar: "+cant+" ** iva: "+iva);
                addmismoItem( Nfila, cantTabla, cant, costo,iva);
                                
            } else {
                array.agregar(dco);
                insertar(String.valueOf(codA), codI, desc, String.valueOf(cant), String.valueOf(costo), String.valueOf(mont), iva, String.valueOf(Gan), tabla);
                String total = String.valueOf(getTotal());
                String exentas = String.valueOf(getExcetas());
                String iva5 = String.valueOf(get5());
                String iva10 = String.valueOf(get10());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras1.txtExentaL.setText(exentas);
                dlgCompras1.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
                dlgCompras1.txt5L.setText(iva5);
                dlgCompras1.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
                dlgCompras1.txt10L.setText(iva10);
                dlgCompras1.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
                dlgCompras1.txtTotalL.setText(total);
                dlgCompras1.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void consLinea() {
        int fila = dlgCompras1.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgCompras1.tbDetalle.getValueAt(fila, 0).toString());
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
        int fila = dlgCompras1.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgCompras1.tbDetalle.getValueAt(fila, 0).toString());
        int p = array.busca(cod);
        if (p != -1) {
            int res = Mensajes.confirmar("Desea quitar esta linea");
            if (res == 0) {
                array.eliminar(p);
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                tb.removeRow(fila);
                String total = String.valueOf((getTotal()));
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras1.txtTotalL.setText(total);
                dlgCompras1.txtTotal.setText(df.format(Integer.valueOf(total.trim().replace(".", "").replace(",", ""))));            
            }
        }
    }

    public static void canCelar() {
        array.vaciar();
    }

/*    public static String addCompra() {
        String msg;
        int codc = Integer.parseInt(dlgCompras1.txtCod.getText());
        int codProv = Integer.parseInt(dlgCompras1.txtCodProv.getText());
        String condPago = (dlgCompras1.lbCond.getText());
        String Fact= dlgCompras1.txtFactura.getText();
        String fecha = dlgCompras1.txtFecha.getText();
        int total = Integer.parseInt(dlgCompras1.txtTotalL.getText());
        int exenta= Integer.parseInt(dlgCompras1.txtExentaL.getText());
        int iva5= Integer.parseInt(dlgCompras1.txt5L.getText());
        int iva10=Integer.parseInt(dlgCompras1.txt10L.getText());

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
        int fila = dlgCompras1.tbDetalle.getRowCount();
        for (int i = 0; i < fila; i++) {
            int codCompra = Integer.valueOf(dlgCompras1.txtCod.getText());
            int codArt = Integer.valueOf(dlgCompras1.tbDetalle.getValueAt(i, 0).toString());
            int cantidad = Integer.valueOf(dlgCompras1.tbDetalle.getValueAt(i, 4).toString());
            int precio = Integer.valueOf(dlgCompras1.tbDetalle.getValueAt(i, 6).toString());
            int mont = Integer.valueOf(dlgCompras1.tbDetalle.getValueAt(i, 14).toString());

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
        DefaultTableModel tb = (DefaultTableModel) dlgCompras1.tbDetalle.getModel();
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
            //fila[10].toString();
            if(fila[10].toString().equals("S")){
                fila[10]="ACTIVO";
            }else{
                fila[10]="ANULADO";
            }
            tb.addRow(fila);
        }
    }
    
        
    public static void listarComprasReparto(JTable tabla, String caja) {
        List lista;
        lista = GestionarCompra.listarComprasReparto(caja);
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
        String fac = dlgConsultarCompras.tbCompra.getValueAt(x, 7).toString();
        String fecha = dlgConsultarCompras.tbCompra.getValueAt(x, 2).toString()+" "+dlgConsultarCompras.tbCompra.getValueAt(x, 3).toString();
        String pro = dlgConsultarCompras.tbCompra.getValueAt(x, 4).toString()+" - "+dlgConsultarCompras.tbCompra.getValueAt(x, 5).toString();
        String codPro = dlgConsultarCompras.tbCompra.getValueAt(x, 8).toString();
        DecimalFormat df = new DecimalFormat("#,###");
        String total = dlgConsultarCompras.tbCompra.getValueAt(x, 9).toString();
        dlgConsultarCompras.txtCodCompra.setText(fac);
        dlgConsultarCompras.txtFechaCompra.setText(fecha);
        dlgConsultarCompras.txtProveedor.setText(pro);
        dlgConsultarCompras.txtTotal.setText("Gs. "+df.format(Integer.parseInt(total.trim().replace(".", "").replace(",", ""))));
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

        
    public static void listarDetalleComprasReparto(JTable tabla) {
        int x = dlgConsultarCompras1.tbCompra.getSelectedRow();
        String cod = dlgConsultarCompras1.tbCompra.getValueAt(x, 0).toString();
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
            fila[7].toString();
            fila[8].toString();
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
            controlCompra1.actStockEliminarCompra();
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
            int st = Integer.valueOf(dlgConsultarCompras.tbDetalleCompra.getValueAt(i, 4).toString());
            Articulo a = new Articulo(coda, st);
            msg = GestionarArticulos.actStockMENOS(a);
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
            int fila = tabla.getSelectedRow();
            double ca = Double.parseDouble(tabla.getValueAt(fila, 4).toString());
            int pre = Integer.parseInt(tabla.getValueAt(fila, 6).toString());
            double cant = (Mensajes.ingresarNumerosC(ca));
            int monto = (int) (pre*cant);
            int iva = Integer.parseInt(tabla.getValueAt(fila, 7).toString());
            tabla.setValueAt(String.valueOf(cant), fila, 3);
            tabla.setValueAt(String.valueOf(cant), fila, 4);
            
            switch (iva) {
                case 10 -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 12);
                    tabla.setValueAt(String.valueOf(monto), fila, 13);
                }
                case 5 -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 10);
                    tabla.setValueAt(String.valueOf(monto), fila, 11);
                }
                default -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 8);
                    tabla.setValueAt(String.valueOf(monto), fila, 9);
                }
            }
            tabla.setValueAt(String.valueOf(monto), fila, 14);
            tabla.setValueAt(String.valueOf(monto), fila, 15);
            
            //int Gan = CalcGanancia();
            //dlgCompras1.tbDetalle.setValueAt(String.valueOf(Gan), fila, 16);
                String total = String.valueOf(getTotal());
                String exentas = String.valueOf(getExcetas());
                String iva5 = String.valueOf(get5());
                String iva10 = String.valueOf(get10());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras1.txtExentaL.setText(exentas);
                dlgCompras1.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
                dlgCompras1.txt5L.setText(iva5);
                dlgCompras1.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
                dlgCompras1.txt10L.setText(iva10);
                dlgCompras1.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
                dlgCompras1.txtTotalL.setText(total);
                dlgCompras1.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (NumberFormatException e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }
    
    public static void addmismoItem(int fila, double cantTabla, double cantAdd, int costo, int iva) {
        try {
            double cantFinal=(cantTabla+cantAdd);
            int montoAct = (int) (costo * cantFinal);
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(cantFinal), fila, 3);
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(cantFinal), fila, 4);
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(costo), fila, 5);
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(costo), fila, 6);
            switch (iva) {
                case 10 -> {
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 12);
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 13);
                }
                case 5 -> {
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 10);
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 11);
                }
                default -> {
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 8);
                    dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 9);
                }
            }
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 14);
            dlgCompras1.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 15);
            String total = String.valueOf(getTotal());
                String exentas = String.valueOf(getExcetas());
                String iva5 = String.valueOf(get5());
                String iva10 = String.valueOf(get10());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras1.txtExentaL.setText(exentas);
                dlgCompras1.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
                dlgCompras1.txt5L.setText(iva5);
                dlgCompras1.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
                dlgCompras1.txt10L.setText(iva10);
                dlgCompras1.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
                dlgCompras1.txtTotalL.setText(total);
                dlgCompras1.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (NumberFormatException e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }
    public static void actPrecio(JTable tabla) {
        try {
            int fila = tabla.getSelectedRow();
            String cod = (tabla.getValueAt(fila, 0).toString());
            art = GestionarArticulosMovil.busArticulo(cod);
            double cant = Double.valueOf(tabla.getValueAt(fila, 4).toString());
            int pre = Integer.parseInt(tabla.getValueAt(fila, 6).toString());
            int precio = (Mensajes.ingresarPrecioC(pre));
            costo = (precio);
            int iva = Integer.parseInt(tabla.getValueAt(fila, 7).toString());
            int pv=art.getPrecio_venta();
            String Gan = String.valueOf(CalcGanancia());
            int monto = (int) (cant*(precio));
            
            tabla.setValueAt(String.valueOf(precio), fila, 5);
            tabla.setValueAt(String.valueOf(precio), fila, 6);
            
            switch (iva) {
                case 10 -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 12);
                    tabla.setValueAt(String.valueOf(monto), fila, 13);
                }
                case 5 -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 10);
                    tabla.setValueAt(String.valueOf(monto), fila, 11);
                }
                default -> {
                    tabla.setValueAt(String.valueOf(monto), fila, 8);
                    tabla.setValueAt(String.valueOf(monto), fila, 9);
                }
            }
            tabla.setValueAt(String.valueOf(monto), fila, 14);
            tabla.setValueAt(String.valueOf(monto), fila, 15);
            tabla.setValueAt(Gan, fila, 16);
            String total = String.valueOf(getTotal());
                String exentas = String.valueOf(getExcetas());
                String iva5 = String.valueOf(get5());
                String iva10 = String.valueOf(get10());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgCompras1.txtExentaL.setText(exentas);
                dlgCompras1.txtExenta.setText(df.format(Integer.parseInt(exentas.trim().replace(".", "").replace(",", ""))));
                dlgCompras1.txt5L.setText(iva5);
                dlgCompras1.txt5.setText(df.format(Integer.parseInt(iva5.replace(".", "").replace(",", ""))));
                dlgCompras1.txt10L.setText(iva10);
                dlgCompras1.txt10.setText(df.format(Integer.parseInt(iva10.replace(".", "").replace(",", ""))));
                dlgCompras1.txtTotalL.setText(total);
                dlgCompras1.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
        } catch (NumberFormatException e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

}
