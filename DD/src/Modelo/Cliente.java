package Modelo;

public class Cliente {
    
    private int codCliente;
    private String razonSocial;
    private int codCiudad;
    private String ruc;
    private String contacto;
    private String celu;
    private String telefon;
    private String direccion;
    private String Cont;
    private String Cred;
    private int limCuenta;
    private String osb;
    private String usuario;
    
    
    //Contructor
    public Cliente(int codCliente, int codCiudad, String razonSocial, String ruc, String contacto, String celu, String telefon, String direccion,String osb, String Cont, String Cred ,int limCuenta, String usuario) {
        this.codCliente = codCliente;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.codCiudad = codCiudad;
        this.telefon = telefon;
        this.celu = celu;
        this.ruc = ruc;
        this.limCuenta = limCuenta;
        this.direccion = direccion;
        this.osb = osb;
        this.Cont= Cont;
        this.Cred= Cred;
        this.usuario=usuario;
    }
    

    //Constructor Vacio
    public Cliente() {
    }

    //Getter y Setter    
    public String getCelu() {
        return celu;
    }

    public void setCelu(String celu) {
        this.celu = celu;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLimCuenta() {
        return limCuenta;
    }

    public void setLimCuenta(int limCuenta) {
        this.limCuenta = limCuenta;
    }

    public String getOsb() {
        return osb;
    }

    public void setOsb(String osb) {
        this.osb = osb;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    public String getContado() {
        return Cont;
    }

    public void setContado(String Cont) {
        this.Cont = Cont;
    }
    
    public String getCredito() {
        return Cred;
    }

    public void setCredito(String Cred) {
        this.Cred = Cred;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
