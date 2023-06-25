package Modelo;

import java.io.Serializable;

public class DetalleSalida implements Serializable{
    
    private int codArt;
    private int codSalida;
    private int codM;
    private double cant;
    private int prec;
    private int monto;

    public DetalleSalida() {}

    public DetalleSalida(int codArt, int codSalida, int codM, double cant, int prec, int monto) {
        this.codArt = codArt;
        this.codSalida = codSalida;
        this.codM = codM;
        this.cant = cant;
        this.prec = prec;
        this.monto = monto;
    }

    public DetalleSalida(int codArt, int codM, int prec, double cant, int monto) {
        this.codArt = codArt;
        this.codM = codM;
        this.cant = cant;
        this.prec = prec;
        this.monto = monto;
    }

    public double getCant() {
        return cant;
    }

    public void setCant(double cant) {
        this.cant = cant;
    }

    public int getCodArt() {
        return codArt;
    }

    public void setCodArt(int codArt) {
        this.codArt = codArt;
    }

    public int getCodM() {
        return codM;
    }

    public void setCodM(int codM) {
        this.codM = codM;
    }

    public int getCodSalida() {
        return codSalida;
    }

    public void setCodSalida(int codSalida) {
        this.codSalida = codSalida;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getPrec() {
        return prec;
    }

    public void setPrec(int prec) {
        this.prec = prec;
    }
    
}
