/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hsancheza
 */
@Entity
@Table(name = "temp_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TempVehiculo.findById", query = "SELECT s FROM TempVehiculo s WHERE s.id  = :id")})

public class TempVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_temp_vehiculo")
    private Integer id;

    @Column(name = "id_tipo_vehiculo")
    private Integer idTipoVehiculo;

    @Column(name = "auto_placa")
    private String autoPlaca;

    @Basic(optional = false)
    @Column(name = "json")
    private String json;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "id_estado")
    private Integer idEstado;

    public TempVehiculo() {
    }

    public TempVehiculo(Integer id, String autoPlaca, String json, Date fecha, Integer idTipoVehiculo, Integer idEstado) {
        this.id = id;
        this.autoPlaca = autoPlaca;
        this.fecha = fecha;
        this.json = json;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    
    
    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public TempVehiculo(Integer i) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoPlaca() {
        return autoPlaca;
    }

    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "com.rta.aeropuerto.domain.CajerosAero[ id=" + id + " ]";
    }
}
