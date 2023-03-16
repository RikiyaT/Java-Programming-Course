import javax.swing.*;
import java.awt.*;

/*** I DON'T RECOMMEND TO CHANGE THE BELOW CODE (EVEN THOUGH YOU CAN) ***/
public class grid{
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
        JPanel gridPane = new JPanel(new GridLayout(1, 3));
        JButton downbutton = new JButton("Border-Down");
        JButton upbutton = new JButton("Border-Up");
        JButton rightbutton = new JButton("Border-Right");
        JButton leftbutton = new JButton("Border-Left");
        JButton grid1=new JButton("Grid1");
        JButton grid2=new JButton("Grid2");
        JButton grid3=new JButton("Grid3");

        gridPane.add(grid1);
        gridPane.add(grid2);
        gridPane.add(grid3);
        contentPane.add(gridPane, BorderLayout.CENTER);
        contentPane.add(downbutton, BorderLayout.SOUTH);
        contentPane.add(upbutton, BorderLayout.NORTH);
        contentPane.add(rightbutton, BorderLayout.EAST);
        contentPane.add(leftbutton, BorderLayout.WEST);
        return contentPane;
    }
}
