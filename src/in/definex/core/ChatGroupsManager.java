package in.definex.core;

import in.definex.core.Feature.FeatureManager;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by adam_ on 30-11-2017.
 */
public class ChatGroupsManager {

    private List<ChatGroup> chatGroupList;

    public ChatGroupsManager() {
    }

    public ChatGroup findGroupById(String id){
        for(ChatGroup chatGroup: chatGroupList){
            if(chatGroup.getGroupId().equals(id)){
                return chatGroup;
            }
        }
        return null;
    }

    public void loadGroups(FeatureManager featureManager, WebDriver driver){
        this.chatGroupList = DatabaseManager.getGroups(featureManager, driver);
    }

    public void add(ChatGroup chatGroups){
        chatGroupList.add(chatGroups);
        DatabaseManager.saveGroup(chatGroups);
    }

    public List<ChatGroup> getChatGroupList() {
        return chatGroupList;
    }
}
