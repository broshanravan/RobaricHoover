package driving;

import beans.Coords;
import beans.Room;

import java.util.List;

public interface Navigation {

    public Coords getFinalPosition(String instructions, Room room);

    public Coords getNextPosition(char moveDirection , Room room);

    public boolean isCoordinateValid(int longitude, int latitude, Room room, String dirt_hoover);

    public int getNumberOfDirtPatchesCovered(String direction , Room room);

    public boolean alreadyCleaned(Coords coordinate, List<Coords> cleanedPatches);

}
