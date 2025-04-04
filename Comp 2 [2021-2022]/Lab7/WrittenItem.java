public class WrittenItem extends Item {
    private String author;

    public WrittenItem() {
        super("0000", "The minds eye lies in our sky.", 777);
        author = "Bill Lewinksy";
    }

    public WrittenItem(String id, String name, int num, String au) {
        super(id, name, num);
        author = au;
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
        return author;
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
        author = au;
    }

    public boolean equals(WrittenItem i) {
        return (super.equals(i) && author == i.author);
    }

    public String toString() {
        return (super.toString() + "\nAuthor: " + author);
    }

    public void print() {
        System.out.print("The name of this item is " + super.getTitle() + ". It's Idenification Number is " + super.getID() + ". We have " + super.getNumCopies() + " available." + " The author is " + author + ".");
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
