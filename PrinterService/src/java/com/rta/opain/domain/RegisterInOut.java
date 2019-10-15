/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hernan Sanchez
 */
@Entity
@Table(name = "register_in_out")
@XmlRootElement
public class RegisterInOut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_register_in_out")
    private Integer idRegisterInOut;
    @JoinColumn(name = "id_sitios", referencedColumnName = "id")
    @ManyToOne
    private Sitios idSitios;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "numero_contrato")
    private Integer numeroContrato;
    @Column(name = "autoplaca")
    private String vehiculoPlaca;
    @Column(name = "detalle")
    private String detalle;
    Integer dias;

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }
    

    public Sitios getIdSitios() {
        return idSitios;
    }

    public void setIdSitios(Sitios idSitios) {
        this.idSitios = idSitios;
    }

    public Integer getIdRegisterInOut() {
        return idRegisterInOut;
    }

    public void setIdRegisterInOut(Integer idRegisterInOut) {
        this.idRegisterInOut = idRegisterInOut;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getVehiculoPlaca() {
        return vehiculoPlaca;
    }

    public void setVehiculoPlaca(String vehiculoPlaca) {
        this.vehiculoPlaca = vehiculoPlaca;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "com.rta.num.cien.domain.idRegisterInOut[ idSrvCarCoste=" + idRegisterInOut + " ]";
    }
}
