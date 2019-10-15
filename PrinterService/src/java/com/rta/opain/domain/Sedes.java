/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hernan Sanchez
 */
@Entity
@Table(name = "sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sedes.findAll", query = "SELECT s FROM Sedes s"),
    @NamedQuery(name = "Sedes.findById", query = "SELECT s FROM Sedes s WHERE s.id = :id"),
    @NamedQuery(name = "Sedes.findByIdCiudades", query = "SELECT s FROM Sedes s WHERE s.idCiudades = :idCiudades"),
    @NamedQuery(name = "Sedes.findByNombreSede", query = "SELECT s FROM Sedes s WHERE s.nombreSede = :nombreSede")})
public class Sedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_ciudades", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudades idCiudades;
    @Basic(optional = false)
    @Column(name = "nombre_sede")
    private String nombreSede;
    @Basic(optional = false)
    @Column(name = "param_ini")
    private Integer param_ini;
    @Basic(optional = false)
    @Column(name = "param_fin")
    private Integer param_fin;
    @Basic(optional = false)
    @Column(name = "distancia")
    private Integer distancia;

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getParam_ini() {
        return param_ini;
    }

    public void setParam_ini(Integer param_ini) {
        this.param_ini = param_ini;
    }

    public Integer getParam_fin() {
        return param_fin;
    }

    public void setParam_fin(Integer param_fin) {
        this.param_fin = param_fin;
    }

    public Sedes() {
    }

    public Sedes(Integer id) {
        this.id = id;
    }

    public Sedes(Integer id, Ciudades idCiudades, String nombreSede) {
        this.id = id;
        this.idCiudades = idCiudades;
        this.nombreSede = nombreSede;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ciudades getIdCiudades() {
        return idCiudades;
    }

    public void setIdCiudades(Ciudades idCiudades) {
        this.idCiudades = idCiudades;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sedes)) {
            return false;
        }
        Sedes other = (Sedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rta.num.cien.domain.Sedes[ id=" + id + " ]";
    }
}
