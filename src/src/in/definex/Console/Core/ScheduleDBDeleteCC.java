package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.Console.ConsoleCommand;

public class ScheduleDBDeleteCC extends ConsoleCommand {


    /**
     * ScheduleDBDeleteCC
     * Delete schedule directly from database
     *
     * Used when ScheduleTask is changed, and the database is needed to be updated
     *
     * Usage:
     * schedule-db-delete SCHEDULENAME
     *
     * example:
     * schedule-db-delete GoodMorningSchedule1
     */
    public ScheduleDBDeleteCC() {
        super("schedule-db-delete", 1);
    }

    @Override
    protected String compute(String[] args) {
        Bot.getScheduleManager().dbDelete(args[0]);

        return "Schedule DB Delete called";
    }
}
