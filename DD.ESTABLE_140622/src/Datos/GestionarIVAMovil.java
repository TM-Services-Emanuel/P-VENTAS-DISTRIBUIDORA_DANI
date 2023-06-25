package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.IVAMovil;
import Modelo.UMMovil;
import java.util.List;

public class GestionarIVAMovil {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idiva) from iva ");
        return cod;
    }

    public static String addIVA(IVAMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO iva VALUES (");
        sql.append(m.getCodI()).append(",");
        sql.append(m.getImpuesto()).append(",'");
        sql.append(m.getDescripcion()).append("','S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actIVA(IVAMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE iva SET impuesto=");
        sql.append(m.getImpuesto()).append(", descripcion='");
        sql.append(m.getDescripcion());
        sql.append("' WHERE idiva=");
        sql.append(m.getCodI()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delIVA(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE iva SET estado= 'N'");
        sql.append(" WHERE idiva =");
        sql.append(cod).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listIVA() {
        String sql = "SELECT * FROM iva WHERE estado='S'";
        return OperacionMovil.getTabla(sql);
    }

}
