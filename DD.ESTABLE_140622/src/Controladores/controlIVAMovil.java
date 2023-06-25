package Controladores;

import Componentes.Mensajes;
import Datos.GestionarIVAMovil;
import IU.dlgIVAMovil;
import Modelo.IVAMovil;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlIVAMovil {
    public static String addIVA()
    {
        String msg;
        int cod = Integer.parseInt(dlgIVAMovil.txtCod.getText().trim());
        String descripcion = dlgIVAMovil.txtDescripcion.getText().trim().toUpperCase();
        int impuesto = Integer.parseInt(dlgIVAMovil.txtImpuesto.getText().trim());
        IVAMovil m = new IVAMovil(cod, impuesto, descripcion);
        msg = GestionarIVAMovil.addIVA(m);
        if(msg==null)
        {
            Mensajes.informacion("Impuesto Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actIVA()
    {
        String msg;
        int cod = Integer.parseInt(dlgIVAMovil.txtCod.getText().trim());
        String descripcion = dlgIVAMovil.txtDescripcion.getText().trim().toUpperCase();
        int impuesto = Integer.parseInt(dlgIVAMovil.txtImpuesto.getText().trim());
        IVAMovil m = new IVAMovil(cod, impuesto, descripcion);
        msg = GestionarIVAMovil.actIVA(m);
        if(msg==null)
        {
            Mensajes.informacion("Impuesto Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delIVA()    
    {
        String msg;
        int cod = Integer.parseInt(dlgIVAMovil.txtCod.getText().trim());
        msg = GestionarIVAMovil.delIVA(cod);
        if(msg==null)
        {
            Mensajes.informacion("Impuesto Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listIVA(JTable tabla)
    {
        List lista;
        lista = GestionarIVAMovil.listIVA();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
}