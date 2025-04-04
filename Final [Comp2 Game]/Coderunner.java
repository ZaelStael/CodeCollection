import java.util.*;
import java.io.*;

public class Coderunner {
    static Scanner in = new Scanner(System.in);
    static int count = 0;

    public static void main(String[] args) throws Exception {

        // Create needed objects for the game.
        GameState state = new GameState();
        // Store the command system for easy reference in the client code.
        CommandSystem commandSystem = state.commandSystem;

        // This controls if the game should continue running.
        boolean gameRunning = true;

        System.out.println(
                "Your brother recommended a certain horror attraction that he worked at last October, \nciting it's 'uber-creepy' atmosphere. He made it sound as though this place was hell itself,\nand you must admit, you were a little intrigued. You followed the instructions he gave you, \npulling off the interstate and onto a barely visible dirt trail. \nAfter driving for a while, you come across a medium-sized clearing with a few cars in it, \na giant tree cutout marking it's entrance. You pull into a spot and prepare yourself for \nwhatever you may see.");

        // The main game loop.
        while (gameRunning) {

            // Gets input from the user in an array of strings that they typed in.
            String[] input = getCommand();

            if (input.length < 1) {
                System.out.println("Unknown command. Type ? for help.");

            } else if (input[0].equals("quit")) {
                gameRunning = false;
                System.out.println("Goodbye.");
                in.close();

                // Command has 1 word - Check if it is a valid verb and execute it.
            } else if (input.length == 1 && commandSystem.hasVerb(input[0])) {
                commandSystem.executeVerb(input[0]);

                // Command has 2 words - should be verb and noun.
            } else if (input.length == 2) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else {
                    // Run command
                    commandSystem.executeVerbNoun(input[0], input[1]);
                }

                // command has 3 words - should be verb noun noun
            } else if (input.length == 3) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else if (!commandSystem.hasNoun(input[2])) {
                    unknownCommand(input[2]);
                } else {
                    // Run command
                    commandSystem.executeVerbNounNoun(input[0], input[1], input[2]);
                }

                // Deal with any possible unknown structure/command
            } else {
                if (input.length > 1) {
                    String userInput = "";

                    for (String s : input)
                        userInput += s + " ";

                    unknownCommand(userInput);

                } else {
                    unknownCommand(input[0]);
                }
            }
        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        if (count == 0) {
            System.out.println("You sit in your car, hyping yourself up to go inside.");
            count++;
        }

        // for (int i = 0; i < GameState.Locales.size(); i++) {
        // System.out.println(GameState.Locales.get(i).localeName);
        // }

        // for (int i = 0; i < GameState.items.size(); i++) {
        // System.out.println(GameState.items.get(i).itemName);
        // }

        in = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.print("What would you like to do next? >  ");
        String input = in.nextLine();
        System.out.println();
        return input.toLowerCase().split("\\s+");
    }

    // Used to let the user know that what they typed as a command is not
    // understood.
    public static void unknownCommand(String input) {
        if (Math.random() < .3) // A random chance for a silly response.
            System.out.println("Don't be silly. Everyone knows '" + input + "' is not a command! Type ? for help.");
        else
            System.out.println("I don't understand '" + input + "'. Type ? for help.");
    }

}
