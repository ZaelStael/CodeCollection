import java.lang.*;

public class Algo1 {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); // Fluff
        System.out.println("Let's do some ALgorithm Math.\nLet's say we have the following:"); // Fluff
        System.out.println("3k + 4 \n----------   1 <= k >= 20\n5(k^2) - 1"); // Stating the problem used
        System.out.println();
        System.out.println(Algor(20));

    }

    public static String Algor(int st) {
        int count = 0; // Initializing count
        double sum = 0; // Initializing sum

        for (int k = 1; k <= st; k++) {
            count++; // Incrementing count by 1 on each loop

            double t = (3 * k) + 4; // Top half of the sum equation
            double b = (double) (5 * (Math.pow(k, 2)) - 1); // Bottom half of the sum equation

            System.out.println("k of " + k + " = " + (t / b));

            sum += t / b; // Adding value calculatred by the ccurrent loop into sum

        }

        System.out.println();

        return "The method would run " + count + " times and the sum would be "
                + (double) Math.round(sum * 1000d) / 1000d + "."; // Sending calaculations back to main method
    }
}
