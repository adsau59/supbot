package in.definex.core.Console;

/**
 * Log
 * manages the logging system of the bot
 *
 * Created by adam_ on 02-04-2018.
 */
public class Log {

    /**
     * set to false to hide all loggin messages
     */
    public static boolean Debug = false;

    /**
     * Print in form "type: thing"
     *
     * @param type first half
     * @param thing second half
     */
    public static void d(String type, Object thing){
        if(!Debug)
            return;

        System.out.println(type+": "+thing);
    }

    /**
     * Announcement log
     * Prints in form "************* msg *************"
     *
     * @param msg string to log
     */
    public static void a(String msg){
        if(!Debug)
            return;

        System.out.println("*********** "+msg+" *************");
    }

    /**
     * Simply logs
     *
     * @param m string to log
     */

    public static void m(String m){
        if(!Debug)
            return;

        System.out.println(m);
    }

}
