package Modelo;

public class Usuario {

    private int codUsuario;
    private String nomUsuario;
    private String pefil;
    private String usuario;
    private String password;
    private String ip;
    private String usu;

    //Constructor vacio
    public Usuario() {
    }

    //Constructor
    public Usuario(int codUsuario, String pefil, String nomUsuario, String usuario, String password, String ip, String usu) {
        this.codUsuario = codUsuario;
        this.pefil = pefil;
        this.nomUsuario = nomUsuario;
        this.usuario = usuario;
        this.password = password;
        this.ip = ip;
        this.usu=usu;
    }
    

    //Getter y Setter
    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getPefil() {
        return pefil;
    }

    public void setPefil(String pefil) {
        this.pefil = pefil;
    }
    
    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

}
