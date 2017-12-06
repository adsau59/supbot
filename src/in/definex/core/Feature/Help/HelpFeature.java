package in.definex.core.Feature.Help;

import in.definex.core.Action.ActionManager;
import in.definex.core.ChatGroupsManager;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpFeature extends Feature {

    public static final String name = "Help";

    public HelpFeature(ActionManager actionManager, WebDriver driver, ChatGroupsManager chatGroupsManager, FeatureManager featureManager) {
        super(name, new Command[]{
            new HelpCommand(actionManager, driver, chatGroupsManager),
                new HelpFeatureCommand(actionManager, driver, featureManager),
                new HelpCommandCommand(actionManager, driver, featureManager)
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.helpDescription;
    }
}
