/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class reparto {
    private int id_reparto;
    private int id_caja;
    private int id_responsable;
    private double comision_resp;
    private int idMovil;
    private String preferencia;
    private int idchofer;
    private String chofer;
    private double comision_chof;
    private Date fecha;
    private Date hora;
    private int valorRA;
    private int valorC;
    private int valorT;
    private int valorTotal;
    private int valorRecambio;
    private int valorDevuelto;
    private int efectivoEntregar;
    private int efectivoEntregado;
    private int diferencia;
    private int recolectorA;
    private int recolectorDA;
    private int recolectorDAC;
    private int recolectorI;
    private int recolectorT;
    private int recolectorF;
    private int recolectorV;
    private int recolectorDF;
    private int RecolectorDFAC;
    private int km_anterior;
    private int km_actual;
    private int km_recorrido;
    private String activo;
    private String cerrado;

    public reparto(int id_reparto, int id_caja, int id_responsable, double comision_resp, int idMovil, String preferencia, int idchofer, String chofer, double comision_chof, Date fecha, Date hora, 
            int valorRA, int valorC, int valorT ,int valorTotal, int valorRecambio, int valorDevuelto, int efectivoEntregar, int efectivoEntregado, int diferencia, int recolectorA, int recolectorDA, int recolectorDAC, int recolectorI, int recolectorT, int recolectorF, int recolectorV, int recolectorDF, int RecolectorDFAC,int km_anterior, int km_actual, int km_recorrido, String activo, String cerrado) {
        this.id_reparto = id_reparto;
        this.id_caja = id_caja;
        this.id_responsable = id_responsable;
        this.comision_resp = comision_resp;
        this.idMovil = idMovil;
        this.preferencia = preferencia;
        this.idchofer = idchofer;
        this.chofer = chofer;
        this.comision_chof = comision_chof;
        this.fecha = fecha;
        this.hora = hora;
        this.valorRA = valorRA;
        this.valorC = valorC;
        this.valorT = valorT;
        this.valorTotal = valorTotal;
        this.valorRecambio = valorRecambio;
        this.valorDevuelto = valorDevuelto;
        this.efectivoEntregar = efectivoEntregar;
        this.efectivoEntregado = efectivoEntregado;
        this.diferencia = diferencia;
        this.recolectorA = recolectorA;
        this.recolectorDA = recolectorDA;
        this.recolectorDAC = recolectorDAC;
        this.recolectorI = recolectorI;
        this.recolectorT = recolectorT;
        this.recolectorF = recolectorF;
        this.recolectorV = recolectorV;
        this.recolectorDF = recolectorDF;
        this.RecolectorDFAC = RecolectorDFAC;
        this.km_anterior = km_anterior;
        this.km_actual = km_actual;
        this.km_recorrido = km_recorrido;
        this.activo = activo;
        this.cerrado = cerrado;
    }

    
    
    public reparto(){
        
    }

    public int getId_reparto() {
        return id_reparto;
    }

    public void setId_reparto(int id_reparto) {
        this.id_reparto = id_reparto;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public int getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(int id_responsable) {
        this.id_responsable = id_responsable;
    }

    public double getComision_resp() {
        return comision_resp;
    }

    public void setComision_resp(double comision_resp) {
        this.comision_resp = comision_resp;
    }

    public int getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(int idMovil) {
        this.idMovil = idMovil;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public int getIdchofer() {
        return idchofer;
    }

    public void setIdchofer(int idchofer) {
        this.idchofer = idchofer;
    }
    
    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public double getComision_chof() {
        return comision_chof;
    }

    public void setComision_chof(double comision_chof) {
        this.comision_chof = comision_chof;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getValorRA() {
        return valorRA;
    }

    public void setValorRA(int valorRA) {
        this.valorRA = valorRA;
    }

    public int getValorC() {
        return valorC;
    }

    public void setValorC(int valorC) {
        this.valorC = valorC;
    }

    public int getValorT() {
        return valorT;
    }

    public void setValorT(int valorT) {
        this.valorT = valorT;
    }
    
    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getValorRecambio() {
        return valorRecambio;
    }

    public void setValorRecambio(int valorRecambio) {
        this.valorRecambio = valorRecambio;
    }

    public int getValorDevuelto() {
        return valorDevuelto;
    }

    public void setValorDevuelto(int valorDevuelto) {
        this.valorDevuelto = valorDevuelto;
    }

    public int getEfectivoEntregar() {
        return efectivoEntregar;
    }

    public void setEfectivoEntregar(int efectivoEntregar) {
        this.efectivoEntregar = efectivoEntregar;
    }

    public int getEfectivoEntregado() {
        return efectivoEntregado;
    }

    public void setEfectivoEntregado(int efectivoEntregado) {
        this.efectivoEntregado = efectivoEntregado;
    }

    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
    }

    public int getRecolectorI() {
        return recolectorI;
    }

    public void setRecolectorI(int recolectorI) {
        this.recolectorI = recolectorI;
    }

    public int getRecolectorF() {
        return recolectorF;
    }

    public void setRecolectorF(int recolectorF) {
        this.recolectorF = recolectorF;
    }
    
    public int getkm_anterior() {
        return km_anterior;
    }

    public void setkm_anterior(int km_anterior) {
        this.km_anterior = km_anterior;
    }
    
    public int getkm_actual() {
        return km_actual;
    }

    public void setkm_actual(int km_actual) {
        this.km_actual = km_actual;
    }
    
    public int getkm_recorrido() {
        return km_recorrido;
    }

    public void setkm_recorrido(int km_recorrido) {
        this.km_recorrido = km_recorrido;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCerrado() {
        return cerrado;
    }

    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }

    public int getRecolectorA() {
        return recolectorA;
    }

    public void setRecolectorA(int recolectorA) {
        this.recolectorA = recolectorA;
    }

    public int getRecolectorDA() {
        return recolectorDA;
    }

    public void setRecolectorDA(int recolectorDA) {
        this.recolectorDA = recolectorDA;
    }

    public int getRecolectorT() {
        return recolectorT;
    }

    public void setRecolectorT(int recolectorT) {
        this.recolectorT = recolectorT;
    }

    public int getRecolectorDF() {
        return recolectorDF;
    }

    public void setRecolectorDF(int recolectorDF) {
        this.recolectorDF = recolectorDF;
    }   

    public int getRecolectorV() {
        return recolectorV;
    }

    public void setRecolectorV(int recolectorV) {
        this.recolectorV = recolectorV;
    }

    public int getRecolectorDFAC() {
        return RecolectorDFAC;
    }

    public void setRecolectorDFAC(int RecolectorDFAC) {
        this.RecolectorDFAC = RecolectorDFAC;
    }

    public int getRecolectorDAC() {
        return recolectorDAC;
    }

    public void setRecolectorDAC(int recolectorDAC) {
        this.recolectorDAC = recolectorDAC;
    }
    
    
}
