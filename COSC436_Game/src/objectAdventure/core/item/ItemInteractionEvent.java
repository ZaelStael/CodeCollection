package objectAdventure.core.item;

/**
 * For now, this is just a record class for the ItemInteractionEvent.
 * <p>
 * A record is used to allow for additional data to be added to the class in the future
 * without breaking any consumers of Events.
 *
 * @param ie The ItemInteractionEventType as defined in ItemInteractionEventType.
 */
public record ItemInteractionEvent(ItemInteractionEventType ie) {
    /**
     * Get the event type.
     *
     * @return The event type.
     */
    public ItemInteractionEventType getEventType() {
        return ie();
    }
}