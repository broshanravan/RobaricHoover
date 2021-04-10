package driving;

import beans.Coordinate;
import beans.Room;
import org.apache.log4j.Logger;

import java.util.List;

public class NavigationImpl implements Navigation{

    static Logger logger = Logger.getLogger(NavigationImpl.class.getName());


    /**
     * this functions used the coordinates for
     * primary location of the hoover and
     * the collection of moves to get the final
     * location that it will end up
     * @param moveDirection
     * @param room
     * @return
     */
    public Coordinate getNextPosition(char moveDirection , Room room){

        Coordinate startPosition = room.getHooverLocation();

        int longitude =startPosition.getPisitionX();
        int latitude = startPosition.getPositionY();


        if(moveDirection =='N'){
            latitude += 1;
            if(latitude > room.getWidth()){
                latitude = room.getWidth();
            }
        }else if(moveDirection =='S'){
            latitude -= 1;
            if(latitude<0){
                latitude = 0;
            }
        }else if(moveDirection =='E'){
            longitude += 1;
            if(longitude > room.getLength()){
                longitude = room.getLength();
            }

        }else if(moveDirection =='W'){
            longitude -= 1;
            if(longitude<0){
                longitude = 0;
            }

        }

        Coordinate newLocation = new Coordinate(longitude,latitude) ;

        return newLocation;


    }

    public int getDirtPatcherCovered(String instructions , Room room){
        int patchesCovered = 0;
        Coordinate startPosition = room.getHooverLocation();

        int longitude =startPosition.getPisitionX();
        int latitude = startPosition.getPositionY();

        char instructionSteps[] = instructions.toCharArray();

        for(int i = 0 ; i< instructionSteps.length ; i++){
            Coordinate newLocation  = getNextPosition(instructionSteps[i], room);
            room.setHooverLocation(newLocation);

        }



        return patchesCovered;
    }
    /**
     * this functions used the coordinates for
     * primary location of the hoover and
     * the collection of moves to get the final
     * location that it will end up
     * @param instructions
     * @param room
     * @return
     */
    public Coordinate getFinalPosition(String instructions, Room room){

        Coordinate startPosition = room.getHooverLocation();

        int longitude =startPosition.getPisitionX();
        int latitude = startPosition.getPositionY();

        char instructionSteps[] = instructions.toCharArray();

        for(int i = 0 ; i< instructionSteps.length ; i++){
            char moveDirection = instructionSteps[i];
            if(i =='N'){
                latitude += 1;
                if(latitude > room.getWidth()){
                    latitude = room.getWidth();
                }
            }else if(i =='S'){
                latitude -= 1;
                if(latitude<0){
                    latitude = 0;
                }
            }else if(i == 'E'){
                longitude += 1;
                if(longitude > room.getLength()){
                    longitude = room.getLength();
                }

            }else if(i == 'W'){
                longitude -= 1;
                if(longitude<0){
                    longitude = 0;
                }

            }

        }

        Coordinate finalPosition = new Coordinate(longitude,latitude) ;


        return finalPosition;


    }


    /**
     * To get the number of  dirty
     * tiles being covered and cleaned
     * @param dirtPatchLocations
     * @param room
     * @return
     */
    public double getNumberOfDirtPatcherCovered( List<Coordinate> dirtPatchLocations , Room room) {

    double roundedNumberOfPatches = 0;
    Coordinate hooverLocation = room.getHooverLocation();

    if(!this.isCoordinateValid(hooverLocation.getPisitionX(), hooverLocation.getPositionY(), room, "Hoover")){
        logger.error("Invalid position for the Hoover");
    }else{
        for(Coordinate coordinate : dirtPatchLocations) {
            if (!this.isCoordinateValid(coordinate.getPisitionX(), coordinate.getPositionY(), room, "Dirt")) {
                logger.error("Invalid position for the dirt");
            } else {




            }
        }
    }

        return roundedNumberOfPatches;

    }


    /**
     * This functions checks if the position  for
     * hoover or dirt has valid coordinates.
     * i.e. is inside the room boundary
     * @param longitude
     * @param latitude
     * @param room
     * @param dirt_hoover
     * @return
     */
    public boolean isCoordinateValid(int longitude, int latitude, Room room, String dirt_hoover){

        boolean isValid = true;

        if(longitude> room.getLength() || latitude > room.getWidth()){
            System .err.println("Invalid coordinates for " + dirt_hoover);
            isValid =false;
        }

        return isValid;

    }
}
