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
@Table(name = "srv_preview")
@XmlRootElement

public class SrvPreview implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_srv_preview")
    private Integer id;

    @Column(name = "placa")
    private String autoPlaca;

    @Column(name = "json")
    private String json;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "id_estado")
    private Integer idEstado;

    public SrvPreview() {
    }

    public SrvPreview(Integer id, String autoPlaca, String json, Date fecha, String referencia, Integer idEstado) {
        this.id = id;
        this.autoPlaca = autoPlaca;
        this.fecha = fecha;
        this.json = json;
        this.referencia = referencia;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

}
