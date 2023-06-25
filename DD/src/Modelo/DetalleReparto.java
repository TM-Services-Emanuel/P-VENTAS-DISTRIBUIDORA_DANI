package Modelo;

public class DetalleReparto {

    private int idreparto;
    private int idproducto;
    private double carga_total;
    private int monto_carga_total;
    private double recambio;
    private int monto_recambio;
    private double venta;
    private int monto_venta;
    private double devuelve;
    private int monto_devuelve;

    public DetalleReparto(int idreparto, int idproducto, double carga_total, int monto_carga_total, double recambio, int monto_recambio, double venta, int monto_venta, double devuelve, int monto_devuelve) {
        this.idreparto = idreparto;
        this.idproducto = idproducto;
        this.carga_total = carga_total;
        this.monto_carga_total = monto_carga_total;
        this.recambio = recambio;
        this.monto_recambio = monto_recambio;
        this.venta = venta;
        this.monto_venta = monto_venta;
        this.devuelve = devuelve;
        this.monto_devuelve = monto_devuelve;
    }

    public DetalleReparto(int idproducto) {
        this.idproducto = idproducto;
    }
    
    

    public int getIdreparto() {
        return idreparto;
    }

    public void setIdreparto(int idreparto) {
        this.idreparto = idreparto;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public double getCarga_total() {
        return carga_total;
    }

    public void setCarga_total(double carga_total) {
        this.carga_total = carga_total;
    }

    public int getMonto_carga_total() {
        return monto_carga_total;
    }

    public void setMonto_carga_total(int monto_carga_total) {
        this.monto_carga_total = monto_carga_total;
    }

    public double getRecambio() {
        return recambio;
    }

    public void setRecambio(double recambio) {
        this.recambio = recambio;
    }

    public int getMonto_recambio() {
        return monto_recambio;
    }

    public void setMonto_recambio(int monto_recambio) {
        this.monto_recambio = monto_recambio;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public int getMonto_venta() {
        return monto_venta;
    }

    public void setMonto_venta(int monto_venta) {
        this.monto_venta = monto_venta;
    }

    public double getDevuelve() {
        return devuelve;
    }

    public void setDevuelve(double devuelve) {
        this.devuelve = devuelve;
    }

    public int getMonto_devuelve() {
        return monto_devuelve;
    }

    public void setMonto_devuelve(int monto_devuelve) {
        this.monto_devuelve = monto_devuelve;
    }

    
        
    
    
}

