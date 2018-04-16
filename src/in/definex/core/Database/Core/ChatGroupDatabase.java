package in.definex.core.Database.Core;
import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.Database.Database;
import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.DatabaseQueries;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupDatabase extends Database {
    public ChatGroupDatabase() {
        super(1, "chat_group");

        addVersionChanger(new VersionChanger(1) {
            @Override
            public void upTo(Connection conn) throws SQLException {
                conn.createStatement().execute(DatabaseQueries.groupCreateTable);
            }

            @Override
            public void downFrom(Connection conn) throws SQLException {
                conn.createStatement().execute(DatabaseQueries.dropGroupTable);
            }
        });
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
                        features

                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatGroups;

    }

    public static void deleteChatGroup(String gid){


        try(Connection conn = connect();
            PreparedStatement pstm = conn.prepareStatement(DatabaseQueries.groupDelete)) {

            pstm.setString(1,gid);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
