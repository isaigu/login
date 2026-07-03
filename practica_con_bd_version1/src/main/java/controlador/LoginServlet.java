package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String pass    = req.getParameter("password");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(usuario, pass);

        if (u == null) {
            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if (!"ACTIVO".equals(u.getStatus())) {
            req.setAttribute("error", "Tu cuenta no está activada. Revisa tu correo.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", u);
            resp.sendRedirect("SAlumno"); // entra a la app
        }
    }
}