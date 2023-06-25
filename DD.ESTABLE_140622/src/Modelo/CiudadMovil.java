package Modelo;

public class CiudadMovil {
    
    private int codCiudad;
    private String ciudad;
    private int departamento;
    
    //Constructor
    public CiudadMovil(int codCiudad, String ciudad, int departamento) {
        this.codCiudad = codCiudad;
        this.ciudad = ciudad;
        this.departamento=departamento;
    }
    
    //Constructor Vacio
    public CiudadMovil() {}
    
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
    
    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
    
}
