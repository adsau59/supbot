package in.definex.Console.Core;

import in.definex.Action.Action;
import in.definex.Action.StringActionInitializer;
import in.definex.Bot;
import in.definex.Console.ConsoleCommand;
import in.definex.Functions.out;

import java.util.Arrays;


/**
 * CheckerCallerCC : ConsoleCommand
 * add a checker action during runtime using console
 * used for debugging,
 * CHECKER_TIMEOUT 0 to run it forever
 *
 * Usage:
 * checker ACTION_CLASS_NAME [ACTION_CONSTRUCTOR_PARAMS...] BOOL_REMOVE_CHECKER_ON_SUCCESS CHECKER_TIMEOUT
 *
 * Example:
 * checker CheckOtherChatForNewAction false 0
 *
 */

public class CheckerCallerCC extends ConsoleCommand {

    public CheckerCallerCC() {
        super("checker", -1);
    }

    @Override
    public String compute(String[] args) {

        try{
            long checkerTimeout = Long.valueOf(args[args.length-1]);
            boolean removeCheckerOnSuccess = Boolean.valueOf(args[args.length-2]);


            out<Action> actionout = new out<>();
            StringActionInitializer.Response response = Bot.getRemoteActionCall().getAction(actionout, Arrays.copyOfRange(args, 0, args.length-2));

            if(response.isSuccess()) {
                if (checkerTimeout == 0)
                    Bot.getChecker().addCheckers(actionout.obj);
                else
                    Bot.getChecker().addCheckerWithTimeout(actionout.obj, checkerTimeout, removeCheckerOnSuccess,success -> {});

                return "Action added in checker successfully";
            }
            return response.getResponseString();

        }catch (Exception e){
            e.printStackTrace();
            return "must be in format:\nchecker ActionName arg1 arg2 argN removeCheckerOnSuccess? timeIntMs";
        }
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "checker ACTION_CLASS_NAME [ACTION_CONSTRUCTOR_PARAMS...] BOOL_REMOVE_CHECKER_ON_SUCCESS CHECKER_TIMEOUT",
                "checker CheckOtherChatForNewAction false 0",
                "add a checker action during runtime using console\n" +
                        "used for debugging, \n" +
                        "CHECKER_TIMEOUT 0 to run it forever"
        );
    }
}
