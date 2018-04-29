package in.definex.Database;

import in.definex.Bot;
import in.definex.Console.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * DatabaseManager
 *
 * controls the version of the different tables in the database
 * a table in mysqlite is abstracted into Database class and is called a database here
 */
public class DatabaseManager {

    /**
     * List of databases in the bot
     */
    private List<Database> databases;

    /**
     * Format of database name in configuration file
     */
    private static final String configVersion = "db-%s-version";

    /**
     * Constructor
     */
    public DatabaseManager(){
        databases = new ArrayList<>();
    }

    /**
     * Add a database to the manager
     * @param databases databases to add
     */
    public void add(Database... databases){
        this.databases.addAll(Arrays.asList(databases));
    }

    /**
     * Get a database with table name
     * @param name name of the database
     * @return databse, null if not found
     */
    public Database getDatabaseByName(String name){
        for(Database d:databases){
            if(name.equals(d.getName())){
                return d;
            }
        }
        return null;
    }

    /**
     * called to initialize the database which changes the version,
     * called at the end of init method in Looper class
     */
    public void init(){
        //check for version of database in configuration
        //create/update table and update the version in configuration
        for(Database database:databases){

            int currentVersion = Bot.getConfiguration().GetConfig(GetConfigName(database.getName()), 0);

            if(currentVersion != database.getVersion()){
                changeVersion(database, currentVersion);
            }

            Bot.getConfiguration().SaveConfig(GetConfigName(database.getName()), database.getVersion());
        }
    }

    /**
     * Recursive method used by init()
     *
     * @param database database who's version is to be changed
     * @param currentVersion current version of the database
     */
    private void changeVersion(Database database, int currentVersion){

        int targetVersion = database.getVersion();

        if(currentVersion < targetVersion){
            try {
                database.getVersionChanger(currentVersion+1).upTo(Database.connect());
            } catch (SQLException e) {
                Log.e(String.format("Error while upgrading database %s, from v%d to v%d", database.getName(), currentVersion, currentVersion+1));
                Log.p(e);
            } catch (NullPointerException e){
                Log.e(String.format("Could not find a version changer of %s from v%d to v%d", database.getName(), currentVersion, currentVersion+1));
            }
            Log.s(String.format("Upgraded database %s from v%d to v%d", database.getName(), currentVersion, currentVersion+1));
            changeVersion(database, ++currentVersion);
        }
        else if(currentVersion > targetVersion){
            try {
                database.getVersionChanger(currentVersion).downFrom(Database.connect());
            } catch (SQLException e) {
                Log.e("Error while downgrading database, "+database.getName()+" from v"+currentVersion+" to v"+(currentVersion-1));
                Log.p(e);
            }catch (NullPointerException e){
                Log.e(String.format("Could not find a version changer of %s from v%d to v%d", database.getName(), currentVersion, currentVersion-1));
            }
            changeVersion(database, --currentVersion);
        }

    }

    /**
     * Used to get the name format for the configuration
     * @param name name of the database
     * @return name in the configuration
     */
    private static String GetConfigName(String name){
        return String.format(configVersion, name);
    }

}
