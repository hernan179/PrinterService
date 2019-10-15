/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.main;

import com.aero.aplication.dto.RtaDTO;
import com.aero.aplication.tools.Helper;
import com.aero.aplication.tools.Utilidades;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Date;
import javax.net.ssl.SSLSocket;

/**
 * Esta clase gestiona el envio de datos entre el cliente y el servidor.
 *
 * @author Ivan Salas Corrales
 * <http://programando-o-intentandolo.blogspot.com.es/>
 */
public class ConexionServidor {

    public static void main(String[] args) {
        String servidor = "185.80.129.150";
        int puerto = 18282;             // puerto de daytime

        String selectLoginAero = "src/vjs.json";

        try {
            // Se abre un socket conectado al servidor y al
            // puerto estándar de echo

            Socket socket = new Socket(servidor, puerto);
            System.out.println("Socket Abierto.");
            String RTA_CARGUE = null;
            do {
                DataOutputStream salidaDatos = new DataOutputStream(socket.getOutputStream());
                String noData = Utilidades.Encriptar(Helper.getJSONConsultaSrv(args[0]));
                System.out.println("send:  " + noData);
                salidaDatos.writeUTF(noData);
                DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());
                // Se cierra el canal de entrada
                RTA_CARGUE = entradaDatos.readUTF();

                if (Helper.isOK(RTA_CARGUE)) {
                    entradaDatos.close();
                    System.out.println("entrada: " + RTA_CARGUE);
                    socket.close();
                    boolean isAllOk = false;
                    isAllOk = true;
                    RtaDTO obj = new Gson().fromJson(RTA_CARGUE, RtaDTO.class);
                    if (obj.getError().equals("ok") && obj.getAlertas() != null && obj.getAlertas().size() > 0) {

                    }
                }
            } while (Helper.isOK(RTA_CARGUE));

        } catch (UnknownHostException e) {
            System.out.println(e);
            System.out.println(
                    "Debes estar conectado para que esto funcione bien.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
