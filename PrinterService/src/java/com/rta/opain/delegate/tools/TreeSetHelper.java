/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import com.rta.opain.domain.RegisterInOut;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author hsancheza
 */
public class TreeSetHelper implements Serializable{

    public static Set<RegisterInOut> getRegistrer(List<RegisterInOut> lLista) {
        TreeSet<RegisterInOut> todasLasCompras = new TreeSet<RegisterInOut>(new Comparator<RegisterInOut>() {
            @Override
            public int compare(RegisterInOut o1, RegisterInOut o2) {
                RegisterInOut p1 = (RegisterInOut) o1;
                RegisterInOut p2 = (RegisterInOut) o2;
                if (p2.getFecha().compareTo(p1.getFecha()) == 0) {
                    return p2.getFecha().compareTo(p1.getFecha());
                }
                return (p2.getFecha().compareTo(p1.getFecha()));
            }
        });

        if (lLista != null && lLista.size() > 0) {
            todasLasCompras.addAll(lLista);
        }
        return todasLasCompras;
    }
}
