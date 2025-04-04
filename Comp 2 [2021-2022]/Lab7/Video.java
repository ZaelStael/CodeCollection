public class Video extends MediaItem {
    private String year;
    private String genre;
    private String director;

    public Video() {
        super("0000", "The minds eye lies in our sky.", 777, "350 seconds");
        year = "1 B.C.";
        genre = "Mental";
        director = "Bill Lewinsky";
    }

    public Video(String id, String name, int num, String run, String ye, String gen, String dir) {
        super(id, name, num, run);
        year = ye;
        genre = gen;
        director = dir;

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

    public String getYear() {
        return year;
    }

    public String getGen() {
        return genre;
    }

    public String getDir() {
        return director;
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

    public void setYear(String ye) {
        year = ye;
    }

    public void setGen(String gen) {
        genre = gen;
    }

    public void setDir(String dir) {
        director = dir;
    }

    public boolean equals(Video i) {
        return (super.equals(i) && year == i.year && genre == i.genre && director == i.director);
    }

    public String toString() {
        return (super.toString() + "\nYear: " + year + "\nGenre: " + genre + "\nDirector: " + director);
    }

    public void print() {
        System.out.print("The name of this item is " + super.getTitle() + ". It's Idenification Number is " + super.getID() + ". We have " + super.getNumCopies() + " available." + " The runtime is " + super.getRun() + "."); 
        System.out.print(" It was published in " + year + ". Its genre is " + genre + ". It was directed by" + director + ".");
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
