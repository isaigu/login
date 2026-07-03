<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Registro</title></head>
<body>
    <h2>Crear cuenta</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red"><%= request.getAttribute("error") %></p>
    <% } %>
    <% if (request.getAttribute("mensaje") != null) { %>
        <p style="color:green"><%= request.getAttribute("mensaje") %></p>
    <% } %>

    <form action="RegistroServlet" method="post">
        <label>Nombre completo:</label><br>
        <input type="text" name="nombreCompleto" required><br>
        <label>Usuario:</label><br>
        <input type="text" name="usuario" required><br>
        <label>Correo:</label><br>
        <input type="email" name="correo" required><br>
        <label>Contraseña:</label><br>
        <input type="password" name="password" required><br><br>
        <button type="submit">Registrarse</button>
    </form>
    <a href="login.jsp">Ya tengo cuenta</a>
</body>
</html>
