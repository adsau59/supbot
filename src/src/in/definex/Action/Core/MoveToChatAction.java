package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.Client;


/**
 * MoveToChatAction
 *
 * used to move to a different chat
 *
 * Created by adam_ on 30-11-2017.
 */
public class MoveToChatAction extends Action {

    private Client client;
    public MoveToChatAction(Client chatGroup) {
        super();
        this.client = chatGroup;
    }


    @Override
    public boolean task() {
        Bot.getActionManager().gotoChat(client);
        return true;
    }
}
