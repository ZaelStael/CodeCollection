package objectAdventure.core.command;
// $Id: CommandProcessor.java 720 2024-11-18 20:54:59Z tbaker17 $

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEventType;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static objectAdventure.core.DescriptionType.LONG;
import static objectAdventure.core.DescriptionType.SHORT;
import static objectAdventure.core.Direction.directionFromLexeme;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!! NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>This class is intended to be improved upon by using a more sophisticated design pattern such
 * as the 'Interpreter' pattern, perhaps in combination with other collaborating patterns such as
 * 'Command', 'Chain of Responsibility', and/or 'Visitor' patterns!!!</p>
 *
 * <p>Aside from Directions and a few helpful exceptions (noted in "showCommands()"), all commands
 * are two words (verb noun).</p>
 *
 * @author Adam J. Conover, COSC436/COSC716
 */

// AJC_TODO: Rename this to CommandInterpreter.
public class CommandProcessor {

    // The Game Controller object.
    private final GameController controller;

    /**
     * Constructor for the CommandProcessor.
     * <p>
     * User input is tokenized and then the appropriate method(s) of the controller are invoked.
     *
     * @param controller The controller to use for the command processor.
     */
    public CommandProcessor(GameController controller) {
        this.controller = controller;
    }

    /**
     * Get the controller being used by the command processor.
     *
     * @return The controller being used by the command processor.
     */
    GameController getController() {
        return controller;
    }

    /**
     * Parse player input
     *
     * @param inputLine The line typed by the user
     * @throws UnknownCommandException Thrown if the command cannot be processed
     */
    Optional<String> processCommand(final String inputLine) throws UnknownCommandException {
        String preProcessedLine = controller.preProcessInput(inputLine);
        var normalizedLine = preProcessedLine.trim().toUpperCase();

        // Bail out if blank
        if (normalizedLine.isBlank()) {
            return Optional.empty();
        }

        // Return help if requested
        if (normalizedLine.startsWith("?") || normalizedLine.startsWith("HELP")) {
            return Optional.of(CommandHelp.help());
        }

        // Tokenize the string
        var tokenizedInput = PlayerCommand.tokenizeInputString(normalizedLine);

        // AJC_TODO: Use the canonical "isPresent" pattern (currently cannot due to the UnknownCommandException).
        // Process command
        if (tokenizedInput.isPresent()) {
            PlayerCommand playerCommand = tokenizedInput.get();
            return processCommand(playerCommand);
        } else {
            return Optional.of(CommandHelp.help());
        }
    }

    /**
     * Processes the command input from the player and executes the corresponding action. This
     * method interprets various commands such as movement, inventory management item interactions,
     * debugging, and system operations. (In an extreme violation of the Single Responsibility
     * Principle, this method does a lot of things.)
     *
     * <p>The method uses a switch expression to handle different command verbs and their
     * associated actions. It covers a wide range of game functionalities including player movement,
     * inventory display, item inspection, teleportation, room display, debugging, item
     * manipulation, and log level setting.</p>
     *
     * <p>After processing the command, the move counter is incremented.</p>
     *
     * <p><strong>Note:</strong> This method is intended for future refactoring to eliminate
     * the need for UnknownCommandException.</p>
     *
     * @param playerCommand The command object containing the parsed player input, including the
     *                      original input string, verb, and noun.
     * @return An Optional<String> containing the response message to be displayed to the player. If
     *         no response is needed, an empty Optional is returned.
     * @throws UnknownCommandException If the command cannot be processed or is not recognized.
     */
    private Optional<String> processCommand(final PlayerCommand playerCommand) throws UnknownCommandException {

        // Extract the verb and noun from the command because they are used multiple times.
        final var noun = playerCommand.noun;
        final var verb = playerCommand.verb;

        // NOTE: This switch is intended for an eventual refactoring.
        var response = switch (verb) {
            // Movement
            case "N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST", "U", "UP", "D", "DOWN" -> {
                var dir = directionFromLexeme(verb);
                dir.ifPresent(controller::movePlayer);

                // Nulls are usually bad, but this one never leaves the method and is only used for
                // part of the "optional" response.
                yield null;
            }

            // Inventory
            case "I", "INVENTORY" ->
                    "Inventory:%n%s".formatted(controller.getPlayerInventoryDisplayNames());

            // Looking at objects
            case "L", "LOOK" -> noun.isBlank()
                    ? "%s%n%nYou See:%n%s"
                    .formatted(
                            controller.getCurrentRoom().getRoomDescription(),
                            controller.getRoomItemDisplayNames(SHORT))
                    : controller
                    .getAllItemsFromItemAlias(
                            noun,
                            controller.getPlayer().getItemList(),
                            controller.getCurrentRoom().getItemList())
                    .stream()
                    .map(Item::getItemFullDescription)
                    .collect(Collectors.collectingAndThen(
                            Collectors.joining("\n"),
                            str -> str.isBlank() ? format("You don't see %s here.", noun) : str));

            // Teleportation from room to room
            case "T", "TELEPORT" -> {
                try {
                    var roomId = parseInt(noun);
                    if (controller.teleportPlayer(roomId)) {
                        yield ("Teleported to room: " + noun);
                    } else {
                        yield "Teleportation to non-existent locations is not yet supported.";
                    }
                } catch (NumberFormatException nfe) {
                    Logger.getGlobal().warning("Invalid room ID: " + noun);
                    yield "You can only teleport to a room by its ID.";
                }
            }

            // Various "DISPLAY" commands
            case "DISPLAY", "SHOW" -> {
                if ("ROOM".equalsIgnoreCase(noun)) {
                    controller.getCurrentRoom().displayRoomImage();
                    yield null;
                } else {
                    yield "I don't know how to DISPLAY %s.".formatted(noun);
                }
            }

            // Various "DISPLAY" commands
            case "DEBUG" -> switch (noun) {
                case "ROOM" -> DEBUG_getFormattedRoomInfo();
                case "MAP" -> controller.DEBUG_DescribeAllRoomContents();
                default -> "I don't know how to DISPLAY %s.".formatted(noun);
            };

            // Commands for obtaining items
            case "GET", "TAKE" -> controller.takeItem(noun);

            // Commands for dropping items
            case "DROP" -> controller.dropItem(noun);

            // Set the log level dynamically.
            case "LOG" -> {
                try {
                    Logger.getGlobal().setLevel(Level.parse(noun));
                    yield format("Log level set to: %s", noun);
                } catch (IllegalArgumentException __) {
                    yield format("Invalid log level: %s", noun);
                }
            }

            // The default is to assume the command was the result of an interaction with an item.
            // Loop though all item interaction commands and fire off the one that matches the verb.
            default -> controller.interactWithItem(
                    noun,
                    ItemInteractionEventType
                            .actionFromLexeme(verb)
                            .orElseThrow(() -> new UnknownCommandException(
                                    "I don't understand: %s".formatted(playerCommand.originalInput))));
        };

        // Return the response
        return Optional.ofNullable(response);
    }

    /**
     * Helper for processCommand() for greater legibility.
     *
     * @return Formatted room description, room contents, and Inventory
     */
    private String DEBUG_getFormattedRoomInfo() {
        return """
                Room Author: %s
                
                Room Description:
                %s
                
                Items in Room:
                %s
                
                Items in Inventory:
                %s""".formatted(
                controller.getCurrentRoom().getRoomAuthor(),
                controller.getCurrentRoom().getRoomDescription(),
                controller.getRoomItemDisplayNames(LONG),
                controller.getPlayerInventoryDisplayNames());
    }


    /**
     * Record representing a player command.
     *
     * @param originalInput The original input string from the player.
     * @param verb          The verb part of the command.
     * @param noun          The noun part of the command.
     */
    record PlayerCommand(String originalInput, String verb, String noun) {
        // Regex for tokenizing input. (It is simply split on spaces.)
        private static final Pattern INPUT_TOKEN_PATTERN = Pattern.compile("(\\S+)(.*)");

        /**
         * Tokenizes the input string into a PlayerCommand.
         *
         * @param originalInput The original input string from the player.
         * @return An Optional containing a PlayerCommand if the input matches the expected pattern,
         *         otherwise an empty Optional.
         */
        private static Optional<PlayerCommand> tokenizeInputString(String originalInput) {
            // Create a matcher that will match the input string against the compiled regex pattern
            final var matcher = INPUT_TOKEN_PATTERN.matcher(originalInput);

            // Check if the matcher finds a match in the input string
            if (matcher.find()) {
                // Extract the first group (verb) and trim any leading/trailing whitespace
                var verb = matcher.group(1).trim();

                // Extract the second group (noun) and trim any leading/trailing whitespace
                var noun = matcher.group(2).trim();

                // Return an Optional containing a new PlayerCommand object with the original input, verb, and noun
                return java.util.Optional.of(new PlayerCommand(originalInput, verb, noun));
            } else {
                // If no match is found, return an empty Optional
                return java.util.Optional.empty();
            }
        }
    }
}
