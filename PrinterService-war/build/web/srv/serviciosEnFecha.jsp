<%-- 
    Document   : serviciosByFecha
    Created on : 09-ago-2015, 14:40:46
    Author     : hsancheza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de servicios en fecha</title>
        <link rel="stylesheet" media="all" type="text/css" href="timepicker/css/jquery-ui-1.8.16.custom.css" />

        <script src="../source/js/jquery-1.9.1.js"></script>
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
        <script type="text/javascript" src="timepicker/js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="timepicker/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="timepicker/js/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#fecha1').datetimepicker({showSecond: true, dateFormat: 'yy/mm/dd'});
                $('#fecha2').datetimepicker({showSecond: true, dateFormat: 'yy/mm/dd'});
            });
        </script>
        <script>


            function image() {
                //dynamically add an image and set its attribute
                $("#allReport").html("");
                var img = document.createElement("img");
                img.src = "../img/loading6.gif"
                var foo = document.getElementById("allReport");
                foo.appendChild(img);
            }
            var tid;
            function refrescarSrv() {
                image();
                var fecha1 = document.getElementById('fecha1').value;
                var fecha2 = document.getElementById('fecha2').value;
                if (fecha1 != '' && fecha2 != '') {
                    var URLSRV = '../SrvCamionetaController2?fecha1=' + fecha1 + '&fecha2=' + fecha2;
                    URLSRV += "&jsoncallback=?";
                    var isOk = "";
                    var total = "";
                    var inicio = 0;
                    var fin = 0;
                    var consol = 0;
                    $.getJSON(URLSRV, function (data) {
                        $("#allReport").html("");
                        if (data.error == 'ok') {
                            var contenido = "";
                            contenido += '<table style="width: 800px;border-collapse: collapse;border-spacing: 0;">';
                            contenido += '<tr style="border: 1px solid #CCC;">';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[NUMERO]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[FECHA]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[DETALLE]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[PLACA]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[VALOR]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[COMPB]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[NIT]' + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + '[CAJERO]' + '</td>';
                            contenido += '</tr>';
                            var contador = 0;
                            $.each(data.servicios, function (i, item) {
                                contenido += '<tr style="border: 1px solid #CCC;text-aling:center">';
                                contenido += '<td style="border: 1px solid #CCC;">' + item.numero + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;">' + item.fecha + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;">' + item.detalle + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;text-aling:center">' + item.placa + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;">' + item.valor + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;text-aling:center">' + item.comprob + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;text-aling:center">' + item.nit + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;text-aling:center">' + item.usuario + '</td>';
                                contenido += '</tr>';
                                contador++;
                            });
                            total = "Total: " + contador;

                            contenido += '</table>';
                            $("#consolidado").html(total);
                            $("#allReport").html(contenido);
                            // tid = setInterval("refrescarSrv()", 10000);

                        } else {
                            $("#allReport").html("No hay servicios activos...");
                            //  clearInterval(tid);

                        }
                    });
                } else {
                    document.getElementById('fecha1').style.backgroundColor = 'yellow';
                    document.getElementById('fecha2').style.backgroundColor = 'yellow';
                }
                URLSRV = "";
            }



        </script>
    </head>
    <body>
        
         <img src="../logo/nv_viajes.png" height="50" width="200"> 
        <h1>SERVICIOS VIAJES EN FECHAS</h1>
        <table>
            <tr>
                <td colspan="3">
                    <form action="../SrvCamionetaController">
                        <input type="hidden" name="srv" value="hoy" />
                        ESTA:<input type="text" id="fecha1" size="20"  name="fecha1" />
                        Y ESTA:<input type="text" id="fecha2" size="20"  name="fecha2" /> 
                        <input type="button" onclick="refrescarSrv();" value="Buscar" />

                    </form>
                </td>
                <td id="consolidado">

                </td>
            </tr>
            <tr>
                <td>
                    <div id="allReport" style="width: 100px; height: 300px"> 
                        lista de servicos en fechas...
                    </div> 

                </td>
            </tr>
        </table>
    </body>
</html>
