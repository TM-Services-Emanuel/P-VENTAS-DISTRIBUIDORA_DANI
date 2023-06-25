package Modelo;

public class DetalleIngreso {

    private int dgCodigo;
    private String dgDescripcion;
    private String usu;
    
    /*Constructor Vacio*/
    public DetalleIngreso() {
    }

    /*Constructo Lleno*/
    public DetalleIngreso(int dgCodigo, String dgDescripcion, String usu) {
        this.dgCodigo = dgCodigo;
        this.dgDescripcion = dgDescripcion;
        this.usu = usu;
    }

    /*Getter y Setter*/
    public int getDgCodigo() {
        return dgCodigo;
    }

    public void setDgCodigo(int dgCodigo) {
        this.dgCodigo = dgCodigo;
    }

    public String getDgDescripcion() {
        return dgDescripcion;
    }

    public void setDgDescripcion(String dgDescripcion) {
        this.dgDescripcion = dgDescripcion;
    }
    
    public String getUsuario() {
        return usu;
    }

    public void setUsusario(String usu) {
        this.usu = usu;
    }

}
