/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

import java.io.Serializable;

/**
 *
 * @author hsancheza
 */
public class Persona implements Serializable {

    String nombreConductor1;
    String apellidoConductor1;
    String ccConductor1;
    String licenciaConductor1;
    String vigenciaConductor1;
    String empresaVehiculo;

    public String getEmpresaVehiculo() {
        return empresaVehiculo;
    }

    public void setEmpresaVehiculo(String empresaVehiculo) {
        this.empresaVehiculo = empresaVehiculo;
    }

    Ticket tk;

    public Ticket getTk() {
        return tk;
    }

    public void setTk(Ticket tk) {
        this.tk = tk;
    }

    public String getNombreConductor1() {
        return nombreConductor1;
    }

    public void setNombreConductor1(String nombreConductor1) {
        this.nombreConductor1 = nombreConductor1;
    }

    public String getApellidoConductor1() {
        return apellidoConductor1;
    }

    public void setApellidoConductor1(String apellidoConductor1) {
        this.apellidoConductor1 = apellidoConductor1;
    }

    public String getCcConductor1() {
        return ccConductor1;
    }

    public void setCcConductor1(String ccConductor1) {
        this.ccConductor1 = ccConductor1;
    }

    public String getLicenciaConductor1() {
        return licenciaConductor1;
    }

    public void setLicenciaConductor1(String licenciaConductor1) {
        this.licenciaConductor1 = licenciaConductor1;
    }

    public String getVigenciaConductor1() {
        return vigenciaConductor1;
    }

    public void setVigenciaConductor1(String vigenciaConductor1) {
        this.vigenciaConductor1 = vigenciaConductor1;
    }

}
