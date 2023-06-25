package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarUsuario;
import Datos.GestionarVendedor;
import IU.dlgBuscarEmpleado;
import IU.dlgGestUsuario;
import IU.dlgGestUsuarioD;
import Modelo.Usuario;
import Modelo.Vendedor;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlUsuario {
    
    public static void selecEmpleado()
    {
        int x = dlgBuscarEmpleado.tbBuscarEmpleado.getSelectedRow();
        String cod = dlgBuscarEmpleado.tbBuscarEmpleado.getValueAt(x, 0).toString();
        Vendedor v = GestionarVendedor.busVendedor2(cod);
        dlgGestUsuario.txtCodEmpleado.setText(String.valueOf(v.getCodVe()));
        dlgGestUsuario.txtNomUsuario.setText(v.getNombreV().toUpperCase());
    }
    
    public static void selecEmpleado2()
    {
        int x = dlgBuscarEmpleado.tbBuscarEmpleado.getSelectedRow();
        String cod = dlgBuscarEmpleado.tbBuscarEmpleado.getValueAt(x, 0).toString();
        Vendedor v = GestionarVendedor.busVendedor2(cod);
        dlgGestUsuarioD.txtCodEmpleado.setText(String.valueOf(v.getCodVe()));
        dlgGestUsuarioD.txtNomUsuario.setText(v.getNombreV().toUpperCase());
    }
    static String UsuarioL="";
    public static Usuario capturarCampos()
    {
        Usuario u = null;
        int cod = Integer.parseInt(dlgGestUsuario.txtCod.getText());
        String nomU = dlgGestUsuario.txtCodEmpleado.getText().toUpperCase();
        String user = dlgGestUsuario.txtUsuario.getText();
        //String pass = Encript.getStringMessageDigest(dlgGestUsuario.psPassword.getText(), Encript.MD5);
        String pass = dlgGestUsuario.psPassword.getText();
        String pf = dlgGestUsuario.cbPerfil.getSelectedItem().toString();
        String perfil = null;
        switch (pf) {
            case "ADMINISTRADOR" -> perfil="1";
            case "VENTA" -> perfil="2";
            case "COMPRA" -> perfil="3";
            case "ALMACEN" -> perfil="4";
            case "DESARROLLADOR" -> perfil="5";   
        }
        String ip = dlgGestUsuario.txtIP.getText();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        u = new Usuario(cod, perfil, nomU, user, pass, ip, usuario);
        return u;
    }
    
    public static String addUsuario()
    {
        String msg;
        Usuario u = capturarCampos();
        msg = GestionarUsuario.addUsuario(u);
        if(msg==null)
        {
            Mensajes.informacion("Se registro usuario");
        }
        else
        {
           Mensajes.informacion("No se pudo registrar");
        }
        return msg;
    }
    
    public static String actUsuario()
    {
        String msg;
        Usuario u = capturarCampos();
        msg = GestionarUsuario.actUsuario(u);
        if(msg==null)
        {
            Mensajes.informacion("Se actualizo usuario");
        }
        else
        {
           Mensajes.informacion("No se pudo actualizar");
        }
        return msg;
    }
    
    public static String delUsuario()
    {
        String cod = dlgGestUsuario.txtCodEmpleado.getText();
        String msg;
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarUsuario.delUsuario(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Se Borro Usuario");
        }
        else
        {
            Mensajes.informacion("No se pudo Borrar");
        }
        return msg;
    }
        
    public static void listUsuario(JTable tabla)
    {
        List lista = null;
        lista = GestionarUsuario.listUsuario();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void listEmpleado(JTable tabla)
    {
        List lista = null;
        lista = GestionarUsuario.listEmpleados();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filNombre(JTable tabla, String cad) {
        List lista = null;
        lista = GestionarUsuario.filNombre(cad);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    
}
