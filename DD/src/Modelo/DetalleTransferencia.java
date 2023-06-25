package Modelo;

public class DetalleTransferencia {

    private int codTransferencia;
    private int codArticulo;
    private double cantidad;
    private int precio;
    private int total;

    //Contructor Completo
    public DetalleTransferencia(int codTransferencia, int codArticulo, double cantidad, int precio, int total) {
        this.codTransferencia = codTransferencia;
        this.codArticulo = codArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    //Constructor sin codigo de Factura
    public DetalleTransferencia(int codArticulo, double cantidad, int precio, int total) {
        this.codArticulo = codArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public DetalleTransferencia(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    //Constructor Vacio
    public DetalleTransferencia() {
    }

    //Getter y Setter
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public int getCodTransferencia() {
        return codTransferencia;
    }

    public void setCodTransferencia(int codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
