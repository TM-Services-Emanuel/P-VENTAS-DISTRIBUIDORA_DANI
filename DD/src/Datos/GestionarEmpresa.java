package Datos;

import Componentes.Operacion;
import Componentes.OperacionMovil;
import Componentes.generarCodigos;
//import Modelo.Familia;
import Modelo.Empresa;
import java.util.List;

public class GestionarEmpresa {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idempresa) FROM empresa");
        return cod;
    }

    public static String addEmpresa(Empresa e) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO empresa VALUES (");
        sql.append(e.getCodEmpresa());
        sql.append(",'");
        sql.append(e.getEmpresa());
        sql.append("','");
        sql.append(e.getRuc());
        sql.append("','");
        sql.append(e.getDireccion());
        sql.append("','");
        sql.append(e.getTelefono());
        sql.append("','S',");
        sql.append(e.getIdciudad());
        sql.append(")");
        msg = OperacionMovil.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO unidad VALUES ("+u.getCodUnidad()+",'"+u.getUnidad()+"','S')");
        return msg;
    }

    public static Empresa busEmpresa(String cod) {
        Empresa e = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM v_empresa WHERE idempresa = '");
                sql.append(cod);
                sql.append("'");
//        String sql = "SELECT * FROM unidad WHERE uni_codigo = '"+cod+"'";
        Object[] filaObt = OperacionMovil.getFila(sql.toString());
        if (filaObt != null) {
            e = new Empresa();
            e.setCodEmpresa(Integer.parseInt(filaObt[0].toString()));
            e.setEmpresa(filaObt[1].toString());
        } else {
        }
        return e;
    }

    public static List listEmpresa() {
        String sql = "select * from v_empresa1 WHERE estado='S'";
        return OperacionMovil.getTabla(sql);
    }

   /* public static String actEmpresa(Empresa e) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE empresa SET em_razonsocial = '").append(e.getEmpresa());
        sql.append("',em_ruc='").append(e.getRuc());
        sql.append("',em_direccion='").append(e.getDireccion());
        sql.append("',em_telefono='").append(e.getTelefono());
        sql.append("',em_celular='").append(e.getCelular());
        sql.append("',em_visualizar='").append(e.getVisual());
        sql.append("',usu='").append(e.getUsuario());
        sql.append("' WHERE em_codigo=").append(e.getCodEmpresa()).append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    */
    public static String actEmpresa(Empresa e) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE empresa SET razon_social = '").append(e.getEmpresa());
        sql.append("',ruc='").append(e.getRuc());
        sql.append("',direccion='").append(e.getDireccion());
        sql.append("',telefono='").append(e.getTelefono());
        sql.append("',ciudad_idciudad=").append(e.getIdciudad());
        sql.append(" WHERE idempresa=").append(e.getCodEmpresa()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delEmpresa(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE empresa SET estado='N' ");
                sql.append(" WHERE idempresa = ");
                sql.append(cod);
                sql.append("");
//        String sql = "UPDATE unidad SET uni_indicador='N' WHERE uni_codigo = "+cod+"";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

}
