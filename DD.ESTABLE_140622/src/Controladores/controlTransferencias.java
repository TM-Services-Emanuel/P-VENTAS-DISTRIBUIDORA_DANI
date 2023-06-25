package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.ArregloTransferencia;
import Datos.GestionarArticulosMovil;
import Datos.GestionarTransferencia;
import IU.dlgConsultarTransferencia;
import IU.dlgTransferencias;
import Modelo.ArticuloMovil;
import Modelo.DetalleReparto;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlTransferencias {

    static ArticuloMovil art;
    static DetalleReparto dc;
    public static ArregloTransferencia array = new ArregloTransferencia();
    public static int precio_venta;
    public static int precio_costo;
    public static int ganancia;
    static String UsuarioL = "";
    
    
    public static void listTransferencias(JTable tabla, String cod, String fecha) {
        List lista;
        lista = GestionarTransferencia.listarTransferencias(cod , fecha);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void listDetalleTransferencias(JTable tabla) {
        int x = dlgTransferencias.tbDetalle.getSelectedRow();
        String cod = dlgTransferencias.tbDetalle.getValueAt(x, 0).toString();
        List lista;
        lista = GestionarTransferencia.listDetallesTransferencia(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    public static void listDetalleTransferencias2(JTable tabla) {
        int x = dlgConsultarTransferencia.tbTransferencia.getSelectedRow();
        String cod = dlgConsultarTransferencia.tbTransferencia.getValueAt(x, 0).toString();
        DecimalFormat df = new DecimalFormat("#,###");
        dlgConsultarTransferencia.lbValorTotal.setText(df.format(Integer.valueOf(dlgConsultarTransferencia.tbTransferencia.getValueAt(x, 8).toString())));
        List lista;
        lista = GestionarTransferencia.listDetallesTransferencia(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static String anularTransferenciaRR(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarTransferencia.delTransferencia(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("ANULACIÓN EXITOSA!\nLa Transferencia de Reparto a Reparto fue anulado satisfactoriamente");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String anularTransferenciaOrigenL(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarTransferencia.delTransferencia(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("ANULACIÓN EXITOSA!\nLa Transferencia de Salón de venta a Reparto fue anulado satisfactoriamente");
            controlTransferencias.actStockEliminarTransOrigenL();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actStockEliminarTransOrigenL() {
        String msg = null;
        int f = dlgTransferencias.tbDetalleTransf.getRowCount();
        for (int i = 0; i < f; i++) {
            int coda = Integer.parseInt(dlgTransferencias.tbDetalleTransf.getValueAt(i, 1).toString());
            double st = Double.parseDouble(dlgTransferencias.tbDetalleTransf.getValueAt(i, 4).toString());
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
    
    public static String anularTransferenciaDestinoL(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarTransferencia.delTransferencia(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("ANULACIÓN EXITOSA!\nLa Transferencia de Reparto a Salón de venta fue anulado satisfactoriamente");
            controlTransferencias.actStockEliminarTransDestinoL();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }    
    
    public static String actStockEliminarTransDestinoL() {
        String msg = null;
        int f = dlgTransferencias.tbDetalleTransf.getRowCount();
        for (int i = 0; i < f; i++) {
            int coda = Integer.parseInt(dlgTransferencias.tbDetalleTransf.getValueAt(i, 1).toString());
            double st = Double.parseDouble(dlgTransferencias.tbDetalleTransf.getValueAt(i, 4).toString());
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
    
    public static void listarTransferenciaReparto(JTable tabla, String caja, String IdMovil) {
        List lista;
        lista = GestionarTransferencia.listarTransferenciaReparto(caja, IdMovil);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static int getTotalTransferencia() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgConsultarTransferencia.tbDetalleTransferencia.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(dlgConsultarTransferencia.tbDetalleTransferencia.getValueAt(i, 6).toString().replace(".", "").replace(",", ""));
        }
        return (total);
    }
}
