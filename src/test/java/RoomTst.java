
import beans.Coordinates;
import beans.Room;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RoomTst {

    @Test
    public void testParametrizedConstructor(){
        Coordinates dirtLocation_1 = new Coordinates(10,15);
        Coordinates dirtLocation_2 = new Coordinates(8,3);

        List<Coordinates> dirtLocationsList = new LinkedList<Coordinates>();

        dirtLocationsList.add(dirtLocation_1);
        dirtLocationsList.add(dirtLocation_2);

        Coordinates hooverLocation = new Coordinates(5,9);
        Room room = new Room(25,28,dirtLocationsList, hooverLocation);

        assert (room.getLength() == 25);
        assert (room.getWidth() == 28);

        assert (room.getDirtLocationsList().get(0).getPositionX() == 10 );
        assert (room.getDirtLocationsList().get(0).getPositionY() == 15 );

        assert (room.getHooverLocation().getPositionX() == 5 );
        assert (room.getHooverLocation().getPositionY() == 9 );

    }
}
