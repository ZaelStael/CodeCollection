import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    JButton button;
    JLabel libel;

    MyFrame() {

        new MyFrame(0);

    }

    MyFrame(int i) {

        switch (i) {
            case 1:

                this.setTitle("Practice GUI"); // set title
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows program exiting
                this.setResizable(false); // set ability to be resized
                this.setSize(420, 420); // change frame size
                this.setVisible(true); // make frame visible

                ImageIcon img = new ImageIcon("Spirit Star Token.png"); // create ImageIcon
                this.setIconImage(img.getImage()); // change frame icon

                this.getContentPane().setBackground(Color.BLUE); // change BG color
                this.getContentPane().setBackground(new Color(0x123456)); // change BG color specifically w/ RGB values
                this.getContentPane().setBackground(new Color(50, 25, 150)); // change BG color specifically
                break;

            case 2:

                ImageIcon icon = new ImageIcon("Spirit Star Token.png");

                libel = new JLabel();
                libel.setIcon(icon);
                libel.setVisible(false);

                button = new JButton();
                button.setBounds(200, 100, 300, 200);
                button.addActionListener(this); // allows ActionListener and actionPerformed to listen out for button
                // button.addActionListener(e -> System.out.println("Yee!")); // same as above
                // w/o requiring implementation
                // or actionPerformed
                button.setText("I'm a button!!!"); // set button text
                button.setFocusable(false); // removes useless (for now) box around button text
                // button.setIcon(icon);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setVerticalTextPosition(JButton.BOTTOM);
                button.setFont(new Font("Comic Sans", Font.BOLD, 25)); // change button font
                button.setIconTextGap(-15); // changes space between letters
                button.setForeground(Color.cyan);
                button.setBackground(Color.LIGHT_GRAY);
                button.setBorder(BorderFactory.createEtchedBorder()); // gives a psuedo-3D effect
                // button.setEnabled(false); // disables button, can be placed in
                // actionPerformed to give one click before disable

                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLayout(null);
                this.setSize(750, 500);
                this.setVisible(true);
                this.add(button);
                this.add(libel);
                this.setResizable(true);
                break;

            case 0:
            default:

                this.setTitle("New Frame"); // set title
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows program exiting
                this.setResizable(true); // set ability to be resized
                this.setSize(500, 500); // change frame size
                break;

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            System.out.println("Yee");
            button.setEnabled(false);
            libel.setVisible(true); // make libel visible underneath button...in theory...
        }
    }
}
