package in.definex.Scheduler;

import in.definex.Functions.Utils;
import in.definex.Functions.out;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * ScheduleTaskInitializer
 * Helps convert arguments (array of strings) into ScheduleTask object
 *
 * enables the bot to recognize and create ScheduleTask object from string,
 * The limitation of which being that
 * all the parameters of ScheduleTask to be created should have parameters only of type
 * String, Integer, Float, or Boolean, if its not it should have a static method: Object castFromString(String s);
 * which the ScheduleTaskInitializer uses to convert a string query to parameters object.
 *
 * Before you can use this, you must add the class object of ScheduleTask
 * in the scheduleTasks list by using Bot.getScheduleTaskInitializer().add() method
 * it can be done in  addThingsInBot method in Looper.ExtraLooperFunctions in the Main class
 *
 */
public class ScheduleTaskInitializer {

    /**
     * Stores all the class of ScheduleTask
     */
    private List<Class<? extends ScheduleTask>> scheduleTasks;

    /**
     * Constructor
     */
    public ScheduleTaskInitializer() {
        this.scheduleTasks = new ArrayList<>();
    }


    /**
     * Creates ScheduleTask object from array of strings
     *
     * @param taskout ScheduleTask Object created inside an out object
     * @param args array of strings
     * @return one of the Response enum
     */
    public Response getSceduleTask(out<ScheduleTask> taskout, String... args){

        Class taskClass = getScheduleTaskByName(args[0]);

        if(taskClass == null)
            return Response.NotFound;

        Class[] parameters;
        try {
            parameters = taskClass.getConstructors()[0].getParameterTypes();
        }catch (ArrayIndexOutOfBoundsException e){
            return Response.NoConstructorFound;
        }

        if(args.length - 1 != parameters.length) {
            return Response.IncorrectNumberOfArgs;
        }

        Object[] resultParams = new Object[parameters.length];

        for(int i=0;i<parameters.length;i++){
            Class p = parameters[i];


            if(p == Integer.class){
                resultParams[i] = Integer.valueOf(args[i+1]);
            }
            else if(p == Float.class){
                resultParams[i] = Float.valueOf(args[i+1]);
            }
            else if(p == Boolean.class){
                resultParams[i] = Boolean.valueOf(args[i+1]);
            }
            else if(p == String.class){
                resultParams[i] = args[i+1];
            }
            else if(Utils.hasStringCaster(p)){
                try {
                    Object obj = p.getMethod("castFromString",String.class).invoke(null, args[i+1]);

                    if(obj == null){
                        return Response.StringCasterReturnedNull;
                    }

                    resultParams[i] = obj;

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return Response.IncorrectParameterType;
                }
            }else {
                return Response.IncorrectParameterType;
            }

        }

        try {
            taskout.obj = (ScheduleTask) taskClass.getDeclaredConstructor(parameters).newInstance(resultParams);
            return Response.Success;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return Response.IncorrectParameterType;
        }


    }

    /**
     * Add ScheduleTask to the ScheduleTaskInitializer
     * @param taskClass Class to be added
     */
    @SafeVarargs
    public final void add(Class<? extends ScheduleTask>... taskClass){
        this.scheduleTasks.addAll(Arrays.asList(taskClass));
    }

    /**
     * Gets the ScheduleTask from scheduleTasks list
     * @param className name of the class
     * @return class object of target ScheduleTask
     */
    private Class<? extends ScheduleTask> getScheduleTaskByName(String className){

        for(Class<? extends ScheduleTask> a:scheduleTasks){
            if(a.getSimpleName().equals(className))
                return a;
        }

        return null;
    }


    /**
     * Enum of Responses for getScheduleTask Method
     */
    public enum Response {
        NotFound("ScheduleTask not found."),
        IncorrectNumberOfArgs("Incorrect number of arguments."),
        StringCasterReturnedNull("castFromString returned null"),
        IncorrectParameterType("Parameters should be either int, float, bool, string or should implement static method (Object castFromString(String s))"),
        NoConstructorFound("No public constructors found"),
        Success("ScheduleTask successfully created.");

        private final String response;

        Response(String response) {
            this.response = response;
        }

        public String getResponseString() {
            return response;
        }

        public boolean isSuccess(){
            return this == Success;
        }
    }
}
