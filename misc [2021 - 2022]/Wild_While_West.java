package misc;

import java.util.ArrayList;
import java.util.Scanner;

public class Wild_While_West {
    public static void main(String[] args) {

        int a = 0;

        while (a < 10) {
            System.out.println("a is less than 10: " + a);
        }

        while (true) {
            System.out.println("Okay.");
        }
    }

    public static void other(String[] args) {

        int b = 0;

        do {
            System.out.println("b");
            b++;
        } while (b == 0);
    }

    public static void rando(String[] args) {
        String sentence = "Waffles Win!!!";
        Scanner scan = new Scanner(sentence);
        ArrayList<String> words = new ArrayList<String>();

        while (scan.hasNext()) {
            words.add(scan.next());
        }

        System.out.println(words); // Prints 'sentence' one word at a time.
    }
}
