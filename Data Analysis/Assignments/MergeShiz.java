import java.util.*;
import java.io.*;

public class MergeShiz {

    public static void main(String[] args) {
        ArrayList<Integer> arr1 = fileRead();
        int[] arrA = new int[arr1.size()];
        int i = 0;
        for (Integer n : arr1) {
            arrA[i] = n;
            i++;
        }

        mergeSort(arrA, 0, i - 1);

        for (int n : arrA) {
            System.out.print(n + " ");
        }
    }

    public static ArrayList<Integer> fileRead() {
        ArrayList<Integer> fileRed = new ArrayList<Integer>();

        try {
            File myObj = new File("C:\\SchoolNShiz\\JavaCoding2\\Projects\\Data Analysis\\Assignments\\Input3-1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                fileRed.add(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        return fileRed;

    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);

            merge(a, l, m, r);
        }
    }

    public static void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = a[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = a[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {

                System.out.println(L[i] + ", " + R[j]);
                a[k] = L[i];
                i++;

            } else {
                System.out.println(R[j] + ", " + L[i]);
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            System.out.print(L[i] + ", ");
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            System.out.print(R[j] + ", ");
            a[k] = R[j];
            j++;
            k++;
        }

    }

}
