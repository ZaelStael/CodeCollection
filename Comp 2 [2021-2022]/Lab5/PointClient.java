import java.util.*;

public class PointClient {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Point p1 = new Point(0,0);
        Point p2 = new Point(7,13);
        Point p3 = new Point(7,15);

        System.out.println("First point is "+ p1.toString() + ".");
        System.out.println("Second point is " + p2.toString() + ".");
        System.out.println("Third point is " + p3.toString() + ".");

        if (p2.isVertical(p3)) {
            System.out.println("Second and Third points line up vetically.");
        
        }

        if (!p2.isHorizontal(p3)) {
            System.out.println("Second and Third points are not lined up horizontally");
        }

        System.out.println("We're changing the first point, throw in an x-coordinate:");
        int x1 = getPosInt(in,1,100);
        int y1 = getPosInt(in, 1, 100);
        p1.set(x1, y1);

        System.out.println("First point is "+ p1.toString() + ".");
        System.out.println("Distance from origin for first point is " + p1.distanceFromOrigin());
        System.out.println("Distance from origin for second point is " + p2.distanceFromOrigin());
        System.out.println("Distance between first and second point is " + p1.distance(p2));

        System.out.println("We're translating points this time. Enter by how much you want to move x and y forward:");
        x1 = getPosInt(in,1,100);
        y1 = getPosInt(in, 1, 100);
        p1.translate(x1, y1);
        System.out.println("First point is "+ p1.toString() + ".");
        

        System.out.println("We're translating points again. Enter by how much you want to move x and y forward:");
        x1 = getPosInt(in,1,100);
        y1 = getPosInt(in, 1, 100);
        p2.translate(x1, y1);
        System.out.println("Second point is " + p2.toString() + ".");

        System.out.println("P1 and P2 equal? " + p1.equals(p2) + ".");

        p1.copy(p2);
        p1.print();
        p2.print();

        System.out.println("First point is "+ p1.toString() + ".");
        System.out.println("Second point is " + p2.toString() + ".");

        System.out.println("P1 and P2 equal? " + p1.equals(p2) + ".");





    
    
    }

    public static int getPosInt(Scanner inputInt, int min, int max) throws NoSuchElementException {
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
        while (a < 0) {
            System.out.println("Positive numbers only bromigo.");
            a = inputInt.nextInt();
        }

        inputInt.nextLine();
        return a;
    }
    


}
