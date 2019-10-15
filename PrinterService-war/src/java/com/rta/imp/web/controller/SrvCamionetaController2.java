/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.domain.Servicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.tools.DateHelper;
import static com.rta.opain.delegate.tools.DateHelper.calcularDiferenciaEntreFechas;
import java.util.Date;
import java.util.Enumeration;

/**
 *
 * @author hsancheza
 */
public class SrvCamionetaController2 extends HttpServlet {

    HttpSession session;
    static PrinterServicesDelegateRemote service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
            service = new EJBService().get();
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");
            response.setContentType("application/json");
            final String callback = request.getParameter("jsoncallback");

            Enumeration<String> parameterNames = request.getParameterNames();

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                rw("ParameterName: " + paramName + " ParameterValue: " + request.getParameter(paramName));
            }

            Date fechaInicial = DateHelper.stringToDate2(fecha1);
            Date fechaFinal = DateHelper.stringToDate2(fecha2);
            int dias = calcularDiferenciaEntreFechas(fechaInicial, fechaFinal);
            
            List<Servicios> salidas = null;
            if (dias <= 10) {
                rw("rango de fecha correcto_dias_"+dias);
                salidas = service.srvEnFechas(fecha1, fecha2);
            } else {
                rw("rango de fechas muy amplico_dias_"+dias);
            }
            if (request.getParameter("jsoncallback") != null && salidas != null) {
                String respuesta = callback + "({\"error\":\"no\"});";
                if (salidas != null && salidas.size() > 0) {
                    respuesta = com.rta.imp.web.controller.JsonAjax.generaJSONSrv(salidas);
                    respuesta = callback + "(" + respuesta + ");";
                }
                rw("GPS JSON RTA:= " + respuesta);
                out.write(respuesta.toString());
                out.flush();
                out.close();
            } else {
                session.setAttribute("salidas", salidas);
                response.sendRedirect("index2.jsp");
            }
        } catch (Exception e) {
            out.close();
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
