package in.definex.core.Functions;

import in.definex.core.String.Strings;

/**
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

    public static boolean isCommand(String string){
        return string.startsWith(Strings.commandPrefix);
    }

    public static String chatGroupNameToUID(String name){
        return name.split(Strings.titlePrefix)[1];
    }



}
