/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.reparto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class GestionarReparto {
    
        public static String getCodigoReparto() {
        String cod = generarCodigos.getCodigoMovil("SELECT MAX(id_reparto) from reparto");
        return cod;
    }
        
        public static reparto busReparto(int cod) {
        reparto rep = null;
        StringBuilder s = new StringBuilder("SELECT * FROM reparto WHERE id_reparto=");
        s.append(cod);
        s.append(" AND activo='S'");
        Object[] filaObt = OperacionMovil.getFila(s.toString());
        if (filaObt != null) {
            rep = new reparto();
            rep.setId_reparto(Integer.parseInt(filaObt[0].toString()));
            rep.setId_caja(Integer.parseInt(filaObt[1].toString()));
            rep.setId_responsable(Integer.parseInt(filaObt[2].toString()));
            rep.setComision_resp(Double.parseDouble(filaObt[3].toString()));
            rep.setIdMovil(Integer.parseInt(filaObt[4].toString()));
            rep.setPreferencia((filaObt[5].toString()));
            rep.setIdchofer(Integer.parseInt(filaObt[6].toString()));
            rep.setChofer((filaObt[7].toString()));
            rep.setComision_chof(Double.parseDouble(filaObt[8].toString()));
            rep.setFecha((Date)(filaObt[9]));
            rep.setHora((Date)(filaObt[10]));
            rep.setValorRA(Integer.parseInt(filaObt[11].toString()));
            rep.setValorC(Integer.parseInt(filaObt[12].toString()));
            rep.setValorT(Integer.parseInt(filaObt[13].toString()));
            rep.setValorTotal(Integer.parseInt(filaObt[14].toString()));
            rep.setValorRecambio(Integer.parseInt(filaObt[15].toString()));
            rep.setValorDevuelto(Integer.parseInt(filaObt[16].toString()));
            rep.setEfectivoEntregar(Integer.parseInt(filaObt[17].toString()));
            rep.setEfectivoEntregado(Integer.parseInt(filaObt[18].toString()));
            rep.setDiferencia(Integer.parseInt(filaObt[19].toString()));
            rep.setRecolectorA(Integer.parseInt(filaObt[20].toString()));
            rep.setRecolectorDA(Integer.parseInt(filaObt[21].toString()));
            rep.setRecolectorDAC(Integer.parseInt(filaObt[22].toString()));
            rep.setRecolectorI(Integer.parseInt(filaObt[23].toString()));
            rep.setRecolectorT(Integer.parseInt(filaObt[24].toString()));
            rep.setRecolectorF(Integer.parseInt(filaObt[25].toString()));
            rep.setRecolectorV(Integer.parseInt(filaObt[26].toString()));
            rep.setRecolectorDF(Integer.parseInt(filaObt[27].toString()));
            rep.setRecolectorDFAC(Integer.parseInt(filaObt[28].toString()));
            rep.setkm_anterior(Integer.parseInt(filaObt[31].toString()));
            rep.setkm_actual(Integer.parseInt(filaObt[32].toString()));
            rep.setkm_recorrido(Integer.parseInt(filaObt[33].toString()));
            rep.setActivo((filaObt[34].toString()));
            rep.setCerrado((filaObt[35].toString()));
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }
        return rep;
    }
    
        public static List listarDetalleReparto(int cod) {
        StringBuilder sql = new StringBuilder("SELECT * FROM v_detalle_reparto");
        sql.append(" WHERE idreparto=").append(cod);
        return OperacionMovil.getTabla(sql.toString());
    }
        
        public static String delReparto(int cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE reparto SET activo='N', usuario='"+usuario+"' WHERE id_reparto=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
        public static String delComision(int cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE comisiones SET usuario ='"+usuario+"', estado='N' WHERE idreparto=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = OperacionMovil.exeOperacion(sql.toString());
        return msg;
    }
        
        
        public static List listarRepartoAnterior(int idresponsable) {
        StringBuilder sql = new StringBuilder("SELECT id_reparto,");
        sql.append("  id_caja,");
        sql.append(" referencia,");
        sql.append(" fecha,");
        sql.append(" hora,");
        sql.append(" km_actual,");
        sql.append(" efectivo_entregado,");
        sql.append(" recolector_fin,");
        sql.append(" recolector_dif,");
        sql.append(" recolector_dacum");        
        sql.append(" FROM reparto ");
        //sql.append(" WHERE idresponsable=").append(idresponsable);
        //sql.append(" AND id_reparto = (select MAX(id_reparto) FROM reparto WHERE idresponsable=").append(idresponsable).append(" AND activo='S')");
        sql.append(" WHERE idmovil=").append(idresponsable);
        sql.append(" AND id_reparto = (select MAX(id_reparto) FROM reparto WHERE idmovil=").append(idresponsable).append(" AND activo='S')");
        sql.append(" AND activo='S' AND cerrado='S'");
        return OperacionMovil.getTabla(sql.toString());
    }
        
        public static List listarDetalleRepartoAnterior(int cod) {
        StringBuilder sql = new StringBuilder("SELECT detalle_reparto.idreparto,detalle_reparto.idproducto,");
        sql.append(" productos.cod_interno, productos.descripcion, detalle_reparto.devuelve, detalle_reparto.monto_devuelve");
        sql.append(" FROM detalle_reparto INNER JOIN productos");
        sql.append(" WHERE detalle_reparto.idproducto=productos.idproducto");
        sql.append(" AND idreparto=").append(cod);
        sql.append(" AND devuelve > 0.00");
        return OperacionMovil.getTabla(sql.toString());
    }
        
        public static List listarComisiones(int idvendedor, String fechaD, String fechaH) {
        StringBuilder sql = new StringBuilder("SELECT idreparto, idcaja, fecha, totalcomision");
        sql.append(" FROM comisiones");
        sql.append(" WHERE idvendedor=").append(idvendedor);
        sql.append(" AND fecha>='").append(fechaD).append("'");
        sql.append(" AND fecha<='").append(fechaH).append("'");
        sql.append(" AND estado='S'");
        return OperacionMovil.getTabla(sql.toString());
    }
        
        public static List listarReparto(String cod, String fecha) {
                StringBuilder sql = new StringBuilder("SELECT id_reparto, id_caja, referencia, valor_total, valor_recambio");
                sql.append(", valor_devuelto, efectivo_entregar, efectivo_entregado, diferencia FROM v_reparto_anulados ");
                sql.append("WHERE fecha >= '").append(fecha).append("'");
                sql.append("AND fecha<= '").append(fecha).append("' AND activo='S'");
                sql.append(" ORDER BY ").append(cod)
                .toString();
        return OperacionMovil.getTabla(sql.toString());
    }
    
}
