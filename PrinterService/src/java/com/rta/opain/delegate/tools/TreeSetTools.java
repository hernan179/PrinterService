/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;


import com.rta.opain.delegate.dto.Persona;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author hsancheza
 */
public class TreeSetTools {
List<Persona> lDirecciones = new ArrayList<Persona>();
    
    public static TreeSet<Persona> treeSetDireccion(List cliente) {

        TreeSet<Persona> tsDirecciones = new TreeSet<Persona>(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) // implementing the compare(obj,obj)method of Comparator interface
            {
                Persona p1 = (Persona) o1;
                Persona p2 = (Persona) o2;
                if (p2.getCcConductor1().compareTo(p1.getCcConductor1()) == 0) {
                    return p2.getCcConductor1().compareTo(p1.getCcConductor1());
                }
                return p2.getCcConductor1().compareTo(p1.getCcConductor1());
            }
        });
        tsDirecciones.addAll(cliente);
        return tsDirecciones;
    }
    public static void main(String[] args) {
        
    }
}
