import java.util.*;

public class Matrix {

    public final int max = 20;
    int size;
    int[][] mArray;

    public Matrix() {
        size = max;
        mArray = new int[20][20];

    }

    public Matrix(int n) {
        size = n;
        mArray = new int[n][n];
    }

    public int getInt(Scanner inputInt, int min, int max) throws NoSuchElementException {
        System.out.println("What is the integer you would like to use?");

        while (!inputInt.hasNextInt()) {
            System.out.println("Not an Integer mate, try again...");
            inputInt.next();
        }
        int a = inputInt.nextInt();
        while (a > max || a < min) {
            System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
            a = inputInt.nextInt();
        }

        inputInt.nextLine();
        return a;

    }

    public int getSize() {
        return size;
    }

    public int getElement(int r, int c) {

        return mArray[r][c];
    }

    public void setElement(int r, int c, int newVal) {

        mArray[r][c] = newVal;
    }

    public void init(int min, int max) {

        Random Rand = new Random();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int a = Rand.nextInt(max + 1) + (min + 1);

                while (a > max || a < min) {
                    a = Rand.nextInt(max + 1) + (min + 1);
                }

                mArray[r][c] = a;
            }
        }
    }

    public void print() {

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.printf("%5d", mArray[r][c]);
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix mat2) {
        Matrix addMat = new Matrix(size);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                addMat.mArray[r][c] = (this.mArray[r][c] + mat2.mArray[r][c]);
            }
        }

        return addMat;
    }

    public Matrix sub(Matrix mat2) {

        Matrix subMat = new Matrix(size);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                subMat.mArray[r][c] = (this.mArray[r][c] - mat2.mArray[r][c]);
            }
        }

        return subMat;
    }

    public Matrix multiply(Matrix m2) {

        Matrix multMat = new Matrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++)
                    multMat.mArray[i][j] += this.mArray[i][k] * m2.mArray[k][j];
            }
        }

        return multMat;

    }

    public Matrix multiplyConst(int constant) {

        Matrix multMat = new Matrix(size);
        int scalar = constant;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                multMat.mArray[r][c] = (this.mArray[r][c] * scalar);
            }
        }

        return multMat;

    }

    public Matrix transpose() {
        int n = size;

        Matrix m = new Matrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m.mArray[i][j] = this.mArray[j][i];
            }
        }

        return m;
    }

    public int trace() {

        int trace = 0;
        int c = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == c && j == c) {
                    trace += mArray[i][j];
                }

            }
            c++;
        }

        return trace;
    }

    public boolean equals(Matrix a) {

        boolean ans = true;

        if (size != a.size) {
            ans = false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mArray[i][j] != a.mArray[i][j]) {
                    ans = false;
                }
            }
        }

        return ans;
    }

    public void copy(Matrix a) {
        this.mArray = a.mArray;
        this.size = a.size;
    }

    public Matrix getCopy() {
        Matrix a = new Matrix(size);
        a.mArray = this.mArray;

        return a;

    }

}