package in.definex.ChatSystem;

import in.definex.Bot;
import in.definex.Console.Log;
import in.definex.String.Strings;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

        if(text == null){
            try{
                text = webElement.findElement(By.xpath(XPaths.bubbleToText)).getText();
            }catch (NoSuchElementException e){
                Log.d("Bubble","Bubble doesn't contain text");
                return "";
            }
        }

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
    public String getAuthor(List<Bubble> bubbles){

        if(author == null)
            for(int i = bubbles.indexOf(this); i>=0; i--)
                if (doesHaveAuthorName(bubbles.get(i).webElement))
                    author = bubbles.get(i).webElement.findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;
    }

    /**
     * if author is null, tries to find the author from its own bubble,
     * if not found return null
     *
     * @return author name
     */
    public String getAuthorFromMyBubble(){

        if(author == null && doesHaveAuthorName(webElement))
            author = webElement.findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();

        return author;

    }


    /**
     * Get all the bubbles on screen.
     *
     * @param xPath xpath query used to get bubbles.
     * @return array of bubbles.
     */
    public static Bubble[] GetBubbles(String xPath){
        List<WebElement> webElements = Bot.getWebDriver().findElements(By.xpath(xPath));

        Bubble[] bubbles = new Bubble[webElements.size()];

        for(int i=0; i<webElements.size(); i++){
            bubbles[i] = new Bubble(webElements.get(i));
        }

        return bubbles;

    }

    /**
     * Checks if the bubble have an authorname.
     *
     * @param bubble bubble webelement.
     * @return true if the bubble have author name.
     */
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
