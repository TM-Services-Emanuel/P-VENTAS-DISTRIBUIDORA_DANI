package Datos;

import Componentes.Login;
import Componentes.Operacion;
import Modelo.DetalleStock;
import Modelo.Stock;
import static java.time.Instant.now;

public class GestionarStock {

    static String UsuarioL = "";
    public static String addDetalleStock(DetalleStock ds) {
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO detalle_actualizarstock (motivo_mot_codigo, articulo_art_codigo, act_fechahora, act_cant_a, act_cant_n, act_obs, usu)"
                + " VALUES (");
        sql.append(ds.getCodMot());
        sql.append(",");
        sql.append(ds.getCodArt());
        sql.append(",");
        sql.append("now()");
        sql.append(",");
        sql.append(ds.getStock_a());
        sql.append(",");
        sql.append(ds.getStock_n());
        sql.append(",'");
        sql.append(ds.getObs());
        sql.append("','");
        sql.append(usuario);
        sql.append("')");
        
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO stock  VALUES (" + s.getCod() + "," + s.getStock() + ",'S')");
        return msg;
    }

    public static String actStock(Stock s) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE articulo SET art_stock = ");
        sql.append(s.getStock());
        sql.append(" WHERE art_codigo= ");
        sql.append(s.getCod());
        sql.append("");
//        String sql = "UPDATE stock SET sto_cantidad = " + s.getStock() + " WHERE sto_articulo = " + s.getCod() + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    /*public static Stock busStock(String cod) {
        Stock st = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM stock WHERE sto_articulo = ");
        sql.append(cod);
        sql.append("");
//        String sql = "SELECT * FROM stock WHERE sto_articulo = " + cod + "";
        Object[] fila = Operacion.getFila(sql.toString());
        if (fila != null) {
            st = new Stock();
            st.setCod(Integer.parseInt(fila[0].toString()));
            st.setStock(Double.parseDouble(fila[1].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return st;
    }*/
    public static Stock busStock(String cod) {
        Stock st = null;
        StringBuilder sql = new StringBuilder("SELECT art_codigo, art_stock FROM articulo WHERE art_codigo=");
        sql.append(cod);
        sql.append("");
//        String sql = "SELECT * FROM stock WHERE sto_articulo = " + cod + "";
        Object[] fila = Operacion.getFila(sql.toString());
        if (fila != null) {
            st = new Stock();
            st.setCod(Integer.valueOf(fila[0].toString()));
            st.setStock(Double.parseDouble(fila[1].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return st;
    }

    /*public static String delStock(String c) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE stock SET sto_indicador = 'N' WHERE sto_articulo = ");
        sql.append(c);
//        String sql = "UPDATE stock SET sto_indicador = 'N' WHERE sto_articulo = " + c + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }*/

}