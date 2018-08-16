public class EventDemo1 extends WindowAdapter implements ActionListener {

    public EventDemo1() {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        JButton button = new JButton("按我有声音喔");
        button.addActionListener(this);
        container.add(button);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }

}