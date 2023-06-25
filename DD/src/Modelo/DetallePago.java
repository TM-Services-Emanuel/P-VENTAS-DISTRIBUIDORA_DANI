package Modelo;

public class DetallePago {
    
    private int codCompra;
    private String factura;
    private int monto_compra;
    private int monto_pago;
    private int saldo;
    private String estado;
    
    //Constructor sin codigo de Salida

    public DetallePago(int codCompra, String factura, int monto_compra, int monto_pago, int saldo, String estado) {
        this.codCompra = codCompra;
        this.factura = factura;
        this.monto_compra = monto_compra;
        this.monto_pago = monto_pago;
        this.saldo = saldo;
        this.estado = estado;
    }

    public DetallePago(int codCompra, String factura) {
        this.codCompra = codCompra;
        this.factura = factura;
    }
    
    public DetallePago(int codCompra, int monto_pago, String estado) {
        this.codCompra = codCompra;
        this.monto_pago = monto_pago;
        this.estado= estado;
    }
    
    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public int getMonto_compra() {
        return monto_compra;
    }

    public void setMonto_compra(int monto_compra) {
        this.monto_compra = monto_compra;
    }

    public int getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(int monto_pago) {
        this.monto_pago = monto_pago;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    
    
    
}
