package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.ArticuloMovil;
import java.io.FileInputStream;
import java.util.List;

public class GestionarArticulosMovil {

    static FileInputStream fis;

    public static String getCodigo() {
        String cod = generarCodigos.getCodigoMovil("select MAX(idproducto) from productos");
        return cod;
    }

    public static String addArticulo(ArticuloMovil art) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO productos VALUES (");
        sql.append(art.getIdproducto()).append(", '");
        sql.append(art.getDependencia()).append("',");
        sql.append(art.getIddependencia()).append(",'");
        sql.append(art.getCodinterno()).append("','");
        sql.append(art.getCodBarra()).append("', '");
        sql.append(art.getDescripcion()).append("', ");
        sql.append(art.getPrecio_costo()).append(", ");
        sql.append(art.getGanancia()).append(", ");
        sql.append(art.getPrecio_venta()).append(", ");
        sql.append(art.getStock()).append(", 'S', ");
        sql.append(art.getUm()).append(", ");
        sql.append(art.getDivision()).append(", ");
        sql.append(art.getIva()).append(",'");
        sql.append(art.getVentam()).append("',");
        sql.append(art.getCantm()).append(",");
        sql.append(art.getPreciominorista()).append(",");
        sql.append(art.getGananciaminorista()).append(")");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }


    public static String actArticulo(ArticuloMovil art) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE productos SET dependencia='");
        sql.append(art.getDependencia()).append("', iddependencia=");
        sql.append(art.getIddependencia()).append(", cod_interno='");
        sql.append(art.getCodinterno()).append("', cod_barra='");
        sql.append(art.getCodBarra()).append("', descripcion='");
        sql.append(art.getDescripcion()).append("', precio_costo=");
        sql.append(art.getPrecio_costo()).append(", ganancia=");
        sql.append(art.getGanancia()).append(", precio_venta=");
        sql.append(art.getPrecio_venta()).append(", stock=");
        sql.append(art.getStock()).append(", um_idunidad=");
        sql.append(art.getUm()).append(", division_iddivision=");
        sql.append(art.getDivision()).append(", iva_idiva=");
        sql.append(art.getIva()).append(", ventam='");
        sql.append(art.getVentam()).append("', cantm=");
        sql.append(art.getCantm()).append(", preciominorista=");
        sql.append(art.getPreciominorista()).append(", gananciaminorista=");
        sql.append(art.getGananciaminorista());
        sql.append(" WHERE idproducto=").append(art.getIdproducto()).append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    public static String actStock(ArticuloMovil art) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE productos SET stock=");
        sql.append(art.getStock());
        sql.append(" WHERE idproducto=");
        sql.append(art.getIdproducto());
        sql.append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static String actStockMENOS(ArticuloMovil art) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE productos SET stock=(stock-");
        sql.append(art.getStock());
        sql.append(") WHERE idproducto=");
        sql.append(art.getIdproducto());
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    public static String actStockMAS(ArticuloMovil art) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE productos SET stock=(stock+");
        sql.append(art.getStock());
        sql.append(") WHERE idproducto=");
        sql.append(art.getIdproducto());
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
    
   /* public static String InactivarArticulo(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE articulo SET art_activo = 'N' WHERE art_codigo = ");
        sql.append(cod);
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }*/
   /* public static String ActivarArticulo(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE articulo SET art_activo = 'S' WHERE art_codigo = ");
        sql.append(cod);
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }*/

    public static String delArticulo(String cod) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE productos SET estado = 'N'");
        sql.append(" WHERE idproducto= ");
        sql.append(cod);
        sql.append("");
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }

    public static ArticuloMovil busArticulo(String cod) {
        ArticuloMovil ar = null;
        StringBuilder s = new StringBuilder("SELECT * FROM productos WHERE idproducto= ");
        s.append(cod);
        s.append("");
        Object[] filaObt = OperacionMovil.getFila(s.toString());
        if (filaObt != null) {
            ar = new ArticuloMovil();
            ar.setIdproducto(Integer.parseInt(filaObt[0].toString()));
            ar.setDependencia(filaObt[1].toString());
            ar.setIddependencia(Integer.parseInt(filaObt[2].toString()));
            ar.setCodinterno(filaObt[3].toString());
            ar.setCodBarra(filaObt[4].toString());
            ar.setDescripcion(filaObt[5].toString());
            ar.setPrecio_costo(Integer.parseInt(filaObt[6].toString()));
            ar.setGanancia(Integer.parseInt(filaObt[7].toString()));
            ar.setPrecio_venta(Integer.parseInt(filaObt[8].toString()));
            ar.setStock(Double.parseDouble(filaObt[9].toString()));
            ar.setUm(Integer.parseInt(filaObt[11].toString()));
            ar.setDivision(Integer.parseInt(filaObt[12].toString()));
            ar.setIva(Integer.parseInt(filaObt[13].toString()));
            ar.setVentam((filaObt[14].toString()));
            ar.setCantm(Double.parseDouble(filaObt[15].toString()));
            ar.setPreciominorista(Integer.parseInt(filaObt[16].toString()));
            ar.setGananciaminorista(Integer.parseInt(filaObt[17].toString()));
            
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }
        return ar;
    }
    
    public static List listArticulo(String cod) {
                /*String sql = new StringBuffer("SELECT idproducto, cod_interno, cod_barra, despcripcion, precio_venta,"
                        + "impu, medida, clasif FROM v_productos")*/
                String sql = new StringBuffer("SELECT idproducto, dependencia, clasif, cod_interno, cod_barra,"
                        + " descripcion, medida,precio_costo,ganancia, precio_venta, stock, impu, ventam, gananciaminorista, preciominorista FROM v_productos")
                .append(" ORDER BY ").append(cod)
                .toString();
        return OperacionMovil.getTabla(sql);
    }
    
    public static List listArticuloconStock(String cod) {
                /*String sql = new StringBuffer("SELECT idproducto, cod_interno, cod_barra, despcripcion, precio_venta,"
                        + "impu, medida, clasif FROM v_productos")*/
                String sql = new StringBuffer("SELECT idproducto, dependencia, clasif, cod_interno, cod_barra,"
                        + " descripcion, medida,precio_costo,ganancia, precio_venta, stock, impu, ventam, gananciaminorista, preciominorista FROM v_productos where stock > 0")
                .append(" ORDER BY ").append(cod)
                .toString();
        return OperacionMovil.getTabla(sql);
    }
    

    /*public static List filtrarDescripcion(String cad) {
                String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva, ")
                .append("ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo ")
                .append("WHERE descripcion LIKE '%")
                .append(cad)
                .append("%' AND ind='S' ORDER BY fam")
                .toString();
        return Operacion.getTabla(sql);
    }*/
   /* public static List filtrarDescripcionActivo(String cad) {
        String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva, ")
                .append("ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo ")
                .append("WHERE descripcion LIKE '%")
                .append(cad)
                .append("%' AND ind='S' AND activo='SI' ORDER BY fam")
                .toString();
        return Operacion.getTabla(sql);
    }*/

    /*public static List filtrarPrincipioActivo(String cad) {
        String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva, ")
                .append("ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo ")
                .append("WHERE principio LIKE '%")
                .append(cad)
                .append("%' AND ind='S' AND activo='SI' ORDER BY fam")
                .toString();
        return Operacion.getTabla(sql);
    }*/

   /* public static List filtrarCodBarra(String cad) {
        String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva, ")
                .append("ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo ")
                .append("WHERE barra LIKE '%")
                .append(cad)
                .append("%' AND ind= 'S' ORDER BY fam")
                .toString();
        return Operacion.getTabla(sql);
    }*/
    /*public static List listArticuloActivo(String cod) {
                String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva,")
                .append(" ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo_activo")
                //.append(" WHERE activo='SI' AND ind= 'S'")
                //.append(" ORDER BY fam")
               // .append(cod)
                .toString();
        return Operacion.getTabla(sql);
    }*/
    public static List filtrarCodBarraActivo(String cad) {
                String sql = new StringBuffer("SELECT idproducto, cod_interno, cod_barra, descripcion, precio_costo FROM v_productos")
                .append(" WHERE cod_interno LIKE '")
                .append(cad)
                .append("' OR cod_barra LIKE '")
                .append(cad)
                .append("' OR descripcion LIKE '%")
                .append(cad)
                .append("%' ORDER BY idproducto DESC")
                .toString();
        return OperacionMovil.getTabla(sql);
    }
    public static List filtrarArticulosActivoAuxiliarReparto(String cad) {
                String sql = new StringBuffer("SELECT idproducto, cod_interno, cod_barra, descripcion, precio_venta FROM v_productos")
                .append(" WHERE cod_interno LIKE '")
                .append(cad)
                .append("' OR cod_barra LIKE '")
                .append(cad)
                .append("' OR descripcion LIKE '%")
                .append(cad)
                .append("%' ORDER BY idproducto DESC")
                .toString();
        return OperacionMovil.getTabla(sql);
    }
    
    public static List filtrarGral(String cad) {
              /* String sql = new StringBuffer("SELECT idproducto, cod_interno, cod_barra, despcripcion, precio_venta,"
                        + "impu, medida, clasif FROM v_productos")*/
              String sql = new StringBuffer("SELECT idproducto, dependencia, clasif, cod_interno, cod_barra,"
                        + " descripcion, medida, precio_costo, ganancia, precio_venta,stock, impu, ventam, gananciaminorista, preciominorista FROM v_productos")
                .append(" WHERE cod_interno LIKE '")
                .append(cad)
                .append("' OR cod_barra LIKE '")
                .append(cad)
                .append("' OR descripcion LIKE '%")
                .append(cad)
                .append("%' order by idproducto DESC")
                .toString();
        return OperacionMovil.getTabla(sql);
    }
    /*public static List filtrarCodBarraActivo(String cad) {
        String sql = new StringBuffer("SELECT cod, barra, fam, lab, prov, descripcion, principio, accion, stock, stockmin, costo, iva, costoiva, ")
                .append("ppublico, des, pventa,ganstandar, gan, venta, activo FROM v_articulo ")
                .append("WHERE barra LIKE '%")
                .append(cad)
                .append("%' OR descripcion LIKE '%")
                .append(cad)
                .append("%' OR principio LIKE '%")
                .append(cad)
                .append("%' AND activo='SI' AND ind= 'S' ORDER BY fam")
                .toString();
        return Operacion.getTabla(sql);
    }*/

}
