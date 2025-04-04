import java.util.*;

class Lab3 {

    public static void main(String[] args) throws NoSuchElementException {
        int gamestate = 1;
        Scanner ansIn = new Scanner(System.in);
            String ans = " ";
            int ansInt = 0;
            int count = 1;

            while (gamestate == 1) {
                if (count == 1) {
                    System.out.println("Start? (Y/N)");
                    ans = ansIn.nextLine();
                    count++;
                } else {
                    System.out.println("Continue?");
                    ans = ansIn.nextLine();
                }
                if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("Yes")) {
                    System.out.println("You can tell me whatever number you would like to start an array with.");
                    ansInt = getInt(1, 100);

                    int arr1[] = initRand(ansInt, 100, 0);

                    System.out.println("The first array is... \n");
                    print(arr1);

                    System.out.println("The second array is... \n");
                    print(twice(arr1, ansInt));

                   // System.out.println("Continue? (Y/N)");
                    //ans = ansIn.nextLine();
                

            
                } else if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("No")) {
                    System.out.println("Ciao.");
                    gamestate = 0;
                } else {
                    System.out.println("...\nImma leave now...");
                    gamestate = 0;
                }

            }
        
        

    }

    public static int getInt(int min, int max) throws NoSuchElementException {
        Scanner inputInt = new Scanner(System.in);
        System.out.println("What is the integer you would like to use?");

   
        while (!inputInt.hasNextInt()) {
            System.out.println("Not an Integer mate, try again...");
            inputInt.next();
            //int a = inputInt.nextInt();
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

    static public int[] initRand(int a, int max, int min) throws NoSuchElementException { // Create a holding array in actual 'runner method'.
        final int[] rdmArray = new int[a];
        Random arrNum = new Random();

        for (int i = 0; i < a; i++) {
            rdmArray[i] = arrNum.nextInt(max)+min;
        }

        return rdmArray;
    }

    public static void print(int[] a) throws NoSuchElementException {
        for (int b: a) {
            System.out.printf("%d  ", b);
        }
        System.out.println();
    }

    public static int[] twice(int[] arr1, int size) throws NoSuchElementException {
        ArrayList<Integer> copyArr = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            copyArr.add(arr1[i]);
            copyArr.add(arr1[i]);

        }

        int[] coArr = new int[(size*2)];

        for (int i = 0; i < (size*2); i++) {
            coArr[i] = (int) copyArr.get(i);          
        }

        return coArr;
    }

}