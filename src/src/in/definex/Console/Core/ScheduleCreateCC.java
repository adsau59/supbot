package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.Console.ConsoleCommand;
import in.definex.Functions.out;
import in.definex.Scheduler.Schedule;
import in.definex.Scheduler.ScheduleTask;
import in.definex.Scheduler.ScheduleTaskInitializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static in.definex.Scheduler.Schedule.DATE_PATTERN;


/**
 *
 * ScheduleCreateCC
 * Initialize schedule using Console Command
 *
 * Used to create schedule for the first time,
 * after which it is stored in the database and loaded automatically when needed
 *
 * Usage:
 * schedule-create SCHEDULE_NAME DATE RESCHEDULE_TIME_IN_MS SCHEDULETASK_CLASS_NAME [SCHEDULETASK_PARMS...]
 *
 * Example:
 * schedule-create GoodMorningSchedule1 2018.08.18.09.00.00 86400000 GoodMorningScheduleTask group1
 *
 */
public class ScheduleCreateCC extends ConsoleCommand {

    public ScheduleCreateCC() {
        super("schedule-create", -1);
    }

    @Override
    protected String compute(String[] args) {

        /*
        0: Schedule name
        1: date
        2: reScheduleTime (-1 if dont need)
        3-n: ScheduleTask
         */

        long reScheduleTime;

        try{
            new SimpleDateFormat(DATE_PATTERN).parse(args[1]);
        } catch (ParseException e) {
            return String.format("Enter date in %s format", DATE_PATTERN);
        }

        try {
            reScheduleTime = Long.parseLong(args[2]);
        }catch (NumberFormatException e){
            return "Invalid re-schedule time.";
        }

        out<ScheduleTask> taskout = new out<>();
        ScheduleTaskInitializer.Response response = Bot.getScheduleTaskInitializer().getSceduleTask(taskout, Arrays.copyOfRange(args, 3, args.length));

        if(response != ScheduleTaskInitializer.Response.Success)
            return response.getResponseString();

        Bot.getScheduleManager().add(
                args[0],
                new Schedule(taskout.obj, args[1], reScheduleTime)
                );

        return "Schedule has been created";
    }
}
