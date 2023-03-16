import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class redooo{
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
    private int count=0;    //ここでカウントとリミットを定義
    private int limit=0;

    private JPanel createContentPane() {
        CanvasPanel canvas = new CanvasPanel();
        JButton undobutton = new JButton("Undo");
        JButton redobutton = new JButton("Redo");

        //count と limit に応じて押せる押せないを調整
        if(count>0){              //countが１以上ならundo押せる
            undobutton.setEnabled(true);
        }else{
            undobutton.setEnabled(false);
        }
        if(count<limit){          //countがlimitと同じ数出なければredo押せる
            redobutton.setEnabled(true);
        }else{
            redobutton.setEnabled(false);
        }


        undobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                canvas.undobut(count);
                count--;         //undoしたらcountが下がる
            }
        });

        redobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                canvas.redobut(count);
                count++;        //redoしたらcountが上がる
            }
        });
        
        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                int x=me.getX();
                int y=me.getY();
                canvas.paintcomponent(x, y, count);
                count++;        //書いたらcountとlimitが上がる
                limit++;
            } 
        }); 

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(undobutton, BorderLayout.NORTH);
        contentPane.add(redobutton, BorderLayout.SOUTH);
        contentPane.add(canvas, BorderLayout.CENTER);
        return contentPane;
    }
}

class CanvasPanel extends JPanel {
    private BufferedImage image;
    int a[]= new int[10000];
    int b[]=new int[10000];




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

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void paintcomponent(int x, int y, int count){
        image.setRGB(x, y, Color.BLACK.getRGB());
        this.repaint();
        a[count]=x;
        b[count]=y;
    }

    public void undobut(int count){
        image.setRGB(a[count], b[count], Color.WHITE.getRGB());
        this.repaint();
    }

    public void redobut(int count){
        image.setRGB(a[count+1], b[count+1], Color.BLACK.getRGB());
        this.repaint();
    }

}