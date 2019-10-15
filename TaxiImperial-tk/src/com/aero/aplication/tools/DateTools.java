/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import static com.aero.aplication.tools.Helper.rw;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hernan
 */
public class DateTools {

    public static void main(String[] args) {

        rw("CLIENTES ==> " + (stringToDateMMDDYYYY("5/22/2015")));

        DateTools dateTools = new DateTools();
//        Date fechaActual = new Date();
//        Calendar fechaActualCal = dateTools.dateToCalendar(fechaActual);
//        String fechaActualStr = "04/09/2015";
//        Date fechaActualFormateada = dateTools.stringToDateMMddyyyy(fechaActualStr);

//        System.out.println("CLIENTES ==> fechaActual = " + fechaActual);
//        System.out.println("CLIENTES ==> resDateToCalendar fechaActualCal = " + fechaActualCal.getTime());
//        System.out.println("CLIENTES ==> ____________________________________________________");
//        System.out.println("CLIENTES ==> fechaActualStr = " + fechaActualStr);
//        System.out.println("CLIENTES ==> resStringToDateMMddyyyy fechaActualStr = " + fechaActualStr);
//        System.out.println("CLIENTES ==> ____________________________________________________");
//        System.out.println("CLIENTES ==> Fecha Sistema = " + new Date());
//        System.out.println("CLIENTES ==> Fecha Sistema Formateada dateToStringMMddyyyy = " + dateTools.dateToStringMMddyyyy(new Date()));
//        System.out.println("CLIENTES ==> ____________________________________________________");
//        System.out.println("CLIENTES ==> Hora = 18:00 --> date = " + stringToHhMm("18:00"));
        String date = "2016-03-04 08:53:36.0";
        Date fecha = dateTools.stringToDateyyyyMMdd2(date);

    }

    public Date stringToDate(String str_date) {
        String ss = "2005-05-05";

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
            date = formatter.parse(str_date);
        } catch (Exception e) {
        }

        return date;
    }

    public String fechaLegible(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd/yy HH:mm");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return date.toString();
        }
    }

    public static Date stringToDateMMDDYYYY(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date myFecha = sdf.parse(fecha);
            rw("CLIENTES ==> [DateTools.stringToDateMMDDYYYY] Entrada Str = " + fecha + ", Salida Date = " + myFecha);
            return myFecha;
        } catch (Exception e) {
            return null;
        }
    }
    public Integer[] dateToArray(Date date) {
        Integer[] fechaArray = new Integer[3];
        DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        fechaArray[0] = cal.get(Calendar.YEAR);
        fechaArray[1] = cal.get(Calendar.MONTH) + 1;
        fechaArray[2] = cal.get(Calendar.DAY_OF_MONTH);

        return fechaArray;

    }

    public static Date convertDateFromJsonFormat(String fechaStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date fecha = null;

        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException ex) {
            rw("Error!: " + ex.getMessage());
        }

        return fecha;
    }

    public Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public Date stringToDateMMddyyyy(String dateTxt) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // El método parse devuelve null si no se ha podido parsear el string en  según el formato indicado. 
        //Este método lanza una excepción NullPointer  exception si alguno de sus parámetros es null 
        return sdf.parse(dateTxt, new ParsePosition(0));
    }

    public String dateToStringMMddyyyy(Date fecha) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(fecha);
    }

    public String dateToStringMMddyyyyHHmm(Date fecha) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return df.format(fecha);
    }

    public static Date stringToHhMm(String hora) {
        try {

            if (hora.split(":").length == 2) {
                hora += ":00";
            }

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            return dateFormat.parse(hora);

        } catch (Exception e) {
            return null;
        }

    }

    public static String dateToStringHhMm(Date date) {
        String dateStr = "";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateStr = dateFormat.format(date);
        rw("CLIENTES ==> DateTools.dateToStringHhMm ==> " + dateStr);
        return dateStr;
    }

    public Date dateToDateddMMyyyy(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(sdf.format(date), new ParsePosition(0));
    }

    public Date stringToDateyyyyMMdd(String dateTxt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        // El método parse devuelve null si no se ha podido parsear el string en  según el formato indicado. 
        //Este método lanza una excepción NullPointer  exception si alguno de sus parámetros es null 
        return sdf.parse(dateTxt, new ParsePosition(0));
    }

    public Date StringToDateyyyyMMddFull(String dateTxt) {

        rw("Entrada: " + dateTxt);

        if (dateTxt.contains(".")) {
            dateTxt = dateTxt.split(".")[0];
        }

        Date fecha = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            fecha = formatter.parse(dateTxt);
        } catch (ParseException ex) {
            rw(ex.getMessage());
        }

        return fecha;
    }

    public Date stringToDateyyyyMMdd2(String dateTxt) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(dateTxt);
//            System.out.println(date);
//            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date stringToDateyyyyMMddHHmmss(String dateTxt) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = formatter.parse(dateTxt);
//            System.out.println(date);
//            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
