import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class binarize{
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

        JButton button = new JButton("Binarize");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.binarize();
            }
        });
        contentPane.add(button, BorderLayout.SOUTH);

        return contentPane;
    }
}

class CanvasPanel extends JPanel {
    /**
     * 位置およびサイズが確定した図形のみが描かれた画像。
     * An image containing shapes whose locations and sizes are fixed.
     */
    private final BufferedImage image;

    public CanvasPanel() {
        setName("canvas");
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Kagami_mochi_%28Japanese_New_Year_decoration%29%3B_January_2020.jpg/320px-Kagami_mochi_%28Japanese_New_Year_decoration%29%3B_January_2020.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void binarize() {
        int width = image.getWidth();
        int height = image.getHeight();

        for(int y=0;y < height;y++){
            for(int x=0;x < width;x++){
                int color=image.getRGB(x, y);
                int a = color >> 24;
                int r = ( color >> 16 ) & 0xff;
                int g = ( color >> 8 ) & 0xff;
                int b = color & 0xff;
                if((r+g+b)<384){
                    image.setRGB(x,y,Color.BLACK.getRGB());
                }else{
                    image.setRGB(x,y,Color.WHITE.getRGB());
                }
            }
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
