/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.dto;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author hsancheza
 */
public class TestBarcode {

 
    public static BufferedImage getImage(Barcode barcode) throws OutputException {

        BufferedImage bi = new BufferedImage(barcode.getWidth(), barcode.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g = bi.createGraphics();
        barcode.draw(g, 0, 0);
        bi.flush();
        return bi;
    }

    public static BufferedImage addEmage(String barcode) throws Exception {
        try {
            Barcode barcode2 = BarcodeFactory.createCode128B(barcode);
            barcode2.setBarHeight(34);
            barcode2.setBarWidth(1);
            BufferedImage bufer = getImage(barcode2);
            
         
            return bufer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public static void main(String[] args) throws Exception {
    /*    PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        Barcode barcode = BarcodeFactory.createCode128B("123TUN3312012");
        barcode.setBarHeight(60);
        barcode.setBarWidth(2);

        BufferedImage bufferedImage = getImage(barcode);

        Image jpg = Image.getInstance(bufferedImage, null);
        PdfPTable table_nota2 = new PdfPTable(3);
        table_nota2.setWidthPercentage(98);
        table_nota2.addCell(jpg);
        table_nota2.addCell(jpg);
        table_nota2.addCell(jpg);

        document.add(table_nota2);
        document.close();
        OutputStream RutaArchivo = new FileOutputStream("C:/vehiculosBarCodeXXX.pdf");
        baos.writeTo(RutaArchivo);





        /*PdfWriter writer = PdfWriter.getInstance(document, baos);
         document.open();
         // codigo de barras
         JBarcodeBean barcode = new JBarcodeBean();
         barcode.setCodeType(new Interleaved25());
         barcode.setCode("860");
         barcode.setCheckDigit(true);
         //barcode.setSize(20, 20);
         BufferedImage bufferedImage = barcode.draw(new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB));
         Graphics2D g2 = bufferedImage.createGraphics();
         bufferedImage.getSource();
        

         //ImageIO.write(bufferedImage, "jpeg", baos);
         PdfPTable table_nota2 = new PdfPTable(1);
         table_nota2.addCell(jpg);
         document.add(table_nota2);
         document.close();
         OutputStream RutaArchivo = new FileOutputStream("C:/contrato.pdf");
         baos.writeTo(RutaArchivo);*/
    }
}
