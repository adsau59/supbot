package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.Client;
import in.definex.Functions.ActionTaskFunctions;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
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


    private ChatGroup chatGroup;

    public CheckInNewGroupAction(ChatGroup chatGroup) {
        super();
        this.chatGroup = chatGroup;
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
    public boolean task() {

        Bot.getActionManager().gotoGroup(chatGroup);

        List<Bubble> newBubbles = Arrays.asList(Bubble.GetBubbles(XPaths.newChatBubbles));//Bot.getWebDriver().findElements(By.xpath(XPaths.newChatBubbles));
        for(Bubble bubble:newBubbles) {

            //Bubble bubble = new Bubble(element);
            String clientName = bubble.getAuthor(newBubbles);
            Client client = Client.getClient(
                    clientName,
                    chatGroup.getGroupId()
            );

            Bot.getChatProcessorManager().process(bubble, client);

        }

        return true;
    }

}
