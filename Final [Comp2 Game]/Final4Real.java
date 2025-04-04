import java.util.Scanner;

public class Final4Real {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int angle;
        int score = 10;
        int result = 12;
        result = score--;

        angle = console.nextInt();
        if (angle >= 5) {
            angle += 5;
        } else if (angle > 2) {
            angle += 10;
        }

        System.out.print(result);

    }

    public boolean rue(String s) {
        if (s.equalsIgnoreCase("This is a test") || s.equalsIgnoreCase("This is not a test")) {
            return true;
        } else {
            return false;
        }
    }

}
