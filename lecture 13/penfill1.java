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
        if(r+g+b==0){
            int color1=image.getRGB(x+1, y);
            int r1 = ( color1 >> 16 ) & 0xff;
            int g1 = ( color1 >> 8 ) & 0xff;
            int b1 = color1 & 0xff;
            int color2=image.getRGB(x, y+1);
            int r2 = ( color2 >> 16 ) & 0xff;
            int g2 = ( color2 >> 8 ) & 0xff;
            int b2 = color2 & 0xff;
            int color3=image.getRGB(x-1, y);
            int r3 = ( color3 >> 16 ) & 0xff;
            int g3 = ( color3 >> 8 ) & 0xff;
            int b3 = color3 & 0xff;
            int color4=image.getRGB(x, y-1);
            int r4 = ( color4 >> 16 ) & 0xff;
            int g4 = ( color4 >> 8 ) & 0xff;
            int b4 = color4 & 0xff;
            while(r1+g1+b1==0){
                
            }

        }
        

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
