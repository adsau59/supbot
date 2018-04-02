package in.definex.core.Console.Core;

import in.definex.core.Console.ConsoleCommand;
import in.definex.core.Looper;

/**
 * Created by adam_ on 02-04-2018.
 */
public class QuitCC extends ConsoleCommand {

    Looper looper;
    public QuitCC(Looper looper) {
        super("quit", 0);
        this.looper = looper;
    }

    @Override
    protected String compute(String[] args) {
        looper.quit();
        return "Quitting, Goodbye";
    }
}
