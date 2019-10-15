/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.contab;

import com.google.gson.Gson;
import com.rta.opain.activate.PrinterServicesActivateRemote;
import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.tools.DateHelper;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.delegate.tools.SendMail;
import static com.rta.opain.delegate.tools.StringTools.getNumberInt;
import com.rta.opain.delegate.tools.Utilidades;
import com.rta.opain.delegate.tools.isProduccion;
import com.rta.opain.node.EndPointRemote;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import com.rta.opain.ws.conn.WebserviceConnection;
import java.io.File;
import java.io.FileInputStream;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@Singleton
@Lock(LockType.READ)
public class CrontabEJB {

    @EJB
    EndPointRemote service;

    @EJB
    PrinterServicesDelegateRemote service2;

    @EJB
    PrinterServicesActivateRemote service3;

    static Integer turno = 0;

    @Schedules({
        @Schedule(dayOfWeek = "Mon,Tue, Wed, Thu, Fri, Sat", hour = "9",minute = "5", persistent = false)
    })
    public void guardarStock() {//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a", new Locale("es_ES"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");//.format(new Date());
            rw("Ejecucion container vehiculos VIAJES a las " + sdf.format(new Date()));
            String host = InetAddress.getLocalHost().getHostName();

            if (host.contains("central")) {
                //if (false) {
                new SendMail().sendMesageContainer("INICIO CARGUE CONTAINER", "Se da por INICIO la tarea programada a las " + sdf.format(new Date()) + " desde " + host, null);
                String val = service.actualizarContainer(null);
                if (val != null && !val.isEmpty() && !val.equals("no")) {
                    String ruta = "/opt/compilados/";
                    String fileName = "_vehiculos_sin_actualziacion.txt";
                    File fff = new File(ruta+Utilidades.sdf.format(new Date()) + fileName);
                    FileInputStream fileInputStream = new FileInputStream(fff);
                    long byteLength = fff.length(); //bytecount of the file-content
                    byte[] bytes = new byte[(int) byteLength];
                    fileInputStream.read(bytes, 0, (int) byteLength);
                    MimeBodyPart part = new SendMail().addFileVehiculos(bytes,fileName);
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(part);
                    rw("enviando email---");
                    new SendMail().sendMesageContainer("FIND CARGUE CONTAINER", "Se cargaron " + val + " carros, TERMINA la tarea a las " + sdf.format(new Date()) + " desde " + host, multipart);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Schedules({
        @Schedule(dayOfWeek = "*", hour = "6", persistent = false)
    })
    public void limpiarSateliteCache(String into, String imei) throws Exception {
        execueteCleaner(into, imei);
    }

    @Schedule(minute = "10", hour = "3", dayOfWeek = "*", info = "Todos los dias a las 00:00")
    public void performTask() {
        long timeInit = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        rw(":. Inicio TareaProgramada.");
        try {
            timeInit = System.currentTimeMillis();
            String host = InetAddress.getLocalHost().getHostName();
            turno = 0;
            rw("Nuevo valor para la variable turno " + turno + "start : " + timeInit);
            String serverName = "AERO";
            JsonDTO fotoAERO = null;
            JsonDTO fotoVPS = new JsonDTO();
            if (host.contains("central")) {
                fotoAERO = service2.generarFotoContable(serverName);
                rw("Foto AERO ok, generando foto VPS");

                String jsonAero = new Gson().toJson(fotoAERO);
                rw("foto_Aero_" + jsonAero);

                fotoVPS.setKeyCommand("27");
                String jsonAux = new Gson().toJson(fotoVPS);
                rw("send_trama_vps_"+jsonAux);
                String json = new WebserviceConnection().getStatusAccountRemote185_80_129_150(jsonAux);
                fotoVPS = new Gson().fromJson(json, JsonDTO.class);
                
                 rw("foto_vps_" + json);
                String val = DateHelper._3Dias();// hace 3 dias
                // String val = DateHelper._3Dias(new Date(), (60 * 24 * 3));// hace 3 dias
                Integer val1 = getNumberInt(fotoVPS.getTotal()) - getNumberInt(fotoAERO.getTotal());
                if (val1 == 0) {
                    fotoAERO.setResultado("Foto conciliada sin novedad");
                } else {
                    fotoAERO.setResultado("Revisar diferencia");
                }
                if (getNumberInt(fotoVPS.getConsolidadoValueVps()) > getNumberInt(fotoAERO.getConsolidadoValueAero())) {
                    fotoAERO.setConsolidadoFaltante(" Faltantes <" + (getNumberInt(fotoVPS.getConsolidadoValueVps()) - getNumberInt(fotoAERO.getConsolidadoValueAero())) + ">");
                } else {
                    fotoAERO.setConsolidadoFaltante("");
                }
                fotoAERO.setDiferencia(val1.toString());
                String mensaje = SendMail.getMessageBody(fotoAERO, fotoVPS, val);

                new SendMail().sendMesageContainer("Foto Contable VPS - AERO", mensaje, null);
                time = System.currentTimeMillis() - timeInit;
            } else {
                rw("Foto debe generarse desde jboss aero");
            }

        } catch (Exception e) {
            e.printStackTrace();
            time = System.currentTimeMillis() - timeInit;
        }
    }

    public void execueteCleaner(String into, String imei) throws Exception {
         String host = InetAddress.getLocalHost().getHostName();
         if (host.contains("central")||host.contains("aero")) {

            String fecha = DateHelper.nowTimeDateEasy();
            String trama = "0,deleteAllCache,true";
            if (into != null && !into.isEmpty() && into.equals("0")) {
                trama = "0," + imei + ",true";
            }

            String sentence = trama;

            System.out.println("send: " + sentence);
            String modifiedSentence;
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket("104.196.119.62", 11020);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //do{
            //sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("RTA_" + modifiedSentence + " time: " + new Date());
            clientSocket.close();
            // }while(true);
            clientSocket.close();//ockets (104.196.119.62 )
            String mesaje = "<p>Inicio: " + fecha + "</p>";
            mesaje += "<p>Se borraron los puntos APPs del cache!</p>";
            mesaje += "<p>Fin: " + DateHelper.nowTimeDateEasy() + "</p>";

            new SendMail().sendMesageContainer("Limpiados satelites", mesaje, null);
        }
    }
}
