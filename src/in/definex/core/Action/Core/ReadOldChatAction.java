package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.Functions.ActionTaskFunctions;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * ReadOldChatAction
 * deprecated action
 *
 * used to reset chats in chatgroup and fill it with old chat
 *
 * Created by adam_ on 30-11-2017.
 */
@Deprecated
public class ReadOldChatAction extends Action {


    public ReadOldChatAction(ChatGroup chatGroup) {
        super(chatGroup);
    }

    @Override
    public void task() {

        //chatGroup.getChatWebElement().click();
        Bot.getActionManager().gotoGroup(chatGroup);

        List<WebElement> bubbles =  Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));
        ActionTaskFunctions.resetAndPutChatInGroupChat(bubbles, chatGroup);


    }
}
