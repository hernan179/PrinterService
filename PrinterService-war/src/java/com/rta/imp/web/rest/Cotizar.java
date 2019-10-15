/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.rest;

import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.dto.GenericDTO;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.domain.TempVehiculo;
import com.rta.opain.node.EndPointRemote;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author hsancheza
 */
@Path("cotizar")
public class Cotizar {

    @EJB
    EndPointRemote service;

    @POST
    @Path("/precio")
    @Produces("application/json")
    public JsonDTO loadRequest(InputStream intrada) {
        JsonDTO rta = new JsonDTO();
        StringBuilder jsonVales = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(intrada));

            String line = null;
            while ((line = in.readLine()) != null) {
                jsonVales.append(line);
            }
            rw("___________________________________________________________________________");
            rta.setError("no");
            rw("precio_" + jsonVales);

        } catch (Exception e) {
            e.printStackTrace();
        }
        rta.setError("no");
        return rta;
    }

    @POST
    @Path("/carrosImp")
    @Produces("application/json")
    public String carrosImp(InputStream intrada) {

        rw("Entrada..");
        try {
            String rta = service.misCarrosVANGO();
              rw("rta:.."+rta);
            return rta;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
