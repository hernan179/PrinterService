/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.tools;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author hsancheza
 */
public class MensajeTmp extends JFrame {

    public void createAndShowGui(final String values) {
        final JLabel label = new JLabel();
        int timerDelay = 1000;
        new Timer(timerDelay, new ActionListener() {
            int timeLeft = 2;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    label.setText(values + "\nCerrando en... " + timeLeft);
                    timeLeft--;
                } else {
                    ((Timer) e.getSource()).stop();
                    Window win = SwingUtilities.getWindowAncestor(label);
                    win.setVisible(false);
                }
            }
        }) {
            {
                setInitialDelay(0);
            }
        }.start();

        JOptionPane.showMessageDialog(null, label);

    }
}
