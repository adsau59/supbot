package in.definex.Console.Core;

import in.definex.Action.RemoteActionCall;
import in.definex.Bot;
import in.definex.Console.ConsoleCommand;

public class ActionCallerCC extends ConsoleCommand {


    public ActionCallerCC() {
        super("action", -1);
    }

    @Override
    protected String compute(String[] args) {
        RemoteActionCall.Response response = Bot.getRemoteActionCall().callAction(args);

        return response.getResponseString();
    }
}
