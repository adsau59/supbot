package in.definex.core.Functions;

import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by adam_ on 30-11-2017.
 */
public class BubbleFunctions {

    public static String getTimeFromBubble(WebElement bubble){
        return bubble.findElement(By.xpath(XPaths.bubbleToTime)).getText();
    }

    public static String getTextFromBubble(WebElement bubble){
        return bubble.findElement(By.xpath(XPaths.bubbleToText)).getText();
    }

    public static String getAuthorNameFromBubble(WebElement bubble, List<WebElement> bubbles){

        for(int i = bubbles.indexOf(bubble); i>=0; i--){

            if(doesHaveAuthorName(bubbles.get(i))) {
                return bubbles.get(i).findElement(By.xpath(XPaths.bubbleToAuthorName)).getText();
            }
        }

        return null;
    }

    public static boolean doesHaveAuthorName(WebElement bubble){
        return bubble.findElements(By.xpath(XPaths.bubbleToAuthorName)).size() != 0;
    }


}
