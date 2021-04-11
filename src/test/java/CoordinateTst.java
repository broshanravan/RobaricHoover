import beans.Coordinates;
import org.junit.Test;

public class CoordinateTst {

    @Test
    public void testConstructor(){
        Coordinates coordinates = new Coordinates(12,2);
        assert (coordinates.getPositionX() == 12);
        assert (coordinates.getPositionY() == 2);
    }
}
