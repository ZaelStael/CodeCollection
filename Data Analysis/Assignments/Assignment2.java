import java.util.*;
import java.lang.Math;

public class Assignment2 {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        List<Integer> arr1 = new ArrayList<Integer>();
        List<Integer> ind = new ArrayList<Integer>();
        boolean state = true;
        int addy = 0;
        ;

        System.out.println(
                "So I heard you had some numbers for me...something about finding longest sequences backwards or something? \nGo ahead and put your numbers in, type '-1000000' to exit.");

        while (state = true) {
            System.out.println("What is the integer you would like to use?");

            while (!input1.hasNextInt()) {
                System.out.println("Try again...");
                input1.next();
            }
            addy = input1.nextInt();
            if (addy == -1000000) {
                state = false;
                break;
            }
            arr1.add(addy);
            System.out.println("Another?");

        }
        input1.close();

        int size = arr1.size();
        int[] arr3 = new int[size];
        int count = size;
        int[] arr2 = new int[size];

        for (int i = 0; i < size; i++) {
            arr2[i] = arr1.get(i);
        }

        for (int i = 0; i < size; i++) {
            arr3[i] = size + 1;
        }

        for (int j = (size - 1); j > 1; j--) {
            for (int l = j; l > 0; l--) {
                if (arr2[j] < arr2[l] || arr3[j] > arr3[l] - 1) {
                    if (!ind.contains(arr2[j])) {
                        ind.add(arr2[j]);
                    }
                    arr3[l] = arr3[1] - 1;

                }
            }
        }

        for (int c : arr3) {
            if (c < count) {
                count = c;
            }
        }

        System.out.println("Count = " + count);
        System.out.println(ind);

    }
}
