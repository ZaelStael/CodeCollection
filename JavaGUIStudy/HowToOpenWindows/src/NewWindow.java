import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello!");

    NewWindow() {

        label.setBounds(0, 0, 150, 50);
        label.setFont(new Font(null, Font.PLAIN, 20));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}
