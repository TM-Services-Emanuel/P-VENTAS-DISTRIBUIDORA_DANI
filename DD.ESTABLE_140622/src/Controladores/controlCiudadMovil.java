package Controladores;

import Componentes.ConexionBD;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarCiudadMovil;
import IU.dlgCiudadMovil;
import Modelo.CiudadMovil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;


public class controlCiudadMovil {
    static String UsuarioL="";
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection  con;
    
    public static void prepararBD(){
    {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                sentencia = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
    public static String addCiudad()
    {
        String msg;
        int cod = Integer.parseInt(dlgCiudadMovil.txtCod.getText());
        String nombre = dlgCiudadMovil.txtCiudad.getText().toUpperCase();
        int departamento = 0;// = Integer.parseInt(dlgCiudadMovil.txtCodDepartamento.getText().toUpperCase());
        try {
            prepararBD();
            String depart;
            depart = dlgCiudadMovil.cboDepartamento.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM departamento WHERE departamento='"+depart+"'");
                rs.last();
                departamento = rs.getInt("iddepartamento");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del departamento: "+ex.getMessage());
            }
        } catch (Exception pr) {}
        CiudadMovil c = new CiudadMovil(cod, nombre,departamento);
        msg = GestionarCiudadMovil.addCiudad(c);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Registrada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actCiudad()
    {
        String msg;
        int cod = Integer.parseInt(dlgCiudadMovil.txtCod.getText());
        String nombre = dlgCiudadMovil.txtCiudad.getText().toUpperCase();
        int codDepartamento=0;
        try {
            prepararBD();
            String departamento;
            departamento = dlgCiudadMovil.cboDepartamento.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM departamento WHERE departamento='"+departamento+"'");
                rs.last();
                codDepartamento = rs.getInt("iddepartamento");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del departamento: "+ex.getMessage());
            }
        } catch (Exception pr) {}
        
        
        
        
        CiudadMovil c = new CiudadMovil(cod, nombre,codDepartamento);
        msg = GestionarCiudadMovil.actCiudad(c);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Actualizada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delCiudad()
    {
        String msg;
        String cod = dlgCiudadMovil.txtCod.getText();
        msg = GestionarCiudadMovil.delCiudad(cod);
        if(msg==null)
        {
            Mensajes.informacion("Ciudad Eliminada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listCiudad(JTable tabla)
    {
        List lista = null;
        lista = GestionarCiudadMovil.listCiudad();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            /*fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[4].toString();*/
            tb.addRow(fila);
        }
    }
}
