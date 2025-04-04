import javax.swing.*;

public class DialougueBoxes {
    // JOptionPane = pop up a standard dialogue box that prompts users for a value
    // or informs them of something

    public static void main(String[] args) {

        // JOptionPane.showMessageDialog(null, "Yo.", "Title",
        // JOptionPane.PLAIN_MESSAGE);
        // JOptionPane.showMessageDialog(null, "Yo 2.", "Title",
        // JOptionPane.INFORMATION_MESSAGE);
        // JOptionPane.showMessageDialog(null, "Yo 3?", "Title",
        // JOptionPane.QUESTION_MESSAGE);
        // JOptionPane.showMessageDialog(null, "Yo 4!", "Title",
        // JOptionPane.WARNING_MESSAGE);
        // JOptionPane.showMessageDialog(null, "Yo 5.", "Title",
        // JOptionPane.PLAIN_MESSAGE);

        // int ans = JOptionPane.showConfirmDialog(null, "Do you even code?", "Title",
        // JOptionPane.YES_NO_CANCEL_OPTION); // returns an int as well, if you wanted
        // to throw this into a switch or something you could
        // String name = JOptionPane.showInputDialog("What is your name?");

        ImageIcon icon = new ImageIcon("Spirit Star Token.png");
        String[] responses = { "No, you're awesome!", "Thank you", "I know." };
        JOptionPane.showOptionDialog(null, "You're great.", "Secret", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, icon, responses, 0);
    }
}
