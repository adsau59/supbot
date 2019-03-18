package in.definex.Functions;

import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatItem;
import in.definex.ChatSystem.Client;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * ActionTaskFunctions
 * static function used by other classes to make the code a little cleaner
 *
 * Created by adam_ on 02-12-2017.
 */
public class ActionTaskFunctions {

    public static void resetAndPutChatInGroupChat(List<WebElement> bubbles, Client client) {
        client.clearChatCache();
        for (WebElement element : bubbles) {

            client.getChatItemList().add(
                    new ChatItem(
                            //todo not sure if good solution
                            //Client.getClient(BubbleFunctions.getAuthorNameFromBubble(element, bubbles), chatGroup.getGroupId()),
                            Client.createTempAccount("temp"),
                            new Bubble(element).getTime(),
                            element)
            );

        }
    }

}
