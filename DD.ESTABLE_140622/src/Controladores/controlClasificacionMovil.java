package Controladores;

import Componentes.Mensajes;
import Datos.GestionarClasificacion;
import IU.dlgClasificacionMovil;
import Modelo.ClasificacionMovil;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlClasificacionMovil {
    public static String addClasificacion()
    {
        String msg;
        int cod = Integer.parseInt(dlgClasificacionMovil.txtCod.getText());
        String clasif = dlgClasificacionMovil.txtClasificacion.getText().toUpperCase();
        String especi = dlgClasificacionMovil.cbEspecificación.getSelectedItem().toString();
        ClasificacionMovil m = new ClasificacionMovil(cod, clasif, especi);
        msg = GestionarClasificacion.addClasificacion(m);
        if(msg==null)
        {
            Mensajes.informacion("Clasificación Registrada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actClasificacion()
    {
        String msg;
        int cod = Integer.parseInt(dlgClasificacionMovil.txtCod.getText());
        String clasif = dlgClasificacionMovil.txtClasificacion.getText().toUpperCase();
        String especi = dlgClasificacionMovil.cbEspecificación.getSelectedItem().toString();
        ClasificacionMovil m = new ClasificacionMovil(cod, clasif, especi);
        msg = GestionarClasificacion.actClasificacion(m);
        if(msg==null)
        {
            Mensajes.informacion("Clasificación Actualizada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delClasificacion()    
    {
        String msg;
        String cod = dlgClasificacionMovil.txtCod.getText();
        msg = GestionarClasificacion.delClasificacion(cod);
        if(msg==null)
        {
            Mensajes.informacion("Clasificación Eliminada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listClasificacion(JTable tabla)
    {
        List lista = null;
        lista = GestionarClasificacion.listClasificacion();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            tb.addRow(fila);
        }
    }
}