<%@page import="com.rta.imp.web.controller.EJBService"%>
<%@page import="com.rta.opain.delegate.PrinterServicesDelegateRemote"%>
<%
    PrinterServicesDelegateRemote service = new EJBService().get();


%>

<%@page import="java.util.List"%>
<%@page import="com.rta.opain.domain.Bloqueo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../source/css/demos.css">
        <script src="../source/js/jquery-1.9.1.js"></script>

        <title>Bloqueo de moviles taxi imperial</title>

        <style>
            table {
                width: 50%;
                color: #212424;
                margin: 0 0 1em 0;	

            }
            table, tr, th, td {
                margin: 0;
                border: 2px;
                padding: 0;
                border-spacing: 0;
                border-collapse: collapse;
            }
            caption {
                width: 100%;
                height: 39px;
                font-size: 0;
                line-height: 0;
                text-indent: -5000em;
                background: url(http://taimar.pri.ee/examples/table-design/caption.gif) no-repeat 98% 0;
            }
            caption:hover {
                background-position: 98% 100%;
            }

            /* HEADER */

            thead {
                background: #524123;
            }
            thead tr th {
                padding: 1em 0;
                text-align: center;
                color: #FAF7D4;
                border-bottom: 3px solid #A5D768;
            }

            /* FOOTER */

            tfoot {
                color: #fff;
                background: #524123;
            }
            tfoot tr th, tfoot tr td {
                padding: .2em .6em;
                border-top: 2px solid #A5D768;
            }
            tfoot tr th {
            }
            tfoot tr td {
                text-align: right;
            }

            /* BODY */

            tbody tr td {
                background: #DDF0BD url(http://taimar.pri.ee/examples/table-design/bg_cell.gif) no-repeat top left;
            }
            tbody tr.odd td {
                background-color: #D0EBA6;
            }
            tbody tr td:hover, tbody tr.odd td:hover {
                background: #c5e894;
            }
            tbody tr th, tbody tr td {
                padding: 0.1em 0.4em;
                border: 1px solid #FFFFFF;
            }





        </style>

        <script>
            function salir() {
              
                   this.location.href = "../LoginController?salir=si";
            }
        </script>

    </head>
    <body>

        <%if (session.getAttribute("user") == null) {%>
    <center>
        <h1>Debe ingresar correctamente</h1>    
        <form action="../LoginController">
            Usuario: <input type="text" name="usuario"/>
            </br>Clave:<input type="password" name="clave"/>
            </br><input type="submit" value="Ingresar"/>
        </form>
    </center>
    <%} else {%>

    <h1>BLOQUEO AEROPUERTO TAXI IMPERIAL</h1>

    <% if (session.getAttribute("bloqueo") != null) {
            Bloqueo bloqueo = (Bloqueo) session.getAttribute("bloqueo");
            if (bloqueo.getIdEstado().equals(1)) {
                out.println("<div style=\"background-color: red\"><p>La placa " + bloqueo.getAutoPlaca() + " esta " + (bloqueo.getIdEstado().equals(1) ? "Bloqueada" : "Des-bloqueada") + "</p></div>");
            } else {
                out.println("<div style=\"background-color: green\"><p>La placa " + bloqueo.getAutoPlaca() + " esta " + (bloqueo.getIdEstado().equals(1) ? "Bloqueada" : "Des-bloqueada") + "</p></div>");
            }

        }
    %>
    <form action="../BloqueoTaxiController" method="POST">
        <input type="text" class="bannerCSS" name="placa" required placeholder="Placa" maxlength="6"/>
        <input type="radio" name="bloqueo" checked value="1">Bloquear<input type="radio" name="bloqueo" value="2">Desbloquear
        <input type="submit" name="guardar" value="Guardar"/>
        <a onclick="salir();">Salir</a>
    </form>
    <table border="1">
        <tr>
            <td colspan="3">
                LISTADO DE VEHICULOS BLOQUEADOS
            </td>
        </tr>
        <tr>
            <td>Placa:</td>
            <td>Fecha:</td>
            <td>Detalle:</td>
        </tr>
        <%
            List<Bloqueo> lstBloqueos = service.lstBloqueos();
            if (lstBloqueos != null) {
                for (Bloqueo bloqueos : service.lstBloqueos()) {%>
        <tr>
            <td><%=bloqueos.getAutoPlaca()%></td>
            <td><%=bloqueos.getFecha()%></td>
            <td><%=bloqueos.getDetalle()%></td>
        </tr>   

        <%}
            }
        %>            

    </table>
    <%}%>
</body>
</html>
