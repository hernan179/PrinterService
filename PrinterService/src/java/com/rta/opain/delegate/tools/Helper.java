/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import com.google.gson.Gson;
import com.itextpdf.text.Image;
import javax.swing.ImageIcon;
import com.itextpdf.text.pdf.PdfWriter;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.Ticket;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 *
 * @author hsancheza
 */
public class Helper {

    public static Date getColCurrentTime2() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Colombia"));
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.format(new Date());
        return new Date();
    }

    public static String getNumeroLargo(Ticket tiquete) {
        String YEAR = fechaActual(new Date()).split("\\-")[0];

        return "425497301" + YEAR + tiquete.getNumeroInterno() + tiquete.getId();
    }

    public static boolean esHoraPico() {

        Calendar fechaInicial = GregorianCalendar.getInstance();
        fechaInicial.setTime(new Date());
        int hour = fechaInicial.get(Calendar.HOUR_OF_DAY);

        boolean horaPico = false;
        if (hour >= 6 && hour < 9 || hour >= 16 && hour < 20) {
            horaPico = true;
        }
        return false;
    }

    public int optenerTiempo(int meters) {
        double velocidad = 23000;
        if (esHoraPico()) {
            velocidad = 17000;
        }
        Double tiempo = meters * 60 / velocidad;

        rw("tiempo_" + tiempo + "_metros" + meters);
        return tiempo.intValue();
    }

    public static String[] hallarIncremento(double distanciaCalculada, double tiempoCalculado) {
        double minutoHoras = 60;
        double memtrosKm = 1000;
        double km = distanciaCalculada / memtrosKm;
        double h = tiempoCalculado / minutoHoras;
        Double velocidad = (km / h);

        double constante1 = 36;
        double constante2 = 18;

        double incremento1 = 0.83;
        double incremento2 = 2.48;
        double decremento = 0.825;
        double addIncre = 0.0;
        String ecuacion = "";
        String sTotal = "";
        if (velocidad >= 36) {
            addIncre = 0.0;
        } else if (velocidad >= 18 && velocidad < 36) {
            addIncre = ((constante1 - velocidad) / constante1) * incremento1;
            ecuacion = addIncre + " = ((" + constante1 + " - " + velocidad + ") / " + constante1 + ") * " + incremento1;

        } else {
            double tmp = (((constante1 - velocidad) / constante1) * incremento2);

            addIncre = tmp - decremento;
            String fix = "";
            if (addIncre < 0) {

                fix = "<fix:" + addIncre + " * -1>";
                addIncre = addIncre * -1;
                System.out.println("___" + addIncre);
            }
            ecuacion = addIncre + " = (((" + constante1 + " - " + velocidad + ") / " + constante1 + ") * " + incremento2
                    + ") - " + decremento + " " + fix;
        }
        if (addIncre < 0) {
            addIncre = 0.0;
        }
        rw("ecuacion:" + ecuacion);
        String[] data = new String[2];
        ecuacion = "{" + ecuacion + "}";
        data[0] = ecuacion;
        data[1] = "" + addIncre;
        return data;
    }

    public Integer calcularCostoFromAero(int metros, String myDir) {
        
        long VALORUNIDAD = 150;
        int RECARGO_UNIDADES = 180;
        long RECARGO_NOCFES = 40;
        long unidad;
        if (metros <= 4000) {
            unidad = 175;
        } else {
            unidad = (metros / 100) + RECARGO_UNIDADES;
        }
        //if (esFestivoNoc()) {
        if (false) {
            rw("esFestivoNoc");
            unidad += RECARGO_NOCFES;
        } else {
            rw("noFestivoNoc");
        }
        rw("DIR_" + myDir);
        long val1 = (unidad * VALORUNIDAD);
        Long pesos = val1 + 20000 - 8000 + 3000;
        String part[] = myDir.split("\\ ");

        String part0 = part[0].toUpperCase();

        if (!part0.contains("SUR") && part0.contains("CL") || part0.contains("CR")) {
            rw("DIR_" + part0 + "__No_" + part[1]);
            int j = 0;
            out:
            for (int i = 0; i < part.length; i++) {
                if (part[1].matches("[0-9]*")) {
                    Integer calle = new Integer(part[1]);

                    if (calle > 62 && j == 0) {
                        if (part[0].toUpperCase().contains("CR") && calle > 72) {
                            long axu = pesos + 9000;
                            rw("DIRECION VIP CARGO ADD $" + pesos + " + 9000 = " + (axu));
                            pesos = axu;
                        } else {
                            long axu = pesos + 8000;
                            rw("DIRECION VIP CARGO ADD $" + pesos + " + 8000 = " + (axu));
                            pesos = axu;
                        }
                        j++;
                        break out;
                    } else if (part0.contains("CR") && calle > 1 && calle < 30 && j == 0) {
                        long axu = pesos + 7000;
                        rw("DIRECION VIP CAR CARGO ADD $" + pesos + " + 7000 = " + (axu));
                        pesos = axu;
                        j++;
                        break out;
                    }
                }
            }
        } else {
            rw("DIR_SIN RECARGOS ADICIONALES");
        }
        pesos = pesos - 1000;

        return pesos.intValue();
    }
        
        
 

    public Integer calcularCostoFromPriceAndAero(int metros, String sitio) {
        Integer pesos = 0;

        int unidad = metros / 100;
        int valUni = 140;

        int constante = 25;
        int minima = 11000;//9000;
        if (unidad > constante) {
            int aux = unidad - constante;
            pesos = (aux * valUni) + minima;
        } else {
            pesos = minima;
        }
        rw("Metros: " + metros + " unidad: " + unidad + " constante: " + constante + " valorUnidad: " + valUni + " $: " + pesos);

        Long pesos2 = new Long(new Tarifas().redondeo("" + pesos).toString());
        rw("opcion2+formAero:" + pesos2+"idSitio__"+sitio);
        return pesos2.intValue();
    }
   

    public static String getJSONBuscarPlacaContainer(
            String placa) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "        \"placa\":\"" + placa + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"25\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static void writeToFile(String data, String archivo) throws IOException {
        String ruta = "/opt/compilados/";
        String fileName = archivo;

        String fullRuta = ruta + fileName;
        File f = new File(fullRuta);
        String str = "";
        String nuevalinea = System.getProperty("line.separator");
        if (!f.exists()) {
            OutputStream RutaArchivo = new FileOutputStream(fullRuta);
        }
        str += data;
        str += nuevalinea;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fullRuta, true));
        writer.write(str);
        writer.close();
    }

    public String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }

    public static Date addDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Configuramos la fecha que se recibe
        // calendar.add(Calendar.DAY_OF_YEAR, 2);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime();
    }

    public static String eliminarBarusar(String direccion) {
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

    public static int isOkJTF(JTextField value) {
        if (value.getText() != null && !value.getText().isEmpty()) {
            return new Integer(value.getText());
        } else {
            return 0;
        }
    }

    public static String fechaActual(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("es", "ES"));
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return date.toString();
        }
    }

    public static Date fechaLegible(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("es", "ES"));
        try {
            String tat = sdf.format(date);
            Date fecha = sdf.parse(tat);
            return fecha;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date toDateYYYYMMDDHHMM(String str_date) {
        Date date = null;
        try {
            System.out.println("Fecha: " + str_date);
            if (!(str_date != null && !str_date.isEmpty() && !str_date.equals("null"))) {
                str_date = fechaActual(new Date());
                System.out.println("Fecha2: " + str_date);
            }

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("es_ES"));
            date = formatter.parse(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }

        return date;
    }

    public static String toDateYYYYMMDD(String str_date) {
        String date = null;
        try {
            date = toDateYYMMDD(new Date());
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }

        return date;
    }

    public static Date toDateYYYYMMDD(Date str_date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("es_ES"));
        String fech = formatter.format(str_date);
        Date fecha = toDateYYMMDDHHMM(fech);
        return fecha;
    }

    public static String eliminarCaracteresEspeciales(String input) {

        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;

    }//remove1

    public static void rw(String message) {
        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        System.out.println(className + "." + methodName + "()[" + lineNumber + "] => " + message);
        //Logger.getLogger(Helper.class).info(className + "." + methodName + "()[" + lineNumber + "] => " + message);

    }

    public static String consecutivo(String idSrv, Integer idServicioAct, Integer lengFinal) {
        Integer idServicio = new Integer(idSrv);
        Integer nvValor = idServicio - idServicioAct + 1;
        String lengTrama = nvValor.toString();
        int itera = lengFinal - lengTrama.length();//1 
        String nvTrama = "";

        for (int i = 0; i < itera; i++) {
            nvTrama += "0";
        }
        return nvTrama + nvValor;
    }

    public static void main(String[] args) throws Exception {
        Date feccc = addDay();
        System.out.println("fffffff_" + feccc);

        //String sFecha = "2017-09-29 10:20 am";
        //Date fech = toDateYYMMDDHHMM(sFecha);
        // System.out.println("fech_"+fech);
//
//        File file = new File("c:\\scanner\\plantilla.pdf");
//        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        InputStream in = new FileInputStream(file);
//        PrintService printService1[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
//        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
//        PrintService service = ServiceUI.printDialog(null, 200, 200, printService1, defaultService, flavor, pras);
//
//        pras.add(new Copies(3));
//        pras.add(PrintQuality.HIGH);
//        pras.add(Sides.DUPLEX);
//        pras.add(new JobName("E2", null));
//        Doc doc1 = new SimpleDoc(in, flavor, null);
//
//        PrinterJob pj = PrinterJob.getPrinterJob();
    }

    public static Integer getNumberInt(String value) {
        if (value != null && !value.isEmpty() && value.matches("[0-9]*")) {
            return new Integer(value);
        } else {
            return new Integer("0");
        }
    }

    public static Long getNumberLong(String value) {
        if (value != null && !value.isEmpty() && value.matches("[0-9]*")) {
            return new Long(value);
        } else {
            return new Long("0");
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

    public static Long restartMinutos(Date fechaInicio, Date fechaFin) {
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a", new Locale("es_ES"));
        try {
            String s1 = smdf.format(fechaInicio);
            String s2 = smdf.format(fechaFin);

            Date d1 = smdf.parse(s1);
            Date d2 = smdf.parse(s2);

            long tiempoInicial = d1.getTime();
            long tiempoFinal = d2.getTime();
            long resta = tiempoFinal - tiempoInicial;
            resta = resta / (1000 * 60);
            return resta;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static String toDateYYMMDD(Date str_date) {
        String date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("es_ES"));
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date = formatter.format(str_date);
        } catch (Exception e) {
            e.printStackTrace();
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

            return new Date();

        }
        return date;
    }

    public static Date toDateYYMMDDHHMMJavaScript(String str_date) {
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
            date = formatter.parse(str_date);
        } catch (Exception e) {

            return new Date();

        }
        return date;
    }

    public static Date toDateYYMMDDHHMMTest(String str_date) {
        Date date = null;
        try {
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.parse(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return date;
    }

    public static String getString(String value) {
        if (value != null && !value.isEmpty() && !value.equals("null")) {
            return value.toUpperCase();
        } else {
            return "";
        }
    }

    public Image getImage(String url, PdfWriter writer) {

        Image img = null;
        System.out.println("img::: " + url + " r: " + writer);
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
            java.awt.Image image = imageIcon.getImage();
            img = Image.getInstance(writer, image, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return img;
    }

    public static String getJSONSelectLogin() {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"5\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONHacerCierre(String idUsuario, boolean sino) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"idUsuario\":\"" + idUsuario + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"cierre\":\"" + sino + "\",\n"
                + "	   \"keyCommand\":\"10\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getGeoRuteo(String direccion, String sitio) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"direccion\":\"" + direccion + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"sitio\":\"" + sitio + "\",\n"
                + "	   \"keyCommand\":\"14\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String probarConexion(String idUsuario, String usuario, String sitio) {
        return "[\n"
                + "    {\n"
                + "	  \"ip\":\"" + getIpPc() + "\",\n"
                + "	  \"idUsuario\":\"" + idUsuario + "\",\n"
                + "	  \"usuario\":\"" + usuario + "\",\n"
                + "	  \"idSitio\":\"" + sitio + "\",\n"
                + "	  \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	  \"keyCommand\":\"16\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getLoadPaiMent() {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"15\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONHacerAnulacion(Long numeroFactura, String xp, String sitio, String comprob) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"numeroFactura\":\"" + numeroFactura + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	  \"comprob\":\"" + (comprob) + "\",\n"
                + "	   \"porque\":\"" + xp + "\",\n"
                + "        \"sitio\":\"" + sitio + "\",\n"
                + "	   \"keyCommand\":\"13\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONReimpresion(Long numeroFactura, String sitio, String comprob) {
        return "[\n"
                + "    {\n"
                + "       \"empresaId\":\"12\",\n"
                + "	  \"numeroFactura\":\"" + numeroFactura + "\",\n"
                + "	  \"ip\":\"" + getIpPc() + "\",\n"
                + "	  \"comprob\":\"" + (comprob) + "\",\n"
                + "	  \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "       \"sitio\":\"" + sitio + "\",\n"
                + "	  \"keyCommand\":\"11\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONReimpresionUltimaFactura(Integer idUsuario, String comprob) {
        return "[\n"
                + "    {\n"
                + "      \"empresaId\":\"12\",\n"
                + "	 \"idUsuario\":\"" + idUsuario + "\",\n"
                + "	 \"ip\":\"" + getIpPc() + "\",\n"
                + "	  \"comprob\":\"" + (comprob) + "\",\n"
                + "	 \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	 \"keyCommand\":\"12\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONCambiarClave(String usuario, String clave, String idUsuario) {
        return "[\n"
                + "    {\n"
                + "      \"empresaId\":\"12\",\n"
                + "	 \"usuario\":\"" + usuario + "\",\n"
                + "	 \"ip\":\"" + getIpPc() + "\",\n"
                + "	 \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "      \"clave\":\"" + clave + "\",\n"
                + "      \"idUsuario\":\"" + idUsuario + "\",\n"
                + "	 \"keyCommand\":\"9\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONSitios(String idSitio, String nombre) {
        return "[\n"
                + "    {\n"
                + "      \"empresaId\":\"12\",\n"
                + "	 \"ip\":\"" + getIpPc() + "\",\n"
                + "     \"idSitio\":\"" + idSitio + "\",\n"
                + "     \"nombreSitio\":\"" + nombre + "\",\n"
                + "	 \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	 \"keyCommand\":\"7\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONLogin(String usuario, String clave, String idSitio, String comprob) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	\"usuario\":\"" + usuario + "\",\n"
                + "	\"ip\":\"" + getIpPc() + "\",\n"
                + "	\"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "     \"clave\":\"" + clave + "\",\n"
                + "     \"idSitio\":\"" + idSitio + "\",\n"
                + "     \"comprob\":\"" + comprob + "\",\n"
                + "	\"keyCommand\":\"6\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONConsultModipay(String idUsuario, String usuario, String transaccion, String idSitio) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	\"usuario\":\"" + usuario + "\",\n"
                + "	\"idUsuario\":\"" + idUsuario + "\",\n"
                + "	\"ip\":\"" + getIpPc() + "\",\n"
                + "	\"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "     \"transaccion\":\"" + transaccion + "\",\n"
                + "     \"idSitio\":\"" + idSitio + "\",\n"
                + "	\"keyCommand\":\"23\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String registroFactura(Ticket tiquete) {
        tiquete.setKeyCommand("26");
        String json = new Gson().toJson(tiquete);
        return json;
    }

    public static String nuevoCrearSrv(Ticket factura) {
        JsonDTO myJson = new JsonDTO();
        myJson.setFecha((Helper.toDateYYMMDDHHMM(new Date())));
        factura.setFechaGrab((Helper.toDateYYMMDDHHMM(new Date())));
        factura.setFecha((Helper.toDateYYMMDDHHMM(new Date())));
        factura.setFechaFin(Helper.fechaActual(addDay()));
        myJson.setTiquete(factura);
        myJson.setKeyCommand("8a");
        return new Gson().toJson(myJson);
    }

    public static String getJSONCrearSrv(
            String PLACA,
            String NIT,
            String GRABADOR,
            String IDUSUARIO,
            String VALOR,
            String FECHA,
            String NOMBRE,
            String ADDRESS,
            String USUARIOS,
            String TIPO_PAGO,
            String COMPROBANTE,
            String CIERRE,
            String valorD,
            String valorE,
            String valorC,
            String sitio,
            String indProc,
            String transaccion,
            String retiroParcial,
            String numeroInterno
    ) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"placa\":\"" + PLACA + "\",\n"
                + "        \"nit\":\"" + NIT + "\",\n"
                + "        \"grabador\":\"" + GRABADOR + "\",\n"
                + "	   \"idUsuario\":\"" + IDUSUARIO + "\",\n"
                + "        \"valor\":\"" + VALOR + "\",\n"
                + "        \"fecha\":\"" + FECHA + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "        \"nombre\":\"" + NOMBRE + "\",\n"
                + "        \"contrato\":\"" + numeroInterno + "\",\n"
                + "        \"addres\":\"" + ADDRESS + "\",\n"
                + "        \"tipoPago\":\"" + TIPO_PAGO + "\",\n"
                + "        \"comprob\":\"" + COMPROBANTE + "\",\n"
                + "        \"cierre\":\"" + CIERRE + "\",\n"
                + "        \"direccion\":\"" + ADDRESS + "\",\n"
                + "        \"valorD\":\"" + valorD + "\",\n"
                + "        \"valorE\":\"" + valorE + "\",\n"
                + "        \"transaccion\":\"" + transaccion + "\",\n"
                + "        \"retiroParcial\":\"" + retiroParcial + "\",\n"
                + "        \"valorC\":\"" + valorC + "\",\n"
                + "        \"indProc\":\"" + indProc + "\",\n"
                + "        \"sitio\":\"" + sitio + "\",\n"
                + "	   \"keyCommand\":\"8\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONSendSrv(
            String ID,
            String ADDRESS,
            String FECHA,
            String USUARIO_ID,
            String VALOR,
            String AUTO_PLACA,
            String TIPO_PAGO,
            String ASIGNACIONES_ID,
            String NIT,
            String SITIOS_ID,
            String GRABADOR,
            String COMPROB,
            String FECHA_GRAB,
            String IND_PROC,
            String NUMERO,
            String ESTADO_ID,
            String CIERRE,
            String VALORE,
            String VALORC,
            String VALORD,
            String PORQUE) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "	   \"id\":\"" + ID + "\",\n"
                + "        \"addres\":\"" + ADDRESS + "\",\n"
                + "        \"fecha\":\"" + FECHA + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"usuario\":\"" + USUARIO_ID + "\",\n"
                + "        \"valor\":\"" + VALOR + "\",\n"
                + "        \"placa\":\"" + AUTO_PLACA + "\",\n"
                + "        \"tipoPago\":\"" + TIPO_PAGO + "\",\n"
                + "        \"asignacionesId\":\"" + ASIGNACIONES_ID + "\",\n"
                + "        \"nit\":\"" + NIT + "\",\n"
                + "        \"idSitio\":\"" + SITIOS_ID + "\",\n"
                + "        \"grabador\":\"" + GRABADOR + "\",\n"
                + "        \"comprob\":\"" + COMPROB + "\",\n"
                + "        \"fechaGrab\":\"" + FECHA_GRAB + "\",\n"
                + "        \"indProc\":\"" + IND_PROC + "\",\n"
                + "        \"numero\":\"" + NUMERO + "\",\n"
                + "        \"estadoId\":\"" + ESTADO_ID + "\",\n"
                + "        \"porque\":\"" + PORQUE + "\",\n"
                + "	   \"keyCommand\":\"18\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONConsultaSrv(
            String COMPROB) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "        \"comprob\":\"" + COMPROB + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "	   \"keyCommand\":\"19\"\n"
                + "    }\n"
                + "]\n"
                + "";
    }

    public static String getJSONBuscarPlaca(
            String placa) {
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

    public static String getJSONBuscarContainerPlaca(
            String placa) {
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

    public static String getJSONActializarSrv(
            String COMPROB, String NUMERO) {
        return "[\n"
                + "    {\n"
                + "        \"empresaId\":\"12\",\n"
                + "        \"comprob\":\"" + COMPROB + "\",\n"
                + "	   \"ip\":\"" + getIpPc() + "\",\n"
                + "	   \"fecha\":\"" + getColCurrentTime() + "\",\n"
                + "        \"numero\":\"" + NUMERO + "\",\n"
                + "	  \"keyCommand\":\"20\"\n"
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

    public static boolean isOK(String jsonIn) {
        if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "" && !jsonIn.equals("no") && !jsonIn.equals("null")) {
            return true;
        } else {
            return false;
        }
    }
}
