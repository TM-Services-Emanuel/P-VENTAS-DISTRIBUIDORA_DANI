package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarIngreso;
import IU.dlgIngreso;
import Modelo.Ingreso;

public class ControlIngreso {
    static String UsuarioL="";
    public static String addIngreso() {
        String msg;
        String fecha = dlgIngreso.dFecha.getText();
        String ingFecha = Fecha.formatoFecha(fecha);
        System.out.println(ingFecha);
        int ingCa = Integer.parseInt(dlgIngreso.txtCaja.getText());
        int ingDescripcion = Integer.parseInt(dlgIngreso.lblCodDetallIngreso.getText());
        int ingCliente = Integer.parseInt(dlgIngreso.lblCodCliente.getText());
        int ingImporte = Integer.parseInt(dlgIngreso.txtImporteL.getText());
        String ingObserv = dlgIngreso.txtObservacion.getText().trim();
        String usuario = UsuarioL=Login.getUsuarioLogueado();
           Ingreso ingreso = new Ingreso(ingCa, ingFecha, ingCliente, ingDescripcion, ingImporte, ingObserv, usuario);

        msg = GestionarIngreso.addIngreso(ingreso);

        if (msg == null) {
            Mensajes.informacion("Registrado");
        } else {
            Mensajes.error("No se pudo registrar");
        }

        return msg;
    }

}
