/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.delegate.dto.JsonDTO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hsancheza
 */
public class ContratosController extends HttpServlet {

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

        OutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=contratos.xls");

        try {
            System.out.println("buscando contratos: ");
            PrinterServicesDelegateRemote service = new EJBService().get();  
            List<JsonDTO> contratos = service.lstContratos();//linea y empresa

            String salida = "";
            if (contratos != null) {
                salida += "placa;n_asign;codigo_eq;placa;fecha;clased;dias;nombre;identificacion;modelo;marca;telefono,empresa,linea";
                salida += "\n";
                for (JsonDTO v : contratos) {
                    salida += v.getPlaca().trim() + ";" + v.getnAsign().trim() + ";" + v.getContrato().trim() + ";" + v.getPlaca().trim() + ";" + v.getFecha().trim() + ";" + v.getClased().trim() + ";" + v.getDias()+";"  + v.getNombreConductor()+";"+v.getCedulaConductor()+";"+v.getModelo()+";"+v.getMarca()+";"+v.getTelefono()+";"+v.getEmpresaId()+";"+v.getLineaCar();
                    salida += "\n";

                }
            }
            out.write(salida.getBytes());
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
