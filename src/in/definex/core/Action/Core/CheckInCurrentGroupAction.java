package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.Bubble;
import in.definex.core.ChatSystem.ChatItem;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Console.Log;
import in.definex.core.Functions.ActionTaskFunctions;
import in.definex.core.Functions.BubbleFunctions;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CheckInCurrentGroupAction
 * Checker Action
 *
 * checks for new chat in current group
 *
 * Created by adam_ on 01-12-2017.
 */
public class CheckInCurrentGroupAction extends Action {


    //chatgroup will be null if you want to check in the current chat
    public CheckInCurrentGroupAction() {
        super(null);
    }

    @Override
    public void task() {

        chatGroup = Bot.getActionManager().getActiveChat();

        if(chatGroup == null){
            Log.m("No current chat open");
            return;
        }

        List<WebElement> bubbles = Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));

        //comparing current bubble size vs previous
        if(bubbles.size() > chatGroup.getChatItemList().size()){

            int newChatCount = bubbles.size() - chatGroup.getChatItemList().size();


            //adding new chat to list
            for (int i =0; i<newChatCount; i++){
                int bubbleIndex = bubbles.size() - newChatCount + i;

                Bubble bubble = new Bubble(bubbles.get(bubbleIndex));

                String clientName =  bubble.getAuthor(bubbles);
                Client client = Client.getClient(
                        clientName,
                        chatGroup.getGroupId()
                );

                chatGroup.addChatItem(new ChatItem(
                        client,
                        bubble.getTime(),
                        bubbles.get(bubbleIndex)
                ));

                Bot.getChatProcessorManager().process(bubble, client);

            }

        }

    }
}
