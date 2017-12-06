package in.definex.core.Action;

import in.definex.core.ChatItem;
import in.definex.core.Client;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.Functions.ActionTaskFunctions;
import in.definex.core.Functions.BubbleFunctions;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by adam_ on 01-12-2017.
 */
public class CheckInCurrentGroupAction extends Action {

    private WebDriver driver;
    private FeatureManager featureManager;


    //chatgroup will be null if you want to check in the current chat
    public CheckInCurrentGroupAction(ActionManager actionManager, WebDriver driver, FeatureManager featureManager) {
        super(actionManager, null);

        this.driver = driver;
        this.featureManager = featureManager;
    }

    @Override
    public void task() {

        chatGroup = actionManager.getActiveChat();

        if(chatGroup == null){
            System.out.println("No current chat open");
            return;
        }

        List<WebElement> bubbles = driver.findElements(By.xpath(XPaths.inMessageBubbles));

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

                ActionTaskFunctions.proccessBubbleThenProcessCommand(bubbles.get(bubbleIndex), client, chatGroup, featureManager, actionManager, driver);

            }

        }

    }
}
