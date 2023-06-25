package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Ciudad;
import java.util.List;


public class GestionarCiudad {
    
    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ciu_codigo) AS cod FROM ciudad");
        return cod;
    }

    public static String addCiudad(Ciudad c) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO ciudad VALUES (");
        sql.append(c.getCodCiudad()).append(",'");
        sql.append(c.getCiudad()).append("','S','");
        sql.append(c.getUsuario()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO pro|
        return msg;
    }

    public static String actCiudad(Ciudad c) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ciudad SET ciu_nombre='");
        sql.append(c.getCiudad()).append("', usu='");
        sql.append(c.getUsuario()).append("' WHERE ciu_codigo=");
        sql.append(c.getCodCiudad()).append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE provincias SET prv_nombre='"
//                + p.getProvincia() + "' WHERE prv_codigo="
//                + p.getCodProvincia() + "");
        return msg;
    }

    public static String delCiudad(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ciudad SET ciu_indicador='N', usu='");
        sql.append(usuario).append("' WHERE ciu_codigo=");        
        sql.append(cod).append("");
//        String sql = "UPDATE provincias SET prv_indicador='N' WHERE prv_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listCiudad() {
        String sql = "select * from ciudad WHERE ciu_indicador='S'";
        return Operacion.getTabla(sql);
    }
}
