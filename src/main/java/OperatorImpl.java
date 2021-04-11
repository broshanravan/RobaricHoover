import beans.*;
import com.google.gson.Gson;
import driving.Navigation;
import driving.NavigationImpl;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class OperatorImpl implements Operator{


    final static Logger logger = Logger.getLogger(OperatorImpl.class.getName());


    Navigation navigation = new NavigationImpl();
    private String instruction;
    Gson gson = new Gson();
    Room room = null;


    /**
     * taking  room details containing
     * Room dimensions, hoover original
     * position, dirty patches location,
     * plus the moving directions of the
     * hoover, it produces a JSON
     * containing cleanup results
     * @param finalPositionCoordinates
     * @param numberOfPatchesCleaned
     * @return
     */
    public String getJSONCleaningResult(String[] finalPositionCoordinates, int numberOfPatchesCleaned){
        OutputToJSON result = new OutputToJSON(finalPositionCoordinates, numberOfPatchesCleaned);

        String finialResultJSON = gson.toJson(result);
         return finialResultJSON;
    }


    /**
     * takes JSON string as an input and
     * created Java objjects and variables to be
     * used for running the cleaning instructions
     * @param inputJSON
     * @return
     */
    public Room convertInputJSONToData(String inputJSON){

        InputFromJSON inputFromJson = gson.fromJson(inputJSON,InputFromJSON.class);

        String coords[] = inputFromJson.getCoords();
        String[][] dirtPatches = inputFromJson.getPatches();
        String[] dimensions = inputFromJson.getRoomSize();
        Room room = null;

        Coordinates finalPosition = null;
        int NumberOfPatchesCleaned = 0;

        try {

            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);

            int positionX = Integer.parseInt(coords[0]);
            int positionY = Integer.parseInt(coords[1]);

            Coordinates hooverLocation = new Coordinates(positionX, positionY);

            List<Coordinates> dirtPatchLocationsList = new LinkedList<>();

            for (String[] patch : dirtPatches) {
                Coordinates coordinate = new Coordinates(Integer.parseInt(patch[0]), Integer.parseInt(patch[1]));
                dirtPatchLocationsList.add(coordinate);

            }

            room = new Room(length,  width, dirtPatchLocationsList , hooverLocation);
            instruction = inputFromJson.getInstructions();



        }catch(NumberFormatException nfe){
            logger.error("Wrong number format in input JSON");
        }


        return room;

    }

    public String getInstruction(){
        return instruction;
    }

    public static void main(String[] args){

    }
}
