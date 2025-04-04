
import java.util.*;

public class ClientEmployee {
    public static void main(String[] arg) {
        Scanner input = new Scanner(System.in);

        String last, first, dept;
        double pay_rate;
        double hours;

        Employee prof = new Employee("John", "Doe", 25.50, 50, "COSC");
        Employee newEmp = new Employee();

        System.out.print("Enter employee last name: ");
        last = getString(input);

        System.out.print("Enter employee first name: ");
        first = getString(input);

        System.out.print("Enter department: ");
        dept = getString(input);

        System.out.print("Enter employee pay rate: ");
        pay_rate = getPosDouble(input, 1, 100);

        System.out.print("Enter employee hours worked: ");
        hours = getPosDouble(input, 1, 100);

        newEmp.setAll(first, last, pay_rate, hours, dept);

        System.out.println("--- Record for both employees with overridden .toString from subclass --- ");
        System.out.println(prof.toString());
        System.out.println(newEmp.toString());

        System.out.println("--- Output with calls to overridden method print from subclass --- ");
        prof.print();
        newEmp.print();

        System.out.println("--- Output with calls to getters from the superclass --- ");
        System.out.println("The wages for " + newEmp.getFirstName() + " " + newEmp.getLastName() + " from the "
                + newEmp.getDepartment() + " department are: " + newEmp.calculatePay());

        System.out.println("--- Call to overridden equals/subclass for 2 Employee objects--- ");

        System.out.println("Employees Equal in all counts? " + prof.equals(newEmp) + ".");

    }

    public static Double getPosDouble(Scanner inputInt, double min, double max) throws NoSuchElementException {
        System.out.println("What is the double you would like to use?");

        while (!inputInt.hasNextDouble()) {
            System.out.println("Not an double mate, try again...");
            inputInt.next();
        }
        double a = inputInt.nextDouble();
        while (a > max || a < min) {
            System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
            a = inputInt.nextDouble();
        }
        while (a < 0) {
            System.out.println("Positive numbers only bromigo.");
            a = inputInt.nextDouble();
        }

        inputInt.nextLine();
        return a;
    }

    public static String getString(Scanner inputInt) throws NoSuchElementException {
        System.out.println("What is the name you would like to use?");

        while (!inputInt.hasNextLine()) {
            System.out.println("Not a String mate, try again...");
            inputInt.nextLine();
        }
        String a = inputInt.nextLine();

        // inputInt.nextLine();
        return a;
    }
}
