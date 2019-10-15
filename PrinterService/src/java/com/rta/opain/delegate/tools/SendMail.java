package com.rta.opain.delegate.tools;

import com.rta.opain.delegate.dto.Cierre;
import com.rta.opain.delegate.dto.JsonDTO;
import static com.rta.opain.delegate.tools.StringTools.getString$;
import static com.rta.opain.delegate.tools.Utilidades.getNumberInt;
import com.rta.opain.domain.Servicios;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.rmi.CORBA.Util;

public class SendMail {

    
  public static String from = "contador.taxi.imperial@gmail.com";
    public static String pass = "seguro2018";
    public static String to = "hernan179@gmail.com";
    public static String host = "smtp.gmail.com";
    
    
    

    public static void main(String[] args) {
        Cierre cierre = new Cierre();
        cierre.setUsuarioNombre("prueba");
        cierre.setFecha(new Date());
        cierre.setInicio(123);
        cierre.setFin(22);
        cierre.setValorE(11);
        cierre.setValorC(222);
        cierre.setValorD(33);
        cierre.setValorTotal(33);

        new SendMail().sendMesageCierre(cierre);

    }

    public boolean sendMesageAnulacion(String nombreCajero, String detalle, String numeroFactura, String valorPago, String vjs) {
        String msgBody = "<p>Anulacion Viajes imperial: </p>";
        msgBody += "<p>Cajero     : " + nombreCajero + "</p>";
        msgBody += "<p>Factura:" + vjs + " " + numeroFactura + "</p>";
        msgBody += "<p>Por: $" + valorPago + "</p>";
        msgBody += "<p>Causa:" + detalle + "</p>";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("johnjairo1981@gmail.com"));

            // Set Subject: header field
            message.setSubject("ANULACON DE FACTURA " + numeroFactura);

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + msgBody + "</h1>", "text/html");

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

    public static Session getSession() {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties);
        return session;
    }

    public MimeBodyPart addFacturaByUser(byte[] bytes) throws Exception {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
            baos.write(bytes, 0, bytes.length);
            baos.close();
            DataSource aAttachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
            MimeBodyPart part = new MimeBodyPart();
            String fecha = DateHelper.getFechaFile(60);
            part.setFileName("reporte_cierre_" + fecha + ".pdf");
            part.setDataHandler(new DataHandler(aAttachment));
            return part;
        } catch (Exception e) {
        }
        return null;
    }

    public MimeBodyPart addFileVehiculos(byte[] bytes, String name) throws Exception {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
            baos.write(bytes, 0, bytes.length);
            baos.close();
            DataSource aAttachment = new ByteArrayDataSource(baos.toByteArray(), "text/plain");
            MimeBodyPart part = new MimeBodyPart();
            part.setFileName(name);
            part.setDataHandler(new DataHandler(aAttachment));
            return part;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean sendMesageContainer(String contepto, String body, Multipart multipart) {
        String msgBody = "<p>" + contepto + "</p>";
        msgBody += "<p>" + body + "</p>";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("hernan179@gmail.com"));

            if (contepto.contains("FOTO")) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress("consueloh@viaseguros.com.co"));
                message.addRecipient(Message.RecipientType.CC, new InternetAddress("contabilidad@taxiimperial.com.co"));
            }
            if (!contepto.contains("satelites")) {//
                message.addRecipient(Message.RecipientType.CC, new InternetAddress("johnjairo1981@gmail.com"));
            } else {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress("coordinador_puntos_satelites@taxislibres.com.co"));
            }
            // Set Subject: header field
            message.setSubject(contepto);

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + msgBody + "</h1>", "text/html");
            if (multipart != null) {
                message.setContent(multipart);
            }

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

    public String formatNum(Integer value) {
        Double d = null;
        try {
            d = new Double(value);
        } catch (Exception e) {
            value = 0;
        }

        return String.format("$ %1$,.2f", d);
    }

    public boolean sendMesageCierre(Cierre cierre) {
        String msgBody = "<p>Cierres Viajes imperial: </p>";
        msgBody += "<p>Cierre en     : " + cierre.getSitioNombre() + "</p>";
        msgBody += "<p>Cajero        : " + cierre.getUsuarioNombre() + "</p>";
        msgBody += "<p>Fecha         : " + cierre.getFecha() + "</p>";
        msgBody += "<p>Inicio        :" + cierre.getComprob() + " - " + cierre.getInicio() + "</p>";
        msgBody += "<p>Fin           :" + cierre.getComprob() + " - " + cierre.getFin() + "</p>";
        msgBody += "<p>No. Anuladas  :" + cierre.getCantidadAnuladas() + "</p>";
        msgBody += "<p>No. Total Fac :" + cierre.getCantidad() + "</p>";
        msgBody += "<p>Total Efectivo:" + formatNum(cierre.getValorE()) + "</p>";
        msgBody += "<p>Total Credito :" + formatNum(cierre.getValorC()) + "</p>";
        msgBody += "<p>Total Debito  :" + formatNum(cierre.getValorD()) + "</p>";
        msgBody += "<p>Total         :" + formatNum(cierre.getValorTotal()) + "</p>";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        // Get system properties
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

            message.addRecipient(Message.RecipientType.CC, new InternetAddress("hernan179@gmail.com"));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));
            

            // Set Subject: header field
            message.setSubject("CIERRE DE FACTURAS " + cierre.getUsuarioNombre());

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + msgBody + "</h1>", "text/html");

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

    public boolean sendMesageCierre2(Cierre cierre, Multipart multipart) {
        String msgBody = "<p>Cierres Viajes imperial: </p>";
        msgBody += "<p>Cierre en     : " + cierre.getSitioNombre() + "</p>";
        msgBody += "<p>Cajero        : " + cierre.getUsuarioNombre() + "</p>";
        msgBody += "<p>Fecha         : " + cierre.getFecha() + "</p>";
        msgBody += "<p>Inicio        :" + cierre.getComprob() + " - " + cierre.getInicio() + "</p>";
        msgBody += "<p>Fin           :" + cierre.getComprob() + " - " + cierre.getFin() + "</p>";
        msgBody += "<p>No. Anuladas  :" + cierre.getCantidadAnuladas() + "</p>";
        msgBody += "<p>No. Total Fac :" + cierre.getCantidad() + "</p>";
        msgBody += "<p>Total Efectivo:" + formatNum(cierre.getValorE()) + "</p>";
        msgBody += "<p>Total Credito :" + formatNum(cierre.getValorC()) + "</p>";
        msgBody += "<p>Total Debito  :" + formatNum(cierre.getValorD()) + "</p>";
        msgBody += "<p>Total         :" + formatNum(cierre.getValorTotal()) + "</p>";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        // Get system properties
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

            message.addRecipient(Message.RecipientType.CC, new InternetAddress("hernan179@gmail.com"));

            // Set Subject: header field
            message.setSubject("CIERRE DE FACTURAS " + cierre.getUsuarioNombre());
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + msgBody + "</h1>", "text/html");

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(msgBody, "text/html");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

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

    public boolean sendMesageCierreFinDeMes(String mensaje) {
        String msgBody = "<p>Cierres Viajes imperial fin de mes </p>";
        msgBody += "<p>" + mensaje + " </p>";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        // Get system properties
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("hernan179@gmail.com"));

            // Set Subject: header field
            message.setSubject("CIERRE DE FACTURAS - Fin De mes");
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>" + msgBody + "</h1>", "text/html");

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

    public static String styleCSS() {
        String body = "<style>";
        //body += "table a:link {color: #666;font-weight: bold;text-decoration:none;}";
        //body += "table a:visited {color: #999999;font-weight:bold;text-decoration:none;}";
        //body += "table a:active,table a:hover {color: #bd5a35;text-decoration:underline;}";
        //body += "table {	font-family:Arial, Helvetica, sans-serif;	color:#666;	font-size:12px;	text-shadow: 1px 1px 0px #fff;	background:#eaebec;	margin:20px;	border:#ccc 1px solid;-moz-border-radius:3px;-webkit-border-radius:3px;	border-radius:3px;-moz-box-shadow: 0 1px 2px #d1d1d1;-webkit-box-shadow: 0 1px 2px #d1d1d1;box-shadow: 0 1px 2px #d1d1d1;}";
        body += ".myTable {width: 100%;text-align: left;  background-color: lemonchiffon;  border-collapse: collapse;   }";
        body += ".myTable th {   background-color: goldenrod;  color: white;   }";
        body += ".myTable td, .myTable th {   padding: 10px;  border: 1px solid goldenrod;   }";
        body += "</style>";
        return body;
    }

    public static String getMessageBody(JsonDTO mensajeAERO, JsonDTO mensajeVPS, String fecha) {

        String body = "";//styleCSS();
        body += "<table style=\"font-size:12px;\">";
        body += "<tr>";
        body += "<td colspan=3>";
        body += "<h3>Balance para " + fecha + " hasta " + DateHelper.nowTimeDateEasy() + "<h3>";
        body += "</td>";
        body += "</tr>";
        body += "<tr><td>DETALLE</td><td>VPS</td><td>AERO</td></tr>";
        body += "<tr><td>EFECTIVO</td><td>" + getString$(mensajeVPS.getEfectivo()) + "</td><td>" + getString$(mensajeAERO.getEfectivo()) + "</td></tr>";
        body += "<tr><td>DEBITO</td><td>" + getString$(mensajeVPS.getDebito()) + "</td><td>" + getString$(mensajeAERO.getDebito()) + "</td></tr>";
        body += "<tr><td>CREDITO</td><td>" + getString$(mensajeVPS.getCredito()) + "</td><td>" + getString$(mensajeAERO.getCredito()) + "</td></tr>";
        body += "<tr><td colspan=2 style=\"text-align: right;\">DIFERENCIA:<td>" + mensajeAERO.getDiferencia() + "</td></tr>";
        body += "<tr><td colspan=2 style=\"text-align: right;\">ESTADO:<td>" + mensajeAERO.getResultado() + "</td></tr>";
        body += "<tr><td>TOTAL:</td><td>" + getString$(mensajeVPS.getTotal()) + "</td><td>" + getString$(mensajeAERO.getTotal()) + "</td></tr>";

        body += "<tr><td colspan=3>Detalles aun no contabilizados - pendiente cierre</td></tr>";
        body += "<tr><td colspan=2 style=\"text-align: right;\">Ultima Factura:</td><td>" + mensajeVPS.getFacturaUltima() + "</td></tr>";
        body += "<tr><td colspan=2 style=\"text-align: right;\">Mas antigua:</td><td>" + mensajeVPS.getFacturaAntigua() + "</td></tr>";
        body += "<tr><td colspan=2 style=\"text-align: right;\">En cange:</td><td>" + mensajeVPS.getTotalCange() + "</td></tr>";

        body += "<tr><td colspan=3></td></tr>";
        body += "<tr><td colspan=3>Estadistica del Mes " + mensajeAERO.getConsolidadoFaltante() + "</td></tr>";
        body += "<tr><td>Total Mes</td><td>VPS</td><td>AERO</td></tr>";
        body += "<tr><td>No Facturas</td><td>" + mensajeVPS.getConsolidadoValueVps() + "</td><td>" + mensajeAERO.getConsolidadoValueAero() + "</td></tr>";

        body += "</table>";

        return body;
    }
}
