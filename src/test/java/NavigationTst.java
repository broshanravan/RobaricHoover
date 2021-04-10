import beans.Coordinate;
import beans.Room;
import driving.Navigation;
import driving.NavigationImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class NavigationTst {

    Room room = null;
    @Before
    public void setup(){
        List<Coordinate> dirtLocations = new LinkedList<Coordinate>();

        Coordinate dirtPosition_1 = new Coordinate(3,7);
        Coordinate dirtPosition_2 = new Coordinate(6,5);
        Coordinate dirtPosition_3 = new Coordinate(8,8);

        dirtLocations.add(dirtPosition_1);
        dirtLocations.add(dirtPosition_2);
        dirtLocations.add(dirtPosition_3);

        Coordinate hooverStartingPosition = new Coordinate(3,7);
        room = new Room(15, 10, dirtLocations, hooverStartingPosition );
    }


    @Test
    public void getFinalPositionTst(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NNEWSEESNW";
        Coordinate hooverNextPosition = null;

        char instructionSteps[] = instructions.toCharArray();
        for(int i = 0 ; i< instructionSteps.length ; i++) {
            char moveDirection = instructionSteps[i];
            hooverNextPosition = navigation. getNextPosition(moveDirection , room);
            room.setHooverLocation(hooverNextPosition);
        }

        assert(hooverNextPosition.getPisitionX() == 4);
        assert(hooverNextPosition.getPositionY() == 8);


    }

    @Test
    public void getNextPositionTst(){
        Navigation navigation = new NavigationImpl();
        Coordinate hooverNextPosition = null;

        char moveDirection = 'N';
        hooverNextPosition = navigation. getNextPosition(moveDirection , room);

        assert(hooverNextPosition.getPisitionX() == 3);
        assert(hooverNextPosition.getPositionY() == 8);

        room.setHooverLocation(hooverNextPosition);

        moveDirection = 'E';
        hooverNextPosition = navigation. getNextPosition(moveDirection , room);
        assert(hooverNextPosition.getPisitionX() == 4);
        assert(hooverNextPosition.getPositionY() == 8);


    }
}
