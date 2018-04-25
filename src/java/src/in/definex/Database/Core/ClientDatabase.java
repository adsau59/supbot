package in.definex.Database.Core;

import in.definex.ChatSystem.Client;
import in.definex.Console.Log;
import in.definex.Database.Database;
import in.definex.String.DatabaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDatabase extends Database {
    public ClientDatabase() {
        super(1, "client");

        addVersionChanger(new VersionChanger(1) {
            @Override
            public void upTo(Connection conn) throws SQLException {
                conn.createStatement().execute(DatabaseQueries.clientCreateTable);
            }

            @Override
            public void downFrom(Connection conn) throws SQLException {
                conn.createStatement().execute(DatabaseQueries.dropClient);
            }
        });

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

            if (!rs.isBeforeFirst() ) {
                Log.d("Testing", "client not found");
                return null;
            }

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

}
