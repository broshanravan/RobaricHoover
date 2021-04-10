package driving;

import beans.Coordinate;

public interface Navigation {
    public double gotToCollectDirt(Coordinate hooverLocation, Coordinate dirtLocation , int roomLenght, int roomWidth);
}
