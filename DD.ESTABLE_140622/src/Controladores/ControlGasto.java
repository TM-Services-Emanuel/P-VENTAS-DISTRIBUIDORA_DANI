package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarGasto;
import IU.dlgGastos;
import Modelo.Gasto;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControlGasto {
    static String UsuarioL="";

    public static String addGasto() {
        String msg;
        int caj = Integer.parseInt(dlgGastos.txtCaja.getText());
        String fecha = dlgGastos.dcFecha.getText();
        String gaFecha = Fecha.formatoFecha(fecha);
        System.out.println(gaFecha);
        int gaDescripcion = Integer.parseInt(dlgGastos.lblCodDetalle.getText());
        String generado="";
        switch (dlgGastos.cbGenerado.getSelectedIndex()) {
            case 1 -> generado="L";
            case 2 -> generado="R";
            case 3 -> generado="A";
            default -> {
            }
        }
        int gaNombre = Integer.parseInt(dlgGastos.txtIdMovil.getText().toUpperCase().trim());
        int gaImporte = Integer.parseInt(dlgGastos.txtImporteL.getText());
        String gaObservacion = dlgGastos.txtObservacion.getText().toUpperCase().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
        Gasto gasto = new Gasto(caj, gaFecha, gaDescripcion, gaNombre, gaImporte, gaObservacion, usuario, generado);

        msg = GestionarGasto.addGasto(gasto);

        if (msg == null) {
            Mensajes.informacion("Registrado");
        } else {
            Mensajes.error("No se pudo guardar");
        }

        return "";

    }
    
    public static String anularGasto(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarGasto.delGasto(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("El Gasto fue anulado satisfactoriamente");
            //dlgGestRepartos.Cancelar();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listarGastos(JTable tabla, String fecha) {
        List lista;
        lista = GestionarGasto.listarGastos(fecha);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            switch (fila[4].toString()) {
                case "L":
                    fila[4]="LOCAL";
                    break;
                case "R":
                    fila[4]="REPARTO";
                    break;
                case "A":
                    fila[4]="ADMINISTRATIVO";
                    break;
                default:
                    break;
            }
            //fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            tb.addRow(fila);
        }
    }

}
