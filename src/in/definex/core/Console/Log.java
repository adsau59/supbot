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
    public static boolean Debug = true;

    public static void d(String type, Object obj){
        if(!Debug)
            return;

        System.out.println(type+": "+obj);
    }

    public static void a(String msg){
        if(!Debug)
            return;

        System.out.println("*********** "+msg+" *************");
    }

    public static void m(String m){
        if(!Debug)
            return;

        System.out.println(m);
    }

}
