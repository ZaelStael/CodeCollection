package misc;

public class Fib {

    public static void main(String[] args) {

        for (int i = 0; i <= 20; i++) {
            if (i == 20) {
                System.out.printf("%1d", fib(20));
            } else {
                System.out.printf("%1d,", fib(i));
            }
        }

        printStars(5);

    }

    public static int fib(int nthNum) {

        if (nthNum <= 1) {
            return 0;
        }

        if (nthNum == 2) {
            return 1;
        }

        return fib(nthNum - 1) + fib(nthNum - 2);
    }

    public static void printStars(int numStars){
        
        if (numStars == 0){
            return;
        } else {
            for (int i = 0; i < numStars; i++){
                String result += "*";
                printStars(numStars-1);
                System.out.println(result);
            }
        }
    }
}