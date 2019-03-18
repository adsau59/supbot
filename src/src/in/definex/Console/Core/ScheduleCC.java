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
 * ScheduleCC
 * Initialize schedule using Console Command, Used to create schedule for the first time,
 * after which it is stored in the database and loaded automatically when needed
 * Delete schedule directly from database, Used when ScheduleTask is changed, and the database is needed to be updated
 * See all the schedule tasks
 *
 * Usage:
 * schedule create SCHEDULE_NAME DATE SCHEDULETASK_CLASS_NAME [SCHEDULETASK_PARMS...]
 * schedule delete SCHEDULENAME
 * schedule db-only-delete SCHEDULENAME
 * schedule show
 *
 * Example:
 * schedule create GoodMorningSchedule1 2018.08.18.09.00.00 GoodMorningScheduleTask group1
 *
 */
public class ScheduleCC extends ConsoleCommand {

    public ScheduleCC() {
        super("schedule", -1);
    }

    @Override
    public String compute(String[] args) {

        switch (args[0]){

            case "create":



                try{
                    new SimpleDateFormat(DATE_PATTERN).parse(args[2]);
                } catch (ParseException e) {
                    return String.format("Enter date in %s format", DATE_PATTERN);
                }

                out<ScheduleTask> taskout = new out<>();
                ScheduleTaskInitializer.Response response = Bot.getScheduleTaskInitializer().getSceduleTask(taskout, Arrays.copyOfRange(args, 3, args.length));

                if(response != ScheduleTaskInitializer.Response.Success)
                    return response.getResponseString();

                Bot.getScheduleManager().add(
                        args[1],
                        new Schedule(taskout.obj, args[2])
                );

                return "Schedule has been created";


            case "delete":

                Bot.getScheduleManager().remove(args[1]);

                return "Schedule removed";

            case "show":

                String result = "";
                for(String s: Bot.getScheduleManager().getScheduleMap().keySet()){
                    result += s;
                }
                return result;

        }

        return "Invalid argument";

    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "schedule create SCHEDULE_NAME DATE SCHEDULETASK_CLASS_NAME [SCHEDULETASK_PARMS...]\n" +
                        "schedule delete SCHEDULENAME\n" +
                        "schedule db-only-delete SCHEDULENAME\n" +
                        "schedule show",
                "schedule create GoodMorningSchedule1 2018.08.18.09.00.00 GoodMorningScheduleTask group1",
                "Initialize schedule using Console Command, Used to create schedule for the first time,\n" +
                        "after which it is stored in the database and loaded automatically when needed\n" +
                        "Delete schedule directly from database, Used when ScheduleTask is changed, and the database is needed to be updated\n" +
                        "See all the schedule tasks"
        );
    }
}
