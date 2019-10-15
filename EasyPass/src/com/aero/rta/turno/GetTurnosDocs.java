/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.turno;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hsancheza
 */
public class GetTurnosDocs {

    Document document = new Document(PageSize.POSTCARD, 15, -20, 30, 2);
    //Document document = new Document(pageSize);// mas grande 
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    static SimpleDateFormat smdf = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");

    public byte[] generarTurno(int turno, String parqueadero) {
        try {
            document.open();
            PdfPTable table1 = new PdfPTable(1);
            Paragraph TURNO = new Paragraph("TURNO: " + turno, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            Paragraph PK = new Paragraph(parqueadero, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));

            PdfPCell cell = new PdfPCell(new Paragraph("\n"));
            cell.setBorderWidth(0);
            table1.addCell(cell);

            PdfPCell cell1 = new PdfPCell(PK);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorderWidth(0);
            table1.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(TURNO);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorderWidth(0);
            table1.addCell(cell2);

            PdfPTable table2 = new PdfPTable(4);
            table2.addCell(obtenerCell(1, new Paragraph("Fecha:", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD))));
            table2.addCell(obtenerCell(3, new Paragraph(smdf.format(new Date()), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD))));

            document.add(table1);
            document.add(table2);
            document.close();
            //OutputStream rutaArchivo = new FileOutputStream("C:/Users/hsancheza/Desktop/log view/" + turno + "turno.pdf");
            //baos.writeTo(rutaArchivo);
            //baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] generarSalida(int contrato, String placa, String estado,String QRT,String detalle) {
        try {
            document.open();
            PdfPTable table1 = new PdfPTable(1);
            Paragraph TURNO = new Paragraph("Contrato:" + contrato, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            Paragraph PK = new Paragraph("Placa:" + placa, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));

            PdfPCell cell = new PdfPCell(new Paragraph("\n"));
            cell.setBorderWidth(0);
            table1.addCell(cell);

            PdfPCell cell1 = new PdfPCell(PK);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorderWidth(0);
            table1.addCell(cell1);

            if (estado != null && !estado.isEmpty()) {
                Paragraph ESTA = new Paragraph(estado, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));

                PdfPCell cell3 = new PdfPCell(ESTA);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setBorderWidth(0);
                table1.addCell(cell3);
                System.out.println("esta add table: " + estado);
            }
            
            if (QRT != null && !QRT.isEmpty() && QRT.equals("si")) {
                Paragraph ESTA = new Paragraph("QRT-SC:"+detalle, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
                PdfPCell cell3 = new PdfPCell(ESTA);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setBorderWidth(0);
                table1.addCell(cell3);
                System.out.println("esta add table: " + estado);
            }

            PdfPCell cell2 = new PdfPCell(TURNO);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorderWidth(0);
            table1.addCell(cell2);

            PdfPTable table2 = new PdfPTable(1);
            //table2.addCell(obtenerCell(1, new Paragraph("Fecha:", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD))));
            table2.addCell(obtenerCell(3, new Paragraph("Fec: " + smdf.format(new Date()), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD))));

            document.add(table1);
            document.add(table2);
            document.close();
            
//              FileOutputStream fos = new FileOutputStream("C:/scanner/contrato_"+placa+".pdf");
//            fos.write(baos.toByteArray());
//            fos.close();
//            
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
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
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colSpam);
        cell.setPadding(0);
        cell.setBorder(0);
        return cell;
    }

    private PdfWriter getPdfWriter(Document doc, OutputStream os) {

        PdfWriter out = null;
        try {
            out = PdfWriter.getInstance(doc, os);
        } catch (Exception ex) {
        }

        return out;
    }
}
