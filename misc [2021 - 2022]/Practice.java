package src.misc;

import java.util.*;

public class Practice {
    String myName;
    String myAge;

    public static void main(String[] args) {
        System.out.println("What is your name?");

        try (Scanner scanner = new Scanner(System.in)) {
            String myName = scanner.nextLine();

            System.out.println("Howdy " + myName);
        }
    }

}
