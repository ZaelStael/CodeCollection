import java.util.ArrayList;
import java.util.List;

/*
GameState.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.

This starter code is designed for the verbs to be stored in the commandSystem.

*/

public class GameState {
    static Locale currentLocale;
    static Locale lastLocale;
    CommandSystem commandSystem;

    static List<Locale> Locales = new ArrayList<Locale>();
    static List<Item> items = new ArrayList<Item>();
    static List<Item> itemsHere = new ArrayList<Item>();
    static List<Entity> people = new ArrayList<Entity>();
    static List<Entity> peopleHere = new ArrayList<Entity>();
    static List<Item> invItems = new ArrayList<Item>();
    static List<Item> npcItems = new ArrayList<Item>();

    static String[] billDiag = new String[3];
    static String[] tylerDiag = new String[3];
    static String[] bobDiag = new String[3];
    static String[] yetiDiag = new String[3];

    public static int DISPLAY_WIDTH = 80;

    /*
     * GameState Constructor
     * 
     * Ideally, your game will be fully loaded and ready to play once this
     * constructor has finished running.
     * 
     * How things have been done here are just a rudementry setup to link the other
     * classes and have the
     * bare bones example of the command system. This is not a great way to
     * initilize your project.
     * 
     * You should do better!
     */
    public GameState() {
        commandSystem = new CommandSystem(this);

        currentLocale = new Locale();

        // Yeet Yeti Sheet

        // Create first (starting) Locale
        Locale Car = new Locale();
        currentLocale = Car;
        Car.localeName = "Car";
        Car.localeDescription = "The 'JerryBerry' was a gift from your brother, it's smooth leather seats and red details excite you in all the right ways. Your [wallet] rests comfortably on the seat, even if you probably won't need it, you never know what will happen. Anyway, you should probably grab your stuff from your [trunk] before you head out.";

        // Creates other Locales
        Locale Trunk = new Locale();
        Trunk.localeName = "Trunk";
        Trunk.localeDescription = " Looking over your hood, you spot the [entrance] to the attraction, before returning your attention to your [trunk].\nThe trunk of the JerryBerry was definitely spacious enough to hold all of your junk. Tools, games, and cords coalesced into a tangled mess near it's back. Up front, where it's much more clear, you see the things you came to grab.";

        Locale Entrance = new Locale();
        Entrance.localeName = "Entrance";
        Entrance.localeDescription = "The front entrance to the attraction is not as crowded as you would have expected around this time of year. Many of those who did come, did so in costume, making you feel bare in this place in comparison. There wasn't much of a line, and what little there was, the [ticketboy] kept moving fast. Near the entrance to what you presumed was the actual 'Haunted [Woods]' was a large man in a clean black suit, shades to match covering his eyes.";

        Locale Woods = new Locale();
        Woods.localeName = "Woods";
        Woods.localeDescription = "Black and brown thorns wrap around the forest, almost like teeth waiting to tear at you. The trees seem to close in, choking out the light and letting the fog roll around like a breath. Sharp shadows, darker than the deepest forest slink about just out of sight, while unseen creatures move branches to stalk your path. Maybe you could [use] something to help you out.";

        Locale LostWoods = new Locale();
        LostWoods.localeName = "Lost Woods";
        LostWoods.localeDescription = "You find yourself even further into the woods, the dense foiliage above nearly blocking out all of the moon's light. You feel unsafe, like somethings watching you. You can't see what it is, but you don't know if you want to...";

        Locale YetiWoods = new Locale();
        YetiWoods.localeName = "Yeti Woods";
        YetiWoods.localeDescription = "You stumble into a clearing in the darkened forest, the moonlight shining down elegantly upon a lone figure seated on a stump. It looks like a very hairy man wearing a bowler hat and smoothly playing a small violin. It turns it's head, never ceasing to play, and looks not at you but into you. A voice spills into your head asking if you are lost as you find your legs moving closer towards it on their own. It kind of looks like a [yeti] but not? Maybe you could [talk] to it.";

        Locale Exit1 = new Locale();
        Exit1.localeName = "Backwards";
        Exit1.localeDescription = "You go back way you came.";

        Locale Exit2 = new Locale();
        Exit2.localeName = "Run";
        Exit2.localeDescription = "You blindly ran away.";

        // Adds each Locale to a list of possible Locales
        Locales.add(Car);
        Locales.add(Trunk);
        Locales.add(Woods);
        Locales.add(Entrance);
        Locales.add(Exit1);
        Locales.add(LostWoods);
        Locales.add(Exit2);
        Locales.add(YetiWoods); // (Secret Locale: Run in Lost Woods)

        // Adds exits to Locales

        // Items in the game
        Item Compass = new Item();
        Compass.itemName = "Compass";
        Compass.itemDesc = "The compass shines beautifully in the pale moonlight evident on this night.";
        Compass.used = false;
        Compass.has = false;

        Item Phone = new Item();
        Phone.itemName = "Phone";
        Phone.itemDesc = "It's screen glows dimly in your palm, the darkness around you making the it that much harder to see. It will likely die soon.";
        Phone.used = false;
        Phone.has = false;

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

        // Adds items to a list
        items.add(Compass);
        items.add(Phone);
        items.add(Thread);
        items.add(Map);
        items.add(Wallet);
        items.add(Ticket);

        invItems.add(Phone);

        // Entities in the game

        Entity Bill = new Entity();
        Bill.entName = "bill";
        Bill.entDesc = "A pimble covered young boy stands at the ticket counter, a bored face, hoping-- searching for anyhting of substance to look at.";
        Bill.timesSpoke = 0;
        Bill.mad = false;

        Entity Tyler = new Entity();
        Tyler.entName = "tyler";
        Tyler.entDesc = "A horrifying creature emodying some cruel mix of a lion, a ghost, and a bloody tampon comes shambling at you with outstreched arms.";
        Tyler.timesSpoke = 0;
        Tyler.mad = false;

        Entity Bob = new Entity();
        Bob.entName = "bob";
        Bob.entDesc = "A smug look wrests upon her face, as if your presence alone was enough to ruin her day. You look down at her nametag to see who you'll be addressing... Bob...";
        Bob.timesSpoke = 0;
        Bob.mad = false;

        Entity Yeti = new Entity();
        Yeti.entName = "Tiberius";
        Yeti.entDesc = "How would you even begin to describe a-- supposedly-- fictional creature? It's fur is majestic glistening like newly spun silk on moonlit winter's night. His eyes, oh his eyes...";
        Yeti.timesSpoke = 0;
        Yeti.mad = false;

        // Adds to people list
        people.add(Bill);
        people.add(Tyler);
        people.add(Bob);
        people.add(Yeti);

        npcItems.add(Ticket);

        // Entity dialogue

        billDiag[0] = "Welcome to The Huanted Forest Of Wails, would you like to go in tonight?";
        billDiag[1] = "Do you play DnD?";
        billDiag[2] = "I always wondered why people in horror movies panicked, [Run]ning all over the place and then just stopping. Maybe if they kept running they would find their way back.";

        tylerDiag[0] = "Your soooooouuuuuuulllllll...";
        tylerDiag[1] = "Boooooooooooooooo...";
        tylerDiag[2] = "In the original Scream, the high school janitor is named 'Fred' and wears red and green shirt, an allusion to Nightmare on Elm Street, also directed by Wes Craven. The janitor is also, funnily enough, played by Craven himself.";

        bobDiag[0] = "Why have you been harrasing my employees?";
        bobDiag[1] = "You got some nerve trying to talk to me after the stunt you pulled!";
        bobDiag[2] = "Human lives can easily be snuffed out you know. They're like candles on windy nights.";

        yetiDiag[0] = "What is it littlefoot?";
        yetiDiag[1] = "The stars are often cold, their hearts cry out in loneliness, and their eyes leak with sorrow.";
        yetiDiag[2] = "How beautiful this world is, never let the walls of civilization stifle who YOU wish to be.";

        // Add item to list of nouns so our command system knows it exists.
        commandSystem.addNoun(Ticket.itemName);
        commandSystem.addNoun(Car.localeName);
        commandSystem.addNoun(Woods.localeName);
        commandSystem.addNoun(Entrance.localeName);
        commandSystem.addNoun(Exit1.localeName);
        commandSystem.addNoun(Exit2.localeName);
        commandSystem.addNoun(LostWoods.localeName);
        commandSystem.addNoun(Trunk.localeName);
        commandSystem.addNoun(YetiWoods.localeName);
        commandSystem.addNoun(Wallet.itemName);
        commandSystem.addNoun(Phone.itemName);
        commandSystem.addNoun(Compass.itemName);
        commandSystem.addNoun(Map.itemName);
        commandSystem.addNoun(Thread.itemName);
        commandSystem.addNoun(Bill.entName);
        commandSystem.addNoun(Tyler.entName);
        commandSystem.addNoun(Bob.entName);
        commandSystem.addNoun("47");
        commandSystem.addNoun("yeti");
        commandSystem.addNoun("Yeti");
        commandSystem.addNoun("inventory");
        commandSystem.addNoun("pockets");
        commandSystem.addNoun("creature");

        // Clears

        invItems.removeAll(invItems); /// CHECK!!!

        /*
         * Once the commandSystem knows about the item, we need to code what happens
         * with each of the commands that can happen with the item.
         * See CommandSystem line 64 for what happens if you currently "look mat"
         */
    }
}