package org.luncert.smooth;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class JScrollPaneDemo implements ActionListener {

    JScrollPane scrollPane;

    public JScrollPaneDemo() {
        String picPath = JScrollPaneDemo.class.getClassLoader().getResource("1.png").getPath();

        JFrame frame = new JFrame("JScrollPane");
        Container container = frame.getContentPane();

        JLabel label1 = new JLabel(new ImageIcon(picPath));
        JPanel panel1 = new JPanel();
        panel1.add(label1);
        scrollPane = new JScrollPane(panel1);
        scrollPane.setColumnHeaderView(new JLabel("水平表头"));
        scrollPane.setRowHeaderView(new JLabel("垂直表头"));
        scrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JLabel("corner"));
        scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JLabel("corner"));
        
        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        JButton button = new JButton("显示水平滚动轴");
        button.addActionListener(this);
        panel2.add(button);
        button = new JButton("不显示水平滚动轴");
        button.addActionListener(this);
        panel2.add(button);
        button = new JButton("适时显示水平滚动轴");
        button.addActionListener(this);
        panel2.add(button);

        container.add(panel2, BorderLayout.WEST);
        container.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(new Dimension(350, 220));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
		if (actionCommand.equals("显示水平滚动轴")) {
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        } else if (actionCommand.equals("不显示水平滚动轴")) {
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        } else if (actionCommand.equals("适时显示水平滚动轴")) {
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
        scrollPane.revalidate();
	}
}