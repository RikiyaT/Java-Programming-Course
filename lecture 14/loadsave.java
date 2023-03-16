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
import java.net.URL;
import java.io.IOException;


/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class loadsave{
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

        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        contentPane.add(textArea, BorderLayout.SOUTH);

        JButton load = new JButton("Load");

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.loadImage(textArea.getText());
            }
        });


        JButton save = new JButton("Save");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.saveImageAsPng(textArea.getText());
            }
        });

        contentPane.add(load, BorderLayout.NORTH);
        contentPane.add(save, BorderLayout.EAST);

        MouseListener listener = new MouseListener(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        return contentPane;
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
    private final Graphics2D graphics;

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(Color.BLACK);
    }

    public void drawLine(int startX, int startY, int endX, int endY) {
        graphics.drawLine(startX, startY, endX, endY);
        repaint();
    }

    public void loadImage(String fileName) {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, this.image.getType());
        try {
            newImage = ImageIO.read(new File(fileName));
            graphics.drawImage(newImage, 0, 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveImageAsPng(String fileName) {
        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
