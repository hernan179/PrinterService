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
@Table(name = "srv_car_coste")
@XmlRootElement
public class SrvCarCoste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_srv_car_coste")
    private Integer idSrvCarCoste;
    @Column(name = "numero_contrato")
    private Integer numeroContrato;
    @Column(name = "vehiculo_placa")
    private String vehiculoPlaca;
    @Column(name = "credito")
    private String credito;
    @Column(name = "fecha_vencida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencida;

    public Integer getIdSrvCarCoste() {
        return idSrvCarCoste;
    }

    public void setIdSrvCarCoste(Integer idSrvCarCoste) {
        this.idSrvCarCoste = idSrvCarCoste;
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

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public Date getFechaVencida() {
        return fechaVencida;
    }

    public void setFechaVencida(Date fechaVencida) {
        this.fechaVencida = fechaVencida;
    }

    @Override
    public String toString() {
        return "com.rta.num.cien.domain.SrvCarCoste[ idSrvCarCoste=" + idSrvCarCoste + " ]";
    }
}
