package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarDetalleIngreso;
import IU.dlgDetalleIngreso;
import Modelo.DetalleIngreso;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControlDetalleIngreso {
    static String UsuarioL="";

    public static String addDetalleIngreso() {
        String msg;
        //int diCodigo = Integer.parseInt(GestionarDetalleIngreso.getCodigo());
        int diCodigo = Integer.parseInt(dlgDetalleIngreso.txtCod.getText().trim());
        String diDescripcion = dlgDetalleIngreso.txtDetalle.getText().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        DetalleIngreso di = new DetalleIngreso(diCodigo, diDescripcion,usuario);
        msg = GestionarDetalleIngreso.addDetalleIngreso(di);
        if (msg == null) {
            Mensajes.informacion("Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actDetalleIngreso() {
        String msg;
        int diCodigo = Integer.parseInt(dlgDetalleIngreso.txtCod.getText().trim());
        String diDescripcion = dlgDetalleIngreso.txtDetalle.getText().toUpperCase().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        DetalleIngreso di = new DetalleIngreso(diCodigo, diDescripcion, usuario);
        msg = GestionarDetalleIngreso.actDetalleIngreso(di);
        if (msg == null) {
            Mensajes.informacion("Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delDetalleIngreso() {
        String msg;
        String cod = dlgDetalleIngreso.txtCod.getText().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarDetalleIngreso.delDetalleIngreso(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Eliminado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listDetalleGasto(JTable tabla) {
        List lista = null;
        lista = GestionarDetalleIngreso.listDetalleIngreso();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            tb.addRow(fila);
        }
    }

}
