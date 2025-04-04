import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class textFieldFrame implements ActionListener {

    JButton button;
    JTextField textField = new JTextField("");

    public textFieldFrame() {

        JFrame frame = new MyFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Submit");
        button.addActionListener(this);

        textField = new JTextField(" ");
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
        textField.setForeground(Color.GREEN);
        textField.setBackground(Color.black);
        textField.setCaretColor(Color.white);
        textField.setText("Username");
        // textField.setEditable(false);

        frame.add(button);
        frame.add(textField);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Welcome " + textField.getText());
            button.setEnabled(false);
            textField.setEditable(false);

        }
    }

}
