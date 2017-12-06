package in.definex.core.Action;

import in.definex.core.ChatGroup;
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
 * Created by adam_ on 02-12-2017.
 *
 * Goto a indentified new chat and read new chat and process its command
 * also reset the groupchat
 */


public class CheckInNewGroupAction extends Action {

    WebDriver driver;
    FeatureManager featureManager;


    CheckInNewGroupAction(ActionManager actionManager, ChatGroup chatGroup, WebDriver driver, FeatureManager featureManager) {
        super(actionManager, chatGroup);
        this.driver = driver;
        this.featureManager = featureManager;
    }

    @Override
    public void task() {

        actionManager.gotoGroup(chatGroup);

        List<WebElement> newBubbles = driver.findElements(By.xpath(XPaths.newChatBubbles));

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

            ActionTaskFunctions.proccessBubbleThenProcessCommand(element, client, chatGroup, featureManager, actionManager, driver);
        }

        List<WebElement> allBubbles = driver.findElements(By.xpath(XPaths.inMessageBubbles));
        ActionTaskFunctions.resetAndPutChatInGroupChat(allBubbles, chatGroup);

    }

}
