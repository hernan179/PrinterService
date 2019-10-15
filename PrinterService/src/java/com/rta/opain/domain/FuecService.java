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
@Table(name = "fuec_service")
@XmlRootElement

public class FuecService implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fuec_service")
    private Integer id;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "placa")
    private String autoPlaca;

    @Column(name = "json")
    private String json;

    @Column(name = "consecutivo")
    private String consecutivo;

    public FuecService() {
    }

    public FuecService(Integer id, String autoPlaca, String json, Date fecha, String consecutivo, Integer idEstado) {
        this.id = id;
        this.autoPlaca = autoPlaca;
        this.fecha = fecha;
        this.json = json;
        this.consecutivo = consecutivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

}
