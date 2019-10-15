/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate;

import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.delegate.tools.SendMail;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncBean {

    public String topic;
    public String contenido;

    @Asynchronous
    public void asyncMethod() {
        rw("INICIO SEND MAIL" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            new SendMail().sendMesageContainer(topic, contenido,null);
        } catch (InterruptedException e) {
            rw("Error al enviar el email " + e.getMessage());
        }
        rw("END SEND MAIL " + Thread.currentThread().getName());
    }
}
