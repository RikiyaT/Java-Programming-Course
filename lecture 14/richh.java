import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class richh{
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
        // Don't change the size!
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        contentPane.add(rightPanel, BorderLayout.EAST);

        MouseListener listener = new MouseListener(canvas);
        MouseListener2 listener2 = new MouseListener2(canvas);


        JComboBox<Integer> comboBox = new JComboBox<>();

        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(5);
        contentPane.add(comboBox, BorderLayout.NORTH);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setPenSize((int)comboBox.getSelectedItem());
            }
        });


        JRadioButton pen, rFill, cFill, rOut, cOut;
        pen.setName("Pen");
        rFill.setName("RectangleFill");
        cFill.setName("CircleFill");
        rOut.setName("RectangleOutline");
        cOut.setName("CircleOutline");
        ButtonGroup bg = new ButtonGroup();
        bg.add(pen);
        bg.add(rFill);
        bg.add(cFill);
        bg.add(rOut);
        bg.add(cOut);
        rightPanel.add(pen);
        rightPanel.add(rFill);
        rightPanel.add(cFill);
        rightPanel.add(rOut);
        rightPanel.add(cOut);

        pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.Pen);
                canvas.addMouseListener(listener);
                canvas.addMouseMotionListener(listener);
            }
        });
        rFill.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.RectangleFill);
                canvas.addMouseListener(listener2);
                canvas.addMouseMotionListener(listener2);
            }
        });
        cFill.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.CircleFill);
                canvas.addMouseListener(listener2);
                canvas.addMouseMotionListener(listener2);
            }
        });
        rOut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.RectangleOutline);
                canvas.addMouseListener(listener2);
                canvas.addMouseMotionListener(listener2);
            }
        });
        cOut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.CircleOutline);
                canvas.addMouseListener(listener2);
                canvas.addMouseMotionListener(listener2);
            }
        });

        JRadioButton c1, c2, c3, c4, c5;
        c1.setName("Black");
        c2.setName("White");
        c3.setName("Red");
        c4.setName("Green");
        c5.setName("Blue");
        ButtonGroup cg = new ButtonGroup();
        cg.add(c1);
        cg.add(c2);
        cg.add(c3);
        cg.add(c4);
        cg.add(c5);
        JPanel p = new JPanel();
        p.add(c1);
        p.add(c2);
        p.add(c3);
        p.add(c4);
        p.add(c5);

        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setColor(Color.BLACK);
            }
        });
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setColor(Color.WHITE);
            }
        });
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setColor(Color.RED);
            }
        });
        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setColor(Color.GREEN);
            }
        });
        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setColor(Color.BLUE);
            }
        });

        contentPane.add(p, BorderLayout.SOUTH);



        return contentPane;
    }
}

enum DrawingMode {
    Pen,
    RectangleFill,
    CircleFill,
    RectangleOutline,
    CircleOutline,
}


class MouseListener2 extends MouseAdapter {
    private final CanvasPanel canvasPanel;
    private int startX;
    private int startY;

    MouseListener2(CanvasPanel canvas) {
        canvasPanel = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvasPanel.drawShape(new Rectangle(Math.min(startX, e.getX()), Math.min(startY, e.getY()), Math.abs(startX-e.getX()), Math.abs(startY-e.getY())), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasPanel.drawShape(new Rectangle(Math.min(startX, e.getX()), Math.min(startY, e.getY()), Math.abs(startX-e.getX()), Math.abs(startY-e.getY())), false);
    }
}


class MouseListener extends MouseAdapter {
    private final CanvasPanel canvasPanel;
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
    private final BufferedImage imageInProgress;
    private boolean isInProgress;
    private DrawingMode mode;
    private Color color;
    private BasicStroke wideStroke;

    public void setColor(Color col) {
        color=col;
    }


    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        imageInProgress = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public void setPenSize(int size) {
        wideStroke = new BasicStroke((float)size);
    }

    public void setDrawingMode(DrawingMode newMode) {
        mode = newMode;
    }

    public void drawLine(int startX, int startY, int endX, int endY) {
        Graphics2D g = image.createGraphics();
        
        g.setColor(color);
        g.setStroke(wideStroke);
        if(mode==DrawingMode.Pen){
            g.drawLine(startX, startY, endX, endY);
        }
        repaint();
    }

    public void drawShape(Rectangle rectangle, boolean inProgress) {
        isInProgress = inProgress;
        Graphics2D g = (inProgress ? imageInProgress : image).createGraphics();
        if (inProgress) g.drawImage(image, 0, 0, this);
        g.setStroke(wideStroke);
        if(color==Color.BLACK){
            g.setColor(Color.BLACK);
        }
        if(color==Color.WHITE){
            g.setColor(Color.WHITE);
        }
        if(color==Color.RED){
            g.setColor(Color.RED);
        }
        if(color==Color.GREEN){
            g.setColor(Color.GREEN);
        }
        if(color==Color.BLUE){
            g.setColor(Color.BLUE);
        }

        if(mode==DrawingMode.RectangleFill){
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if(mode==DrawingMode.CircleFill){
            g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if(mode==DrawingMode.RectangleOutline){
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if(mode==DrawingMode.CircleOutline){
            g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(isInProgress ? imageInProgress : image, 0, 0, this);
    }
}