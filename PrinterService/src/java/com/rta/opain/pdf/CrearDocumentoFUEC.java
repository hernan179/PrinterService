/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rta.opain.delegate.dto.Cierre;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.delegate.tools.Helper;
import static com.rta.opain.delegate.tools.LogTest.rw;
import com.rta.opain.domain.CajerosAero;
import com.rta.opain.domain.Sitios;
import java.io.BufferedInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.GregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.ImageIcon;

public class CrearDocumentoFUEC {

    //Document document = new Document(PageSize.LETTER, -20, -20, 39, 40);// mas grande 
    //    Document document = new Document(PageSize.LETTER, 2, 0, -10, 0);// mas grande 
    //   Document document = new Document(PageSize.A4);// mas grande 
    Document document = new Document(PageSize.LETTER, 15, -30, 30, 2);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    static SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat smdf2 = new SimpleDateFormat("yyyy-MM-dd");//2013-11-22 10:44:00
    SimpleDateFormat formatoMes = new SimpleDateFormat("MMMMMMM");
    SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
    Image LOGO;
    Image LOGO_COMPANI;
    Image LOGO_FIRMA;
    Image IMG_FIRMA_REP_LEGAL;
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
            if (isCompany) {
                tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            }

            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner(LOGO));
            tLogo_1.addCell(obtenerCellColspamBanner(CONTRATO));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(ACEPTO));
            //tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner("\n"));

            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA_RAYA));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA));
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
            tLogo_1.addCell(obtenerCellColspamBanner(IMG_FIRMA_REP_LEGAL));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA_RAYA));
            tLogo_1.addCell(obtenerCellColspamBannerLeft(FIRMA2));
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
//            tLogo_1.addCell(obtenerCellColspamBanner("\n"));
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

    public static void main(String[] args) throws Exception {
        Ticket tiquete = new Ticket();
        CajerosAero usuario = new CajerosAero();
        usuario.setNombre("hotel cosmos");
        tiquete.setUsuario(usuario);
        tiquete.setId("1235156");
        tiquete.setNitCliente("12198468");
        tiquete.setDestino("hotel x");
        tiquete.setNombre("usaurio del sistema");

        tiquete.setInfoRuta("desde aero hasta hotel");

        String consecutivo = "0x11";

        //new CrearDocumentoFUEC().generarFactura2(tiquete, null, "Impreso", tiquete.getUsuario().getUsuario(), consecutivo, null);
        new CrearDocumentoFUEC().generarFactura2(tiquete, null, "reimpreso", "usuarioSistema", consecutivo, "fuec");
    }

    public byte[] generarFactura2(Ticket tiquete, Properties pr, String reimpreso, String usuarioSistema, String template, String fuec) throws Exception {
        if (pr == null) {
            pr = new Properties();
            //pr.load(new FileReader(new Helper().getCurrentWorkingDirectory()+"/src/java/com/rta/opain/pdf/factura.properties"));
            pr.load(new FileReader("/opt/compilados/factura.properties"));

            InputStream data = this.getClass().getResourceAsStream("factura.properties");
        }

        System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");

        document.open();
        Font catFont6 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
        if (tiquete == null) {
            Image imgFondo = new Helper().getImage("/tools/images/plantilla final.jpg", writer);
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            writer.getDirectContentUnder().addImage(imgFondo, width, 0, 0, height, 0, 0);
            Paragraph p2 = new Paragraph("\n\n", catFont6);
            document.add(p2);
            document.close();
            FileOutputStream fos = new FileOutputStream("/opt/compilados/fac_" + fuec + ".pdf");
            fos.write(baos.toByteArray());
            fos.close();

            return baos.toByteArray();
        } else {
            try {
                Font catFont26 = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD);
                Font catFont14 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                Font catFont15 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
                Font catFont12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                Font catFont13 = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
                Font catFont11 = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
                Font catFont10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
                Font catFont8 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
                Font catFont9 = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
                Font catFont7 = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);

                if (template != null) {
                    Image imgFondo = Image.getInstance(getImage("/opt/compilados/images/plantilla final.jpg"));
                    if (template.equals("conTemplate_body")) {
                        imgFondo = Image.getInstance(getImage("/opt/compilados/images/plantilla final_body.jpg"));
                    }

                    float width = document.getPageSize().getWidth();
                    float height = document.getPageSize().getHeight();
                    rw("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + imgFondo + "&&&&&&&&&&&&&&&&&&&&&&&");

                    writer.getDirectContentUnder().addImage(imgFondo, width, 0, 0, height, 0, 0);

                    Paragraph p2 = new Paragraph("\n\n", catFont6);
                    document.add(p2);
                }

                Paragraph p1 = new Paragraph("\n\n\n", catFont6);
                document.add(p1);
                PdfPTable banner = new PdfPTable(1);
                banner.getDefaultCell().setBorder(0);
                PdfPCell cellBanner = new PdfPCell(new Phrase((" "), catFont6));
                cellBanner.setFixedHeight(60);
                cellBanner.setBorder(0);
                banner.addCell(cellBanner);
                document.add(banner);

                PdfPTable banner2 = new PdfPTable(1);
                banner2.getDefaultCell().setBorder(0);
                PdfPCell cellBanner2 = new PdfPCell(new Phrase((" "), catFont11));
                cellBanner2.setFixedHeight(30);
                cellBanner2.setBorder(0);
                banner2.addCell(cellBanner2);
                document.add(banner2);

                PdfPTable tableCUNA = new PdfPTable(1);
                tableCUNA.getDefaultCell().setBorder(0);
                PdfPCell celICUNA = new PdfPCell(new Phrase("value", catFont10));
                celICUNA.setFixedHeight(21);
                tableCUNA.addCell(celICUNA);

                PdfPTable tableNo = new PdfPTable(8);
                tableNo.getDefaultCell().setBorder(0);
                tableNo.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                tableNo.setWidthPercentage(100f);
                String NUM = pr.getProperty("NUMERO_LARGO");
                String ICA = pr.getProperty("RESOLUCION") + "" + pr.getProperty("RESOLUCION2");

                String DIAN = pr.getProperty("FECHA") + "\n" + pr.getProperty("INTERVALO");

                //PdfPCell celICA = new PdfPCell(new Phrase("\n" + ICA, catFont10));
                PdfPCell celICA = new PdfPCell(new Phrase("\n" + "", catFont10));
                celICA.setFixedHeight(40);

                celICA.setColspan(3);
                celICA.setBorder(0);
                tableNo.addCell(celICA);

                String numeroInicio = pr.getProperty("NUMERO_INICIO");
                String digitos = pr.getProperty("NUMERO_DIGITOS");
                String numeroFac = tiquete.getId().toString();

                PdfPCell _1cell = new PdfPCell(new Phrase("\n\n" + NUM + " " + fuec, catFont10));
                _1cell.setFixedHeight(40);
                _1cell.setColspan(2);
                _1cell.setBorder(0);

                tableNo.addCell(_1cell);

                PdfPCell cellDIAN = new PdfPCell(new Phrase("", catFont10));
                cellDIAN.setFixedHeight(40);
                cellDIAN.setBorder(0);
                cellDIAN.setColspan(3);
                tableNo.addCell(cellDIAN);
                document.add(tableNo);

                PdfPTable RAZON = new PdfPTable(5);
                RAZON.getDefaultCell().setBorder(0);
                RAZON.addCell(new Paragraph("", catFont11));

                PdfPCell cell4 = new PdfPCell(new Phrase(pr.getProperty("RAZON_SOCIAL"), catFont10));
                cell4.setColspan(3);
                cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell4.setBorder(0);
                cell4.setFixedHeight(15);
                RAZON.addCell(cell4);
                PdfPCell cellNit = new PdfPCell(new Phrase("" + pr.getProperty("NIT"), catFont10));
                cellNit.setBorder(0);
                cellNit.setFixedHeight(15);
                cellNit.setHorizontalAlignment(Element.ALIGN_LEFT);//out

                RAZON.addCell(cellNit);
                document.add(RAZON);

                PdfPTable CONTRATO_NO = new PdfPTable(5);
                CONTRATO_NO.getDefaultCell().setBorder(0);
                PdfPCell cell5 = new PdfPCell(new Phrase("" + tiquete.getId(), catFont10));
                cell5.setColspan(4);
                cell5.setBorder(0);
                cell5.setFixedHeight(18);
                CONTRATO_NO.addCell(new Paragraph("", catFont11));
                CONTRATO_NO.addCell(cell5);

                PdfPTable CONTRATANTE = new PdfPTable(5);//8
                CONTRATANTE.getDefaultCell().setBorder(0);
                CONTRATANTE.addCell(new Paragraph("", catFont11));
                PdfPCell cell6 = new PdfPCell(new Phrase(tiquete.getNombre(), catFont10));
                cell6.setColspan(3);
                cell6.setBorder(0);
                cell6.setFixedHeight(19);

                CONTRATANTE.addCell(cell6);
                CONTRATANTE.addCell(new Paragraph(tiquete.getNitCliente(), catFont10));//tiquete.getNit()

                PdfPTable OBJETO = new PdfPTable(8);
                OBJETO.getDefaultCell().setBorder(0);
                String objeto = pr.getProperty("CONTRATO");
                PdfPCell cell7 = new PdfPCell(new Phrase("", catFont10));
                //cell7.setFixedHeight(19);
                cell7.setColspan(2);
                cell7.setBorder(0);
                OBJETO.addCell(cell7);
                PdfPCell cell72 = new PdfPCell(new Phrase(objeto, catFont10));
                cell72.setColspan(6);
                //cell72.setFixedHeight();
                cell72.setBorder(0);
                OBJETO.addCell(cell72);

                PdfPTable ESPACION = new PdfPTable(5);
                ESPACION.getDefaultCell().setBorder(0);
                PdfPCell cell8 = new PdfPCell(new Phrase("", catFont10));
                cell8.setColspan(5);
                cell8.setFixedHeight(17);
                cell8.setBorder(0);
                ESPACION.addCell(cell8);

                document.add(CONTRATO_NO);
                document.add(CONTRATANTE);
                document.add(OBJETO);
                document.add(ESPACION);

                PdfPTable RECORRIDO = new PdfPTable(5);
                RECORRIDO.getDefaultCell().setBorder(0);

                PdfPCell cell91 = new PdfPCell(new Phrase("", catFont10));
                cell91.setColspan(2);
                //  cell91.setFixedHeight(10);
                cell91.setBorder(0);
                RECORRIDO.addCell(cell91);

                PdfPCell cell92 = new PdfPCell(new Phrase("", catFont10));
                cell92.setColspan(3);
                cell92.setFixedHeight(10);
                cell92.setBorder(0);

                RECORRIDO.addCell(cell92);
                document.add(RECORRIDO);

                PdfPTable VALOR = new PdfPTable(5);
                VALOR.getDefaultCell().setBorder(0);
                //PdfPCell cellValor = new PdfPCell(new Phrase("Valor: " + String.format("$%1$,.2f", new Double(tiquete.getValor())), catFont11));
                PdfPCell cellValor = new PdfPCell(new Phrase(""));
                cellValor.setColspan(5);
                cellValor.setFixedHeight(17);
                cellValor.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                cellValor.setBorder(0);
                VALOR.addCell(cellValor);

                PdfPTable TABLE_WAY = new PdfPTable(5);
                TABLE_WAY.getDefaultCell().setBorder(0);
                //hsa PdfPCell WAY = new PdfPCell(new Phrase(tiquete.getOrigen() + " HASTA ".toUpperCase() + tiquete.getDestino().toUpperCase(), catFont10));
                PdfPCell WAY = new PdfPCell(new Phrase(Helper.getString(tiquete.getInfoRuta()).toLowerCase(), catFont10));
                WAY.setColspan(5);
                WAY.setFixedHeight(20);
                WAY.setBorder(0);
                TABLE_WAY.addCell(WAY);

                document.add(TABLE_WAY);
                document.add(VALOR);

                PdfPTable CONVENIO = new PdfPTable(6);
                CONVENIO.getDefaultCell().setBorder(0);
                PdfPCell cell20 = new PdfPCell(new Phrase("", catFont8));//HSA debe ir el nombre de la empresa donde esta trabajando el carro
                cell20.setColspan(3);
                cell20.setFixedHeight(20);
                cell20.setBorder(0);
                CONVENIO.addCell(cell20);

                PdfPCell cell201 = new PdfPCell(new Phrase(tiquete.getEmpresa(), catFont8));//HSA debe ir el nombre de la empresa donde esta trabajando el carro
                cell201.setColspan(3);
                cell201.setFixedHeight(20);
                cell201.setBorder(0);
                CONVENIO.addCell(cell201);

                document.add(CONVENIO);//hsa 

                //document.add(ESPACION);
                PdfPTable VIGENCIA = new PdfPTable(5);
                VIGENCIA.getDefaultCell().setBorder(0);
                PdfPCell cell21 = new PdfPCell(new Phrase("", catFont10));
                cell21.setColspan(5);
                cell21.setFixedHeight(25);
                cell21.setBorder(0);
                VIGENCIA.addCell(cell21);
                document.add(VIGENCIA);

                PdfPTable FECHA1 = new PdfPTable(5);
                FECHA1.getDefaultCell().setBorder(0);
                PdfPCell cell22 = new PdfPCell(new Phrase("  ", catFont10));
                cell22.setColspan(2);
                cell22.setFixedHeight(20);
                cell22.setBorder(0);
                FECHA1.addCell(cell22);

                String value = smdf2.format(Helper.toDateYYYYMMDDHHMM(tiquete.getFecha()));

                String mm = formatoString(formatoMes.format(Helper.toDateYYYYMMDDHHMM(tiquete.getFecha())));
                String dd = "";
                String aa = "";
                String parFecha[] = value.split("-");
                for (int i = 0; i < parFecha.length; i++) {
                    dd = parFecha[2];
                    // mm = parFecha[1];
                    aa = parFecha[0];
                }

                Date fech = fechaFinal(Helper.toDateYYYYMMDDHHMM(tiquete.getFecha()));

                String value2 = smdf2.format(fech);
                String mm2 = formatoString(formatoMes.format(fech));
                String dd2 = "";
                String aa2 = "";
                String parFecha2[] = value2.split("-");
                for (int i2 = 0; i2 < parFecha2.length; i2++) {
                    dd2 = parFecha2[2];
                    //mm2 = parFecha2[1];
                    aa2 = parFecha2[0];
                }
                PdfPCell celDD = new PdfPCell(new Phrase(dd, catFont10));
                celDD.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celDD.setBorder(0);
                celDD.setFixedHeight(20);

                PdfPCell celMM = new PdfPCell(new Phrase(mm, catFont10));
                celMM.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celMM.setBorder(0);
                celMM.setFixedHeight(20);
                PdfPCell celAA = new PdfPCell(new Phrase(aa, catFont10));
                celAA.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celAA.setBorder(0);
                celAA.setFixedHeight(20);

                FECHA1.addCell(celDD);
                FECHA1.addCell(celMM);
                FECHA1.addCell(celAA);
                document.add(FECHA1);

                PdfPTable FECHA2 = new PdfPTable(5);
                FECHA2.getDefaultCell().setBorder(0);
                PdfPCell cell23 = new PdfPCell(new Phrase("", catFont10));
                cell23.setColspan(2);
                cell23.setBorder(0);
                cell23.setFixedHeight(17);
                FECHA2.addCell(cell23);

                PdfPCell celDD2 = new PdfPCell(new Phrase(dd2, catFont10));
                celDD2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celDD2.setBorder(0);

                PdfPCell celMM2 = new PdfPCell(new Phrase(mm2, catFont10));
                celMM2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celMM2.setBorder(0);

                PdfPCell celAA2 = new PdfPCell(new Phrase(aa2, catFont10));
                celAA2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                celAA2.setBorder(0);

                FECHA2.addCell(celDD2);
                FECHA2.addCell(celMM2);
                FECHA2.addCell(celAA2);
                document.add(FECHA2);

                PdfPTable CARACTERISTICAS = new PdfPTable(5);
                CARACTERISTICAS.getDefaultCell().setBorder(0);
                PdfPCell cell24 = new PdfPCell(new Phrase("", catFont10));
                cell24.setColspan(5);
                cell24.setFixedHeight(17);
                cell24.setBorder(0);
                CARACTERISTICAS.addCell(cell24);
                document.add(CARACTERISTICAS);

                document.add(ESPACION);

                PdfPTable CARRO = new PdfPTable(7);
                CARRO.getDefaultCell().setBorder(0);

                PdfPCell PLC = new PdfPCell(new Phrase(tiquete.getPlaca(), catFont10));
                PLC.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                PLC.setBorder(0);
                CARRO.addCell(PLC);

                PdfPCell MDL = new PdfPCell(new Phrase(tiquete.getModelo(), catFont10));
                MDL.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                MDL.setBorder(0);
                MDL.setColspan(2);
                CARRO.addCell(MDL);

                PdfPCell MRC = new PdfPCell(new Phrase(tiquete.getMarca(), catFont10));
                MRC.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                MRC.setBorder(0);
                MRC.setColspan(2);
                CARRO.addCell(MRC);

                PdfPCell CLASE = new PdfPCell(new Phrase(tiquete.getClase(), catFont10));
                CLASE.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                CLASE.setColspan(2);
                CLASE.setBorder(0);
                CARRO.addCell(CLASE);

                document.add(CARRO);

                PdfPTable ESPACION_MINI = new PdfPTable(5);
                ESPACION_MINI.getDefaultCell().setBorder(0);
                PdfPCell cell27 = new PdfPCell(new Phrase("", catFont10));
                cell27.setColspan(5);
                cell27.setFixedHeight(25);
                cell27.setBorder(0);
                ESPACION_MINI.addCell(cell27);

                document.add(ESPACION_MINI);

                PdfPTable DINAMIC_NUMERO_INTERNO = new PdfPTable(5);
                DINAMIC_NUMERO_INTERNO.getDefaultCell().setBorder(0);

                PdfPCell cell281 = new PdfPCell(new Phrase(tiquete.getNumeroInterno(), catFont10));
                cell281.setColspan(2);
                cell281.setBorder(0);
                cell281.setFixedHeight(19);
                cell281.setHorizontalAlignment(Element.ALIGN_CENTER);
                DINAMIC_NUMERO_INTERNO.addCell(cell281);

                PdfPCell cell282 = new PdfPCell(new Phrase(tiquete.getNumeroTarjetaOpe(), catFont10));
                cell282.setColspan(3);
                cell282.setBorder(0);
                cell282.setFixedHeight(19);
                cell282.setHorizontalAlignment(Element.ALIGN_CENTER);
                DINAMIC_NUMERO_INTERNO.addCell(cell282);

                document.add(DINAMIC_NUMERO_INTERNO);

                //  document.add(new Paragraph("\n", catFont6));
                PdfPTable DATOS_CONDUCTOR = new PdfPTable(7);
                DATOS_CONDUCTOR.getDefaultCell().setBorder(0);
                DATOS_CONDUCTOR.addCell("");
                PdfPCell cell30 = new PdfPCell(new Phrase("\n" + tiquete.getNombreConductor1() + " " + tiquete.getApellidoConductor1(), catFont10));
                cell30.setColspan(2);
                cell30.setBorder(0);
                cell30.setFixedHeight(20);
                DATOS_CONDUCTOR.addCell(cell30);
                DATOS_CONDUCTOR.addCell(new Phrase("\n" + tiquete.getCcConductor1(), catFont8));
                PdfPCell cell31 = new PdfPCell(new Phrase("\n" + tiquete.getLicenciaConductor1(), catFont10));
                cell31.setColspan(2);
                cell31.setBorder(0);
                cell31.setFixedHeight(20);
                DATOS_CONDUCTOR.addCell(cell31);
                DATOS_CONDUCTOR.addCell(new Phrase("\n" + tiquete.getVigenciaConductor1(), catFont10));
                document.add(DATOS_CONDUCTOR);

                PdfPTable DATOS_CONDUCTOR2CUNA = new PdfPTable(7);
                PdfPCell cellDATOS_CONDUCTOR2CUNA = new PdfPCell(new Phrase("\n\n", catFont10));
                cellDATOS_CONDUCTOR2CUNA.setColspan(7);
                cellDATOS_CONDUCTOR2CUNA.setBorder(0);
                cellDATOS_CONDUCTOR2CUNA.setFixedHeight(10);
                DATOS_CONDUCTOR2CUNA.addCell(cellDATOS_CONDUCTOR2CUNA);
                document.add(DATOS_CONDUCTOR2CUNA);

                PdfPTable DATOS_CONDUCTOR2 = new PdfPTable(7);
                DATOS_CONDUCTOR2.getDefaultCell().setBorder(0);
                DATOS_CONDUCTOR2.addCell("");
                PdfPCell cell302 = new PdfPCell(new Phrase("\n" + "" + " " + "", catFont10));
                cell302.setColspan(2);
                cell302.setBorder(0);
                cell302.setFixedHeight(20);
                DATOS_CONDUCTOR2.addCell(cell302);
                DATOS_CONDUCTOR2.addCell(new Phrase("\n" + "", catFont10));
                PdfPCell cell312 = new PdfPCell(new Phrase("\n" + "", catFont10));
                cell312.setColspan(2);
                cell312.setBorder(0);
                cell312.setFixedHeight(20);
                DATOS_CONDUCTOR2.addCell(cell312);
                DATOS_CONDUCTOR2.addCell(new Phrase("\n" + "", catFont10));
                document.add(DATOS_CONDUCTOR2);

                PdfPTable DATOS_CONDUCTOR3 = new PdfPTable(7);
                DATOS_CONDUCTOR3.getDefaultCell().setBorder(0);
                DATOS_CONDUCTOR3.addCell("");
                PdfPCell cell323 = new PdfPCell(new Phrase("", catFont10));
                cell323.setColspan(2);
                cell323.setBorder(0);
                cell323.setFixedHeight(30);
                DATOS_CONDUCTOR3.addCell(cell323);
                DATOS_CONDUCTOR3.addCell("");
                PdfPCell cell333 = new PdfPCell(new Phrase("", catFont10));
                cell333.setColspan(2);
                cell333.setBorder(0);
                cell333.setFixedHeight(30);
                DATOS_CONDUCTOR3.addCell(cell333);
                DATOS_CONDUCTOR3.addCell(new Phrase("", catFont10));
                document.add(DATOS_CONDUCTOR3);

                document.add(new Phrase(Helper.getString("\n"), catFont10));

                PdfPTable DATOS_RESPONSABLE = new PdfPTable(7);
                DATOS_RESPONSABLE.getDefaultCell().setBorder(0);
                DATOS_RESPONSABLE.addCell("");
                PdfPCell cell34 = new PdfPCell(new Phrase(Helper.getString(tiquete.getNombreResponsable()), catFont10));
                cell34.setColspan(2);
                cell34.setFixedHeight(15);
                cell34.setBorder(0);
                DATOS_RESPONSABLE.addCell(cell34);
                PdfPCell cell342 = new PdfPCell(new Phrase(Helper.getString(tiquete.getCcResponsable()), catFont10));
                cell342.setColspan(2);
                cell342.setFixedHeight(15);
                cell342.setBorder(0);
                DATOS_RESPONSABLE.addCell(cell342);

                PdfPCell cell35 = new PdfPCell(new Phrase(Helper.getString(tiquete.getTelResponsable()), catFont10));
                cell35.setFixedHeight(15);
                cell35.setBorder(0);
                DATOS_RESPONSABLE.addCell(cell35);

                PdfPCell cell353 = new PdfPCell(new Phrase(Helper.getString(tiquete.getDireccionResponsable()), catFont10));
                cell353.setFixedHeight(15);
                cell353.setBorder(0);
                DATOS_RESPONSABLE.addCell(cell353);

                document.add(DATOS_RESPONSABLE);

                // numeracion automatica
                for (int i = 1; i < 6; i++) {
                    // document.add(new PdfPCell(new Paragraph("\n")));
                    PdfPTable CONSECUTIVO = new PdfPTable(7);
                    CONSECUTIVO.getDefaultCell().setBorder(0);

                    if (i < 5) {
                        PdfPCell cellCONSECUTIVO = new PdfPCell(new Phrase("", catFont10));
                        cellCONSECUTIVO.setFixedHeight(15);
                        cellCONSECUTIVO.setBorder(0);
                        cellCONSECUTIVO.setColspan(2);
                        cellCONSECUTIVO.setHorizontalAlignment(Element.ALIGN_CENTER);
                        CONSECUTIVO.addCell(cellCONSECUTIVO);

                        PdfPCell cellCONSECUTIVO2 = new PdfPCell(new Phrase("", catFont10));
                        cellCONSECUTIVO2.setFixedHeight(15);
                        cellCONSECUTIVO2.setBorder(0);
                        cellCONSECUTIVO2.setColspan(5);
                        cellCONSECUTIVO2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        CONSECUTIVO.addCell(cellCONSECUTIVO2);
                    } else {
                        PdfPCell cellCONSECUTIVO = new PdfPCell(new Phrase(tiquete.getNumeroLargo(), catFont10));
                        cellCONSECUTIVO.setFixedHeight(15);
                        cellCONSECUTIVO.setBorder(0);
                        cellCONSECUTIVO.setColspan(2);
                        cellCONSECUTIVO.setHorizontalAlignment(Element.ALIGN_CENTER);
                        CONSECUTIVO.addCell(cellCONSECUTIVO);

                        PdfPCell cellCONSECUTIVO2 = new PdfPCell(new Phrase("", catFont10));
                        cellCONSECUTIVO2.setFixedHeight(15);
                        cellCONSECUTIVO2.setBorder(0);
                        cellCONSECUTIVO2.setColspan(5);
                        cellCONSECUTIVO2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        CONSECUTIVO.addCell(cellCONSECUTIVO2);
                    }
                    document.add(CONSECUTIVO);
                }

                if (false) {
                    try {
                        LOGO = new Helper().getImage("/opt/compilados/images/logo_viajes_imperial.jpg", writer);
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
                        IMG_FIRMA_REP_LEGAL = new Helper().getImage("/images/firma.jpg", writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        LOGO_COMPANI = new Helper().getImage("/images/" + tiquete.getLogEmpreVehiculo(), writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        LOGO_FIRMA = new Helper().getImage("/images/" + tiquete.getLogFirmaVehiculo(), writer);
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
                    FECHA = new Paragraph("Fecha Ini: ", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    FECHA_EX = new Paragraph(smdf.format(tiquete.getFecha()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    FECHA_END = new Paragraph("Fecha Fin: ", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    FECHA_EX_END = new Paragraph(smdf.format(fechaFinal(new Date())), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    PLACA = new Paragraph(pr.getProperty("PLACA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    PLACA_EX = new Paragraph(tiquete.getPlaca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    MARCA = new Paragraph(pr.getProperty("MARCA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    MARCA_EX = new Paragraph(tiquete.getMarca(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    MODELO = new Paragraph(pr.getProperty("MODELO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    MODELO_EX = new Paragraph(tiquete.getModelo(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    ATENDIDO = new Paragraph(pr.getProperty("ATENDIDO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    ATENDIDO_EX = new Paragraph(usuarioSistema, new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    ORIGEN = new Paragraph("Origen:", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    ORIGEN_EX = new Paragraph(pr.getProperty("ORIGEN"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    DESTINO = new Paragraph(pr.getProperty("DESTINO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    tamanio = tiquete.getDestino().length() < 10 ? 10 : 8;
                    DESTINO_EX = new Paragraph(tiquete.getDestino(), new Font(Font.FontFamily.TIMES_ROMAN, tamanio, Font.BOLD));
                    PASAJERO = new Paragraph(pr.getProperty("PASAJERO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    PASAJERO_EX = new Paragraph(tiquete.getPasajero(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    NOMBRE_CLI = new Paragraph("Nombre Contratante", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    NIT_CLI = new Paragraph(tiquete.getNombre() + " " + tiquete.getNitCliente(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    TOTAL = new Paragraph(pr.getProperty("TOTAL"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    TOTAL_EX = new Paragraph("$ " + tiquete.getValor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    CONDUCTOR = new Paragraph(pr.getProperty("CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    CONDUCTOR_EX = new Paragraph(tiquete.getConductor(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    ESPACIO = new Paragraph("", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    SENOR = new Paragraph(pr.getProperty("SENOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    COPIA_USUARIO = new Paragraph(pr.getProperty("COPIA_USUARIO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    COPIA_CONDUCTOR = new Paragraph(pr.getProperty("COPIA_CONDUCTOR"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
                    COPIA_EMPRESA = new Paragraph(pr.getProperty("COPIA_EMRESA"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));

                    //addCopyCliente(tiquete, COPIA_EMPRESA, true);
                    //addCopyCliente(tiquete, COPIA_CONDUCTOR, false);
                    //addCopyDriver(tiquete, COPIA_USUARIO);
                }

                document.close();
                String path = "/opt/compilados/" + fuec + "_fac_nuevo.pdf";
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(baos.toByteArray());
                fos.close();
            } catch (RuntimeException e) {
                e.printStackTrace();
                rw("Error al generar el pdf " + e.getMessage());
            }
            return baos.toByteArray();
        }
    }

    public byte[] generarFacturaAvianca(Ticket tiquete, Properties pr, String reimpresion, String img) throws Exception {

        document.open();
        try {
            LOGO = new Helper().getImage("/images/logo_viajes_imperial.jpg", writer);
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
            IMG_FIRMA_REP_LEGAL = new Helper().getImage("/images/firma.jpg", writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LOGO_COMPANI = new Helper().getImage("/images/" + tiquete.getLogEmpreVehiculo(), writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            LOGO_FIRMA = new Helper().getImage("/images/" + tiquete.getLogFirmaVehiculo(), writer);
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
        FECHA_EX = new Paragraph(smdf.format(tiquete.getFecha()), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
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
        DESTINO = new Paragraph(pr.getProperty("DESTINO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        tamanio = tiquete.getDestino().length() < 10 ? 10 : 8;
        DESTINO_EX = new Paragraph(tiquete.getDestino(), new Font(Font.FontFamily.TIMES_ROMAN, tamanio, Font.BOLD));
        PASAJERO = new Paragraph(pr.getProperty("PASAJERO"), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        PASAJERO_EX = new Paragraph(tiquete.getPasajero(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        NOMBRE_CLI = new Paragraph("Nombre Contratante", new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
        NIT_CLI = new Paragraph(tiquete.getNombre() + " " + tiquete.getNitCliente(), new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD));
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
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
            LOGO = new Helper().getImage("/images/logo_viajes_imperial.jpg", writer);

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
            Paragraph FECHA = new Paragraph("" + smdf.format(new Helper().getColCurrentTime()), new Font(Font.FontFamily.TIMES_ROMAN, sizeWord(), Font.BOLD));

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

    protected Image getImage(String url) {

        Image img = null;

        try {
            //   ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));

            InputStream is = new BufferedInputStream(new FileInputStream(url));
            java.awt.Image image = ImageIO.read(is);

            //java.awt.Image image = imageIcon.getImage();
            img = Image.getInstance(writer, image, 1);
        } catch (IOException ex) {
            // log.error(ex);
        } catch (BadElementException ex) {
            //log.error(ex);
        }

        return img;
    }

    public static Date fechaFinal(Date fecha) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha); // Configuramos la fecha que se recibe
            calendar.add(Calendar.DAY_OF_YEAR, 2);  // numero de días a añadir, o restar en caso de días<0
            return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        } catch (Exception e) {
//            e.printStackTrace();
            return fecha;
        }
    }

    public static Date restarHoras(Date fecha, int horas) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha); // Configuramos la fecha que se recibe
            calendar.add(Calendar.HOUR_OF_DAY, -horas);  // numero de días a añadir, o restar en caso de días<0
            return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        } catch (Exception e) {
//            e.printStackTrace();
            return fecha;
        }
    }

    public static String formatoString(String cadena) {
        char first = Character.toUpperCase(cadena.charAt(0));
        return first + cadena.substring(1);
    }

}
