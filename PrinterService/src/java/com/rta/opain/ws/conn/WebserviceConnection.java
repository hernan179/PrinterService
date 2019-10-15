package com.rta.opain.ws.conn;

import com.rta.opain.delegate.dto.JsonDTO;
import java.net.InetAddress;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.delegate.tools.Utilidades;
import com.rta.opain.delegate.tools.isProduccion;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class WebserviceConnection {

    private static String NAMESPACE;
    private static String METHODUNIC;
    private SoapSerializationEnvelope envelope = null;
    //Production
    private static String URL = isProduccion.ipSrv;
    String host;
    Integer port;

    public WebserviceConnection() {
    }

    public JsonDTO radioLocalizadorAero(String direccion) {
        JsonDTO jsonDTO = new JsonDTO();
        String response = "";
        NAMESPACE = "http://radiolocalizador.aeropuerto.com/";
        METHODUNIC = "solicitudServicioAeropuerto";
        try {
            //URL = "http://srvapp-aero.losunos.net:8080/Aeropuerto/WebServiceRadiolocalizadorAero?wsdl";

            URL = "http://" + new Utilidades().isProduccionS() + "/Aeropuerto/WebServiceRadiolocalizadorAero?wsdl";

            rw("ws url" + URL);
            URL = isProduccion.ipSrv + ":8080/ESBService-ejb/HttpdService?wsdl";
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("direccion", direccion);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            transporte.call(NAMESPACE + METHODUNIC, envelope);

            SoapObject result = (SoapObject) envelope.bodyIn;

            int totalCount = result.getPropertyCount();

            for (int detailCount = 0; detailCount < totalCount; detailCount++) {
                SoapObject pojoSoap = (SoapObject) result.getProperty(detailCount);
                jsonDTO.setPrecio((pojoSoap.getProperty("precio").toString()));
                jsonDTO.setMetros((pojoSoap.getProperty("metros").toString()));
                jsonDTO.setTiempo((pojoSoap.getProperty("tiempo").toString()));
                jsonDTO.setDireccionNormalizada((pojoSoap.getProperty("direccionNormalizada").toString()));

            }

        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            e.printStackTrace();

        }
        return jsonDTO;

    }

    public String getStatusAccountRemote200_91_204_38(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        //URL = "http://" + "200.91.204.38" + ":" + 9999 + "/PrinterService/EndPoint?wsdl";
        URL = isProduccion.ipSrv + ":8280/ESBService-ejb/HttpdService?wsdl";
        rw(URL);
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
    
    
        public String cargarPlacaContainer185_80_129_150(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        URL = "http://213.136.84.4:8380/PrinterService/EndPoint?wsdl";
        //URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";

        // URL = ipRemoteHostVPS_GEO();
        try {
            rw("CONCILIANDO_VPS_" + URL);
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
        
         public String getStatusAccountRemote93_188_167_147X(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        URL ="http://213.136.84.4:8280/PrinterService/EndPoint?wsdl";
        //URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";

        // URL = ipRemoteHostVPS_GEO();
        try {
            rw("CONCILIANDO_VPS_" + URL);
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

    public String getStatusAccountRemote93_188_167_147(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        URL = isProduccion.ipSrv + ":8280/PrinterService/EndPoint?wsdl";
        //URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";

        // URL = ipRemoteHostVPS_GEO();
        try {
            rw("CONCILIANDO_VPS_" + URL);
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

    public static String accesPpintCopiaRespaldoOld(String json) throws Exception {
        String URL = ipRemoteHostVPS_DRP_TEST_PLAN();
        Long start = System.currentTimeMillis();

        rw("DRP_COPY_TO_" + URL);
        String NAMESPACE = "http://node.opain.rta.com/";
        String METHODUNIC = "crearCopiaRespaldo";
        SoapObject request = new SoapObject(NAMESPACE, METHODUNIC);
        request.addProperty("arg0", json);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        String host = InetAddress.getLocalHost().getHostName();
        transporte.call("", envelope);
        SoapObject result = (SoapObject) envelope.bodyIn;
        Long diff = System.currentTimeMillis() - start;
        rw("DURATION_" + diff + "_Msg_RESULT_" + (URL != null && !URL.equals("NO") ? "EXITO" : "ERROR"));
        return result.getProperty(0).toString();

    }

    public String getStatusAccountRemote93_188_165_75(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        //URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";
        URL = isProduccion.ipSrv + ":8380/PrinterService/EndPoint?wsdl";
        // URL = ipRemoteHostVPS_GEO();
        try {
            rw("CONCILIANDO_VPS_" + URL);
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
    
    
    public String getStatusAccountRemote185_80_129_150(String json2) {
        String json = Utilidades.Encriptar(json2);

        String response = "no";
        NAMESPACE = "http://node.opain.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "gateWayConnection";
        //URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";
        URL = "http://213.136.84.4" + ":8380/PrinterService/EndPoint?wsdl";
        // URL = ipRemoteHostVPS_GEO();
        try {
            rw("CONCILIANDO_VPS_" + URL);
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

    public static String ipRemoteHostVPS_DRP_TEST_PLAN() {
        try {
            int i = 0;
            String urlSend = "";
            String hostFile = "/opt/compilados/ipHostDrpTestPlan.txt";
            FileReader fr = new FileReader(new File(hostFile));
            if (fr != null) {
                BufferedReader br = new BufferedReader(fr);
                String info = "";
                while ((info = br.readLine()) != null) {
                    boolean isOk = loadIp(info);
                    if (isOk) {
                        urlSend = info;
                        rw("URL_LOAD_" + urlSend);
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

    public String ipRemoteHostVPS_GEO() {
        try {
            int i = 0;
            long diff = System.currentTimeMillis();
            String urlSend = "";
            String hostFile = "/opt/compilados/pool_VPS_GEO_9999.txt";
            FileReader fr = new FileReader(new File(hostFile));
            if (fr != null) {
                BufferedReader br = new BufferedReader(fr);
                String info = "";
                while ((info = br.readLine()) != null) {
                    boolean isOk = loadIp(info);
                    if (isOk) {
                        urlSend = info;
                        diff = System.currentTimeMillis() - diff;
                        rw("DURATION_TIME_" + diff + "_BALANCER_" + urlSend);
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

    public String getRquestVPS(String json) {
        String response = "no";
        // NAMESPACE = "http://endpoint.dto.esb.rta.com/";
        NAMESPACE = "http://endpoint.dto.esb.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "processRequest";
        URL = isProduccion.ipSrv + ":8280/PrinterService/EndPoint?wsdl";
        // URL = "http://192.168.20.53:8280/PrinterService/EndPoint?wsdl";
        //URL = "http://200.91.204.38:8999/ESBService-ejb/HttpdService?wsdl";// ip aero publica

        //URL = ipRemoteHostVPS_GEO();
        rw("URL_LOAD_: " + URL);
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
            rw("Erro:" + error);
        }
        return response;
    }

    public String getRquestAeroPrice(String json) {
        String response = "no";
        // NAMESPACE = "http://endpoint.dto.esb.rta.com/";
        NAMESPACE = "http://endpoint.dto.esb.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "processRequest";
        //URL = isProduccion.ipSrv + ":8280/ESBService-ejb/HttpdService?wsdl";

        //URL = ipRemoteHostVPS_GEO();
        URL = "http://"+isProduccion.ipSrv + ":8380/ESBService-ejb/HttpdService?wsdl";

        rw("URL_LOAD_: " + URL);
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
            rw("Erro:" + error);
        }
        return response;
    }
    
    
    public String getStatusAccountWildFly15(String json) {
        //String json = Utilidades.Encriptar(json2);//hsa

        String response = "no";
        NAMESPACE = "http://endpoint.dto.esb.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "processRequest";
        
        URL = "http://"+isProduccion.ipSrv + ":8380/ESBService-ejb/HttpdService?wsdl";
       // URL = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";
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

    public String getRquestAeroAERO(String json) {
        String response = "no";
        // NAMESPACE = "http://endpoint.dto.esb.rta.com/";
        NAMESPACE = "http://endpoint.dto.esb.rta.com/";//http://node.opain.rta.com/
        METHODUNIC = "processRequest";

        //URL = isProduccion.ipSrv + ":8280/ESBService-ejb/HttpdService?wsdl";
        URL = ipRemoteHostVPS_GEO();
        rw("URL_LOAD_AERO: " + URL);
        try {
            SoapObject request = new SoapObject(NAMESPACE, WebserviceConnection.METHODUNIC);
            request.addProperty("arg0", json);
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL, 6000);
            transporte.call(NAMESPACE + METHODUNIC, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result.getProperty(0).toString();
        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            rw("Erro:" + error);
        }
        return response;
    }

}
