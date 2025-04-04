package objectAdventure.core.command;
// $Id: GameController.java 720 2024-11-18 20:54:59Z tbaker17 $

import objectAdventure.common.InputInterceptor;
import objectAdventure.common.Utils;
import objectAdventure.core.DescriptionType;
import objectAdventure.core.Direction;
import objectAdventure.core.GameMap;
import objectAdventure.core.RoomList;
import objectAdventure.core.item.*;
import objectAdventure.core.player.PlayerImpl;
import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;
import objectAdventure.common.RoomEventListener;
import objectAdventure.core.room.SecretTestingRoom.SecretTestingRoom;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.System.out;
import static objectAdventure.core.DescriptionType.SHORT;
import static objectAdventure.core.item.ItemInteractionEventType.DROP;
import static objectAdventure.core.item.ItemInteractionEventType.GET;

// AJC_TODO: Refactor the isTransferable flag, use the "GET" event to determine if the item can be taken.

/**
 * All the "Player Interactions" of the game are routed through this class.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!! NOTICE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<br>
 * <p>
 * This class is intended to be improved upon by utilizing the "command", and a
 * "Chain of Responsibility" patterns!!! THERE ARE MANY REFACTORING
 * OPPORTUNITIES HERE!!!
 * <p>
 * (Ok... I'll admit it... This class grew out of control, and I haven't had
 * time to refactor it properly...)
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class GameController {
    private final GameMap gameMap;
    private final RoomList rooms;
    private final PlayerImpl player;
    private int moveNumber;

    /**
     * Constructor for the GameController.
     *
     * @param player The player object.
     */
    public GameController(PlayerImpl player) {
        this.player = player;
        this.gameMap = new GameMap();
        this.rooms = RoomList.newInstance();

        rooms.addRoom(SecretTestingRoom.newInstance(99, "Secret Testing Room"));

        this.player.setCurrentRoomId(0);
        this.moveNumber = 1;
    }

    /**
     * Mainly just an example us using a high-level interface.
     *
     * @param from The Item possessor to take the item from.
     * @param to   The Item possessor to give the item to.
     * @param item The item to be transferred.
     * @implNote moves item from "from" --> to "to".
     */
    private static boolean transferItem(final ItemContainer from, final ItemContainer to, final Item item) {
        if (item.isTransferable()) {
            from.removeItem(item);
            to.addItem(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increment the move number with every applicable move.
     */
    void incMoveNumber() {
        moveNumber++;
    }

    /**
     * Gets the current move number.
     *
     * @return the current move number.
     */
    int getMoveNumber() {
        return this.moveNumber;
    }

    /**
     * Gets debugging information for the current room.
     *
     * @return a string containing the current room's ID and author.
     */
    String getCurrentRoomInfo() {
        try {
            final var theRoom = this.getCurrentRoom();
            return "Room ID: %s, Room Author: %s".formatted(theRoom.getRoomId(), theRoom.getRoomAuthor());
        } catch (NoSuchRoomException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns the current Room object of the active player.
     *
     * @return the current Room object of the active player.
     */
    Room getCurrentRoom() {
        return this.getRoomFromID(this.getPlayer().getCurrentRoomId());
    }

    /**
     * Gets a room object from its ID.
     *
     * @param currentRoomId The ID of the room.
     * @return The room object, which corresponds to the specified ID.
     */
    private Room getRoomFromID(final int currentRoomId) {
        final var room = rooms.getRoomFromID(currentRoomId);
        return room.orElseThrow(() -> new NoSuchRoomException(currentRoomId));
    }

    /**
     * Gets the player object.
     *
     * @return the player object.
     */
    PlayerImpl getPlayer() {
        return this.player;
    }

    /**
     * Gets a comma-delimited list of player inventory items.
     *
     * @return a string containing the player's inventory items.
     */
    String getPlayerInventoryDisplayNames() {
        final List<Item> inventory = player.getItemList();

        if (inventory.isEmpty()) {
            return "You are empty-handed.";
        } else {
            return Utils.getFormattedItemList(inventory, SHORT);
        }
    }

    /**
     * Gets item descriptions for the current room.
     *
     * @param type The description type.
     * @return a string containing the item descriptions.
     */
    String getRoomItemDisplayNames(DescriptionType type) {
        try {
            return this.getRoomItemDisplayNames(this.getCurrentRoom(), type);
        } catch (NoSuchRoomException e) {
            return "No items in a non-existent room.";
        }
    }

    /**
     * Gets the item descriptions for a specified room.
     *
     * @param theRoom The room with the items.
     * @param type    The description type.
     * @return a string containing the item descriptions.
     */
    private String getRoomItemDisplayNames(final ItemContainer theRoom, DescriptionType type) {
        if (theRoom.getItemList().isEmpty()) {
            return "Nothing of Interest.";
        } else {
            return Utils.getFormattedItemList(theRoom.getItemList(), type);
        }
    }

    /**
     * Gets a list of exit directions for the current room.
     *
     * @return a list of Direction instances.
     */
    List<Direction> getExitDirections() {
        final var currentRoomId = player.getCurrentRoomId();
        final var directionSet = this.gameMap.getExitConnections(currentRoomId).keySet();

        return new ArrayList<>(directionSet);
    }

    /**
     * Moves the player in the specified direction.
     *
     * @param dir The direction to move the player.
     */
    void movePlayer(final Direction dir) {
        final var currentRoomId = this.getPlayer().getCurrentRoomId();
        final var exits = this.gameMap.getExitConnections(currentRoomId);

        assert (this.gameMap.doesExist(currentRoomId)) : "The current Room does not exist! How did you get here!?";

        // Check if the exit exists, and if so, teleport the player.
        if (exits.containsKey(dir)) {
            this.teleportPlayer(exits.get(dir));
        } else {
            out.printf("Ouch! (There is no exit: %s)%n", dir.toString());
        }
    }

    /**
     * Sets the player's new room number.
     *
     * @param newRoomId The destination room number for the player.
     * @return true if the change was successful, false otherwise.
     */
    boolean teleportPlayer(final int newRoomId) {
        assert (this.gameMap.doesExist(newRoomId)) : "The target Room does not exist...";

        // If the room does not exist, do not change the player's current room.
        if (!gameMap.doesExist(newRoomId)) {
            Logger.getGlobal().warning(format("The target Room (%d) does not exist.", newRoomId));
            // Do not change the player's current room.
            return false;
        }

        // Get the current and new room objects.
        final var currentRoomId = this.player.getCurrentRoomId();
        final var fromRoom = this.rooms.getRoomFromID(currentRoomId);
        final var toRoom = this.rooms.getRoomFromID(newRoomId);

        // Update the player's current room.
        player.setCurrentRoomId(newRoomId);

        // Notify the rooms of the player's movement.
        fromRoom.ifPresent(room -> {
            if (room instanceof RoomEventListener listener)
                listener.playerLeavingRoom(player);
        });

        // Notify the rooms of the player's movement.
        toRoom.ifPresent(room -> {
            if (room instanceof RoomEventListener listener)
                listener.playerEnteringRoom(player);
        });

        // Update the player's current room.
        return true;
    }

    /**
     * Gets a list of items based on the alias.
     *
     * @param noun           The target object name.
     * @param itemCollection The list of items to search.
     * @return a list of item objects matching the alias.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    final List<? extends Item> getAllItemsFromItemAlias(final String noun,
            Collection<? extends Item>... itemCollection) {
        // Combine lists of room and player items.
        return Arrays.stream(itemCollection)
                .flatMap(Collection::stream)
                .filter(item -> Item.getUpperCaseAliases(item).contains(noun.toUpperCase()))
                .toList();
    }

    /**
     * Drops all items from the player's inventory.
     *
     * @return a string describing the result of dropping all items.
     */
    private String dropAllItems() {
        // Move all items from player to room, and collect responses.
        // NOTE1: We have to make a copy of the list first, as we can't iterate and modify concurrently.
        // NOTE2: BAD PRACTICE ALERT!  Here, a stream is being used to collect the RESULTS of dropping items AND
        //        remove items from a list. "Functional streams" generally should NOT have side effects (like modifying
        //        the state of shared list!), but this is a case where it is easy to illustrate some functionality of
        //        streams. In defense of this here, the method is private and only used in a single place in a
        //        larger chain of operations.
        return new ArrayList<>(this.player.getItemList()).stream()
                .map(this::dropItem) // Drop the item and collect the result. (THE BAD PRACTICE!!!)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining("\n"),
                        str -> !str.isBlank()
                                ? str
                                : "You don't have anything to drop."));
    }

    /**
     * Drops a single item to the room based on the alias.
     *
     * @param noun The item to be taken.
     * @return a string describing the result of the action.
     */
    String dropItem(final String noun) {
        if (!this.isRoomPresent()) {
            Logger.getGlobal().warning("Player is not in a room, can't drop items.");
            return "You can't drop items here!";
        }

        // If noun is "ALL", then drop all items.
        if (noun.equalsIgnoreCase("ALL")) {
            return this.dropAllItems();
        }

        // Find the item object from the alias.
        final var itemFromAlias = this.getItemFromAlias(noun, this.getPlayer().getItemList());

        // If the item exists, and the player has it, then drop it.
        if (itemFromAlias.stream().anyMatch(this.getPlayer()::hasItem)) {
            final Item theItem = itemFromAlias.get();
            return this.dropItem(theItem);
        } else {
            return format("You have no '%s' to drop!!!", noun);
        }
    }

    /**
     * Drops a single item to the room. This handles the actual dropping of the item.
     *
     * @param theItem The item to be taken.
     * @return a string describing the result of the action.
     */
    private String dropItem(Item theItem) {
        ItemInteractionResult result = theItem.itemInteractionHandler(new ItemInteractionEvent(DROP));
        String responseMessage;

        // If the item was successfully dropped, then remove it from the player and add
        // it to the room.
        if (result.bSuccess()) {
            boolean xfered = transferItem(this.getPlayer(), this.getCurrentRoom(), theItem);

            responseMessage = xfered
                    ? result.message().isBlank() ? format("You dropped the %s.", theItem.getItemDisplayName())
                            : result.message()
                    : "You can't drop a non-transferable item!";
        } else {
            responseMessage = result.message().isBlank()
                    ? format("You can't drop the %s right now.%n", theItem.getItemDisplayName())
                    : result.message();
        }

        return responseMessage;
    }

    /**
     * Takes all items from the room and moves them to the player.
     *
     * @return a string describing the result of the action.
     */
    private String takeAllItems() {
        if (!this.isRoomPresent()) {
            Logger.getGlobal().warning("Player is not in a room, can't take items.");
            return "You can't take items here!";
        }

        List<Item> itemList = this.getCurrentRoom().getItemList();

        if (itemList.isEmpty()) {
            return "No items to take.";
        } else {
            return (new ArrayList<>(itemList).stream().map(item -> this.takeItem(item.getItemAliases().getFirst()))
                    .collect(Collectors.joining("\n")));
        }
    }

    /**
     * Takes a single item from the room.
     *
     * @param itemAlias The item to be taken.
     * @return a string describing the result of the action.
     */
    String takeItem(final String itemAlias) {
        if (!this.isRoomPresent()) {
            Logger.getGlobal().warning("Player is not in a room, can't take items.");
            return "You can't take items here!";
        }

        // If "ALL" was specified, then take all items.
        if ("ALL".equalsIgnoreCase(itemAlias)) {
            return this.takeAllItems();
        }

        // Get the item from any one of its aliases.
        final var itemFromAlias = this.getItemFromAlias(itemAlias, this.getCurrentRoom().getItemList());

        // If the item exists, then take it.
        return itemFromAlias
                .map(this::takeItem)
                .orElse(format("I see no '%s' here!!!", itemAlias));
    }

    /**
     * Takes a single item from the room. This handles the actual taking of the item.
     *
     * @param item The item to be taken.
     * @return a string describing the result of the action.
     */
    private String takeItem(Item item) {
        String itemDisplayName = item.getItemDisplayName();

        if (item.isTransferable()) {
            ItemInteractionResult result = item.itemInteractionHandler(new ItemInteractionEvent(GET));
            String responseMessage;

            if (result.bSuccess()) {
                // transfer the item from the room to the player.
                boolean xfered = transferItem(this.getCurrentRoom(), this.player, item);

                responseMessage = xfered
                        ? result.message().isBlank() ? format("You picked up the %s.", item.getItemDisplayName())
                                : result.message()
                        : "You can't take a non-transferable item!";
            } else {
                responseMessage = result.message().isBlank()
                        ? format("You can't take the %s.", item.getItemDisplayName())
                        : result.message();

            }

            return responseMessage;
        } else {
            return format("Despite your valiant attempts, the %s is unmovable.", itemDisplayName);
        }
    }

    /**
     * Invokes the item interaction handler for the specified item.
     *
     * @param noun   The item to be manipulated.
     * @param action The action to be performed.
     * @return a string describing the result of the action.
     */
    String interactWithItem(String noun, ItemInteractionEventType action) {
        // Get the item from the alias, searching both the player's inventory and the
        // current room's items.
        var item = this.getItemFromAlias(noun,
                Utils.concatLists(
                        this.getPlayer().getItemList(),
                        this.getCurrentRoom().getItemList()));

        // Check if the item is present.
        if (item.isPresent()) {
            // Interact with the item using the specified action.
            var result = item.get().itemInteractionHandler(new ItemInteractionEvent(action));

            // Return the result message if the interaction was successful.
            if (result.bSuccess()) {
                return result.message();
            } else {
                // Return a default message if the result message is blank.
                return result.message().isBlank() ? "You can't %s the %s.".formatted(action.getAliases(), noun)
                        : result.message();
            }

        } else {
            // Return a message indicating that the item was not found.
            return format("You see no '%s' here!!!", noun);
        }
    }

    /**
     * Get an item object based on the alias. If there are multiple matches, the
     * first one found will be returned.
     *
     * @param lexeme   The target object name.
     * @param itemList The list of items to search.
     * @return         An Optional containing the item object if found, otherwise an empty Optional.
     */
    @SafeVarargs
    private Optional<? extends Item> getItemFromAlias(final String lexeme, Collection<? extends Item>... itemList) {
        // This replaces the above!
        return Arrays.stream(itemList)
                .flatMap(Collection::stream)
                .filter(item -> item.getItemAliases().stream()
                        .anyMatch(lexeme::equalsIgnoreCase))
                .findFirst();
    }

    /**
     * This is a bit of a hack to allow the rooms to build their own parsers or
     * manipulate the raw string before being handed off to the main game shell.
     *
     * @param inputLine The raw input line.
     * @return The manipulated input line.
     */
    String preProcessInput(String inputLine) {
        if (this.isRoomPresent() && this.getCurrentRoom() instanceof InputInterceptor room) {
            String returnString = room.interceptInput(inputLine);
            return returnString == null ? "" : returnString;
        } else {
            return inputLine;
        }
    }

    /**
     * Checks if the room for the current player exists (has a "room" object).
     *
     * @return true if the room exists, false otherwise.
     */
    private boolean isRoomPresent() {
        return this.isRoomPresent(player.getCurrentRoomId());
    }

    /**
     * Checks if a room with a given ID exists.
     *
     * @param roomID The ID of the Room.
     * @return true if the room exists, false otherwise.
     */
    private boolean isRoomPresent(int roomID) {
        return rooms.getRoomFromID(roomID).isPresent();
    }

    /**
     * Debug call to show the contents of all rooms. (Delegate Method)
     *
     * @return a formatted string listing the complete contents of the room.
     */
    String DEBUG_DescribeAllRoomContents() {
        return this.rooms.DEBUG_GetAllMapContents();
    }
}
