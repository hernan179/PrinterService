/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author hsancheza
 */
public class ToolsDias {

    public static void main(String[] args) throws Exception {
        isData(new Date());
        
        Calendar fechaInicial = new GregorianCalendar(2015, 7 - 1, 01);
        Calendar fechaFinal = new GregorianCalendar(2015, 7 - 1, 01);

        int diffDays = 0;
        int diffDaysFestivos = 0;

        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
            //si el dia de la semana de la fecha minima es diferente de sabado o domingo
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                //se aumentan los dias de diferencia entre min y max
                diffDays++;
            } else {
                diffDaysFestivos++;
            }
            //se suma 1 dia para hacer la validacion del siguiente dia.
            fechaInicial.add(Calendar.DATE, 1);

        }

        //   return diffDays;
        System.out.print(diffDays + " festiovs y sabados: " + diffDaysFestivos);

        //    }    
    }

    public static void isData(Date date) {
        Calendar fechaInicial = GregorianCalendar.getInstance();
        fechaInicial.setTime(date);

        //si el dia de la semana de la fecha minima es diferente de sabado o domingo
        if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            //se aumentan los dias de diferencia entre min y max
            System.out.println(" is un dia festivo: ");
        }else{
            System.out.println("es un dia habil");
        }


    }
}
