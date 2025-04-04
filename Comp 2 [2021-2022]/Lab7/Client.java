public class Client {
    public static void main(String[] args) {
        Book I1 = new Book("123456", "To kill a mockingbird", 5, "Harper Lee");
        Book I2 = new Book();

        System.out.println("Book Info");

        
        System.out.println(I1);
        System.out.println("");
        System.out.println(I2);
        
        System.out.println("---------------------");
        
        System.out.println("Info After checking out one of each book.");
        I1.checkOut();
        I2.checkOut();
        System.out.println();
        System.out.println();
        System.out.println(I1);
        System.out.println();
        System.out.println();
        System.out.println(I2);
        System.out.println();

        System.out.println("Info after checking in one of each book.");
        I1.checkIn();
        I2.checkIn();
        System.out.println();
        System.out.println();
        System.out.println(I1);
        System.out.println();
        System.out.println();
        System.out.println(I2);

        System.out.println("---------------------");

        Video V1 = new Video("1111", "The Godfather", 3, "215 minutes", "1981", "Drama", "Francis DFord Coppola");
        Video V2 = new Video();
        
        System.out.println("Video Info");

        System.out.println(V1);
        System.out.println();
        System.out.println();
        System.out.println(V2);

        System.out.println("---------------------");

        JournalPaper W1 = new JournalPaper("12357", "Tommorow, Never, and Forever", 1, "Your Own S.", "1999");
        JournalPaper W2 = new JournalPaper();

        System.out.println("Journal Paper Info (using print)");

        W1.print();
        System.out.println();
        System.out.println();
        W2.print();
        System.out.println();
        

        System.out.println("---------------------");

        CD C1 = new CD("1597", "After Hours", 5, "69 minutes", "Pop", "The Weeknd");
        CD C2 = new CD();

        System.out.println("CD Info");

        System.out.println(C1);
        C2.print();
        System.out.println();
        System.out.println();
        System.out.println(C2);
        C1.print();
        System.out.println();
        

        System.out.println("---------------------");

        System.out.println("CD names after changing with setters");

        C1.setTitle("Dawn FM");
        C2.setTitle("Where or When");

        System.out.println(C1.getTitle());
        System.out.println(C2.getTitle());

        System.out.println("-------The-End-------");

        


    }
}
