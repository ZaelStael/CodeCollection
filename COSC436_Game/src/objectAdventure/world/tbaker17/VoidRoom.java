package objectAdventure.world.tbaker17;

import java.util.ArrayList;
import java.util.List;

import objectAdventure.common.Observable;
import objectAdventure.common.Observer;

import objectAdventure.core.room.Room;
import objectAdventure.core.RoomList;

// @param roomId = "Void Room";

@SuppressWarnings("rawtypes")
public class VoidRoom extends Room implements Observer<Notification> {

    private List<Observer<Notification>> observers = new ArrayList<>();
    private List<Observable<Notification>> observed = new ArrayList<>();

    private VoidRoom(final int roomID, final String roomName) {

        super(roomID, roomName);
        super.setRoomAuthor("Tyrique Baker");

        String description = """

                    +------------------------------------+
                    |   You found the V o I d room...    |
                    +------------------------------------+


                The room before you evokes a pit in the deepest bowels of your stomach...
                There are no walls, no ceilings, not even a floor. But somehow, against
                all reason and laws of nature, stepping your foot inside allows it purchase
                with solid...something. In front(?) of you lies a recliner, suspended in
                nothingness, a lone knife resting upon its right arm.""";

        super.setRoomDescription(description);
    }

    public static Room newInstance(final int roomID, String roomName) {
        var vRoom = new VoidRoom(roomID, roomName);

        var item1 = new Recliner();
        var item2 = new Knife();
        var item3 = new Shield();
        var item3p5 = new objectAdventure.world.tbaker17.ShieldStateVers.Shield(100);

        vRoom.addItem(item1);
        item1.addObserver(vRoom);
        vRoom.addItem(item2);
        item2.addObserver(vRoom);
        vRoom.addItem(item3);
        item3.addObserver(vRoom);
        vRoom.addItem(item3p5);
        item3p5.addObserver(vRoom);

        return vRoom;

    }

    @Override
    public void update(Notification object) {

        var itemClass = object.n().getClass().getSimpleName();

        System.out.printf("The room has been updated with '%s' by '%s'\n", object.m(), itemClass);

    }

    // @Override
    // public void playerEnteringRoom() {
    // int prevRoom = plaver.getPreviousRoomID();
    // System.out.printf(
    // "Leaving the %s, you find the path ahead growing dark. All trace of light
    // disappearing as you continue on your way.",
    // RoomList.getRoomFromID(prevRoom).roomName);

    // }

}
