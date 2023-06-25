package Controladores;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Datos.GestionarCheques;
import IU.dlgCheques;
import IU.dlgGestCheques;
import Modelo.Cheques;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class ControlCheques {
    
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
        DefaultTableModel m = (DefaultTableModel) dlgCheques.tbCheques.getModel();
        int x = dlgCheques.tbCheques.getSelectedRow();
        
        String cod = m.getValueAt(x, 0).toString();
        Cheques ch = GestionarCheques.busCheques(cod);
        dlgGestCheques.txtidCheque.setText(String.valueOf(ch.getIdcheques()));
        dlgGestCheques.txtFecha.setText(Fecha.formatoFechaFF(ch.getFecha()));
        dlgGestCheques.txtidTipo.setText(String.valueOf(ch.getIdtipo()));
        dlgGestCheques.txtidBanco.setText(String.valueOf(ch.getIdbanco()));
        dlgGestCheques.txtidMovil.setText(String.valueOf(ch.getIdmovil()));
        dlgGestCheques.txtRZ.setText(ch.getRazon_social());
        dlgGestCheques.txtRuc.setText(ch.getRuc());
        dlgGestCheques.txtEmision.setText(ch.getFecha_emision());
        dlgGestCheques.txtPago.setText(ch.getFecha_pago());
        dlgGestCheques.txtCuenta.setText(ch.getCuenta_n());
        dlgGestCheques.txtCheque.setText(ch.getCheque_n());        
        DecimalFormat df = new DecimalFormat("#,###");
        dlgGestCheques.txtMonto.setText(df.format(ch.getMonto()));
        dlgGestCheques.txtObservación.setText(ch.getObservacion());
    }
    
       public static String addCheques() {
        String msg;
        int idcheque = Integer.parseInt(dlgGestCheques.txtidCheque.getText().trim());
        String fecha = Fecha.formatoFecha(dlgGestCheques.txtFecha.getText());
        int idTipo = 0;
        try {
            prepararBD();
            String tipo;
            tipo = dlgGestCheques.cbTipo.getSelectedItem().toString();
            try {
                rs = sentenciaM.executeQuery("SELECT * FROM tipo_cheques WHERE descripcion='" + tipo + "'");
                rs.last();
                idTipo = rs.getInt("idtipos");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Tipo de Cheque.: " + ex.getMessage());
            }
        } catch (Exception pr) {}
        int idBanco = 0;
        try {
            prepararBD();
            String banco;
            banco = dlgGestCheques.cbBanco.getSelectedItem().toString();
            try {
                rs = sentenciaM.executeQuery("SELECT * FROM bancos WHERE descripcion='" + banco + "'");
                rs.last();
                idBanco = rs.getInt("idbancos");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Banco.: " + ex.getMessage());
            }
        } catch (Exception pr) {}
        int idMovil = 0;
        try {
            prepararBD();
            String movil;
            movil = dlgGestCheques.cbReparto.getSelectedItem().toString();
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
        String RZ = dlgGestCheques.txtRZ.getText().trim();
        String Ruc = dlgGestCheques.txtRuc.getText().trim();
        String FE = dlgGestCheques.txtEmision.getText().trim();
        String FP = dlgGestCheques.txtPago.getText().trim();
        String Cuenta = dlgGestCheques.txtCuenta.getText().trim();
        String Cheque = dlgGestCheques.txtCheque.getText().trim();
        int  monto = Integer.parseInt(dlgGestCheques.txtMonto.getText().replace(".", "").replace(",", ""));
        String Observacion = dlgGestCheques.txtObservación.getText().trim();
        
        Cheques cheque = new Cheques(idcheque, fecha, idTipo, idBanco, idMovil, RZ, Ruc, FE, FP, Cuenta, Cheque, monto, Observacion);

        msg = GestionarCheques.addCheques(cheque);

        if (msg == null) {
            Mensajes.informacion("Cheque Registrado.");
        } else {
            Mensajes.error("No se pudo registrar el Cheque.");
        }

        return "";

    }
    
    public static String modCheques() {
        String msg;
        int idcheque = Integer.parseInt(dlgGestCheques.txtidCheque.getText().trim());
        String fecha = Fecha.formatoFecha(dlgGestCheques.txtFecha.getText());
        int idTipo = 0;
        try {
            prepararBD();
            String tipo;
            tipo = dlgGestCheques.cbTipo.getSelectedItem().toString();
            try {
                rs = sentenciaM.executeQuery("SELECT * FROM tipo_cheques WHERE descripcion='" + tipo + "'");
                rs.last();
                idTipo = rs.getInt("idtipos");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Tipo de Cheque.: " + ex.getMessage());
            }
        } catch (Exception pr) {}
        int idBanco = 0;
        try {
            prepararBD();
            String banco;
            banco = dlgGestCheques.cbBanco.getSelectedItem().toString();
            try {
                rs = sentenciaM.executeQuery("SELECT * FROM bancos WHERE descripcion='" + banco + "'");
                rs.last();
                idBanco = rs.getInt("idbancos");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener ID del Banco.: " + ex.getMessage());
            }
        } catch (Exception pr) {}
        int idMovil = 0;
        try {
            prepararBD();
            String movil;
            movil = dlgGestCheques.cbReparto.getSelectedItem().toString();
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
        String RZ = dlgGestCheques.txtRZ.getText().trim();
        String Ruc = dlgGestCheques.txtRuc.getText().trim();
        String FE = dlgGestCheques.txtEmision.getText().trim();
        String FP = dlgGestCheques.txtPago.getText().trim();
        String Cuenta = dlgGestCheques.txtCuenta.getText().trim();
        String Cheque = dlgGestCheques.txtCheque.getText().trim();
        int  monto = Integer.parseInt(dlgGestCheques.txtMonto.getText().replace(".", "").replace(",", ""));
        String Observacion = dlgGestCheques.txtObservación.getText().trim();
        
        Cheques cheque = new Cheques(idcheque, fecha, idTipo, idBanco, idMovil, RZ, Ruc, FE, FP, Cuenta, Cheque, monto, Observacion);

        msg = GestionarCheques.modCheques(cheque);

        if (msg == null) {
            Mensajes.informacion("Registro Modificado");
        } else {
            Mensajes.error("No se pudo registrar la Modificación");
        }

        return "";

    }
    
    public static String anularCheque() {
        int x = dlgCheques.tbCheques.getSelectedRow();
        int cod = Integer.parseInt(dlgCheques.tbCheques.getValueAt(x, 0).toString());
        String msg;
        msg = GestionarCheques.delCheques(cod);
        if (msg == null) {
            Mensajes.informacion("El Cheque fue eliminado satisfactoriamente");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    
    public static void listarCheques(JTable tabla) {
        List lista;
        lista = GestionarCheques.listarCheques();
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
            fila[7].toString();
            fila[8].toString();
            tb.addRow(fila);
        }
    }
    
    public static void filCheques(JTable tabla, String cod)
    {
        List lista;
        lista = GestionarCheques.fil_RS_RUC(cod);
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
            fila[7].toString();
            fila[8].toString();
            tb.addRow(fila);
        }
    }
    

}
