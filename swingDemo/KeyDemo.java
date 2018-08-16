
public class KeyDemo extends KeyAdapter implements ActionListener {

    JFrame frame;
    JLabel label;
    JTextField textField;
    StringBuilder keyString;

    public KeyDemo() {
        keyString = new StringBuilder();
        frame = new JFrame("KeyFrameDemo");
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 1));
        label = new JLabel();
        textField = new JTextField();
        textField.requestFocus();
        textField.addKeyListener(this);
        JButton button = new JButton("clear");
        button.addActionListener(this);
        container.add(label);
        container.add(textField);
        container.add(button);
        frame.pack();
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
        keyString.delete(0, keyString.length());
        label.setText("");
        textField.setText("");
        textField.requestFocus();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        keyString.append(c);
        label.setText(keyString.toString());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // e.getKeyCode();
        // e.getModifiers(e.getModifiers());
    }

}