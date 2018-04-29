package in.definex.Scheduler;

import java.io.Serializable;
/**
 * ScheduleTask
 *
 * Task that can be schedules using Schedule and ScheduleManager,
 * Must only contain members who is Implemented by Serializable or should have transient keyword.
 *
 */
public abstract class ScheduleTask implements Serializable {

    /**
     * Task to be performed on schedule.
     */
    protected abstract void task();

    /**
     * When the task is missed because the bot was off,
     * taskMissed is called instead of task method.
     *
     * Rescheduling the Schedule should be done explicitly over here, if needed.
     *
     * @param schedule schedule which is ran this ScheduleTask
     */
    protected abstract void taskMissed(Schedule schedule);

    /**
     * Ran by Schedule, to run the task method
     */
    void perform(){
        task();
    }

}
