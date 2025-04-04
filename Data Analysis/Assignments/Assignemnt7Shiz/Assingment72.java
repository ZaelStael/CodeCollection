package Assignemnt7Shiz;

import java.io.*;
import java.util.*;

public class Assingment72 {
    // Find max value in array
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }

        }
        return mx;
    }

    // count sorting based on the digit of exp
    static void countSort(int arr[], int n, int exp, int k) {

        // Initialize placeholder (output) array, counter (i), and
        int output[] = new int[n];
        int i;
        int count[] = new int[k];
        Arrays.fill(count, 0);

        // Find number of occurences and store them in the count array
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % k] += 1;
        }

        // Change count[i] so that it contains actual positionof the digit for the next
        // array
        for (i = 1; i < k; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = (n - 1); i >= 0; i--) {
            output[count[(arr[i] / exp) % k] - 1] = arr[i];
            count[(arr[i] / exp) % k]--;
        }

        // Replace original array with output, which should be sorted to the current
        // place
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }

    }

    // Recursively sorts by way of countsort for an array of size n, k is the chosen
    // base
    static void radixSort(int arr[], int n, int k) {

        // Find max number, m, to track the highest number of digits/places
        int m = getMax(arr, n);

        // CountSort every digit. exp will equal 10^i, where i is the current digit
        // place
        for (int exp = 1; m / exp > 0; exp *= k) {
            countSort(arr, n, exp, k);
        }

    }

    static void print(int arr[], int n) {
        // Print to screen
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int n = 0;
        int k = 0;

        ArrayList<Integer> nums = new ArrayList<Integer>();

        // Take data we need from a file and initialize a list to work with
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Please enter the name of the file:");
            String fileName = input.nextLine();

            try {
                File fileReader = new File(fileName);
                Scanner myReader = new Scanner(fileReader);
                while (myReader.hasNextInt()) {
                    int data = myReader.nextInt();
                    nums.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");

            }
        }
        // Find size of list and initialize array to place them into
        n = nums.size();
        int[] arr = new int[n];

        // Transfer list to array
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }

        // Find our k with formula provided
        k = (int) (Math.pow(n, 2) - 1);

        // Print original array for comparison later
        print(arr, n);

        // Sort using digits/places and then provide a new line
        radixSort(arr, n, k);
        System.out.println("");

        // print sorted array
        print(arr, n);

        System.out.println("");

        System.out.print("Our n is " + n + ", which means our base (k) will be " + k + ".");

    }

}
