package in.definex.core;

import java.util.List;

/**
 * Created by adam_ on 30-11-2017.
 */
public class Client {

    private String name;
    private String groupId;
    private Role role;

    private ChatGroup chatGroupCache;

    public Client(String name, String groupId, Role role) {
        this.name = name;
        this.groupId = groupId;
        this.role = role;
    }

    public static Client createTempAccount(String name, String groupId){
        return new Client(name, groupId, Role.Unregistered);
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

    public static Client getClient(String name, String groupUID){
        return DatabaseManager.getClient(name, groupUID);
    }

    public boolean saveToDatabase(){

        if(getClient(name, groupId) == null) {
            DatabaseManager.saveClient(this);
            return true;
        }

        return false;

    }

    public ChatGroup getChatGroup(ChatGroupsManager chatGroupsManager){
        if(chatGroupCache == null)
            chatGroupCache = chatGroupsManager.findGroupById(groupId);

        return chatGroupCache;
    }

    public void changeRole(Role role){
        this.role = role;
        DatabaseManager.updateRole(this);
    }

    public static List<Client> getClientsWithRole(String groupId, Role role){
        return DatabaseManager.getClientWithRole(groupId, role);
    }

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
