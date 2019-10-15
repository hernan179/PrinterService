/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.easypass.tools;

import com.aero.rta.dto.JsonDTO;
import com.aero.rta.dto.RtaDTO;
import com.google.gson.Gson;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hsancheza
 */
public class Utilitario {

    public static File crearReporte(String data, String archivo) {
        String ruta = null;
        try {
            String current = new File(".").getCanonicalPath();
            ruta = current + "/" + archivo;
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true));
            writer.write(data);
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e) {
        }
        return new File(ruta);
    }

    public static String dinamicFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String fileFecha = sdf.format(new Date());
        return fileFecha;
    }

    public static void writeFile() {
        try {
            String fileName = "contratos_" + dinamicFile() + ".xls";
            File f = new File(fileName);
            if (!(f.exists() && !f.isDirectory())) {
                System.out.println("creando el archivo....");
                String fileUrl = loadFileUrl();
                final String dir = System.getProperty("user.dir");
                FileWriter file = new FileWriter(new File(dir + "/" + fileName));
                file.write(fileUrl);
                file.close();
            } else {
                System.out.println("ya esta el archivo cargado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadFileJson(String value) {
        String fileName = "contratos_" + dinamicFile() + ".xls";
        try {
            writeFile();
        } catch (Exception e) {
            fileName = "contratos.xls";
        }
        String placa = "";
        if (value.matches("[0-9]*")) {
            placa = value;
        } else {
            if (value.length() > 6) {
                placa = value.substring(value.length() - 6, value.length());
            } else {
                placa = value;
            }
        }
        System.out.println("loadPlaca_" + value);
        System.out.println("placa_" + placa);
        try {
            final String dir = System.getProperty("user.dir");
            FileReader fr = new FileReader(dir + "/"+fileName);
            BufferedReader br = new BufferedReader(fr);
            String s;
            int contador = 1;
            String dias = "0";
            String fecha = "";
            String contrato = "0";
            String myPlaca = "NOT_FOUND";
            String error = "no";
            String imprimeTiquete = "true";
            String msjError = "no";

            RtaDTO tt = new RtaDTO();

            List<JsonDTO> lMorosos = new ArrayList<JsonDTO>();
            while ((s = br.readLine()) != null) {
                JsonDTO DIAS = new JsonDTO();
                String[] datos = s.split("\\;");

                DIAS.setContrato(datos[2]);
                tt.setError("ok");
                //System.out.println("FOUND IT:     =>  " + datos[0] + " placa: " + placa);
                if (datos != null && datos.length > 1 && contador > 1) {
                    if (value.matches("[0-9]*")) {
                        if (datos[2].toUpperCase().equals(placa.toUpperCase())) {
                            System.out.println("line 91 CONTRACTO:   " + datos[2] + " =>  " + placa);
                            dias = datos[6];
                            fecha = datos[4];
                            contrato = datos[2];
                            myPlaca = datos[0];
                        }
                    } else {
                        if (datos[0].toUpperCase().equals(placa.toUpperCase())) {
                            System.out.println("PLACA:   " + datos[0] + " =>  " + placa);
                            dias = datos[6];
                            fecha = datos[4];
                            contrato = datos[2];
                            myPlaca = datos[0];
                        }
                    }
                    DIAS.setPlaca(myPlaca);
                    if (!myPlaca.equals("NOT_FOUND")) {
                        System.out.println("PLACA_" + placa + "_LINE_" + s + "_dias_" + dias);
                        DIAS.setDias(dias);
                        if (getInt(dias) > 0) {
                            DIAS.setDias(dias);
                            if (getInt(dias) >= 75) {

                                DIAS.setError("no");
                                DIAS.setMsjError("BLOQUEADO POR MORA");
                                DIAS.setImprimeTiquete("false");
                            } else {
                                DIAS.setImprimeTiquete("true");
                                DIAS.setError("no");
                                DIAS.setMsjError("DIAS EN MORA " + DIAS.getDias());
                            }
                        } else {
                            DIAS.setDias("0");
                            DIAS.setError("ok");
                            DIAS.setMsjError("HABILITADO");
                            DIAS.setImprimeTiquete("true");

                        }

                        lMorosos.add(DIAS);
                        tt.setAlertas(lMorosos);
                        fr.close();
                        return new Gson().toJson(tt);
                    }

                }
                contador++;
            }
            //String json = "{\"alertas\":[{\"contrato\":\"" + contrato + "\",\"fecha\":\"" + fecha + "\",\"placa\":\"" + myPlaca + "\",\"detalle\":\"Salida del parqueadero\",\"keyCommand\":\"3\",\"dias\":" + dias + ",\"error\":\"ok\"}],\"error\":\"ok\"}";
            String json = Utilitario.getJSONRta(contrato, fecha, myPlaca, msjError, dias, error, imprimeTiquete);

            fr.close();
            return json;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String loadFileUrl() throws Exception {
        String paramero = "?file=ok";
        String OK = "http://192.168.20.53:8280/PrinterService-war/ContratosController";

        URL website = new URL(OK);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }

        in.close();

        return response.toString();
    }
    
    public static String loadFileUrlGeo(String la,String lo,String key) throws Exception {
        String paramero = "?file=ok";
        String OK = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+la+","+lo+"&key="+key;

        URL website = new URL(OK);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }

        in.close();

        return response.toString();
    }

    public static void main(String[] args) throws Exception {
        String la="3.4639301";
        String lo="-76.53079688";
        String key="AIzaSyC1lI_gxmH4yCb1ykLxpXRXYOkmaTXuZ-g";
       String rta= loadFileUrlGeo(la,lo,key);
        System.out.println("rta:"+rta);
        //writeFile();

    }

    public void escribirRegistro(String placa, String contrato, String fecha) {
        String filename = "salidasCarros.txt";
        try {

            String ruta = new File(".").getCanonicalPath() + "/" + filename;
            File file = new File(ruta);
            String prevText = "";
            if (file.exists()) {
                prevText = loaderFile(filename);
            }

            FileWriter fw = new FileWriter(ruta);
            fw.write(placa + ";" + contrato + ";" + fecha + "\n" + prevText);//appends the string to the file
            if (fw != null) {
                fw.close();
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    static String loaderFile(String file) {
        String result;
        try {
            File f = new File(file);
            byte[] buffer = new byte[(int) f.length()];
            java.io.DataInputStream in = new java.io.DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
        }
        return result;
    }

    public long[] calcularDiferenciaEntreFechas(Date inicio, Date fin) {

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        // Establecer las fechas
        cal1.setTime(inicio);
        cal2.setTime(fin);
        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        // calcular la diferencia en milisengundos
        long milisegundos = milis2 - milis1;
        // calcular la diferencia en segundos
        long segundos = milisegundos / 1000;

        // calcular la diferencia en minutos
        long minutos = milisegundos / (60 * 1000);
        // calcular la diferencia en horas
        long horas = milisegundos / (60 * 60 * 1000);
        // calcular la diferencia en dias
        long dias = milisegundos / (24 * 60 * 60 * 1000);

        long[] values = new long[5];
        values[0] = milisegundos;
        values[1] = segundos;
        values[2] = minutos;
        values[3] = horas;
        values[4] = dias;
        return values;
    }

    public static Date restarHoras(Date fecha, int horas) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha); // Configuramos la fecha que se recibe
            calendar.add(Calendar.MINUTE, -horas);  // numero de días a añadir, o restar en caso de días<0
            return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        } catch (Exception e) {
//            e.printStackTrace();
            return fecha;
        }
    }

    public static boolean hayInternet() {
        String estado = "no";
        try {
            //return false;
            URL ruta = new URL("http://www.google.es");
            URLConnection rutaC = ruta.openConnection();
            rutaC.connect();
            return true;
        } catch (Exception e) {
            estado = "desactivado";
            return false;
        }
    }

    public static boolean hayInternet2() {
        String dirWeb = "www.google.com";
        int puerto = 80;
        try {
            Socket s = new Socket(dirWeb, puerto);
            s.setSoTimeout(1000);
            if (s.isConnected()) {
                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
    }

    public static String getJSONRta(String contrato, String fecha, String placa, String detalle, String dias, String error, String omprimeTiquete) {
        return "\n"
                + "{\"alertas\":["
                + "    {\n"
                + "        \"contrato\":\"" + contrato + "\",\n"
                + "	   \"fecha\":\"" + fecha + "\",\n"
                + "	   \"placa\":\"" + placa + "\",\n"
                + "	   \"detalle\":\"" + detalle + "\",\n"
                + "	   \"omprimeTiquete\":\"" + omprimeTiquete + "\",\n"
                + "	   \"dias\":\"" + dias + "\",\n"
                + "	   \"error\":\"" + error + "\",\n"
                + "	   \"keyCommand\":\"3\"\n"
                + "    }],\n"
                + "	   \"error\":\"" + error + "\"}\n"
                + "\n"
                + "";

    }

    public JFrame position(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);
        frame.setLocation(x, y);
        return frame;
    }

    public static String getStringTrim(String data) {
        if (data != null) {
            data = data.replaceAll(" +", " ");
            if (data.length() > 1) {
                if (data.substring(0, 1).equals(" ")) {
                    data = data.trim();
                }
                if (data.substring(data.length() - 1, data.length()).equals(" ")) {
                    data = data.substring(0, data.length() - 1);
                } else {
                    data = data.substring(0, data.length());
                }
                return data;
            } else {
                return data.trim();
            }
        } else {
            return "";
        }
    }

    public static Integer getInt(String values) {
        if (values != null && !values.isEmpty() && values.matches("[0-9]*")) {
            return new Integer(values);
        } else {
            return new Integer("0");
        }
    }

    public static boolean isOK(String jsonIn) {
        if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "" && !jsonIn.equals("no") && !jsonIn.equals("null")) {

            return true;
        } else {
            return false;
        }
    }

    public static String getColCurrentTime() {
        return toDateYYMMDDHHMM(new Date());
    }

    public static String toDateYYMMDDHHMM(Date str_date) {
        String date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a", new Locale("es_ES"));
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date = formatter.format(str_date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;

        }
        return date;
    }

    public static Date toDateYYMMDDHHMM(String str_date) {
        Date date = null;
        try {
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a", new Locale("es_ES"));
            date = formatter.parse(str_date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;

        }
        return date;
    }

    public static String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }
}
