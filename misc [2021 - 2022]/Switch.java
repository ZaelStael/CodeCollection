package misc;

import java.util.*;

public class Switch {
    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            int yearLevel;
            System.out.print("Enter your college year:");
            yearLevel = console.nextInt();

            switch (yearLevel) {
                case 1:
                    System.out.println("You're a freshman eh? Scrub.");
                    break;

                case 2:
                    System.out.println("Hm, you got some experience eh - I'm a sophomore too - you're alright mate.");
                    break;

                case 3:
                    System.out.println("You're almost there Junior; your pain will end soon. Keep pushing.");
                    break;

                case 4:
                    System.out.println(
                            "Last road; You're a senior in school but still green in life. Good luck out there young one.");
                    break;

                default:
                    System.out.println("So are you gonna actually put a proper year in or... (Only numbers 1-4)");
                    break;
            }
        }
    }
}