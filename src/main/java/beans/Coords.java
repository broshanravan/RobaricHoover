package beans;

public class Coords {
    private int positionX = 0;
    private int positionY = 0;

    public Coords(int pisitionX, int positionY) {
        this.positionX = pisitionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }


}
