/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

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
        log.info("\n#100:"+className + "." + methodName + "()[" + lineNumber + "]" + message);

    }
}
