package Modelo;

public class Vendedor {
    
    private int codVe;
    private int idfuncion;
    private int idmovil;
    private String nombreV;
    private String direccion;
    private String telefono;
    private String celular;
    private String Sueldo;
    private double comision;
    public String Obs;

    //Constructor
    public Vendedor(int codVe, int idfuncion, int idmovil, String nombreV, String direccion, String telefono, String celular, String Sueldo, double comision, String Obs) {
        this.codVe = codVe;
        this.idfuncion = idfuncion;
        this.idmovil = idmovil;
        this.nombreV = nombreV;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.Sueldo = Sueldo;
        this.comision = comision;
        this.Obs = Obs;
    }

    //Constructor Vacio

    public Vendedor() {
    }
       

    public int getCodVe() {
        return codVe;
    }

    public void setCodVe(int codVe) {
        this.codVe = codVe;
    }

    public int getIdfuncion() {
        return idfuncion;
    }

    public void setIdfuncion(int idfuncion) {
        this.idfuncion = idfuncion;
    }

    public int getIdmovil() {
        return idmovil;
    }

    public void setIdmovil(int idmovil) {
        this.idmovil = idmovil;
    }

    public String getNombreV() {
        return nombreV;
    }

    public void setNombreV(String nombreV) {
        this.nombreV = nombreV;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSueldo() {
        return Sueldo;
    }

    public void setSueldo(String Sueldo) {
        this.Sueldo = Sueldo;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }
    
}
