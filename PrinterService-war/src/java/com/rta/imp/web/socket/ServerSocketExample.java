/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author hsancheza
 */
public class ServerSocketExample {

    private ServerSocket server;
    private int port = 8888;
    static HashSet<ConnectionHandler> clientes = new HashSet<ConnectionHandler>();

    public ServerSocketExample() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnection() {
        System.out.println("Waiting for client message...");
        while (true) {
            try {
                Socket socket = server.accept();
                ConnectionHandler cliente = new ConnectionHandler(socket);
                clientes.add(cliente);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("clientes conectados:  " + clientes.size());
        }
    }
}

class ConnectionHandler implements Runnable {

    private Socket socket;
    Thread t = new Thread(this);

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        t.start();
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            String mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);
            salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");

            socket.close();//Aqui se cierra la conexión con el cliente


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
