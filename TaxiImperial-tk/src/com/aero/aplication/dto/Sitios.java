/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

/**
 *
 * @author hsancheza
 */
public class Sitios {

    String nombre;
    String direccion;
    String idSitio;
    String comprob;
    String fuec;

    public String getFuec() {
        return fuec;
    }

    public void setFuec(String fuec) {
        this.fuec = fuec;
    }
    

    public String getComprob() {
        return comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    
    public String getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(String idSitio) {
        this.idSitio = idSitio;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
