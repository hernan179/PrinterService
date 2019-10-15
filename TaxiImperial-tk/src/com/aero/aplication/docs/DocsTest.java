/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.docs;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 *
 * @author hsancheza
 */
public class DocsTest {

    Document document = new Document(PageSize.LETTER, 15, -30, 30, 2);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = getPdfWriter(document, baos);
    Font catFont6 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);

    public byte[] getImgByte() throws Exception {

        PdfPTable banner = new PdfPTable(1);
        banner.getDefaultCell().setBorder(0);
        PdfPCell cellBanner = new PdfPCell(new Phrase((" "), catFont6));
        cellBanner.setFixedHeight(60);
        cellBanner.setBorder(0);
        banner.addCell(cellBanner);
        document.add(banner);

        document.open();
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
}
