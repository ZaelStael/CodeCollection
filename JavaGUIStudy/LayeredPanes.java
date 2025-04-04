import javax.swing.*;
import java.awt.*;

public class LayeredPanes {

    // JLayeredPane = Swing container that provides a third dimension for
    // positioning components (depth aka Z-index)

    public static void main(String[] args) {

        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.RED);
        label1.setBounds(50, 50, 200, 200);

        JLabel label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.GREEN);
        label2.setBounds(100, 100, 200, 200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.BLUE);
        label3.setBounds(150, 150, 200, 200);

        JLayeredPane lPane = new JLayeredPane();
        lPane.setBounds(0, 0, 500, 500);

        lPane.add(label1, JLayeredPane.DEFAULT_LAYER);
        // lPane.add(label1, Integer.valueOf(0)); //sets pane on layer defined by
        // integer, O is default, the higher number appear above 0
        lPane.add(label2, JLayeredPane.DEFAULT_LAYER);
        lPane.add(label3, JLayeredPane.DRAG_LAYER);

        JFrame frame = new MyFrame();
        frame.add(lPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
