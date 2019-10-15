/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

//import javax.activation.*;
public class SendMail {

    static String from = "delegate.bussines@gmail.com";
    static String pass = "delegate.";

    public static void main(String[] args) {
        //new SendMail().sendOtpCode(from, pass, String to,"02",(com.rta.num.cien.facade.tools.DateTransformed.fechaFinal(new Helper().getColCurrentTime()).toString()));
    }

    public boolean sendOtpCode(String to, String mensaje) {

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("TEST RESULTADO PRUEBA CONSULTA PRECIO");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + mensaje + "</h1>", "text/html");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
}