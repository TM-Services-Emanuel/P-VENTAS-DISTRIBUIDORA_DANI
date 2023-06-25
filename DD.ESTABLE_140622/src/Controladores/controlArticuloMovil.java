package Controladores;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Datos.GestionarArticulosMovil;
import IU.dlgArticulosMovil;
import IU.dlgGestArticulosMovil;
import IU.dlgGestArticulosMovil1;
import Modelo.ArticuloMovil;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;
import java.sql.*;

public class controlArticuloMovil {

    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    static int codClasificacion;
    static int codUM;
    static int codImpuesto;

    public static void prepararBD() {
        {
            try {
                con = (MariaDbConnection) new ConexionBD().getConexionMovil();
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
            int x = dlgArticulosMovil.tbProductos.getSelectedRow();
            String cod = dlgArticulosMovil.tbProductos.getValueAt(x, 0).toString();
            System.out.println("articulo a mod: " + cod);
            ArticuloMovil ar = GestionarArticulosMovil.busArticulo(cod);
            dlgGestArticulosMovil.txtCodProducto.setText(String.valueOf(ar.getIdproducto()));
            if(ar.getDependencia().equals("S")){
                dlgGestArticulosMovil.ckDependencia.setSelected(true);
                dlgGestArticulosMovil.txtidDependencia.setText(String.valueOf(ar.getIddependencia()));
            }else if(ar.getDependencia().equals("N")){
                dlgGestArticulosMovil.ckDependencia.setSelected(false);
                dlgGestArticulosMovil.txtidDependencia.setText(String.valueOf(ar.getIddependencia()));
            }
            dlgGestArticulosMovil.txtCodInterno.setText((ar.getCodinterno()));
            dlgGestArticulosMovil.txtCodBarra.setText((ar.getCodBarra()));
            dlgGestArticulosMovil.txtDescripcion.setText(ar.getDescripcion());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgGestArticulosMovil.txtPrecioCostoL.setText(String.valueOf(ar.getPrecio_costo()));
            dlgGestArticulosMovil.txtCosto.setText(df.format(Integer.valueOf(dlgGestArticulosMovil.txtPrecioCostoL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulosMovil.txtGanancia.setText(String.valueOf(df.format(ar.getGanancia())));
            dlgGestArticulosMovil.txtPrecioVentaL.setText(String.valueOf(ar.getPrecio_venta()));
            dlgGestArticulosMovil.txtPrecioVenta.setText(df.format(Integer.valueOf(dlgGestArticulosMovil.txtPrecioVentaL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulosMovil.txtStock.setText(String.valueOf(ar.getStock()).trim().replace(".0", "").replace(",", ""));
            dlgGestArticulosMovil.txtCodClasificacion.setText(String.valueOf(ar.getDivision()));
            dlgGestArticulosMovil.txtCodImpuesto.setText(String.valueOf(ar.getIva()));
            dlgGestArticulosMovil.txtCodUM.setText(String.valueOf(ar.getUm()));
            if(ar.getVentam().equals("SI")){
               dlgGestArticulosMovil.ckHabilitar.setSelected(true);
               dlgGestArticulosMovil.txtCantM.setEnabled(true);
            }else{
               dlgGestArticulosMovil.ckHabilitar.setSelected(false);
               dlgGestArticulosMovil.txtCantM.setEnabled(false);
            }
            dlgGestArticulosMovil.txtCantM.setText(String.valueOf(ar.getCantm()).trim().replace(".0", "").replace(",", ""));
            dlgGestArticulosMovil.txtGananciaMinorista.setText(String.valueOf(df.format(ar.getGananciaminorista())));
            dlgGestArticulosMovil.txtPrecioVentaMinorista.setText(String.valueOf(df.format(ar.getPreciominorista())));
            
        } catch (NumberFormatException ee) {
            System.out.println("Error: " + ee.getMessage());
        }

    }

    public static ArticuloMovil capturarCampos() {
        ArticuloMovil art;
        int codA = Integer.parseInt(dlgGestArticulosMovil.txtCodProducto.getText());
        String codInt;
        String dependencia = null;
        int iddependencia = 0;
        if(dlgGestArticulosMovil.ckDependencia.isSelected()){
            dependencia="S";
            iddependencia=Integer.parseInt(dlgGestArticulosMovil.txtidDependencia.getText().trim());
        }else if(!dlgGestArticulosMovil.ckDependencia.isSelected()){
            dependencia="N";
            iddependencia=0;
        }
        if (dlgGestArticulosMovil.txtCodInterno.getText().trim().isEmpty()) {
            codInt = (dlgGestArticulosMovil.txtCodProducto.getText());
        } else {
            codInt = (dlgGestArticulosMovil.txtCodInterno.getText().trim().toUpperCase());
        }
        String codBar;
        if (dlgGestArticulosMovil.txtCodBarra.getText().trim().isEmpty()) {
            codBar = "SIN CODIGO";
        } else {
            codBar = (dlgGestArticulosMovil.txtCodBarra.getText().trim().toUpperCase());
        }

        String descripcion = dlgGestArticulosMovil.txtDescripcion.getText().toUpperCase();

        try {
            prepararBD();
            String clas;
            clas = dlgGestArticulosMovil.cboClasificacion.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM division WHERE descripcion='" + clas + "'");
                rs.last();
                codClasificacion = rs.getInt("iddivision");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la clasificación: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        try {
            prepararBD();
            String um;
            um = dlgGestArticulosMovil.cboUM.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM unidad_medida WHERE unidadmedida='" + um + "'");
                rs.last();
                codUM = rs.getInt("idunidad");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la Unidad de medida: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        try {
            prepararBD();
            String iva;
            iva = dlgGestArticulosMovil.cboImpuesto.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM iva WHERE descripcion='" + iva + "'");
                rs.last();
                codImpuesto = rs.getInt("idiva");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del impuesto: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }

        int Pcosto = Integer.valueOf(dlgGestArticulosMovil.txtPrecioCostoL.getText().trim());
        int Ganancia = Integer.valueOf(dlgGestArticulosMovil.txtGanancia.getText().trim().replace(".", "").replace(",", ""));
        int Pventa = Integer.valueOf(dlgGestArticulosMovil.txtPrecioVentaL.getText().trim());
        double stock = Double.parseDouble(dlgGestArticulosMovil.txtStock.getText().trim());
        String ventam;
        if(dlgGestArticulosMovil.ckHabilitar.isSelected()){
            ventam="SI";
            }else{
            ventam="NO";
        }
        double cantM=Double.parseDouble(dlgGestArticulosMovil.txtCantM.getText().trim().replace(".", "").replace(",", ""));
        int GananciaM=Integer.parseInt(dlgGestArticulosMovil.txtGananciaMinorista.getText().trim().replace(".", "").replace(",", ""));
        int PventaM= Integer.parseInt(dlgGestArticulosMovil.txtPrecioVentaMinorista.getText().trim().replace(".", "").replace(",", ""));

        art = new ArticuloMovil(codA, dependencia, iddependencia, codInt, codBar, descripcion, Pcosto, Ganancia, Pventa, stock, codUM, codClasificacion, codImpuesto, ventam, cantM, PventaM,GananciaM);
        return art;
    }
    
    public static ArticuloMovil capturarCampos1() {
        ArticuloMovil art;
        int codA = Integer.parseInt(dlgGestArticulosMovil1.txtCodProducto.getText());
        String codInt;
        String dependencia = null;
        int iddependencia = 0;
        if(dlgGestArticulosMovil1.ckDependencia.isSelected()){
            dependencia="S";
            iddependencia=Integer.parseInt(dlgGestArticulosMovil1.txtidDependencia.getText().trim());
        }else if(!dlgGestArticulosMovil1.ckDependencia.isSelected()){
            dependencia="N";
            iddependencia=0;
        }
        if (dlgGestArticulosMovil1.txtCodInterno.getText().trim().isEmpty()) {
            codInt = (dlgGestArticulosMovil1.txtCodProducto.getText());
        } else {
            codInt = (dlgGestArticulosMovil1.txtCodInterno.getText().trim().toUpperCase());
        }
        String codBar;
        if (dlgGestArticulosMovil1.txtCodBarra.getText().trim().isEmpty()) {
            codBar = "SIN CODIGO";
        } else {
            codBar = (dlgGestArticulosMovil1.txtCodBarra.getText().trim().toUpperCase());
        }

        String descripcion = dlgGestArticulosMovil1.txtDescripcion.getText().toUpperCase();

        try {
            prepararBD();
            String clas;
            clas = dlgGestArticulosMovil1.cboClasificacion.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM division WHERE descripcion='" + clas + "'");
                rs.last();
                codClasificacion = rs.getInt("iddivision");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la clasificación: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        try {
            prepararBD();
            String um;
            um = dlgGestArticulosMovil1.cboUM.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM unidad_medida WHERE unidadmedida='" + um + "'");
                rs.last();
                codUM = rs.getInt("idunidad");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la Unidad de medida: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }
        try {
            prepararBD();
            String iva;
            iva = dlgGestArticulosMovil1.cboImpuesto.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM iva WHERE descripcion='" + iva + "'");
                rs.last();
                codImpuesto = rs.getInt("idiva");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor del impuesto: " + ex.getMessage());
            }
        } catch (Exception pr) {
        }

        int Pcosto = Integer.valueOf(dlgGestArticulosMovil1.txtPrecioCostoL.getText().trim());
        int Ganancia = Integer.valueOf(dlgGestArticulosMovil1.txtGanancia.getText().trim().replace(".", "").replace(",", ""));
        int Pventa = Integer.valueOf(dlgGestArticulosMovil1.txtPrecioVentaL.getText().trim());
        double stock = Double.parseDouble(dlgGestArticulosMovil1.txtStock.getText().trim());
        String ventam;
        if(dlgGestArticulosMovil1.ckHabilitar.isSelected()){
            ventam="SI";
            }else{
            ventam="NO";
        }
        double cantM=Double.parseDouble(dlgGestArticulosMovil1.txtCantM.getText().trim().replace(".", "").replace(",", ""));
        int GananciaM=Integer.parseInt(dlgGestArticulosMovil1.txtGananciaMinorista.getText().trim().replace(".", "").replace(",", ""));
        int PventaM= Integer.parseInt(dlgGestArticulosMovil1.txtPrecioVentaMinorista.getText().trim().replace(".", "").replace(",", ""));

        art = new ArticuloMovil(codA, dependencia, iddependencia, codInt, codBar, descripcion, Pcosto, Ganancia, Pventa, stock, codUM, codClasificacion, codImpuesto, ventam, cantM, PventaM,GananciaM);
        return art;
    }

    public static String addArticulo() {
        String msg;
        ArticuloMovil art = capturarCampos();
        msg = GestionarArticulosMovil.addArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addArticulo1() {
        String msg;
        ArticuloMovil art = capturarCampos1();
        msg = GestionarArticulosMovil.addArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actArticulo() {
        String msg;
        ArticuloMovil art = capturarCampos();
        msg = GestionarArticulosMovil.actArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Modifcado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static ArticuloMovil busArticulo(String cod) {
        ArticuloMovil art;
        art = GestionarArticulosMovil.busArticulo(cod);
        return art;
    }

    public static String delArticulo() {
        int x = dlgArticulosMovil.tbProductos.getSelectedRow();
        String msg;
        String cod = dlgArticulosMovil.tbProductos.getValueAt(x, 0).toString();
        msg = GestionarArticulosMovil.delArticulo(cod);
        if (msg == null) {
            Mensajes.informacion("Artículo Eliminado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listArticulo(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulosMovil.listArticulo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            if(fila[1].toString().equals("N")){
                fila[1]="INHABILITADO";
            }else if(fila[1].toString().equals("S")){
                fila[1]="HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            tb.addRow(fila);
        }
    }
    
    public static void listArticuloconStock(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulosMovil.listArticuloconStock(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            if(fila[1].toString().equals("N")){
                fila[1]="INHABILITADO";
            }else if(fila[1].toString().equals("S")){
                fila[1]="HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            tb.addRow(fila);
        }
    }

    /*   public static void listArticuloActivo(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulos.listArticuloActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }*/

    public static void filtrarGral(JTable tabla, String cod) {
        String C = cod;
        List lista1;
        lista1 = GestionarArticulosMovil.filtrarGral(cod);
        if (lista1 != null) {
            for (int i = 1; i < lista1.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista1.get(i);
                fila[0].toString();
            if(fila[1].toString().equals("N")){
                fila[1]="INHABILITADO";
            }else if(fila[1].toString().equals("S")){
                fila[1]="HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
                tb.addRow(fila);
            }
        }
    }

    /*public static void filtrarDescripcion(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcion(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }*/
 /*public static void filtrarDescripcionActivo(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcionActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/

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
 /* public static void filrarPrincipioActivo(JTable tabla, String pr) {
        List lista = null;
        lista = GestionarArticulos.filtrarPrincipioActivo(pr);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/

 /*public static void filtrarCodBarra(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulos.filtrarCodBarra(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/
  public static void filtrarCodBarraActivo(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulosMovil.filtrarCodBarraActivo(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
  
  public static void filtrarArticulosMovActivoAuxReparto(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulosMovil.filtrarArticulosActivoAuxiliarReparto(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }
}
