package in.definex.core.Functions;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.SendMessageAction;
import in.definex.core.ChatGroup;
import in.definex.core.ChatItem;
import in.definex.core.Client;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.Strings;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam_ on 02-12-2017.
 */
public class ActionTaskFunctions {

    public static void resetAndPutChatInGroupChat(List<WebElement> bubbles, ChatGroup chatGroup){
        chatGroup.resetChatGroup();
        for (WebElement element : bubbles) {

            chatGroup.getChatItemList().add(
                    new ChatItem(
                            Client.getClient(BubbleFunctions.getAuthorNameFromBubble(element, bubbles), chatGroup.getGroupId()),
                            BubbleFunctions.getTimeFromBubble(element),
                            element)
            );

        }
    }

    public static void proccessBubbleThenProcessCommand(WebElement bubble, Client client, ChatGroup chatGroup, FeatureManager featureManager, ActionManager actionManager, WebDriver driver){

        //todo check if string then process (in another thread)
        String text = BubbleFunctions.getTextFromBubble(bubble);
        if(Utils.isCommand(text)){
            CommandAndArgs commandAndArgs = CommandAndArgs.Split(text);

            List<String> newArgs = new ArrayList<>();

            for(int i=0; i<commandAndArgs.args.length; i++){
                int noOfExtraAttri = 0;
                if(commandAndArgs.args[i].startsWith("@")){
                    String reference = bubble.findElement(By.xpath(XPaths.getClientReferenceFromBubble)).getAttribute(XPaths.referenceAttribute);

                    String referenceText = bubble.findElement(By.xpath(XPaths.getClientReferenceFromBubble)).getText();
                    noOfExtraAttri = referenceText.split(" ").length;

                    commandAndArgs.args[i] = Strings.fromReferenceToClientName(reference);
                }

                newArgs.add(commandAndArgs.args[i]);
                i+=noOfExtraAttri;
            }

            commandAndArgs.args = newArgs.toArray(new String[newArgs.size()]);

            FeatureManager.FeatureAndCommand featureAndCommand = featureManager.findFeatureByCommandKeyword(commandAndArgs.cmd);

            if(featureAndCommand == null)
                actionManager.add(new SendMessageAction(actionManager, chatGroup, driver, Strings.commandDoesntExists));
            else if(!chatGroup.hasFeature(featureAndCommand.feature))
                actionManager.add(new SendMessageAction(actionManager, chatGroup, driver, Strings.commandNotAllowedInThisGroup));
            else
                featureAndCommand.command.proccess(chatGroup, client,commandAndArgs.args);
        }


    }

}
