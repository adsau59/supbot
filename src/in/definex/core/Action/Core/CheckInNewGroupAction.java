package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Functions.ActionTaskFunctions;
import in.definex.core.Functions.BubbleFunctions;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by adam_ on 02-12-2017.
 *
 * Goto a indentified new chat and read new chat and process its command
 * also reset the groupchat
 */


public class CheckInNewGroupAction extends Action {


    CheckInNewGroupAction(ChatGroup chatGroup) {
        super(chatGroup);
    }

    @Override
    public void task() {

        Bot.getActionManager().gotoGroup(chatGroup);

        List<WebElement> newBubbles = Bot.getWebDriver().findElements(By.xpath(XPaths.newChatBubbles));

        for(WebElement element:newBubbles) {


            String clientName = BubbleFunctions.getAuthorNameFromBubble(element, newBubbles);
            Client client = Client.getClient(
                    clientName,
                    chatGroup.getGroupId()
            );

            //todo what if client is null
            if(client == null){
                client = Client.createTempAccount(clientName, chatGroup.getGroupId());
            }

            ActionTaskFunctions.proccessBubbleThenProcessCommand(element, client, chatGroup);
        }

        List<WebElement> allBubbles = Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));
        ActionTaskFunctions.resetAndPutChatInGroupChat(allBubbles, chatGroup);

    }

}
