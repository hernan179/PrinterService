/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.opain.delegate.dto.JsonDTO;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author hsancheza
 */
public class JsonReturn {

    public List<JsonDTO> servicios;
    String error;
    String correo;
    String clave;
    String empresaId;
    String keyCommand;
    String telefono;
    String dir;
    String codCiudad;
    String telhID;
    String pidJBPM;

    public String getTelhID() {
        return telhID;
    }

    public void setTelhID(String telhID) {
        this.telhID = telhID;
    }

    public String getPidJBPM() {
        return pidJBPM;
    }

    public void setPidJBPM(String pidJBPM) {
        this.pidJBPM = pidJBPM;
    }
    
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(String codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public void setKeyCommand(String keyCommand) {
        this.keyCommand = keyCommand;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<JsonDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<JsonDTO> servicios) {
        this.servicios = servicios;
    }
}
