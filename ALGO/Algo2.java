import java.util.concurrent.TimeUnit;
import java.lang.*;

public class Algo2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!"); // Fluff
        System.out.println("Let's do some Algorithm Math.\nLet's say we have the following two sums:"); // Fluff
        double[] sum1 = Algor1(9999); // Initializing object array with first summation
        double[] sum2 = Algor2(10000); // Initializing object array with second summation
        double[] coSum = Algor3(9999); // Initializing object array with combined summation

        System.out.println("   2k + 3 \n2  ----------   0 <= k >= 9999\n   5k - 1"); // Stating the first summation used
        System.out.println(); // &nbsp
        System.out.println("The method would run " + sum1[0] + " times and the sum would be "
                + (double) Math.round(((double) sum1[1]) * 1000d) / 1000d + "."); // Printing count and sum of
                                                                                  // summation1

        System.out.println(); // &nbsp

        System.out.println("   k + 3 \n3  ----------   1 <= k >= 10000\n   5k - 2"); // Stating the second summation
                                                                                     // used
        System.out.println(); // &nbsp
        System.out.println("The method would run " + sum2[0] + " times and the sum would be "
                + (double) Math.round(((double) sum2[1]) * 1000d) / 1000d + "."); // Printing count and sum of
                                                                                  // summation2

        System.out.println(); // &nbsp

        System.out.println(); // &nbsp
        System.out
                .println("The total sum (difference of these two summations subtracted in seperate functions) is: "
                        + ((double) sum1[1] - (double) sum2[1]) + ".");

        TimeUnit.SECONDS.sleep(3);

        System.out.println("How long would it take if we combined the summations and then calculated?");

        System.out.println("   k^2 - 3k + 6 \n5  -------------------------   0 <= k >= 9999\n   (5k + 3)(5k - 1)"); // Stating
                                                                                                                    // the
                                                                                                                    // combined
                                                                                                                    // summation
                                                                                                                    // used
        System.out.println(); // &nbsp
        System.out.println("The method would run " + coSum[0] + " times and the sum would be "
                + (double) Math.round(((double) coSum[1]) * 1000d) / 1000d + ".");
    }

    public static double[] Algor1(int st) {
        int count = 0; // Initializing count
        double sum = 0; // Initializing sum

        for (int k = 0; k <= st; k++) {
            count++; // Incrementing count by 1 on each loop

            double t = 2 * ((2 * k) + 3); // Top half of the sum equation
            double b = (double) ((5 * k) - 1); // Bottom half of the sum equation

            // System.out.println("k of " + k + " = " + (t / b));

            sum += t / b; // Adding value calculatred by the ccurrent loop into sum

        }

        System.out.println(); // &nbsp

        double[] sum1 = new double[2]; // Priming double array to recieve count and sum
        sum1[0] = count; // Setting count
        sum1[1] = sum; // Setting sum

        return sum1; // Returning found values to main method
    }

    public static double[] Algor2(int st) {
        int count = 0; // Initializing count
        double sum = 0; // Initializing sum

        for (int k = 1; k <= st; k++) {
            count++; // Incrementing count by 1 on each loop

            double t = 3 * ((k) + 3); // Top half of the sum equation
            double b = (double) (5 * k - 2); // Bottom half of the sum equation

            // System.out.println("k of " + k + " = " + (t / b));

            sum += t / b; // Adding value calculatred by the ccurrent loop into sum

        }

        System.out.println(); // &nbsp

        double[] sum2 = new double[2]; // Priming double array to recieve count and sum
        sum2[0] = count; // Setting count
        sum2[1] = sum; // Setting sum

        return sum2; // Returning found values to main method
    }

    public static double[] Algor3(int st) {
        int count = 0; // Initializing count
        double sum = 0; // Initializing sum

        for (int k = 0; k <= st; k++) {
            count++; // Incrementing count by 1 on each loop

            double t = (double) (5 * ((Math.pow(k, 2)) - (3 * k) + 6)); // Top half of the sum equation
            double b = (double) (((5 * k) + 3) * ((5 * k) - 1)); // Bottom half of the sum equation

            // System.out.println("k of " + k + " = " + (t / b));

            sum += t / b; // Adding value calculatred by the current loop into sum

        }

        System.out.println(); // &nbsp

        double[] coSum = new double[2]; // Priming double array to recieve count and sum
        coSum[0] = count; // Setting count
        coSum[1] = sum; // Setting sum

        return coSum; // Returning found values to main method
    }
}