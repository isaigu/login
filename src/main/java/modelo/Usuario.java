package modelo;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String usuario;
    private String correo;
    private String password;
    private String status;
    private String token;

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String u) { this.usuario = u; }
    public String getCorreo() { return correo; }
    public void setCorreo(String c) { this.correo = c; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public String getToken() { return token; }
    public void setToken(String t) { this.token = t; }
}