/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import java.net.HttpURLConnection;

/**
 *
 * @author hsancheza
 */
public class UrlConnectionTimeOut implements Runnable {

    HttpURLConnection con;

    public UrlConnectionTimeOut(HttpURLConnection con) {
        this.con = con;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000); // or Thread.sleep(con.getConnectTimeout())
        } catch (InterruptedException e) {

        }
        con.disconnect();
        System.out.println("Timer thread forcing to quit connection");
    }
}
