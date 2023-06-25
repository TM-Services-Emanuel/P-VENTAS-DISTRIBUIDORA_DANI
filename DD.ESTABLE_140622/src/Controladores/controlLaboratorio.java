package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarLaboratorio;
import IU.dlgLaboratorio;
import Modelo.Laboratorio;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class controlLaboratorio {
    static String UsuarioL="";
    public static String addLaboratorio()
    {
        String msg;
        int cod = Integer.parseInt(dlgLaboratorio.txtCod.getText().trim());
        String nombre = dlgLaboratorio.txtLaboratorio.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Laboratorio l = new Laboratorio(cod, nombre,usuario);
        msg = GestionarLaboratorio.addLaboratorio(l);
        if(msg==null)
        {
            Mensajes.informacion("Laboratorio Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actLaboratorio()
    {
        String msg;
        int cod = Integer.parseInt(dlgLaboratorio.txtCod.getText().trim());
        String nombre = dlgLaboratorio.txtLaboratorio.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Laboratorio l = new Laboratorio(cod, nombre, usuario);
        msg = GestionarLaboratorio.actLaboratorio(l);
        if(msg==null)
        {
            Mensajes.informacion("Laboratorio Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delLaboratorio()
    {
        String msg;
        String cod = dlgLaboratorio.txtCod.getText().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarLaboratorio.delLaboratorio(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Laboratorio Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    
    public static void lisLaboratorio(JTable tabla)
    {
        List lista = null;
        lista = GestionarLaboratorio.listLaboratorio();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1].toString();
            tb.addRow(fila);
        }
    }
    public static void filtrarLaboratorios(JTable tabla, String cod) {
        List lista;
        List lista1;
        lista = GestionarLaboratorio.filtrarLaboratorioID(cod);
        lista1 = GestionarLaboratorio.filtrarLaboratorio(cod);
        if (lista != null) {
            for (int i = 1; i < lista.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista.get(i);
                tb.addRow(fila);
            }
        }
        if (lista1 != null) {
            for (int i = 1; i < lista1.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista1.get(i);
                tb.addRow(fila);
            }
        }
    }
    
}
