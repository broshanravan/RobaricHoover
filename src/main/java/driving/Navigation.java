package driving;

import beans.Coordinate;
import beans.Room;

import java.util.List;

public interface Navigation {

    public Coordinate getFinalPosition(String instructions, Room room);

    public Coordinate getNextPosition(char moveDirection , Room room);

    public boolean isCoordinateValid(int longitude, int latitude, Room room, String dirt_hoover);
}
