package Modelo;

public class Cheques {

    private int idcheques;
    private String fecha;
    private int idtipo;
    private int idbanco;
    private int idmovil;
    private String razon_social;
    private String ruc;
    private String fecha_emision;
    private String fecha_pago;
    private String cuenta_n;
    private String cheque_n;
    private int monto;
    private String observacion;
    

    /*Constructo vacio*/
    public Cheques() {
    }

    /*Constructor Lleno*/

    public Cheques(int idcheques, String fecha, int idtipo, int idbanco, int idmovil, String razon_social, String ruc, String fecha_emision, String fecha_pago, String cuenta_n, String cheque_n, int monto, String observacion) {
        this.idcheques = idcheques;
        this.fecha = fecha;
        this.idtipo = idtipo;
        this.idbanco = idbanco;
        this.idmovil = idmovil;
        this.razon_social = razon_social;
        this.ruc = ruc;
        this.fecha_emision = fecha_emision;
        this.fecha_pago = fecha_pago;
        this.cuenta_n = cuenta_n;
        this.cheque_n = cheque_n;
        this.monto = monto;
        this.observacion = observacion;
    }

    /*Getter y Setter*/

    public int getIdcheques() {
        return idcheques;
    }

    public void setIdcheques(int idcheques) {
        this.idcheques = idcheques;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public int getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(int idbanco) {
        this.idbanco = idbanco;
    }

    public int getIdmovil() {
        return idmovil;
    }

    public void setIdmovil(int idmovil) {
        this.idmovil = idmovil;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getCuenta_n() {
        return cuenta_n;
    }

    public void setCuenta_n(String cuenta_n) {
        this.cuenta_n = cuenta_n;
    }

    public String getCheque_n() {
        return cheque_n;
    }

    public void setCheque_n(String cheque_n) {
        this.cheque_n = cheque_n;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
}
