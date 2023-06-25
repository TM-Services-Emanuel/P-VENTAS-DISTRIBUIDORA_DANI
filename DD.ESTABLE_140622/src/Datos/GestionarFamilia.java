package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Familia;
import java.util.List;

public class GestionarFamilia {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(fam_codigo) FROM familia");
        //String cod = generarCodigos.getCodigo("SELECT fam_codigo FROM familia");
        return cod;
    }

    public static String addFamilia(Familia r) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO familia VALUES (");
        sql.append(r.getCodFamilia());
        sql.append(",'");
        sql.append(r.getFamilia());
        sql.append("',");
        sql.append(r.getGanacia());
        sql.append(",");
        sql.append(r.getIVA());
        sql.append(",'S','");
        sql.append(r.getUsuario());
        sql.append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO rubro VALUES ("+r.getCodRubro()+",'"+r.getRubro()+"','S')");
        return msg;
    }

    public static Familia busFamilia(String nom) {
        Familia r = null;
        StringBuilder sql = new StringBuilder("SELECT fam_codigo FROM familia WHERE fam_nombre = '");
        sql.append(nom);
        sql.append("'");
//        String sql = "SELECT rub_codigo FROM rubro WHERE rub_nombre = '"+nom+"'";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            r = new Familia();
            r.setCodFamilia(Integer.parseInt(filaObt[0].toString()));
            r.setFamilia(filaObt[1].toString());
            r.setGanacia(Double.valueOf(filaObt[2].toString()));
            r.setIVA(Integer.parseInt(filaObt[3].toString()));
        } else {
        }
        return r;
    }

    public static List listFamilia() {
        String sql = "select * from familia WHERE fam_indicador='S'";
        return Operacion.getTabla(sql);
    }

    public static String actFamilia(Familia r) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE familia SET fam_nombre='");
        sql.append(r.getFamilia());
        sql.append("', fam_ganancia=");
        sql.append(r.getGanacia());
        sql.append(", fam_iva=");
        sql.append(r.getIVA());
        sql.append(", usu='");
        sql.append(r.getUsuario());
        sql.append("' WHERE fam_codigo = ");
        sql.append(r.getCodFamilia());
        sql.append("");
//        String sq = "UPDATE rubro SET rub_nombre = '" + r.getRubro() + "' WHERE rub_codigo = '" + r.getCodRubro() + "'";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delFamilia(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE familia SET fam_indicador='N', usu='");       
        sql.append(usuario);
        sql.append("' WHERE fam_codigo = ");
        sql.append(cod);
        sql.append("");
//        String sql = "UPDATE rubro SET rub_indicador='N' WHERE rub_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

}
