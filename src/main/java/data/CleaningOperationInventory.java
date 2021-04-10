package data;

import beans.Room;

public interface CleaningOperationInventory {
    public void persistOperation(Room room, int NumberOfTiles);
}
