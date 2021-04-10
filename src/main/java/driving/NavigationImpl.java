package driving;

import beans.Coordinate;

public class NavigationImpl implements Navigation{

    /**
     * To get exact nubmer of grids
     * always round the double number
     * to its higher boundary
     * @param hooverLocation
     * @param dirtLocation
     * @param roomLenght
     * @param roomWidth
     * @return
     */
    public double gotToCollectDirt(Coordinate hooverLocation, Coordinate dirtLocation , int roomLenght, int roomWidth) {

        double numberOfPatches = getNumberOfPatches(hooverLocation,  dirtLocation);
        double roundedNumberOfPatches  = Math.round(numberOfPatches);

        if (roundedNumberOfPatches<numberOfPatches){
            roundedNumberOfPatches += 1;
        }

        return roundedNumberOfPatches;

    }

    /**
     * It will be presumed that the hover will move through an straight line towards the dirt
     * hence the distance will be the hypatenus of a right angle triangle  for which
     * the other two sides would be the difference between longitudes of  dirt and hoover
     * and the latitudes of them
     * @param hooverLocation
     * @param dirtLocation
     * @return
     */
    private double getNumberOfPatches(Coordinate hooverLocation, Coordinate dirtLocation){
        int height = hooverLocation.getPositionY() - dirtLocation.getPositionY();
        int width = hooverLocation.getPisitionX() - dirtLocation.getPisitionX();

        double distance  = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));

        return distance;

    }
}
