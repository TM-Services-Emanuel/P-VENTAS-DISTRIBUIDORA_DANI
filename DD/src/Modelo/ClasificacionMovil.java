package Modelo;

public class ClasificacionMovil {
    
    private int codM;
    private String clasificacion;
    private String especificacion;

    //Constructor
    public ClasificacionMovil(int codM, String clasificacion,String especificacion) {
        this.codM = codM;
        this.clasificacion = clasificacion;
        this.especificacion = especificacion;
    }
    
    //Constructor Vacio
    public ClasificacionMovil() {
    }

    //Getter y Setter
    public int getCodM() {
        return codM;
    }

    public void setCodM(int codM) {
        this.codM = codM;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }
    

}
