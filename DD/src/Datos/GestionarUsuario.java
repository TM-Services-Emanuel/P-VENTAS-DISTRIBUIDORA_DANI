package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Usuario;
import java.util.List;

public class GestionarUsuario {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(usu_codigo) FROM usuario");
        return cod;
    }

    public static String addUsuario(Usuario u) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO usuario VALUES (");
        sql.append(u.getCodUsuario()).append(",'").append(u.getPefil()).append("','");
        sql.append(u.getNomUsuario()).append("','").append(u.getUsuario()).append("','");
        sql.append(u.getPassword()).append("','S','");
        sql.append(u.getIp()).append("','");
        sql.append(u.getUsu()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO usuario VALUES (" + 
//                u.getCodUsuario() + ",'" + u.getNomUsuario() + "','" + 
//                u.getPefil() + "','" + u.getUsuario() + "','" + 
//                u.getPassword() + "','S','" + 
//                u.getIp() + "')");
        return msg;
    }

    public static String actUsuario(Usuario u) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE usuario SET perfil_per_id='");
        sql.append(u.getPefil()).append("',usu_usuario='");
        sql.append(u.getUsuario()).append("',usu_password='");
        sql.append(u.getPassword()).append("',usu_ip='");
        sql.append(u.getIp()).append("',usu='");
        sql.append(u.getUsu()).append("' WHERE usu_codigo = '");
        sql.append(u.getCodUsuario()).append("'");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE usuario SET usu_nombre='"
//                + u.getNomUsuario() + "',usu_perfil='"
//                + u.getPefil() + "',usu_usuario='"
//                + u.getUsuario() + "',usu_password='"
//                + u.getPassword() + "',usu_ip='"
//                + u.getIp() + "' WHERE usu_codigo = '"
//                + u.getCodUsuario() + "'");
        return msg;
    }

    public static String delUsuario(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE usuario SET usu_indicador='N', usu='"); 
        sql.append(usuario).append("' WHERE usu_codigo=");
        sql.append(cod);
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE usuario SET usu_indicador='N' WHERE usu_codigo=" + cod + "");
        return msg;
    }

    public static Usuario busUsuario(String cod) {
        Usuario u = null;
        String sql = "SELECT * FROM v_usuario WHERE usu_codigo=" + cod + "";
        Object[] filaObt = Operacion.getFila(sql);
        if (filaObt != null) {
            u = new Usuario();
            u.setCodUsuario(Integer.parseInt(filaObt[0].toString()));
            u.setPefil(filaObt[1].toString());
            u.setNomUsuario(filaObt[2].toString());
            u.setUsuario(filaObt[3].toString());
            u.setPassword(filaObt[4].toString());
            u.setIp(filaObt[6].toString());
        }
        return u;
    }

    public static List listUsuario() {
        String sql = "SELECT * FROM v_usuario WHERE usu_indicador='S' ORDER BY usu_codigo";
        return Operacion.getTabla(sql);
    }

    public static List listEmpleados() {
        StringBuilder sql = new StringBuilder("SELECT ven_codigo, ven_nombre, ven_observacion ");
        sql.append("FROM vendedor WHERE ven_indicador='S' AND idfuncion>=3 AND idfuncion<=4 AND NOT EXISTS");
        sql.append("(SELECT usu_codigo FROM usuario where vendedor.ven_codigo = usuario.vendedor_ven_codigo)");
//        String sql = "SELECT ven_codigo, ven_nombre, ven_observacion "
//                + "FROM vendedor WHERE NOT EXISTS "
//                + "(SELECT usu_codigo FROM usuario where vendedor.ven_codigo = usuario.usu_codigo)";
        return Operacion.getTabla(sql.toString());
    }
    public static List filNombre(String cad) {
        StringBuilder sql = new StringBuilder("SELECT ven_codigo, ven_nombre, ven_observacion ");
        sql.append("FROM vendedor WHERE ven_nombre LIKE '%");
        sql.append(cad);
        sql.append("%' AND ven_indicador='S' AND idfuncion=3 AND NOT EXISTS ");
        sql.append("(SELECT usu_codigo FROM usuario where vendedor.ven_codigo = usuario.vendedor_ven_codigo)");
//        String sql = "SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_nombre ILIKE '" + cad + "%'";
        return Operacion.getTabla(sql.toString());
    }
}
