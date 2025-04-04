public class Book extends WrittenItem {
    
    public Book() {
        super("0000", "The minds eye lies in our sky.", 777, "Bill Lewinsky");
    }

    public Book(String id, String name, int num, String au) {
        super(id, name, num, au);
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

    public boolean equals(WrittenItem i) {
        return (super.equals(i));
    }

    public String toString() {
        return (super.toString());
    }

    public void print() {
        super.print();
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
