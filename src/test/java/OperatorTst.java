import beans.Coordinates;
import beans.Room;

import org.junit.Test;

import java.util.List;

public class OperatorTst {
    Operator operator = new OperatorImpl();

    @Test
    public void testReadingJsonToObject(){
        String jsonInputString = "{" +
                "  \"roomSize\" : [5, 5]," +
                "  \"coords\" : [1, 2]," +
                "  \"patches\" : [" +
                "    [1, 0]," +
                "    [2, 2]," +
                "    [2, 3]" +
                "  ]," +
                "  \"instructions\" : \"NNESEESWNWW\"" +
                "}";

        Room room = operator.convertInputJSONToData(jsonInputString);
        List<Coordinates> coordinatesList = room.getDirtLocationsList();
        Coordinates coordinates = coordinatesList.get(2);
        assert(coordinates.getPositionX() == 2);
        assert(coordinates.getPositionY() == 3);

        assert("NNESEESWNWW".equals(operator.getInstruction()));

        assert (room.getLength() == 5);

    }

    @Test
    public void testCreatingOutputJSON() {
        String[] finalCoordinates = {"1", "3"};
        int patchesCovered = 2;
        String SONCleaningResult = operator.getJSONCleaningResult(finalCoordinates, patchesCovered);

        assert (SONCleaningResult.equals("{\"coords\":[\"1\",\"3\"],\"patches\":2}"));
    }

}
