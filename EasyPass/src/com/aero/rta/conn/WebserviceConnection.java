package com.aero.rta.conn;

import com.aero.easypass.tools.Md5HastTools;
import com.aero.easypass.tools.Utilitario;
import static com.aero.easypass.tools.Utilitario.getInt;
import com.aero.rta.dto.JsonDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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
        String json = Md5HastTools.Encriptar(json2);

        String response = "";
        NAMESPACE = "http://node.opain.rta.com/";
        METHODUNIC = "gateWayConnection";
        URL = "http://" + host + ":" + port + "/PrinterService/EndPoint?wsdl";
        System.out.println("url: " + URL);
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHODUNIC);
            request.addProperty("arg0", json);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            transporte.call(NAMESPACE + METHODUNIC, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            System.out.println("ingreso:   " + result.getProperty(0).toString());

            response = result.getProperty(0).toString();
            System.out.println("desemcriptado:   " + response);
        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }
        return response;
    }

  
    public void radioLocalizadorAero(String direccion) {
        String response = "";
        NAMESPACE = "http://radiolocalizador.aeropuerto.com/";

        METHODUNIC = "solicitudServicioAeropuerto";
        URL = "http://" + host + ":" + port + "/Aeropuerto/WebServiceRadiolocalizadorAero?wsdl";
        System.out.println("url: " + URL);
        try {
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
                System.out.println("precio:   " + (pojoSoap.getProperty("precio").toString()));
                System.out.println("metros:   " + (pojoSoap.getProperty("metros").toString()));
                System.out.println("tiempo:   " + (pojoSoap.getProperty("tiempo").toString()));
            }

        } catch (Exception e) {
            String error = e.getMessage();
            error += "";
            System.out.println(error);
        }
        //  return null;

    }

    public static void main(String[] args) {
        new WebserviceConnection("srvapp-aero.losunos.net", new Integer("8080")).radioLocalizadorAero("Avenida americas 50 15");
    }
}
