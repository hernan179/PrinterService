/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.easypass;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalIconFactory;

public class Test {

    Test2 test = new Test2();
    JFrame frame = new JFrame();

    Test() {

        frame.setLayout(new BorderLayout());
        frame.add(test, BorderLayout.CENTER);

    }
//main
    public static void main(String[] args) {
        new Test().frame.setVisible(true);
    }
}

//public class Test2{
class Test2 extends JPanel {

//JPanel test2 = new JPanel();
    Test2() {
    }
}
