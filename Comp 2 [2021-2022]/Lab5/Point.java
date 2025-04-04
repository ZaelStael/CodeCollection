import java.lang.Math;
import java.util.*;

class Point {
    int x;
    int y;
    
    
    public Point() {

    }

    public Point(int a, int b) {
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int a, int b) {
        x = a;
        y = b;
    }

    public void print() {
        System.out.println("(" + x + ", " + y +")");
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Point p) {
        return (p.x == x && p.y == y);
    }

    public void copy(Point p) {
        x = p.x;
        y = p.y;
    }

    public Point getCopy() {
        Point p = new Point(x,y);

        return p;
    }

    public double distanceFromOrigin() {
        Point origin = new Point(0,0);
        
        if (this.equals(origin)) {
            return 0;
        } else {
            double dX = x*x;
            double dY = y*y;
            double dis = Math.sqrt(dX + dY);

            return dis;
        }

    }

    public double distance(Point p) {
        int pY;
        int pX;
        double dY;
        double dX;
        double dist;

        if (this.equals(p)) {
            return 0;
        } else {
            if (y > p.y) {
                pY = (y - p.y);
            } else {
                pY = (p.y - y);
            }

            if (x > p.x) {
                pX = (x - p.x);
            } else {
                pX = (p.x - x);
            }

            dY = pY * pY;
            dX = pX * pX;

            dist = Math.sqrt(dX + dY);
            return dist;
        }
    }

    public void translate(int a, int b) {
        x = x + a;
        if (x < 0) {
            System.out.println("Quadrant 1 only!");
            x = x - a;
        }

        y = y + b;
        if (y < 0) {
            System.out.println("Quadrant 1 only!");
            y = y - a;
        }
    }

    public boolean isHorizontal(Point p) {
        return (y == p.y);
    }

    public boolean isVertical(Point p) {
        return (x == p.x);
    }

    public double slope(Point p) {
        double sY;
        double sX;
        double slope;

        if (this.equals(p)) {
            return 0;
        } else {
            
            sY = (p.y - y);
            

            
            sX = (p.x - x);
            

            slope = sY/sX;
            
            return slope;
        }
    }

    public int getPosInt(Scanner inputInt, int min, int max) throws NoSuchElementException {
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