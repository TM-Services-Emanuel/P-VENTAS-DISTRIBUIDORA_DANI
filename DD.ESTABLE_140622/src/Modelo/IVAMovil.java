package Modelo;

public class IVAMovil {
    
    private int codI;
    private int impuesto;
    private String descripcion;
    
    //Constructor
    public IVAMovil(int codI, int impuesto, String descripcion) {
        this.codI = codI;
        this.impuesto = impuesto;
        this.descripcion = descripcion;
    }
    
    //Constructor Vacio
    public IVAMovil() {
    }

    //Getter y Setter

    public int getCodI() {
        return codI;
    }

    public void setCodI(int codI) {
        this.codI = codI;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

}
