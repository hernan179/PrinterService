/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.domain.RegisterInOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.rta.imp.web.controller.LogTest.rw;
import java.util.Set;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hsancheza
 */
public class InOutController extends HttpServlet {

    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        final String callback = request.getParameter("jsoncallback");
        try {

            rw("buscando salidas......");

            session = request.getSession();
            PrinterServicesDelegateRemote service = new EJBService().get();
            rw("inicio...");
            rw("service:   "+service);
            List<RegisterInOut> salidas = service.ultimoLogs();
            rw("end...");
            if (request.getParameter("jsoncallback") != null) {
                String respuesta = callback + "({\"error\":\"no\"});";
                if (salidas != null && salidas.size() > 0) {
                    respuesta = com.rta.imp.web.controller.JsonAjax.generaJSONDir(salidas);
                    respuesta = callback + "(" + respuesta + ");";
                }
              //  rw("GPS JSON RTA:= " + respuesta);
                out.write(respuesta.toString());
                out.flush();
                out.close();
            } else {
                session.setAttribute("salidas", salidas);
                response.sendRedirect("index2.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
