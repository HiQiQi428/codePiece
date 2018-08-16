package org.luncert.smooth;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// panel：面，pane：窗格
public class JPanelDemo {

    public JPanelDemo() {
        JFrame frame = new JFrame("JPanelDemo");
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(2, 1));
        JLabel[] labels = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Label " + i, JLabel.CENTER);
            label.setBackground(Color.lightGray);
            label.setBorder(BorderFactory.createEtchedBorder());
            label.setOpaque(true);
            labels[i] = label;
        }

        JPanel panel1 = new JPanel(new GridLayout(1, 1));
        panel1.add(labels[0]);
        
        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        
        JPanel panel3 = new JPanel(new GridLayout(1, 2));
        panel3.add(labels[1]);
        panel3.add(labels[2]);
        
        JPanel panel4 = new JPanel(new GridLayout(2, 1));
        panel4.add(labels[3]);
        panel4.add(labels[4]);

        panel2.add(panel3);
        panel2.add(panel4);

        container.add(panel1);
        container.add(panel2);

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