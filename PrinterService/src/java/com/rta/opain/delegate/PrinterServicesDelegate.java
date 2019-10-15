/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate;

import com.google.gson.Gson;
import com.rta.opain.activate.PrinterServicesActivateRemote;
import com.rta.opain.contab.CrontabEJB;
import com.rta.opain.delegate.dto.GenericDTO;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.delegate.tools.DateHelper;
import com.rta.opain.domain.*;
import java.io.BufferedReader;
import java.io.FileReader;
import org.jboss.logging.Logger;
import static com.rta.opain.delegate.tools.DateHelper.calcularDiferenciaEntreFechas;
import static com.rta.opain.delegate.tools.StringTools.getStringTrim;
import java.io.DataOutputStream;
import java.net.Socket;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.delegate.tools.Utilidades;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import static com.rta.opain.delegate.tools.Utilidades.validaArray;
import static com.rta.opain.delegate.tools.Utilidades.getNumberLong;
import static com.rta.opain.delegate.tools.Utilidades.getNumberInt;
import static com.rta.opain.delegate.tools.DateHelper.restartMinutos;
import com.rta.opain.delegate.tools.SendMail;
import static com.rta.opain.delegate.tools.TreeSetHelper.getRegistrer;
import com.rta.opain.ws.conn.WebserviceConnection;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 *
 * @author hsancheza
 */
@Stateless
public class PrinterServicesDelegate implements PrinterServicesDelegateRemote {

    private static Logger log = Logger.getLogger(PrinterServicesDelegate.class);
    @EJB
    PrinterServicesActivateRemote service;
    @Resource
    TimerService timerService;
    static String jsonDTO = "";
    //String SERVER_ADDRESS = "181.48.120.58";
    //  String SERVER_ADDRESS = "192.168.23.135";
    Integer TCP_SERVER_PORT = 1234;
    static List<RegisterInOut> lLogs;
    static List<Servicios> srvMail = new ArrayList<Servicios>();

    @Override
    public ServiciosEspecial crearServicioAvianca(ServiciosEspecial servicio) throws Exception {
        try {
            return service.crearServicioAvianca(servicio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RtaDTO crearCintaTestigo(String fecha, String fecha2) throws Exception {
        return service.crearCintaTestigo(fecha, fecha2);
    }

    @Override
    public boolean updateSrv(Servicios servicio) throws Exception {
        try {
            return service.updateSrv(servicio);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> allCar(String placa) throws Exception {
        return service.allCar(placa);
    }

    @Override
    public RtaDTO allVehicule() throws Exception {
        return service.allVehicule();
    }

    @Override
    public List<Servicios> updateSQLPendientesVPS(String sql, String fecha) throws Exception {

        return service.updateSQLPendientesVPS(sql, fecha);
    }

    @Override
    public List<Servicios> selectPendientesVPS(String sql, String fecha) throws Exception {
        return service.selectPendientesVPS(sql, fecha);
    }

    @Override
    public List<Servicios> ultServiciosNoCargados(String sql) throws Exception {
        return service.ultServiciosNoCargados(sql);
    }

    @Override
    public List<Servicios> nuevoServicios(String fecha) throws Exception {
        return service.nuevoServicios(fecha);
    }

    @Override
    public List<String> realizarCierreAutomatico() throws Exception {
        return service.realizarCierreAutomatico();
    }

    @Override
    public Ticket consultaModipay(String idTrans) throws Exception {
        return service.consultaModipay(idTrans);
    }

    @Override
    public Servicios validaModipay(String idTrans) throws Exception {
        return service.validaModipay(idTrans);
    }

    public RtaDTO documentosVencidos(String placa) throws Exception {
        return service.documentosVencidos(placa);
    }

    @Override
    public RtaDTO lRtaDTO(String placa, Date fechaActual, boolean barcode) throws Exception {
        try {// +90, BLQOUADO POR MORA Y NO GENERA IMPRESION
            //  < 90, LOCAR LOS DIAS EN  45 MORA 

            RtaDTO tt = service.lRtaDTO(placa);

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();

            if (tt != null && tt.getAlertas() != null) {
                tt.setError("ok");
                int i = 0;
                for (JsonDTO dias : tt.getAlertas()) {
                    Bloqueo bloqueo = null;
                    if (!barcode) {
                        bloqueo = service.bloqueoByPlaca(dias.getPlaca());
                    }

                    // verificar si tiene coducmentos al dia
                    //documentosVencidos(placa);
                    if (bloqueo != null) {
                        dias.setError("no");
                        tt.setError("no");
                        dias.setMsjError("BLOQUEADO POR SAC");
                        dias.setImprimeTiquete("false");
                        lMorosos.add(dias);
                        tt.setAlertas(lMorosos);
                        //  return tt;
                    } else {
                        Date fecha = DateHelper.toDateYYYYMMDDHHMM2(dias.getFecha());
                        dias.setPlaca(getStringTrim(dias.getPlaca()));
                        if (fecha.before(new Date())) {
                            Integer DIAS = calcularDiferenciaEntreFechas(fecha, fechaActual);
                            if (DIAS > 0) {
                                dias.setDias(DIAS);
                                if (DIAS >= 35) {
                                    dias.setError("no");
                                    tt.setError("no");
                                    dias.setMsjError("BLOQUEADO");
                                    dias.setImprimeTiquete("false");
                                } else if (DIAS >= 30 && dias.getEmpresaId() != null && (dias.getEmpresaId().equals("98") || dias.getEmpresaId().equals("10028"))) {
                                    dias.setError("no");
                                    tt.setError("no");
                                    dias.setMsjError("BLOQUEADO");
                                    dias.setImprimeTiquete("false");
                                } else {
                                    dias.setImprimeTiquete("true");
                                    dias.setError("ok");
                                    dias.setMsjError("MORA " + DIAS + " DIAS");
                                }
                            } else {
                                dias.setDias(0);
                                dias.setError("ok");
                                dias.setMsjError("HABILITADO");
                                dias.setImprimeTiquete("true");
                            }
                        } else {
                            dias.setDias(0);
                            dias.setError("ok");
                            dias.setMsjError("HABILITADO");
                            dias.setImprimeTiquete("true");
                        }

                        Integer countXMinuto = service.generarTikect(dias.getContrato());
                        if (countXMinuto > 2) {
                            dias.setError("no");
                            tt.setError("no");
                            dias.setMsjError("HAY DUPLICADOS");
                            dias.setImprimeTiquete("false");

                        }
                    }
                    // verificar docs vencidos

                    // validar si es del grupo, de lo contrario no validar
                    boolean empresaGrupo = service.empresaGrupo(dias.getPlaca());
                    if (empresaGrupo) {
                        RtaDTO docs = service.documentosVencidos(dias.getPlaca());
                        if (docs != null && docs.getError() != null && docs.getError().equals("no")) {
                            rw("vencidos____" + docs.getMsjError());
                            dias.setError("ok");
                            tt.setError("ok");
                            dias.setMsjError(docs.getMsjError());
                            dias.setImprimeTiquete("true");
                            lMorosos.add(dias);
                            tt.setAlertas(lMorosos);
                        } else {
                            rw("_____sss__no se valida tarjeton");
                        }
                    }

                    lMorosos.add(dias);

                    i++;
                }
            }
            tt.setAlertas(lMorosos);
            rw("RTA_" + new Gson().toJson(tt));
            return tt;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void lRegistrosJK(Integer codigo) throws Exception {
        service.lRegistrosJK(codigo);
    }

    @Override
    public void makeTask(String topic, String contenido) throws Exception {
        Long idTimer = System.currentTimeMillis();
        JsonDTO obj = new JsonDTO();
        obj.setId(idTimer.toString());
        obj.setDetalle(contenido);
        obj.setConvenio(topic);
        timerService.createTimer(2000L, obj);
    }

    @Override
    public Sitios sitioById(Integer sitio) throws Exception {

        try {
            return service.sitioById(sitio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Sitios> lSitios() throws Exception {

        try {
            List<Sitios> test = new ArrayList<Sitios>();

            List<Sitios> lSitios = service.lSitios();

            System.out.println("total de sitios:  " + lSitios.size());

            return lSitios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer sincronizarPagos() throws Exception {
        try {
            final String dir = System.getProperty("user.dir");
            List<Servicios> lSrv = service.lServicioWithOutNit();
            Integer valueLoad = 0;

            for (Servicios srv : lSrv) {
                String s;
                BufferedReader br = new BufferedReader(new FileReader("/opt/compilados/vehiculos.csv"));
                while ((s = br.readLine()) != null) {
                    String[] myNits = new String[7];
                    String[] datos = s.split("\\;");
                    if (datos != null && datos.length > 1) {

                        if (datos[2].toUpperCase().equals(srv.getAutoPlaca().toUpperCase())) {
                            rw("idSrv: " + srv.getNumero() + " placa: " + srv.getAutoPlaca() + "   File palca " + validaArray(datos, 2) + "  nit: " + validaArray(datos, 17));
                            myNits = datos;
                            //return myNits;
                            srv.setNit(getNumberLong(validaArray(datos, 17)));
                            boolean isOk = service.updatePlaca(srv);
                            if (isOk) {
                                valueLoad++;
                            }

                        }
                    }

                }
                br.close();
            }

            rw("Es necesario actualizar " + valueLoad + " pagos en aeropuerto");
            return valueLoad;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public void containerPlaca(String placa, String json, Integer tipo) throws Exception {
        try {
            TempVehiculo rta = service.containerPlaca(placa);

            if (rta == null) {
                rta = new TempVehiculo();
                Integer ids = service.generateId("id", TempVehiculo.class.getSimpleName());
                rw("myIds " + ids);
                rta.setId(ids);
                rta.setAutoPlaca(placa);
            }
            rta.setFecha(new Date());
            rta.setJson(json);
            rta.setIdTipoVehiculo(2);

            //rta.setIdEstado(1);
            // Ticket tk = new Gson().fromJson(rta.getJson(), Ticket.class);
            service.save(rta);
            rw("Container actuliazado");
        } catch (Exception e) {
            rw("No se actualiza el container");
        }
        //return rta;
    }

    @Override
    public String buscarPlacaIntoContainer(String placa) throws Exception {

        try {
            TempVehiculo temp = service.containerPlaca(placa);
            // rw("jsonDB: " + temp.getJson());
            return temp.getJson();
        } catch (Exception e) {
            //e.printStackTrace();
            rw("No existe el vebiculo en el container(temp_vehiculo) " + placa);
            return null;
        }
    }

    @Override
    public Ticket buscarPlaca(String placa) throws Exception {
        Ticket tiquete = new Ticket();
        try {
            Long nitPagador = service.optenerPagdor(placa);
            Ticket propietario = service.optenerPropieta(placa);

            Ticket conductor = service.optenerConductor(placa, propietario);
            rw("nitPagador_" + nitPagador + "_conductor.getError()_" + conductor.getError());
            if (nitPagador > 0 && conductor.getError().equals("ok") && propietario.getError().equals("ok")) {

                tiquete.setEmpresa(propietario.getEmpresa());

                tiquete.setPlaca(propietario.getPlaca());
                tiquete.setModelo(propietario.getModelo());
                tiquete.setMarca(propietario.getMarca());
                tiquete.setNumeroInterno(propietario.getNumeroInterno());
                tiquete.setClase(propietario.getClase());
                tiquete.setNit(nitPagador.toString());

                tiquete.setNitPagador(nitPagador.toString());
                tiquete.setNitPropieta(propietario.getNit());
                tiquete.setEmpresaVehi(propietario.getEmpresaVehi());

                tiquete.setEmpresaVehi(conductor.getEmpresaVehi());

                tiquete.setPasajeros(conductor.getPasajeros());
                tiquete.setPasajero(conductor.getPasajero());

                tiquete.setEmpresaVehiculo(conductor.getEmpresaVehi());

                tiquete.setNombreConductor1(conductor.getNombreConductor1());
                tiquete.setApellidoConductor1(conductor.getApellidoConductor1());
                tiquete.setCcConductor1(conductor.getCcConductor1());
                tiquete.setLicenciaConductor1(conductor.getLicenciaConductor1());
                tiquete.setNitConductor(conductor.getNitConductor());
                tiquete.setVigenciaConductor1(conductor.getVigenciaConductor1());
                tiquete.setAutorizado(conductor.getEmpresa());

                tiquete.setPersonas(conductor.getPersonas());

                tiquete.setError("ok");
                tiquete.setNumeroTarjetaOpe(conductor.getNumeroTarjetaOpe());

                tiquete.setDireccionResponsable(propietario.getDireccionResponsable());
                tiquete.setTelResponsable(propietario.getTelResponsable());
                tiquete.setNombreResponsable(propietario.getNombreResponsable());
                tiquete.setApellidoResponsable(propietario.getApellidoResponsable());
                tiquete.setCcResponsable(propietario.getCcResponsable());

                if (conductor.getNombreConductor2() != null) {
                    tiquete.setNombreConductor2(conductor.getNombreConductor2());
                    tiquete.setApellidoConductor2(conductor.getApellidoConductor2());
                    tiquete.setCcConductor2(conductor.getCcConductor2());
                    tiquete.setLicenciaConductor2(conductor.getLicenciaConductor2());
                    tiquete.setNitConductor2(conductor.getNitConductor2());
                    tiquete.setVigenciaConductor2(conductor.getVigenciaConductor2());
                }

                if (conductor.getNombreConductor3() != null) {
                    tiquete.setNombreConductor3(conductor.getNombreConductor3());
                    tiquete.setApellidoConductor3(conductor.getApellidoConductor3());
                    tiquete.setCcConductor3(conductor.getCcConductor3());
                    tiquete.setLicenciaConductor3(conductor.getLicenciaConductor3());
                    tiquete.setNitConductor3(conductor.getNitConductor3());
                    tiquete.setVigenciaConductor3(conductor.getVigenciaConductor3());
                }

            } else {
                rw("Placa no disponible para registrar en containaer..");
                tiquete.setError("no");

                String placaNoUpdate = "Placa:" + placa;
                placaNoUpdate += ", no cargada ";

                Utilidades.writeToFile(placa);
                // new SendMail().sendMesageContainer("VEHICULO SIN NIT-PAGADOR", placaNoUpdate);

            }
            return (tiquete);
        } catch (Exception e) {
            tiquete.setError("no");
            rw("Error al cargalos los propietarios/conductores " + e.getMessage());
            return (tiquete);
        }

    }

    static String[] cargarNits2(String placa) throws Exception {
        String[] myNits = new String[7];
        String columnas = "CONVENIO;NIT;PLACA;MODELO;MARCA;CLASE;NUMERO INTERNO;TARJETA OPERACION;CONDUCTOR 1;CDULA;LICENCIA CONDUCCION;VIGENCIA;CONDUCTOR 2;CEDULA;LICENCIA CONDUCCIàN;VIGENCIA;RESPONSABLE DEL CONTRATANTE;CEDULA ;TELEFONO;DIRECCION";
        String valuesColum[] = columnas.split("\\;");

        Integer nombreConductor1 = 0;
        Integer apellidoConductor1 = 0;
        Integer ccConductor1 = 0;
        Integer licenciaConductor1 = 0;
        Integer ligenciaConductor1 = 0;

        try {
            final String dir = System.getProperty("user.dir");
            FileReader fr = new FileReader(dir + "/vehiculos.csv");
            BufferedReader br = new BufferedReader(fr);
            String s;
            int contador = 1;
            while ((s = br.readLine()) != null) {
                String[] datos = s.split("\\;");
                if (datos != null && datos.length > 1 && contador > 1) {

                    if (datos[2].toUpperCase().equals(placa.toUpperCase())) {
                        System.out.println("FOUND IT:   " + datos.length + " =>  " + s);
                        myNits = datos;
                        return myNits;
                    }
                }
                contador++;
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return myNits;
    }

    @Override
    public List<String> comprobXFecha(String fecha) throws Exception {
        return service.comprobXFecha(fecha);
    }

    @Override
    public List<Servicios> revisarServiciosByComprob(String fecha, String comprob) throws Exception {
        try {
            return service.revisarServiciosByComprob(fecha, comprob);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Servicios> srvNoCargados(String sql) throws Exception {
        try {
            return service.srvNoCargados(sql);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TempVehiculo> lstCarros() throws Exception {
        try {
            return service.lstCarros();
        } catch (Exception e) {
            return null;
        }
    }

    //
    @Override
    public int changeStateCar(String[] idsCars, String nuevoEstado) throws Exception {
        int contadorBq = 1;
        try {
            String param = "";

            for (String ids : idsCars) {
                rw("idBloquedo: " + ids);

                if (contadorBq < idsCars.length) {
                    param += ids + ",";
                } else {
                    param += ids;
                }
                contadorBq++;
            }
            String sql = "update temp_vehiculos set id_estado = " + nuevoEstado + " where id_temp_vehiculo in( " + param + ");";
            rw("sqlUpdate: " + sql);
            service.changeStateCar(sql);
        } catch (Exception e) {

        }
        return contadorBq - 1;
    }

    @Override
    public List<JsonDTO> lstContratos() throws Exception {
        try {
            return service.lstContratos();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Bloqueo> lstBloqueos() throws Exception {
        try {
            return service.lstBloqueos();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TempVehiculo> misCarros() throws Exception {
        return service.misCarros();
    }

    public List<JsonDTO> misCarrosVANGO() throws Exception {
        return service.misCarrosVANGO();
    }

    @Override
    public String findPlaca(String placa) throws Exception {
        String nits = null;
        /*try {
            
         log.info("aero: buscando el nits placa: "+placa);
         DatosRadiolocalizador OBJ = obtenerMovilConPlaca(placa);
         if (OBJ != null && OBJ.getDocPropietario() != null && !OBJ.getDocConductor().isEmpty()) {
         nits = OBJ.getDocPropietario();
         }
         log.info("aero: 1. placa: "+placa+"  nits: "+OBJ.getDocPropietario());
         } catch (RuntimeException e) {
         log.info("aero: no hya movil con placa "+e.getMessage());
         e.printStackTrace();
         }*/

        if (nits == null) {
            try {
                FileReader fr = new FileReader("/tmp/nit_placa.txt");
                BufferedReader br = new BufferedReader(fr);
                String s;

                while ((s = br.readLine()) != null) {
                    String[] datos = s.split("\\;");
                    if (datos != null && datos.length > 1) {
                        log.info("Placa: " + datos[0] + "Nit:" + datos[1]);
                        if (placa.toUpperCase().equals(datos[0].toUpperCase())) {
                            nits = datos[1];
                        }
                    }
                }
                fr.close();
                return nits;
            } catch (Exception e) {
                e.printStackTrace();
                return nits;
            }
        }
        return nits;
    }

    @Override
    public List<Servicios> ultServiciosPendientes(String comprob) throws Exception {
        return service.ultServiciosPendientes(comprob);
    }

    @Override
    public List<Servicios> srvEnFechas(String fecha1, String fecha2) throws Exception {
        return service.srvEnFechas(fecha1, fecha2);
    }

    public FuecService crearServicioFuec(FuecService servicio) throws Exception {
        try {
            if (servicio.getId() == null) {
                servicio.setId(service.generateId("id", FuecService.class.getSimpleName()));
            }
            return service.crearServicioFuec(servicio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Servicios crearServicioRespaldo(Servicios servicio) throws Exception {
        try {
            return service.crearServicioRespaldo(servicio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Servicios crearServicio(Servicios servicio) throws Exception {

        Integer id = service.getNextId(servicio);
        servicio.setNumero(service.getCmprobanteServicio(servicio));
        servicio.setEstado(new Estados(27));
        servicio.setId(id);
        servicio.setAutoPlaca(servicio.getAutoPlaca().toUpperCase());
        if (servicio.getComprob().equals("VES")) {
            servicio.setTipoPago("BALOTO");
        } else {
            servicio.setTipoPago("EFECTIVO");
        }
        try {
            servicio = service.crearServicio(servicio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return servicio;
    }

    @Override
    public Servicios updateSrvContable(String comprob, Integer numero) throws Exception {

        try {
            return service.updateSrvContable(comprob, numero);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Servicios saveServicio(Servicios servicio) throws Exception {

        try {
            return service.saveServicio(servicio);
        } catch (Exception e) {
            // e.printStackTrace();
            // rw("No se carga  " + servicio.getNumero() + "  " + servicio.getComprob());
            return null;
        }

    }

    @Override
    public boolean logout(Integer movil) throws Exception {

        return true;
    }

    @Override
    public CajerosAero login(String usuario, String clave) {
        try {

            CajerosAero cajero = service.login(usuario, clave);
            return cajero;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public CajerosAero changePass(CajerosAero usuario) {
        try {
            service.changePass(usuario);
            return usuario;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object[] cerrarServicios(CajerosAero usuario, boolean control, Properties pr) {
        try {
            Object[] obj = service.cerrarServicios2(usuario, control, pr);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Servicios anulacion(Long numero, String porque, Sitios stio, Date fecha) throws Exception {
        Servicios serv = null;
        try {
            serv = service.anulacion(numero, porque, stio, fecha);

            if (serv != null) {
                // srvMail.add(serv);
                //timerService.createTimer(5000l, serv.getNumero());
                rw("Timer creado para enviar despues el email");

                new SendMail().sendMesageAnulacion(serv.getUsuarios().getNombre(), serv.getPorque(), serv.getNumero().toString(), serv.getValor().toString(), serv.getComprob());
            }
            return serv;
        } catch (Exception e) {
            return serv;
        }
    }

    /*@Timeout
    public void enviarEmail(Timer timer) {
        try {
            JsonDTO smsDTO = (JsonDTO) timer.getInfo();
            rw("Se vencio el timer...." + timer.getInfo());
            new SendMail().sendMesageContainer(smsDTO.getConvenio(), smsDTO.getDetalle());
            timer.cancel();
        } catch (Exception e) {
            rw("No fue posible procesar el timer...envir x default " + e.getMessage() + " causa: " + e.getCause());
            timer.cancel();
        }

    }*/
    @Override
    public void programarEnvioSrvRespaldo(Servicios servicio) {
        rw("programar envio srv respaldo");

        timerService.createTimer(2000L, servicio.getJson());
    }

    @Timeout
    public void enviarSrvRespaldo(Timer timer) {
        try {
            for (Object o : timerService.getTimers()) {
                Timer t = (Timer) o;
                if (t.equals(timer)) {
                    //  JsonDTO servicio = (JsonDTO) timer.getInfo();

                    rw("Se vencio el timer...." + timer.getInfo().toString());
                    String jsonBK = timer.getInfo().toString();
                    rw("servicio_" + jsonBK);
                    // /String rtaBK = new WebserviceConnection().accesPpintCopiaRespaldo(jsonBK);
                    timer.cancel();
                }
            }
        } catch (Exception e) {
            rw("No fue posible procesar el timer...envir x default " + e.getMessage() + " causa: " + e.getCause());
            timer.cancel();
        }

    }

    @Override
    public Servicios lastSrvByNumeroComprob(Integer numero, String comprob) throws Exception {
        try {
            return service.lastSrvByNumeroComprob(numero, comprob);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Servicios lastServicioByCajero(CajerosAero usuario) throws Exception {

        try {
            Servicios srv = service.lastServicioByCajero(usuario);
            log.info("ULTIMO SERVICIO CREADO " + srv.getNumero() + " USUARIO: " + srv.getUsuarios().getNombre());
            return srv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bloqueo crearBloqueoTaxi(Bloqueo servicio) throws Exception {
        try {
            return service.crearBloqueoTaxi(servicio);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SrvPreview crearSrvPreview(SrvPreview servicio) throws Exception {
        try {
            return service.crearSrvPreview(servicio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean cancelarSrvPreview(String cedula) throws Exception {
        return service.cancelarSrvPreview(cedula);

    }

    @Override
    public List<SrvPreview> listarSrvPreview(String idSitio) throws Exception {
        return service.listarSrvPreview(idSitio);
    }

    @Override
    public Servicios _servicio(Long numero, String sitio) throws Exception {
        try {
            return service._servicio(numero, sitio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RegisterInOut> ultimoLogs() throws Exception {
        try {
            return service.ultimoLogs();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RegisterInOut insertLogs(RegisterInOut logs, String jsonRTA, Integer dias) throws Exception {
        try {
            if (lLogs == null) {
                lLogs = new ArrayList<RegisterInOut>();
            }

            logs.setIdRegisterInOut(service.generateId("idRegisterInOut", RegisterInOut.class.getSimpleName()));
            //  Timer timer = timerService.createTimer(1000, "Created new programmatic timer");

            logs.setDias(dias);
            jsonDTO = "[" + jsonRTA + "]";

            rw(
                    "jsonDTO:=           " + jsonRTA);

            RegisterInOut test = service.insertLogs(logs);

            lLogs.add(logs);
            return logs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonDTO generarFotoContable(String serverName) throws Exception {
        try {
            return service.generarFotoContable(serverName);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CajerosAero usuarioById(Integer idUsusrio) throws Exception {
        try {
            return service.usuarioById(idUsusrio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Lugares> listLugares(String idSitio
    ) {
        try {
            return service.listLugares(idSitio);
        } catch (Exception e) {
            return null;
        }
    }

    // @Timeout
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public void reportActivity(String jsonDTO
    ) {
        rw("numCien:=======REPORTANDO ACITIVIDAD DEL MOVIL ===========");
        rw("numCien:=====jsonDTO ======= " + jsonDTO);
        String[] config = new Utilidades().loadConfig();

        try {

            String esteJson = jsonDTO;
            rw("SEND TO SOCKET:   " + (esteJson));
            String modifiedSentence;
            SocketAddress sockaddr = new InetSocketAddress(config[0], getNumberInt(config[1]));
            Socket socket = new Socket();

            socket.connect(sockaddr, 1000);

            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToServer.writeBytes(esteJson + '\n');
            modifiedSentence = inFromServer.readLine();
            socket.close();
            jsonDTO = "";
            rw(" ============MENSAJE ENVIADO!!! ======HOST " + config[0] + "== PORT =" + config[1] + "==================");
        } catch (Exception e) {

            rw("=====SOCKET FUERA DE LINEA ================");
        }

    }

}
