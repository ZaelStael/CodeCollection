package objectAdventure.world.tbaker17.ShieldStateVers;

import java.util.Scanner;

public class BrokenState implements State {
    // Shield instance.
    final Shield shield;

    public BrokenState(Shield shield) {
        System.out.println("The shield no longer exists.\n\nSuffer your consequences petty mortal.");
        this.shield = shield;
    }

    /*
     * Handles shield feeding.
     * Prints message indicating feeding can not commence whilst activated.
     */
    @Override
    public void feedShield() {
        System.out.println("The shield refuses your attempt at care.");
    }

    /*
     * Handles shield breaking.
     * Prints message asking for confirmation.
     */

    @Override
    public void BreakShield() {
        System.out.println("Breaking the shield again?");

        Scanner scan = new Scanner(System.in);
        String ans = scan.nextLine();

        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
            shield.BreakShield();
            System.out.println("""
                    You smash the shield into the ground. It falls into pieces.
                    """);
            scan.close();

        } else {
            shield.setPatience(shield.getPatience() - 7);
            System.out.println("Too little, too late...");
            scan.close();
        }
    }

    /*
     * Handles shield activation.
     */

    @Override
    public void ActivateShield() {
        System.out.println("You try turnning the shield on...to no avail.");
    }

    /*
     * Handles shield adectivation.
     */

    @Override
    public void DeactivateShield() {
        System.out.println("Just drop the shield weather boi.");
    }

    /**
     * Returns a string representation of the state.
     *
     * @return a string indicating the state is waiting for a turn of the crank
     */

    @Override
    public String toString() {
        return "Cancel your subscription.";
    }

}
