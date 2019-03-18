package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;
import in.definex.Functions.Utils;
import in.definex.String.XPaths;
import org.apache.http.annotation.Experimental;
import org.openqa.selenium.By;
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
    private Client client;
    private String phoneRegex;


    /**
     * Constructor
     *
     * @param client ChatGroup to send message
     * @param text Text to send
     */
    public SendMessageAction(Client client,String text) {
        super();
        this.text = text;
        this.client = client;
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

        Bot.getActionManager().gotoChat(client);

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
