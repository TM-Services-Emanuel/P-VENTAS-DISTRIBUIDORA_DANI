package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Usuario;
import IU.frmAcceso;

public class Logeo {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(acc_codigo) FROM accesos");
        return cod;
    }

    public static Usuario logear(String user, String pass) {
        Usuario u = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM v_usuario WHERE (usu_usuario = '");
        sql.append(user);
        sql.append("' AND usu_password = '");
        sql.append(pass);
        sql.append("')");
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            u = new Usuario();
            u.setCodUsuario(Integer.parseInt(filaObt[0].toString()));
            System.out.println("CodUsusario:"+Integer.parseInt(filaObt[0].toString()));
            u.setPefil(filaObt[1].toString());
            System.out.println("Perfil:"+filaObt[1].toString());
            u.setNomUsuario(filaObt[2].toString());
            System.out.println("NomUsuario:"+filaObt[2].toString());
            u.setUsuario(filaObt[3].toString());
            System.out.println("Usuario:"+filaObt[3].toString());
            u.setPassword(filaObt[4].toString());
            System.out.println("Password:"+filaObt[4].toString());
            u.setIp(filaObt[6].toString());
            System.out.println("IP:"+filaObt[6].toString());
            System.out.println("Usuario Correcto");
        } else {
            frmAcceso.lblMensaje.setText("Datos Incorrectos, verifique su Usuario y Contraseña.");
        }
        return u;
    }

    public static String acceso(Usuario u) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO accesos VALUES (0");
        sql.append(",").append(u.getCodUsuario()).append(",'");
        sql.append(u.getNomUsuario()).append("','").append(u.getUsuario()).append("','");
        sql.append(u.getPefil()).append("','").append(u.getUsuario());
        sql.append(" ingresó al sistema','Entrada',NOW())");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String salida(Usuario u) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO accesos VALUES (0");
        sql.append(",").append(u.getCodUsuario()).append(",'");
        sql.append(u.getNomUsuario()).append("','").append(u.getUsuario()).append("','");
        sql.append(u.getPefil()).append("','").append(u.getUsuario());
        sql.append(" salió del sistema','Salida',NOW())");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

}
