package com.aero.aplication.docs;

//import co.com.rta.util.DateTools;
//import co.com.rta.util.LoggerFuec;
import com.aero.aplication.dto.DatosFuec;
import com.aero.aplication.dto.InitHelper;
import com.aero.aplication.dto.Persona;
import com.aero.aplication.dto.Ticket;
import com.aero.aplication.tools.DateTools;
import com.aero.aplication.tools.Helper;
import static com.aero.aplication.tools.Helper.rw;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuecTool {

    private static DateTools dateTools = new DateTools();
    Document document = new Document(PageSize.LETTER);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);

    static public void main(String[] args) {
        FuecTool fuecTool = new FuecTool();
        // byte[] doc = fuecTool.crearFuec(fuecTool.settingDatosFuec());

        String path = new InitHelper().getCurrentWorkingDirectory() + "/01_fac_nuevo.pdf";
        System.out.println("file: " + path);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path);
//            fos.write(doc);
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(FuecTool.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public FuecTool() {
    }

    private PdfWriter getPdfWriter(Document doc, OutputStream os) {

        PdfWriter out = null;

        try {
            out = PdfWriter.getInstance(doc, os);
        } catch (Exception ex) {
        }

        return out;
    }

    public byte[] crearFuec(Ticket datosFuec, String path) {

        String archFuec;
        String archivoFuecFinal;
        Image imgMarca;
        Image imagenMin;
        Image imagenImp;
        Image imgFirma;
        Image imgSuperIntend;

        System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");

        document.open();

        try {

            // archFuec = "/opt/fuec/FuecSin_" + datosFuec.getPlaca() + "_" + new DateTools().dateToStringyyyyMMddHHmm(new Date()) + ".pdf";
            // archivoFuecFinal = "/opt/fuec/Fuec_" + datosFuec.getPlaca() + "_" + new DateTools().dateToStringyyyyMMddHHmm(new Date()) + ".pdf";
            imgMarca = new Helper().getImage("/images/img/marcaAguaViajes.png", writer);

            imagenMin = new Helper().getImage("/images/img/MinTransporte.png", writer);
            imagenImp = new Helper().getImage("/images/img/Imperial.png", writer);

            imgFirma = new Helper().getImage("/images/img/FIRMA_SELLO.png", writer);
            //imgSuperIntend = new Helper().getImage("/images/img/SuperInten.png", writer);
            imgSuperIntend = new Helper().getImage("/images/img/FIRMA_SELLO.png", writer);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell(crearCeldaImg(imagenMin, 3, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            table.addCell(crearCeldaImg(imagenImp, 2, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            /*PdfPCell cl = new PdfPCell(new Paragraph(" \n \n \n \n "));
             cl.setFixedHeight(11f);
             table.addCell(cl);
             table.addCell(cl);*/

            table.addCell(crearCelda("\n\n\n\n\n\n\n\nFORMATO ÚNICO DEL EXTRACTO DEL SERVICIO PÚBLICO DE TRANSPORTE "
                    + "TERRESTRE AUTOMOTOR ESPECIAL No. " + datosFuec.getNumeroLargo(), 5, 3, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda("Razón Social:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Viajes Imperial S.A.S", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("Nit:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("830087404-7", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Contrato No:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getId().toString(), 4, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Contratante:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            if(datosFuec.getComprob() != null && datosFuec.getComprob().equals("VAI")){
            table.addCell(crearCelda("PRICESMART Y ", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));
            }else{
            table.addCell(crearCelda(datosFuec.getNombre(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));    
            }
            table.addCell(crearCelda("Nit:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNitCliente(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Objeto del Contrato:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getObjecto(), 4, 1, Element.ALIGN_JUSTIFIED, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("Recorrido:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getInfoRuta(), 4, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Convenio / Consorcio / Unión Temporal con:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getEmpresaVehiculo(), 3, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("\n\nVigencia del Contrato", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            System.out.println("Fecha de formato: " + datosFuec.getFecha() + " fIN: " + datosFuec.getFechaFin());

            String fechaIni[] = datosFuec.getFecha().split("\\ ");
            String fechaFin[] = datosFuec.getFechaFin().split("\\ ");
            String F1[] = fechaIni[0].split("\\-");
            String F2[] = fechaFin[0].split("\\-");

            table.addCell(crearCelda("Fecha Inicial:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda("Día: " + F1[2], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Mes: " + F1[1], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Año: " + F1[0], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda("Fecha Final:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda("Día: " + F2[2], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Mes: " + F2[1], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Año: " + F2[0], 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda("\n\n\n\nCaracterísticas del Vehículo", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda("Placa:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Módelo:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Marca:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Clase:", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getPlaca(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getModelo(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getMarca(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getClase(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Número Interno:", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Número de Tarjeta de Operación:", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda(datosFuec.getNumeroInterno(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNumeroTarjetaOpe(), 3, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            int i = 1;
            if (datosFuec.getPersonas() != null) {
                for (Persona per : datosFuec.getPersonas()) {
                    table.addCell(crearCelda("Datos del Conductor " + i + ":", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda("No. Licencia de Conducción:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda("Vigencia:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

                    table.addCell(crearCelda(per.getNombreConductor1() + " " + per.getApellidoConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(per.getCcConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(per.getLicenciaConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(per.getVigenciaConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    i++;
                }
            }

            if (i < 3) {
                for (int j = 0; j < datosFuec.getPersonas().size() - 3; j++) {
                    table.addCell(crearCelda(" ", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                    table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
                }
            }
            /*table.addCell(crearCelda("Datos del Conductor 2:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("No. Licencia de Conducción:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("Vigencia:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

             table.addCell(crearCelda(datosFuec.getNombreConductor2() + " " + datosFuec.getApellidoConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getCcConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getLicenciaConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getVigenciaConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             if (datosFuec.getNombreConductor3() != null) {
             //start conductor 3
             table.addCell(crearCelda("Datos del Conductor 3:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("No. Licencia de Conducción:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda("Vigencia:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

             table.addCell(crearCelda(datosFuec.getNombreConductor3() + " " + datosFuec.getApellidoConductor3(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getCcConductor3(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getLicenciaConductor3(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(datosFuec.getVigenciaConductor3(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             //end codnuctor 3
             } else {
             table.addCell(crearCelda(" ", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
             table.addCell(crearCelda(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

             }*/
            table.addCell(crearCelda("Responsable del Contratante:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Teléfono:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Dirección:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            //table.addCell(crearCelda(datosFuec.getNombreResponsable() + " " + datosFuec.getApellidoResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            //table.addCell(crearCelda(datosFuec.getCcResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            //table.addCell(crearCelda(datosFuec.getTelResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            //table.addCell(crearCelda(datosFuec.getDireccionResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("VIAJES IMPERIAL y "+datosFuec.getNombre(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("830087404 y "+datosFuec.getNitCliente(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("3173003000", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("Cl 26 #113-90", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCeldaTxPeque("\n\n\n\n\n\n\n\nPara efectos de control y seguridad,\n"
                    + "Contáctenos:\n"
                    + "VIAJES IMPERIAL SAS, Oficinas Administrativas\n"
                    + "Centro Aereo Internacional; KR 102 A No 25H - 45 Off. 306 - Bogota D.C\n"
                    + "Teléfonos administrativos, 5188383 – PBX Contact Center 3456789\n"
                    + "Servicios@viajesimperialsas.com, asistente.administrativa@viajesimperialsas.com", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCeldaImg(imgFirma, 2, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM));
            table.addCell(crearCeldaTxtGrande(datosFuec.getNumeroLargo(), 3, 4, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM, true));
            //table.addCell(crearCeldaImg(imgSuperIntend, 3, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            table.addCell(crearCelda2("Firma y Sello Gerente Contrato", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCelda2("Firma digital del representante legal de Viajes Imperial, válida según Certificado Digital N°415162 del 2014/09/16 Certicamara", 2, 3, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCeldaOnlyTopBorder("", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCeldaOnlyTopBorder("\n", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            //table.addCell(crearCeldaImgTopBorde(imgSuperIntend, 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            table.addCell(crearCelda2("\n ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            //hsa table.addCell(crearCelda("\nOcupantes Grupo Específico de Usuarios", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda2("\n ", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            if (false) {// quitar conductor 3 y habilitar esta linea
                table.addCell(crearCelda2("\n ", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
                table.addCell(crearCelda2("\n ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
                table.addCell(crearCelda2("\n ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
                table.addCell(crearCelda2("\n ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

                table.addCell(crearCelda2(" ", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
                table.addCell(crearCelda2(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
                table.addCell(crearCelda2(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
                table.addCell(crearCelda2(" ", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            }

            document.add(table);

            document.close();

            //Marca de Agua
            /*PdfReader pdfReader = new PdfReader(archFuec);
             int numeroPaginas = pdfReader.getNumberOfPages();
             PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(archivoFuecFinal));
             int i = 0;

             imgMarca.setAbsolutePosition(100, 300);
             PdfContentByte pdfContentByte;

             while (i < numeroPaginas) {
             i++;
             pdfContentByte = pdfStamper.getUnderContent(i);
             pdfContentByte.addImage(imgMarca);
             }

             pdfStamper.close();
             pdfReader.close();*/
            // String path = new InitHelper().getCurrentWorkingDirectory() + "/fact_" + datosFuec.getNumeroLargo() + "_.pdf";
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(baos.toByteArray());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ocurrio un error al crear el archivo");
            return baos.toByteArray();
        }

        return baos.toByteArray();
    }

    public byte[] crearFuecOLD(DatosFuec datosFuec) {

        String archFuec;
        String archivoFuecFinal;
        Image imgMarca;
        Image imagenMin;
        Image imagenImp;
        Image imgFirma;
        Image imgSuperIntend;

        System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");

        document.open();

        try {

            // archFuec = "/opt/fuec/FuecSin_" + datosFuec.getPlaca() + "_" + new DateTools().dateToStringyyyyMMddHHmm(new Date()) + ".pdf";
            // archivoFuecFinal = "/opt/fuec/Fuec_" + datosFuec.getPlaca() + "_" + new DateTools().dateToStringyyyyMMddHHmm(new Date()) + ".pdf";
            imgMarca = new Helper().getImage("/images/img/marcaAguaViajes.png", writer);
            imagenMin = new Helper().getImage("/images/img/MinTransporte.png", writer);
            imagenImp = new Helper().getImage("/images/img/Imperial.png", writer);
            imgFirma = new Helper().getImage("/images/img/FIRMA_SELLO.png", writer);
            //imgSuperIntend = new Helper().getImage("/images/img/SuperInten.png", writer);
            imgSuperIntend = new Helper().getImage("/images/img/FIRMA_SELLO.png", writer);

//hsa            PdfWriter.getInstance(document, new FileOutputStream(archFuec));
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell(crearCeldaImgSinBorder(imagenMin, 3, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            table.addCell(crearCeldaImgSinBorder(imagenImp, 2, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));

            table.addCell(crearCeldaSinBorder("\nFORMATO ÚNICO DEL EXTRACTO DEL SERVICIO PÚBLICO DE TRANSPORTE "
                    + "TERRESTRE AUTOMOTOR ESPECIAL No. " + datosFuec.getNoFuec(), 5, 3, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda("Razón Social:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getRazonSocial(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("Nit:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNitRazonSocial(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Contrato No:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNumContrato(), 4, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Contratante:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getContratante(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Nit:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNitContratante(), 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Objeto del Contrato:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getObjetoContrato(), 4, 1, Element.ALIGN_JUSTIFIED, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda("Recorrido:", 1, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getRecorrido(), 4, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Convenio / Consorcio / Unión Temporal con:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getConvenioUt(), 3, 1, Element.ALIGN_LEFT, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("\nVigencia del Contrato", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda("Fecha Inicial:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda("Día: " + dateTools.dateToArray(datosFuec.getFechaInicial())[2].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Mes: " + dateTools.dateToArray(datosFuec.getFechaInicial())[1].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Año: " + dateTools.dateToArray(datosFuec.getFechaInicial())[0].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda("Fecha Final:", 2, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda("Día: " + dateTools.dateToArray(datosFuec.getFechaFinal())[2].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Mes: " + dateTools.dateToArray(datosFuec.getFechaFinal())[1].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Año: " + dateTools.dateToArray(datosFuec.getFechaFinal())[0].toString(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda("\nCaracterísticas del Vehículo", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda("Placa:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Módelo:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Marca:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Clase:", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getPlaca(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getModelo(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getMarca(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getClase(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Número Interno:", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Número de Tarjeta de Operación:", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda(datosFuec.getNumInterno(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda(datosFuec.getNumTarjetaOper(), 3, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda("Datos del Conductor 1:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Licencia de Conducción:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Vigencia:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda(datosFuec.getNombreConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getCcConductor1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getLicConduccion1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getVigenciaLic1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Datos del Conductor 2:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Licencia de Conducción:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Vigencia:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda(datosFuec.getNombreConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getCcConductor2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getLicConduccion2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getVigenciaLic2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda("Responsable del Contratante:", 1, 2, Element.ALIGN_LEFT, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Nombres y Apellidos:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("No. Cédula:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Teléfono:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));
            table.addCell(crearCelda("Dirección:", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, true));

            table.addCell(crearCelda(datosFuec.getNombreResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getCcResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getTelResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));
            table.addCell(crearCelda(datosFuec.getDirResponsable(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_LEFT, false));

            table.addCell(crearCelda2("\nOcupantes Grupo Específico de Usuarios", 5, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda2("\nNombre", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda2("\nC.C.", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda2("\nNombre", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCelda2("\nC.C.", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCelda2(datosFuec.getPasajero1(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero1(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero11(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero11(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero2(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero2(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero12(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero12(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero3(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero3(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero13(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero13(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero4(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero4(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero14(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero14(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero5(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero5(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero15(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero15(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero6(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero6(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero16(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero16(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero7(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero7(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero17(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero17(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero8(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero8(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero18(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero18(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero9(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero9(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero19(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero9(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

            table.addCell(crearCelda2(datosFuec.getPasajero10(), 2, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero10(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getPasajero20(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
            table.addCell(crearCelda2(datosFuec.getCcPasajero20(), 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));

//            table.addCell(crearCelda("\n\n\nAv. El Dorado No. 101 - 36, Tel: 7569803\nBogotá D.C.\ne-mail: viajesimperialsas@gmail.com\n\n ", 3, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));
            table.addCell(crearCeldaTxPeque("\n\nPara efectos de control y seguridad,\n"
                    + "Contáctenos:\n"
                    + "VIAJES IMPERIAL SAS, Oficinas Administrativas\n"
                    + "Centro Comercial Automotriz Carrera; Av. calle 9 No 50 – 15 Local 1069 – Bogotá D. C.\n"
                    + "Teléfonos administrativos, 2609539 – PBX Contact Center 3456789\n"
                    + "Servicios@viajesimperialsas.com, asistente.administrativa@viajesimperialsas.com", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, true));

            table.addCell(crearCeldaImg(imgFirma, 2, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM));

            table.addCell(crearCeldaTxtGrande(datosFuec.getFueCodigo().toString(), 3, 4, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM, true));
            //table.addCell(crearCeldaImg(imgSuperIntend, 3, 4, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
            table.addCell(crearCelda2("Firma y Sello Gerente Contrato", 2, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCelda2("Firma digital del representante legal de Viajes Imperial, válida según Certificado Digital N°415162 del 2014/09/16 Certicamara", 2, 3, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCeldaOnlyTopBorder("", 3, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCeldaOnlyTopBorder("\n", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM, true));
            table.addCell(crearCeldaImgTopBorde(imgSuperIntend, 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER));

//            PdfPTable table2 = new PdfPTable(2);
//            table.setWidthPercentage(100);
//
//            table2.addCell(crearCelda("", 1, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER, false));
//            table2.addCell(crearCeldaImg(superIntend, 1, 1, Element.ALIGN_RIGHT, Element.ALIGN_CENTER));
            document.add(table);
            //document.add(table2);
            document.close();

            //Marca de Agua
            /*PdfReader pdfReader = new PdfReader(archFuec);
             int numeroPaginas = pdfReader.getNumberOfPages();
             PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(archivoFuecFinal));
             int i = 0;

             imgMarca.setAbsolutePosition(100, 300);
             PdfContentByte pdfContentByte;

             while (i < numeroPaginas) {
             i++;
             pdfContentByte = pdfStamper.getUnderContent(i);
             pdfContentByte.addImage(imgMarca);
             }

             pdfStamper.close();
             pdfReader.close();*/
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ocurrio un error al crear el archivo");
            return baos.toByteArray();
        }

        return baos.toByteArray();
    }

    protected PdfPCell crearCelda(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(Rectangle.BOX);

        return celda;
    }

    protected PdfPCell crearCeldaSinBorder(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(0);

        return celda;
    }

    protected PdfPCell crearCelda2(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(0);

        return celda;
    }

    protected PdfPCell crearCeldaTxPeque(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(7);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(7);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(Rectangle.BOX);

        return celda;
    }

    protected PdfPCell crearCeldaTxtGrande(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(14);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(14);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(Rectangle.BOX);

        return celda;
    }

    protected PdfPCell crearCeldaNoBorder(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(PdfPCell.RIGHT);

        return celda;
    }

    protected PdfPCell crearCeldaOnlyTopBorder(String leyenda, int columnas, int filas, int alignHor, int alignVer, boolean negrilla) {

        PdfPCell celda;

        if (negrilla) {
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            fuente.setColor(BaseColor.BLACK);
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        } else {
            Font fuente = new Font();
            fuente.setSize(8);
            celda = new PdfPCell(new Paragraph(leyenda, fuente));
        }

        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(PdfPCell.TOP);

        return celda;
    }

    protected PdfPCell crearCeldaImg(Image img, int columnas, int filas, int alignHor, int alignVer) throws Exception {
        System.out.println("img: " + img);
        //hsa img.setScaleToFitHeight(true);
        img.scalePercent(10f);
        PdfPCell celda = new PdfPCell(img, true);
        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(Rectangle.BOX);

        return celda;
    }

    protected PdfPCell crearCeldaImgSinBorder(Image img, int columnas, int filas, int alignHor, int alignVer) throws Exception {
        System.out.println("img: " + img);
        //hsa img.setScaleToFitHeight(true);
        img.scalePercent(10f);
        PdfPCell celda = new PdfPCell(img, true);
        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(0);

        return celda;
    }

    protected PdfPCell crearCeldaImgSinBorde(Image img, int columnas, int filas, int alignHor, int alignVer) {

        //hsa img.setScaleToFitHeight(true);
        img.scalePercent(10f);
        PdfPCell celda = new PdfPCell(img, true);
        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(PdfPCell.LEFT);
        //celda.set

        return celda;
    }

    protected PdfPCell crearCeldaImgTopBorde(Image img, int columnas, int filas, int alignHor, int alignVer) {

        //hsa img.setScaleToFitHeight(true);
        img.scalePercent(10f);
        PdfPCell celda = new PdfPCell(img, true);
        celda.setColspan(columnas);
        celda.setRowspan(filas);
        celda.setHorizontalAlignment(alignHor);
        celda.setVerticalAlignment(alignVer);
        celda.setBorder(PdfPCell.TOP);
        //celda.set

        return celda;
    }

    private PdfPCell changeSizeImgCell(Image image, int columnas, int filas, int alignHor, int alignVer) {
        PdfPCell celda = new PdfPCell();
        try {

            image.setAlignment(Image.ALIGN_CENTER);
            //hsa  image.setScaleToFitHeight(true);

            celda.addElement(new Chunk(image, -100, -100));
            celda.setColspan(columnas);
            celda.setRowspan(filas);
            celda.setHorizontalAlignment(alignHor);
            celda.setVerticalAlignment(alignVer);
            //celda.setBorder(Rectangle.BOX);
        } catch (Exception ex) {
            rw(ex.getMessage());
        }
        return celda;
    }

    private DatosFuec settingDatosFuec() {
        DatosFuec datosFuec = new DatosFuec();

        try {
            datosFuec.setFueCodigo(10000);
            datosFuec.setNoFuec("001");
            datosFuec.setRazonSocial("Razón Social");
            datosFuec.setNitRazonSocial("Nit Razón Social");
            datosFuec.setNumContrato("0000001111111");
            datosFuec.setContratante("Contratante");
            datosFuec.setNitContratante("800565656-0");
            datosFuec.setNumContrato("numContrato");
            datosFuec.setContratante("Contratante");
            datosFuec.setNitContratante("nitContratante");
            datosFuec.setObjetoContrato("objetoContrato");
            datosFuec.setRecorrido("recorrido  recorrido2");
            datosFuec.setConvenioUt("convenioUt");
            datosFuec.setFechaInicial(new Date());
            datosFuec.setFechaFinal(new Date());
            datosFuec.setPlaca("PRU001");
            datosFuec.setModelo("2013");
            datosFuec.setMarca("Yamaya");
            datosFuec.setClase("R es R, lo demás es genérico");
            datosFuec.setNumInterno("000000001");
            datosFuec.setNumTarjetaOper("numTarjetaOper");
            datosFuec.setNombreConductor1("nombreConductor1");
            datosFuec.setCcConductor1("ccConductor1");
            datosFuec.setLicConduccion1("licConduccion1");
            datosFuec.setVigenciaLic1("vigenciaLic1");
            datosFuec.setNombreConductor2("ccConductor2");
            datosFuec.setCcConductor2("ccConductor2");
            datosFuec.setLicConduccion2("licConduccion2");
            datosFuec.setVigenciaLic2("vigenciaLic2");
            datosFuec.setNombreResponsable("nombreResponsable");
            datosFuec.setCcResponsable("ccResponsable");
            datosFuec.setTelResponsable("telResponsable");
            datosFuec.setDirResponsable("dirResponsable");
            datosFuec.setPasajero1("pasajero1");
            datosFuec.setCcPasajero1("ccPasajero1");
            datosFuec.setPasajero2("pasajero2");
            datosFuec.setCcPasajero2("ccPasajero2");
            datosFuec.setPasajero3("pasajero3");
            datosFuec.setCcPasajero3("pasajero4");
            datosFuec.setPasajero4("pasajero4");
            datosFuec.setCcPasajero4("ccPasajero4");
            datosFuec.setPasajero5("pasajero5");
            datosFuec.setCcPasajero5("ccPasajero5");
            datosFuec.setPasajero6("pasajero6");
            datosFuec.setCcPasajero6("ccPasajero6");
            datosFuec.setPasajero7("pasajero7");
            datosFuec.setCcPasajero7("ccPasajero7");
            datosFuec.setPasajero8("pasajero8");
            datosFuec.setCcPasajero8("ccPasajero8");
            datosFuec.setPasajero9("pasajero9");
            datosFuec.setCcPasajero9("ccPasajero9");
            datosFuec.setPasajero10("pasajero10");
            datosFuec.setCcPasajero10("ccPasajero10");
            datosFuec.setPasajero11("pasajero11");
            datosFuec.setCcPasajero11("ccPasajero11");
            datosFuec.setPasajero12("pasajero12");
            datosFuec.setCcPasajero12("ccPasajero12");
            datosFuec.setPasajero13("pasajero13");
            datosFuec.setCcPasajero13("ccPasajero13");
            datosFuec.setPasajero14("pasajero14");
            datosFuec.setCcPasajero14("ccPasajero14");
            datosFuec.setPasajero15("pasajero15");
            datosFuec.setCcPasajero15("ccPasajero15");
            datosFuec.setPasajero16("pasajero16");
            datosFuec.setCcPasajero16("ccPasajero16");
            datosFuec.setPasajero17("pasajero17");
            datosFuec.setCcPasajero17("ccPasajero17");
            datosFuec.setPasajero18("pasajero18");
            datosFuec.setCcPasajero18("ccPasajero18");
            datosFuec.setPasajero19("pasajero19");
            datosFuec.setCcPasajero19("ccPasajero19");
            datosFuec.setPasajero20("pasajero20");
            datosFuec.setCcPasajero20("ccPasajero20");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return datosFuec;
    }

}
