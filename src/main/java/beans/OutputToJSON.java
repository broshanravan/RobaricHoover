package beans;

public class OutputToJSON {
    private String[] coords;
    private int patches;

    public OutputToJSON(){

    }

    public OutputToJSON(String[] coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public String[] getCoords() {
        return coords;
    }

    public void setCoords(String[] coords) {
        this.coords = coords;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
