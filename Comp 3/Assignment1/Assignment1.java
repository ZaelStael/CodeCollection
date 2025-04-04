import java.util.*;
import java.lang.Math;

public class Assignment1 {

    public static void main(String[] args) {
        int elementNum = getInt(1, 10);

        switch (a) {

            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;

            case 0:
                System.out.println("Deuces.");
                System.exit(0);

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

    public static int[][] generate(int n) {
        int[][] mat1 = new int[n][n];
        Random Rand = new Random();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                mat1[r][c] = Rand.nextInt(10) + 1;
            }
        }

        return mat1;
    }

    public static void printMat(int[][] m, int n) {

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.printf("'%s  '", m[r][c]);
            }
        }
    }

    public static void addMat() {
        int ans = 0;
        int[][] addMat = new int[ans][ans];

        System.out.println("So you want to add matrices eh? I got you.\nWhat's the size of your matrices?");
        ans = getInt(1, 10);

        System.out.println("The first matrix is: \n");
        int[][] mat1 = generate(ans);
        printMat(mat1, ans);

        System.out.println("The second matrix is: \n");
        int[][] mat2 = generate(ans);
        printMat(mat2, ans);

        System.out.println("Added together that is: \n");

        for (int r = 0; r < ans; r++) {
            for (int c = 0; c < ans; c++) {
                addMat[r][c] = (mat1[r][c] + mat2[r][c]);
            }
        }

        printMat(addMat, ans);
    }

    public static void subMat() {
        int ans = 0;
        int[][] subMat = new int[ans][ans];

        System.out.println("So you want to subtract matrices eh? I got you.\nWhat's the size of your matrices?");
        ans = getInt(1, 10);

        System.out.println("The first matrix is: \n");
        int[][] mat1 = generate(ans);
        printMat(mat1, ans);

        System.out.println("The second matrix is: \n");
        int[][] mat2 = generate(ans);
        printMat(mat2, ans);

        System.out.println("If we subtract 1 from 2, that is: \n");

        for (int r = 0; r < ans; r++) {
            for (int c = 0; c < ans; c++) {
                subMat[r][c] = (mat1[r][c] - mat2[r][c]);
            }
        }

        printMat(subMat, ans);
    }

    public void multMatrix() {
        int ans = 0;
        int select = 0;
        int[][] multMat = new int[ans][ans];

        System.out.println(
                "So you want to multiply matrices eh? I got you.\nDid you want to multiply 2 individual matrices(1) or multiply 1 matrix by a number(2)?");
        select = getInt(1, 2);

        switch (select) {
            case 1:
                System.out.println("What's the size of you matrices.");
                ans = getInt(1, 10);

                System.out.println("The first matrix is: \n");
                int[][] mat1 = generate(ans);
                printMat(mat1, ans);

                System.out.println("The second matrix is: \n");
                int[][] mat2 = generate(ans);
                printMat(mat2, ans);

                System.out.println("If we multiply these two, we get: \n");

                for (int i = 0; i < ans; i++) {
                    for (int j = 0; j < ans; j++) {
                        for (int k = 0; k < ans; k++)
                            multMat[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }

                break;

            case 2:
                int scalar = 0;

                System.out.println("What's the size of your matrix?");
                ans = getInt(1, 10);

                System.out.println("What are you multiplying your matrix by?");
                scalar = getInt(1, 10);

                System.out.println("The matrix is: \n");
                int[][] mat = generate(ans);
                printMat(mat, ans);

                System.out.println("If we multiply it out, we get: \n");

                for (int r = 0; r < ans; r++) {
                    for (int c = 0; c < ans; c++) {
                        multMat[r][c] = (mat[r][c] * scalar);
                    }
                }

                break;

        }
        printMat(multMat, ans);
    }

}
