package objectAdventure.core.item;
// $Id: Item.java 720 2024-11-18 20:54:59Z tbaker17 $

import java.util.List;
import java.util.logging.Logger;

/**
 * The Item interface is the base interface for all items in the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
@SuppressWarnings("SameReturnValue")
public interface Item {

    /**
     * Returns the full description of the item for use when looking at the item.
     *
     * @return The full item description
     */
    String getItemFullDescription();

    /**
     * The short description of the item for display in lists, etc.
     *
     * @return A short item description.
     */
    String getItemDisplayName();

    /**
     * Can the item be picked up and relocated?
     *
     * @return return true if the item can be moved.
     */
    default boolean isTransferable() {
        return true;
    }

    /**
     * Short Names lists for use in picking up and removing items.
     *
     * @return A list containing the aliases (short names) for an item.
     */
    default List<String> getItemAliases() {
        return List.of(this.getClass().getSimpleName());
    }

    /**
     * Get the list of aliases, normalized to uppercase.
     *
     * @param item The item
     * @return A list of aliases, normalized to uppercase.F
     */
    static List<String> getUpperCaseAliases(Item item) {
        return item.getItemAliases().stream().map(String::toUpperCase).toList();
    }

    /**
     * Notify the game core the item has been interacted with.
     *
     * @param itemInteractionEvent The event that triggered the interaction.
     * @return The result of the interaction as an ItemInteractionResult object.
     */
    default ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        Logger.getGlobal().fine("DEBUG: " + itemInteractionEvent.ie());

        // The reason fo "success" yet "Nothing happens." is because item actions should succeed by default
        // (such as take and drop). Action responses should override the default response with their own.
        return ItemInteractionResult.success();
    }

}
