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
 * CheckInNewGroupAction
 *
 * Goto a non current chat and read new chat and process its command
 * also reset the groupchat and refills it with new chat
 *
 * used when CheckOtherGroupForNewAction checker, finds new chat
 *
 * Created by adam_ on 02-12-2017.
 *
 */


public class CheckInNewGroupAction extends Action {


    CheckInNewGroupAction(ChatGroup chatGroup) {
        super(chatGroup);
    }

    /**
     *
     * Goto specified group chat
     * get new chat bubbles (bubbles after "x new messages" sign)
     *
     * loop through each new bubbles
     *      get author name and create a client, and run the bubble
     *
     * reset the group chat and add all the bubbles the chat has
     *
     */
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

            if(client == null){
                client = Client.createTempAccount(clientName, chatGroup.getGroupId());
            }

            ActionTaskFunctions.proccessBubbleThenProcessCommand(element, client, chatGroup);
        }

        List<WebElement> allBubbles = Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));


        ActionTaskFunctions.resetAndPutChatInGroupChat(allBubbles, chatGroup);

    }

}
