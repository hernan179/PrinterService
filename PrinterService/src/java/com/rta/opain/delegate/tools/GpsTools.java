/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import static com.rta.opain.delegate.tools.LogTest.rw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GpsTools {

    public static boolean laloIntoPolygon(double la, double lo) {
        try {
            final GeometryFactory gf = new GeometryFactory();
            final ArrayList<Coordinate> points = new ArrayList<Coordinate>();
            List<double[]> coordenadasAero = coordenadasAero();
            for (double d[] : coordenadasAero) {
                points.add(new Coordinate(d[0], d[1]));
            }
            final Polygon polygon = gf.createPolygon(new LinearRing(new CoordinateArraySequence(points.toArray(new Coordinate[points.size()])), gf), null);
            final Coordinate coord = new Coordinate(la, lo);
            final Point point = gf.createPoint(coord);
            return (point.within(polygon));
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static List<double[]> coordenadasAero() {
        String puntos = "4.68618,-74.12252|4.69696,-74.13222|4.70928,-74.14544|4.69764,-74.15539|4.69029,-74.14595|4.67985,-74.13282|4.68618,-74.12252";
        //String puntos = "3.5744156301361207,-76.49162292480469|3.4603045339626335,-76.58483505249023|3.3763401216086986,-76.59358978271484|3.3490929577861226,-76.5582275390625|3.332555786944134,-76.52767181396484|3.337953961416485,-76.50089263916016|3.411297626483123,-76.4487075805664|3.487034508433257,-76.44115447998047|3.544090173655612,-76.45231246948242|3.5744156301361207,-76.49162292480469";
        String punto[] = puntos.split("\\|");
        List<double[]> lstPuntos = new ArrayList<double[]>();
        for (int i = 0; i < punto.length; i++) {
            String lalo[] = punto[i].split("\\,");
            double[] d = new double[2];
            d[0] = new Double(lalo[0]);
            d[1] = new Double(lalo[1]);
            lstPuntos.add(d);
        }
        return lstPuntos;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static Integer[] durationRoadAndDisntance(Double lat, Double lng, Double lat2, Double lng2) {
        try {

            String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=" + lat + "," + lng + "&destinations=" + lat2 + "," + lng2 + "&mode=driving&language=en-EN&sensor=false";

            rw("url_" + url);
            String myUrl = readUrl(url);
            JSONObject json = new JSONObject(myUrl);

            String status = json.getString("status");
            rw("status_" + status);
            if (status != null && !status.isEmpty() && status.equals("OK")) {

                JSONArray array = json.getJSONArray("rows");
                JSONObject json2 = new JSONObject(array.getString(0));

                JSONArray array2 = json2.getJSONArray("elements");
                JSONObject json3 = new JSONObject(array2.getString(0));

                JSONObject distance = new JSONObject(json3.getString("distance"));
                String distancia = distance.getString("value");

                JSONObject duration = new JSONObject(json3.getString("duration"));

                String duracion = duration.getString("value");

                Integer iDistancia = new Integer(distancia.trim());
                Integer iDuraccon = new Integer(duracion.trim()) / 60;

                Integer[] data = new Integer[]{iDuraccon, iDistancia};

                String rw = ("El vehiculo tarda en llegar " + data[0] + " Minutos, distancia " + data[1]);

                rw("rw: " + rw);
                return data;
            } else {

                Integer[] data = new Integer[]{0, 0};
            }

        } catch (Exception e) {
            //d = calculationByDistance(lat, lng, lat2, lng2);
            e.printStackTrace();
        }
        return new Integer[]{new Integer("0"), new Integer("0")};
    }

    public static void ciudadByLaLo(Double lat, Double lng) {
        try {
            String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
                    + lat + "," + lng + "&sensor=true";

            String myUrl = readUrl(url);
            //System.out.println("myUrl_"+myUrl);
            JSONObject json = new JSONObject(myUrl);
            JSONArray results = json.getJSONArray("results");
            JSONObject argCiudad = new JSONObject(results.getString(0));
            JSONArray nameCiduad = argCiudad.getJSONArray("address_components");
            JSONObject CIUDAD = new JSONObject(nameCiduad.getString(4));

            JSONObject argBarrio = new JSONObject(results.getString(1));
            JSONArray barrio = argBarrio.getJSONArray("address_components");

            JSONObject BARRIO = new JSONObject(barrio.getString(0));
            String barrioCity = eliminarCaracteresEspeciales(BARRIO.getString("long_name")) + "," + eliminarCaracteresEspeciales(CIUDAD.getString("long_name"));
            System.out.println(barrioCity);

        } catch (Exception e) {
            //d = calculationByDistance(lat, lng, lat2, lng2);
            e.printStackTrace();
        }
        //  return  new Integer[]{new Integer("5"),new Integer("1000")};
    }

    public static String eliminarCaracteresEspeciales(String input) {
        String original = "áàäéèëíìïóòöüúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;

    }

    public static void main(String[] args) {

        ciudadByLaLo(6.327412726349185, 6.327412726349185);
        /*ciudadByLaLo(6.331196248364003, -75.55807068943977);
        ciudadByLaLo(6.24456589, -75.5697534);
        ciudadByLaLo(6.16915042, -75.61329198);
        ciudadByLaLo(6.16539616183573, -75.59121680242242);
        ciudadByLaLo(6.183850, -75.584793);
        ciudadByLaLo(6.23552499, -75.54504841);
        ciudadByLaLo(4.62438284,-74.19310781);*/

    }
}
