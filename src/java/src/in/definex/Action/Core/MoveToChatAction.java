package in.definex.Action.Core;

import in.definex.Action.Action;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;


/**
 * MoveToChatAction
 *
 * used to move to a different chat
 *
 * Created by adam_ on 30-11-2017.
 */
public class MoveToChatAction extends Action {

    private ChatGroup chatGroup;
    public MoveToChatAction(ChatGroup chatGroup) {
        super();
        this.chatGroup = chatGroup;
    }


    @Override
    public boolean task() {
        Bot.getActionManager().gotoGroup(chatGroup);
        return true;
    }
}
