<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Usuario"%>
<%@page import="dao.DAOAlumno"%>
<%!
    DAOAlumno lista = new DAOAlumno();
    Alumno edit = null;
%>

<%
    Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
    if (usuarioSesion == null)
    {
        response.sendRedirect("login.jsp");
        return;
    }

    edit = null;
    if (request.getAttribute("edit") != null)
    {
        edit = (Alumno) request.getAttribute("edit");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>BD con Servlets</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container py-4">
            <div class="d-flex justify-content-end align-items-center mb-3">
                <span class="me-3">Sesión: <strong><%= usuarioSesion.getUsuario() %></strong></span>
                <a href="LogoutServlet" class="btn btn-outline-secondary btn-sm">Cerrar sesión</a>
            </div>
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h2 class="card-title mb-3"><%= (edit != null) ? "Modificar calificaciones" : "Registro de calificaciones"%></h2>
                    <form method="post" action="SAlumno" class="row g-2 align-items-end">
                        <input type="hidden" name="accion"  id="accion"  value="<%= (edit != null) ? "Modificar" : "Agregar"%>" />
                        <input type="hidden" name="tfNLOld" id="tfNLOld" value="<%= (edit != null) ? edit.getNL() : ""%>" />

                        <div class="col-6 col-md-1">
                            <label for="tfNL" class="form-label">NL</label>
                            <input type="text" class="form-control" name="tfNL" id="tfNL" value="<%= (edit != null) ? edit.getNL() : ""%>" placeholder="NL" required />
                        </div>
                        <div class="col-6 col-md-2">
                            <label for="tfNombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" name="tfNombre" id="tfNombre" value="<%= (edit != null) ? edit.getNombre() : ""%>" placeholder="Nombre" required />
                        </div>
                        <div class="col-6 col-md-2">
                            <label for="tfPaterno" class="form-label">Paterno</label>
                            <input type="text" class="form-control" name="tfPaterno" id="tfPaterno" value="<%= (edit != null) ? edit.getPaterno() : ""%>" placeholder="Paterno" required />
                        </div>
                        <div class="col-6 col-md-2">
                            <label for="tfMaterno" class="form-label">Materno</label>
                            <input type="text" class="form-control" name="tfMaterno" id="tfMaterno" value="<%= (edit != null) ? edit.getMaterno() : ""%>" placeholder="Materno" required />
                        </div>
                        <div class="col-4 col-md-1">
                            <label for="tfDDI" class="form-label">DDI</label>
                            <input type="number" step="0.01" min="0" max="10" class="form-control" name="tfDDI" id="tfDDI" value="<%= (edit != null) ? edit.getDDI() : ""%>" placeholder="DDI" required />
                        </div>
                        <div class="col-4 col-md-1">
                            <label for="tfDWI" class="form-label">DWI</label>
                            <input type="number" step="0.01" min="0" max="10" class="form-control" name="tfDWI" id="tfDWI" value="<%= (edit != null) ? edit.getDWI() : ""%>" placeholder="DWI" required />
                        </div>
                        <div class="col-4 col-md-1">
                            <label for="tfECBD" class="form-label">ECBD</label>
                            <input type="number" step="0.01" min="0" max="10" class="form-control" name="tfECBD" id="tfECBD" value="<%= (edit != null) ? edit.getECBD() : ""%>" placeholder="ECBD" required />
                        </div>
                        <div class="col-6 col-md-2">
                            <button type="submit" class="btn btn-primary w-100"><%= (edit != null) ? "Modificar" : "Agregar"%></button>
                        </div>
                    </form>
                </div>
            </div>

            <%=lista.mostrar()%>
        </div>
    </body>
</html>
