import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class trim2{
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
        canvasPanel.drawRectangle(new Rectangle(Math.min(startX,e.getX()), Math.min(startY,e.getY()), Math.abs(startX - e.getX()), Math.abs(startY - e.getY())));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasPanel.trim(new Rectangle(Math.min(startX,e.getX()), Math.min(startY,e.getY()), Math.abs(startX - e.getX()), Math.abs(startY - e.getY())));

    }
}

class CanvasPanel extends JPanel {
    /**
     * 位置およびサイズが確定した図形のみが描かれた画像。
     * An image containing shapes whose locations and sizes are fixed.
     */
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

    /**
     * 破線を描くための BasicStroke インスタンス。
     * A BasicStroke instance for drawing a dotted line.
     */
    private static final BasicStroke DASHED_LINE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{7, 3}, 0);

    public CanvasPanel() {
        setName("canvas");
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Kagami_mochi_%28Japanese_New_Year_decoration%29%3B_January_2020.jpg/320px-Kagami_mochi_%28Japanese_New_Year_decoration%29%3B_January_2020.jpg");
            image = ImageIO.read(url);
            imageInProgress = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 指定した位置とサイズの矩形を描画する。Draw a rectangle with the given location and size.
     *
     * @param rectangle  矩形を描画する位置とサイズ。 A position and size of a rectangle to be drawn.
     */
    public void drawRectangle(Rectangle rectangle) {
        isInProgress = true;

        Graphics2D g = imageInProgress.createGraphics();
        // "image" の内容を "imageInProgress" にコピーして、描画中の図形を消す。
        // Copy the content of "image" to "imageInProgress" to clear the square drawing in progress.
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.setStroke(DASHED_LINE);

        g.drawRect(rectangle.x, rectangle.y, rectangle.width-1, rectangle.height-1);

        repaint();
    }

    public void trim(Rectangle rectangle) {
        Graphics2D g =image.createGraphics();
        isInProgress = false;
        int recx=rectangle.x+rectangle.width;
        int recy=rectangle.y+rectangle.height;
        g.setColor(Color.WHITE);

        for(int i=0; i<=image.getWidth(); i++){
            for(int j=0; j<=image.getHeight(); j++){
                if(i<rectangle.x||j<rectangle.y||i >= recx ||j >= recy){
                    g.fillRect(i, j, 1, 1);
                }
            }
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(isInProgress ? imageInProgress : image, 0, 0, this);
    }
}