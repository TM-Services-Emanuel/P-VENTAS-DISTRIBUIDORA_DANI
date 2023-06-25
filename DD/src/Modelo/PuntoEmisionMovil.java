package Modelo;

public class PuntoEmisionMovil {
    
    private int idEmision;
    private int idTimbrado;
    private String Establecimiento;
    private String puntoEmision;
    private String direccion;
    private int facturaInicio;
    private int facturaFin;
    private int facturaActual;
    private String Tipo;
    private String Tipo2;
    private String Ip;
    private String Estado;
    

    //Constructor

    public PuntoEmisionMovil(int idEmision, int idTimbrado, String Establecimiento, String puntoEmision, String direccion, int facturaInicio, int facturaFin, int facturaActual, String Tipo, String Tipo2, String Ip, String Estado) {
        this.idEmision = idEmision;
        this.idTimbrado = idTimbrado;
        this.Establecimiento = Establecimiento;
        this.puntoEmision = puntoEmision;
        this.direccion = direccion;
        this.facturaInicio = facturaInicio;
        this.facturaFin = facturaFin;
        this.facturaActual = facturaActual;
        this.Tipo = Tipo;
        this.Tipo2 = Tipo2;
        this.Ip = Ip;
        this.Estado = Estado;
    }
    public PuntoEmisionMovil(int idEmision, String Establecimiento, String puntoEmision, String direccion, int facturaInicio, int facturaFin, int facturaActual, String Tipo, String Tipo2, String Ip, String Estado) {
        this.idEmision = idEmision;
        this.Establecimiento = Establecimiento;
        this.puntoEmision = puntoEmision;
        this.direccion = direccion;
        this.facturaInicio = facturaInicio;
        this.facturaFin = facturaFin;
        this.facturaActual = facturaActual;
        this.Tipo = Tipo;
        this.Tipo2 = Tipo2;
        this.Ip = Ip;
        this.Estado = Estado;
    }

    

    public PuntoEmisionMovil() {
    }

    public int getIdEmision() {
        return idEmision;
    }

    public void setIdEmision(int idEmision) {
        this.idEmision = idEmision;
    }

    public int getIdTimbrado() {
        return idTimbrado;
    }

    public void setIdTimbrado(int idTimbrado) {
        this.idTimbrado = idTimbrado;
    }

    public String getEstablecimiento() {
        return Establecimiento;
    }

    public void setEstablecimiento(String Establecimiento) {
        this.Establecimiento = Establecimiento;
    }

    public String getPuntoEmision() {
        return puntoEmision;
    }

    public void setPuntoEmision(String puntoEmision) {
        this.puntoEmision = puntoEmision;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getFacturaInicio() {
        return facturaInicio;
    }

    public void setFacturaInicio(int facturaInicio) {
        this.facturaInicio = facturaInicio;
    }

    public int getFacturaFin() {
        return facturaFin;
    }

    public void setFacturaFin(int facturaFin) {
        this.facturaFin = facturaFin;
    }

    public int getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(int facturaActual) {
        this.facturaActual = facturaActual;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipo2() {
        return Tipo2;
    }

    public void setTipo2(String Tipo2) {
        this.Tipo2 = Tipo2;
    }
    
    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    
       
}
