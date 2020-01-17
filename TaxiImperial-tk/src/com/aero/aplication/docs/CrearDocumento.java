/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.docs;

import com.aero.aplication.dto.Cierre;
import com.aero.aplication.dto.Sitios;
import com.aero.aplication.dto.Ticket;
import com.aero.aplication.tools.Helper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.print.*;
import javax.swing.ImageIcon;
//import static com.aero.aplication.main.InitialContextUtil.getCurrentWorkingDirectory;

public class CrearDocumento {

    Rectangle pageSize = new Rectangle(PageSize.POSTCARD);//200,500--ok
   //  Rectangle pageSize = new Rectangle(164.41f, 14400);
   // Rectangle rectangleOnPage = new Rectangle(10, 10, 100, 100);
    Document document = new Document(pageSize);// mas grande 
    
    
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    Image LOGO;
    Image LOGO_COMPANI;
    Image LOGO_FIRMA;
    Image IMG_FIRMA_REP_LEGAL;
    Image IMG_VIAJES_IMP;
    Paragraph CONTRATO;
    Paragraph ACEPTO;
    Paragraph ACEPTO2;
    Paragraph FIRMA;
    Paragraph FIRMA_RAYA;
    Paragraph FIRMA2;
    Paragraph FIRMA_RAYA2;
    Paragraph GRUPOS;
    Paragraph CONTRATISTA;
    Paragraph NIT;
    Paragraph LINEA;
    Paragraph DATOS_SRV;
    Paragraph RESOLUCION;
    Paragraph AUTORIZADO;
    Paragraph AUTORIZADO_EXT;
    Paragraph REPRESENTANTE;
    Paragraph RESOLUCION_EX;
    Paragraph RESOLUCION_EX3;
    Paragraph RESOLUCION_EX4;
    Paragraph RESOLUCION_EX5;
    Paragraph FACTURA;
    Paragraph FACTURA_EX;
    Paragraph FECHA;
    Paragraph TIPO_VEHICULO;
    Paragraph TIPO_VEHICULO_TEXT;

    Paragraph FORMA_PAGO;
    Paragraph FORMA_PAGO_TEXT;

    Paragraph FECHA_END;
    Paragraph FECHA_EX_END;
    Paragraph FECHA_EX;
    Paragraph PLACA;
    Paragraph PLACA_EX;
    Paragraph MARCA;
    Paragraph MARCA_EX;
    Paragraph MODELO;
    Paragraph MODELO_EX;
    Paragraph ATENDIDO;
    Paragraph ATENDIDO_EX;
    Paragraph ORIGEN;
    Paragraph ORIGEN_EX;
    Paragraph OBSERV;
    Paragraph OBSERV_EX;
    Paragraph DESTINO;
    int tamanio;
    Paragraph DESTINO_EX;
    Paragraph PASAJERO;
    Paragraph PASAJERO_EX;
    Paragraph NOMBRE_CLI;
    Paragraph NIT_CLI;
    Paragraph TOTAL;
    Paragraph TOTAL_EX;
    Paragraph CONDUCTOR;
    Paragraph CONDUCTOR_EX;
    Paragraph ESPACIO;
    Paragraph SENOR;
    Paragraph COPIA_USUARIO;
    Paragraph COPIA_CONDUCTOR;
    Paragraph COPIA_EMPRESA;

    void addCopyCliente(Ticket tiquete, Paragraph copia, boolean isCompany) {
        PdfPTable tLogo_1 = new PdfPTable(3);
        try {
//            if (isCompany) {
//                tLogo_1.addCell(obtenerCellColspamBanner("\n"));
//            }
//
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(LOGO));
            if (isCompany) {
                tLogo_1.addCell(obtenerCellColspamBanner(CONTRATO));

            }
            tLogo_1.addCell(obtenerCellColspamBannerLeft(ACEPTO));
            //tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));

            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA_RAYA));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA));

            if (isCompany) {
                tLogo_1.addCell(obtenerCellColspamBanner(IMG_FIRMA_REP_LEGAL));
            } else {
                tLogo_1.addCell(obtenerCellColspamBanner(IMG_VIAJES_IMP));

            }
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA_RAYA));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA2));

            tLogo_1.addCell(obtenerCellColspamBanner(LOGO_COMPANI));

            System.out.println("generando factura para company:" + isCompany);
            if (isCompany) {
                tLogo_1.addCell(obtenerCellColspamBanner(NIT));
                System.out.println("generando factura para company 2:" + isCompany);
            }

            if (isCompany) {

                tLogo_1.addCell(obtenerCellColspamBanner(RESOLUCION));
                tLogo_1.addCell(obtenerCellColspamBanner(RESOLUCION_EX));
                tLogo_1.addCell(obtenerCellColspamBanner(RESOLUCION_EX3));
            }
            tLogo_1.addCell(obtenerCellColspamBanner(LINEA));
            tLogo_1.addCell(obtenerCell(FACTURA));
            tLogo_1.addCell(obtenerCellColspam2(FACTURA_EX));
            tLogo_1.addCell(obtenerCell(FECHA));
            tLogo_1.addCell(obtenerCellColspam2(FECHA_EX));

            tLogo_1.addCell(obtenerCell(FECHA_END));
            tLogo_1.addCell(obtenerCellColspam2(FECHA_EX_END));

            tLogo_1.addCell(obtenerCell(PLACA));
            tLogo_1.addCell(obtenerCellColspam2(PLACA_EX));
            tLogo_1.addCell(obtenerCell(MARCA));
            tLogo_1.addCell(obtenerCellColspam2(MARCA_EX));
            tLogo_1.addCell(obtenerCell(MODELO));
            tLogo_1.addCell(obtenerCellColspam2(MODELO_EX));
            tLogo_1.addCell(obtenerCell(CONDUCTOR));
            tLogo_1.addCell(obtenerCellColspam2(CONDUCTOR_EX));
            tLogo_1.addCell(obtenerCell(ORIGEN));
            tLogo_1.addCell(obtenerCellColspam2(ORIGEN_EX));
            tLogo_1.addCell(obtenerCell(DESTINO));
            tLogo_1.addCell(obtenerCellColspam2(DESTINO_EX));

            //start
            tLogo_1.addCell(obtenerCell(OBSERV));
            tLogo_1.addCell(obtenerCellColspam2(OBSERV_EX));
            // end

            if (NOMBRE_CLI != null && !NOMBRE_CLI.isEmpty()) {
                tLogo_1.addCell(obtenerCell(NOMBRE_CLI));
                tLogo_1.addCell(obtenerCellColspam2(NIT_CLI));
            } else {
                tLogo_1.addCell(obtenerCell(new Paragraph(" - ")));
                tLogo_1.addCell(obtenerCellColspam2(new Paragraph(" - ")));
            }
            tLogo_1.addCell(obtenerCell(PASAJERO));
            tLogo_1.addCell(obtenerCellColspam2(PASAJERO_EX));

            tLogo_1.addCell(obtenerCell(TOTAL));
            tLogo_1.addCell(obtenerCellColspam2(TOTAL_EX));
            tLogo_1.addCell(obtenerCell(ATENDIDO));
            tLogo_1.addCell(obtenerCellColspam2(ATENDIDO_EX));

            if (!isCompany) {
                tLogo_1.addCell(obtenerCell(AUTORIZADO));
                tLogo_1.addCell(obtenerCellColspam2(AUTORIZADO_EXT));
                tLogo_1.addCell(obtenerCellColspamBanner(REPRESENTANTE));
                tLogo_1.addCell(obtenerCellColspamBanner(LOGO_FIRMA));
            }

            tLogo_1.addCell(obtenerCellColspamBanner(SENOR));
            tLogo_1.addCell(obtenerCellColspamBanner(copia));
            document.add(tLogo_1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addCopyCliente_qr(Ticket tiquete, Paragraph copia, boolean isCompany) {
        PdfPTable tLogo_1 = new PdfPTable(3);
        try {
            NIT = new Paragraph("NIT:900.529865", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));

            tLogo_1.addCell(obtenerCellColspamBanner(LOGO));

            tLogo_1.addCell(obtenerCellColspamBanner(NIT));
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://www.taxiimperial.com.co", 1000, 1000, null);
            Image codeQrImage = barcodeQRCode.getImage();

            tLogo_1.addCell(obtenerCellColspamBanner(codeQrImage));
            //tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            //tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            //document.add(tLogo_1);

            //PdfPTable tLogo_1 = new PdfPTable(3);
            DATOS_SRV = new Paragraph("Datos del servicio", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

            tLogo_1.addCell(obtenerCellColspamBanner(DATOS_SRV));

            tLogo_1.addCell(obtenerCell(ORIGEN));
            tLogo_1.addCell(obtenerCellColspam2(ORIGEN_EX));
            tLogo_1.addCell(obtenerCell(DESTINO));
            tLogo_1.addCell(obtenerCellColspam2(DESTINO_EX));

            FECHA = new Paragraph("Fecha y Hora", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            tLogo_1.addCell(obtenerCell(FECHA));
            tLogo_1.addCell(obtenerCellColspam2(FECHA_EX));

            TIPO_VEHICULO = new Paragraph("Tipo de Vehiculo", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            TIPO_VEHICULO_TEXT = new Paragraph("TAXI", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            tLogo_1.addCell(obtenerCell(TIPO_VEHICULO));
            tLogo_1.addCell(obtenerCellColspam2(TIPO_VEHICULO_TEXT));

            FORMA_PAGO = new Paragraph("Forma de pago", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            FORMA_PAGO_TEXT = new Paragraph("EFECTIVO", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            tLogo_1.addCell(obtenerCell(FORMA_PAGO));
            tLogo_1.addCell(obtenerCellColspam2(FORMA_PAGO_TEXT));

            tLogo_1.addCell(obtenerCell(TOTAL));
            tLogo_1.addCell(obtenerCellColspam2(TOTAL_EX));

            tLogo_1.addCell(obtenerCell(FACTURA));
            tLogo_1.addCell(obtenerCellColspam2(FACTURA_EX));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));

            document.add(tLogo_1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addCopyDriver(Ticket tiquete, Paragraph copia) throws Exception {
        PdfPTable tLogo_1 = new PdfPTable(3);
        try {
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));

            tLogo_1.addCell(obtenerCellColspamBanner(LOGO));

            tLogo_1.addCell(obtenerCellColspamBanner(NIT));
            tLogo_1.addCell(obtenerCellColspamBanner(LINEA));

            tLogo_1.addCell(obtenerCell(FACTURA));
            tLogo_1.addCell(obtenerCellColspam2(FACTURA_EX));

            tLogo_1.addCell(obtenerCell(FECHA));
            tLogo_1.addCell(obtenerCellColspam2(FECHA_EX));

            tLogo_1.addCell(obtenerCell(FECHA_END));
            tLogo_1.addCell(obtenerCellColspam2(FECHA_EX_END));

            tLogo_1.addCell(obtenerCell(PLACA));
            tLogo_1.addCell(obtenerCellColspam2(PLACA_EX));

            tLogo_1.addCell(obtenerCell(MARCA));
            tLogo_1.addCell(obtenerCellColspam2(MARCA_EX));

            tLogo_1.addCell(obtenerCell(MODELO));
            tLogo_1.addCell(obtenerCellColspam2(MODELO_EX));

            tLogo_1.addCell(obtenerCell(CONDUCTOR));
            tLogo_1.addCell(obtenerCellColspam2(CONDUCTOR_EX));

            tLogo_1.addCell(obtenerCell(ORIGEN));
            tLogo_1.addCell(obtenerCellColspam2(ORIGEN_EX));

            tLogo_1.addCell(obtenerCell(DESTINO));
            tLogo_1.addCell(obtenerCellColspam2(DESTINO_EX));

            if (NOMBRE_CLI != null && !NOMBRE_CLI.isEmpty()) {
                tLogo_1.addCell(obtenerCell(NOMBRE_CLI));
                tLogo_1.addCell(obtenerCellColspam2(NIT_CLI));
            } else {
                tLogo_1.addCell(obtenerCell(new Paragraph(" - ")));
                tLogo_1.addCell(obtenerCellColspam2(new Paragraph(" - ")));
            }
            tLogo_1.addCell(obtenerCell(PASAJERO));
            tLogo_1.addCell(obtenerCellColspam2(PASAJERO_EX));
            tLogo_1.addCell(obtenerCell(TOTAL));
            tLogo_1.addCell(obtenerCellColspam2(TOTAL_EX));
            tLogo_1.addCell(obtenerCell(ATENDIDO));
            tLogo_1.addCell(obtenerCellColspam2(ATENDIDO_EX));

            tLogo_1.addCell(obtenerCellColspamBanner(SENOR));
            tLogo_1.addCell(obtenerCellColspamBanner(copia));

            document.add(tLogo_1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] generarFactura_qr(Ticket tiquete, Sitios sitio, Properties pr, String reimpresion) throws Exception {

        document.open();
        try {
            //LOGO = Image.getInstance(getImage("/images/logo_viajes_imperial.jpg"));//
            LOGO = Image.getInstance(getImage("/images/taxi_imperial.jpg"));//nv_viajes.png
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String des = tiquete.getDestino();
            if (tiquete.getDestino().length() > 20) {
                des = tiquete.getDestino().substring(0, 20);
            }

            tiquete.setDestino(des);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //hsa   LOGO_FIRMA = Image.getInstance(getImage("/images/" + tiquete.getLogFirmaVehiculo()));

            String sitioDir = sitio.getDireccion();
            String myStart[] = sitioDir.split("\\|");

            CONTRATO = new Paragraph(pr.getProperty("CONTRATO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //ACEPTO = new Paragraph(pr.getProperty("ACEPTO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //ACEPTO2 = new Paragraph(pr.getProperty("ACEPTO2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //FIRMA = new Paragraph(pr.getProperty("FIRMA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //FIRMA_RAYA = new Paragraph(pr.getProperty("FIRMA_RAYA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //FIRMA2 = new Paragraph(pr.getProperty("FIRMA2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            //FIRMA_RAYA2 = new Paragraph(pr.getProperty("FIRMA_RAYA2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            GRUPOS = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            CONTRATISTA = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            NIT = new Paragraph(pr.getProperty("NIT"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            LINEA = new Paragraph(pr.getProperty("LINEA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            RESOLUCION = new Paragraph(pr.getProperty("RESOLUCION"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            RESOLUCION_EX = new Paragraph(pr.getProperty("FECHA"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            RESOLUCION_EX3 = new Paragraph(pr.getProperty("INTERVALO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            RESOLUCION_EX4 = new Paragraph(pr.getProperty("CODIGO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            RESOLUCION_EX5 = new Paragraph(pr.getProperty("AVENIDA"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            AUTORIZADO_EXT = new Paragraph(tiquete.getAutorizado(), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            AUTORIZADO = new Paragraph(pr.getProperty("AUTORIZADO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            REPRESENTANTE = new Paragraph(pr.getProperty("REPRESENTANTE"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
            FACTURA = new Paragraph(pr.getProperty("FACTURA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            FACTURA_EX = new Paragraph(myStart[0] + " No " + tiquete.getId(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            FECHA = new Paragraph("Fecha Ini: ", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            try {
                System.out.println("fecha es: tiquete.getFecha()  " + tiquete.getFecha());
                FECHA_EX = new Paragraph("" + (tiquete.getFecha()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            } catch (RuntimeException e) {
                System.out.println("fecha es: tiquete.getFecha()  " + tiquete.getFecha());
            }
            FECHA_END = new Paragraph("Fecha Fin: ", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            FECHA_EX_END = new Paragraph(tiquete.getFechaFin(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            PLACA = new Paragraph(pr.getProperty("PLACA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            PLACA_EX = new Paragraph(tiquete.getPlaca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            MARCA = new Paragraph(pr.getProperty("MARCA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            MARCA_EX = new Paragraph(tiquete.getMarca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            MODELO = new Paragraph(pr.getProperty("MODELO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            MODELO_EX = new Paragraph(tiquete.getModelo(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            ATENDIDO = new Paragraph(pr.getProperty("ATENDIDO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            ATENDIDO_EX = new Paragraph(tiquete.getUsuario().getNombre(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            ORIGEN = new Paragraph("Origen:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

            ORIGEN_EX = new Paragraph(myStart[1], new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            OBSERV = new Paragraph("Obser:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            OBSERV_EX = new Paragraph(tiquete.getControl(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

            DESTINO = new Paragraph(pr.getProperty("DESTINO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            tamanio = tiquete.getDestino().length() < 10 ? 10 : 8;
            DESTINO_EX = new Paragraph(tiquete.getDestino(), new Font(Font.FontFamily.TIMES_ROMAN, tamanio, Font.BOLD));
            PASAJERO = new Paragraph(pr.getProperty("PASAJERO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            PASAJERO_EX = new Paragraph(tiquete.getPasajero(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            NOMBRE_CLI = new Paragraph("Nombre Contratante", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            NIT_CLI = new Paragraph(Helper.getString(tiquete.getNombre()) + " " + Helper.getString(tiquete.getNitCliente()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            TOTAL = new Paragraph(pr.getProperty("TOTAL"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            TOTAL_EX = new Paragraph("$ " + tiquete.getValor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            CONDUCTOR = new Paragraph(pr.getProperty("CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            CONDUCTOR_EX = new Paragraph(tiquete.getConductor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            ESPACIO = new Paragraph("", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            SENOR = new Paragraph(pr.getProperty("SENOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            COPIA_USUARIO = new Paragraph(pr.getProperty("COPIA_USUARIO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            COPIA_CONDUCTOR = new Paragraph(pr.getProperty("COPIA_CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
            COPIA_EMPRESA = new Paragraph(reimpresion, new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

            addCopyCliente_qr(tiquete, COPIA_EMPRESA, true);

            document.close();

        } catch (Exception e) {
            System.out.println("no fue posible crear el doc " + e.getCause());
        }
        return baos.toByteArray();
    }

    public byte[] generarFactura(Ticket tiquete, Sitios sitio, Properties pr, String reimpresion) throws Exception {

        document.open();
        document.setMargins(1,5,10,10);
        try {
            System.out.println("busncoando logo............");
            //LOGO = Image.getInstance(getImage("/images/logo_viajes_imperial.jpg"));//
            LOGO = Image.getInstance(getImage("/images/nv_viajes.jpg"));//nv_viajes.png
            System.out.println("logo encontrado.............");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String des = tiquete.getDestino();
            if (tiquete.getDestino().length() > 20) {
                des = tiquete.getDestino().substring(0, 20);
            }

            tiquete.setDestino(des);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            IMG_FIRMA_REP_LEGAL = Image.getInstance(getImage("/images/firma.jpg"));
            IMG_VIAJES_IMP = Image.getInstance(getImage("/images/logo_viajes_imperial.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //hsa LOGO_COMPANI = Image.getInstance(getImage("/images/" + tiquete.getLogEmpreVehiculo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //hsa   LOGO_FIRMA = Image.getInstance(getImage("/images/" + tiquete.getLogFirmaVehiculo()));

            String sitioDir = sitio.getDireccion();
            String myStart[] = sitioDir.split("\\|");
            int size = 6;
            int sizeMin = 4;

            CONTRATO = new Paragraph(pr.getProperty("CONTRATO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ACEPTO = new Paragraph(pr.getProperty("ACEPTO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ACEPTO2 = new Paragraph(pr.getProperty("ACEPTO2"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FIRMA = new Paragraph(pr.getProperty("FIRMA"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FIRMA_RAYA = new Paragraph(pr.getProperty("FIRMA_RAYA"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FIRMA2 = new Paragraph(pr.getProperty("FIRMA2"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FIRMA_RAYA2 = new Paragraph(pr.getProperty("FIRMA_RAYA2"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            GRUPOS = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            CONTRATISTA = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            NIT = new Paragraph(pr.getProperty("NIT"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            LINEA = new Paragraph(pr.getProperty("LINEA"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            RESOLUCION = new Paragraph(pr.getProperty("RESOLUCION"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            RESOLUCION_EX = new Paragraph(pr.getProperty("FECHA"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            RESOLUCION_EX3 = new Paragraph(pr.getProperty("INTERVALO"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            RESOLUCION_EX4 = new Paragraph(pr.getProperty("CODIGO"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            RESOLUCION_EX5 = new Paragraph(pr.getProperty("AVENIDA"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            AUTORIZADO_EXT = new Paragraph(tiquete.getAutorizado(), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            AUTORIZADO = new Paragraph(pr.getProperty("AUTORIZADO"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            REPRESENTANTE = new Paragraph(pr.getProperty("REPRESENTANTE"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            FACTURA = new Paragraph(pr.getProperty("FACTURA"), new Font(Font.FontFamily.TIMES_ROMAN, sizeMin, Font.BOLD));
            FACTURA_EX = new Paragraph(myStart[0] + " No " + tiquete.getId(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FECHA = new Paragraph("Fecha Ini: ", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            try {
                System.out.println("fecha es: tiquete.getFecha()  " + tiquete.getFecha());
                FECHA_EX = new Paragraph("" + Helper.toDateYYMMDDHHMM(tiquete.getFecha()), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            } catch (RuntimeException e) {
                System.out.println("fecha es: tiquete.getFecha()  " + tiquete.getFecha());
            }
            FECHA_END = new Paragraph("Fecha Fin: ", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            FECHA_EX_END = new Paragraph(tiquete.getFechaFin(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            PLACA = new Paragraph(pr.getProperty("PLACA"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            PLACA_EX = new Paragraph(tiquete.getPlaca(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            MARCA = new Paragraph(pr.getProperty("MARCA"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            MARCA_EX = new Paragraph(tiquete.getMarca(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            MODELO = new Paragraph(pr.getProperty("MODELO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            MODELO_EX = new Paragraph(tiquete.getModelo(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ATENDIDO = new Paragraph(pr.getProperty("ATENDIDO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ATENDIDO_EX = new Paragraph(tiquete.getUsuario().getNombre(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ORIGEN = new Paragraph("Origen:", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));

            ORIGEN_EX = new Paragraph(myStart[1], new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            OBSERV = new Paragraph("Obser:", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            OBSERV_EX = new Paragraph(tiquete.getControl(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));

            DESTINO = new Paragraph(pr.getProperty("DESTINO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            tamanio = tiquete.getDestino().length() < 10 ? 10 : 8;
            DESTINO_EX = new Paragraph(tiquete.getDestino(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            PASAJERO = new Paragraph(pr.getProperty("PASAJERO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            PASAJERO_EX = new Paragraph(tiquete.getPasajero(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            NOMBRE_CLI = new Paragraph("Nombre Contratante", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            NIT_CLI = new Paragraph(Helper.getString(tiquete.getNombre()) + " " + Helper.getString(tiquete.getNitCliente()), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            TOTAL = new Paragraph(pr.getProperty("TOTAL"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            TOTAL_EX = new Paragraph("$ " + tiquete.getValor(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            CONDUCTOR = new Paragraph(pr.getProperty("CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            CONDUCTOR_EX = new Paragraph(tiquete.getConductor(), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            ESPACIO = new Paragraph("", new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            SENOR = new Paragraph(pr.getProperty("SENOR"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            COPIA_USUARIO = new Paragraph(pr.getProperty("COPIA_USUARIO"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            COPIA_CONDUCTOR = new Paragraph(pr.getProperty("COPIA_CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            COPIA_EMPRESA = new Paragraph(reimpresion, new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));

            addCopyCliente(tiquete, COPIA_EMPRESA, true);
            //addCopyCliente(tiquete, COPIA_EMPRESA, false);

            //addCopyDriver(tiquete, COPIA_USUARIO);
            document.close();

        } catch (Exception e) {
            System.out.println("no fue posible crear el doc " + e.getCause());
        }
        return baos.toByteArray();
    }

    public byte[] generarFacturaAvianca(Ticket tiquete, Properties pr, String reimpresion, String img) throws Exception {

        document.open();
        try {
            LOGO = Image.getInstance(getImage("/images/logo_viajes_imperial.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String des = tiquete.getDestino();
            if (tiquete.getDestino().length() > 20) {
                des = tiquete.getDestino().substring(0, 20);
            }

            tiquete.setDestino(des);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            IMG_FIRMA_REP_LEGAL = Image.getInstance(getImage("/images/firma.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LOGO_COMPANI = Image.getInstance(getImage("/images/" + tiquete.getLogEmpreVehiculo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            LOGO_FIRMA = Image.getInstance(getImage("/images/" + tiquete.getLogFirmaVehiculo()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        CONTRATO = new Paragraph(pr.getProperty("CONTRATO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ACEPTO = new Paragraph(pr.getProperty("ACEPTO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ACEPTO2 = new Paragraph(pr.getProperty("ACEPTO2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FIRMA = new Paragraph(pr.getProperty("FIRMA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FIRMA_RAYA = new Paragraph(pr.getProperty("FIRMA_RAYA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FIRMA2 = new Paragraph(pr.getProperty("FIRMA2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FIRMA_RAYA2 = new Paragraph(pr.getProperty("FIRMA_RAYA2"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        GRUPOS = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        CONTRATISTA = new Paragraph(pr.getProperty("GRUPOS"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        NIT = new Paragraph(pr.getProperty("NIT"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        LINEA = new Paragraph(pr.getProperty("LINEA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        RESOLUCION = new Paragraph(pr.getProperty("RESOLUCION"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        RESOLUCION_EX = new Paragraph(pr.getProperty("FECHA"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        RESOLUCION_EX3 = new Paragraph(pr.getProperty("INTERVALO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        RESOLUCION_EX4 = new Paragraph(pr.getProperty("CODIGO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        RESOLUCION_EX5 = new Paragraph(pr.getProperty("AVENIDA"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        AUTORIZADO_EXT = new Paragraph(tiquete.getAutorizado(), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        AUTORIZADO = new Paragraph(pr.getProperty("AUTORIZADO"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        REPRESENTANTE = new Paragraph(pr.getProperty("REPRESENTANTE"), new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD));
        FACTURA = new Paragraph(pr.getProperty("FACTURA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FACTURA_EX = new Paragraph(pr.getProperty("TAX") + tiquete.getId(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FECHA = new Paragraph("Fecha: ", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        FECHA_EX = new Paragraph("" + Helper.toDateYYMMDDHHMM(tiquete.getFecha()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        PLACA = new Paragraph(pr.getProperty("PLACA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        PLACA_EX = new Paragraph(tiquete.getPlaca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        MARCA = new Paragraph(pr.getProperty("MARCA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        MARCA_EX = new Paragraph(tiquete.getMarca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        MODELO = new Paragraph(pr.getProperty("MODELO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        MODELO_EX = new Paragraph(tiquete.getModelo(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ATENDIDO = new Paragraph(pr.getProperty("ATENDIDO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ATENDIDO_EX = new Paragraph(tiquete.getUsuario().getNombre(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ORIGEN = new Paragraph("Origen:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

        ORIGEN_EX = new Paragraph(pr.getProperty("ORIGEN"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        OBSERV = new Paragraph("Obser:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        OBSERV_EX = new Paragraph(tiquete.getControl(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

        DESTINO = new Paragraph(pr.getProperty("DESTINO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        tamanio = tiquete.getDestino().length() < 10 ? 10 : 8;
        DESTINO_EX = new Paragraph(tiquete.getDestino(), new Font(Font.FontFamily.TIMES_ROMAN, tamanio, Font.BOLD));
        PASAJERO = new Paragraph(pr.getProperty("PASAJERO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        PASAJERO_EX = new Paragraph(tiquete.getPasajero(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        NOMBRE_CLI = new Paragraph("Nombre Contratante", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        NIT_CLI = new Paragraph(Helper.getString(tiquete.getNombre()) + " " + Helper.getString(tiquete.getNitCliente()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        TOTAL = new Paragraph(pr.getProperty("TOTAL"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        TOTAL_EX = new Paragraph("$ " + tiquete.getValor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        CONDUCTOR = new Paragraph(pr.getProperty("CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        CONDUCTOR_EX = new Paragraph(tiquete.getConductor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        ESPACIO = new Paragraph("", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        SENOR = new Paragraph(pr.getProperty("SENOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        COPIA_USUARIO = new Paragraph(pr.getProperty("COPIA_USUARIO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        COPIA_CONDUCTOR = new Paragraph(pr.getProperty("COPIA_CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        COPIA_EMPRESA = new Paragraph(pr.getProperty("COPIA_EMRESA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

        addCopyCliente(tiquete, COPIA_EMPRESA, true);
        addCopyCliente(tiquete, COPIA_CONDUCTOR, false);
        addCopyDriver(tiquete, COPIA_USUARIO);
        // addCopyCliente(tiquete, pr, reimpresion, tLogo_1);
        document.close();
        try {
            // OutputStream rutaArchivo = new FileOutputStream("C:/temp/pdf_.pdf");
            // baos.writeTo(rutaArchivo);
        } catch (RuntimeException e) {
        }
        return baos.toByteArray();
    }

    private PdfWriter getPdfWriter(Document doc, OutputStream os) {

        PdfWriter out = null;

        try {
            out = PdfWriter.getInstance(doc, os);
        } catch (Exception ex) {
        }

        return out;
    }

    public PdfPCell obtenerCellColspamBanner(Object contendio) {
        PdfPCell cell = null;

        if (contendio instanceof Image) {
            Image img = (Image) contendio;
            img.scaleToFit(120, 72);
            cell = new PdfPCell(img);

            cell.addElement(img);

        } else if (contendio instanceof Paragraph) {
            Paragraph txt = (Paragraph) contendio;
            cell = new PdfPCell(txt);
        } else {
            cell = new PdfPCell(new Paragraph(" "));
        }
        cell.setColspan(3);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    public PdfPCell obtenerCellColspamBanner2(Object contendio) {
        PdfPCell cell = null;

        if (contendio instanceof Image) {
            Image img = (Image) contendio;
            img.scaleToFit(120, 72);
            cell = new PdfPCell(img);

            cell.addElement(img);

        } else if (contendio instanceof Paragraph) {
            Paragraph txt = (Paragraph) contendio;
            cell = new PdfPCell(txt);
        } else {
            cell = new PdfPCell(new Paragraph(" "));
        }
        cell.setColspan(3);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    public PdfPCell obtenerCellColspamBannerLeft(Object contendio) {
        PdfPCell cell = null;

        if (contendio instanceof Image) {
            Image img = (Image) contendio;
            cell = new PdfPCell(img);
            cell.addElement(img);
        } else if (contendio instanceof Paragraph) {
            Paragraph txt = (Paragraph) contendio;
            cell = new PdfPCell(txt);
        } else {
            cell = new PdfPCell(new Paragraph(" "));
        }
        cell.setColspan(3);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    public PdfPCell obtenerCellColspam2(Paragraph contendio) {
        return obtenerCell(2, contendio);

    }

    public PdfPCell obtenerCell(Paragraph contendio) {
        return obtenerCell(0, contendio);

    }

    public PdfPCell obtenerCell(int colSpam, Paragraph contendio) {
        PdfPCell cell = null;
        if (colSpam == 3) {
            cell = new PdfPCell(contendio);
        } else if (colSpam == 2) {
            cell = new PdfPCell(contendio);
        } else {
            cell = new PdfPCell(contendio);
        }
        cell.setColspan(colSpam);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
    public PdfPCell obtenerCellCenter(int colSpam, Paragraph contendio) {
        PdfPCell cell = null;
        if (colSpam == 3) {
            cell = new PdfPCell(contendio);
        } else if (colSpam == 2) {
            cell = new PdfPCell(contendio);
        } else {
            cell = new PdfPCell(contendio);
        }
        cell.setColspan(colSpam);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    protected Image getImage(String url) {

        Image img = null;

        try {

            ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
            java.awt.Image image = imageIcon.getImage();
            img = Image.getInstance(writer, image, 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (BadElementException ex) {
            ex.printStackTrace();
        }

        return img;
    }

    int sizeWord() {
        return 6;
    }

    public void print() {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
        FileInputStream psStream = null;
        try {
            psStream = new FileInputStream("c:\\test.pdf");
        } catch (FileNotFoundException ffne) {
            ffne.printStackTrace();
        }
        if (psStream == null) {
            return;
        }
        if (services.length > 0) {
            PrintService myService = null;
            for (PrintService service : services) {
                System.out.println(service.getName());
                if (service.getName().contains("my printer")) {
                    myService = service;
                    break;
                }
            }
            DocPrintJob printJob = myService.createPrintJob();
            Doc document = new SimpleDoc(psStream, flavor, null);
            try {
                printJob.print(document, null);
            } catch (PrintException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("No PDF printer available.");
        }
    }

    public byte[] generarCierreFactura(Cierre tk, Properties pr) throws Exception {
        document.open();

        Image LOGO = null;
        try {
            LOGO = Image.getInstance(getImage("/images/logo_viajes_imperial.jpg"));

            Paragraph VIAJES = new Paragraph(pr.getProperty("VIAJES"), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph NIT = new Paragraph(pr.getProperty("NIT"), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph AV = new Paragraph(pr.getProperty("AVENIDA"), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            String ini = "" + tk.getInicio();
            String fin = "" + "" + tk.getValor();
            Paragraph TEXT_INICIO = new Paragraph("INICIO : ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph INICIO = new Paragraph(ini, new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph TEXT_CANTIDAD = new Paragraph("TIQUETES : ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph CANTIDAD = new Paragraph("" + tk.getCantidad(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph FIN = new Paragraph(tk.getFin().toString(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_TOTAL_ANULACIONES = new Paragraph("ANULACIONES :", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_CANT_ANUALCONES = new Paragraph("" + tk.getCantidadAnuladas(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph TEX_TOTAL_VALOR_ANULACIONES = new Paragraph("VALOR ANUALADO :", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_CANT__VALOR_ANUALCONES = new Paragraph("" + tk.getValorAnualdos(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_FIN = new Paragraph("FINAL :", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph VALOR = new Paragraph(fin, new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_VALOR = new Paragraph("TOTAL: ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_TOTAL = new Paragraph("" + tk.getValorTotal(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_VALORE = new Paragraph("EFECTIVO: ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_VALORE = new Paragraph("" + tk.getValorE(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_VALORD = new Paragraph("DEBITO: ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_VALORD = new Paragraph("" + tk.getValorD(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_VALORC = new Paragraph("CREDITO: ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph VALOR_VALORC = new Paragraph("" + tk.getValorC(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_SITIO = new Paragraph("EMITIDO EN : ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph SITIO = new Paragraph("" + tk.getSitioNombre(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph TEXT_USUARIO = new Paragraph("CAJERO: ", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph USUARIO = new Paragraph("" + tk.getUsuarioNombre(), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            Paragraph TEXT_FECHA = new Paragraph("FECHA:", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));
            Paragraph FECHA = new Paragraph("" + (new Helper().getColCurrentTime()), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

            PdfPTable cierre = new PdfPTable(1);

            cierre.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            cierre.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cierre.addCell("\n");

            cierre.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            cierre.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cierre.addCell(LOGO);

            document.add(cierre);

            PdfPTable table = new PdfPTable(1);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(VIAJES);
            table.addCell(NIT);
            table.addCell(AV);
            document.add(table);

            PdfPTable conta = new PdfPTable(2);
            conta.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_INICIO);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(INICIO);

            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_TOTAL_ANULACIONES);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_CANT_ANUALCONES);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEX_TOTAL_VALOR_ANULACIONES);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_CANT__VALOR_ANUALCONES);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_CANTIDAD);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(CANTIDAD);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_FIN);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(FIN);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_VALOR);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_TOTAL);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_VALORE);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_VALORE);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_VALORD);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_VALORD);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_VALORC);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(VALOR_VALORC);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_FECHA);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(FECHA);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            conta.addCell(TEXT_USUARIO);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.LEFT);
            conta.addCell(USUARIO);
            conta.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            document.add(conta);

            PdfPTable table2 = new PdfPTable(1);
            table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table2.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(new Paragraph("@estion de Tecnologia RTA - 2014 Tel 4202600 Ext. 159", new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD)));

            document.add(table2);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();

    }

    public static Date fechaFinal(Date fecha) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            cal.add(Calendar.HOUR, 1);
            return cal.getTime();

        } catch (Exception e) {
            e.printStackTrace();
            return fecha;
        }
    }

    public static void main(String[] args) throws Exception {

        try {

        } catch (Exception ex) {

        }

    }
}
