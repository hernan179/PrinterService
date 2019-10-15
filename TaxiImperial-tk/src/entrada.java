
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsancheza
 */
public class entrada extends JFrame {

    static String intoPrecion = "0";

    public static void main(String[] args) {
        new entrada().test();
    }

    public void test() {
        int intoPreciono = 0;
        String precion = "";

        int i = 1;
        do {
            System.out.println("validar " + i);

            if (intoPreciono == 0) {
                precion = JOptionPane.showInputDialog(this, "Ingresar precio", "Debe ingresar costo de la carrera", JOptionPane.INFORMATION_MESSAGE);
                if (precion != null && !precion.isEmpty() && precion.matches("[0-9]*")) {
                    intoPreciono = new Integer(precion);
                }
            }
            i++;
        } while (i <= 3);
    }
}
