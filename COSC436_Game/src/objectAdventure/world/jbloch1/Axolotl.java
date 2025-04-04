package objectAdventure.world.jbloch1;


import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionEventType;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 * An Axolotl you can scoop up.
 *
 * @author Julie N. Bloch, COSC436/COSC716
 */
public class Axolotl implements Item {

    private String description;
    private String displayName;

    /**
     * Constructor
     */
    public Axolotl() {
        this.displayName = "An Axolotl";
        this.description = "This little friend let you pick him up, and now you have a cute companion to your travels!";
    }

    /**
     *
     * @return The item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public List<String> getItemAliases() {
        return List.of("Axolotl", "Cheeklet", "Axolotl-Item", "Axol","Friend","Pet");
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
     * <p>
     * The actual parameter is an enum (singleton) value of one of the following:<br>
     * TAKE("Get", "Take"): Take an Item.
     * DROP("Drop"): Drop an Item.
     * INSPECT("Inspect", "Examine"): Inspect an Item.
     * USE("Use"): Use an Item.
     * PUSH("Push"), PULL("Push"): Push or pull an Item.
     * ACTIVATE("Activate"), DEACTIVATE("Deactivate"): Activate or deactivate an Item.
     * OPEN("Open"), CLOSE("Close"): Open or close an Item.
     * THROW("Throw", "Toss"): Throw an Item.
     * REPAIR("Fix", "Repair", "Mend"), DESTROY("Break", "Destroy", "Smash"): Repair or destroy an Item.
     * TAUNT("Taunt"); // Do NOT taunt Happy Fun Ball!!!
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
                this.displayName = "You attempted to pick up the axolotl...";

                // Then return a success message.
                yield ItemInteractionResult.success("He's very slimy and slippery, but... You picked up the %s".formatted(previousName));
            }

            case USE -> {
                // Do whatever you want when an item is picked up.
                this.description = "You held out the axolotl, he whoops!.";

                // Then return a success message.
                yield ItemInteractionResult.success("You put back your " + this.getItemDisplayName());

            }

            case ACTIVATE -> {
                this.description = "Your axolotl starts vibrating!";

                yield ItemInteractionResult.success(this.getItemDisplayName() + " Speaks to you in fluent english. You learn the secrets of the universe.\nHe then burps...");

            }

            case THROW -> {
                this.description = "You do not!";

                yield ItemInteractionResult.failure(this.getItemDisplayName() + " Looks at you begrudgingly.");
            }

            case TAUNT-> {
                this.description = "You mockingly hold a worm right out of reach of the axolotl";

                yield ItemInteractionResult.failure(this.getItemDisplayName() + " Jumps up and eats it anyway.");
            }
            // Everything else should "succeed", but do nothing.
            default -> ItemInteractionResult.success();
        };
    }
}












