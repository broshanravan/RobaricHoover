package beans;

public class CleaningOutcomResult {
    private Coords finalLocation;
    private int patches;

    public CleaningOutcomResult(Coords finalLocation, int numberOfPatchesCovered) {
        this.finalLocation = finalLocation;
        this.patches = numberOfPatchesCovered;
    }

    public Coords getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(Coords finalLocation) {
        this.finalLocation = finalLocation;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
