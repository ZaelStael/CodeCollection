public class MediaItem extends Item {
    private String runtime;

    public MediaItem() {
        super("0000", "The minds eye lies in our sky.", 777);
        runtime = "350 seconds";
    }

    public MediaItem(String id, String name, int num, String run) {
        super(id, name, num);
        runtime = run;
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

    public String getRun() {
        return runtime;
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

    public void setRun(String run) {
        runtime = run;
    }

    public boolean equals(MediaItem i) {
        return (super.equals(i) && runtime == i.runtime);
    }

    public String toString() {
        return (super.toString() + "\nRuntime: " + runtime);
    }

    public void print() {
        System.out.print("The name of this item is " + super.getTitle() + ". It's Idenification Number is " + super.getID() + ". We have " + super.getNumCopies() + " available." + " The runtime is " + runtime + ".");
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
