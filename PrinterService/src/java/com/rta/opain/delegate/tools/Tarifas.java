/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import static com.rta.opain.delegate.tools.LogTest.rw;
import static com.rta.opain.delegate.tools.LogTest.rwS;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author hsancheza
 */
public class Tarifas {

    public static void main(String[] args) {

        Integer[] vales = getValorReteIcaFuente(36300);
        System.out.println("nuevo_valor_" + vales[0] + "____BASE_" + vales[1]);

    }

    public static Integer calcularImpuestos(int costoSinIvas) {
        int VALOR_A_RECIBIR = costoSinIvas;
        Double RETE_FUENTE = 3.5;
        Double RETE_ICA = 0.0041;
        Double VALOR_CUENTA_COBRO = 0.0;
        int BASE_100 = 100;

        Double _RETE_FUENTE = RETE_FUENTE / BASE_100;

        Double _RETE_ICA = RETE_ICA * BASE_100;

        System.out.println("$ " + BASE_100 + " - " + RETE_FUENTE + " - " + _RETE_ICA);
        Double porcentaje_01 = BASE_100 - RETE_FUENTE - RETE_ICA;

        VALOR_CUENTA_COBRO = VALOR_A_RECIBIR * (porcentaje_01 + RETE_FUENTE + _RETE_ICA) / porcentaje_01;

        double auxCal = VALOR_CUENTA_COBRO * (-_RETE_FUENTE) / 1000;

        System.out.println("cal_" + VALOR_CUENTA_COBRO + "_____" + auxCal);
//cal_104051.98562840493
//cal_104051.98562840493
        return 0;
    }

    public static Integer redondeoSrvTaxi(String value) {// 123456-300
        if (value.length() > 2) {
            String centesima = value.substring(value.length() - 2);
            Integer nCentena = new Integer(centesima);
            Integer total = new Integer(value);
            Integer valorRedondo = total;
            if (nCentena < 50) {
                valorRedondo = total - nCentena;// 4480-480
            } else {
                valorRedondo = valorRedondo + (100 - nCentena);

            }
            return new Integer(valorRedondo);
        } else {
            return new Integer(value);
        }
    }

    public static Integer[] getValorReteIcaFuente(Integer valor2) {
        double constante = 96.781;
        double integral = 100 - constante;
        double valorBase = valor2 * constante / 100;
        double incrementoParcial = valor2 * integral / 100;
        Double valorTotalIncrementadoTmp = valor2 + incrementoParcial;

        Integer valorTotalIncrementado = redondeo("" + valorTotalIncrementadoTmp.intValue());

        valorBase = valorTotalIncrementado * constante / 100;

        double base85 = valorBase * 0.85;
        double base35 = base85 * 0.035;
        double base414 = base85 * 0.00414;

        Double valorTotalBase = valorBase + base35 + base414;

        rwS("CALCULO_TARIFA_valorInicial_" + valor2);
        rwS("CALCULO_TARIFA_valorBase_" + valorBase);
        rwS("CALCULO_TARIFA_incrementoParcial_" + incrementoParcial);
        rwS("CALCULO_TARIFA_valorTotalIncrementado_" + valorTotalIncrementadoTmp + "___redondeo_" + valorTotalIncrementado);
        rwS("CALCULO_TARIFA_ValorTotal_" + valorTotalIncrementado.intValue() + "___baseTotal_" + valorTotalBase.intValue());

        return new Integer[]{valorTotalIncrementado.intValue(), (int) valorBase};

    }

    public static Integer redondeo(String value) {
        if (value.length() > 3) {
            String centesima = value.substring(value.length() - 3);
            Integer nCentena = new Integer(centesima);
            Integer total = new Integer(value);
            Integer valorRedondo = total;
            if (nCentena < 500) {
                valorRedondo = total - nCentena;//4480-480
            } else {
                valorRedondo = valorRedondo + (1000 - nCentena);// 4480 + (1000- 480) = 520;
            }
            return new Integer(valorRedondo);
        } else {
            return new Integer(value);
        }
    }

    public long calcularTarifaAeropuerto(int metros, String myDir) {
        long VALORUNIDAD = 150;
        int RECARGO_UNIDADES = 180;
        long RECARGO_NOCFES = 40;
        long unidad;
        if (metros <= 4000) {
            unidad = 175;
        } else {
            unidad = (metros / 100) + RECARGO_UNIDADES;
        }
        //if (esFestivoNoc()) {
        if (false) {
            rw("esFestivoNoc");
            unidad += RECARGO_NOCFES;
        } else {
            rw("noFestivoNoc");
        }
        long val1 = (unidad * VALORUNIDAD);
        long pesos = val1 + 20000 - 8000 + 3000;
        String part[] = myDir.split("\\ ");
        if (!myDir.toUpperCase().trim().contains("SUR") && part[0].toUpperCase().contains("CL") || part[0].toUpperCase().contains("KR")) {
            int j = 0;
            out:
            for (int i = 0; i < part.length; i++) {
                if (part[1].matches("[0-9]*")) {
                    Integer calle = new Integer(part[1]);

                    if (calle > 62 && j == 0) {
                        if (part[0].toUpperCase().contains("KR") && calle > 72) {
                            long axu = pesos + 6000;
                            rw("DIRECION VIP CARGO ADD $" + pesos + " + 6000 = " + (axu));
                            pesos = axu;
                        } else {
                            long axu = pesos + 6000;
                            rw("DIRECION VIP CARGO ADD $" + pesos + " + 6000 = " + (axu));
                            pesos = axu;
                        }
                        j++;
                        break out;
                    }
                }
            }
        }
        return pesos;
    }

    public static boolean esFestivoNoc() {

        Date date = new Date("12/12/11 8:22:09 PM");
        System.out.println("Time in 24Hours =" + new SimpleDateFormat("HH:mm").format(date));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar fechaInicial = GregorianCalendar.getInstance();
        fechaInicial.setTime(DateHelper.toDateYYYYMMDDHHMM("2015-07-01 25:32 PM"));

        int hour = fechaInicial.get(Calendar.HOUR_OF_DAY);
        System.out.println("hour: " + hour);

        //si el dia de la semana de la fecha minima es diferente de sabado o domingo
        if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        } else {
            return false;
        }
    }
}
