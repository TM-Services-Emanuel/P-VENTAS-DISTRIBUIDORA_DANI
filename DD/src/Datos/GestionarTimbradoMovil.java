package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.TimbradoMovil;
import Modelo.UMMovil;
import java.util.List;

public class GestionarTimbradoMovil {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idtimbrado) from timbrado ");
        return cod;
    }

    public static String addTimbrado(TimbradoMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO timbrado VALUES (");
        sql.append(m.getCodT()).append(",");
        sql.append(m.getDescripcion()).append(",'");
        sql.append(m.getFechadesde()).append("','");
        sql.append(m.getFechahasta()).append("','");
        sql.append(m.getEstado()).append("')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actTimbrado(TimbradoMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE timbrado SET descripcion=");
        sql.append(m.getDescripcion()).append(", fechadesde='");
        sql.append(m.getFechadesde()).append("', fechahasta='");
        sql.append(m.getFechahasta()).append("', estado='");
        sql.append(m.getEstado());
        sql.append("' WHERE idtimbrado=");
        sql.append(m.getCodT()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delTimbrado(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE timbrado SET estado= 'Inactivo'");
        sql.append(" WHERE idtimbrado =");
        sql.append(cod).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listTimbrado() {
        //String sql = "SELECT * FROM timbrado WHERE estado='S'";
        String sql = "SELECT * FROM timbrado";
        return OperacionMovil.getTabla(sql);
    }

}
