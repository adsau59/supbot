package in.definex.core.Action;

import in.definex.core.ChatGroup;


/**
 * Created by adam_ on 30-11-2017.
 */
public class MoveToChatAction extends Action {


    MoveToChatAction(ActionManager actionManager, ChatGroup chatGroup) {
        super(actionManager, chatGroup);
    }


    @Override
    public void task() {
        actionManager.gotoGroup(chatGroup);
    }
}
