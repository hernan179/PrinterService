/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsancheza
 */
public class StringTest {

    public static void main(String[] args) {
        String palabra = "384000.00000";
        Integer it = new Double(palabra).intValue();
        System.out.println("it_" + it);
        String conductor = "148164896571420810878";
        String idConductorPart01 = conductor.substring(0, 4);
        String idConductorPart02 = conductor.substring(conductor.length()-4,conductor.length());
        
        
        System.out.println("soze      " + conductor.length());
        System.out.println("conductor_" + conductor);
        System.out.println("conductor_" + idConductorPart01+"_"+idConductorPart02);

    }
}
