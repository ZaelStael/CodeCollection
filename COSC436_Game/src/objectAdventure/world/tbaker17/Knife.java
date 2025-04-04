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
public class Knife implements Item, Observable<Notification> {

    private String description;
    private String oDesc;
    private String displayName;
    private boolean have;
    private boolean thrown;
    private boolean active;
    private List<Observer<Notification>> observers = new ArrayList<>();
    private Notification note;
    // private List<Observable<Notification>> observed = new ArrayList<>();

    public Knife() {
        this.displayName = "Strange Knife";
        this.description = """
                A knife with blade shifting through various shadows with a handle of dark cured...something.
                Reminds you of the room surrounding you.

                Strange. """

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
            return List.of("Knife", "Strange-Knife", "Rcliner-Knife", "Void-Knife");
        else
            return List.of("Blade", "Neat-Blade", "Gleaming-Blade", "White-Blade");
    }

    public void throwKnife() {
        this.thrown = true;
        this.have = false;
    }

    public void actKnife() {
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

                yield ItemInteractionResult.success("It's handle seems to be pulsing, almost like its alive...");
            }

            case USE -> {

                // note = new Notification("%d used by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success("On what exactly?");
            }

            case ACTIVATE -> {
                if (this.active == false) {
                    System.out.println(
                            """
                                    The knife begins to tremble as it senses your intentions, a viscous black substance
                                    leaking from its blade and begining thicken as it tastes the air around you.
                                    Before you know it the knife is a knife no longer.

                                    The blade has lengthed to nearly twice the length of your arm, it's hue now blindingly
                                    white along the faint glow emanating from its new, majesty. Its shaft now leads to a
                                    strange, face-shaped crossguard, depicting some creature of an origin unknown to you.

                                    Neat.
                                            """);
                    this.oDesc = getItemFullDescription();
                    this.description = "A gleaming long-blade that would evoke angelic sensibilities...if not for the obviously alien face it features as a crossguard.";
                    this.displayName = "Neat Blade";
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
                    System.out.println("The blade crumbles away, returning to your hand the dagger it once was.");
                    this.description = this.oDesc;
                    this.displayName = "Strange Knife";
                    this.active = false;

                    // note = new Notification("%d deactivated by
                    // player.".formatted(this.getItemDisplayName()));
                    // notifyObservers(note);
                } else {

                }

                yield ItemInteractionResult.success();
            }

            case THROW -> {
                throwKnife();

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
