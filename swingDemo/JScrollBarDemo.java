package org.luncert.smooth;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class JScrollBarDemo implements AdjustmentListener {

    JScrollBar scrollBar1;
    JScrollBar scrollBar2;
    JPanel panel1;
    JLabel label2 = new JLabel("刻度", JLabel.CENTER);

    public JScrollBarDemo() {
        JFrame frame = new JFrame("JScrollBarDemo");
        Container container = frame.getContentPane();

        JLabel label1 = new JLabel(new ImageIcon(JScrollBarDemo.class.getClassLoader().getResource("1.png").getPath()));
        panel1 = new JPanel();
        panel1.add(label1);

        scrollBar1 = new JScrollBar(JScrollBar.VERTICAL, 10, 10, 0, 100);
        scrollBar1.setUnitIncrement(1);
        scrollBar1.setBlockIncrement(10);
        scrollBar1.addAdjustmentListener(this);

        scrollBar2 = new JScrollBar(JScrollBar.HORIZONTAL);
        scrollBar2.setValue(0);
        scrollBar2.setVisibleAmount(20);
        scrollBar2.setMinimum(10);
        scrollBar2.setMaximum(60);
        scrollBar2.setBlockIncrement(5);
        scrollBar2.addAdjustmentListener(this);

        container.add(panel1, BorderLayout.CENTER);
        container.add(scrollBar1, BorderLayout.EAST);
        container.add(scrollBar2, BorderLayout.SOUTH);
        container.add(label2, BorderLayout.NORTH);

        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource() == scrollBar1) {
            label2.setText("垂直刻度：" + e.getValue());
        }
        else if (e.getSource() == scrollBar2) {
            label2.setText("水平刻度：" + e.getValue());
        }
	}

}