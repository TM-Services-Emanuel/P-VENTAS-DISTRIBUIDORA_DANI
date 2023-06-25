package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Ingreso;

public class GestionarIngreso {
    
    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ing_codigo) FROM ingreso");
        return cod;
    }
    
    public static String addIngreso(Ingreso i) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO ingreso VALUES (");
        sql.append(getCodigo()).append(",").append(i.getIngCa()).append(",'").append(i.getIngFecha()).append("',");
        sql.append(i.getIngCliente()).append(",").append(i.getIngDescripcion()).append(",");
        sql.append(i.getIngImporte()).append(",'").append(i.getIngObservacion()).append("','S', '").append(i.getUsuario()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
}
