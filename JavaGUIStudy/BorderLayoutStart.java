import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorderLayoutStart {

    // Layout Manager = Deines natural layout for components of a container

    // 3 common managers

    // BorderLayout = A BorderLayout places components at: NORTH, SOUTH, EAST, WEST,
    // or CENTER. All extra space is allocated to center area.

    public static void main(String[] args) {

        int width_margin = 10;
        int height_margin = 10;

        JFrame frame = new MyFrame(0);
        frame.setLayout(new BorderLayout(10, 10)); // adds margins between each panel
        // frame.setLayout(new FlowLayout(0, 0, FlowLayout.RIGHT));
        // frame.setLayout(new GridLayout(2, 5));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);

        panel5.setLayout(new BorderLayout());

        panel1.setPreferredSize(new Dimension(100, 75));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 100));
        panel4.setPreferredSize(new Dimension(100, 125));
        panel5.setPreferredSize(new Dimension(100, 100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        // ------------- sub-panels --------------------

        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();

        panel6.setBackground(Color.black);
        panel7.setBackground(Color.darkGray);
        panel8.setBackground(Color.gray);
        panel9.setBackground(Color.lightGray);
        panel10.setBackground(Color.white);

        panel5.add(panel6, BorderLayout.NORTH);
        panel5.add(panel7, BorderLayout.WEST);
        panel5.add(panel8, BorderLayout.EAST);
        panel5.add(panel9, BorderLayout.SOUTH);
        panel5.add(panel10, BorderLayout.CENTER);

        panel6.setPreferredSize(new Dimension(50, 50));
        panel7.setPreferredSize(new Dimension(50, 50));
        panel8.setPreferredSize(new Dimension(50, 50));
        panel9.setPreferredSize(new Dimension(50, 50));
        panel10.setPreferredSize(new Dimension(50, 50));

        // --------- end of sub-panels --------------

        frame.setVisible(true);

    }
}
