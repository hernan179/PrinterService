/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.socket;

import com.google.gson.Gson;
import com.rta.opain.delegate.dto.JsonDTO;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hsancheza
 */
public class ServletSocketClient extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
//        try {
//
//            String json = ""
//                    + "[{\"contrato\":\"3\",\"fecha\":\"diciembre 23 2014 12:004:31\",\"placa\":\"SIH505\",\"detalle\":\"P\",\"keyCommand\":\"3\",\"dias\":39,\"error\":\"ok\"}]";
//
//
//            SocketAddress sockaddr = new InetSocketAddress("192.168.23.135", 1234);
//            String modifiedSentence;
//
//            Socket socket = new Socket();
//
//            socket.connect(sockaddr, 1000);
//            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
//            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outToServer.writeBytes(json + '\n');
//            modifiedSentence = inFromServer.readLine();
//            out.print(modifiedSentence);
//            socket.close();
//
//            out.println(" ============MENSAJE ENVIADO!!! ======HOST " + "localhost" + "== PORT =" + 1234 + "==================");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("=====SOCKET FUERA DE LINEA ================");
//        }
        try {
            callerSocket(out);
        } catch (Exception ex) {
            Logger.getLogger(ServletSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void callerSocket(PrintWriter out) throws Exception {
        try {

            String jsonIn;
            String capitalizedSentence;
            ServerSocket serverSocket = new ServerSocket(1234);
            String actividad = "";

            do {
                out.println(String.format("SERVER INICIADO %s:%d", Inet4Address.getLocalHost().getAddress(), serverSocket.getLocalPort()));
                // actividad = jtaConsola.getText() != null && jtaConsola.getText().length() > 0 ? jtaConsola.getText() + "\n" : "";
                Socket socket = serverSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                jsonIn = inFromClient.readLine();
                Gson gson = new Gson();
                JsonDTO[] entra = gson.fromJson(jsonIn.trim(), JsonDTO[].class);
                System.out.println("entrada form cliente:  " + jsonIn);
                capitalizedSentence = jsonIn.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);

                JsonDTO dto = entra[0];

                if (entra[0].getKeyCommand().equals("3")) {
                    actividad += "VEHICULO";

                    out.println("<p>Salida...</p>");
                } else {
                    actividad += "Salida de parqueadero auxiliar av 26";
                }
                actividad += " " + dto.getPlaca();
                actividad += " CONTRATO[" + dto.getContrato() + "]";

                if (new Integer(dto.getDias()) > 60) {
                    String motivo = " BLOQUEADO POR MORA!!! ";
                    actividad += motivo;

                } else {
                    actividad += " DIAS MORA[" + dto.getDias() + "]";
                }

                actividad += " HORA[" + dto.getFecha() + "]";
                actividad += " SALIENDO DEL PARQUEADERO PRINCIPAL";
                System.out.println("nv mensaje:   " + actividad);

            } while (true);
        } catch (Exception e) {
            e.printStackTrace();


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
