package Modelo;

public class Proveedor {
    
    private int codP;
    private int codCiudad;
    private String razoS;
    private String Ruc;
    private String contac;
    private String celu;
    private String telef;
    private String direccion;
    private String obs;
    private String usuario;
    
    //Constructor
    public Proveedor(int codP, int codCiudad, String razoS, String Ruc, String contac, String celu, String telef, String direccion, String obs, String usuario) {
        this.codP = codP;
        this.codCiudad=codCiudad;
        this.razoS = razoS;
        this.Ruc = Ruc;
        this.contac = contac;
        this.celu = celu;
        this.telef = telef;
        this.direccion = direccion;
        this.obs = obs;
        this.usuario=usuario;
    }
    
    //Constructor Vacio
    public Proveedor() {}
    
    //Getter y Setter
    public String getCelu() {
        return celu;
    }

    public void setCelu(String celu) {
        this.celu = celu;
    }

    public int getCodP() {
        return codP;
    }

    public void setCodP(int codP) {
        this.codP = codP;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getContac() {
        return contac;
    }

    public void setContac(String contac) {
        this.contac = contac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getRazoS() {
        return razoS;
    }

    public void setRazoS(String razoS) {
        this.razoS = razoS;
    }
    
    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String Ruc) {
        this.Ruc = Ruc;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
