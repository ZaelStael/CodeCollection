import java.util.Scanner;

class Fraction {
    private int num;
    private int dnum;

    public Fraction() {
        num = 0;
        dnum = 1;
    }

    public Fraction(int n, int d) {
        num = n;

        dnum = (d != 0 ? d : 1);
    }

    public Fraction(int n) {
        num = n;
        dnum = 1;
    }

    public void setNumDnum(int n, int d) {
        num = n;
        dnum = (d != 0 ? d : 1);
    }

    public void setNum(int n) {
        num = n;
    }

    public void setDnum(int d) {
        dnum = (d != 0 ? d : 1);
    }

    public int getNum() {
        return num;
    }

    public int getDnum() {
        return dnum;
    }

    public double transformer() {
        return ((double) num / dnum);
    }

    public boolean equals(Fraction f) {
        return (num == f.num && dnum == f.dnum);
    }

    public void read() {
        Scanner input = new Scanner(System.in);

        num = getInt(input, "Enter Numerator");

        dnum = getInt(input, "Enter Denominator");

        while (dnum == 0) {
            System.out.println("You wanna divide by zero? Nah m8.");
            dnum = getInt(input, "Enter Denominator");
        }

    }

    public int getInt(Scanner in, String w) { // port over & modify

    }

    public void print() {
        System.out.println("" + num + "/" + dnum);
    }

    public String toString() {
        return num + "/" + dnum;
    }

    public void copy(Fraction f) {
        num = f.num;
        dnum = f.dnum;

    }

    public Fraction getCopy() {
        Fraction f = new Fraction();
        f.num = num;
        f.dnum = dnum;

        return f;

        // return new Fraction(num, dnum);
    }

    public int gcf(int a, int b) {

    }

    public Fraction simplify() {

    }

    public Fraction addFraction(Fraction f) {
        int finNum = ((num * f.dnum) + (f.num * dnum));
        int finDnum = (num * dnum);

        Fraction s = new Fraction(finNum, finDnum);

        s.simplify();

        return s;
    }

    public Fraction subFraction(Fraction f) {
        int finNum = ((num * f.dnum) - (f.num * dnum));
        int finDnum = (num * dnum);

        Fraction s = new Fraction(finNum, finDnum);

        s.simplify();

        return s;
    }
}
