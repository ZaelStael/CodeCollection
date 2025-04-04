package objectAdventure.world.tbaker17.ShieldStateVers;

import java.util.Scanner;

import objectAdventure.core.command.GameController;

public class ActiveState implements State {

    // Shield instance.
    final Shield shield;

    public ActiveState(Shield shield) {
        System.out.println("The Door is pleased to make your acquaintance.");
        this.shield = shield;
    }

    /*
     * Handles shield feeding.
     * Prints message indicating feeding can not commence whilst activated.
     */
    @Override
    public void feedShield() {
        System.out.println("You can't feed The Door right now.");
    }

    /*
     * Handles shield breaking.
     * Prints message asking for confirmation.
     */

    @Override
    public void BreakShield() {
        System.out.println("Are you positive you want to break THE DOOR?!?");

        Scanner scan = new Scanner(System.in);
        String ans = scan.nextLine();

        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
            shield.BreakShield();
            System.out.println("""
                    You rip off the metaphysical tethers binding you to The Door, rejecting it, and it follows suit...
                    rejecting you.
                    """);
            shield.setBrokenState();
            scan.close();

        } else {
            shield.setPatience(shield.getPatience() - 7);
            System.out.println("Good choice...");
            scan.close();
        }
    }

    /*
     * Handles shield activation.
     */

    @Override
    public void ActivateShield() {
        System.out.println("You tease. The Door is already activated.");
    }

    /*
     * Handles shield deactivation.
     */

    @Override
    public void DeactivateShield() {
        shield.DeactivateShield();
        System.out.println("You turn the Door off with your bad attitude.");
        shield.setDeactiveState();
    }

    public void teleport() {
        System.out.println("Where to (As a number)?");

        Scanner scan = new Scanner(System.in);
        int ans = scan.nextInt();
        System.out.println("Knock %d times and then enter.".formatted(ans));
        scan.close();
        // player.tPlayer(ans); Something something something, teleport player wherever
    }

    /**
     * Returns a string representation of the state.
     *
     * @return a string indicating the state is waiting for a turn of the crank
     */

    @Override
    public String toString() {
        return "Waiting for a destination.";
    }
}
