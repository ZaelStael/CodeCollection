package objectAdventure;
// $Id: RoomInitializer.java 677 2024-11-14 18:38:21Z tbaker17 $

import objectAdventure.core.RoomList;

/**
 * Note that this REALLY doesn't belong in this package in terms of "good
 * design" (it
 * should in the Room package or perhaps with the Core package). However, it's
 * placed
 * here to make it easier to edit as everyone adds their Room Implementations
 * and makes them available to the shell.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class RoomInitializer {

    private RoomInitializer() {
        // Utility classes cannot (should not) be instantiated.
    }

    /**
     * Initialization method for all rooms in the game.
     * <p>
     * NOTE: As some want to constantly rearrange imports, use the "Fully Qualified
     * Name" (FQN) in this class to avoid merge conflicts.
     * <p>
     * Using a "newInstance" method is often a better approach than using a
     * constructor directly, for reasons that will be discussed in class later in
     * the semester.
     *
     * @param rooms The list of rooms in the game.
     */
    public static void initRooms(RoomList rooms) {
        // !!! DO NOT MODIFY THE EXAMPLE BELOW, OTHERS ARE REPLYING UPON IT !!!
        // !!! All rooms must be constructed with the room ID and Room Name
        // (short name to be displayed at prompts) !!!

        // NOTE: The FQN (Fully Qualified Name) is used to prevent merge conflicts due
        // to
        // "import wars" within a source file that is touched by numerous developers.
        // Many IDE's will automatically reformat imports, causing merge conflicts.
        // Using the FQN prevents this problem.

        // aconover
        // NOTE: The room ID (the one assigned), and the room's display name.
        rooms.addRoom(objectAdventure.world.aconover.StartRoom.newInstance(0, "The Lobby"));

        /*
         * **************************************************************************
         * EACH STUDENT SHOULD ADD THEIR ROOMS BELOW.
         * USE THE SAME FORMAT AS ABOVE. (The "// aconover" example.)
         *
         * Use the "Fully Qualified Class Name" for the room as shown. This will
         * prevent most merge conflicts caused by frequently changing import
         * statements. The room ID should be the same as the one assigned.
         *
         * Be sure to use *Your Own* ROOM ID, ROOM NAME, and USERNAME.
         * **************************************************************************
         */

        // aadelua1

        // aagyei1

        // ababatu1

        // aboddu1

        // aduress1

        // agarcia1

        // ahelle6

        // along28

        // amanda4

        // amoral16

        // aowoade1

        // cbucki1

        // dchin2

        // fsanch4

        // jbloch1
        rooms.addRoom(objectAdventure.world.jbloch1.AxolotlRoom.newInstance(21, "Axolotl Room"));

        // jlin22

        // kaceve2

        // kcypra1

        // kreid11

        // kyomba1

        // lmouss1

        // mreifer1

        // nnguyen1

        // nscardi1

        // onahum1

        // pchodav1

        // pthapa6

        // slopez12

        // tbaker17
        rooms.addRoom(objectAdventure.world.tbaker17.VoidRoom.newInstance(3, "Void Room")); // Find some way into that
                                                                                            // rooms list...
        // twatki8

        // Jbloch1

        // Jlin
    }
}
