package Modelo;

public class ArticuloMovil {

    private int idproducto;
    private String dependencia;
    private int iddependencia;
    private String codinterno;
    private String codBarra;
    private String descripcion;
    private int precio_costo;
    private int ganancia;
    private int precio_venta;
    private double stock;
    private int um;
    private int division;
    private int iva;
    private String ventam;
    private double cantm;
    private int preciominorista;
    private int gananciaminorista;
    private String prom;
    private double cant_prom;
    private int precio_prom;
    private String porc_prom;
    private String r1;
    private String r2;

    public ArticuloMovil(int idproducto, String dependencia, int iddependencia, String codinterno, String codBarra, String descripcion, int precio_costo, int ganancia, int precio_venta, double stock, int um, int division, int iva, String ventam, double cantm, int preciominorista, int gananciaminorista, String prom, double cant_prom, int precio_prom, String porc_prom) {
        this.idproducto = idproducto;
        this.dependencia = dependencia;
        this.iddependencia = iddependencia;
        this.codinterno = codinterno;
        this.codBarra = codBarra;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.ganancia = ganancia;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.um = um;
        this.division = division;
        this.iva = iva;
        this.ventam = ventam;
        this.cantm = cantm;
        this.preciominorista = preciominorista;
        this.gananciaminorista = gananciaminorista;
        this.prom = prom;
        this.cant_prom = cant_prom;
        this.precio_prom = precio_prom;
        this.porc_prom = porc_prom;
    }

    public ArticuloMovil(int idproducto, String dependencia, int iddependencia, String codinterno, String codBarra, String descripcion, int precio_costo, int ganancia, int precio_venta, double stock, int um, int division, int iva, String ventam, double cantm, int preciominorista, int gananciaminorista, String prom, double cant_prom, int precio_prom, String porc_prom, String r1, String r2) {
        this.idproducto = idproducto;
        this.dependencia = dependencia;
        this.iddependencia = iddependencia;
        this.codinterno = codinterno;
        this.codBarra = codBarra;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.ganancia = ganancia;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.um = um;
        this.division = division;
        this.iva = iva;
        this.ventam = ventam;
        this.cantm = cantm;
        this.preciominorista = preciominorista;
        this.gananciaminorista = gananciaminorista;
        this.prom = prom;
        this.cant_prom = cant_prom;
        this.precio_prom = precio_prom;
        this.porc_prom = porc_prom;
        this.r1 = r1;
        this.r2 = r2;
    }
    
    //Constructor para actualizar stock
    public ArticuloMovil(int idproducto, double stock) {
        this.idproducto = idproducto;
        this.stock = stock;
    }
    //Constructor Vacio
    public ArticuloMovil() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public int getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(int iddependencia) {
        this.iddependencia = iddependencia;
    }
    

    public String getCodinterno() {
        return codinterno;
    }

    public void setCodinterno(String codinterno) {
        this.codinterno = codinterno;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(int precio_costo) {
        this.precio_costo = precio_costo;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public int getUm() {
        return um;
    }

    public void setUm(int um) {
        this.um = um;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public String getVentam() {
        return ventam;
    }

    public void setVentam(String ventam) {
        this.ventam = ventam;
    }

    public double getCantm() {
        return cantm;
    }

    public void setCantm(double cantm) {
        this.cantm = cantm;
    }

    public int getPreciominorista() {
        return preciominorista;
    }

    public void setPreciominorista(int preciominorista) {
        this.preciominorista = preciominorista;
    }

    public int getGananciaminorista() {
        return gananciaminorista;
    }

    public void setGananciaminorista(int gananciaminorista) {
        this.gananciaminorista = gananciaminorista;
    }

    public String getProm() {
        return prom;
    }

    public void setProm(String prom) {
        this.prom = prom;
    }

    public double getCant_prom() {
        return cant_prom;
    }

    public void setCant_prom(double cant_prom) {
        this.cant_prom = cant_prom;
    }

    public int getPrecio_prom() {
        return precio_prom;
    }

    public void setPrecio_prom(int precio_prom) {
        this.precio_prom = precio_prom;
    }

    public String getPorc_prom() {
        return porc_prom;
    }

    public void setPorc_prom(String porc_prom) {
        this.porc_prom = porc_prom;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }
    
    
    
    
      
}
