import javax.swing.*;
import java.awt.*;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class border{
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
        JButton centerbutton = new JButton("Center");
        JButton downbutton = new JButton("Down");
        JButton upbutton = new JButton("Up");
        JButton rightbutton = new JButton("Right");
        JButton leftbutton = new JButton("Left");
        contentPane.add(centerbutton, BorderLayout.CENTER);
        contentPane.add(downbutton, BorderLayout.SOUTH);
        contentPane.add(upbutton, BorderLayout.NORTH);
        contentPane.add(rightbutton, BorderLayout.EAST);
        contentPane.add(leftbutton, BorderLayout.WEST);
        return contentPane;
    }
}
