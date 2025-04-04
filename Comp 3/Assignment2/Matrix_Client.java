import java.util.*;

public class Matrix_Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice; // operation to be executed from menu
        int gamestate = 1;
        int size;

        System.out.println("First off. What size matrix would you like? Less than or equal to 20 please.");
        size = getInt(input, 1, 20);

        Matrix first = new Matrix(size);
        Matrix second = new Matrix(size);
        Matrix result = new Matrix(size);

        first.init(1, 10);
        second.init(1, 10);

        while (gamestate == 1) {

            choice = menu(input, "That's not a number mate.", "Nothing was chosen...");

            switch (choice) {

                case 1:
                    System.out.println("Adding eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("Second Matrix:");
                    second.print();

                    System.out.println("The resulting matrix is:");
                    result = first.add(second);
                    result.print();
                    break;

                case 2:
                    System.out.println("Subbing eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("Second Matrix:");
                    second.print();

                    System.out.println("The resulting matrix is:");
                    result = first.sub(second);
                    result.print();
                    break;

                case 3:
                    System.out.println("Multiplying Matrices eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("Second Matrix:");
                    second.print();

                    System.out.println("The resulting matrix is:");
                    result = first.multiply(second);
                    result.print();
                    break;

                case 4:
                    System.out.println("Multiplying by constants eh?\nWhat's the constant?");
                    int constant = getInt(input, 1, 10);

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("Constant:");
                    System.out.println(constant);

                    System.out.println("The resulting matrix is:");
                    result = first.multiplyConst(constant);
                    result.print();
                    break;

                case 5:
                    System.out.println("Transposing eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("The resulting matrix is:");
                    result = first.transpose();
                    result.print();
                    break;

                case 6:
                    System.out.println("Tracing eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.print("The trace is: ");
                    System.out.println(first.trace());
                    break;

                case 7:
                    System.out.println("Copying eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("The resulting matrix is:");
                    result = first.getCopy();
                    result.print();

                    System.out.println(" Equal? " + first.equals(result));
                    break;

                case 8:
                    System.out.println("Multiplying Matrices eh?");

                    System.out.println("First Matrix:");
                    first.print();

                    System.out.println("Second Matrix:");
                    second.print();

                    System.out.println(" Equal? " + first.equals(result));
                    break;

                case 9:
                    System.out.println("Changing eh?");

                    System.out.println("New First Matrix:");
                    first.init(1, 10);
                    first.print();

                    System.out.println("New Second Matrix:");
                    second.init(1, 10);
                    second.print();

                    System.out.println(" Equal? " + first.equals(result));
                    break;

                case 0:
                    System.out.println("Cya mate.");
                    gamestate = 0;
                    break;

                default:
                    System.out.println("???");
                    System.exit(0);

            }

        }

    }

    public static int menu(Scanner in, String error, String error2) {
        int user = 0;
        int i = 0;

        System.out.println("_________________");
        System.out.println();
        System.out.println("YOUR OPTIONS ARE:");
        System.out.println();
        System.out.println("1) Add Two Mactrices");
        System.out.println("2) Subtract 2 Matrices");
        System.out.println("3) Multiply 2 Matrices");
        System.out.println("4) Multiply Matrix by Constant");
        System.out.println("5) Transpose Matrix");
        System.out.println("6) Matric Trace");
        System.out.println("7) Make a copy");
        System.out.println("8) Test for equality");
        System.out.println("9) Change Matrices");
        System.out.println("0) EXIT");
        System.out.println("_________________");
        System.out.println("...");
        System.out.println("Please Enter an Option:");

        while (i == 0) {
            while (!in.hasNextInt()) {
                System.out.println(error);
                in.next();
            }

            user = in.nextInt();

            switch (user) {
                case 1:
                    return user;
                case 2:
                    return user;
                case 3:
                    return user;
                case 4:
                    return user;
                case 5:
                    return user;
                case 6:
                    return user;
                case 7:
                    return user;
                case 8:
                    return user;
                case 9:
                    return user;
                case 0:
                    return user;
                default:
                    System.out.println(error2);
                    break;
            }
        }

        return user;
    }

    public static int getInt(Scanner in, int min, int max) {
        int user = 0;
        int i = 0;

        while (i == 0) {
            while (!in.hasNextInt()) {
                System.out.println("Not an Integer mate, try again...");
                in.next();
            }

            user = in.nextInt();

            if (user < min || user > max) {
                System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
            } else {
                i++;
            }
        }

        return user;
    }

}
