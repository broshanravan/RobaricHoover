import beans.Coords;
import org.junit.Test;

public class CoordinateTst {

    @Test
    public void testConstructor(){
        Coords coordinates = new Coords(12,2);
        assert (coordinates.getPositionX() == 12);
        assert (coordinates.getPositionY() == 2);
    }
}
