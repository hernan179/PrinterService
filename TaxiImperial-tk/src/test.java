/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsancheza
 */
public class test {

    public static void main(String[] args) {
     boolean isBien =    isOk("12g566");
        System.out.println("is : "+isBien);
    }

    static boolean isOk(String data) {
        String data1 = data.substring(0, 3);
        String data2 = data.substring(3, 6);
        System.out.println("data1:= " + data1);
        System.out.println("data2:= " + data2);
        boolean isOk = true;
        if (!data2.matches("[0-9]*")) {
            System.out.println("my mal no son numeros");
            isOk = false;
        }

        if (data1.matches("[0-9]")) {
            System.out.println("my mal no son letras");
            isOk = false;
        }
        return isOk;
    }
}
