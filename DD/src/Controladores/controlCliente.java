package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarCliente;
import IU.dlgClientes;
import IU.dlgGestClientes;
import IU.dlgGestClientes1;
import Modelo.ClienteMovil;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlCliente {
    static String UsuarioL="";
    public static void aModificar()
    {
        int x = dlgClientes.tablaClientes.getSelectedRow();
        String cod = dlgClientes.tablaClientes.getValueAt(x, 0).toString();
        ClienteMovil c = GestionarCliente.busCliente(cod);
        dlgGestClientes.lblCodC.setText(String.valueOf(c.getidCliente()));
        dlgGestClientes.cbCiudad.setSelectedIndex(c.getCodCiudad());
        dlgGestClientes.txtRazonS.setText(c.getRazonSocial());
        dlgGestClientes.txtRuc.setText(c.getRuc());
        dlgGestClientes.txtTelefono.setText(c.getTelefono());
        dlgGestClientes.txtDireccion.setText(c.getDireccion());
    }
    
    public static ClienteMovil capturarCampos()
    {
        ClienteMovil c = null;
        String telf;
        int codC = Integer.parseInt(dlgGestClientes.lblCodC.getText());
        int codCi = Integer.parseInt(dlgGestClientes.lbCiudad.getText());
        String razonS = dlgGestClientes.txtRazonS.getText();
        String ruc = dlgGestClientes.txtRuc.getText().toUpperCase();
        if(dlgGestClientes.txtTelefono.getText().isEmpty()){
            telf="' '";
        }else{
            telf = dlgGestClientes.txtTelefono.getText();
        }
        String direc = dlgGestClientes.txtDireccion.getText().toUpperCase();
        c = new ClienteMovil(codC , razonS, ruc, direc, telf, codCi);                
        return c;
    }
    public static ClienteMovil capturarCampos1()
    {
        ClienteMovil c = null;
        String telf;
        int codC = Integer.parseInt(dlgGestClientes1.lblCodC.getText());
        int codCi = Integer.parseInt(dlgGestClientes1.lbCiudad.getText());
        String razonS = dlgGestClientes1.txtRazonS.getText().toUpperCase();
        String ruc = dlgGestClientes1.txtRuc.getText().toUpperCase();
        if(dlgGestClientes1.txtTelefono.getText().isEmpty()){
            telf="' '";
        }else{
            telf = dlgGestClientes1.txtTelefono.getText();
        }
        String direc = dlgGestClientes1.txtDireccion.getText().toUpperCase();
        c = new ClienteMovil(codC , razonS, ruc, direc, telf, codCi);                
        return c;
    }
    
    public static String addCliente()
    {
        String msg;
        ClienteMovil c = capturarCampos();
        msg = GestionarCliente.addCliente(c);
        if(msg==null)
        {
            Mensajes.informacion("Cliente Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
     public static String addCliente1()
    {
        String msg;
        ClienteMovil c = capturarCampos1();
        msg = GestionarCliente.addCliente(c);
        if(msg==null)
        {
            Mensajes.informacion("Cliente Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actCliente()
    {
        String msg;
        ClienteMovil c = capturarCampos();
        msg = GestionarCliente.actCliente(c);
        if(msg==null)
        {
            Mensajes.informacion("Cliente Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delCliente()
    {
        int x = dlgClientes.tablaClientes.getSelectedRow();
        String msg;
        String cod = dlgClientes.tablaClientes.getValueAt(x, 0).toString();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarCliente.delCliente(cod);
        if(msg==null)
        {
            Mensajes.informacion("Cliente Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listClientes(JTable tabla, String cod)
    {
        List lista = null;
        lista = GestionarCliente.listClientes(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filtClientes(JTable tabla, String cad)
    {
        List lista = null;
        lista = GestionarCliente.filRazonS(cad);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filtRuc(JTable tabla, String cad)
    {
        List lista = null;
        lista = GestionarCliente.filRuc(cad);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    /*public static void filContacto(JTable tabla, String cad)
    {
        List lista = null;
        lista = GestionarCliente.filContacto(cad);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    */
    /*public static void filDireccion(JTable tabla, String cad)
    {
        List lista = null;
        lista = GestionarCliente.filDireccion(cad);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
   */         
}
