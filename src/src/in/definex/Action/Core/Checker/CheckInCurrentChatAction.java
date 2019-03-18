package in.definex.Action.Core.Checker;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatItem;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;
import in.definex.String.XPaths;

import java.util.Arrays;
import java.util.List;

/**
 * CheckInCurrentChatAction
 * Checker Action
 *
 * checks for new chat in current group
 *
 * Created by adam_ on 01-12-2017.
 */
public class CheckInCurrentChatAction extends Action {

    //chatgroup will be null if you want to check in the current chat
    public CheckInCurrentChatAction() {
        super();
    }

    @Override
    public boolean task() {

        Client client = Bot.getActionManager().getActiveChat();

        if(client == null){
            Log.m("No current chat open");
            return false;
        }

        List<Bubble> bubbles = Arrays.asList(Bubble.GetBubbles(XPaths.inMessageBubbles));//Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));

        //comparing current bubble size vs previous
        if(bubbles.size() > client.getChatItemList().size()){

            int newChatCount = bubbles.size() - client.getChatItemList().size();


            //adding new chat to list
            for (int i =0; i<newChatCount; i++){
                int bubbleIndex = bubbles.size() - newChatCount + i;

                Bubble bubble = bubbles.get(bubbleIndex);

                String clientName =  bubble.getAuthor();

                client.addChatItem(new ChatItem(
                        client,
                        bubble.getTime(),
                        bubble.getWebElement()
                ));

                Bot.getChatProcessorManager().process(bubble, client);

            }

        }

        return true;

    }
}
