/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hsancheza
 */
public class RtaDTO implements Serializable{
    List<JsonDTO> alertas;
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
     List<JsonDTO> lstSrvPreview;

    public List<JsonDTO> getLstSrvPreview() {
        return lstSrvPreview;
    }

    public void setLstSrvPreview(List<JsonDTO> lstSrvPreview) {
        this.lstSrvPreview = lstSrvPreview;
    }

    public List<JsonDTO> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<JsonDTO> alertas) {
        this.alertas = alertas;
    }

   
}
