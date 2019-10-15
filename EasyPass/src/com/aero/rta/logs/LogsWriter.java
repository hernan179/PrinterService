/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.logs;

import org.apache.log4j.Logger;

/**
 *
 * @author hsancheza
 */
public class LogsWriter {

    static Logger log = Logger.getLogger(LogsWriter.class);

    public static void logs(String message) {

        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info("easyPass:" + className + "." + methodName + "()[" + lineNumber + "] " + message);

    }
}
