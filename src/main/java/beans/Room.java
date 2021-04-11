package beans;

import java.util.List;

public class Room {
    private int length;
    private int width;
    private List<Coords> dirtLocationsList;
    private Coords hooverLocation;

    public Room(int length, int width, List<Coords> dirtLocationsList, Coords hooverLocation) {
        this.length = length;
        this.width = width;
        this.dirtLocationsList = dirtLocationsList;
        this.hooverLocation = hooverLocation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<Coords> getDirtLocationsList() {
        return dirtLocationsList;
    }

    public void setDirtLocationsList(List<Coords> dirtLocationsList) {
        this.dirtLocationsList = dirtLocationsList;
    }

    public Coords getHooverLocation() {
        return hooverLocation;
    }

    public void setHooverLocation(Coords hooverLocation) {
        this.hooverLocation = hooverLocation;
    }
}
