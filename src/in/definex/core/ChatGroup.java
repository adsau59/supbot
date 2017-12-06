package in.definex.core;

import in.definex.core.Feature.Feature;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam_ on 30-11-2017.
 */
public class ChatGroup {


    private String groupId;
    private List<Feature> myFeatures;

    private List<ChatItem> chatItemList;
    private WebElement chatWebElement; //div on which u click to open chat

    public ChatGroup(String groupId, List<Feature> myFeatures, WebDriver driver) {
        this.chatItemList = new ArrayList<>();
        this.groupId = groupId;
        this.chatWebElement = driver.findElement(By.xpath(XPaths.getGroupNameXPath(groupId)));
        this.myFeatures = myFeatures;
    }

    public ChatGroup(String groupId, WebDriver driver){
        this(groupId, new ArrayList<>(), driver);
    }

    public void addChatItem(ChatItem chatItem){
        chatItemList.add(chatItem);
    }

    public List<ChatItem> getChatItemList() {
        return chatItemList;
    }

    public List<Feature> getMyFeatures() {
        return myFeatures;
    }

    public boolean hasFeature(Feature feature){

        for(Feature f:myFeatures){
            if(feature == f)
                return true;
        }

        return false;

    }

    public void addFeature(Feature feature){
        this.myFeatures.add(feature);
        DatabaseManager.updateFeatures(this);
    }

    public void removeFeature(Feature feature){
        this.myFeatures.remove(feature);
        DatabaseManager.updateFeatures(this);
    }

    public void resetChatGroup(){
        chatItemList = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public WebElement getChatWebElement() {
        return chatWebElement;
    }
}
