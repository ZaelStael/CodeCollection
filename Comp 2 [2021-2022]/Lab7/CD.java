public class CD extends MediaItem {
    private String genre;
    private String artist;

    public CD() {
        super("0000", "The minds eye lies in our sky.", 777, "350 seconds");
        genre = "Mental";
        artist = "Bill Lewinsky";
    }

    public CD(String id, String name, int num, String run, String gen, String art) {
        super(id, name, num, run);
        
        genre = gen;
        artist = art;

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
        return super.getRun();
    }

    public String getGen() {
        return genre;
    }

    public String getArt() {
        return artist;
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
        super.setRun(run);
    }

    public void setGen(String gen) {
        genre = gen;
    }

    public void setDir(String art) {
        artist = art;
    }

    public boolean equals(CD i) {
        return (super.equals(i) && genre == i.genre && artist == i.artist);
    }

    public String toString() {
        return (super.toString() + "\nGenre: " + genre + "\nArtist: " + artist);
    }

    public void print() {
        System.out.print("The name of this item is " + super.getTitle() + ". It's Idenification Number is " + super.getID() + ". We have " + super.getNumCopies() + " available." + " The runtime is " + super.getRun() + "."); 
        System.out.print(". Its genre is " + genre + ". It was created by " + artist + ".");
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
