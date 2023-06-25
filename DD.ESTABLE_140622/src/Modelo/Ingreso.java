package Modelo;

public class Ingreso {

    private int ingCodigo;
    private int ingCa;
    private String ingFecha;
    private int ingDescripcion;
    private int ingCliente;
    private int ingImporte;
    private String ingObservacion;
    private String usu;

    /*Constructor Vacio*/
    public Ingreso() {
    }

    /*Constructor Lleno*/
    public Ingreso(int ingCa, String ingFecha, int ingCliente , int ingDescripcion, int ingImporte, String ingObservacion, String usu) {
        this.ingCa = ingCa;
        this.ingFecha = ingFecha;
        this.ingCliente = ingCliente;
        this.ingDescripcion = ingDescripcion;
        this.ingImporte = ingImporte;
        this.ingObservacion = ingObservacion;
        this.usu = usu;
    }

    /*Getter y Setter*/
    public int getIngCodigo() {
        return ingCodigo;
    }

    public void setIngCodigo(int ingCodigo) {
        this.ingCodigo = ingCodigo;
    }
    
    public int getIngCa() {
        return ingCa;
    }

    public void setIngCa(int ingCa) {
        this.ingCa = ingCa;
    }

    public String getIngFecha() {
        return ingFecha;
    }

    public void setIngFecha(String ingFecha) {
        this.ingFecha = ingFecha;
    }

    public int getIngDescripcion() {
        return ingDescripcion;
    }

    public void setIngDescripcion(int ingDescripcion) {
        this.ingDescripcion = ingDescripcion;
    }

    public int getIngCliente() {
        return ingCliente;
    }

    public void setIngCliente(int ingCliente) {
        this.ingCliente = ingCliente;
    }

    public int getIngImporte() {
        return ingImporte;
    }

    public void setIngImporte(int ingImporte) {
        this.ingImporte = ingImporte;
    }

    public String getIngObservacion() {
        return ingObservacion;
    }

    public void setIngObservacion(String ingObservacion) {
        this.ingObservacion = ingObservacion;
    }
    
    public String getUsuario() {
        return usu;
    }

    public void setUsuario(String usu) {
        this.usu = usu;
    }

}
