package in.definex.Scheduler;

import java.io.Serializable;
/**
 * ScheduleTask
 *
 * Task that can be schedules using Schedule and ScheduleManager,
 * Must only contain members who is Implemented by Serializable or should have transient keyword.
 *
 */
public interface ScheduleTask extends Serializable {

    /**
     * Task to be performed on schedule.
     */
    void task();

    /**
     * When the task is missed because the bot was off,
     * taskMissed is called instead of task method.
     */
    void taskMissed();


    /**
     * Schedule for next task
     *
     * @param lastScheduleDate date of last schedule
     * @return return empty string or null for one time task, else return date for next schedule
     */
    String getNextDate(String lastScheduleDate);

}
