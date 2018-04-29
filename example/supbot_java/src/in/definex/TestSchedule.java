package in.definex;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Scheduler.Schedule;
import in.definex.Scheduler.ScheduleTask;

public class TestSchedule extends ScheduleTask{

    private String groupId;
    private String message;
    private String missedMessage;

    public TestSchedule(String groupId, String message, String missedMessage) {
        this.groupId = groupId;
        this.message = message;
        this.missedMessage = missedMessage;
    }

    protected void task()
    {
        Bot.getActionManager().add(new SendMessageAction(Bot.getChatGroupsManager().findGroupById(groupId), message));
    }

    @Override
    protected void taskMissed(Schedule schedule) {
        Bot.getActionManager().add(new SendMessageAction(Bot.getChatGroupsManager().findGroupById(groupId), missedMessage));
    }

}