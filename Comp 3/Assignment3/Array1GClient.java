
import java.util.*;
import java.io.*;

class Array1GClient {

    public static <T> void main(String[] args) throws FileNotFoundException, NoSuchElementException {

        String arrFile;
        String arr2File;
        int key;
        Scanner console = new Scanner(System.in);

        System.out.println("For array 1: What is the name of your array file?");
        arrFile = console.nextLine();

        UnorderedArrayList<T> arr1 = loadFileArr(arrFile);

        // console.reset();

        System.out.println();

        System.out.println("For array 2: What is the name of your array file?");

        if (console.hasNextLine()) {
            arr2File = console.nextLine();
        } else {
            console.close();
            try (Scanner console2 = new Scanner(System.in)) {
                arr2File = console2.nextLine();
            }
        }

        UnorderedArrayList<T> arr2 = loadFileArr(arr2File);

        System.out.println();

        System.out.println("For array 1: ");
        for (T b : arr1.list) {
            if (b != null) {
                System.out.print(b + " ");
            }
        }
        System.out.println();

        System.out.println("For array 2: ");
        for (T b : arr2.list) {
            if (b != null) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("The Merged list is: ");
        UnorderedArrayList<T> result = new UnorderedArrayList<>();
        result = arr1.merge(arr2);

        for (T b : result.list) {
            if (b != null) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("Enter a key for spilt.");

        key = getInt(1, 100);

        result.split(arr1, arr2, key);

        System.out.println("Split1: ");
        for (T b : arr1.list) {
            if (b != null) {
                System.out.print(b + " ");
            }
        }
        System.out.println();

        System.out.println("Split2: ");
        for (T b : arr2.list) {
            if (b != null) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("---------------------------------------------------");
        System.out.println("Testing Done.");

    }

    public static <T> UnorderedArrayList<T> loadFileArr(String arrFile)
            throws FileNotFoundException, NoSuchElementException {
        // try (Scanner console = new Scanner(System.in)) {
        try {

            try {

                Scanner read = new Scanner(new File(arrFile));
                UnorderedArrayList<T> fileArray = new UnorderedArrayList<>();
                UnorderedArrayList<T> misnomer = new UnorderedArrayList<>();
                int count = 0;

                while (read.hasNext()) {
                    if (!read.hasNextInt()) {
                        misnomer.insertEnd((Comparable<T>) read.next());

                    } else {
                        fileArray.insertEnd((Comparable<T>) read.next());
                        count++;

                    }

                }

                if (count > 0) {
                    System.out.println("The usable values are: ");

                    for (T b : fileArray.list) {
                        if (b != null) {
                            System.out.printf("%s  ", b);
                        }

                    }

                    read.reset();

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

                try (Scanner console2 = new Scanner(System.in)) {
                    System.out.println("For array 1: What is the name of your array file?");
                    arrFile = console2.nextLine();

                    UnorderedArrayList<T> arr1 = loadFileArr(arrFile);

                    console2.reset();

                    System.out.println();

                    System.out.println("For array 2: What is the name of your array file?");
                    arrFile = console2.nextLine();
                    UnorderedArrayList<T> arr2 = loadFileArr(arrFile);

                    System.out.println();

                    System.out.println("For array 1: ");
                    for (T b : arr1.list) {
                        if (b != null) {
                            System.out.print(b + " ");
                        }
                    }
                    System.out.println();

                    System.out.println("For array 2: ");
                    for (T b : arr2.list) {
                        if (b != null) {
                            System.out.print(b + " ");
                        }
                    }

                    System.out.println();

                    System.out.println("The Merged list is: ");
                    UnorderedArrayList<T> result = new UnorderedArrayList<>();
                    result = arr1.merge(arr2);

                    for (T b : result.list) {
                        if (b != null) {
                            System.out.print(b + " ");
                        }
                    }

                    System.out.println();

                    System.out.println("Enter a key for spilt.");
                    UnorderedArrayList<T> split1 = new UnorderedArrayList<>();
                    UnorderedArrayList<T> split2 = new UnorderedArrayList<>();
                    key = getInt(1, 100);

                    result.split(split1, split2, key);

                    System.out.println("Split1: ");
                    for (T b : split1.list) {
                        if (b != null) {
                            System.out.print(b + " ");
                        }
                    }
                    System.out.println();

                    System.out.println("Split2: ");
                    for (T b : split2.list) {
                        if (b != null) {
                            System.out.print(b + " ");
                        }
                    }
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
        // }
        return null;

    }

    public static int getInt(int min, int max) throws NoSuchElementException {
        try (Scanner inputInt = new Scanner(System.in)) {
            while (!inputInt.hasNextInt()) {
                System.out.println("Not an Integer mate, try again...");
                inputInt.next();

            }
            int a = inputInt.nextInt();
            while (a > max || a < min) {
                System.out.println("Not within the acceptable range " + min + " to " + max + " bruh. Try again.");
                a = inputInt.nextInt();

            }

            inputInt.nextLine();
            return a;
        }

    }
}
