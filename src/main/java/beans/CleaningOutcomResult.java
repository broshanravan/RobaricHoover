package beans;

public class CleaningOutcomResult {
    private Coordinates finalLocation;
    private int patches;

    public CleaningOutcomResult(Coordinates finalLocation, int numberOfPatchesCovered) {
        this.finalLocation = finalLocation;
        this.patches = numberOfPatchesCovered;
    }

    public Coordinates getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(Coordinates finalLocation) {
        this.finalLocation = finalLocation;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
