package dao;

import config.Conexion;
import modelo.Usuario;
import util.PasswordUtil;
import java.sql.*;

public class UsuarioDAO {

    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre_completo, usuario, correo, password, status, token_verificacion) VALUES (?,?,?,?, 'PENDIENTE', ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombreCompleto());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getCorreo());
            ps.setString(4, PasswordUtil.hash(u.getPassword()));
            ps.setString(5, u.getToken());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {return false; }
    }

    public boolean existeUsuarioOCorreo(String usuario, String correo) {
        String sql = "SELECT id FROM usuarios WHERE usuario=? OR correo=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, correo);
            return ps.executeQuery().next();
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean activarPorToken(String token) {
        String sql = "UPDATE usuarios SET status='ACTIVO', token_verificacion=NULL WHERE token_verificacion=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, token);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Usuario login(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && PasswordUtil.verify(password, rs.getString("password"))) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setUsuario(rs.getString("usuario"));
                u.setStatus(rs.getString("status"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}