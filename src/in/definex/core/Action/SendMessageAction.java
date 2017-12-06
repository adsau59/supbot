package in.definex.core.Action;

import in.definex.core.ChatGroup;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by adam_ on 01-12-2017.
 */
public class SendMessageAction extends Action {

    WebDriver driver;
    String text;

    public SendMessageAction(ActionManager actionManager, ChatGroup chatGroup, WebDriver driver, String text) {
        super(actionManager, chatGroup);
        this.text = text;
        this.driver = driver;
    }

    @Override
    public void task() {

        actionManager.gotoGroup(chatGroup);

        WebElement chatBox = driver.findElement(By.xpath(XPaths.inputBody));

        text = text.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));

        chatBox.sendKeys(text);

        System.out.println("Sending: "+text);

        driver.findElement(By.xpath(XPaths.sendButton)).click();

    }
}
