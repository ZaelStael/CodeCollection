package objectAdventure.world.tbaker17.ShieldStateVers;

/**
 * The State interface represents the different states of the shield.
 */
public interface State {

    /**
     * Handles the action of feeding the shield.
     */
    void feedShield();

    /**
     * Handles the action of Breaking the shield.
     */
    void BreakShield();

    /**
     * Handles the action of turning the shield into The Door.
     */
    void ActivateShield();

    /**
     * Handles the action of turning The Door into the shield.
     */
    void DeactivateShield();
}
