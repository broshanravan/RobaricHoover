package beans;

public class Coordinate {
    private int pisitionX = 0;
    private int positionY = 0;

    public Coordinate(int pisitionX, int positionY) {
        this.pisitionX = pisitionX;
        this.positionY = positionY;
    }

    public int getPisitionX() {
        return pisitionX;
    }

    public void setPisitionX(int pisitionX) {
        this.pisitionX = pisitionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }


}
