//Java program to find maximum rectangular area in linear 

//time 

import java.util.*;

public class RectAreaCompleted {

    static int width;

    // class made to represent a point
    // Anish was here
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = 0;
            this.y = 0;
        }

        public void clear() {
        }
    }

    // The main function to find the maximum rectangular area within a given polygon
    // represented by ordered pairs
    static int getMaxArea(Point points[], int n) {
        int heights[] = new int[n];
        // Calculate the heights array which represents the heights of bars in a
        // histogram
        for (int i = 0; i < n; i++) {
            heights[i] = points[i].y;
        }

        // Create an empty stack. The stack holds indexes of
        // hist[] array The bars stored in stack are always
        // in increasing order of their heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp; // To store top of stack
        int area_with_top; // To store area with top bar as
                           // the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n) {
            // If this bar is higher than the bar on top
            // stack, push it to stack
            if (s.empty() || heights[s.peek()] <= heights[i])
                s.push(i++);

            // If this bar is lower than top of stack, then
            // calculate area of rectangle with stack top as
            // the smallest (or minimum height) bar. 'i' is
            // 'right index' for the top and element before
            // top in stack is 'left index'
            else {
                tp = s.peek(); // store the top index
                s.pop(); // pop the top

                // Calculate the area with heights[tp] stack as
                // smallest bar
                width = (s.empty() ? i : i - s.peek());

                area_with_top = heights[tp] * width;

                // update max area, if needed
                max_area = Math.max(max_area, area_with_top);
            }
        }

        // Now pop the remaining bars from stack and
        // calculate area with every popped bar as the
        // smallest bar
        while (s.empty() == false) {
            tp = s.peek();
            s.pop();
            width = (s.empty() ? i : i - s.peek());

            area_with_top = heights[tp] * width;

            max_area = Math.max(max_area, area_with_top);
        }

        return max_area;
    }

    public static int arrXSearch(ArrayList<Point> a, int n) {
        int i = 0;

        for (Point p : a) {
            if (p.x == n) {
                break;
            }
            i++;

        }

        return i;
    }

    public static int arrYSearch(ArrayList<Point> a, int n) {
        int i = 0;

        for (Point p : a) {
            if (p.y == n) {
                break;
            }
            i++;

        }

        return i;
    }

    public static int getInt(int min, int max) throws NoSuchElementException {
        Scanner inputInt = new Scanner(System.in);

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

    // Driver code
    public static void main(String[] args) {
        // Sample input
        // Number of vertices of the polygon
        System.out.println("How many points will you be using?");

        int n = getInt(0, 1000000);
        int[] xArr = new int[n];
        int[] yArr = new int[n];

        int i = n * 2;
        int k = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Now what are those points? Input x a 'space' then y.");
        while (i > 0) {
            if (i % 2 == 0) {
                while (!in.hasNextInt()) {
                    System.out.println("Not an Integer mate, try again...");
                    in.next();
                    // int a = inputInt.nextInt();
                }
                int a = in.nextInt();
                xArr[k] = a;
                i--;
            }
            if (i % 2 != 0) {
                while (!in.hasNextInt()) {
                    System.out.println("Not an Integer mate, try again...");
                    in.next();
                    // int a = inputInt.nextInt();
                }
                int a = in.nextInt();
                yArr[k] = a;
                i--;

            }

            if ((xArr[k] < 0 || xArr[k] > 1000000) || (yArr[k] < 0 || yArr[k] > 1000000)) {
                System.out.println("Not within range of 0 to 1000000, try again.");
            } else {
                k++;
            }

        }

        int yLow = 100;
        int xLow = 100;
        int xHigh = 0;
        int xH1 = 0;
        int xH2 = 0;

        ArrayList<Point> points = new ArrayList<>();

        for (int b = 0; b < n; b++) {
            points.add(new Point(xArr[b], yArr[b]));
        }

        for (int b = 0; b < n; b++) {
            points.get(b).x = xArr[b];
            points.get(b).y = yArr[b];
        }

        for (Point P : points) {
            if (P.y <= yLow && P.y != 0) {
                if ((P.x - xLow) > xHigh) {
                    xHigh = (P.x - xLow);
                    xH1 = xLow;
                    xH2 = P.x;
                }
                yLow = P.y;
                xLow = P.x;

            }

        }

        int area = (xHigh * yLow);
        yLow = 100;

        int xPlace = arrXSearch(points, xH2);

        for (int l = (xH1); l < (xPlace); l++) {
            if (points.get(l).y < yLow) {
                yLow = points.get(l).y;
                if ((xHigh * yLow) != 0) {
                    area = (xHigh * yLow);
                }
            }
        }

        int yL = 0;
        int yR = 0;
        int yC = 0;
        int yArea = 0;

        yL = points.get(0).x;
        yC = points.get(0).y;

        for (int l = 0; l < n; l++) {

            if (points.get(l).y != yC) {

                yR = points.get(l).x;
                int tempY = yC * (yR - yL);
                if (tempY > yArea) {
                    yArea = tempY;
                }
                yC = points.get(l).y;
                yL = points.get(l).x;

            }
        }

        // int n = points.length;
        System.out.println("Maximum area is " + Math.max(area, yArea));

        // Function call
        // System.out.println("Maximum area is " + getMaxArea(points, n));
    }
}
// This code is Contributed by Sumit Ghosh and Tyrique Baker