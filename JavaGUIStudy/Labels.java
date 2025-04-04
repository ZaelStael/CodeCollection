import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Labels {
    public static void main(String[] args) {
        // JLabel = a GUI display are for a string of text, an img, or both

        ImageIcon img = new ImageIcon("Spirit Star Token.png");
        Border bord = BorderFactory.createLineBorder(Color.green, 3);

        JLabel label = new JLabel(); // create label
        label.setText("BB do you even code?"); // set label text
        label.setIcon(img);
        label.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, RIGHT, or CENTER of imageicon
        label.setVerticalTextPosition(JLabel.TOP); // text TOP, CENTER, or BOTTOM of imageicon
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("MV Boli", Font.PLAIN, 20)); // set font, alterations, and size of text
        label.setIconTextGap(25); // set gap of text to image
        label.setBackground(Color.BLACK); // change BG color
        label.setOpaque(true); // show BG
        label.setBorder(bord); // set label border (not img + text)
        label.setVerticalAlignment(JLabel.CENTER); // set verticle position of icon+text within label
        label.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon+text within label
        // label.setBounds(0, 0, 250, 250); // set x, y position within frame +
        // dimensions

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        // frame.setLayout(null);
        frame.setVisible(true);
        frame.add(label);
        frame.pack(); // requires all components first

    }
}
