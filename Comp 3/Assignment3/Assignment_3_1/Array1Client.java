package Assignment_3_1;

import java.util.*;
import java.io.*;

class Array1Client {

    public static void main(String[] args) throws FileNotFoundException, NoSuchElementException {

        String arrFile;
        String arr2File;
        int key;
        Scanner console = new Scanner(System.in);

        System.out.println("For array 1: What is the name of your array file?");
        arrFile = console.nextLine();

        UnorderedArrayListN arr1 = loadFileArr(arrFile);

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

        UnorderedArrayListN arr2 = loadFileArr(arr2File);

        System.out.println();

        System.out.println("For array 1: ");
        for (int b : arr1.list) {
            if (b != 0) {
                System.out.print(b + " ");
            }
        }
        System.out.println();

        System.out.println("For array 2: ");
        for (int b : arr2.list) {
            if (b != 0) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("The Merged list is: ");
        UnorderedArrayListN result = new UnorderedArrayListN();
        result = merge(arr1, arr2);

        for (int b : result.list) {
            if (b != 0) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("Enter a key for spilt.");

        key = getInt(1, 100);

        split(result, arr1, arr2, key);

        System.out.println("Split1: ");
        for (int b : arr1.list) {
            if (b != 0) {
                System.out.print(b + " ");
            }
        }
        System.out.println();

        System.out.println("Split2: ");
        for (int b : arr2.list) {
            if (b != 0) {
                System.out.print(b + " ");
            }
        }

        System.out.println();

        System.out.println("---------------------------------------------------");
        System.out.println("Testing Done.");

    }

    public static UnorderedArrayListN loadFileArr(String arrFile)
            throws FileNotFoundException, NoSuchElementException {
        // try (Scanner console = new Scanner(System.in)) {
        try {

            try {

                Scanner read = new Scanner(new File(arrFile));
                UnorderedArrayListN fileArray = new UnorderedArrayListN();
                String[] misnomer = new String[50];

                int count = 0;
                int misCount = 0;

                while (read.hasNext()) {
                    if (!read.hasNextInt()) {

                        misnomer[misCount] = read.next();
                        misCount++;

                    } else {
                        fileArray.insertEnd(read.nextInt());
                        count++;

                    }

                }

                if (count > 0) {
                    System.out.println("The usable values are: ");

                    for (int b : fileArray.list) {
                        if (b != 0) {
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

                    UnorderedArrayListN arr1 = loadFileArr(arrFile);

                    console2.reset();

                    System.out.println();

                    System.out.println("For array 2: What is the name of your array file?");
                    arrFile = console2.nextLine();
                    UnorderedArrayListN arr2 = loadFileArr(arrFile);

                    System.out.println();

                    System.out.println("For array 1: ");
                    for (int b : arr1.list) {
                        if (b != 0) {
                            System.out.print(b + " ");
                        }
                    }
                    System.out.println();

                    System.out.println("For array 2: ");
                    for (int b : arr2.list) {
                        if (b != 0) {
                            System.out.print(b + " ");
                        }
                    }

                    System.out.println();

                    System.out.println("The Merged list is: ");
                    UnorderedArrayListN result = new UnorderedArrayListN();
                    result = merge(arr1, arr2);

                    for (int b : result.list) {
                        if (b != 0) {
                            System.out.print(b + " ");
                        }
                    }

                    System.out.println();

                    System.out.println("Enter a key for spilt.");
                    UnorderedArrayListN split1 = new UnorderedArrayListN();
                    UnorderedArrayListN split2 = new UnorderedArrayListN();
                    key = getInt(1, 100);

                    split(result, split1, split2, key);

                    System.out.println("Split1: ");
                    for (int b : split1.list) {
                        if (b != 0) {
                            System.out.print(b + " ");
                        }
                    }
                    System.out.println();

                    System.out.println("Split2: ");
                    for (int b : split2.list) {
                        if (b != 0) {
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

    public static UnorderedArrayListN merge(UnorderedArrayListN list1, UnorderedArrayListN list2) {
        final UnorderedArrayListN resList = new UnorderedArrayListN(list1.listSize() + list2.listSize());
        int c = 0;
        int l = list1.listSize() + list2.listSize();

        if (list1 instanceof UnorderedArrayListN) {
            for (int a : list1.list) {
                if (a != 0) {
                    resList.replaceAt(l, c, a);
                    resList.length++;
                    c++;
                }

            }
        } else {
            System.err.print("Incorrect call.");
        }

        if (list2 instanceof UnorderedArrayListN) {
            for (int b : list2.list) {
                if (b != 0) {
                    resList.replaceAt(l, c, b);
                    resList.length++;
                    c++;
                }
            }
        } else {
            System.err.print("Incorrect call.");
        }

        return resList;
    }

    public static void split(UnorderedArrayListN org, UnorderedArrayListN res1, UnorderedArrayListN res2,
            Integer splitK) {
        res1 = new UnorderedArrayListN();
        res2 = new UnorderedArrayListN();

        for (int i = 0; i < org.length; i++) {
            if ((org.list[i]) < splitK) {
                res1.insertEnd(org.list[i]);
            } else {
                res2.insertEnd(org.list[i]);
            }
        }

        // for (T el : list) {

        // if (((Integer) el) instanceof Integer) {
        // if (((Integer) el) < splitK) {
        // res1.insertEnd((Comparable<T>) el);
        // } else if ((Integer) el > splitK) {
        // res2.insertEnd((Comparable<T>) el);
        // }
        // } else {
        // System.err.print("What now..?");
        // }
        // }
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
