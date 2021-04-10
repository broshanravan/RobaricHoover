package beans;

public class Room {
    private int length;
    private int width;
    private Coordinate dirtLocation;
    private Coordinate hooverLocation;

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

    public Coordinate getDirtLocation() {
        return dirtLocation;
    }

    public void setDirtLocation(Coordinate dirtLocation) {
        this.dirtLocation = dirtLocation;
    }

    public Coordinate getHooverLocation() {
        return hooverLocation;
    }

    public void setHooverLocation(Coordinate hooverLocation) {
        this.hooverLocation = hooverLocation;
    }
}
