<%-- 
    Document   : satelite
    Created on : 3/12/2016, 06:54:39 PM
    Author     : hsancheza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Satelite</title>
    </head>
    <body>
    <center>
        <h1>Limpiar cache puntos satelites</h1>
        <%
            if (request.getParameter("rta") != null) {
                out.println("<p>Satelites limpiados exitosamente...</p>");
            }
        %>
        <form action="SateliteController">
            <input type="text" name="imei"/>
            IMEI<input checked="true" type="radio" name="operacion" value="0"/></br>
            TODOS<input type="radio" name="operacion" value="1"/></br>
            <input type="submit" value="Limpiar puntos"/>
        </form>
    </center>

</body>
</html>
