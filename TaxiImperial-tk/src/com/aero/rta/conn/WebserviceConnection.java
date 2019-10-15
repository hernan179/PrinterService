package com.aero.rta.conn;

import static com.aero.aplication.main.InitialContextUtil.getCurrentWorkingDirectory;
import static com.aero.aplication.tools.Helper.rw;
import com.aero.aplication.tools.UrlConnectionTimeOut;
import com.aero.aplication.tools.Utilidades;
import com.aero.aplication.tools.isProduccion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.ksoap2.HeaderProperty;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebserviceConnection {

    private static String NAMESPACE;
    private static String METHODUNIC;
    private SoapSerializationEnvelope envelope = null;
    //Production
    private static String URL;
    String host;
    Integer port;

    public WebserviceConnection(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getStatusAccount(String json2) {
        String json = Utilidades.Encriptar(json2);//hsa

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        // URL = "http://localhost:8080/PrinterService/EndPoint?wsdl";
        URL = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";
        rw("url: " + URL + " port:  " + port);
        try {
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("arg0", json);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            //transporte.call(NAMESPACE + METHODUNIC, envelope);
            System.out.println(" sin ws");
             transporte.call("", envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result.getProperty(0).toString();
        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }

        return response;
    }

    public String getStatusAccountRemote200_91_204_38(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        //URL = "http://localhost:8380/PrinterService/EndPoint?wsdl";

        //URL = "http://" + "200.91.204.38" + ":" + 9999 + "/PrinterService/EndPoint?wsdl";// producion publica
        // URL = "http://srvapp-aero.losunos.net:" + 8380 + "/PrinterService/EndPoint?wsdl";// pruebas internas
        //URL = "http://192.168.20.53:" + 8280 + "/PrinterService/EndPoint?wsdl";// produccion interna
        URL = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";
        System.out.println("url: " + URL);
        try {
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("arg0", json);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            transporte.call(NAMESPACE + METHODUNIC, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result.getProperty(0).toString();

        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }

        return response;
    }

    public String getStatusAccountRemote192_168_20_53_8380(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";

        URL = "http://srvapp-aero.losunos.net:" + 8380 + "/PrinterService/EndPoint?wsdl";

        try {
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("arg0", json);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            transporte.call(NAMESPACE + METHODUNIC, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result.getProperty(0).toString();

        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }

        return response;
    }

    public static boolean loadIp(String ips) {
        //URL server = new URL("http://200.91.204.38:9999/NumeralCienv1-ejb/IVRNumeralCienWS?wsdl");

        try {
            URL server = new URL(ips);
            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 2000);
            HttpConnectionParams.setSoTimeout(params, 2000);
            HttpClient httpclient = new DefaultHttpClient(params);
            HttpGet httpRequest = new HttpGet(server.toString());
            HttpResponse response = httpclient.execute(httpRequest);
            int code = response.getStatusLine().getStatusCode();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String ipRemoteHost(String url) {
        String userDir = getCurrentWorkingDirectory();

        try {
            int i = 0;
            String urlSend = "NO";

            String hostFile = userDir + "poolRTA9999.txt";
            FileReader fr = new FileReader(new File(hostFile));

            if (fr != null) {
                BufferedReader br = new BufferedReader(fr);
                String info = "";
                boolean isOnline = true;
                while ((info = br.readLine()) != null && isOnline) {
                    if (loadIp(info)) {
                        urlSend = info;
                        isOnline = false;
                        rw("URL LOAD BALANCING...." + "indice[" + i + "] " + urlSend);
                        return urlSend;
                    }
                    i++;
                }
            }
            return urlSend;
        } catch (Exception e) {
            return "NO";
        }

    }
    
     public String getStatusAccountZonificar(String json2) {
        String json = Utilidades.Encriptar(json2);//hsa

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        // URL = "http://localhost:8080/PrinterService/EndPoint?wsdl";
        URL = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";
        rw("url: " + URL + " port:  " + port);
        try {
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("arg0", json);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            //transporte.call(NAMESPACE + METHODUNIC, envelope);
            System.out.println(" sin ws");
             transporte.call("", envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result.getProperty(0).toString();
        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }

        return response;
    }

    public String getStatusAccountZonificarx(String json2) {
        String json = Utilidades.Encriptar(json2);
        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        String URL2 = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";

        //URL = ipRemoteHost(URL2);
        rw("URL BALANCING... CLIENT " + URL);

        if (URL != null && !URL.isEmpty() && !URL.equals("NO")) {
            try {
                SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
                request.addProperty("arg0", json);
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envelope.dotNet = false;
                envelope.setOutputSoapObject(request);
                System.out.println("wait resonse...");
                HttpTransportSE transporte = new HttpTransportSE(URL, 3000);
                transporte.call(NAMESPACE + METHODUNIC, envelope);

                SoapObject result = (SoapObject) envelope.bodyIn;
                response = result.getProperty(0).toString();
            } catch (Exception e) {
                System.out.println("wait exception...");
                String error = e.getMessage();
                error += "";
                System.out.println(error);

            }
        } else {
            return "no";
        }
        return response;
    }

    public String pruebaMDPY(String param1, String param2) {
        String response = "";
        NAMESPACE = "http://members.webservices.cyclos.strohalm.nl/";
        METHODUNIC = "loadByPrincipal";
        try {
            URL = "http://199.180.186.183:8081/macquirer/webresources/pagos";

            ArrayList headerProperty = new ArrayList();
            headerProperty.add(new HeaderProperty("principalType", "USER"));
            headerProperty.add(new HeaderProperty("principal", "543571"));

            HttpTransportSE transporte = new HttpTransportSE(URL);
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;

            System.out.println("NAMESPACE + METHODUNIC " + NAMESPACE + METHODUNIC);
            try {
                transporte.call(NAMESPACE + METHODUNIC, envelope, headerProperty);

            } catch (IOException e) { //<-  IOException: HTTP request failed, HTTP status: 500 Here, when the response is a soap fault
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            SoapObject result = (SoapObject) envelope.bodyIn;

            System.out.println("value:   " + result);

            int totalCount = result.getPropertyCount();

            for (int detailCount = 0; detailCount < totalCount; detailCount++) {
                SoapObject pojoSoap = (SoapObject) result.getProperty(detailCount);
                System.out.println("rta: " + pojoSoap);

//                jsonDTO.setPrecio((pojoSoap.getProperty("precio").toString()));
//                jsonDTO.setMetros((pojoSoap.getProperty("metros").toString()));
//                jsonDTO.setTiempo((pojoSoap.getProperty("tiempo").toString()));
//                jsonDTO.setDireccionNormalizada((pojoSoap.getProperty("direccionNormalizada").toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            String error = e.getMessage();
            error += "";

        }
        return null;

    }

    public static void main(String[] args) {
        new WebserviceConnection(URL, Integer.SIZE).pruebaMDPY(URL, URL);
    }
}
