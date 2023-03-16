import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class rec1ex{
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
        canvas.addMouseListener(new MouseAdapter() {
            int startX;
            int startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas.drawRectangle(startX, startY, e.getX(), e.getY());
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        return contentPane;
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

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
        g.dispose();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}