package in.definex.Scheduler;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * ScheduleManager
 * Maintains a list of Schedule in name value BiMap
 */
public class ScheduleManager {

    private BiMap<String,Schedule> scheduleMap;

    /**
     * Loads the schedule map from database,
     * and then schedules them.
     */
    public void init()
    {
        scheduleMap = ScheduleDatabase.GetSchedules();

        for(String name:scheduleMap.keySet())
        {
            scheduleMap.get(name).schedule();
        }
    }

    public BiMap<String, Schedule> getScheduleMap() {
        return scheduleMap;
    }

    /**
     * Add a new Schedule.
     *
     * @param name name of the schedule, should not already exist.
     * @param schedule schedule object.
     * @return false, if name already exists.
     */
    public boolean add(String name,Schedule schedule)
    {
        if(scheduleMap.containsKey(name))
            return false;

        scheduleMap.put(name, schedule);
        schedule.schedule();

        ScheduleDatabase.SaveSchedule(name, schedule);

        return true;
    }

    /**
     * Returns the Schedule with name
     * @param name name of the schedule
     * @return Schedule object
     */
    public Schedule get(String name){
        return scheduleMap.get(name);
    }

    /**
     * Remove a schedule with name.
     * @param name name of the schedule to be removed.
     */
    public void remove(String name){
        scheduleMap.get(name).cancel();
        scheduleMap.remove(name);
        ScheduleDatabase.DeleteSchedule(name);
    }

    public void dbDelete(String name){
        ScheduleDatabase.DeleteSchedule(name);
    }

    /**
     * Remove schedule with schedule object.
     * @param schedule Schedule object to be removed
     */
    public void remove(Schedule schedule){
        remove(scheduleMap.inverse().get(schedule));
    }

    /**
     * Updates the database with the Schedule Object.
     * @param schedule schedule object to be updated.
     */
    public void notifyDBUpdate(Schedule schedule){
        ScheduleDatabase.UpdateSchedule(scheduleMap.inverse().get(schedule), schedule);
    }

    /**
     * Cancel all the schedules,
     * called when the bot is quit.
     */
    public void cancelAll(){
        for(String name:scheduleMap.keySet())
        {
            scheduleMap.get(name).cancel();
        }
    }


}
