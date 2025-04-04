package objectAdventure.world.tbaker17.ShieldStateVers;

import java.util.Scanner;

public class DeactiveState implements State {

    // Shield instance.
    final Shield shield;

    public DeactiveState(Shield shield) {
        System.out.println("The shield is a bit dissappointed at being a shield.");
        this.shield = shield;
    }

    /*
     * Handles shield feeding.
     * Prints message indicating feeding can not commence whilst activated.
     */
    @Override
    public void feedShield() {
        System.out.println("The shield appreiates your contributions to its health.");
        shield.feedShield();
    }

    /*
     * Handles shield breaking.
     * Prints message asking for confirmation.
     */

    @Override
    public void BreakShield() {
        System.out.println("Are you positive you want to break the shield?");

        Scanner scan = new Scanner(System.in);
        String ans = scan.nextLine();

        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
            shield.BreakShield();
            System.out.println("""
                    You rip off the shield, rejecting it, and it follows suit...
                    rejecting you and dissappearing from existence.
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
        shield.ActivateShield();
        System.out.println("You turn the shield on.");
        shield.setActiveState();
    }

    /*
     * Handles shield adectivation.
     */

    @Override
    public void DeactivateShield() {
        shield.DeactivateShield();
        System.out.println("Already dry as a bone bro.");
        shield.setDeactiveState();
    }

    /**
     * Returns a string representation of the state.
     *
     * @return a string indicating the state is waiting for a turn of the crank
     */

    @Override
    public String toString() {
        return "Waiting for you to make a move babe.";
    }
}
