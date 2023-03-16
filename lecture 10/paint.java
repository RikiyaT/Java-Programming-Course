import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.applet.Applet;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class paint{
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
        this.setContentPane(this.createContentPane());
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        
        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                int x=me.getX();
                int y=me.getY();
                canvas.paintcomponent(x, y);
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
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        for(int y=0;y < height;y++){
            for(int x=0;x < width;x++){
                image.setRGB(x,y,Color.WHITE.getRGB());
            }
        }
        
    }

    public void paintcomponent(int x, int y){
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        
        image.setRGB(x, y, Color.BLACK.getRGB());

        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
