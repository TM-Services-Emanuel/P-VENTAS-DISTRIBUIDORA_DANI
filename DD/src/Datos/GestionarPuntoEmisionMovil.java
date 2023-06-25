package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.PuntoEmisionMovil;
import Modelo.refMovil;
import java.util.List;

public class GestionarPuntoEmisionMovil {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idemision) from puntoemision ");
        return cod;
    }

    public static String addPuntoEmision(PuntoEmisionMovil pe) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO puntoemision VALUES (");
        sql.append(pe.getIdEmision()).append(",");
        sql.append(pe.getIdTimbrado()).append(",'");
        sql.append(pe.getEstablecimiento()).append("','");
        sql.append(pe.getPuntoEmision()).append("','");
        sql.append(pe.getDireccion()).append("',");
        sql.append(pe.getFacturaInicio()).append(",");
        sql.append(pe.getFacturaFin()).append(",");
        sql.append(pe.getFacturaActual()).append(",'");
        sql.append(pe.getTipo()).append("','");
        sql.append(pe.getTipo2()).append("','");
        sql.append(pe.getIp()).append("','");
        sql.append(pe.getEstado()).append("','S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    public static String addRef(refMovil rm) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO ref VALUES (");
        sql.append(rm.getIdemision()).append(",");
        sql.append(rm.getNventa()).append(")");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actPuntoEmision(PuntoEmisionMovil m) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE puntoemision SET establecimiento='");
        sql.append(m.getEstablecimiento()).append("', puntoemision='");
        sql.append(m.getPuntoEmision()).append("', direccion='");
        sql.append(m.getDireccion()).append("', facturainicio=");
        sql.append(m.getFacturaInicio()).append(", facturafin=");
        sql.append(m.getFacturaFin()).append(", facturaactual=");
        sql.append(m.getFacturaActual()).append(", tipo='");
        sql.append(m.getTipo()).append("', tipo2='");
        sql.append(m.getTipo2()).append("', ip='");
        sql.append(m.getIp()).append("', estado='");
        sql.append(m.getEstado());
        sql.append("' WHERE idemision=");
        sql.append(m.getIdEmision()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    public static String actRef(refMovil rm) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ref SET nventa=");
        sql.append(rm.getNventa());
        sql.append(" WHERE idemision=");
        sql.append(rm.getIdemision()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delPuntoEmision(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE puntoemision SET estado2='N'");
        sql.append(" WHERE idemision =");
        sql.append(cod).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static List listPuntoEmision() {
        //String sql = "SELECT * FROM timbrado WHERE estado='S'";
        String sql = "SELECT * FROM v_puntoemision4 ORDER BY idemision";
        return OperacionMovil.getTabla(sql);
    }

}
