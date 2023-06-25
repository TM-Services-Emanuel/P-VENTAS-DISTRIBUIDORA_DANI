package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.DetalleFactura;
import Modelo.DetalleTransferencia;
import Modelo.Factura;
import Modelo.Transferencia;
import java.util.*;

public class GestionarTransferencia {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idtransferencia) from transferencia");
        return cod;
    }

    public static String addTransferencia(Transferencia t) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO transferencia VALUES (");
        sql.append(t.getCodTransferencia()).append(",'");
        sql.append(t.getFecha()).append("','");
        sql.append(t.getHora()).append("','");
        sql.append(t.getSalida_tipo()).append("',");
        sql.append(t.getIdsalida()).append(",'");
        sql.append(t.getObs_salida()).append("','");
        sql.append(t.getEntrada_tipo()).append("',");
        sql.append(t.getIdentrada()).append(",'");
        sql.append(t.getObs_entrada()).append("',");
        sql.append(t.getTotal()).append(",'S')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String actFactura(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE transferencia SET estado='N' WHERE idtransferencia=");
        sql.append(cod);
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }

    public static String addDetalleTransferencia(DetalleTransferencia dt) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO detalle_transferencia VALUES (");
        sql.append(dt.getCodTransferencia()).append(",");
        sql.append(dt.getCodArticulo()).append(",");
        sql.append(dt.getCantidad()).append(",");
        sql.append(dt.getPrecio()).append(",");
        sql.append(dt.getTotal()).append(")");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static Transferencia busTransferencia(String cod) {
        Transferencia trans = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM factura WHERE fac_codigo=");
        sql.append(cod).append("");
//        String sql = "SELECT * FROM factura WHERE fac_codigo=" + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            trans = new Transferencia();
          //  trans.setCodFactura(Integer.parseInt(filaObt[0].toString()));
           // trans.setCodCliente(Integer.parseInt(filaObt[1].toString()));
           // trans.setCodVendedor(Integer.parseInt(filaObt[2].toString()));
           // trans.setDescuento(Double.parseDouble(filaObt[3].toString()));
           // trans.setTipoPago(filaObt[4].toString());
           // trans.setFecha(filaObt[5].toString());
           // trans.setHora(filaObt[6].toString());
           // trans.setTotal(Double.parseDouble(filaObt[7].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return trans;
    }
    
    public static List listarTransferencias(String cod, String fecha) {
                StringBuilder sql = new StringBuilder("SELECT idtransferencia, idcaja, fecha, hora,salida_tipo, obs_salida");
                sql.append(", entrada_tipo, obs_entrada, total FROM transferencia ");
                sql.append("WHERE fecha >= '").append(fecha).append("'");
                sql.append("AND fecha<= '").append(fecha).append("' AND estado='S'");
                sql.append(" ORDER BY ").append(cod)
                .toString();
        return OperacionMovil.getTabla(sql.toString());
    }


    public static List listDetallesTransferencia(String cod) {
        StringBuilder sql = new StringBuilder("SELECT * FROM v_detalle_transferencias");
        sql.append(" WHERE idtransferencia=").append(cod);
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static String delTransferencia(int cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE transferencia SET estado='N', usuario='"+usuario+"' WHERE idtransferencia=");
        sql.append(cod);
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List listarTransferenciaReparto(String caja, String IdMovil) {
        StringBuilder sql = new StringBuilder("SELECT idtransferencia, idcaja, fecha, hora, salida_tipo, obs_salida, entrada_tipo, obs_entrada, total,id_salida, id_entrada");
        sql.append(" FROM v_transferencia");
        sql.append(" WHERE id_salida=").append(IdMovil);
        sql.append(" OR id_entrada=").append(IdMovil);
        /*sql.append(" AND idcaja=").append(caja);*/
        return OperacionMovil.getTabla(sql.toString());
    }

}
