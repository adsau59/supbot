package in.definex.Functions;

import in.definex.String.Strings;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String ConvertNewLine(String s){
        return s.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));
    }

    public static String ConvertNumberToTag(String text, String regex){


        Matcher matcher = Pattern.compile(regex).matcher(text);

        String toConcatBefore = "@";
        String toConcatAfter = Keys.chord(Keys.UP,Keys.ENTER);


        int count = 0;
        while(matcher.find()) {

            int offset = count*(toConcatAfter.length()+toConcatAfter.length());

            text = text.substring(0, matcher.start() + offset)
                    +toConcatBefore
                    +text.substring(matcher.start() + offset);

            text = text.substring(0, matcher.end() + offset + toConcatBefore.length())
                    + toConcatAfter
                    + text.substring(matcher.end() + offset + toConcatBefore.length());

            count++;

        }

        return text;

    }

    /**
     * Searces for castFromString method in a class
     *
     * @param p Class to be searched
     * @return true if p class have castFromString method
     */
    public static boolean hasStringCaster(Class p){
        try {
            return p.getMethod("castFromString",String.class) != null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }




}
