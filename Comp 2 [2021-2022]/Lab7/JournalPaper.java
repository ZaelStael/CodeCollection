public class JournalPaper extends WrittenItem{
    private String year;

    public JournalPaper() {
        super("0000", "The minds eye lies in our sky.", 777, "Bill Lewinsky");
        year = "1 B.C.";
    }

    public JournalPaper(String id, String name, int num, String au, String ye) {
        super(id, name, num, au);
        year = ye;

    }

    public String getID() {
        return super.getID();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public int getNumCopies() {
        return super.getNumCopies();
    }

    public String getAuthor() {
        return super.getAuthor();
    }

    public String getYear() {
        return year;
    }

    public void setID(String id) {
        super.setID(id);
    }

    public void setTitle(String name) {
        super.setTitle(name);
    }

    public void setTitle(int num) {
        super.setNum(num);
    } 

    public void setAuthor(String au) {
        super.setAuthor(au);
    }

    public void setYear(String ye) {
        year = ye;
    }

    public boolean equals(JournalPaper i) {
        return (super.equals(i) && year == i.year);
    }

    public String toString() {
        return (super.toString() + "\nYear: " + year);
    }

    public void print() {
        System.out.print("The name of this item is " + super.getTitle() + ". It's Idenification Number is " + super.getID() + ". We have " + super.getNumCopies() + " available." + " The author is " + super.getAuthor() + ". It was published in " + year + "."); 
    }
    public void checkIn() {
        super.checkIn();
    }

    public void checkOut() {
        super.checkOut();
    }

    public void addItem() {
        super.addItem();
    }
}
