package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarEmpresa;
import IU.dlgEmpresa;
import Modelo.Empresa;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlEmpresa {
    static String UsuarioL="";
    public static String addEmpresa()
    {
        String msg;
        int cod = Integer.parseInt(dlgEmpresa.txtCod.getText());
        String nombre = dlgEmpresa.txtNombre.getText().toUpperCase();
        String ruc = dlgEmpresa.txtRuc.getText().toUpperCase();
        String direccion = dlgEmpresa.txtDireccion.getText().toUpperCase();
        String telefono = dlgEmpresa.txtTelefono.getText().toUpperCase();
        int idciudad = Integer.parseInt(dlgEmpresa.lbCiudad.getText());
        String visual = dlgEmpresa.lbvisual.getText().toUpperCase();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Empresa e = new Empresa(cod, nombre, ruc, direccion, telefono, idciudad);
        msg = GestionarEmpresa.addEmpresa(e);
        if(msg==null)
        {
            Mensajes.informacion("Empresa Registrada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actEmpresa()
    {
        String msg;
        int cod = Integer.parseInt(dlgEmpresa.txtCod.getText());
        String nombre = dlgEmpresa.txtNombre.getText().toUpperCase();
        String ruc = dlgEmpresa.txtRuc.getText().toUpperCase();
        String direccion = dlgEmpresa.txtDireccion.getText().toUpperCase();
        String telefono = dlgEmpresa.txtTelefono.getText().toUpperCase();
        int codCiudad = 0;
        try {
            dlgEmpresa.prepararBD();
            String clas;
            clas = dlgEmpresa.cboCiudad.getSelectedItem().toString();
            try {
                dlgEmpresa.rsM = dlgEmpresa.sentenciaM.executeQuery("SELECT * FROM ciudad WHERE ciudad='" + clas + "'");
                dlgEmpresa.rsM.last();
                codCiudad = dlgEmpresa.rsM.getInt("idciudad");
                dlgEmpresa.rsM.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la ciudad: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        String visual = dlgEmpresa.lbvisual.getText().toUpperCase();
        System.out.println(visual);
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Empresa e = new Empresa(cod, nombre, ruc, direccion, telefono, codCiudad);
        msg = GestionarEmpresa.actEmpresa(e);
        if(msg==null)
        {
            Mensajes.informacion("Empresa Actualizada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delEmpresa()
    {
        String msg;
        String cod = dlgEmpresa.txtCod.getText();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarEmpresa.delEmpresa(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Empresa Eliminada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    
    public static void lisEmpresa(JTable tabla)
    {
        List lista;
        lista = GestionarEmpresa.listEmpresa();
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
