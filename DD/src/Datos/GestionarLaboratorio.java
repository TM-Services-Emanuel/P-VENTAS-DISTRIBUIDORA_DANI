package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Laboratorio;
import java.util.List;


public class GestionarLaboratorio {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(lab_codigo) AS cod FROM laboratorio");
        return cod;
    }

    public static String addLaboratorio(Laboratorio l) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO laboratorio VALUES (");
        sql.append(l.getCodLaboratorio()).append(", '");
        sql.append(l.getLaboratorio()).append("','S','");
        sql.append(l.getUsuario()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO marca VALUES ("
//                + m.getCodMarca() + ",'"
//                + m.getMarca() + "','S')");
        return msg;
    }

    public static Laboratorio busLaboratorio(String cod) {
        Laboratorio l = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM laboratorio WHERE lab_codigo = '");
        sql.append(cod).append("'");
//        String sql = "SELECT * FROM marca WHERE mar_codigo = '" + cod + "'";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            l = new Laboratorio();
            l.SetCodLaboratorio(Integer.parseInt(filaObt[0].toString()));
            l.setLaboratorio(filaObt[1].toString());
        } else {
        }
        return l;
    }

    public static List listLaboratorio() {
        String sql = "select * from laboratorio WHERE lab_indicador='S' ORDER BY lab_codigo";
        return Operacion.getTabla(sql);
    }

    public static String actLaboratorio(Laboratorio l) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE laboratorio SET lab_nombre = '");
        sql.append(l.getLaboratorio());
        sql.append("', usu='");
        sql.append(l.getUsuario());
        sql.append("' WHERE lab_codigo = '");
        sql.append(l.getCodLaboratorio()).append("'");
//        String sql = "UPDATE marca SET mar_nombre = '"
//                + m.getMarca()
//                + "' WHERE mar_codigo = '"
//                + m.getCodMarca() + "'";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delLaboratorio(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE laboratorio SET lab_indicador='N', usu='");
        sql.append(usuario).append("' WHERE lab_codigo =");
        sql.append(cod).append("");
//        String sql = "UPDATE marca SET mar_indicador='N' WHERE mar_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List filtrarLaboratorioID(String cod) {
        String sql = "select * from laboratorio WHERE lab_indicador='S' AND lab_codigo LIKE '%"+cod+"%'";
        return Operacion.getTabla(sql);
    }
    public static List filtrarLaboratorio(String cod) {
        String sql = "select * from laboratorio WHERE lab_indicador='S' AND lab_nombre LIKE '%"+cod+"%'";
        return Operacion.getTabla(sql);
    }

}
