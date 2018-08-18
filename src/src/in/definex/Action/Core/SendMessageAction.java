package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Console.Log;
import in.definex.Functions.Utils;
import in.definex.String.XPaths;
import org.apache.http.annotation.Experimental;
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
    private String phoneRegex;


    /**
     * Constructor
     *
     * @param chatGroup ChatGroup to send message
     * @param text Text to send
     */
    public SendMessageAction(ChatGroup chatGroup,String text) {
        super();
        this.text = text;
        this.chatGroup = chatGroup;
    }

    /**
     * Use this to tag the user using phonenumber
     *
     * @param phoneRegex Regular expression for phone number
     */
    @Experimental
    public void tagPhoneNumbers(String phoneRegex){
        this.phoneRegex = phoneRegex;
    }

    @Override
    public boolean task() {

        Bot.getActionManager().gotoGroup(chatGroup);

        WebElement chatBox = Bot.getWebDriver().findElement(By.xpath(XPaths.inputBody));

        text = Utils.ConvertNewLine(text);

        if(phoneRegex != null)
            text = Utils.ConvertNumberToTag(text, phoneRegex);

        chatBox.sendKeys(text);

        Log.d("Sending",text);

        Bot.getWebDriver().findElement(By.xpath(XPaths.sendButton)).click();

        return true;
    }
}
