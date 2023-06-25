package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Gasto;
import java.util.List;

public class GestionarGasto {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ga_codigo) FROM gastos");
        return cod;
    }

    public static String addGasto(Gasto g) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO gastos VALUES (");
        sql.append(getCodigo()).append(", ").append(g.getCaj()).append(", '").append(g.getGaFecha()).append("',");
        sql.append(g.getGaDescripcion()).append(",").append(g.getGaNombre()).append(",");
        sql.append(g.getGaImporte()).append(",'").append(g.getGaObservacion()).append("','S','").append(g.getUsusario()).append("','").append(g.getGenerado()).append("')");;
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List listarGastos(String fecha) {
        StringBuilder sql = new StringBuilder("SELECT ga_codigo,caja_ca_id,ga_fecha,dg_descripcion,tipo,descripcion,ga_importe,ga_observacion FROM v_gastos");
        sql.append(" WHERE ga_fecha>='").append(fecha).append("' AND ga_fecha<='").append(fecha).append("' ORDER BY tipo ASC");
        return Operacion.getTabla(sql.toString());
    }
    
    public static String delGasto(int cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE gastos SET ga_indicador='N', usu='"+usuario+"' WHERE ga_codigo=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
}
