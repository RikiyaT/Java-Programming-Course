import javax.swing.*;
import java.awt.*;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class gridbag{
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
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createContentPane() {
        JButton b1 = new JButton("Flow-1");
        JButton b2 = new JButton("Flow-2");
        JButton b3 = new JButton("Flow-3");
        JButton b4 = new JButton("Flow-4");
        JButton b5 = new JButton("Flow-5");

        JButton c1 = new JButton("GridBag-1");
        JButton c2 = new JButton("GridBag-2");
        JButton c3 = new JButton("GridBag-3");
        JButton c4 = new JButton("GridBag-4");

        JPanel a = new JPanel(new GridLayout(3, 1));
        JPanel b = new JPanel();
        b.setLayout(new FlowLayout());
        JPanel d = new JPanel();
        d.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        c.gridx=0;
        d.add(c1, c);
        c.gridy=0;
        c.gridx=1;
        d.add(c2, c);
        c.gridy=1;
        c.gridx=0;
        d.add(c3, c);
        c.gridx=0;
        c.gridy=0;
        d.add(c4, d);

        a.add(b);
        a.add(d);
        a.add(e);
        b.add(b1);
        b.add(b2);
        b.add(b3);
        b.add(b4);
        b.add(b5);

        return a;

    }
}
