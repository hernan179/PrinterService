/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import static com.rta.opain.delegate.tools.LogTest.rw;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author hsancheza
 */
public class EvaluarFaltantes {

    public static void main(String[] args) {
        int contador = 1;
        for (int i = 0; i < 10; i++) {

            if (contador > 1 && contador < 10) {
               System.out.println("union all");
                System.out.println("b select * " + i);
            } else if (contador == 1) {
                System.out.println("c select * " + i);
            }
            
            System.out.println("--------------------------");
            contador++;
        }
    }

    public String[] evalFaltantes(List<Integer> val, String comprob) {
        int inicio = val.get(0);
        int fin = val.get(val.size() - 1);
        int catidad = val.size();
        //LogTest.rw("inicio:" + inicio + ", fin:" + fin + " total: " + catidad);
        int countEstan = 0;
        List<Integer> inEstan = new ArrayList<Integer>();
        List<Integer> inTodos = new ArrayList<Integer>();
        out:
        for (Integer i : val) {
            for (int j = inicio; j <= fin; j++) {
                if (i == j) {
                    countEstan++;
                    inEstan.add(i);
                    continue out;
                }
            }
        }

        int itera = 0;
        for (int k = inicio; k <= fin; k++) {
            if (itera == 0) {
                // System.out.println("cantidad de iteracions: " + (k));
                inTodos.add(k);
            } else {
                // System.out.println("cantidad de iteracions: " + (k + 1));
                inTodos.add(k + 1);
            }
        }
        Set<Integer> faltantes = new TreeSet<Integer>();
        List<Integer> fal = new ArrayList<Integer>();
        for (Integer todos : inTodos) {
            if (!inEstan.contains(todos)) {
                faltantes.add(todos);
            }
        }
        String sqlFaltan = null;
        int contador = 1;
        String updateFaltan = " update srv_camioneta set ind_proc = 1 where ind_proc = 0  and comprob = '" + comprob + "' and cierre = 1 and numero <= " + fin + ";";
        String updateFaltan2 = " update srv_camioneta set ind_proc = 0 where comprob = '" + comprob + "' and cierre = 1 and numero > " + fin + ";";
        if (faltantes.size() > 0) {
            sqlFaltan = "select * from srv_camioneta where numero in(";

            for (Integer s : faltantes) {
                fal.add(s);
                if (faltantes.size() > contador) {
                    sqlFaltan += s + ",";
                } else {
                    sqlFaltan += s;
                }
                contador++;
            }

            sqlFaltan += ") and comprob = '" + comprob + "' and cierre = 1";
        }

        rw("###########################################################################################");
        rw("inicio:" + inicio + " fin:" + fin + " total: " + catidad + "  faltan: " + fal.size() + " comprob: " + comprob + "\n sql" + comprob + ": " + sqlFaltan);
        return new String[]{sqlFaltan, updateFaltan, updateFaltan2};
    }
}
