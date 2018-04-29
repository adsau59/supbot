package in.definex.Console.Core;

import in.definex.Console.ConsoleCommand;
import in.definex.Console.Log;

/**
 * Console Command to turn loging on or off,
 *
 * use "log on" to turn on logging
 * use "log off" to turn off logging
 *
 * Created by adam_ on 06-04-2018.
 */

public class LogCC extends ConsoleCommand {
    /**
     * Constructor
     */
    public LogCC() {
        super("log", 1);
    }

    @Override
    protected String compute(String[] args) {

        switch (args[0]){
            case "on":
                Log.SetDebug(true);
                return "Debug turned on";

            case "off":
                Log.SetDebug(false);
                return "Debug turned off";

            default:
                return "Invalid Arguments, use either 'on' or 'off'";
        }
    }

}
