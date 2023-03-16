import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// Please add missing import statements here

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class canvas {
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
        super("Problem2");
        this.setContentPane(this.createContentPane());
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel createContentPane() {
        MyCanvas canvas = new MyCanvas();
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        return contentPane;
    }
}

class MyCanvas extends Canvas {
    URL url = null;
    private final Image image;

    public MyCanvas() {
        try {
            image = ImageIO.read(new URL(
      "https://docs.oracle.com/javase/tutorial/2d/images/examples/strawberry.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image, 0, 0, this);
    }
}