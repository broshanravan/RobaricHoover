package data;

import beans.Coordinates;
import beans.Room;

public interface CleaningOperationInventory {
    public void persistOperation(Coordinates finalHooverLocation, Room room, int NumberOfTilesCleaned, String resultJSON);
}
