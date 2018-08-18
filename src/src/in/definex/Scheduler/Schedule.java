package in.definex.Scheduler;
import in.definex.Bot;
import in.definex.Console.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Schedule
 *
 * Executes a ScheduleTask at specified date and time.
 *
 */
public class Schedule implements Serializable {

    private ScheduleTask scheduleTask;
    private String date;
    private transient Timer timer;
    private Long reScheduleDeltaTime;
    public static final String DATE_PATTERN = "yyyy.MM.dd.HH.mm.ss";

    /**
     * Constructor
     *
     * Should be of pattern specifed by DATE_PATTERN (yyyy.MM.dd.HH.mm.ss)
     *
     * @param scheduleTask ScheduleTask to be ran
     * @param date date and time in format yyyy.MM.dd.HH.mm.ss to execute the ScheduleTask
     * @param reScheduleDeltaTime time in ms to re-scheduled task (optional).
     */
    public Schedule(ScheduleTask scheduleTask, String date, Long reScheduleDeltaTime) {
        try {
            new SimpleDateFormat(DATE_PATTERN).parse(date);
        } catch (ParseException e) {
            Log.p(e);
            Log.e(String.format("Date is not in format: %s", DATE_PATTERN));
        }

        this.scheduleTask = scheduleTask;
        this.date = date;
        this.reScheduleDeltaTime = reScheduleDeltaTime;
    }
    public Schedule(ScheduleTask scheduleTask, String date){
        this(scheduleTask,date,-1L);
    }


    /**
     * Getters
     */
    public ScheduleTask getScheduleTask() {
        return scheduleTask;
    }
    public String getDate() {
        return date;
    }
    public Long getReScheduleDeltaTime() {
        return reScheduleDeltaTime;
    }
    public void setDate(String date) {
        this.date = date;
    }
    private Timer getTimer()
    {
        if(timer == null)
            timer = new Timer(scheduleTask.getClass().getSimpleName());

        return timer;
    }

    /**
     * Setters
     */
    public void setReScheduleDeltaTime(Long reScheduleDeltaTime) {
        this.reScheduleDeltaTime = reScheduleDeltaTime;
    }


    /**
     * Called by ScheduleManager to schedule the task.
     * runs taskMissed of ScheduleTask if the schedule time has passed.
     */
    void schedule()
    {
        long delay = getDelay();

        if(delay>0){
            getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    scheduleTask.perform();
                    nextSchedule();
                    checkIfFinishedThenUpdate();
                }
            }, delay);
        }else {
            scheduleTask.taskMissed(this);
            checkIfFinishedThenUpdate();
        }
    }

    /**
     * Reschedule the task, if reScheduleDeltaTime is greater than zero
     */
    public void nextSchedule(){

        if(reScheduleDeltaTime <= 0)
            return;

        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                scheduleTask.perform();
                nextSchedule();
            }
        }, reScheduleDeltaTime);
        updateDateFromDelay(reScheduleDeltaTime);
    }

    /**
     * Update the database after the reScheduleing/ScheduleTask is finished
     */
    private void checkIfFinishedThenUpdate()
    {
        if (getDelay() <= 0)
            Bot.getScheduleManager().remove(this);
        else
            Bot.getScheduleManager().notifyUpdate(this);
    }

    /**
     * Cancel the timer threads.
     */
    void cancel()
    {
        timer.cancel();
    }

    /**
     * @return time remaining in ms for the ScheduleTask
     */
    private Long getDelay()
    {
        try {
            long taskEpoc = new SimpleDateFormat(DATE_PATTERN).parse(date).getTime();
            long now = new Date().getTime();

            return  taskEpoc - now;
        } catch (ParseException e) { }

        return -1L;
    }

    /**
     * Calculates the target date from time difference in ms.
     * @param delay time difference in ms from now till ScheduleTask.
     */
    private void updateDateFromDelay(long delay)
    {
        long epoch = new Date().getTime() + delay;
        date = new SimpleDateFormat(DATE_PATTERN).format(new Date(epoch));
    }

}
