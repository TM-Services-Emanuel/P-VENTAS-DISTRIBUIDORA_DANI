package Controladores;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarDeudas;
import Datos.GestionarGasto;
import IU.dlgDeudas;
import IU.dlgGestDeudas;
import Modelo.Deudas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class ControlDeudas {
    
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    public static MariaDbStatement sentenciaM;
    public static MariaDbConnection conM;
    
    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                sentencia = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            conM = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (conM == null) {
                System.out.println("No hay Conexion con la Base de Datos Móvil");
            } else {
                sentenciaM = (MariaDbStatement) conM.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void aModificar()
    {
        DefaultTableModel m = (DefaultTableModel) dlgDeudas.tbDeudasPendientes.getModel();
        int x = dlgDeudas.tbDeudasPendientes.getSelectedRow();
        
        String cod = m.getValueAt(x, 0).toString();
        Deudas pr = GestionarDeudas.busDeudas(cod);
        dlgGestDeudas.txtIdDeuda.setText(String.valueOf(pr.getIddeuda()));
        dlgGestDeudas.idCliente.setText(String.valueOf(pr.getIdcliente()));
        dlgGestDeudas.idmovil.setText(String.valueOf(pr.getIdmovil()));
        dlgGestDeudas.txtObservación.setText(pr.getObservacion());
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fe= formato.parse(pr.getFecha());
            dlgGestDeudas.dFecha.setDate((fe));
        }catch(ParseException e){
            Mensajes.error("Error convirtiendo Fecha");}
        DecimalFormat df = new DecimalFormat("#,###");
        dlgGestDeudas.txtMonto.setText(df.format(pr.getMonto()));
        if(pr.getPagado().equals("S")){
            dlgGestDeudas.rbAbonado.setSelected(true);
        }else if(pr.getPagado().equals("N")){
            dlgGestDeudas.rbPendiente.setSelected(true);
        }
    }
    
    
    public static void aModificarSaldados()
    {
        DefaultTableModel m = (DefaultTableModel) dlgDeudas.tbDeudasSaldadas.getModel();
        int x = dlgDeudas.tbDeudasSaldadas.getSelectedRow();
        
        String cod = m.getValueAt(x, 0).toString();
        Deudas pr = GestionarDeudas.busDeudas(cod);
        dlgGestDeudas.txtIdDeuda.setText(String.valueOf(pr.getIddeuda()));
        dlgGestDeudas.idCliente.setText(String.valueOf(pr.getIdcliente()));
        dlgGestDeudas.idmovil.setText(String.valueOf(pr.getIdmovil()));
        dlgGestDeudas.txtObservación.setText(pr.getObservacion());
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fe= formato.parse(pr.getFecha());
            dlgGestDeudas.dFecha.setDate((fe));
        }catch(ParseException e){
            Mensajes.error("Error convirtiendo Fecha");}
        DecimalFormat df = new DecimalFormat("#,###");
        dlgGestDeudas.txtMonto.setText(df.format(pr.getMonto()));
        if(pr.getPagado().equals("S")){
            dlgGestDeudas.rbAbonado.setSelected(true);
        }else if(pr.getPagado().equals("N")){
            dlgGestDeudas.rbPendiente.setSelected(true);
        }
    }

    public static String addDeudas() {
        String msg;
        int iddeuda = Integer.parseInt(dlgGestDeudas.txtIdDeuda.getText());
        int idCliente = Integer.parseInt(dlgGestDeudas.idCliente.getText());
        int idMovil = 0;
        try {
            prepararBD();
            String movil;
            movil = dlgGestDeudas.cbReparto.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM movil_reparto WHERE descripcion='" + movil + "'");
                rs.last();
                idMovil = rs.getInt("idmovil");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Origen: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        
        String fecha = String.valueOf(Fecha.formatoFechaD(dlgGestDeudas.dFecha.getDate()));
        String Observacion = (dlgGestDeudas.txtObservación.getText().toUpperCase());
        int monto = Integer.parseInt(dlgGestDeudas.txtMonto.getText().trim().replace(".", "").replace(",", ""));
        String pagado="";
        if(dlgGestDeudas.rbAbonado.isSelected()){
            pagado="S";
        }else if(dlgGestDeudas.rbPendiente.isSelected()){
            pagado="N";
        }
        Deudas deuda = new Deudas(iddeuda, idCliente, idMovil, fecha, Observacion, monto, pagado);

        msg = GestionarDeudas.addDeudas(deuda);

        if (msg == null) {
            Mensajes.informacion("Deuda Registrada");
        } else {
            Mensajes.error("No se pudo registrar la Deuda");
        }

        return "";

    }
    
    public static String modDeudas() {
        String msg;
        int iddeuda = Integer.parseInt(dlgGestDeudas.txtIdDeuda.getText());
        int idCliente = Integer.parseInt(dlgGestDeudas.idCliente.getText());
        int idMovil = 0;
        try {
            prepararBD();
            String movil;
            movil = dlgGestDeudas.cbReparto.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM movil_reparto WHERE descripcion='" + movil + "'");
                rs.last();
                idMovil = rs.getInt("idmovil");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Origen: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        
        String fecha = String.valueOf(Fecha.formatoFechaD(dlgGestDeudas.dFecha.getDate()));
        String Observacion = (dlgGestDeudas.txtObservación.getText().toUpperCase());
        int monto = Integer.parseInt(dlgGestDeudas.txtMonto.getText().trim().replace(".", "").replace(",", ""));
        String pagado="";
        if(dlgGestDeudas.rbAbonado.isSelected()){
            pagado="S";
        }else if(dlgGestDeudas.rbPendiente.isSelected()){
            pagado="N";
        }
        Deudas deuda = new Deudas(iddeuda, idCliente, idMovil, fecha, Observacion, monto, pagado);

        msg = GestionarDeudas.modDeudas(deuda);

        if (msg == null) {
            Mensajes.informacion("Registro Modificado");
        } else {
            Mensajes.error("No se pudo registrar la Modificación");
        }

        return "";

    }
    
    public static String anularDeudaPendiente() {
        int x = dlgDeudas.tbDeudasPendientes.getSelectedRow();
        int cod = Integer.parseInt(dlgDeudas.tbDeudasPendientes.getValueAt(x, 0).toString());
        String msg;
        msg = GestionarDeudas.delDeudas(cod);
        if (msg == null) {
            Mensajes.informacion("La deuda fue eliminada satisfactoriamente");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String anularDeudaSaldada() {
        int x = dlgDeudas.tbDeudasSaldadas.getSelectedRow();
        int cod = Integer.parseInt(dlgDeudas.tbDeudasSaldadas.getValueAt(x, 0).toString());
        String msg;
        msg = GestionarDeudas.delDeudas(cod);
        if (msg == null) {
            Mensajes.informacion("La deuda fue eliminada satisfactoriamente");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listarDeudasPendientes(JTable tabla) {
        List lista;
        lista = GestionarDeudas.listarDeudasPendientes();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1]=Fecha.formatoFechaFF(fila[1].toString());
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            tb.addRow(fila);
        }
    }
    
    public static void listarDeudasSaldadas(JTable tabla) {
        List lista;
        lista = GestionarDeudas.listarDeudasSaldadas();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1]=Fecha.formatoFechaFF(fila[1].toString());
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            tb.addRow(fila);
        }
    }
    
    public static void filDeudas(JTable tabla, String cod)
    {
        List lista;
        lista = GestionarDeudas.fil_RS_RUC(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1]=Fecha.formatoFechaFF(fila[1].toString());
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            tb.addRow(fila);
        }
    }
    
    public static void filDeudasSaldadas(JTable tabla, String cod)
    {
        List lista;
        lista = GestionarDeudas.fil_RS_RUC_Saldados(cod);
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            fila[0].toString();
            fila[1]=Fecha.formatoFechaFF(fila[1].toString());
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            tb.addRow(fila);
        }
    }

}
