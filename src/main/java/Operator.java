import beans.Coords;
import beans.CleaningOutcomResult;
import beans.Room;
import com.google.gson.Gson;
import driving.Navigation;
import driving.NavigationImpl;

public class Operator {


    Navigation navigation = new NavigationImpl();
    Gson gson = new Gson();

    /**
     * taking  room details containing
     * Room dimensions, hoover original
     * position, dirty patches location,
     * plus the moving directions of the
     * hoover, it produces a JSON
     * containing cleanup results
     * @param instructions
     * @param room
     * @return
     */
    public String getJSONCleaningResult(String instructions, Room room){
        Coords finalPosition = navigation.getFinalPosition(instructions, room);
        int NumberOfPatchesCleaned = navigation.getNumberOfDirtPatchesCovered(instructions, room);
        CleaningOutcomResult result = new CleaningOutcomResult(finalPosition, NumberOfPatchesCleaned);

        String finialResultJSON = gson.toJson(result);
         return finialResultJSON;
    }


    public static void main(String[] args){

    }
}
