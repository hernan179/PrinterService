/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hsancheza
 */
@Entity
@Table(name = "lugares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugares.findAll", query = "SELECT l FROM Lugares l"),
    @NamedQuery(name = "Lugares.findById", query = "SELECT l FROM Lugares l WHERE l.id = :id"),
    @NamedQuery(name = "Lugares.findByNombre", query = "SELECT l FROM Lugares l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Lugares.findByDireccion", query = "SELECT l FROM Lugares l WHERE l.direccion = :direccion"),
    @NamedQuery(name = "Lugares.findByParam0", query = "SELECT l FROM Lugares l WHERE l.param0 = :param0"),
    @NamedQuery(name = "Lugares.findByParam1", query = "SELECT l FROM Lugares l WHERE l.param1 = :param1"),
    @NamedQuery(name = "Lugares.findByParam2", query = "SELECT l FROM Lugares l WHERE l.param2 = :param2"),
    @NamedQuery(name = "Lugares.findByParam3", query = "SELECT l FROM Lugares l WHERE l.param3 = :param3"),
    @NamedQuery(name = "Lugares.findByParam4", query = "SELECT l FROM Lugares l WHERE l.param4 = :param4"),
    @NamedQuery(name = "Lugares.findByParam5", query = "SELECT l FROM Lugares l WHERE l.param5 = :param5"),
    @NamedQuery(name = "Lugares.findByParam6", query = "SELECT l FROM Lugares l WHERE l.param6 = :param6"),
    @NamedQuery(name = "Lugares.findByParam7", query = "SELECT l FROM Lugares l WHERE l.param7 = :param7"),
    @NamedQuery(name = "Lugares.findByParam8", query = "SELECT l FROM Lugares l WHERE l.param8 = :param8"),
    @NamedQuery(name = "Lugares.findByParam9", query = "SELECT l FROM Lugares l WHERE l.param9 = :param9"),
    @NamedQuery(name = "Lugares.findByParam10", query = "SELECT l FROM Lugares l WHERE l.param10 = :param10"),
    @NamedQuery(name = "Lugares.findByParam11", query = "SELECT l FROM Lugares l WHERE l.param11 = :param11")})
public class Lugares implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "param_0")
    private Integer param0;
    @Column(name = "param_1")
    private Integer param1;
    @Column(name = "param_2")
    private Integer param2;
    @Column(name = "param_3")
    private Integer param3;
    @Column(name = "param_4")
    private Integer param4;
    @Column(name = "param_5")
    private Integer param5;
    @Column(name = "param_6")
    private Integer param6;
    @Column(name = "param_7")
    private Integer param7;
    @Column(name = "param_8")
    private Integer param8;
    @Column(name = "param_9")
    private Integer param9;
    @Column(name = "param_10")
    private Integer param10;
    @Column(name = "param_11")
    private Integer param11;
    @Column(name = "id_sitio")
    private Integer idSitio;

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public Lugares() {
    }

    public Lugares(Integer id) {
        this.id = id;
    }

    public Lugares(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public Integer getParam0() {
        return param0;
    }

    public void setParam0(Integer param0) {
        this.param0 = param0;
    }

    public Integer getParam1() {
        return param1;
    }

    public void setParam1(Integer param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

    public Integer getParam3() {
        return param3;
    }

    public void setParam3(Integer param3) {
        this.param3 = param3;
    }

    public Integer getParam4() {
        return param4;
    }

    public void setParam4(Integer param4) {
        this.param4 = param4;
    }

    public Integer getParam5() {
        return param5;
    }

    public void setParam5(Integer param5) {
        this.param5 = param5;
    }

    public Integer getParam6() {
        return param6;
    }

    public void setParam6(Integer param6) {
        this.param6 = param6;
    }

    public Integer getParam7() {
        return param7;
    }

    public void setParam7(Integer param7) {
        this.param7 = param7;
    }

    public Integer getParam8() {
        return param8;
    }

    public void setParam8(Integer param8) {
        this.param8 = param8;
    }

    public Integer getParam9() {
        return param9;
    }

    public void setParam9(Integer param9) {
        this.param9 = param9;
    }

    public Integer getParam10() {
        return param10;
    }

    public void setParam10(Integer param10) {
        this.param10 = param10;
    }

    public Integer getParam11() {
        return param11;
    }

    public void setParam11(Integer param11) {
        this.param11 = param11;
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
        if (!(object instanceof Lugares)) {
            return false;
        }
        Lugares other = (Lugares) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rta.aeropuerto.domain.Lugares[ id=" + id + " ]";
    }
}
