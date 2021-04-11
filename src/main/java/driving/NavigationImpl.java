package driving;

import beans.Coordinates;
import beans.Room;
import org.apache.log4j.Logger;

import java.util.LinkedList;
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
    public Coordinates getNextPosition(char moveDirection , Room room){

        Coordinates startPosition = room.getHooverLocation();

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

        Coordinates newLocation = new Coordinates(longitude,latitude) ;

        return newLocation;


    }

    public int getDirtPatcherCovered(String instructions , Room room){
        int patchesCovered = 0;
        Coordinates startPosition = room.getHooverLocation();

        int longitude =startPosition.getPositionX();
        int latitude = startPosition.getPositionY();

        char instructionSteps[] = instructions.toCharArray();

        for(int i = 0 ; i< instructionSteps.length ; i++){
            Coordinates newLocation  = getNextPosition(instructionSteps[i], room);
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
    public Coordinates getFinalPosition(String instructions, Room room){

        Coordinates startPosition = room.getHooverLocation();

        int longitude =startPosition.getPositionX();
        int latitude = startPosition.getPositionY();

        char instructionSteps[] = instructions.toCharArray();

        for(int i = 0 ; i< instructionSteps.length ; i++){
            char moveDirection = instructionSteps[i];
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
            }else if(moveDirection == 'E'){
                longitude += 1;
                if(longitude > room.getLength()){
                    longitude = room.getLength();
                }

            }else if(moveDirection == 'W'){
                longitude -= 1;
                if(longitude<0){
                    longitude = 0;
                }

            }

        }

        Coordinates finalPosition = new Coordinates(longitude,latitude) ;


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

        List<Coordinates> dirtPatchLocations = room.getDirtLocationsList();
        List<Coordinates> cleanedPatches = new LinkedList<Coordinates>();
        int numberOfDirtPatchesCovered = 0;
        Coordinates hooverLocation = room.getHooverLocation();

        if(!this.isCoordinateValid(hooverLocation.getPositionX(), hooverLocation.getPositionY(), room, "Hoover")){
            logger.error("Invalid position for the Hoover");
        }else{
            Coordinates hooverNextPosition = null;
            char instructionSteps[] = instructions.toCharArray();

            /**
             * To check if the starting point of
             * hover is a dirty patch
             */
            if(!alreadyCleaned(hooverLocation, cleanedPatches) && isPatchDirty(hooverLocation,room)){
                cleanedPatches.add(hooverLocation);
                numberOfDirtPatchesCovered ++;
            }

            for(int i = 0 ; i< instructionSteps.length ; i++) {
                char moveDirection = instructionSteps[i];
                hooverNextPosition = getNextPosition(moveDirection , room);
                room.setHooverLocation(hooverNextPosition);

                if((!alreadyCleaned(hooverLocation, cleanedPatches) && isPatchDirty(hooverLocation,room))){
                    numberOfDirtPatchesCovered ++;
                    cleanedPatches.add(hooverLocation);
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
    public boolean alreadyCleaned(Coordinates coordinate, List<Coordinates> cleanedPatches){
        boolean alreadyCleaned = false;
        for(Coordinates cleanedCoordinate : cleanedPatches){
            if(cleanedCoordinate.getPositionX() == coordinate.getPositionX() &&
            cleanedCoordinate.getPositionY() == coordinate.getPositionY()){
                alreadyCleaned = true;
            } else{
                cleanedPatches.add(coordinate);
            }
        }
        return alreadyCleaned;
    }


    private boolean isPatchDirty(Coordinates patchCoordinate, Room room){
        Boolean isPatchDirty = false;
        List<Coordinates> dirtyPatches = room.getDirtLocationsList();
        for (Coordinates dirtyPatchCoord: dirtyPatches){

            if(patchCoordinate.getPositionX() == dirtyPatchCoord.getPositionX() &&
                    patchCoordinate.getPositionY() == dirtyPatchCoord.getPositionY()
            ){
                isPatchDirty = true;
            }
        }
        return isPatchDirty;

    }


}
