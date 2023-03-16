import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class pen{
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

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        JComboBox<Integer> comboBox = new JComboBox<>();

        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(5);
        contentPane.add(comboBox, BorderLayout.SOUTH);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setPenSize((int)comboBox.getSelectedItem());
            }
        });

        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        return contentPane;
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
    private final Graphics2D graphics;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(Color.BLACK);
    }

    public void setPenSize(int size) {
        BasicStroke wideStroke = new BasicStroke((float)size);
        graphics.setStroke(wideStroke);
    }



    public void drawLine(int startX, int startY, int endX, int endY) {
        graphics.drawLine(startX, startY, endX, endY);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}