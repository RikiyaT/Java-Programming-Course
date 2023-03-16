import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class rich{
    public static void main(String[] args) {
        showWindow();
    }

    public static MainWindow showWindow() {
        return new MainWindow();
    }
}

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

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        contentPane.add(rightPanel, BorderLayout.EAST);
        
        JComboBox<Integer> comboBox = new JComboBox<Integer>();
        
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(5);
        
        rightPanel.add(comboBox);
        
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object size = comboBox.getSelectedItem();
        		canvas.setPenSize((int) size);
            }
        });
        
        
        ButtonGroup bg = new ButtonGroup();
        JRadioButton Pen = new JRadioButton("Pen", true);
        Pen.setName("Pen");
        
        Pen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.DrawLine);
            }
        });
        
        JRadioButton Rec = new JRadioButton("Rectangle (Fill)");
        Rec.setName("RectangleFill");
        
        Rec.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.RectangleFill);
            }
        });
        
        JRadioButton Cir = new JRadioButton("Circle (Fill)");
        Cir.setName("CircleFill");
        
        Cir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.CircleFill);
            }
        });
        
        JRadioButton RecLine = new JRadioButton("Rectangle (Outline)");
        RecLine.setName("RectangleOutline");
        
        RecLine.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.RectangleOutline);
            }
        });
        
        JRadioButton CirLine = new JRadioButton("Circle (Outline)");
        CirLine.setName("CircleOutline");
        
        CirLine.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingMode(DrawingMode.CircleOutline);
            }
        });
        
        bg.add(Pen);
        bg.add(Rec);
        bg.add(Cir);
        bg.add(RecLine);
        bg.add(CirLine);
        rightPanel.add(Pen);
        rightPanel.add(Rec);
        rightPanel.add(Cir);
        rightPanel.add(RecLine);
        rightPanel.add(CirLine);
        
        ButtonGroup bgroup_cl = new ButtonGroup();
        JRadioButton radioBLACK = new JRadioButton("Black", true);
        radioBLACK.setName("Black");
        
        JRadioButton radioWHITE = new JRadioButton("White");
        radioWHITE.setName("White");
        JRadioButton radioRED = new JRadioButton("Red");
        radioRED.setName("Red");
        JRadioButton radioGREEN = new JRadioButton("Green");
        radioGREEN.setName("Green");
        JRadioButton radioBLUE = new JRadioButton("Blue");
        radioBLUE.setName("Blue");
        
        radioBLACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                canvas.setDrawingColor(Color.BLACK);
            }
        });
        radioWHITE.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		canvas.setDrawingColor(Color.WHITE);
            }
        });
        
        radioRED.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		canvas.setDrawingColor(Color.RED);
        	}
        });

        radioGREEN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		canvas.setDrawingColor(Color.GREEN);
        	}
        });
        
        radioBLUE.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		canvas.setDrawingColor(Color.BLUE);
        	}
        });
        
        bgroup_cl.add(radioBLACK);
        bgroup_cl.add(radioWHITE);
        bgroup_cl.add(radioRED);
        bgroup_cl.add(radioGREEN);
        bgroup_cl.add(radioBLUE);
        
        rightPanel.add(radioBLACK);
        rightPanel.add(radioWHITE);
        rightPanel.add(radioRED);
        rightPanel.add(radioGREEN);
        rightPanel.add(radioBLUE);
        
        return contentPane;
    }
    
}

class MouseListener extends MouseAdapter {
    private final CanvasPanel canvasPanel;
    private int startX;
    private int startY;
    private int lastX;
    private int lastY;
    
    MouseListener(CanvasPanel canvas) {
        canvasPanel = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        
        if(canvasPanel.getDrawingMode() == DrawingMode.DrawLine) {
        	canvasPanel.drawLine(startX, startY, startX, startY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	lastX = e.getX();
        lastY = e.getY();
        
        Rectangle rec = new Rectangle(
        		Math.min(startX,lastX), 
        		Math.min(startY, lastY), 
        		Math.abs(startX - lastX), 
        		Math.abs(startY - lastY)
        		);
        
        if (canvasPanel.getDrawingMode() == DrawingMode.DrawLine) {
        	canvasPanel.drawLine(startX, startY, lastX, lastY);
        	
        	startX = lastX;
        	startY = lastY;
        	
        } else {
        	canvasPanel.drawShape(rec, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	lastX = e.getX();
        lastY = e.getY();
        
        Rectangle rec = new Rectangle(
        		Math.min(startX,lastX), 
        		Math.min(startY, lastY), 
        		Math.abs(startX - lastX), 
        		Math.abs(startY - lastY)
        		);
        
        if (canvasPanel.getDrawingMode() != DrawingMode.DrawLine) {
        	canvasPanel.drawShape(rec, false);
        }
    }
}

enum DrawingMode {
    RectangleFill,
    CircleFill,
    RectangleOutline,
    CircleOutline,
    DrawLine,
}

class CanvasPanel extends JPanel {
    private final BufferedImage image;

    private final BufferedImage imageInProgress;

    private boolean isInProgress;

    private DrawingMode mode = DrawingMode.DrawLine;
    private Color drawingColor = Color.BLACK;
    private BasicStroke PenSize = new BasicStroke(1);

    public CanvasPanel() {
        setName("canvas");
        image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        imageInProgress = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public void setDrawingMode(DrawingMode newMode) {
        mode = newMode;
    }
    
    public DrawingMode getDrawingMode() {
        return mode;
    }
    
    public void setDrawingColor(Color newColor) {
         drawingColor= newColor;
    }

    /**
     * @param rectangle
     * @param inProgress
     */
    
    public void drawShape(Rectangle rectangle, boolean inProgress) {
        isInProgress = inProgress;
        Graphics2D g = (inProgress ? imageInProgress : image).createGraphics();
        if (inProgress) {
            g.drawImage(image, 0, 0, this);
        }
        g.setColor(drawingColor);
        g.setStroke(PenSize);
        
        if(mode == DrawingMode.RectangleFill) {
        	g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else if(mode == DrawingMode.CircleFill) {
        	g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }else if(mode == DrawingMode.RectangleOutline) {
        	g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else if(mode == DrawingMode.CircleOutline) {
        	g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        
        repaint();
    }
    
    public void drawLine(int startX, int startY, int endX, int endY) {
        Graphics2D g = image.createGraphics();
        g.setColor(drawingColor);
        g.setStroke(PenSize);
    	g.drawLine(startX, startY, endX, endY);
        repaint();
    }
    
    public void setPenSize(int size) {
    	PenSize = new BasicStroke(size);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(isInProgress ? imageInProgress : image, 0, 0, this);
    }
}
