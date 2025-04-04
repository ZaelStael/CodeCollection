package objectAdventure.world.jbloch1;

import objectAdventure.core.room.Room;

public class AxolotlRoom extends Room {

        private AxolotlRoom(final int roomId, final String roomName) {
            super(roomId, roomName);
            super.setRoomAuthor("Julie N. Bloch");

            String description = """
                +------------------------------------+
                |      You found the axolotl room!   |
                +------------------------------------+
                
                In this room you find yourself sloshing your feet on
                very wet and mossy flooring. Several axolotls come at
                you to check you out, determining if you are food or not.
                You can see several more axolotls swimming about living their
                best life.
                """;

            // Set the room description.
            super.setRoomDescription(description);
        }

    public static Room newInstance(final int roomId, String roomName) {
        // Create a new room
        var theRoom = new AxolotlRoom(roomId, roomName);

        // Create a new item
        var aAxolotl = new Axolotl();

        // Add the Item to the room
        theRoom.addItem(aAxolotl);

        // Return the room.
        return theRoom;
    }
}
