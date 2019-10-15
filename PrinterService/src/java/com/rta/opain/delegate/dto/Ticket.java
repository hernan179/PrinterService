package com.rta.opain.delegate.dto;

import com.rta.opain.domain.CajerosAero;
import java.io.Serializable;
import java.util.List;

public class Ticket
        implements Serializable {

    public String base;
    public String retiros;
    public String indProc;
    public String transaccion;
    public String retiroParcial;
    public String disponible;
    public String objecto;
    public String fecha;
    public String fechaGrab;
    public String fechaFin;
    public String valor;
    public String origen;
    public String destino;
    public String empresa;
    public String empresaVehiculo;
    public String nitPagador;
    public String firma;
    public Integer distancia;
    public Integer unidades;
    public CajerosAero usuario;
    public String placa;
    public String tipoPago;
    public String nitCliente;
    public String nitConductor;
    public String id;
    public Integer iva;
    public String valorServicio;
    public String conductor;
    public Integer cuotaAdmon;
    public String empresaVehi;
    public Integer total;
    public Integer movil;
    public String hora;
    public String _2300;
    public String val_conduct;
    public String nombre;
    public String pasajero;
    public String marca;
    public String modelo;
    public String logEmpreVehiculo;
    public String logFirmaVehiculo;
    public String firmaRepLegal;
    public String autorizado;
    public String urlLogo;
    public String nombreConductor1;
    public String nombreConductor2;
    public String apellidoConductor1;
    public String apellidoConductor2;
    public String ccConductor1;
    public String ccConductor2;
    public String licenciaConductor1;
    public String licenciaConductor2;
    public String vigenciaConductor1;
    public String vigenciaConductor2;
    public String nombreResponsable;
    public String apellidoResponsable;
    public String ccResponsable;
    public String telResponsable;
    public String direccionResponsable;
    public String msjError;
    public String fechaMDP;
    public String nitConductor3;
    public String nombreConductor3;
    public String apellidoConductor3;
    public String ccConductor3;
    public String vigenciaConductor3;
    public String licenciaConductor3;
    public String numeroLargo;
    public String ip;
    public String usuarios;
    String clase;
    String numeroInterno;
    //Ticket json;
    String consecutivo;
    String infoRuta;
    String infoCosto;
    String nitPago;
    public String nitPropieta;
    public String nitConductor2;
    public String error;
    public String numeroTarjetaOpe;
    public String contrato;
    public String credito;
    public String detalle;
    public String keyCommand;
    public Integer dias;
    public String empresaId;
    public String nombreSitio;
    public String direccion;
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
    public List<Persona> personas;
    public String pasajeros;

    public String getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(String pasajeros) {
        this.pasajeros = pasajeros;
    }

    public String getObjecto() {
        return this.objecto;
    }

    public void setObjecto(String objecto) {
        this.objecto = objecto;
    }

    public String getNumeroLargo() {
        return this.numeroLargo;
    }

    public void setNumeroLargo(String numeroLargo) {
        this.numeroLargo = numeroLargo;
    }

    public String getDisponible() {
        return this.disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
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

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public String getRetiroParcial() {
        return this.retiroParcial;
    }

    public void setRetiroParcial(String retiroParcial) {
        this.retiroParcial = retiroParcial;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getNitConductor3() {
        return this.nitConductor3;
    }

    public void setNitConductor3(String nitConductor3) {
        this.nitConductor3 = nitConductor3;
    }

    public String getNombreConductor3() {
        return this.nombreConductor3;
    }

    public void setNombreConductor3(String nombreConductor3) {
        this.nombreConductor3 = nombreConductor3;
    }

    public String getApellidoConductor3() {
        return this.apellidoConductor3;
    }

    public void setApellidoConductor3(String apellidoConductor3) {
        this.apellidoConductor3 = apellidoConductor3;
    }

    public String getCcConductor3() {
        return this.ccConductor3;
    }

    public void setCcConductor3(String ccConductor3) {
        this.ccConductor3 = ccConductor3;
    }

    public String getVigenciaConductor3() {
        return this.vigenciaConductor3;
    }

    public void setVigenciaConductor3(String vigenciaConductor3) {
        this.vigenciaConductor3 = vigenciaConductor3;
    }

    public String getLicenciaConductor3() {
        return this.licenciaConductor3;
    }

    public void setLicenciaConductor3(String licenciaConductor3) {
        this.licenciaConductor3 = licenciaConductor3;
    }

    public String getNitPagador() {
        return this.nitPagador;
    }

    public void setNitPagador(String nitPagador) {
        this.nitPagador = nitPagador;
    }

    public String getEmpresaVehiculo() {
        return this.empresaVehiculo;
    }

    public void setEmpresaVehiculo(String empresaVehiculo) {
        this.empresaVehiculo = empresaVehiculo;
    }

    public String getRetiros() {
        return this.retiros;
    }

    public void setRetiros(String retiros) {
        this.retiros = retiros;
    }

    public String getFechaMDP() {
        return this.fechaMDP;
    }

    public void setFechaMDP(String fechaMDP) {
        this.fechaMDP = fechaMDP;
    }

    public String getMsjError() {
        return this.msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getNitPropieta() {
        return this.nitPropieta;
    }

    public void setNitPropieta(String nitPropieta) {
        this.nitPropieta = nitPropieta;
    }

    public String getNitConductor2() {
        return this.nitConductor2;
    }

    public void setNitConductor2(String nitConductor2) {
        this.nitConductor2 = nitConductor2;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getContrato() {
        return this.contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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

    public String getKeyCommand() {
        return this.keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public Integer getDias() {
        return this.dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getEmpresaId() {
        return this.empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombreSitio() {
        return this.nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitio() {
        return this.sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getGrabador() {
        return this.grabador;
    }

    public void setGrabador(String grabador) {
        this.grabador = grabador;
    }

    public String getComprob() {
        return this.comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    public String getCierre() {
        return this.cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
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

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getControl() {
        return this.control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
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

    public String getNumeroFactura() {
        return this.numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

//    public Ticket getJson() {
//        return this.json;
//    }
//
//    public void setJson(Ticket json) {
//        this.json = json;
//    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFirma() {
        return this.firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Integer getDistancia() {
        return this.distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getUnidades() {
        return this.unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public CajerosAero getUsuario() {
        return this.usuario;
    }

    public void setUsuario(CajerosAero usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoPago() {
        return this.tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNitCliente() {
        return this.nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNitConductor() {
        return this.nitConductor;
    }

    public void setNitConductor(String nitConductor) {
        this.nitConductor = nitConductor;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIva() {
        return this.iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public String getValorServicio() {
        return this.valorServicio;
    }

    public void setValorServicio(String valorServicio) {
        this.valorServicio = valorServicio;
    }

    public String getConductor() {
        return this.conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Integer getCuotaAdmon() {
        return this.cuotaAdmon;
    }

    public void setCuotaAdmon(Integer cuotaAdmon) {
        this.cuotaAdmon = cuotaAdmon;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMovil() {
        return this.movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String get2300() {
        return this._2300;
    }

    public void set2300(String _2300) {
        this._2300 = _2300;
    }

    public String getVal_conduct() {
        return this.val_conduct;
    }

    public void setVal_conduct(String val_conduct) {
        this.val_conduct = val_conduct;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasajero() {
        return this.pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLogEmpreVehiculo() {
        return this.logEmpreVehiculo;
    }

    public void setLogEmpreVehiculo(String logEmpreVehiculo) {
        this.logEmpreVehiculo = logEmpreVehiculo;
    }

    public String getLogFirmaVehiculo() {
        return this.logFirmaVehiculo;
    }

    public void setLogFirmaVehiculo(String logFirmaVehiculo) {
        this.logFirmaVehiculo = logFirmaVehiculo;
    }

    public String getFirmaRepLegal() {
        return this.firmaRepLegal;
    }

    public void setFirmaRepLegal(String firmaRepLegal) {
        this.firmaRepLegal = firmaRepLegal;
    }

    public String getAutorizado() {
        return this.autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public String getUrlLogo() {
        return this.urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNombreConductor1() {
        return this.nombreConductor1;
    }

    public void setNombreConductor1(String nombreConductor1) {
        this.nombreConductor1 = nombreConductor1;
    }

    public String getNombreConductor2() {
        return this.nombreConductor2;
    }

    public void setNombreConductor2(String nombreConductor2) {
        this.nombreConductor2 = nombreConductor2;
    }

    public String getApellidoConductor1() {
        return this.apellidoConductor1;
    }

    public void setApellidoConductor1(String apellidoConductor1) {
        this.apellidoConductor1 = apellidoConductor1;
    }

    public String getApellidoConductor2() {
        return this.apellidoConductor2;
    }

    public void setApellidoConductor2(String apellidoConductor2) {
        this.apellidoConductor2 = apellidoConductor2;
    }

    public String getCcConductor1() {
        return this.ccConductor1;
    }

    public void setCcConductor1(String ccConductor1) {
        this.ccConductor1 = ccConductor1;
    }

    public String getCcConductor2() {
        return this.ccConductor2;
    }

    public void setCcConductor2(String ccConductor2) {
        this.ccConductor2 = ccConductor2;
    }

    public String getLicenciaConductor1() {
        return this.licenciaConductor1;
    }

    public void setLicenciaConductor1(String licenciaConductor1) {
        this.licenciaConductor1 = licenciaConductor1;
    }

    public String getLicenciaConductor2() {
        return this.licenciaConductor2;
    }

    public void setLicenciaConductor2(String licenciaConductor2) {
        this.licenciaConductor2 = licenciaConductor2;
    }

    public String getVigenciaConductor1() {
        return this.vigenciaConductor1;
    }

    public void setVigenciaConductor1(String vigenciaConductor1) {
        this.vigenciaConductor1 = vigenciaConductor1;
    }

    public String getVigenciaConductor2() {
        return this.vigenciaConductor2;
    }

    public void setVigenciaConductor2(String vigenciaConductor2) {
        this.vigenciaConductor2 = vigenciaConductor2;
    }

    public String getNombreResponsable() {
        return this.nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return this.apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public String getCcResponsable() {
        return this.ccResponsable;
    }

    public void setCcResponsable(String ccResponsable) {
        this.ccResponsable = ccResponsable;
    }

    public String getTelResponsable() {
        return this.telResponsable;
    }

    public void setTelResponsable(String telResponsable) {
        this.telResponsable = telResponsable;
    }

    public String getDireccionResponsable() {
        return this.direccionResponsable;
    }

    public void setDireccionResponsable(String direccionResponsable) {
        this.direccionResponsable = direccionResponsable;
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

    public String getNumeroTarjetaOpe() {
        return this.numeroTarjetaOpe;
    }

    public void setNumeroTarjetaOpe(String numeroTarjetaOpe) {
        this.numeroTarjetaOpe = numeroTarjetaOpe;
    }

    public List<Persona> getPersonas() {
        return this.personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public String getInfoRuta() {
        return this.infoRuta;
    }

    public void setInfoRuta(String infoRuta) {
        this.infoRuta = infoRuta;
    }

    public String getInfoCosto() {
        return this.infoCosto;
    }

    public void setInfoCosto(String infoCosto) {
        this.infoCosto = infoCosto;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmpresaVehi() {
        return this.empresaVehi;
    }

    public void setEmpresaVehi(String empresaVehi) {
        this.empresaVehi = empresaVehi;
    }

    public String getNitPago() {
        return this.nitPago;
    }

    public void setNitPago(String nitPago) {
        this.nitPago = nitPago;
    }
}
