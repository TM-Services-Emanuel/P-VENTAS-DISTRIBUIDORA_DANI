package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Compra;
import Modelo.DetalleCompra;
import Modelo.DetallePago;
import java.util.List;

public class GestionarCompra {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(com_codigo) from compra");
        return cod;
    }
    
    public static String getCodigoPago() {
        String cod = generarCodigos.getCodigo("SELECT MAX(idpago) from pagos_proveedor");
        return cod;
    }
    public static String addCompra(Compra c) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO compra VALUES (");
        sql.append(c.getCodCompra()).append(",");
        sql.append(c.getCodProveedor()).append(",'");
        sql.append(c.getCondPago()).append("','");
        sql.append(c.getFact()).append("','");
        sql.append(c.getFecha()).append("','");
        sql.append(Fecha.darHora()).append("',");
        sql.append(c.getTotal()).append(",");
        sql.append(c.getExenta()).append(",");
        sql.append(c.getIVA5()).append(",");
        sql.append(c.getIVA10()).append(",'S')");
        msg = Operacion.exeOperacion(sql.toString());

        return msg;
    }


    public static String actCompra(Compra c) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE compras SET com_codigo=");
        sql.append(c.getCodCompra()).append(",com_proveedor=");
        sql.append(c.getCodProveedor()).append(",com_condPago='");
        sql.append(c.getCondPago()).append("',com_fecha='");
        sql.append(c.getFecha()).append("',com_total=");
        sql.append(c.getTotal()).append(",com_indicador='N' WHERE com_codigo=");
        sql.append(c.getCodCompra()).append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE compras SET com_codigo=" 
//                + c.getCodCompra() + ",com_proveedor=" 
//                + c.getCodProveedor() + ",com_condPago='" 
//                + c.getCondPago() + "',com_fecha='" 
//                + c.getFecha() + "',com_total=" 
//                + c.getTotal() + ",com_indicador='N' WHERE com_codigo=" 
//                + c.getCodCompra() + "");
        return msg;
    }
    
    /*public static String actCompra_despues_anular_pago(DetallePago dp) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE compras SET com_codigo=");
        sql.append(c.getCodCompra()).append(",com_proveedor=");
        sql.append(c.getCodProveedor()).append(",com_condPago='");
        sql.append(c.getCondPago()).append("',com_fecha='");
        sql.append(c.getFecha()).append("',com_total=");
        sql.append(c.getTotal()).append(",com_indicador='N' WHERE com_codigo=");
        sql.append(c.getCodCompra()).append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }*/

    public static String addDetalleCompra(DetalleCompra dc) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO detalle_compra VALUES (");
        sql.append(dc.getCodCompra()).append(",");
        sql.append(dc.getCodArticulo()).append(",");
        sql.append(dc.getCant()).append(",");
        sql.append(dc.getPrecio()).append(",");
        sql.append(dc.getMonto()).append(")");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delCompra(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE compra SET com_indicador='N', usu='"+usuario+"', estado='AN', saldo=0 WHERE com_codigo=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
    public static String delPagosProveedor(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE pagos_proveedor SET indicador='N', usuario='"+usuario+"' WHERE idpago=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listarCompras() {
        StringBuilder sql = new StringBuilder("SELECT compra.com_codigo,");
        sql.append("compra.caja_ca_id,");
        sql.append("compra.tipo,");
        sql.append(" compra.com_fecha,");
        sql.append(" compra.com_hora,");
        sql.append(" proveedor.pro_ruc,");
        sql.append(" proveedor.pro_razonsocial,");
        sql.append(" compra.com_condpago,");
        sql.append(" compra.com_factura,");
        sql.append(" proveedor.pro_codigo,");
        sql.append(" compra.com_total,");
        sql.append(" compra.com_indicador,");
        sql.append(" compra.obs,");
        sql.append(" compra.estado,");
        sql.append(" compra.saldo");
        sql.append(" FROM compra ");
        sql.append(" JOIN proveedor ON compra.proveedor_pro_codigo = proveedor.pro_codigo order by compra.com_codigo");
        //sql.append(" WHERE compra.com_indicador='S'");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarPagosCompras() {
        StringBuilder sql = new StringBuilder("SELECT idpago, idcaja, proveedor.pro_codigo, proveedor.pro_ruc, proveedor.pro_razonsocial, recibo_nro, fecha, hora, ");
        sql.append("monto_pago, indicador FROM pagos_proveedor INNER JOIN proveedor ");
        sql.append("WHERE pagos_proveedor.idproveedor=proveedor.pro_codigo ");
        sql.append("ORDER BY idpago");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarPagosComprasFiltro(int CodProveedor) {
        StringBuilder sql = new StringBuilder("SELECT idpago, idcaja, proveedor.pro_codigo, proveedor.pro_ruc, proveedor.pro_razonsocial, recibo_nro, fecha, hora, ");
        sql.append("monto_pago, indicador FROM pagos_proveedor INNER JOIN proveedor ");
        sql.append("WHERE pagos_proveedor.idproveedor=proveedor.pro_codigo ");
        sql.append("AND proveedor.pro_codigo=").append(CodProveedor);
        sql.append(" ORDER BY idpago");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarComprasFiltro(int CodProveedor) {
        StringBuilder sql = new StringBuilder("SELECT compra.com_codigo,");
        sql.append("compra.caja_ca_id,");
        sql.append("compra.tipo,");
        sql.append(" compra.com_fecha,");
        sql.append(" compra.com_hora,");
        sql.append(" proveedor.pro_ruc,");
        sql.append(" proveedor.pro_razonsocial,");
        sql.append(" compra.com_condpago,");
        sql.append(" compra.com_factura,");
        sql.append(" proveedor.pro_codigo,");
        sql.append(" compra.com_total,");
        sql.append(" compra.com_indicador,");
        sql.append(" compra.obs,");
        sql.append(" compra.estado,");
        sql.append(" compra.saldo");
        sql.append(" FROM compra ");
        sql.append(" JOIN proveedor ON compra.proveedor_pro_codigo = proveedor.pro_codigo");
        sql.append(" WHERE proveedor.pro_codigo=").append(CodProveedor);
        sql.append(" order by compra.com_codigo");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarComprasPendientes(int codProveedor, String desde, String hasta) {
        StringBuilder sql = new StringBuilder("SELECT compra.com_codigo,");
        sql.append("compra.caja_ca_id,");
        //sql.append("compra.tipo,");
        sql.append(" compra.com_fecha,");
        sql.append(" compra.com_hora,");
        //sql.append(" proveedor.pro_ruc,");
        //sql.append(" proveedor.pro_razonsocial,");
       // sql.append(" compra.com_condpago,");
        sql.append(" compra.com_factura,");
        //sql.append(" proveedor.pro_codigo,");
        sql.append(" compra.com_total,");
       // sql.append(" compra.com_indicador,");
       // sql.append(" compra.obs,");
        sql.append(" compra.estado,");
        sql.append(" compra.saldo");
        sql.append(" FROM compra INNER JOIN proveedor ");
        sql.append(" WHERE compra.proveedor_pro_codigo = proveedor.pro_codigo");
        sql.append(" AND proveedor.pro_codigo=").append(codProveedor);
        sql.append(" AND compra.com_fecha>='").append(desde).append("' AND compra.com_fecha<='").append(hasta).append("'");
        sql.append(" AND compra.com_indicador='S'");
        sql.append(" AND compra.estado!='AB'");
        sql.append(" order by compra.com_codigo");
        //sql.append(" WHERE compra.com_indicador='S'");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarComprasReparto(String caja) {
        StringBuilder sql = new StringBuilder("SELECT compra.com_codigo,");
        sql.append("compra.caja_ca_id,");
        sql.append("compra.tipo,");
        sql.append(" compra.com_fecha,");
        sql.append(" compra.com_hora,");
        sql.append(" proveedor.pro_ruc,");
        sql.append(" proveedor.pro_razonsocial,");
        sql.append(" compra.com_condpago,");
        sql.append(" compra.com_factura,");
        sql.append(" proveedor.pro_codigo,");
        sql.append(" compra.com_total,");
        sql.append(" compra.com_indicador,");
        sql.append(" compra.obs");
        sql.append(" FROM compra ");
        sql.append(" JOIN proveedor ON compra.proveedor_pro_codigo = proveedor.pro_codigo");
        sql.append(" WHERE compra.caja_ca_id=").append(caja);
        sql.append(" AND compra.tipo='R'");
        sql.append(" AND compra.com_indicador='S'");
        return Operacion.getTabla(sql.toString());
    }

    public static List listarDetalleCompras(String cod) {
        StringBuilder sql = new StringBuilder("SELECT `p-ventasbd`.detalle_compra.compra_com_codigo,");
        sql.append("bd_distribuidoradani.productos.idproducto,");
        sql.append("bd_distribuidoradani.productos.cod_interno,");
        sql.append("bd_distribuidoradani.productos.descripcion,");
        sql.append("`p-ventasbd`.detalle_compra.com_cantidad,");
        sql.append("`p-ventasbd`.detalle_compra.com_costo,");
        sql.append("`p-ventasbd`.detalle_compra.com_total,");
        sql.append("bd_distribuidoradani.productos.precio_venta, (`p-ventasbd`.detalle_compra.com_cantidad * bd_distribuidoradani.productos.precio_venta) AS venta_total");
        sql.append(" FROM `p-ventasbd`.compra");
        sql.append(" JOIN `p-ventasbd`.detalle_compra ON `p-ventasbd`.detalle_compra.compra_com_codigo = `p-ventasbd`.compra.com_codigo");
        sql.append(" JOIN bd_distribuidoradani.productos ON `p-ventasbd`.detalle_compra.articulo_art_codigo = bd_distribuidoradani.productos.idproducto");
        sql.append(" WHERE `p-ventasbd`.compra.com_codigo =").append(cod);
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarDetallePagosCompras(String cod) {
        StringBuilder sql = new StringBuilder("SELECT idpago, idcompra, compra.com_condpago, compra.com_factura, compra.com_fecha, compra.com_hora, compra.com_total, monto, nc_nro, nc_monto ");
        sql.append("FROM detalle_pagos_proveedor INNER JOIN compra ");
        sql.append("WHERE detalle_pagos_proveedor.idcompra=compra.com_codigo ");
        sql.append("AND idpago=").append(cod);
        sql.append(" ORDER BY orden asc");
        return Operacion.getTabla(sql.toString());
    }

}
