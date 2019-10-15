/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.easypass.tools;

/**
 *
 * @author hsancheza
 */
public class AppUtilidades {

    public static void main(String[] args) throws Exception {

        String encriptado = Md5HastTools.Encriptar("Esto es una prueba ....");
        System.out.println(encriptado);
        String desencriptado = Md5HastTools.Desencriptar(encriptado);
        System.out.println(desencriptado);

    }
}
