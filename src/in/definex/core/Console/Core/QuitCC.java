package in.definex.core.Console.Core;

import in.definex.core.Bot;
import in.definex.core.Console.ConsoleCommand;
import in.definex.core.Looper;

/**
 * Used to quit the bot,
 * just type quit and press enter to quit
 *
 * Created by adam_ on 02-04-2018.
 */
public class QuitCC extends ConsoleCommand {


    public QuitCC() {
        super("quit", 0);
    }

    @Override
    protected String compute(String[] args) {
        Bot.getLooper().quit();
        return "Quitting, Goodbye";
    }
}
