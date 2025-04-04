package objectAdventure.core.item.SecretTestingItem;
// $Id: SecretTestingItem.java 166 2024-10-10 16:03:43Z aconover $

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 * An item for illustrating advanced item features.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class SecretTestingItem implements Item {

    private String displayName;
    private boolean isPlayerHoldingMap = false;
    private String description;

    private int pickupAttempts = 0;

    /**
     * Constructor
     */
    public SecretTestingItem() {
        this.displayName = "The GameMap";
        this.description = "A GameMap (PDF located in Game source folder).";
    }

    /**
     * THIS IS JUST A SAMPLE.
     *
     * @return The item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    /**
     * This a list of nouns the player can use to reference the item.
     *
     * @return Aliases for item.
     */
    @Override
    public List<String> getItemAliases() {
        return List.of("Map", "Game-Map");
    }

    /**
     * The game core has notified the item it has been interacted with.
     * <p>
     * NOTE: The Item could easily be BOTH an Observer AND Observable (observing events, which it
     * then forwards on to its own observers)! But, because EVERY item receives the same
     * information, making it an "observer" of the game controller is unnecessary. The observer
     * common makes most sense when only some Objects are observable and others are not
     * or Observable items are observed by many other kinds of objects.
     *
     * @param itemInteractionEvent@return The ItemInteractionResult object.
     */
    @Override
    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        return switch (itemInteractionEvent.ie()) {
            case DROP -> {
                isPlayerHoldingMap = false;
                yield ItemInteractionResult.success("The map has been dropped!");
            }

            case GET -> {
                if (pickupAttempts < 1) {
                    pickupAttempts = 1; // no need to keep incrementing this.
                    this.displayName = "Discarded Map";
                    yield ItemInteractionResult.failure("The map slips through your fingers. Maybe you should try again!");
                } else {
                    isPlayerHoldingMap = true;
                    this.displayName = "Game Map";
                    yield ItemInteractionResult.success("The map has been taken!");
                }
            }

            case USE -> {
                if (isPlayerHoldingMap) {
                    this.description = "A map that appears to have been refolded with great frustration.";
                    yield ItemInteractionResult.success("You successfully use the map.");
                } else {
                    yield ItemInteractionResult.failure("You must be holding the map to use it!");
                }
            }

            case INSPECT -> {
                if (isPlayerHoldingMap) {
                    this.description = "A thoroughly inspected map.";
                    yield ItemInteractionResult.success("You inspect the map.");
                } else {
                    yield ItemInteractionResult.failure("You must be holding the map to inspect it!");
                }
            }

            // Not implemented/handled for this item.
            default -> ItemInteractionResult.failure("This item does not respond to that action.");
        };

    }

}
