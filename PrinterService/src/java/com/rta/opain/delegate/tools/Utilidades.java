/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import com.rta.opain.domain.Servicios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author hsancheza
 */
public class Utilidades {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    public static void main(String[] args)throws Exception{
        writeToFile("ABC000");
    }
    
    public static void writeToFile(String args) throws IOException {
        
        String ruta = "/opt/compilados/";
        
        String fileName = sdf.format(new Date()) + "_vehiculos_sin_actualziacion.txt";

        String fullRuta = ruta + fileName;
        File f = new File(fullRuta);
        String str = "";
        String nuevalinea = System.getProperty("line.separator");
        if (f.exists()) {      
         str += nuevalinea;
        } else {
            str = "REPORTES VEHICULOS SIN ACTUALIZACION HASTA LA FECHA "+sdf.format(new Date());
            OutputStream RutaArchivo = new FileOutputStream(fullRuta);
         str += nuevalinea;   
        }
        str += args;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fullRuta, true));
        writer.write(str);
        writer.close();

    }

    public static Integer getNumberIntNito(Servicios srv) {
/* 273 */     if (getNumberInt("" + srv.getValoro()).intValue() > 0) {
/* 274 */       return getNumberInt("" + srv.getValoro());
/*     */     }
/* 276 */     if (srv.getComprob().equals("VJS") || srv
/* 277 */       .getComprob().equals("VJX") || srv
/* 278 */       .getComprob().equals("VJN")) {
/* 279 */       LogTest.rw("FIX_4000_DEBIDO_NOT_UPPDATE_BEFORE");
/* 280 */       return Integer.valueOf(4000);
/*     */     } 
/* 282 */     return Integer.valueOf(0);
/*     */   }
    
    
    public static String loaderFile(String file) {
        String result;
        try {
            File f = new File(file);
            byte[] buffer = new byte[(int) f.length()];
            java.io.DataInputStream in = new java.io.DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (Exception e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
        }
        return result;
    }
    
    public static void main2(String[] args) {
        String filtro1 = "CL127*72|FELIPE MOTTA|8ññññ0179940 . ".toUpperCase();
        String direccion = filtro1.trim();

        String soloPermitidos = "";
        out:
        for (int i = 0; i < direccion.length(); i++) {
            String val = direccion.substring(i, i + 1);
            for (int j = 0; j < letras().length(); j++) {
                String val2 = letras().substring(j, j + 1);
                if (val2.equals(val)) {
                    soloPermitidos += val;
                    continue out;
                }
            }
        }
        System.out.println("filtro: " + soloPermitidos);
    }

    public static String getStringValue(String data) {
        if (data != null && !data.isEmpty() && !data.equals("null")) {
            data = data.replaceAll(" +", " ");//   
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

    public static String eliminarBarusar(String tmpDireccion) {
        String soloPermitidos = "";
        String direccion = tmpDireccion.toUpperCase();
        out:
        for (int i = 0; i < direccion.length(); i++) {
            String val = direccion.substring(i, i + 1);
            for (int j = 0; j < letras().length(); j++) {
                String val2 = letras().substring(j, j + 1);
                if (val2.equals(val)) {
                    soloPermitidos += val;
                    continue out;
                }
            }
        }
        return (soloPermitidos);
    }

    public static String letras() {
        int a = (int) 'a';
        String s = "";
        char[] abecedario = new char[26];
        char[] imprimio = new char[26];
        for (int i = 0; i < abecedario.length; i++) {
            abecedario[i] = (char) (a + i);
            imprimio[i] = 'n';
            s += abecedario[i];
        }
        s += " ";
        s += "1234567890";
        s += "#";
        s += "-";
        s += "|";
        return s.toUpperCase();
    }

    public static boolean checkAlpha(String str) {
        boolean respuesta = false;
        if ((str).matches("([a-z]|[A-Z]|\\s)+") || (str).matches("([0-9]|\\-)+")) {
            respuesta = true;
        }

        return respuesta;
    }

    public static boolean checkNonAlpha(String str) {
        boolean respuesta = false;
        if ((str).matches("([0-9]|\\-)+")) {
            respuesta = true;
        }
        return respuesta;
    }

    private long calcularTarifaAeropuerto(int metros) {
        long VALORUNIDAD = 150;
        int RECARGO_UNIDADES = 140;
        long RECARGO_NOCFES = 40;
        long unidad;
        if (metros <= 6000) {
            unidad = 145;
        } else {
            unidad = (metros / 100) + RECARGO_UNIDADES;
        }
        if (esFestivoNoc()) {
            unidad += RECARGO_NOCFES;
        }
        long val1 = (unidad * VALORUNIDAD);
        return val1;
    }

    public static boolean isOK(String jsonIn) {
        if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "" && !jsonIn.equals("no") && !jsonIn.equals("null")) {

            return true;
        } else {
            return false;
        }
    }

    public static String remove2(String input) {
        // Descomposición canónica
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Nos quedamos únicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\p{ASCII}+");
        return pattern.matcher(normalized).replaceAll("");
    }//remove2

    public static String eliminarCaracteresEspeciales(String input) {

        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ?";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCN";
        String output = input;

        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;

    }//remove1

    public static String remplazNoOk(String input) {

//        String output = input.replaceAll("?", "");
//        output = output.replaceAll("#", "No");
//        output = output.replaceAll("â€“", "No");
//        output = output.replaceAll("\\.", " ");
        return input;

    }

    public static boolean esFestivoNoc() {

        Date date = new Date("12/12/11 8:22:09 PM");
        System.out.println("Time in 24Hours =" + new SimpleDateFormat("HH:mm").format(date));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar fechaInicial = GregorianCalendar.getInstance();
        fechaInicial.setTime(DateHelper.toDateYYYYMMDDHHMM("2015-07-01 25:32 PM"));

        int hour = fechaInicial.get(Calendar.HOUR_OF_DAY);
        System.out.println("hour: " + hour);

        //si el dia de la semana de la fecha minima es diferente de sabado o domingo
        if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        } else {
            return false;
        }
    }

    public static String validaArray(String[] args, int indice) {
        if (args != null && args.length - 1 >= indice && args[indice] != null && !args[indice].isEmpty()) {
            return args[indice];
        }
        return "";
    }

    public static Integer getNumberInt(String val) {
        if (getStringBoolean(val) && val.matches("[0-9]*")) {
            return new Integer(val);
        } else {
            return 0;
        }
    }

    public static Long getNumberLong(String val) {
        if (getStringBoolean(val) && val.matches("[0-9]*")) {
            return new Long(val);
        } else {
            return 0l;
        }
    }

    public static Double getNumberDouble(String val) {
        try {
            return new Double(val);
        } catch (Exception e) {
            return new Double(0.0);
        }
    }

    public static boolean getStringBoolean(String val) {
        if (val != null && !val.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getString(String val) {
        if (val != null && !val.isEmpty()) {
            return (val);
        } else {
            return "";
        }
    }

    public static String Encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos  
        String base64EncryptedString = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos  
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public String isProduccionS() {
        try {
            String host = InetAddress.getLocalHost().getHostName();
            if (host.contains("desarrollo")) {
                return "srvapp-aero.losunos.net:8080";
            } else if (host.contains("aero")) {
                return "srvapp-aero.losunos.net:8080";
            } else if (host.contains("central")) {
                return "srvapp-central.losunos.net:8080";
            } else {
                return "200.91.204.38:8999";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean isProduccion() {
        try {
            String host = InetAddress.getLocalHost().getHostName();
            if (host.contains("aero")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String[] loadConfig() {
        String[] config = new String[2];
        try {
            String osname = System.getProperty("os.name");
            String hostFile = System.getProperty("user.dir");

            if (osname.toLowerCase().contains("windows")) {
                hostFile = "c:/socketConfig.properties";
            } else {
                hostFile = "/opt/compilados/socketConfig.properties";
            }

            Properties configFile = new Properties();
            try {
                configFile.load(new FileInputStream(hostFile));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            config[0] = configFile.getProperty("HOST");
            config[1] = configFile.getProperty("PORT");
            return config;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isOK2(String jsonIn) {
        if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "" && !jsonIn.equals("no") && !jsonIn.equals("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getJsonPlaca(String placa) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "        \"placa\":\"" + placa + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"22\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJsonUpdatePlacaContainer(String placa) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "        \"placa\":\"" + placa + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"24\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getIpPc() {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            return host;
        } catch (Exception e) {
            return "localhost";
        }
    }

    public static String getColCurrentTime() {
        return toDateYYMMDDHHMM(new Date());
    }

    public static String toDateYYMMDDHHMM(Date str_date) {
        String date = null;
        try {
            //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");//yyyy-MM-dd HH:mm:ss
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a", new Locale("es_ES"));

            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date = formatter.format(str_date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;

        }
        return date;
    }

    public String getJsonGeoRuteo(String direccion) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"direccion\":\"" + direccion + "\",\n"
                + "	   \"keyCommand\":\"13\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }
}
