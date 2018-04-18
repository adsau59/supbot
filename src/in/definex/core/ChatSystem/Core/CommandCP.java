package in.definex.core.ChatSystem.Core;

import in.definex.core.Action.Core.SendMessageAction;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.Bubble;
import in.definex.core.ChatSystem.ChatProcessor;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.CommandAndArgs;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.Strings;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


/**
 * CommandCP
 * Processes the chat text into command and arguments
 * Checks if the group has that feature
 * if yes, then runs the command
 */
public class CommandCP extends ChatProcessor {
    public CommandCP() {
        super(true);
    }

    @Override
    public void task(Bubble bubble, Client client) {

        if(isCommand(bubble.getText())){
            CommandAndArgs commandAndArgs = CommandAndArgs.Split(bubble.getText());

            List<String> newArgs = new ArrayList<>();


            //todo move this part to Bubble class or a static function
            //convert name tagging to argument as reference
            for(int i=0; i<commandAndArgs.args.length; i++){
                int noOfExtraAttri = 0;
                if(commandAndArgs.args[i].startsWith("@")){
                    String reference = bubble.getWebElement().findElement(By.xpath(XPaths.getClientReferenceFromBubble)).getAttribute(XPaths.referenceAttribute);

                    String referenceText = bubble.getWebElement().findElement(By.xpath(XPaths.getClientReferenceFromBubble)).getText();
                    noOfExtraAttri = referenceText.split(" ").length;

                    commandAndArgs.args[i] = Strings.fromReferenceToClientName(reference);
                }

                newArgs.add(commandAndArgs.args[i]);
                i+=noOfExtraAttri;
            }

            commandAndArgs.args = newArgs.toArray(new String[0]);

            FeatureManager.FeatureAndCommand featureAndCommand = Bot.getFeatureManager().findFeatureByCommandKeyword(commandAndArgs.cmd);

            if(featureAndCommand == null)
                Bot.getActionManager().add(new SendMessageAction(client.getChatGroup(), Strings.commandDoesntExists));
            else if(!client.getChatGroup().hasFeature(featureAndCommand.feature))
                Bot.getActionManager().add(new SendMessageAction(client.getChatGroup(), Strings.commandNotAllowedInThisGroup));
            else
                featureAndCommand.command.proccess(client.getChatGroup(), client,commandAndArgs.args);
        }

    }

    private static boolean isCommand(String string){
        return string.startsWith(Strings.commandPrefix);
    }
}
