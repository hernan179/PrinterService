package com.rta.imp.pdf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.rta.imp.web.controller.LogTest.rw;
import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.RtaDTO;
import com.rta.opain.delegate.tools.StringTools;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 *
 * @author hsancheza
 */
public class CintaTestigo {

    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    Font estadoText = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

    public byte[] generarFactura(RtaDTO cintaMg, String fecha, String fecha2, com.itextpdf.text.Image image) throws Exception {

        document.open();

        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
        writer.getDirectContentUnder().addImage(image, width, 0, 0, height, 0, 0);

        PdfPTable table = new PdfPTable(8);
        table.addCell(obtenerCellColspam(getParagraph("VIAJES IMPERIAL S.A.S Nit: 830087404", 8), 8, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph("Cinta Testigo Magnetica", 8), 8, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph("Comprobante informe " + fecha + " hasta " + fecha2, 8), 8, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph("________________________________________________", 8), 8, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph(" ", 10), 8, Element.ALIGN_CENTER));

        table.addCell(obtenerCellColspam(getParagraph("Grabado", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Cantidad", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Dispositivo", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Inicio", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Final", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Efectivo", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Debito", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Credito", 6), 1, Element.ALIGN_RIGHT));
        table.addCell(obtenerCellColspam(getParagraph(" ", 10), 8, Element.ALIGN_RIGHT));
        Long valorTotal = 0l;
        Long facturasTotal = 0l;
        for (JsonDTO cintas : cintaMg.getAlertas()) {
            table.addCell(obtenerCellColspam(getParagraph(cintas.getGrabador(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getCantidad(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getDispositivo(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getInicio(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getFin(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getEfectivo(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getDebito(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getCredito(), 6), 1, Element.ALIGN_RIGHT));
            table.addCell(obtenerCellColspam(getParagraph(" ", 6), 7, Element.ALIGN_CENTER));
            table.addCell(obtenerCellColspam(getParagraph("Total:" + StringTools.getString$(cintas.getTotal()), 6), 1, Element.ALIGN_RIGHT));
            table.addCell(obtenerCellColspam(getParagraph("============", 6), 8, Element.ALIGN_RIGHT));
            table.addCell(obtenerCellColspam(getParagraph(" ", 6), 8, Element.ALIGN_RIGHT));
            valorTotal += StringTools.getNumberLong(cintas.getTotal());
            facturasTotal += StringTools.getNumberLong(cintas.getCantidad());
        }
        table.addCell(obtenerCellColspam(getParagraph(" ", 6), 6, Element.ALIGN_RIGHT));
        table.addCell(obtenerCellColspam(getParagraph("Total de Fecturas " + (facturasTotal.toString()), 6), 3, Element.ALIGN_RIGHT));

        table.addCell(obtenerCellColspam(getParagraph(" ", 6), 6, Element.ALIGN_RIGHT));
        table.addCell(obtenerCellColspam(getParagraph("Valor Total registrado " + StringTools.getString$(valorTotal.toString()), 6), 3, Element.ALIGN_RIGHT));

        document.add(table);
        document.close();
        try {
            // OutputStream rutaArchivo = new FileOutputStream("C:/temp/pdf_.pdf");
            // baos.writeTo(rutaArchivo);
        } catch (RuntimeException e) {
        }
        return baos.toByteArray();
    }

    public Paragraph getParagraph(String data, int size) {
        return new Paragraph(data, new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
    }

    public PdfPCell obtenerCellColspam(Paragraph contendio, int colSpan, int orientacion) {
        PdfPCell cell = new PdfPCell(contendio);
        cell.setColspan(colSpan);
        cell.setPadding(0);
        cell.setBorder(0);
        cell.setHorizontalAlignment(orientacion);

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private PdfWriter getPdfWriter(Document doc, OutputStream os) {

        PdfWriter out = null;

        try {
            out = PdfWriter.getInstance(doc, os);
        } catch (DocumentException ex) {
            //log.error(ex);
        }

        return out;
    }
}
