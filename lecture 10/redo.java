import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class redo{
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
        UndoManager undoManager = new UndoManager();
        Vector pointVector = new Vector();
        JButton undobutton = new JButton("Undo");
        JButton redobutton = new JButton("Redo");
        undobutton.setEnabled(true);
        redobutton.setEnabled(true);

        undobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    undoManager.undo();
                } catch (CannotRedoException cre) {
                    cre.printStackTrace();
                }
                canvas.undobut();
            }
        });

        redobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    undoManager.redo();
                } catch (CannotRedoException cre) {
                    cre.printStackTrace();
                }
                canvas.redobut();
            }
        });
        
        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                int x=me.getX();
                int y=me.getY();
                Point point = new Point(me.getX(), me.getY());
                canvas.paintcomponent(x, y);

                undoManager.undoableEditHappened(new UndoableEditEvent(
                    canvas, new UndoablePaintSquare(point, pointVector)));
            } 
        }); 

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(undobutton, BorderLayout.NORTH);
        contentPane.add(redobutton, BorderLayout.SOUTH);
        contentPane.add(canvas, BorderLayout.CENTER);
        return contentPane;
    }
    class UndoablePaintSquare extends AbstractUndoableEdit {
        protected Vector points;
    
        protected Point point;
    
        public UndoablePaintSquare(Point p, Vector v) {
          points = v;
          point = p;
        }
    
        public String getPresentationName() {
          return "Square Addition";
        }
    
        public void undo() {
          super.undo();
          points.remove(point);
        }
    
        public void redo() {
          super.redo();
          points.add(point);
        }
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

    public void undobut(){
        repaint();

    }

    public void redobut(){
          repaint();

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}