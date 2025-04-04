//Muteeb Younus
//Tyrique Baker
//Amira Alabi

import java.util.*;

public class Assignment1Task1 {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int i = 0;

        while (i == 0) {
            int user = menu(in, "ERROR: Not an Integer...", "ERROR: Out of Range (0-6)...");

            System.out.println("Please Enter the Size of the Square Matrices:");

            int elementNum = getInt(in, 1, 10, "ERROR: Not an Integer...", "ERROR: Out of Range (1-10)...");

            switch (user) {

                case 1:
                    addMat(elementNum);
                    break;
                case 2:

                    subMat(elementNum);
                    break;

                case 3:

                    multMat(elementNum);
                    break;

                case 4:
                    System.out.println("Enter an Integer to Multiply the Matrix By: ");
                    int scaler = getInt(in, 1, 10, "ERROR: Not an Integer...", "ERROR: Out of Range (1-10)...");
                    multScaler(elementNum, scaler);
                    break;

                case 5:
                    transpose(elementNum);
                    break;
                case 6:
                    trace(elementNum);
                    break;
                case 0:
                    System.out.println("Deuces.");
                    System.exit(0);

            }
        }

    }

    public static int menu(Scanner in, String error, String error2) {
        int user = 0;
        int i = 0;

        System.out.println("_________________");
        System.out.println();
        System.out.println("YOUR OPTIONS ARE:");
        System.out.println();
        System.out.println("1) Add Two Mactrices");
        System.out.println("2) Subtract 2 Matrices");
        System.out.println("3) Multipl 2 Matrices");
        System.out.println("4) Multiply Matrix by Constant");
        System.out.println("5) Transpose Matrix");
        System.out.println("6) Matric Trance");
        System.out.println("0) EXIT");
        System.out.println("_________________");
        System.out.println();
        System.out.println("Please Enter an Option:");

        while (i == 0) {
            while (!in.hasNextInt()) {
                System.out.println(error);
                in.next();
            }

            user = in.nextInt();

            switch (user) {
                case 1:
                    return user;
                case 2:
                    return user;
                case 3:
                    return user;
                case 4:
                    return user;
                case 5:
                    return user;
                case 6:
                    return user;
                case 0:
                    System.exit(0);
                default:
                    System.out.println(error2);
                    break;
            }
        }

        return user;
    }

    public static int getInt(Scanner in, int min, int max, String error, String error2) {
        int user = 0;
        int i = 0;

        while (i == 0) {
            while (!in.hasNextInt()) {
                System.out.println(error);
                in.next();
            }

            user = in.nextInt();

            if (user < min || user > max) {
                System.out.println(error2);
            } else {
                i++;
            }
        }

        return user;
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
                System.out.printf("%5d", m[r][c]);
            }
            System.out.println();
        }
    }

    public static void addMat(int size) {
        int ans = size;

        int[][] addMat = new int[ans][ans];

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

    public static void subMat(int size) {
        int ans = size;
        int[][] subMat = new int[ans][ans];

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

    public static void multMat(int size) {
        int ans = size;
        int[][] multMat = new int[ans][ans];

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
        printMat(multMat, ans);
    }

    public static void multScaler(int size, int mult) {
        int ans = size;
        int[][] multMat = new int[ans][ans];

        System.out.println("The matrix is: \n");
        int[][] mat1 = generate(ans);
        printMat(mat1, ans);

        System.out.println("If we multiply these by the contsant: " + mult + ", we get: \n");

        for (int i = 0; i < ans; i++) {
            for (int j = 0; j < ans; j++) {
                multMat[i][j] += mat1[i][j] * mult;
            }
        }
        printMat(multMat, ans);
    }

    public static void transpose(int size) {
        int[][] matrix = generate(size);
        System.out.println("The Matrix is: ");
        printMat(matrix, size);

        int[][] copy = new int[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                copy[c][r] = matrix[r][c];
            }
        }

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                matrix[c][r] = matrix[r][c];
            }
        }
        System.out.println();
        System.out.println("The Transposed Matrix is: ");
        printMat(copy, size);
    }

    public static void trace(int size) {
        int[][] matrix = generate(size);
        System.out.println("The Matrix is: ");
        printMat(matrix, size);

        int trace = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r == c) {
                    trace = trace + matrix[r][c];
                }
            }
        }

        System.out.println("The Trace of the Matrix is: " + trace);

    }

}
