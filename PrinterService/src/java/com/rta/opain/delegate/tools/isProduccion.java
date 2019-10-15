/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import java.net.InetAddress;

/**
 *
 * @author hsancheza
 */
public class isProduccion {

    public static String ipSrv = hostAddres();//"http://185.80.129.150";
//public static String ipSrv = "http://192.168.20.53";

    public String isProduccion() {

        try {
            String host = InetAddress.getLocalHost().getHostName();
            LogTest.rw("numCien:Server Name:=>  " + host);
             if (host.contains("aero")) {
                return "AERO";
            } else {
                return "VPS";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "AERO";
        }
    }

    public static String hostAddres() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "http://192.168.20.53";
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("host: " + hostAddres() );
    }
}
