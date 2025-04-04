import java.awt.*;
import javax.swing.*;

public class FlowLayoutStart {

    // Layout Manager = Defines the natural layout for components within a container

    // FlowLayout = places components in a row, sized at their preferred size. If
    // horizontal space too small, next available row is used.

    public static void main(String[] args) {
        JFrame frame = new MyFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // have text stick to LEFT (LEADING), CENTER, or
                                                                    // RIGHT (TRAILING) of frame

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 250));
        panel.setBackground(Color.lightGray);
        panel.setLayout(new FlowLayout());

        // JButton butt1 = new JButton("1");
        // JButton butt2 = new JButton("2");
        // JButton butt3 = new JButton("3");

        // frame.add(butt1);
        // frame.add(butt2);
        // frame.add(butt3);

        // frame.add(new JButton("4"));
        // frame.add(new JButton("5"));
        // frame.add(new JButton("6"));
        // frame.add(new JButton("7"));
        // frame.add(new JButton("8"));
        // frame.add(new JButton("9"));

        JButton butt1 = new JButton("1");
        JButton butt2 = new JButton("2");
        JButton butt3 = new JButton("3");

        panel.add(butt1);
        panel.add(butt2);
        panel.add(butt3);

        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));

        frame.add(panel);
        frame.setVisible(true);

    }

}
