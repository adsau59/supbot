package in.definex.core.Action.Core;

import in.definex.core.Action.Action;
import in.definex.core.Bot;
import in.definex.core.ChatSystem.ChatGroup;


/**
 * Created by adam_ on 30-11-2017.
 */
public class MoveToChatAction extends Action {


    MoveToChatAction(ChatGroup chatGroup) {
        super(chatGroup);
    }


    @Override
    public void task() {
        Bot.getActionManager().gotoGroup(chatGroup);
    }
}
