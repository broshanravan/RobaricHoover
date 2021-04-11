package driving;

import beans.Coordinates;
import beans.Room;

import java.util.List;

public interface Navigation {

    public Coordinates getFinalPosition(String instructions, Room room);

    public Coordinates getNextPosition(char moveDirection , Room room);

    public boolean isCoordinateValid(int longitude, int latitude, Room room, String dirt_hoover);

    public int getNumberOfDirtPatchesCovered(String direction , Room room);

    public boolean alreadyCleaned(Coordinates coordinate, List<Coordinates> cleanedPatches);



}
