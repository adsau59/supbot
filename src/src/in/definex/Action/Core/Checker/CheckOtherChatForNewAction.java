package in.definex.Action.Core.Checker;

import in.definex.Action.Action;
import in.definex.Action.Core.CheckInNewGroupAction;
import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Functions.Utils;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CheckOtherChatForNewAction
 * checker action
 *
 * Check for new chat in other groups,
 * if finds one, adds CheckInNewGroupAction to ActionManager
 *
 * Created by adam_ on 02-12-2017.
 */
public class CheckOtherChatForNewAction extends Action {
;

    public CheckOtherChatForNewAction() {
        super();
    }

    @Override
    public boolean task() {

        //xpath search for new chats in other groups (group that is not currently selected
        List<WebElement> newChats = Bot.getWebDriver().findElements(By.xpath(XPaths.newChatGroup));

        for(WebElement element:newChats) {

            String clientName = element.getText();

            if(clientName != null){
                //getting name from html and searching for it in database for id, then
                Client newClient = Bot.getClientManager().findClientByName(clientName);



                //if id is found add action to actionmanager
                if(newClient != null) {
                    CheckInNewGroupAction checkInNewGroupAction = new CheckInNewGroupAction(newClient);
                    Bot.getActionManager().add(checkInNewGroupAction);
                }
            }
        }

        return true;
    }
}
