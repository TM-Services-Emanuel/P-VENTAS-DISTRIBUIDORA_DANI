package Modelo;

public class DetalleFactura {

    private int codFactura;
    private int codArticulo;
    private double cantidad;
    private int precio;
    private int total;

    //Contructor Completo
    public DetalleFactura(int codFactura, int codArticulo, double cantidad, int precio, int total) {
        this.codFactura = codFactura;
        this.codArticulo = codArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    //Constructor sin codigo de Factura
    public DetalleFactura(int codArticulo, double cantidad, int precio, int total) {
        this.codArticulo = codArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public DetalleFactura(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    //Constructor Vacio
    public DetalleFactura() {
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

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
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
