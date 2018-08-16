package org.luncert.smooth;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageIconDemo {

    public ImageIconDemo() {
        JFrame frame = new JFrame("ImageIconDemo");
        Container container = frame.getContentPane();
        Icon icon = new ImageIcon(ImageIconDemo.class.getClassLoader().getResource("1.png").getPath());
        JLabel label = new JLabel(icon, JLabel.CENTER);
        container.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}