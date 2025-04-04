import java.util.*;

public class Item {

    // All items to be made: Compass, Phone, Thread, Map, Wallet
    String itemName;
    String itemDesc;
    static Item[] list;
    boolean has;
    boolean used;

    public Item() {
    };

    public void newItem() {

        String itemName;
        String itemDesc;

        has = false;
        used = false;

    }

    public String getDesc() {
        return itemDesc;
    }

    public static void itemList() {
        Item Compass = new Item();
        Compass.itemName = "compass";
        Compass.itemDesc = "The compass shines beautifully in the pale moonlight evident on this night.";
        Compass.used = false;
        Compass.has = false;

        Item Phone = new Item();
        Phone.itemName = "Phone";
        Phone.itemDesc = "It's screen glows dimly in your palm, the darkness around you making the it that much harder to see. It will likely die soon.";
        Phone.used = false;
        Phone.has = true;

        Item Thread = new Item();
        Thread.itemName = "Thread";
        Thread.itemDesc = "The threads colors look like glistening spider silk, it feels thin, yet strong on your fingertips.";
        Thread.used = false;
        Thread.has = false;

        Item Map = new Item();
        Map.itemName = "Map";
        Map.itemDesc = "It's really dark out here, you can just barely make out the parking lot through the darkness. Maybe your phone could help.";
        Map.used = false;
        Map.has = false;

        Item Wallet = new Item();
        Wallet.itemName = "Wallet";
        Wallet.itemDesc = "The coarse but smooth leather of your wallet feels pleasing in your hands, the fullness of it sending your excitement to new peaks.";
        Wallet.used = false;
        Wallet.has = false;

        Item Ticket = new Item();
        Ticket.itemName = "Ticket";
        Ticket.itemDesc = "A slip of thin paper, used to gain entrance to The Haunted Woods.";
        Ticket.used = false;
        Ticket.has = false;

        Item[] list = { Compass, Phone, Thread, Map, Wallet, Ticket };
    }

    public String getName() {
        return itemName;
    }

}
