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

        JPanel grid = new JPanel(new GridLayout(2, 1));
        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout());
        JPanel bag = new JPanel();
        bag.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        c.gridx=0;
        bag.add(c1, c);
        c.gridy=0;
        c.gridx=1;
        bag.add(c2, c);
        c.gridy=1;
        c.gridx=0;
        bag.add(c3, c);
        /*c.gridx=???;*/       //ここをどうするべきですか。。？
                               //"GridBag-4"を中心に合わせる方法がわかりません...。
                               //僕の考えでは、もし2つのグリッドを結合するのが良いのではないかと思ったのですが、その方法がわかりません。
        c.gridy=2;
        bag.add(c4, c);

        grid.add(flow);
        grid.add(bag);
        flow.add(b1);
        flow.add(b2);
        flow.add(b3);
        flow.add(b4);
        flow.add(b5);

        return grid;

    }
}
