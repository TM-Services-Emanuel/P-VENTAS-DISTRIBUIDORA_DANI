package Modelo;

public class Transferencia {

    private int codTransferencia;
    private String fecha;
    private String hora;
    private String salida_tipo;
    private int idsalida;
    private String obs_salida;
    private String entrada_tipo;
    private int identrada;
    private String obs_entrada;
    private int total;

    //Contructor

    public Transferencia(int codTransferencia, String fecha, String hora, String salida_tipo, int idsalida, String obs_salida, String entrada_tipo, int identrada, String obs_entrada, int total) {
        this.codTransferencia = codTransferencia;
        this.fecha = fecha;
        this.hora = hora;
        this.salida_tipo = salida_tipo;
        this.idsalida = idsalida;
        this.obs_salida = obs_salida;
        this.entrada_tipo = entrada_tipo;
        this.identrada = identrada;
        this.obs_entrada = obs_entrada;
        this.total = total;
    }
    

    //Constructor vacio
    public Transferencia() {
    }

    //Getter y Setter

    public int getCodTransferencia() {
        return codTransferencia;
    }

    public void setCodTransferencia(int codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSalida_tipo() {
        return salida_tipo;
    }

    public void setSalida_tipo(String salida_tipo) {
        this.salida_tipo = salida_tipo;
    }

    public int getIdsalida() {
        return idsalida;
    }

    public void setIdsalida(int idsalida) {
        this.idsalida = idsalida;
    }

    public String getObs_salida() {
        return obs_salida;
    }

    public void setObs_salida(String obs_salida) {
        this.obs_salida = obs_salida;
    }

    public String getEntrada_tipo() {
        return entrada_tipo;
    }

    public void setEntrada_tipo(String entrada_tipo) {
        this.entrada_tipo = entrada_tipo;
    }

    public int getIdentrada() {
        return identrada;
    }

    public void setIdentrada(int identrada) {
        this.identrada = identrada;
    }

    public String getObs_entrada() {
        return obs_entrada;
    }

    public void setObs_entrada(String obs_entrada) {
        this.obs_entrada = obs_entrada;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

}
