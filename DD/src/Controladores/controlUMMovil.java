package Controladores;

import Componentes.Mensajes;
import Datos.GestionarUMMovil;
import IU.dlgUMMovil;
import Modelo.UMMovil;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlUMMovil {
    public static String addUnidadMedida()
    {
        String msg;
        int cod = Integer.parseInt(dlgUMMovil.txtCod.getText().trim());
        String unidad = dlgUMMovil.txtunidad.getText().trim().toUpperCase();
        int cant = Integer.parseInt(dlgUMMovil.txtequivalencia.getText().trim());
        UMMovil m = new UMMovil(cod, unidad, cant);
        msg = GestionarUMMovil.addUM(m);
        if(msg==null)
        {
            Mensajes.informacion("Unidad de medida Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actUnidadMedida()
    {
        String msg;
        int cod = Integer.parseInt(dlgUMMovil.txtCod.getText().trim());
        String unidad = dlgUMMovil.txtunidad.getText().trim().toUpperCase();
        int cant = Integer.parseInt(dlgUMMovil.txtequivalencia.getText().trim());
        UMMovil m = new UMMovil(cod, unidad, cant);
        msg = GestionarUMMovil.actUM(m);
        if(msg==null)
        {
            Mensajes.informacion("Unidad de medida Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delUnidadMedida()    
    {
        String msg;
        int cod = Integer.parseInt(dlgUMMovil.txtCod.getText().trim());
        msg = GestionarUMMovil.delUM(cod);
        if(msg==null)
        {
            Mensajes.informacion("Unidad de medida Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listUnidadMedida(JTable tabla)
    {
        List lista;
        lista = GestionarUMMovil.listUM();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
}