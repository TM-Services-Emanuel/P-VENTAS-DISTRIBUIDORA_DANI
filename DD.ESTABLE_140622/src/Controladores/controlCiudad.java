package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarCiudad;
import IU.dlgCiudad;
import Modelo.Ciudad;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class controlCiudad {
    static String UsuarioL="";
    public static String addCiudad()
    {
        String msg;
        int cod = Integer.parseInt(dlgCiudad.txtCod.getText());
        String nombre = dlgCiudad.txtCiudad.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Ciudad c = new Ciudad(cod, nombre,usuario);
        msg = GestionarCiudad.addCiudad(c);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Registrada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actCiudad()
    {
        String msg;
        int cod = Integer.parseInt(dlgCiudad.txtCod.getText());
        String nombre = dlgCiudad.txtCiudad.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Ciudad c = new Ciudad(cod, nombre,usuario);
        msg = GestionarCiudad.actCiudad(c);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Actualizada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delCiudad()
    {
        String msg;
        String cod = dlgCiudad.txtCod.getText();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarCiudad.delCiudad(cod,usuario);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Eliminada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listCiudad(JTable tabla)
    {
        List lista = null;
        lista = GestionarCiudad.listCiudad();
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
