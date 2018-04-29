package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Functions.ActionTaskFunctions;
import in.definex.String.XPaths;
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

    private ChatGroup chatGroup;
    public ReadOldChatAction(ChatGroup chatGroup) {
        super();
        this.chatGroup = chatGroup;
    }

    @Override
    public boolean task() {

        //chatGroup.getChatWebElement().click();
        Bot.getActionManager().gotoGroup(chatGroup);

        List<WebElement> bubbles =  Bot.getWebDriver().findElements(By.xpath(XPaths.inMessageBubbles));
        ActionTaskFunctions.resetAndPutChatInGroupChat(bubbles, chatGroup);
        return true;

    }
}
