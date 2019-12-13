<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <script src="source/js/jquery-1.9.1.js"></script>
        <style>
            body{
                background-color:#e4dab8;
            }
            form fieldset{
                background-color:#E0E5D1;

                border-width:2px 2px 2px 10px;
                border-style:solid;
                border-color:#cee574;

                font-family:Arial, Helvetica, sans-serif;
                font-size:16px;

                margin:20px 0px 20px 20px;
                width:90%;
                position:relative;
                display:block;
                padding: 0px 10px 10px 10px;
            }

            form fieldset legend{	
                background-color:#ecefcb;

                border-width:1px 1px 1px 10px;
                border-color:#ff9900;
                border-style:solid;

                color:#5c71a2;
                font-weight:bold;
                text-transform:uppercase;
                font-size:90%;
                text-align:center;

                width:80%;
                padding:3px 5px;
                margin:0px 0px 10px -40px;
                position:relative;
                top: -14px;

            }

            form fieldset legend img{
                padding:0px 5px 0px 5px;	
            }
            .rojo{
                background: red;
                text-aling:center;
            }
            .amarillo{
                background: yellow;
                text-aling:center;
            }
            .negro{
                background: black;
                text-aling:center;
                color: white;
            }

        </style>




        <script>

            var tid;
            function refrescarSrv() {
                if (tid != null) {
                    clearInterval(tid);
                }
                var URLSRV = 'InOutController?opcion=online';
                URLSRV += "&jsoncallback=?";
                var isOk = "";
                $.getJSON(URLSRV, function (data) {
                    $("#allReport").html("");
                    if (data.error == 'ok') {
                        var contenido = "";
                        contenido += '<table style="width: 800px;border-collapse: collapse;border-spacing: 0;">';

                        contenido += '<tr style="border: 1px solid #CCC;">';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[CONTRATO]' + '</td>';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[FECHA]' + '</td>';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[PLACA]' + '</td>';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[DETALLE]' + '</td>';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[DIAS]' + '</td>';
                        contenido += '<td style="border: 1px solid #CCC;">' + '[BLOQUEADO]' + '</td>';
                        contenido += '</tr>';
                        var contador = 0;
                        $.each(data.servicios, function (i, item) {
                            contenido += '<tr style="border: 1px solid #CCC;text-aling:center">';
                            contenido += '<td style="border: 1px solid #CCC;">' + item.contrato + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + item.fecha + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + item.placa + '</td>';
                            contenido += '<td style="border: 1px solid #CCC;">' + item.detalle + '</td>';

                            if (item.dias > 60) {
                                contenido += '<td style="border: 1px solid #CCC;" class="rojo">' + item.dias + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;" class="negro">' + item.placa + '</td>';
                            } else if (item.dias > 50 && item.dias < 60) {
                                contenido += '<td style="border: 1px solid #CCC;" class="amarillo">' + item.dias + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;"></td>';
                            } else {
                                contenido += '<td style="border: 1px solid #CCC;text-aling:center">' + item.dias + '</td>';
                                contenido += '<td style="border: 1px solid #CCC;"></td>';
                            }


                            contenido += '</tr>';
                        });

                        contenido += '</table>';

                        $("#allReport").html(contenido);
                        // tid = setInterval("refrescarSrv()", 10000);
                        tid = setInterval(function () {
                            refrescarSrv()
                        }, 5000);
                    } else {
                        $("#allReport").html("No hay servicios activos...");
                        //  clearInterval(tid);
                        tid = setInterval(function () {
                            refrescarSrv()
                        }, 5000);
                    }
                });
                URLSRV = "";
            }



        </script>


        <script>

            function salir(enlace) {
                window.location = this.href = enlace;

            }
            function borrar() {
                document.f.mensaje.value = 'salir';
                document.f.target = "interno";
                document.f.action = "InOutController";
                document.f.method = "POST";

                document.f.submit();
                document.f.mensaje.value = '';
            }
            function mostrardiv(num) {

                if (num == 1) {

                    div = document.getElementById('flotante');
                    div.style.display = '';
                }


            }
            function cerrar() {
                div = document.getElementById('flotante');
                div.style.display = 'none';
            }
            function guardarFactura(nume) {
                if (document.f.mensaje.value != '') {
                    document.f.target = "interno";
                    document.f.action = "InOutController";
                    document.f.method = "POST";

                    document.f.submit();
                }
                document.f.mensaje.value = '';
            }

        </script>

        <link rel="stylesheet" href="assets/fonts/stylesheet.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/iconFonts/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/jquery-ui.css">
        <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
        <link href="assets/css/jquery.bxslider.css" rel="stylesheet" />
        <link href="assets/css/bootstrap-tagsinput.css" rel="stylesheet" />
        <link href="assets/css/dataTables.bootstrap.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="source/js/jquery-1.9.1.js"></script>


    </head>

   <!--body onload="refrescarSrv();"-->

    <body>
    <center>
            <img src="logo/nv_viajes.png" height="50" width="200"> 
        <i class="fa fa-automobile" style="font-size:24px"></i>
        <a href="car/carros.jsp">Vehiculos</a>
        <i class="fa fa-close" style="font-size:36px"></i>
        <a href="car/bloqueos.jsp">Bloqueo</a>
        <i class="fa fa-vcard" style="font-size:24px"></i>
        <a href="srv/serviciosEnFecha.jsp">Servicios en fecha</a>
        <i class="fa fa-database" style="font-size:24px"></i>
        <a href="srv/pagos.jsp">Pagos</a>

        <i class="fa fa-level-up" style="font-size:24px"></i>
        <a href="upload.jsp">Subir placa</a>
        <i class="fa fa-file-pdf-o" style="font-size:24px"></i>
        <a href="fuec.jsp">Gen Fuec</a>
        <i class="fa fa-thumbs-down" style="font-size:24px"></i>
        <a href="LoginController?salir=salir">Salir</a>
    </center>
    <FORM  name="f" id="f" ACTION="InOutController" target="interno"> 
        <fieldset>
            <legend>  <h1>Reporte salida de vehiculos - V2.0</h1></legend>
            <div id="allReport">
                ....
            </div>
            <h4 style="text-align: center">Gestion de Tecnologia RTA </h4>
        </fieldset>
    </form>

</body>
</html>
