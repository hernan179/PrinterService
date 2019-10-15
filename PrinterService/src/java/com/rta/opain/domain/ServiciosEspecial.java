package com.rta.opain.domain;

import com.rta.opain.domain.CajerosAero;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hsancheza
 */
@Entity
@Table(name = "srv_especial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosEspecial.findAll", query = "SELECT s FROM ServiciosEspecial s"),
    @NamedQuery(name = "ServiciosEspecial.findById", query = "SELECT s FROM ServiciosEspecial s WHERE s.id = :id"),
    @NamedQuery(name = "ServiciosEspecial.findByAddres", query = "SELECT s FROM ServiciosEspecial s WHERE s.addres = :addres"),
    @NamedQuery(name = "ServiciosEspecial.findByFecha", query = "SELECT s FROM ServiciosEspecial s WHERE s.fecha = :fecha"),
    //@NamedQuery(name = "Servicios.findByUsuariosId", query = "SELECT s FROM Servicios s WHERE s.usuarios = :usuarios"),
    @NamedQuery(name = "ServiciosEspecial.findByValor", query = "SELECT s FROM ServiciosEspecial s WHERE s.valor = :valor"),
    @NamedQuery(name = "ServiciosEspecial.findByAutoPlaca", query = "SELECT s FROM ServiciosEspecial s WHERE s.autoPlaca = :autoPlaca"),
    @NamedQuery(name = "ServiciosEspecial.findByTipoPago", query = "SELECT s FROM ServiciosEspecial s WHERE s.tipoPago = :tipoPago"),
    @NamedQuery(name = "ServiciosEspecial.findByNit", query = "SELECT s FROM ServiciosEspecial s WHERE s.nit = :nit"),
    @NamedQuery(name = "ServiciosEspecial.findByGrabador", query = "SELECT s FROM ServiciosEspecial s WHERE s.grabador = :grabador"),
    @NamedQuery(name = "ServiciosEspecial.findByMaxValue", query = "SELECT s FROM ServiciosEspecial s WHERE s.numero = (SELECT MAX(s2.numero) FROM Servicios s2)"),
    @NamedQuery(name = "ServiciosEspecial.findByComprob", query = "SELECT s FROM ServiciosEspecial s WHERE s.comprob = :comprob"),
    @NamedQuery(name = "ServiciosEspecial.findByFechaGrab", query = "SELECT s FROM ServiciosEspecial s WHERE s.fechaGrab = :fechaGrab"),
    @NamedQuery(name = "ServiciosEspecial.findByIndProc", query = "SELECT s FROM ServiciosEspecial s WHERE s.indProc = :indProc")})
public class ServiciosEspecial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "addres")
    private String addres;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Integer valor;
    @Column(name = "distancia")
    private Integer distancia;
    @Column(name = "unidades")
    private Integer unidades;
    @Column(name = "porque")
    private String porque;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "auto_placa")
    private String autoPlaca;
    @Column(name = "tipo_pago")
    private String tipoPago;
    @Column(name = "nit")
    private Long nit;
    @Column(name = "grabador")
    private String grabador;
    @Column(name = "comprob")
    private String comprob;
    @Column(name = "fecha_grab")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGrab;
    @Column(name = "ind_proc")
    private Integer indProc;
    @Column(name = "cierre")
    private Integer cierre;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne
    private CajerosAero usuarios;
    @Column(name = "estado_id")
    private Integer estado;
    @JoinColumn(name = "sitios_id", referencedColumnName = "id")
    @ManyToOne
    private Sitios sitio;

    public Sitios getSitio() {
        return sitio;
    }

    public void setSitio(Sitios sitio) {
        this.sitio = sitio;
    }

    public String getPorque() {
        return porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Integer getCierre() {
        return cierre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setCierre(Integer cierre) {
        this.cierre = cierre;
    }

    public ServiciosEspecial() {
    }

    public ServiciosEspecial(Integer id) {
        this.id = id;
    }

    public ServiciosEspecial(Integer id, String addres, Date fecha, Integer estado) {
        this.id = id;
        this.addres = addres;
        this.fecha = fecha;
        this.usuarios = usuarios;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CajerosAero getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(CajerosAero usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getAutoPlaca() {
        return autoPlaca;
    }

    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getGrabador() {
        return grabador;
    }

    public void setGrabador(String grabador) {
        this.grabador = grabador;
    }

    public String getComprob() {
        return comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    public Date getFechaGrab() {
        return fechaGrab;
    }

    public void setFechaGrab(Date fechaGrab) {
        this.fechaGrab = fechaGrab;
    }

    public Integer getIndProc() {
        return indProc;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setIndProc(Integer indProc) {
        this.indProc = indProc;
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
        if (!(object instanceof ServiciosEspecial)) {
            return false;
        }
        ServiciosEspecial other = (ServiciosEspecial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rta.aeropuerto.domain.ServiciosEspecial[ id=" + id + " ]";
    }
}
