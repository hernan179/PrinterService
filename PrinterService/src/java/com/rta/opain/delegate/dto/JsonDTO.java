package com.rta.opain.delegate.dto;

import java.io.Serializable;
import java.util.List;

public class JsonDTO
        implements Serializable {

    public String msjError;
    public String count;
    public String diferencia;
    public String resultado;
    public String bloqueo;
    public String bloqueoX;
    public String total;
    public String efectivo;
    public String credito;
    public String debito;
    public String contrato;
    public String fecha;
    public String placa;
    public String detalle;
    public String keyCommand;
    public Integer dias;
    public String empresaId;
    public String error;
    public String nombreSitio;
    public String direccion;
    public String valor;
    public String idSitio;
    public String usuario;
    public String clave;
    public String nombre;
    public String tipoPago;
    public String nit;
    public String sitio;
    public String grabador;
    public String comprob;
    public String cierre;
    public String valorE;
    public String valorD;
    public String valorC;
    public String porque;
    public String idUsuario;
    public String control;
    public String valorTotal;
    public String inicio;
    public String fin;
    public String usuarioNombre;
    public String sitioNombre;
    public String cantidad;
    public String numeroFactura;
    public String nombreCajero;
    public String estado;
    public String valorAnulados;
    public String cantidadAnulads;
    public String escala;
    public String consecutivo;
    public String id;
    public String addres;
    public String asignacionesId;
    public String fechaGrab;
    public String indProc;
    public String numero;
    public String estadoId;
    public String direccionNormalizada;
    public String metros;
    public String movil;
    public String precio;
    public String tiempo;
    public String totalCange;
    public String facturaAntigua;
    public String facturaUltima;
    public String pesos;
    public String sqlFaltan;
    public String sqlUpdate;
    public String sql;
    public String ultimo;
    public String nAsign;
    public String clased;
    public Ticket json;
    public String fuec;
    public String transaccion;
    public String numeroLargo;
    public Ticket tiquete;
    public String latitud;
    public String longitud;
    public List<Persona> persona;
    public String convenio;
    public String modelo;
    public String marca;
    public String clase;
    public String numeroInterno;
    public String tarjetaOperacion;
    public String nombreConductor;
    public String cedulaConductor;
    public String licenciaConudctor;
    public String vigenciaLicenciaConductor;
    public String conductor2;
    public String cedulaConductor2;
    public String licenciaConudctor2;
    public String vigenciaLicenciaConductor2;
    public String responsableContratante;
    public String cedula;
    public String telefono;
    public String dispositivo;
    public String fechaFin;
    public String base;
    public String lineaCar;
    public String mac;
    public String valoro;
    public String nitot;
    
    public String pasajero;
    
    public String nombreEmpresa;
    
    public String consolidadoNameAero;
    public String consolidadoValueAero;
    public String consolidadoNameVps;
    public String consolidadoValueVps;
    public String consolidadoFaltante;
    public String imprimeTiquete;
    public List<Persona> personas;
    public String atenditoPor;
    public List<Ticket> vehiculos;

    public String getValoro() {
        return valoro;
    }

    public void setValoro(String valoro) {
        this.valoro = valoro;
    }

    public String getNitot() {
        return nitot;
    }

    public void setNitot(String nitot) {
        this.nitot = nitot;
    }
    
    

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLineaCar() {
        return lineaCar;
    }

    public void setLineaCar(String lineaCar) {
        this.lineaCar = lineaCar;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    
    
    public List<Ticket> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Ticket> vehiculos) {
        this.vehiculos = vehiculos;
    }

    
            

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
    
    

    /* public List<Ticket> vehiculos;

    public List<Ticket> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Ticket> vehiculos) {
        this.vehiculos = vehiculos;
    }*/

    public String getConsolidadoFaltante() {
        return this.consolidadoFaltante;
    }

    public void setConsolidadoFaltante(String consolidadoFaltante) {
        this.consolidadoFaltante = consolidadoFaltante;
    }

    public String getConsolidadoNameVps() {
        return this.consolidadoNameVps;
    }

    public void setConsolidadoNameVps(String consolidadoNameVps) {
        this.consolidadoNameVps = consolidadoNameVps;
    }

    public String getConsolidadoValueVps() {
        return this.consolidadoValueVps;
    }

    public void setConsolidadoValueVps(String consolidadoValueVps) {
        this.consolidadoValueVps = consolidadoValueVps;
    }

    public String getConsolidadoNameAero() {
        return this.consolidadoNameAero;
    }

    public void setConsolidadoNameAero(String consolidadoNameAero) {
        this.consolidadoNameAero = consolidadoNameAero;
    }

    public String getConsolidadoValueAero() {
        return this.consolidadoValueAero;
    }

    public void setConsolidadoValueAero(String consolidadoValueAero) {
        this.consolidadoValueAero = consolidadoValueAero;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDispositivo() {
        return this.dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImprimeTiquete() {
        return this.imprimeTiquete;
    }

    public void setImprimeTiquete(String imprimeTiquete) {
        this.imprimeTiquete = imprimeTiquete;
    }

    public String getTotalCange() {
        return this.totalCange;
    }

    public void setTotalCange(String totalCange) {
        this.totalCange = totalCange;
    }

    public String getFacturaAntigua() {
        return this.facturaAntigua;
    }

    public void setFacturaAntigua(String facturaAntigua) {
        this.facturaAntigua = facturaAntigua;
    }

    public String getFacturaUltima() {
        return this.facturaUltima;
    }

    public void setFacturaUltima(String facturaUltima) {
        this.facturaUltima = facturaUltima;
    }

    public String getBloqueoX() {
        return this.bloqueoX;
    }

    public void setBloqueoX(String bloqueoX) {
        this.bloqueoX = bloqueoX;
    }

    public String getBloqueo() {
        return this.bloqueo;
    }

    public void setBloqueo(String bloqueo) {
        this.bloqueo = bloqueo;
    }

    public String getResultado() {
        return this.resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMsjError() {
        return this.msjError;
    }

    public String getDiferencia() {
        return this.diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEfectivo() {
        return this.efectivo;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public String getDebito() {
        return this.debito;
    }

    public void setDebito(String debito) {
        this.debito = debito;
    }

    public String getAtenditoPor() {
        return this.atenditoPor;
    }

    public void setAtenditoPor(String atenditoPor) {
        this.atenditoPor = atenditoPor;
    }

    public Ticket getTiquete() {
        return this.tiquete;
    }

    public List<Persona> getPersonas() {
        return this.personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void setTiquete(Ticket tiquete) {
        this.tiquete = tiquete;
    }

    public String getSqlFaltan() {
        return this.sqlFaltan;
    }

    public void setSqlFaltan(String sqlFaltan) {
        this.sqlFaltan = sqlFaltan;
    }

    public String getNumeroLargo() {
        return this.numeroLargo;
    }

    public void setNumeroLargo(String numeroLargo) {
        this.numeroLargo = numeroLargo;
    }

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public String getFuec() {
        return this.fuec;
    }

    public void setFuec(String fuec) {
        this.fuec = fuec;
    }

    public Ticket getJson() {
        return this.json;
    }

    public void setJson(Ticket json) {
        this.json = json;
    }

    public String getSqlUpdate() {
        return this.sqlUpdate;
    }

    public void setSqlUpdate(String sqlUpdate) {
        this.sqlUpdate = sqlUpdate;
    }

    public List<Persona> getPersona() {
        return this.persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }

    public String getConvenio() {
        return this.convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getClase() {
        return this.clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNumeroInterno() {
        return this.numeroInterno;
    }

    public void setNumeroInterno(String numeroInterno) {
        this.numeroInterno = numeroInterno;
    }

    public String getTarjetaOperacion() {
        return this.tarjetaOperacion;
    }

    public void setTarjetaOperacion(String tarjetaOperacion) {
        this.tarjetaOperacion = tarjetaOperacion;
    }

    public String getNombreConductor() {
        return this.nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getCedulaConductor() {
        return this.cedulaConductor;
    }

    public void setCedulaConductor(String cedulaConductor) {
        this.cedulaConductor = cedulaConductor;
    }

    public String getLicenciaConudctor() {
        return this.licenciaConudctor;
    }

    public void setLicenciaConudctor(String licenciaConudctor) {
        this.licenciaConudctor = licenciaConudctor;
    }

    public String getVigenciaLicenciaConductor() {
        return this.vigenciaLicenciaConductor;
    }

    public void setVigenciaLicenciaConductor(String vigenciaLicenciaConductor) {
        this.vigenciaLicenciaConductor = vigenciaLicenciaConductor;
    }

    public String getConductor2() {
        return this.conductor2;
    }

    public void setConductor2(String conductor2) {
        this.conductor2 = conductor2;
    }

    public String getCedulaConductor2() {
        return this.cedulaConductor2;
    }

    public void setCedulaConductor2(String cedulaConductor2) {
        this.cedulaConductor2 = cedulaConductor2;
    }

    public String getLicenciaConudctor2() {
        return this.licenciaConudctor2;
    }

    public void setLicenciaConudctor2(String licenciaConudctor2) {
        this.licenciaConudctor2 = licenciaConudctor2;
    }

    public String getVigenciaLicenciaConductor2() {
        return this.vigenciaLicenciaConductor2;
    }

    public void setVigenciaLicenciaConductor2(String vigenciaLicenciaConductor2) {
        this.vigenciaLicenciaConductor2 = vigenciaLicenciaConductor2;
    }

    public String getResponsableContratante() {
        return this.responsableContratante;
    }

    public void setResponsableContratante(String responsableContratante) {
        this.responsableContratante = responsableContratante;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUltimo() {
        return this.ultimo;
    }

    public void setUltimo(String ultimo) {
        this.ultimo = ultimo;
    }

    public String getnAsign() {
        return this.nAsign;
    }

    public void setnAsign(String nAsign) {
        this.nAsign = nAsign;
    }

    public String getClased() {
        return this.clased;
    }

    public void setClased(String clased) {
        this.clased = clased;
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getPesos() {
        return this.pesos;
    }

    public void setPesos(String pesos) {
        this.pesos = pesos;
    }

    public String getAsignacionesId() {
        return this.asignacionesId;
    }

    public void setAsignacionesId(String asignacionesId) {
        this.asignacionesId = asignacionesId;
    }

    public String getFechaGrab() {
        return this.fechaGrab;
    }

    public void setFechaGrab(String fechaGrab) {
        this.fechaGrab = fechaGrab;
    }

    public String getIndProc() {
        return this.indProc;
    }

    public void setIndProc(String indProc) {
        this.indProc = indProc;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstadoId() {
        return this.estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getEscala() {
        return this.escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getDireccionNormalizada() {
        return this.direccionNormalizada;
    }

    public void setDireccionNormalizada(String direccionNormalizada) {
        this.direccionNormalizada = direccionNormalizada;
    }

    public String getMetros() {
        return this.metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public String getMovil() {
        return this.movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getPrecio() {
        return this.precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getValorAnulados() {
        return this.valorAnulados;
    }

    public void setValorAnulados(String valorAnulados) {
        this.valorAnulados = valorAnulados;
    }

    public String getCantidadAnulads() {
        return this.cantidadAnulads;
    }

    public void setCantidadAnulads(String cantidadAnulads) {
        this.cantidadAnulads = cantidadAnulads;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmpresaId() {
        return this.empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombreCajero() {
        return this.nombreCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public String getNumeroFactura() {
        return this.numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getInicio() {
        return this.inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return this.fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getUsuarioNombre() {
        return this.usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getSitioNombre() {
        return this.sitioNombre;
    }

    public void setSitioNombre(String sitioNombre) {
        this.sitioNombre = sitioNombre;
    }

    public String getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getControl() {
        return this.control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getValorE() {
        return this.valorE;
    }

    public void setValorE(String valorE) {
        this.valorE = valorE;
    }

    public String getValorD() {
        return this.valorD;
    }

    public void setValorD(String valorD) {
        this.valorD = valorD;
    }

    public String getValorC() {
        return this.valorC;
    }

    public void setValorC(String valorC) {
        this.valorC = valorC;
    }

    public String getPorque() {
        return this.porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public String getCierre() {
        return this.cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getComprob() {
        return this.comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    public String getGrabador() {
        return this.grabador;
    }

    public void setGrabador(String grabador) {
        this.grabador = grabador;
    }

    public String getSitio() {
        return this.sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoPago() {
        return this.tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreSitio() {
        return this.nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdSitio() {
        return this.idSitio;
    }

    public void setIdSitio(String idSitio) {
        this.idSitio = idSitio;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getDias() {
        return this.dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getKeyCommand() {
        return this.keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public String getContrato() {
        return this.contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCredito() {
        return this.credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
