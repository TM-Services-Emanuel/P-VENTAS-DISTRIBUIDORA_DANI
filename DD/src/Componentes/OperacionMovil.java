package Componentes;

import java.sql.*;
import org.mariadb.jdbc.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionMovil {
    
    public static String exeOperacion(String sql)//Metodo insertar, actualizar y eliminar
    {
        String msg=null;
        try{
            MariaDbConnection cn=(MariaDbConnection) new ConexionBD().getConexionMovil();
            if(cn==null){
                msg="No hay Conexion con la Base de Datos";
            }else{
                MariaDbStatement st=(MariaDbStatement) cn.createStatement();
                st.executeUpdate(sql);
                st.close();
                cn.close();
            }          
        }catch(SQLException e){
            msg=e.getMessage();
            System.out.println("CONEXIÓN: "+e.getMessage());
        }
        return msg;
    }
    public static String exeQuery(String sql)//Metodo insertar, actualizar y eliminar
    {
        String msg=null;
        try{
            MariaDbConnection cn=(MariaDbConnection) new ConexionBD().getConexionMovil();
            if(cn==null){
                msg="No hay Conexion con la Base de Datos";
            }else{
                MariaDbStatement st=(MariaDbStatement) cn.createStatement();
                st.executeQuery(sql);
                st.close();
                cn.close();
            }          
        }catch(SQLException e){
            msg=e.getMessage();
            System.out.println(e.getMessage());
        }
        return msg;
    }
    
    public static List getTabla(String sql){//Metodo para mostrar la tabla completa
        List Lista=new ArrayList();
        try{
            MariaDbConnection cn=(MariaDbConnection) new ConexionBD().getConexionMovil();
            if(cn==null){//Comprobamos la conexion
                Lista=null;
            }else{//Hay conexion
                MariaDbStatement st=(MariaDbStatement) cn.createStatement();
                ResultSet rs=st.executeQuery(sql);
                MariaDbResultSetMetaData rm=(MariaDbResultSetMetaData) rs.getMetaData();
                int numCol=rm.getColumnCount();
                String[] titCol=new String[numCol];
                for(int i=0;i<numCol;i++)
                   titCol[i]=rm.getColumnName(i+1);
                    Lista.add(titCol);
                while(rs.next()){
                    Object[]fila=new Object[numCol];
                    for(int i=0;i<numCol;i++)
                        fila[i]=rs.getObject(i+1);
                    Lista.add(fila);
                }
                rs.close();
                st.close();
                cn.close();
            }
        }catch(SQLException e){
            Lista=null;
        }
        return Lista;
    }
    
    public static List getTablaUnicoRegistro(String sql){//Metodo para mostrar la tabla completa
        List Lista=new ArrayList();
        try{
            MariaDbConnection cn=(MariaDbConnection) new ConexionBD().getConexionMovil();
            if(cn==null){//Comprobamos la conexion
                Lista=null;
            }else{//Hay conexion
                MariaDbStatement st=(MariaDbStatement) cn.createStatement();
                ResultSet rs=st.executeQuery(sql);
                MariaDbResultSetMetaData rm=(MariaDbResultSetMetaData) rs.getMetaData();
                int numCol=rm.getColumnCount();
                String[] titCol=new String[numCol];
                for(int i=0;i<numCol;i++)
                   titCol[i]=rm.getColumnName(i+1);
                    Lista.add(titCol);
                while(rs.first()){
                    Object[]fila=new Object[numCol];
                    for(int i=0;i<numCol;i++)
                        fila[i]=rs.getObject(i+1);
                    Lista.add(fila);
                }
                rs.close();
                st.close();
                cn.close();
            }
        }catch(SQLException e){
            Lista=null;
        }
        return Lista;
    }
    public static Object[] getFila(String sql){//Metodo que solo retorna una fila
        Object[]fila=null;
        List lista=getTabla(sql);//Llamamos al metodo getTabla
        if(lista!=null){
            if(lista.size()>1)
                fila=(Object[])lista.get(1);
        }
        return fila;
    }
    
       
}
