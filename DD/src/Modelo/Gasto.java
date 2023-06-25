package Modelo;

public class Gasto {

    private int caj;
    private int gaCodigo;
    private String gaFecha;
    private int gaDescripcion;
    private int gaNombre;
    private int gaImporte;
    private String gaObservacion;
    private String usuario;
    private String generado;

    /*Constructo vacio*/
    public Gasto() {
    }

    /*Constructor Lleno*/
    public Gasto(int caj, String gaFecha, int gaDescripcion, int gaNombre, int gaImporte, String gaObservacion, String usuario, String generado) {
        this.caj = caj;
        this.gaFecha = gaFecha;
        this.gaDescripcion = gaDescripcion;
        this.gaNombre = gaNombre;
        this.gaImporte = gaImporte;
        this.gaObservacion = gaObservacion;
        this.usuario = usuario;
        this.generado = generado;
    }

    /*Getter y Setter*/
    public int getGaCodigo() {
        return gaCodigo;
    }

    public void setGaCodigo(int gaCodigo) {
        this.gaCodigo = gaCodigo;
    }

    public int getCaj() {
        return caj;
    }

    public void setCaj(int caj) {
        this.caj = caj;
    }

    public String getGaFecha() {
        return gaFecha;
    }

    public void setGaFecha(String gaFecha) {
        this.gaFecha = gaFecha;
    }

    public int getGaDescripcion() {
        return gaDescripcion;
    }

    public void setGaDescripcion(int gaDescripcion) {
        this.gaDescripcion = gaDescripcion;
    }

    public int getGaNombre() {
        return gaNombre;
    }

    public void setGaNombre(int gaNombre) {
        this.gaNombre = gaNombre;
    }

    public int getGaImporte() {
        return gaImporte;
    }

    public void setGaImporte(int gaImporte) {
        this.gaImporte = gaImporte;
    }

    public String getGaObservacion() {
        return gaObservacion;
    }

    public void setGaObservacion(String gaObservacion) {
        this.gaObservacion = gaObservacion;
    }

    public String getUsusario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGenerado() {
        return generado;
    }

    public void setGenerado(String generado) {
        this.generado = generado;
    }
    
    

}
