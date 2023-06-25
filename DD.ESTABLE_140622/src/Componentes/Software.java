/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class Software {

    /**
     * @return the Software
     */
    public static String getSoftware() {
        return software;
    }
    public static String getDescripcion() {
        return descripcion;
    }
    public static String getVersion() {
        return version;
    }
    public static String getDesarrollador() {
        return desarrollador;
    }
    public static String getProfesion() {
        return profesion;
    }
    public static String getTelefono() {
        return telefono;
    }
    public static String getCorreo() {
        return correo;
    }

    /**
     * @param asoftware
     */
    public static void setSoftware(String asoftware) {
        software = asoftware;
    }
    public static void setDescripcion(String adescripcion) {
        descripcion = adescripcion;
    }
    public static void setVersion(String aversion) {
        version = aversion;
    }
    public static void setDesarrollador(String adesarrollador) {
        desarrollador = adesarrollador;
    }
    public static void setProfesion(String aprofesion) {
        profesion = aprofesion;
    }
    public static void setTelefoo(String atelefono) {
        telefono = atelefono;
    }
    public static void setCorreo(String acorreo) {
        correo = acorreo;
    }
    
    private static String software="";
    private static String descripcion="";
    private static String version="";
    private static String desarrollador="";
    private static String profesion="";
    private static String telefono="";
    private static String correo="";
    
}
