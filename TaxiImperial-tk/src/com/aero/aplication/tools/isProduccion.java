/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import java.net.InetAddress;

/**
 *
 * @author hsancheza
 */
public class isProduccion {

    public static void main(String[] args) {
        String value = new isProduccion().isProduccion();
        System.out.println("val:" + value);
    }

    public String isProduccion() {

        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            if (host.contains("BOGRTATECAP5") || host.contains("192.168.23.135")) {
                return "srvapp-desarrollo.losunos.net";
            } else {
                return "200.91.204.38";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "AERO";
        }
    }
}
