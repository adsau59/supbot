package in.definex.ChatSystem;

import in.definex.Bot;
import in.definex.Database.Core.ClientDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * Client Class
 * unique identification of each person in each group
 *
 * Created by adam_ on 30-11-2017.
 */
public class Client {

    private String name;
    private String alias;
    private String groupId;
    private Role role;

    private ChatGroup chatGroupCache;


    /***
     * Constructor
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupId id of the group in which the client is
     * @param role role of the client
     * @param alias alias name of client
     */
    public Client(String name, String alias, String groupId, Role role) {
        this.name = name;
        this.groupId = groupId;
        this.role = role;
        this.alias = alias;
    }

    /**
     * Constructor
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param chatGroup chatgroup object of which client belongs to
     * @param role role of the client
     */
    public Client(String name, ChatGroup chatGroup, Role role){
        this.name = name;
        this.role = role;
        this.alias = "";

        this.chatGroupCache = chatGroup;
        this.groupId = chatGroup.getGroupId();
    }

    /**
     * Getters
     */
    public String getName() {
        return name;
    }
    public String getGroupId() {
        return groupId;
    }
    public Role getRole() {
        return role;
    }

    public String getAlias() {
        return alias;
    }

    public String getFaceName()
    {
        if(alias.isEmpty())
            return name;

        return alias;
    }
    /**
     * Setters
     */
    public void setAlias(String alias) {
        this.alias = alias;
        ClientDatabase.updateAlias(this);
    }


    /***
     * Creates a temporary client for clients who havent registered
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupId id of the group in which the client is
     * @return Client object
     */
    public static Client createTempAccount(String name, String groupId){
        return new Client(name, "", groupId, Role.Unregistered);
    }

    /**
     * Return super client which have the highest role super admin.
     *
     * @param groupId group id
     * @return client object of super client
     */
    public static Client getSuperClient(String groupId){
        return new Client("ZenoSama", "", groupId, Role.SuperAdmin);
    }

    /***
     * Retrieves a client from the database
     * returns a temp account if client is not found
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupUID id of the group in which the client is
     * @return Client object
     */
    public static Client getClient(String name, String groupUID){

        if(name == null)
            return createTempAccount("unknown", groupUID);

        Client result = ClientDatabase.getClient(name, groupUID);

        if(result == null){
            result = Client.createTempAccount(name, groupUID);
        }

        return result;
    }


    /***
     * If client doesn't exist int the database, saves it
     * @return true if client gets saved in the database
     */
    public boolean saveToDatabase(){

        if(ClientDatabase.getClient(name,groupId) == null) {
            ClientDatabase.saveClient(this);
            return true;
        }

        return false;

    }

    /**
     * caches chatgroup of the client and returns it
     *
     * @return chatgroup object of the client
     */
    public ChatGroup getChatGroup(){
        if(chatGroupCache == null)
            chatGroupCache = Bot.getChatGroupsManager().findGroupById(groupId);

        return chatGroupCache;
    }

    /**
     * Changes the role of the client and saves it into database
     * @param role target role
     */
    public void changeRole(Role role){
        this.role = role;
        ClientDatabase.updateRole(this);
    }

    /**
     * Get all the clients with role from the damage
     *
     * @param groupId id of the target group
     * @param role role of the clients to be retried
     * @return List of clients
     */
    public static List<Client> getClientsWithRole(String groupId, Role role){
        return ClientDatabase.getClientWithRole(groupId, role);
    }

    /**
     * Finds the client with given alias from database.
     *
     * @param alias alias name
     * @return client object
     */
    public static Client GetClientWithAlias(String alias)
    {
        return ClientDatabase.getClientWithAlias(alias);
    }


    /**
     * Used in StringActionInitializer to convert string query into client.
     * passed in string in format, a-b, where a is client name, and b is group id
     *
     * @param s client string query
     * @return client object
     */
    public static Client castFromString(String s){
        String[] args = s.split("-");

        return getClient(args[0],args[1]);
    }

    /**
     * Role Enum
     */
    public enum Role{
        SuperAdmin(0),
        Admin(1),
        CoAdmin(2),
        Elder(3),
        Member(4),
        Unregistered(5);

        private Integer prestige;

        Role(int prestige){
            this.prestige = prestige;
        }

        public boolean hasPermission(Role minRole){
            return this.prestige <= minRole.prestige;
        }
        public int getPrestige(){
            return prestige;
        }

        public static Role prestigeToRole(int prestige){
            switch (prestige){
                case 0:
                    return SuperAdmin;
                case 1:
                    return Admin;
                case 2:
                    return  CoAdmin;
                case 3:
                    return Elder;
                case 4:
                    return Member;
            }

            return Unregistered;
        }
    }

}
