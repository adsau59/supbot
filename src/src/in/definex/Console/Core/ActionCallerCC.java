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
 * Example:
 * action SendMessageAction group1 "I am bot, SUPBOT!!!"
 *
 */
public class ActionCallerCC extends ConsoleCommand {


    public ActionCallerCC() {
        super("action", -1);
    }

    @Override
    public String compute(String[] args) {
        StringActionInitializer.Response response = Bot.getRemoteActionCall().callAction(args);

        return response.getResponseString();
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "action ACTION_CLASS_NAME [ACTION_CONSTRUCTOR_PARAMS]",
                "action SendMessageAction group1 \"I am bot, SUPBOT!!!\"",
                "Add and action to ActionManager using Console Commands"
        );
    }
}
