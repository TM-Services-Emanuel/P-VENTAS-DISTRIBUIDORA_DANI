package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.CiudadMovil;
import java.util.List;


public class GestionarCiudadMovil {
    
    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idciudad) AS cod FROM ciudad");
        return cod;
    }

    public static String addCiudad(CiudadMovil c) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO ciudad VALUES (");
        sql.append(c.getCodCiudad()).append(",'");
        sql.append(c.getCiudad()).append("','S',");
        sql.append(c.getDepartamento()).append(")");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actCiudad(CiudadMovil c) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ciudad SET ciudad='");
        sql.append(c.getCiudad()).append("', departamento_iddepartamento=");
        sql.append(c.getDepartamento()).append(" WHERE idciudad=");
        sql.append(c.getCodCiudad()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delCiudad(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ciudad SET estado='N'");
        sql.append("' WHERE idciudad=");        
        sql.append(cod).append("");
//        String sql = "UPDATE provincias SET prv_indicador='N' WHERE prv_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listCiudad() {
        String sql = "select * from v_ciudades WHERE estado='S'";
        return OperacionMovil.getTabla(sql);
    }
}
