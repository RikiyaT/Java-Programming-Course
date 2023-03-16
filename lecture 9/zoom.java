import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Please add missing import statements here

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class zoom {
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
    public MainWindow(){

        super("Problem3");

        this.setSize(320, 240);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(this.createContentPane());

        this.setVisible(true);
    }

    private JPanel createContentPane() {
        MyCanvas canvas = new MyCanvas();
        JButton zibutton = new JButton("Zoom In");
        zibutton.setPreferredSize(new Dimension(20, 10));
        JButton zobutton = new JButton("Zoom Out");
        zobutton.setPreferredSize(new Dimension(20, 10));


        zibutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.zoomin();
            }
        });

        zobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.zoomout();
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(zibutton, BorderLayout.WEST);
        contentPane.add(zobutton, BorderLayout.EAST);

        return contentPane;
    }
}

class MyCanvas extends Canvas {
    URL url = null;
    private BufferedImage image;

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
        g.drawImage(this.image, 0, 0, this);
    }

    public void zoomin() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        BufferedImage newImage = new BufferedImage(2*width, 2*height, this.image.getType());

        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                newImage.setRGB(2*x, 2*y, this.image.getRGB(x, y));
                newImage.setRGB(2*x+1, 2*y, this.image.getRGB(x, y));
                newImage.setRGB(2*x, 2*y+1, this.image.getRGB(x, y));
                newImage.setRGB(2*x+1, 2*y+1, this.image.getRGB(x, y));
            }
        }

        this.image = newImage;
        this.repaint();
    }

    public void zoomout() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        BufferedImage newImage = new BufferedImage(width/2, height/2, this.image.getType());

        for(int x=0; x<width/2; x++){
            for(int y=0; y<height/2; y++){
                newImage.setRGB(x, y, this.image.getRGB(2*x, 2*y));
            }
        }

        this.image = newImage;
        this.repaint();
    }
}