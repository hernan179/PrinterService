package com.aero.rta.easypass;

import com.aero.rta.dto.JsonDTO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;
import static com.aero.rta.logs.LogsWriter.logs;
import com.google.gson.Gson;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;

public class ConexionCliente extends Thread implements Observer {

    private Logger log = Logger.getLogger(ConexionCliente.class);
    private Socket socket;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    String host;
    Integer port;

    public ConexionCliente(String host, Integer port, Socket socket) {
        this.socket = socket;
        this.host = host;
        this.port = port;

        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        String mensajeRecibido;
        boolean isOnline = true;

        if (isOnline) {
            try {
                if (socket.getOutputStream() != null) {
                    salidaDatos = new DataOutputStream(socket.getOutputStream());
                }
                if (socket.getInputStream() != null) {
                    entradaDatos = new DataInputStream(socket.getInputStream());
                }

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //convert ObjectInputStream object to String
                String json = (String) ois.readObject();


                Gson gson = new Gson();
                JsonDTO[] entra = gson.fromJson(json.trim(), JsonDTO[].class);
                String actividad = "";

                System.out.println("Message Received: " + actividad);
                //create ObjectOutputStream object
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //write object to Socket
                oos.writeObject("Hi Client " + actividad);
                //close resources
                ois.close();
                oos.close();
                socket.close();
                if (entra[0].getKeyCommand().equals("1")) {
                    System.out.println("line 74");
                    actividad = "Salida de parqueadero principal av 26";
                    JFrame f = new JFrame();
                    f.setSize(300, 300);
                    f.setVisible(true);

                } else {
                    System.out.println("line 81");
                    JFrame f = new JFrame();
                    f.setSize(300, 300);
                    f.setVisible(true);
                    actividad = "Salida de parqueadero auxiliar av 26";
                }

            } catch (Exception ex) {
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
                try {
                    System.out.println("7. leyecto el socket");
                    entradaDatos.close();
                    salidaDatos.close();
                    socket.close();
                    System.out.println("8. leyecto el socket");
                } catch (IOException ex2) {
                    logs("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            salidaDatos.writeUTF(arg.toString());
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
