package Controladores;

import Componentes.Login;
import Componentes.traerIP;
import Datos.Logeo;
import IU.frmAcceso;
import IU.frmPrincipal;
import Modelo.Usuario;
import javax.swing.Timer;

public class ControlLogeo {

    //static Timer timer;
    //int cont;
    //public static final int ONE_SECOND=2;
    
    static Usuario u;
    static String user;
    static String pass;
    public static String UsuarioL = "";

    public static String logear() {
        user = frmAcceso.txtUsuario.getText().trim();
        //pass = Encript.getStringMessageDigest(frmAcceso.psPasword.getText(), Encript.MD5);
        pass = frmAcceso.psPasword.getText().trim();
        u = Logeo.logear(user, pass);
        
        if (u.getPefil().equalsIgnoreCase("ADMINISTRADOR")) {
            //ip=u.getIp();
            //System.out.println(u.getIp()+" vs "+ traerIP.getIP() );
           // if (ip.equals(traerIP.getIP())) {
                String msg = Logeo.acceso(u);
                abrirPrincipal();
                frmPrincipal.lblUsuario.setText(u.getNomUsuario());
                frmPrincipal.lbUsuario.setText(u.getUsuario());
                UsuarioL=u.getUsuario();
                Login.setUsuarioLogueado(UsuarioL);
                frmPrincipal.lbPerfil.setText(u.getPefil());
            //} else {
            //    frmAcceso.lblMensaje.setText("Dirección IP no autorizada para la conexión, verifique la Configuración.");
            //}
        }else if (u.getPefil().equalsIgnoreCase("VENTA")) {
            //ip=u.getIp();
            //if (ip.equals(traerIP.getIP())) {
                String msg = Logeo.acceso(u);
                abrirPrincipal();
                frmPrincipal.lblUsuario.setText(u.getNomUsuario());
                frmPrincipal.lbUsuario.setText(u.getUsuario());
                UsuarioL=u.getUsuario();
                Login.setUsuarioLogueado(UsuarioL);
                frmPrincipal.lbPerfil.setText(u.getPefil());
            //} else {
            //    frmAcceso.lblMensaje.setText("Dirección IP no autorizada para la conexión, verifique la Configuración.");
            //}
        }else if (u.getPefil().equalsIgnoreCase("COMPRA")) {
            //ip=u.getIp();
            //if (ip.equals(traerIP.getIP())) {
                String msg = Logeo.acceso(u);
                abrirPrincipal();
                frmPrincipal.lblUsuario.setText(u.getNomUsuario());
                frmPrincipal.lbUsuario.setText(u.getUsuario());
                UsuarioL=u.getUsuario();
                Login.setUsuarioLogueado(UsuarioL);
                frmPrincipal.lbPerfil.setText(u.getPefil());
            //} else {
            //    frmAcceso.lblMensaje.setText("Dirección IP no autorizada para la conexión, verifique la Configuración.");
            //}
        }else if (u.getPefil().equalsIgnoreCase("ALMACEN")) {
            //ip=u.getIp();
            //if (ip.equals(traerIP.getIP())) {
                String msg = Logeo.acceso(u);
                abrirPrincipal();
                frmPrincipal.lblUsuario.setText(u.getNomUsuario());
                frmPrincipal.lbUsuario.setText(u.getUsuario());
                UsuarioL=u.getUsuario();
                Login.setUsuarioLogueado(UsuarioL);
                frmPrincipal.lbPerfil.setText(u.getPefil());
            //} else {
            //    frmAcceso.lblMensaje.setText("Dirección IP no autorizada para la conexión, verifique la Configuración.");
            //}
        }
        else if (u.getPefil().equalsIgnoreCase("DESARROLLADOR")) {
            //ip=u.getIp();
            //if (ip.equals(traerIP.getIP())) {
                String msg = Logeo.acceso(u);
                abrirPrincipal();
                frmPrincipal.lblUsuario.setText(u.getNomUsuario());
                frmPrincipal.lbUsuario.setText(u.getUsuario());
                UsuarioL=u.getUsuario();
                Login.setUsuarioLogueado(UsuarioL);
                frmPrincipal.lbPerfil.setText(u.getPefil());
            //} else {
            //    frmAcceso.lblMensaje.setText("Dirección IP no autorizada para la conexión, verifique la Configuración.");
            //}
        }
        return String.valueOf(u.getNomUsuario());
    }

    public static String perfil() {
        return String.valueOf(u.getPefil());
    }

    public static String desLogeo() {
        String msg = null;
        msg = Logeo.salida(u);
        if (msg == null) {
            System.out.println("Se inserto Salida");
        } else {
            System.out.println("No se inserto salida");
        }
        return msg;
    }

    public static void abrirPrincipal() {
        frmPrincipal p = new frmPrincipal();
        p.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
        p.setVisible(true);
    }

}
