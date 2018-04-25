package in.definex.Functions;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.ChatItem;
import in.definex.ChatSystem.Client;
import in.definex.Feature.CommandAndArgs;
import in.definex.Feature.FeatureManager;
import in.definex.String.Strings;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * ActionTaskFunctions
 * static function used by other classes to make the code a little cleaner
 *
 * Created by adam_ on 02-12-2017.
 */
public class ActionTaskFunctions {

    public static void resetAndPutChatInGroupChat(List<WebElement> bubbles, ChatGroup chatGroup) {
        chatGroup.resetChatGroup();
        for (WebElement element : bubbles) {

            chatGroup.getChatItemList().add(
                    new ChatItem(
                            //todo not sure if good solution
                            //Client.getClient(BubbleFunctions.getAuthorNameFromBubble(element, bubbles), chatGroup.getGroupId()),
                            Client.createTempAccount("temp", chatGroup.getGroupId()),
                            new Bubble(element).getTime(),
                            element)
            );

        }
    }

}
