package Modelo;

public class Motivo {
    
    private int codM;
    private String motivo;
    private String usuario;

    //Constructor
    public Motivo(int codM, String motivo, String usuario) {
        this.codM = codM;
        this.motivo = motivo;
        this.usuario = usuario;
    }

    //Constructor Vacio
    public Motivo() {
    }

    //Getter y Setter
    public int getCodM() {
        return codM;
    }

    public void setCodM(int codM) {
        this.codM = codM;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
