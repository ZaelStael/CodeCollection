import java.util.*;
import java.io.*;

class Array4Client extends ArrayList {

    public static void main(String[] args) throws FileNotFoundException, NoSuchElementException {

        String arrFile;
        String arr2File;
        int key;
        Scanner console = new Scanner(System.in);

        System.out.println("For array 1: What is the name of your array file?");
        arrFile = console.nextLine();

        ArrayList<Integer> arr1 = loadFileArr(100, arrFile);

        console.reset();

        System.out.println();

        System.out.println("For array 2: What is the name of your array file?");
        arr2File = console.nextLine();
        ArrayList<Integer> arr2 = loadFileArr(100, arr2File);

        System.out.println();

        System.out.println("For array 1: ");
        for (Integer b : arr1) {
            System.out.print(b + " ");
        }
        System.out.println();

        System.out.println("For array 2: ");
        for (Integer b : arr2) {
            System.out.print(b + " ");
        }

        System.out.println();

        System.out.println("The Merged list is: ");
        ArrayList<Integer> result = new ArrayList<>();
        result = merge(arr1, arr2);

        for (Integer b : result) {
            System.out.print(b + " ");
        }
        System.out.println();

        System.out.println("The Merged list sorted is: ");
        bubbleSort(result);

        for (Integer b : result) {
            System.out.print(b + " ");
        }

        System.out.println();

        System.out.println("Enter a key for spilt.");
        ArrayList<Integer> split1 = new ArrayList<>();
        ArrayList<Integer> split2 = new ArrayList<>();
        key = getInt(1, 100);

        split(result, split1, split2, key);

        System.out.println("Split1: ");
        for (Integer b : split1) {
            System.out.print(b + " ");
        }
        System.out.println();

        System.out.println("Split2: ");
        for (Integer b : split2) {
            System.out.print(b + " ");
        }

        System.out.println();

        System.out.println("---------------------------------------------------");
        System.out.println("Testing Done.");

    }

    public static ArrayList<Integer> loadFileArr(int max, String arrFile)
            throws FileNotFoundException, NoSuchElementException {
        Scanner console = new Scanner(System.in);
        try {

            try {

                Scanner read = new Scanner(new File(arrFile));
                ArrayList<Integer> fileArray = new ArrayList<>();
                ArrayList<String> misnomer = new ArrayList<String>();
                int count = 0;

                while (read.hasNext() && count <= max) {
                    if (!read.hasNextInt()) {
                        misnomer.add(read.next());

                    } else {
                        fileArray.add(read.nextInt());
                        count++;

                    }

                }

                if (count > 0) {
                    System.out.println("The usable values are: ");

                    for (int b : fileArray) {
                        System.out.printf("%s  ", b);
                    }

                    System.out.println("The unwanted values are: ");

                    for (String b : misnomer) {
                        System.out.printf("%s  ", b);
                    }

                    read.close();

                    return fileArray;

                } else {
                    System.out
                            .println("You think you're funny? Where are the integers? I don't have time for this.");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("We messed something up...try again.");
                e.printStackTrace();

                int key;

                System.out.println("For array 1: What is the name of your array file?");
                arrFile = console.nextLine();

                ArrayList<Integer> arr1 = loadFileArr(100, arrFile);

                console.reset();

                System.out.println();

                System.out.println("For array 2: What is the name of your array file?");
                arrFile = console.nextLine();
                ArrayList<Integer> arr2 = loadFileArr(100, arrFile);

                System.out.println();

                System.out.println("For array 1: ");
                for (Integer b : arr1) {
                    System.out.print(b + " ");
                }
                System.out.println();

                System.out.println("For array 2: ");
                for (Integer b : arr2) {
                    System.out.print(b + " ");
                }

                System.out.println();

                System.out.println("The Merged list is: ");
                ArrayList<Integer> result = new ArrayList<>();
                result = merge(arr1, arr2);

                for (Integer b : result) {
                    System.out.print(b + " ");
                }
                System.out.println();

                System.out.println("The Merged list sorted is: ");
                bubbleSort(result);

                for (Integer b : result) {
                    System.out.print(b + " ");
                }

                System.out.println();

                System.out.println("Enter a key for spilt.");
                ArrayList<Integer> split1 = new ArrayList<>();
                ArrayList<Integer> split2 = new ArrayList<>();
                key = getInt(1, 100);
                split(result, split1, split2, key);

                System.out.println("Split1: ");
                for (Integer b : split1) {
                    System.out.print(b + " ");
                }
                System.out.println();

                System.out.println("Split2: ");
                for (Integer b : split2) {
                    System.out.print(b + " ");
                }

                System.out.println();

                System.out.println("---------------------------------------------------");
                System.out.println("Testing Done.");

            }
        } catch (Exception e) {
            System.out.println("We messed something up...");
            e.printStackTrace();
            System.exit(0);
        }
        return null;

    }

    public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {

        ArrayList<Integer> result = new ArrayList<>();

        for (Integer add1 : a) {
            result.add(add1);
        }

        for (Integer add2 : b) {
            result.add(add2);
        }

        return result;

    }

    public static void split(ArrayList<Integer> a, ArrayList<Integer> res1, ArrayList<Integer> res2, int splitN) {

        for (Integer el : a) {
            if (el < splitN) {
                res1.add(el);
            } else if (el > splitN) {
                res2.add(el);
            }
        }

    }

    public static void bubbleSort(ArrayList<Integer> a) {

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = 0; j < a.size() - i - 1; j++) {
                if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                    int tmp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, tmp);
                }

            }
        }

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = 0; j < a.size() - i - 1; j++) {
                if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                    int tmp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set((j + 1), tmp);
                }

            }
        }

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = 0; j < a.size() - (j + 1); j++) {
                if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                    int tmp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set((j + 1), tmp);
                }
            }
        }
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
}