package in.definex.core.Feature.Accounts;

import in.definex.core.Action.ActionManager;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.String.FeatureAndCommandDescription;
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
                new DemoteCommand()
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.accountsDescription;
    }
}
