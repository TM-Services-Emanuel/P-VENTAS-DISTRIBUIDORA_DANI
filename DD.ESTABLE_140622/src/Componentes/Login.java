/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class Login {

    /**
     * @return the UsuarioLogueado
     */
    public static String getUsuarioLogueado() {
        return UsuarioLogueado;
    }

    /**
     * @param aUsuarioLogueado the UsuarioLogueado to set
     */
    public static void setUsuarioLogueado(String aUsuarioLogueado) {
        UsuarioLogueado = aUsuarioLogueado;
    }
    
    private static String UsuarioLogueado="";
    
}
