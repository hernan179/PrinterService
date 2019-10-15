/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hsancheza
 */
public class Servicios implements Serializable{

    String valor;
    String valorD;
    String valorC;
    String valorE;
    Sitios sitio;
    CajerosAero cajero;
    String id;
    String numeroFactura;
    String addres;
    String autoPlaca;
    String nit;
    String grabador;
    String fecha;
    String suarios;
    String tipoPago;
    CajerosAero usuarios;
    String comprob;
    String cierre;
    String base;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    

    public String getSuarios() {
        return suarios;
    }

    public void setSuarios(String suarios) {
        this.suarios = suarios;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public CajerosAero getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(CajerosAero usuarios) {
        this.usuarios = usuarios;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGrabador() {
        return grabador;
    }

    public void setGrabador(String grabador) {
        this.grabador = grabador;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getAutoPlaca() {
        return autoPlaca;
    }

    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CajerosAero getCajero() {
        return cajero;
    }

    public void setCajero(CajerosAero cajero) {
        this.cajero = cajero;
    }

    public Sitios getSitio() {
        return sitio;
    }

    public void setSitio(Sitios sitio) {
        this.sitio = sitio;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public String getValorE() {
        return valorE;
    }

    public void setValorE(String valorE) {
        this.valorE = valorE;
    }
}
