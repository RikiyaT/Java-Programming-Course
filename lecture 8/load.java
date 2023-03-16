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
public class load {
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
        JButton zibutton = new JButton("Load");
        zibutton.setPreferredSize(new Dimension(20, 10));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);


        zibutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.loadImage(textArea.getText());
            }
        });


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(textArea);
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(zibutton, BorderLayout.SOUTH);

        return contentPane;
    }
}

class MyCanvas extends Canvas {
    private BufferedImage image;
    public void loadImage(String urlString) {
        try {
            image = ImageIO.read(new URL(urlString));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(this.image, 0, 0, this);
    }
}