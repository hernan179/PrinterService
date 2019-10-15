/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class implements java socket client
 *
 * @author pankaj
 *
 */
public class SocketClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();

        Socket socket = null;
        String SERVER_ADDRESS = "localhost";
        Integer TCP_SERVER_PORT = 1234;

        if (hostAvailabilityCheck(socket, SERVER_ADDRESS, TCP_SERVER_PORT)) {

            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            int i = 0;
            String json = "["
                    + "    {"
                    + "        \"empresaId\":\"12\",\n"
                    + "	\"contrato\":\"" + 1 + "\",\n"
                    + "	\"keyCommand\":\"1\"\n"
                    + "    }"
                    + "]";

            //do {1

            //establish socket connect-ion to server
            // socket = new Socket("192.168.35.39", 1234);
            socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("enviar mensaje al socket");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String entrada = br.readLine();

            //write to socket using ObjectOutputStream
            oos.writeObject(json);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("cliente socket lee del servidor: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
            i++;
            //} while (i < 10);
        } else {
            System.out.println("socket no disponible");
        }
    }

    public static boolean hostAvailabilityCheck(Socket socket, String SERVER_ADDRESS, Integer TCP_SERVER_PORT) {

        boolean available = true;
        try {
            socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
            if (socket.isConnected()) {
                socket.close();
            }
        } catch (UnknownHostException e) { // unknown host 
            available = false;
            socket = null;
        } catch (IOException e) { // io exception, service probably not running 
            available = false;
            socket = null;
        } catch (NullPointerException e) {
            available = false;
            socket = null;
        }
        return available;
    }
}