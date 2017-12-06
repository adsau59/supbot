package in.definex.core.Action;

import in.definex.core.ChatGroup;

/**
 * Created by adam_ on 30-11-2017.
 */
public abstract class Action {

    ChatGroup chatGroup;
    CompleteActionCallback completeActionCallback;
    ActionManager actionManager;

    Action(ActionManager actionManager, ChatGroup chatGroup){
        this.chatGroup = chatGroup;
        this.actionManager = actionManager;
        completeActionCallback = () -> {};
    }

    public void setCompleteActionCallback(CompleteActionCallback completeActionCallback){
        this.completeActionCallback = completeActionCallback;
    }

    public final void perform(){
        System.out.println(this.getClass().getSimpleName() + " started");
        task();
        actionManager.finishedWork();
        completeActionCallback.callback();
        System.out.println(this.getClass().getSimpleName() + " ended");

    }

    public abstract void task();

}
