package in.definex.core.Feature;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.SendMessageAction;
import in.definex.core.ChatGroup;
import in.definex.core.Client;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import in.definex.core.Client.Role;

/**
 * Created by adam_ on 01-12-2017.
 */
public abstract class Command {

    private ActionManager actionManager;
    private String keyword;
    private int noOfArgs; //-1 for variable arguments
    private Role minRole;
    private WebDriver driver;


    public Command(ActionManager actionManager, WebDriver driver, String keyword, int noOfArgs, Role minRole) {
        this.actionManager = actionManager;
        this.keyword = keyword;
        this.noOfArgs = noOfArgs;
        this.minRole = minRole;
        this.driver = driver;
    }

    private boolean doesHaveSufficiantArgs(String[] args) {
        return noOfArgs == -1 || args.length == noOfArgs;
    }

    protected abstract String compute(Client client, String[] args);

    private String checkAndCompute(Client client, String[] args){

        if(!client.getRole().hasPermission(minRole))
            return Strings.inSufficiantPermission;

        if(!doesHaveSufficiantArgs(args))
            return invalidArgumentsRespose();

        return compute(client, args);

    }

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

    protected class Helper{
        String template;
        String example;
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

    public String invalidArgumentsRespose(){
        return String.format(Strings.inCorrectArguments, getHelper().getTemplate(), keyword);
    }


}
