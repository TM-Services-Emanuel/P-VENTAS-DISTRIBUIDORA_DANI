package Controladores;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarArticulos;
import IU.dlgArticulos;
import IU.dlgGestArticulos;
import Modelo.Articulo;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;
import java.sql.*;

public class controlArticulo {

    static String UsuarioL = "";
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection  con;
    static int codLab;
    static int codProv;
    static int codFam;
    
    
    public static void prepararBD(){
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

    public static void aModifcar() {
        try {
            int x = dlgArticulos.tbProductos.getSelectedRow();
            String cod = dlgArticulos.tbProductos.getValueAt(x, 0).toString();
            System.out.println("articulo a mod: "+cod);
            Articulo ar = GestionarArticulos.busArticulo(cod);
            dlgGestArticulos.txtCodProducto.setText(String.valueOf(ar.getCodArticulo()));
            dlgGestArticulos.txtCodBarra.setText((ar.getCodBarra()));
            //dlgGestAriculos1.cbLaboratorio.setSelectedIndex(ar.getCodLaboratorio());
            dlgGestArticulos.txtCodLab.setText(String.valueOf(ar.getCodLaboratorio()));
            //dlgGestAriculos1.cbFamilia.setSelectedIndex(ar.getCodFamilia());
            dlgGestArticulos.txtCodFam.setText(String.valueOf(ar.getCodFamilia()));
            //dlgGestAriculos1.cbProveedor.setSelectedIndex(ar.getCodProveedor());
            dlgGestArticulos.txtCodPro.setText(String.valueOf(ar.getCodProveedor()));
            dlgGestArticulos.txtDescripcion.setText(ar.getDescripcion());
            dlgGestArticulos.txtPrincipio.setText(ar.getPrincipio());
            dlgGestArticulos.txtAccion.setText(ar.getAccion());
            if (ar.getVenta().equals("LIBRE")) {
                dlgGestArticulos.rLibre.setSelected(true);
            } else {
                dlgGestArticulos.rControlado.setSelected(true);
            }
            if (ar.getTipo().equals("N")) {
                dlgGestArticulos.rNacional.setSelected(true);
            } else {
                dlgGestArticulos.rImportado.setSelected(true);
            }
            if (ar.getProdActivo().equals("SI")) {
                dlgGestArticulos.rActivo.setSelected(true);
            } else {
                dlgGestArticulos.rInactivo.setSelected(true);
            }
            DecimalFormat df = new DecimalFormat("#,###");
            dlgGestArticulos.txtCostoL.setText(String.valueOf(ar.getCosto()));
            dlgGestArticulos.txtCosto.setText(df.format(Integer.valueOf(dlgGestArticulos.txtCostoL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulos.txtPrecioPublicoL.setText(String.valueOf(ar.getPrecioPublico()));
            dlgGestArticulos.txtPrecioPublico.setText(df.format(Integer.valueOf(dlgGestArticulos.txtPrecioPublicoL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulos.txtGanancia.setText(String.valueOf(ar.getGanancia()));
            dlgGestArticulos.txtDesc.setText(String.valueOf(ar.getDescuento()));
            dlgGestArticulos.txtPrecioVentaL.setText(String.valueOf(ar.getPrecioVenta()));
            dlgGestArticulos.txtPrecioVenta.setText(df.format(Integer.valueOf(dlgGestArticulos.txtPrecioVentaL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulos.txtIVA.setText(String.valueOf(ar.getIVA()));
            dlgGestArticulos.txtIVACosto.setText(String.valueOf(ar.getCostoIva()));
            dlgGestArticulos.txtStock.setText(String.valueOf(ar.getStock()));
            dlgGestArticulos.txtStockMin.setText(String.valueOf(ar.getStockMin()));
            try {
                if (ar.getVencimiento().equals("0000-00-00")) {
                    dlgGestArticulos.dfecha.cleanup();
                } else {
                    SimpleDateFormat fe = new SimpleDateFormat("dd/MM/yyyy");
                    dlgGestArticulos.dfecha.setDate(fe.parse(ar.getVencimiento()));
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            try{
                if(ar.getVM().equals("S")){
                    dlgGestArticulos.ckHabilitar.setSelected(true);
                    dlgGestArticulos.txtCantM.setText(String.valueOf(ar.getCM()));
                    dlgGestArticulos.txtCantM.setEnabled(true);
                    dlgGestArticulos.txtPrecioVentaML.setText(String.valueOf(ar.getPM()));
                    dlgGestArticulos.txtPrecioVentaM.setText(df.format(Integer.valueOf(dlgGestArticulos.txtPrecioVentaML.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestArticulos.txtPrecioVentaM.setEnabled(true);
                }else{
                    dlgGestArticulos.ckHabilitar.setSelected(false);
                    dlgGestArticulos.txtCantM.setText(String.valueOf(ar.getCM()));
                    dlgGestArticulos.txtCantM.setEnabled(false);
                    dlgGestArticulos.txtPrecioVentaML.setText(String.valueOf(ar.getPM()));
                    dlgGestArticulos.txtPrecioVentaM.setText(df.format(Integer.valueOf(dlgGestArticulos.txtPrecioVentaML.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestArticulos.txtPrecioVentaM.setEnabled(false);
                }
            }catch(NumberFormatException e){
            System.out.println("Error al obtener datos Mayorista"+e.getMessage());}
        } catch (NumberFormatException ee) {
            System.out.println("E: "+ee.getMessage());
        }

    }

    public static Articulo capturarCampos() {
        Articulo art;

        int codA = Integer.parseInt(dlgGestArticulos.txtCodProducto.getText());
        String codBar = null;
        if (dlgGestArticulos.txtCodBarra.getText().trim().isEmpty()) {
            String ini=(dlgGestArticulos.txtCodProducto.getText());
            switch(ini.length()){
                case 1 -> codBar="000000000000"+ini;
                case 2 -> codBar="00000000000"+ini;
                case 3 -> codBar="0000000000"+ini;
                case 4 -> codBar="000000000"+ini;
                case 5 -> codBar="00000000"+ini;
                case 6 -> codBar="0000000"+ini;
                case 7 -> codBar="000000"+ini;
                case 8 -> codBar="00000"+ini;
                case 9 -> codBar="0000"+ini;
                case 10 -> codBar="000"+ini;
                case 11 -> codBar="00"+ini;
                case 12 -> codBar="0"+ini;
                case 13 -> codBar= ini;
            }
            //codBar = (dlgGestArticulos.txtCodProducto.getText());
        } else {
            codBar = (dlgGestArticulos.txtCodBarra.getText());
        }

        String nomb = dlgGestArticulos.txtDescripcion.getText().toUpperCase();
        String princ;
        if (dlgGestArticulos.txtPrincipio.getText().trim().toUpperCase().isEmpty()) {
            princ = " ";
        } else {
            princ = dlgGestArticulos.txtPrincipio.getText().toUpperCase();
        }
        String accion;
        if (dlgGestArticulos.txtAccion.getText().trim().toUpperCase().isEmpty()) {
            accion = " ";
        } else {
            accion = dlgGestArticulos.txtAccion.getText().toUpperCase();
        }
        String venta;
        if (dlgGestArticulos.rLibre.isSelected()) {
            venta = "LIBRE";
        } else {
            venta = "CONTROLADO";
        }
        String tipo;
        if (dlgGestArticulos.rNacional.isSelected()) {
            tipo = "N";
        } else {
            tipo = "I";
        }
        try {
            prepararBD();
            String lab;
            lab = dlgGestArticulos.cbLaboratorio.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM laboratorio WHERE lab_nombre='"+lab+"'");
                rs.last();
                codLab = rs.getInt("lab_codigo");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del laboratorio: "+ex.getMessage());
            }
        } catch (Exception pr) {}
        try {
            prepararBD();
            String prov;
            prov = dlgGestArticulos.cbProveedor.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM proveedor WHERE pro_razonsocial='"+prov+"'");
                rs.last();
                codProv = rs.getInt("pro_codigo");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del proveedor: "+ex.getMessage());
            }
        } catch (Exception pr) {}
        try {
            prepararBD();
            String fam;
            fam = dlgGestArticulos.cbFamilia.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM familia WHERE fam_nombre='"+fam+"'");
                rs.last();
                codFam = rs.getInt("fam_codigo");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la familia: "+ex.getMessage());
            }
        } catch (Exception pr) {}
        int Pcosto = Integer.valueOf(dlgGestArticulos.txtCostoL.getText().trim());
        int Ppublico = Integer.valueOf(dlgGestArticulos.txtPrecioPublicoL.getText().trim());
        int Gan = Integer.parseInt(dlgGestArticulos.txtGanancia.getText());
        int des = Integer.parseInt(dlgGestArticulos.txtDesc.getText());
        int Pventa = Integer.valueOf(dlgGestArticulos.txtPrecioVentaL.getText());
        int ivaG = Integer.parseInt(dlgGestArticulos.txtIVA.getText());
        double ivaC = Double.valueOf(dlgGestArticulos.txtIVACosto.getText());
        String fechas;
        if (dlgGestArticulos.dfecha.getDate() == null) {
            fechas = "0000-00-00";
        } else {
            Date fecha = dlgGestArticulos.dfecha.getDate();
            fechas = Fecha.formatoFechaD(fecha);
        }
        float stock = Float.parseFloat(dlgGestArticulos.txtStock.getText().trim());
        int stockMin = Integer.valueOf(dlgGestArticulos.txtStockMin.getText().trim());
        String prodActivo;
        if (dlgGestArticulos.rActivo.isSelected()) {
            prodActivo = "SI";
        } else {
            prodActivo = "NO";
        }
        String VM;
        int CM;
        int PM;
        if(dlgGestArticulos.ckHabilitar.isSelected()){
            VM="S";
            CM=Integer.parseInt(dlgGestArticulos.txtCantM.getText().trim());
            PM=Integer.parseInt(dlgGestArticulos.txtPrecioVentaML.getText().trim());
        }else{
            VM="N";
            CM=Integer.parseInt(dlgGestArticulos.txtCantM.getText().trim());
            PM=Integer.parseInt(dlgGestArticulos.txtPrecioVentaML.getText().trim());
        }
        UsuarioL = Login.getUsuarioLogueado();
        String usuario = UsuarioL;
        art = new Articulo(codA, codFam, codLab, codProv, codBar, nomb, princ, accion, Pcosto, ivaC, ivaG, stock, stockMin, fechas,
                Gan, des, Ppublico, Pventa, venta, tipo, prodActivo, VM, CM, PM ,usuario);
        return art;
    }

    public static String addArticulo() {
        String msg;
        Articulo art = capturarCampos();
        msg = GestionarArticulos.addArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actArticulo() {
        String msg;
        Articulo art = capturarCampos();
        msg = GestionarArticulos.actArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Modifcado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static Articulo busArticulo(String cod) {
        Articulo art;
        art = GestionarArticulos.busArticulo(cod);
        return art;
    }

    public static String delArticulo() {
        int x = dlgArticulos.tbProductos.getSelectedRow();
        String msg;
        String cod = dlgArticulos.tbProductos.getValueAt(x, 0).toString();
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarArticulos.delArticulo(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Artículo Eliminado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listArticulo(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.listArticulo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    public static void listArticuloActivo(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulos.listArticuloActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    
    public static void filtrarGral(JTable tabla, String cod) {
        String C = cod;
        List lista1;
        lista1 = GestionarArticulos.filtrarGral(cod);
        if (lista1 != null) {
            for (int i = 1; i < lista1.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista1.get(i);
                tb.addRow(fila);
            }
        }
    }

    public static void filtrarDescripcion(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcion(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
    public static void filtrarDescripcionActivo(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcionActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }

/*    public static void filrarPrincipio(JTable tabla, String pr) {
        List lista = null;
        lista = GestionarArticulos.filtrarPrincipio(pr);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/
    public static void filrarPrincipioActivo(JTable tabla, String pr) {
        List lista = null;
        lista = GestionarArticulos.filtrarPrincipioActivo(pr);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }

    public static void filtrarCodBarra(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulos.filtrarCodBarra(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }
    public static void filtrarCodBarraActivo(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulos.filtrarCodBarraActivo(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }

}
