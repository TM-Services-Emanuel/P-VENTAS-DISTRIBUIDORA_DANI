package Modelo;

public class UMMovil {
    
    private int codU;
    private String unidadmedida;
    private int cantidad;

    //Constructor

    public UMMovil(int codU, String unidadmedida, int cantidad) {
        this.codU = codU;
        this.unidadmedida = unidadmedida;
        this.cantidad = cantidad;
    }
    
    
    //Constructor Vacio
    public UMMovil() {
    }

    //Getter y Setter

    public int getCodU() {
        return codU;
    }

    public void setCodU(int codU) {
        this.codU = codU;
    }

    public String getunidadmedida() {
        return unidadmedida;
    }

    public void setunidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    

}
