import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * BorderLayout 将版面划分为东西南北中五个区域
 * FlowLayout 将组件一个接一个向右排列，自动换行。可以使用FlowLayout 带参数的构造器，设置排列间距和排列对齐方向
 * GridLayout 比 FlowLayout 多了行和列的设置
 * CardLayout 的效果如多张卡片叠在一起，一次只能看到一张，但可以任意抽一张到表面
 */
class CardLayoutDemo {

    private JFrame frame;
    private JPanel p1, p2, p3, p4;
    private int curCard = 0;

    App() {
        frame = new JFrame("Card Layout Demo");
        frame.setSize(250, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));

        ActionListener actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (curCard) {
                    case 0:
                        ((CardLayout)p4.getLayout()).show(p4, "two");
                        curCard = 1;
                        break;
                    case 1:
                        ((CardLayout)p4.getLayout()).show(p4, "one");
                        curCard = 0;
                        break;
                }
                frame.validate();
            }
        };

        p1 = new JPanel();
        Button b = new Button("Change Card");
        b.addActionListener(actionListener);
        p1.add(b);
        contentPane.add(p1);

        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(new JButton("1"));
        p2.add(new JButton("2"));
        p2.add(new JButton("3"));

        p3 = new JPanel();
        p3.setLayout(new GridLayout(3, 1));
        p3.add(new JButton("4"));
        p3.add(new JButton("5"));
        p3.add(new JButton("6"));

        p4 = new JPanel();
        p4.setLayout(new CardLayout());
        p4.add("one", p2);
        p4.add("two", p3);
        ((CardLayout)p4.getLayout()).show(p4, "one");
        contentPane.add(p4);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
        BeautyEyeLNFHelper.launchBeautyEyeLNF();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CardLayoutDemo();
            }
        });
    }

}