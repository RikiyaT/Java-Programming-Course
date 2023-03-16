import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class box{
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
        super("Layout");
        setContentPane(createContentPane());
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel a = new JPanel();
        a.setLayout(new FlowLayout());
        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        JButton downbutton = new JButton("Border-Down");
        JButton upbutton = new JButton("Border-Up");
        JButton rightbutton = new JButton("Border-Right");
        JButton leftbutton = new JButton("Border-Left");
        JButton abutton = new JButton("Flow1-1");
        JButton bbutton = new JButton("Flow1-22");
        JButton cbutton = new JButton("Flow1-333");
        JButton dbutton = new JButton("Flow1-4444");
        JButton ebutton = new JButton("Flow2-1");
        JButton fbutton = new JButton("Flow2-22");
        JButton gbutton = new JButton("Flow2-333");
        JButton hbutton = new JButton("Flow2-4444");
        a.setBackground(Color.BLACK);
        b.setBackground(Color.BLACK);
        c.setBackground(Color.BLACK);

        a.add(b);
        a.add(c);
        b.add(abutton);
        b.add(bbutton);
        b.add(cbutton);
        b.add(dbutton);
        c.add(ebutton);
        c.add(fbutton);
        c.add(gbutton);
        c.add(hbutton);
        contentPane.add(a, BorderLayout.CENTER);
        contentPane.add(downbutton, BorderLayout.SOUTH);
        contentPane.add(upbutton, BorderLayout.NORTH);
        contentPane.add(rightbutton, BorderLayout.EAST);
        contentPane.add(leftbutton, BorderLayout.WEST);
        return contentPane;
    }
}
