package controlador;

import dao.UsuarioDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/VerificarServlet")
public class VerificarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String token = req.getParameter("token");
        UsuarioDAO dao = new UsuarioDAO();
        boolean ok = dao.activarPorToken(token);
        req.setAttribute("mensaje", ok
            ? "¡Cuenta activada! Ya puedes iniciar sesión."
            : "El enlace no es válido o ya fue usado.");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}