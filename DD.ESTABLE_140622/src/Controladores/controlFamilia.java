package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarFamilia;
import IU.dlgFamilia;
import Modelo.Familia;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlFamilia {
    static String UsuarioL="";
    public static String addFamilia() {
        String msg;
        int cod = Integer.parseInt(dlgFamilia.txtCod.getText().trim());
        String nombre = dlgFamilia.txtFamilia.getText().toUpperCase();
        Double ganacia = Double.parseDouble(dlgFamilia.txtGanancia.getText().trim());
        int iva=Integer.valueOf(dlgFamilia.lbIVA.getText().trim());
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Familia r = new Familia(cod, nombre, ganacia, iva, usuario);
        msg = GestionarFamilia.addFamilia(r);
        if (msg == null) {
            Mensajes.informacion("Familia Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actFamilia() {
        String msg;
        int cod = Integer.parseInt(dlgFamilia.txtCod.getText().trim());
        String nombre = dlgFamilia.txtFamilia.getText().toUpperCase();
        Double ganacia = Double.parseDouble(dlgFamilia.txtGanancia.getText().trim());
        int iva=Integer.valueOf(dlgFamilia.lbIVA.getText().trim());
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Familia r = new Familia(cod, nombre, ganacia, iva, usuario);
        msg = GestionarFamilia.actFamilia(r);
        if (msg == null) {
            Mensajes.informacion("Familia Actualizada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delFamilia() {
        String msg;
        String cod = dlgFamilia.txtCod.getText().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarFamilia.delFamilia(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Familia Eliminada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listFamilia(JTable tabla) {
        List lista = null;
        lista = GestionarFamilia.listFamilia();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            tb.addRow(fila);
        }
    }

}
