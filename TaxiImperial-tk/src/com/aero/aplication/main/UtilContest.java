/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.main;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;

public class UtilContest {


    public Object jndi(Object service, String nombre, String tipo) {
        try {
            Context jndi = new InitialContextUtil().inicializarCTX();
            Object obj = jndi.lookup(nombre + "/" + tipo);
            service = (Object) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }


    public static String nitEnFormato(String nits) {
        String test[] = nits.split("\\.");
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(Integer.parseInt(test[0]));
    }

    public static String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }
}
