import java.util.*;

class Lab4 {

    public static void main(String[] args) {
        Time t1 = new Time();

        t1.set(9, 15, 30);

        System.out.print("Initial time t1 (alt constructor used) - militia format: ");
        t1.printMilitia();

        System.out.print("Initial time t1 (alt constructor used) - standard format: ");
        t1.printStandard();

        Time t2 = new Time();

        t2.timeSet();

        System.out.print("Initial time t2 (alt constructor used) - militia format: ");
        t2.printMilitia();

        System.out.print("Initial time t2 (alt constructor used) - standard format: ");
        t2.printStandard();

        t2.set(9, 45, 35);

        System.out.print("t2 after call to set time - militia format: ");
        t2.printMilitia();

        System.out.print("t2 after call to set time - standard format: ");
        t2.printStandard();

        System.out.println("After call to equals: T1 equal t2? " + t1.equals(t2) + ".");
        System.out.println("After call to lessThan: T1 less Than t2? " + t1.lessThan(t2) + ".");

        System.out.print("Enter the time you would like to set. (Hours. Minutes. Seconds.)\n");
        int h = getInt(0, 24);
        int m = getInt(0, 59);
        int s = getInt(0, 59);

        t1.set(h, m, s);

        System.out.print("New time t1 after call to setTime - standard format: ");
        t1.printStandard();

        System.out.print("New time t1 after call to advanceSecs - standard format: ");
        t1.advanceSecs();
        t1.printStandard();

        System.out.print("New t3 after call to copy - standard format: ");
        Time t3 = t1.getCopy();
        t3.printStandard();

        System.out.print("Test toString for t3: ");
        System.out.println(t1.toString());

        System.out.print("Test toMilitia for t3: ");
        System.out.println(t1.toMilitia());

        System.out.print("Test toStandard for t3: ");
        System.out.println(t1.toStandard());

        System.out.println("After call to lessOrE: T3 less than or equal to t2? " + t3.lessOrE(t2) + ".");

        System.out.println("After call to GreaterOE: T3 greater than or equal to t1? " + t3.greaterOrE(t1) + ".");

        System.out.println("After call to greaterThan: T1 greater than t3? " + t1.greaterThan(t3) + ".");

        t3.advanceMins();
        t3.advanceHrs();
        System.out
                .println("New time t3 after call to advanceMins & advanceHours - standard format: " + t3.toStandard());

        t3.advanceMins();
        t3.advanceHrs();
        System.out.println("New time t3 after call to advanceMins & advanceHours - militia format: " + t3.toMilitia());

        System.out.println("After call to doesNotEqual: T3 not equal to t1? " + t3.doesNotEqual(t2) + ".");

        System.out.println("Time Tester Complete.");

    }

    public static int getInt(int min, int max) throws NoSuchElementException {
        Scanner inputInt = new Scanner(System.in);
        System.out.println("What is the integer you would like to use?");

        while (!inputInt.hasNextInt()) {
            System.out.println("Not an Integer mate, try again...");
            inputInt.next();
            // int a = inputInt.nextInt();
        }
        int a = inputInt.nextInt();
        while (a > max || a < min) {
            System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
            a = inputInt.nextInt();
            // int a = inputInt.nextInt();
        }

        inputInt.nextLine();
        return a;

    }

}