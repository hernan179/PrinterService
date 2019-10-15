function makeCall() {
    var telefono = document.getElementById('phone').value;
    var tel_id = '0';
    document.getElementById('phone').style.backgroundColor = 'white';
    if (telefono != '' && fTelefono(telefono)) {
        var dyn05 = 1;
        var dyn06 = 0;
        var dyn07 = "339361-127825-200000-[{indice=1, detalle=PAGO EFECTIVO}, {indice=2, detalle=PAGO CON VALE ELECTRONICO}]";
        // window.parent.loadScript(4, 2, 2, 2, 5, 2, telefono,4, dyn05, dyn06, dyn07, 0);   
        // OK original parent.llamadaEntrante(telefono,11001,tel_id,'0');
        parent.report(4, 2, 2, 2, 5, 2, telefono, dyn06, '0');
    } else {
        document.getElementById('phone').style.backgroundColor = 'yellow';
        alert("3. Numero de telefono incorrecto");
    }
}


function callVale() {

    var telefono = document.getElementById('phone').value;
    if (telefono != '' && telefono.length > 6) {
        document.getElementById('phone').style.backgroundColor = 'white';

        parent.validarVale(telefono);

    } else {
        document.getElementById('phone').style.backgroundColor = 'yellow';

    }
    return null;
}
function Solo_Numerico(variable) {
    Numer = parseInt(variable);
    if (isNaN(Numer)) {
        return "";
    }
    return Numer;
}
function showData(value) {
    var textBox = document.getElementById("ciudad").value;
    var textLength = textBox.length;

    if (textLength >= 7 && textLength <= 8) {//4903000
        document.getElementById('ciudad').style.backgroundColor = 'red';
    }
}
function showDataCar(value) {
    var textBox = document.getElementById("numeroCarros").value;
    if (!(textBox >= 2 && textBox <= 5)) {//4903000
        document.getElementById('numeroCarros').style.backgroundColor = 'yellow';
        document.getElementById('numeroCarros').value = '1';
    }
}
function ValNumeroVal(Control) {
    Control.value = Solo_Numerico(Control.value);
    showData(Control.value);
    
}

function disableChkBoxVale(){
     document.getElementById("conVale").checked = false;
}

function ValNumeroCar(Control) {
    Control.value = Solo_Numerico(Control.value);
    showDataCar(Control.value);
}

function fTelefono(tel) {
    var RegExPatternX = new RegExp("[0123456789]");
    if (tel.match(RegExPatternX)) {
        return true;
    } else {
        return false;
    }
}

function updateCliente() {
    parent.updateCliente();
}

function buscarDir(e) {
    if (e.keyCode == 13) {
        zonificador();

    }
}
function buscarVale() {

    callVale();

}
function buscarValeEvent(e) {
    if (e.keyCode == 13) {
        validarVale('no');
    }
}

function buscarCli(e) {
    if (e.keyCode == 13) {
        makeCall();
        return false;
    }
}
function buscarCliThis(e) {
    if (e.keyCode == 13) {
        callClienteThis();
        return false;
    }
}

function buscarSrvByTelefono(e) {
    if (e.keyCode == 13) {
        srvByTelefono();
        return false;
    }
}
function buscarSrvByNombre(e) {
    if (e.keyCode == 13) {
        srvByNombre();
        return false;
    }
}
function buscarSrvByDir(e) {
    if (e.keyCode == 13) {
        srvByDir();
        return false;
    }
}