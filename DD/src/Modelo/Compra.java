package Modelo;

public class Compra {

    private int codCompra;
    private int codProveedor;
    private String condPago;
    private String Fact;
    private String fecha;
    private int total;
    private int exenta;
    private int iva5;
    private int iva10;

    //Constructor
    public Compra(int codCompra, int codProveedor, String condPago,String Fact, String fecha, int total, int exenta, int iva5, int iva10) {
        this.codCompra = codCompra;
        this.codProveedor = codProveedor;
        this.condPago = condPago;
        this.Fact = Fact;
        this.fecha = fecha;
        this.total = total;
        this.exenta = exenta;
        this.iva5 = iva5;
        this.iva10 = iva10;
    }

    //Constructor Vacio
    public Compra() {
    }

    //Getter y Setter
    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public int getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getCondPago() {
        return condPago;
    }

    public void setCondPago(String condPago) {
        this.condPago = condPago;
    }
    
    public String getFact() {
        return Fact;
    }

    public void setFact(String Fact) {
        this.Fact = Fact;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getExenta() {
        return exenta;
    }

    public void setExenta(int exenta) {
        this.exenta = exenta;
    }
    
    public int getIVA5() {
        return iva5;
    }

    public void setIVA5(int iva5) {
        this.iva5 = iva5;
    }

    public int getIVA10() {
        return iva10;
    }

    public void setIVA10(int iva10) {
        this.iva10 = iva10;
    }
}
