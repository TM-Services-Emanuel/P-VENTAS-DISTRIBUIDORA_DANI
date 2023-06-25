package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.Deudas;
import java.util.List;

public class GestionarDeudas {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(iddeudas) FROM deudas");
        return cod;
    }

    public static String addDeudas(Deudas g) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO deudas VALUES (");
        sql.append(g.getIddeuda()).append(", ").append(g.getIdcliente()).append(", ").append(g.getIdmovil()).append(",'");
        sql.append(g.getFecha()).append("','").append(g.getObservacion()).append("',");
        sql.append(g.getMonto()).append(",'").append(g.getPagado()).append("','S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static String modDeudas(Deudas g) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE deudas SET idcliente=");
        sql.append(g.getIdcliente()).append(", idmovil=").append(g.getIdmovil()).append(", fecha='");
        sql.append(g.getFecha()).append("', observacion='").append(g.getObservacion()).append("', monto=");
        sql.append(g.getMonto()).append(", pagado='").append(g.getPagado()).append("' WHERE iddeudas=");
        sql.append(g.getIddeuda());
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List listarDeudasPendientes() {
        StringBuilder sql = new StringBuilder("SELECT iddeudas, fecha, razon_social, ruc, observacion, monto, descripcion FROM v_deudas_pendientes");
        sql.append(" ORDER BY iddeudas ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List listarDeudasSaldadas() {
        StringBuilder sql = new StringBuilder("SELECT iddeudas, fecha, razon_social, ruc, observacion, monto, descripcion FROM v_deudas_saldadas");
        sql.append(" ORDER BY iddeudas ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static String delDeudas(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE deudas SET estado='N' WHERE iddeudas=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static Deudas busDeudas(String cod) {
        Deudas p = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM deudas WHERE iddeudas = ");
        sql.append(cod).append("");
//        String sql = "SELECT * FROM proveedor WHERE pro_codigo = " + cod + "";
        Object[] filaObt = OperacionMovil.getFila(sql.toString());
        if (filaObt != null) {
            p = new Deudas();
            p.setIddeuda(Integer.parseInt(filaObt[0].toString()));
            p.setIdcliente(Integer.parseInt(filaObt[1].toString()));
            p.setIdmovil(Integer.parseInt(filaObt[2].toString()));
            p.setFecha(filaObt[3].toString());
            p.setObservacion(filaObt[4].toString());
            p.setMonto(Integer.parseInt(filaObt[5].toString()));
            p.setPagado(filaObt[6].toString());
        } else {
            System.out.println("No encontrado");
        }
        return p;
    }
    
    public static List fil_RS_RUC(String cod) {
        StringBuilder sql = new StringBuilder("SELECT iddeudas, fecha, razon_social, ruc, observacion, monto, descripcion from v_deudas_pendientes");
        sql.append(" WHERE razon_social LIKE '%");
        sql.append(cod).append("%' OR ruc LIKE '%");
        sql.append(cod).append("%' ORDER BY iddeudas");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List fil_RS_RUC_Saldados(String cod) {
        StringBuilder sql = new StringBuilder("SELECT iddeudas, fecha, razon_social, ruc, observacion, monto, descripcion from v_deudas_saldadas");
        sql.append(" WHERE razon_social LIKE '%");
        sql.append(cod).append("%' OR ruc LIKE '%");
        sql.append(cod).append("%' ORDER BY iddeudas");
        return OperacionMovil.getTabla(sql.toString());
    }
    
}
