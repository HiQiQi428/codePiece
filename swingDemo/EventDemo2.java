
public class EventDemo2 extends WindowAdapter implements ActionListener {

    JButton b1;
    JButton b2;

    public EventDemo2() {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(2, 1));
        b1 = new JButton("响");
        b2 = new JButton("打开");
        b1.addActionListener(this);
        b2.addActionListener(this);
        container.add(b1);
        container.add(b2);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // e.getActionCommand().equals("响");
        if (e.getSource() == b1) {
            System.out.println("Beep!");
        }
        else if (e.getSource() == b2) {
            JFrame frame = new JFrame("新窗口");
            frame.setSize(200, 200);
            frame.setVisible(true);
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

}