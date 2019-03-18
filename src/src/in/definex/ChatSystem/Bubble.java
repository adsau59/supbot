package in.definex.ChatSystem;

import in.definex.Bot;
import in.definex.Console.Log;
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
     * must be on the current client
     *
     * @return authon name
     */
    public String getAuthor(){
        if(author == null)
        author = Bot.getWebDriver().findElement(By.xpath(XPaths.authorName)).getText();

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
     * Web element getter
     * @return bubble webelement
     */
    public WebElement getWebElement() {
        return webElement;
    }


}
