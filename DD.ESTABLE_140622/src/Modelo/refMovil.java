/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ADMIN
 */
public class refMovil {
    private int idemision;
    private int nventa;

    public refMovil(int idemision, int nventa) {
        this.idemision = idemision;
        this.nventa = nventa;
    }

    public refMovil() {
    }

    public int getIdemision() {
        return idemision;
    }

    public void setIdemision(int idemision) {
        this.idemision = idemision;
    }

    public int getNventa() {
        return nventa;
    }

    public void setNventa(int nventa) {
        this.nventa = nventa;
    }    
}
