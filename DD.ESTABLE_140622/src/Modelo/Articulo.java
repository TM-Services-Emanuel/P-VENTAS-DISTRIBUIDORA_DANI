package Modelo;

public class Articulo {

    private int codArticulo;
    private int codFam;
    private int codLab;
    private int codProv;
    private String codBarra;
    private String descripcion;
    private String principio;
    private String accion;
    private int costo;
    private double costoiva;
    private int iva;
    private float stock;
    private int stockMin;
    private String vencimiento;
    private int ganancia;
    private int descuento;
    private int ppublico;
    private int pventa;
    private String venta;
    private String tipo;
    private String prodActivo;
    private String VM;
    private int CM;
    private int PM;
    private String usu;

    //Constructor sin la fotografia del articulo
    public Articulo(int codArticulo, int codFam, int codLab, int codProv, String codBarra, String descripcion, String principio, String accion,
            int costo, double costoiva, int iva, float stock, int stockMin, String vencimiento, int ganancia, int descuento, int ppublico,
            int pventa, String venta, String tipo, String prodActivo, String VM, int CM, int PM, String usu) {
        this.codArticulo = codArticulo;
        this.codFam = codFam;
        this.codLab = codLab;
        this.codProv = codProv;
        this.codBarra = codBarra;
        this.descripcion = descripcion;
        this.principio = principio;
        this.accion = accion;
        this.costo = costo;
        this.costoiva = costoiva;
        this.iva = iva;
        this.stock = stock;
        this.stockMin = stockMin;
        this.vencimiento = vencimiento;
        this.ganancia = ganancia;
        this.descuento = descuento;
        this.ppublico = ppublico;
        this.pventa = pventa;
        this.venta = venta;
        this.tipo = tipo;
        this.prodActivo= prodActivo;
        this.VM = VM;
        this.CM = CM;
        this.PM = PM;
        this.usu = usu;
    }

    //Constructor para actualizar stock
    public Articulo(int codArticulo, float stock) {
        this.codArticulo = codArticulo;
        this.stock = stock;
    }

    //Constructor Vacio
    public Articulo() {
    }

    //Getter y Setter
    public int getCodArticulo() {
        return codArticulo;
    }
    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public int getCodFamilia() {
        return codFam;
    }
    public void setCodFamilia(int codFam) {
        this.codFam = codFam;
    }
    
    public int getCodLaboratorio() {
        return codLab;
    }
    public void setCodLaboratorio (int codLab){
        this.codLab = codLab;
    }
    
    public int getCodProveedor() {
        return codProv;
    }
    public void setCodProveedor (int codProv){
        this.codProv = codProv;
    }
    
    public String getCodBarra() {
        return codBarra;
    }
    public void setCodBarra (String codBarra){
        this.codBarra = codBarra;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion (String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getPrincipio() {
        return principio;
    }
    public void setPrincipio (String principio){
        this.principio = principio;
    }
    
    public String getAccion() {
        return accion;
    }
    public void setAccion (String accion){
        this.accion = accion;
    }
    
    public int getCosto() {
        return costo;
    }
    public void setCosto (int costo){
        this.costo = costo;
    }
    
    public double getCostoIva() {
        return costoiva;
    }
    public void setCostoIva (double costoiva){
        this.costoiva = costoiva;
    }
    
    public int getIVA() {
        return iva;
    }
    public void setIVA (int iva){
        this.iva = iva;
    }
    
    public float getStock() {
        return stock;
    }
    public void setStock (float stock){
        this.stock = stock;
    }
    
    public int getStockMin() {
        return stockMin;
    }
    public void setStockMin (int stockMin){
        this.stockMin = stockMin;
    }
    
    public String getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento (String vencimiento){
        this.vencimiento = vencimiento;
    }
    
    public int getGanancia() {
        return ganancia;
    }
    public void setGanancia (int ganancia){
        this.ganancia = ganancia;
    }
    
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento (int descuento){
        this.descuento = descuento;
    }
    
    public int getPrecioPublico() {
        return ppublico;
    }
    public void setPrecioPublico (int ppublico){
        this.ppublico = ppublico;
    }
    
    public int getPrecioVenta() {
        return pventa;
    }
    public void setPrecioVenta (int pventa){
        this.pventa = pventa;
    }
    
    public String getVenta() {
        return venta;
    }
    public void setVenta (String venta){
        this.venta = venta;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo (String tipo){
        this.tipo = tipo;
    }
    
    public String getProdActivo() {
        return prodActivo;
    }
    public void setProdActivo (String prodActivo){
        this.prodActivo = prodActivo;
    }
    
    public String getVM() {
        return VM;
    }
    public void setVM (String VM){
        this.VM = VM;
    }
    
    public int getCM() {
        return CM;
    }
    public void setCM (int CM){
        this.CM = CM;
    }
    
    public int getPM() {
        return PM;
    }
    public void setPM (int PM){
        this.PM = PM;
    }
    
    public String getUsuario() {
        return usu;
    }
    public void setUsuario (String usu){
        this.usu = usu;
    }
}
