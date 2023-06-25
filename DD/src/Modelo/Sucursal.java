package Modelo;

public class Sucursal {
    
    private int codsucursal;
    private String sucursal;
    private int codEmpresa;
    private String usuario;

    //Constructor
    public Sucursal(int codSucursal, String Sucursal, int codEmpresa, String usuario) {
        this.codsucursal = codSucursal;
        this.sucursal= Sucursal;
        this.codEmpresa=codEmpresa;
        this.usuario=usuario;
    }
    
    public Sucursal(int codSucursal, String Sucursal, String usuario) {
        this.codsucursal = codSucursal;
        this.sucursal= Sucursal;
        this.usuario=usuario;
    }
    
    //Constructor Vacio
    public Sucursal() {}
    
    //Getter y Setter
    public int getCodSucursal() {
        return codsucursal;
    }

    public void setCodSucursal(int codSucursal) {
        this.codsucursal = codSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.sucursal = Sucursal;
    }
    
    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int CodEmpresa) {
        this.codEmpresa = CodEmpresa;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
