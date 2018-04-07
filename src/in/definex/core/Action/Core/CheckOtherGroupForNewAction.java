package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.Functions.Utils;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CheckOtherGroupForNewAction
 * checker action
 *
 * Check for new chat in other groups,
 * if finds one, adds CheckInNewGroupAction to ActionManager
 *
 * Created by adam_ on 02-12-2017.
 */
public class CheckOtherGroupForNewAction extends Action {
;

    public CheckOtherGroupForNewAction() {
        super(null);
    }

    @Override
    public void task() {

        //xpath search for new chats in other groups (group that is not currently selected
        List<WebElement> newChats = Bot.getWebDriver().findElements(By.xpath(XPaths.newChatGroup));

        for(WebElement element:newChats) {
            //getting name from html and searching for it in database for id, then
            ChatGroup newChatGroup = Bot.getChatGroupsManager().findGroupById(Utils.chatGroupNameToUID(element.getText()));

            //if id is found add action to actionmanager
            if(newChatGroup != null) {
                CheckInNewGroupAction checkInNewGroupAction = new CheckInNewGroupAction(newChatGroup);
                Bot.getActionManager().add(checkInNewGroupAction);
            }
        }
    }
}
