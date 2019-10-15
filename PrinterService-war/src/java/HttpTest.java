
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsancheza
 */
public class HttpTest {

    public static void main(String[] args) throws Exception {
        String data = "jsonResp={  \n"
                + "   \"estado\":\"true\",\n"
                + "   \"argumento\":\"Pago realizado a través de Baloto\",\n"
                + "   \"codigoProducto\":\"Aplicacion\",\n"
                + "   \"idpagoModipay\":\"M-0000000000037573-Co\",\n"
                + "   \"canalPago\":\"Baloto\",\n"
                + "   \"descripcion\":\"Pago Aplicacion Conductor, Periodo Julio 2018:1:4097-APP:0:TAXIS LIBRES BOGOTA:8002339688:8002339688:80139829:11001:57\",\n"
                + "   \"fechaPago\":\"28/06/2018 02:44:44 PM\",\n"
                + "   \"valorCompra\":\"55000\",\n"
                + "   \"idPagador\":\"80139829\",\n"
                + "   \"mailPagador\":\"contador1@cotech.com.co\",\n"
                + "   \"nombrePagador\":\"null\",\n"
                + "   \"direcciondeenvio\":\"null\",\n"
                + "   \"telefonodeenvio\":\"null\",\n"
                + "   \"nombrecomprador\":\"null\",\n"
                + "   \"pais\":\"null\",\n"
                + "   \"ciudad\":\"null\",\n"
                + "   \"codigopostaldeenvio\":\"null\",\n"
                + "   \"referencia1\":\"null\",\n"
                + "   \"referencia2\":\"null\",\n"
                + "   \"referencia3\":\"null\",\n"
                + "\"referenciaExterna\":\"ref123\",\n"
                + "\"generadorPago\":\"Carlos J\"\n"
                + "}";
        
        System.out.println("data"+data.replaceAll("[\n\r]", "").replaceAll(" +", ""));
        sendaData(data,"hola");
      
    }
    
    static String urlConstants = "http://192.168.20.37:8380/PrinterService-war/TestLinkPago";   
    private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
       public static String sendaData(String direccion, String ciudad) throws Exception {
        String urlData = urlConstants + "?";
        URL url = new URL(urlData);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("addres", direccion));
        params.add(new BasicNameValuePair("codCiudad", ciudad));
        params.add(new BasicNameValuePair("tolerancia", "4"));

   
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(getQuery(params));
        writer.flush();
        writer.close();
        os.close();
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        String respuesta = "";
        while ((inputLine = in.readLine()) != null) {
            if (inputLine != null) {
                respuesta += inputLine;
            }
        }

        in.close();
        System.out.println("rtaZinificador\n" + respuesta);
        return respuesta;
    }


}
