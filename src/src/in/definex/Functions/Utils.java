package in.definex.Functions;

import in.definex.String.Strings;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;

/**
 * Utils
 * static function used by other classes to make the code a little cleaner
 *
 * Created by adam_ on 30-11-2017.
 */
public class Utils {

    public static void waitFor(long ms){
        //wait for load
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static String chatGroupNameToUID(String name){
        String[] nameParts = name.split(Strings.titlePrefix);

        if(nameParts.length >= 2)
            return  nameParts[1];

        return null;
    }

    public static String ConvertNewLine(String s){
        return s.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));
    }




}
