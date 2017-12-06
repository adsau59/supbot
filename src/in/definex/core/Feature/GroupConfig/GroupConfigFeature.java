package in.definex.core.Feature.GroupConfig;

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
public class GroupConfigFeature extends Feature {

    public static final String name = "GroupConfig";

    public GroupConfigFeature(ActionManager actionManager, WebDriver webDriver, FeatureManager featureManager, ChatGroupsManager chatGroupsManager) {
        super(name, new Command[]{
            new ShowAvailableFeatureCommand(actionManager, webDriver, featureManager),
            new AddFeatureCommand(actionManager, webDriver, featureManager, chatGroupsManager),
            new RemoveFeatureCommand(actionManager, webDriver, featureManager, chatGroupsManager),
                new RoleCommand(actionManager, webDriver)
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.groupConfigDescription;
    }
}
