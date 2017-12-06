package in.definex.core.Feature.GroupConfig;

import in.definex.core.Action.ActionManager;
import in.definex.core.ChatGroupsManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class RemoveFeatureCommand extends Command {

    public static final String name = "remove-feature";

    private FeatureManager featureManager;
    private ChatGroupsManager chatGroupsManager;

    public RemoveFeatureCommand(ActionManager actionManager, WebDriver driver, FeatureManager featureManager, ChatGroupsManager chatGroupsManager) {
        super(actionManager, driver, name, 1, Client.Role.CoAdmin);
        this.featureManager = featureManager;
        this.chatGroupsManager = chatGroupsManager;
    }

    @Override
    protected String compute(Client client, String[] args) {

        Feature newFeature = featureManager.findFeatureByName(args[0]);

        if(newFeature == null)
            return Strings.featureDoesntExisits;

        if(!client.getChatGroup(chatGroupsManager).hasFeature(newFeature))
            return Strings.featureHasNotBeenAdded;

        client.getChatGroup(chatGroupsManager).removeFeature(newFeature);

        return String.format(Strings.succesffullyAddedFeature, newFeature.getName());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.removeFeatureTemplate,
                FeatureAndCommandDescription.removeFeatureExample,
                FeatureAndCommandDescription.removeFeatureDescription
        );
    }

}
