package objectAdventure.core;
// $Id: RoomList.java 114 2024-10-08 20:19:42Z aconover $

import objectAdventure.RoomInitializer;
import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.err;

/**
 * The RoomList class is a singleton class that contains all the rooms in the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class RoomList {

    private static final Logger LOGGER = Logger.getGlobal();
    private final Map<Integer, Room> roomList = new TreeMap<>();
    private static volatile RoomList instance;

    /**
     * Prevent instantiation, this is a singleton class.
     */
    private RoomList() {
        // Force usage of factory method.
    }

    /**
     * Singleton method for construction of the RoomList Object before adding any rooms to it. (Uses
     * a double-checked locking pattern, though it would only be useful if this were a multithreaded
     * application.)
     *
     * @return a fully constructed room list object
     */
    public static RoomList newInstance() {
        if (instance == null) {
            synchronized (RoomList.class) {
                if (instance == null) {
                    // Create a new instance of the RoomList.
                    instance = new RoomList();

                    // Initialize the rooms in the game.
                    RoomInitializer.initRooms(instance);

                    // Ensure the Lobby and Secret Testing Room exist.
                    ensureRoomExists(0, "The Lobby", "objectAdventure.world.aconover.StartRoom", "newInstance");
                    ensureRoomExists(99, "Secret Testing Room", "objectAdventure.core.room.SecretTestingRoom.SecretTestingRoom", "newInstance");
                }
            }
        }

        return instance;
    }

    /**
     * Ensure the room exists in the RoomList.
     *
     * @param roomId            The ID of the room.
     * @param roomName          The name of the room.
     * @param roomClassName     The class name of the room.
     * @param factoryMethodName The factory method name to create the room.
     */
    private static void ensureRoomExists(int roomId, String roomName, String roomClassName, String factoryMethodName) {
        try {
            final Class<?> roomClass = Class.forName(roomClassName);
            final Class<?>[] paramSignature = {int.class, String.class};

            final boolean hasConstructor = Arrays
                    .stream(roomClass.getDeclaredConstructors())
                    .anyMatch(x -> hasMethod(x, paramSignature, roomClassName));

            final boolean hasNewInstanceMethod = Arrays
                    .stream(roomClass.getDeclaredMethods())
                    .anyMatch(x -> hasMethod(x, paramSignature, factoryMethodName));

            // Use the newInstance method if it exists, otherwise use the constructor.
            if (hasNewInstanceMethod) {
                Method newInstanceMethod = roomClass.getDeclaredMethod(factoryMethodName, int.class, String.class);
                instance.addRoom((Room) newInstanceMethod.invoke(instance, roomId, roomName));
            } else if (hasConstructor) {
                Constructor<?> declaredConstructor = roomClass.getDeclaredConstructor(int.class, String.class);
                instance.addRoom((Room) declaredConstructor.newInstance(roomId, roomName));
            }
        } catch (Exception e) {
            err.printf("Error ensuring room instantiation %d: %s%n", roomId, e.getMessage());
        }
    }

    /**
     * Checks if the given method has the specified name and parameter types.
     *
     * @param method     The method or constructor to check.
     * @param paramTypes The parameter types to match.
     * @param methodName The name of the method to match.
     * @return true if the method matches the specified name and parameter types, false otherwise.
     */
    private static boolean hasMethod(Executable method, Class<?>[] paramTypes, String methodName) {
        return method.getName()
                     .equals(methodName) && Arrays.equals(method.getParameterTypes(), paramTypes);
    }

    /**
     * Show the contents of all rooms in the game.
     *
     * @return formatted string listing the complete contents of the room.
     */
    public String DEBUG_GetAllMapContents() {
        StringBuilder sb = new StringBuilder();

        // Append the game contents header
        sb.append("Game Contents:\n");

        // Iterate through each room in the room list
        for (var room : roomList.values()) {
            // Append room details
            sb.append("\tRoom %02d: %s\n"
                              .formatted(room.getRoomId(), room.getClass().getSimpleName()));

            // Iterate through each item in the room
            for (var item : room.getItemList()) {
                // Append item details
                sb.append("\t\tItem: %s (Aliases: %s)%n"
                                  .formatted(item.getClass().getSimpleName(),
                                             item.getItemAliases()));
            }
        }

        return sb.toString();
    }

    /**
     * Get the room object from the ID.
     *
     * @param roomId the ID of the room to retrieve.
     * @return The room object from the ID.
     * @throws NoSuchRoomException thrown if the room does not exist.
     */
    public Optional<Room> getRoomFromID(int roomId) {
        if (this.exists(roomId)) {
            return Optional.of(roomList.get(roomId));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Does the room exist in the map?
     *
     * @param roomId RoomID to check
     * @return true if exists, false otherwise.
     */
    private boolean exists(int roomId) {
        return roomList.containsKey(roomId);
    }


    /**
     * Add a Room Object to the map.
     *
     * @param room The room object being added to the map.
     */
    public void addRoom(Room room) {
        Integer roomId = room.getRoomId();

        // Check if the room already exists in the room list and if it is of a different class
        if (roomList.containsKey(roomId) && !roomList.get(roomId)
                                                     .getClass()
                                                     .equals(room.getClass())) {
            final String msgTmpl = "Room '%d' already exists in the RoomList\nNot adding '%s' (Class: %s) to Room List.";
            LOGGER.log(Level.WARNING, msgTmpl
                    .formatted(roomId, room.getRoomName(), room.getClass().getSimpleName()));
        } else {
            // Add the room to the room list
            roomList.put(roomId, room);

            // Log the addition of the room.
            LOGGER.log(Level.CONFIG, "Added room {0}: {1}", new Object[]{roomId, room});
        }
    }
}
