package in.definex.core.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Database
 * Abstraction of tables in mysqlite
 * Used to control the version of the tables in mysqlite
 *
 * Static functions should be used to query sqlite.
 */
public abstract class Database {

    /**
     * Version to be used
     */
    private int version;
    /**
     * Name of the database (usually the table name)
     */
    private String name;

    /**
     * List of VersionChangers
     */
    private List<VersionChanger> versionChangers;

    /**
     * Constructor
     * @param version Version to be used
     * @param name Name of the database (usually the table name)
     */
    public Database(int version, String name){
        this.version = version;
        this.name = name;
        versionChangers = new ArrayList<>();
    }

    /**
     * Add new version changer.
     * @param versionChangers version changer to be added
     */
    public void addVersionChanger(VersionChanger... versionChangers){
        this.versionChangers.addAll(Arrays.asList(versionChangers));
    }

    /**
     * Get version changer with version
     * @param version version of the version changer
     * @return null if not found
     */
    VersionChanger getVersionChanger(int version){
        for(VersionChanger v: versionChangers){
            if(v.version == version)
                return v;
        }

        return null;
    }

    /**
     * Getters
     */
    int getVersion() {
        return version;
    }
    public String getName() {
        return name;
    }


    /**
     * VersionChanger
     * Used to change version of the Database.
     */
    public abstract class VersionChanger{
        public int version;

        /**
         * @param version Target version
         */
        public VersionChanger(int version){
            this.version = version;
        }

        /**
         * defined to upgrade the version up to the target version from version-1
         * for example, for v1, upto will create the table
         *
         * @param conn used to run sqlite queries
         * @throws SQLException
         */
        public abstract void upTo(Connection conn) throws SQLException;
        /**
         * defined to downgrade the version down to the target version-1 from version
         * for example, for v1, downFrom will drop the table
         *
         * @param conn used to run sqlite queries
         * @throws SQLException
         */
        public abstract void downFrom(Connection conn) throws SQLException;
    }

    /**
     * Used in static database functions, to get mysqlite connection
     * @return mysqlite connection
     */
    public static Connection connect(){
        String url = "jdbc:sqlite:supbot.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
