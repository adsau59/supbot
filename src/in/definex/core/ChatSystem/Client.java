package in.definex.core.ChatSystem;

import in.definex.core.Bot;
import in.definex.core.Database.Core.ClientDatabase;

import java.util.List;

/**
 * Client Class
 * unique identification of each person in each group
 *
 * Created by adam_ on 30-11-2017.
 */
public class Client {

    private String name;
    private String groupId;
    private Role role;

    private ChatGroup chatGroupCache;


    /***
     * Constructor
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupId id of the group in which the client is
     * @param role role of the client
     */
    public Client(String name, String groupId, Role role) {
        this.name = name;
        this.groupId = groupId;
        this.role = role;
    }

    /***
     * Creates a temporary client for clients who havent registered
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupId id of the group in which the client is
     * @return Client object
     */
    public static Client createTempAccount(String name, String groupId){
        return new Client(name, groupId, Role.Unregistered);
    }

    public static Client getSuperClient(String groupId){
        return new Client("ZenoSama", groupId, Role.SuperAdmin);
    }

    public String getName() {
        return name;
    }
    public String getGroupId() {
        return groupId;
    }
    public Role getRole() {
        return role;
    }

    /***
     * Retrieves a client from the database
     *
     * @param name name of the client in chat (can be phonenumber of saved name)
     * @param groupUID id of the group in which the client is
     * @return Client object
     */
    public static Client getClient(String name, String groupUID){

        if(name == null)
            return createTempAccount("unknown", groupUID);

        return ClientDatabase.getClient(name, groupUID);
    }

    /***
     * If client doesn't exist int the database, saves it
     * @return true if client gets saved in the database
     */
    public boolean saveToDatabase(){

        if(getClient(name, groupId) == null) {
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
     * Get all the clients with role from the datase
     *
     * @param groupId id of the target group
     * @param role role of the clients to be retrived
     * @return List of clients
     */
    public static List<Client> getClientsWithRole(String groupId, Role role){
        return ClientDatabase.getClientWithRole(groupId, role);
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
