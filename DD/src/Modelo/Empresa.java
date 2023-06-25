package Modelo;

public class Empresa {
    
    private int codEmpresa;
    private String empresa;
    private String ruc;
    private String direccion;
    private String telefono;
    private int idciudad;
    private String visual;
    private String usuario;
    //Constructor

    public Empresa(int codEmpresa, String empresa, String ruc, String direccion, String telefono, int idciudad) {
        this.codEmpresa = codEmpresa;
        this.empresa = empresa;
        this.ruc=ruc;
        this.direccion=direccion;
        this.telefono=telefono;
        this.idciudad=idciudad;
    }
    //Constructor Vacio

    public Empresa() {
    }
    
    //Getter y Setter

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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
    
    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }
    
    
}
