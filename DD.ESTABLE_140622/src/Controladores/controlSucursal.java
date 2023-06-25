package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionalSucursal;
import IU.dlgSucursal;
import Modelo.Sucursal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlSucursal {
    static String UsuarioL="";
    public static String addSucursal()
    {
        String msg;
        int cod = Integer.parseInt(dlgSucursal.txtCod.getText());
        String sucur = dlgSucursal.txtSucursal.getText().toUpperCase();
        int emp = Integer.parseInt(dlgSucursal.lbCod.getText());
        System.out.println(emp);
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Sucursal s = new Sucursal(cod, sucur, emp, usuario);
        msg = GestionalSucursal.addSucursal(s);
        if(msg==null)
        {
            Mensajes.informacion("Sucursal registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actSucursal()
    {
        String msg;
        int cod = Integer.parseInt(dlgSucursal.txtCod.getText());
        String sucur = dlgSucursal.txtSucursal.getText().toUpperCase();
        //int emp= Integer.parseInt(dlgSucursal.lbCod.getText());
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Sucursal s = new Sucursal(cod,sucur, usuario);
        msg = GestionalSucursal.actSucursal(s);
        if(msg==null)
        {
            Mensajes.informacion("Sucursal Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delSucursal()
    {
        String msg;
        String cod = dlgSucursal.txtCod.getText();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionalSucursal.delSucursal(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Sucursal Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listSucursal(JTable tabla)
    {
        List lista = null;
        lista = GestionalSucursal.listSucursal();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            //fila[3].toString();
            
            tb.addRow(fila);
        }
    }
    
}
