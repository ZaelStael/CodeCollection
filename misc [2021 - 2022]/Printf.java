import java.util.*;

public class Printf {

    public static void printBoard(String[][] board) {

        for (int i = 0; i < board.length; i++) {

            for (int k = 0; k < 3; k++) {

                System.out.printf("|%-9s", board[i][k]);

            }
            System.out.println("");

        }
    }

    public static void main(String[] args) {

        System.out.println("Print f can be used vto format various different variable types.");

        String[][] display = { { "Operator", "Effects", "Uses" },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" },
                { "%s", "Strings",
                        "Can format the given string to your specifications, such as, how spaced out you want it or what you want to apear after it." },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" },
                { "%d", "Decimals",
                        "Can be used to format spaces, alignment ('-' for left), and number of deciamal places shown." },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" },
                { "%f", "Floats", " Same as decimals." },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" },
                { "t", "Date/Time",
                        "Can format a given time (into hours, minutes, seconds) or a date (into month, day year)." },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" },
                { "%n", "Lines", "Starts a new line when it's called on." },
                { "_________", "_________",
                        "________________________________________________________________________________________________________________________________________________________________" }, };

        printBoard(display);

        System.out.println("In example:   ");

        System.out.println("Input = '%2s' %n, baeldung && '%2S', baeldung");
        System.out.print("Output = ");
        System.out.printf("'%2s' %n", "baeldung");
        System.out.printf(" && ");
        System.out.printf("'%S'", "baeldung");

    }
}