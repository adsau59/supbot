package in.definex.core.Console;

import org.omg.CORBA.PUBLIC_MEMBER;

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

    public static void e(String error){
        System.out.println(RED+error+RESET);
    }

    public static void s(String success){
        System.out.println(GREEN+success+RESET);
    }

    public static void r(String reply){
        System.out.println(reply);
    }

}
