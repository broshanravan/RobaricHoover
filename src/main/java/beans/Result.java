package beans;

public class Result {
    private Coordinate finalLocation;
    private int numberOfPatchesCovered;

    public Result(Coordinate finalLocation, int numberOfPatchesCovered) {
        this.finalLocation = finalLocation;
        this.numberOfPatchesCovered = numberOfPatchesCovered;
    }

    public Coordinate getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(Coordinate finalLocation) {
        this.finalLocation = finalLocation;
    }

    public int getNumberOfPatchesCovered() {
        return numberOfPatchesCovered;
    }

    public void setNumberOfPatchesCovered(int numberOfPatchesCovered) {
        this.numberOfPatchesCovered = numberOfPatchesCovered;
    }
}
