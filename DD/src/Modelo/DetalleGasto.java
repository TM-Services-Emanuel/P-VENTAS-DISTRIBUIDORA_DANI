package Modelo;

public class DetalleGasto {

    private int dgCodigo;
    private String dgDescripcion;
    private String usu;

    /*Constructor vacio*/
    public DetalleGasto() {
    }

    /*Constructor Lleno*/
    public DetalleGasto(int dgCodigo, String dgDescripcion, String usu) {
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

    public void setUsuario(String usu) {
        this.usu = usu;
    }

}
