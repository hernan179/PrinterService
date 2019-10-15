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
@Table(name = "bloqueo")
@XmlRootElement

public class Bloqueo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bloqueo")
    private Integer id;

    @Column(name = "placa")
    private String autoPlaca;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hasta;

    public Bloqueo() {
    }

    public Bloqueo(Integer id, String autoPlaca, String detalle, Date fecha, Date hasta, Integer idEstado) {
        this.id = id;
        this.autoPlaca = autoPlaca;
        this.fecha = fecha;
        this.detalle = detalle;
        this.hasta = hasta;
        this.idEstado = idEstado;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    

}
