package com.aero.rta.dto;

import java.util.List;

/**
 *
 * @author hsancheza
 */
public class RtaDTO {

    String error;
    String msjError;
    String keyCommand;
    String imprimeTiquete;
    List<JsonDTO> alertas;

    public String getError() {
        return error;
    }
    public List<Integer> escala;

    public List<Integer> getEscala() {
        return escala;
    }

    public void setEscala(List<Integer> escala) {
        this.escala = escala;
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
