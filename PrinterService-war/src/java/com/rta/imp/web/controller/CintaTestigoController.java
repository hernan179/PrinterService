/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.imp.pdf.CintaTestigo;
import com.google.gson.Gson;
import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.delegate.dto.RtaDTO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.taglibs.standard.tag.common.core.CatchTag;

/**
 *
 * @author hsancheza
 */
public class CintaTestigoController extends HttpServlet {

    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // PrintWriter out = response.getWriter();

        session = request.getSession();
        try {
            PrinterServicesDelegateRemote service = new EJBService().get();
            String fecha = request.getParameter("fecha");
            String fecha2 = request.getParameter("fecha2");

            RtaDTO jsonData = service.crearCintaTestigo(fecha, fecha2);
            if (jsonData != null) {
                String respuesta = new Gson().toJson(jsonData);
                response.setContentType("text/javascript");
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Access-Control-Allow-Origin", "*");
                rw("JSON_RTA_" + respuesta);
                //out.write(respuesta);
                //out.close();

                ServletContext cntx = request.getServletContext();
                // Get the absolute path of the image
                InputStream imageInputStream = cntx.getResourceAsStream("/WEB-INF/img/img.gif");
                Image image = ImageIO.read(imageInputStream);
                com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(image, null);

                rw("iamgen_" + image);
                byte[] bytes = null;
                try {
                    bytes = new CintaTestigo().generarFactura(jsonData, fecha, fecha2, img);
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                if (bytes != null) {
                    response.setContentLength(bytes.length);
                    ServletOutputStream ouputStream = response.getOutputStream();
                    response.addHeader("content-disposition", "attachment; filename=" + "cinta_testigo" + "_final.pdf");
                    ouputStream.write(bytes, 0, bytes.length);
                    ouputStream.flush();
                    ouputStream.close();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //out.close()
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
