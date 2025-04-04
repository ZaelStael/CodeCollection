import javax.swing.*;
import java.awt.*;

public class Panels {
    public static void main(String[] args) {

        // JPanel = a GUI component that holds other components

        ImageIcon icon = new ImageIcon("Spirit Star Token.png");

        JLabel label = new JLabel();
        label.setText("Hi!");
        label.setIcon(icon);
        // label.setVerticalAlignment(JLabel.TOP); // sets label position TOP, CENTER,
        // or BOTTOM within component
        // label.setHorizontalAlignment(JLabel.RIGHT); // sets label position LEFT,
        // CENTER, or RIGHT within component
        label.setBounds(100, 100, 75, 75); // adds components (label) relative to chosen container (greenPanel)

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);
        redPanel.setLayout(new BorderLayout()); // makes new layout with no border gaps

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(250, 0, 250, 250);
        bluePanel.setLayout(null);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 250, 500, 250);
        greenPanel.setLayout(null);
        greenPanel.add(label);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // allows manual setting of layout
        frame.setSize(750, 750);
        frame.setVisible(true);
        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
    }
}
