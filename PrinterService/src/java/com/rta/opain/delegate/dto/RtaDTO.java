/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.dto;

import com.rta.opain.domain.Servicios;
import com.rta.opain.domain.SrvPreview;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hsancheza
 */
public class RtaDTO implements Serializable {

    List<JsonDTO> alertas;
    List<Integer> escala;
    String pendientes;
    String error;
    String msjError;
    String keyCommand;
    String intentsPrinter;
    List<JsonDTO> lstSrvPreview;

    public List<JsonDTO> getLstSrvPreview() {
        return lstSrvPreview;
    }

    public void setLstSrvPreview(List<JsonDTO> lstSrvPreview) {
        this.lstSrvPreview = lstSrvPreview;
    }

    public String getIntentsPrinter() {
        return intentsPrinter;
    }

    public void setIntentsPrinter(String intentsPrinter) {
        this.intentsPrinter = intentsPrinter;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    String mac;

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getPendientes() {
        return pendientes;
    }

    public void setPendientes(String pendientes) {
        this.pendientes = pendientes;
    }

    public String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public List<Integer> getEscala() {
        return escala;
    }

    public void setEscala(List<Integer> escala) {
        this.escala = escala;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<JsonDTO> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<JsonDTO> alertas) {
        this.alertas = alertas;
    }
}
