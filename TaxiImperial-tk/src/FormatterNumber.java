
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsancheza
 */
public class FormatterNumber {

    public static void main(String[] args) throws Exception {

        DecimalFormat formateador = new DecimalFormat("###,###.##");

//Este daria a la salida 1,000
        System.out.println(formateador.format(1000));

//Este otro 10,000
        System.out.println(formateador.format(1000000));

    }
}
