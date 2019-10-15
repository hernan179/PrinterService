/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.node;

import com.google.gson.Gson;
import com.rta.opain.contab.CrontabEJB;
import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.delegate.dto.Cierre;
import com.rta.opain.delegate.dto.GenericDTO;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.Persona;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.delegate.tools.DateHelper;
import static com.rta.opain.delegate.tools.DateHelper.toDateYYYYMMDDHHMM;
import static com.rta.opain.delegate.tools.DateHelper.nowTimeDateEasy2;

import com.rta.opain.delegate.tools.EvaluarFaltantes;
import com.rta.opain.delegate.tools.GpsTools;
import com.rta.opain.delegate.tools.Helper;
import com.rta.opain.delegate.tools.InitHelper;
import com.rta.opain.delegate.tools.LogTest;
import static com.rta.opain.delegate.tools.LogTest.rw;
import static com.rta.opain.delegate.tools.LogTest.rwS;
import com.rta.opain.delegate.tools.SendMail;
import com.rta.opain.delegate.tools.StringTools;
import static com.rta.opain.delegate.tools.StringTools.getNumberInt;
import static com.rta.opain.delegate.tools.StringTools.getNumberLong;
import static com.rta.opain.delegate.tools.StringTools.getStringTrim;
import com.rta.opain.delegate.tools.Tarifas;
import com.rta.opain.delegate.tools.Utilidades;
import com.rta.opain.delegate.tools.isProduccion;
import com.rta.opain.domain.CajerosAero;
import com.rta.opain.domain.Estados;
import com.rta.opain.domain.Lugares;
import com.rta.opain.domain.RegisterInOut;
import com.rta.opain.domain.Servicios;
import com.rta.opain.domain.Sitios;
import com.rta.opain.domain.SrvPreview;
import com.rta.opain.domain.TempVehiculo;
import com.rta.opain.ws.conn.GeoReferenciador;
import com.rta.opain.ws.conn.WebserviceConnection;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author hsancheza
 */
@Stateless
@WebService(name = "EndPoint")
public class EndPoint implements EndPointRemote {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");//.format(new Date());
    @EJB
    PrinterServicesDelegateRemote service;
    static Integer turno = 0;

    @Override
    public void cleanup(String into, String imei) {
        try {
            new CrontabEJB().execueteCleaner(into, imei);
        } catch (Exception e) {
        }
    }

    @Override
    public void eassyPassLoadContratos() {
        long timeInit = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        rw(":. Inicio TareaProgramada.");
        try {

            String serverName = "AERO";
            JsonDTO fotoAERO = null;
            if (new isProduccion().isProduccion().equals("AERO")) {
                RtaDTO loadContratos = service.lRtaDTO(serverName, null, false);

                rw("Foto AERO ok, generando foto VPS");
                JsonDTO fotoVPS = new JsonDTO();
                fotoVPS.setKeyCommand("27");
                String jsonAux = new Gson().toJson(fotoVPS);
                String json = (new WebserviceConnection().getStatusAccountRemote93_188_165_75(jsonAux));
                fotoVPS = new Gson().fromJson(json, JsonDTO.class);
                //String val = DateHelper._3Dias(new Date(), (60 * 24 * 3));// hace 3 dias
                String val = DateHelper._3Dias();// hace 3 dias
                Integer val1 = getNumberInt(fotoVPS.getTotal()) - getNumberInt(fotoAERO.getTotal());
                if (val1 == 0) {
                    fotoAERO.setResultado("Foto conciliada sin novedad");
                } else {
                    fotoAERO.setResultado("Revisar diferencia");
                }
                fotoAERO.setDiferencia(val1.toString());
                String mensaje = SendMail.getMessageBody(fotoAERO, fotoVPS, val);
                new SendMail().sendMesageContainer("Foto Contable VPS - AERO", mensaje, null);
                time = System.currentTimeMillis() - timeInit;
            }

        } catch (Exception e) {
            time = System.currentTimeMillis() - timeInit;
        }
    }

    @Override
    @WebMethod
    public String gateWayConnection(String jsonAux) throws Exception {
        rw("JSON_IN ENCRIPTADO=>   " + jsonAux);

        String nvJson = Utilidades.Desencriptar(jsonAux);
        rw("JSON_IN DESEMCRIPTADO=> " + nvJson);

        //String MAC = "8C:DE:52:F3:24:C2"; //1.  version
        //String MAC = "00:19:5D:C2:19:0C";
        String MAC = "00:0C:BF:13:6B:2E";
        //String MAC = "00:0C:BF:13:33:9B";// 10 abril 2018
        // String MAC = "00:0C:BF:13:35:6F|00:0C:BF:13:33:9B";// 10 abril 

        // MAC = Utilidades.loaderFile("/opt/compilados/mac_files.txt");  
        //mac_files.txt
        String SALIDA_PK1 = "1";
        String CONSULTA_TODO_CONTRATO = "2";
        String REPORT_SALIDA = "3";
        String BARCODE = "4";
        String SELECT_LOGIN = "5";
        String LOGIN = "6";
        String SITIOS = "7";
        String CREAR_SRV = "8";
        String CAMBIAR_CLAVE = "9";
        String HACER_CIERRE = "10";
        String REIMPRECION = "11";
        String REIMPRECION_ULTIMA_FACT = "12";
        String ANULACION = "13";
        String RUTEO = "14";
        String SINCRONIZAR_NIT = "15";
        String CONEXION = "16";
        String TURNO = "17";
        String CARGUE_FACTURA = "18";
        String CONSULTA_FACTURAS_NUEVAS = "19";
        String ACTUALIZA_CONTA = "20";
        String FORZAR_CIERRE = "21";
        String BUSCAR_PLACA = "22";
        String BUSCAR_PAGO_MDPY = "23";
        String ACTUALIZAR_PLACA_CONTAINER = "24";
        String BUSCAR_PLACA_INTO_CONTAINER = "25";
        String REGISTRO_FACTURA = "26";
        String CREAR_SRV_2 = "8a";
        String FOTO_CONTABLE = "27";
        String ACTUALIZAR_PLACA_CONTRATO = "28";
        String CREAR_SRV_PREVIEW = "29";
        String CANCELA_SRV_PREVIEW = "30";
        String CREAR_SRV_PREVIO_SRV_CAMIONETA = "31";

        String[] D = new String[32];
        D[0] = SALIDA_PK1 + ",SALIDA DE PARQUEADERO";
        D[1] = CONSULTA_TODO_CONTRATO + ",CONSULTAR TODOS LOS CONTRATOS";
        D[2] = REPORT_SALIDA + ",REPORTE DE SALIDA";
        D[3] = BARCODE + ",GENERAR BARCODE DE LOS VEHICULOS";
        D[4] = SELECT_LOGIN + ",SELECT OPCION DE LOGING";
        D[5] = LOGIN + ",LOGIN DE USUARIOS";
        D[6] = SITIOS + ",LISTAR SITIOS";
        D[7] = CREAR_SRV + ",CREAR SERVICIO";
        D[8] = CAMBIAR_CLAVE + ",CAMBIAR CONTRASEÑA";
        D[9] = HACER_CIERRE + ",HACER CIERRE DE CAJA";
        D[10] = REIMPRECION + ",REIMPRESION DE FACTURA";
        D[11] = REIMPRECION_ULTIMA_FACT + ",REIMPRESION ULTIMA FACTURA";
        D[12] = ANULACION + ",ANULACION DE FACTURA";
        D[13] = RUTEO + ",RUTEANDO DIRECCION";
        D[14] = SINCRONIZAR_NIT + ",SINCRONIZAR NIT";
        D[15] = CONEXION + ",PROBAR CONEXION";
        D[16] = TURNO + ",GENERAR TURNO";
        D[17] = CARGUE_FACTURA + ",CARGAR FACTURAS";
        D[18] = CONSULTA_FACTURAS_NUEVAS + ",FACTURAS PENDIENTES";
        D[19] = ACTUALIZA_CONTA + ",ACTUALIZANDO LA CONTABILIDAD LOCAL";
        D[20] = FORZAR_CIERRE + ",FORZAR CIERRE";
        D[21] = BUSCAR_PLACA + ",BUSQUEDA DE PLACAS";
        D[22] = BUSCAR_PAGO_MDPY + ",PAGOS MODIPAI";
        D[23] = ACTUALIZAR_PLACA_CONTAINER + ",ACTUALIZAR PLACA CONTAINER-UPDATE";
        D[24] = BUSCAR_PLACA_INTO_CONTAINER + ",BUSCAR PLACA INTO CONTAINER";
        D[25] = REGISTRO_FACTURA + ",REGISTRO DE FACTURA";
        D[26] = CREAR_SRV_2 + ",NUEVO CREAR SERVICIO";
        D[27] = FOTO_CONTABLE + ",GENERA FOTO CONTABLE AERO/VPS";
        D[28] = ACTUALIZAR_PLACA_CONTRATO + ",ACTUALIZAR ESTADO CONTRATOS";
        D[29] = CREAR_SRV_PREVIEW + ",CREAR SRV PREVIO DESDE LA WEB";
        D[30] = CANCELA_SRV_PREVIEW + ",CANCELAR PREVIO DESDE LA WEB";
        D[31] = CREAR_SRV_PREVIO_SRV_CAMIONETA + ",CREAR_SRV_PREVIO_SRV_CAMIONETA";

        List<JsonDTO> alertas = new ArrayList<JsonDTO>();
        Gson gson = new Gson();
        try {
            String pesos = nvJson;
            if (!pesos.startsWith("[") && !pesos.endsWith("]")) {
                pesos = "[" + nvJson + "]";
            }
            JsonDTO[] entra = gson.fromJson(pesos, JsonDTO[].class);
            if (entra != null) {
                if (entra[0].error != null && entra[0].error.equals("no")) {
                    return "{\"error\":\"no\"}";
                }
            } else {
                return "{\"error\":\"no\"}";
            }
            OUT:
            for (int i = 0; i < D.length; i++) {
                String[] com = D[i].split("\\,");
                //rw(com[0] + ", " + com[1]);
                if (entra[0].keyCommand.equals(com[0]) && entra[0].fecha != null) {
                    rw("\n==================KEYCOMMAND_" + entra[0] + "_PROCESADA_" + com[1] + "_OVER_" + DateHelper.stringToDate(entra[0].fecha) + "============");
                    break OUT;
                }
            }

            if (entra[0].keyCommand.equals(FOTO_CONTABLE)) {
                String rta = "no";
                try {
                    if (new isProduccion().isProduccion().equals("VPS")) {
                        JsonDTO fotoAERO = service.generarFotoContable("VPS");
                        rta = new Gson().toJson(fotoAERO);
                    } else {
                        rw("FOTO_DEBE_GENERARSE_DESDE_AERO: " + rta);
                    }
                } catch (RuntimeException e) {
                    rw("Finalizado" + rta);
                }
                rw("RTA VPS: " + rta);
                return rta;
            } else if (entra[0].keyCommand.equals(CREAR_SRV_PREVIEW)) {

                SrvPreview servicio = new SrvPreview();
                servicio.setFecha(new Date());
                servicio.setIdEstado(1);
                servicio.setReferencia(entra[0].direccion);
                servicio.setAutoPlaca(entra[0].placa.toUpperCase());
                servicio.setJson(nvJson);

                servicio = service.crearSrvPreview(servicio);

                rw("JSON_" + nvJson);
                String placa = entra[0].direccion;
                String precio = entra[0].precio;
                String cedula = entra[0].cedula;

                JsonDTO json = new JsonDTO();
                json.setId("" + servicio.getId());
                json.setError("ok");
                json.setPrecio(precio);
                json.setPlaca(placa);
                pesos = new Gson().toJson(json);
                rw("RTA_" + pesos);
                return pesos;
            } else if (entra[0].keyCommand.equals(CANCELA_SRV_PREVIEW)) {
                String placa = entra[0].placa.toUpperCase();

                SrvPreview servicio = new SrvPreview();
                servicio.setFecha(new Date());
                servicio.setIdEstado(1);
                servicio.setReferencia(entra[0].direccion);
                servicio.setAutoPlaca(entra[0].placa);
                servicio.setJson(nvJson);

                boolean cancelado = service.cancelarSrvPreview(placa);

                JsonDTO json = new JsonDTO();
                if (cancelado) {
                    json.setError("ok");
                    json.setMsjError("ok, cancelado exitosamente");

                } else {
                    json.setError("no");
                    json.setMsjError("Error, no se puede cancelar la solcitud");
                }
                pesos = new Gson().toJson(json);
                rw("RTA_" + pesos);
                return pesos;
            } else if (entra[0].keyCommand.equals(CREAR_SRV_PREVIO_SRV_CAMIONETA)) {
                String placa = entra[0].placa.toUpperCase();
                String nombre = entra[0].nombre.toUpperCase();
                String precio = entra[0].precio.toUpperCase();
                String direccion = entra[0].direccion.toUpperCase();
                String tipoPago = entra[0].tipoPago.toUpperCase();
                String fecha = entra[0].fecha;
                String cedual = entra[0].cedula;

                Servicios srv = new Servicios();
                srv.setAddres(direccion);
                srv.setFecha(toDateYYYYMMDDHHMM(fecha));
                srv.setUsuarios(new CajerosAero(0));
                srv.setValor(new Integer(precio));
                srv.setAutoPlaca(placa);
                srv.setTipoPago("EFECTIVO");
                srv.setNit(new Long(cedual));
                srv.setSitio(new Sitios(1));
                srv.setGrabador("admin");
                srv.setComprob("VJS");
                srv.setFechaGrab(toDateYYYYMMDDHHMM(fecha));
                srv.setIndProc(0);
                srv.setEstado(new Estados(27));
                srv.setCierre(0);
                if (tipoPago.equals("efectivo")) {
                    srv.setValorE(new Integer(precio));
                    srv.setValorD(new Integer("0"));
                    srv.setValorC(new Integer("0"));
                } else if (tipoPago.equals("debito")) {
                    srv.setValorD(new Integer(precio));
                    srv.setValorE(new Integer("0"));
                    srv.setValorC(new Integer("0"));
                } else {
                    srv.setValorC(new Integer(precio));
                    srv.setValorE(new Integer("0"));
                    srv.setValorD(new Integer("0"));
                }
                srv.setJson("");// incluir el json del srv

                /*  Servicios srv2 = service.crearServicio(srv);

                pesos = new Gson().toJson(json);*/
                rw("RTA_" + pesos);
                return pesos;
            } else if (entra[0].keyCommand.equals(REGISTRO_FACTURA)) {
                String rta = "no";
                try {
                    Ticket nuevaFactura = gson.fromJson(pesos, Ticket.class);
                    String placa = nuevaFactura.getPlaca();
                    String numero = nuevaFactura.getNumeroFactura();
                    String comprob = nuevaFactura.getComprob();
                    String idSitio = nuevaFactura.getSitio();
                    Servicios servicio = service._servicio(getNumberLong(numero), comprob);
                    if (servicio != null) {
                        String infoPlaca = service.buscarPlacaIntoContainer(placa);
                        servicio.setJson(pesos);
                        service.updateSrv(servicio);
                        rw("Factura actualizada.." + numero + " comprob:" + comprob);
                    }
                    rta = "ok";
                } catch (RuntimeException e) {
                    rw("Finalizado" + rta);

                }
                return rta;
            } else if (entra[0].keyCommand.equals(ACTUALIZAR_PLACA_CONTAINER)) {
                String placa = entra[0].placa;
                rw("PROCESO CONTAINER ");
                if (new isProduccion().isProduccion().equals("VPS")) {
                    rw("ACTUALIZANDO CONTAINER EN VPS");
                    service.containerPlaca(placa.toUpperCase(), pesos, 2);
                } else {
                    rw("ENVIANDO CONTAINER A VPS");
                    pesos = new WebserviceConnection().cargarPlacaContainer185_80_129_150(pesos);
                    rw(pesos);
                }
            } else if (entra[0].keyCommand.equals(ACTUALIZAR_PLACA_CONTRATO)) {
                String placa = entra[0].placa;
                rw("PROCESO CONTAINER ");
                if (new isProduccion().isProduccion().equals("VPS")) {
                    rw("ACTUALIZANDO CONTAINER EN VPS");
                    service.containerPlaca(placa.toUpperCase(), pesos, 2);
                } else {
                    rw("ENVIANDO CONTAINER A VPS");
                    pesos = new WebserviceConnection().getStatusAccountRemote93_188_165_75(pesos);
                    rw(pesos);
                }
            } else if (entra[0].keyCommand.equals(BUSCAR_PAGO_MDPY)) {
                if (new isProduccion().isProduccion().equals("AERO")) {
                    rw("Buscnado pagos modiay");
                    String noTransaccion = entra[0].transaccion;

                    Ticket rta = service.consultaModipay(noTransaccion);
                    if (rta != null) {
                        Servicios srv = service.validaModipay(noTransaccion);
                        if (srv != null) {
                            srv.getValor();
                            Integer valorMainAccount = getNumberInt(rta.getValorTotal());
                            Integer valorRetired = srv.getValor();
                            if (valorMainAccount > valorRetired) {
                                rta.setKeyCommand(BUSCAR_PAGO_MDPY);
                                rta.setError("ok");
                                rta.setMsjError("Se ha retirado de la cuenta: $" + valorRetired);
                                rta.setRetiros(valorRetired.toString());
                                rta.setDisponible("" + (valorMainAccount - valorRetired));
                            } else {
                                rta = new Ticket();
                                rta.setError("no");
                                rta.setMsjError("No hay fondos para retirar");
                                rta.setRetiros(valorRetired.toString());
                                rta.setDisponible("0");
                            }
                        } else {
                            rta.setKeyCommand(BUSCAR_PAGO_MDPY);
                            rta.setError("ok");
                            rta.setMsjError("Puede retirar $" + rta.getValorTotal());
                            rta.setRetiros("0");
                            rta.setDisponible(rta.getValorTotal());
                        }

                    } else {
                        rw("no hay regisros..");
                        rta = new Ticket();
                        rta.setError("no");
                        rta.setMsjError("Transaccion no encontrada");
                    }
                    String myJson = new Gson().toJson(rta, Ticket.class);
                    pesos = getStringTrim(myJson);
                } else {
                    rw("End updated sin exito ");
                    pesos = new WebserviceConnection().getStatusAccountRemote200_91_204_38(pesos);
                    rw(pesos);
                }

                rw("RTA_" + pesos);
            } else if (entra[0].keyCommand.equals(BUSCAR_PLACA_INTO_CONTAINER)) {
                rw("evaluando container (temp_vehiculo) " + entra[0].placa);
                String JSON = service.buscarPlacaIntoContainer(entra[0].placa);
                if (JSON != null) {
                    if (JSON.startsWith("[") && JSON.endsWith("]")) {
                        pesos = JSON.substring(1, JSON.length() - 1);
                    } else {
                        pesos = JSON;
                    }

                } else {
                    pesos = "{\"error\":\"no\"}";
                    rw("RTA_" + pesos);
                }

                try {
                    rw(pesos);
                    Ticket rta = new Gson().fromJson(pesos, Ticket.class);
                    rw("ok_1");
                    rta.setEmpresaVehiculo(rta.getEmpresaVehi());
                    rw("ok_2");
                    String empresaVehiculo = rta.getEmpresaVehi();
                    List<Persona> lstPersona = new ArrayList<Persona>();
                    for (Persona p : rta.getPersonas()) {
                        p.setEmpresaVehiculo(empresaVehiculo);
                        lstPersona.add(p);
                    }

                    rta.setEmpresaVehiculo(empresaVehiculo);

                    rta.setPersonas(lstPersona);
                    pesos = new Gson().toJson(rta);

                    rw("RTA_CONTAINER:" + pesos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (entra[0].keyCommand.equals(BUSCAR_PLACA)) {
                if (!(new isProduccion().isProduccion().equals("VPS"))) {
                    rw("Buscnado placa en operativa");
                    String placa = entra[0].placa;
                    Ticket rta = service.buscarPlaca(placa.toUpperCase());

                    if (rta != null && rta.getError().equals("ok")) {

                        rta.setKeyCommand("24");
                        rta.setEmpresaVehiculo(rta.getEmpresaVehi());

                        //rta.setNit("8300887404-7");
                        //rta.setNumeroLargo("425497301201610111228");
                        rta.setObjecto("Contrato de prestacion de servicios publico de transporte especial del contratante (RES 0348/2015)");
                        String jsonAnt = gson.toJson(rta, Ticket.class);
                        pesos = jsonAnt.trim();

                        try {
                            rw("Actualizando container ");
                            service.containerPlaca(placa, pesos, 2);

                        } catch (RuntimeException e) {
                            rw("no se Actualizando container ");
                        }
                    } else {
                        rw("_RTA_");
                        rta = new Ticket();
                        rta.setError("no");
                        String jsonAnt = gson.toJson(rta, Ticket.class);
                        pesos = jsonAnt.trim();
                    }
                    rw(pesos);
                } else {
                    rw("End updated sin exito ");
                    // pesos = new WebserviceConnection().getStatusAccountRemote200_91_204_38(pesos);
                    pesos = new WebserviceConnection().getStatusAccountRemote93_188_167_147X(pesos);
                    rw("_HSA_" + pesos);

                    try {
                        rw("Actualizando container in VPS ");
                        if (Utilidades.isOK(pesos)) {
                            Ticket myTk = gson.fromJson(pesos, Ticket.class);
                            service.containerPlaca(myTk.getPlaca(), pesos, 2);
                        }
                    } catch (RuntimeException e) {
                        rw("no se Actualizando container ");
                    }
                    rw(pesos);
                }
            } else if (entra[0].keyCommand.equals(FORZAR_CIERRE)) {
                if (new isProduccion().isProduccion().equals("VPS")) {
                    List<Servicios> lstSrv = service.ultServiciosPendientes("ALL");
                    RtaDTO[] entra2 = gson.fromJson(pesos, RtaDTO[].class);
                    List<JsonDTO> srvPendientes = entra2[0].getAlertas();//20
                    int i = 0;
                    for (JsonDTO j : srvPendientes) {
                        service.updateSrvContable(j.getComprob(), getNumberInt(j.getNumero()));
                        i++;
                    }
                    rw("End updated with " + i + " servicios actualizados");
                } else {
                    rw("End updated sin exito ");
                }
            } else if (entra[0].keyCommand.equals(ACTUALIZA_CONTA)) {
                // if (new isProduccion().isProduccion().equals("VPS")) {
                RtaDTO[] entra2 = gson.fromJson(pesos, RtaDTO[].class);
                List<JsonDTO> srvPendientes = entra2[0].getAlertas();//20
                int i = 0;
                for (JsonDTO j : srvPendientes) {
                    service.updateSrvContable(j.getComprob(), getNumberInt(j.getNumero()));
                    i++;
                }
                rw("End updated with " + i + " servicios actualizados");

            } else if (entra[0].keyCommand.equals(CONSULTA_FACTURAS_NUEVAS)) {
                if (new isProduccion().isProduccion().equals("VPS")) {
                    JsonDTO dto = entra[0];
                    RtaDTO rtaDTO = new RtaDTO();
                    List<Servicios> lstSrv = service.ultServiciosPendientes(dto.getComprob());
                    List<JsonDTO> srvPendientes = new ArrayList<JsonDTO>();
                    rtaDTO.setError("ok");
                    if (lstSrv != null && lstSrv.size() > 0) {
                        for (Servicios srv : lstSrv) {

                            JsonDTO j = new JsonDTO();
                            j.setId(srv.getId().toString());
                            j.setAddres(Utilidades.remplazNoOk(Utilidades.eliminarBarusar(srv.getAddres())));
                            j.setFecha(toDateYYYYMMDDHHMM(srv.getFechaGrab()));
                            j.setIdUsuario(srv.getUsuarios().getId().toString());
                            j.setValor(getNumberInt(srv.getValor()));
                            j.setPlaca(srv.getAutoPlaca());
                            j.setTipoPago(srv.getTipoPago());
                            j.setNit(srv.getNit().toString());
                            j.setIdSitio(srv.getSitio().getId().toString());
                            j.setGrabador(srv.getGrabador());
                            j.setComprob(srv.getComprob());
                            j.setFechaGrab(toDateYYYYMMDDHHMM(srv.getFechaGrab()));
                            j.setIndProc(srv.getIndProc() != null ? srv.getIndProc().toString() : null);
                            j.setNumero(srv.getNumero().toString());
                            j.setEstadoId(srv.getEstado().getId().toString());
                            j.setCierre(getNumberInt(srv.getCierre()));
                            j.setValorE(getNumberInt(srv.getValorE()));
                            j.setValorC(getNumberInt(srv.getValorC()));
                            j.setValorD(getNumberInt(srv.getValorD()));
                            j.setPorque(srv.getPorque());

                            srvPendientes.add(j);
                        }
                        rtaDTO.setAlertas(srvPendientes);
                        rtaDTO.setKeyCommand("18");
                        pesos = gson.toJson(rtaDTO, RtaDTO.class);
                        rw(pesos);

                    } else {
                        rtaDTO.setAlertas(srvPendientes);
                        rtaDTO.setError("no");

                        pesos = gson.toJson(rtaDTO, RtaDTO.class);
                        rw(pesos);
                    }
                } else {
                    rw("End updated sin exito ");
                }
            } else if (entra[0].keyCommand.equals(CARGUE_FACTURA)) {

                if (!new isProduccion().isProduccion().equals("VPS")) {
                    RtaDTO[] entra2 = gson.fromJson(pesos, RtaDTO[].class);
                    List<JsonDTO> srvPendientes = entra2[0].getAlertas();//20
                    List<JsonDTO> lstSrvCumplidos = new ArrayList<JsonDTO>();
                    RtaDTO rtaDTO = new RtaDTO();

                    int count = 0;
                    List<Servicios> persistidos = new ArrayList<Servicios>();
                    for (JsonDTO j : srvPendientes) {
                        Servicios srv = new Servicios();
                        srv.setAddres(Utilidades.eliminarBarusar(j.getAddres()));
                        srv.setFecha(toDateYYYYMMDDHHMM(j.getFechaGrab()));
                        srv.setFechaGrab(toDateYYYYMMDDHHMM(j.getFechaGrab()));
                        srv.setUsuarios(new CajerosAero(getNumberInt(j.getIdUsuario())));
                        srv.setValor(getNumberInt(j.getValor()));

                        srv.setBase(getNumberInt(j.getBase()));
                        srv.setAutoPlaca(j.getPlaca());
                        srv.setTipoPago(j.getTipoPago());
                        srv.setNit(getNumberLong(j.getNit()));
                        srv.setSitio(new Sitios(getNumberInt(j.getIdSitio())));
                        srv.setGrabador(j.getGrabador());
                        srv.setComprob(j.getComprob());
                        srv.setIndProc(0);
                        srv.setNumero(getNumberInt(j.getNumero()));
                        srv.setEstado(new Estados(getNumberInt(j.getEstadoId())));
                        srv.setCierre(getNumberInt(j.getCierre()));
                        srv.setValorE(getNumberInt(j.getValorE()));
                        srv.setValorC(getNumberInt(j.getValorC()));
                        srv.setValorD(getNumberInt(j.getValorD()));
                        srv.setPorque(j.getPorque());

                        if (getNumberInt(j.getValor()) > 0) {
                            if (getNumberInt(j.getBase()) > 0) {
                            } else {
                                Integer valorServicio = srv.getValor();
                                Double valorBase = valorServicio * 96.781 / 100;
                                srv.setBase(valorBase.intValue());
                            }
                        } else {
                            srv.setBase(0);
                        }
                        srv.setBase(StringTools.getNumberInt(j.getBase()));
                        srv.setValoro(StringTools.getNumberInt(j.getValoro()));

                        srv.setIndProc(Integer.valueOf(0));

                        Servicios nvSrv = service.saveServicio(srv);
                        if (nvSrv != null) {
                            count++;
                            persistidos.add(srv);
                        }
                    }

                    rw("listar los servicios persistidos y/o conbalizados");
                    rtaDTO = new RtaDTO();
                    rtaDTO.setError("ok");
                    for (Servicios serv : persistidos) {
                        JsonDTO dto = new JsonDTO();
                        dto.setError("ok");
                        dto.setComprob(serv.getComprob());
                        dto.setNumero(getNumberInt(serv.getNumero()));
                        lstSrvCumplidos.add(dto);
                    }
                    rtaDTO.setAlertas(lstSrvCumplidos);
                    rtaDTO.setKeyCommand("20");
                    pesos = gson.toJson(rtaDTO, RtaDTO.class);
                }
                rw(pesos);
                //hsa }

            } else if (entra[0].keyCommand.equals(CONEXION)) {
                RtaDTO rtaDTO = new RtaDTO();
                JsonDTO dto = new JsonDTO();
                dto.setError("ok");
                alertas.add(dto);
                rtaDTO.setAlertas(alertas);

                List<SrvPreview> lstSrvPreview = service.listarSrvPreview(entra[0].idSitio);
                if (lstSrvPreview != null && lstSrvPreview.size() > 0) {
                    List<JsonDTO> servicios = new ArrayList<JsonDTO>();
                    for (SrvPreview srv : lstSrvPreview) {
                        String myJson = srv.getJson();
                        JsonDTO srvs = new Gson().fromJson(myJson, JsonDTO.class);
                        srvs.setId(srv.getId().toString());
                        servicios.add(srvs);
                    }
                    rtaDTO.setLstSrvPreview(servicios);
                }
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(TURNO)) {

                RtaDTO rtaDTO = new RtaDTO();
                List<Integer> escalas = new ArrayList<Integer>();
                int iter = getNumberInt(entra[0].escala);
                for (int i = 1; i < iter; i++) {
                    turno++;
                    escalas.add(i);
                    JsonDTO dto = new JsonDTO();
                    dto.setError("ok");
                    dto.setControl(turno.toString());
                    dto.setId(turno.toString());
                    alertas.add(dto);
                }
                rtaDTO.setAlertas(alertas);
                rtaDTO.setEscala(escalas);

                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(SINCRONIZAR_NIT)) {

                Integer intLoad = service.sincronizarPagos();

                JsonDTO dto = new JsonDTO();
                RtaDTO rtaDTO = new RtaDTO();

                if (intLoad != null && intLoad > 0) {
                    dto.setError("ok");
                    dto.setCantidad(intLoad.toString());
                } else {
                    dto.setError("no");
                    dto.setCantidad("0");
                }
                alertas.add(dto);
                rtaDTO.setAlertas(alertas);

                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);

            } else if (entra[0].keyCommand.equals(RUTEO)) {
                JsonDTO dto = entra[0];
                RtaDTO rtaDTO = new RtaDTO();

                boolean isPrice = false;
                boolean isCRB = false;
                if (dto.getSitio() != null && dto.getSitio().equals("7")) {
                    isPrice = true;
                }
                if (dto.getSitio() != null && dto.getSitio().equals("16")) {
                    isCRB = true;
                }
                String latitud = "4.6971956";
                String longitud = "-74.1405186";

                if (isPrice) {
                    latitud = "4.66661986";
                    longitud = "-74.11119928";
                }
                rw("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                JsonDTO data = GeoReferenciador.zonificadorAndGeoRuteo(entra[0].direccion, "11001", latitud, longitud);
                rw("%%%%%%%%%%%%%%%%%%%metros%%   "+data.getMetros()+"  %%%%%%%%%%%%%%%%%%%%%%%%%%");
                Integer costo = 0;
                if (getNumberInt(data.getMetros()) > 0) {
                    if (isPrice || isCRB) {
                        costo = new Helper().calcularCostoFromPriceAndAero(getNumberInt(data.getMetros()), dto.getSitio());
                        dto.setPesos(costo.toString());
                        dto.setBase(costo.toString());
                        dto.setPrecio(costo.toString());
                    } else {
                        costo = new Helper().calcularCostoFromAero(getNumberInt(data.getMetros()), data.getDireccion());
                        rw("========APLICAR RETE FUENTE E RETE ICA ====");

                        Integer[] precioYbase = Tarifas.getValorReteIcaFuente(costo);

                        int tmp = precioYbase[0].intValue();
                        costo = Integer.valueOf(precioYbase[0].intValue() + 4000);
                        precioYbase[0] = costo;
                        rw("=====INCREMENTO_4000_1_OCT_2019_SIN_BASE====" + costo + "____antes_" + tmp);
                        dto.setPesos(precioYbase[0].toString());
                        dto.setBase(precioYbase[0].toString());
                        dto.setPrecio(precioYbase[0].toString());

                    }
                    dto.setError("ok");
                    alertas.add(dto);
                    rtaDTO.setAlertas(alertas);
                } else {
                    dto = new JsonDTO();
                    dto.setError("no");
                    alertas.add(dto);
                }

                rtaDTO.setAlertas(alertas);
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw("RESPUESTA++++++++++++++++++++++++++");
                rw(pesos);

            } else if (entra[0].keyCommand.equals(ANULACION)) {
                JsonDTO dto = entra[0];
                RtaDTO rtaDTO = new RtaDTO();
                Servicios srv = service.anulacion(getNumberLong(dto.getNumeroFactura()), dto.getPorque(),
                        new Sitios(getNumberInt(dto.getSitio())), toDateYYYYMMDDHHMM(dto.getFecha()));
                if (srv != null) {
                    dto = new InitHelper().reimpresion(srv);
                    if (dto != null) {
                        dto.setError("ok");
                        alertas.add(dto);
                        // rtaDTO.setAlertas(alertas);
                    }
                } else {
                    dto = new JsonDTO();
                    dto.setError("no");
                    alertas.add(dto);
                    rtaDTO.setError("no");
                    rtaDTO.setAlertas(alertas);
                }
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(REIMPRECION_ULTIMA_FACT)) {
                JsonDTO dto = entra[0];
                RtaDTO rtaDTO = new RtaDTO();
                Servicios srv = service.lastServicioByCajero(new CajerosAero(getNumberInt(dto.getIdUsuario())));

                if (srv != null) {
                    rw("srv_encontrado_con_el_id_" + srv.getId());
                    dto = new InitHelper().reimpresion(srv);
                    rw("rsv:   " + dto);
                    dto.setError("ok");
                    //hsa dto.setTransaccion(srv.getTransaccion());
                    dto.setTransaccion(srv.getJson());
                    //hsa Ticket factura = new Gson().fromJson(srv.getTransaccion(), Ticket.class);
                    Ticket factura = new Gson().fromJson(srv.getJson(), Ticket.class);
                    dto.setTiquete(factura);

                    String empresaVehiculo = "";
                    if (!(factura.getEmpresaVehiculo() != null && !factura.getEmpresaVehiculo().isEmpty())) {
                        for (Persona p : factura.getPersonas()) {
                            if (p.getEmpresaVehiculo() != null && !p.getEmpresaVehiculo().isEmpty()) {
                                empresaVehiculo = p.getEmpresaVehiculo();
                            }
                        }
                        factura.setEmpresaVehiculo(empresaVehiculo);
                    }

                } else {
                    rw("srv_no_encontrado");
                    dto = new JsonDTO();
                    dto.setError("no");
                }
                alertas.add(dto);
                rtaDTO.setAlertas(alertas);

                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(REIMPRECION)) {

                JsonDTO dto = entra[0];
                Servicios srv = service._servicio(new Long(dto.getNumeroFactura()), dto.comprob);
                RtaDTO rtaDTO = new RtaDTO();

                if (srv != null) {
                    /*dto = new InitHelper().reimpresion(srv);
                     dto.setError("ok");
                     dto.setTransaccion(srv.getTransaccion());
                     if (srv.getTransaccion() != null) {
                     Ticket factura = gson.fromJson(srv.getTransaccion(), Ticket.class);
                     dto.setTiquete(factura);
                     }
                     alertas.add(dto);
                     rtaDTO.setAlertas(alertas);
                     pesos = gson.toJson(dto, JsonDTO.class);
                     pesos = gson.toJson(rtaDTO, RtaDTO.class);*/

                    pesos = srv.getJson();
                    //rw(pesos);
                } else {
                    Ticket factura = new Ticket();
                    factura.setError("no");
                    pesos = gson.toJson(factura, Ticket.class);
                    //rw(pesos);
                }
                rw(pesos);
            } else if (entra[0].keyCommand.equals(HACER_CIERRE)) {
                JsonDTO dto = entra[0];
                List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                CajerosAero CAJERO = service.usuarioById(getNumberInt(dto.getIdUsuario()));

                Object obj[] = service.cerrarServicios(CAJERO, new Boolean(dto.getCierre()), null);
                JsonDTO dto2 = new JsonDTO();
                RtaDTO rtaDTO = new RtaDTO();
                if (obj != null && obj.length > 1 && obj[1] != null) {
                    Cierre cierre = (Cierre) obj[1];
                    obj[1] = cierre;
                    dto2.setValor(cierre.getValor().toString());
                    dto2.setValorTotal(cierre.getValorTotal().toString());
                    dto2.setInicio(cierre.getInicio().toString());
                    dto2.setFin(cierre.getFin().toString());
                    dto2.setUsuarioNombre(cierre.getUsuarioNombre());
                    dto2.setSitioNombre(cierre.getSitioNombre());
                    dto2.setCantidad(cierre.getCantidad().toString());
                    dto2.setFecha(toDateYYYYMMDDHHMM(cierre.getFecha()));

                    dto2.setValorE(cierre.getValorE().toString());
                    dto2.setValorC(cierre.getValorC().toString());
                    dto2.setValorD(cierre.getValorD().toString());
                    dto2.setValorAnulados(getNumberLong("" + cierre.getValorAnualdos()).toString());
                    dto2.setCantidadAnulads(getNumberLong("" + cierre.getCantidadAnuladas()).toString());

                    dto2.setError("ok");
                    dto2.setComprob(cierre.getComprob());
                    lRta.add(dto2);

                } else {
                    dto2.setError("no");
                    lRta.add(dto2);
                }
                rtaDTO.setAlertas(lRta);
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(CAMBIAR_CLAVE)) {
                JsonDTO dto = entra[0];
                CajerosAero CAJERO = service.usuarioById(getNumberInt(dto.getIdUsuario()));
                CAJERO.setClave(dto.getClave());

                CajerosAero srv2 = service.changePass(CAJERO);

                List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                JsonDTO jsonDTO = new JsonDTO();

                if (srv2.getId() != null) {
                    jsonDTO.setError("ok");
                } else {
                    jsonDTO.setError("no");
                }
                lRta.add(jsonDTO);
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO.setAlertas(lRta);
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(CREAR_SRV)) {
                JsonDTO tiqueteNuevo = entra[0];

                Servicios srv = new Servicios();
                srv.setComprob(tiqueteNuevo.getComprob());

                srv.setAddres(Utilidades.eliminarBarusar(tiqueteNuevo.getDireccion()));
                srv.setFecha(toDateYYYYMMDDHHMM(tiqueteNuevo.getFecha()));
                srv.setFechaGrab(toDateYYYYMMDDHHMM(tiqueteNuevo.getFecha()));
                srv.setUsuarios(new CajerosAero(getNumberInt(tiqueteNuevo.getIdUsuario())));
                srv.setValor(getNumberInt(tiqueteNuevo.getValor()));
                srv.setAutoPlaca(tiqueteNuevo.getPlaca());
                srv.setTipoPago(tiqueteNuevo.getTipoPago());
                srv.setNit(getNumberLong(tiqueteNuevo.getNit()));
                srv.setSitio(new Sitios(getNumberInt(tiqueteNuevo.getSitio())));
                srv.setIndProc(getNumberInt(tiqueteNuevo.getIndProc()));
                if (getNumberInt(tiqueteNuevo.getSitio()).toString().equals("9")) {
                    //  srv.setIndProc(getNumberInt("1"));
                }
                // colocar el grabador...
                srv.setGrabador(tiqueteNuevo.getGrabador());

                srv.setFechaGrab(srv.getFecha());
                srv.setCierre(getNumberInt(tiqueteNuevo.getCierre()));
                srv.setValorE(getNumberInt(tiqueteNuevo.getValorE()));
                srv.setValorC(getNumberInt(tiqueteNuevo.getValorC()));
                srv.setValorD(getNumberInt(tiqueteNuevo.getValorD()));
                srv.setAddres(tiqueteNuevo.getDireccion());

                srv.setTransaccion(tiqueteNuevo.getTransaccion());
                rw("IndProc:=" + srv.getIndProc());
                if (srv.getIndProc() != null && srv.getIndProc().equals(0)
                        && tiqueteNuevo.getSitio().toString().equals("8")) {
                    // srv.setIndProc(1);
                    srv.setValorC(getNumberInt(tiqueteNuevo.getValor()));
                    srv.setTipoPago("BALOTO");
                }

                if (tiqueteNuevo.getJson() != null) {
                    Ticket tk = tiqueteNuevo.getJson();

                    tk.setEmpresaVehiculo(tk.getEmpresa());

                    String myJson = new Gson().toJson(tk, Ticket.class);
                    srv.setJson(myJson);
                }

                if (getNumberInt(tiqueteNuevo.getValor()) > 0) {
                    if (getNumberInt(tiqueteNuevo.getBase()) > 0) {
                    } else {
                        Integer valorServicio = srv.getValor();
                        Double valorBase = valorServicio * 96.781 / 100;
                        srv.setBase(valorBase.intValue());
                    }
                } else {
                    srv.setBase(0);
                }

                srv.setBase(srv.getValor());

                Servicios srv2 = service.crearServicio(srv);
                rw("RTA_HOY" + srv2);
                try {

                    // dto = new JsonDTO();
                    if (srv2 != null && srv2.getId() != null) {
                        tiqueteNuevo.setId(srv2.getNumero().toString());
                        tiqueteNuevo.setNumeroFactura(srv2.getNumero().toString());
                        tiqueteNuevo.setFecha(toDateYYYYMMDDHHMM(srv2.getFechaGrab()));
                        tiqueteNuevo.setError("ok");
                        tiqueteNuevo.setConsecutivo(srv2.getId().toString());
                        Sitios sitio = service.sitioById(getNumberInt(tiqueteNuevo.getSitio()));
                        Integer start = sitio.getStock();
                        tiqueteNuevo.setFuec(start.toString());
                        try {
                            String numeroLargo = "4254973012016" + tiqueteNuevo.getContrato() + tiqueteNuevo.getId();
                            rw("NumeroLargo: " + numeroLargo);
                            tiqueteNuevo.setNumeroLargo(numeroLargo);

                            String sTiquete = new Gson().toJson(tiqueteNuevo, Ticket.class);
                            srv2.setTransaccion(sTiquete);
                            srv2.setJson(sTiquete);
                            boolean uptualizado = service.updateSrv(srv2);

                            rw("se cargo la transacion " + uptualizado);
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }
                    } else {
                        tiqueteNuevo.setError("no");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                lRta.add(tiqueteNuevo);
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO.setAlertas(lRta);
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(CREAR_SRV_2)) {
                JsonDTO obj = entra[0];
                Long start = System.currentTimeMillis();

                Ticket tiqueteNuevo = obj.getTiquete();
                Servicios srv = new Servicios();
                srv.setComprob(tiqueteNuevo.getComprob());

                srv.setAddres(Utilidades.eliminarBarusar(tiqueteNuevo.getDireccion()));
                srv.setFecha(DateHelper.toDateYYYYMMDDHHMM(tiqueteNuevo.getFecha()));
                srv.setFechaGrab(DateHelper.toDateYYYYMMDDHHMM(tiqueteNuevo.getFechaGrab()));
                srv.setUsuarios(new CajerosAero(StringTools.getNumberInt(tiqueteNuevo.getIdUsuario())));
                srv.setValor(StringTools.getNumberInt(tiqueteNuevo.getValor()));
                srv.setAutoPlaca(tiqueteNuevo.getPlaca());
                srv.setTipoPago(tiqueteNuevo.getTipoPago());
                srv.setNit(StringTools.getNumberLong(tiqueteNuevo.getNit()));
                srv.setSitio(new Sitios(StringTools.getNumberInt(tiqueteNuevo.getSitio())));

                //srv.setIndProc(StringTools.getNumberInt(tiqueteNuevo.getIndProc()));
                srv.setBase(StringTools.getNumberInt(tiqueteNuevo.getBase()));
                if (StringTools.getNumberInt(tiqueteNuevo.getSitio()).toString().equals("8")) {
                }
                srv.setIndProc(0);

                srv.setTipoPago("BALOTO");

                srv.setGrabador(tiqueteNuevo.getGrabador());

                srv.setFechaGrab(srv.getFecha());
                srv.setCierre(StringTools.getNumberInt(tiqueteNuevo.getCierre()));
                srv.setValorE(StringTools.getNumberInt(tiqueteNuevo.getValorE()));
                srv.setValorC(StringTools.getNumberInt(tiqueteNuevo.getValorC()));
                srv.setValorD(StringTools.getNumberInt(tiqueteNuevo.getValorD()));
                srv.setAddres(tiqueteNuevo.getDireccion());
                srv.setTransaccion(tiqueteNuevo.getTransaccion());
                LogTest.rw("IndProc:=" + srv.getIndProc());
                if (srv.getIndProc() != null && srv.getIndProc().equals(0)
                        && tiqueteNuevo.getSitio().toString().equals("8")) {
                    srv.setIndProc(0);
                    srv.setValorC(getNumberInt(tiqueteNuevo.getValor()));
                }

                if (getNumberInt(tiqueteNuevo.getValor()) > 0) {
                    if (getNumberInt(tiqueteNuevo.getBase()) > 0) {
                    } else {
                        Integer valorServicio = srv.getValor();
                        Double valorBase = valorServicio * 96.781 / 100;
                        srv.setBase(valorBase.intValue());
                    }
                } else {
                    srv.setBase(0);
                }
                int valoro = Utilidades.getNumberIntNito(srv).intValue();
                int base = srv.getValor().intValue() - valoro;
                if (base <= 0) {
                    base = 0;
                }
                srv.setBase((base));
                srv.setValoro((valoro));

                rw("creando servicio.....");
                Servicios srv2 = service.crearServicio(srv);
                JsonDTO lRta = new JsonDTO();
                rw("creando servicio..rta...");
                try {

                    // dto = new JsonDTO();
                    if (srv2 != null && srv2.getId() != null && srv2.getNumero() != null) {
                        try {
                            rw("creando servicio..rta..ok.");

                            String json = new Gson().toJson(srv2);

                            rw("json" + json);
                            tiqueteNuevo.setId(srv2.getNumero().toString());
                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setNumeroFactura(srv2.getNumero().toString());
                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setFecha(toDateYYYYMMDDHHMM(srv2.getFechaGrab()));
                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setError("ok");
                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setConsecutivo(srv2.getId().toString());
                            rw("creando servicio..rta..ok.");

//                    Sitios sitio = service.sitioById(getNumberInt(tiqueteNuevo.getSitio()));
//                    Integer start = sitio.getStock();
//                    tiqueteNuevo.setFuec(start.toString());
                            String numeroLargo = "4254973012019" + tiqueteNuevo.getNumeroInterno() + tiqueteNuevo.getId();
//                        rw("NumeroLargo: " + numeroLargo);
                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setNumeroLargo(numeroLargo);

                            rw("creando servicio..rta..ok.");
                            tiqueteNuevo.setEmpresaVehiculo(tiqueteNuevo.getEmpresa());
//rw("creando servicio..rta..ok.");
                            String sTiquete = new Gson().toJson(tiqueteNuevo, Ticket.class);
                            rw("creando servicio..rta..ok.");
                            if (sTiquete.contains("'")) {
                                sTiquete = sTiquete.replaceAll("'", "");
                            }
                            srv2.setJson(sTiquete);
                            rw("creando servicio..rta..ok.");
                            String host = InetAddress.getLocalHost().getHostName();

                            if (!(host.contains("central"))) {
                                service.updateSrv(srv2);
                            }

                            rw("creando servicio..rta..ok.");
                            // service.programarEnvioSrvRespaldo(srv2);

                            //rw("se cargo la transacion " + actualizado);
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }
                        lRta.setTiquete(tiqueteNuevo);
                        lRta.setError("ok");
                    } else {
                        rw("creando servicio..error...");
                        tiqueteNuevo.setError("no");
                        lRta.setError("ok");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rw("creando servicio..try...");
                pesos = gson.toJson(lRta, JsonDTO.class);
                rw(pesos);
                rw("crear_srv_duracion_" + (System.currentTimeMillis() - start));
            } else if (entra[0].keyCommand.equals(SITIOS)) {
                JsonDTO dto = entra[0];
                List<Lugares> lLugares = service.listLugares(entra[0].idSitio);
                if (lLugares != null && lLugares.size() > 0) {
                    List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                    for (Lugares s : lLugares) {
                        JsonDTO jsonDTO = new JsonDTO();
                        jsonDTO.setNombreSitio(s.getNombre());
                        jsonDTO.setValor(getNumberInt(s.getParam0()));
                        jsonDTO.setDireccion(s.getDireccion());
                        jsonDTO.setError("no");
                        lRta.add(jsonDTO);
                    }
                    dto.setError("ok");
                    RtaDTO rtaDTO = new RtaDTO();
                    rtaDTO.setAlertas(lRta);
                    pesos = gson.toJson(rtaDTO, RtaDTO.class);
                } else {
                    dto.setError("no");
                }
                rw(pesos);
            } else if (entra[0].keyCommand.equals(SELECT_LOGIN)) {
                JsonDTO dto = entra[0];
                List<Sitios> lSitios = service.lSitios();

                List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                for (Sitios s : lSitios) {
                    //  if (s.getId().equals(3) || s.getId().equals(4) || s.getId().equals(5) || s.getId().equals(6)) {
                    JsonDTO jsonDTO = new JsonDTO();
                    jsonDTO.setNombreSitio(s.getNombre());
                    jsonDTO.setIdSitio(s.getId().toString());
                    jsonDTO.setDireccion(s.getDireccion());
                    jsonDTO.setComprob(s.getDireccion());

                    lRta.add(jsonDTO);

                    // }
                }
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO.setAlertas(lRta);
                rtaDTO.setError("ok");
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(LOGIN)) {
                JsonDTO dto = entra[0];
                CajerosAero cajero = service.login(dto.getUsuario(), dto.getClave());
                JsonDTO jsonDTO = new JsonDTO();
                RtaDTO rtaDTO = new RtaDTO();
                if (cajero != null) {

                    Sitios s = service.sitioById(getNumberInt(dto.getIdSitio()));
                    jsonDTO.setClave(dto.getClave());
                    jsonDTO.setUsuario(dto.getUsuario());
                    jsonDTO.setNombre(cajero.getNombre());
                    jsonDTO.setIdSitio(dto.getIdSitio());
                    jsonDTO.setId(cajero.getId().toString());
                    jsonDTO.setComprob(s.getDireccion());
                    jsonDTO.setError("ok");
                    rtaDTO.setError("ok");
                } else {
                    rtaDTO.setError("no");
                }

                List<JsonDTO> lRta = new ArrayList<JsonDTO>();
                lRta.add(jsonDTO);

                rtaDTO.setAlertas(lRta);

                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            } else if (entra[0].keyCommand.equals(SALIDA_PK1)) {
                rw("contrato:   " + entra[0].getContrato() + " fecha: " + entra[0].fecha + "_mac_" + entra[0].mac);
                String barcode = entra[0].getContrato();
                String parametro = "";
                String imprimeTk = "false";
                if (barcode.matches("[0-9]*")) {
                    parametro = barcode;
                } else if (barcode.length() > 6) {
                    parametro = barcode.substring(barcode.length() - 6, barcode.length());
                } else {
                    parametro = barcode;
                }

                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO = service.lRtaDTO(parametro.toUpperCase(), toDateYYYYMMDDHHMM(entra[0].fecha), false);

                if (rtaDTO != null && rtaDTO.getAlertas() != null && rtaDTO.getAlertas().size() > 0
                        && rtaDTO.getAlertas().get(0).getContrato() != null) {

                    RegisterInOut obj = new RegisterInOut();
                    obj.setDetalle("Salida parqueadero");
                    obj.setFecha(toDateYYYYMMDDHHMM(entra[0].fecha));
                    obj.setIdSitios(new Sitios(4));
                    obj.setNumeroContrato(new Integer(rtaDTO.getAlertas().get(0).getContrato()));
                    obj.setVehiculoPlaca(getStringTrim(rtaDTO.getAlertas().get(0).getPlaca()));

                    JsonDTO dto = rtaDTO.getAlertas().get(0);
                    dto.setKeyCommand(REPORT_SALIDA);
                    dto.setFecha(nowTimeDateEasy2());
                    dto.setContrato("" + obj.getNumeroContrato());

                    dto.setPlaca(obj.getVehiculoPlaca());
                    dto.setDias(rtaDTO.getAlertas().get(0).getDias());
                    String jsonRTA = gson.toJson(dto, JsonDTO.class);

                    if (rtaDTO.getError().equals("ok")) {
                        service.insertLogs(obj, jsonRTA, dto.getDias());
                        service.reportActivity("[" + jsonRTA + "]");
                    }
                    String[] arrMac = MAC.split("\\|");
                    if (entra[0].mac != null) {
                        //MAC = entra[0].mac;
                    } else {
                        // MAC = arrMac[0];
                    }
                    rtaDTO.setMac(MAC);
                    if (entra[0].mac != null && !entra[0].mac.equals("null") && entra[0].mac.equals("38:fd:fe:e1:7a:37")) {//
                        rtaDTO.setMac("00:0C:BF:13:33:9B");
                    }
                    if (entra[0].mac != null && !entra[0].mac.equals("null") && entra[0].mac.equals("00:90:4c:59:fc:1f")) {//
                        rtaDTO.setMac("00:0C:BF:13:33:9B");
                    }

                    pesos = gson.toJson(rtaDTO, RtaDTO.class);
                    // rw(json);
                    rw("[ok, rta exitosa...]");
                } else {
                    rtaDTO.setError("no");
                    rtaDTO.setMac(MAC);
                    pesos = gson.toJson(rtaDTO, RtaDTO.class);
                    rw(pesos);
                }
            } else if (entra[0].keyCommand.equals(CONSULTA_TODO_CONTRATO)) {
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO = service.lRtaDTO(null, toDateYYYYMMDDHHMM(entra[0].fecha), true);

                if (rtaDTO != null && rtaDTO.getAlertas() != null && rtaDTO.getAlertas().get(0) != null && rtaDTO.getAlertas().get(0).getContrato() != null) {
                    rtaDTO.setError("ok");
                } else {
                    rtaDTO.setError("no");
                }
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw("Retornando_los_contratos" + rtaDTO.getAlertas().size());
                return pesos;

            } else if (entra[0].keyCommand.equals(BARCODE)) {
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO = service.allVehicule();
                rtaDTO.setError("ok");
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                // rw(json);
                rw("[ok, rta exitosa...]");
            } else {
                RtaDTO rtaDTO = new RtaDTO();
                rtaDTO.setError("403");
                pesos = gson.toJson(rtaDTO, RtaDTO.class);
                rw(pesos);
            }
            // json = Utilidades.Desencriptar(json);
            rw("RTA: " + pesos);

            return pesos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String[] reprocesar(Date fecha) {
        String[] values2 = new String[4];
        try {
            rwS("PERMITE IDENTIFICAR SALDO DE CONSECUTIVO");

            // String hace3Dias = _3Dias(new Date(), ((60 * 24) * 3)); 3 dias 
            String hace3Dias = DateHelper._3Dias();
            if (fecha != null) {
                hace3Dias = DateHelper.dateToSYYYYMMDD(fecha);

            } else {
                hace3Dias = DateHelper._3Dias();
            }

            List<String> lstComprob = service.comprobXFecha(hace3Dias);
            String resume = "";
            String previoUpdate = "\n";
            String update = "\n";
            String fech = "";
            String[] obj = new String[4];
            String selectFaltantes = "";
            String updateCargados = "";
            String updateCargados2 = "";
            int contador = 1;
            if (lstComprob != null) {

                for (String lst : lstComprob) {
                    rw("comprobandte_" + lst);
                    if (!lst.equals("VES")) {
                        List<Servicios> values = service.revisarServiciosByComprob(hace3Dias, lst);//1000
                        List<Integer> hay = new ArrayList<Integer>();
                        for (Servicios srv : values) {
                            hay.add(new Integer(srv.getNumero()));
                        }
                        String arrFaltan[] = new EvaluarFaltantes().evalFaltantes(hay, lst);
                        String sqlToVPS = arrFaltan[0];
                        updateCargados += "\n" + arrFaltan[1];
                        updateCargados2 += "\n" + arrFaltan[2];
                        rw("total_comprobantes_" + arrFaltan.length);
                        //selectFaltantes += "\n " + sqlToVPS;
                        if (sqlToVPS != null && !sqlToVPS.isEmpty()) {
                            if (contador > 1 && contador <= lstComprob.size()) {
                                selectFaltantes += "\n union all ";
                                selectFaltantes += "\n " + sqlToVPS;
                            } else if (contador == 1) {
                                selectFaltantes += "\n " + sqlToVPS;
                            }
                            contador++;
                        }
                    }

                }
                rw("sql_faltan: " + selectFaltantes);
                rw("sql_update: " + updateCargados);
                values2[0] = selectFaltantes;
                values2[1] = updateCargados;
                values2[2] = hace3Dias;
                values2[3] = updateCargados2;
            } else {
                rw("NO HAY FACTURAS");
            }
            return values2;
        } catch (Exception e) {
            rwS("No existen registros: " + e.getCause());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String updateSQLPendientesVPS(String json) throws Exception {
        //  rw("SQL=> " + json);
        try {
            JsonDTO[] entra = new Gson().fromJson(json, JsonDTO[].class);
            List<Servicios> allServicios = new ArrayList<Servicios>();

            RtaDTO rtaDTO = new RtaDTO();
            rw("\nsqlFaltan: " + entra[0].sqlFaltan + "\n updateLoad: " + entra[0].sqlUpdate + "\n fecha: " + entra[0].fecha);
            if (entra[0].sqlUpdate != null && !entra[0].sqlUpdate.isEmpty()) {
                service.updateSQLPendientesVPS(entra[0].sqlUpdate, entra[0].fecha);
            }
            if (entra[0].sqlFaltan != null && !entra[0].sqlFaltan.isEmpty()) {
                List<Servicios> srvFaltantes = service.srvNoCargados(entra[0].sqlFaltan);//hsa
                if (srvFaltantes != null) {
                    allServicios.addAll(srvFaltantes);
                }
            }
            //String hace3Dias = DateHelper._30Dias(new Date(), (60 * 24 * 30));// hace 3 dias
            String hace3Dias = DateHelper._3Dias();// hace 3 dias

            List<Servicios> srvNuevos = service.nuevoServicios(hace3Dias);

            if (srvNuevos != null) {
                allServicios.addAll(srvNuevos);
            }

            List<JsonDTO> srvPendientes = new ArrayList<JsonDTO>();
            rtaDTO.setError("no");
            if (allServicios != null && allServicios.size() > 0) {
                int i = 0;
                rw("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                rw(" %%%%%%%%%%%%%%%%%%  FACTURAS PENDIENTES: " + allServicios.size());

                for (Servicios srv : allServicios) {
                    //  if (i < 50 && (srv.getComprob().equals("VCH") && srv.getNumero() > 144)) { // se salgo el consecutivo de VCH
                    JsonDTO j = new JsonDTO();
                    j.setId(srv.getId().toString());
                    j.setAddres(Utilidades.remplazNoOk(Utilidades.eliminarBarusar(srv.getAddres())));
                    j.setFecha(toDateYYYYMMDDHHMM(srv.getFechaGrab()));
                    j.setIdUsuario(srv.getUsuarios().getId().toString());
                    j.setValor(getNumberInt(srv.getValor()));
                    j.setBase(getNumberInt(srv.getBase()));
                    j.setPlaca(srv.getAutoPlaca());
                    j.setTipoPago(srv.getTipoPago());
                    j.setNit(srv.getNit().toString());
                    j.setIdSitio(srv.getSitio().getId().toString());
                    j.setGrabador(srv.getGrabador());
                    j.setComprob(srv.getComprob());
                    j.setFechaGrab(toDateYYYYMMDDHHMM(srv.getFechaGrab()));
                    j.setIndProc(srv.getIndProc() != null ? srv.getIndProc().toString() : null);
                    j.setNumero(srv.getNumero().toString());
                    j.setEstadoId(srv.getEstado().getId().toString());
                    j.setCierre(getNumberInt(srv.getCierre()));
                    j.setValorE(getNumberInt(srv.getValorE()));
                    j.setValorC(getNumberInt(srv.getValorC()));
                    j.setValorD(getNumberInt(srv.getValorD()));
                    j.setPorque(srv.getPorque());
                    Integer valoro = Utilidades.getNumberIntNito(srv);
                    j.setValoro(valoro.toString());
                    j.setBase("" + srv.getBase());

                    rtaDTO.setError("ok");
                    srvPendientes.add(j);
                    rtaDTO.setPendientes("" + (50));
                    //  } else {
                    rtaDTO.setPendientes("" + (allServicios.size()));
                    //s  }
                    i++;
                }
                rtaDTO.setAlertas(srvPendientes);
                rtaDTO.setKeyCommand("18");
                rtaDTO.setPendientes("" + (allServicios.size()));
            }
            String RTA = new Gson().toJson(rtaDTO, RtaDTO.class);
            rw("RTA: " + RTA);
            return RTA;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String misCarrosVANGO() throws Exception {
        try {
            List<JsonDTO> carros = service.misCarrosVANGO();
            String gson = new Gson().toJson(carros);
            rw("RTA_" + gson);
            return gson;
        } catch (Exception e) {
            e.printStackTrace();
            JsonDTO carros = new JsonDTO();
            carros.setError("No existen registros");
            String gson = new Gson().toJson(carros);
            rw("RTA_" + gson);
            return gson;
        }

    }

    @Override
    public String misCarros() throws Exception {

        List<TempVehiculo> bloques = service.misCarros();
        JsonDTO carritos = new JsonDTO();
        if (bloques != null && bloques.size() > 0) {
            List<Ticket> car = new ArrayList<Ticket>();
            for (TempVehiculo tem : bloques) {
                Ticket tk = new Gson().fromJson(tem.getJson(), Ticket.class);
                car.add(tk);
            }
            carritos.setVehiculos(car);
            carritos.setError("ok");
        } else {
            carritos.setError("no");
        }
        String gson = new Gson().toJson(carritos);
        return gson;

    }

    @Override
    public String actualizarContainer(String placa) throws Exception {
        String rta = "no";
        try {
            Integer totales = 0;
            if (!new isProduccion().isProduccion().equals("VPS")) {//subir al vps los registros nuevos
                List<String> lstPlaca = service.allCar(placa);// NFB001 MAY564 --259
                for (String myPlaca : lstPlaca) {
                    String jsonVPS = (gateWayConnection(Utilidades.Encriptar(Utilidades.getJsonPlaca(myPlaca))));
                    if (Utilidades.isOK(jsonVPS)) {
                        Ticket obj = new Gson().fromJson(jsonVPS, Ticket.class);
                        if (obj != null && obj.getError().equals("ok")) {
                            String jsonVPS2 = (gateWayConnection(Utilidades.Encriptar(jsonVPS)));
                        }
                    }
                    Thread.sleep(1000);
                    totales++;
                }
            }
            rta = "" + totales;
        } catch (Exception e) {
            rta = "no";
        }
        return rta;
    }

    @Override
    public String test() {
        String value = "RTA correcta desde la interzar remota";
        rw(value);
        return value;
    }

    @Override
    @WebMethod
    public void crearCopiaRespaldo(String jsonInt) {
        rw("=============xxxxxxx=========");

        try {
            if (!jsonInt.startsWith("[")) {
                jsonInt = "[" + jsonInt;
            }

            if (!jsonInt.endsWith("]")) {
                jsonInt = jsonInt + "]";
            }
            rw("jsonInt_" + jsonInt);

            Ticket[] tiqueteTmp = new Gson().fromJson(jsonInt, Ticket[].class);
            Ticket tiqueteNuevo = tiqueteTmp[0];
            //  JsonDTO[] entra = gson.fromJson(pesos, JsonDTO[].class);

            Servicios srv = new Servicios();
            srv.setComprob(tiqueteNuevo.getComprob());
            srv.setNumero(new Integer(tiqueteNuevo.getNumeroFactura()));

            srv.setAddres(Utilidades.eliminarBarusar(tiqueteNuevo.getDestino()));
            srv.setFecha(toDateYYYYMMDDHHMM(tiqueteNuevo.getFecha()));
            srv.setFechaGrab(toDateYYYYMMDDHHMM(tiqueteNuevo.getFechaGrab()));

            srv.setValor(getNumberInt(tiqueteNuevo.getValor()));
            srv.setAutoPlaca(tiqueteNuevo.getPlaca());
            srv.setTipoPago(tiqueteNuevo.getTipoPago());
            srv.setNit(getNumberLong(tiqueteNuevo.getNit()));

            srv.setIndProc(getNumberInt(tiqueteNuevo.getIndProc()));
            srv.setBase(getNumberInt(tiqueteNuevo.getBase()));

            srv.setSitio(new Sitios(getNumberInt(tiqueteNuevo.getSitio().toString())));
            srv.setUsuarios(new CajerosAero(getNumberInt(tiqueteNuevo.getIdUsuario().toString())));
            srv.setEstado(new Estados(27));

            srv.setTipoPago("EFECTIVO");
            // colocar el grabador...
            srv.setGrabador(tiqueteNuevo.getGrabador());

            srv.setFechaGrab(srv.getFecha());
            srv.setCierre(getNumberInt(tiqueteNuevo.getCierre()));
            srv.setValorE(getNumberInt(tiqueteNuevo.getValorE()));
            srv.setValorC(getNumberInt(tiqueteNuevo.getValorC()));
            srv.setValorD(getNumberInt(tiqueteNuevo.getValorD()));
            srv.setAddres(tiqueteNuevo.getDireccion());
            srv.setTransaccion(tiqueteNuevo.getTransaccion());
            srv.setId(new Integer(tiqueteNuevo.getConsecutivo()));
            srv.setValorC(getNumberInt(tiqueteNuevo.getValor()));
            srv.setBase(getNumberInt(tiqueteNuevo.getValor()));
            srv.setJson(jsonInt);

            srv.setBase(srv.getValor());
            String host = InetAddress.getLocalHost().getHostName();
            if (host.contains("hostname5615") || host.contains("vpsnet")) {
                Servicios srv2 = service.crearServicioRespaldo(srv);
            } else {
                rw("SERVICIO_NO_PERMITIDO_EN_SRV_" + host);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
