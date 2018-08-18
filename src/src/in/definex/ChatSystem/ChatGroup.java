package in.definex.ChatSystem;

import in.definex.Bot;
import in.definex.Console.Log;
import in.definex.Database.Core.ChatGroupDatabase;
import in.definex.Database.Core.ClientDatabase;
import in.definex.Feature.Feature;
import in.definex.String.Strings;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatGroup
 * Chat group in whatsapp with a group id
 *
 * Created by adam_ on 30-11-2017.
 */
public class ChatGroup{

    private String groupId;
    private List<Feature> myFeatures;

    /**
     * Chat item list, contains chat sent in the group
     * it is compared in CheckInCurrentGroupAction with itself, to detect new chat
     */
    private List<ChatItem> chatItemList;
    //private WebElement chatWebElement; //div on which u click to open chat

    /**
     * Constructor
     *
     * @param groupId id of the group
     */
    public ChatGroup(String groupId, List<Feature> myFeatures) {
        this.chatItemList = new ArrayList<>();
        this.groupId = groupId;
/*
        try {
            this.chatWebElement = Bot.getWebDriver().findElement(By.xpath(XPaths.getGroupNameXPath(groupId)));
        }catch (NoSuchElementException e){
            Log.e(String.format("Group with id: %s not found, please make sure you have a group with %s%s in the end of the group title,",
                    groupId, Strings.commandPrefix, groupId));

            Log.p(e);
        }
*/
        this.myFeatures = myFeatures;
    }

    /**
     * Constructor
     * ChatGroup with no features
     *
     * @param groupId id of the group
     */
    public ChatGroup(String groupId){
        this(groupId, new ArrayList<>());
    }

    /**
     * Add chatItem to chatItem list
     * @param chatItem new chat item to be added
     */
    public void addChatItem(ChatItem chatItem){
        chatItemList.add(chatItem);
    }

    /**
     * chatItemList getter
     * @return chatItemList
     */
    public List<ChatItem> getChatItemList() {
        return chatItemList;
    }

    /**
     * Getter of features allowed in the group
     * @return
     */
    public List<Feature> getMyFeatures() {
        return myFeatures;
    }

    /**
     * Checks if the group has a feature
     * @param feature feature to be searched
     * @return true if group has given feature
     */
    public boolean hasFeature(Feature feature){

        for(Feature f:myFeatures){
            if(feature == f)
                return true;
        }

        return false;

    }

    /**
     * Add a new feature to the group and update it to the database
     * @param feature feature to be added
     */
    public void addFeature(Feature feature){
        this.myFeatures.add(feature);
        ChatGroupDatabase.updateFeatures(this);
    }

    /**
     * remove an existing feature to the group and update it to the database
     * @param feature feature to be added
     */
    public void removeFeature(Feature feature){
        this.myFeatures.remove(feature);
        ChatGroupDatabase.updateFeatures(this);
    }

    /**
     * removes all the chat items from chatItemList
     */
    public void resetChatGroup(){
        chatItemList = new ArrayList<>();
    }

    /**
     * groupId getter
     * @return group id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Gets the ChatWebElement
     * @return chat group web element
     */
    public WebElement getChatWebElement() {
        try {
            return Bot.getWebDriver().findElement(By.xpath(XPaths.getGroupNameXPath(groupId)));
        }catch (NoSuchElementException e){
            Log.e(String.format("Group with id: %s not found, please make sure you have a group with %s%s in the end of the group title,",
                    groupId, Strings.commandPrefix, groupId));

            Log.p(e);
        }
        return null;
    }

    /**
     * @return list of client in the chatgroup
     */
    public List<Client> getAllClients()
    {
        return ClientDatabase.getClientsInGroup(groupId);
    }

    /**
     * Method used by StringActionInitializer
     * @param s chatgroup id
     * @return chatgroup object
     */
    public static ChatGroup castFromString(String s){
        return Bot.getChatGroupsManager().findGroupById(s);
    }
}
