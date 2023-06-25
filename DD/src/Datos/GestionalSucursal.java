package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Sucursal;
import java.util.List;

public class GestionalSucursal {    

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(suc_codigo) FROM sucursal");
        return cod;
    }

    public static String addSucursal(Sucursal s) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO sucursal VALUES (");
        sql.append(s.getCodSucursal());
        sql.append(",");
        sql.append(s.getCodEmpresa());
        sql.append(",'");
        sql.append(s.getSucursal());
        sql.append("','S','");
        sql.append(s.getUsuario());
        sql.append("')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO transporte VALUES ("+t.getCodTransporte()+",'"+t.getTransporte()+"','S')");
        return msg;
    }

    public static String actSucursal(Sucursal s) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE sucursal SET suc_nombre='");
        sql.append(s.getSucursal());
        sql.append("', usu='");
        sql.append(s.getUsuario());
        sql.append("' WHERE suc_codigo=");
        sql.append(s.getCodSucursal());
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE transporte SET tra_nombre='"+t.getTransporte()+"' WHERE tra_codigo="+t.getCodTransporte()+"");
        return msg;
    }

    public static String delSucursal(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE sucursal SET suc_indicador='N', usu='");
        sql.append(usuario);
        sql.append("' WHERE suc_codigo = ");
        sql.append(cod);
        sql.append("");
//        String sql = "UPDATE transporte SET tra_indicador='N' WHERE tra_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listSucursal() {
        String sql = "select suc_codigo,suc_nombre,razon_social from v_sucursal WHERE suc_indicador='S'";
        return Operacion.getTabla(sql);
    }

}
