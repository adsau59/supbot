package in.definex.core.Action;

import in.definex.core.Action.Core.CompleteActionCallback;
import in.definex.core.ChatGroup;
import in.definex.core.Console.Log;

/**
 * Action
 * abstract action class to be implemented for new actions
 * actions are actions that can be performed by the bot
 *
 * Created by adam_ on 30-11-2017.
 */
public abstract class Action {

    protected ChatGroup chatGroup;
    private CompleteActionCallback completeActionCallback;
    protected ActionManager actionManager;

    /**
     * Constructor
     *
     * @param actionManager ActionManager
     * @param chatGroup ChatGroup the action is referred to set null if you dont need
     */
    protected Action(ActionManager actionManager, ChatGroup chatGroup){
        this.chatGroup = chatGroup;
        this.actionManager = actionManager;
        completeActionCallback = () -> {};
    }

    /**
     * Sets callback that is called after the action has been performed
     * @param completeActionCallback callback interface
     */
    public void setCompleteActionCallback(CompleteActionCallback completeActionCallback){
        this.completeActionCallback = completeActionCallback;
    }

    /**
     * Logs start of action, performs the tasks,
     * notifies actionmanager that task has been completed
     * calls callback and logs that it has ended
     */
    public final void perform(){
        Log.m(this.getClass().getSimpleName() + " started");
        task();
        actionManager.finishedWork();
        completeActionCallback.callback();
        Log.m(this.getClass().getSimpleName() + " ended");

    }

    /**
     * Task performed by the Action
     */
    public abstract void task();

}
