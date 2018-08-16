package org.luncert.smooth;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JTabbedPaneDemo implements ActionListener, ChangeListener {

    public JTabbedPaneDemo() {
        String picPath = JTabbedPaneDemo.class.getClassLoader().getResource("1.png").getPath();

        JFrame frame = new JFrame("JTabbedPane");
        Container container = frame.getContentPane();
        
        JLabel label1 = new JLabel(new ImageIcon(picPath));
        label1.setSize(20, 20);
        JPanel panel1 = new JPanel();
        panel1.add(label1);
        
        JLabel label2 = new JLabel("Label 2", JLabel.CENTER);
        label2.setBackground(Color.pink);
        label2.setOpaque(true);
        JPanel panel2 = new JPanel();
        panel2.add(label2);

        JLabel label3 = new JLabel("Label 3", JLabel.CENTER);
        label3.setBackground(Color.yellow);
        label3.setOpaque(true);
        JPanel panel3 = new JPanel();
        panel3.add(label3);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addChangeListener(this);
        tabbedPane.addTab("Picture", new ImageIcon(picPath), panel1, "图案");
        tabbedPane.addTab("Label 2", panel2);
        tabbedPane.addTab("Label 3", null, panel3, "label");

        container.add(tabbedPane);

        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}