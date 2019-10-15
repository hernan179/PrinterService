/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.google.gson.Gson;
import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.imp.web.rest.Cotizar;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.tools.Utilidades;
import com.rta.opain.node.EndPointRemote;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author hsancheza
 */
public class CotizarPrecio extends HttpServlet {

    @EJB
    EndPointRemote service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        rw("geo_________________________________________________");

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);

            }
            String callback = request.getParameter("jsoncallback");
            String json = Utilidades.Encriptar(jb.toString());//hsa
            
            json = Utilidades.eliminarCaracteresEspeciales(json);
            
            String RTA = service.gateWayConnection(json);
            response.setContentType("text/javascript");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Access-Control-Allow-Origin", "*");
            // response.setContentType("application/json");
            String respuesta = RTA;//.substring(1, RTA.length()-1);
            rw("geo_"+respuesta);
            out.write(respuesta);
            out.close();

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
