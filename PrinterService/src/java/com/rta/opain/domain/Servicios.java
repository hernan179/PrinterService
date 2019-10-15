package com.rta.opain.domain;

import com.rta.opain.domain.CajerosAero;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hsancheza
 */
@Entity
@Table(name = "srv_camioneta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")
    ,
    @NamedQuery(name = "Servicios.findById", query = "SELECT s FROM Servicios s WHERE s.id = :id")
    ,
    @NamedQuery(name = "Servicios.findByAddres", query = "SELECT s FROM Servicios s WHERE s.addres = :addres")
    ,
    @NamedQuery(name = "Servicios.findByFecha", query = "SELECT s FROM Servicios s WHERE s.fecha = :fecha")
    ,
    //@NamedQuery(name = "Servicios.findByUsuariosId", query = "SELECT s FROM Servicios s WHERE s.usuarios = :usuarios"),
    @NamedQuery(name = "Servicios.findByValor", query = "SELECT s FROM Servicios s WHERE s.valor = :valor")
    ,
    @NamedQuery(name = "Servicios.findByAutoPlaca", query = "SELECT s FROM Servicios s WHERE s.autoPlaca = :autoPlaca")
    ,
    @NamedQuery(name = "Servicios.findByTipoPago", query = "SELECT s FROM Servicios s WHERE s.tipoPago = :tipoPago")
    ,
    @NamedQuery(name = "Servicios.findByNit", query = "SELECT s FROM Servicios s WHERE s.nit = :nit")
    ,
    @NamedQuery(name = "Servicios.findByGrabador", query = "SELECT s FROM Servicios s WHERE s.grabador = :grabador")
    ,
    @NamedQuery(name = "Servicios.findByMaxValue", query = "SELECT s FROM Servicios s WHERE s.numero = (SELECT MAX(s2.numero) FROM Servicios s2)")
    ,
    @NamedQuery(name = "Servicios.findByComprob", query = "SELECT s FROM Servicios s WHERE s.comprob = :comprob")
    ,
    @NamedQuery(name = "Servicios.findByFechaGrab", query = "SELECT s FROM Servicios s WHERE s.fechaGrab = :fechaGrab")
    ,
    @NamedQuery(name = "Servicios.findByIndProc", query = "SELECT s FROM Servicios s WHERE s.indProc = :indProc")
    ,
    @NamedQuery(name = "Servicios.findByIndProc2", query = "SELECT s FROM Servicios s WHERE s.numero  = :numero AND s.sitio = :sitio")})

public class Servicios implements Serializable {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Integer valor;
    @Column(name = "valord")
    private Integer valorD;
    @Column(name = "valorc")
    private Integer valorC;
    @Column(name = "valore")
    private Integer valorE;
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

    @Basic(optional = false)
    @Column(name = "json")
    private String json;

    @Column(name = "transaccion")
    private String transaccion;
    @Column(name = "base")
    private Integer base;

    //@Column(name = "diff")
    // private Integer diff;
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
    @JoinColumn(name = "sitios_id", referencedColumnName = "id")
    @ManyToOne
    private Sitios sitio;
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne
    private Estados estado;

    @Column(name = "nitot")
    private Integer nitot;
    @Column(name = "valoro")
    private Integer valoro;

    public Integer getNitot() {
        return nitot;
    }

    public void setNitot(Integer nitot) {
        this.nitot = nitot;
    }

    public Integer getValoro() {
        return valoro;
    }

    public void setValoro(Integer valoro) {
        this.valoro = valoro;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

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

    public Integer getValorE() {
        return valorE;
    }

    public void setValorE(Integer valorE) {
        this.valorE = valorE;
    }

    public Integer getValorD() {
        return valorD;
    }

    public void setValorD(Integer valorD) {
        this.valorD = valorD;
    }

    public Integer getValorC() {
        return valorC;
    }

    public void setValorC(Integer valorC) {
        this.valorC = valorC;
    }

    public Integer getCierre() {
        return cierre;
    }

    public void setCierre(Integer cierre) {
        this.cierre = cierre;
    }

    public Servicios() {
    }

    public Servicios(Integer id) {
        this.id = id;
    }

    public Servicios(Integer id, String addres, Date fecha, Estados estado) {
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
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "com.rta.aeropuerto.domain.Servicios[ id=" + id + " ]";
    }
}
