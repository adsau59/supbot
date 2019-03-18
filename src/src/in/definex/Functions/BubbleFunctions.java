package in.definex.Functions;

import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * BubbleFunctions
 * Deprecated, use Bubble class instead
 * static function used by other classes to make the code a little cleaner
 *
 * Created by adam_ on 30-11-2017.
 */
@Deprecated
public class BubbleFunctions {

    public static String getTimeFromBubble(WebElement bubble){
        return bubble.findElement(By.xpath(XPaths.bubbleToTime)).getText();
    }

    public static String getTextFromBubble(WebElement bubble){
        return bubble.findElement(By.xpath(XPaths.bubbleToText)).getText();
    }

}
