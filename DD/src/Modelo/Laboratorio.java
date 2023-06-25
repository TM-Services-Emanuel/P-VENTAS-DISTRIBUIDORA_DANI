package Modelo;

public class Laboratorio {
    
    private int codLaboratorio;
    private String Laboratorio;
    private String usuario;

    //Constructor
    public Laboratorio(int codLaboratorio, String Laboratorio, String Usuario) {
        this.codLaboratorio = codLaboratorio;
        this.Laboratorio = Laboratorio;
        this.usuario=Usuario;
    }
    
    //Constructor Vacio

    public Laboratorio() {
    }
    
    //Getter y Setter

    public int getCodLaboratorio() {
        return codLaboratorio;
    }

    public void SetCodLaboratorio(int codLaboratorio) {
        this.codLaboratorio = codLaboratorio;
    }

    public String getLaboratorio() {
        return Laboratorio;
    }

    public void setLaboratorio(String Laboratorio) {
        this.Laboratorio = Laboratorio;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }
}
