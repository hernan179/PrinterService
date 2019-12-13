/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.node;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author hsancheza
 */
public class Constantes {

      public static final String HOST_GEO = "http://192.168.20.53:8280/GeoReferenciador/GeoController";
      //public static final String HOST_GEO = "http://213.136.84.4:8280/GeoReferenciador/GeoController";
    static Properties pr = getProperties();
    //public static final String HOST_GEO = pr.getProperty("HOST_GEO");

    public static Properties getProperties() {

        String hostFile = "/opt/compilados/servinformacion.properties";
        Properties configFile = new Properties();

        try {
            configFile.load(new FileInputStream(hostFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return configFile;
    }
}
