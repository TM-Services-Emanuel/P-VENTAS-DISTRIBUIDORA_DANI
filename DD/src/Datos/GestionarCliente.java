package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.ClienteMovil;
import java.util.List;

public class GestionarCliente {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(idcliente) FROM clientes");
        return cod;
    }

    public static String addCliente(ClienteMovil c) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO clientes  VALUES (");
        sql.append(c.getidCliente()).append(",'");
        sql.append(c.getRazonSocial()).append("','");
        sql.append(c.getRuc()).append("','");
        sql.append(c.getDireccion()).append("','");
        sql.append(c.getTelefono()).append("','S',");
        sql.append(c.getCodCiudad()).append(")");
        msg = OperacionMovil.exeOperacion(sql
                .toString());
        return msg;
    }

    public static String actCliente(ClienteMovil c) {
        String msg;
        //StringBuilder sql = new StringBuilder("UPDATE clientes SET cli_razonSocial='");
        StringBuilder sql = new StringBuilder("UPDATE clientes SET razon_social='");
        sql.append(c.getRazonSocial()).append("', ruc='");
        sql.append(c.getRuc()).append("',direccion='");
        sql.append(c.getDireccion()).append("', telefono='");
        sql.append(c.getTelefono()).append("',ciudad_idciUdad=");
        sql.append(c.getCodCiudad()).append(" WHERE idcliente=");
        sql.append(c.getidCliente()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String delCliente(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE clientes SET estado='N' WHERE idcliente =");
        sql.append(cod).append("");
//        String sql = "UPDATE clientes SET cli_indicador='N' WHERE cli_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static ClienteMovil busCliente(String cod) {
        ClienteMovil c = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM clientes WHERE idcliente = ");
        sql.append(cod).append("");
        Object[] filaObt = OperacionMovil.getFila(sql.toString());
        if (filaObt != null) {
            c = new ClienteMovil();
            c.setidCliente(Integer.parseInt(filaObt[0].toString()));
            c.setRazonSocial(filaObt[1].toString());
            c.setRuc(filaObt[2].toString());
            c.setDireccion(filaObt[3].toString());
            c.setTelefono(filaObt[4].toString());
            c.setCodCiudad(Integer.parseInt(filaObt[6].toString()));
            
        } else {
            System.out.println("No encotrado");
        }
        return c;
    }

    public static ClienteMovil busClienteRuc(String cod) {
        ClienteMovil c = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM clientes WHERE ruc LIKE '%");
        sql.append(cod).append("%'");
//        String sql = "SELECT * FROM clientes WHERE cli_ruc LIKE '" + cod + "%'";
        Object[] filaObt = OperacionMovil.getFila(sql.toString());
        if (filaObt != null) {
            c = new ClienteMovil();
            c.setidCliente(Integer.parseInt(filaObt[0].toString()));
            c.setRazonSocial(filaObt[1].toString());
            c.setRuc(filaObt[2].toString());
            c.setDireccion(filaObt[3].toString());
            c.setTelefono(filaObt[4].toString());
            c.setCodCiudad(Integer.parseInt(filaObt[6].toString()));
        } else {
            System.out.println("No encotrado");
        }
        return c;
    }

    public static List listClientes(String cod) {
        StringBuilder sql = new StringBuilder("SELECT clientes.idcliente,");
        sql.append("clientes.razon_social,");
        sql.append("clientes.ruc,");
        sql.append("clientes.direccion,");
        sql.append("clientes.telefono,");
        sql.append("ciudad.ciudad");
        sql.append(" FROM clientes ");
        sql.append(" JOIN ciudad ON clientes.ciudad_idciudad = ciudad.idciudad");
        sql.append(" WHERE clientes.estado = 'S'");
        //sql.append(" LIMIT 4000 ");
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List filRazonS(String cad) {
        StringBuilder sql = new StringBuilder("SELECT clientes.idcliente,");
        sql.append("clientes.razon_social,");
        sql.append("clientes.ruc,");
        sql.append("clientes.direccion,");
        sql.append("clientes.telefono,");
        sql.append("ciudad.ciudad");
        sql.append(" FROM clientes ");
        sql.append(" JOIN ciudad ON clientes.ciudad_idciudad = ciudad.idciudad");
        sql.append(" WHERE clientes.estado='S' AND clientes.razon_social LIKE '%").append(cad).append("%'");
        sql.append(" OR clientes.ruc LIKE '%").append(cad).append("%'");
        return OperacionMovil.getTabla(sql.toString());
    }
    public static List filRuc(String cad) {
        StringBuilder sql = new StringBuilder("SELECT clientes.idcliente,");
        sql.append("clientes.razon_social,");
        sql.append("clientes.ruc,");
        sql.append("clientes.direccion,");
        sql.append("clientes.telefono,");
        sql.append("ciudad.ciudad");
        sql.append(" FROM clientes ");
        sql.append(" JOIN ciudad ON clientes.ciudad_idciudad = ciudad.idciudad");
        sql.append(" WHERE (((clientes.estado) = 'S') AND ((clientes.ruc) LIKE '%").append(cad).append("%'))");
        return OperacionMovil.getTabla(sql.toString());
    }

   /* public static List filRuc(String cad) {
        StringBuilder sql = new StringBuilder("SELECT clientes.cli_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("clientes.cli_contacto,");
        sql.append("vendedor.ven_nombre,");
        sql.append("clientes.cli_telefono,");
        sql.append("clientes.cli_celular,");
        sql.append("clientes.cli_fax,");
        sql.append("rubro.rub_nombre,");
        sql.append("clientes.cli_ruc,");
        sql.append("clientes.cli_limitecuenta,");
        sql.append("clientes.cli_direccion,");
        sql.append("clientes.cli_email,");
        sql.append("clientes.cli_web,");
        sql.append("transporte.tra_nombre,");
        sql.append("provincias.prv_nombre ");
        sql.append(" FROM clientes ");
        sql.append(" JOIN vendedor ON clientes.cli_vendedor = vendedor.ven_codigo ");
        sql.append(" JOIN rubro ON clientes.cli_rubro = rubro.rub_codigo ");
        sql.append(" JOIN transporte ON clientes.cli_transporte = transporte.tra_codigo ");
        sql.append(" JOIN provincias ON clientes.cli_provincia = provincias.prv_codigo AND clientes.cli_provincia = provincias.prv_codigo ");
        sql.append("  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_ruc) LIKE '").append(cad).append("%'))");
//        String sq = "SELECT clientes.cli_codigo,"
//                + "    clientes.cli_razonsocial,"
//                + "    clientes.cli_contacto,"
//                + "    vendedor.ven_nombre,"
//                + "    clientes.cli_telefono,"
//                + "    clientes.cli_celular,"
//                + "    clientes.cli_fax,"
//                + "    rubro.rub_nombre,"
//                + "    clientes.cli_ruc,"
//                + "    clientes.cli_limitecuenta,"
//                + "    clientes.cli_direccion,"
//                + "    clientes.cli_email,"
//                + "    clientes.cli_web,"
//                + "    transporte.tra_nombre,"
//                + "    provincias.prv_nombre"
//                + "   FROM ((((clientes"
//                + "   JOIN vendedor ON ((clientes.cli_vendedor = vendedor.ven_codigo)))"
//                + "   JOIN rubro ON ((clientes.cli_rubro = rubro.rub_codigo)))"
//                + "   JOIN transporte ON ((clientes.cli_transporte = transporte.tra_codigo)))"
//                + "   JOIN provincias ON (((clientes.cli_provincia = provincias.prv_codigo) AND (clientes.cli_provincia = provincias.prv_codigo))))"
//                + "  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_ruc) LIKE '" + cad + "%'))";
        return Operacion.getTabla(sql.toString());
    }

    public static List filContacto(String cad) {
        StringBuilder sql = new StringBuilder("SELECT clientes.cli_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("clientes.cli_contacto,");
        sql.append("vendedor.ven_nombre,");
        sql.append("clientes.cli_telefono,");
        sql.append("clientes.cli_celular,");
        sql.append("clientes.cli_fax,");
        sql.append("rubro.rub_nombre,");
        sql.append("clientes.cli_ruc,");
        sql.append("clientes.cli_limitecuenta,");
        sql.append("clientes.cli_direccion,");
        sql.append("clientes.cli_email,");
        sql.append("clientes.cli_web,");
        sql.append("transporte.tra_nombre,");
        sql.append("provincias.prv_nombre ");
        sql.append(" FROM clientes ");
        sql.append(" JOIN vendedor ON clientes.cli_vendedor = vendedor.ven_codigo ");
        sql.append(" JOIN rubro ON clientes.cli_rubro = rubro.rub_codigo ");
        sql.append(" JOIN transporte ON clientes.cli_transporte = transporte.tra_codigo ");
        sql.append(" JOIN provincias ON clientes.cli_provincia = provincias.prv_codigo AND clientes.cli_provincia = provincias.prv_codigo ");
        sql.append("  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_contacto) LIKE '").append(cad).append("%'))");
//        String sq = "SELECT clientes.cli_codigo,"
//                + "    clientes.cli_razonsocial,"
//                + "    clientes.cli_contacto,"
//                + "    vendedor.ven_nombre,"
//                + "    clientes.cli_telefono,"
//                + "    clientes.cli_celular,"
//                + "    clientes.cli_fax,"
//                + "    rubro.rub_nombre,"
//                + "    clientes.cli_ruc,"
//                + "    clientes.cli_limitecuenta,"
//                + "    clientes.cli_direccion,"
//                + "    clientes.cli_email,"
//                + "    clientes.cli_web,"
//                + "    transporte.tra_nombre,"
//                + "    provincias.prv_nombre"
//                + "   FROM ((((clientes"
//                + "   JOIN vendedor ON ((clientes.cli_vendedor = vendedor.ven_codigo)))"
//                + "   JOIN rubro ON ((clientes.cli_rubro = rubro.rub_codigo)))"
//                + "   JOIN transporte ON ((clientes.cli_transporte = transporte.tra_codigo)))"
//                + "   JOIN provincias ON (((clientes.cli_provincia = provincias.prv_codigo) AND (clientes.cli_provincia = provincias.prv_codigo))))"
//                + "  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_contacto) LIKE '" + cad + "%'))";
        return Operacion.getTabla(sql.toString());
    }

    public static List filDireccion(String cad) {
        StringBuilder sql = new StringBuilder("SELECT clientes.cli_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("clientes.cli_contacto,");
        sql.append("vendedor.ven_nombre,");
        sql.append("clientes.cli_telefono,");
        sql.append("clientes.cli_celular,");
        sql.append("clientes.cli_fax,");
        sql.append("rubro.rub_nombre,");
        sql.append("clientes.cli_ruc,");
        sql.append("clientes.cli_limitecuenta,");
        sql.append("clientes.cli_direccion,");
        sql.append("clientes.cli_email,");
        sql.append("clientes.cli_web,");
        sql.append("transporte.tra_nombre,");
        sql.append("provincias.prv_nombre ");
        sql.append(" FROM clientes ");
        sql.append(" JOIN vendedor ON clientes.cli_vendedor = vendedor.ven_codigo ");
        sql.append(" JOIN rubro ON clientes.cli_rubro = rubro.rub_codigo ");
        sql.append(" JOIN transporte ON clientes.cli_transporte = transporte.tra_codigo ");
        sql.append(" JOIN provincias ON clientes.cli_provincia = provincias.prv_codigo AND clientes.cli_provincia = provincias.prv_codigo ");
        sql.append("  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_direccion) LIKE '").append(cad).append("%'))");
//        String sq = "SELECT clientes.cli_codigo,"
//                + "    clientes.cli_razonsocial,"
//                + "    clientes.cli_contacto,"
//                + "    vendedor.ven_nombre,"
//                + "    clientes.cli_telefono,"
//                + "    clientes.cli_celular,"
//                + "    clientes.cli_fax,"
//                + "    rubro.rub_nombre,"
//                + "    clientes.cli_ruc,"
//                + "    clientes.cli_limitecuenta,"
//                + "    clientes.cli_direccion,"
//                + "    clientes.cli_email,"
//                + "    clientes.cli_web,"
//                + "    transporte.tra_nombre,"
//                + "    provincias.prv_nombre"
//                + "   FROM ((((clientes"
//                + "   JOIN vendedor ON ((clientes.cli_vendedor = vendedor.ven_codigo)))"
//                + "   JOIN rubro ON ((clientes.cli_rubro = rubro.rub_codigo)))"
//                + "   JOIN transporte ON ((clientes.cli_transporte = transporte.tra_codigo)))"
//                + "   JOIN provincias ON (((clientes.cli_provincia = provincias.prv_codigo) AND (clientes.cli_provincia = provincias.prv_codigo))))"
//                + "  WHERE (((clientes.cli_indicador) = 'S') AND ((clientes.cli_direccion) LIKE '" + cad + "%'))";
        return Operacion.getTabla(sql.toString());
    }
*/
}
