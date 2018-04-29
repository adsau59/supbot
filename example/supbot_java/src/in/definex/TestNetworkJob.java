package in.definex;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.NetworkJob.NetworkJob;

public class TestNetworkJob extends NetworkJob {

    public TestNetworkJob() {
        super("localhost", 23456);
    }

    @Override
    protected String requestString() {
        return "yo";
    }

    @Override
    protected void onResponse(String response) {
        Bot.getActionManager().add(new SendMessageAction(
                Bot.getChatGroupsManager().findGroupById("1"),
                response
        ));
    }
}
