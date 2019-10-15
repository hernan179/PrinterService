/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static com.aero.aplication.main.InitialContextUtil.getCurrentWorkingDirectory;

/**
 *
 * @author hsancheza
 */
public class FileUtilities {

    public void escribirRegistro(String inout, String json) {
        String filename = getCurrentWorkingDirectory() + "cargue_contable.txt";

        try {
            String osname = System.getProperty("os.name");
            String userDir = System.getProperty("user.dir");

            if (osname.toLowerCase().contains("windows")) {
                filename = getCurrentWorkingDirectory() + "cargue_contable.txt";
            }

            FileWriter fw = new FileWriter(filename, true);

            fw.write("FECHA: " + new SimpleDateFormat("dd MMMM yy HH:mm:ss").format(new Helper().getColCurrentTime()) + inout + " => " + json + "\n");//appends the string to the file

            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
