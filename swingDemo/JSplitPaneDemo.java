package org.luncert.smooth;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class JSplitPaneDemo {

    public JSplitPaneDemo() {
        JFrame frame = new JFrame("JSplitPaneDemo");
        Container container = frame.getContentPane();
        JLabel label1 = new JLabel("Label 1", JLabel.CENTER);
        label1.setBackground(Color.green);
        label1.setOpaque(true);
        JLabel label2 = new JLabel("Label 2", JLabel.CENTER);
        label2.setBackground(Color.pink);
        label2.setOpaque(true);
        JLabel label3 = new JLabel("Label 3", JLabel.CENTER);
        label3.setBackground(Color.yellow);
        label3.setOpaque(true);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, label1, label2);
        splitPane1.setDividerLocation(0.3);
        splitPane1.setOneTouchExpandable(true);
        splitPane1.setDividerSize(10);

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, splitPane1, label3);
        splitPane2.setDividerLocation(35);
        splitPane2.setOneTouchExpandable(true);
        splitPane2.setDividerSize(5);

        container.add(splitPane2);
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