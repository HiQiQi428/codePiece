package org.luncert.smooth;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class BorderDemo {

    public BorderDemo() {
        JFrame frame = new JFrame("BorderDemo");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        JButton button = new JButton();
        button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        container.add(button, BorderLayout.CENTER);
        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}