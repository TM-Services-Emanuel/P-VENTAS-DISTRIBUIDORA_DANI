package Componentes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbResultSetMetaData;
import org.mariadb.jdbc.MariaDbStatement;

/*CLASE PARA GENERAR LOS ID's DE TODAS LAS TABLAS Y PARA EXTRAER VALORES ENTEROS*/
/*-------------------------------------------------------------------------------*/
public class generarCodigos {
    /*Metodo solo es util cuando las primary key son enteros, si el primary key es varchar
     solo generara el codigo hasta el 10 en caso de mysql y acces, aun no se ha hecno la prueba con 
     SQL server y ORACLE */

    /*public static String getCodigo(String sql)//Traemos el id mayor de las tablas
    {
        String codgen = "";
        String CodMaximo = "";
        int contador = 0;
        try {
            Connection conexion = new ConexionBD().getConexion();//Nos conectamos
            //System.out.println("1");
            Statement sentencia = conexion.createStatement();
            //System.out.println("2");
            ResultSet resultado = sentencia.executeQuery(sql);
           // System.out.println("3");
            ResultSetMetaData rmeta = resultado.getMetaData();
            //System.out.println("4");
            int numColumnas = rmeta.getColumnCount();
            System.out.println("5 " + numColumnas);
            while (resultado.next())//Recorremos la tabla
            {
                System.out.println("6");
                for (int j = 1; j <= numColumnas; j++) 
                {
                    System.out.println("7 -"+ j + "- "+resultado.getString("cod"));
                    CodMaximo = resultado.getString(j); //Obtenermos el codigo maximo
                    System.out.println("8"+ CodMaximo);
                    contador++;//Acumulamos
                    System.out.println("9 "+contador);
                }
                
            }
            
            conexion.close();
            System.out.println("10");
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacian()
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }
        if (contador == 0)//Si no hay registros
        {
            codgen = "1";
            //System.out.println("11");
        } else//Si hay mas de un registro
        {
            int mayor = Integer.parseInt(CodMaximo.substring(0));
            codgen = unoMas(mayor + 1);//Aumentamos un valor mas
            //System.out.println("12");
        }
        
        return codgen;//Retornamos el valor
    }*/
    public static String getCodigo(String sql)//Traemos el id mayor de las tablas
    {
        String codgen = "";
        //String CodMaximo = "";
        //int contador = 0;
        try {
            //System.out.println("1");
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                //System.out.println("1");
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                //System.out.println("2");
                ResultSet resultado = sentencia.executeQuery(sql);
                // System.out.println("3");
                resultado.first();
                codgen=String.valueOf(resultado.getInt(1) + 1);
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacian()
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }
        
        return codgen;//Retornamos el valor
    }
    
    public static String getCodigoMovil(String sql)//Traemos el id mayor de las tablas
    {
        String codgen = "";
        //String CodMaximo = "";
        //int contador = 0;
        try {
            //System.out.println("1");
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexionMovil() //Nos conectamos
            ) {
                //System.out.println("1");
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                //System.out.println("2");
                ResultSet resultado = sentencia.executeQuery(sql);
                // System.out.println("3");
                resultado.first();
                codgen=String.valueOf(resultado.getInt(1) + 1);
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacian()
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }
        
        return codgen;//Retornamos el valor
    }
    public static String ObtenerCodigo(String sql)//Traemos el id mayor de las tablas
    {
        String codgen = "";
        //String CodMaximo = "";
        //int contador = 0;
        try {
            //System.out.println("1");
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                //System.out.println("1");
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                //System.out.println("2");
                ResultSet resultado = sentencia.executeQuery(sql);
                // System.out.println("3");
                resultado.first();
                codgen=String.valueOf(resultado.getInt(1));
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacian()
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }
        
        return codgen;//Retornamos el valor
    }

    /*static String unoMas(int num)//Aumentar el valor obtenido en getCodigo() +1
    {
        String nnum = String.valueOf(num);
        for (int i = String.valueOf(num).length(); i <= 1; i++) {
            nnum = "" + nnum;//Sumamos el valor
        }
        return nnum;//Retornamos el valor
    }*/

    public static String getCantidad(String sql)//Metodo para traer valores enteros de la base de datos
    {
        String codgen = "";
        String CodMaximo = "";
        int contador = 0;
        try {
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                ResultSet resultado = sentencia.executeQuery(sql);
                MariaDbResultSetMetaData rmeta = (MariaDbResultSetMetaData) resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                
                while (resultado.next())//Recorremos la tabla
                {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);//Obtenermos el codigo maximo
                    }
                    contador++;//Acumulamos
                }
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacia
            System.out.println("Tabla vacía/");
        }
        if (contador == 0)//Si no hay registros
        {
            codgen = "1";
        } else//Si hay mas de un registro
        {
            int mayor = Integer.parseInt(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);//No aumentamos nada
        }
        return codgen;//Retornamos el valor
    }

    public static String getDecimales(String sql)//Metodo para traer datos decimales
    {
        String codgen;
        String CodMaximo = "";
        int contador = 0;
        try {
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                ResultSet resultado = sentencia.executeQuery(sql);
                MariaDbResultSetMetaData rmeta = (MariaDbResultSetMetaData) resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                
                while (resultado.next())//Recorremos la tabla
                {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);//Obtenermos el codigo maximo
                    }
                    contador++;//Acumulamos
                }
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacia
            System.out.println(0.0);
        }
        if (contador == 0)//Si no hay registros
        {
            codgen = "1";
        } else//Si hay mas de un registro
        {
            int mayor = Integer.parseInt(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);//No aumentamos nada
        }
        return codgen;//Retornamos el valor
    }
    
    public static String getFecha(String sql)//Metodo para traer fechas
    {
        String codgen = "";
        String CodMaximo = "";
        int contador = 0;
        try {
            try (MariaDbConnection conexion = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement sentencia = (MariaDbStatement) conexion.createStatement();
                ResultSet resultado = sentencia.executeQuery(sql);
                MariaDbResultSetMetaData rmeta = (MariaDbResultSetMetaData) resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                
                while (resultado.next())//Recorremos la tabla
                {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);//Obtenermos el codigo maximo
                    }
                    contador++;//Acumulamos
                }
            }
        } catch (SQLException e) {
            //Excepcion en caso la tabla este vacia
            System.out.println("Tabla vacía-");
        }
        if (contador == 0)//Si no hay registros
        {
            codgen = "1";
        } else//Si hay mas de un registro
        {
            String mayor = String.valueOf(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);//No aumentamos nada
        }
        return codgen;//Retornamos el valor
    }

}
