/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hsancheza
 */
public class Ticket implements Serializable {

    String numeroLargo;
    String ip;
    String usuarios;
    String disponible;
    String objecto;
    String fecha;
    String fechaFin;
    String fechaGrab;
    String valor;
    String origen;
    String destino;
    String empresa;
    String empresaVehiculo;
    String nitPagador;
    String firma;
    Integer distancia;
    Integer unidades;
    CajerosAero usuario;
    String placa;
    String tipoPago;
    String nitCliente;
    String nitConductor;
    Long id;
    Integer iva;
    String valorServicio;
    String conductor;
    Integer cuotaAdmon;
    String empresaVehi;
    Integer total;
    Integer movil;
    String hora;
    String _2300;
    String val_conduct;
    String nombre;
    String pasajero;
    String marca;
    String modelo;
    String logEmpreVehiculo;
    String logFirmaVehiculo;
    String firmaRepLegal;
    String autorizado;
    String urlLogo;
    String nombreConductor1;
    String nombreConductor2;
    String apellidoConductor1;
    String apellidoConductor2;
    String ccConductor1;
    String ccConductor2;
    String licenciaConductor1;
    String licenciaConductor2;
    String vigenciaConductor1;
    String vigenciaConductor2;
    String nombreResponsable;
    String apellidoResponsable;
    String ccResponsable;
    String telResponsable;
    String direccionResponsable;
    public String msjError;
    String fechaMDP;
    String retiros;
    String indProc;
    String transaccion;
    String retiroParcial;
    String base;
    

    public String nombreConductor3;
    public String apellidoConductor3;
    public String ccConductor3;
    public String vigenciaConductor3;
    public String licenciaConductor3;
    
    ////////////////////////////////////////////
  
 
 
    public String nitConductor3;

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
    
    ////////////////////////////////////////
    
    
    
    
    
    
    

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    
    public String getFechaGrab() {
        return fechaGrab;
    }

    public void setFechaGrab(String fechaGrab) {
        this.fechaGrab = fechaGrab;
    }

    public String getRetiroParcial() {
        return retiroParcial;
    }

    public void setRetiroParcial(String retiroParcial) {
        this.retiroParcial = retiroParcial;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public String getIndProc() {
        return indProc;
    }

    public void setIndProc(String indProc) {
        this.indProc = indProc;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getObjecto() {
        return objecto;
    }

    public void setObjecto(String objecto) {
        this.objecto = objecto;
    }

    public String getNumeroLargo() {
        return numeroLargo;
    }

    public void setNumeroLargo(String numeroLargo) {
        this.numeroLargo = numeroLargo;
    }

    public String getDisponible() {
        return disponible;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getNombreConductor3() {
        return nombreConductor3;
    }

    public void setNombreConductor3(String nombreConductor3) {
        this.nombreConductor3 = nombreConductor3;
    }

    public String getApellidoConductor3() {
        return apellidoConductor3;
    }

    public void setApellidoConductor3(String apellidoConductor3) {
        this.apellidoConductor3 = apellidoConductor3;
    }

    public String getCcConductor3() {
        return ccConductor3;
    }

    public void setCcConductor3(String ccConductor3) {
        this.ccConductor3 = ccConductor3;
    }

    public String getVigenciaConductor3() {
        return vigenciaConductor3;
    }

    public void setVigenciaConductor3(String vigenciaConductor3) {
        this.vigenciaConductor3 = vigenciaConductor3;
    }

    public String getLicenciaConductor3() {
        return licenciaConductor3;
    }

    public void setLicenciaConductor3(String licenciaConductor3) {
        this.licenciaConductor3 = licenciaConductor3;
    }

    public String getNitPagador() {
        return nitPagador;
    }

    public void setNitPagador(String nitPagador) {
        this.nitPagador = nitPagador;
    }

    public String getEmpresaVehiculo() {
        return empresaVehiculo;
    }

    public void setEmpresaVehiculo(String empresaVehiculo) {
        this.empresaVehiculo = empresaVehiculo;
    }

    public String getRetiros() {
        return retiros;
    }

    public void setRetiros(String retiros) {
        this.retiros = retiros;
    }

    public String getFechaMDP() {
        return fechaMDP;
    }

    public void setFechaMDP(String fechaMDP) {
        this.fechaMDP = fechaMDP;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getNitPropieta() {
        return nitPropieta;
    }

    public void setNitPropieta(String nitPropieta) {
        this.nitPropieta = nitPropieta;
    }

    public String getNitConductor2() {
        return nitConductor2;
    }

    public void setNitConductor2(String nitConductor2) {
        this.nitConductor2 = nitConductor2;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getGrabador() {
        return grabador;
    }

    public void setGrabador(String grabador) {
        this.grabador = grabador;
    }

    public String getComprob() {
        return comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getValorE() {
        return valorE;
    }

    public void setValorE(String valorE) {
        this.valorE = valorE;
    }

    public String getValorD() {
        return valorD;
    }

    public void setValorD(String valorD) {
        this.valorD = valorD;
    }

    public String getValorC() {
        return valorC;
    }

    public void setValorC(String valorC) {
        this.valorC = valorC;
    }

    public String getPorque() {
        return porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getSitioNombre() {
        return sitioNombre;
    }

    public void setSitioNombre(String sitioNombre) {
        this.sitioNombre = sitioNombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
  
   

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public CajerosAero getUsuario() {
        return usuario;
    }

    public void setUsuario(CajerosAero usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNitConductor() {
        return nitConductor;
    }

    public void setNitConductor(String nitConductor) {
        this.nitConductor = nitConductor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public String getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(String valorServicio) {
        this.valorServicio = valorServicio;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Integer getCuotaAdmon() {
        return cuotaAdmon;
    }

    public void setCuotaAdmon(Integer cuotaAdmon) {
        this.cuotaAdmon = cuotaAdmon;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMovil() {
        return movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String get2300() {
        return _2300;
    }

    public void set2300(String _2300) {
        this._2300 = _2300;
    }

    public String getVal_conduct() {
        return val_conduct;
    }

    public void setVal_conduct(String val_conduct) {
        this.val_conduct = val_conduct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLogEmpreVehiculo() {
        return logEmpreVehiculo;
    }

    public void setLogEmpreVehiculo(String logEmpreVehiculo) {
        this.logEmpreVehiculo = logEmpreVehiculo;
    }

    public String getLogFirmaVehiculo() {
        return logFirmaVehiculo;
    }

    public void setLogFirmaVehiculo(String logFirmaVehiculo) {
        this.logFirmaVehiculo = logFirmaVehiculo;
    }

    public String getFirmaRepLegal() {
        return firmaRepLegal;
    }

    public void setFirmaRepLegal(String firmaRepLegal) {
        this.firmaRepLegal = firmaRepLegal;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNombreConductor1() {
        return nombreConductor1;
    }

    public void setNombreConductor1(String nombreConductor1) {
        this.nombreConductor1 = nombreConductor1;
    }

    public String getNombreConductor2() {
        return nombreConductor2;
    }

    public void setNombreConductor2(String nombreConductor2) {
        this.nombreConductor2 = nombreConductor2;
    }

    public String getApellidoConductor1() {
        return apellidoConductor1;
    }

    public void setApellidoConductor1(String apellidoConductor1) {
        this.apellidoConductor1 = apellidoConductor1;
    }

    public String getApellidoConductor2() {
        return apellidoConductor2;
    }

    public void setApellidoConductor2(String apellidoConductor2) {
        this.apellidoConductor2 = apellidoConductor2;
    }

    public String getCcConductor1() {
        return ccConductor1;
    }

    public void setCcConductor1(String ccConductor1) {
        this.ccConductor1 = ccConductor1;
    }

    public String getCcConductor2() {
        return ccConductor2;
    }

    public void setCcConductor2(String ccConductor2) {
        this.ccConductor2 = ccConductor2;
    }

    public String getLicenciaConductor1() {
        return licenciaConductor1;
    }

    public void setLicenciaConductor1(String licenciaConductor1) {
        this.licenciaConductor1 = licenciaConductor1;
    }

    public String getLicenciaConductor2() {
        return licenciaConductor2;
    }

    public void setLicenciaConductor2(String licenciaConductor2) {
        this.licenciaConductor2 = licenciaConductor2;
    }

    public String getVigenciaConductor1() {
        return vigenciaConductor1;
    }

    public void setVigenciaConductor1(String vigenciaConductor1) {
        this.vigenciaConductor1 = vigenciaConductor1;
    }

    public String getVigenciaConductor2() {
        return vigenciaConductor2;
    }

    public void setVigenciaConductor2(String vigenciaConductor2) {
        this.vigenciaConductor2 = vigenciaConductor2;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public String getCcResponsable() {
        return ccResponsable;
    }

    public void setCcResponsable(String ccResponsable) {
        this.ccResponsable = ccResponsable;
    }

    public String getTelResponsable() {
        return telResponsable;
    }

    public void setTelResponsable(String telResponsable) {
        this.telResponsable = telResponsable;
    }

    public String getDireccionResponsable() {
        return direccionResponsable;
    }

    public void setDireccionResponsable(String direccionResponsable) {
        this.direccionResponsable = direccionResponsable;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNumeroInterno() {
        return numeroInterno;
    }

    public void setNumeroInterno(String numeroInterno) {
        this.numeroInterno = numeroInterno;
    }

    public String getNumeroTarjetaOpe() {
        return numeroTarjetaOpe;
    }

    public void setNumeroTarjetaOpe(String numeroTarjetaOpe) {
        this.numeroTarjetaOpe = numeroTarjetaOpe;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public String getInfoRuta() {
        return infoRuta;
    }

    public void setInfoRuta(String infoRuta) {
        this.infoRuta = infoRuta;
    }

    public String getInfoCosto() {
        return infoCosto;
    }

    public void setInfoCosto(String infoCosto) {
        this.infoCosto = infoCosto;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmpresaVehi() {
        return empresaVehi;
    }

    public void setEmpresaVehi(String empresaVehi) {
        this.empresaVehi = empresaVehi;
    }

    public String getNitPago() {
        return nitPago;
    }

    public void setNitPago(String nitPago) {
        this.nitPago = nitPago;
    }

}
