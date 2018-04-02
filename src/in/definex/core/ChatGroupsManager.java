package in.definex.core;

import in.definex.core.Feature.FeatureManager;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * ChatGroupsManager
 * Manages the chat groups.
 *
 * Created by adam_ on 30-11-2017.
 */
public class ChatGroupsManager {

    /**
     * List of chatgroups
     */
    private List<ChatGroup> chatGroupList;

    public ChatGroupsManager() {
    }

    /**
     * Finds a group with id
     * @param id id of the group
     * @return ChatGroup object, return null if not found
     */
    public ChatGroup findGroupById(String id){
        for(ChatGroup chatGroup: chatGroupList){
            if(chatGroup.getGroupId().equals(id)){
                return chatGroup;
            }
        }
        return null;
    }

    /**
     * Loads the Chat Groups from the database to chatGroupList List
     * @param featureManager Feature Manager
     * @param driver Selenium Webdriver
     */
    public void loadGroups(FeatureManager featureManager, WebDriver driver){
        this.chatGroupList = DatabaseManager.getGroups(featureManager, driver);
    }

    /**
     * adds chatgroup to chatgroup list and saves it into the database
     * @param chatGroups
     */
    public void add(ChatGroup chatGroups){
        chatGroupList.add(chatGroups);
        DatabaseManager.saveGroup(chatGroups);
    }

    /**
     * @return chatGroupList
     */
    public List<ChatGroup> getChatGroupList() {
        return chatGroupList;
    }
}
