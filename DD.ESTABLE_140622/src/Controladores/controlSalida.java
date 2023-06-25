package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Redondeo;
import Datos.ArregloDetalles;
import Datos.GestionarArticulosMovil;
import Datos.GestionarSalida;
import IU.dlgBuscarArticulo;
import IU.dlgConSalidas;
import IU.dlgSalidaMercaderia;
import static IU.dlgSalidaMercaderia.Renders;
import static IU.dlgSalidaMercaderia.txtCant;
import Modelo.ArticuloMovil;
import Modelo.DetalleSalida;
import Modelo.Salidas;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlSalida {

    static DetalleSalida ds;
    static ArticuloMovil art;
    public static ArregloDetalles array = new ArregloDetalles();
    static String UsuarioL = "";

    public static void selecProducto() {
        try {
            int x = dlgBuscarArticulo.tbDetalle.getSelectedRow();
            String cod = dlgBuscarArticulo.tbDetalle.getValueAt(x, 0).toString();
            art = GestionarArticulosMovil.busArticulo(cod);
            if (art.getStock() == 0.00) {
                Mensajes.informacion("Artículo con stock actual 0");
            } else {
                dlgSalidaMercaderia.lblCodArt.setText(String.valueOf(art.getIdproducto()));
                dlgSalidaMercaderia.txtArt.setText(art.getDescripcion());
                dlgSalidaMercaderia.txtCant.requestFocus();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*public static void addTabla(JTable tabla)
    {
        try {
            int codA = art.getCodArticulo();
            String desc = art.getDescripcion();
            int codM = Integer.parseInt(dlgSalidaMercaderia.lblMotivo.getText());
            String mot = dlgSalidaMercaderia.cbMotivo.getSelectedItem().toString();
            int can = Integer.parseInt(dlgSalidaMercaderia.txtCant.getText());
            int pre = art.getCosto();
            int mon = Redondeo.redondearI(can * pre);
            int sa = (int) stockActual();
            
            DetalleSalida dsa = new DetalleSalida(codA, codM, can, pre, mon);
            
                if(array.busca(dsa.getCodArt()) !=-1 )
                {
                    Mensajes.error("Articulo ya fue agregado");
                }
            else
            {
                if(dlgSalidaMercaderia.txtCant.getText().compareTo("0") != 0)
                {
                    if(can<art.getStock())
                    {
                        array.agregar(dsa);
                        insertar(String.valueOf(codA),desc ,String.valueOf(codM),mot ,String.valueOf(can), String.valueOf(pre), String.valueOf(mon), String.valueOf(sa), tabla);
                        double total = getTotal();
                        dlgSalidaMercaderia.txtTotal.setText(String.valueOf(total));
                    }
                    else
                    {
                        Mensajes.error("No tiene stock sufisiente");
                    }
                }
                else
                {
                    Mensajes.error("Ingrese una cantidad");
                }
            
            }
        } catch (Exception e) {
            Mensajes.error("Eliga al menos un Artículo");
        }
    }*/

    public static void addTabla(JTable tabla) {
        try {
            int codA = art.getIdproducto();
            String desc = art.getDescripcion();
            int codM = Integer.parseInt(dlgSalidaMercaderia.lblMotivo.getText());
            String mot = dlgSalidaMercaderia.cbMotivo.getSelectedItem().toString();
            float can = Float.parseFloat(dlgSalidaMercaderia.txtCant.getText());
            int pre = art.getPrecio_costo();
            int mon = ((int) (can * pre));

            DetalleSalida dsa = new DetalleSalida(codA, codM, pre, can, mon);
            if (array.busca(dsa.getCodArt()) != -1) {
                int Nfila = array.busca(dsa.getCodArt());
                double cantTabla = Double.parseDouble(dlgSalidaMercaderia.tbDetalle.getValueAt(Nfila, 4).toString());
                if((cantTabla+can)> art.getStock()){
                    Mensajes.informacion("Stock insuficiente");
                    double sobra=art.getStock()-cantTabla;
                    txtCant.setText(String.valueOf(sobra));
                    txtCant.requestFocus();
                }else{
                    addmismoItem(Nfila, cantTabla, can, pre);
                    Renders();
                }
                
            } else {

                array.agregar(dsa);
                insertar(String.valueOf(codA), desc, String.valueOf(codM), mot, String.valueOf(can), String.valueOf(pre), String.valueOf(mon), tabla);
                int total = getTotal();
                dlgSalidaMercaderia.txtTotalL.setText(String.valueOf(total));
                if (dlgSalidaMercaderia.txtTotalL.getText().trim().length() >= 1) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    dlgSalidaMercaderia.txtTotal.setText(df.format(Integer.valueOf(dlgSalidaMercaderia.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));
                    Renders();
                }
            }

        } catch (Exception e) {
            Mensajes.error("Eliga al menos un Artículo");
        }
    }

    public static void addmismoItem(int fila, double cantTabla, double cantAdd, int costo) {
        try {
            double cantFinal = (cantTabla + cantAdd);
            int montoAct = (int) (costo * cantFinal);
            dlgSalidaMercaderia.tbDetalle.setValueAt(String.valueOf(cantFinal), fila, 4);
            dlgSalidaMercaderia.tbDetalle.setValueAt(String.valueOf(costo), fila, 5);
            dlgSalidaMercaderia.tbDetalle.setValueAt(String.valueOf(montoAct), fila, 6);
            int total = getTotal();
            dlgSalidaMercaderia.txtTotalL.setText(String.valueOf(total));
            if (dlgSalidaMercaderia.txtTotalL.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                dlgSalidaMercaderia.txtTotal.setText(df.format(Integer.valueOf(dlgSalidaMercaderia.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));
            }
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

    public static void delRenglon(JTable tabla) {
        consLinea();
        int f = dlgSalidaMercaderia.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgSalidaMercaderia.tbDetalle.getValueAt(f, 0).toString());
        int p = array.busca(cod);
        if (p != -1) {
            int res = Mensajes.confirmar("Desea quitar esta linea");
            if (res == 0) {
                array.eliminar(p);
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                tb.removeRow(f);
                int total = getTotal();
                //dlgSalidaMercaderia.txtTotal.setText(String.valueOf(total));
                dlgSalidaMercaderia.txtTotalL.setText(String.valueOf(total));

                if (dlgSalidaMercaderia.txtTotalL.getText().trim().length() >= 1) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    dlgSalidaMercaderia.txtTotal.setText(df.format(Integer.valueOf(dlgSalidaMercaderia.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));

                }
            }
        }
    }

    public static void actCantidad(JTable tabla) {
        try {
            int fila = dlgSalidaMercaderia.tbDetalle.getSelectedRow();
            String cod = dlgSalidaMercaderia.tbDetalle.getValueAt(fila, 0).toString();
            art = GestionarArticulosMovil.busArticulo(cod);
            int pre = Integer.valueOf(dlgSalidaMercaderia.tbDetalle.getValueAt(fila, 5).toString());
            float can = Mensajes.ingresarNumeros();
            int monto = Redondeo.redondearI((int) (pre * can));
            double st = (art.getStock() - can);
            dlgSalidaMercaderia.tbDetalle.setValueAt(can, fila, 4);
            dlgSalidaMercaderia.tbDetalle.setValueAt(monto, fila, 6);
            dlgSalidaMercaderia.tbDetalle.setValueAt(st, fila, 7);
            int total = getTotal();

            dlgSalidaMercaderia.txtTotalL.setText(String.valueOf(total));
            if (dlgSalidaMercaderia.txtTotalL.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                dlgSalidaMercaderia.txtTotal.setText(df.format(Integer.valueOf(dlgSalidaMercaderia.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));
            }
            //dlgSalidaMercaderia.txtTotal.setText(String.valueOf(total));
            //dlgSalidaMercaderia.txtTotalL.setText(String.valueOf(total));
            dlgSalidaMercaderia.txtArt.setText("");
            dlgSalidaMercaderia.txtCant.setText("");
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }

    public static void consLinea() {
        int f = dlgSalidaMercaderia.tbDetalle.getSelectedRow();
        int cod = Integer.parseInt(dlgSalidaMercaderia.tbDetalle.getValueAt(f, 0).toString());
        int p = array.busca(cod);
        if (p == -1) {
            Mensajes.informacion("Artículo no existe");
        } else {
            ds = array.getFila(p);
            int codA = ds.getCodArt();
            int codM = ds.getCodM();
            double cant = ds.getCant();
            int pre = ds.getPrec();
            int monto = ds.getMonto();
        }

    }

    static void insertar(String codA, String desc, String codM, String mont, String cand, String prec, String tot, JTable tabla) {
        Object[] fila = {codA, desc, codM, mont, cand, prec, tot};
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        tb.addRow(fila);
    }

    public static int getTotal() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgSalidaMercaderia.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgSalidaMercaderia.tbDetalle.getModel().getValueAt(i, 6)));
        }
        return (total);
    }

    public static double stockActual() {
        double cant = Double.parseDouble(dlgSalidaMercaderia.txtCant.getText());
        return (double) (art.getStock() - cant);
    }

    public static String actStock() {
        String msg = null;
        int f = dlgSalidaMercaderia.tbDetalle.getRowCount();
        for (int i = 0; i < f; i++) {
            int coda = Integer.parseInt(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 0).toString());
            double st = Double.parseDouble(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 4).toString());
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

    public static String actStockEliminarSalida() {
        String msg = null;
        int f = dlgConSalidas.tbDetalleSalida.getRowCount();
        for (int i = 0; i < f; i++) {
            int coda = Integer.parseInt(dlgConSalidas.tbDetalleSalida.getValueAt(i, 0).toString());
            double st = Double.parseDouble(dlgConSalidas.tbDetalleSalida.getValueAt(i, 2).toString());
            ArticuloMovil a = new ArticuloMovil(coda, st);
            msg = GestionarArticulosMovil.actStockMAS(a);
        }
        if (msg == null) {
            Mensajes.informacion("Stock Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void canCelar() {
        array.vaciar();
    }

    public static String addSalida() {
        String msg;
        int codS = Integer.valueOf(dlgSalidaMercaderia.txtCod.getText());
        int codP = Integer.valueOf(dlgSalidaMercaderia.txtProveedor.getText());
        String fecha = dlgSalidaMercaderia.txtFechaR.getText();
        int total = Integer.valueOf(dlgSalidaMercaderia.txtTotalL.getText());
        String Obs = dlgSalidaMercaderia.txtObs.getText().toUpperCase();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        Salidas salida = new Salidas(codS, codP, fecha, total, Obs, usuario);
        array.vaciar();
        msg = GestionarSalida.addSalida(salida);

        if (msg == null) {
            Mensajes.informacion("Salida Registrada");
            controlSalida.addDetalleSalida();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actSalida() {
        String msg;
        int x = dlgConSalidas.tbSalida.getSelectedRow();
        String cod = dlgConSalidas.tbSalida.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarSalida.actSalida(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Salida Eliminada");
            controlSalida.actStockEliminarSalida();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String addDetalleSalida() {
        String msg = null;
        int f = dlgSalidaMercaderia.tbDetalle.getRowCount();
        for (int i = 0; i < f; i++) {
            int codA = Integer.valueOf(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 0).toString());
            int codS = Integer.valueOf(dlgSalidaMercaderia.txtCod.getText());
            int codM = Integer.valueOf(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 2).toString());
            double cant = Double.parseDouble(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 4).toString());
            int prec = Integer.valueOf(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 5).toString());
            int impo = Integer.valueOf(dlgSalidaMercaderia.tbDetalle.getValueAt(i, 6).toString());

            ds = new DetalleSalida(codA, codS, codM, cant, prec, impo);

            msg = GestionarSalida.addDetalleSalida(ds);
        }
        if (msg == null) {
            Mensajes.informacion("Detalle Registrado");
            controlSalida.actStock();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listSalidas(JTable tabla) {
        List lista = null;
        lista = GestionarSalida.listSalidas();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

    public static void listDetalle(JTable tabla) {
        int x = dlgConSalidas.tbSalida.getSelectedRow();
        String cod = dlgConSalidas.tbSalida.getValueAt(x, 0).toString();
        List lista = null;
        lista = GestionarSalida.listDetalle(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

}
