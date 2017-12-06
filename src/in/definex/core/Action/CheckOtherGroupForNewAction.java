package in.definex.core.Action;

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

        List<WebElement> newChats = driver.findElements(By.xpath(XPaths.newChatGroup));

        for(WebElement element:newChats) {
            ChatGroup newChatGroup = chatGroupsManager.findGroupById(Utils.chatGroupNameToUID(element.getText()));

            if(newChatGroup != null) {
                CheckInNewGroupAction checkInNewGroupAction = new CheckInNewGroupAction(actionManager, newChatGroup, driver, featureManager);
                actionManager.add(checkInNewGroupAction);
            }
        }
    }
}
