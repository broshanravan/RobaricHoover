package data;

import beans.Room;

import java.sql.*;

/**
 * this class uses the java library SQLight
 * to save details of
 * the cleaning Operation
 * The database for SQLLight will be created in a file but
 * operated as a Relational SQL database
 */

public class CleaningOperationInventoryImpl implements CleaningOperationInventory{

    private static Connection con;
    private boolean hasData = false;
    SystemAndNetworkProperties systemAndNetworkProperties = SystemAndNetworkProperties.getInstance();

    /**
     * the function will persist
     * the details of navigation for
     * cleaning a dirt patch
     * in the room
     * @param room
     * @param NumberOfTiles
     */
    public void persistOperation(Room room, int NumberOfTiles){


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
                String customerInitQuery = "SELECT name FROM sqlite_master WHERE type ='table' and NAME = 'CLEANING_OPERATION'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(customerInitQuery);
                if (!rs.next()) {
                    String createCustomerTableSQL = "CREATE TABLE CLEANING_OPERATION(" +
                            " OPERATION_ID INTEGER," +
                            " HOOVER_LAT INTEGER," +
                            " HOOVER_LONG INTEGER," +
                            " DIRT_LAT INTEGER," +
                            " DIRT_LONG INTEGER," +
                            " TILES_NUM_COVERED INTEGER," +
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
