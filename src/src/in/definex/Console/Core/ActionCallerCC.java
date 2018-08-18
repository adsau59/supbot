package in.definex.Console.Core;

import in.definex.Action.StringActionInitializer;
import in.definex.Bot;
import in.definex.Console.ConsoleCommand;

/**
 *
 * ActionCallerCC : ConsoleCommand
 * Add and action to ActionManager using Console Commands
 *
 * Usage:
 * action ACTION_CLASS_NAME [ACTION_CONSTRUCTOR_PARAMS]
 *
 * Usage:
 * action SendMessageAction group1 "I am bot, SUPBOT!!!"
 *
 */
public class ActionCallerCC extends ConsoleCommand {


    public ActionCallerCC() {
        super("action", -1);
    }

    @Override
    protected String compute(String[] args) {
        StringActionInitializer.Response response = Bot.getRemoteActionCall().callAction(args);

        return response.getResponseString();
    }
}
