/**
 * BoxLayout 只有两种排列：水平、垂直
 * 使用水平的 BoxLayout 时会强制组件等高，且不会自动换行（当 container 装不下时）
 */
public class BoxLayoutDemo {

    BoxLayoutDemo() {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        Box baseBox = Box.createHorizontalBox();
        container.add(baseBox);
        baseBox.add(new JButton("A"));
        baseBox.add(new JButton("B"));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("BoxLayoutDemo");
        frame.pack();
        frame.setVisible(true);
    }
    
}