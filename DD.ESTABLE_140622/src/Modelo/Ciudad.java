package Modelo;

public class Ciudad {
    
    private int codCiudad;
    private String ciudad;
    private String usuario;
    
    //Constructor
    public Ciudad(int codCiudad, String ciudad, String usuario) {
        this.codCiudad = codCiudad;
        this.ciudad = ciudad;
        this.usuario=usuario;
    }
    
    //Constructor Vacio
    public Ciudad() {}
    
    //Getter y Setter
    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
