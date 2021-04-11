import beans.Room;

public interface Operator {
    public String getJSONCleaningResult(String[] finalPositionCoordinates, int numberOfPatchesCleaned);
    public Room convertInputJSONToData(String inputJSON);
    public String getInstruction();
}
