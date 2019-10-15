
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsancheza
 */
public class NumeroFaltante {

    public static void main(String[] args) {
        int[] val = new int[]{1, 2, 4, 5, 6, 7, 8, 9, 12, 13, 16, 19, 23};
        new NumeroFaltante().evalFaltantes(val);
    }

    public Set<Integer> evalFaltantes(int[] val) {
        int inicio = val[0];
        int fin = val[val.length - 1];
        int catidad = val.length;
        System.out.println("inicio:" + inicio + ", fin:" + fin + " total: " + catidad);
        int countEstan = 0;
        List<Integer> inEstan = new ArrayList<Integer>();
        List<Integer> inTodos = new ArrayList<Integer>();
        out:
        for (int i = 0; i < val.length; i++) {
            for (int j = inicio; j <= fin; j++) {
                if (val[i] == j) {
                    // System.out.println("si: " + val[i]);
                    countEstan++;
                    inEstan.add(val[i]);
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
        for (Integer todos : inTodos) {
            if (!inEstan.contains(todos)) {
                faltantes.add(todos);
            }
        }

        for (Integer falta : faltantes) {
            System.out.println("falta: " + falta);
        }
        return faltantes;
    }

}
