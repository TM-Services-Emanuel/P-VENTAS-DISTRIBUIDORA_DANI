package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarCaja;
import IU.dlgCaja;
import IU.dlgCajaDia;
import Modelo.Caja;

public class ControlCaja {
static String UsuarioL="";
    public static String addCaja() {
        String msg;
        String caFechaI = Fecha.formatoFecha(dlgCaja.lbFecha.getText());
        System.out.println(caFechaI);
        String caHoraI= dlgCaja.lbHora.getText();
        System.out.println(caHoraI);
        String caUsuI= dlgCaja.lbUsuario.getText();
        String caUsuF=" ";
        int caInicial = Integer.parseInt(dlgCaja.txtCaInicial.getText().replace(",", "").replace(".", ""));

        Caja caja = new Caja(caFechaI,caHoraI,caInicial, 0, 0, 0, 0, 0,caUsuI,caUsuF);
        msg = GestionarCaja.addCaja(caja);
        if (msg == null) {
            Mensajes.informacion("Caja inicial del día establecida");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String CerrarCaja() {
        String msg;
        int caId=Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        String caFechaF = Fecha.fechaCorrecta();
        String caHoraF = Fecha.darHora();
        int caFinal = Integer.parseInt(dlgCajaDia.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));
        String caUsuF=UsuarioL=Login.getUsuarioLogueado();

        Caja caja = new Caja(caId, caFechaF, caHoraF, caFinal, caEntre, caGastos, caDif, caMontoSalida, caUsuF);
        msg = GestionarCaja.CerrarCaja(caja);
        if (msg == null) {
            Mensajes.informacion("CAJA DEL DÍA CERRADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actCaja() {
        String msg;
        int caId = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int caFinal = Integer.parseInt(dlgCajaDia.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));

        Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actCaja(caja);
        if (msg == null) {
            Mensajes.informacion("CAJA DEL DÍA ACTUALIZADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }

}
