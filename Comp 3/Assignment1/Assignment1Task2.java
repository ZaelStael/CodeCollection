//Muteeb Younus
//Tyrique Baker
//Amira Alabi

import java.util.*;

public class Assignment1Task2 {

    public static void main(String[] args) {
        int ansInt = 0;
        String ans = " ";
        int count = 1;
        Scanner ansIn = new Scanner(System.in);
        int gamestate = 1;

        while (gamestate == 1) {
            if (count == 1) {
                System.out.println("Start? (Y/N)");
                ans = ansIn.nextLine();
                count++;
            } else {
                System.out.println("Continue?");
                ans = ansIn.nextLine();
            }
            if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("Yes")) {
                System.out.println("You can tell me whatever number you would like to start an array with.");
                ansInt = getOddInt(1, 100);

                int arr1[][] = initiateArray(ansInt);
                int sum = 0;

                System.out.println("The array is... \n");
                printMat(arr1, ansInt);

                for (int i = 0; i < ansInt; i++) {
                    sum += arr1[0][i];
                }
                System.out.println("This array adds up to: " + sum);

                // System.out.println("Continue? (Y/N)");
                // ans = ansIn.nextLine();

            } else if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("No")) {
                System.out.println("Ciao.");
                System.exit(0);
            } else {
                System.out.println("...\nImma leave now...");
                System.exit(0);
            }
        }

    }

    public static int getOddInt(int min, int max) throws NoSuchElementException {
        Scanner inputInt = new Scanner(System.in);
        System.out.println("What is the integer you would like to use?");

        while (!inputInt.hasNextInt()) {
            System.out.println("Not an Integer mate, try again...");
            inputInt.next();
            // int a = inputInt.nextInt();
        }
        int a = inputInt.nextInt();
        while (a > max || a < min) {
            System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
            a = inputInt.nextInt();
            // int a = inputInt.nextInt();
        }
        while (a % 2 == 0) {
            System.out.println("Number must be Odd. Try again.");
            a = inputInt.nextInt();
        }

        inputInt.nextLine();
        return a;

    }

    public static int[][] initiateArray(int n) {
        int[][] mat1 = new int[n][n];
        int sentinel = 1;
        System.out.println("Got here.");
        int r = 1;
        int c = ((n / 2) - 1);

        do {
            r--;
            c++;

            if (r < 0) {
                // mat1[r][c] = 0;
                System.out.println("Got here.");
                r = (n - 1);
                // mat1[r][c] = sentinel;
            }
            if (c >= n) {
                c = 0;
                // mat1[r][c] = sentinel;
            }
            if (mat1[r][c] != 0) {
                if (r >= n) {
                    r = 0;
                }
                System.out.println("Got here.");
                r++;
                if (r >= n) {
                    r = 0;
                }
                r++;
                c--;
                if (c < 0) {
                    c = (n - 1);
                }
            }

            mat1[r][c] = sentinel;
            sentinel++;

        } while (sentinel != ((n * n) + 1));
        return mat1;

    }

    public static void printMat(int[][] m, int n) {

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.printf("%s  ", m[r][c]);
            }
            System.out.println();
        }
    }

}
