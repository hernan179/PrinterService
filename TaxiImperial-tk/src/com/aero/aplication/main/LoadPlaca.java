/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.main;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author hsancheza
 */
public class LoadPlaca {

    public static void main(final String[] args) throws Exception {
        // cargarNits("TFR534");
        String[] _nits = new LoadPlaca().cargarNits2("SPS820");
        for (int i = 0; i < _nits.length; i++) {
            System.out.println("nits:   " + i + " = " + _nits[i]);
        }
    }

    static String[] cargarNits2(String placa) throws Exception {
       // Fila:VIAJES IMPERIAL;1022378961          ;TEO630;2012;HYUNDAI             ;MICROBUS;0;19123460;ARMANDO                                 GARCIA              CHICAEME            ;19123460;19123460;2018-08-27;0;0;0;0;ARMANDO                                 GARCIA              CHICAEME            ;4705561;CR 79C # 7A-61 APTO 203
       //CONVENIO;NIT;PLACA;MODELO;MARCA;CLASE;NUMERO INTERNO;TARJETA OPERACION;CONDUCTOR 1;CDULA;LICENCIA CONDUCCION;VIGENCIA;CONDUCTOR 2;CEDULA;LICENCIA CONDUCCIàN;VIGENCIA;RESPONSABLE DEL CONTRATANTE;CEDULA ;TELEFONO;DIRECCION 
       
        String[] myNits = new String[7];
        String columnas = "CONVENIO;NIT;PLACA;MODELO;MARCA;CLASE;NUMERO INTERNO;TARJETA OPERACION;CONDUCTOR 1;CDULA;LICENCIA CONDUCCION;VIGENCIA;CONDUCTOR 2;CEDULA;LICENCIA CONDUCCIàN;VIGENCIA;RESPONSABLE DEL CONTRATANTE;CEDULA ;TELEFONO;DIRECCION";
        String valuesColum[] = columnas.split("\\;");

        Integer nombreConductor1 = 0;
        Integer apellidoConductor1 = 0;
        Integer ccConductor1 = 0;
        Integer licenciaConductor1 = 0;
        Integer ligenciaConductor1 = 0;

        try {
            final String dir = System.getProperty("user.dir");
            FileReader fr = new FileReader(dir + "/vehiculos.csv");
            BufferedReader br = new BufferedReader(fr);
            String s;
            int contador = 1;
            while ((s = br.readLine()) != null) {
                String[] datos = s.split("\\;");
                if (datos != null && datos.length > 1 && contador > 1) {

                    if (datos[2].toUpperCase().equals(placa.toUpperCase())) {
                        System.out.println("FOUND IT:   " + datos.length + " =>  " + s);
                        myNits = datos;
                        return myNits;
                    }
                }
                contador++;
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return myNits;
    }

    static String[] cargarNits(String placa) throws Exception {
        String[] myNits = new String[7];
        try {
            final String dir = System.getProperty("user.dir");
            FileReader fr = new FileReader(dir + "/nit_placa.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                String[] datos = s.split("\\;");
                if (datos != null && datos.length > 1) {
                    System.out.println("datos:   " + datos[0] + "  vr " + placa);
                    if (datos[0].toUpperCase().equals(placa.toUpperCase())) {
                        System.out.println("FOUND IT: " + placa);
                        myNits = datos;
                        return myNits;
                    }
                }
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return myNits;
    }
}
