package data;

import beans.Coords;
import beans.Room;

public interface CleaningOperationInventory {
    public void persistOperation(Coords finalHooverLocation, Room room, int NumberOfTilesCleaned, String resultJSON);
}
