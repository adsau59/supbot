package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.Console.ConsoleCommand;
import in.definex.Looper;

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
    public String compute(String[] args) {
        Bot.getLooper().quit();
        return "Quitting, Goodbye.";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "quit",
                "quit",
                "Used to quit the bot,\n" +
                        "just type quit and press enter to quit"
        );
    }
}
