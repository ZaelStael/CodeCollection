package objectAdventure.world.tbaker17;

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;
import objectAdventure.common.Observable;
import objectAdventure.common.Observer;

import java.util.ArrayList;
import java.util.List;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

// @author Tyrique Baker

@SuppressWarnings("rawtypes")
public class Recliner implements Item, Observable<Notification> {

    private String description;
    private String displayName;
    private List<Observer<Notification>> observers = new ArrayList<>();
    private Notification note;
    // private List<Observable<Notification>> observed = new ArrayList<>();

    public Recliner() {
        this.displayName = "Comfy Recliner";
        this.description = """
                A recliner sitting within this nexus of emptyness. Its primary color is
                black---so dark your eyes feel dragged into its mass---and along its body
                a shifting spots of hues purple, blue, and red. Upon touching it, the strange
                material seems to morph from cotton, to silk, to leather, almost as if trying to
                sus out your preference.

                Wierd. """;

    }

    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public List<String> getItemAliases() {
        return List.of("Recliner", "Chair", "Comfy-Recliner", "Comfy-Chair", "Comfortable-Recliner",
                "Comfortable-Chair");
    }

    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent iIE) {
        return switch (iIE.ie()) {

            case GET -> {

                // note = new Notification("%d being carried by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success(
                        "You somehow manage to somehow get the massive seat onto your back, legs buckling underneath what feels like it must be infinite weight.");
            }

            case DROP -> {

                // note = new Notification("%d dropped by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success(
                        "You rush the chair off your back, the soreness already beginning to set, and wonder what made you want to pick up a couch.");

            }

            case INSPECT -> {

                // note = new Notification("%d inspected by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                System.out.println(getItemFullDescription());

                yield ItemInteractionResult.success("It looks soooooooo comfortable...you should use it.");
            }

            case USE -> {

                // note = new Notification("%d used by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success(
                        """
                                You sit in the seat, it's surface morphing into the perfect fabric. Every single joint
                                 on your body lets out a sigh of relief, and you drift slowly into The Sandman's realm.

                                                                 [5 Hours Later]

                                You Awaken feeling refeshed, and a shield now lies at your feet. What now?

                                        """);
            }

            case THROW -> {

                // note = new Notification("%d not thrown by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success("Haha, no.");
            }

            case DESTROY -> {

                // note = new Notification("%d destroyed by
                // player.".formatted(this.getItemDisplayName()));
                // notifyObservers(note);

                yield ItemInteractionResult.success("You've defaced a chair.\n\nWhat do you want? A cookie?");

            }

            default -> ItemInteractionResult.success("Yay!!! You did...something!!");
        };

    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
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
        notifyObservers(new Notification(this, "Items name hs been changed."));
    }

}
