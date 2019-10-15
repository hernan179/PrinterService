/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.controller;

import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.PrinterServicesDelegate;   
import com.rta.opain.delegate.PrinterServicesDelegateRemote;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Paola
 */
public class EJBService {

    @EJB
    private static PrinterServicesDelegateRemote service;
    Context ctx;
    final String appName = "PrintServer-ear";
    final String moduleName = "PrinterService";

    public PrinterServicesDelegateRemote get() {
        if (service == null) {
            try {
                ctx = new InitialContext();
                final String distinctName = "";
                final String nombreBean = PrinterServicesDelegate.class.getSimpleName();
                final String nombreInterface = PrinterServicesDelegateRemote.class.getName();
                Object o = ctx.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nombreBean + "!" + nombreInterface);

                if (o != null) {
                    service = (PrinterServicesDelegateRemote) o;
                    //rw("Interface remote is onload..");
                } else {
                    // rw(" ncien: La interfaz remota del EJB para Recepcion no ha sido encontrado en el Servidor de Aplicaciones");

                }
            } catch (Exception e) {
                try {
                    service = (PrinterServicesDelegateRemote) ctx.lookup("PrinterServicesDelegate/remote");
                } catch (Exception e2) {
                    e.printStackTrace();
                    //System.err.println(" ncien: La interfaz remota del EJB para Recepcion no ha sido encontrado en el Servidor de Aplicaciones");  
                }
            }
        } else {
            rw("EJB CORRECTO");
        }
        return service;
    }
}
