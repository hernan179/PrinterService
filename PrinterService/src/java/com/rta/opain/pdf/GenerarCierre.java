package com.rta.opain.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rta.opain.delegate.tools.StringTools;
import com.rta.opain.domain.Servicios;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author hsancheza
 */
public class GenerarCierre {

    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    Font estadoText = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

    public byte[] generarFactura(List<Servicios> servicios, String fecha) throws Exception {

        document.open();
        
        String cajero = "";
        if(servicios != null && servicios.size() > 0 ){
            cajero = "["+servicios.get(0).getUsuarios().getUsuario()+"]"+servicios.get(0).getUsuarios().getNombre();
        }

        PdfPTable table = new PdfPTable(6);
        table.addCell(obtenerCellColspam(getParagraph("VIAJES IMPERIAL S.A.S Nit: 830087404", 8), 6, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph("Cierre de Cajas " +fecha, 8), 6, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph("_____________________"+cajero+"_____________________", 8), 6, Element.ALIGN_CENTER));
        table.addCell(obtenerCellColspam(getParagraph(" ", 10), 6, Element.ALIGN_CENTER));

        table.addCell(obtenerCellColspam(getParagraph("Numero", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Efectivo", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Debito", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Crédito", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Estado", 6), 1, Element.ALIGN_LEFT));
        table.addCell(obtenerCellColspam(getParagraph("Valor", 6), 1, Element.ALIGN_RIGHT));

        Long valorTotal = 0l;
        Integer facturasTotal = 0;
        
        for (Servicios cintas : servicios) {
            table.addCell(obtenerCellColspam(getParagraph(cintas.getNumero().toString(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getValorE().toString(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getValorD().toString(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getValorC().toString(), 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getEstado().getId().toString().equals("26") ? "ANULADA" : "CONTABILIZADA", 6), 1, Element.ALIGN_LEFT));
            table.addCell(obtenerCellColspam(getParagraph(cintas.getValor().toString(), 6), 1, Element.ALIGN_RIGHT));

            valorTotal += cintas.getValor();
            facturasTotal = servicios.size();
        }
  
        table.addCell(obtenerCellColspam(getParagraph(" ", 6), 6, Element.ALIGN_RIGHT));
        table.addCell(obtenerCellColspam(getParagraph(" ", 6), 3, Element.ALIGN_RIGHT));
        table.addCell(obtenerCellColspam(getParagraph("Total de Fecturas " + (facturasTotal.toString()), 6), 3, Element.ALIGN_RIGHT));

        table.addCell(obtenerCellColspam(getParagraph(" ", 6), 3, Element.ALIGN_RIGHT));
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
