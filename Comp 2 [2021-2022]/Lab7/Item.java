class Item {
    private String idNumber = "";
    private String title = "";
    private int numCopies = 0;


    public Item() {
        idNumber = "0000";
        title = "The minds eye lies in our sky.";
        numCopies = 777;
    }

    public Item(String id, String name, int num) {
        idNumber = id;
        title = name;
        numCopies = num;
    }

    public String getID() {
        return idNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setID(String id) {
        idNumber = id;
    }

    public void setTitle(String name) {
        title = name;
    }

    public void setNum(int num) {
        numCopies = num;
    }

    public boolean equals(Item i) {
        return (idNumber == i.idNumber && title == i.title && numCopies == i.numCopies);
    }

    public String toString() {
        return ("Name: " + title + "\nID Number: " + idNumber + "\nAvailable Copies:" + numCopies);
    }

    public void print() {
        System.out.print("The name of this item is " + title + ". It's Idenification Number is " + idNumber + ". We have " + numCopies + " available.");
    }

    public void checkIn() {
        numCopies++;
        System.out.println(this.title + " has been checked in.");
    }

    public void checkOut() {
        numCopies--;
        System.out.println(this.title + " has been checked out.");
    }

    public void addItem() {
        numCopies++;
    }
}