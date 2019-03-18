package in.definex.Action;

import in.definex.Bot;
import in.definex.Functions.Utils;
import in.definex.Functions.out;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * StringActionInitializer enables the bot to recognize and create Action object from string,
 * The limitation of which being that all the parameters of Action to be created should have parameters only of type
 * String, Integer, Float, or Boolean, if its not it should have a static method: Object castFromString(String s);
 * which the StringActionInitializer uses to convert a string query to parameters object.
 */
public class StringActionInitializer {

    private List<Class<? extends Action>> actions;

    /**
     * Constructor
     */
    public StringActionInitializer(){
        actions = new ArrayList<>();
    }

    /**
     * Creates an action and returns the action and response
     *
     * @param actionout out action that is returned
     * @param args String query to create the action
     * @return Response, showing if it successfully created the action, or the what error has occurred creating the action
     */
    public Response getAction(out<Action> actionout, String... args){

        Class actionClass = getActionByName(args[0]);

        if(actionClass == null) {
            return Response.ActionNotFound;
        }

        Class[] parameters;
        try {
            parameters = actionClass.getConstructors()[0].getParameterTypes();
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
            actionout.obj = (Action) actionClass.getDeclaredConstructor(parameters).newInstance(resultParams);
            return Response.Success;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return Response.IncorrectParameterType;
        }


    }

    /**
     * Directly calls the action instead of returning it
     *
     * @param args String query to create the action
     * @return Response, showing if it successfully called the action, or the what error has occurred creating the action
     */
    public Response callAction(String... args){
        out<Action> actionout = new out<>();
        Response response = getAction(actionout, args);

        if(response.isSuccess())
            Bot.getActionManager().add(actionout.obj);

        return response;
    }

    /**
     * Returns class object with name.
     *
     * @param className Name of the action class
     * @return Class object of the action1
     */
    private Class<? extends Action> getActionByName(String className){

        for(Class<? extends Action> a:actions){
            if(a.getSimpleName().equals(className))
                return a;
        }

        return null;
    }

    /**
     * Used to add more Action to StringActionInitializer
     * @param actions Action classes to add
     */
    @SafeVarargs
    public final void add(Class<? extends Action>... actions){
        this.actions.addAll(Arrays.asList(actions));
    }



    /**
     * Response enum, for getAction method
     */
    public enum Response {
        ActionNotFound("Action not found."),
        IncorrectNumberOfArgs("Incorrect number of arguments."),
        StringCasterReturnedNull("castFromString returned null"),
        IncorrectParameterType("Parameters should be either int, float, bool, string or should implement static method (Object castFromString(String s))"),
        NoConstructorFound("No public constructors found"),
        Success("Action successfully called.");

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
