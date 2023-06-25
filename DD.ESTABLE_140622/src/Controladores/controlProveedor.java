package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarProveedor;
import IU.dlgGestProveedor;
import IU.dlgProveedores;
import Modelo.Proveedor;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlProveedor {
    static String UsuarioL="";
    public static void aModificar()
    {
        DefaultTableModel m = (DefaultTableModel) dlgProveedores.tablaProveedores.getModel();
        int x = dlgProveedores.tablaProveedores.getSelectedRow();
        
        String cod = m.getValueAt(x, 0).toString();
        Proveedor pr = GestionarProveedor.busProveedor(cod);
        
        dlgGestProveedor.lblCodP.setText(String.valueOf(pr.getCodP()));
        dlgGestProveedor.txtRazonS.setText(pr.getRazoS());
        dlgGestProveedor.txtRuc.setText(pr.getRuc());
        dlgGestProveedor.txtContacto.setText(pr.getContac());
        dlgGestProveedor.txtCelular.setText(pr.getCelu());
        dlgGestProveedor.txtTelefono.setText(pr.getTelef());
        dlgGestProveedor.txtDireccion.setText(pr.getDireccion());
        dlgGestProveedor.cbCiudad.setSelectedIndex(pr.getCodCiudad());
        dlgGestProveedor.txaS.setText(pr.getObs());
    }
    
    public static Proveedor capturarCampos()
    {
        Proveedor pr = null;
        String contac;
        String telef;
        String direc;
        String obs;
        int cod = Integer.parseInt(dlgGestProveedor.lblCodP.getText());
        String razonS = dlgGestProveedor.txtRazonS.getText();
        String ruc = dlgGestProveedor.txtRuc.getText();
        if(dlgGestProveedor.txtContacto.getText().trim()==null){
            contac="''";
        }else{
            contac= dlgGestProveedor.txtContacto.getText().toUpperCase();
        }
        String celu = dlgGestProveedor.txtCelular.getText();
        if(dlgGestProveedor.txtTelefono.getText().trim()==null){
            telef="''";
        }else{
            telef= dlgGestProveedor.txtTelefono.getText();
        }
        if(dlgGestProveedor.txtDireccion.getText().trim()==null){
            direc="''";
        }else{
            direc=dlgGestProveedor.txtDireccion.getText().toUpperCase();
        }
        int codCiu = Integer.valueOf(dlgGestProveedor.lbCiudad.getText().trim());
        if(dlgGestProveedor.txaS.getText().trim()==null){
            obs="''";
        }else{
            obs= dlgGestProveedor.txaS.getText();
        }
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        pr = new Proveedor(cod,codCiu, razonS, ruc, contac, celu, telef, direc, obs, usuario);
        return pr;
    }
    
    public static String addProveedor()
    {
        String msg = null;
        Proveedor pr = capturarCampos();
        msg = GestionarProveedor.addProveedor(pr);
        if(msg==null)
        {
            Mensajes.informacion("Proveedor Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actProveedor()
    {
        String msg = null;
        Proveedor pr = capturarCampos();
        msg = GestionarProveedor.actProveedor(pr);
        if(msg==null)
        {
            Mensajes.informacion("Proveedor Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delProveedor()
    {
        int x = dlgProveedores.tablaProveedores.getSelectedRow();
        String msg;
        String cod = dlgProveedores.tablaProveedores.getValueAt(x, 0).toString();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        msg = GestionarProveedor.delProveedor(cod, usuario);
        if(msg==null)
        {
            Mensajes.informacion("Proveedor Eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listProveedor(JTable tabla, String cod)
    {
        List lista = null;
        lista = GestionarProveedor.listProveedor(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filProveedor(JTable tabla, String cod)
    {
        List lista = null;
        lista = GestionarProveedor.filRazonS(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filID(JTable tabla, String cod)
    {
        List lista = null;
        lista = GestionarProveedor.filID(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    public static void filRuc(JTable tabla, String cod)
    {
        List lista = null;
        lista = GestionarProveedor.filRuc(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
    
}
