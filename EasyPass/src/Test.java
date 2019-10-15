/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsancheza
 */
public class Test {
    public static void main(String[] args) {
        double number = 9.3234;
        String data = (String.format("%.2f", number));
        Double dd  = new Double(data.replaceAll(",", "."));
        System.out.println("ddd___"+dd);  
    }
}
