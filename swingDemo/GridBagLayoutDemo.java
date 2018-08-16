
/**
 * 支持设置组件位置、大小，完全自定义
 * 必须配合 GridBagConstraints
 */
public class GridBagLayoutDemo {

    GridBagLayoutDemo() {
        
        JFrame frame = new JFrame();
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(gridBagLayout);
        
        GridBagConstraints c;
        JButton b = new JButton("first");

        c = new GridBagConstraints(0, 0, 1, 1, 10, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(b, c);
        contentPane.add(b);

        b = new JButton("second");
        c = new GridBagConstraints(2, 1, 1, 2, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        contentPane.add(b, c);

        frame.setTitle("GridBagLayoutDemo");
        frame.pack();
        frame.setVisible(true);            
    }

}