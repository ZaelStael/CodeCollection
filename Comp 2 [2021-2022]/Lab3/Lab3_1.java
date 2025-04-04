import java.io.*;
import java.util.*;

class Lab3_1 {
    public static void main(String[] args) {
        int gamestate = 1;
        Scanner ansIn = new Scanner(System.in);
        String ans = " ";
        int r = 0;
        int c = 0;
        int count = 1;

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
                System.out.println("You can tell me whatever number of rows you would like to start an array with.");
                r = getInt(1, 10);

                System.out.println("You can tell me whatever number of cols you would like to start an array with.");
                c = getInt(1, 10);

                int arr1[][] = initRand(r, c, 2);

                System.out.println("The matrix is... \n");
                printMat(arr1, r, c);

                for (int i = 1; i <= r; i++) {
                    isFlipped(arr1, i, (i + 1));
                }

            } else if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("No")) {
                System.out.println("Ciao.");
                gamestate = 0;
            } else {
                System.out.println("...\nImma leave now...");
                gamestate = 0;
            }

        }

    }

    public static int getInt(int min, int max) throws NoSuchElementException {
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

        inputInt.nextLine();
        return a;

    }

    public static int[][] initRand(int n, int m, int randNum) {
        int[][] mat1 = new int[n][m];
        Random Rand = new Random();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                mat1[r][c] = Rand.nextInt(randNum);
            }
        }

        return mat1;
    }

    public static void printMat(int[][] m, int n, int l) {

        for (int r = 0; r < n; r++) {
            // System.out.printf("'[%s] ' ", m[r]);
            for (int c = 0; c < l; c++) {
                System.out.printf("%s  ", m[r][c]);
            }
            System.out.println("");
        }
    }

    public static boolean isFlipped(int[][] a, int startRow, int endRow) {
        ArrayList<Integer> r1 = new ArrayList<Integer>();
        ArrayList<Integer> r2 = new ArrayList<Integer>();
        int flipCount = 0;
        int cols = a[startRow].length;

        for (int r = startRow; r < endRow; r++) {
            for (int c = 0; c < cols; c++) {
                r1.add(a[r][c]);
            }
        }

        for (int s = endRow; s > startRow; s--) {
            for (int c = (cols - 1); c > 0; c--) {
                r2.add(a[s][c]);
            }
        }

        for (int b = 1; b <= cols; b++) {
            if (r1.get(b - 1).equals(r2.get(cols - b))) {
                flipCount++;
            }
        }

        if (flipCount == cols) {
            System.out.print("Row " + startRow + " and row " + endRow + " are flipped.");
            return true;
        } else {
            return false;
        }

    }
}