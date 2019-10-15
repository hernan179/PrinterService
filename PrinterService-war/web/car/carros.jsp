<%-- 
    Document   : serviciosByFecha
    Created on : 09-ago-2015, 14:40:46
    Author     : hsancheza
--%>

<%@page import="com.rta.imp.web.controller.EJBService"%>
<%@page import="com.rta.opain.domain.TempVehiculo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    List<TempVehiculo> misCarros = new EJBService().get().lstCarros();
%>

<script>
    function confirmarBloqueo(tipo) {
        var checkedBoxes = getCheckedBoxes("idCarBloq");
        if (checkedBoxes > 0) {
            var isOk = confirm("favor confirmar operacion para " + checkedBoxes + " vehiculos");
            if (isOk) {
                document.getElementById('tipoOperacion').value = tipo;
                document.forms["form1"].submit();

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    function getCheckedBoxes(chkboxName) {
        var checkboxes = document.getElementsByName(chkboxName);
        var checkboxesChecked = [];
        // loop over them all
        var cantidad = 0;
        for (var i = 0; i < checkboxes.length; i++) {
            // And stick the checked ones onto an array...
            if (checkboxes[i].checked) {
                checkboxesChecked.push(checkboxes[i]);
                cantidad++;
            }
        }
        // Return the array if it is non-empty, or null
        return cantidad;
    }
</script>
<style>
    label {
        cursor: pointer;
        height:24px;
        padding:12px;
        border:solid 1px #06F;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }
</style>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de carros operativos</title>


        <script src="../source/js/jquery-1.9.1.js"></script>

        <script src="../source/js/jquery-1.9.1.js"></script>
        <script src="jjsonviewer.js"></script>
        <link rel="stylesheet" media="all" type="text/css" href="jjsonviewer.css" />

        <style type="text/css">
            #ui-datepicker-div, .ui-datepicker{ font-size: 70%; }

            /* css for timepicker */
            .ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
            .ui-timepicker-div dl { text-align: left; }
            .ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
            .ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
            .ui-timepicker-div td { font-size: 90%; }
            .ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
        </style>
        <style>
            table {
                width: 90%;
                color: #854400;
                margin: 0 0 1em 0;	
                font: 80%/150% "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Lucida, Helvetica, sans-serif;
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
                border: 1px solid #a6ce39;
            }
            tbody tr th {
                padding-right: 1em;
                text-align: right;
                font-weight: normal;
                background: #c5e894 url(http://taimar.pri.ee/examples/table-design/bg_cell.gif) no-repeat top left;
                text-transform: uppercase;
            }
            tbody tr th:hover {
                background: #D0EBA6;
            }
            table a[href*="taimar.pri.ee"] {
                float: left;
                width: 64px;
                height: 64px;
                font-size: 0;
                text-decoration: none;
                background: transparent url(http://taimar.pri.ee/examples/table-design/taimar.gif) no-repeat 0 0;
            }
            table a[title^="Download"] {
                float: none;
                width: auto;
                height: auto;
                font-size: 100%;
                background: none;
            }

            /* LINKS */

            table a {
                color: #854400;
                text-decoration: none;
            }
            table a:visited {
                text-decoration: line-through;
            }
            table a:hover {
                text-decoration: underline;
            }
            .ui-helper-center {
                text-align: center;
            }
        </style>
        <style>
            .derecha {
                text-align: right !important;
                margin-right: 1em;
            }
            .centrado {
                text-align: center !important;
                margin-right: 1em;
            }
            .izq {
                text-align: left;
                margin-left: 1em;
            }

            .tableCSS{
                font-family: arial, monospace;
                color: black;
                font-size:12px;
                background-color: white;
                border-bottom: 1px solid #CCC;
                border-color: green;
                border: green;
                padding: 0 0.5em;
                width: 95%;

            }

            .modal-body2 {
                max-height: 400px;
                width: 200px;
                overflow-y: auto;
                padding: 15px;
                position: relative;
            }

            .divVale{
                width: 100%;
                padding: 1px;
                border: 0.1px solid red;
                margin: 0.2px;
            }

        </style>

    </head>
    <body>
        <h1>LISTADO CARROS AUTORIZADOS VIAJES IMPERIAL TOTAL <%=misCarros.size()%></h1>
        <form action="../BloqueoController" method="POST" id="form1" name="form1">
            <input type="hidden" value="2" id="tipoOperacion" name="tipoOperacion"/>
            <table>
                <tr>
                    <td>#ids</td>
                    <td>Vehiculo</td>
                    <td>Actualizado</td>
                    <td>Detalles</td>
                    <td>Bloqueo</td>
                </tr>
                <%if (misCarros != null && misCarros.size() > 0) {
                        int i = 1;
                        for (TempVehiculo car : misCarros) {%>

                <tr>
                    <td>#<%=car.getId()%></td>
                    <td><%=car.getAutoPlaca()%></td>
                    <td><%=car.getFecha()%></td>
                    <td>
                        <a onclick="setValueJson<%=car.getId()%>1();">Ver...</a>
                        <script>
                            function setValueJson<%=car.getId()%>1() {
                                var json =<%=car.getJson()%>;
                                var str = JSON.stringify(json, undefined, 4);

                                $("#1<%=car.getId()%>").jJsonViewer(str);
                            }
                        </script>
                        <pre id="1<%=car.getId()%>"></pre>

                    </td>
                    <td>

                        <%if (car.getIdEstado() != null  && car.getIdEstado() == 23) {%>

                        <label style="background-color: red">Des-bloquear
                            <input type="checkbox" name="idCarBloq" value="<%=car.getId()%>"/>
                        </label>
                        <%} else {%>
                        <label>Bloquear
                            <input type="checkbox" name="idCarBloq" value="<%=car.getId()%>"/>
                        </label>
                        <%}%>

                    </td>
                </tr> 
                <%}
                    }

                %>
                <tr>
                    <td style="text-align: center;" colspan="5">
                        <input type="button" style="background-color: red;" onclick="confirmarBloqueo('23');" name="bloqueo" value="Bloquear"/>
                        <input type="button" onclick="confirmarBloqueo('1');" name="desbloqueo" value="Des-Bloquear"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
