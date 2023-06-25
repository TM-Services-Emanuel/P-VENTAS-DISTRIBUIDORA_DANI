package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.DetalleFactura;
import Modelo.Factura;
import java.util.*;

public class GestionarFactura {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(fac_codigo) from factura");
        return cod;
    }
    public static String getFactura() {
        String cod = generarCodigos.getCodigo("SELECT MAX(fac_factura) from factura");
        return cod;
    }

    public static String addFactura(Factura f) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO factura VALUES (");
        sql.append(f.getCodFactura()).append(",");
        sql.append(f.getCodCliente()).append(",");
        sql.append(f.getCodVendedor()).append(",");
        sql.append(f.getDescuento()).append(",'");
        sql.append(f.getTipoPago()).append("','");
        sql.append(f.getFecha()).append("','");
        sql.append(Fecha.darHora()).append("','");
        sql.append(f.getNeto()).append("' ,");
        sql.append(f.getTotal()).append(",'S')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO factura VALUES ("
//                + f.getCodFactura() + ","
//                + f.getCodCliente() + ","
//                + f.getCodVendedor() + ","
//                + f.getDescuento() + ",'"
//                + f.getTipoPago() + "','"
//                + f.getFecha() + "','"
//                + Fecha.darHora() + "','"
//                + f.getNeto() + "' ,"
//                + f.getTotal() + ",'S')");
        return msg;
    }

    public static String actFactura(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE factura SET fac_indicador='N', usu='"+usuario+"' WHERE fac_codigo=");
        sql.append(cod).append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }
    
    public static String actFacturaMovil(String cod, String idemision) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ventas_1 SET estado='N' WHERE idventas=");
        sql.append(cod).append(" AND idemision=").append(idemision);
        msg = OperacionMovil.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }
    
    public static String actFacturaMovil1(String cod, String idemision) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ventas SET estado='N' WHERE idventas=");
        sql.append(cod).append(" AND idemision=").append(idemision);
        msg = OperacionMovil.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }

    public static String addDetalleFactura(DetalleFactura df) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO detalle_factura VALUES (0,");
        sql.append(df.getCodFactura()).append(",");
        sql.append(df.getCodArticulo()).append(",");
        sql.append(df.getCantidad()).append(",");
        sql.append(df.getPrecio()).append(",");
        sql.append(df.getTotal()).append(")");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO detalle_factura VALUES ("
//                + df.getCodFactura() + ","
//                + df.getCodArticulo() + ","
//                + df.getCantidad() + ","
//                + df.getPrecio() + ","
//                + df.getTotal() + ")");
        return msg;
    }

    public static Factura busFactura(String cod) {
        Factura fac = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM factura WHERE fac_codigo=");
        sql.append(cod).append("");
//        String sql = "SELECT * FROM factura WHERE fac_codigo=" + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            fac = new Factura();
            fac.setCodFactura(Integer.parseInt(filaObt[0].toString()));
            fac.setCodCliente(Integer.parseInt(filaObt[1].toString()));
            fac.setCodVendedor(Integer.parseInt(filaObt[2].toString()));
            fac.setDescuento(Double.parseDouble(filaObt[3].toString()));
            fac.setTipoPago(filaObt[4].toString());
            fac.setFecha(filaObt[5].toString());
            fac.setHora(filaObt[6].toString());
            fac.setTotal(Double.parseDouble(filaObt[7].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return fac;
    }

    public static List listFacturas() {
        StringBuilder sql = new StringBuilder("SELECT `p-ventasbd`.factura.fac_codigo,");
        sql.append("`bd_distribuidoradani`.clientes.razon_social,");
        sql.append("`p-ventasbd`.factura.fac_fecha,");
        sql.append("`p-ventasbd`.factura.fac_hora,");
        sql.append("`bd_distribuidoradani`.clientes.idcliente,");
        sql.append("`p-ventasbd`.factura.caja_ca_id,");
        sql.append("`p-ventasbd`.factura.fac_factura,");
        sql.append("`p-ventasbd`.factura.fac_tipoventa,");
        sql.append("`p-ventasbd`.factura.estado,");
        sql.append("`p-ventasbd`.factura.fac_totalfinal,");
        sql.append("`p-ventasbd`.vendedor.ven_codigo,");
        sql.append("`p-ventasbd`.factura.fac_indicador");
        sql.append(" FROM `p-ventasbd`.factura ");
        sql.append(" JOIN `p-ventasbd`.vendedor ON `p-ventasbd`.factura.vendedor_ven_codigo = `p-ventasbd`.vendedor.ven_codigo");
        sql.append(" JOIN `bd_distribuidoradani`.clientes ON `p-ventasbd`.factura.clientes_cli_codigo = `bd_distribuidoradani`.clientes.idcliente");
        sql.append(" ORDER BY `p-ventasbd`.factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listFacturasMoviles() {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" ORDER BY idemision ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List listFacturasMovil1() {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" ORDER BY idemision ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List listFacturasMovilesT(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List listFacturasMovilesT1(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    /*public static List listFacturasMovilesT1(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }*/
    public static List listFacturasMovilesTPE(String idPE,String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List listFacturasMovilesTPE1(String idPE,String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }
    
    /*public static List listFacturasMovilesTPE1(String idPE,String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }*/
    
    public static List listFacturasCredito(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    public static List listFacturasCreditoPendiente(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.estado='PENDIENTE'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    public static List listFacturasCreditoActivo(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_indicador='S'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    public static List listFacturasCreditoPendienteActivo(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_indicador='S'");
        sql.append(" AND factura.estado='PENDIENTE'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List lisFacturas2() {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.fac_total,");
        sql.append("vendedor.ven_codigo ");
        sql.append(" FROM ((factura ");
        sql.append(" JOIN vendedor ON ((factura.vendedor_ven_codigo = vendedor.ven_codigo))) ");
        sql.append(" JOIN clientes ON ((factura.clientes_cli_codigo = clientes.cli_codigo))) ");
        sql.append(" WHERE (((factura.fac_indicador) = 'S') AND (NOT (EXISTS ( SELECT factura.fac_codigo ");
        sql.append(" FROM notacredito ");
        sql.append(" WHERE (factura.fac_codigo = notacredito.nc_factura)))))");
        return Operacion.getTabla(sql.toString());
    }

//    public static List listFacturasAnuladas() {
//        String sql = "SELECT factura.fac_codigo, clientes.cli_razonSocial, factura.fac_fecha, clientes.cli_codigo, factura.fac_descuento,factura.fac_total, vendedor.ven_codigo FROM factura, clientes, vendedor where factura.fac_cliente = clientes.cli_codigo AND factura.fac_vendedor = vendedor.ven_codigo AND factura.fac_indicador='N'";
//        return Operacion.getTabla(sql);
//    }
    public static List fillCliente(String nom) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.fac_descuento,");
        sql.append("factura.fac_total,");
        sql.append("vendedor.ven_codigo ");
        sql.append(" FROM ((factura ");
        sql.append(" JOIN vendedor ON ((factura.fac_vendedor = vendedor.ven_codigo))) ");
        sql.append(" JOIN clientes ON ((factura.fac_cliente = clientes.cli_codigo))) ");
        sql.append(" WHERE ");
        sql.append("(((factura.fac_indicador) = 'S') AND ");
        sql.append(" (NOT (EXISTS ( SELECT factura.fac_codigo ");
        sql.append(" FROM notacredito ");
        sql.append("  WHERE (factura.fac_codigo = notacredito.nc_factura)))) AND clientes.cli_razonsocial ILIKE '");
        sql.append(nom).append("%')");
//        String sql = "SELECT factura.fac_codigo,"
//                + "    clientes.cli_razonsocial,"
//                + "    factura.fac_fecha,"
//                + "    clientes.cli_codigo,"
//                + "    factura.fac_descuento,"
//                + "    factura.fac_total,"
//                + "    vendedor.ven_codigo"
//                + "   FROM ((factura"
//                + "   JOIN vendedor ON ((factura.fac_vendedor = vendedor.ven_codigo)))"
//                + "   JOIN clientes ON ((factura.fac_cliente = clientes.cli_codigo)))"
//                + "   WHERE "
//                + "(((factura.fac_indicador) = 'S') AND "
//                + "(NOT (EXISTS ( SELECT factura.fac_codigo"
//                + "   FROM notacredito"
//                + "  WHERE (factura.fac_codigo = notacredito.nc_factura)))) AND clientes.cli_razonsocial ILIKE '" + nom + "%')";
        return Operacion.getTabla(sql.toString());
    }

    public static List listDetalles(String cod) {
        StringBuilder sql = new StringBuilder("SELECT `p-ventasbd`.detalle_factura.ven_cantidad,`p-ventasbd`.detalle_factura.dependencia,`p-ventasbd`.detalle_factura.iddependencia,");
        sql.append("`p-ventasbd`.detalle_factura.articulo_art_codigo,");
        sql.append("bd_distribuidoradani.productos.cod_interno,");
        sql.append("bd_distribuidoradani.productos.descripcion,");
        sql.append("`p-ventasbd`.detalle_factura.ven_precio,");
        sql.append("`p-ventasbd`.detalle_factura.ven_total");
        sql.append(" FROM `p-ventasbd`.detalle_factura");
        sql.append(" JOIN bd_distribuidoradani.productos ON `p-ventasbd`.detalle_factura.articulo_art_codigo = bd_distribuidoradani.productos.idproducto");
        sql.append(" JOIN `p-ventasbd`.factura ON `p-ventasbd`.detalle_factura.factura_fac_codigo = `p-ventasbd`.factura.fac_codigo");
        sql.append(" WHERE `p-ventasbd`.factura.fac_codigo=").append(cod);
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listDetallesVentasMovil(String cod, String idemision) {
        StringBuilder sql = new StringBuilder("SELECT cod_interno,producto,cant,precio,total");
        sql.append(" FROM v_detalleventa1");
        sql.append(" WHERE id=").append(cod);
        sql.append(" AND idemision=").append(idemision);
        return OperacionMovil.getTabla(sql.toString());
    }
    
    public static List listDetallesVentasMovil1(String cod, String idemision) {
        StringBuilder sql = new StringBuilder("SELECT cod_interno,cod_barra,producto,cant,precio,total,impuesto_aplicado,um");
        sql.append(" FROM v_detalleventa_1");
        sql.append(" WHERE id=").append(cod);
        sql.append(" AND idemision=").append(idemision);
        return OperacionMovil.getTabla(sql.toString());
    }

    /*public static List listDetallesF(String cod) {
        StringBuilder sql = new StringBuilder("SELECT detalle_factura.df_cantidad,");
        sql.append("detalle_factura.df_articulo,");
        sql.append("articulo.art_descripcion,");
        sql.append("detalle_factura.df_precarticulo,");
        sql.append("detalle_factura.df_total ");
        sql.append(" FROM ((detalle_factura ");
        sql.append(" JOIN factura ON ((detalle_factura.fac_codigo = factura.fac_codigo))) ");
        sql.append(" JOIN articulo ON ((detalle_factura.df_articulo = articulo.art_codigo))) ");
        sql.append(" WHERE (factura.fac_codigo = ").append(cod).append(")");
        return Operacion.getTabla(sql.toString());
    }*/

}
