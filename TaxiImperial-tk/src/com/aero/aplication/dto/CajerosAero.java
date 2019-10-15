/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

/**
 *
 * @author hsancheza
 */
public class CajerosAero {

    private String nombre;
    private String clave;
    private String usuario;
    private String idUsuario;
    private String idSitio;

    public String getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(String idSitio) {
        this.idSitio = idSitio;
    }
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
