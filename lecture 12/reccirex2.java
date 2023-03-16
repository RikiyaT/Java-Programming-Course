import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class reccirex2{
    public static void main(String[] args) {
        showWindow();
    }

    public static MainWindow showWindow() {
        return new MainWindow();
    }
}

class MainWindow extends JFrame {
    public MainWindow() {
        super("Paint Tool");
        setContentPane(createContentPane());
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        contentPane.add(rightPanel, BorderLayout.EAST);

        JRadioButton rButton = new JRadioButton("Rectangle", true);
        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.Rectangle);
            }
        });
        JRadioButton cButton = new JRadioButton("Circle");
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.Circle);
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(rButton);
        group.add(cButton);
        rightPanel.add(rButton);
        rightPanel.add(cButton);

        JRadioButton c1=new JRadioButton("Black", true);
        JRadioButton c2=new JRadioButton("White");
        JRadioButton c3=new JRadioButton("Red");
        JRadioButton c4=new JRadioButton("Green");
        JRadioButton c5=new JRadioButton("Blue");  
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

        contentPane.add(p, BorderLayout.NORTH);

        return contentPane;
    }
}

class MouseListener extends MouseAdapter {
    private final CanvasPanel canvasPanel;
    private int startX;
    private int startY;

    MouseListener(CanvasPanel canvas) {
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

enum DrawingMode {
    Rectangle,
    Circle
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;
    private final BufferedImage imageInProgress;
    private boolean isInProgress;
    private DrawingMode mode;

    public void setColor(Color color) {
        Graphics2D g = image.createGraphics();
        g.setColor(color);
    }


    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        imageInProgress = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        setDrawingMode(DrawingMode.Rectangle);
    }

    public void setDrawingMode(DrawingMode newMode) {
        mode = newMode;
    }

    public void drawShape(Rectangle rectangle, boolean inProgress) {
        isInProgress = inProgress;
        Graphics2D g = (inProgress ? imageInProgress : image).createGraphics();
        if (inProgress) g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        if (mode == DrawingMode.Rectangle) g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (mode == DrawingMode.Circle) g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.dispose();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(isInProgress ? imageInProgress : image, 0, 0, this);
    }
}