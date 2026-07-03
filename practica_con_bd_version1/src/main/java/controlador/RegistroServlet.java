package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.CorreoUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nombre = req.getParameter("nombreCompleto");
        String usuario = req.getParameter("usuario");
        String correo  = req.getParameter("correo");
        String pass    = req.getParameter("password");

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.existeUsuarioOCorreo(usuario, correo)) {
            req.setAttribute("error", "El usuario o correo ya está registrado");
            req.getRequestDispatcher("registro.jsp").forward(req, resp);
            return;
        }

        Usuario u = new Usuario();
        u.setNombreCompleto(nombre);
        u.setUsuario(usuario);
        u.setCorreo(correo);
        u.setPassword(pass); // idealmente encriptar (ver conclusión)
        u.setToken(UUID.randomUUID().toString());

        if (dao.registrar(u)) {
            try {
                CorreoUtil.enviarVerificacion(correo, u.getToken());
                req.setAttribute("mensaje", "Registro exitoso. Revisa tu correo para activar la cuenta.");
            } catch (Exception e) {
                req.setAttribute("error", "Registrado, pero no se pudo enviar el correo.");
            }
        } else {
            req.setAttribute("error", "No se pudo registrar.");
        }
        req.getRequestDispatcher("registro.jsp").forward(req, resp);
    }
}