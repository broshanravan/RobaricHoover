package beans;

public class InputFromJSON {
    private String[] roomSize;
    private String[] coords;
    private String[][] patches;
    private String instructions;

    public InputFromJSON(){

    }

    public InputFromJSON(String[] roomSize, String[] coords, String[][] patches,String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    public String[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String[] roomSize) {
        this.roomSize = roomSize;
    }

    public String[] getCoords() {
        return coords;
    }

    public void setCoords(String[] coords) {
        this.coords = coords;
    }

    public String[][] getPatches() {
        return patches;
    }

    public void setPatches(String[][] patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
