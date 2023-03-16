import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class reccir2{
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
        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        contentPane.add(rightPanel, BorderLayout.EAST);

        JRadioButton r1=new JRadioButton("Rectangle (Fill)",true);
        JRadioButton r2=new JRadioButton("Circle (Fill)");
        JRadioButton r3=new JRadioButton("Rectangle (Outline)");
        JRadioButton r4=new JRadioButton("Circle (Outline)");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        rightPanel.add(r1);
        rightPanel.add(r2);
        rightPanel.add(r3);
        rightPanel.add(r4);

        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.RectangleFill);
            }
        });
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.CircleFill);
            }
        });
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.RectangleOutline);
            }
        });
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.setDrawingMode(DrawingMode.CircleOutline);
            }
        });


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
        startX=e.getX();
        startY=e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvasPanel.drawShape(new Rectangle(Math.min(startX,e.getX()), Math.min(startY,e.getY()), Math.abs(startX - e.getX()), Math.abs(startY - e.getY())), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasPanel.drawShape(new Rectangle(Math.min(startX,e.getX()), Math.min(startY,e.getY()), Math.abs(startX - e.getX()), Math.abs(startY - e.getY())),false);
    }
}

enum DrawingMode {
    RectangleFill,
    CircleFill,
    RectangleOutline,
    CircleOutline,
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;

    /**
     * マウスをドラッグしている最中に表示する画像。
     * An image to be shown during the mouse is being dragged.
     */
    private final BufferedImage imageInProgress;

    /**
     * マウスをドラッグしている最中か否かを表す値。
     * A boolean value indicating whether the mouse is being dragged or not.
     */
    private boolean isInProgress;

    private DrawingMode mode;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        imageInProgress = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public void setDrawingMode(DrawingMode newMode) {
        mode = newMode;
    }

    /**
     * 指定した位置とサイズの図形を描画する。Draw a shape with the given location and size.
     *
     * @param rectangle  図形を描画する位置とサイズ。 A position and size of a shape to be drawn.
     * @param inProgress マウスをドラッグしている最中か否か。A boolean value indicating whether the mouse is being dragged or not.
     */
    public void drawShape(Rectangle rectangle, boolean inProgress) {
        isInProgress = inProgress;
        Graphics2D g = (inProgress ? imageInProgress : image).createGraphics();
        if (inProgress) {
            g.drawImage(image, 0, 0, this);
        }
        g.setColor(Color.BLACK);
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
