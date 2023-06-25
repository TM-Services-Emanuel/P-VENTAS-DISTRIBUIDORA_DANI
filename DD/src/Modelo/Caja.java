package Modelo;

public class Caja {

    private int caId;
    private String fechaI;
    private String horaI;
    private String fechaF;
    private String horaF;
    private int caInicial;
    private int caFinal;
    private int caEntregado;
    private int caGastos;
    private int caDiferencia;
    private int caMontoSalida;
    private String usuarioI;
    private String usuarioF;
    private String indi;

    public Caja(String fechaI,String horaI,String fechaF,String horaF, int caInicial, int caFinal,int caEntregado, int caDiferencia, String usuarioI, String usuarioF) {
        this.fechaI = fechaI;
        this.horaI = horaI;
        this.fechaF = fechaF;
        this.horaF = horaF;
        this.caInicial = caInicial;
        this.caFinal = caFinal;
        this.caEntregado = caEntregado;
        this.caDiferencia = caDiferencia;
        this.usuarioI = usuarioI;
        this.usuarioF = usuarioF;
    }
    
    public Caja(String fechaI,String horaI, int caInicial, int caFinal,int caEntregado, int caGastos, int caDiferencia, int caMontoSalida, String usuarioI, String usuarioF) {
        this.fechaI = fechaI;
        this.horaI = horaI;
        this.caInicial = caInicial;
        this.caFinal = caFinal;
        this.caEntregado = caEntregado;
        this.caGastos = caGastos;
        this.caDiferencia = caDiferencia;
        this.caMontoSalida = caMontoSalida;
        this.usuarioI = usuarioI;
        this.usuarioF = usuarioF;
    }
    
    public Caja(int caId, String fechaF, String horaF,int caFinal, int caEntregado, int caGastos, int caDiferencia, int caMontoTotal, String usuarioF){
        this.caId = caId;
        this.fechaF = fechaF;
        this.horaF = horaF;
        this.caEntregado = caEntregado;
        this.caGastos = caGastos;
        this.caDiferencia = caDiferencia;
        this.caMontoSalida = caMontoTotal;
        this.caFinal = caFinal;
        this.usuarioF = usuarioF;
    }

    public Caja(int caId, int caFinal, int caEntregado, int caGastos, int caDiferencia, int caMontoSalida) {
        this.caId = caId;
        this.caFinal = caFinal;
        this.caEntregado = caEntregado;
        this.caGastos = caGastos;
        this.caDiferencia = caDiferencia;
        this.caMontoSalida = caMontoSalida;
    }
    
    
    
    public Caja() {    
    }

    public int getCaId() {
        return caId;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }
    public String getHoraI() {
        return horaI;
    }
    public void setHoraI(String horaI) {
        this.horaI = horaI;
    }
    
    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
    
    public String getHoraF() {
        return horaF;
    }
    public void setHoraF(String horaF) {
        this.horaF = horaF;
    }
    
    public int getCaInicial() {
        return caInicial;
    }

    public void setCaInicial(int caInicial) {
        this.caInicial = caInicial;
    }

    public int getCaFinal() {
        return caFinal;
    }

    public void setCaFinal(int caFinal) {
        this.caFinal = caFinal;
    }

    public int getCaEntregado() {
        return caEntregado;
    }

    public void setCaEntregado(int caEntregado) {
        this.caEntregado = caEntregado;
    }
    
    public int getCaDiferencia() {
        return caDiferencia;
    }

    public void setCaDiferencia(int caDiferencia) {
        this.caDiferencia = caDiferencia;
    }
    
    public String getUsuarioI() {
        return usuarioI;
    }

    public void setUsuarioI(String usuarioI) {
        this.usuarioI = usuarioI;
    }
    
    public String getUsuarioF() {
        return usuarioF;
    }

    public void setUsuarioF(String usuarioF) {
        this.usuarioF = usuarioF;
    }
    
    public String getIndicador() {
        return indi;
    }

    public void setIndicador(String indi) {
        this.indi = indi;
    }

    public int getCaGastos() {
        return caGastos;
    }

    public void setCaGastos(int caGastos) {
        this.caGastos = caGastos;
    }

    public int getCaMontoSalida() {
        return caMontoSalida;
    }

    public void setCaMontoSalida(int caMontoSalida) {
        this.caMontoSalida = caMontoSalida;
    }
    
    

}
