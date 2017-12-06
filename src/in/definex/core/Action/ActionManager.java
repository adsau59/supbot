package in.definex.core.Action;

import in.definex.core.ChatGroup;
import in.definex.core.Functions.Utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by adam_ on 30-11-2017.
 */
public class ActionManager {

    private Queue<Action> que;
    private ChatGroup activeGroup;
    private boolean working;

    public ActionManager(){
        que = new LinkedList<>();
        working=false;
    }

    public void add(Action action){
        que.add(action);
    }


    public void popAndPerform(){
        if(que.size()>0 && !working){
            working = true;
            que.remove().perform();

        }
    }

    public void gotoGroup(ChatGroup chatGroup, boolean delay){

        if(chatGroup == activeGroup)
            return;

        chatGroup.getChatWebElement().click();
        activeGroup = chatGroup;

        if(delay)
            Utils.waitFor(2000);
    }

    public void gotoGroup(ChatGroup chatGroup){
        gotoGroup(chatGroup, true);
    }

    public ChatGroup getActiveChat(){
        return activeGroup;
    }

    public void finishedWork(){
        working = false;
    }

    public boolean isWorking() {
        return working;
    }

    public boolean hasPendingWork() {return  que.size() != 0;}
}
