package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Console.Log;
import in.definex.Functions.Utils;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * SendMessageAction
 *
 * Action used to send message to a chat group
 *
 * Created by adam_ on 01-12-2017.
 */
public class SendMessageAction extends Action {

    private String text;
    private ChatGroup chatGroup;

    public SendMessageAction(ChatGroup chatGroup,String text) {
        super();
        this.text = text;
        this.chatGroup = chatGroup;
    }

    @Override
    public boolean task() {

        Bot.getActionManager().gotoGroup(chatGroup);

        WebElement chatBox = Bot.getWebDriver().findElement(By.xpath(XPaths.inputBody));

        text = Utils.ConvertNewLine(text);

        chatBox.sendKeys(text);

        Log.d("Sending",text);

        Bot.getWebDriver().findElement(By.xpath(XPaths.sendButton)).click();

        return true;
    }
}
