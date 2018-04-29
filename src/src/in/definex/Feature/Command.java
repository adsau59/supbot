package in.definex.Feature;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;
import in.definex.String.Strings;

import in.definex.ChatSystem.Client.Role;

/**
 * Command
 * command called by clients in chat,
 * to be implemented to create commands,
 * view core commands to learn how to implement your own commands
 *
 * Created by adam_ on 01-12-2017.
 */
public abstract class Command {

    private String keyword;
    private int noOfArgs; //-1 for variable arguments
    private Role minRole;

    /**
     * Constructor
     *
     * @param keyword keyword to be used by the client in chat
     * @param noOfArgs number of aruments to be used (-1 for variable noOfArguments)
     * @param minRole minimum role of the client to use the command
     */
    public Command(String keyword, int noOfArgs, Role minRole) {
        this.keyword = keyword;
        this.noOfArgs = noOfArgs;
        this.minRole = minRole;
    }

    /**
     * Checks if the client have sufficient arguments
     * @param args arguments
     * @return
     */
    private boolean doesHaveSufficiantArgs(String[] args) {
        return noOfArgs == -1 || args.length == noOfArgs;
    }

    /**
     * Computes command and returns result to be sent in chat
     * abstract method that has to be defined during implimentation
     *
     * @param client client that used the command
     * @param args arguments used by the client
     * @return result to be sent in chat
     */
    protected abstract String compute(Client client, String[] args);

    /**
     * check role, args, then runs compute
     *
     * @param client client that used the command
     * @param args arguments used by the client
     * @return result to be sent in chat
     */
    private String checkAndCompute(Client client, String[] args){

        if(!client.getRole().hasPermission(minRole))
            return Strings.inSufficiantPermission;

        if(!doesHaveSufficiantArgs(args))
            return invalidArgumentsRespose();

        return compute(client, args);

    }

    /**
     * ran by outside the class, calculates result and create send message action which is sent to action manager
     *
     * @param chatGroup chat group to which the result is sent
     * @param client client that used the command
     * @param args arguments used by the client
     */
    public void proccess(ChatGroup chatGroup, Client client, String[] args){
        Log.d("Using Command",this.getClass().getSimpleName());
        Bot.getActionManager().add(new SendMessageAction(chatGroup, checkAndCompute(client, args)));
    }

    public String getKeyword() {
        return keyword;
    }
    public Role getMinRole() {
        return minRole;
    }

    public abstract Helper getHelper();

    /**
     * Helper
     * Contains information that is displayed when client uses 'help' command
     * FeatureAndCommandDescription has strings which is displayed for each command
     *
     */
    protected class Helper{
        /**
         * Template of the command
         */
        String template;
        /**
         * example of the command
         */
        String example;
        /**
         * discription of the command
         */
        String description;

        public Helper(String template, String example, String description) {
            this.template = template;
            this.example = example;
            this.description = description;
        }

        public String getTemplate() {
            return template;
        }
        public String getExample() {
            return example;
        }
        public String getDescription() {
            return description;
        }
    }

    /**
     * Creates response for invalid argument
     * @return string which is to be sent to the group
     */
    public String invalidArgumentsRespose(){
        return String.format(Strings.inCorrectArguments, getHelper().getTemplate(), keyword);
    }


}
