package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarMotivo;
import IU.dlgMotivo;
import Modelo.Motivo;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlMotivo {
    static String UsuarioL="";
    public static String addMotivo()
    {
        String msg;
        int cod = Integer.parseInt(dlgMotivo.txtCod.getText());
        String mot = dlgMotivo.txtMotivo.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Motivo m = new Motivo(cod, mot, usuario);
        msg = GestionarMotivo.addMotivo(m);
        if(msg==null)
        {
            Mensajes.informacion("Motivo Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actMotivo()
    {
        String msg;
        int cod = Integer.parseInt(dlgMotivo.txtCod.getText());
        String mot = dlgMotivo.txtMotivo.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Motivo m = new Motivo(cod, mot, usuario);
        msg = GestionarMotivo.actMotivo(m);
        if(msg==null)
        {
            Mensajes.informacion("Motivo Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delMotivo()    
    {
        String msg;
        String cod = dlgMotivo.txtCod.getText();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarMotivo.delMotivo(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Motivo Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listMotivo(JTable tabla)
    {
        List lista = null;
        lista = GestionarMotivo.listMotivo();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1].toString();
            tb.addRow(fila);
        }
    }
}