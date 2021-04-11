import beans.Coords;
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
        List<Coords> dirtLocations = new LinkedList<Coords>();

        Coords dirtPosition_1 = new Coords(3,7);
        Coords dirtPosition_2 = new Coords(6,5);
        Coords dirtPosition_3 = new Coords(8,8);

        dirtLocations.add(dirtPosition_1);
        dirtLocations.add(dirtPosition_2);
        dirtLocations.add(dirtPosition_3);

        Coords hooverStartingPosition = new Coords(3,7);
        room = new Room(15, 10, dirtLocations, hooverStartingPosition );
    }


    @Test
    public void getFinalPositionTst(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NNEWSEESNW";
        Coords hooverNextPosition = null;

        char instructionSteps[] = instructions.toCharArray();
        for(int i = 0 ; i< instructionSteps.length ; i++) {
            char moveDirection = instructionSteps[i];
            hooverNextPosition = navigation. getNextPosition(moveDirection , room);
            room.setHooverLocation(hooverNextPosition);
        }

        assert(hooverNextPosition.getPositionX() == 4);
        assert(hooverNextPosition.getPositionY() == 8);


    }

    @Test
    public void getNextPositionTst(){
        Navigation navigation = new NavigationImpl();
        Coords hooverNextPosition = null;

        char moveDirection = 'N';
        hooverNextPosition = navigation. getNextPosition(moveDirection , room);

        assert(hooverNextPosition.getPositionX() == 3);
        assert(hooverNextPosition.getPositionY() == 8);

        room.setHooverLocation(hooverNextPosition);

        moveDirection = 'E';
        hooverNextPosition = navigation. getNextPosition(moveDirection , room);
        assert(hooverNextPosition.getPositionX() == 4);
        assert(hooverNextPosition.getPositionY() == 8);

    }

    @Test
    public void getNumberOfDirtPatcherCoveredTst(){
        Navigation navigation = new NavigationImpl();
        int numberOfDirtPatchesCovered = navigation.getNumberOfDirtPatchesCovered("NNEWSEE" ,room);
        assert (numberOfDirtPatchesCovered == 0);

    }
}
