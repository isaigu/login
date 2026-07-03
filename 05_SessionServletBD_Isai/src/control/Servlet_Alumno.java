package control;

import modelo.Alumno;
import dao.DAOAlumno;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/SAlumno") public class Servlet_Alumno extends HttpServlet
{
    private Alumno alumno;
    private DAOAlumno dao;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html;charset=UTF-8");
        Alumno edit = null;

        try
        {
            String accion = request.getParameter("accion");
            if (null == accion)
            {
                request.setAttribute("edit", edit);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }
            else switch (accion) {
                case "Agregar":
                    {
                        alumno = new Alumno();
                        alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                        alumno.setNombre(request.getParameter("tfNombre"));
                        alumno.setPaterno(request.getParameter("tfPaterno"));
                        alumno.setMaterno(request.getParameter("tfMaterno"));
                        alumno.setDDI(Double.parseDouble(request.getParameter("tfDDI")));
                        alumno.setDWI(Double.parseDouble(request.getParameter("tfDWI")));
                        alumno.setECBD(Double.parseDouble(request.getParameter("tfECBD")));
                        dao=new DAOAlumno();
                        dao.agregar(alumno);
                        request.setAttribute("edit", edit);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                        rd.forward(request, response);
                        break;
                    }
                case "Editar":
                    {
                        dao=new DAOAlumno();
                        edit = dao.buscar(Integer.parseInt(request.getParameter("tfNL")));
                        request.setAttribute("edit", edit);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                        rd.forward(request, response);
                        break;
                    }
                case "Modificar":
                    {
                        alumno = new Alumno();
                        alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                        alumno.setNombre(request.getParameter("tfNombre"));
                        alumno.setPaterno(request.getParameter("tfPaterno"));
                        alumno.setMaterno(request.getParameter("tfMaterno"));
                        alumno.setDDI(Double.parseDouble(request.getParameter("tfDDI")));
                        alumno.setDWI(Double.parseDouble(request.getParameter("tfDWI")));
                        alumno.setECBD(Double.parseDouble(request.getParameter("tfECBD")));
                        dao = new DAOAlumno();
                        dao.editar(alumno, Integer.parseInt(request.getParameter("tfNLOld")));
                        edit = null;
                        request.setAttribute("edit", edit);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                        rd.forward(request, response);
                        break;
                    }
                case "Eliminar":
                    {
                        int nl = Integer.parseInt(request.getParameter("tfNL"));
                        dao = new DAOAlumno();
                        dao.eliminar(nl);
                        request.setAttribute("edit", edit);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                        rd.forward(request, response);
                        break;
                    }
                default:
                    {
                        request.setAttribute("edit", edit);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                        rd.forward(request, response);
                        break;
                    }
            }
        } catch (IOException | ServletException ex)
        {
            Logger.getLogger(Servlet_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
