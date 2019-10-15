/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.activate;

import com.rta.opain.delegate.dto.Cierre;
import com.rta.opain.delegate.dto.GenericDTO;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.domain.Bloqueo;
import com.rta.opain.domain.CajerosAero;
import com.rta.opain.domain.FuecService;
import com.rta.opain.domain.Lugares;
import com.rta.opain.domain.RegisterInOut;
import com.rta.opain.domain.Servicios;
import com.rta.opain.domain.ServiciosEspecial;
import com.rta.opain.domain.Sitios;
import com.rta.opain.domain.SrvPreview;
import com.rta.opain.domain.TempVehiculo;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.Remote;

/**
 *
 * @author Paola
 */
@Remote
public interface PrinterServicesActivateRemote {

    public List<Servicios> ultServiciosNoCargados(String sql) throws Exception;

    public List<Servicios> serviciosPorUsuarioToCierre(String usuario) throws Exception;

    public Cierre savarCierre(Cierre cierre) throws RuntimeException;

    public void loadDataBase(String file) throws Exception;

    public JsonDTO lstBloqueos(String placa) throws Exception;

    public String[] cantidadFacturasEnFecha(String fecha) throws Exception;

    public JsonDTO buscarResponsable(String placa) throws Exception;

    public List<String> realizarCierreAutomatico() throws Exception;

    public SrvPreview crearSrvPreview(SrvPreview servicio) throws Exception;

    public boolean cancelarSrvPreview(String cedula) throws Exception;

    public List<SrvPreview> listarSrvPreview(String idSitio) throws Exception;

    public Bloqueo crearBloqueoTaxi(Bloqueo servicio) throws Exception;

    public List<Bloqueo> lstBloqueos() throws Exception;

    public List<TempVehiculo>  misCarros() throws Exception;
    
    public List<JsonDTO>  misCarrosVANGO() throws Exception;
    
    public List<Servicios> nuevoServicios(String fecha) throws Exception;

    public Servicios saveServicio(Servicios servicio) throws Exception;

    public List<String> comprobXFecha(String fecha) throws Exception;

    public List<String> comprobEnFecha(String fecha, String fecha2) throws Exception;

    public RtaDTO crearCintaTestigo(String fecha, String fecha2) throws Exception;

    public List<Object> serviciosByUserByFechas(String grabador, String fecha, String fecha2) throws Exception;

    public Servicios lastSrvByNumeroComprob(Integer numero, String comprob) throws Exception;

    public Ticket optenerPropieta(String placa) throws Exception;

    public Long optenerPagdor(String placa) throws Exception;

    public Ticket consultaModipay(String idTrans) throws Exception;

    public Servicios validaModipay(String idTrans) throws Exception;

    public Ticket optenerConductor(String placa, Ticket tk) throws Exception;

    public TempVehiculo containerPlaca(String placa) throws Exception;

    public Bloqueo bloqueoByPlaca(String placa) throws Exception;
    
    public RtaDTO documentosVencidos(String placa) throws Exception;
    
    public boolean empresaGrupo(String placa)throws Exception;

    public TempVehiculo save(TempVehiculo tempCar) throws Exception;

    public String[] cargarVehiculoFromCajas(String placa) throws Exception;

    public Integer generateId(String columna, String entity) throws Exception;

    public CajerosAero login(String usuario, String clave) throws Exception;

    public Servicios crearServicio(Servicios servicio) throws Exception;

    public Servicios crearServicioRespaldo(Servicios servicio) throws Exception;

    public FuecService crearServicioFuec(FuecService servicio) throws Exception;

    public Servicios updateSrvContable(String comprob, Integer numero) throws Exception;

    public ServiciosEspecial crearServicioAvianca(ServiciosEspecial servicio) throws Exception;

    public RtaDTO lRtaDTO(String placa) throws Exception;

    public Integer generarTikect(String placa) throws Exception;

    public List<Servicios> updateSQLPendientesVPS(String sql, String fecha) throws Exception;

    public List<Servicios> selectPendientesVPS(String sql, String fecha) throws Exception;

    public void lRegistrosJK(Integer codigo) throws Exception;

    public RtaDTO allVehicule() throws Exception;

    public List<String> allCar(String placa) throws Exception;

    public String[] sincronizarPagos(String placa) throws Exception;

    public boolean updatePlaca(Servicios serv) throws Exception;

    public Servicios lastServicioByCajero(CajerosAero usuario) throws Exception;

    public boolean logout(Integer movil) throws Exception;

    public Object[] cerrarServicios(CajerosAero usuario, boolean control, Properties pr);

    public Object[] cerrarServicios2(CajerosAero usuario, boolean control, Properties pr);

    public Servicios anulacion(Long numero, String porque, Sitios sitio, Date fecha) throws Exception;

    public Servicios _servicio(Long numero, String sitio) throws Exception;

    public List<Servicios> lServicioWithOutNit() throws Exception;

    public RegisterInOut insertLogs(RegisterInOut logs) throws Exception;

    public List<RegisterInOut> ultimoLogs() throws Exception;

    public Integer getCmprobanteServicio(Servicios srv);

    public List<Sitios> lSitios() throws Exception;

    public Sitios sitioById(Integer sitio) throws Exception;

    public Boolean updateSrv(Servicios serv) throws Exception;

    public Integer getNextId(Servicios servicio);

    public List<Servicios> ultServiciosPendientes(String comprob) throws Exception;

    public List<Servicios> srvEnFechas(String fecha1, String fecha2) throws Exception;

    public List<Servicios> srvNoCargados(String sql) throws Exception;

    public List<Servicios> revisarServiciosByComprob(String fecha, String comprob) throws Exception;

    public List<TempVehiculo> lstCarros() throws Exception;

    public void changeStateCar(String estado) throws Exception;

    public List<JsonDTO> lstContratos() throws Exception;

    public CajerosAero changePass(CajerosAero usuario);

    public List<Lugares> listLugares(String idSitio);

    public JsonDTO generarFotoContable(String serverName) throws Exception;

    public CajerosAero usuarioById(Integer idUsusrio) throws Exception;
}
