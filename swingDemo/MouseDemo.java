
public class MouseDemo extends JFrame implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = -4584282627764877759L;
    int flag, x, y, startX, startY, endX, endY;

    public MouseDemo() {
        Container container = getContentPane();
        container.addMouseListener(this);
        container.addMouseMotionListener(this);
        setSize(200, 200);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        flag = 2;
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        flag = 1;
        x = e.getX();
        y = e.getY();
        repaint();
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        if (flag == 1) {
            g.drawString("鼠标坐标：(" + x + ", " + y + ")", 10, 50);
            g.drawLine(startX, startY, endX, endY);
        }
        else if (flag == 2) {
            g.drawString("拖拽鼠标坐标：(" + x + ", " + y + ")", 10, 50);
            g.drawLine(startX, startY, endX, endY);
        }
    }
}