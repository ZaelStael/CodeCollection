
/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.Math;

class MainF {
    public static void main(String[] args) throws FileNotFoundException {
        int elementNum = getInt(1, 100);

        int[] arr1 = loadFileArr(elementNum);

        System.out.print("Your randomly generated values are: ");
        print(arr1);

        int ans = menu();

        switch (ans) {

            case 1:
                System.out.println(isAllEven(arr1));
                break;

            case 2:
                System.out.println(isUnique(arr1));
                break;

            case 3:
                System.out.println("The minimum gap between values is " + minGap(arr1));
                break;

            case 4:
                System.out.println("The mean for this list is: " + getMean(arr1));
                System.out.println("The variance for this list is: " + getVariance(arr1));
                System.out.println("The standardard deviation for this list is: " + (Math.sqrt(getVariance(arr1))));
                break;

            case 5:
                System.out.println("The list sorted: \n");
                print(bubbleSort(arr1));
                System.out.println("80th-percentile from this list: ");
                top_20(arr1);
                break;

            case 0:
                System.out.println("Deuces.");
                System.exit(0);

        }

    }

    public static int[] loadFileArr(int max) throws FileNotFoundException { // Needs debugging
        try {
            try (Scanner console = new Scanner(System.in)) {
                System.out.println("What is the name of your array file?");
                String arrFile = console.nextLine();

                try {

                    Scanner read = new Scanner(new File(arrFile));
                    int[] fileArray = new int[max];
                    ArrayList<String> misnomer = new ArrayList<String>();
                    int count = 0;

                    int i = 0;

                    while (read.hasNext() && count <= max) {
                        if (!read.hasNextInt()) {
                            misnomer.add(read.next());

                        } else {
                            fileArray[i] = read.nextInt();
                            i++;
                            count++;

                        }

                    }

                    if (count > 0) {
                        System.out.println("The usable values are: ");

                        for (int b : fileArray) {
                            System.out.printf("'%s  '", b);
                        }

                        System.out.println("The unwanted values are: ");

                        for (String b : misnomer) {
                            System.out.printf("'%s  '", b);
                        }

                        return fileArray;

                    } else {
                        System.out
                                .println("You think you're funny? Where are the integers? I don't have time for this.");
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("We messed something up...");
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println("We messed something up...");
            e.printStackTrace();
            System.exit(0);
        }
        return null;

    }

    public static int getInt(int min, int max) throws FileNotFoundException {
        try (Scanner inputInt = new Scanner(System.in)) {
            System.out.println("What is the integer you would like to use?");

            while (!inputInt.hasNextInt()) {
                System.out.println("Not an Integer mate, try again...");
                inputInt.nextLine();
                int a = inputInt.nextInt();
            }
            while (inputInt.nextInt() > max || inputInt.nextInt() < min) {
                System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
                inputInt.nextLine();
                int a = inputInt.nextInt();
            }
            int a = inputInt.nextInt();

            inputInt.nextLine();
            return a;
        }
    }

    static public int[] initRand(int a, int max, int min) throws FileNotFoundException { // Create a holding array in
                                                                                         // actual 'runner method'.
        final int[] rdmArray = new int[a];
        Random arrNum = new Random();

        for (int i = 0; i < a; i++) {
            rdmArray[i] = arrNum.nextInt(max) + min;
        }

        return rdmArray;
    }

    public static void print(int[] a) throws FileNotFoundException {
        for (int b : a) {
            System.out.print(" " + b + " ");
        }
        System.out.println();
    }

    public static boolean isAllEven(int[] a) throws FileNotFoundException {
        int evenCount = 0;
        int oddCount = 0;

        for (int b : a) {
            if (b % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        if (oddCount > 0) {

            System.out.println("There are " + oddCount + " odd numbers and " + evenCount
                    + " even numbers. All values are not even.");
            return false;
        } else {
            System.out.println("All values are even.");
            return true;
        }
    }

    public static boolean isUnique(int[] a) throws FileNotFoundException {
        boolean repeats = false;

        for (int i = 0; i < a.length; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) {
                    count++;
                }

            }
            if (count > 1) {
                repeats = true;
            }
        }

        if (repeats) {
            System.out.println("These numbers are not unique.");
            return false;
        } else {
            System.out.println("Everything seems unique.");
            return true;
        }
    }

    public static int minGap(int[] a) throws FileNotFoundException {
        if (a.length < 2) {
            return 0;
        }

        int min = (a[1] - a[0]);

        for (int i = 0; i > a.length - 1; i++) {
            min = a[(i + 1)] - a[i];
        }

        return min;
    }

    static public int menu() throws FileNotFoundException {
        try (Scanner input = new Scanner(System.in)) {
        }

        System.out.println("Your options are: \n -----------");
        System.out.println("\t1) All even valus?");
        System.out.println("\t2) All unique values?");
        System.out.println("\t3) Print min gap between values");
        System.out.println("\t4) Statistics");
        System.out.println("\t5) Print 80% percentile");
        System.out.println("\t0) EXIT");

        int ans = getInt(0, 5);

        return ans;

    }

    static public int[] bubbleSort(int[] a) throws FileNotFoundException {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[(j + 1)]) {

                    int temp = a[j];
                    a[j] = a[(j + 1)];
                    a[(j + 1)] = temp;
                }
            }
        }

        return a;
    }

    public static int[] copyArray(int[] a) throws FileNotFoundException {
        int[] b = a;

        return b;
    }

    static public void top_20(int[] a) throws FileNotFoundException {
        int[] b = bubbleSort(copyArray(a));
        int n = (a.length / 5);
        int m = (a.length - 1);

        for (int i = m; i > (m - n); i--) {
            System.out.printf("%d%n", b[i]);
        }

    }

    static public int getMean(int[] a) throws FileNotFoundException {
        int sum = 0;
        int n = a.length;

        for (int b : a) {
            sum += b;
        }

        int avg = (sum / n);

        return avg;
    }

    public static int getVariance(int[] list) throws FileNotFoundException {
        int avg = getMean(list);
        int n = list.length;
        int[] devScores = new int[n];
        int[] sqrDevScores = new int[n];
        int sqrSum = 0;

        for (int i = 0; i < n; i++) {
            devScores[i] = (list[i] - avg);
        }

        for (int j = 0; j < n; j++) {
            sqrDevScores[j] = (int) Math.pow(devScores[j], 2);
        }

        for (int b : sqrDevScores) {
            sqrSum += b;
        }

        int var = (sqrSum / (n - 1));

        return var;
    }
}
