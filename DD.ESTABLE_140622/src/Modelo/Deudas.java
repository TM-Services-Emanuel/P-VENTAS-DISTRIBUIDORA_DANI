package Modelo;

public class Deudas {

    private int iddeuda;
    private int idcliente;
    private int idmovil;
    private String fecha;
    private String observacion;
    private int monto;
    private String pagado;

    /*Constructo vacio*/
    public Deudas() {
    }

    /*Constructor Lleno*/

    public Deudas(int iddeuda, int idcliente, int idmovil, String fecha, String observacion, int monto, String pagado) {
        this.iddeuda = iddeuda;
        this.idcliente = idcliente;
        this.idmovil = idmovil;
        this.fecha = fecha;
        this.observacion = observacion;
        this.monto = monto;
        this.pagado = pagado;
    }
    

    /*Getter y Setter*/

    public int getIddeuda() {
        return iddeuda;
    }

    public void setIddeuda(int iddeuda) {
        this.iddeuda = iddeuda;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdmovil() {
        return idmovil;
    }

    public void setIdmovil(int idmovil) {
        this.idmovil = idmovil;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }
    
}
