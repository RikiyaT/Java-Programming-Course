import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class rec1{
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
        canvas.addMouseListener(new MouseAdapter() {
            int startX;
            int startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX=e.getX();
                startY=e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endX=e.getX();
                int endY=e.getY();
                canvas.drawRectangle(startX, startY, endX, endY);
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

    /**
     * Draws a rectangle filled with black color between (x1, y1) and (x2, y2).
     * Note that you cannot expect x1 <= x2 and/or y1 <= y2 do not always hold.
     * (x1, y1) から (x2, y2) の範囲で黒色で塗りつぶした長方形を描画する。
     * x1 <= x2 や y1 <= y2 が常に成りつ立つわけではないことに注意せよ。
     */
    public void drawRectangle(int x1, int y1, int x2, int y2) {
        image.setRGB(x1, y1, Color.BLACK.getRGB());
        image.setRGB(x2, y2, Color.BLACK.getRGB());
        int maxx, maxy, minx, miny;
        if(x1<x2){
            if(y1<y2){
                maxx=x2;
                maxy=y2;
                minx=x1;
                miny=y1;
            }else{
                maxx=x2;
                maxy=y1;
                minx=x1;
                miny=y2;
            }
        }else{
            if(y1<y2){
                maxx=x1;
                maxy=y2;
                minx=x2;
                miny=y1;
            }else{
                maxx=x1;
                maxy=y1;
                minx=x2;
                miny=y2;
            }
        }
        for(int x=minx; x<maxx; x++){
            for(int y=minx; y<maxx; y++){
                image.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
