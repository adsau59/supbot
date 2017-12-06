package in.definex.core;

import in.definex.core.Action.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adam_ on 05-12-2017.
 */
public class Checker {

    private final int maxCheckingActions;
    private int currentCheckingAction;

    private List<Action> checkerActions;
    private CheckerResetCallback checkerResetCallback;

    public Checker(CheckerResetCallback checkerResetCallback, Action... checkerActions){
        this.checkerActions = new ArrayList<>();
        addCheckers(checkerActions);
        this.maxCheckingActions = checkerActions.length;
        currentCheckingAction = 0;
        this.checkerResetCallback = checkerResetCallback;
    }

    public void addCheckers(Action... actions){
        Collections.addAll(this.checkerActions, actions);
    }

    public Action getNextChecker(){
        if(currentCheckingAction>=maxCheckingActions) {
            currentCheckingAction = 0;
            checkerResetCallback.callback();
        }
        return checkerActions.get(currentCheckingAction++);
    }

    public interface CheckerResetCallback {

        void callback();

    }


}
