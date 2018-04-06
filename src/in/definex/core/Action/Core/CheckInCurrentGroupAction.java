package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
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

        if(bubbles.size() > chatGroup.getChatItemList().size()){

            int newChatCount = bubbles.size() - chatGroup.getChatItemList().size();


            //adding new chat to list
            for (int i =0; i<newChatCount; i++){
                int bubbleIndex = bubbles.size() - newChatCount + i;

                String clientName =  BubbleFunctions.getAuthorNameFromBubble(bubbles.get(bubbleIndex), bubbles);
                Client client = Client.getClient(
                        clientName,
                        chatGroup.getGroupId()
                );

                //todo what if getClient returns null
                if(client == null){
                    client = Client.createTempAccount(clientName, chatGroup.getGroupId());
                }

                chatGroup.addChatItem(new ChatItem(
                        client,
                        BubbleFunctions.getTimeFromBubble(bubbles.get(i)),
                        bubbles.get(i)
                ));

                ActionTaskFunctions.proccessBubbleThenProcessCommand(bubbles.get(bubbleIndex), client, chatGroup);

            }

        }

    }
}
