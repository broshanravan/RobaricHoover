package beans;

import java.util.List;

public class Room {
    private int length;
    private int width;
    private List<Coordinate> dirtLocationsList;
    private Coordinate hooverLocation;

    public Room(int length, int width, List<Coordinate> dirtLocationsList, Coordinate hooverLocation) {
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

    public List<Coordinate> getDirtLocationsList() {
        return dirtLocationsList;
    }

    public void setDirtLocationsList(List<Coordinate> dirtLocationsList) {
        this.dirtLocationsList = dirtLocationsList;
    }

    public Coordinate getHooverLocation() {
        return hooverLocation;
    }

    public void setHooverLocation(Coordinate hooverLocation) {
        this.hooverLocation = hooverLocation;
    }
}
