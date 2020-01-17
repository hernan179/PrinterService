/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import static com.rta.opain.delegate.tools.LogTest.rw;
import java.net.InetAddress;
import java.util.TimeZone;

/**
 *
 * @author Hernan Sanchez
 */
public class DateHelper {

    public static Date restartMinutos(Date fechaInicio, int minutos) {

        try {
            Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy 
            calendar.setTime(fechaInicio);
            calendar.add(Calendar.MINUTE, -minutos);
            return calendar.getTime();
        } catch (Exception ex) {
            return fechaInicio;
        }
    }

    public static String getFechaFile(int minutos) {
        Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy 
        calendar.add(Calendar.MINUTE, -minutos);
        SimpleDateFormat smdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        Date d = calendar.getTime();
        String sDate = smdf.format(d); //14-09-2017 10:35    
        return smdf.format(d);

    }

    public static String fechaIniMes() throws Exception {
        Date fDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sFecha = sdf.format(fDate);
        String part[] = sFecha.split("\\-");

        String anio = part[0];
        String mes = part[1];
        String dia = "01";
        return anio + "-" + mes + "-" + dia;
    }

    public static Date addDay(int addDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, addDias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime();
    }

    public static String _3Dias() {
        Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy 
        calendar.add(Calendar.DATE, -3);
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = calendar.getTime();
        String fecha = smdf.format(d);
        return fecha;
    }

    public static String restarMinutos(Integer minutos) throws Exception {
        Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy 
        calendar.add(Calendar.MINUTE, -minutos);
        SimpleDateFormat smdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date d = calendar.getTime();

        String sDate = smdf.format(d); //14-09-2017 10:35    
        //14-09-2017 10:47

        System.out.println("ahora_" + new Date() + "____restar_" + minutos + "____" + d + "   Sdate_" + sDate);
        return sDate;
    }


    /*public static String _3Dias(Date d1, int minutos) throws Exception {//06/05/2011 -- 01/05/2011
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");//'2017-04-15'
        String serverName = new isProduccion().isProduccion();
        if (serverName.equals("AERO")) {
            //  smdf = new SimpleDateFormat("dd-MM-yyyy");
        }

        try {
            long tiempoInicial = d1.getTime() - (minutos * 1000 * 60);
            Date d = new Date(tiempoInicial);
            String fecha = smdf.format(d);

            return fecha;
        } catch (Exception ex) {
            return smdf.format(new Date());
        }

    }*/

 /*public static String _30Dias(Date d1, int minutos) throws Exception {//06/05/2011 -- 01/05/2011
        SimpleDateFormat smdf = new SimpleDateFormat("MM-dd-yyyy");
        String serverName = new isProduccion().isProduccion();
        if (serverName.equals("AERO")) {
            smdf = new SimpleDateFormat("dd-MM-yyyy");
        }

        try {
            long tiempoInicial = d1.getTime() - (minutos * 1000 * 60);
            Date d = new Date(tiempoInicial);
            String fecha = smdf.format(d);

            return fecha;
        } catch (Exception ex) {
            return smdf.format(new Date());
        }

    }*/
    public static boolean isFestivo() {

        Calendar now = Calendar.getInstance();
        boolean isfestivo = false;
        String[] strDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday"};
        String festivo = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
        if (festivo.equals("Sunday")) {
            isfestivo = true;
        }
        String fecha = (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);

        String[] fes = new String[]{"10/15/2015", "02/11/2015", "16/11/2015", "08/12/2015", "25/12/2015", "01/01/2016", "21/03/2016", "24/03/2016", "25/03/2016"};
        for (int i = 0; i < fes.length; i++) {
            if (fes[i].equals(fecha)) {
                System.out.println("es festivo: " + fecha);
                isfestivo = true;
            }
        }

        return isfestivo;
    }

    public static String primerDia(Date date) throws Exception {//06/05/2011 -- 01/05/2011
        Calendar fe = Calendar.getInstance();
        fe.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (new isProduccion().isProduccion().equals("VPS")) {
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        }
        Date nvFecha = fe.getTime();
        rw("Primer dia del mes :" + nvFecha);
        return sdf.format(nvFecha);
    }

    public static Date stringToDate(String str_date) {
        Date date = null;
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(str_date.replaceAll("/", "-"));
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date stringToDate2(String str_date) {
        Date date = null;
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.parse(str_date.replaceAll("/", "-"));
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date toDateYYYYMMDDHHMM2(String str_date) {
        Date date = null;
        try {

            if (str_date.contains("\\/")) {
                str_date.replaceAll("\\/", "-");
            }

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.parse(str_date);
        } catch (Exception e) {
            //e.printStackTrace();
            //return null;
        }
        // rw("Fecha como string :   " + str_date + "  es:  " + date);
        return date;
    }

    public static Date toDateYYYYMMDDHHMM(String str_date) {
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            date = formatter.parse(str_date);
        } catch (Exception e) {
            //e.printStackTrace();
            //return null;
            String date2 = formatter.format(new Date());
            try {
                date = formatter.parse(date2);
            } catch (Exception e2) {
                return new Date();
            }
        }
        // rw("Fecha como string :   " + str_date + "  es:  " + date);
        return date;
    }

    public static Date toDateYYYYMMDDHHMM_eror(String str_date) {
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {

            date = formatter.parse(str_date);
        } catch (Exception e) {
            //e.printStackTrace();
            //return null;
            String date2 = formatter.format(new Date());
            try {
                date = formatter.parse(date2);
            } catch (Exception e2) {
                return new Date();
            }
        }
        // rw("Fecha como string :   " + str_date + "  es:  " + date);
        return date;
    }

    public static String toDateYYYYMMDDHHMM(Date str_date) {
        String date = null;
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.format(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            //  return null;

        }
        //rw("Fecha como date :   " + str_date + "  estring:  " + date);
        return date;
    }

    public static String toDateYYYYMMDDHHMMOK(Date str_date) {
        String date = null;
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = formatter.format(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            //  return null;

        }
        //rw("Fecha como date :   " + str_date + "  estring:  " + date);
        return date;
    }

    public static String dateToSYYYYMMDD(Date str_date) {
        String date = null;
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.format(str_date);
        } catch (Exception e) {
            e.printStackTrace();
            //  return null;

        }
        return date;
    }

    public static int calcularDiferenciaEntreFechas(Date inicio, Date fin) {

        rw("Diferencia entre fecha1: " + inicio + " fecha2:" + fin);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] date = dateFormat.format(inicio).split("-");
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());
        long diferencia = (fin.getTime() - fecha.getTime()) / MILLSECS_PER_DAY;

        return (int) diferencia;
    }

    public static Long restartMinutos(Date fechaInicio, Date fechaFin) {
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            String s1 = smdf.format(fechaInicio);
            String s2 = smdf.format(fechaFin);

            Date d1 = smdf.parse(s1);
            Date d2 = smdf.parse(s2);

            long tiempoInicial = d1.getTime();
            long tiempoFinal = d2.getTime();
            long resta = tiempoFinal - tiempoInicial;
            resta = resta / (1000 * 60);
            return resta;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static void main(String[] args) throws Exception {
        Date fecha = toDateYYYYMMDDHHMM("2020-01-10 08:24");//fecha_Fri Dec 06 17:19:00 COT 2019
        //fecha_Fri Dec 06 13:28:00 COT 2019
        //  System.out.println("fecha_" + fecha.toString());

        System.out.println("diff_" + fecha);
        Date diff = restartMinutos(fecha, 5);
        System.out.println("diff_" + diff);

//        
//        Date fecha = stringToDate("11/01/2018");
//        System.out.println("ddddddddddddddddddddddddddddd_"+fecha);
//        
//        
//        String ddd = fechaIniMes();
//        System.out.println("ddd_"+ddd);
//        
//       Date fecha1 = stringToDate("2018/01/23 00:00");
//       Date fecha2 = stringToDate("2018/01/23 07:55");
//       
//       int dias = calcularDiferenciaEntreFechas(fecha1, fecha2);
//        System.out.println("fecha_1"+fecha1);
//        System.out.println("fecha_2"+fecha2);
//        System.out.println("fecha_dias_"+dias);
//        String values = getFechaFile(60);
//
//        System.out.println("============== " + values);
//
//        restarMinutos(5);
//
//        String sFecha = _3Dias();//04-30-2017
//        System.out.println("sFecha: " + sFecha);
//
//        String fech = primerDia(new Date());
//        System.out.println("fech: " + fech);
//
//        Date d = toDateYYYYMMDDHHMM2("2016-09-29 10:16 AM");
//        System.out.println("f: " + d);
//
//        Date dd = restartMinutos(new Date(), 60 * 5);
//        System.out.println("restar minutos: " + new Date() + " menos 5h " + dd);
//
//        String date = "2016-06-30 14:57:11.0";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date sDates = sdf.parse(date);
//        System.out.println("date: " + sDates);
//
//        isFestivo();
        //System.out.println("is festivo: " + isFestivo());
//        System.out.println("dia mes: " + primerDia(new Date()));
//
//        Date f1 = toDateYYYYMMDDHHMM("2015-04-21 00:00 PM");
//        Date f2 = toDateYYYYMMDDHHMM("2015-06-22 16:48 PM");
//        Integer ii = calcularDiferenciaEntreFechas(f1, f2);
//        System.out.println("values:  " + ii);
//
//        Date f = toDateYYYYMMDDHHMM("2015-04-21 00:00 PM");
//        //  Date f = toDateYYYYMMDDHHMM("2015-06-22 16:24 PM");
//        System.out.println("f: " + f);
//        System.out.println("_____________________");
//
//// Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Colombia"));
//// Date currentDate = calendar.getTime();
//        Date d1 = toDateYYYYMMDDHHMM("2015/06/13 06:52");//Sat Jun 13 15:53:48 COT 2015,
//
//        String d2 = toDateYYYYMMDDHHMM(d1);
//        System.out.println("StringFecha d1:   " + d1);
//        System.out.println("StringFecha d2:   " + d2);
//
//        Date ddd = restartMinutos(new Date(), 15);
//        System.out.println("fecha hace 15 minutos:  " + toDateYYYYMMDDHHMM(ddd));
//
//        String fecha2 = "01/01/2015 02:31:37";
//
////        System.out.println("fecha dmy:   " + stringToDateDMYY(new Date()));
//        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
//        java.util.Date hoy = new Date(); //Fecha de hoy 
//
//        int año = 2009;
//        int mes = 10;
//        int dia = 22; //Fecha anterior 
//        Calendar calendar = new GregorianCalendar(año, mes - 1, dia);
//        java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());
//
//        long diferencia = (hoy.getTime() - fecha.getTime()) / MILLSECS_PER_DAY;
//        System.out.println(diferencia);
    }

    public static String nowTimeDateEasy() {
        // SimpleDateFormat dma = new SimpleDateFormat("MMMM dd yyyy HH:mmm a", new Locale("es_ES"));
        SimpleDateFormat dma = new SimpleDateFormat("yyyy MMMM dd HH:mmm");
        return dma.format(new Date());

    }

    public static String nowTimeDateEasy2() {
        // SimpleDateFormat dma = new SimpleDateFormat("MMMM dd yyyy HH:mmm a", new Locale("es_ES"));
        SimpleDateFormat dma = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dma.format(new Date());

    }
}
