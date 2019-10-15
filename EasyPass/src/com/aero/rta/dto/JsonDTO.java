package com.aero.rta.dto;

import java.util.Date;

/**
 *
 * @author hsancheza
 */
public class JsonDTO {

    String contrato;
    String fecha;
    String placa;
    String credito;
    String detalle;
    String keyCommand;
    String dias;
    public String bloqueo;
    public String bloqueoX;
    public String msjError;
    public String error;
    String imprimeTiquete;

    String direccionNormalizada;
    String id;
    String metros;
    String movil;
    String precio;
    String tiempo;
    Date ultimaImpresion;

    public String getImprimeTiquete() {
        return imprimeTiquete;
    }

    public void setImprimeTiquete(String imprimeTiquete) {
        this.imprimeTiquete = imprimeTiquete;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(String bloqueo) {
        this.bloqueo = bloqueo;
    }

    public String getBloqueoX() {
        return bloqueoX;
    }

    public void setBloqueoX(String bloqueoX) {
        this.bloqueoX = bloqueoX;
    }

    public Date getUltimaImpresion() {
        return ultimaImpresion;
    }

    public void setUltimaImpresion(Date ultimaImpresion) {
        this.ultimaImpresion = ultimaImpresion;
    }

    public String getDireccionNormalizada() {
        return direccionNormalizada;
    }

    public void setDireccionNormalizada(String direccionNormalizada) {
        this.direccionNormalizada = direccionNormalizada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetros() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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
}
