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

    public static final String clientTable = "client";
    public static final String clientName = "name";
    public static final String clientGUID = "groupUID";
    public static final String clientRole = "role";
    public static final String clientCreateTable =
            "CREATE TABLE "+clientTable+" ("+clientName+" VARCHAR(30), "+clientGUID+" VARCHAR(30), "+clientRole+" INT)";

    public static final String clientInsert =
            "INSERT INTO "+clientTable+" VALUES (?,?,?)";

    public static final String clientSelectWhere =
            "SELECT * FROM "+clientTable+" WHERE "+clientName+" = ? AND "+clientGUID+" = ?";

    public static final String clientRoleUpdate =
            "UPDATE "+clientTable+" SET "+clientRole+" = ? WHERE "+clientName+" = ? AND "+clientGUID+" = ?";

    public static final String clientGetWithRole =
            "SELECT * FROM "+clientTable+" WHERE "+clientRole+" = ? AND "+clientGUID+" = ?";

    public static final String dropClient = "DROP TABLE "+clientTable;

    //v2

    public static final String alias = "name_alias";

    public static final String addAlias =
            String.format("ALTER TABLE %s ADD COLUMN %s VARCHAR(30)", clientTable, alias);

    public static final String removeAlias =
            String.format("ALTER TABLE %s DROP COLUMN %s",clientTable,alias);

    public static final String updateAliasQuery =
            "UPDATE "+clientTable+" SET "+alias+" = ? WHERE "+clientName+" = ? AND "+clientGUID+" = ?";

    public static final String getAllClientsInGroup =
            String.format("SELECT * FROM %s WHERE %s = ?", clientTable, clientGUID);

    public static final String selectClientWithAlias  =
            String.format("SELECT * FROM %s WHERE %s = ?", clientTable, alias);

    public ClientDatabase() {
        super(2, "client");

        addVersionChanger(new VersionChanger(1) {
            @Override
            public void upTo(Connection conn) throws SQLException {
                conn.createStatement().execute(clientCreateTable);
            }

            @Override
            public void downFrom(Connection conn) throws SQLException {
                conn.createStatement().execute(dropClient);
            }
        });

        addVersionChanger(new VersionChanger(2) {
            @Override
            public void upTo(Connection conn) throws SQLException {
                conn.createStatement().execute(addAlias);
            }

            @Override
            public void downFrom(Connection conn) throws SQLException {
                conn.createStatement().execute(removeAlias);
            }
        });

    }




    public static void saveClient(Client client){

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(clientInsert)){

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
            PreparedStatement pstmt = con.prepareStatement(clientSelectWhere)) {

            pstmt.setString(1, name);
            pstmt.setString(2, guid);
            ResultSet rs  = pstmt.executeQuery();

            if (!rs.isBeforeFirst() ) {
                Log.d("Testing", "client not found");
                return null;
            }

            return new Client(
                    rs.getString(clientName),
                    rs.getString(alias),
                    rs.getString(clientGUID),
                    Client.Role.prestigeToRole(rs.getInt(clientRole)
                    ));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Client getClientWithAlias(String alias){

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(selectClientWithAlias)) {

            pstmt.setString(1, alias);
            ResultSet rs  = pstmt.executeQuery();

            if (!rs.isBeforeFirst() ) {
                return null;
            }

            return new Client(
                    rs.getString(clientName),
                    rs.getString(alias),
                    rs.getString(clientGUID),
                    Client.Role.prestigeToRole(rs.getInt(clientRole)
                    ));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static List<Client> getClientWithRole(String guid, Client.Role role){

        List<Client> clients = new ArrayList<>();

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(clientSelectWhere)) {

            pstmt.setInt(1, role.getPrestige());
            pstmt.setString(2, guid);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()){
                clients.add(new Client(
                        rs.getString(clientName),
                        rs.getString(alias),
                        rs.getString(clientGUID),
                        Client.Role.prestigeToRole(rs.getInt(clientRole)
                        )));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;

    }

    public static List<Client> getClientsInGroup(String guid)
    {
        List<Client> clients = new ArrayList<>();

        try(Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(getAllClientsInGroup)) {

            pstmt.setString(1, guid);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()){
                clients.add(new Client(
                        rs.getString(clientName),
                        rs.getString(alias),
                        rs.getString(clientGUID),
                        Client.Role.prestigeToRole(rs.getInt(clientRole)
                        )));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public static void updateRole(Client client){

        try(Connection conn = connect();
            PreparedStatement pstm = conn.prepareStatement(clientRoleUpdate)){

            pstm.setInt(1, client.getRole().getPrestige());
            pstm.setString(2, client.getName());
            pstm.setString(3, client.getGroupId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateAlias(Client client){

        try(Connection conn = connect();
            PreparedStatement pstm = conn.prepareStatement(updateAliasQuery)){

            pstm.setString(1, client.getAlias());
            pstm.setString(2, client.getName());
            pstm.setString(3, client.getGroupId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
