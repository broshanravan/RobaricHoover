package driving;

import beans.Coords;
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
    public Coords getNextPosition(char moveDirection , Room room){

        Coords startPosition = room.getHooverLocation();

        int longitude =startPosition.getPositionX();
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

        Coords newLocation = new Coords(longitude,latitude) ;

        return newLocation;


    }

    public int getDirtPatcherCovered(String instructions , Room room){
        int patchesCovered = 0;
        Coords startPosition = room.getHooverLocation();

        int longitude =startPosition.getPositionX();
        int latitude = startPosition.getPositionY();

        char instructionSteps[] = instructions.toCharArray();

        for(int i = 0 ; i< instructionSteps.length ; i++){
            Coords newLocation  = getNextPosition(instructionSteps[i], room);
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
    public Coords getFinalPosition(String instructions, Room room){

        Coords startPosition = room.getHooverLocation();

        int longitude =startPosition.getPositionX();
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

        Coords finalPosition = new Coords(longitude,latitude) ;


        return finalPosition;


    }


    /**
     * To get the number of  dirty
     * tiles being covered and cleaned
     * @param instructions
     * @param room
     * @return
     */
    public int getNumberOfDirtPatchesCovered(String instructions , Room room) {

        List<Coords> dirtPatchLocations = room.getDirtLocationsList();
        List<Coords> cleanedPatches = room.getDirtLocationsList();
        int numberOfDirtPatchesCovered = 0;
        Coords hooverLocation = room.getHooverLocation();

        if(!this.isCoordinateValid(hooverLocation.getPositionX(), hooverLocation.getPositionY(), room, "Hoover")){
            logger.error("Invalid position for the Hoover");
        }else{
            Coords hooverNextPosition = null;
            char instructionSteps[] = instructions.toCharArray();

            /**
             * To check if the starting point of
             * hover is a dirty patch
             */
            if(alreadyCleaned( hooverLocation, cleanedPatches)){
                numberOfDirtPatchesCovered ++;
                cleanedPatches.add(hooverLocation);
                numberOfDirtPatchesCovered ++;
            }

            for(int i = 0 ; i< instructionSteps.length ; i++) {
                char moveDirection = instructionSteps[i];
                hooverNextPosition = getNextPosition(moveDirection , room);
                room.setHooverLocation(hooverNextPosition);

                for(Coords dirtyPatchCoordinate : dirtPatchLocations){
                    if(hooverNextPosition.getPositionX() == dirtyPatchCoordinate.getPositionX() &&
                            hooverNextPosition.getPositionY() == dirtyPatchCoordinate.getPositionY() &&
                            !alreadyCleaned(dirtyPatchCoordinate, cleanedPatches)

                    ){
                        numberOfDirtPatchesCovered ++;
                        cleanedPatches.add(dirtyPatchCoordinate);

                    }

                }

            }

        }
        return numberOfDirtPatchesCovered;

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

    /**
     * to make sure the dirty patch which the  hoover
     * passed over will be flagged as clean.
     * So that in case it passes over the same patch again
     * the number of cleaned patches would not increase
     * @param coordinate
     * @param cleanedPatches
     * @return
     */
    public boolean alreadyCleaned(Coords coordinate, List<Coords> cleanedPatches){
        boolean alreadyCleaned = false;
        for(Coords cleanedCoordinate : cleanedPatches){
            if(cleanedCoordinate.getPositionX() == coordinate.getPositionX() &&
            cleanedCoordinate.getPositionY() == coordinate.getPositionY()){
                alreadyCleaned = true;
            } else{
                cleanedPatches.add(coordinate);
            }
        }
        return alreadyCleaned;
    }


}
