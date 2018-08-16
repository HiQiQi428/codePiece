
public class JInternalFrameDemo extends JFrame implements ActionListener {

    private static final long serialVersionUID = -4556888252397676634L;

    JDesktopPane desktopPane;
    int count = 1;

    public JInternalFrameDemo() {
        super("JInternalFrameDemo");
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JButton button = new JButton("Create New Internal Frames");
        button.addActionListener(this);
        container.add(button, BorderLayout.SOUTH);

        desktopPane = new JDesktopPane();
        container.add(desktopPane);
        
        setSize(350, 350);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JInternalFrame internalFrame = new JInternalFrame("Internal Frame " + (++count), true, true, true, true);
        internalFrame.setLocation(20, 20);
        internalFrame.setSize(200, 200);
        internalFrame.setVisible(true);

        Container iContainer = internalFrame.getContentPane();
        JTextArea textArea = new JTextArea();
        JButton button = new JButton();
        iContainer.add(textArea, BorderLayout.CENTER);
        iContainer.add(button, BorderLayout.SOUTH);

        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }

}