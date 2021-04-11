package beans;

import java.util.List;

public class Room {
    private int length;
    private int width;
    private List<Coordinates> dirtLocationsList;
    private Coordinates hooverLocation;

    public Room(int length, int width, List<Coordinates> dirtLocationsList, Coordinates hooverLocation) {
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

    public List<Coordinates> getDirtLocationsList() {
        return dirtLocationsList;
    }

    public void setDirtLocationsList(List<Coordinates> dirtLocationsList) {
        this.dirtLocationsList = dirtLocationsList;
    }

    public Coordinates getHooverLocation() {
        return hooverLocation;
    }

    public void setHooverLocation(Coordinates hooverLocation) {
        this.hooverLocation = hooverLocation;
    }
}
