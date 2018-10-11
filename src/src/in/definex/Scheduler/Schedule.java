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

    public static final String DATE_PATTERN = "yyyy.MM.dd.HH.mm.ss";
    public static final SimpleDateFormat DateFormat = new SimpleDateFormat(DATE_PATTERN);

    /**
     * Constructor
     *
     * Should be of pattern specifed by DATE_PATTERN (yyyy.MM.dd.HH.mm.ss)
     *
     * @param scheduleTask ScheduleTask to be ran
     * @param date date and time in format yyyy.MM.dd.HH.mm.ss to execute the ScheduleTask
     */
    public Schedule(ScheduleTask scheduleTask, String date) {
        try {
            DateFormat.parse(date);
        } catch (ParseException e) {
            Log.p(e);
            Log.e(String.format("Date is not in format: %s", DATE_PATTERN));
        }

        this.scheduleTask = scheduleTask;
        this.date = date;
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
                    scheduleTask.task();
                    checkIfFinishedThenUpdate();
                }
            }, delay);
        }else {
            scheduleTask.taskMissed();
            checkIfFinishedThenUpdate();
        }
    }

    /**
     * Update the database after the reScheduleing/ScheduleTask is finished
     */
    private void checkIfFinishedThenUpdate()
    {
        date = scheduleTask.getNextDate(date);

        if (getDelay() <= 0)
            Bot.getScheduleManager().remove(this);
        else {
            Bot.getScheduleManager().notifyDBUpdate(this);
            schedule();
        }
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
            long taskEpoc = DateFormat.parse(date).getTime();
            long now = new Date().getTime();

            return  taskEpoc - now;
        } catch (Exception e) { }

        return -1L;
    }

}
