package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.Cheques;
import java.util.List;

public class GestionarCheques {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idcheques) FROM cheques");
        return cod;
    }

    public static String addCheques(Cheques g) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO cheques VALUES (");
        sql.append(g.getIdcheques()).append(", '").append(g.getFecha()).append("', ").append(g.getIdtipo()).append(",").append(g.getIdbanco()).append(",").append(g.getIdmovil()).append(",'");
        sql.append(g.getRazon_social()).append("','").append(g.getRuc()).append("','").append(g.getFecha_emision()).append("','").append(g.getFecha_pago()).append("','");
        sql.append(g.getCuenta_n()).append("','").append(g.getCheque_n()).append("',");
        sql.append(g.getMonto()).append(",'").append(g.getObservacion()).append("','S')");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static String modCheques(Cheques g) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE cheques SET idtipo=");
        sql.append(g.getIdtipo()).append(", idbanco=").append(g.getIdbanco()).append(", idmovil=").append(g.getIdmovil()).append(", razon_social='");
        sql.append(g.getRazon_social()).append("', ruc='").append(g.getRuc()).append("', fecha_emision='");
        sql.append(g.getFecha_emision()).append("', fecha_pago='").append(g.getFecha_pago()).append("', cuenta_n='");
        sql.append(g.getCuenta_n()).append("', cheque_n='").append(g.getCheque_n()).append("', monto=");
        sql.append(g.getMonto()).append(", observacion='").append(g.getObservacion()).append("' WHERE idcheques=");
        sql.append(g.getIdcheques());
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List listarCheques() {
        StringBuilder sql = new StringBuilder("SELECT idcheques, fecha, tipo, banco, cheque_n, monto, razon_social, ruc, descripcion FROM v_cheques_activos");
        sql.append(" ORDER BY idcheques ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    
    public static String delCheques(int cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE cheques SET estado='N' WHERE idcheques=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
    public static Cheques busCheques(String cod) {
        Cheques p = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM cheques WHERE idcheques = ");
        sql.append(cod).append("");
//        String sql = "SELECT * FROM proveedor WHERE pro_codigo = " + cod + "";
        Object[] filaObt = OperacionMovil.getFila(sql.toString());
        if (filaObt != null) {
            p = new Cheques();
            p.setIdcheques(Integer.parseInt(filaObt[0].toString()));
            p.setFecha(filaObt[1].toString());
            p.setIdtipo(Integer.parseInt(filaObt[2].toString()));
            p.setIdbanco(Integer.parseInt(filaObt[3].toString()));
            p.setIdmovil(Integer.parseInt(filaObt[4].toString()));
            p.setRazon_social(filaObt[5].toString());
            p.setRuc(filaObt[6].toString());
            p.setFecha_emision(filaObt[7].toString());
            p.setFecha_pago(filaObt[8].toString());
            p.setCuenta_n(filaObt[9].toString());
            p.setCheque_n(filaObt[10].toString());
            p.setMonto(Integer.parseInt(filaObt[11].toString()));
            p.setObservacion(filaObt[12].toString());
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }
        return p;
    }
    
    public static List fil_RS_RUC(String cod) {
        StringBuilder sql = new StringBuilder("SELECT idcheques, fecha, tipo, banco, cheque_n, monto, razon_social, ruc, descripcion FROM v_cheques_activos");
        sql.append(" WHERE razon_social LIKE '%");
        sql.append(cod).append("%' OR ruc LIKE '%");
        sql.append(cod).append("%' OR cheque_n LIKE '%");
        sql.append(cod).append("%' ORDER BY idcheques");
        return OperacionMovil.getTabla(sql.toString());
    }
}
