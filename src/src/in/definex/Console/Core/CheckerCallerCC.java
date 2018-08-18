package in.definex.Console.Core;

import in.definex.Action.Action;
import in.definex.Action.StringActionInitializer;
import in.definex.Bot;
import in.definex.Console.ConsoleCommand;
import in.definex.Functions.out;

import java.util.Arrays;

public class CheckerCallerCC extends ConsoleCommand {

    public CheckerCallerCC() {
        super("checker", -1);
    }

    @Override
    protected String compute(String[] args) {

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
}
