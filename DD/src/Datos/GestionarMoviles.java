package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
//import Modelo.Familia;
import Modelo.Moviles;
import java.util.List;

public class GestionarMoviles {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(idmovil) FROM movil_reparto");
        return cod;
    }

    public static String addMovil(Moviles e) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO movil_reparto VALUES (");
        sql.append(e.getIdmovil());
        sql.append(",'");
        sql.append(e.getDescipcion());
        sql.append("','");
        sql.append(e.getChapa());
        sql.append("','");
        sql.append(e.getMarca());
        sql.append("','");
        sql.append(e.getModelo());
        sql.append("','");
        sql.append(e.getChasis());
        sql.append("','");
        sql.append(e.getAnho());
        sql.append("','");
        sql.append(e.getColor());
        sql.append("','");
        sql.append(e.getCapacidad());
        sql.append("','S','");
        sql.append(e.getUsuario());
        sql.append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO unidad VALUES ("+u.getCodUnidad()+",'"+u.getUnidad()+"','S')");
        return msg;
    }

    public static Moviles busMoviles(String cod) {
        Moviles e = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM movil_reparto WHERE idmovil = '");
                sql.append(cod);
                sql.append("'");
//        String sql = "SELECT * FROM unidad WHERE uni_codigo = '"+cod+"'";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            e = new Moviles();
            e.setIdmovil(Integer.parseInt(filaObt[0].toString()));
            e.setDescipcion(filaObt[1].toString());
        } else {
        }
        return e;
    }

    public static List listMoviles() {
        String sql = "select * from movil_reparto WHERE estado='S'";
        return Operacion.getTabla(sql);
    }

    public static String actMoviles(Moviles e) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE movil_reparto SET descripcion = '").append(e.getDescipcion());
        sql.append("',chapa='").append(e.getChapa());
        sql.append("',marca='").append(e.getMarca());
        sql.append("',modelo='").append(e.getModelo());
        sql.append("',chasis='").append(e.getChasis());
        sql.append("',anofabricacion='").append(e.getAnho());
        sql.append("',color='").append(e.getColor());
        sql.append("',capacidad='").append(e.getCapacidad());
        sql.append("',usuario='").append(e.getUsuario());
        sql.append("' WHERE idmovil=").append(e.getIdmovil()).append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delMoviles(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE movil_reparto SET estado='N', ");
                sql.append("usu='").append(usuario);
                sql.append("' WHERE idmovil = ");
                sql.append(cod);
                sql.append("");
//        String sql = "UPDATE unidad SET uni_indicador='N' WHERE uni_codigo = "+cod+"";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

}
