/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

/**
 *
 * @author Hernan Sanchez
 */
public class InitHelper {

    public String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }
}
