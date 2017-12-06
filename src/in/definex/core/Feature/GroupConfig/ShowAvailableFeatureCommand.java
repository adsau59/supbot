package in.definex.core.Feature.GroupConfig;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class ShowAvailableFeatureCommand extends Command {

    public static final String name = "showallfeatures";

    FeatureManager featureManager;

    public ShowAvailableFeatureCommand(ActionManager actionManager, WebDriver driver, FeatureManager featureManager) {
        super(actionManager, driver, name, 0, Client.Role.Admin);
        this.featureManager = featureManager;
    }

    @Override
    protected String compute(Client client, String[] args) {
        return "Available features are :\n"+featureManager.getListOfFeatures();
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.showAvailTemplateExample,
                FeatureAndCommandDescription.showAvailTemplateExample,
                FeatureAndCommandDescription.showAvailDescription
        );
    }
}
