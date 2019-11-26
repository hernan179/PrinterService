/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.activate;

import com.google.gson.Gson;
import com.rta.opain.delegate.dto.Cierre;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.Persona;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.delegate.tools.DateHelper;
import com.rta.opain.delegate.tools.LogTest;
import com.rta.opain.delegate.tools.Md5Helper;
import com.rta.opain.delegate.tools.SendMail;
import com.rta.opain.domain.CajerosAero;
import com.rta.opain.domain.Lugares;
import com.rta.opain.domain.RegisterInOut;
import com.rta.opain.domain.Servicios;
import com.rta.opain.domain.ServiciosEspecial;
import com.rta.opain.domain.Sitios;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.delegate.tools.StringTools;
import com.rta.opain.delegate.tools.TreeSetTools;
import com.rta.opain.delegate.tools.Utilidades;
import static com.rta.opain.delegate.tools.Utilidades.getNumberInt;
import com.rta.opain.delegate.tools.isProduccion;
import com.rta.opain.domain.Estados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import static com.rta.opain.delegate.tools.Utilidades.getStringValue;
import com.rta.opain.domain.Bloqueo;
import com.rta.opain.domain.FuecService;
import com.rta.opain.domain.SrvPreview;
import com.rta.opain.domain.TempVehiculo;
import com.rta.opain.pdf.GenerarCierre;
import java.util.Set;
import javax.persistence.NoResultException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Multipart;
import javax.mail.internet.*;

/**
 *
 * @author Paola
 */
@Stateless
public class PrinterServicesActivate implements PrinterServicesActivateRemote {

    @PersistenceContext(unitName = "PrinterServicePU")
    private EntityManager em;
    @PersistenceContext(unitName = "PrinterServicevpsPU")
    private EntityManager emVps;

    public DataSource dsGetImperialDS() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/imperialDS");
        return ds;
    }

    /*public DataSource dsGetvpsDS() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/NumeralCienvpsDS");
        return ds;
    }*/
    public DataSource dsGetYellowDS() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/YellowPagos");
        return ds;
    }

    public DataSource dsGetOperativaDS() throws Exception {
        try {
            //if (new isProduccion().isProduccion().equals("AERO")) {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/OperativaDS");
            rw("Conexion VPS/OPERATIVA  EXITOSA!");
            return ds;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String[] cargarVehiculoFromCajas(String placa) throws Exception {

        String[] datos = new String[2];
        try {
            String sql = "";
            sql += " select a.placa,m.descrimar,a.modelo ";
            sql += " from auto a";
            sql += " join marca m on(m.cod_mar=a.cod_mar)";
            sql += " where a.placa=" + "'" + placa + "'";
            rw(sql);

//
//            Auto auto = null;
//            try {
//                String sql2 = "SELECT a FROM Auto a WHERE a.placa =:placa";
//                Query query = em.createQuery(sql2);
//                query.setParameter("placa", placa);
//                auto = (Auto) query.getSingleResult();
//
//            } catch (Exception e) {
//                return null;
//
//            }
//
//            log.info("marca =>    " + auto.getCodMar().getDescrimar());
//            datos[0] = auto.getCodMar().getDescrimar();
//            datos[1] = auto.getModelo();
            //return null;
        } catch (Exception e) {

            return null;

        }
        return datos;

    }

    @Override
    public String[] sincronizarPagos(String placa) throws Exception {
        return cargarVehiculoFromCajas(placa);
//       List<String> sincronizarPagos = new ArrayList<String>();
//       for(int i=0;i<ss.length;i++){
//           
//       }
//       return ss;
//        try {
//            String sql = "SELECT s FROM Servicios s WHERE s.autoPlaca = :autoPlaca";
//            Query query = em.createQuery(sql);
//            query.setParameter("autoPlaca", placa);
//            List<Servicios> lSrv = (List<Servicios>) query.getResultList();
//            return lSrv;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    @Override
    public Servicios lastSrvByNumeroComprob(Integer numero, String comprob) throws Exception {
        try {
            rw("Buscando servicio: " + numero + " comprob: " + comprob);
            String sql = "SELECT s FROM Servicios s WHERE s.comprob = :comprob and s.numero = :numero";
            Query query = em.createQuery(sql);
            query.setParameter("comprob", comprob);
            query.setParameter("numero", numero);
            Servicios srv = (Servicios) query.getSingleResult();
            em.flush();
            return srv;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updatePlaca(Servicios serv) throws Exception {
        try {
            em.merge(serv);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public FuecService crearServicioFuec(FuecService servicio) throws Exception {
        try {
            rw("GUARDANDO...");
            em.persist(servicio);
            em.flush();
            rw("GUARDADO...OK");
            return servicio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Servicios crearServicioRespaldo(Servicios servicio) throws Exception {
        try {
            // String sServicio = new Gson().toJson(servicio);

            rw("GUARDANDO...");
            em.persist(servicio);
            em.flush();
            rw("GUARDADO...OK");

            return servicio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Servicios crearServicio(Servicios servicio) throws Exception {
        try {
            if (servicio.getEstado() == null) {
                rw("Es necesario especificar el estado del servicio");
                servicio.setEstado(new Estados(27));
            }
            em.persist(servicio);
            rw("aero: servicio creado con el numero: " + servicio.getNumero());
            em.flush();
            rw("rta srv: servicio creado con el numero: " + servicio.getNumero());
            return (servicio);
        } catch (Exception e) {
            e.printStackTrace();
            rw("aero: NO FUE POSIBLE PROCESAR SU SOLICITUD " + e.getCause());
            return null;
        }
    }

    @Override
    public Servicios updateSrvContable(String comprob, Integer numero) throws Exception {

        try {
            String sql = "SELECT s FROM Servicios s WHERE s.comprob = :comprob and s.numero = :numero";
            Query query = emVps.createQuery(sql);
            query.setParameter("comprob", comprob);
            query.setParameter("numero", numero);
            Servicios srv = (Servicios) query.getSingleResult();
            srv.setIndProc(1);
            emVps.merge(srv);
            return srv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TempVehiculo save(TempVehiculo tempCar) throws Exception {
        try {
            tempCar = em.merge(tempCar);
            return tempCar;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public boolean empresaGrupo(String placa) throws Exception {
        Connection con = null;
        boolean estado = false;
        try {

            String sql = "select e.emp_idempresa,v.veh_placa,e.emp_nomempresa,e.emp_grupo\n"
                    + "    from top_vehiculo v,top_empresa e \n"
                    + "        where \n"
                    + "        v.emp_idempresa = e.emp_idempresa\n"
                    + "        and v.veh_placa = '" + placa + "'";
            

            con = dsGetOperativaDS().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                estado = rs.getBoolean("emp_grupo");
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            rw("empresa_grupo_"+placa+"_rta_"+estado);
            return estado;
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return false;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RtaDTO documentosVencidos(String placa) throws Exception {
        RtaDTO rta = new RtaDTO();
        Connection con = null;
        try {

            List<JsonDTO> conductores = new ArrayList<JsonDTO>();
            String sql = "select per_numidenti,tar_fechven , case when tar_fechven < current then 'VENCIDO' else 'OK' end AS DOCS\n"
                    + "    from top_tarjetacontrol \n"
                    + "where veh_placa = '" + placa + "'";
            rw("sql_" + sql);
            con = dsGetOperativaDS().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rta.setError("no");
            rta.setMsjError("DOCS VENCIDOS");
            while (rs.next()) {
                rw("validar_tarjeton_" + rs.getString("docs"));
                JsonDTO tk = new JsonDTO();
                tk.setCedula(rs.getString("per_numidenti"));
                tk.setFecha(rs.getString("tar_fechven"));
                tk.setBloqueo(rs.getString("docs"));
                if (rs.getString("docs") != null && rs.getString("docs").equals("VENCIDO")) {
                    rta.setError("no");
                    rta.setMsjError("DOCS VENCIDOS");
                } else {
                    rta.setError("ok");
                    rta.setMsjError("HABILITADO");
                }
                conductores.add(tk);
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }

            rta.setAlertas(conductores);
            return rta;
        } catch (Exception e) {
            e.printStackTrace();
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return rta;
        }
    }

    @Override
    public Bloqueo bloqueoByPlaca(String placa) throws Exception {
        try {
            String sql = "select * from bloqueo where id_estado = 1 and placa = '" + placa + "' order by fecha desc limit 1";
            rw("sql_" + sql);
            Bloqueo bloqueo = (Bloqueo) em.createNativeQuery(sql, Bloqueo.class).getSingleResult();
            return bloqueo;

        } catch (Exception e) {
            //  e.printStackTrace();

            return null;
        }
    }

    @Override
    public TempVehiculo containerPlaca(String placa) throws Exception {
        try {
            String sql = "SELECT s FROM TempVehiculo s WHERE s.autoPlaca = :autoPlaca order by autoPlaca";
            Query query = em.createQuery(sql);
            rw("SQL: " + sql);
            query.setParameter("autoPlaca", placa);
            // query.setParameter("idEstado", 23);
            TempVehiculo plc = (TempVehiculo) query.getSingleResult();
            String gson = new Gson().toJson(plc);
            rw("CONTAINER_VEHICULO_" + gson);
            if (plc != null && plc.getIdEstado() != null && plc.getIdEstado().toString().equals("23")) {
                return null;
            }
            return plc;
        } catch (Exception e) {
            //e.printStackTrace();
            rw("Contianer no ENCONTRADO");
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Ticket optenerConductor(String placa, Ticket tiquete) throws Exception {
        Connection con = null;
        Ticket tk = new Ticket();
        List<Persona> lstPersona = new ArrayList<Persona>();
        List<Persona> lstPersona2 = new ArrayList<Persona>();
        try {
            String sql = "SELECT DISTINCT v.veh_placa,v.veh_modelo ,m.mar_nommarca ,v.veh_numinterno, c.cla_nomclase,p.per_numidenti\n"
                    + ",pe.per_nombre[1,40] f,d.doc_numeroper ,pe.per_nombre[41,80] a,d.doc_fechavenper,de.doc_numeroveh, de.doc_fechavenveh,e.emp_nomempresa    \n"
                    + "          FROM top_vehiculo v, top_persovehiculo p, top_linea l, top_marca m,top_clase_vehiculo c, top_persona pe,top_documenpers d, top_documenveh de, top_empresa e    \n"
                    + "         WHERE p.veh_codigo = v.veh_codigo    \n"
                    + "           AND p.per_numidenti = pe.per_numidenti    \n"
                    + "           AND d.doc_cedula = p.per_numidenti    \n"
                    + "           AND pe.suc_cod = v.suc_cod    \n"
                    + "        AND v.cla_idclaseveh = c.cla_idclaseveh    \n"
                    + "           AND v.lin_idlinea = l.lin_idlinea    \n"
                    + "           AND l.mar_idmarca = m.mar_idmarca    \n"
                    + "           AND v.veh_placa = de.doc_vehplaca    \n"
                    + "           AND v.emp_idempresa = e.emp_idempresa    \n"
                    + "           AND v.veh_placa = '" + placa + "'    \n"
                    + "           AND p.tip_idtipoper IN (1,3)    \n"
                    + "           AND d.tip_codigo = 6\n"
                    + "           AND de.tip_codigo = 4\n"
                    + "           AND v.suc_cod=12";

            sql = "SELECT  v.veh_placa,v.veh_modelo ,m.mar_nommarca ,v.veh_numinterno, c.cla_nomclase,p.per_numidenti,\n"
                    + "        pe.per_nombre[1,40] f,d.doc_numeroper ,pe.per_nombre[41,80] a,doc_fechavenper\n"
                    + " ,(SELECT doc_numeroveh FROM top_documenveh WHERE tip_codigo = 4 AND doc_vehplaca ='" + placa + "' ) as doc_numeroveh \n"
                    + " ,(SELECT doc_fechavenveh FROM top_documenveh WHERE tip_codigo = 4 AND doc_vehplaca ='" + placa + "' ) as doc_fechavenveh\n"
                    + " ,d.doc_fechavenper,e.emp_nomempresa,v.cap_idcapaci as pasajeros\n"
                    + " FROM top_vehiculo v, top_persovehiculo p, top_linea l, top_marca m,top_clase_vehiculo c, top_persona pe,top_documenpers d, \n"
                    + "     top_empresa e\n"
                    + " WHERE p.veh_codigo = v.veh_codigo\n"
                    + " AND p.per_numidenti = pe.per_numidenti\n"
                    + " AND d.doc_cedula = p.per_numidenti\n"
                    + " AND v.cla_idclaseveh = c.cla_idclaseveh\n"
                    + " AND v.lin_idlinea = l.lin_idlinea\n"
                    + " AND l.mar_idmarca = m.mar_idmarca\n"
                    + " AND v.emp_idempresa = e.emp_idempresa\n"
                    + " AND v.veh_placa = '" + placa + "' \n"
                    + " AND d.tip_codigo = 6\n"
                    + " AND v.suc_cod =  12\n"
                    + " AND pe.suc_cod = 12\n"
                    + " AND e.suc_cod =  12\n"
                    + " AND d.suc_cod =  12";

            rw("sql conductor:  " + sql);

            con = dsGetOperativaDS().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17;18;19
//CONVENIO;NIT;PLACA;MODELO;MARCA;CLASE;NUMERO INTERNO;TARJETA OPERACION;CONDUCTOR 1;C EDULA;LICENCIA CONDUCCION;VIGENCIA;CONDUCTOR 2;CEDULA;LICENCIA CONDUCCIàN;VIGENCIA;RESPONSABLE DEL CONTRATANTE;CEDULALA;TELEFONO;DIRECCION
            rw("START");
            int i = 0;
            tk.setError("no");

            while (rs.next()) {
                tk.setError("ok");
                rw("i => " + i + "_OK_" + tk.getError());
                Persona p = new Persona();

                tk.setNumeroTarjetaOpe(getStringValue(rs.getString("doc_numeroveh")));
                p.setNombreConductor1(getStringValue(rs.getString("f")));
                p.setApellidoConductor1(getStringValue(rs.getString("a")));
                p.setCcConductor1(getStringValue(rs.getString("doc_numeroper")));
                p.setLicenciaConductor1(p.getCcConductor1());
                p.setVigenciaConductor1(getStringValue(rs.getString("doc_fechavenper")));
                p.setEmpresaVehiculo(getStringValue(rs.getString("emp_nomempresa")));
                if (!lstPersona.contains(p)) {
                    lstPersona.add(p);
                }
                tk.setEmpresaVehiculo(getStringValue(rs.getString("emp_nomempresa")));
                tk.setPasajeros(getStringValue(rs.getString("pasajeros")));
                tk.setPasajero(getStringValue(rs.getString("pasajeros")));
                if (i == 0) {
                    tk.setNombreConductor1(getStringValue(rs.getString("f")));
                    tk.setApellidoConductor1(getStringValue(rs.getString("a")));
                    tk.setCcConductor1(getStringValue(rs.getString("doc_numeroper")));
                    tk.setLicenciaConductor1(getStringValue(rs.getString("per_numidenti")));
                    tk.setNitConductor(getStringValue(rs.getString("doc_numeroper")));
                    tk.setVigenciaConductor1(getStringValue(rs.getString("doc_fechavenper")));
                    tk.setEmpresaVehi(getStringValue(rs.getString("emp_nomempresa")));

                    tk.setPlaca(getStringValue(rs.getString("veh_placa")));

                } else if (i == 1) {
                    rw("Segundo conductor del vehiculo");
                    tk.setNombreConductor2(getStringValue(rs.getString("f")));
                    tk.setApellidoConductor2(getStringValue(rs.getString("a")));
                    tk.setCcConductor2(getStringValue(rs.getString("doc_numeroper")));
                    tk.setLicenciaConductor2(getStringValue(rs.getString("per_numidenti")));
                    tk.setNitConductor2(getStringValue(rs.getString("doc_numeroper")));
                    tk.setVigenciaConductor2(getStringValue(rs.getString("doc_fechavenper")));
                    tk.setEmpresaVehi(getStringValue(rs.getString("emp_nomempresa")));
                } else {
                    rw("Segundo conductor del vehiculo");
                    tk.setNombreConductor3(getStringValue(rs.getString("f")));
                    tk.setApellidoConductor3(getStringValue(rs.getString("a")));
                    tk.setCcConductor3(getStringValue(rs.getString("doc_numeroper")));
                    tk.setLicenciaConductor3(getStringValue(rs.getString("per_numidenti")));
                    tk.setNitConductor3(getStringValue(rs.getString("doc_numeroper")));
                    tk.setVigenciaConductor3(getStringValue(rs.getString("doc_fechavenper")));
                    tk.setEmpresaVehi(getStringValue(rs.getString("emp_nomempresa")));
                }
                tk.setError("ok");

                i++;
            }

            if (tk.getError().equals("ok")) {
                rw("TODO_CORRECTO_UPLOADING..." + tk.getError());
                Persona p = new Persona();
                p.setNombreConductor1(tiquete.getNombreResponsable());
                p.setApellidoConductor1(tiquete.getApellidoResponsable());
                p.setCcConductor1(tiquete.getCcResponsable());
                p.setEmpresaVehiculo(tiquete.getEmpresaVehi());
                p.setLicenciaConductor1(tiquete.getCcResponsable());
                tk.setError("ok");
                if (!lstPersona.contains(p)) {
                    lstPersona.add(p);
                }
            } else {
                rw("No hay registros validos..." + tk.getError());
            }

            Set<Persona> myDir = TreeSetTools.treeSetDireccion(lstPersona);

            for (Persona p2 : myDir) {
                lstPersona2.add(p2);
            }

            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rw("Error:" + e.getMessage());
            tk.setError("no");
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        tk.setPersonas(lstPersona2);
        return (tk);
    }

    @Override
    public Long optenerPagdor(String placa) throws Exception {
        String fila = "";
        Connection con = null;
        Long nits = 0l;
        try {

            String sql2 = "SELECT p.per_numidenti\n"
                    + "FROM top_movil m, top_persovehiculo p, top_vehiculo v\n"
                    + "WHERE m.per_idperve = p.per_idperve\n"
                    + "AND p.veh_codigo = v.veh_codigo\n"
                    + "AND v.veh_placa = '" + placa + "' AND v.suc_cod=12"; // and per_pagador = 1 ;

            rw("sql2 propieta:  " + sql2);

            /*sql2 = "SELECT DISTINCT v.veh_placa,v.veh_modelo ,m.mar_nommarca ,v.veh_numinterno, c.cla_nomclase,p.per_numidenti,pe.per_nombre[1,40] f,pe.per_nombre[41,80] a\n"
                    + "          FROM top_vehiculo v, top_persovehiculo p, top_linea l, top_marca m,top_clase_vehiculo c\n"
                    + ", top_persona pe\n"
                    + "         WHERE p.veh_codigo = v.veh_codigo\n"
                    + "           AND p.per_numidenti = pe.per_numidenti\n"
                    + "           AND v.cla_idclaseveh = c.cla_idclaseveh\n"
                    + "           AND v.lin_idlinea = l.lin_idlinea\n"
                    + "           AND l.mar_idmarca = m.mar_idmarca\n"
                    + "           AND v.veh_placa = '" + placa + "'\n"
                    + "           AND p.tip_idtipoper = 1  AND v.suc_cod=12";*/
            rw("sql22 propieta:  " + sql2);

            DataSource operativaDS = dsGetOperativaDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs2 = stmt.executeQuery(sql2);
            String sNit = "0";
            int contador = 0;
            while (rs2.next()) {
                contador++;
                if (contador < 2) {
                    sNit = rs2.getString("per_numidenti").trim();
                    nits = new Long(sNit);
                }

            }
            if (con != null && !con.isClosed()) {
                con.close();
            }

        } catch (Exception e) {
            rw("Error al consultar el nits agador: " + e.getMessage());
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
        }
        return nits;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Ticket optenerPropieta(String placa) throws Exception {
        String fila = "";
        Connection con = null;
        Ticket tk = new Ticket();
        try {

            String sql2 = "SELECT DISTINCT v.veh_placa,v.veh_modelo ,m.mar_nommarca ,v.veh_numinterno, c.cla_nomclase,p.per_numidenti,pe.per_nombre[1,40] f,pe.per_nombre[41,80] a \n"
                    + "          FROM top_vehiculo v, top_persovehiculo p, top_linea l, top_marca m,top_clase_vehiculo c\n"
                    + ", top_persona pe \n"
                    + "         WHERE p.veh_codigo = v.veh_codigo \n"
                    + "           AND p.per_numidenti = pe.per_numidenti \n"
                    + "           AND v.cla_idclaseveh = c.cla_idclaseveh \n"
                    + "           AND v.lin_idlinea = l.lin_idlinea \n"
                    + "           AND l.mar_idmarca = m.mar_idmarca \n"
                    + "           AND v.veh_placa = '" + placa + "' \n"
                    + "           AND p.tip_idtipoper = 1  AND v.suc_cod=12 ";

            rw("sql2 propieta:  " + sql2);
            DataSource operativaDS = dsGetOperativaDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery(sql2);

            int i = 0;
            while (rs2.next()) {

//tk.setEmpresa(getStringValue(rs2.getString("viajes")));
                tk.setPlaca(getStringValue(rs2.getString("veh_placa")));
                tk.setModelo(getStringValue(rs2.getString("veh_modelo")));
                tk.setMarca(getStringValue(rs2.getString("mar_nommarca")));
                tk.setNumeroInterno(getStringValue(rs2.getString("veh_numinterno")));
                tk.setClase(getStringValue(rs2.getString("cla_nomclase")));
                tk.setNit(getStringValue(rs2.getString("per_numidenti")));
                tk.setError("ok");
                tk.setDireccionResponsable("");
                tk.setNombreResponsable(getStringValue(rs2.getString("f")));
                tk.setApellidoResponsable(getStringValue(rs2.getString("a")));
                tk.setCcResponsable(getStringValue(rs2.getString("per_numidenti")));

            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            rw("error;: " + e.getMessage());
            tk.setError("no");
            if (con != null) {
                con.close();
            }
        }
        return tk;
    }

    @Override
    public List<String> comprobEnFecha(String fecha, String fecha2) throws Exception {
        try {
            // donde fecha debe ser asi: 01-08-2015
            String sqlComprob = "SELECT grabador FROM srv_camioneta \n"
                    + "where  fecha >= TO_DATE('" + fecha + "','%Y-%m-%d %H:%M') and fecha <= TO_DATE('" + fecha2 + "','%Y-%m-%d %H:%M') \n"
                    + "group by  grabador ";
            if (new isProduccion().isProduccion().equals("VPS")) {
                // donde fecha debe ser asi: 2015/08/09 00:00
                sqlComprob = "SELECT grabador FROM srv_camioneta \n"
                        + "where  fecha >= ('" + fecha + "') and fecha <= ('" + fecha2 + "') \n"
                        + "group by  grabador";
            }
            rw("SQL: " + sqlComprob);
            Query query = em.createNativeQuery(sqlComprob);
            List<String> VJS = query.getResultList();

            return VJS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object> serviciosByUserByFechas(String grabador, String fecha, String fecha2) throws Exception {

        try {
            String sqlComprob = "select (select nombre from cajeros_aero where usuario like '%" + grabador + "%'),count(sitios_id) CANTIDAD,(select contacto from sitios where id = sitios_id) DISPOSITIVO,min(numero) INICIALES,max(numero) FINALES \n"
                    + ",sum(valore) EFECTIVO,sum(valord) DEBITO,sum(valorc) CREDITO,sum(valore+valord+valorc) TOTAL\n"
                    + "from srv_camioneta \n"
                    + "where grabador = '" + grabador + "' and fecha >= TO_DATE('" + fecha + "','%Y-%m-%d %H:%M') and fecha <= TO_DATE('" + fecha2 + "','%Y-%m-%d %H:%M') \n"
                    + "group by sitios_id,grabador";
            if (new isProduccion().isProduccion().equals("VPS")) {
                // donde fecha debe ser asi: 2015/08/09 00:00
                sqlComprob = "select grabador,count(sitios_id) CANTIDAD,(select contacto from sitios where id = sitios_id) DISPOSITIVO,min(numero) INICIALES,max(numero) FINALES \n"
                        + ",sum(valore) EFECTIVO,sum(valord) DEBITO,sum(valorc) CREDITO\n"
                        + "from srv_camioneta \n"
                        + "where grabador = '" + grabador + "' and fecha >= ('" + fecha + "') and fecha <= ('" + fecha2 + "') \n"
                        + "group by sitios_id,grabador";
            }
            rw("SQL: " + sqlComprob);
            Query query = em.createNativeQuery(sqlComprob);
            List<Object> VJS = query.getResultList();
            return VJS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RtaDTO crearCintaTestigo(String fecha, String fecha2) throws Exception {
        RtaDTO respuesta = new RtaDTO();
        try {
            respuesta.setError("no");
            respuesta.setMsjError("No existen facturas en las fechas seleccionadas");

            List<String> comprobEnFecha = comprobEnFecha(fecha, fecha2);
            List<JsonDTO> lstCintas = new ArrayList<JsonDTO>();
            for (String comprob : comprobEnFecha) {
                List<Object> results = serviciosByUserByFechas(comprob, fecha, fecha2);
                if ((results != null && results.size() > 0)) {
                    for (Object oRow : results) {
                        respuesta.setError("ok");
                        respuesta.setMsjError("Listado de facturas cinta testigo");
                        Object[] r = (Object[]) oRow;
                        String grabador = "" + r[0];
                        String cantidad = "" + r[1];
                        String dispositivo = "" + r[2];
                        String iniciales = "" + r[3];
                        String finales = "" + r[4];
                        Integer efectivo = new Double("" + r[5]).intValue();
                        Integer debito = new Double("" + r[6]).intValue();
                        Integer credito = new Double("" + r[7]).intValue();
                        Integer totales = new Double("" + r[8]).intValue();
                        JsonDTO cinta = new JsonDTO();
                        cinta.setGrabador(grabador);
                        cinta.setCantidad(cantidad);
                        cinta.setDispositivo(dispositivo);
                        cinta.setInicio(iniciales);
                        cinta.setFin(finales);
                        cinta.setEfectivo(efectivo.toString());
                        cinta.setDebito(debito.toString());
                        cinta.setCredito(credito.toString());
                        cinta.setTotal(totales.toString());
                        cinta.setFecha(fecha);
                        cinta.setFechaFin(fecha2);
                        lstCintas.add(cinta);

                    }
                }
            }
            respuesta.setAlertas(lstCintas);

            return respuesta;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> comprobXFecha(String fecha) throws Exception {
        try {
            // donde fecha debe ser asi: 01-08-2015
            String sqlComprob = "select comprob from srv_camioneta where fecha >= TO_DATE('" + fecha + "','%Y-%m-%d') group by comprob";
            if (new isProduccion().isProduccion().equals("VPS")) {
                // donde fecha debe ser asi: 2015/08/09 00:00
                sqlComprob = "select comprob from srv_camioneta where fecha >= ('" + fecha + "')  and cierre = 1 group by comprob";
            }
            rw("SQL: " + sqlComprob);
            Query query = em.createNativeQuery(sqlComprob);
            List<String> VJS = query.getResultList();

            return VJS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Servicios saveServicio(Servicios servicio) throws Exception {
        try {

            Servicios srv = _servicio(new Long(servicio.getNumero()), servicio.getComprob());
            if (srv == null) {
                //rw("SERVICIO: " + servicio);
                Integer id = generateId("id", Servicios.class.getSimpleName());
                // rw("nuevo ids: " + id);
                servicio.setId(id);
                servicio = em.merge(servicio);
                rw("CARGADO NUEVO  NUMERO: " + servicio.getNumero() + " comprob: " + servicio.getComprob());
                return (servicio);
            } else {
                // rw("CARGADO EXISTENTE NUMERO: " + servicio.getNumero() + " comprob: " + servicio.getComprob());
                return srv;
            }

        } catch (Exception e) {
            //e.printStackTrace();
            //rw("CARGUE " + e.getCause() + "  srv: " + servicio.getNumero() + " comprob: " + servicio.getComprob() + " mensaje: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void lRegistrosJK(Integer codigo) throws Exception {
        try {
            String sql = " SELECT a.*, m.numero, i.nombre FROM auto a JOIN movil m ON a.placa = m.placa ";
            sql += " JOIN identi i ON m.placa = i.placauto ";
            sql += " WHERE a.placa = 'JOH477' AND a.estado != 'R' AND i.cedula = 222 AND m.numero = 300001 ";

            rw("SQL:= " + sql);

            Query query2 = em.createNativeQuery(sql);
            List<Object> results = query2.getResultList();

            RtaDTO consolidado = new RtaDTO();
            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();
            for (Object oRow : results) {
                Object[] r = (Object[]) oRow;
                rw("JK=" + r[0] + "=" + "=" + r[1] + "=" + "=" + r[2] + "=" + r[3] + "=" + r[4] + "=" + r[5] + "=" + r[6] + "=" + r[7] + "=" + r[8]);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public List<Servicios> selectPendientesVPS(String sql, String fecha) throws Exception {
        try {
            List<Servicios> lsit = nuevoServicios(fecha);

            return lsit;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Servicios> updateSQLPendientesVPS(String sql, String fecha) throws Exception {
        try {
            List<Servicios> lstSrv = new ArrayList<Servicios>();
            if (false && new isProduccion().isProduccion().equals("VPS")) {

                try {

                    rw("ExceuteSQL: " + sql);
                    int val = em.createNativeQuery(sql).executeUpdate();
                    rw("RTA update: " + val);
                } catch (Exception e) {
                    rw("No fue posible realizar la actualizacion ");
                }
                /*List<String> lstComprob = comprobXFecha(fecha);
                 if (lstComprob != null && lstComprob.size() > 0) {
                 for (String VJS : lstComprob) {
                 if (!VJS.equals("VES")) {
                 lstSrv.addAll(ultServiciosPendientes(VJS));
                 }
                 }
                 rw("Ult pendientes: " + lstSrv.size());
                 } else {
                 rw("No hay servicios pendientes para subir");
                 }*/
            } else if (new isProduccion().isProduccion().equals("AERO")) {
                rw("ExceuteSQL: " + sql);
                int val = emVps.createNativeQuery(sql).executeUpdate();
                rw("RTA update: " + val);
            }
            return lstSrv;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Servicios> revisarServiciosByComprob(String fecha, String comprob) throws Exception {
        try {
            String sql = "select * from srv_camioneta where fecha >= TO_DATE('" + fecha + "','%Y-%m-%d')  and comprob = '" + comprob + "' order by numero";
            if (new isProduccion().isProduccion().equals("VPS")) {
                // donde fecha debe ser asi: 2015/08/09 00:00
                sql = "select * from srv_camioneta where fecha >= ('" + fecha + "')  and comprob = '" + comprob + "' order by numero";
            }
            rw("sql: " + sql);
            List<Servicios> lstSrv = em.createNativeQuery(sql, Servicios.class).getResultList();
            return lstSrv;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer generarTikect(String contrato) throws Exception {
        Connection con = null;
        Integer count = 0;
        try {
            String fecha = DateHelper.restarMinutos(15);

            String sql = "select count(*) as cantidad from  register_in_out where fecha >= TO_DATE('" + fecha + "','%d-%m-%Y %H:%M') and numero_contrato = " + contrato;
            rw("sql_count_" + sql);
            DataSource operativaDS = dsGetImperialDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();

            while (rs.next()) {
                count = rs.getInt("cantidad");
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        rw("sql_count_" + count);
        return count;
    }

    //
    @Override
    public RtaDTO lRtaDTO(String placa) throws Exception {

        Connection con = null;
        RtaDTO consolidado = new RtaDTO();
        JsonDTO bloqueos = new JsonDTO();
        try {

            String sql = " select a.n_asign as ID ,a.codigo_eq CONTRATO ,a.placa PLACA , to_char(af.fechat,'%Y-%m-%d') FECHA,af.clased CONCEPTO "
                    + " ,(SELECT emp_idempresa FROM operativa:top_vehiculo WHERE veh_placa =  a.placa) as empresa "
                    + " from asign a "
                    + "  inner join asignaf af on (af.n_asign=a.n_asign) ";
            sql += " where AF.clased = 'P' ";
            if (placa != null && !placa.isEmpty()) {
                if (placa.matches("[0-9]*")) {
                    sql += " AND a.codigo_eq =" + placa;
                } else {
                    sql += " AND a.placa= '" + placa + "'";

                }
            }
            rw("sql:= " + sql);
            DataSource operativaDS = dsGetImperialDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();
            while (rs.next()) {
                JsonDTO consol = new JsonDTO();
                consol.setContrato(rs.getString("contrato"));
                consol.setDetalle("Salida del parqueadero");
                consol.setFecha(rs.getString("fecha") + " 00:00 PM");
                consol.setPlaca(rs.getString("placa"));
                consol.setEmpresaId(rs.getString("empresa"));

                //hsa   bloqueos = lstBloqueos(consol.getPlaca());
                if (bloqueos != null) {
                    // rw("Vehiculo tiene bloqueos " + bloqueos.getDetalle());
                    consol.setBloqueoX(bloqueos.getDetalle());
                    consol.setBloqueo("si");
                } else {
                    consol.setBloqueo("no");

                }
                lMorosos.add(consol);
                consolidado.setAlertas(lMorosos);

            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
//            return null;
        } catch (Exception e) {
            rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }

        }
        return consolidado;
    }

    @Override
    public List<TempVehiculo> misCarros() throws Exception {
        try {

            String sql = "SELECT * FROM temp_vehiculos";
            rw("sql_" + sql);
            List<TempVehiculo> bloques = (List<TempVehiculo>) em.createNativeQuery(sql, TempVehiculo.class).getResultList();

            return bloques;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<JsonDTO> misCarrosVANGO() throws Exception {
        Connection con = null;
        try {

            String sql = "SELECT  v.veh_placa,v.veh_modelo ,m.mar_nommarca ,v.veh_numinterno, c.cla_nomclase,p.per_numidenti,\n"
                    + "        pe.per_nombre[1,40] f,pe.per_nombre[41,80] a ,\n"
                    + "        e.emp_nomempresa,v.cap_idcapaci as pasajeros\n"
                    + " FROM top_vehiculo v, top_persovehiculo p, top_linea l, top_marca m,top_clase_vehiculo c, top_persona pe, \n"
                    + "     top_empresa e\n"
                    + " WHERE p.veh_codigo = v.veh_codigo\n"
                    + " AND p.per_numidenti = pe.per_numidenti\n"
                    + " AND v.cla_idclaseveh = c.cla_idclaseveh\n"
                    + " AND v.lin_idlinea = l.lin_idlinea\n"
                    + " AND l.mar_idmarca = m.mar_idmarca\n"
                    + " AND v.emp_idempresa = e.emp_idempresa\n"
                    + " AND v.suc_cod =  12\n"
                    + " AND pe.suc_cod = 12\n"
                    + " AND e.suc_cod =  12\n"
                    + " AND v.sit_codsitio = 5";
            rw("sql_" + sql);

            DataSource operativaDS = dsGetOperativaDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String sNit = "0";
            int contador = 0;
            List<JsonDTO> vehiculos = new ArrayList<JsonDTO>();
            while (rs.next()) {

                JsonDTO carro = new JsonDTO();

                carro.setPlaca(rs.getString(1));
                carro.setModelo(rs.getString(2));
                carro.setMarca(rs.getString(3));
                carro.setNumeroInterno(rs.getString(4));
                carro.setClase(rs.getString(5));
                carro.setCedula(rs.getString(6));
                carro.setNombre(new StringTools().getStringTrim(rs.getString(7) + " " + rs.getString(8)));

                carro.setNombreEmpresa(rs.getString(9));
                carro.setPasajero(rs.getString(10));

                carro.setError("ok");

                vehiculos.add(carro);

            }

            if (con != null && !con.isClosed()) {
                con.close();
            }

            return vehiculos;
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bloqueo> lstBloqueos() throws Exception {
        try {
            String sql = "select * from bloqueo where id_estado = 1";
            rw("sql_" + sql);
            List<Bloqueo> bloques = (List<Bloqueo>) em.createNativeQuery(sql, Bloqueo.class).getResultList();

            return bloques;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Bloqueo crearBloqueoTaxi(Bloqueo servicio) throws Exception {
        try {

            Bloqueo bloqueo = bloqueoByPlaca(servicio.getAutoPlaca());
            if (bloqueo == null) {
                if (servicio.getId() == null) {
                    Integer ids = generateId("id", servicio.getClass().getSimpleName());
                    servicio.setId(ids);
                }
                servicio = em.merge(servicio);
            } else {
                bloqueo.setIdEstado(servicio.getIdEstado());

                servicio = em.merge(bloqueo);

            }
            return servicio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SrvPreview> listarSrvPreview(String idSitio) throws Exception {
        try {
            String sql = "SELECT * FROM srv_preview where id_estado = 1";
            rw("sql_" + sql);
            Query query = em.createNativeQuery(sql, SrvPreview.class);
            List<SrvPreview> lstServicios = query.getResultList();
            return lstServicios;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean cancelarSrvPreview(String placa) throws Exception {
        try {
            String sql = "update srv_preview set id_estado = 2 where id_estado = 1 and placa = '" + placa + "'";
            rw("sql_" + sql);
            em.createNativeQuery(sql).executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public SrvPreview crearSrvPreview(SrvPreview servicio) throws Exception {
        try {
            if (servicio.getId() == null) {
                Integer idS = generateId("id", servicio.getClass().getSimpleName());
                servicio.setId(idS);
            }
            servicio = em.merge(servicio);
            return servicio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonDTO lstBloqueos(String placa) throws Exception {
        Connection con = null;
        JsonDTO consol = null;
        try {

            String sql = "select * from top_bloqueos where tip_codigobloq in('605') and  blo_indicador='" + placa.toUpperCase() + "' order by blo_observacion desc";
            rw("sqlBloqueo: " + sql);
            DataSource operativaDS = dsGetOperativaDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                consol = new JsonDTO();
                consol.setDetalle(rs.getString("blo_observacion"));
                consol.setFecha(rs.getString("blo_fechaing"));
                consol.setPlaca(rs.getString("blo_indicador"));
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return consol;
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return null;
        }
    }

    @Override
    public List<String> allCar(String placa) throws Exception {
        Connection con = null;
        RtaDTO consolidado = new RtaDTO();
        List<String> allCar = new ArrayList<String>();
        try {
            String sql = "SELECT veh_placa\n"
                    + "FROM top_vehiculo v, top_empresa e, top_clase_vehiculo c\n"
                    + "WHERE v.emp_idempresa = e.emp_idempresa\n"
                    + "AND v.cla_idclaseveh = c.cla_idclaseveh\n"
                    + "AND v.suc_cod = 12\n";
            if (placa != null) {
                sql += " and v.veh_placa='" + placa + "' ";
            }

            // + "AND c.cla_idclaseveh = 2";
            rw("sql:= " + sql);
            DataSource operativaDS = dsGetOperativaDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();

            while (rs.next()) {
                allCar.add(rs.getString("veh_placa"));

            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
//            return null;
        } catch (Exception e) {
            rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
        }
        return allCar;
    }

    @Override
    public RtaDTO allVehicule() throws Exception {
        Connection con = null;
        RtaDTO consolidado = new RtaDTO();
        try {
            String sql = " select distinct a.placa,a.n_asign as ID ,a.codigo_eq CONTRATO ,a.placa PLACA , to_char(af.fechat,'%Y-%m-%d') FECHA,af.clased CONCEPTO ";
            sql += "  from asign a ";
            sql += "   inner join asignaf af on (af.n_asign=a.n_asign) ";

            rw("SQL:= " + sql);

            DataSource operativaDS = dsGetImperialDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();
            while (rs.next()) {

                JsonDTO consol = new JsonDTO();
                consol.setContrato(rs.getString("id"));
                consol.setDetalle(rs.getString("contrato"));
                consol.setFecha(rs.getString("fecha"));
                consol.setPlaca(rs.getString("placa"));
                lMorosos.add(consol);

            }
            consolidado.setAlertas(lMorosos);
            consolidado.setAlertas(lMorosos);
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }

        }
        return consolidado;
    }

    @Override
    public ServiciosEspecial crearServicioAvianca(ServiciosEspecial servicio) throws Exception {
        try {
            if (servicio.getEstado() == null) {
                rw("Es necesario especificar el estado del servicio");
                servicio.setEstado(27);
            }
            //servicio.setFechaGrab(new Date());
            //servicio.setFecha(new Date());
            servicio.setValor(servicio.getValor());
            em.persist(servicio);
            rw("aero: servicio creado con el numero: " + servicio.getNumero());

            return (servicio);
        } catch (Exception e) {

            rw("aero: NO FUE POSIBLE PROCESAR SU SOLICITUD " + e.getCause());
            return null;
        }
    }

    @Override
    public Servicios lastServicioByCajero(CajerosAero usuario) throws Exception {
        try {
            String _sql = "SELECT max(numero) FROM Servicios s WHERE s.usuarios = :usuarios";
            Query _query = em.createQuery(_sql);
            _query.setParameter("usuarios", usuario);
            Integer maxId = (Integer) _query.getSingleResult();
            if (maxId != null) {
                String sql = "SELECT s FROM Servicios s WHERE s.numero = :numero";
                Query query = em.createQuery(sql);
                query.setParameter("numero", maxId);
                Servicios servicio = (Servicios) query.getSingleResult();

                return (servicio);
            } else {
                return null;
            }
        } catch (Exception e) {

            rw("aero: NO FUE POSIBLE PROCESAR SU SOLICITUD " + e.getCause());
            return null;
        }
    }

    @Override
    public Boolean updateSrv(Servicios serv) throws Exception {
        boolean isOk = false;
        try {
            String sql = "update srv_camioneta set json='" + serv.getJson() + "' where id = " + serv.getId();
            rw("sqlUpdate:" + sql);
            //em.createNativeQuery(sql).executeUpdate();
            em.merge(serv);
            em.flush();
            rw("updated:" + serv.getId());
            isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Sitios sitioById(Integer sitio) throws Exception {

        try {
            String sql = "SELECT s FROM Sitios s WHERE s.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", sitio);

            Sitios mySito = (Sitios) query.getSingleResult();
            em.flush();
            return mySito;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public List<Sitios> lSitios() throws Exception {
        try {
            String sql = "SELECT s FROM Sitios s";
            Query query = em.createQuery(sql);
            List<Sitios> lSitios = query.getResultList();
            em.flush();
            return lSitios;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public Integer getCmprobanteServicio(Servicios srv
    ) {
        Integer numcero = null;
        try {
            if (srv.getSitio().getId().equals(1) || srv.getSitio().getId().equals(2)) {
                String sql = "SELECT MAX(s2.numero) FROM Servicios s2 where s2.sitio = :sitio OR s2.sitio = :sitio2";
                Query query = em.createQuery(sql);
                query.setParameter("sitio", new Sitios(1));
                query.setParameter("sitio2", new Sitios(2));
                numcero = (Integer) query.getSingleResult();
            } else {
                String sql = "SELECT MAX(s2.numero) FROM Servicios s2 where s2.sitio = :sitio";
                Query query = em.createQuery(sql);
                query.setParameter("sitio", srv.getSitio());
                numcero = (Integer) query.getSingleResult();
            }
        } catch (Exception e) {
            //   e.printStackTrace();
        }

        if (numcero == null) {
            return 1;
        } else {
            return numcero + 1;
        }

    }

    @Override
    public Integer generateId(String columna, String entity
    ) {
        try {
            String sql = "select max(" + columna + ") from " + entity;
            rw("sql: " + sql);
            Query query = em.createQuery(sql);
            Integer nvVal = (Integer) query.getSingleResult();
            rw("id encontrado: " + nvVal);
            em.flush();
            return (nvVal + 1);
        } catch (Exception e) {

            return 1;
        }
    }

    @Override
    public List<JsonDTO> lstContratos() throws Exception {
        Connection con = null;
        List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();
        try {
            String sql = "SELECT a.placa, a.n_asign, a.codigo_eq, a.placa, TO_CHAR(af.fechat,'%Y-%m-%d') as fecha, af.clased, TODAY - af.fechat as dias \n"
                    + ",(SELECT emp_idempresa FROM operativa:top_vehiculo WHERE veh_placa = a.placa) as empresa\n"
                    + "FROM asign a\n"
                    + "INNER JOIN asignaf af on (af.n_asign=a.n_asign)\n"
                    + "WHERE af.clased = 'P' --AND (TODAY - af.fechat) > 0\n"
                    + " ";

            sql = "SELECT a.placa, a.n_asign, a.codigo_eq, a.placa, TO_CHAR(af.fechat,'%Y-%m-%d') as fecha, \n"
                    + "af.clased, TODAY - af.fechat as dias ,\n"
                    + "(SELECT e.emp_nomempresa  || ',' || l.lin_nomlinea FROM operativa:top_vehiculo v,   operativa:top_linea l,\n"
                    + "operativa:top_empresa e\n"
                    + "WHERE veh_placa = a.placa and v.lin_idlinea=l.lin_idlinea and v.emp_idempresa=e.emp_idempresa) as empresa\n"
                    + "  FROM asign a\n"
                    + "   INNER JOIN asignaf af on (af.n_asign=a.n_asign)\n"
                    + "     WHERE af.clased = 'P'";

            rw("sql: " + sql);
            DataSource operativaDS = dsGetImperialDS();
            con = operativaDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                JsonDTO consol = new JsonDTO();
                consol.setPlaca(rs.getString("placa"));
                consol.setnAsign(rs.getString("n_asign"));
                consol.setContrato(rs.getString("codigo_eq"));
                consol.setFecha(rs.getString("fecha"));
                consol.setClased(rs.getString("clased"));
                consol.setDias(rs.getInt("dias"));

                if (consol.getPlaca() != null && !consol.getPlaca().trim().equals("PIRATA")) {
                    try {
                        String empresaLinea[] = rs.getString("empresa").trim().split("\\,");
                        String empresa = empresaLinea[0];
                        String linea = empresaLinea[1];

                        consol.setEmpresaId(empresa);
                        consol.setLineaCar(linea);

                        JsonDTO responsable = buscarResponsable(consol.getPlaca().trim());
                        if (responsable != null) {

                            consol.setNombreConductor(responsable.getNombreConductor());
                            consol.setCedulaConductor(responsable.getCedulaConductor());
                            consol.setModelo(responsable.getModelo());

                            consol.setMarca(responsable.getMarca());
                            consol.setTelefono(responsable.getTelefono());

                            lMorosos.add(consol);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return lMorosos;
        } catch (Exception e) {
            rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }

        }
        return (lMorosos);

    }

    @Override
    public JsonDTO buscarResponsable(String placa) throws Exception {
        Connection con = null;
        try {

            String sql = "select p.per_numidenti,p.per_nombre,v.veh_modelo from top_persovehiculo pv, top_vehiculo v, top_persona p\n"
                    + "where pv.veh_codigo = v.veh_codigo\n"
                    + "and p.per_numidenti = pv.per_numidenti\n"
                    + "and pv.tip_idtipoper = 1\n"
                    + "and v.veh_placa = '" + placa + "'\n"
                    + "and pv.per_pagador =  1";

            sql = "select p.per_numidenti,p.per_nombre,v.veh_modelo,m.mar_nommarca, p.per_celular1\n"
                    + "from top_persovehiculo pv, top_vehiculo v, top_persona p, top_linea l, top_marca m\n"
                    + "where pv.veh_codigo = v.veh_codigo\n"
                    + "and p.per_numidenti = pv.per_numidenti\n"
                    + "AND v.lin_idlinea = l.lin_idlinea\n"
                    + "AND l.mar_idmarca = m.mar_idmarca\n"
                    + "and pv.tip_idtipoper = 1\n"
                    + "and v.veh_placa = '" + placa + "'\n"
                    + "and pv.per_pagador =  1\n"
                    + "--and p.suc_cod = 12 -- Viajes Imperial";

            rw("sql_" + sql);

            con = dsGetOperativaDS().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            JsonDTO consol = new JsonDTO();
            while (rs.next()) {
                String cedula = rs.getString("per_numidenti");
                String nombreResponsable = rs.getString("per_nombre");
                String modelo = rs.getString("veh_modelo");
                String marca = rs.getString("mar_nommarca");
                String telefono = rs.getString("per_celular1");

                consol.setNombreConductor(StringTools.getStringTrim(nombreResponsable));
                consol.setCedulaConductor(cedula);
                consol.setModelo(modelo);
                consol.setMarca(marca);
                consol.setTelefono(telefono);
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return consol;
        } catch (Exception e) {
            e.printStackTrace();
            rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }

        }
        return null;
    }

    @Override
    public void changeStateCar(String sql) throws Exception {
        try {
            Query query = em.createNativeQuery(sql);
            query.executeUpdate();
            em.flush();
        } catch (Exception e) {
            rw("No fue posible procesar su solicitud " + e.getMessage());
        }
    }

    @Override
    public List<TempVehiculo> lstCarros() throws Exception {
        try {
            String sql = "SELECT s FROM TempVehiculo s order by s.fecha";
            Query query = em.createQuery(sql);
            List<TempVehiculo> lstCar = query.getResultList();
            em.flush();
            return lstCar;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Servicios> srvNoCargados(String sql) throws Exception {

        try {
            if (true) {
                rw("???????????????????????  VPS ???????????????????????????????????????");
                rw("SQL faltantes: " + sql);
                List<Servicios> query2 = emVps.createNativeQuery(sql, Servicios.class).getResultList();
                emVps.flush();
                return query2;
            } else {
                rw("SQL faltantes: " + sql);
                List<Servicios> query2 = em.createNativeQuery(sql, Servicios.class).getResultList();
                em.flush();
                return query2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Servicios> srvEnFechas(String inico, String fin) throws Exception {
        try {
            Date fecha1 = DateHelper.stringToDate2(inico);
            Date fecha2 = DateHelper.stringToDate2(fin);

            String sqlDir = " select * from srv_camioneta ";
            if (fecha1 != null && fecha2 != null) {
                sqlDir += " where fecha >= ('" + fecha1 + "') ";
                sqlDir += " and ";
                sqlDir += " fecha <= ('" + fecha2 + "')";
            } else {
                sqlDir += " where fecha >= ('" + fecha1 + "') ";
            }
            sqlDir += " ORDER BY numero ";

            String sql2 = "SELECT s FROM Servicios s WHERE s.fecha >=:fecha and s.fecha <= :fecha2  order by s.numero";

            rw("sql2___" + sql2 + "_fecha1:" + fecha1 + "____fecha2:" + fecha2);

            Query query = em.createQuery(sql2);
            query.setParameter("fecha", fecha1);
            query.setParameter("fecha2", fecha2);

            List<Servicios> lstServicios = query.getResultList();
            em.flush();
            rw("Srv: " + sql2);
            //List<Servicios> lstServicios = (List<Servicios>) em.createNativeQuery(sqlDir, Servicios.class).getResultList();
            return lstServicios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Servicios> ultServiciosPendientes(String comprob) throws Exception {
        try {
            String sql = "SELECT s FROM Servicios s WHERE s.indProc = :indProc and s.comprob = :comprob and s.cierre = :cierre";
            Query query = em.createQuery(sql);
            query.setParameter("indProc", 0);
            query.setParameter("comprob", comprob);
            query.setParameter("cierre", 1);
            List<Servicios> lstSrv = (List<Servicios>) query.getResultList();
            em.flush();
            return lstSrv;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Servicios> nuevoServicios(String fecha) throws Exception {
        try {
            if (new isProduccion().isProduccion().equals("VPS")) {
                String sql = "SELECT * FROM srv_camioneta  WHERE ind_proc = 0 and cierre = 1 ";
                Query query = null;
                if (fecha != null) {
                    sql += " and fecha >= '" + fecha + "'";
                }
                sql += " order by numero ";
                rw("sql: " + sql);
                List<Servicios> lstSrv = em.createNativeQuery(sql, Servicios.class).getResultList();
                em.flush();
                return lstSrv;
            } else {
                rw("¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿  VPS ?????????????????????????" + emVps.getProperties());
                String sql = "SELECT * FROM srv_camioneta  WHERE ind_proc = 0 and cierre = 1 ";
                Query query = null;
                if (fecha != null) {
                    sql += " and fecha >= '" + fecha + "'";
                }
                sql += " order by numero ";
                rw("sql: " + sql);
                List<Servicios> lstSrv = emVps.createNativeQuery(sql, Servicios.class).getResultList();
                emVps.flush();
                return lstSrv;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Servicios> ultServiciosNoCargados(String sql) throws Exception {
        try {
            List<Servicios> query = em.createNativeQuery(sql, Servicios.class).getResultList();
            em.flush();
            return query;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer getNextId(Servicios servicio) {
        try {
            String sql = "SELECT max(id) FROM Servicios s";
            Query query = em.createQuery(sql);
            Integer id = (Integer) query.getSingleResult();
            em.flush();
            return id + 1;
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public boolean logout(Integer movil) throws Exception {

        return true;
    }

    @Override
    public CajerosAero login(String usuario, String clave
    ) {
        try {
            rw("User: " + usuario + "  clave: " + com.rta.opain.delegate.tools.Md5Helper.encodeMD5(clave));
            String sql = "SELECT c FROM CajerosAero c WHERE c.usuario = :usuario and c.clave = :clave";
            Query query = em.createQuery(sql);
            query.setParameter("usuario", usuario);
            query.setParameter("clave", com.rta.opain.delegate.tools.Md5Helper.encodeMD5(clave));
            CajerosAero cajero = (CajerosAero) query.getSingleResult();
            em.flush();
            return cajero;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public CajerosAero changePass(CajerosAero usuario
    ) {
        try {
            String clave = new Md5Helper().encodeMD5(usuario.getClave());
            usuario.setClave(clave);
            em.merge(usuario);
            return usuario;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public List<String> realizarCierreAutomatico() throws Exception {
        try {
            List<String> cajeros = em.createNativeQuery("select usuarios_id from srv_camioneta where cierre = 0 group by usuarios_id").getResultList();
            em.flush();
            return cajeros;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void loadDataBase(String file) throws Exception {
        try {

        } catch (Exception e) {
            rw("Datos cargados con exito");
        }
    }

    @Override
    public Object[] cerrarServicios(CajerosAero usuario, boolean control, Properties pr
    ) {
        try {
            List<Servicios> srv = null;
            try {
                Query query = em.createQuery("SELECT s FROM Servicios s WHERE s.usuarios = :usuarios and s.cierre=:cierre order by s.id ");
                query.setParameter("usuarios", usuario);
                query.setParameter("cierre", 0);
                srv = (List<Servicios>) query.getResultList();

                if (srv != null && srv.size() > 0) {
                    rw("a. Cierre de usuario: " + usuario.getUsuario() + "");
                } else {
                    rw("b. No hay cierres pendientes,.............");
                }

            } catch (Exception e) {

            }

            int i = 0;
            String comprob = "";
            Integer valor = 0;
            Integer valorTInteger = 0;
            int ini = !srv.isEmpty() ? srv.get(0).getNumero() : 0;
            int top = !srv.isEmpty() ? srv.get(srv.size() - 1).getNumero() : 0;
            Cierre cierre = new Cierre();
            Object[] obj = new Object[2];

            if (srv != null && !srv.isEmpty()) {
                Integer cantidadAnulads = 0;
                Integer valorAnulados = 0;

                if (!control) {//no se crea cierre
                    int valore = 0, valord = 0, valorc = 0;
                    int cantidadFacturas = 0;
                    for (Servicios s : srv) {
                        comprob = s.getComprob();
                        if (s.getEstado().getId().equals(26)) {
                            valorAnulados += s.getValorC() + s.getValorE() + s.getValorD();
                            cantidadAnulads += 1;
                        } else {
                            valor += s.getValor();
                            valorTInteger += s.getValor();
                            valorc += s.getValorC();
                            valord += s.getValorD();
                            valore += s.getValorE();
                        }
                        i++;
                        cantidadFacturas++;
                        cierre.setSitioNombre(s.getSitio().getNombre().split("\\|")[1]);
                    }

                    cierre.setValor(valor);
                    cierre.setValorTotal(valorTInteger);
                    cierre.setUsuarioNombre(usuario.getNombre());
                    cierre.setInicio(ini);
                    cierre.setFin(top);
                    cierre.setCantidad(srv.size());
                    cierre.setFecha(new Date());

                    cierre.setComprob(comprob);
                    cierre.setValorE(valore);
                    cierre.setValorD(valord);
                    cierre.setValorC(valorc);
                    cierre.setValorAnualdos(valorAnulados);
                    cierre.setCantidadAnuladas(cantidadAnulads);

                    // byte[] bytes = new CreateDocument().generarCierreFactura(cierre, control, pr);
                    //  obj[0] = bytes;
                    obj[1] = cierre;
                } else {
                    try {
                        cierre.setInicio(ini);
                        cierre.setFin(top);
                        cierre.setCantidad(srv.size());
                        cierre.setFecha(new Date());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int valore = 0, valord = 0, valorc = 0;
                    for (Servicios s : srv) {
                        if (s.getEstado().getId().equals(26)) {
                            valorAnulados += s.getValorC() + s.getValorE() + s.getValorD();
                            cantidadAnulads += 1;
                        } else {
                            valor += s.getValor();
                            valorTInteger += s.getValor();
                            valorc += s.getValorC();
                            valord += s.getValorD();
                            valore += s.getValorE();
                        }
                        cierre.setComprob(s.getComprob());
                        cierre.setSitioNombre(s.getSitio().getNombre().split("\\|")[1]);
                        s.setCierre(1);

                        rw("cierrer...  " + s.getCierre());
                        em.merge(s);
                    }
                    cierre.setValor(valor);
                    cierre.setValorTotal(valorTInteger);
                    cierre.setUsuarioNombre(usuario.getNombre());
                    cierre.setValorE(valore);
                    cierre.setValorD(valord);
                    cierre.setValorC(valorc);
                    cierre.setValorAnualdos(valorAnulados);
                    cierre.setCantidadAnuladas(cantidadAnulads);
                    //cierre.setIdCierre(generateId("idCierre", Cierre.class.getSimpleName()));

                    //cierre =  savarCierre(cierre);
                    try {
                        new SendMail().sendMesageCierre(cierre);
                    } catch (RuntimeException e) {
                        rw("no fue posible enviar el correo");
                    }
                    obj[1] = cierre;
                }
            } else {
                rw("No hay cierres pendientes,.............");
            }
            em.flush();
            return obj;
        } catch (Exception e) {

            return null;
        }

    }

    @Override
    public List<Servicios> serviciosPorUsuarioToCierre(String usuario) throws Exception {
        List<Servicios> srvs = null;
        try {
            String sql = "select * from srv_camioneta where grabador = '" + usuario + "' and cierre = 0";
            rw("sql_" + sql);
            Query query = em.createNativeQuery(sql, Servicios.class);
            srvs = (List<Servicios>) query.getResultList();
            rw("TOTAL_DE_FACTURAS_" + srvs.size());
            em.flush();
            return srvs;
        } catch (Exception e) {
            rw("Error_" + e.getMessage());
            return null;
        }
    }

    @Override
    public Object[] cerrarServicios2(CajerosAero usuario,String idSitio, boolean control, Properties pr) {
        Multipart multipart = null;
        try {
            List<Servicios> srv = null;
            Cierre cierre = new Cierre();
            Object[] obj = new Object[2];

            srv = serviciosPorUsuarioToCierre(usuario.getUsuario());

            if (control == true && srv != null) {
                byte[] bytes = new GenerarCierre().generarFactura(srv, DateHelper.getFechaFile(60));

                MimeBodyPart part = new SendMail().addFacturaByUser(bytes);
                multipart = new MimeMultipart();
                multipart.addBodyPart(part);

            }
            String andSitio = "";
            String tmpSitio = idSitio;
            if(idSitio != null){
               idSitio = " ,sitios_id "; 
               andSitio = " and sitios_id = "+tmpSitio;
            }
            rw("sitio________"+idSitio);

            String sql = "select sum(valore) as EFECTIVO,sum(valord) AS DEBIDO,sum(valorc) AS CREDITO,sum(valor) AS VALOR,max(numero) as ULTIMA,min(numero) AS PRIMERA,comprob\n"
                    + ",(SELECT COUNT(*) FROM srv_camioneta WHERE grabador ='" + usuario.getUsuario() + "' and cierre = 0 AND estado_id = 26  GROUP BY VALOR) AS TOTAL_ANULADAS\n"
                    + ",(select count(*) from srv_camioneta where grabador ='" + usuario.getUsuario() + "' and cierre = 0) as CANTIDAD\n"
                    + " ,(SELECT sum(valore+valord+valorc) FROM srv_camioneta WHERE grabador ='" + usuario.getUsuario() + "' and cierre = 0 AND estado_id = 26  GROUP BY VALOR) AS VALOR_ANULADO "
                    + ",(select nombre from sitios where id = sitios_id) as nombreSitio "
                    + "from srv_camioneta \n"
                    + "where id in(\n"
                    + "select id\n"
                    + "from srv_camioneta \n"
                    + "where grabador ='" + usuario.getUsuario() + "' and cierre = 0 --and estado_id = 27 \n"
                    + ") GROUP BY comprob,sitios_id";

            sql = "select sum(case when valor = 0 then valor else valore end) as efectivo,sum(case when valor = 0 then valor else valord end) as debito,sum(case when valor = 0 then valor else valorc end)as credito,sum(valor) AS VALOR,max(numero) as ULTIMA,min(numero) AS PRIMERA,comprob\n"
                    + ",(SELECT COUNT(*) FROM srv_camioneta WHERE grabador ='" + usuario.getUsuario() + "' and cierre = 0 AND estado_id = 26  GROUP BY VALOR) AS TOTAL_ANULADAS\n"
                    + ",(select count(*) from srv_camioneta where grabador ='" + usuario.getUsuario() + "' and cierre = 0) as CANTIDAD\n"
                    + " ,(SELECT sum(valore+valord+valorc) FROM srv_camioneta WHERE grabador ='" + usuario.getUsuario() + "' and cierre = 0 AND estado_id = 26  GROUP BY VALOR) AS VALOR_ANULADO "
                    + ",(select nombre from sitios where id = sitios_id) as nombreSitio "
                    + " "+idSitio+" from srv_camioneta \n"
                    + "where id in(\n"
                    + "select id\n"
                    + "from srv_camioneta \n"
                    + "where grabador ='" + usuario.getUsuario() + "' and cierre = 0 "+andSitio
                    + ") GROUP BY comprob,sitios_id";

            rw("sql_" + sql);
            Query query = em.createNativeQuery(sql);
            List<Object> results = query.getResultList();
            if ((results != null && results.size() > 0)) {

                for (Object oRow : results) {
                    Object[] r = (Object[]) oRow;

                    String valore = "" + r[0];
                    String valord = "" + r[1];
                    String valorc = "" + r[2];
                    String valor = "" + r[3];
                    String ultima = "" + r[4];
                    String primera = "" + r[5];
                    String comprob = "" + r[6];
                    String cantidadAnulads = "" + r[7];//-574000
                    String cantidad = "" + r[8];
                    String valorAnulados = "" + r[9];
                    String sitio = "" + r[10];

                    //   rw("JK=" + r[0] + "=" + "=" + r[1] + "=" + "=" + r[2] + "=" + r[3] + "=" + r[4] + "=" + r[5] + "=" + r[6] + "=" + r[7] + "=" + r[8]);
                    cierre.setValor(getNumberInt(valor));
                    cierre.setValorTotal(getNumberInt(valor));
                    cierre.setUsuarioNombre(usuario.getNombre());
                    cierre.setInicio(getNumberInt(primera));
                    cierre.setFin(getNumberInt(ultima));
                    cierre.setCantidad(getNumberInt(cantidad));
                    cierre.setFecha(new Date());
                    cierre.setComprob(comprob);
                    cierre.setValorE(getNumberInt(valore));
                    cierre.setValorD(getNumberInt(valord));
                    cierre.setValorC(getNumberInt(valorc));
                    cierre.setCantidadAnuladas(getNumberInt(cantidadAnulads));
                    cierre.setValorAnualdos(getNumberInt(valorAnulados));
                    cierre.setSitioNombre(sitio.split("\\|")[1]);

                }

                if (control) {
                    try {
                        String sqlUpdate = "update srv_camioneta set cierre = 1 where grabador ='" + usuario.getUsuario() + "' and cierre = 0 and sitios_id = "+tmpSitio;
                        rw("sqlUpdate_" + sqlUpdate);
                        em.createNativeQuery(sqlUpdate).executeUpdate();
                        new SendMail().sendMesageCierre2(cierre, multipart);
                    } catch (RuntimeException e) {
                        rw("no fue posible enviar el correo");
                    }
                }
            } else {
                obj = null;
            }
            obj[1] = cierre;
            rw("values___return___");
            em.flush();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            rw("Error_" + e.getMessage());
            return null;
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public Cierre savarCierre(Cierre cierre) throws RuntimeException {
        try {
            rw("CIERRE INICIO");
            cierre = em.merge(cierre);
            em.flush();
            rw("CIERRE GUARDADO");
        } catch (RuntimeException e) {
            rw("CIERRE ERROR " + e.getCause());
        }
        return cierre;
    }

    @Override
    public List<RegisterInOut> ultimoLogs() throws Exception {
        try {
            Date de1 = new DateHelper().restartMinutos(new Date(), 1 * 5);
            String sql = "SELECT r FROM RegisterInOut r WHERE r.fecha >= :fecha order by r.idRegisterInOut desc";
            Query query = em.createQuery(sql);
            query.setParameter("fecha", de1);
            List<RegisterInOut> ultimoLogs = query.getResultList();
            /* for (RegisterInOut r : ultimoLogs) {
                rw("salida:   " + r.getIdRegisterInOut() + "   placa:   " + r.getVehiculoPlaca());
            }*/
            em.flush();
            return ultimoLogs;
        } catch (Exception e) {
            // e.printStackTrace();

            return null;
        }
    }

    @Override
    public RegisterInOut insertLogs(RegisterInOut logs) throws Exception {
        try {
            return em.merge(logs);
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public List<Servicios> lServicioWithOutNit() throws Exception {
        try {
            String sHQL = "SELECT s FROM Servicios s WHERE s.nit = :nit and s.valor >= :valor";
            Query query = em.createQuery(sHQL);
            query.setParameter("nit", 0l);
            query.setParameter("valor", 0);
            List<Servicios> serv = (List<Servicios>) query.getResultList();
            em.flush();
            return serv;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public Servicios _servicio(Long numero, String comprob) throws Exception {
        Servicios serv = null;
        if (false) {
            try {
                String sql = "SELECT s FROM Servicios s WHERE s.numero  = :numero AND s.comprob = :comprob";
                Query query = em.createQuery(sql);
                query.setParameter("numero", new Integer(numero.toString()));
                query.setParameter("comprob", comprob);
                serv = (Servicios) query.getSingleResult();
                rw("Reimprimire srv:id" + serv.getId() + " numero:" + numero + " comprob: " + comprob);
                em.close();
                return serv;
            } catch (Exception e) {
                //rw("");//
                //  e.printStackTrace();
                rw("no hay: numero:" + numero + " comprob: " + comprob);
                return null;
            }
        } else {
            String sql = "select * from srv_camioneta where numero = " + numero + " and comprob = '" + comprob.toUpperCase() + "'";
            rw("sql: " + sql);
            try {
                serv = (Servicios) em.createNativeQuery(sql, Servicios.class).getSingleResult();
                //em.close();
                em.flush();
                rw("Servicio cargado: " + sql);
                return serv;
            } catch (NoResultException e) {
                //rw("");//
                rw("no hay: numero:" + sql);
                return null;
            }
        }
    }

    @Override
    public Servicios anulacion(Long numero, String porque, Sitios sitio, Date fecha) throws Exception {
        Servicios serv = null;

        Integer num = new Integer("" + numero);
        try {
            Date de1 = new DateHelper().restartMinutos(fecha, 1 * 5);
            rw("Anulacion de factura de: " + de1 + "  hasta " + fecha + " id:= " + numero + " sitio: " + sitio.getId());
            String sql = "SELECT s FROM Servicios s WHERE s.numero  = :numero and s.estado  = :estado and s.fecha >= :fecha and s.cierre = :cierre and s.sitio = :sitio";
            Query query = em.createQuery(sql);
            query.setParameter("numero", num);
            query.setParameter("estado", new Estados(27));
            query.setParameter("fecha", de1);
            query.setParameter("cierre", 0);
            query.setParameter("sitio", sitio);
            serv = (Servicios) query.getSingleResult();

            if (serv != null) {
                if (serv.getEstado().getId().equals(27)) {

                    rw("nuevo estado del servicio");
                    serv.setValor(0);
                    serv.setBase(0);
                    //serv.setIndProc(1);
                    serv.setEstado(new Estados(26));
                    serv.setPorque(porque);
                    serv = em.merge(serv);
                    em.flush();

                } else {
                    rw("no hay anulacion del servicio....");
                }

                return serv;
            } else {
                return null;
            }

        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public String[] cantidadFacturasEnFecha(String fecha) throws Exception {
        String dataRecord[] = new String[2];
        try {
            String host = InetAddress.getLocalHost().getHostName();
            String sql = "";
            if (host.contains("central") || host.contains("aero")) {
                sql = "select count(*) from srv_camioneta where fecha_grab >=  TO_DATE('" + fecha + " 00:00','%Y-%m-%d %H:%M') and comprob != 'VES'";
                dataRecord[0] = "CONTABILIDAD-INFORMIX";
            } else {
                sql = "select count(*) from srv_camioneta where fecha_grab >= '" + fecha + "' and cierre = 1 ";
                dataRecord[0] = "VPS-HOSTINGER";
            }
            rw("sql_" + sql);
            Query query = em.createNativeQuery(sql);
            Object data = query.getSingleResult();
            Integer cantidad = new Integer(data.toString());
            dataRecord[1] = "" + cantidad;
            em.flush();
            return dataRecord;
        } catch (Exception e) {
            e.printStackTrace();
            return dataRecord;
        }
    }

    @Override
    public JsonDTO generarFotoContable(String serverName) throws Exception {
        //String d = DateHelper._3Dias(new Date(), (60 * 24 * 3));// hace 3 dias
        String d = DateHelper._3Dias();// hace 3 dias
        JsonDTO customers = new JsonDTO();
        customers.setError("no");
        try {
            String fecha = "";
            String and = "";
            if (serverName.equals("AERO")) {
                customers.setMsjError("no");
                fecha = "to_date('" + d + "','%Y-%m-%d')";//'dd-MM-yyyy'
                and = "";
            } else {
                fecha = "'" + d + "'";
                and = " and cierre = 1 ";
            }
            customers.setMsjError("no");
            //String sql = "select count(*),sum(valor) TOTAL,sum(valore) EFECTIVO,sum(valorc) CREDITO,sum(valord) DEBITO \n"
            //        + "from srv_camioneta where fecha >= " + fecha + " " + and + " and comprob != 'VES'";
            String sql = "select count(*),cast(sum(valor) as int) TOTAL,cast(sum(valore) as int) EFECTIVO,cast(sum(valorc) as int) CREDITO,cast(sum(valord) as int) DEBITO\n"
                    + " from srv_camioneta where fecha >= " + fecha + " " + and + " and comprob != 'VES'";

            rw("sql: " + sql);
            // count     total     efectivo     credito     debito    
            //--------  --------  -----------  ----------  --------- 
            //985       51809600  31588300     20322500    859000 
            Query query = em.createNativeQuery(sql);
            List<Object> results = query.getResultList();
            RtaDTO consolidado = new RtaDTO();

            int i = 0;
            String count = "";
            String total = "";
            String efectivo = "";
            String credito = "";
            String debito = "";
            for (Object oRow : results) {
                Object[] s = (Object[]) oRow;
                customers.setCount("" + s[0]);
                customers.setTotal("" + s[1]);
                customers.setEfectivo("" + s[2]);
                customers.setCredito("" + s[3]);
                customers.setError("ok");
                customers.setDebito("" + s[4]);
                i++;
            }
            if (!new isProduccion().isProduccion().equals("AERO")) {

                String sql2 = "select comprob||' No. '||numero||' $'||valor||' Desde: '||fecha||' cajero: '||grabador from srv_camioneta where cierre=0 order by fecha desc limit 1";
                rw("sql2:" + sql2);
                Query query2 = em.createNativeQuery(sql2);
                Object results2 = query2.getSingleResult();
                customers.setFacturaUltima("" + results2);

                String sql22 = "select comprob||' No. '||numero||' $'||valor||' Desde: '||fecha||' Cajero: '||grabador from srv_camioneta where cierre=0 order by fecha asc limit 1\n";
                rw("sql22:" + sql22);
                Query query22 = em.createNativeQuery(sql22);
                Object results22 = query22.getSingleResult();

                customers.setFacturaAntigua("" + results22);

                String sql3 = "select sum(valor) from srv_camioneta where cierre = 0 and fecha >= " + fecha + "";
                rw("sql3: " + sql3);
                Query query3 = em.createNativeQuery(sql3);
                Object lstCierre = query3.getSingleResult();
                customers.setTotalCange("" + lstCierre);

            }
            String sFecha = DateHelper.fechaIniMes();
            String[] varVps = cantidadFacturasEnFecha(sFecha);
            customers.setConsolidadoNameAero(varVps[0]);
            customers.setConsolidadoValueAero(varVps[1]);

            customers.setConsolidadoNameVps(varVps[0]);
            customers.setConsolidadoValueVps(varVps[1]);

            em.flush();

            return customers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Lugares> listLugares(String idStio
    ) {
        try {

            if (idStio != null && idStio.toString().equals("9")) {

                String sql = "SELECT l FROM Lugares l WHERE l.idSitio = :idSitio";
                rw(sql);
                Query query = em.createQuery(sql);
                query.setParameter("idSitio", new Integer(idStio));
                return (List<Lugares>) query.getResultList();
            } else {
                String sql = "SELECT l FROM Lugares l WHERE l.idSitio = :idSitio";
                rw(sql);
                Query query = em.createQuery(sql);
                if (idStio.toString().equals("1") || idStio.toString().equals("2") || idStio.toString().equals("7") || idStio.toString().equals("11") || idStio.toString().equals("12")) {
                    query.setParameter("idSitio", 1);
                } else {
                    query.setParameter("idSitio", new Integer(idStio));
                }

                return (List<Lugares>) query.getResultList();
            }

        } catch (Exception e) {
            //   e.printStackTrace();
            return null;
        }
    }

    @Override
    public CajerosAero usuarioById(Integer idUsusrio) throws Exception {
        try {
            String sql = "SELECT c FROM CajerosAero c WHERE c.id = :id";

            Query query = em.createQuery(sql);
            query.setParameter("id", idUsusrio);
            return (CajerosAero) query.getSingleResult();

        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public Servicios validaModipay(String transaccion) throws Exception {
        List<Servicios> serv = null;
        Servicios mySrv = null;
        Double val = 0.0;
        try {
            if (true) {
                String sql = "select sum(valor) as valor from srv_camioneta where transaccion = '" + transaccion + "'";
                rw("sqlSrv:" + sql);
                Object obj = em.createNativeQuery(sql).getSingleResult();
                if (obj != null) {
                    mySrv = new Servicios();
                    rw("valor:" + obj);
                    rw("valor2:" + obj.toString());
                    val = new Double(obj.toString());
                    mySrv.setValor(val.intValue());
                    em.flush();
                    rw("return " + val);
                    return mySrv;
                } else {
                    return null;
                }

            }

            String sql = "SELECT s FROM Servicios s WHERE s.transaccion   = :transaccion";
            rw("sqlSrv:" + sql);
            Query query = em.createQuery(sql);
            query.setParameter("transaccion", transaccion);
            serv = (List<Servicios>) query.getResultList();

            for (Servicios srv : serv) {
                val += srv.getValor();
            }

            mySrv.setValor(val.intValue());

            rw("Se ha descontado de la cuentaBonusModiPay: $" + val);
            return mySrv;
        } catch (Exception e) {
            e.printStackTrace();
            rw("no hay: numero:" + transaccion);
            return null;
        }

    }

    @Override
    public Ticket consultaModipay(String idTrans) throws Exception {
        Connection con = null;
        RtaDTO consolidado = new RtaDTO();
        Ticket myTick = null;
        try {
            String sql = "SELECt * from link_pago where id_transac ='" + idTrans + "' and estado_pago='true'";
            LogTest.rw("sql:= " + sql);
            DataSource yellowDS = dsGetYellowDS();
            con = yellowDS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                myTick = new Ticket();
                myTick.setPlaca(rs.getString("placa"));
                myTick.setMovil(Utilidades.getNumberInt(rs.getString("pin")));
                myTick.setPlaca(rs.getString("placa"));
                myTick.setValorTotal(rs.getString("valor"));
                myTick.setNitCliente(rs.getString("nit_benef"));
                //myTick.setFecha(rs.getString("valor"));
                myTick.setFechaMDP(rs.getString("fecha_modipay"));
                myTick.setDetalle(rs.getString("descrip"));
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
            rw("end...modipay");
        } catch (Exception e) {
            LogTest.rw("Error al ejecutar la transacion......" + e.getCause());
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        return myTick;
    }
}
