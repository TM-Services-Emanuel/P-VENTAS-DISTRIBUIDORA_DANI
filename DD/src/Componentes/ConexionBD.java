package Componentes;

import java.sql.Connection;
import org.mariadb.jdbc.MariaDbConnection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private final String servidor = "127.0.0.1:3306";
    //private final String servidor = "192.168.0.1:3306";
    private final String bd = "p-ventasbd";
    private final String bdmovil = "bd_distribuidoradani";
    //private final String bdmovil = "jca_representaciones";
    private final String usuario = "root";
    private final String password = "";

    public Connection getConexion() {
        MariaDbConnection cn;
        try {
            //MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            cn = (MariaDbConnection) DriverManager.getConnection("jdbc:mariadb://"+servidor+"/"+bd+"", ""+usuario+"", ""+password+"");
            
            //MySQL
            //Class.forName("com.mysql.jdbc.Driver");
            //cn = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+bd+"", ""+usuario+"", ""+password+"");
            
            //PostgreSQL
//            Class.forName("org.postgresql.Driver");
//            cn = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/"+bd+"", ""+usuario+"", ""+password+"");
            /*SQL SERVER*/
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            cn = DriverManager.getConnection("jdbc:odbc:bdfarmacia","root","");
            
        } catch (ClassNotFoundException | SQLException e) {
            Mensajes.error("ERR_ClassConect: "+e.getMessage());
            cn = null;
        }
        return cn;
    }
    
    public Connection getConexionMovil() {
        MariaDbConnection cn;
        try {
            //MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            cn = (MariaDbConnection) DriverManager.getConnection("jdbc:mariadb://"+servidor+"/"+bdmovil+"", ""+usuario+"", ""+password+"");            
        } catch (ClassNotFoundException | SQLException e) {
            Mensajes.error("ERR_ClassConectM: "+e.getMessage());
            cn = null;
        }
        return cn;
    }

}
