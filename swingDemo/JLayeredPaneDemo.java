
public class JLayeredPaneDemo extends JFrame {
    private static final long serialVersionUID = 1L;

    public JLayeredPaneDemo() {
        super("JLayeredPanel");
        Integer[] layerConstants = { JLayeredPane.DEFAULT_LAYER,
            JLayeredPane.PALETTE_LAYER, new Integer(101),
            JLayeredPane.MODAL_LAYER, new Integer(201),
            JLayeredPane.POPUP_LAYER, JLayeredPane.DRAG_LAYER };
        Color[] colors = { Color.red, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.green, Color.pink };
        Point position = new Point(10, 10);
        JLabel[] labels = new JLabel[7];
        JLayeredPane layeredPane = getLayeredPane();

        for (int i = 0; i < 7; i++) {
            labels[i] = createLabel("layer " + i, colors[i], position);
            position.x = position.x + 20;
            position.y = position.y + 20;
            layeredPane.add(labels[i], layerConstants[i], 0);
        }

        setSize(280, 280);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private JLabel createLabel(String content, Color color, Point position) {
        JLabel label = new JLabel(content, JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setOpaque(true);
        label.setBounds(position.x, position.y, 100, 100);
        return label;
    }
}