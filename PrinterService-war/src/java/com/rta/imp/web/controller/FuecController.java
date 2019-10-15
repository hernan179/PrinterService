/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.google.gson.Gson;
import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.delegate.dto.Persona;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.delegate.tools.Helper;
import static com.rta.opain.delegate.tools.Helper.getNumeroLargo;
import com.rta.opain.domain.CajerosAero;
import com.rta.opain.domain.FuecService;
import com.rta.opain.pdf.CrearDocumentoFUEC;
import com.rta.opain.ws.conn.WebserviceConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hsancheza
 */
public class FuecController extends HttpServlet {

    @EJB
    PrinterServicesDelegateRemote service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map params = request.getParameterMap();
        Iterator i = params.keySet().iterator();
        while (i.hasNext()) {
            String key = (String) i.next();
            String value = ((String[]) params.get(key))[0];
            rw("key=" + key + ", value=" + value);
        }
        try {

            if (request.getParameter("buscar") != null) {
                String hotel = request.getParameter("hotel_srv");
                String placa = request.getParameter("placa");
                String josonRquest = Helper.getJSONBuscarPlacaContainer(placa);

                String respuesta = new WebserviceConnection().getStatusAccountRemote93_188_165_75(josonRquest);
                if (respuesta != null && respuesta.equals("no")) {
                    respuesta = "{\"error\":\"no\"}";
                }

                response.setContentType("text/html;charset=UTF-8");
                response.setContentType("application/json");
                final String callback = request.getParameter("jsoncallback");

                PrintWriter out = response.getWriter();
                rw("respuesta:" + respuesta);
                out.write(respuesta);
                out.close();
            } else {
                String cedula = request.getParameter("conductor_srv");

                String hotel = request.getParameter("hotel_srv");
                String[] nameRuta = hotel.split("\\|");

                String placa = request.getParameter("placa");

                String dataJson = request.getParameter("dataJson");

                rw("dataJson_" + dataJson);
                Ticket tiquete = new Ticket();
                tiquete = new Gson().fromJson(dataJson, Ticket.class);

                if (tiquete != null && tiquete.getPersonas() != null) {
                    for (Persona p : tiquete.getPersonas()) {
                        if (p.getCcConductor1().equals(cedula)) {
                            tiquete.setNombre(p.getNombreConductor1());
                            tiquete.setNitCliente(p.getCcConductor1());
                            tiquete.setEmpresa(p.getEmpresaVehiculo());
                        }
                    }
                }

                String json = new Gson().toJson(tiquete);

                rw("jsonTK:" + json);

                tiquete.setNombre(nameRuta[0]);

                tiquete.setInfoRuta(nameRuta[1]);

                FuecService servicio = new FuecService();
                servicio.setFecha(new Date());
                servicio.setAutoPlaca(placa);
                servicio.setJson(json);

                servicio = service.crearServicioFuec(servicio);

                tiquete.setConsecutivo("" + servicio.getId());
                tiquete.setId("" + servicio.getId());
                tiquete.setNumeroLargo(getNumeroLargo(tiquete));

                //new CrearDocumentoFUEC().generarFactura2(tiquete, null, "Impreso", tiquete.getUsuario().getUsuario(), consecutivo, null);
                byte[] bytes = new CrearDocumentoFUEC().generarFactura2(tiquete, null, "reimpreso", "usuarioSistema", request.getParameter("crear2"), tiquete.getConsecutivo());
                String tipoArchivo = "fuec_" + tiquete.getConsecutivo() + "_" + "_.pdf";

                //response.setContentType("text/html;charset=UTF-8");
                response.setContentLength(bytes.length);

                ServletOutputStream ouputStream = response.getOutputStream();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + tipoArchivo);
                response.setHeader("Cache-Control", "cache, must-revalidate");
                response.setHeader("Pragma", "public");

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

//                RequestDispatcher dispatcher = request.getRequestDispatcher("/fuec.jsp?newFuec=" + servicio.getId());
//                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
