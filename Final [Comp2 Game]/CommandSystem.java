import java.util.*;

import javax.xml.transform.Templates;

public class CommandSystem { // FORMAT ALL SYSTEM.OUTS

    private static int DISPLAY_WIDTH;
    private GameState state;

    private static List<String> verbs = new ArrayList<String>();
    private static List<String> verbDescription = new ArrayList<String>();
    private static List<String> nouns = new ArrayList<String>();

    static Item[] ilist = Item.list;
    ArrayList<String> itemNames = new ArrayList<String>();
    ArrayList<String> itemDescs = new ArrayList<String>();
    int pCharges = 3;
    int lastQuote = 0;
    int timesLost = 0;
    int manAnger = 0;

    public CommandSystem(GameState State) {
        this.state = state; /// Question
        DISPLAY_WIDTH = GameState.DISPLAY_WIDTH;
        addVerb("?", "Show this help screen.");
        addVerb("look",
                "Use the look command by itself to look at your immediate surroundings. \nYou can also look at certain people and objects, gaining useful information as you do. \n(Use Examples: look Woods, look Bill, look Compass)");
        addVerb("l", "Second verse same as the first (Another form of the 'look' command.");
        addVerb("move", "Use this keyword combined with a place in order to go to the designated location.");
        addVerb("goto", "Another form of the 'move to' command.");
        addVerb("m", "Same as the 'move' command.");
        addVerb("leave",
                "If used this command takes you back to the previous lacation. (WARNING: Disabled during certain paths)");
        addVerb("run", "Run around in a blind panic.");
        addVerb("talkto", "Communicate with the designated entity.");
        addVerb("talk", "Same as the 'talk to' command.");
        addVerb("t", "Same as the 'talk to' command.");
        addVerb("quit", "End the game...");

        // Verbs to add
        addVerb("give", "Give a particular item to the designated entity.");
        addVerb("take",
                "You can take an item not belonging to an entity OR You can take a particular item from the designated entity.");
        addVerb("tk", "Same as th 'take' command.");
        addVerb("use", "Use an item.");
        addVerb("hit", "Hit the designated entity.");

    }

    public void addNoun(String string) {
        if (!nouns.add(string.toLowerCase())) {
            nouns.add(string.toLowerCase());
        }

    }

    public static void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description.toLowerCase());

    }

    public void executeVerb(String verb) {
        switch (verb) {
            case "?":
                printHelp();
                break;
            case "look":
                System.out.println("You look around.");
                System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));
                break;
            case "l":
                System.out.println("You look around.");
                System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));
                break;

            case "leave":
                executeVerbNoun("m", "backwards");
                break;

            case "run":
                System.out.println("You run away, not keeping track of where you're going.");
                if (GameState.currentLocale == GameState.Locales.get(5) && GameState.people.get(3).mad == false) {
                    System.out.println(formatStringToScreenWidth(changeLocale("YetiWoods")));
                    break;
                }
                System.out.println(formatStringToScreenWidth(changeLocale("run")));
                break;

            case "take":
                System.out.println(formatStringToScreenWidth("Take what?"));
                break;

            case "give":
                System.out.println(formatStringToScreenWidth("Give what?"));
                break;

            case "m":
            case "moveto":
                System.out.println(formatStringToScreenWidth("Move where?"));
                break;

            case "talk":
            case "talkto":
                System.out.println(formatStringToScreenWidth("Whom do you wish to converse with?"));
                break;

            case "hit":
                System.out.println(formatStringToScreenWidth("Hit who?"));
                break;

            case "quit":
                System.out.println("Ending 0: F*** this game.");
                System.exit(0);
        }
    }

    public void executeVerbNoun(String verb, String noun) {

        String resultString = "";

        switch (verb) {
            case "look":
                resultString += lookAt(noun);
                break;
            case "l":
                resultString += lookAt(noun);
                break;
            case "moveto":
                resultString += changeLocale(noun);
                break;
            case "m":
                resultString += changeLocale(noun);
                break;
            case "goto":
                resultString += changeLocale(noun);
                break;
            case "talk":
            case "talkto":
                resultString += talkTo(noun);
                break;
            case "t":
                resultString += talkTo(noun);
                break;
            case "use":
                resultString += use(noun);
                break;
            case "take":
            case "tk":
                resultString += take(noun);
                break;
            case "hit":
                resultString += hit(noun);
                break;
        }
        System.out.println(formatStringToScreenWidth(resultString));
    }

    public void executeVerbNounNoun(String verb, String noun1, String noun2) {

        String resultString = "";

        switch (verb) {
            case "give":
                System.out.println(formatStringToScreenWidth(give(noun1, noun2)));
                break;

            case "take":
                takeFrom(noun1, noun2);
                break;

        }
        System.out.println(formatStringToScreenWidth(resultString));

    }

    public String give(String item, String ent) { // add inv interaction
        String resultString = "";
        switch (item) {
            case "wallet":
                if (GameState.currentLocale == GameState.Locales.get(3) && ent.equalsIgnoreCase("Bill")) {
                    System.out.println(
                            "Thank you for your patronage on this most unholy night. Head on over and [give] your ticket to [47], he'll let you through.");
                    GameState.invItems.add(GameState.items.get(5));
                    GameState.items.get(5).has = true;
                    resultString += GameState.items.get(5).itemName + " has been placed in your pocket.";
                    // System.out.println(resultString);
                    break;

                } else if (GameState.currentLocale == GameState.Locales.get(3) && GameState.people.get(2).mad
                        && ent.equalsIgnoreCase("Bob")) {
                    System.out.println("You should be ashamed of yourself.");
                    GameState.invItems.remove(GameState.items.get(4));
                    GameState.items.get(4).has = false;
                    System.out.println(GameState.items.get(4).itemName
                            + " has been taken from your hands, rumaged through, and set on fire with a blowtorch. Jeez.");
                    resultString += "Bob then allows you back into the attraction warning you that your on thin ice.";
                    // System.out.println(resultString);
                    break;

                } else if (GameState.currentLocale == GameState.Locales.get(3) && GameState.people.get(2).mad
                        && ent.equalsIgnoreCase("Manager")) {
                    System.out.println("You should be ashamed of yourself.");
                    GameState.invItems.remove(GameState.items.get(4));
                    GameState.items.get(4).has = false;
                    System.out.println(GameState.items.get(4).itemName
                            + " has been taken from your hands, rumaged through, and set on fire with a blowtorch. Jeez.");
                    resultString += "Bob then allows you back into the attraction warning you that your on thin ice.";
                    // System.out.println(resultString);
                    break;

                } else {
                    resultString += "Now why would you do that?";
                    break;
                }

            case "map":
                if (ent.equalsIgnoreCase("Yeti")) {
                    resultString += "The creature takes the map from your hands befoe chanting some sort of ancient hymm. A white light radiates from his palm, heavenly voices filling your ears. He stretches his hand out, map now glowing vibrantly, and tells you to [take] it.";
                    break;
                } else {
                    resultString += "To who? Why would they want that?";
                    break;
                }

            case "ticket":
                if (GameState.items.get(5).has && ent.equalsIgnoreCase("47")) {
                    if (GameState.currentLocale == GameState.Locales.get(3)) {
                        GameState.items.get(5).used = true;
                        System.out.println(
                                "You show 47(?) your ticket, he eats it in one bite, and then steps to the side. You can now enter The Haunted [Woods].");
                        GameState.items.get(5).has = false;
                        resultString += "The ticket has been removed from your inventory.";
                        break;

                    } else {
                        resultString += "You don't have that item yet.";
                        break;
                    }
                }

            default:
                resultString += "Why are you trying to give people things... there's no romance system... unless...";
                break;
        }

        return resultString;

    }

    public String take(String item) {
        String resultString = "";
        switch (item) {
            case "phone":
                GameState.invItems.add(GameState.items.get(1));
                GameState.items.get(1).has = true;
                if (GameState.invItems.size() > 3) {
                    GameState.invItems.remove(GameState.items.get(1));
                    resultString += "You don't have enough space in your pockets.";
                    break;
                } else if (GameState.invItems.size() <= 3) {
                    resultString += GameState.items.get(1).itemName + " has been placed in your pocket.";
                    break;

                }

            case "compass":
                GameState.invItems.add(GameState.items.get(0));
                GameState.items.get(0).has = true;
                if (GameState.invItems.size() > 3) {
                    GameState.invItems.remove(GameState.items.get(0));
                    resultString += "You don't have enough space in your pockets.";
                    break;
                } else if (GameState.invItems.size() <= 3) {
                    resultString += GameState.items.get(0).itemName + " has been placed in your pocket.";
                    break;

                }

            case "thread":
                GameState.invItems.add(GameState.items.get(2));
                GameState.items.get(2).has = true;
                if (GameState.invItems.size() > 3) {
                    GameState.invItems.remove(GameState.items.get(2));
                    resultString += "You don't have enough space in your pockets.";
                    break;

                } else if (GameState.invItems.size() <= 3) {
                    resultString += GameState.items.get(2).itemName + " has been placed in your pocket.";
                    break;

                }

            case "map":
                GameState.invItems.add(GameState.items.get(3));
                GameState.items.get(3).has = true;
                if (GameState.invItems.size() > 3) {
                    GameState.invItems.remove(GameState.items.get(3));
                    resultString += "You don't have enough space in your pockets.";
                    break;

                } else if (GameState.invItems.size() <= 3) {
                    resultString += GameState.items.get(3).itemName + " has been placed in your pocket.";
                    break;

                }

            case "wallet":
                GameState.invItems.add(GameState.items.get(4));
                GameState.items.get(4).has = true;
                if (GameState.invItems.size() > 3) {
                    GameState.invItems.remove(GameState.items.get(4));
                    resultString += "You don't have enough space in your pockets.";
                    break;
                } else if (GameState.invItems.size() <= 3) {
                    resultString += GameState.items.get(4).itemName + " has been placed in your pocket.";
                    break;

                }
        }
        return resultString;
    }

    public void takeFrom(String item, String ent) {

        String resultString = "";
        Scanner in = new Scanner(System.in);
        String ans = "";

        switch (item) {
            case "map":
                if (ent.equalsIgnoreCase("Yeti")) {
                    // Yeti encounter, upgrades map to magic map (Starts if you 'run' in the lost
                    // woods, you wind up in Yeti Woods.)
                    if (GameState.currentLocale == GameState.Locales.get(7)) {
                        System.out.println(
                                "Your map now looks freshly drawn, it's glossy finish gliding through your fingers. It even glows.\nYou now have the [Magic Map].");
                        GameState.items.get(3).itemName = "Magic Map";
                        GameState.items.get(
                                3).itemDesc = "Your map has been revitalized, no longer is it a faded remainder from the ice age. It clearly shows you how to get back.";
                        System.out.println(
                                "The yeti tips his hat to you, slowly backing up until his form is obscured by the bushes.\nEventually you can't even hear his steps any more, and begin heading back.");
                        System.out.println("...");
                        System.out.println(
                                "You find your way out, get in your car, and ponder on the validity of the nights events.");
                        System.out.println("Leave?");
                        ans = in.nextLine();
                        if (ans.equalsIgnoreCase("Yes")) {
                            System.out.println("You drive away.\n(Secret Ending: When you wish upon a yeti.)");
                            System.exit(0);
                        } else if (ans.equalsIgnoreCase("No")) {
                            break;
                        }

                        break;

                    } else {
                        resultString += "What?";
                        break;
                    }
                } else {
                    resultString += " You may be trying to use the take command, try that.";
                    break;
                }
            case "ticket":
                if (ent.equalsIgnoreCase("Bill") && GameState.currentLocale == GameState.Locales.get(3)) {
                    System.out
                            .println("The young boy looks at you confused but nonetheless hands you another ticket...");
                    resultString += "You now have two tickets!! For some reason...";
                    break;

                }
            default:
                resultString += "Why would you take that.";
                break;

        }

        System.out.println(resultString);

    }

    public String use(String item) { // fix

        String resultString = "";
        String ans;
        Scanner in = new Scanner(System.in);

        switch (item) {
            case "compass":
                if (GameState.items.get(0).has) {
                    in.reset();
                    return "It's constantly spinning in circles. Huh, must be broken.";
                } else {
                    return "You don't have that item yet.";
                }

            case "phone":

                if (pCharges == 0) {
                    System.out.println("Your phone's dead, are you next?");

                    return resultString;
                } else if (pCharges > 0) {
                    System.out.println(
                            "Your phone's screen flickers dimly to life, you should've charged it before you left.\nIt may be bright enough to look at the [map] though.");
                    pCharges--;
                    if (GameState.items.get(3).has) {
                        System.out.println("Use map?");
                        ans = in.nextLine();

                        if (ans.equalsIgnoreCase("yes")) {

                            resultString += "You belive walking [backwards] may lead you back to the main attraction.";
                            return resultString;
                        } else if (ans.equalsIgnoreCase("no")) {

                            resultString += "Suit yourself.";
                            return resultString;
                        } else {
                            resultString += "(Yes/No Only). Try again.";
                            return resultString;

                        }
                    } else {
                        resultString += "Unfortunately for you, you didn't bring it with you...";
                        return resultString;
                    }
                }

            case "thread":
                if (GameState.items.get(2).has) {
                    if (GameState.currentLocale == GameState.Locales.get(2)) {
                        GameState.items.get(2).used = true;
                        in.reset();
                        return "You wrap one end of the thread around a tree branch, putting the rest of the spool in your back pocket.";
                    }
                    if (GameState.currentLocale == GameState.Locales.get(5) && GameState.items.get(2).used == true) {
                        System.out.println("You follow the thread back to the path.");
                        changeLocale("woods");
                        System.out.println(
                                "You found your way back out, that was close. You drive home and have a nice relaxing bath...\n (Ending 2: A-MAZE-ing tactics)");
                        System.exit(0);
                    }
                } else {
                    return "You don't have that item yet.";
                }

            case "map":
                if (GameState.items.get(3).has) {
                    if (GameState.currentLocale == GameState.Locales.get(0)
                            || GameState.currentLocale == GameState.Locales.get(3)) {
                        in.reset();
                        return "This dusty map  vaguely looks like it would be useful to find your way. You are currently at "
                                + GameState.currentLocale + ".";
                    } else {
                        in.reset();
                        return "It's too dark to use this.";
                    }
                }

            case "wallet":
                if (GameState.items.get(4).has) {
                    if (GameState.currentLocale == GameState.Locales.get(3)
                            && GameState.people.get(0).timesSpoke >= 1) {
                        GameState.items.get(4).used = true;
                        GameState.items.get(5).has = true;
                        in.reset();
                        return "You got a ticket! You can now enter the [woods].";
                    } else if (GameState.currentLocale == GameState.Locales.get(3)
                            && GameState.people.get(0).timesSpoke < 1) {
                        System.out.println(
                                "It would be rude to just thrust your wallet at the poor boy, [talk] to him first.");
                        return "The ticketboy awaits your conversation.";
                    }
                } else {
                    return "You don't have that item yet.";
                }

            case "ticket":
                if (GameState.items.get(5).has) {
                    if (GameState.currentLocale == GameState.Locales.get(3)) {
                        GameState.items.get(5).used = true;
                        System.out.println(
                                "You show 47(?) your ticket, he eats it in one bite, and then steps to the side. \nYou can now enter The Haunted [Woods].");
                        GameState.items.get(5).has = false;
                        return "The ticket has been removed from your inventory.";

                    } else {
                        return "You don't have that item yet.";
                    }
                }

            default:
                in.reset();
                return resultString;
        }

    }

    public String changeLocale(String noun) {

        String resultString = "";
        String ans = "";
        Scanner in = new Scanner(System.in);

        switch (noun) {
            case "car":

                if (GameState.currentLocale == GameState.Locales.get(1)
                        || GameState.currentLocale == GameState.Locales.get(3)) {
                    GameState.lastLocale = GameState.currentLocale;
                    GameState.currentLocale = GameState.Locales.get(0);

                    GameState.itemsHere.removeAll(GameState.itemsHere);
                    GameState.itemsHere.add(GameState.items.get(4));

                    resultString += GameState.currentLocale.localeDescription;
                    System.out.println(formatStringToScreenWidth(resultString));
                    System.out.println("Would you like to leave?");
                    ans = in.nextLine();
                    if (ans.equalsIgnoreCase("yes")) {
                        System.out.println(
                                "You drive home and have a nice relaxing bath...\n(Ending 1: Didn't feel like it.) ");
                        System.exit(0);
                    } else if (ans.equalsIgnoreCase("no")) {
                        return "Okay.";
                    } else {
                        return "Next time say yes or no.";
                    }

                } else {
                    resultString += "You can't get there from here.";
                    return resultString;
                }

            case "trunk":

                if (GameState.currentLocale == GameState.Locales.get(0)) {
                    GameState.lastLocale = GameState.currentLocale;
                    GameState.currentLocale = GameState.Locales.get(1);

                    GameState.itemsHere.removeAll(GameState.itemsHere);
                    GameState.itemsHere.add(GameState.items.get(0));
                    GameState.itemsHere.add(GameState.items.get(1));
                    GameState.itemsHere.add(GameState.items.get(2));
                    GameState.itemsHere.add(GameState.items.get(3));

                    System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));
                    System.out.println("You see:");
                    for (Item i : GameState.itemsHere) {
                        System.out.println(" A " + i.itemName + ".");
                    }

                    resultString += "You have enough pocket space to take three items. \nWhat will you take?";
                    return resultString;
                } else {
                    resultString += "You can't get there from here.";
                    return resultString;
                }

            case "woods":

                if (GameState.currentLocale == GameState.Locales.get(3)) {
                    if (GameState.items.get(5).used == true) {
                        GameState.lastLocale = GameState.currentLocale;
                        GameState.currentLocale = GameState.Locales.get(2);
                        System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));
                        System.out.println(formatStringToScreenWidth(GameState.people.get(1).entDesc));
                        System.out.println(formatStringToScreenWidth(
                                "The creature then begins to run at you while grumbling in some unknown language."));

                        resultString += "You could try and [run] or maybe even [hit] the [creature]. What will you do?";
                        return resultString;

                    } else {
                        resultString += "You can't go there yet.";
                        return resultString;
                    }
                } else {
                    resultString += "You can't get there from here.";
                    return resultString;
                }

            case "entrance":

                if (GameState.currentLocale == GameState.Locales.get(0)
                        || GameState.currentLocale == GameState.Locales.get(1)) {
                    GameState.lastLocale = GameState.currentLocale;
                    GameState.currentLocale = GameState.Locales.get(3);
                    System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));

                    GameState.itemsHere.removeAll(GameState.itemsHere);
                    GameState.itemsHere.add(GameState.items.get(5));

                    resultString += "You walk over to the actual entrance, hoping to get a peak inside. The buff agent [47] looking dude asks you whether or not you have a ticket. Before you have a chance to answer, he nods in the direction of the ticket booth with an annoyed grunt, helmed by a pimply tennager with a nametag identifying him as [Bill]. You should probably [talk] to him and [give] him your [wallet] so he can grab the cash. Why would you hand over your entire wallet? Just do it...";
                    return resultString;
                } else {
                    resultString += "You can't get there from here.";
                    return resultString;
                }

            case "backwards":
                if (GameState.currentLocale == GameState.Locales.get(0)) {
                    resultString += "To go where?";
                    return resultString;
                }
                Locale temp = GameState.lastLocale;
                Locale temp2 = GameState.currentLocale;
                GameState.currentLocale = GameState.Locales.get(4);
                System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));
                GameState.currentLocale = temp;

                resultString += temp.localeDescription;
                return resultString;

            case "run":
                if (GameState.currentLocale == GameState.Locales.get(2)
                        || GameState.currentLocale == GameState.Locales.get(5)) {
                    System.out.println(formatStringToScreenWidth(GameState.Locales.get(6).localeDescription));
                    GameState.currentLocale = GameState.Locales.get(5);
                    System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));

                    timesLost++;
                    if (timesLost == 3) {
                        System.out.println(formatStringToScreenWidth(
                                "You get lost in the woods, no one hears from you again, and after a year of desperate campaigns, your family is forced into a settlement with the attraction.\n (Ending 3: Missing?)"));
                        System.exit(0);
                    }

                    resultString += "You have no idea where you are. Maybe you could [use] something to find your way back.";
                    return resultString;
                } else {
                    if (GameState.currentLocale != GameState.Locales.get(2)
                            || GameState.currentLocale != GameState.Locales.get(5)) {
                        resultString += "Why are you running?";
                        return resultString;
                    }
                }

            case "YetiWoods":
                if (GameState.currentLocale == GameState.Locales.get(5)) {
                    GameState.lastLocale = GameState.currentLocale;
                    GameState.currentLocale = GameState.Locales.get(7);
                    System.out.println(formatStringToScreenWidth(GameState.currentLocale.localeDescription));

                    GameState.itemsHere.removeAll(GameState.itemsHere);

                    resultString += "The yeti looks at you, waiting to hear your answer. For some reason you want to trust him, far the same unknown reason you know what he wants...\nWill you [Give] him your map?";
                    return resultString;
                }

            default:
                return resultString;
        }

    }

    public String lookAt(String noun) {
        String resultString = "";

        switch (noun) {
            case "car":
                if (GameState.currentLocale == GameState.Locales.get(0)) {
                    if (noun.equalsIgnoreCase("car")) {
                        resultString += GameState.Locales.get(0).localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "trunk":
                if (GameState.currentLocale == GameState.Locales.get(1)) {
                    if (noun.equalsIgnoreCase("trunk")) {
                        resultString += GameState.Locales.get(1).localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "entrance":
                if (GameState.currentLocale == GameState.Locales.get(3)) {
                    if (noun.equalsIgnoreCase("entrance")) {
                        resultString += GameState.Locales.get(3).localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "woods":
                if (GameState.currentLocale == GameState.Locales.get(2)) {
                    if (noun.equalsIgnoreCase("woods")) {
                        resultString += GameState.Locales.get(2).localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "lost woods":
                if (GameState.currentLocale == GameState.Locales.get(5)) {
                    if (noun.equalsIgnoreCase("lost woods")) {
                        resultString += GameState.Locales.get(5).localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "backwards":
                if (GameState.currentLocale == GameState.Locales.get(0)) {
                    if (noun.equalsIgnoreCase("backwards")) {
                        System.out.println(GameState.Locales.get(4).localeDescription);
                        resultString += GameState.lastLocale.localeDescription;
                        break;
                    }
                } else {
                    resultString += "You're no where near there.";
                    break;
                }

            case "behind":
                System.out.println(
                        "Damn, those sqauts really have been working out. \n(You may be trying to look behind you, if so use 'look backwards'.)");
                break;

            case "compass":
                if (GameState.items.get(0).has) {
                    resultString += GameState.items.get(0).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }

            case "phone":
                if (GameState.items.get(1).has) {
                    resultString += GameState.items.get(1).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }

            case "thread":
                if (GameState.items.get(2).has) {
                    resultString += GameState.items.get(2).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }

            case "map":
                if (GameState.items.get(3).has) {
                    resultString += GameState.items.get(3).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }

            case "wallet":
                if (GameState.items.get(4).has) {
                    resultString += GameState.items.get(4).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }

            case "ticket":
                if (GameState.items.get(5).has) {
                    resultString += GameState.items.get(5).itemDesc;
                    break;
                } else {
                    resultString += "You don't have that yet...";
                    break;
                }
            case "pockets":
            case "inventory":
                System.out.println("You currently have...");
                for (Item i : GameState.invItems) {
                    System.out.println(i);
                }
                resultString += "You can [look] at them to get a better idea of their functions.";
                break;

            default:
                System.out.println("Sorry didn't catch that.");
                break;
        }

        return resultString;
    }

    public void printHelp() {
        String s1 = "";
        while (s1.length() < DISPLAY_WIDTH)
            s1 += "-";

        String s2 = "";
        while (s2.length() < DISPLAY_WIDTH) {
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) {
                s2 += " Commands ";
            } else {
                s2 += " ";
            }
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
        for (String v : verbs) {
            System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
        }

    }

    public boolean hasVerb(String string) {
        return verbs.contains(string);
    }

    public boolean hasNoun(String string) {
        return nouns.contains(string);
    }

    public String formatMenuString(String longString) {
        String result = "";
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            charLength += next.length();
            result += next + " ";
            if (charLength >= (DISPLAY_WIDTH - 30)) {
                result += "\n          ";
                charLength = 0;
            }
        }
        chop.close();
        return result + "\n\n";

    }

    public String formatStringToScreenWidth(String longString) {
        Scanner chop = new Scanner(longString);
        String result = " ";
        int charLength = 0;
        boolean addSpace = true;

        while (chop.hasNext()) {
            String next = chop.next();

            charLength += next.length() + 1;

            if (next.contains("[n1]")) {
                int secondHalf = next.indexOf("[n1]") + 4;

                if (secondHalf < next.length()) {
                    charLength = secondHalf;
                } else {
                    charLength = 0;
                    addSpace = false;
                }

                next = next.replace("[n1]", "\n");
            }

            result += next;

            if (addSpace) {
                result += " ";
            }
            addSpace = true;

            if (charLength >= DISPLAY_WIDTH) {
                result += "\n";
                charLength = 0;
            }
        }

        chop.close();
        return result;
    }

    public String talkTo(String ent) {
        String resultString = "";
        Random rand = new Random();

        switch (ent) {
            case "bill":
                int selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.billDiag[selector];
                break;

            case "ticketboy":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.billDiag[selector];
                break;

            case "tyler":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.tylerDiag[selector];
                break;
            case "creature":
            case "worker":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.tylerDiag[selector];
                break;

            case "bob":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.bobDiag[selector];
                break;

            case "manager":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.bobDiag[selector];
                break;

            case "47":

                resultString += "The man grunts in your direction, you try to get closer to the door but you can feel his glare through his darkened shades.";
                break;

            case "yeti":
                selector = rand.nextInt(3);
                while (selector == lastQuote) {
                    selector = rand.nextInt(3);
                }
                lastQuote = selector;

                resultString += GameState.yetiDiag[selector];
                break;

        }

        return resultString;
    }

    public void managerAnger() {
        Scanner in = new Scanner(System.in);
        GameState.people.get(2).mad = true;
        String ans = "";

        if (GameState.people.get(0).mad = true) {
            System.out.println(formatStringToScreenWidth(
                    "Your ears feel hot as you find yourself laid out on the ground, the bouncer(?) having knocked you in the back of your noggin. A small blonde-haired woman comes out, an angered scowl on her face."));
            System.out.println(formatStringToScreenWidth(
                    "'You're done, leave.', she says whilst pointing at the parking lot. The bouncer cracks his knuckles before lifting you up and throwing you out into it."));
            System.exit(0);
        } else if (GameState.people.get(1).mad = true) {
            System.out.println(formatStringToScreenWidth(
                    "The worker walks over towwards the entrance, returning a little later with a small blonde woman weaaring what looked like a security guard's outfit. She introduces herself as the manger, Bob, and tells you that you WILL be paying for her worker's pain and suffering."));
            System.out.println("Will you pay them?");
            ans = in.nextLine();
            if (ans.equalsIgnoreCase("yes")) {
                System.out.println("Alright, hand over your wallet.");
                manAnger = 1;

            } else if (ans.equalsIgnoreCase("no")) {
                System.out.println("Oh really? 47, take his kneecaps.");
                System.out.println(
                        "You got your kneecaps stolen AND kicked out.\n(Ending 4: They had to make the donuts...)");
                System.exit(0);

            } else if (manAnger == 1) {
                System.out.println(
                        "You feel a sharp blow to the back of your head before falling unconcious.\nYou wake up int the back of a trunk...");
                System.out.println("You are never heard from again.\nEnding 7: F*cked around and found out.");
            } else {
                System.out.println("That's not an answer. GET OUT!!!");
                System.out.println("You are kicked out...\n(Ending 5: I'm still alive?");
                System.exit(0);
            }

            in.close();

        }

    }

    public String hit(String ent) { // Command for aggressive actions
        String resultString = "";

        switch (ent) {
            case "bill":
            case "ticketboy":
                if (GameState.currentLocale == GameState.Locales.get(4)) {
                    System.out.println("You ruthlessly slap the young man, and he begins to cry.");
                    GameState.people.get(0).mad = true;

                    managerAnger();
                    System.out.println(
                            "You are kicked out of the attraction. You drive back to your house, thinking about the nights events.\n (Ending 6: D*ckhead.)");
                    System.exit(0);
                } else {
                    resultString += "You're nowhere near them.";
                    break;
                }

            case "tyler":
            case "creature":
            case "worker":
                if (GameState.currentLocale == GameState.Locales.get(2)) {
                    System.out.println(
                            "You punch the monster in the face, whether out of fear or out of curiosity to see what will happen.");
                    GameState.people.get(1).mad = true;
                    GameState.people.get(2).mad = true;

                    managerAnger();
                    resultString += "Bob waits for you to [Give] her your wallet.";
                    break;
                } else {
                    resultString += "You're nowhere near them.";
                    break;
                }

            case "bob":
            case "manager":
                if (GameState.currentLocale == GameState.Locales.get(3)) {
                    System.out.println("...");
                    System.out.println(
                            "You are viciously drawn, quartered, and mailed to several undescript locales.\n (Ending 8: All the queen's guards)");

                    System.exit(0);
                } else {
                    resultString += "You're nowhere near them.";
                    break;
                }

            case "yeti":
                if (GameState.currentLocale == GameState.Locales.get(7)) {
                    System.out.println(
                            "Your hand passes through him, the forest around you shifting as you once again find yourself lost in the woods.");
                    GameState.people.get(3).mad = true;

                    GameState.currentLocale = GameState.Locales.get(5);

                    resultString += GameState.currentLocale.localeDescription;
                    break;
                } else {
                    resultString += "You're nowhere near them.";
                    break;
                }

            case "47":
                if (GameState.currentLocale == GameState.Locales.get(3)) {
                    resultString += "You move to hit him but one quick glance from under his shades causes you to think better of that...";
                    break;
                } else {
                    resultString += "You're nowhere near them.";
                    break;
                }

            default:
                resultString += "Who?";
                break;
        }
        return resultString;
    }
}
