package Modelo;

public class TimbradoMovil {
    
    private int codT;
    private int descripcion;
    private String fechadesde;
    private String fechahasta;
    private String estado;

    //Constructor

    public TimbradoMovil(int codT, int descripcion, String fechadesde, String fechahasta, String estado) {
        this.codT = codT;
        this.descripcion = descripcion;
        this.fechadesde = fechadesde;
        this.fechahasta = fechahasta;
        this.estado = estado;
    }

    public TimbradoMovil() {
    }

    public int getCodT() {
        return codT;
    }

    public void setCodT(int codT) {
        this.codT = codT;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(String fechadesde) {
        this.fechadesde = fechadesde;
    }

    public String getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(String fechahasta) {
        this.fechahasta = fechahasta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
       
}
