import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutStart {

    // Layout Manager = Defines the natural layout for components within a container

    // FlowLayout = places components in a grid of cells. Each component takes all
    // the available space within its cell, and each cell is the same size.

    public static void main(String[] args) {
        JFrame frame = new MyFrame();
        frame.setLayout(new GridLayout(4, 3, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new JButton("1"));
        frame.add(new JButton("2"));
        frame.add(new JButton("3"));
        frame.add(new JButton("4"));
        frame.add(new JButton("5"));
        frame.add(new JButton("6"));
        frame.add(new JButton("7"));
        frame.add(new JButton("8"));
        frame.add(new JButton("9"));
        frame.add(new JButton("0"));

        frame.setVisible(true);
    }

}
