package in.definex.core.Feature.Math;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import org.openqa.selenium.WebDriver;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 * Created by adam_ on 01-12-2017.
 */
public class SolveCommand extends Command {

    public static final String name = "solve";

    public SolveCommand(ActionManager actionManager, WebDriver driver) {
        super(actionManager, driver, name, -1, Client.Role.Member);
    }

    @Override
    protected String compute(Client client, String[] args) {

        //3 + ( 2 - 3 ) / 5

        if(args.length == 0)
            return invalidArgumentsRespose();

        String expr = "";
        for(String s: args){
            expr += s;
        }

        try {
            return  expr+"="+(new ScriptEngineManager().getEngineByName("JavaScript").eval(expr)).toString();
        } catch (ScriptException e) {
            return invalidArgumentsRespose();
        }

    }

    @Override
    public Command.Helper getHelper() {
        return new Helper(
                ";;solve [equation]",
                ";;solve 8+3/(9+1)",
                "Solves equation you give it to."
        );
    }


}
