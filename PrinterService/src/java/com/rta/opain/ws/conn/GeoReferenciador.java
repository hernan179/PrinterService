/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.ws.conn;

import com.google.gson.JsonObject;
import com.rta.opain.delegate.dto.JsonDTO;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.node.Constantes;
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
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/**
 *
 * @author hsancheza
 */
public class GeoReferenciador {

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

    public static String zonificador(String direccion, String ciudad) throws Exception {

        URL url = new URL(Constantes.HOST_GEO);
        rw("HOST>" + url.getHost());
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

        System.out.println("sending...");
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
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

    public static void main(String[] args)throws  Exception{
        String latitud = "4.6971956";
                String longitud = "-74.1405186";
     JsonDTO dd =   zonificadorAndGeoRuteo("av americas 50 15", "11001", latitud, longitud);
        System.out.println(" metros   "+dd.getMetros());
    }

    public static JsonDTO zonificadorAndGeoRuteo(String direccion, String ciudad, String latitud, String longitud) throws Exception {
        String rta = zonificador(direccion, ciudad);
        System.out.println("Dir))))))))))))))))))))"+rta);
        JSONObject json = new JSONObject(rta);

        String latitud2 = json.getString("latitud");
        String longitud2 = json.getString("longitud");
        direccion = json.getString("direccion");
        

        String rtaGeoRuteo = geoRuteo(latitud, longitud, latitud2, longitud2);

        JSONObject jsonRuteo = new JSONObject(rtaGeoRuteo);

        String metros = jsonRuteo.getString("metros");
        String minutos = jsonRuteo.getString("minutos");

        JsonDTO geoRuteoDTO = new JsonDTO();
        geoRuteoDTO.setMetros(metros);
        geoRuteoDTO.setDireccion(direccion);

        rw("metros: " + metros + ", minutos:" + minutos);
        return geoRuteoDTO;
    }

    public static String geoRuteo(String la1, String lo1, String la2, String lo2) throws Exception {
        String urlData = Constantes.HOST_GEO + "?geo=si&";
        URL url = new URL(urlData);

        rw("HOST>" + url.getHost());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("la1", la1));
        params.add(new BasicNameValuePair("lo1", lo1));
        params.add(new BasicNameValuePair("la2", la2));
        params.add(new BasicNameValuePair("lo2", lo2));

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
        rw("geoRuteo\n" + respuesta);
        return respuesta;
    }
}
