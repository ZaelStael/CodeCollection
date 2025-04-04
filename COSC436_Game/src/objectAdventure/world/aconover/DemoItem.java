package objectAdventure.world.aconover;
// $Id: DemoItem.java 457 2024-11-02 19:43:11Z tbaker17 $

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionEventType;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 * A sample item for demonstration purposes.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class DemoItem implements Item {

    private String description;
    private String displayName;

    /**
     * Constructor
     */
    public DemoItem() {
        this.displayName = "Demonstration Item";
        this.description = "An item for demonstration purposes.";
    }

    /**
     * Get the full description of the room.
     *
     * @return The item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }


    /**
     * Get the list of item aliases.
     *
     * @return The list of item aliases.
     */
    @Override public List<String> getItemAliases() {
        return List.of("Demo", "Demo-Item", "Demonstration-Item", "Mysterious-thing");
    }

    /**
     * The short name of the item for display in lists, etc.
     *
     * @return A short item description that will show in inventory and looking around a room.
     */
    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }


    /**
     * Item Interaction Example
     *
     * The actual parameter is an enum (singleton) value of one of the following:
     *
     *   - TAKE("Get", "Take"): Take an Item.
     *   - DROP("Drop"): Drop an Item.
     *   - INSPECT("Inspect", "Examine"): Inspect an Item.
     *   - USE("Use"): Use an Item.
     *   - PUSH("Push"), PULL("Push"): Push or pull an Item.
     *   - ACTIVATE("Activate"), DEACTIVATE("Deactivate"): Activate or deactivate an Item.
     *   - OPEN("Open"), CLOSE("Close"): Open or close an Item.
     *   - THROW("Throw", "Toss"): Throw an Item.
     *   - REPAIR("Fix", "Repair", "Mend"), DESTROY("Break", "Destroy", "Smash"): Repair or destroy an Item.
     *   - TAUNT("Taunt"): Do NOT taunt Happy Fun Ball!!! (https://en.m.wikipedia.org/wiki/Happy_Fun_Ball)
     *
     * @param itemInteractionEvent@return The ItemInteractionResult object.
     * @see ItemInteractionEventType
     **/
    @Override
    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        return switch (itemInteractionEvent.ie()) {
            case GET -> {
                // Do whatever you want when an item is picked up.
                String previousName = this.getItemDisplayName();
                this.displayName = "Looted Demo Item";

                // Then return a success message.
                yield ItemInteractionResult.success("Congratulations! You picked up the %s".formatted(previousName));
            }

            case USE -> {
                // Do whatever you want when an item is picked up.
                this.description = "A slightly used demonstration item.";

                // Then return a success message.
                yield ItemInteractionResult.success("You use the " + this.getItemDisplayName());
            }

            // Everything else should "succeed", but do nothing.
            default -> ItemInteractionResult.success();
        };
    }
}

