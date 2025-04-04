package objectAdventure.core.command;
// $Id: CommandHelp.java 114 2024-10-08 20:19:42Z aconover $

import objectAdventure.core.item.ItemInteractionEventType;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static objectAdventure.core.item.ItemInteractionEventType.DROP;
import static objectAdventure.core.item.ItemInteractionEventType.GET;

final class CommandHelp {
    /**
     * A private constructor to prevent instantiation.
     */
    private CommandHelp() {
        // Prevent instantiation, this is a utility class.
    }

    /* The help text for the game. */
    final private static String COMMAND_HELP_TEMPLATE = """
            Commands
                Movement:
                  N(orth) | S(outh) | E(ast) | W(est) | U(p) | D(own)
            
                Item Interactions:
                  ( TAKE | DROP ) ( <item> | ALL )
                  ( %s ) <item>
            
                Room Interactions:
                    SHOW ROOM
            
                Debugging:
                  Show Game Elements:
                    DEBUG ( ROOM | MAP )
                  Change the logging level: [See Main.java]
                    LOG ( <Java Logging Level> )
            
                 Other:
                    I                # ("Inventory": Show Player Inventory)
                    L {item}         # ("Look": Show Room Description & Room Items)
                    T [room id]      # ("Teleport": Jump to RoomID)
                    ?                # (This List)""";


    /**
     * Return the help text for item interactions.
     */
    private static String getItemInteractionHelp(Function<ItemInteractionEventType, String> getAliasesFunction) {
        // Get all enum values from ItemInteractionEvent
        return Arrays.stream(ItemInteractionEventType.values())
                     .filter(value -> value != GET)  // In relocation help.
                     .filter(value -> value != DROP)  // In relocation help.
                     .map(getAliasesFunction)
                     .sorted()
                     .collect(Collectors.joining(" | "));
    }


    /**
     * Return the help text for the game.
     *
     * @return The help text for the game.
     */
    public static String help() {
        return String.format(COMMAND_HELP_TEMPLATE, getItemInteractionHelp(ItemInteractionEventType::name));
    }

}
