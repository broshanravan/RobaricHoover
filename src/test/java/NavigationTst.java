import beans.Coordinates;
import beans.Room;
import driving.Navigation;
import driving.NavigationImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;

public class NavigationTst {

    Room room = null;
    Room roomNoDirtyPatchInPath =null;
    Room roomDirtyPatchCoveredMoreThanOnce =null;

    /**
     * preparing all the needed
     * variables and attribute
     * for the tests to go ahead
     */
    @Before
    public void setup(){
        List<Coordinates> dirtLocationsStartingFromADirtyPatchLst = new LinkedList<Coordinates>();

        Coordinates dirtPosition_1 = new Coordinates(3,7);
        Coordinates dirtPosition_2 = new Coordinates(6,5);
        Coordinates dirtPosition_3 = new Coordinates(8,8);
        Coordinates dirtPosition_4 = new Coordinates(1,1);

        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_1);
        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_2);
        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_3);


        List<Coordinates> dirtLocationsDirtyPatchNotCoveredLst = new LinkedList<Coordinates>();

        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_1);
        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_2);
        dirtLocationsStartingFromADirtyPatchLst.add(dirtPosition_4);

        List<Coordinates> dirtyPatchCoveredMoreThanOnceLst = new LinkedList<Coordinates>();
        dirtyPatchCoveredMoreThanOnceLst.add(dirtPosition_1);
        dirtyPatchCoveredMoreThanOnceLst.add(dirtPosition_1);
        dirtyPatchCoveredMoreThanOnceLst.add(dirtPosition_2);
        dirtyPatchCoveredMoreThanOnceLst.add(dirtPosition_3);
        dirtyPatchCoveredMoreThanOnceLst.add(dirtPosition_4);

        Coordinates hooverStartingPosition = new Coordinates(3,7);
        room = new Room(15, 10, dirtLocationsStartingFromADirtyPatchLst, hooverStartingPosition );
        roomNoDirtyPatchInPath = new Room(15, 10, dirtLocationsDirtyPatchNotCoveredLst, hooverStartingPosition);
        roomDirtyPatchCoveredMoreThanOnce = new Room(15, 10, dirtyPatchCoveredMoreThanOnceLst, hooverStartingPosition);;
    }


    /**
     * finding the final position of
     * the hoover after following
     * all the instructions
     */
    @Test
    public void getFinalPositionTst(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NNESESWSE";
        Coordinates hooverNextPosition =
        navigation.getFinalPosition(instructions, room);

        assert(hooverNextPosition.getPositionX() == 5);
        assert(hooverNextPosition.getPositionY() == 6);


    }

    /**
     * get the hoover position after
     * each incremental step
     */
    @Test
    public void getNextPositionTst(){
        Navigation navigation = new NavigationImpl();
        Coordinates hooverNextPosition = null;

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

    /**
     * The number of dirty patch  covered when the
     * hoover starts from a dirty patch
     */
    @Test
    public void getNumberOfDirtPatcherCoveredTst(){
        Navigation navigation = new NavigationImpl();
        int numberOfDirtPatchesCovered = navigation.getNumberOfDirtPatchesCovered("NNEWSEE" ,room);
        assert (numberOfDirtPatchesCovered == 1);

    }

    /**
     * Completing navigation steps do not
     * direct the hoover to any dirty patch
     */
    @Test
    public void testNumberOfCleanedPatchesNoDirtCovered(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NNESESWSE";
        int numberOfCleanedPatches = navigation.getNumberOfDirtPatchesCovered(instructions,roomNoDirtyPatchInPath);
        assert(numberOfCleanedPatches == 0);
    }

    /**
     * if a dirty patch is covered more than once
     * it should only
     * be counted as one
     * cleaned patch
     */
    @Test
    public void testDirtyPatchCoveredMoreThanOnce(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NESWS";
        int numberOfCleanedPatches = navigation.getNumberOfDirtPatchesCovered(instructions,roomDirtyPatchCoveredMoreThanOnce);
        assert(numberOfCleanedPatches == 1);
    }

    @Test
    public void testStartingFromADirtyPatch(){
        Navigation navigation = new NavigationImpl();
        String instructions = "NNEWSEESNW";
        Coordinates hooverNextPosition = null;

        char instructionSteps[] = instructions.toCharArray();
        for(int i = 0 ; i< instructionSteps.length ; i++) {
            char moveDirection = instructionSteps[i];
            hooverNextPosition = navigation. getNextPosition(moveDirection , room);
            room.setHooverLocation(hooverNextPosition);
        }


    }

    @Test
    public void testCoveringADirtyPatchMoreThanOnce(){

    }
}
