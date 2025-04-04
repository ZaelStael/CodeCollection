package objectAdventure.world.tbaker17.ShieldStateVers;

import java.util.ArrayList;
import java.util.List;

import objectAdventure.common.Observable;
import objectAdventure.common.Observer;
import objectAdventure.core.item.Item;
import objectAdventure.world.tbaker17.Notification;

public class Shield implements Item, Observable<Notification> {

    // Needed variables
    private String description;
    private String oDesc;
    private String displayName;
    private int n;

    // Holds current state of the shield
    private final CurrentState currentState;

    // List of observers and a notification
    private List<Observer<Notification>> observers = new ArrayList<>();
    private Notification note;

    // Number of steps player has taken with shield
    private int steps = 0;

    // The amount of steps remaining before the shield disappears
    private int patience = 100;

    /*
     * Shield Constructor
     * 
     */

    public Shield(int p) {
        this.displayName = "Intoxicating Shield";
        this.description = """
                A shield with material that glances along the edge of many differing
                materia, it matches the knife in visage but the recliner in comfort...

                It calls to something behind your eyes.

                Intoxicating. """

        ;
        this.patience = p;
        this.steps = patience - steps;
        currentState = new CurrentState(patience);

    }

    /*
     * Feeds shield.
     */

    public void feedShield() {
        System.out.println("Feeding shield...");
        this.patience++;
        currentState.state.feedShield();
        note = new Notification("%d fed by player.".formatted(this.getItemDisplayName()));
        notifyObservers(note);
    }

    /*
     * Breaks relationship with sheild.
     */

    public void BreakShield() {
        System.out.println("Breaking...");
        this.patience = 0;
        currentState.state.BreakShield();
        note = new Notification("%d destroyed by player.".formatted(this.getItemDisplayName()));
        notifyObservers(note);
    }

    /*
     * Turns the shield on. (Kinky)
     */

    public void ActivateShield() {
        System.out.println("Turning shield on...");
        this.patience += 3;
        n = 1;
        this.description = "A portable door that evokes otherworldly sensibilities...you feel [use]ing it may lead to something.";
        this.displayName = "Pristine Door";

        currentState.state.ActivateShield();
        note = new Notification("%d activated by player.".formatted(this.getItemDisplayName()));
        notifyObservers(note);
    }

    public List<String> getItemAliases() {
        if (n == 0)
            return List.of("Shield", "Intoxicating-Shield", "Recliner-Shield", "Void-Shield");
        else
            return List.of("Door", "Pristine-Door", "Gleaming-Door", "White-Door");
    }

    /*
     * Turns the shield off.
     */

    public void DeactivateShield() {
        System.out.println("Turning shield off...");
        this.patience -= 5;
        currentState.state.DeactivateShield();
        n = 0;
        this.displayName = "Intoxicating Shield";
        this.description = """
                A shield with material that glances along the edge of many differing
                materia, it matches the knife in visage but the recliner in comfort...

                It calls to something behind your eyes.

                Intoxicating. """

        ;
        note = new Notification("%d deactivated by player.".formatted(this.getItemDisplayName()));
        notifyObservers(note);
    }

    /**
     * Returns the steps.
     *
     * @return the steps.
     */

    int getSteps() {
        return steps;
    }

    /**
     * Returns the patience.
     *
     * @return the patience.
     */

    int getPatience() {
        return patience;
    }

    /*
     * Sets the patience.
     */

    void setPatience(int p) {
        this.patience = p;
    }

    // Various setters of states

    public void setState(State state) {
        currentState.state = state;
    }

    public void setActiveState() {
        setState(currentState.activeState);
    }

    public void setDeactiveState() {
        setState(currentState.deactiveState);
    }

    public void setBrokenState() {
        setState(currentState.brokenState);
    }

    @Override
    public String toString() {
        return """
                (State Pattern) D.O. Industries.
                Patience = %d,
                Steps taken = %d,
                Shield State = %s
                """.formatted(patience, steps, currentState.state);
    }

    // Possible states of the shield
    private class CurrentState {
        public final State activeState, brokenState, deactiveState;
        private State state;

        /*
         * CurrentState Constructor
         * 
         * @param patience The amount of steps remaining before the shield disappears
         */

        CurrentState(int patience) {
            // Initialize the states
            activeState = new ActiveState(Shield.this);
            brokenState = new BrokenState(Shield.this);
            deactiveState = new DeactiveState(Shield.this);

            this.state = (patience > 0) ? deactiveState : brokenState;
        }
    }

    @Override
    public void addObserver(Observer<Notification> theObserver) {
        observers.add(theObserver);
    }

    @Override
    public void removeObserver(Observer<Notification> theObserver) {
        observers.remove(theObserver);
    }

    @Override
    public void notifyObservers(Notification nO) {
        for (Observer<Notification> O : observers) {
            O.update(nO);
        }
    }

    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

}
