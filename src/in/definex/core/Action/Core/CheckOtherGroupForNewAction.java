package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Action.ActionManager;
import in.definex.core.ChatGroup;
import in.definex.core.ChatGroupsManager;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.Functions.Utils;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by adam_ on 02-12-2017.
 */
public class CheckOtherGroupForNewAction extends Action {

    private WebDriver driver;
    private ChatGroupsManager chatGroupsManager;
    private FeatureManager featureManager;

    public CheckOtherGroupForNewAction(ActionManager actionManager, WebDriver driver, ChatGroupsManager chatGroupsManager, FeatureManager featureManager) {
        super(actionManager, null);
        this.driver = driver;
        this.chatGroupsManager = chatGroupsManager;
        this.featureManager = featureManager;
    }

    @Override
    public void task() {

        //xpath search for new chats in other groups (group that is not currently selected
        List<WebElement> newChats = driver.findElements(By.xpath(XPaths.newChatGroup));

        for(WebElement element:newChats) {
            //getting name from html and searching for it in database for id, then
            ChatGroup newChatGroup = chatGroupsManager.findGroupById(Utils.chatGroupNameToUID(element.getText()));

            //if id is found add action to actionmanager
            if(newChatGroup != null) {
                CheckInNewGroupAction checkInNewGroupAction = new CheckInNewGroupAction(actionManager, newChatGroup, driver, featureManager);
                actionManager.add(checkInNewGroupAction);
            }
        }
    }
}
