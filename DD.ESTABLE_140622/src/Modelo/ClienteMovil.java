package Modelo;

public class ClienteMovil {
    
    private int idCliente;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String telefono;
    private int codCiudad;
    
    
    //Contructor
    public ClienteMovil(int idCliente, String razonSocial, String ruc, String direccion, String telefono, int codCiudad) {
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codCiudad = codCiudad;
    }

    //Constructor Vacio
    public ClienteMovil() {
    }

    //Getter y Setter    

    public int getidCliente() {
        return idCliente;
    }

    public void setidCliente(int idClente) {
        this.idCliente = idClente;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
