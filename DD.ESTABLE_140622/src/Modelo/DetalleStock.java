package Modelo;

public class DetalleStock {
    
    private int codArt;
    private int codMot;
    private double stock_a;
    private double stock_n;
    private String obs;

    //Constructor
    public DetalleStock(int codArt, int codMot, double stock_a, double stock_n, String obs) {
        this.codArt= codArt;
        this.codMot = codMot;
        this.stock_a = stock_a;
        this.stock_n = stock_n;
        this.obs = obs;
    }

    //Constructor Vacio
    public DetalleStock() {
    }

    //Getter y Setter
    public int getCodArt() {
        return codArt;
    }

    public void setCodArt(int codArt) {
        this.codArt = codArt;
    }

    public int getCodMot() {
        return codMot;
    }

    public void setCodMot(int codMot) {
        this.codMot = codMot;
    }
    public double getStock_a() {
        return stock_a;
    }

    public void setStock_a(double stock_a) {
        this.stock_a = stock_a;
    }
    public double getStock_n() {
        return stock_n;
    }

    public void setStock_n(double stock_n) {
        this.stock_n = stock_n;
    }
    public String getObs() {
        return obs;
    }

   public void setObs(String obs) {
        this.obs = obs;
    }
    
    
}
