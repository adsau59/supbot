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
        return name.split(Strings.titlePrefix)[1];
    }

    public static Image getImageFromClipboard() throws Exception
    {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
        {
            return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
        }
        else
        {
            return null;
        }
    }

    public static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }

    public static String ConvertNewLine(String s){
        return s.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));
    }

}
