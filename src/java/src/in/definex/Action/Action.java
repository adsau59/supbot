package in.definex.Action;

import in.definex.Bot;
import in.definex.Console.Log;

/**
 * Action
 * abstract action class to be implemented for new actions
 * actions are actions that can be performed by the bot
 *
 * Created by adam_ on 30-11-2017.
 */
public abstract class Action {

    private CompleteActionCallback completeActionCallback;

    /**
     * Constructor
     */
    protected Action(){
        completeActionCallback = (boolean s) -> {};
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
        boolean success = task();
        Bot.getActionManager().finishedWork();
        completeActionCallback.callback(success);
        Log.m(this.getClass().getSimpleName() + " ended");

    }

    /**
     * Task performed by the Action
     */
    public abstract boolean task();

}
