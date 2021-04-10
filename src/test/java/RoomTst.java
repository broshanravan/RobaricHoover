
import beans.Coordinate;
import beans.Room;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RoomTst {

    @Test
    public void testParametrizedConstructor(){
        Coordinate dirtLocation_1 = new Coordinate(10,15);
        Coordinate dirtLocation_2 = new Coordinate(8,3);

        List<Coordinate> dirtLocationsList = new LinkedList<Coordinate>();

        dirtLocationsList.add(dirtLocation_1);
        dirtLocationsList.add(dirtLocation_2);

        Coordinate hooverLocation = new Coordinate(5,9);
        Room room = new Room(25,28,dirtLocationsList, hooverLocation);

        assert (room.getLength() == 25);
        assert (room.getWidth() == 28);

        assert (room.getDirtLocationsList().get(0).getPisitionX() == 10 );
        assert (room.getDirtLocationsList().get(0).getPositionY() == 15 );

        assert (room.getHooverLocation().getPisitionX() == 5 );
        assert (room.getHooverLocation().getPositionY() == 9 );

    }
}
