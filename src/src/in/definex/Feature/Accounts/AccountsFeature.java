package in.definex.Feature.Accounts;

import in.definex.Action.ActionManager;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.String.FeatureAndCommandDescription;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 02-12-2017.
 */
public class AccountsFeature extends Feature {

    public static String name = "Accounts";

    public AccountsFeature() {
        super(name, new Command[]{
                new RegisterCommand(),
                new PromoteCommand(),
                new DemoteCommand(),
                new SetAliasCommand(),
                new WhoIsCommand()
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.accountsDescription;
    }
}
