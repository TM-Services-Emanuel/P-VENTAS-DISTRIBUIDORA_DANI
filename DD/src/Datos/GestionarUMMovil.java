package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.UMMovil;
import java.util.List;

public class GestionarUMMovil {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idunidad) from unidad_medida ");
        return cod;
    }

    public static String addUM(UMMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO unidad_medida VALUES (");
        sql.append(m.getCodU()).append(",'");
        sql.append(m.getunidadmedida()).append("',");
        sql.append(m.getCantidad()).append(",'S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actUM(UMMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE unidad_medida SET unidadmedida='");
        sql.append(m.getunidadmedida()).append("', cantidad=");
        sql.append(m.getCantidad());
        sql.append(" WHERE idunidad=");
        sql.append(m.getCodU()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delUM(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE unidad_medida SET estado= 'N'");
        sql.append(" WHERE idunidad =");
        sql.append(cod).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listUM() {
        String sql = "SELECT * FROM unidad_medida WHERE estado='S'";
        return OperacionMovil.getTabla(sql);
    }

}
