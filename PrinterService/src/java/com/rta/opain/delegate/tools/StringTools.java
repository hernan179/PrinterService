/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hsancheza
 */
public class StringTools {

    public static void main(String[] args) {
        System.out.println("=>" + (getStringTrim("TUO565  ")) + "=");
    }

    public static String getString$(String sValue) {
        try {
            String value = getNumberInt(sValue).toString();
            if (value != null && !value.isEmpty() && new Double(value) > 0) {
                Double d = new Double(value);
                return String.format("$ %1$,.2f", d);
            } else {
                Double d = new Double("0");
                return String.format("$ %1$,.2f", d);

            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getNumberInt(String value) {
        if(value != null ){
            if(value.contains(".")){
              String[] nvString = value.split("\\.");
              value = nvString[0];
            }
        }
        if (value != null && !value.isEmpty() && value.matches("[0-9]*")) {
            return new Integer(value);
        } else {
            return new Integer("0");
        }
    }

    public static String getNumberInt(Integer value) {
        if (value != null) {
            return "" + value;
        } else {
            return new String("0");
        }
    }

    public static String dateToStringDMYHM(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fech = sdf.format(fecha);
        return fech;
    }

    public static Long getNumberLong(String value) {
        if (value != null && !value.isEmpty() && value.matches("[0-9]*")) {
            return new Long(value);
        } else {
            return new Long("0");
        }
    }

    public static String getStringTrim(String data) {
        if (data != null) {
            data = data.replaceAll(" +", " ");
            if (data.length() > 1) {
                if (data.substring(0, 1).equals(" ")) {
                    data = data.trim();
                }
                if (data.substring(data.length() - 1, data.length()).equals(" ")) {
                    data = data.substring(0, data.length() - 1);
                } else {
                    data = data.substring(0, data.length());
                }
                return data;
            } else {
                return data.trim();
            }
        } else {
            return "";
        }
    }
}
