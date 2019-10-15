/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MyFirstTable {

    /**
     * The resulting PDF file.
     */
    public static final String RESULT = "c:/scanner/first_table.pdf";

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args)
            throws IOException, DocumentException {
        new MyFirstTable().createPdf(RESULT);
         
        
        
    }

    /**
     * Creates a PDF with information about the movies
     *
     * @param filename the name of the PDF file that will be created.
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(String filename)
            throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(createFirstTable());
        // step 5
        document.close();
    }

    /**
     * Creates our first table
     *
     * @return our first table
     */
    public static PdfPTable createFirstTable() {
        // a table with three columns
        PdfPTable table = new PdfPTable(3);
        // the cell object
        PdfPCell cell = new PdfPCell(new Phrase("Cell with colspan 2"));
        PdfPCell cell2 = new PdfPCell(new Phrase("Cell with colspan 2"));
        PdfPCell cell3 = new PdfPCell(new Phrase("Cell with colspan 2"));
        PdfPCell cell4 = new PdfPCell(new Phrase("Cell with colspan 2"));
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Cell with colspan 2"));
        cell.setColspan(3);
        cell.setPadding(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);


        cell2 = new PdfPCell(new Phrase("1,1"));
        table.addCell(cell2);


        cell3.setColspan(2);
        table.addCell(cell3);


        return table;
    }
}