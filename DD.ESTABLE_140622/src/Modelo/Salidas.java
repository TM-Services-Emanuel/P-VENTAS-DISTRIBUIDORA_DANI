package Modelo;

public class Salidas {

    private int codSal;
    private int codProv;
    private String fecha;
    private String hora;
    private int monto;
    private String Obs;
    private String usu;

    //Constructor
    public Salidas(int codSal, int codProv, String fecha, int monto, String Obs, String usu ) {
        this.codSal = codSal;
        this.codProv = codProv;
        this.fecha = fecha;
        this.monto = monto;
        this.Obs = Obs;
        this.usu = usu;
    }

    //Constructor Vacio
    public Salidas() {
    }

    //Getter y Setter
    public int getCodSal() {
        return codSal;
    }

    public void setCodSal(int codSal) {
        this.codSal = codSal;
    }
    
    public int getCodProv() {
        return codProv;
    }

    public void setCodProv(int codProv) {
        this.codProv = codProv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    
    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }
    
    public String getUsuario() {
        return usu;
    }

    public void setUsuario(String usu) {
        this.usu = usu;
    }

}
