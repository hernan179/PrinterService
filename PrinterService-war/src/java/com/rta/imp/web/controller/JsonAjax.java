/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.google.gson.Gson;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.domain.RegisterInOut;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import static com.rta.opain.delegate.tools.DateHelper.toDateYYYYMMDDHHMM;
import static com.rta.opain.delegate.tools.DateHelper.toDateYYYYMMDDHHMMOK;
import java.util.Set;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.domain.Servicios;

public class JsonAjax {

    public static String generaJSONDir(List<RegisterInOut> salidas) {

        List<JsonDTO> lista = new ArrayList<JsonDTO>();
        for (RegisterInOut r : salidas) {

            JsonDTO json = new JsonDTO();
            json.setFecha(toDateYYYYMMDDHHMM(r.getFecha()));
            json.setDetalle(r.getDetalle().toString().trim());
            json.setPlaca(r.getVehiculoPlaca().toString().trim());
            json.setContrato(r.getNumeroContrato().toString().trim());
            json.setDias(r.getDias());
            //json.setError("ok");
            lista.add(json);
        }
        JsonReturn json = new JsonReturn();
        json.setServicios(lista);
        json.setError("ok");

        return new Gson().toJson(json);

    }

    public static String generaJSONSrv(List<Servicios> servicios) {

        List<JsonDTO> lista = new ArrayList<JsonDTO>();
        for (Servicios r : servicios) {

            JsonDTO json = new JsonDTO();
            json.setNumero(r.getNumero().toString());
            json.setFecha(toDateYYYYMMDDHHMMOK(r.getFecha()));
            json.setDetalle(r.getAddres());
            json.setPlaca(r.getAutoPlaca());
            json.setValor(r.getValor().toString());
            json.setComprob(r.getComprob().toString());
            json.setUsuario(r.getUsuarios().getNombre()); 
            json.setNit(r.getNit().toString());
            //json.setError("ok");
            lista.add(json);
        }
        JsonReturn json = new JsonReturn();
        json.setServicios(lista);
        json.setError("ok");

        return new Gson().toJson(json);

    }
}
