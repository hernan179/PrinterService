/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate;

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
import java.util.Set;
import javax.ejb.Remote;
import javax.ejb.Timer;

/**
 *
 * @author hsancheza
 */
@Remote
public interface PrinterServicesDelegateRemote {

    public String buscarPlacaIntoContainer(String placa) throws Exception;

    public Servicios lastSrvByNumeroComprob(Integer numero, String comprob) throws Exception;

    public Servicios crearServicioRespaldo(Servicios servicio) throws Exception;

    public FuecService crearServicioFuec(FuecService servicio) throws Exception;

    public String findPlaca(String placa) throws Exception;

    public List<TempVehiculo> misCarros() throws Exception;

    public List<JsonDTO> misCarrosVANGO() throws Exception;

    public RtaDTO crearCintaTestigo(String fecha, String fecha2) throws Exception;

    public List<JsonDTO> lstContratos() throws Exception;

    public List<Bloqueo> lstBloqueos() throws Exception;

    public void programarEnvioSrvRespaldo(Servicios servicio);

    public int changeStateCar(String[] estado, String tipoOperacion) throws Exception;

    public boolean updateSrv(Servicios servicio) throws Exception;

    public boolean cancelarSrvPreview(String placa) throws Exception;

    public List<TempVehiculo> lstCarros() throws Exception;

    public List<String> realizarCierreAutomatico() throws Exception;

    public Ticket consultaModipay(String idTrans) throws Exception;

    public Servicios validaModipay(String idTrans) throws Exception;

    public List<Servicios> srvNoCargados(String sql) throws Exception;

    public List<Servicios> revisarServiciosByComprob(String fecha, String comprob) throws Exception;

    public List<String> comprobXFecha(String fecha) throws Exception;

    public List<Sitios> lSitios() throws Exception;

    public Sitios sitioById(Integer sitio) throws Exception;

    public RtaDTO lRtaDTO(String placa, Date fechaActual, boolean barcode) throws Exception;
    
    public RtaDTO documentosVencidos(String placa) throws Exception;


    public void lRegistrosJK(Integer codigo) throws Exception;

    public RtaDTO allVehicule() throws Exception;

    public List<String> allCar(String placa) throws Exception;

    public List<Servicios> updateSQLPendientesVPS(String sql, String fecha) throws Exception;

    public List<Servicios> selectPendientesVPS(String sql, String fecha) throws Exception;

    public List<Servicios> ultServiciosNoCargados(String sql) throws Exception;

    public List<Servicios> nuevoServicios(String fecha) throws Exception;

    public void reportActivity(String jsonDTO);

    public Servicios saveServicio(Servicios servicio) throws Exception;

    public Servicios updateSrvContable(String comprob, Integer numero) throws Exception;

    public ServiciosEspecial crearServicioAvianca(ServiciosEspecial servicio) throws Exception;

    public CajerosAero login(String usuario, String clave) throws Exception;

    public Servicios lastServicioByCajero(CajerosAero usuario) throws Exception;

    public Servicios crearServicio(Servicios servicio) throws Exception;

    public List<Servicios> ultServiciosPendientes(String conprob) throws Exception;

    public Ticket buscarPlaca(String placa) throws Exception;

    public void containerPlaca(String placa, String json, Integer tipo) throws Exception;

    public List<Servicios> srvEnFechas(String fecha1, String fecha2) throws Exception;

    public Integer sincronizarPagos() throws Exception;

    public boolean logout(Integer movil) throws Exception;

    public Object[] cerrarServicios(CajerosAero usuario, boolean control, Properties pr);

    public Servicios anulacion(Long numero, String porque, Sitios sitio, Date date) throws Exception;

    public Servicios _servicio(Long numero, String sitio) throws Exception;

    public SrvPreview crearSrvPreview(SrvPreview servicio) throws Exception;

    public List<SrvPreview> listarSrvPreview(String idSitio) throws Exception;

    public Bloqueo crearBloqueoTaxi(Bloqueo servicio) throws Exception;

    public RegisterInOut insertLogs(RegisterInOut logs, String jsonDTO, Integer dias) throws Exception;

    public CajerosAero usuarioById(Integer idUsusrio) throws Exception;

    public JsonDTO generarFotoContable(String serverName) throws Exception;

    public CajerosAero changePass(CajerosAero usuario);

    public List<Lugares> listLugares(String idSitio);

    public List<RegisterInOut> ultimoLogs() throws Exception;

    public void makeTask(String topic, String contenido) throws Exception;
}
