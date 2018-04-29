package in.definex.Scheduler;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import in.definex.Database.Database;

import java.io.*;
import java.sql.*;


/**
 * Database class for Scheduler.
 * Saves the serialized schedule objects in database in name value hashes.
 */
public class ScheduleDatabase extends Database {


    private static final String tableName = "schedule";
    private static final String name = "name";
    private static final String scheduleObj = "schedule_obj";

    private static final String createTable =
            String.format("CREATE TABLE %s (%s VARCHAR(30), %s BLOB)",
                    tableName, name, scheduleObj);
    private static final String dropTable =
            "DROP TABLE "+tableName;

    private static final String insert =
            "INSERT INTO "+tableName+" VALUES (?,?)";

    //private static final String selectByName =
    //       String.format("SELECT * FROM %s WHERE %s = ?", tableName, name);

    private static final String deleteByName =
            String.format("DELETE FROM %s WHERE %s = ?", tableName, name);

    private static final String selectAll =
            "SELECT * FROM "+tableName;

    private static final String updateByName =
            String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, scheduleObj, name);



    public ScheduleDatabase() {
        super(1, tableName);

        addVersionChanger(new VersionChanger(1) {
            @Override
            public void upTo(Connection conn) throws SQLException {
                conn.createStatement().execute(createTable);
            }

            @Override
            public void downFrom(Connection conn) throws SQLException {
                conn.createStatement().execute(dropTable);
            }
        });
    }


    static void SaveSchedule(String name, Schedule schedule){

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(insert)){

            pstmt.setString(1, name);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            /*
            Kryo kryo = new Kryo();
            Output output = new Output(stream);
            kryo.writeClassAndObject(output, schedule);
            */

            ObjectOutputStream out = null;
            out = new ObjectOutputStream(stream);
            out.writeObject(schedule);

            byte[] bytes = null;
            bytes = stream.toByteArray();

            //out.close();

            //Blob x = new SerialBlob(bytes);

            pstmt.setBytes(2, bytes);

            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    static void DeleteSchedule(String name)
    {
        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(deleteByName)){

            pstmt.setString(1, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static BiMap<String,Schedule> GetSchedules(){

        BiMap<String,Schedule> scheduleBiMap = HashBiMap.create();

        try(Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(selectAll)){

            while(rs.next()){
                String myName = rs.getString(name);
                byte[] bytes = rs.getBytes(scheduleObj);

                ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
                ObjectInputStream in = null;
                in = new ObjectInputStream(stream);
                Schedule schedule = (Schedule)in.readObject();


/*
                byte[] bytes = null;
                bytes = stream.toByteArray();



                Kryo kryo = new Kryo();
                Input input = new Input(new ByteArrayInputStream(bytes));

                Schedule schedule = (Schedule) kryo.readClassAndObject(input);
*/
                scheduleBiMap.put(myName, schedule);

            }

        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return scheduleBiMap;

    }

    static void UpdateSchedule(String name, Schedule schedule){

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(updateByName)){

            pstmt.setObject(1, schedule);
            pstmt.setString(2, name);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
