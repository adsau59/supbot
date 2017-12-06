package in.definex.core.Action;

import in.definex.core.ChatGroup;
import in.definex.core.Functions.ActionTaskFunctions;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by adam_ on 30-11-2017.
 */
public class ReadOldChatAction extends Action {


    private WebDriver webDriver;

    public ReadOldChatAction(ActionManager actionManager, ChatGroup chatGroup, WebDriver webDriver) {
        super(actionManager, chatGroup);
        this.webDriver = webDriver;
    }

    @Override
    public void task() {

        //chatGroup.getChatWebElement().click();
        actionManager.gotoGroup(chatGroup);

        List<WebElement> bubbles =  webDriver.findElements(By.xpath(XPaths.inMessageBubbles));
        ActionTaskFunctions.resetAndPutChatInGroupChat(bubbles, chatGroup);


    }
}
