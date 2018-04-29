package in.definex.Console;

import in.definex.Bot;

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
    private static boolean Debug = false;

    private final static String RED = "\u001B[31m";
    private final static String GREEN = "\u001B[32m";
    private final static String RESET = "\u001B[0m";

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
     * load debug settings from configuration
     * run in init method in looper in the end
     */
    public static void init()
    {
        Debug = Bot.getConfiguration().GetConfig("log", false);
    }

    /**
     * Changes the debug setting
     * Changed using "1log on|off" command
     *
     * @param Debug target status
     */
    public static void SetDebug(boolean Debug){
        Log.Debug = Debug;
        Bot.getConfiguration().SaveConfig("log", Debug);
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

    /**
     * Used to printStackTrace only when Debug is on
     * @param e exception to be printed
     */
    public static void p(Exception e){ if(Debug)e.printStackTrace();}

    /**
     * Logs error in red.
     * logs even when Debug is false
     * @param error error to be logged
     */
    public static void e(String error){
        System.out.println(RED+error+RESET);
    }

    /**
     * Logs success in green.
     * logs even when Debug is false
     * @param success message to be logged
     */
    public static void s(String success){
        System.out.println(GREEN+success+RESET);
    }

    /**
     * Logs even when Debug is false
     * @param reply message to be logged
     */
    public static void r(String reply){
        System.out.println(reply);
    }

}
