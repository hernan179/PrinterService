/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ciudades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c"),
    @NamedQuery(name = "Ciudades.findByIdCiudad", query = "SELECT c FROM Ciudades c WHERE c.idCiudad = :idCiudad"),
    @NamedQuery(name = "Ciudades.findByCodigo", query = "SELECT c FROM Ciudades c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Ciudades.findByDetalle", query = "SELECT c FROM Ciudades c WHERE c.detalle = :detalle")})
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer idCiudad;
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "detalle")
    private String detalle;
    @JoinColumn(name = "id_paises", referencedColumnName = "id_paises")
    @ManyToOne
    private Pais idPais;
    @OneToMany(mappedBy = "idCiudades")
    private List<Sedes> sedes;

    public List<Sedes> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sedes> sedes) {
        this.sedes = sedes;
    }

    public Ciudades() {
    }

    public Ciudades(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Ciudades(Integer idCiudad, Integer codigo) {
        this.idCiudad = idCiudad;
        this.codigo = codigo;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.idCiudad == null && other.idCiudad != null) || (this.idCiudad != null && !this.idCiudad.equals(other.idCiudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rta.num.cien.domain.Ciudades[ idCiudad=" + idCiudad + " ]";
    }
}
