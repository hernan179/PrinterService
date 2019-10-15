/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.opain.delegate.tools.Helper;
import static com.rta.opain.delegate.tools.Helper.rw;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hsancheza
 */
public class AddSrvController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("application/json");

            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                rw("ParameterName: " + paramName + " ParameterValue: " + request.getParameter(paramName));
            }
            rw("lectura de archivo");
            String s;
            BufferedReader br = new BufferedReader(new FileReader("/opt/compilados/ves.csv"));
            String sql = "";
            while ((s = br.readLine()) != null) {
                rw("line_"+s);
                String[] datos = s.split("\\;");
                if (datos != null && datos.length == 6) {
                    String fecha = datos[0];
                    String ids = datos[1];
                    String costo = datos[2];
                    String nombre = datos[3];
                    String cc = datos[4];
                    String placa = datos[5];
                    String[] diffFecha = fecha.split("\\/");
                    String dia = diffFecha[0];
                    String mes = diffFecha[1];
                    String anio = diffFecha[2];

                    sql = "INSERT INTO link_pago(base, nit_empresa, nit_sucursal, nit_benef, placa, pin, concepto, cuotas, valor, ind_ctrl, referencia,"
                            + "fecha_grab, grabador, id_transac, tipo_pago, estado_pago, currency, pais, ciudad, descrip, fecha_modipay)"
                            + "VALUES('yellow', 830087404, 830087404, " + cc + ", 'VES', 0, 1, 1, " + costo + ", 1, 'Ves modipay-load-02', "
                            + "EXTEND(MDY(" + mes + "," + dia + "," + anio + "), YEAR to SECOND)+11 UNITS HOUR+29 UNITS MINUTE+34 UNITS SECOND, 'hsa',"
                            + " '" + ids + "', 1, 'true', 'COP', 57, 11001, 'Ves mal amigo send email', EXTEND(MDY(" + mes + "," + dia + "," + anio + "),"
                            + "YEAR to SECOND)+11 UNITS HOUR+29 UNITS MINUTE+34 UNITS SECOND);";
                    Helper.writeToFile(sql, "ves_load.txt");

                }
            }
            rw("sql_" + sql);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
