/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.node;

import com.rta.opain.delegate.dto.GenericDTO;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.domain.TempVehiculo;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.jws.WebMethod;

/**
 *
 * @author hsancheza
 */
@Remote
public interface EndPointRemote {

    public void cleanup(String into, String imei);

    public void eassyPassLoadContratos();

    @WebMethod
    public String gateWayConnection(String json) throws Exception;

    public String test();

    //public void performTask();
    public String[] reprocesar(Date fecha);

    @WebMethod
    public void crearCopiaRespaldo(String json);

    public String updateSQLPendientesVPS(String sql) throws Exception;

    public String actualizarContainer(String placa) throws Exception;

    public String misCarros() throws Exception;

    public String misCarrosVANGO() throws Exception;

}
