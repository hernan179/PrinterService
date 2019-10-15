/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.imp.web.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author hsancheza
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application {


    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    /*private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.rta.ws.rest.EndPointContabilidad.class);
        resources.add(com.rta.ws.rest.EndPointVales.class);
        resources.add(com.rta.ws.rest.EndPointVales2.class);
        resources.add(com.rta.ws.rest.EndPointZonificador.class);
        resources.add(com.rta.ws.rest.Vales.class);
    }*/
    
}
