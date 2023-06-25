package Controladores;

import Componentes.ConexionBD;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarVendedor;
import IU.dlgGestVendedor;
import IU.dlgVendedor;
import Modelo.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class controlVendedor {

    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;

    static String UsuarioL = "";
    public static int idfuncion;
    public static int idmovil;

    public static void prepararBD() {
        {
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
        }
    }

    public static void aModificar() {
        int x = dlgVendedor.tablaEmpleados.getSelectedRow();
        DefaultTableModel m = (DefaultTableModel) dlgVendedor.tablaEmpleados.getModel();
        String cod = m.getValueAt(x, 0).toString();

        Vendedor v = GestionarVendedor.busVendedor(cod);
        DecimalFormat df = new DecimalFormat("#,###");
        dlgGestVendedor.lblCodV.setText(String.valueOf(v.getCodVe()));
        dlgGestVendedor.txtCodFuncion.setText(String.valueOf(v.getIdfuncion()));
        dlgGestVendedor.txtCodMovil.setText(String.valueOf(v.getIdmovil()));
        dlgGestVendedor.txtNombre.setText(v.getNombreV());
        dlgGestVendedor.txtDireccion.setText(df.format(Integer.valueOf(v.getDireccion().replace(".", ""))));
        dlgGestVendedor.txtTelefono.setText(v.getTelefono());
        dlgGestVendedor.txtCelular.setText(v.getCelular());
        dlgGestVendedor.txtSueldo.setText(df.format(Integer.valueOf(v.getSueldo().replace(".", ""))));
        dlgGestVendedor.txtComision.setText(String.valueOf(v.getComision()));
        dlgGestVendedor.txaS.setText(v.getObs());

    }

    public static Vendedor capturarCampos() {
        Vendedor ven;
        int codV;
        String nombreV;
        String direccion;
        String telef;
        String sueldo;
        String celu;
        double comis;
        String obs;
        
        codV = Integer.parseInt(dlgGestVendedor.lblCodV.getText());
        nombreV = dlgGestVendedor.txtNombre.getText();
        try {
            prepararBD();
            String funcion;
            funcion = dlgGestVendedor.cboFuncion.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM funcion WHERE descripcion='"+funcion+"'");
                rs.last();
                idfuncion = rs.getInt("idfuncion");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la Función: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        try {
            prepararBD();
            String movil;
            movil = dlgGestVendedor.cboMovil.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM movil_reparto WHERE descripcion='"+movil+"'");
                rs.last();
                idmovil = rs.getInt("idmovil");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del Punto de Control: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        if (dlgGestVendedor.txtDireccion.getText().trim().isEmpty()) {
            direccion ="0";
        } else {
            direccion = dlgGestVendedor.txtDireccion.getText().replace(".", "").replace(",", ".");
        }
        if(dlgGestVendedor.txtTelefono.getText().trim().isEmpty()){
            telef ="00/00/0000";
        }else{
            telef = dlgGestVendedor.txtTelefono.getText().toUpperCase();
        }
        if(dlgGestVendedor.txtCelular.getText().trim().isEmpty()){
            celu="0";
        }else{
            celu = dlgGestVendedor.txtCelular.getText().toUpperCase();
        }
        if ((dlgGestVendedor.txtSueldo.getText().trim() == null)) {
            sueldo = "0";
        } else {
            sueldo = (dlgGestVendedor.txtSueldo.getText().replace(".", "").replace(",", "."));
        }
        if (dlgGestVendedor.txtComision.getText().trim() == null) {
            comis = 0;
        } else {
            comis = Double.parseDouble(dlgGestVendedor.txtComision.getText());
        }
        if(dlgGestVendedor.txaS.getText().trim().isEmpty()){
            obs="SIN OBSERVACIÓN";
        }else{
            obs = dlgGestVendedor.txaS.getText().toUpperCase();
        }
        
        ven = new Vendedor(codV, idfuncion, idmovil, nombreV, direccion, telef, celu, sueldo, comis, obs);
        return ven;
    }

    public static String addVendedor() {
        String msg;
        Vendedor v = capturarCampos();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarVendedor.addVendedor(v, usuario);
        if (msg == null) {
            Mensajes.informacion("Funcionario Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void addImagen(String cod) {
        GestionarVendedor.addImagen(cod);
    }

    public static void actImagen(String cod) {
        GestionarVendedor.actImagen(cod);
    }

    public static void busImagen(String cod, JLabel lblImagen) {
        GestionarVendedor.busImagen(cod, lblImagen);
    }

    public static String actVendedor() {
        String msg;
        Vendedor v = capturarCampos();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarVendedor.actVendedor(v, usuario);
        if (msg == null) {
            Mensajes.informacion("Funcionario Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delVendedor() {
        int x = dlgVendedor.tablaEmpleados.getSelectedRow();
        String msg;
        String cod = dlgVendedor.tablaEmpleados.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarVendedor.delVendedor(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Funcionario Eliminado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listVendedor(JTable tabla, String cad) {
        List lista = null;
        lista = GestionarVendedor.listVendedor(cad);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

    public static void filNombre(JTable tabla, String cad) {
        List lista = null;
        lista = GestionarVendedor.filNombre(cad);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

    public static void filDireccion(JTable tabla, String cad) {
        List lista = null;
        lista = GestionarVendedor.filDireccion(cad);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

    public static void filTelefono(JTable tabla, String cad) {
        List lista = null;
        lista = GestionarVendedor.filTelefono(cad);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

}
