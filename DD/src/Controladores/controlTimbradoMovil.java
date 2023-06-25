package Controladores;

import Componentes.Mensajes;
import Datos.GestionarTimbradoMovil;
import IU.dlgTimbradoMovil;
import Modelo.TimbradoMovil;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlTimbradoMovil {
    public static String addTimbrado()
    {
        String msg;
        int cod = Integer.parseInt(dlgTimbradoMovil.txtCod.getText().trim());
        int timbrado = Integer.parseInt(dlgTimbradoMovil.txtTimbrado.getText().trim());
        String desde = dlgTimbradoMovil.txtFDesde.getText().trim();
        String hasta = dlgTimbradoMovil.txtFHasta.getText().trim();
        String estado="Activo";
        TimbradoMovil m = new TimbradoMovil(cod, timbrado, desde,hasta,estado);
        msg = GestionarTimbradoMovil.addTimbrado(m);
        if(msg==null)
        {
            Mensajes.informacion("Timbrado Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actTimbrado()
    {
        String msg;
        int cod = Integer.parseInt(dlgTimbradoMovil.txtCod.getText().trim());
        int timbrado = Integer.parseInt(dlgTimbradoMovil.txtTimbrado.getText().trim());
        String desde = dlgTimbradoMovil.txtFDesde.getText().trim();
        String hasta = dlgTimbradoMovil.txtFHasta.getText().trim();
        String estado;
        if(dlgTimbradoMovil.cbEstado.isSelected()){
            estado="Activo";
        }else{
            estado="Inactivo";
        }
        TimbradoMovil m = new TimbradoMovil(cod, timbrado, desde,hasta,estado);
        msg = GestionarTimbradoMovil.actTimbrado(m);
        if(msg==null)
        {
            Mensajes.informacion("Timbrado Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delTimbrado()    
    {
        String msg;
        int cod = Integer.parseInt(dlgTimbradoMovil.txtCod.getText().trim());
        msg = GestionarTimbradoMovil.delTimbrado(cod);
        if(msg==null)
        {
            Mensajes.informacion("Timbado desactivado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listTimbrado(JTable tabla)
    {
        List lista;
        lista = GestionarTimbradoMovil.listTimbrado();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
}