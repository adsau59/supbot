package in.definex.ChatSystem;

import in.definex.Bot;
import in.definex.String.Strings;
import in.definex.String.XPaths;
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
    /*public String getAuthor(List<WebElement> bubbles){

        if(author == null)
            for(int i = bubbles.indexOf(webElement); i>=0; i--)
                if (doesHaveAuthorName(bubbles.get(i)))
                    author = bubbles.get(i).findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;
    }*/

    public String getAuthor(List<Bubble> bubbles){

        if(author == null)
            for(int i = bubbles.indexOf(this); i>=0; i--)
                if (doesHaveAuthorName(bubbles.get(i).webElement))
                    author = bubbles.get(i).webElement.findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;
    }

    public String getAuthorFromMyBubble(){

        if(author == null && doesHaveAuthorName(webElement))
            author = webElement.findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;

    }


    public static Bubble[] GetBubbles(String xPath){
        List<WebElement> webElements = Bot.getWebDriver().findElements(By.xpath(xPath));

        Bubble[] bubbles = new Bubble[webElements.size()];

        for(int i=0; i<webElements.size(); i++){
            bubbles[i] = new Bubble(webElements.get(i));
        }

        return bubbles;

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
