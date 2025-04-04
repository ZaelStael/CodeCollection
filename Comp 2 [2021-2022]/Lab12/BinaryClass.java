public class BinaryClass {

    public static void sumDigi(int n) {
        int bN = n;
        int res = 0;
        do {
            res += (((n % 10)));
            n = n / 10;
        } while (res % 10 != 0 && n != 0);

        if (n != 0) {
            do {
                res += (((n % 10)));
                n = n / 10;
            } while (res % 10 != 0 && n != 0);
        }

        System.out.println("The sum of digits for " + bN + " is " + res);

    }

    public static void sumDigiRecursive(int n) {

        int res = sumDR(n);
        System.out.println("The sum of digits for " + n + " is " + res);

    }

    public static int sumDR(int n) {

        if (n == 0) {
            return 0;
        } else {
            return (n % 10) + sumDR(n / 10);
        }

    }

    public static void binDisplay(int n) {
        int bN = n;
        String res = " ";
        int count = 0;
        do {
            res = (n % 2 + "") + res;
            n = n / 2;
            if (n == 1) {
                res = 1 + res;
            }
            count++;
        } while (n / 2 != 0 && n != 0);

        System.out.println(bN + " in binary code is " + res);

    }

    public static void binDisplayRecursive(int n) {
        String res = bDR(n);
        System.out.println(n + " in binary code is " + res);

    }

    public static String bDR(int n) {

        if (n == 0) {
            return "";
        } else {
            return bDR(n / 2) + "" + (n % 2);
        }
    }

    public static String numRep(int n, int base) { // Start Testing Bitch
        int bN = n;
        String res = "";
        do {
            res = (n % base + "") + res;
            n = n / base;
            // if (n == 1) {
            // res = 1 + res;
            // }
        } while (n / base != 0 && n > 0);

        if (n > 0) {
            do {
                res = (n % base + "") + res;
                n = n / base;
                // if (n == 1) {
                // res = 1 + res;
                // }
            } while (n / base != 0 && n > 0);
        }

        return (bN + " in base " + base + " is " + res);
    }

    public static String numRepRecursive(int n, int base) {
        String res = nMR(n, base);
        return (n + " in base " + base + " is " + res);
    }

    public static String nMR(int n, int base) {

        if (n == 0) {
            return "";
        } else {
            return nMR(n / base, base) + (n % base);
        }
    }
}
