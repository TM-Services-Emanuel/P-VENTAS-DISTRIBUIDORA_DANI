package Modelo;

public class Familia {
    
    private int codFamilia;
    private String Familia;
    private double ganacia;
    private int iva;
    private String usuario;

    //Constructor
    public Familia(int codFamilia, String Familia, double ganacia, int iva, String usuario) {
        this.codFamilia = codFamilia;
        this.Familia = Familia;
        this.ganacia=ganacia;
        this.usuario=usuario;
        this.iva=iva;
    }
    
    //Constructor Vacio
    public Familia(){}
    
    //Getter y Setter
    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String Familia) {
        this.Familia = Familia;
    }

    public int getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(int codFamilia) {
        this.codFamilia = codFamilia;
    }
    
    public double getGanacia() {
        return ganacia;
    }

    public void setGanacia(double ganacia) {
        this.ganacia = ganacia;
    }
    
    public double getIVA() {
        return iva;
    }

    public void setIVA(int iva) {
        this.iva = iva;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
