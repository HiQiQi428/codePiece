package org.luncert.smooth;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IconDemo {
	
	public IconDemo() {
		JFrame frame = new JFrame("IconDemo");
		Container container = frame.getContentPane();
		JLabel label = new JLabel(new MyIcon(), JLabel.CENTER);
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

	public static class MyIcon implements Icon {

		int height = 50;
		int width = 70;

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.drawRect(x, y, width, height);
			g.setColor(Color.blue);
			g.fillRect(x + 5, y + 5, width - 10, height - 10);
		}
	
		@Override
		public int getIconWidth() {
			return width;
		}
	
		@Override
		public int getIconHeight() {
			return height;
		}

	}

}