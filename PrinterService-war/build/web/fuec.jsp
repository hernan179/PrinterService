<%
    String ruta = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

%>
<!doctype html>
<html>

    <html lang="en">
        <head>
            <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
            <meta charset="utf-8">

            <title>Fuec factory online</title>

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


            <script>
                function limpiar() {
                    document.getElementById("myForm").reset();
                    cerrarDivPrecio();
                }

                function leerUuario(variable) {
                    var ids = '<%=request.getParameter("newFuec")%>';
                    var idUsuario = 0;
                    var query = window.location.search.substring(1);
                    var vars = query.split("&");
                    for (var i = 0; i < vars.length; i++) {
                        var pair = vars[i].split("=");
                        if (pair[0] == variable) {
                            idUsuario = pair[1];
                        }
                        if (pair[0] == 'ipPunto') {
                            idPunto = pair[1];
                        }
                    }

                    document.getElementById('idPunto').value = idPunto;

                    if (idUsuario != 0) {
                        alert("Fuec creadp con el id " + ids);
                    }

                }
            </script>

            <script>
                var map;
                var geocoder;
                var zoomSize = 16;
                var markersArray = [];
                var imagen = 'http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png'

                function formatDate(date) {
                    var hours = date.getHours();
                    var minutes = date.getMinutes();
                    var ampm = hours >= 12 ? 'pm' : 'am';
                    hours = hours % 12;
                    hours = hours ? hours : 12; // the hour '0' should be '12'
                    minutes = minutes < 10 ? '0' + minutes : minutes;
                    var strTime = hours + ':' + minutes + ' ' + ampm;
                    var mes = (date.getMonth() + 1);
                    if ((date.getMonth() + 1) < 10) {
                        mes = "0" + (date.getMonth() + 1);
                    }
                    return date.getFullYear() + "-" + mes + "-" + date.getDate() + " " + strTime;
                }
                function getJsonCrearSrv() {

                    var d = new Date();
                    alert("d=" + d);
                    var e = formatDate(d);
                    alert("e=" + e);
                    var usuario = {
                        "empresaId": "12",
                        "fecha": e,
                        "placa": document.getElementById('placa').value,
                        "hotel_srv": getHotel(),
                        "keyCommand": "30",
                        "sitio": "1"
                    };
                    alert("return " + usuario);
                    return usuario;
                }


                function getJsonCancelarSolicitud(placa) {

                    var d = new Date();
                    var e = formatDate(d);
                    var jsonServicio = {
                        "placa": placa,
                        "keyCommand": "30",
                        "fecha": e,
                        "sitio": "1"
                    };
                    return jsonServicio;
                }

                function addImgDiv() {
                    $("#divprecio").html("");
                    mostrarDivPrecio();
                    var img = document.createElement("img");
                    img.src = "../source/img/loading6.gif";
                    var foo = document.getElementById("divprecio");
                    foo.appendChild(img);
                }

                function mostrarDivPrecio() {
                    var modal = document.getElementById('divprecio');
                    modal.style.display = "block";
                    document.getElementById("latitud").value = '';
                    document.getElementById("longitud").value = '';
                }
                function cerrarDivPrecio() {
                    var modal = document.getElementById('divprecio');
                    modal.style.display = "none";
                    document.getElementById("latitud").value = '';
                    document.getElementById("longitud").value = '';
                    document.getElementById('divprecio').innerHTML = '';
                }


                function buscarPlaca(e) {
                    alert("buscandp..");
                    if (e.keyCode == 13) {
                        alert("buscandp..2..");
                        if (true) {
                            buscarPlacaAjax();
                        } else {
                            var placa = document.getElementById('placa').value;
                            var URLSRV = '../FuecController?placa=' + placa + "&buscar=true";
                            URLSRV += "&jsoncallback=?";
                            var isOk = "";
                            $.getJSON(URLSRV, function (data) {
                                if (data.error == 'ok') {
                                    alert("nitPagador:" + data.nitPagador);
                                } else {
                                    alert("no encontrado");
                                }
                            });
                            URLSRV = "";
                        }
                    }
                }

                function setImg() {
                    var img = document.createElement("img");
                    img.src = "source/img/loading6.gif";
                    var foo = document.getElementById("rtaPlaca");
                    foo.appendChild(img);
                    // sleep(300);
                }
                function seleccionarVehiculo(cedula) {
                    document.getElementById('conductor_srv').value = cedula;

                    document.getElementById('cedula').value = cedula;

                }
                function buscarPlacaAjax() {
                    var placa = document.getElementById('placa').value;
                    //var url = <%=ruta%> + '/FuecController'
                    $("#rtaPlaca").html("");

                    setImg();
                    var URLSRV = "FuecController?placa=" + placa + "&buscar=true";
                    URLSRV += "&jsoncallback=?";
                    clearAll();
                    $.ajax({
                        url: URLSRV,
                        type: 'POST',
                        data: jQuery.param({
                            placa: placa,
                            jsoncallback: "",
                            action: 'consultarServicio'
                        }),
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        success: function (datos) {
                            var rtaData = datos.error;
                            if (datos != '' && datos != 'no' && datos.error == 'ok') {
                                var html = '';
                                var contador = 0;


                                document.getElementById('dataJson').value = JSON.stringify(datos);

                                $.each(datos.personas, function (i, item) {
                                    if (contador < 1) {
                                        document.getElementById('conductor_srv').value = item.ccConductor1;
                                        seleccionarVehiculo(item.ccConductor1);
                                        html += '<input type="radio" checked="checked" onclick="seleccionarVehiculo(' + item.ccConductor1 + ');" name="conductor_srv" value="' + item.ccConductor1 + '"/>' + item.nombreConductor1 + " " + item.apellidoConductor1 + ", " + item.ccConductor1 + ',Empresa:' + item.empresaVehiculo;
                                    } else {
                                        html += '<input type="radio" name="conductor_srv" onclick="seleccionarVehiculo(' + item.ccConductor1 + ');" value="' + item.ccConductor1 + '"/>' + item.nombreConductor1 + " " + item.apellidoConductor1 + ", " + item.ccConductor1 + ',Empresa:' + item.empresaVehiculo;
                                    }
                                    contador++;
                                    html += '</br>';
                                });
                                document.getElementById('placa').style.backgroundColor = 'white';
                                $("#rtaPlaca").html(html);
                            } else {
                                $("#rtaPlaca").html("<h3>No existen datos</h3>");
                                document.getElementById('placa').style.backgroundColor = 'yellow';
                            }


                        },
                        error: function () {
                            $("#rtaPlaca").html("");
                        }
                    });
                    $("#rtaPlaca").html("");
                }



                function manageRTA(data) {
                    $.each(data.alertas, function (i, item) {

                        var precioCarrera = item.precio;
                        mostrarDivPrecio();
                        document.getElementById('precioCarrera').value = precioCarrera;
                        if (precioCarrera > 0) {
                            document.getElementById('divprecio').innerHTML = '<h3>' + item.metros + ' mts $ ' + precioCarrera + ' COP </h3>';
                        } else {
                            document.getElementById('divprecio').innerHTML = '<p>Ho hemos encontrado el destino, intente cambiar la direcciÃ³n</p>';
                        }

                    });
                }



                function clearAll() {
                    $("#rtaPlaca").html("");
                    document.getElementById('conductor_srv').value = "";
                    document.getElementById('cedula').value = "";
                }
                function getHotel() {
                    var radios = document.getElementsByName('hotel_srv');
                    var formaDePago = "no";
                    for (var i = 0; i < radios.length; i++) {
                        if (radios[i].checked) {
                            // do whatever you want with the checked radio
                            formaDePago = radios[i].value;
                            // only one radio can be logically checked, don't check the rest
                            break;
                        }
                    }
                    return formaDePago;
                }
                function cancelarSolicitud() {
                    var hostName = 'http://185.80.129.150:8380/PrinterService-war/CotizarPrecio';
                    var placa = prompt("Ingrese placa del vehiculo");
                    if (placa != null && placa != '') {
                        var jsonData = JSON.stringify(getJsonCancelarSolicitud(placa));
                        $.ajax({
                            url: hostName,
                            type: 'POST',
                            //headers: {"Content-Type": "application/json"},
                            data: jsonData,
                            //contentType: "application/json",
                            dataType: 'json',
                            cache: false,
                            success: function (response) {
                                var rta = response.error;
                                if (rta = 'ok') {
                                    limpiar();
                                    alert("Ok, informacion recibida, id<" + response.msjError + '>');
                                } else {
                                    alert("ERROR!, intente nuevamente");
                                }
                                derrarDivPrecio();
                            },
                            error: function () {
                                derrarDivPrecio();
                            }
                        });
                    } else {

                    }
                }
                function validarForm() {
                    var data = document.getElementById('conductor_srv').value;
                    if (data != '' && data > 0) {
                        return true;
                    } else {
                        document.getElementById('placa').style.backgroundColor = 'yellow';
                        return false;
                    }
                }

            </script>

        </head>
        <body onload="leerUuario('idUuario');">

            <div id="div01"></div>

            <div style="background: #F7DC6F;">
                <div class="container-fluid">
                    <div class="text-center">
                        </br>
                        <form id="myForm" metho='POST' class="form-horizontal" role="form" autocomplete="of" action="FuecController" onsubmit="return validarForm();">
                            <input type="hidden" name ="conductor_srv" id="conductor_srv"/>
                            <input type="hidden" name="dataJson" id="dataJson"/>

                            <div class="form-group">
                                <img src="icon/logo3.png" width="250" height="100"/>
                            </div>

                            <div class="container">
                                <div class="row">	
                                    <div class="col-xs-3 form-group">
                                        <input type="radio" checked="checked" class="form-control" name="hotel_srv" value="GLOBAL OPERADORA HOTELERA S.A.S. / HUESPEDES - FUNCIONARIOS - VISITANTES ( HOTEL MOVICH BURO 26 )|DESDE AEROPUERTO INTERNACIONAL EL DORADO HASTA AVENIDA DORADO No. 102 - 20 ( HOTEL MOVICH BURO ) - VICEVERSA "/>GLOBAL OPERADORA HOTELERA S.A.S. / HUESPEDES - FUNCIONARIOS - VISITANTES ( HOTEL MOVICH BURO 26 )

                                    </div>
                                    <div class="col-xs-3 form-group">
                                        <input type="radio" class="form-control" name="hotel_srv" value="HOTEL WYNDHAM BOGOTA / HUESPEDES - FUNCIONARIOS - VISITANTES|DESDE AEROPUERTO INTERNACIONAL EL DORADO HASTA AVENIDA  CALLE 24 No  51 - 40  /  VICEVERSA "/>HOTEL WYNDHAM BOGOTA / HUESPEDES - FUNCIONARIOS - VISITANTES
                                    </div>
                                    <div class="col-xs-3 form-group">
                                        <input type="radio" class="form-control" name="hotel_srv" value="DEUTSCHE LUFTHANSA AG / USUARIOS-FUNCIONARIOS|AEROPUERTO INTERNACIONAL EL DORADO Y PERIMETRO URBANO DE BOGOTA"/>DEUTSCHE LUFTHANSA AG / USUARIOS-FUNCIONARIOS
                                    </div>

                                    <div class="col-xs-3 form-group">
                                        <input type="radio" class="form-control" name="hotel_srv" value="SOCIEDAD OPERADORA URBAN ROYAL CALLE 26 S.A.S / HUESPEDES - FUNCIONARIOS- VISITANTES|DESDE AEROPUERTO INTERNACIONAL EL DORADO HASTA LA CARRERA 33 No 25 F  18 / VICEVERSA"/>SOCIEDAD OPERADORA URBAN ROYAL CALLE 26 S.A.S / HUESPEDES - FUNCIONARIOS- VISITANTES
                                    </div>

                                    <div class="col-xs-3 form-group">
                                        <input type="radio" class="form-control" name="hotel_srv" value="SOCIEDAD HOTELERA CALLE 74 LTDA / HUESPEDES - FUNCIONARIOS- VISITANTES|DESDE AEROPUERTO INTERNACIONAL EL DORADO HASTA CALLE 74 No 13 27 / VICEVERSA"/>SOCIEDAD HOTELERA CALLE 74 LTDA / HUESPEDES - FUNCIONARIOS- VISITANTES
                                    </div>

                                    <div class="col-xs-3 form-group">
                                        <input type="radio" class="form-control" name="hotel_srv" value="IBERIA LINEAS AEREAS DE ESPANA / PASAJEROS - CLIENTES - TURISTAS - FUNCIONARIOS|AEROPUERTO INTERNACIONAL EL DORADO Y PERIMETRO URBANO DE BOGOTA "/>IBERIA LINEAS AEREAS DE ESPANA / PASAJEROS - CLIENTES - TURISTAS - FUNCIONARIOS
                                    </div>

                                </div>  
                            </div> 


                            <div class="container">
                                <div class="row">
                                    <div class="input-group">
                                        <input required type="text" name="placa" id="placa" value="WCZ109" class="form-control  clearable">
                                        <span class="input-group-btn">
                                            <button class="btn btn-info"  onclick='buscarPlacaAjax();' id="buscar" type="button">Buscar..</button>
                                        </span>
                                    </div>

                                    <div class="input-group">
                                        <input required type="text" readonly="true" name="cedula" id="cedula" class="form-control  clearable">

                                    </div>

                                    <div id="rtaPlaca"></div>


                                </div>  
                            </div> 


                            <div class="form-group" id="divprecio" style="display:none"></div>

                            <div class="btn-group">
                                <div class="row">
                                    <button type="submit"  name='crear' value="sinTemplate" class="btn btn-success">Genear FUEC</button>
                                    <button type="submit"  name='crear2' value="conTemplate_body" class="btn btn-warning">Genear FUEC Template body</button>
                                    <button type="submit"  name='crear2' value="conTemplate" class="btn btn-info">Genear FUEC Template</button>



                                </div>
                            </div>



                            <!--div class="item">
                                <a href="#" class="item_event">
                                        <div class="imageContentEvent">
                                                <div class="info_place_item">
                                                        <span class="glyphicon glyphicon-map-marker"></span>
                                                        <span><?php echo $row[1]?></span>
                                                </div>
                                                <div class="like">
                                                        <span><i class="fa fa-heart-o" aria-hidden="true"></i></span>
                                                </div>
                                                <div class="img_event" style="background-image: url(assets/img/nike.jpg);">
                                                </div>
                                                <div class="destacados">
                                                        <span class="icn">
                                                                <i class="glyphicon glyphicon-star"></i>
                                                        </span>Destacado
                                                </div>
                                                <div class="peopleIcon"></div>
                                        </div>

                                        <div class="content_text_event">
                                                <p>
                                                        El próximo <?php echo $row[2]?>
                                                </p>
                                                <span class="view_more">Ver más</span>
                                        </div>
                                </a>
                        </div-->



                            </br></br>
                        </form>
                    </div>
                </div>	
            </div>	
            <div style="display:none" id="map_canvas"></div>

            <script src="assets/js/jquery.js"></script>
            <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/jquery.bxslider.min.js"></script>
            <script src="assets/js/jquery-ui.js"></script>
            <script type="text/javascript" src="assets/js/moment.js"></script>
            <script type="text/javascript" src="assets/js/bootstrap-datetimepicker.js"></script>
            <script src="assets/js/owl.carousel.js"></script>
            <script src="assets/js/jquery.cropit.js"></script>
            <script src="assets/js/bootstrap-tagsinput.js"></script>
            <script src="assets/js/autocomplete.js"></script>
            <script src="assets/js/touchpunch.js"></script>
            <script src="assets/js/autogrow-textarea.js"></script>
            <script src="assets/js/dataTables.bootstrap.js"></script>
            <script src="assets/js/main.js"></script>

        </body>