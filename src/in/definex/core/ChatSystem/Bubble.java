package in.definex.core.ChatSystem;

import in.definex.core.String.Strings;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Bubble
 * Abstraction of webelement of whatsapp chatitem
 *
 */
public class Bubble {

    private WebElement webElement;
    private String text;
    private String time;
    private String author;

    /**
     * Constructor
     * @param webElement bubble chatelement
     */
    public Bubble(WebElement webElement) {
        this.webElement = webElement;
    }

    /**
     * @return text in chat
     */
    public String getText(){

        if(text == null)
            text = webElement.findElement(By.xpath(XPaths.bubbleToText)).getText();

        return text;
    }

    /**
     * @return time of chat
     */
    public String getTime(){

        if(time == null)
            time =  webElement.findElement(By.xpath(XPaths.bubbleToTime)).getText();

        return time;
    }

    /**
     * Get name of the author of the message
     *
     * @param bubbles all the chatitems from which the bubble belongs
     * @return authon name
     */
    public String getAuthor(List<WebElement> bubbles){

        if(author == null)
            for(int i = bubbles.indexOf(webElement); i>=0; i--)
                if (doesHaveAuthorName(bubbles.get(i)))
                    author = bubbles.get(i).findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;
    }

    private static boolean doesHaveAuthorName(WebElement bubble){
        return bubble.findElements(By.xpath(XPaths.bubbleToAuthorName)).size() != 0;
    }

    /**
     * Web element getter
     * @return bubble webelement
     */
    public WebElement getWebElement() {
        return webElement;
    }
}
