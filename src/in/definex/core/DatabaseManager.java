package in.definex.core;


import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.DatabaseQueries;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam_ on 03-12-2017.
 */
public class DatabaseManager {

    //public int version = 1;

    private static Connection connect(){
        String url = "jdbc:sqlite:supbot.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createTable(){

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(DatabaseQueries.clientCreateTable);
            stmt.execute(DatabaseQueries.groupCreateTable);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void deleteClientTable(){
        try {
            connect().createStatement().execute(DatabaseQueries.dropClient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroupTable(){
        try {
            connect().createStatement().execute(DatabaseQueries.dropGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveClient(Client client){

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(DatabaseQueries.clientInsert)){

            pstmt.setString(1,client.getName());
            pstmt.setString(2,client.getGroupId());
            pstmt.setInt(3,client.getRole().getPrestige());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Client getClient(String name, String guid){

        try(Connection con = connect();
        PreparedStatement pstmt = con.prepareStatement(DatabaseQueries.clientSelectWhere)) {

            pstmt.setString(1, name);
            pstmt.setString(2, guid);
            ResultSet rs  = pstmt.executeQuery();

            return new Client(
                    rs.getString(DatabaseQueries.clientName),
                    rs.getString(DatabaseQueries.clientGUID),
                    Client.Role.prestigeToRole(rs.getInt(DatabaseQueries.clientRole)
                    ));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static List<Client> getClientWithRole(String guid, Client.Role role){

        List<Client> clients = new ArrayList<>();

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(DatabaseQueries.clientSelectWhere)) {

            pstmt.setInt(1, role.getPrestige());
            pstmt.setString(2, guid);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()){
                clients.add(new Client(
                        rs.getString(DatabaseQueries.clientName),
                        rs.getString(DatabaseQueries.clientGUID),
                        Client.Role.prestigeToRole(rs.getInt(DatabaseQueries.clientRole)
                        )));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;

    }

    public static void updateRole(Client client){

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(DatabaseQueries.clientRoleUpdate)){

            pstm.setInt(1, client.getRole().getPrestige());
            pstm.setString(2, client.getName());
            pstm.setString(3, client.getGroupId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String featuresToStringList(List<Feature> features){

        String featuresString = "";
        for(Feature feature: features){
            featuresString += feature.getName()+ Strings.dbListSeperator;
        }
        return featuresString.substring(0, featuresString.length()-1);

    }

    public static void saveGroup(ChatGroup group){

        String featuresString = featuresToStringList(group.getMyFeatures());

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(DatabaseQueries.groupInsert)) {

            pstm.setString(1, group.getGroupId());
            pstm.setString(2, featuresString);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<ChatGroup> getGroups(FeatureManager featureManager, WebDriver driver){

        List<ChatGroup> chatGroups = new ArrayList<>();

        try(Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(DatabaseQueries.groupSelectAll)){

            while(rs.next()){
                String[] featureNames = rs.getString(DatabaseQueries.groupMyFeature).split(Strings.dbListSeperator);

                List<Feature> features = new ArrayList<>();

                for(String name: featureNames){

                    Feature feature = featureManager.findFeatureByName(name);

                    if(feature!=null)
                        features.add(feature);

                }

                chatGroups.add(new ChatGroup(
                        rs.getString(DatabaseQueries.groupUID),
                        features,
                        driver

                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatGroups;

    }

    public static void updateFeatures(ChatGroup chatGroup){

        String featureString = featuresToStringList(chatGroup.getMyFeatures());

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(DatabaseQueries.groupFeatureUpdate)) {

            pstm.setString(1,featureString);
            pstm.setString(2,chatGroup.getGroupId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}