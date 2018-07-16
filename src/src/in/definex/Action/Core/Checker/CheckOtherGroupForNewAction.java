package in.definex.Action.Core.Checker;

import in.definex.Action.Action;
import in.definex.Action.Core.CheckInNewGroupAction;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Functions.Utils;
import in.definex.String.XPaths;
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
        super();
    }

    @Override
    public boolean task() {

        //xpath search for new chats in other groups (group that is not currently selected
        List<WebElement> newChats = Bot.getWebDriver().findElements(By.xpath(XPaths.newChatGroup));

        for(WebElement element:newChats) {

            String chatgroupUID = Utils.chatGroupNameToUID(element.getText());

            if(chatgroupUID != null){
                //getting name from html and searching for it in database for id, then
                ChatGroup newChatGroup = Bot.getChatGroupsManager().findGroupById(chatgroupUID);



                //if id is found add action to actionmanager
                if(newChatGroup != null) {
                    CheckInNewGroupAction checkInNewGroupAction = new CheckInNewGroupAction(newChatGroup);
                    Bot.getActionManager().add(checkInNewGroupAction);
                }
            }
        }

        return true;
    }
}
