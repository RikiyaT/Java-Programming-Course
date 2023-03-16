import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*********** DON'T MODIFY THE FOLLOWING CODE ***********/
public class twobutton {
    public static void main(String[] args) {
        showWindow();
    }

    public static MainWindow showWindow() {
        return new MainWindow();
    }
}
/*********** DON'T MODIFY THE ABOVE CODE ***********/

class MainWindow extends JFrame {
    public MainWindow(){

        super("Problem1");

        this.setVisible(true);

        this.setSize(320, 240);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(this.createContentPane());
    }

    private JPanel createContentPane() {
        // Create a readonly text box
        // - JP: https://docs.oracle.com/javase/jp/8/docs/api/index.html?javax/swing/JTextArea.html
        // - EN: https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/JTextArea.html
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Create a button
        // - JP: https://docs.oracle.com/javase/jp/8/docs/api/index.html?javax/swing/JButton.html
        // - EN: https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/JButton.html
        JButton leftbutton = new JButton("Left");
        JButton rightbutton = new JButton("Right");

        // Configure an event handler in the button
        leftbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Show a generated text on the readonly text box when the button is pushed
                textArea.setText(textArea.getText() + "Left!\n");
            }
        });

        rightbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Show a generated text on the readonly text box when the button is pushed
                textArea.setText(textArea.getText() + "Right!\n");
            }
        });

        // Create a panel with BorderLayout
        // - JPanel
        //   - JP: https://docs.oracle.com/javase/jp/8/docs/api/index.html?javax/swing/JPanel.html
        //   - EN: https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/JPanel.html
        // - BorderLayout
        //   - JP: https://docs.oracle.com/javase/jp/8/docs/api/index.html?java/awt/BorderLayout.html
        //   - EN: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/BorderLayout.html
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(textArea);
        contentPane.add(leftbutton, BorderLayout.NORTH);
        contentPane.add(rightbutton, BorderLayout.SOUTH);

        // Return a created panel as a content pane
        return contentPane;
    }
}
