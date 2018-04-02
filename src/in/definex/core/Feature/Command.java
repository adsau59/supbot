package in.definex.core.Feature;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.Core.SendMessageAction;
import in.definex.core.ChatGroup;
import in.definex.core.Client;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import in.definex.core.Client.Role;

/**
 * Command
 * command called by clients in chat,
 * to be implemented to create commands,
 * view core commands to learn how to implement your own commands
 *
 * Created by adam_ on 01-12-2017.
 */
public abstract class Command {

    private ActionManager actionManager;
    private String keyword;
    private int noOfArgs; //-1 for variable arguments
    private Role minRole;
    private WebDriver driver;

    /**
     * Constructor
     *
     * @param actionManager ActionManager
     * @param driver Selenium Web Driver
     * @param keyword keyword to be used by the client in chat
     * @param noOfArgs number of aruments to be used (-1 for variable noOfArguments)
     * @param minRole minimum role of the client to use the command
     */
    public Command(ActionManager actionManager, WebDriver driver, String keyword, int noOfArgs, Role minRole) {
        this.actionManager = actionManager;
        this.keyword = keyword;
        this.noOfArgs = noOfArgs;
        this.minRole = minRole;
        this.driver = driver;
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
        System.out.println("Using Command: "+this.getClass().getSimpleName());
        actionManager.add(new SendMessageAction(actionManager, chatGroup, driver, checkAndCompute(client, args)));
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
