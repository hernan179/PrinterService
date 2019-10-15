package com.rta.opain.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arquitecto java
 */
@Entity
@Table(name = "sitios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sitios.findAll", query = "SELECT s FROM Sitios s"),
    @NamedQuery(name = "Sitios.findById", query = "SELECT s FROM Sitios s WHERE s.id = :id"),
    @NamedQuery(name = "Sitios.findByIdBetweentId", query = "SELECT s FROM Sitios s WHERE s.id >= :id and s.id <= :id2 order by id "),
    @NamedQuery(name = "Sitios.findByNombre", query = "SELECT s FROM Sitios s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sitios.findByDireccion", query = "SELECT s FROM Sitios s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Sitios.findByTelefono", query = "SELECT s FROM Sitios s WHERE s.telefono = :telefono"),
    @NamedQuery(name = "Sitios.findByContacto", query = "SELECT s FROM Sitios s WHERE s.contacto = :contacto"),
    @NamedQuery(name = "Sitios.findByStock", query = "SELECT s FROM Sitios s WHERE s.stock = :stock"),
    @NamedQuery(name = "Sitios.findByExistencias", query = "SELECT s FROM Sitios s WHERE s.existencias = :existencias")})
public class Sitios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private Integer telefono;
    @Basic(optional = false)
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "existencias")
    private Integer existencias;

    public Sitios() {
    }

    public Sitios(Integer id) {
        this.id = id;
    }

    public Sitios(Integer id, String nombre, String direccion, Integer telefono, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contacto = contacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
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
        if (!(object instanceof Sitios)) {
            return false;
        }
        Sitios other = (Sitios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aeropuerto.domain.Sitios[ id=" + id + " ]";
    }
}
