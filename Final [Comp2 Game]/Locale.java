import java.util.*;
import java.io.*;

public class Locale {

    String localeName;
    String localeDescription;
    static Locale currentLocale;
    static Locale lastLocale;

    Item[] itemsHere;
    static String[] exits;
    static Locale[] locales;
    Entity[] peopleHere;

    public Locale() {
    };

    public void Locale() {

        String localeName;
        String LocaleDescription;

    }

    public void LocaleList() {
        Locale Car = new Locale();
        Car.localeName = "Car";
        Car.localeDescription = "The 'JerryBerry' was a gift from your brother, it's smooth leather seats and red details excite you in all the right ways. Your wallet rests comfortably on the seat, even if you probably won't need it, you never know what will happen...";

        // add item to list of callable nouns. CREATE COMMANDSYSTEM!

        Locale Trunk = new Locale();
        Trunk.localeName = "Trunk";
        Trunk.localeDescription = " Your Trunk. (WIP)";

        Locale Entrance = new Locale();
        Entrance.localeName = "Entrance";
        Entrance.localeDescription = "The entrance to The Haunted Woods. (WIP)";

        Locale Woods = new Locale();
        Woods.localeName = "Woods";
        Woods.localeDescription = "The Haunted Woods. (WIP)";

        Locale LostWoods = new Locale();
        LostWoods.localeName = "Lost Woods";
        LostWoods.localeDescription = "The Lost Woods. (WIP)";

        Locale Exit1 = new Locale();
        Exit1.localeName = "Backwards";
        Exit1.localeDescription = "The way you came.";

        Locale Exit2 = new Locale();
        Exit2.localeName = "Run";
        Exit2.localeDescription = "You blindly run away.";

        Locale[] locales = { Car, Trunk, Entrance, Woods, LostWoods, Exit1, Exit2 };
    }

}
