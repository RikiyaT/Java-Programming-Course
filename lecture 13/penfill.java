import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class penfill{
    public static void main(String[] args) {
        showWindow();
    }

    // DON'T CHANGE THE METHOD SIGNATURE OF showWindow()
    public static MainWindow showWindow() {
        return new MainWindow();
    }
}
/*** I DON'T RECOMMEND TO CHANGE THE ABOVE CODE (EVEN THOUGH YOU CAN) ***/

class MainWindow extends JFrame {
    public MainWindow() {
        super("Paint Tool");
        setContentPane(createContentPane());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        MouseListener1 listener1= new MouseListener(canvas);
        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JRadioButton r1=new JRadioButton("Pen", true);
        JRadioButton r2=new JRadioButton("Fill");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        JPanel p = new JPanel();
        p.add(r1);
        p.add(r2);
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.addMouseListener(listener);
                canvas.addMouseMotionListener(listener);
            }
        });
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.addMouseListener(listener1);
            }
        });
        contentPane.add(p, BorderLayout.NORTH);

        return contentPane;
    }
}

class MouseListener1 extends MouseAdapter {
    private final CanvasPanel canvasPanel;

    MouseListener(CanvasPanel canvas) {
        canvasPanel = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvasPanel.Fill(e.getX(), e.getY());
    }

}

class MouseListener extends MouseAdapter {
    private final CanvasPanel canvasPanel;

    // Store the most recent X and Y of `mousePressed` or `mousePressed`.
    // 最も最近の `mousePressed` もしくは `mousePressed` のXとYを保存する。
    private int lastX;
    private int lastY;

    MouseListener(CanvasPanel canvas) {
        canvasPanel = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX=e.getX();
        lastY=e.getY();   
        canvasPanel.drawLine(lastX, lastY, lastX, lastY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx=e.getX();
        int dy=e.getY();

        canvasPanel.drawLine(lastX, lastY, dx, dy);
        lastX=e.getX();
        lastY=e.getY();   
    }
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public void drawLine(int startX, int startY, int endX, int endY) {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.drawLine(startX, startY, endX, endY);
        repaint();
    }

    public void fill(int x, int y) {
        int color=image.getRGB(x, y);
        int r = ( color >> 16 ) & 0xff;
        int g = ( color >> 8 ) & 0xff;
        int b = color & 0xff;
        int trainx, trainy;
        if(r+g+b==0){
            int x1=x;
            int y1=y;
            int color1==image.getRGB(x, y);
            int r1 = ( color >> 16 ) & 0xff;
            int g1 = ( color >> 8 ) & 0xff;
            int b1 = color & 0xff;

            while(r1+g1+b1=0){
                image.setRGB(x1,y1,Color.BLACK.getRGB());
                color1=image.getRGB(x1+1, y1);
                r1 = ( color >> 16 ) & 0xff;
                g1 = ( color >> 8 ) & 0xff;
                b1 = color & 0xff;
                x1++;
            }

            int x2=x;
            int y2=y;
            int color2==image.getRGB(x, y);
            int r2 = ( color >> 16 ) & 0xff;
            int g2 = ( color >> 8 ) & 0xff;
            int b2 = color & 0xff;
            while(r2+g2+b2=0){
                image.setRGB(x2,y2,Color.BLACK.getRGB());
                color2=image.getRGB(x1+1, y1);
                r2 = ( color >> 16 ) & 0xff;
                g2 = ( color >> 8 ) & 0xff;
                b2 = color & 0xff;
                y2++;
            }
            
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
