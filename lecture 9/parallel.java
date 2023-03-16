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
public class parallel{
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

        super("Problem1");

        this.setVisible(true);

        this.setSize(320, 240);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(this.createContentPane());
    }

    private JPanel createContentPane() {
        MyCanvas canvas = new MyCanvas();
        JButton leftbutton = new JButton("Left");
        JButton rightbutton = new JButton("Right");
        JButton upbutton = new JButton("Up");
        JButton downbutton = new JButton("Down");


        leftbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(-1, 0);
            }
        });

        rightbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(1, 0);
            }
        });

        upbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(0, 1);
            }
        });

        downbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.shift(0, -1);
            }
        });


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(leftbutton, BorderLayout.WEST);
        contentPane.add(rightbutton, BorderLayout.EAST);
        contentPane.add(upbutton, BorderLayout.NORTH);
        contentPane.add(downbutton, BorderLayout.SOUTH);

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

    public void shift(int dx, int dy) {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, this.image.getType());

        if(dy==1){
            for(int y=0; y<height-1; y++){
                for(int x=0; x<width; x++){
                    newImage.setRGB(x, y, this.image.getRGB(x, y+1));
                }
            }

            for(int x=0; x<width; x++){
                newImage.setRGB(x, height - 1, Color.WHITE.getRGB());
            }

        }

        if(dy==-1){
            for(int y=height-1; y>0; y--){
                for(int x=0; x<width; x++){
                    newImage.setRGB(x, y, this.image.getRGB(x, y-1));
                }
            }

            for(int x=0; x<width; x++){
                newImage.setRGB(x, 0, Color.WHITE.getRGB());
            }

        }

        if(dx==-1){
            for(int x=0; x<width-1; x++){
                for(int y=0; y<height; y++){
                    newImage.setRGB(x, y, this.image.getRGB(x+1, y));
                }
            }

            for(int y=0; y<height; y++){
                newImage.setRGB(width-1, y, Color.WHITE.getRGB());
            }
        }

        if(dx==1){
            for(int x=width-1; x>0; x--){
                for(int y=0; y<height; y++){
                    newImage.setRGB(x, y, this.image.getRGB(x-1, y));
                }
            }

            for(int y=0; y<height; y++){
                newImage.setRGB(0, y, Color.WHITE.getRGB());
            }

        }


        this.image = newImage;
        this.repaint();
    }
}
