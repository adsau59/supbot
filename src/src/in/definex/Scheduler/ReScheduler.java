package in.definex.Scheduler;

import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * ReScheduler
 * Helps reschedule in taskMissed method in ScheduleTask
 *
 * to use call,
 * schedule.setDate(ReScheduler.RESCHEDULING_RULE(params));
 *
 * example
 * schedule.setDate(ReScheduler.AfterDaysSameTime(schedule.getDate(), 1));
 */
public class ReScheduler {


    /**
     * Reschedule on the same time as before, but after specified days,
     * used to reschedule daily, weekly, monthly, yearly tasks
     *
     * @param date previous data
     * @param days no of days to schedule after
     * @return new date
     */
    public static String AfterDaysSameTime(String date, int days){

        try {
            Calendar old = Calendar.getInstance();
            old.setTime(Schedule.DateFormat.parse(date));

            Calendar now = Calendar.getInstance();
            now.setTime(Date.from(Instant.now()));

            Calendar newCal = Calendar.getInstance();

            newCal.set(Calendar.YEAR, now.get(Calendar.YEAR));
            newCal.set(Calendar.MONTH, now.get(Calendar.MONTH));
            newCal.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) + days);

            newCal.set(Calendar.HOUR_OF_DAY, old.get(Calendar.HOUR_OF_DAY));
            newCal.set(Calendar.MINUTE, old.get(Calendar.MINUTE));
            newCal.set(Calendar.SECOND, old.get(Calendar.SECOND));

            return Schedule.DateFormat.format(newCal.getTime());


        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * Reschedule seconds after current time,
     * used for tasks which run every x secs, minutes
     *
     * @param secs secs to schedule task
     * @return new date
     */
    public static String AfterSecondsFromNow(int secs){
        Calendar now = Calendar.getInstance();
        now.setTime(Date.from(Instant.now()));

        Calendar newCal = Calendar.getInstance();

        newCal.set(Calendar.YEAR, now.get(Calendar.YEAR));
        newCal.set(Calendar.MONTH, now.get(Calendar.MONTH));
        newCal.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));

        newCal.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
        newCal.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
        newCal.set(Calendar.SECOND, now.get(Calendar.SECOND) + secs);

        return Schedule.DateFormat.format(newCal.getTime());
    }

}
