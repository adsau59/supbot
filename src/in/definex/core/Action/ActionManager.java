package in.definex.core.Action;

import in.definex.core.ChatSystem.ChatGroup;
import in.definex.core.Functions.Utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ActionManager
 * Manages the actions that can be performed by the bot
 * When a new action is added to the actionManager,
 * it puts it in back of the que, and it is performed when it gets in front of the que
 *
 * Created by adam_ on 30-11-2017.
 */
public class ActionManager {

    /**
     * Action Que
     */
    private Queue<Action> que;

    /**
     * Group with is focused in whatsapp web currently
     */
    private ChatGroup activeGroup;

    /**
     * True when performing an action
     */
    private boolean working;

    /**
     * Constructor
     */
    public ActionManager(){
        que = new LinkedList<>();
        working=false;
    }

    /**
     * Add a new action to the ActionManager
     *
     * @param action action to be added
     */
    public void add(Action action){
        que.add(action);
    }


    /**
     * Pops next action in the que, and performs it
     * called in loop method in Looper class
     *
     */
    public void popAndPerform(){
        if(que.size()>0 && !working){
            working = true;
            que.remove().perform();

        }
    }

    /**
     * used to move to a target group to perform action
     *
     * @param chatGroup target group
     * @param delay true if want to wait after changing group
     */
    private void gotoGroup(ChatGroup chatGroup, boolean delay){

        if(chatGroup == activeGroup)
            return;

        chatGroup.getChatWebElement().click();
        activeGroup = chatGroup;

        if(delay)
            Utils.waitFor(2000);
    }

    /**
     * used to move to a target group to perform action
     * (waits for 2s after changing group)
     *
     * @param chatGroup target group
     */
    public void gotoGroup(ChatGroup chatGroup){
        gotoGroup(chatGroup, true);
    }

    /**
     * Returns the current open group chat in whatsapp web
     *
     * @return current open group chat
     */
    public ChatGroup getActiveChat(){
        return activeGroup;
    }

    /**
     * changed current working status to false
     */
    void finishedWork(){
        working = false;
    }

    /**
     * returns working status for the actionManager
     *
     * @return true if an action is being performed
     */
    public boolean isWorking() {
        return working;
    }

    /**
     * Used by Checker class to check if actionManager is free,
     * if its free then it gives it checker action to perform
     *
     * @return true if actionManger has no action to perform
     */
    public boolean hasPendingWork() {return  que.size() != 0;}
}
