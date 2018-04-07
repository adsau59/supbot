package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.Console.Log;
import in.definex.core.String.XPaths;
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

    public SendMessageAction(ChatGroup chatGroup,String text) {
        super(chatGroup);
        this.text = text;
    }

    @Override
    public void task() {

        Bot.getActionManager().gotoGroup(chatGroup);

        WebElement chatBox = Bot.getWebDriver().findElement(By.xpath(XPaths.inputBody));

        text = text.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));

        chatBox.sendKeys(text);

        Log.d("Sending",text);

        Bot.getWebDriver().findElement(By.xpath(XPaths.sendButton)).click();

    }
}
