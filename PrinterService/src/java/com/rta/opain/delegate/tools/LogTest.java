/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import org.jboss.logging.Logger;

/**
 *
 * @author hsancheza
 */
public class LogTest {

    static Logger log = Logger.getLogger(LogTest.class);

    public static void rw(String message) {
        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info("" + className + "." + methodName + "() linea[" + lineNumber + "] " + message);

    }
     public static void rwS(String message) {
        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        System.out.println("" + className + "." + methodName + "() linea[" + lineNumber + "] " + message);

    }
    public static void main(String[] args) {
         String json = "{\"alertas\":\"error\":\"ok\",\"keyCommand\":\"18\"}";
         String nvJson =json;
            if (!json.startsWith("[") && !json.endsWith("]")) {
                json = "[" + nvJson + "]";
            }
            nvJson = json; 
            System.out.println("nv:  "+nvJson);
    }
}
