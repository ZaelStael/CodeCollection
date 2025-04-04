package objectAdventure.world.tbaker17;

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;
import objectAdventure.common.Observable;
import objectAdventure.common.Observer;

import java.util.*;
import java.util.regex.*;
//import objectAdventure.world.tbaker17.VoidRoom;

// @author Tyrique Baker

@SuppressWarnings("rawtypes")
public class Shield implements Item, Observable<Notification> {

    private String description;
    private String oDesc;
    private String displayName;
    private boolean have;
    private boolean thrown;
    private boolean active;
    private List<Observer<Notification>> observers = new ArrayList<>();
    private Notification note;
    // private List<Observable<Notification>> observed = new ArrayList<>();

    public Shield() {
        this.displayName = "Intoxicating Shield";
        this.description = """
                A shield with material that glances along the edge of many differing
                materia, it matches the knife in visage but the recliner in comfort...

                It calls to something behind your eyes.

                Intoxicating. """

        ;
        this.thrown = false;
        this.active = false;
        this.have = false;
    }

    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    public boolean hasItem() {
        return this.have;
    }

    @Override
    public List<String> getItemAliases() {
        if (this.active == false)
            return List.of("Shield", "Intoxicating-Shield", "Recliner-Shield", "Void-Shield");
        else
            return List.of("Door", "Pristine-Door", "Gleaming-Door", "White-Door");
    }

    public void throwShield() {
        this.thrown = true;
        this.have = false;
    }

    public void actShield() {
        if (this.active == false) {
            this.active = true;
        } else {
            this.active = false;
        }
    }

    public boolean thrownState() {
        return this.thrown;
    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent iIE) {
        return switch (iIE.ie()) {

            case GET -> {

                String previousName = this.getItemDisplayName();
                this.displayName = previousName + " [Looted]";
                this.have = true;

                // note = new Notification("%d picked up by player.".formatted(previousName));
                // notifyObservers(note);

                yield ItemInteractionResult
                        .success("You have picked up the %s and addeded it to your inventory.".formatted(previousName));
            }

            case DROP -> {
                String newName = this.getItemDisplayName();
                Pattern lootNote = Pattern.compile("Looted");
                Matcher lootCon = lootNote.matcher(newName);

                if (lootCon.find()) {
                    newName = newName.replace("[Looted]", "");
                    this.have = false;

                    // note = new Notification("%d dropped by player.".formatted(newName));
                    // notifyObservers(note);

                    yield ItemInteractionResult.success("You have dropped the %s.".formatted(newName));
                } else {
                    System.err.println("Do you have this item?");

                    yield ItemInteractionResult.failure("Check again...");
                }

            }

            case INSPECT -> {
                System.out.println(getItemFullDescription());

                // note = new Notification("%d inspected by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult
                        .success("It's grip pushes into your own, as if trying to cling ever tighter to you...");
            }

            case USE -> {

                if (this.active = false) {

                    // note = new Notification("%d used by
                    // player.".formatted(this.getItemDisplayName()));
                    // notifyObservers(note);

                    yield ItemInteractionResult.success("On what exactly?");

                } else {

                    // note = new Notification("%d used by
                    // player.".formatted(this.getItemDisplayName()));
                    // notifyObservers(note);

                    // Door teleports you to a random map

                    yield ItemInteractionResult.success();
                }

            }

            case ACTIVATE -> {
                if (this.active == false) {
                    System.out.println(
                            """
                                    The shield begins to tremble as it senses your intentions, a thin colorful liquid
                                    spilling from its face and begining thicken as it breathes the space around you.
                                    Before you know it the shield is a shield no longer.

                                    The shield has grown exponentially, its shape now more closely resembling a door and
                                    its hue now blindingly white along  with a faint black creeping about the edges. It has
                                    an unspoken potential to it, an air of authority if you will.

                                    It's...

                                    Pristine.
                                            """);
                    this.oDesc = getItemFullDescription();
                    this.description = "A portable door that evokes otherworldly sensibilities...you feel [use]ing it may lead to something.";
                    this.displayName = "Pristine Door [Looted]";
                    this.active = true;

                    // note = new Notification("%d activated by
                    // player.".formatted(this.getItemDisplayName()));
                    // notifyObservers(note);
                } else {

                }

                yield ItemInteractionResult.success();

            }

            case DEACTIVATE -> {
                if (this.active) {
                    System.out.println("The door crumbles away, returning to your hand the shield it once was.");
                    this.description = this.oDesc;
                    this.displayName = "Intoxicating Shield";
                    this.active = false;

                    // note = new Notification("%d deactivated by
                    // player.".formatted(this.getItemDisplayName()));
                    // notifyObservers(note);
                } else {

                }

                yield ItemInteractionResult.success();
            }

            case THROW -> {
                throwShield();

                // note = new Notification("%d thrown by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success("You have thrown the %s.".formatted(getItemDisplayName()));
            }

            case DESTROY -> {

                // note = new Notification("%d destroyed by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success(
                        "The %s, angry at your decision, fades from existence.".formatted(getItemDisplayName()));

            }

            default -> ItemInteractionResult.success("Yay!!! You did...something!!");
        };

    }

    @Override
    public void addObserver(Observer<Notification> theObserver) {
        observers.add(theObserver);
    }

    @Override
    public void removeObserver(Observer theObserver) {
        observers.remove(theObserver);
    }

    @Override
    public void notifyObservers(Notification nO) {
        for (Observer<Notification> O : observers) {
            O.update(nO);
        }
    }

    public void changeSomethingAboutTheObject(String val) {
        this.displayName = val;
        System.out.printf("The Item's name ahs been changed to \"%s\"".formatted(val));
        notifyObservers(new Notification(this, "Items name has been changed."));
    }

}
