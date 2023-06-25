package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.ClasificacionMovil;
import java.util.List;

public class GestionarClasificacion {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(iddivision) from division ");
        return cod;
    }

    public static String addClasificacion(ClasificacionMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO division VALUES (");
        sql.append(m.getCodM()).append(",'");
        sql.append(m.getClasificacion()).append("','");
        sql.append(m.getEspecificacion()).append("','S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actClasificacion(ClasificacionMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE division SET descripcion='");
        sql.append(m.getClasificacion()).append("', especificacion='");
        sql.append(m.getEspecificacion());
        sql.append("' WHERE iddivision=");
        sql.append(m.getCodM()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delClasificacion(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE division SET estado= 'N'");
        sql.append(" WHERE iddivision =");
        sql.append(cod);
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listClasificacion() {
        String sql = "SELECT * FROM division WHERE estado='S'";
        return OperacionMovil.getTabla(sql);
    }

}
