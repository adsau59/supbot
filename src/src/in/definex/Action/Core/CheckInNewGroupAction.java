package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.Client;
import in.definex.String.XPaths;

import java.util.Arrays;
import java.util.List;

/**
 * CheckInNewGroupAction
 *
 * Goto a non current chat and read new chat and process its command
 * also reset the groupchat and refills it with new chat
 *
 * used when CheckOtherChatForNewAction checker, finds new chat
 *
 * Created by adam_ on 02-12-2017.
 *
 */


public class CheckInNewGroupAction extends Action {


    private Client chatGroup;

    public CheckInNewGroupAction(Client client) {
        super();
        this.chatGroup = client;
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

        Bot.getActionManager().gotoChat(chatGroup);

        List<Bubble> newBubbles = Arrays.asList(Bubble.GetBubbles(XPaths.newChatBubbles));//Bot.getWebDriver().findElements(By.xpath(XPaths.newChatBubbles));
        for(Bubble bubble:newBubbles) {

            //Bubble bubble = new Bubble(element);
            String clientName = bubble.getAuthor();
            Client client = Bot.getClientManager().findClientByName(clientName);
            Bot.getChatProcessorManager().process(bubble, client);

        }

        return true;
    }

}
