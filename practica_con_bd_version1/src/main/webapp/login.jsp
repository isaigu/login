<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesión</title>
</head>
<body style="font-family: Arial; max-width: 400px; margin: 40px auto;">
    <h2>Iniciar sesión</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <% if (request.getAttribute("mensaje") != null) { %>
        <p style="color:green;"><%= request.getAttribute("mensaje") %></p>
    <% } %>

    <form action="LoginServlet" method="post">
        <p>Usuario:<br>
           <input type="text" name="usuario" required style="width:100%;"></p>
        <p>Contraseña:<br>
           <input type="password" name="password" required style="width:100%;"></p>
        <button type="submit">Entrar</button>
    </form>
    <p><a href="registro.jsp">Crear cuenta</a></p>
</body>
</html>