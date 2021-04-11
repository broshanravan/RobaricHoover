package data;

import beans.Coords;
import beans.Room;
import org.apache.log4j.Logger;
import java.sql.*;

/**
 * this class uses the java library SQLight
 * to save details of
 * the cleaning Operation
 * The database for SQLLight will be created in a file but
 * operated as a Relational SQL database
 */

public class CleaningOperationInventoryImpl implements CleaningOperationInventory{

    static Logger logger = Logger.getLogger(CleaningOperationInventoryImpl.class.getName());

    private static Connection con;
    private boolean hasData = false;

    SystemAndNetworkProperties systemAndNetworkProperties = SystemAndNetworkProperties.getInstance();

    /**
     * the function will persist
     * the details of navigation for
     * cleaning a dirt patch
     * in the room
     * @param room
     * @param NumberOfTilesCleaned
     */
    public void persistOperation(Coords finalHooverLocation, Room room, int NumberOfTilesCleaned, String resultJSON){

        if (!isConnectionValid()){
            getConnection();
        }
        try {

            String saveQuery ="Insert into CLEANING_OPERATION_RESULTS" +
                    " HOOVER_INITIAL_LAT, " +
                    " HOOVER_INITIAL_LONG," +
                    " HOOVER_FINIAL_LAT," +
                    " HOOVER_FINIAL_LONG," +
                    " ROOM_WIDTH," +
                    " ROOM_LENGTH,"+
                    " NUM_TILES_CLEANED," +
                    " RESULT_JSON" +
                    " values(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(saveQuery);

            preparedStatement.setInt(1, room.getHooverLocation().getPositionX());
            preparedStatement.setInt(2, room.getHooverLocation().getPositionY() );
            preparedStatement.setInt(3, finalHooverLocation.getPositionY() );
            preparedStatement.setInt(4, finalHooverLocation.getPositionX());
            preparedStatement.setInt(5, room.getWidth());
            preparedStatement.setInt(6, room.getLength() );
            preparedStatement.setInt(7, NumberOfTilesCleaned);
            preparedStatement.setString(8, resultJSON );

            preparedStatement.execute();

        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        } finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }


    }

    /**
     * creates connection to the local
     * database
     */
    private void getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:" + systemAndNetworkProperties.getDBFileLocation());
            initialize();
        }catch(ClassNotFoundException cnfx){
            cnfx.printStackTrace();
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

    }

    private boolean isConnectionValid() {

        if (con == null) {
            return false;
        } else {
            try {
                if (con.isClosed()) {
                    return false;
                }
            } catch (SQLException sqle) {
                return false;
            }
        }
        return true;
    }

    private void initialize(){

        if (con == null){
            getConnection();
        }
        if(!hasData){

            System.out.println("First time initialization of DB");
            try {
                String customerInitQuery = "SELECT name FROM sqlite_master WHERE type ='table' and NAME = 'CLEANING_OPERATION_RESULTS'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(customerInitQuery);
                if (!rs.next()) {
                    String createCustomerTableSQL = "CREATE TABLE CLEANING_OPERATION(" +
                            " OPERATION_ID INTEGER," +
                            " HOOVER_INITIAL_LAT INTEGER," +
                            " HOOVER_INITIAL_LONG INTEGER," +
                            " HOOVER_FINIAL_LAT INTEGER," +
                            " HOOVER_FINIAL_LONG INTEGER," +
                            " ROOM_WIDTH INTEGER," +
                            " ROOM_LENGTH INTEGER,"+
                            " NUM_TILES_CLEANED INTEGER," +
                            " RESULT_JSON Varchar2(20)" +
                            " PRIMARY KEY (OPERATION_ID)" +
                            ");";
                    Statement statement_2 = con.createStatement();
                    statement_2.execute(createCustomerTableSQL);

                    hasData = true;
                }

            }catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }

    }


}
