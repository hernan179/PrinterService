/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import com.aero.aplication.docs.CrearDocumento;
import com.aero.aplication.dto.JsonDTO;
import com.aero.aplication.dto.Ticket;
import com.google.gson.Gson;
import com.itextpdf.text.Image;
import javax.swing.ImageIcon;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return date.toString();
        }
    }

    public static Date fechaLegible(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.parse(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }

        return date;
    }

    public static String getNumeroLargo(Ticket tiquete){
       String YEAR = fechaActual(new Date()).split("\\-")[0];
        
        return "425497301" + YEAR + tiquete.getNumeroInterno() + tiquete.getId();
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
        String ddd = fechaActual(new Date());
           System.out.println("fech_"+ddd);
        
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date = formatter.format(str_date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;

        }
        return date;
    }
 public static Long restartMinutos(Date fechaInicio, Date fechaFin) {
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.parse(str_date);
        } catch (Exception e) {

            return new Date();

        }
        return date;
    }
        public static Date toDateYYMMDDHHMMJavaScript(String str_date) {
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
