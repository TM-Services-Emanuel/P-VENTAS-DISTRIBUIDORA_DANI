package Modelo;

public class Moviles {
    
    private int idmovil;
    private String descipcion;
    private String chapa;
    private String marca;
    private String modelo;
    private String chasis;
    private String anho;
    private String color;
    private String capacidad;
    private String usuario;
    //Constructor

    public Moviles(int idmovil, String descipcion, String chapa, String marca, String modelo, String chasis, String anho, String color, String capacidad, String usuario) {
        this.idmovil = idmovil;
        this.descipcion = descipcion;
        this.chapa = chapa;
        this.marca = marca;
        this.modelo = modelo;
        this.chasis = chasis;
        this.anho = anho;
        this.color = color;
        this.capacidad = capacidad;
        this.usuario = usuario;
    }

    //Constructor Vacio

    public Moviles() {
    }
    
    //Getter y Setter

    public int getIdmovil() {
        return idmovil;
    }

    public void setIdmovil(int idmovil) {
        this.idmovil = idmovil;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
