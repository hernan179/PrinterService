<%-- 
    Document   : cintaTestigo
    Created on : 18/10/2017, 07:33:50 AM
    Author     : hsancheza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinta testigo</title>
                <link rel="stylesheet" media="all" type="text/css" href="srv/timepicker/css/jquery-ui-1.8.16.custom.css" />

        <script src="source/js/jquery-1.9.1.js"></script>
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
        <script type="text/javascript" src="srv/timepicker/js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="srv/timepicker/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="srv/timepicker/js/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#fecha').datetimepicker({showSecond: true, dateFormat: 'yy-mm-dd'});
                $('#fecha2').datetimepicker({showSecond: true, dateFormat: 'yy-mm-dd'});
            });
        </script>
    </head>
    <body>
       <h1>CINTA TESTIGO VIAJES IMPERIAL</h1>
        <table>
            <tr>
                <td colspan="3">
                    <form action="CintaTestigoController">
                        <input type="hidden" name="srv" value="hoy" />
                        ESTA:<input type="text" id="fecha" size="20"  required="true"   name="fecha"/>
                        Y ESTA:<input type="text" id="fecha2" size="20" required="true" name="fecha2" /> 
                        <input type="submit" value="Buscar" />

                    </form>
                </td>
                <td id="consolidado">

                </td>
            </tr>
            <tr>
                <td>
                    <div id="allReport" style="width: 100px; height: 300px"> 
                            </div> 

                </td>
            </tr>
        </table>
    </body>
</html>
