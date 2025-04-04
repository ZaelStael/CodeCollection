import java.util.*;

//Sample Problem
public class midterm1 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the price of your item.");
        double price = console.nextDouble();
        double tax = 1.10;
        double totCost = price * tax;

        System.out.printf("Total Cost: $%5.2f", totCost);
    }

}
